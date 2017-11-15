package eu.strasbourg.portlet.sectorized.configuration;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.service.adict.SectorType;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	configurationPid = "eu.strasbourg.portlet.sectorized.configuration.SectorizedPlacesConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.SECTORIZED_PLACES_WEB },
	service = ConfigurationAction.class)
public class SectorizedPlacesConfigurationAction extends DefaultConfigurationAction {

	@Reference
	private AdictService adictService;
	
	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/configuration/sectorized-configuration.jsp";
	}

	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		// Affichage
		String template = ParamUtil.getString(actionRequest, "template");
		setPreference(actionRequest, "template", template);

		// Types
		String[] types = ParamUtil.getStringValues(actionRequest, "types");
		setPreference(actionRequest, "types", types);
		
		// Utilisation uniquement des rues de Strasbourg
		boolean forceStrasbourg = ParamUtil.getBoolean(actionRequest, "forceStrasbourg");
		setPreference(actionRequest, "forceStrasbourg", String.valueOf(forceStrasbourg));

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			SectorizedPlacesConfiguration configuration = themeDisplay
				.getPortletDisplay()
				.getPortletInstanceConfiguration(SectorizedPlacesConfiguration.class);

			request.setAttribute("template", configuration.template());

			// On passe la liste des types de secteurs à la JSP
			List<SectorType> allTypes = adictService.getSectorTypes();
			request.setAttribute("allTypes", allTypes);
			
			// Types sélectionnés
			request.setAttribute("types", StringUtil.merge(configuration.types()));
			
			// Utilisation uniquement des rues de Strasbourg
			request.setAttribute("forceStrasbourg", configuration.forceStrasbourg());
			
			// Tout ce qui est Application Display Template
			String portletResource = ParamUtil.getString(request,
				"portletResource");
			PortletPreferences preferences = PortletPreferencesFactoryUtil
				.getPortletSetup(request, portletResource);
			String className = Place.class.getName();
			request.setAttribute("className", className);
			String displayStyle = GetterUtil.getString(
				preferences.getValue("displayStyle", StringPool.BLANK));
			request.setAttribute("displayStyle", displayStyle);
			long displayStyleGroupId = GetterUtil
				.getLong(preferences.getValue("displayStyleGroupId", null), 0);
			String refreshURL = PortalUtil.getCurrentURL(request);
			request.setAttribute("displayStyleGroupId", displayStyleGroupId);
			request.setAttribute("refreshURL", refreshURL);
			
			
			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			_log.error(e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
