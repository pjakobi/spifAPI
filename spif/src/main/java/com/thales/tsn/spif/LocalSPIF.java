package com.thales.tsn.spif;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xmlspif.spif.SPIF;

import java.io.File;

import org.xmlspif.spif.ObjectFactory;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class LocalSPIF extends SPIF {
	private static final Logger log = LogManager.getLogger(LocalSPIF.class);
	public LocalSPIF() {
		super();
	}
	static public SPIF get(ServletContext servletContext) throws JAXBException {
		SPIF localSpif = (SPIF)servletContext.getAttribute("spif");
		if (localSpif != null) return localSpif; // Unmarshalling has already done
		log.trace("Unmarshalling SPIF {}",servletContext.getInitParameter("spifName"));
		Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(SPIF.class).createUnmarshaller();
		localSpif = (SPIF) jaxbUnmarshaller.unmarshal(new File(servletContext.getInitParameter("spifName"))); 
		servletContext.setAttribute("spif", localSpif);
		log.trace("Unmarshalling ok - SPIF {} ",servletContext.getInitParameter("spifName"));
		return localSpif;
	}
	

	


}
