package eu.strasbourg.portlet.objtp.configuration;

import java.util.ArrayList;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.objtp.service.ObjectCategoryLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
		configurationPid = "eu.strasbourg.portlet.objtp.configuration.ObjtpConfiguration",
		configurationPolicy = ConfigurationPolicy.OPTIONAL,
		immediate = true,	
		property = {
				"javax.portlet.name=" + StrasbourgPortletKeys.OBJTP_WEB },
		service = ConfigurationAction.class)
public class ObjtpConfigurationAction extends DefaultConfigurationAction{

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
			
			// Titre de la page
			String title = ParamUtil.getString(request, "title");
			setPreference(request, "title", title);
			
			String categoryCodes= new String();
			
			long categoriesCount = ParamUtil.getLong(request,
					"categoriesCount");
				int j = 0;
				for (long i = 0; i < categoriesCount; i++) {
					String categoryCodeString = ParamUtil.getString(request, "categoryCode_" + i);
					boolean assetClassNameSelected = !Validator.isNull(categoryCodeString)
						&& !categoryCodeString.equals("false");
					if (assetClassNameSelected) {
						if (categoryCodes.length() > 0) {
							categoryCodes += ",";
						}
						categoryCodes += categoryCodeString;
					}
				}			
			setPreference(request, "categoryCodes", categoryCodes);

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
			ObjtpConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
						ObjtpConfiguration.class);
			
			// Titre
			request.setAttribute("title", configuration.title());
			
			// Codes de catégorie d'objets trouvés
			request.setAttribute("categoryCodes", configuration.categoryCodes());

			request.setAttribute("allCategories", ObjectCategoryLocalServiceUtil.getObjectCategories(-1, -1));
			
			
		} catch (ConfigurationException e) {
			_log.error(e);
		}
		super.include(portletConfig, request, response);
	}
	

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/configuration/objtp-configuration.jsp";
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

