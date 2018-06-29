package eu.strasbourg.portlet.validation_address;

import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
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
	private String zipCode;
	private String address;

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

	// récupération de l'adresse de l'utilisateur
	public String getAddress() {
		if (address == null) {

			// Récupération du publik ID avec la session
			String internalId = getPublikID();
			if (Validator.isNotNull(internalId)) {
				JSONObject userDetail = PublikApiClient.getUserDetails(internalId);
				if (Validator.isNotNull(userDetail.get("address")) && Validator.isNotNull(userDetail.get("city"))) {
					address = userDetail.get("address") + " " + userDetail.get("city");
					zipCode = userDetail.getString("zipcode");
				}
			}
		}

		return address;
	}

	// vérifie si l'adresse possède un CP dans l'EMS
	public Boolean isEMSZipCode() {
		getAddress();
		if (StrasbourgPropsUtil.getEMSZipCode().contains(zipCode)) {
			return true;
		}

		return false;
	}

	// récupère l/les adresse(s) dans adict
	public List<Street> getAddressList() {
		List<Street> streets = adictService.searchStreetNumbers(getAddress());
		streets = streets.stream().filter(s -> s.getZipCode() == Integer.parseInt(zipCode)).sorted((s1, s2) -> s2.getScore().compareTo(s1.getScore()))
				.collect(Collectors.toList());
		return streets;
	}
}
