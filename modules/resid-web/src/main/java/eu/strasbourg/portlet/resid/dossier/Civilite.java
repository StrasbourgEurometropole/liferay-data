package eu.strasbourg.portlet.resid.dossier;

import com.liferay.portal.kernel.json.JSONObject;

public class Civilite {

	public String code;
	public String libelleCourt;
	public String libelleLong;
	
	public Civilite(JSONObject json) {
		code = json.getString("code");
		libelleCourt = json.getString("libelleCourt");
		libelleLong = json.getString("libelleLong");
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelleCourt() {
		return libelleCourt;
	}

	public void setLibelleCourt(String libelleCourt) {
		this.libelleCourt = libelleCourt;
	}

	public String getLibelleLong() {
		return libelleLong;
	}

	public void setLibelleLong(String libelleLong) {
		this.libelleLong = libelleLong;
	}
}
