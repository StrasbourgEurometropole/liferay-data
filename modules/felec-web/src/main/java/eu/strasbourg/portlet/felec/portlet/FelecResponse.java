package eu.strasbourg.portlet.felec.portlet;

import com.liferay.portal.kernel.json.JSONObject;

public class FelecResponse {
    
    public String firstName;
    public String name;
    public String birthDate;
    public String responseCode;
    public String responseMessage;
    public String address1;
    public String address2;
    public String address3;
    public String stationNumber;
    public String stationLabel;
    public String center;
    public String centerCountry;
    public String list;
    public String startDate;
    public String endDate;
    public String applicationDate;
	public String returnCard;
    
    public FelecResponse(JSONObject json) {
        firstName = json.getString("tous_les_prenoms");
        name = json.getString("nom_de_famille");
        birthDate = json.getString("date_de_naissance");
        responseCode = json.getString("code_retour");
        responseMessage = json.getString("lib_retour");
        address1 = json.getString("adresse1_du_bureau");
        address2 = json.getString("adresse2_du_bureau");
        address3 = json.getString("adresse3_du_bureau");
        stationNumber = json.getString("numero_du_bureau");
        stationLabel = json.getString("libelle_du_bureau");
        center = json.getString("centre_de_vote");
        centerCountry = json.getString("pays_centre_de_vote");
        list = json.getString("libelle_liste");
        startDate = json.getString("debut_validite_centre");
        endDate = json.getString("fin_validite_centre");
        applicationDate = json.getString("date_effet_mouvement");
        returnCard = json.getString("carte_en_retour");
    }

    public FelecResponse() {
    }

    public String getReturnCard() {
		return returnCard;
	}

	public void setReturnCard(String returnCard) {
		this.returnCard = returnCard;
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

    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    public String getResponseMessage() {
        return responseMessage;
    }
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getAddress3() {
        return address3;
    }
    public void setAddress3(String address3) {
        this.address3 = address3;
    }
    public String getStationNumber() {
        return stationNumber;
    }
    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }
    public String getStationLabel() {
        return stationLabel;
    }
    public void setStationLabel(String stationLabel) {
        this.stationLabel = stationLabel;
    }
    public String getCenter() {
        return center;
    }
    public void setCenter(String center) {
        this.center = center;
    }
    public String getCenterCountry() {
        return centerCountry;
    }
    public void setCenterCountry(String centerCountry) {
        this.centerCountry = centerCountry;
    }
    public String getList() {
        return list;
    }
    public void setList(String list) {
        this.list = list;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getApplicationDate() {
        return applicationDate;
    }
    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }
}
