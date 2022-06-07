package com.thales.tsn.spif;

public class SimpleSecurityCategoryTagSet {
	private String name;
	private String id;
	SimpleSecurityCategoryTagSet(String name,String id) {
		this.name = name;
		this.id = id;
	}
	public String getName() { return name; }
	public String getId() { return id; }
	
	public void setName(String value) { name = value; }
	public void setId(String value) { id = value; }
}
