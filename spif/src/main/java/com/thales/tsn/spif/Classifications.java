package com.thales.tsn.spif;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

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
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.spi.Contract;
import org.xml.sax.SAXException;
import org.xmlspif.spif.MarkingCode;
import org.xmlspif.spif.MarkingData;
import org.xmlspif.spif.ObjectIdData;
import org.xmlspif.spif.SPIF;
import org.xmlspif.spif.SecurityClassification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;  
@jakarta.ws.rs.Path("/classifications")
public class Classifications {
	
	@Context private ServletContext servletContext;
	@Context private HttpServletRequest httpServletRequest;
	@Context private HttpServletResponse httpServletResponse;
	
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final Logger log = LogManager.getLogger(Classifications.class);
	private SPIF spif;	



	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//curl --header "Accept: application/json" --header "Content-Type: application/json" http://localhost:8080/spif/classifications
	public Response JSONClassifs() throws IOException, JAXBException {
		
		log.info("Get JSON classifications");
		spif = LocalSPIF.get(servletContext);
		StringWriter sw = new StringWriter();
		ArrayNode arrayNode = mapper.createArrayNode();
		for(SecurityClassification classif:spif.getSecurityClassifications().getSecurityClassification()) {
			SimpleSecurityClassification myClassif = new SimpleSecurityClassification(classif);
			arrayNode.add(myClassif.toJsonNode());
		} // for
		mapper.writeValue(sw, arrayNode);
		log.trace("Get JSON classifications end");
		return Response.status(Response.Status.OK).entity(arrayNode).build();
	} // JSONClassifs
	
	// Retrieve a classification
	private SecurityClassification searchClassification(String name) throws NoSuchElementException {
		for(SecurityClassification currentClassif:spif.getSecurityClassifications().getSecurityClassification())
			if (currentClassif.getName().equals(name)) return (SecurityClassification) currentClassif;
		throw  new NoSuchElementException(String.format("Invalid classification {}", name));	
	}

	//curl --header "Accept: application/json" --header "Content-Type: application/json" http://localhost:8080/spif/classifications/<classification name>
	@GET
	@Path("/{cl}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response JSONClassif(@PathParam("cl") String classif) {
		log.info("Get JSON classification: {}", classif);
		try { 
			spif = LocalSPIF.get(servletContext);
			SecurityClassification fullClassifObject = searchClassification(classif);
			log.trace("Get classification: {} end", classif);
			return Response.status(Response.Status.OK).entity(fullClassifObject).build(); 
		} catch (NoSuchElementException e) {
			log.info("Error : no such classification {}", classif);
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (JAXBException e) {
			log.info("SPIF Init. error {} (json)", classif);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	} // JSONClassif
}
