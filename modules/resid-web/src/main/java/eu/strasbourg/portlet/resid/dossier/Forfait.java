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
	public LocalDate dateDebut;
	public LocalDate dateFin;
	
	public Forfait(JSONObject json) {
		annee = json.getInt("Annee");
		moisCode = json.getInt("MoisCode");
		moisIntitule = json.getString("MoisIntitule");
    	if(Validator.isNotNull(json.getString("DateAchat")) && Validator.isNotNull(json.getJSONObject("DateAchat").getString("Date"))) {
    		dateAchat = LocalDate.parse(json.getJSONObject("DateAchat").getString("Date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    	}
    	dateDebut = LocalDate.of(annee, moisCode, 1);
    	dateFin = dateDebut.plusMonths(1).minusDays(1);
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

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}
}
