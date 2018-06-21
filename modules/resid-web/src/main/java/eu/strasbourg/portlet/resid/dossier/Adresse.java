package eu.strasbourg.portlet.resid.dossier;

import com.liferay.portal.kernel.json.JSONObject;

public class Adresse {

	public int numero;
	public String extension;
	public String voie;
	public String complementVoie;
	public String complementVoie2;
	public String CodePostal;
	public String ville;
	
	public Adresse(JSONObject json) {
		numero = json.getInt("Numero");
		extension = json.getString("Extension");
		voie = json.getString("Voie");
		complementVoie = json.getString("ComplementVoie");
		complementVoie2 = json.getString("ComplementVoie2");
		CodePostal = json.getString("CodePostal");
		ville = json.getString("Ville");
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public String getComplementVoie() {
		return complementVoie;
	}

	public void setComplementVoie(String complementVoie) {
		this.complementVoie = complementVoie;
	}

	public String getComplementVoie2() {
		return complementVoie2;
	}

	public void setComplementVoie2(String complementVoie2) {
		this.complementVoie2 = complementVoie2;
	}

	public String getCodePostal() {
		return CodePostal;
	}

	public void setCodePostal(String codePostal) {
		CodePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
}
