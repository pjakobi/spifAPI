package com.thales.tsn.spif;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.naming.InvalidNameException;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xmlspif.spif.ObjectIdData;
import org.xmlspif.spif.SPIF;


@jakarta.ws.rs.Path("/geninfo")
public class GenlInfo {
	@Context private ServletContext servletContext;
	@Context HttpServletRequest httpServletRequest;
	@Context private HttpServletResponse httpServletResponse;
	private static final Logger log = LogManager.getLogger(GenlInfo.class);
	private SPIF spif;	


	
private String jsonSetValue(String inLabel, String inValue, String inEnding) { // shorthand
		if (inValue != null) return "\"" + inLabel + "\"" + ":" + "\"" + inValue + "\"" + inEnding;	
		log.trace("Get JSON General SPIF Info. : no {} ", inLabel);
		return "";
} // jsonSetValue

	
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response JSONGeneralSpifInfo() {
	log.info("Get JSON General SPIF Information");
	try {
		spif = LocalSPIF.get(servletContext);
	} catch (javax.xml.bind.JAXBException e) {
		log.info("SPIF Init. error (Gen. Info.)");
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	GeneralInfo result;
	try {
		result = new GeneralInfo(spif);
	} catch (InvalidNameException e) {
		log.info("SPIF Init. error (Invalid originator DN)");
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	log.trace("Get JSON General SPIF Info. end");
	return Response.status(Response.Status.OK).entity(result).build();
} // JSONGeneralSpifInfo
}
