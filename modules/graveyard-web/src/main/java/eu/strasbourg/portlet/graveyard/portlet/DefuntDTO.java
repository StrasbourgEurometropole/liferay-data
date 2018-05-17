package eu.strasbourg.portlet.graveyard.portlet;

import com.liferay.portal.kernel.json.JSONObject;

import eu.strasbourg.service.place.exception.NoSuchPlaceException;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;

public class DefuntDTO {

	public String firstName;
	public String name;
	public String birthDate;
	public String deathDate;
	public String deathPlace;
	public String EndConcessionDate;
	public String graveyard;
	public String graveSector;
	public String graveRow;
	public String graveNumber;
	public String nameImage;
	public String graveyardSIG;

	public DefuntDTO(JSONObject json) {
	    	name = json.getString("nom");
	    	firstName = json.getString("prenom");
	    	birthDate = json.getString("date_naissance");
	    	deathDate = json.getString("date_deces");
	    	deathPlace = json.getString("lieu_deces");
	    	EndConcessionDate = json.getString("date_fin_concession");
	    	graveyard = json.getString("nom_cimetiere");
	    	graveSector = json.getString("numero_secteur");
	    	graveRow = json.getString("numero_rangee");
	    	graveNumber = json.getString("numero_tombe");
	    	nameImage = json.getString("nom_image");
			Place place = null;
			try {
				place = PlaceLocalServiceUtil.findByName(graveyard);
			} catch (NoSuchPlaceException e) {
				e.printStackTrace();
			}
			if (place != null){
				graveyardSIG = place.getSIGid();
			}
	    }

	public DefuntDTO() {
	    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}

	public String getDeathPlace() {
		return deathPlace;
	}

	public void setDeathPlace(String deathPlace) {
		this.deathPlace = deathPlace;
	}

	public String getEndConcessionDate() {
		return EndConcessionDate;
	}

	public void setEndConcessionDate(String endConcessionDate) {
		EndConcessionDate = endConcessionDate;
	}

	public String getGraveyard() {
		return graveyard;
	}

	public void setGraveyard(String graveyard) {
		this.graveyard = graveyard;
	}

	public String getGraveSector() {
		return graveSector;
	}

	public void setGraveSector(String graveSector) {
		this.graveSector = graveSector;
	}

	public String getGraveRow() {
		return graveRow;
	}

	public void setGraveRow(String graveRow) {
		this.graveRow = graveRow;
	}

	public String getGraveNumber() {
		return graveNumber;
	}

	public void setGraveNumber(String graveNumber) {
		this.graveNumber = graveNumber;
	}

	public String getNameImage() {
		return nameImage;
	}

	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
	}

	public String getGraveyardSIG() {
		return graveyardSIG;
	}

	public void setGraveyardSIG(String graveyardSIG) {
		this.graveyardSIG = graveyardSIG;
	}

}
