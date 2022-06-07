package com.thales.tsn.spif;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xmlspif.spif.SecurityClassification;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@XmlRootElement(name = "SimpleSecurityClassification")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleSecurityClassification {
	@XmlAttribute(name = "name", required = true)
	private String name = null;
	@XmlAttribute(name = "lacv", required = true)
	private Integer lacv = null;
	@XmlAttribute(name = "hierarchy", required = true)
	private Integer hierarchy = null;
	@XmlAttribute(name = "obsolete", required = true)
	private Boolean obsolete = false;
	@XmlAttribute(name = "color", required = true)
	private String color = null;
	
	private static final Logger log = LogManager.getLogger(SimpleSecurityClassification.class);
	
	public SimpleSecurityClassification(SecurityClassification classif) {
		if (classif.getName() != null) name = classif.getName();
		if (classif.getLacv() != null) lacv = classif.getLacv().intValue();
		if (classif.getHierarchy() != null) hierarchy = classif.getHierarchy().intValue();
		obsolete = classif.isObsolete();
		color = classif.getColor();
		log.trace(this.toString());
	}
	
	void setName(String value) { name = value; }
	void setLacv(BigInteger value) { lacv = value.intValue(); }
	void setHierarchy(BigInteger value) { hierarchy = value.intValue(); }
	String getName() { return name; }
	String getLacv() { return BigInteger.valueOf(lacv).toString();	}
	String getHierarchy() { return BigInteger.valueOf(hierarchy).toString(); }
	
	JsonNode toJsonNode() {
		 ObjectMapper mapper = new ObjectMapper();
		 ObjectNode localNode = mapper.createObjectNode();
		 localNode.put("name", name);
		 localNode.put("lacv", lacv);
		 localNode.put("hierarchy", hierarchy);
		 return localNode;
	}
	String toXmlString() {
		String result = "<SecurityClassification>" + "<name>" + name + "</name>";
		if (lacv != null) result += ("<lacv>" + lacv + "</lacv>");
		if (hierarchy != null) result += ("<hierarchy>" + hierarchy + "</hierarchy>");
		result += "</SecurityClassification>";	
		return result;
	}
	
	// TODO : other attributes
	String toXmlStringFull() {
		String result = "<name>" + name + "</name>";
		if (lacv != null) result += ("<lacv>" + lacv + "</lacv>");
		if (hierarchy != null) result += ("<hierarchy>" + hierarchy + "</hierarchy>");
		if (obsolete) result += "<obsolete/>";
		if (color != null) result += "<color>" + color + "</color>"; 
		return result;
	}
	
	public String toString() {
		String fmt1="", fmt2="", fmt3="";
		fmt1 = String.format("Simple classification: %s", name);
		if (lacv != null) fmt2 = String.format("- lacv: %s", lacv);
		if (hierarchy != null) fmt3 = String.format("- hierarchy: %s", hierarchy);
		return "Simple classification: " + fmt1 + fmt2  + fmt3;
		//return fmt1 + fmt2 + fmt3;
	} // toString
}
