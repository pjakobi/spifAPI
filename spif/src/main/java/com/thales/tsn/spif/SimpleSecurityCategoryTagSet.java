package com.thales.tsn.spif;

import org.xmlspif.spif.TagType;

public class SimpleSecurityCategoryTagSet {
	private String name,id;
	private TagType tagType;
	
	SimpleSecurityCategoryTagSet(String name,String id, TagType myTagType) {
		this.name = name;
		this.id = id;
		this.tagType = myTagType;
	}
	public String getName() { return name; }
	public String getId() { return id; }
	public String getTagType() { return tagType.value(); }
	
	public void setName(String value) { name = value; }
	public void setId(String value) { id = value; }
	public void setTagType(String value) { tagType = TagType.fromValue(value); }
}
