//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.01 at 12:18:02 PM CEST 
//


package org.xmlspif.spif;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * Categories associated with specific classification or category.
 * 
 * <p>Java class for optionalCategoryData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="optionalCategoryData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="tagSetRef" use="required" type="{http://www.xmlspif.org/spif}tagSetName" />
 *       &lt;attribute name="tagType" use="required" type="{http://www.xmlspif.org/spif}tagType" />
 *       &lt;attribute name="enumType" type="{http://www.xmlspif.org/spif}enumType" />
 *       &lt;attribute name="lacv" type="{http://www.xmlspif.org/spif}lacv" />
 *       &lt;attribute name="all" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;anyAttribute/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "optionalCategoryData")
public class OptionalCategoryData {

    @XmlAttribute(name = "tagSetRef", required = true)
    protected String tagSetRef;
    @XmlAttribute(name = "tagType", required = true)
    protected TagType tagType;
    @XmlAttribute(name = "enumType")
    protected EnumType enumType;
    @XmlAttribute(name = "lacv")
    protected String lacv;
    @XmlAttribute(name = "all")
    protected Boolean all;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the tagSetRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTagSetRef() {
        return tagSetRef;
    }

    /**
     * Sets the value of the tagSetRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTagSetRef(String value) {
        this.tagSetRef = value;
    }

    /**
     * Gets the value of the tagType property.
     * 
     * @return
     *     possible object is
     *     {@link TagType }
     *     
     */
    public TagType getTagType() {
        return tagType;
    }

    /**
     * Sets the value of the tagType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TagType }
     *     
     */
    public void setTagType(TagType value) {
        this.tagType = value;
    }

    /**
     * Gets the value of the enumType property.
     * 
     * @return
     *     possible object is
     *     {@link EnumType }
     *     
     */
    public EnumType getEnumType() {
        return enumType;
    }

    /**
     * Sets the value of the enumType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumType }
     *     
     */
    public void setEnumType(EnumType value) {
        this.enumType = value;
    }

    /**
     * Gets the value of the lacv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLacv() {
        return lacv;
    }

    /**
     * Sets the value of the lacv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLacv(String value) {
        this.lacv = value;
    }

    /**
     * Gets the value of the all property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAll() {
        return all;
    }

    /**
     * Sets the value of the all property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAll(Boolean value) {
        this.all = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
