package thales.tsn.iam.spif;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;  
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.*;

import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

@Path("/service")

public class Service {
	private static final Logger log = LogManager.getLogger(Service.class);  
	JAXBContext jc;
	Marshaller marshaller;
	
	private spifContext ctxt = new spifContext();
	@Context ServletContext context;
	//SpifXml spifXml = null;
	@PostConstruct
	public void init() throws ParserConfigurationException, SAXException, IOException, JAXBException { 
		jc = JAXBContext.newInstance(spifContext.class);
		marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);       
	    marshaller.setProperty(MarshallerProperties.MEDIA_TYPE,"application/json");
		marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
		  
		 ctxt.setClassName(this.getClass().getSimpleName());
		 if (context.getInitParameter("spifSchema") == null) throw new NotFoundException("Initialization failed (no schema/xsd declared) for class " + ctxt.getClassName());
		 ctxt.setXsd(context.getInitParameter("spifSchema"));
		 log.debug("Schema: " +ctxt.getXsd());
		 
		 
	     if (context.getInitParameter("spifName") == null)  throw new NotFoundException("Initialization failed (no spif declared) for class " + ctxt.getClassName());
	     ctxt.setSpif(context.getInitParameter("spifName"));
	     log.debug("SPIF: " +ctxt.getSpif());
    
	     String spifPath = new File(ctxt.getSpif()).getAbsolutePath();
	     if (File.separatorChar != '/') spifPath = spifPath.replace(File.separatorChar, '/');
	     if (!spifPath.startsWith("/")) spifPath = "/" + spifPath;
 	     
	     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	     DocumentBuilder db = dbf.newDocumentBuilder();
	     Document doc = db.parse(new File(ctxt.getSpif()));
	     doc.getDocumentElement().normalize();
	     NodeList list = doc.getElementsByTagName("securityClassification");
	     for (int temp = 0; temp < list.getLength(); temp++) {
	    	 Node node = list.item(temp);
	    	 if (node.getNodeType() != Node.ELEMENT_NODE) continue;
	    	 Element element = (Element) node;
	    	 log.debug("classif: " + element.getAttribute("name"));
	     } // for
	 } // init

	  @GET  
	  @Consumes(MediaType.TEXT_PLAIN)  
	  @Produces(MediaType.TEXT_PLAIN)
	  public String PlainTextVersion() {  
		  return ctxt.getClassName() + ": " + ctxt.getVersion(); 
	  } // PlainTextVersion
	  
	  
	  // This method is called if XML is requested  
	  @GET  
	  @Consumes(MediaType.APPLICATION_XML)  
	  @Produces(MediaType.APPLICATION_XML)
	  //curl --header "Accept: application/xml" --header "Content-Type: application/xml" http://localhost:8080/spif/service
	  public Response XMLVersion() {
		log.info("GET XML");
		return Response.status(Response.Status.OK).entity(ctxt).build(); 
	  } // XMLVersion 
	  
	  @POST
	  @Consumes(MediaType.APPLICATION_XML)
	  @Produces(MediaType.APPLICATION_XML)
	  // curl -X POST -H "Accept: application/xml" -H "Content-Type: application/xml"  -d "<EncryptRequest><target>x@y</target><data>xx</data></EncryptRequest>" http://localhost:8080/spif/service
	  public Response XmlPost(EncryptRequest req) {
		  log.info("POST XML");
		  log.debug(req.toString());
		  log.debug("JSON: " + req.toJson());
		  return Response.status(Response.Status.OK).entity(ctxt).build();
	  } //XmlPost
	  
	  
	  // This method is called if HTML is requested  
	  @GET  
	  @Consumes(MediaType.TEXT_HTML)
	  @Produces(MediaType.TEXT_HTML)  
	  public String HtmlVersion() {  
		  log.info("GET HTML");
		  return "<html> " + "<title>" + this.getClass().getSimpleName() + "</title>"  + "<body>"
	        + "<h1>" + ctxt.getClassName() + ": " + ctxt.getVersion() + "</h1>"
	        + "<h2>Schema: " +  ctxt.getXsd() + "</h2>"
	        + "<h2>File Name: " +  ctxt.getSpif() + "</h2>" 
	        //+ spifXml.dumpDocument()
	        + "</body>" + "</html> ";  
	  } // HtmlVersion
	  
	// This method is called if JSON is requested
	  @GET
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  //curl --header "Accept: application/json" --header "Content-Type: application/json" http://localhost:8080/spif/service
	public Response JsonVersion() throws JsonProcessingException, JAXBException { 
		  log.info("GET JSON");
        return Response.status(Response.Status.OK).entity(ctxt).build();
	} // JsonVersion
	  
	  @POST
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  // curl -X POST -H "Accept: application/json" -H "Content-Type: application/json"  -d '{ "target": "x@y", "data":"xx" }' http://localhost:8080/spif/service
	  public Response JsonPost(EncryptRequest req) {
		  log.info("POST JSON");
		  return Response.status(Response.Status.OK).entity(ctxt).build();
	  } //JsonPost
	  
	
}
