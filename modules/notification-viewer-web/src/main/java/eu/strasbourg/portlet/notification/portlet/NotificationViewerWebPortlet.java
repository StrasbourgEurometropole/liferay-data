package eu.strasbourg.portlet.notification.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.notification.configuration.NotificationConfiguration;
import eu.strasbourg.portlet.notification.portlet.context.NotificationViewerDisplayContext;
import eu.strasbourg.service.notification.model.Notification;
import eu.strasbourg.service.notification.model.UserNotificationStatus;
import eu.strasbourg.service.notification.service.UserNotificationStatusLocalServiceUtil;
import eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author romain.vergnais
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=Notifications",
		"javax.portlet.init-param.add-process-action-success-action=false", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/notification-viewer-view.jsp",
		"javax.portlet.init-param.config-template=/configuration/notification-viewer-configuration.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.NOTIFICATION_VIEWER_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class NotificationViewerWebPortlet extends MVCPortlet {

	public void showNotification(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException {
		// Supprime le message d'erreur s'il existe
		// SessionMessages.add(actionRequest,
		// PortalUtil.getPortletId(actionRequest) +
		// SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

		// Récupération du publik ID avec la session
		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(actionRequest);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
		String internalId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
		Long notificationId = ParamUtil.getLong(actionRequest, "notificationId");

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {
			// Récupération de la notification pour la passer à 'Lu'
			UserNotificationStatus notif = UserNotificationStatusLocalServiceUtil
					.getUserNotificationStatus(new UserNotificationStatusPK(notificationId, internalId));

			if (!notif.isRead()) {
				notif.setRead(true);
				UserNotificationStatusLocalServiceUtil.updateUserNotificationStatus(notif);
			}

			// Redirection vers l'url de la notification
			if (!notif.getNotification().getUrl().isEmpty()) {
				actionResponse.sendRedirect(notif.getNotification().getUrl());
			}
			// Redirection vers une page de détail de notif
			else if(notif.getNotification().getUrl().isEmpty() && !notif.getNotification().getDescription().isEmpty()){
				String portalURL ="";
				if(themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname().isEmpty()) {
					portalURL="/web" + themeDisplay.getLayout().getGroup().getFriendlyURL();
				}
				actionResponse.sendRedirect(portalURL + "/notification?notificationId=" + notificationId);
			}

		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		NotificationViewerDisplayContext dc = new NotificationViewerDisplayContext(renderRequest, renderResponse);
		renderRequest.setAttribute("dc", dc);
		
		//Récupération de 'Id de notif pour l'affichage sur la page de détail
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest)); 
		String notifID = httpReq.getParameter("notificationId");
		if(notifID != null) {
			Long notificationId = Long.parseLong(notifID);
			if(!notificationId.equals((long)0)) {
				LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(renderRequest);
				HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
				String internalId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
				try {
					UserNotificationStatus notif = UserNotificationStatusLocalServiceUtil
						.getUserNotificationStatus(new UserNotificationStatusPK(notificationId, internalId));
					
					Notification notification =notif.getNotification();
					renderRequest.setAttribute("notification", notification);
					
				} catch (Exception e) {
					SessionErrors.add(renderRequest, e.getClass().getName());
				}
			}
		}
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		NotificationConfiguration configuration;
		try {
			configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(NotificationConfiguration.class);

			String showAllURL = configuration.showAllURL();
			if (Validator.isNull(showAllURL)) {
				showAllURL = "#";
			}
			renderRequest.setAttribute("showAllURL", showAllURL);

			String template = configuration.template();
			if (Validator.isNull(template)) {
				template = "notification-viewer-view";
			}
			include("/" + template + ".jsp", renderRequest, renderResponse);
		} catch (ConfigurationException e) {
			_log.error(e.getMessage(), e);
		}
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("toggleNotification")) {

				// Récupération du publik ID avec la session
				LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
				HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
				String internalId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
				Long notificationId = ParamUtil.getLong(resourceRequest, "notificationId");

				// Récupération de la notification pour la passer à 'Lu'/'Non
				// lu'
				UserNotificationStatus notif = UserNotificationStatusLocalServiceUtil
						.getUserNotificationStatus(new UserNotificationStatusPK(notificationId, internalId));

				// toggle read
				notif.setRead(!notif.isRead());
				UserNotificationStatusLocalServiceUtil.updateUserNotificationStatus(notif);
			}
		} catch (Exception e) {
			_log.error(e);
		}

		super.serveResource(resourceRequest, resourceResponse);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}