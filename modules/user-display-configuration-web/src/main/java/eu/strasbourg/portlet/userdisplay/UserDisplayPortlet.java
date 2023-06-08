package eu.strasbourg.portlet.userdisplay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import eu.strasbourg.portlet.userdisplay.configuration.UserDisplayConfiguration;
import eu.strasbourg.portlet.userdisplay.configuration.UserDisplayConfigurationDisplayContext;
import eu.strasbourg.utils.PortletHelper;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.convert.ConvertProcessUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author romain.vergnais
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg", "com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.instanceable=false", "javax.portlet.display-name=Personnalisation utilisateur",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/user-display-view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.USER_DISPLAY, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class UserDisplayPortlet extends MVCPortlet {

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public void render(RenderRequest request, RenderResponse renderResponse) throws IOException, PortletException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			UserDisplayConfiguration configuration = themeDisplay
					.getPortletDisplay()
					.getPortletInstanceConfiguration(UserDisplayConfiguration.class);
			UserDisplayConfigurationDisplayContext dc  =
					new UserDisplayConfigurationDisplayContext(themeDisplay, configuration);

			request.setAttribute("dc", dc);
		} catch (Exception e) {
			_log.error(e);
		}
		super.render(request, renderResponse);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		try {
			String resourceID = resourceRequest.getResourceID();
            HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(resourceRequest);
            String portletId = ParamUtil.getString(resourceRequest, "portletId");

			if (resourceID.equals("hidePortlet")) {
                PortletHelper.hidePortlet(portletId);
			} else if (resourceID.equals("showPortlet")) {
                PortletHelper.showPortlet(portletId);
            }
		} catch (Exception e) {
			_log.error(e);
		}

		super.serveResource(resourceRequest, resourceResponse);
	}

}