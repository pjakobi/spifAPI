package com.thales.tsn.spif;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;  
import org.xmlspif.spif.SPIF;
import java.util.List;
import java.util.NoSuchElementException;


import org.xmlspif.spif.SecurityCategoryTag;
import org.xmlspif.spif.SecurityCategoryTagSet;
import org.xmlspif.spif.MarkingCode;
import org.xmlspif.spif.MarkingData;
import org.xmlspif.spif.TagCategory;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.dataformat.xml.XmlMapper;



@Path("/service")

public class Service extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(Service.class);
	
@Context private ServletContext servletContext;
@Context HttpServletRequest httpServletRequest;
@Context private HttpServletResponse httpServletResponse;
@Context private ServletConfig config;
@Context String spifName2;

private SPIF spif;	
private static final ObjectMapper mapper = new ObjectMapper();
private JAXBContext jaxbContext;
private String spifName;




// Ping 
@GET  
@Consumes(MediaType.TEXT_PLAIN)  
@Produces(MediaType.TEXT_PLAIN)
public String PlainTextVersion() {  
	log.info("Ping");
	return httpServletRequest.getContextPath().toString() + " - " + serialVersionUID + " - " + servletContext.getInitParameter("spifName")  + "\n"; 
} // PlainTextVersion



private String XmlMarkingData(MarkingData data) {
	String result = "<markingData>";
	result += "<phrase>"  + data.getPhrase() + "</phrase>" ;
	if (data.getLang() != null) result += "<lang>"  + data.getLang() + "</lang>" ;
	List<MarkingCode> listCodes = data.getCode();
	if (!listCodes.isEmpty())
		for(MarkingCode currentMarkingCode:data.getCode()) result += "<code>"  + currentMarkingCode.value() + "</code>" ;
	result += "</markingData>";
	return result;
} // XmlMarkingData

//<securityCategoryTagSet name="Special Category Designators"
@GET
@Path("securitytagsets")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public String XMLSecurityCategoryTagSets() throws NoSuchElementException {
	log.info("Get XML Security Category Tag Sets");
	if (spif.getSecurityCategoryTagSets() == null) {
		log.trace("Get XML Security Category Tag Sets end : No tag set");
		throw new NoSuchElementException(String.format("No Security Category Tag Sets"));
	}
	String result = "<SecurityCategoryTagSets>";
	for(SecurityCategoryTagSet currentSecurityCategoryTagSet:spif.getSecurityCategoryTagSets().getSecurityCategoryTagSet()) {
		result += "<SecurityCategoryTagSet>";
		
		String name = currentSecurityCategoryTagSet.getName();
		String id = currentSecurityCategoryTagSet.getId();
		if (name != null) result += ("<name>" + name + "</name>");
		if (id != null) result += ("<id>" + id + "</id>");
		log.trace("XML Security Category Tag Sets : {}/{}",name, id);
		
		for (SecurityCategoryTag currentTag:currentSecurityCategoryTagSet.getSecurityCategoryTag()) {
			result += "<SecurityCategoryTag>";
			result += ("<name>" + currentTag.getName() + "</name>");
			result += ("<type>" + currentTag.getTagType() + "</type>");
			log.trace("XML Security Category Tag : {}",currentTag.getName());
			for (TagCategory tc:currentTag.getTagCategory()) {
				result += "<TagCategory>";
				result += "<name>" + tc.getName() + "</name>";
				result += "<lacv>" + tc.getLacv() + "</lacv>";
				for(MarkingData currentMarkingData:tc.getMarkingData()) result += XmlMarkingData(currentMarkingData);
				result += "</TagCategory>";
			}
			result += "</SecurityCategoryTag>";
		}
		result += "</SecurityCategoryTagSet>";
	}
	result += "</SecurityCategoryTagSets>";
	log.trace("Get XML Security Category Tag Sets end");
	return result;
} // XMLSecurityCategoryTagSets



} // Service class
