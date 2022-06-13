package com.thales.tsn.spif;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.annotation.JacksonFeatures;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.xmlspif.spif.MarkingCode;
import org.xmlspif.spif.MarkingData;
import org.xmlspif.spif.SPIF;
import org.xmlspif.spif.SecurityCategoryTag;
import org.xmlspif.spif.SecurityCategoryTagSet;
import org.xmlspif.spif.TagCategory;
import org.xmlspif.spif.TagType;

import com.fasterxml.jackson.databind.SerializationFeature;

@Path("/securitytagsets")
public class SecurityCategoryTagSets {
	@Context private ServletContext servletContext;
	@Context HttpServletRequest httpServletRequest;
	@Context private HttpServletResponse httpServletResponse;
	private static final Logger log = LogManager.getLogger(SecurityCategoryTagSets.class);
	private static SPIF spif;	



@GET
@Produces(MediaType.APPLICATION_JSON)
public Response SecurityCategoryTagSets()  {
	log.info("Get Security Category Tag Sets");
	try {
		spif = LocalSPIF.get(servletContext);
	} catch (javax.xml.bind.JAXBException e) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(String.format("SPIF Init. error (Get Security Category Tag Set)")).build();
	}
	
	if (spif.getSecurityCategoryTagSets().getSecurityCategoryTagSet().size() < 1) {
		log.trace("Get  Security Category Tag Sets end - No Tag set");
		return Response.status(Response.Status.OK).entity("").build();
	}
	
	// This will work only if one <securityCategoryTag> follows <securityCategoryTagSet> clauses
	// (should be the case if the xsd is conformant)
	List<SimpleSecurityCategoryTagSet> myList = new ArrayList<SimpleSecurityCategoryTagSet>();
	for(SecurityCategoryTagSet currentSecurityCategoryTagSet:spif.getSecurityCategoryTagSets().getSecurityCategoryTagSet()) {
		TagType myTagType = currentSecurityCategoryTagSet.getSecurityCategoryTag().get(0).getTagType();
		if (myTagType == null) myTagType =  TagType.fromValue("notApplicable");
		
		SimpleSecurityCategoryTagSet ssc = new SimpleSecurityCategoryTagSet(
				currentSecurityCategoryTagSet.getName(), currentSecurityCategoryTagSet.getId(), myTagType);
		myList.add(ssc);
	} // for
	
	log.trace("Get Security Category Tag Sets end");
	return Response.status(Response.Status.OK).entity(myList).build();
}



@GET
@Path("/{id}")
@Produces(MediaType.APPLICATION_JSON)
@JacksonFeatures(serializationEnable =  { SerializationFeature.INDENT_OUTPUT })
public Response SecurityCategoryTagSet(@PathParam("id") String id)  {
	log.info("Get Security Category Tag Set {}",id);
	try {
		spif = LocalSPIF.get(servletContext);
	} catch (javax.xml.bind.JAXBException e) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(String.format("SPIF Init. error (Get Security Category Tag Set {})",id)).build();
	}
	
	List<SimpleTagCategory> myList = new ArrayList<SimpleTagCategory>();
	for(SecurityCategoryTagSet currentSecurityCategoryTagSet:spif.getSecurityCategoryTagSets().getSecurityCategoryTagSet()) {
		if (!currentSecurityCategoryTagSet.getId().equals(id)) continue; // not the object id we are looking for
		for (SecurityCategoryTag categoryTag:currentSecurityCategoryTagSet.getSecurityCategoryTag()) {
			log.trace("Security Category Tag Set: {} - {} ({})", categoryTag.getName(), currentSecurityCategoryTagSet.getId(), categoryTag.getTagType());
			for (TagCategory tc:categoryTag.getTagCategory()) {
				log.trace("Tag Category: {} - {} ({})", tc.getName(), tc.getLacv(), currentSecurityCategoryTagSet.getId());
				SimpleTagCategory simpleTagCategory = new SimpleTagCategory(tc.getName(),Integer.parseInt(tc.getLacv()),currentSecurityCategoryTagSet.getId());
				myList.add(simpleTagCategory);
			}
		}
		return Response.status(Response.Status.OK).entity(myList).build();
	} // for
	
	
	log.trace("Get Security Category Tag Set {} end",id);
	return Response.status(Response.Status.OK).entity("").build();
} // SecurityCategoryTagSet

@GET
@Path("/{id}/{lacv}")
@Produces(MediaType.APPLICATION_JSON)
public Response TagCategory(@PathParam("id") String id, @PathParam("lacv") Integer lacv)  {
	try { spif = LocalSPIF.get(servletContext); }
	catch (javax.xml.bind.JAXBException e) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(String.format("SPIF Init. error (Get Security Category Tag Set {})",id)).build();
	}
	
	//<securityCategoryTagSets>
	//<securityCategoryTagSet name="Special Category Designators" id="1.3.26.1.4.1">
		//<securityCategoryTag name="Special Category Designators" tagType="restrictive">
			//<tagCategory name="ATOMAL" lacv="1">
				//<markingData phrase="ATOMAL" xml:lang="en">
					//<code>pageTopBottom</code>
				//</markingData>
				//<markingData phrase="ATOMAL" xml:lang="fr">
					//<code>pageTopBottom</code>
				//</markingData>

	
	for(SecurityCategoryTagSet currentSecurityCategoryTagSet:spif.getSecurityCategoryTagSets().getSecurityCategoryTagSet()) {
		if (!currentSecurityCategoryTagSet.getId().equals(id)) continue; // not the object id we are looking for
		log.trace("Security Category Tag Set found: {} - {}", currentSecurityCategoryTagSet.getName(),currentSecurityCategoryTagSet.getId());
		for (SecurityCategoryTag categoryTag:currentSecurityCategoryTagSet.getSecurityCategoryTag()) {
			for (TagCategory tc:categoryTag.getTagCategory()) {
				if (lacv != Integer.parseInt(tc.getLacv())) continue;
				log.trace("Tag Category found: {} - {}", tc.getName(), tc.getLacv());
				SimpleTagCategory simpleTagCategory = new SimpleTagCategory(tc.getName(),Integer.parseInt(tc.getLacv()),currentSecurityCategoryTagSet.getId());
				return Response.status(Response.Status.OK).entity(tc.getMarkingData()).build();
			}
			return Response.status(Response.Status.NOT_FOUND).entity(String.format("Tag Category found: {} - {}",id,lacv)).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(String.format("Security Category Tag Set not found: {}",id)).build(); // should not happen
	} // for
	return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(String.format("Internal error: {} - {}",id,lacv)).build();  // should not happen
} // TagCategory

} // class SecurityCategoryTagSets 
