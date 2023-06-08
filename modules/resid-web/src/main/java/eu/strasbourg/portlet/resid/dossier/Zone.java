package eu.strasbourg.portlet.resid.dossier;

import com.liferay.portal.kernel.json.JSONObject;

public class Zone {

	public String code;
	public String intitule;
	public Usager typeUsager;
	
	public Zone(JSONObject json) {
		code = json.getString("Code");
		intitule = json.getString("Intitule");
		typeUsager = new Usager(json.getJSONObject("TypeUsager"));
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Usager getTypeUsager() {
		return typeUsager;
	}

	public void setTypeUsager(Usager typeUsager) {
		this.typeUsager = typeUsager;
	}
}
