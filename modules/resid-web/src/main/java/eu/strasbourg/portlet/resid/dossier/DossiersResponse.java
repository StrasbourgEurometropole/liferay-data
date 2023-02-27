package eu.strasbourg.portlet.resid.dossier;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

public class DossiersResponse {

	public int codeRetour;
	public String erreurDescription;
	public int count;
	public List<Dossier> dossiers;

	public DossiersResponse(JSONObject json) {
		codeRetour = json.getInt("CodeRetour");
		erreurDescription = json.getString("ErreurDescription");
		count = json.getInt("Count");

		dossiers = new ArrayList<>();
		if (count > 0) {
			JSONArray dossiersJSON = json.getJSONArray("Dossiers");
			for (int i = 0; i < dossiersJSON.length(); i++) {
				Dossier dossier = new Dossier(dossiersJSON.getJSONObject(i));
				dossiers.add(dossier);
			}
		}
	}

	public int getCodeRetour() {
		return codeRetour;
	}

	public void setCodeRetour(int codeRetour) {
		this.codeRetour = codeRetour;
	}

	public String getErreurDescription() {
		return erreurDescription;
	}

	public void setErreurDescription(String erreurDescription) {
		this.erreurDescription = erreurDescription;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Dossier> getDossiers() {
		return dossiers;
	}

	public void setDossiers(List<Dossier> dossiers) {
		this.dossiers = dossiers;
	}
}
