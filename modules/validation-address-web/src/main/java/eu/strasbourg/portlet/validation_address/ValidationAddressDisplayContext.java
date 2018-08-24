package eu.strasbourg.portlet.validation_address;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.service.adict.Street;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class ValidationAddressDisplayContext {

	private ThemeDisplay themeDisplay;
	private PortletRequest request;
	private AdictService adictService;
	private Boolean hasError;
	private String lastName;
	private String address;
	private String zipCode;
	private List<Street> streets;

	public ValidationAddressDisplayContext(ThemeDisplay themeDisplay, PortletRequest request, AdictService adict) {
		this.themeDisplay = themeDisplay;
		this.request = request;
		this.adictService = adict;
	}

	// Récupération de l'id utilisateur
	public String getPublikID() {

		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();

		return SessionParamUtil.getString(originalRequest, "publik_internal_id");
	}

	// récupération des informations de l'utilisateur
	public void getUserInfo() {
		hasError = false;
		streets = new ArrayList<Street>();
		// Récupération du publik ID avec la session
		String internalId = getPublikID();
		if (Validator.isNotNull(internalId)) {
			JSONObject userDetail = PublikApiClient.getUserDetails(internalId);
			if (userDetail.toString().equals("{}")) {
				hasError = true;
			} else {
				if (Validator.isNotNull(userDetail.get("last_name"))) {
					lastName = userDetail.getString("last_name");
					address = userDetail.get("address") + " " + userDetail.getString("zipcode") + " "
								+ userDetail.get("city");
					zipCode = userDetail.getString("zipcode");

					if (Validator.isNotNull(userDetail.get("address")) && Validator.isNotNull(userDetail.get("zipcode"))
							&& Validator.isNotNull(userDetail.get("city"))) {
						if (!isEMS()) {
							streets = null;
						}else{
							streets = adictService.searchStreetNumbers(address);
							if(Validator.isNull(streets)) {
								hasError = true;
							} else {
								hasError = false;
								if(streets.stream().anyMatch(s -> s.getScore() > 0.9)) {
									streets = null;
								}else {
									if(!streets.stream().anyMatch(s -> s.getHouseNumber() != null)) {
										streets = new ArrayList<Street>();
									}else {
										streets = streets.stream().filter(s -> s.getZipCode() == Integer.parseInt(zipCode))
												.sorted((s1, s2) -> s2.getScore().compareTo(s1.getScore())).collect(Collectors.toList());
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// vérifi si il y a eu une erreur au niveau d'un webService
	public Boolean hasError() {
		getUserInfo();
		return hasError;
	}

	// récupération de l'adresse de l'utilisateur
	public Boolean hasAddress() {
		return Validator.isNotNull(address);
	}

	// retourne le nom de l'utilisateur
	public String getLastName() {
		return lastName;
	}

	// récupère l/les adresse(s) dans adict
	public List<Street> getAddressList() {
		return streets;
	}

	// vérifie si l'adresse saisie fait partie de l'EMS
	public Boolean isEMS(){
		return StrasbourgPropsUtil.getEMSZipCode().contains(zipCode);
	}

	// vérifie si le CP est null ou fait partie de l'EMS
	public Boolean isEMSOrNull(){
		return isEMS()||Validator.isNull(zipCode);
	}
}
