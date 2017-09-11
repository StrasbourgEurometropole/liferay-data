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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Notification. This utility wraps
 * {@link eu.strasbourg.service.notification.service.impl.NotificationServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author BenjaminBini
 * @see NotificationService
 * @see eu.strasbourg.service.notification.service.base.NotificationServiceBaseImpl
 * @see eu.strasbourg.service.notification.service.impl.NotificationServiceImpl
 * @generated
 */
@ProviderType
public class NotificationServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.notification.service.impl.NotificationServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONArray getChannels() {
		return getService().getChannels();
	}

	public static com.liferay.portal.kernel.json.JSONArray getTypes()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTypes();
	}

	public static com.liferay.portal.kernel.json.JSONObject addNotification(
		long userId, java.lang.String title, java.lang.String description,
		java.lang.String url, java.lang.String startDate,
		java.lang.String endDate, long typeId) {
		return getService()
				   .addNotification(userId, title, description, url, startDate,
			endDate, typeId);
	}

	public static com.liferay.portal.kernel.json.JSONObject getUserNotifications(
		long userId) {
		return getService().getUserNotifications(userId);
	}

	public static com.liferay.portal.kernel.json.JSONObject getUserSettings(
		long userId) {
		return getService().getUserSettings(userId);
	}

	/**
	* Modification des abonnements et des canaux de communication d'un
	* utilisateur
	*/
	public static com.liferay.portal.kernel.json.JSONObject setUserSettings(
		long userId, java.lang.String typeIds, java.lang.String channelIds) {
		return getService().setUserSettings(userId, typeIds, channelIds);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static NotificationService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NotificationService, NotificationService> _serviceTracker =
		ServiceTrackerFactory.open(NotificationService.class);
}