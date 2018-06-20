package eu.strasbourg.portlet.resid.dossier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

public class Forfait {

	public int annee;
	public int moisCode;
	public String moisIntitule;
	public LocalDate dateAchat;
	
	public Forfait(JSONObject json) {
		annee = json.getInt("Annee");
		moisCode = json.getInt("MoisCode");
		moisIntitule = json.getString("MoisIntitule");
    	if(Validator.isNotNull(json.getString("DateAchat")) && Validator.isNotNull(json.getJSONObject("DateAchat").getString("Date"))) {
    		dateAchat = LocalDate.parse(json.getJSONObject("DateAchat").getString("Date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    	}
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getMoisCode() {
		return moisCode;
	}

	public void setMoisCode(int moisCode) {
		this.moisCode = moisCode;
	}

	public String getMoisIntitule() {
		return moisIntitule;
	}

	public void setMoisIntitule(String moisIntitule) {
		this.moisIntitule = moisIntitule;
	}

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}
}
