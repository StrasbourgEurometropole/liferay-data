package eu.strasbourg.portlet.page_header.configuration;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

@Component(
	configurationPid = "eu.strasbourg.portlet.page_header.configuration.PageHeaderConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=eu_strasbourg_portlet_page_header_PageHeaderPortlet" },
	service = ConfigurationAction.class)
public class PageHeaderConfigurationAction extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/page-header-configuration.jsp";
	}

	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String imageCredit = ParamUtil.getString(actionRequest, "imageCredit");
		setPreference(actionRequest, "imageCredit", imageCredit);

		boolean displayShareButtons = ParamUtil.getBoolean(actionRequest,
			"displayShareButtons", false);
		setPreference(actionRequest, "displayShareButtons",
			String.valueOf(displayShareButtons));
		
		boolean displayImage = ParamUtil.getBoolean(actionRequest,
			"displayImage", false);
		setPreference(actionRequest, "displayImage",
			String.valueOf(displayImage));

		boolean alternativeTheme = ParamUtil.getBoolean(actionRequest,
			"alternativeTheme", false);
		setPreference(actionRequest, "alternativeTheme",
			String.valueOf(alternativeTheme));

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			PageHeaderConfiguration configuration = themeDisplay
				.getPortletDisplay()
				.getPortletInstanceConfiguration(PageHeaderConfiguration.class);

			request.setAttribute("imageCredit", ParamUtil.getString(
				request, "imageCredit", configuration.imageCredit()));
			
			request.setAttribute("displayShareButtons",
				ParamUtil.getBoolean(request, "displayShareButtons",
					configuration.displayShareButtons()));
			
			request.setAttribute("displayImage",
				ParamUtil.getBoolean(request, "displayImage",
					configuration.displayImage()));

			request.setAttribute("alternativeTheme",
				ParamUtil.getBoolean(request, "alternativeTheme",
					configuration.alternativeTheme()));
			
			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
