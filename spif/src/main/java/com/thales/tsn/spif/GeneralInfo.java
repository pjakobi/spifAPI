package com.thales.tsn.spif;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.xml.bind.annotation.XmlAccessType;
import org.xmlspif.spif.SPIF;


@XmlRootElement(name = "GeneralInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	    "version",
	    "originatorDN",
	    "creationDate",
	    "schemaVersion",
	    "keyIdentifier",
	    "privilegeId",
	    "rbacId",
	    "name",
	    "id"
	})

public class GeneralInfo {
	@XmlElement(name = "version", required = true)
	private String version;
	@XmlElement(name = "originatorDN", required = true)
	private String originatorDN;
	@XmlElement(name = "creationDate", required = true)
	private String creationDate;
	@XmlElement(name = "schemaVersion", required = true)
	private String schemaVersion;
	@XmlElement(name = "keyIdentifier", required = true)
	private String keyIdentifier;
	@XmlElement(name = "privilegeId", required = true)
	private String privilegeId;
	@XmlElement(name = "rbacId", required = true)
	private String rbacId;
	@XmlElement(name = "name", required = true)
	private String name;
	@XmlElement(name = "id", required = true)
	private String id;
	
	public GeneralInfo(SPIF spif) throws InvalidNameException {
		version = spif.getVersion().toString();
		originatorDN = (new LdapName(spif.getOriginatorDN())).toString();
		creationDate = spif.getCreationDate();
		schemaVersion = spif.getSchemaVersion();
		keyIdentifier = spif.getKeyIdentifier();
		privilegeId = spif.getPrivilegeId();
		rbacId = spif.getRbacId();
		name = spif.getSecurityPolicyId().getName();
		id = spif.getSecurityPolicyId().getId();
		
	} // constructor
	
	public String getVersion() { return version; }
	public String getOriginatorDN() { return originatorDN.toString(); }
	public String getSchemaVersion() { return schemaVersion; }
	public String getKeyIdentifier() { return keyIdentifier; }
	public String getPrivilegeId() { return privilegeId; }
	public String getRbacId() { return rbacId; }
	public String getName() { return name; }
	public String getId() { return id; }

	public void setVersion(String value) { version = value; }
	public void setOriginatorDN(String value) throws InvalidNameException { originatorDN = (new LdapName(value)).toString(); }
	public void setSchemaVersion(String value) { schemaVersion = value; }
	public void setKeyIdentifier(String value) { keyIdentifier = value; }
	public void setPrivilegeId(String value) { privilegeId = value; }
	public void setRbacId(String value) { rbacId = value; }
	public void setName(String value) { name = value; }
	public void setId(String value) { id = value; }
	

}
