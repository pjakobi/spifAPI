package com.thales.tsn.spif;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "SimpleTagCategory")
@XmlAccessorType(XmlAccessType.FIELD)

public class SimpleTagCategory {
	@XmlAttribute(name = "name", required = true)
	String name;
	@XmlAttribute(name = "lacv", required = true)
	Integer lacv;
	@XmlAttribute(name = "id", required = true)
	String id;
	 
	 SimpleTagCategory(String name, Integer lacv, String id) {
		 this.name = name;
		 this.lacv = lacv;
		 this.id = id;
	 }
	 
	 String getName() { return name; }
	 String getId() { return id; }
	 Integer getLacv() { return lacv; }
	 
	 void setName(String val) { name = val; }
	 void setId(String val) { id = val; }
	 void setLacv(Integer val) { lacv = val; }
}
