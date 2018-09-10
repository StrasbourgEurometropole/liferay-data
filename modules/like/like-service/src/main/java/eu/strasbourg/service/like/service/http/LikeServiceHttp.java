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

package eu.strasbourg.service.like.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import eu.strasbourg.service.like.service.LikeServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link LikeServiceUtil} service utility. The
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
 * @author Cedric Henry
 * @see LikeServiceSoap
 * @see HttpPrincipal
 * @see LikeServiceUtil
 * @generated
 */
@ProviderType
public class LikeServiceHttp {
	public static com.liferay.portal.kernel.json.JSONObject getTypes(
		HttpPrincipal httpPrincipal) {
		try {
			MethodKey methodKey = new MethodKey(LikeServiceUtil.class,
					"getTypes", _getTypesParameterTypes0);

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

	public static com.liferay.portal.kernel.json.JSONObject getUserLikes(
		HttpPrincipal httpPrincipal, java.lang.String userId) {
		try {
			MethodKey methodKey = new MethodKey(LikeServiceUtil.class,
					"getUserLikes", _getUserLikesParameterTypes1);

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

	public static com.liferay.portal.kernel.json.JSONObject addLike(
		HttpPrincipal httpPrincipal, java.lang.String userId,
		java.lang.String title, boolean isDislike, long typeId, long entityId) {
		try {
			MethodKey methodKey = new MethodKey(LikeServiceUtil.class,
					"addLike", _addLikeParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					title, isDislike, typeId, entityId);

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

	public static com.liferay.portal.kernel.json.JSONObject addLikeLink(
		HttpPrincipal httpPrincipal, java.lang.String title, boolean isDislike,
		long typeId, long entityId, long entityGroupId) {
		try {
			MethodKey methodKey = new MethodKey(LikeServiceUtil.class,
					"addLikeLink", _addLikeLinkParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, title,
					isDislike, typeId, entityId, entityGroupId);

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

	public static com.liferay.portal.kernel.json.JSONObject deleteLike(
		HttpPrincipal httpPrincipal, java.lang.String userId, long likeId) {
		try {
			MethodKey methodKey = new MethodKey(LikeServiceUtil.class,
					"deleteLike", _deleteLikeParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					likeId);

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

	public static com.liferay.portal.kernel.json.JSONObject deleteLikeLink(
		HttpPrincipal httpPrincipal, java.lang.String title,
		java.lang.String url, boolean isDislike, long typeId, long entityId) {
		try {
			MethodKey methodKey = new MethodKey(LikeServiceUtil.class,
					"deleteLikeLink", _deleteLikeLinkParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, title,
					url, isDislike, typeId, entityId);

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

	private static Log _log = LogFactoryUtil.getLog(LikeServiceHttp.class);
	private static final Class<?>[] _getTypesParameterTypes0 = new Class[] {  };
	private static final Class<?>[] _getUserLikesParameterTypes1 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _addLikeParameterTypes2 = new Class[] {
			java.lang.String.class, java.lang.String.class, boolean.class,
			long.class, long.class
		};
	private static final Class<?>[] _addLikeLinkParameterTypes3 = new Class[] {
			java.lang.String.class, boolean.class, long.class, long.class,
			long.class
		};
	private static final Class<?>[] _deleteLikeParameterTypes4 = new Class[] {
			java.lang.String.class, long.class
		};
	private static final Class<?>[] _deleteLikeLinkParameterTypes5 = new Class[] {
			java.lang.String.class, java.lang.String.class, boolean.class,
			long.class, long.class
		};
}