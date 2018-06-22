package eu.strasbourg.portlet.resid.dossier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

public class Vehicule {

	public String immatriculation;
	public Boolean principal;
	public Boolean secondaire;
	public Boolean temporaire;
	public Boolean carteGriseProvisoire;
	public LocalDate dateDebutValidite;
	public LocalDate dateFinValidite;
	
	public Vehicule(JSONObject json) {
		immatriculation = json.getString("Immatriculation");
		principal = json.getBoolean("EstPrincipal");
		secondaire = json.getBoolean("EstSecondaire");
		temporaire = json.getBoolean("EstTemporaire");
		carteGriseProvisoire = json.getBoolean("EstCarteGriseProvisoire");
    	if(Validator.isNotNull(json.getJSONObject("DateDebutValidite")) && Validator.isNotNull(json.getJSONObject("DateDebutValidite").getString("Date"))) {
    		dateDebutValidite = LocalDate.parse(json.getJSONObject("DateDebutValidite").getString("Date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	}
    	if(Validator.isNotNull(json.getJSONObject("DateFinValidite")) && Validator.isNotNull(json.getJSONObject("DateFinValidite").getString("Date"))) {
    		dateFinValidite = LocalDate.parse(json.getJSONObject("DateFinValidite").getString("Date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	}
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public Boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	public Boolean isSecondaire() {
		return secondaire;
	}

	public void setSecondaire(Boolean secondaire) {
		this.secondaire = secondaire;
	}

	public Boolean isTemporaire() {
		return temporaire;
	}

	public void setTemporaire(Boolean temporaire) {
		this.temporaire = temporaire;
	}

	public Boolean hasCarteGriseProvisoire() {
		return carteGriseProvisoire;
	}

	public void setCarteGriseProvisoire(Boolean carteGriseProvisoire) {
		this.carteGriseProvisoire = carteGriseProvisoire;
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public LocalDate getDateDebutValidite() {
		return dateDebutValidite;
	}

	public void setDateDebutValidite(LocalDate dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}

	public LocalDate getDateFinValidite() {
		return dateFinValidite;
	}

	public void setDateFinValidite(LocalDate dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}
}
