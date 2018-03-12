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
public class NotificationServiceWrapper implements NotificationService,
	ServiceWrapper<NotificationService> {
	public NotificationServiceWrapper(NotificationService notificationService) {
		_notificationService = notificationService;
	}

	/**
	* Envoie une notification à un utilisateur
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject addNotification(
		java.lang.String userId, boolean isGlobal, java.lang.String title,
		java.lang.String url, java.lang.String publicationDate,
		java.lang.String expirationDate, java.lang.String typeId) {
		return _notificationService.addNotification(userId, isGlobal, title,
			url, publicationDate, expirationDate, typeId);
	}

	/**
	* Retourne la liste des canaux de notifications
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject getChannels() {
		return _notificationService.getChannels();
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
		java.lang.String userId) {
		return _notificationService.getUserNotifications(userId);
	}

	/**
	* Retourne la liste des types et des canaux de communication d'un
	* utilisateur
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject getUserSettings(
		java.lang.String userId) {
		return _notificationService.getUserSettings(userId);
	}

	/**
	* Modification des abonnements et des canaux de communication d'un
	* utilisateur
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject setUserSettings(
		java.lang.String userId, java.lang.String typeIds,
		java.lang.String channelIds) {
		return _notificationService.setUserSettings(userId, typeIds, channelIds);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _notificationService.getOSGiServiceIdentifier();
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