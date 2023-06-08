package eu.strasbourg.portlet.resid.dossier;

import com.liferay.portal.kernel.json.JSONObject;

public class Usager {

	public String code;
	public String intitule;
	
	public Usager(JSONObject json) {
		code = json.getString("Code");
		intitule = json.getString("Intitule");
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
}
