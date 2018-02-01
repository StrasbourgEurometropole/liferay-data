package eu.strasbourg.portlet.notification.portlet;

import java.io.IOException;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;

import eu.strasbourg.portlet.notification.constants.NotificationViewerWebPortletKeys;
import eu.strasbourg.portlet.notification.model.display.NotificationDisplay;
import eu.strasbourg.service.notification.model.UserNotificationStatus;
import eu.strasbourg.service.notification.service.UserNotificationStatusLocalServiceUtil;
import eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK;

/**
 * @author romain.vergnais
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=false", "javax.portlet.display-name=notification-viewer-web Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/notification-viewer-view.jsp",
		"javax.portlet.name=" + NotificationViewerWebPortletKeys.NotificationViewerWeb,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class NotificationViewerWebPortlet extends MVCPortlet {

	
	public void showNotification(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException {
		
		// Récupération du publik ID avec la session
		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(actionRequest);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
		String internalId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
		Long notificationId = ParamUtil.getLong(actionRequest, "notificationId");

		try {
			//Récupération de la notification pour la passer à 'Lu'
			UserNotificationStatus notif = UserNotificationStatusLocalServiceUtil
					.getUserNotificationStatus(new UserNotificationStatusPK(notificationId, internalId));
			
			if(!notif.isRead()){
				notif.setRead(true);
				UserNotificationStatusLocalServiceUtil.updateUserNotificationStatus(notif);
			}
			
			//Redirection vers l'url de la notification
			actionResponse.sendRedirect(notif.getNotification().getUrl());
			
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		// Récupération du publik ID avec la session
		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(renderRequest);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
		String internalId = SessionParamUtil.getString(originalRequest, "publik_internal_id");

		// Récupération de la liste des notifications de l'utilisateur
		List<UserNotificationStatus> usrNotifStatus = UserNotificationStatusLocalServiceUtil
				.getByPublikUserId(internalId)
				.stream().filter(c -> c.getNotification() != null).sorted((f1, f2) -> f2.getNotification()
						.getPublicationDate().compareTo(f1.getNotification().getPublicationDate()))
				.collect(Collectors.toList());

		// Le nombre de notifications non lus
		long notifCount = usrNotifStatus.stream().filter(c -> !c.isRead()).count();

		List<NotificationDisplay> notifications = new ArrayList<NotificationDisplay>();

		int countNotif = 0;
		// Création de la liste des notifications à afficher en fonction de la
		// notification, de son statut et de l'utilisateur 
		for (UserNotificationStatus un : usrNotifStatus) {
			NotificationDisplay nd = new NotificationDisplay();
			nd.setTitle(un.getNotification().getTitle(renderRequest.getLocale()));
			nd.setRead(un.isRead());
			nd.setDate(new SimpleDateFormat("dd.MM").format(un.getNotification().getPublicationDate()));
			nd.setNotificationId(un.getNotificationId());
			notifications.add(nd);

			// On affiche 3 notifications Max
			if (++countNotif == 3)
				break;
		}

		renderRequest.setAttribute("notifications", notifications);
		renderRequest.setAttribute("notifCount", notifCount);

		super.render(renderRequest, renderResponse);
	}

}