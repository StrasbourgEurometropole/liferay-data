package eu.strasbourg.service.adict;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * Rue Adict
 */
public class Street {
	private String id;
	private String houseNumber;
	private Double score;
	private int zipCode;
	private String label;
	private String name;
	private String city;
	private String x;
	private String y;
	
	public Street(String id, String houseNumber, Double score, int zipCode, String label, String name, String city, String x, String y) {
		this.id = id;
		this.houseNumber = houseNumber;
		this.score = score;
		this.zipCode = zipCode;
		this.label = label;
		this.name = name;
		this.city = city;
		this.x = x;
		this.y = y;
	}

	public JSONObject toJSON() {
		JSONObject jsonStreet = JSONFactoryUtil.createJSONObject();
		jsonStreet.put("id", this.getId());
		jsonStreet.put("houseNumber", this.getHouseNumber());
		jsonStreet.put("score", this.getScore());
		jsonStreet.put("zipCode", this.getZipCode());
		jsonStreet.put("label", this.getLabel());
		jsonStreet.put("name", this.getName());
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

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
