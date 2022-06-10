//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.19 at 05:28:17 PM CEST 
//


package org.xmlspif.spif;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * 
 *         
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;div xmlns="http://www.xmlspif.org/spif" xmlns:spif="http://www.xmlspif.org/spif" xmlns:xs="http://www.w3.org/2001/XMLSchema" class="bodytext"&gt;
 *           &lt;p&gt;The markingQualifier qualifies the markingData associated with a data object (e.g. it specifies a suffix or a prefix). &lt;/p&gt;
 *           &lt;p&gt;It consists of:&lt;/p&gt;
 *           &lt;ul&gt;&lt;li&gt;qualifier - a qualifier (e.g. a suffix, prefix or separator)&lt;/li&gt;&lt;li&gt;markingCode - a code which identifies where the phrase is to be physically applied.&lt;/li&gt;
 *           &lt;/ul&gt;     
 *         &lt;/div&gt;
 * </pre>
 * 
 *       
 * 
 * <p>Java class for markingQualifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="markingQualifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.xmlspif.org/spif}qualifier" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="markingCode" type="{http://www.xmlspif.org/spif}markingCode" />
 *       &lt;anyAttribute/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "markingQualifier", propOrder = {
    "qualifier"
})
public class MarkingQualifier {

    @XmlElement(required = true)
    protected List<Qualifier> qualifier;
    @XmlAttribute(name = "markingCode")
    protected MarkingCode markingCode;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the qualifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qualifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQualifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Qualifier }
     * 
     * 
     */
    public List<Qualifier> getQualifier() {
        if (qualifier == null) {
            qualifier = new ArrayList<Qualifier>();
        }
        return this.qualifier;
    }

    /**
     * Gets the value of the markingCode property.
     * 
     * @return
     *     possible object is
     *     {@link MarkingCode }
     *     
     */
    public MarkingCode getMarkingCode() {
        return markingCode;
    }

    /**
     * Sets the value of the markingCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link MarkingCode }
     *     
     */
    public void setMarkingCode(MarkingCode value) {
        this.markingCode = value;
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