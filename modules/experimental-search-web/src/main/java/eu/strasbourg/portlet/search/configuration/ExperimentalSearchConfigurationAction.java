package eu.strasbourg.portlet.search.configuration;

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
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	configurationPid = "eu.strasbourg.portlet.search.configuration.ExperimentalSearchConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.EXPERIMENTAL_SEARCH_WEB },
	service = ConfigurationAction.class)
public class ExperimentalSearchConfigurationAction extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/configuration/experimental-search-configuration.jsp";
	}

	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);
		
		// Si la friendlyURL ne correspond pas à un layout, on
		// renvoie une erreur
		String agendaFriendlyURL = ParamUtil.getString(actionRequest, "agendaFriendlyURL");
		if (LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(
			themeDisplay.getScopeGroupId(), false,
			agendaFriendlyURL) == null) {
			SessionErrors.add(actionRequest, "wrong-friendly-url");
			return;
		}
		setPreference(actionRequest, "agendaFriendlyURL", agendaFriendlyURL);

		// Affichage
		String template = ParamUtil.getString(actionRequest, "template");
		setPreference(actionRequest, "template", template);

		// Titre pour le critère 1
		Map<Locale, String> criteria1Map = LocalizationUtil.getLocalizationMap(actionRequest, "criteria1");
		LocalizedValuesMap criteria1LocalizedValuesMap = new LocalizedValuesMap();
		for (Map.Entry<Locale, String> e : criteria1Map.entrySet()) {
			criteria1LocalizedValuesMap.put(e.getKey(), e.getValue());
		}
		String crtieria1Xml = LocalizationUtil.getXml(criteria1LocalizedValuesMap, "Criteria1");
		setPreference(actionRequest, "criteria1", crtieria1Xml);

		// Options du critère 1
		String[] criteria1Options = new String[6];
		String[] criteria1OptionCategories = new String[6];
		for (int i = 0; i < 6; i++) {
			// Titre de l'option
			Map<Locale, String> optionMap = LocalizationUtil.getLocalizationMap(actionRequest, "criteria1Option" + i);
			LocalizedValuesMap criteria1OptionLocalizedValuesMap = new LocalizedValuesMap();
			for (Map.Entry<Locale, String> e : optionMap.entrySet()) {
				criteria1OptionLocalizedValuesMap.put(e.getKey(), e.getValue());
			}
			String optionXml = LocalizationUtil.getXml(criteria1OptionLocalizedValuesMap, "Option" + i);
			criteria1Options[i] = optionXml;

			// Catégories de l'option
			String optionCategories = ParamUtil.getString(actionRequest, "criteria1Option" + i + "Categories");
			criteria1OptionCategories[i] = optionCategories;
		}
		setPreference(actionRequest, "criteria1Options", criteria1Options);
		setPreference(actionRequest, "criteria1OptionCategories", criteria1OptionCategories);

		// Titre pour le critère 2
		Map<Locale, String> criteria2Map = LocalizationUtil.getLocalizationMap(actionRequest, "criteria2");
		LocalizedValuesMap criteria2LocalizedValuesMap = new LocalizedValuesMap();
		for (Map.Entry<Locale, String> e : criteria2Map.entrySet()) {
			criteria2LocalizedValuesMap.put(e.getKey(), e.getValue());
		}
		String crtieria2Xml = LocalizationUtil.getXml(criteria2LocalizedValuesMap, "Criteria2");
		setPreference(actionRequest, "criteria2", crtieria2Xml);

		// Options du critère 2
		String[] criteria2Options = new String[6];
		String[] criteria2OptionCategories = new String[6];
		for (int i = 0; i < 6; i++) {
			// Titre de l'option
			Map<Locale, String> optionMap = LocalizationUtil.getLocalizationMap(actionRequest, "criteria2Option" + i);
			LocalizedValuesMap criteria2OptionLocalizedValuesMap = new LocalizedValuesMap();
			for (Map.Entry<Locale, String> e : optionMap.entrySet()) {
				criteria2OptionLocalizedValuesMap.put(e.getKey(), e.getValue());
			}
			String optionXml = LocalizationUtil.getXml(criteria2OptionLocalizedValuesMap, "Option" + i);
			criteria2Options[i] = optionXml;

			// Catégories de l'option
			String optionCategories = ParamUtil.getString(actionRequest, "criteria2Option" + i + "Categories");
			criteria2OptionCategories[i] = optionCategories;
		}
		setPreference(actionRequest, "criteria2Options", criteria2Options);
		setPreference(actionRequest, "criteria2OptionCategories", criteria2OptionCategories);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			ExperimentalSearchConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(ExperimentalSearchConfiguration.class);

			request.setAttribute("template", configuration.template());

			request.setAttribute("agendaFriendlyURL", configuration.agendaFriendlyURL());

			request.setAttribute("criteria1", configuration.criteria1());
			request.setAttribute("criteria1Options", configuration.criteria1Options());
			request.setAttribute("criteria1OptionCategories", configuration.criteria1OptionCategories());

			request.setAttribute("criteria2", configuration.criteria2());
			request.setAttribute("criteria2Options", configuration.criteria2Options());
			request.setAttribute("criteria2OptionCategories", configuration.criteria2OptionCategories());

			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			_log.error(e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
