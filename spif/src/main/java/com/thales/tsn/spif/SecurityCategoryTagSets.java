package com.thales.tsn.spif;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
		log.info("SPIF Init. error (Get Security Category Tag Sets)");
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
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
public Response SecurityCategoryTagSet(@PathParam("id") String id)  {
	log.info("Get Security Category Tag Set {}",id);
	try {
		spif = LocalSPIF.get(servletContext);
	} catch (javax.xml.bind.JAXBException e) {
		log.info("SPIF Init. error (Get Security Category Tag Set {})",id);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	List<SimpleSecurityCategoryTagSet> myList = new ArrayList<SimpleSecurityCategoryTagSet>();
	for(SecurityCategoryTagSet currentSecurityCategoryTagSet:spif.getSecurityCategoryTagSets().getSecurityCategoryTagSet()) {
		if (!currentSecurityCategoryTagSet.getId().equals(id)) continue; // not the object id we are looking for
		log.trace("Security Category Tag Set {} - {}", currentSecurityCategoryTagSet.getId(),currentSecurityCategoryTagSet.getName());
		for (SecurityCategoryTag categoryTag:currentSecurityCategoryTagSet.getSecurityCategoryTag()) {
			log.trace("Security Category Tag: {} ({})", categoryTag.getName(), categoryTag.getTagType());

		}
		
		return Response.status(Response.Status.OK).entity(currentSecurityCategoryTagSet.getSecurityCategoryTag()).build();
	} // for
	
	
	log.trace("Get Security Category Tag Set {} end",id);
	return Response.status(Response.Status.OK).entity("").build();
} // SecurityCategoryTagSet


} // class SecurityCategoryTagSets 
