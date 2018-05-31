package eu.strasbourg.portlet.mediatheque.configuration;

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
	configurationPid = "eu.strasbourg.portlet.mediatheque.configuration.MediathequeConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.MEDIATHEQUE_WEB },
	service = ConfigurationAction.class)
public class MediathequeConfigurationAction
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

			// Error
			Map<Locale, String> errorMap = LocalizationUtil
				.getLocalizationMap(request, "errorMap");
			LocalizedValuesMap map = new LocalizedValuesMap();
			for (Map.Entry<Locale, String> e : errorMap.entrySet()) {
				map.put(e.getKey(), e.getValue());
			}
			String errorXML = LocalizationUtil.getXml(map, "error");
			setPreference(request, "errorXML", errorXML);
			
			// Demarche
			Map<Locale, String> demarcheMap = LocalizationUtil
				.getLocalizationMap(request, "demarcheMap");
			map = new LocalizedValuesMap();
			for (Map.Entry<Locale, String> e : demarcheMap.entrySet()) {
				map.put(e.getKey(), e.getValue());
			}
			String demarcheXML = LocalizationUtil.getXml(map, "demarche");
			setPreference(request, "demarcheXML", demarcheXML);
			
			// URL retour
			String retourURL = ParamUtil.getString(request, "retourURL");
			setPreference(request, "retourURL", retourURL);
			
			// URL contact
			String contactURL = ParamUtil.getString(request, "contactURL");
			setPreference(request, "contactURL", contactURL);
			
			// URL médiathèque
			String mediathequeURL = ParamUtil.getString(request, "mediathequeURL");
			setPreference(request, "mediathequeURL", mediathequeURL);
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

			// Pages sélectionnées
			MediathequeConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
					MediathequeConfiguration.class);
			request.setAttribute("error", configuration.errorXML());
			request.setAttribute("demarche", configuration.demarcheXML());
			request.setAttribute("retourURL", configuration.retourURL());
			request.setAttribute("contactURL", configuration.contactURL());
			request.setAttribute("mediathequeURL", configuration.mediathequeURL());
			
		} catch (ConfigurationException e) {
			_log.error(e);
		}
		super.include(portletConfig, request, response);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
