package eu.strasbourg.portlet.resid.dossier;

import com.liferay.portal.kernel.json.JSONObject;

public class StatutDossier {

	public String code;
	public String libelle;
	public Boolean valide;
	
	public StatutDossier(JSONObject json) {
		code = json.getString("Code");
		libelle = json.getString("Libelle");
		valide = json.getBoolean("EstValide");
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Boolean isValide() {
		return valide;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
	}
}
