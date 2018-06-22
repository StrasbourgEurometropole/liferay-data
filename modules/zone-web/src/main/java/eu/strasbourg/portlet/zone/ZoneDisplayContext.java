package eu.strasbourg.portlet.zone;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.portlet.zone.configuration.ZoneConfiguration;
import eu.strasbourg.utils.PublikApiClient;

public class ZoneDisplayContext {

	private ThemeDisplay themeDisplay;
	private ZoneConfiguration configuration;
	private String publikId;
	private String address;

	public ZoneDisplayContext(ThemeDisplay themeDisplay) {
		this.themeDisplay = themeDisplay;
		try {
			this.configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(ZoneConfiguration.class);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public ZoneConfiguration getConfiguration() {
		return configuration;
	}

	public String getText() {
		String text = "";
		Map<Locale, String> mapText = LocalizationUtil.getLocalizationMap(configuration.textXML());
		for (Map.Entry<Locale, String> map : mapText.entrySet()) {
			if (themeDisplay.getLocale().toString().equals(map.getKey().toString())) {
				text = HtmlUtil.unescape(map.getValue());
				break;
			}
		}
		if (Validator.isNull(text)) {
			text = "No configuration";
		}
		return text;
	}

	// Récupération de l'id utilisateur
	public String getPublikID(PortletRequest resourceRequest) {
		if (Validator.isNull(this.publikId)) {
			LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
			HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();

			this.publikId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
		}

		return this.publikId;
	}

	// récupération de l'adresse de l'utilisateur
	public String getAddress(PortletRequest request) {
		if (address == null) {

			// Récupération du publik ID avec la session
			String internalId = getPublikID(request);
			if (Validator.isNotNull(internalId)) {
				JSONObject userDetail = PublikApiClient.getUserDetails(internalId);
				if (Validator.isNotNull(userDetail.get("address")))
					address = ""+userDetail.get("address");
			}
		}

		return address;
	}
}
