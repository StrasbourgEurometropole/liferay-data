package eu.strasbourg.portlet.resid.dossier;

import com.liferay.portal.kernel.json.JSONObject;

public class Contractant {

	public Civilite civilite;
	public String nom;
	public String prenom;
	public String courriel;
	public String telephoneFixe;
	public String telephonePortable;
	public Adresse adresse;
	
	public Contractant(JSONObject json) {
		civilite = new Civilite(json.getJSONObject("Civilite"));
		nom = json.getString("Nom");
		prenom = json.getString("Prenom");
		courriel = json.getString("Courriel");
		telephoneFixe = json.getString("TelephoneFixe");
		telephonePortable = json.getString("TelephonePortable");
		adresse = new Adresse(json.getJSONObject("Adresse"));
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public String getTelephoneFixe() {
		return telephoneFixe;
	}

	public void setTelephoneFixe(String telephoneFixe) {
		this.telephoneFixe = telephoneFixe;
	}

	public String getTelephonePortable() {
		return telephonePortable;
	}

	public void setTelephonePortable(String telephonePortable) {
		this.telephonePortable = telephonePortable;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
}
