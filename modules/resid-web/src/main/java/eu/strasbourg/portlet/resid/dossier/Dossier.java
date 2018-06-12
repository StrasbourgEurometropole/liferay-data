package eu.strasbourg.portlet.resid.dossier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

public class Dossier {

	public String numeroDossier;
	public StatutDossier statutDossier;
	public Zone zone;
	public Contractant contractantPrincipal;
	public LocalDate DateFinValiditeDossier;
	public Vehicule vehiculePrincipal;
	public Vehicule vehiculePrincipalTemporaire;
	public Vehicule vehiculeSecondaire;
	public Vehicule vehiculeSecondaireTemporaire;
	public List<Forfait> forfaits;

	public Dossier(JSONObject dossier) {

		numeroDossier = dossier.getString("NumeroDossier");
		statutDossier = new StatutDossier(dossier.getJSONObject("StatutDossier"));
		zone = new Zone(dossier.getJSONObject("Zone"));
		contractantPrincipal = new Contractant(dossier.getJSONObject("ContractantPrincipal"));
		if (Validator.isNotNull(dossier.getJSONObject("DateFinValiditeDossier")) && Validator.isNotNull(dossier.getJSONObject("DateFinValiditeDossier").getString("Date"))) {
			DateFinValiditeDossier = LocalDate.parse(dossier.getJSONObject("DateFinValiditeDossier").getString("Date"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		JSONArray listeVehicules = dossier.getJSONArray("Vehicules");
		for (int i = 0; i < listeVehicules.length(); i++) {
			Vehicule auto = new Vehicule(listeVehicules.getJSONObject(i));
			if(auto.isPrincipal()) {
				if(auto.isTemporaire()) {
					vehiculePrincipalTemporaire = auto;
				}else {
					vehiculePrincipal = auto;
				}
			}else {
				if(auto.isTemporaire()) {
					vehiculeSecondaireTemporaire = auto;
				}else {
					vehiculeSecondaire = auto;
				}
			}
		}
		forfaits = new ArrayList<Forfait>();
		JSONArray listeForfaits = dossier.getJSONArray("Forfaits");
		for (int i = 0; i < listeForfaits.length(); i++) {
			forfaits.add(new Forfait(listeForfaits.getJSONObject(i)));
		}
	}

	public String getNumeroDossier() {
		return numeroDossier;
	}

	public void setNumeroDossier(String numeroDossier) {
		this.numeroDossier = numeroDossier;
	}

	public StatutDossier getStatutDossier() {
		return statutDossier;
	}

	public void setStatutDossier(StatutDossier statutDossier) {
		this.statutDossier = statutDossier;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public Contractant getContractantPrincipal() {
		return contractantPrincipal;
	}

	public void setContractantPrincipal(Contractant contractantPrincipal) {
		this.contractantPrincipal = contractantPrincipal;
	}

	public LocalDate getDateFinValiditeDossier() {
		return DateFinValiditeDossier;
	}

	public void setDateFinValiditeDossier(LocalDate dateFinValiditeDossier) {
		DateFinValiditeDossier = dateFinValiditeDossier;
	}

	public Vehicule getVehiculePrincipal() {
		return vehiculePrincipal;
	}

	public void setVehiculePrincipal(Vehicule vehiculePrincipal) {
		this.vehiculePrincipal = vehiculePrincipal;
	}

	public Vehicule getVehiculePrincipalTemporaire() {
		return vehiculePrincipalTemporaire;
	}

	public void setVehiculePrincipalTemporaire(Vehicule vehiculePrincipalTemporaire) {
		this.vehiculePrincipalTemporaire = vehiculePrincipalTemporaire;
	}

	public Vehicule getVehiculeSecondaire() {
		return vehiculeSecondaire;
	}

	public void setVehiculeSecondaire(Vehicule vehiculeSecondaire) {
		this.vehiculeSecondaire = vehiculeSecondaire;
	}

	public Vehicule getVehiculeSecondaireTemporaire() {
		return vehiculeSecondaireTemporaire;
	}

	public void setVehiculeSecondaireTemporaire(Vehicule vehiculeSecondaireTemporaire) {
		this.vehiculeSecondaireTemporaire = vehiculeSecondaireTemporaire;
	}

	public List<Forfait> getForfaits() {
		return forfaits;
	}

	public void setForfaits(List<Forfait> forfaits) {
		this.forfaits = forfaits;
	}
}
