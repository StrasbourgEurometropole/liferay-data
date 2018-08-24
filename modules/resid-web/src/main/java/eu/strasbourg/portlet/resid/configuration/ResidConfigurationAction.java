package eu.strasbourg.portlet.resid.configuration;

import java.util.List;

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

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	configurationPid = "eu.strasbourg.portlet.resid.configuration.ResidConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.RESID_WEB },
	service = ConfigurationAction.class)
public class ResidConfigurationAction
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

			// URL liaison
			String liaisonURL = ParamUtil.getString(request, "liaisonURL");
			setPreference(request, "liaisonURL", liaisonURL);

			// URL resid
			String residURL = ParamUtil.getString(request, "residURL");
			setPreference(request, "residURL", residURL);

			// Listes des zones
			int nbZone = ParamUtil.getInteger(request, "nbZone");
			String[] zones = new String[nbZone];
			String[] code = ParamUtil.getStringValues(request, "code");
			String[] url = ParamUtil.getStringValues(request, "url");	
			for (int i = 0; i < nbZone; i++) {	
				zones[i] = code[i] + ";" + url[i];
			}
			setPreference(request, "zones", zones);
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
			ResidConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
						ResidConfiguration.class);
			request.setAttribute("liaisonURL", configuration.liaisonURL());
			request.setAttribute("residURL", configuration.residURL());
			request.setAttribute("zones", configuration.zones());
			
		} catch (ConfigurationException e) {
			_log.error(e);
		}
		super.include(portletConfig, request, response);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
