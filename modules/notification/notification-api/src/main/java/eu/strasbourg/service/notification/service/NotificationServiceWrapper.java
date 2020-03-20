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

package eu.strasbourg.service.notification.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NotificationService}.
 *
 * @author BenjaminBini
 * @see NotificationService
 * @generated
 */
@ProviderType
public class NotificationServiceWrapper
	implements NotificationService, ServiceWrapper<NotificationService> {

	public NotificationServiceWrapper(NotificationService notificationService) {
		_notificationService = notificationService;
	}

	/**
	 * Envoie une notification à un utilisateur
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONObject addNotification(
		String userId, boolean isGlobal, String title, String description,
		String url, String publicationDate, String expirationDate,
		String typeId) {

		return _notificationService.addNotification(
			userId, isGlobal, title, description, url, publicationDate,
			expirationDate, typeId);
	}

	/**
	 * Retourne la liste des canaux de notifications
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONObject getChannels() {
		return _notificationService.getChannels();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _notificationService.getOSGiServiceIdentifier();
	}

	/**
	 * Retourne la liste des types de notifications
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONObject getTypes()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationService.getTypes();
	}

	/**
	 * Retourne la liste des notifications d'un utilisateur
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONObject getUserNotifications(
		String userId) {

		return _notificationService.getUserNotifications(userId);
	}

	/**
	 * Retourne la liste des types et des canaux de communication d'un
	 * utilisateur
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONObject getUserSettings(
		String userId) {

		return _notificationService.getUserSettings(userId);
	}

	/**
	 * Modification des abonnements et des canaux de communication d'un
	 * utilisateur
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONObject setUserSettings(
		String userId, String typeIds, String channelIds) {

		return _notificationService.setUserSettings(
			userId, typeIds, channelIds);
	}

	@Override
	public NotificationService getWrappedService() {
		return _notificationService;
	}

	@Override
	public void setWrappedService(NotificationService notificationService) {
		_notificationService = notificationService;
	}

	private NotificationService _notificationService;

}