package eu.strasbourg.portlet.userdisplay.configuration;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import java.util.List;

@Component(
	configurationPid = "eu.strasbourg.portlet.userdisplay.UserDisplayConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.USER_DISPLAY },
	service = ConfigurationAction.class)
public class UserDisplayConfigurationAction extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/configuration/user-display-configuration.jsp";
	}

	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
                .getAttribute(WebKeys.THEME_DISPLAY);

        UserDisplayConfiguration configuration = themeDisplay
                .getPortletDisplay()
                .getPortletInstanceConfiguration(UserDisplayConfiguration.class);

        UserDisplayConfigurationDisplayContext dc = new UserDisplayConfigurationDisplayContext(themeDisplay, configuration);
        List<String> portletIds = dc.getPortletIds();
        JSONArray jsonConfig = JSONFactoryUtil.createJSONArray();
        for (String portletId : portletIds) {
            JSONObject jsonPortletConfig = JSONFactoryUtil.createJSONObject();
			String portletDisplayStatus = ParamUtil.getString(actionRequest, "display_" + portletId);
			String portletRetractableStatus = ParamUtil.getString(actionRequest, "retractable_" + portletId);
            String portletTitle = ParamUtil.getString(actionRequest, portletId + "Title");
            String portletDescription = ParamUtil.getString(actionRequest, portletId + "Description");
            jsonPortletConfig.put("portletId", portletId);
			jsonPortletConfig.put("displayStatus", portletDisplayStatus);
			jsonPortletConfig.put("retractableStatus", portletRetractableStatus);
            jsonPortletConfig.put("title", portletTitle);
            jsonPortletConfig.put("description", portletDescription);
            jsonConfig.put(jsonPortletConfig);
        }
        setPreference(actionRequest, "adminConfig", jsonConfig.toJSONString());

		ExpandoBridge ed = themeDisplay.getScopeGroup().getExpandoBridge();
		try {
			ed.setAttribute("user_display_global_config", jsonConfig.toJSONString());
		} catch (Exception ex) {
			_log.error("Missing expando field : user_display_global_config");
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			UserDisplayConfiguration configuration = themeDisplay
				.getPortletDisplay()
				.getPortletInstanceConfiguration(UserDisplayConfiguration.class);

			UserDisplayConfigurationDisplayContext dc = new UserDisplayConfigurationDisplayContext(themeDisplay, configuration);
			request.setAttribute("dc", dc);

			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			_log.error(e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
