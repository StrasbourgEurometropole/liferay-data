package eu.strasbourg.portlet.offer;

import com.liferay.portal.kernel.json.JSONObject;

public class Application {

	String refPoste;
	String libPoste;
	String url;
	String codeSuivi;
	String date;
	String status;
	
	public Application(String refPoste, String libPoste, String url, String codeSuivi, String date, String status) {
		super();
		this.refPoste = refPoste;
		this.libPoste = libPoste;
		this.url = url;
		this.codeSuivi = codeSuivi;
		this.date = date;
		this.status = status;
	}

	public String getRefPoste() {
		return refPoste;
	}

	public void setRefPoste(String refPoste) {
		this.refPoste = refPoste;
	}

	public String getLibPoste() {
		return libPoste;
	}

	public void setLibPoste(String libPoste) {
		this.libPoste = libPoste;
	}

	public String getCodeSuivi() {
		return codeSuivi;
	}

	public void setCodeSuivi(String codeSuivi) {
		this.codeSuivi = codeSuivi;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
