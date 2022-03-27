package thales.tsn.iam.spif;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.json.simple.JSONObject;


@XmlRootElement(name = "EncryptRequest")
public class EncryptRequest {
	@XmlElement(name = "target")
	private String target;
	@XmlElement(name = "data")
	private String data;

	public EncryptRequest() {	target = "x@y";	} // EncryptRequest
	String getTarget() { return target; }
	String getData() { return data; }
	@Override
    public String toString() {
		return "target: " + target + " - data:" + data;
	}
	public String toJson() {
		JSONObject obj = new JSONObject();
		obj.put("target", "x@y");
		obj.put("data", "bbb");
		return obj.toString();
	} // toJson
}
