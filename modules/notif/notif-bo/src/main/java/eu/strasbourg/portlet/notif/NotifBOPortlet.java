package eu.strasbourg.portlet.notif;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.notif.display.context.EditNotificationDisplayContext;
import eu.strasbourg.portlet.notif.display.context.EditServiceDisplayContext;
import eu.strasbourg.portlet.notif.display.context.ViewNotificationsDisplayContext;
import eu.strasbourg.portlet.notif.display.context.ViewServicesDisplayContext;
import eu.strasbourg.utils.constants.RoleNames;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

/**
 * @author angelique.champougny
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.version=3.0",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-css=/css/notif-bo-main.css",
		"com.liferay.portlet.footer-portlet-javascript=/js/notif-bo-main.js",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/notif-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class NotifBOPortlet extends MVCPortlet {
	private ThemeDisplay themeDisplay;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		this.themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");
		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");

		// Verification des requetes issues d'un champ repetable
		Boolean fromAjaxNature = GetterUtil.getBoolean(renderRequest.getAttribute("fromAjaxNature"));
		Boolean fromAjaxMessage = GetterUtil.getBoolean(renderRequest.getAttribute("fromAjaxMessage"));

		renderResponse.setTitle("Notif");

		// If we are on an "add" page, we set a return URL and show the "back"
		// button
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL);
		}
		// If we are on the Session, we add the corresponding
		// display context
		if (cmd.equals("editService") || mvcPath.equals("/notif-bo-edit-service.jsp") || fromAjaxNature || fromAjaxMessage) {
			EditServiceDisplayContext dc = new EditServiceDisplayContext(renderRequest);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editNotification") || mvcPath.equals("/notif-bo-edit-notification.jsp")) {
			EditNotificationDisplayContext dc = new EditNotificationDisplayContext(renderRequest);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("notifications") || !this.isAdminNotification()) {
			ViewNotificationsDisplayContext dc = new ViewNotificationsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else {
			ViewServicesDisplayContext dc = new ViewServicesDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		}

		// Admin Notif ou pas
		renderRequest.setAttribute("isAdminNotification", this.isAdminNotification());

		super.render(renderRequest, renderResponse);
	}


	public boolean isAdminNotification(){
		try {
			Role siteAdministrator = RoleLocalServiceUtil.getRole(this.themeDisplay.getCompanyId(), RoleNames.ADMINISTRATEUR_NOTIFICATION);
			if(themeDisplay.getPermissionChecker().isOmniadmin()
					|| UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(),themeDisplay.getScopeGroupId(), siteAdministrator.getRoleId()))
				return true;
		} catch (PortalException e) {
			e.printStackTrace();
		}
		return false;
	}
}