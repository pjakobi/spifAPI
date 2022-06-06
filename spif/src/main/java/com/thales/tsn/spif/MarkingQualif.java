package com.thales.tsn.spif;

import java.util.NoSuchElementException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xmlspif.spif.MarkingQualifier;
import org.xmlspif.spif.SPIF;


@Path("/markingqualif")
public class MarkingQualif {
	@Context private ServletContext servletContext;
	@Context HttpServletRequest httpServletRequest;
	@Context private HttpServletResponse httpServletResponse;
	private static final Logger log = LogManager.getLogger(MarkingQualifier.class);
	private static SPIF spif;	

//curl --header "Accept: application/json" --header "Content-Type: application/json" http://localhost:8080/spif/service/markingqualif
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response JSONMarkingQualifier() throws NoSuchElementException {
	log.info("Get Marking Qualifier");
	try {
		spif = LocalSPIF.get(servletContext);
	} catch (javax.xml.bind.JAXBException e) {
		log.info("SPIF Init. error (Get Marking Qualifier)");
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	if (spif.getSecurityClassifications().getMarkingQualifier().size() < 1) {
		log.trace("Get Marking Qualifier end - No Marking Qualifier");
		return Response.status(Response.Status.OK).entity("").build();
	}
	log.trace("Get Marking Qualifier end");
	return Response.status(Response.Status.OK).entity(spif.getSecurityClassifications().getMarkingQualifier()).build();
} // JSONMarkingQualifier

}
