/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.strasbourg.portlet.notification.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.notification.model.Notification;
import eu.strasbourg.service.notification.service.NotificationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.NOTIFICATION_BO,
		"mvc.command.name=saveNotification" }, service = MVCActionCommand.class)
public class SaveNotificationActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY)).getCompanyGroupId());
			long notificationId = ParamUtil.getLong(request, "notificationId");
			Notification notification;
			if (notificationId == 0) {
				notification = _notificationLocalService.createNotification(sc);
			} else {
				notification = _notificationLocalService.getNotification(notificationId);
			}

			Map<Locale, String> title = LocalizationUtil.getLocalizationMap(request, "title");
			notification.setTitleMap(title);
			Map<Locale, String> description = LocalizationUtil.getLocalizationMap(request, "description");
			notification.setDescriptionMap(description);

			long typeId = ParamUtil.getLong(request, "typeId");
			notification.setTypeId(typeId);

			String publicationDateString = ParamUtil.getString(request, "publicationDate");
			String publicationDateTimeString = ParamUtil.getString(request, "publicationDateTime");
			Date publicationDate = GetterUtil.getDate(publicationDateString + " " + publicationDateTimeString,
					new SimpleDateFormat("dd/MM/yyyy hh:mm"));
			notification.setPublicationDate(publicationDate);
			
			String expirationDateString = ParamUtil.getString(request, "expirationDate");
			String expirationDateTimeString = ParamUtil.getString(request, "expirationDateTime");
			Date expirationDate = GetterUtil.getDate(expirationDateString + " " + expirationDateTimeString,
					new SimpleDateFormat("dd/MM/yyyy hh:mm"));
			notification.setExpirationDate(expirationDate);

			_notificationLocalService.updateNotification(notification, sc);

			// Redirection (évite double
			// requête POST si l'utilisateur actualise sa page)
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
			PortletURL renderUrl = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
					PortletRequest.RENDER_PHASE);
			response.sendRedirect(renderUrl.toString());
		} catch (Exception e) {
			_log.error(e);
		}

		return true;
	}

	private NotificationLocalService _notificationLocalService;

	@Reference(unbind = "-")
	protected void setNotificationLocalService(NotificationLocalService notificationLocalService) {

		_notificationLocalService = notificationLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}