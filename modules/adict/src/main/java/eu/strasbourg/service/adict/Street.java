package eu.strasbourg.service.adict;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * Rue Adict
 */
public class Street {
	private String id;
	private String label;
	private String city;
	private String x;
	private String y;

	public Street(String id, String label, String city, String x, String y) {
		this.id = id;
		this.label = label;
		this.city = city;
		this.x = x;
		this.y = y;
	}

	public JSONObject toJSON() {
		JSONObject jsonStreet = JSONFactoryUtil.createJSONObject();
		jsonStreet.put("id", this.getId());
		jsonStreet.put("label", this.getLabel());
		jsonStreet.put("city", this.getCity());
		jsonStreet.put("x", this.getX());
		jsonStreet.put("y", this.getY());
		return jsonStreet;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

}
