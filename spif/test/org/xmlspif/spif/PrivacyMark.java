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
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * A privacy mark that may be used in the label.
 * 
 * <p>Java class for privacyMark complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="privacyMark">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.xmlspif.org/spif}markingData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.xmlspif.org/spif}markingQualifier" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="obsolete" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;anyAttribute/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "privacyMark", propOrder = {
    "markingData",
    "markingQualifier"
})
public class PrivacyMark {

    protected List<MarkingData> markingData;
    protected List<MarkingQualifier> markingQualifier;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "obsolete")
    protected Boolean obsolete;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the markingData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the markingData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarkingData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarkingData }
     * 
     * 
     */
    public List<MarkingData> getMarkingData() {
        if (markingData == null) {
            markingData = new ArrayList<MarkingData>();
        }
        return this.markingData;
    }

    /**
     * Gets the value of the markingQualifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the markingQualifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarkingQualifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarkingQualifier }
     * 
     * 
     */
    public List<MarkingQualifier> getMarkingQualifier() {
        if (markingQualifier == null) {
            markingQualifier = new ArrayList<MarkingQualifier>();
        }
        return this.markingQualifier;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the obsolete property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isObsolete() {
        if (obsolete == null) {
            return false;
        } else {
            return obsolete;
        }
    }

    /**
     * Sets the value of the obsolete property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setObsolete(Boolean value) {
        this.obsolete = value;
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
