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

package eu.strasbourg.service.strasbourg.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import eu.strasbourg.service.strasbourg.service.StrasbourgServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link StrasbourgServiceUtil} service utility. The
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
 * @author Brian Wing Shun Chan
 * @see StrasbourgServiceSoap
 * @see HttpPrincipal
 * @see StrasbourgServiceUtil
 * @generated
 */
@ProviderType
public class StrasbourgServiceHttp {
	public static com.liferay.portal.kernel.json.JSONObject getCopyright(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String uuid,
		java.lang.String language) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getCopyright", _getCopyrightParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					uuid, language);

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

	public static com.liferay.portal.kernel.json.JSONObject getFileDetails(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String uuid,
		java.lang.String language) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getFileDetails", _getFileDetailsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					uuid, language);

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

	public static com.liferay.portal.kernel.json.JSONObject searchStreets(
		HttpPrincipal httpPrincipal, java.lang.String query) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"searchStreets", _searchStreetsParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, query);

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

	public static com.liferay.portal.kernel.json.JSONObject searchStreets(
		HttpPrincipal httpPrincipal, java.lang.String query,
		java.lang.String city) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"searchStreets", _searchStreetsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, query,
					city);

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

	public static java.lang.String getArticleHTMLContent(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getArticleHTMLContent",
					_getArticleHTMLContentParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getPois(
		HttpPrincipal httpPrincipal, java.lang.String interests) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getPois", _getPoisParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, interests);

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

	public static com.liferay.portal.kernel.json.JSONObject getFavoritesPois(
		HttpPrincipal httpPrincipal) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getFavoritesPois", _getFavoritesPoisParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey);

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

	public static com.liferay.portal.kernel.json.JSONArray getCoordinateForAddress(
		HttpPrincipal httpPrincipal, java.lang.String address) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getCoordinateForAddress",
					_getCoordinateForAddressParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, address);

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

	private static Log _log = LogFactoryUtil.getLog(StrasbourgServiceHttp.class);
	private static final Class<?>[] _getCopyrightParameterTypes2 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _getFileDetailsParameterTypes3 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _searchStreetsParameterTypes4 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _searchStreetsParameterTypes5 = new Class[] {
			java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _getArticleHTMLContentParameterTypes6 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getPoisParameterTypes7 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _getFavoritesPoisParameterTypes8 = new Class[] {
			
		};
	private static final Class<?>[] _getCoordinateForAddressParameterTypes9 = new Class[] {
			java.lang.String.class
		};
}