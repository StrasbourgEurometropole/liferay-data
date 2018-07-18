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

package eu.strasbourg.service.agenda.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import eu.strasbourg.service.agenda.service.EventParticipationServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link EventParticipationServiceUtil} service utility. The
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
 * @see EventParticipationServiceSoap
 * @see HttpPrincipal
 * @see EventParticipationServiceUtil
 * @generated
 */
@ProviderType
public class EventParticipationServiceHttp {
	public static com.liferay.portal.kernel.json.JSONObject getUserEventParticipations(
		HttpPrincipal httpPrincipal, java.lang.String userId) {
		try {
			MethodKey methodKey = new MethodKey(EventParticipationServiceUtil.class,
					"getUserEventParticipations",
					_getUserEventParticipationsParameterTypes0);

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

	public static com.liferay.portal.kernel.json.JSONObject addEventParticipationLink(
		HttpPrincipal httpPrincipal, long eventId, long groupId) {
		try {
			MethodKey methodKey = new MethodKey(EventParticipationServiceUtil.class,
					"addEventParticipationLink",
					_addEventParticipationLinkParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, eventId,
					groupId);

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

	public static com.liferay.portal.kernel.json.JSONObject deleteEventParticipationLink(
		HttpPrincipal httpPrincipal, long eventId) {
		try {
			MethodKey methodKey = new MethodKey(EventParticipationServiceUtil.class,
					"deleteEventParticipationLink",
					_deleteEventParticipationLinkParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, eventId);

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

	public static com.liferay.portal.kernel.json.JSONObject isUserParticipates(
		HttpPrincipal httpPrincipal, long eventId) {
		try {
			MethodKey methodKey = new MethodKey(EventParticipationServiceUtil.class,
					"isUserParticipates", _isUserParticipatesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, eventId);

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

	private static Log _log = LogFactoryUtil.getLog(EventParticipationServiceHttp.class);
	private static final Class<?>[] _getUserEventParticipationsParameterTypes0 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _addEventParticipationLinkParameterTypes1 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _deleteEventParticipationLinkParameterTypes2 =
		new Class[] { long.class };
	private static final Class<?>[] _isUserParticipatesParameterTypes3 = new Class[] {
			long.class
		};
}