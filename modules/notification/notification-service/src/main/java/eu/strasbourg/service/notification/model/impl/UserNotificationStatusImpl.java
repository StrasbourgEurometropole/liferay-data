/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.notification.model.impl;

import java.text.DateFormat;
import java.util.Locale;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.notification.model.Notification;
import eu.strasbourg.service.notification.service.NotificationLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

/**
 * The extended model implementation for the UserNotificationStatus service.
 * Represents a row in the &quot;notification_UserNotificationStatus&quot;
 * database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the
 * {@link eu.strasbourg.service.notification.model.UserNotificationStatus}
 * interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class UserNotificationStatusImpl extends UserNotificationStatusBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a user
	 * notification status model instance should use the {@link
	 * eu.strasbourg.service.notification.model.UserNotificationStatus}
	 * interface instead.
	 */
	public UserNotificationStatusImpl() {
	}

	/**
	 * Retourne la version JSON de l'objet
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		Notification notification = NotificationLocalServiceUtil.fetchNotification(this.getNotificationId());
		if (notification != null) {
			result.put("id", notification.getNotificationId());
			result.put("title", notification.getTitle(Locale.FRANCE));
			result.put("url", notification.getUrl());
			if (notification.getType() != null) {
				result.put("type", notification.getType().getTitle(Locale.FRANCE));
				result.put("typeId", AssetVocabularyHelper.getExternalId(notification.getType()));
			}
			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			result.put("publicationDate", dateFormat.format(notification.getPublicationDate()));
			result.put("expirationDate", dateFormat.format(notification.getExpirationDate()));
			result.put("isRead", this.isRead());
		}

		return result;
	}
	
	public Notification getNotification(){
		return NotificationLocalServiceUtil.fetchNotification(this.getNotificationId());
	}
}