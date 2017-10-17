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

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.notification.model.Notification;
import eu.strasbourg.service.notification.model.NotificationChannel;
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

	/**
	 * Retourne la liste des types de notifications
	 */
	@Override
	public JSONObject getTypes() throws PortalException {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		AssetVocabulary notificationTypes = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.NOTIFICATION_TYPE);
		JSONArray jsonTypes = AssetVocabularyHelper.toJSON(notificationTypes);
		result.put("types", jsonTypes);
		return result;
	}

	/**
	 * Retourne la liste des canaux de notifications
	 */
	@Override
	public JSONObject getChannels() {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray jsonChannels = JSONFactoryUtil.createJSONArray();
		for (NotificationChannel channel : NotificationChannel.values()) {
			JSONObject jsonChannel = JSONFactoryUtil.createJSONObject();
			jsonChannel.put("id", channel.getId());
			jsonChannel.put("name", channel.getName());
			jsonChannels.put(jsonChannel);
		}
		result.put("channels", jsonChannels);
		return result;
	}

	/**
	 * Retourne la liste des types et des canaux de communication d'un
	 * utilisateur
	 */
	@Override
	public JSONObject getUserSettings(String userId) {
		if (!isAuthorized()) {
			return error("not authorized");
		}

		// Types
		List<AssetCategory> types = this.userNotificationTypeLocalService.getUserNotificationTypes(userId);
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("userId", userId);
		result.put("types", AssetVocabularyHelper.toJSON(types));

		// Canaux
		List<NotificationChannel> channels = this.userNotificationChannelLocalService
				.getUserNotificationChannels(userId);
		JSONArray jsonChannels = JSONFactoryUtil.createJSONArray();
		for (NotificationChannel channel : channels) {
			jsonChannels.put(channel.getId());
		}
		result.put("channels", channels);
		return result;
	}

	/**
	 * Retourne la liste des notifications d'un utilisateur
	 */
	@Override
	public JSONObject getUserNotifications(String userId) {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray notificationArray = JSONFactoryUtil.createJSONArray();
		List<UserNotificationStatus> statuses = UserNotificationStatusLocalServiceUtil.getByPublikUserId(userId);
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
	public JSONObject setUserSettings(String userId, String typeIds, String channelIds) {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		// On va d'abord vérifier que tout est ok avec les paramètres et remplir
		// une liste de types et une liste de canaux qu'on enregistrera en base
		// si tout est ok
		List<AssetCategory> subscriptions = new ArrayList<AssetCategory>();
		List<NotificationChannel> channels = new ArrayList<NotificationChannel>();
		try {
			// Vérification et récupération des types
			AssetVocabulary notificationTypes;
			notificationTypes = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.NOTIFICATION_TYPE);
			for (String typeIdStr : typeIds.split(",")) {
				try {
					AssetCategory type = AssetVocabularyHelper.getCategoryByExternalId(notificationTypes, typeIdStr);
					if (type == null) {
						return error("type does not exist");
					}
					subscriptions.add(type);
				} catch (Exception ex) {
					return error("incorrect typeIds value");
				}
			}
			// Vérification et récupération des canaux
			for (String channelId : channelIds.split(",")) {
				try {
					NotificationChannel channel = NotificationChannel.get(Integer.parseInt(channelId));
					if (channel == null) {
						return error("channel does not exist");
					}
					channels.add(channel);
				} catch (Exception ex) {
					return error("incorrect channelIds value");
				}
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}

		// Enregistrement des nouveaux abonnements
		this.userNotificationTypeLocalService.replaceUserSubscriptions(userId, subscriptions);

		// Enregistrement des nouveaux channels
		this.userNotificationChannelLocalService.replaceUserChannels(userId, channels);

		return this.getUserSettings(userId);
	}

	/**
	 * Envoie une notification à un utilisateur
	 */
	@Override
	public JSONObject addNotification(String userId, boolean isGlobal, String title, String description, String url,
			String publicationDate, String expirationDate, String typeId) {
		if (!isAuthorized()) {
			return error("not authorized");
		}

		try {
			// Validation
			if (isGlobal && Validator.isNotNull(userId)) {
				return error("isGlobal is true but userId is not empty");
			}
			if (!isGlobal) {
				PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(userId);
				if (user == null) {
					return error("user does not exist");
				}
			}
			if (Validator.isNull(title)) {
				return error("title is empty");
			}
			if (Validator.isNull(description)) {
				return error("description is empty");
			}
			if (Validator.isNotNull(url) && !Validator.isUrl(url)) {
				return error("url is not valid");
			}
			try {
				LocalDateTime publicationDateTime = LocalDateTime.parse(publicationDate);
				LocalDateTime expirationDateTime = LocalDateTime.parse(expirationDate);
				if (publicationDateTime.isAfter(expirationDateTime)) {
					return error("publication date is after expiration date");
				}
			} catch (DateTimeParseException e) {
				return error("wrong date format");
			}
			AssetVocabulary notificationTypesVocabulary = AssetVocabularyHelper
					.getGlobalVocabulary(VocabularyNames.NOTIFICATION_TYPE);
			AssetCategory type = AssetVocabularyHelper.getCategoryByExternalId(notificationTypesVocabulary, typeId);
			if (type == null) {
				return error("type does not exist");
			}

			// Tout est ok, on peut enregistrer
			ServiceContext sc = new ServiceContext();

			LocalDateTime publicationDateTime = LocalDateTime.parse(publicationDate);
			Instant publicationInstant = publicationDateTime.atZone(ZoneId.systemDefault()).toInstant();
			LocalDateTime expirationDateTime = LocalDateTime.parse(expirationDate);
			Instant expirationInstant = expirationDateTime.atZone(ZoneId.systemDefault()).toInstant();
			sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

			Notification notification = this.notificationLocalService.createNotification(sc);
			notification.setTitle(title, Locale.FRANCE);
			notification.setDescription(title, Locale.FRANCE);
			notification.setPublicationDate(Date.from(publicationInstant));
			notification.setExpirationDate(Date.from(expirationInstant));
			notification.setUrl(url);
			notification.setTypeId(type.getCategoryId());
			notification.setAutomatic(true);
			if (!isGlobal) {
				notification.setSingleUserId(userId);
				notification.setSingleUser(true);
			}
			this.notificationLocalService.updateNotification(notification, sc);
			return success("notification sent");
		} catch (PortalException e) {
			return error("unknown error");
		}

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

	private JSONObject success(String message) {
		return JSONFactoryUtil.createJSONObject().put("sucess", message);
	}

	private JSONObject error(String message) {
		return JSONFactoryUtil.createJSONObject().put("error", message);
	}
}