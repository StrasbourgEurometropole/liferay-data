package eu.strasbourg.portlet.contact.configuration;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	configurationPid = "eu.strasbourg.portlet.contact.configuration.ContactFormConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.CONTACT_FORM_WEB },
	service = ConfigurationAction.class)
public class ContactFormConfigurationAction extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/configuration/contact-form-configuration.jsp";
	}

	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		// Affichage
		String template = ParamUtil.getString(actionRequest, "template");
		setPreference(actionRequest, "template", template);
		
		// Titre
		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(actionRequest, "title");
		LocalizedValuesMap titleLocalizedValuesMap = new LocalizedValuesMap();
		for (Map.Entry<Locale, String> e : titleMap.entrySet()) {
			titleLocalizedValuesMap.put(e.getKey(), e.getValue());
		}
		String titleXml = LocalizationUtil.getXml(titleLocalizedValuesMap, "title");
		setPreference(actionRequest, "title", titleXml);
		
		// Email du destinataire
		String email = ParamUtil.getString(actionRequest, "email");
		setPreference(actionRequest, "email", email);
		
		// Texte de description
		Map<Locale, String> descriptionTextMap = LocalizationUtil
			.getLocalizationMap(actionRequest, "descriptionText");
		LocalizedValuesMap descriptionTextLocalizedValuesMap = new LocalizedValuesMap();
		for (Map.Entry<Locale, String> e : descriptionTextMap.entrySet()) {
			descriptionTextLocalizedValuesMap.put(e.getKey(), e.getValue());
		}
		String descriptionTextXML = LocalizationUtil.getXml(descriptionTextLocalizedValuesMap,
			"descriptionText");
		setPreference(actionRequest, "descriptionText", descriptionTextXML);

		// Texte "confidentialité"
		Map<Locale, String> privacyTextMap = LocalizationUtil
			.getLocalizationMap(actionRequest, "privacyText");
		LocalizedValuesMap privacyTextLocalizedValuesMap = new LocalizedValuesMap();
		for (Map.Entry<Locale, String> e : privacyTextMap.entrySet()) {
			privacyTextLocalizedValuesMap.put(e.getKey(), e.getValue());
		}
		String privacyTextXML = LocalizationUtil.getXml(privacyTextLocalizedValuesMap,
			"privacyText");
		setPreference(actionRequest, "privacyText", privacyTextXML);
		

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			ContactFormConfiguration configuration = themeDisplay
				.getPortletDisplay()
				.getPortletInstanceConfiguration(ContactFormConfiguration.class);

			request.setAttribute("template", configuration.template());
			request.setAttribute("title", configuration.title());
			request.setAttribute("email", configuration.email());
			request.setAttribute("descriptionText", configuration.descriptionText());
			request.setAttribute("privacyText", configuration.privacyText());

			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			_log.error(e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
