package thales.tsn.iam.spif;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "spifContext")

public class spifContext {
	@XmlElement(name = "version")
	private String version;
	@XmlElement(name = "name")
	private String className;
	@XmlElement(name = "xsd")
	private String xsd;
	@XmlElement(name = "spif")
	private String spif;
	
	
	public spifContext() {	version = "0.0.0";	} // spifContext
	
	String getClassName( ) { return className; }
	void setClassName(String Name) { className = Name; }
	
	String getVersion() { return version; }
	
	void setXsd(String Name) { xsd = Name; }
	String getXsd() { return xsd; }
	
	void setSpif(String Name) { spif = Name; }
	String getSpif() { return spif; }
	
	@Override
    public String toString() {
		String result = "";
		result += (" class: " + className);
		result += (" version: " + version);
		result += (" xsd: " + xsd);
		result += (" spif: " + spif);
		return result;
	} // toString
}
