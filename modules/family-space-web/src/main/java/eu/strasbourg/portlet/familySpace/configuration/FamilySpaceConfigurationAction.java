package eu.strasbourg.portlet.familySpace.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(
	configurationPid = "eu.strasbourg.portlet.familySpace.configuration.FamilySpaceConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.FAMILY_SPACE_WEB },
	service = ConfigurationAction.class)
public class FamilySpaceConfigurationAction
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

			// Maitenance
			String maintenance = ParamUtil.getString(request, "maintenance");
			setPreference(request, "maintenance", maintenance);

			// URL ajout d'un repas
			String addLunchURL = ParamUtil.getString(request, "addLunchURL");
			setPreference(request, "addLunchURL", addLunchURL);

			// URL lier un compte
			String linkAccountURL = ParamUtil.getString(request, "linkAccountURL");
			setPreference(request, "linkAccountURL", linkAccountURL);
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
			FamilySpaceConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
						FamilySpaceConfiguration.class);
			request.setAttribute("maintenance", configuration.maintenance());
			request.setAttribute("addLunchURL", configuration.addLunchURL());
			request.setAttribute("linkAccountURL", configuration.linkAccountURL());
			
		} catch (ConfigurationException e) {
			_log.error(e);
		}
		super.include(portletConfig, request, response);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
