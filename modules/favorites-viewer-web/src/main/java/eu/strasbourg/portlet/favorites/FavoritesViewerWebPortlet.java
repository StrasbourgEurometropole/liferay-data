package eu.strasbourg.portlet.favorites;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.favorites.configuration.FavoritesConfiguration;
import eu.strasbourg.portlet.favorites.display.context.FavoritesDisplayContext;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.PortletHelper;
import eu.strasbourg.utils.api.PortletHelperService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;


/**
 * @author jeremy.zwickert
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name= Favoris",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.FAVORITES_VIEWER_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.add-process-action-success-action=false"
	},
	service = Portlet.class
)
public class FavoritesViewerWebPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			
			FavoritesConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(FavoritesConfiguration.class);		
			
			
			String template = configuration.template();
			if (Validator.isNull(template)) {
				template = "default";
			}
			String showAllURL = configuration.showAllURL();
			if(Validator.isNull(showAllURL)){
				showAllURL = "#";
			}
			renderRequest.setAttribute("showAllURL", showAllURL);
			
			
			FavoritesDisplayContext dc = new FavoritesDisplayContext(renderRequest, themeDisplay);
			renderRequest.setAttribute("dc", dc);
			
			include("/templates/" + template + ".jsp", renderRequest, renderResponse);
			
		} catch (Exception e) {
			_log.error(e);
		}
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		try {
			String resourceID = resourceRequest.getResourceID();
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			
			if (resourceID.equals("hidePortlet")) {
				String portletName = ParamUtil.getString(resourceRequest, "portletName");
				PortletHelper.hidePortlet(themeDisplay, portletName);
			}
			
		} catch (Exception e) {
			_log.error(e);
		}

		super.serveResource(resourceRequest, resourceResponse);
	}
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}