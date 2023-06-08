package eu.strasbourg.portlet.association.configuration;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.activity.model.Association;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component(
	configurationPid = "eu.strasbourg.portlet.association.configuration.SearchAssociationConfiguration",
			configurationPolicy = ConfigurationPolicy.OPTIONAL,
			immediate = true,
			property = {
			"javax.portlet.name=" + StrasbourgPortletKeys.ASSOCIATION_SEARCH_WEB },
			service = ConfigurationAction.class)
public class SearchAssociationConfigurationAction extends DefaultConfigurationAction {

	/**
	 * Sauvegarde des préférences
	 */
	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {
		String cmd = ParamUtil.getString(actionRequest, "cmd");
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		if (cmd.equals("update")) {
			// Template correspondant
			String templateKey = ParamUtil
					.getString(actionRequest, "templateKey");

			// Et la friendlyURL du layout de détail correspondant
			String layoutFriendlyURL = ParamUtil
					.getString(actionRequest, "layoutFriendlyURL");
			// Si la friendlyURL ne correspond pas à un layout, on
			// renvoie une erreur
			if (LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(
					themeDisplay.getScopeGroupId(), false,
					layoutFriendlyURL) == null) {
				SessionErrors.add(actionRequest, "wrong-friendly-url");
				return;
			}
			setPreference(actionRequest, "templateKey", templateKey);
			setPreference(actionRequest, "layoutFriendlyURL",
					layoutFriendlyURL);

			// Delta
			long delta = ParamUtil.getLong(actionRequest, "delta");
			setPreference(actionRequest, "delta", String.valueOf(delta));
		}
		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	/**
	 * Send to the JSP the needed data
	 */
	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			SearchAssociationConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
							SearchAssociationConfiguration.class);

			// Liste des templates de l'association
			long classNameId = ClassNameLocalServiceUtil.getClassNameId(Association.class.getName());
			List<DDMTemplate> templateList = DDMTemplateLocalServiceUtil
					.getTemplates(themeDisplay.getScopeGroupId(), classNameId);
			request.setAttribute("templateList", templateList);

			// Template sélectionné
			String templateKey = ParamUtil.getString(request,
					"templateKey");
			if (templateKey.isEmpty()) {
				templateKey = configuration.templateKey();
			}
			request.setAttribute("templateKey", templateKey);

			// Layouts
			String layoutFriendlyURL = configuration.layoutFriendlyURL();
			request.setAttribute("layoutFriendlyURL", layoutFriendlyURL);

			// Delta
			long delta = ParamUtil.getLong(request, "delta",
				configuration.delta());
			request.setAttribute("delta", delta);

			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			_log.error(e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
