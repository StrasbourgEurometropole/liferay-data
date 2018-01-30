package eu.strasbourg.portlet.notification.portlet;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;

import eu.strasbourg.portlet.notification.constants.NotificationViewerWebPortletKeys;
import eu.strasbourg.service.notification.model.Notification;
import eu.strasbourg.service.notification.service.NotificationLocalServiceUtil;

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

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
	
		
		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(renderRequest);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
		
		String internalId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
		
		
		List<Notification> notifications = NotificationLocalServiceUtil.getNotifications(-1, -1);
		
		long notifs = notifications.size();

		DynamicQuery dq = NotificationLocalServiceUtil.dynamicQuery();
		
		dq.add(PropertyFactoryUtil.forName("singleUserId").eq(internalId));
		
		long notifCount = NotificationLocalServiceUtil.dynamicQueryCount(dq);
		
		renderRequest.setAttribute("notifications", notifications);
		renderRequest.setAttribute("notifCount", notifCount);
		
		super.render(renderRequest, renderResponse);
	}

}