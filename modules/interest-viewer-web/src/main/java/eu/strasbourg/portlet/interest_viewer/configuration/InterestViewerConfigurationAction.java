package eu.strasbourg.portlet.interest_viewer.configuration;

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
	configurationPid = "eu.strasbourg.portlet.interest_viewer.configuration.InterestViewerConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.INTEREST_VIEWER_WEB },
	service = ConfigurationAction.class)
public class InterestViewerConfigurationAction
	extends DefaultConfigurationAction {

	/**
	 * Action : Sauvegarde de la configuration si on a validé le formulaire ou
	 * envoi de la JSP des sélecteurs si on a changé la liste déroulante des
	 * types d'entité
	 */
	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest request, ActionResponse response) throws Exception {


		String cmd = ParamUtil.getString(request, "cmd");

		if (cmd.equals("update")) {
			
			String template = ParamUtil.getString(request, "template");
			setPreference(request, "template", template);

			String allNewsURL = ParamUtil.getString(request, "allNewsURL");
			setPreference(request, "allNewsURL", allNewsURL);
			
			String allEventsURL = ParamUtil.getString(request, "allEventsURL");
			setPreference(request, "allEventsURL", allEventsURL);
			
			// Text
			Map<Locale, String> textMap = LocalizationUtil
				.getLocalizationMap(request, "noInterestMap");
			LocalizedValuesMap map = new LocalizedValuesMap();
			for (Map.Entry<Locale, String> e : textMap.entrySet()) {
				map.put(e.getKey(), e.getValue());
			}
			String noInterestXML = LocalizationUtil.getXml(map, "noInterest");
			setPreference(request, "noInterestXML", noInterestXML);
			
			int eventNumberOnListPage = ParamUtil.getInteger(request, "eventNumberOnListPage");
			setPreference(request, "eventNumberOnListPage", String.valueOf(eventNumberOnListPage));

			int newsNumberOnListPage = ParamUtil.getInteger(request, "newsNumberOnListPage");
			setPreference(request, "newsNumberOnListPage", String.valueOf(newsNumberOnListPage));
		}
		super.processAction(portletConfig, request, response);
	}

	/**
	 * Envoie à la JSP de configuration des informations nécessaires
	 */
	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			InterestViewerConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
						InterestViewerConfiguration.class);
			
			request.setAttribute("template", configuration.template());
			request.setAttribute("allNewsURL", configuration.allNewsURL());
			request.setAttribute("allEventsURL", configuration.allEventsURL());
			request.setAttribute("noInterest", configuration.noInterestXML());
			request.setAttribute("eventNumberOnListPage", configuration.eventNumberOnListPage());
			request.setAttribute("newsNumberOnListPage", configuration.newsNumberOnListPage());			
		} catch (ConfigurationException e) {
			_log.error(e);
		}
		super.include(portletConfig, request, response);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
