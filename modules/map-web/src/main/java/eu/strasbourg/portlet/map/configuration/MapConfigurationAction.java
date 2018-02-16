package eu.strasbourg.portlet.map.configuration;

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
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.map.configuration.MapConfiguration;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
		configurationPid = "eu.strasbourg.portlet.map.configuration.MapConfiguration",
		configurationPolicy = ConfigurationPolicy.OPTIONAL,
		immediate = true,	
		property = {
				"javax.portlet.name=" + StrasbourgPortletKeys.MAP_WEB },
		service = ConfigurationAction.class)
public class MapConfigurationAction extends DefaultConfigurationAction{

	/**
	 * Action : Sauvegarde de la configuration si on a validé le formulaire ou
	 * envoi de la JSP des sélecteurs si on a changé la liste déroulante des
	 * types d'entité
	 */
	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest request, ActionResponse response) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(request, "cmd");

		if (cmd.equals("update")) {
			// Choix "nouvel onglet, onglet courant"
			String openInNewTab = ParamUtil.getString(request, "openInNewTab");
			setPreference(request, "openInNewTab", openInNewTab);
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
			MapConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
						MapConfiguration.class);
			
			// Choix "nouvel onglet, onglet courant"
			request.setAttribute("openInNewTab", configuration.openInNewTab());

		} catch (ConfigurationException e) {
			_log.error(e);
		}
		super.include(portletConfig, request, response);
	}
	

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/map-configuration.jsp";
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
