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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import eu.strasbourg.service.notification.service.NotificationServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link NotificationServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author BenjaminBini
 * @see NotificationServiceSoap
 * @see HttpPrincipal
 * @see NotificationServiceUtil
 * @generated
 */
@ProviderType
public class NotificationServiceHttp {
	public static com.liferay.portal.kernel.json.JSONArray getTypes(
		HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(NotificationServiceUtil.class,
					"getTypes", _getTypesParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray getChannels(
		HttpPrincipal httpPrincipal) {
		try {
			MethodKey methodKey = new MethodKey(NotificationServiceUtil.class,
					"getChannels", _getChannelsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getUserSettings(
		HttpPrincipal httpPrincipal, java.lang.String userId) {
		try {
			MethodKey methodKey = new MethodKey(NotificationServiceUtil.class,
					"getUserSettings", _getUserSettingsParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getUserNotifications(
		HttpPrincipal httpPrincipal, java.lang.String userId) {
		try {
			MethodKey methodKey = new MethodKey(NotificationServiceUtil.class,
					"getUserNotifications", _getUserNotificationsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject setUserSettings(
		HttpPrincipal httpPrincipal, java.lang.String userId,
		java.lang.String typeIds, java.lang.String channelIds) {
		try {
			MethodKey methodKey = new MethodKey(NotificationServiceUtil.class,
					"setUserSettings", _setUserSettingsParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					typeIds, channelIds);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject addNotification(
		HttpPrincipal httpPrincipal, java.lang.String userId, boolean isGlobal,
		java.lang.String title, java.lang.String description,
		java.lang.String url, java.lang.String publicationDate,
		java.lang.String expirationDate, java.lang.String typeId) {
		try {
			MethodKey methodKey = new MethodKey(NotificationServiceUtil.class,
					"addNotification", _addNotificationParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					isGlobal, title, description, url, publicationDate,
					expirationDate, typeId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(NotificationServiceHttp.class);
	private static final Class<?>[] _getTypesParameterTypes0 = new Class[] {  };
	private static final Class<?>[] _getChannelsParameterTypes1 = new Class[] {  };
	private static final Class<?>[] _getUserSettingsParameterTypes2 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _getUserNotificationsParameterTypes3 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _setUserSettingsParameterTypes4 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class
		};
	private static final Class<?>[] _addNotificationParameterTypes5 = new Class[] {
			java.lang.String.class, boolean.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class
		};
}