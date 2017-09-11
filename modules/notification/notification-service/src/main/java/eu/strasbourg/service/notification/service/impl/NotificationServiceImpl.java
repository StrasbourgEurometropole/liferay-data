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

package eu.strasbourg.service.notification.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;

import eu.strasbourg.service.notification.model.UserNotificationStatus;
import eu.strasbourg.service.notification.service.UserNotificationStatusLocalServiceUtil;
import eu.strasbourg.service.notification.service.base.NotificationServiceBaseImpl;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;

/**
 * The implementation of the notification remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.notification.service.NotificationService}
 * interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author BenjaminBini
 * @see NotificationServiceBaseImpl
 * @see eu.strasbourg.service.notification.service.NotificationServiceUtil
 */
public class NotificationServiceImpl extends NotificationServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.notification.service.NotificationServiceUtil} to
	 * access the notification remote service.
	 */
	private Log log = LogFactoryUtil.getLog(this.getClass());

	@Override
	public JSONArray getTypes() throws PortalException {
		AssetVocabulary notificationTypes = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.NOTIFICATION_TYPE);
		return AssetVocabularyHelper.toJSON(notificationTypes);
	}

	@Override
	public JSONArray getChannels() {
		// TODO CHANNELS
		return null;
	}

	@Override
	public JSONObject getUserSettings(long userId) {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		List<AssetCategory> types = this.userNotificationTypeLocalService.getUserNotificationTypes(userId);
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("types", AssetVocabularyHelper.toJSON(types));

		// TODO CHANNELS
		return null;
	}

	@Override
	public JSONObject getUserNotifications(long userId) {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray notificationArray = JSONFactoryUtil.createJSONArray();
		List<UserNotificationStatus> statuses = UserNotificationStatusLocalServiceUtil.getByUserId(userId);
		for (UserNotificationStatus status : statuses) {
			notificationArray.put(status.toJSON());
		}
		result.put("notifications", notificationArray);
		return result;
	}

	/**
	 * Modification des abonnements et des canaux de communication d'un
	 * utilisateur
	 */
	@Override
	public JSONObject setUserSettings(long userId, String typeIds, String channelIds) {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		// On va d'abord vérifier que tout est ok avec les paramètres et remplir
		// une liste de types et une liste de canaux qu'on enregistrera en base
		// si tout est ok
		// Vérification de l'utilisateur
		PublikUser user = PublikUserLocalServiceUtil.fetchPublikUser(userId);
		if (user == null) {
			return error("user does not exist");
		}
		List<AssetCategory> subscriptions = new ArrayList<AssetCategory>();
		for (String typeIdStr : typeIds.split(",")) {
			try {
				// Vérification du type
				long typeId = Long.parseLong(typeIdStr);
				AssetCategory type = AssetCategoryLocalServiceUtil.fetchAssetCategory(typeId);
				if (type == null) {
					return error("type does not exist");
				}
				subscriptions.add(type);
			} catch (Exception ex) {
				return error("incorrect typeIds value");
			}
		}
		// TODO : vérification channels

		// Enregistrement des nouveaux abonnements
		this.userNotificationTypeLocalService.replaceUserSubscriptions(userId, subscriptions);
		
		// TODO : enregistrement des nouveaux channels
		
		return this.getUserSettings(userId);
	}

	@Override
	public JSONObject addNotification(long userId, String title, String description, String url, String startDate,
			String endDate, long typeId) {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		return null;
	}

	private boolean isAuthorized() {
		try {
			Company defaultCompany = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
			long globalGroupId = defaultCompany.getGroup().getGroupId();
			return this.getPermissionChecker().hasPermission(globalGroupId, StrasbourgPortletKeys.NOTIFICATION_BO,
					StrasbourgPortletKeys.NOTIFICATION_BO, "CONTRIBUTE");
		} catch (PortalException e) {
			log.error(e);
			return false;
		}
	}

	private JSONObject error(String message) {
		return JSONFactoryUtil.createJSONObject().put("error", message);
	}
}