//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.01 at 12:18:02 PM CEST 
//


package org.xmlspif.spif;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for operation.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="operation">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="onlyOne"/>
 *     &lt;enumeration value="oneOrMore"/>
 *     &lt;enumeration value="all"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "operation")
@XmlEnum
public enum Operation {

    @XmlEnumValue("onlyOne")
    ONLY_ONE("onlyOne"),
    @XmlEnumValue("oneOrMore")
    ONE_OR_MORE("oneOrMore"),
    @XmlEnumValue("all")
    ALL("all");
    private final String value;

    Operation(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Operation fromValue(String v) {
        for (Operation c: Operation.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
