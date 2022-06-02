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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Notification. This utility wraps
 * <code>eu.strasbourg.service.notification.service.impl.NotificationServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author BenjaminBini
 * @see NotificationService
 * @generated
 */
public class NotificationServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.notification.service.impl.NotificationServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Envoie une notification à un utilisateur
	 */
	public static com.liferay.portal.kernel.json.JSONObject addNotification(
		String userId, boolean isGlobal, String title, String description,
		String url, String publicationDate, String expirationDate,
		String typeId) {

		return getService().addNotification(
			userId, isGlobal, title, description, url, publicationDate,
			expirationDate, typeId);
	}

	/**
	 * Retourne la liste des canaux de notifications
	 */
	public static com.liferay.portal.kernel.json.JSONObject getChannels() {
		return getService().getChannels();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * Retourne la liste des types de notifications
	 */
	public static com.liferay.portal.kernel.json.JSONObject getTypes()
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTypes();
	}

	/**
	 * Retourne la liste des notifications d'un utilisateur
	 */
	public static com.liferay.portal.kernel.json.JSONObject
		getUserNotifications(String userId) {

		return getService().getUserNotifications(userId);
	}

	/**
	 * Retourne la liste des types et des canaux de communication d'un
	 * utilisateur
	 */
	public static com.liferay.portal.kernel.json.JSONObject getUserSettings(
		String userId) {

		return getService().getUserSettings(userId);
	}

	/**
	 * Modification des abonnements et des canaux de communication d'un
	 * utilisateur
	 */
	public static com.liferay.portal.kernel.json.JSONObject setUserSettings(
		String userId, String typeIds, String channelIds) {

		return getService().setUserSettings(userId, typeIds, channelIds);
	}

	public static NotificationService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NotificationService, NotificationService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(NotificationService.class);

		ServiceTracker<NotificationService, NotificationService>
			serviceTracker =
				new ServiceTracker<NotificationService, NotificationService>(
					bundle.getBundleContext(), NotificationService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}