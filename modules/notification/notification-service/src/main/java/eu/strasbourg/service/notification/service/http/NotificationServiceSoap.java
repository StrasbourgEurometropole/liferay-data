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

package eu.strasbourg.service.notification.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import eu.strasbourg.service.notification.service.NotificationServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link NotificationServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link eu.strasbourg.service.notification.model.NotificationSoap}.
 * If the method in the service utility returns a
 * {@link eu.strasbourg.service.notification.model.Notification}, that is translated to a
 * {@link eu.strasbourg.service.notification.model.NotificationSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author BenjaminBini
 * @see NotificationServiceHttp
 * @see eu.strasbourg.service.notification.model.NotificationSoap
 * @see NotificationServiceUtil
 * @generated
 */
@ProviderType
public class NotificationServiceSoap {
	public static java.lang.String getTypes() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = NotificationServiceUtil.getTypes();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getChannels() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = NotificationServiceUtil.getChannels();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getUserSettings(long userId)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = NotificationServiceUtil.getUserSettings(userId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getUserNotifications(long userId)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = NotificationServiceUtil.getUserNotifications(userId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Modification des abonnements et des canaux de communication d'un
	* utilisateur
	*/
	public static java.lang.String setUserSettings(long userId,
		java.lang.String typeIds, java.lang.String channelIds)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = NotificationServiceUtil.setUserSettings(userId,
					typeIds, channelIds);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String addNotification(long userId,
		java.lang.String title, java.lang.String description,
		java.lang.String url, java.lang.String startDate,
		java.lang.String endDate, long typeId) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = NotificationServiceUtil.addNotification(userId,
					title, description, url, startDate, endDate, typeId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(NotificationServiceSoap.class);
}