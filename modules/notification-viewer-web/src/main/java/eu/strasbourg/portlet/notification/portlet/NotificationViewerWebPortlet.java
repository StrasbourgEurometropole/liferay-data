package eu.strasbourg.portlet.notification.portlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import eu.strasbourg.portlet.notification.model.display.NotificationDisplay;
import eu.strasbourg.service.notification.model.UserNotificationStatus;
import eu.strasbourg.service.notification.service.UserNotificationStatusLocalServiceUtil;
import eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author romain.vergnais
 */
@Component(
		immediate = true,
		property = { 
		"com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", 
		"javax.portlet.display-name=Notifications",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/notification-viewer-view.jsp",
		"javax.portlet.init-param.config-template=/configuration/notification-viewer-configuration.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.NOTIFICATION_VIEWER_WEB, 
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
		}, 
		service = Portlet.class)
public class NotificationViewerWebPortlet extends MVCPortlet {

	public void showNotification(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException {

		// Récupération du publik ID avec la session
		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(actionRequest);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
		String internalId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
		Long notificationId = ParamUtil.getLong(actionRequest, "notificationId");

		try {
			// Récupération de la notification pour la passer à 'Lu'
			UserNotificationStatus notif = UserNotificationStatusLocalServiceUtil
					.getUserNotificationStatus(new UserNotificationStatusPK(notificationId, internalId));

			if (!notif.isRead()) {
				notif.setRead(true);
				UserNotificationStatusLocalServiceUtil.updateUserNotificationStatus(notif);
			}

			// Redirection vers l'url de la notification
			actionResponse.sendRedirect(notif.getNotification().getUrl());

		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			NotificationConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(NotificationConfiguration.class);			
			
			// Récupération du publik ID avec la session
			LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(renderRequest);
			HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
			String internalId = SessionParamUtil.getString(originalRequest, "publik_internal_id");

			// Récupération de la liste des notifications de l'utilisateur
			List<UserNotificationStatus> usrNotifStatus = UserNotificationStatusLocalServiceUtil
					.getByPublikUserId(internalId).stream()
					.filter(c -> c.getNotification() != null).sorted((f1, f2) -> f2.getNotification()
							.getPublicationDate().compareTo(f1.getNotification().getPublicationDate()))
					.collect(Collectors.toList());

			// Le nombre de notifications non lus
			long notifCount = usrNotifStatus.stream().filter(c -> !c.isRead()).count();

			List<NotificationDisplay> notifications = new ArrayList<NotificationDisplay>();

			// Création de la liste des notifications à afficher en fonction de
			// la notification, de son statut et de l'utilisateur
			for (UserNotificationStatus un : usrNotifStatus) {
				NotificationDisplay nd = new NotificationDisplay();
				nd.setTitle(un.getNotification().getTitle(renderRequest.getLocale()));
				nd.setRead(un.isRead());
				nd.setDate(un.getNotification().getPublicationDate());
				nd.setNotificationId(un.getNotificationId());
				notifications.add(nd);
			}

			renderRequest.setAttribute("notifications", notifications);
			renderRequest.setAttribute("notifCount", notifCount);

			String template = configuration.template();
			if (Validator.isNull(template)) {
				template = "notification-viewer-view";
			}
			String showAllURL = configuration.showAllURL();
			if(Validator.isNull(showAllURL)){
				showAllURL = "#";
			}
			renderRequest.setAttribute("showAllURL", showAllURL);
			
			include("/" + template + ".jsp", renderRequest, renderResponse);
			
		} catch (Exception e) {
			_log.error(e);
		}
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
	
		try 
		{
             String resourceID = resourceRequest.getResourceID();

             if (resourceID.equals("toggleNotification")) {
            	 
            	// Récupération du publik ID avec la session
         		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
         		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
         		String internalId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
         		Long notificationId = ParamUtil.getLong(resourceRequest, "notificationId");
         		
         		// Récupération de la notification pour la passer à 'Lu'/'Non lu'
    			UserNotificationStatus notif = UserNotificationStatusLocalServiceUtil
    					.getUserNotificationStatus(new UserNotificationStatusPK(notificationId, internalId));
    			
    			//toggle read
				notif.setRead(!notif.isRead());
				UserNotificationStatusLocalServiceUtil.updateUserNotificationStatus(notif);
             }
		}
		catch (Exception e)
		{
			_log.error(e);
		}
		
		super.serveResource(resourceRequest, resourceResponse);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}