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

package eu.strasbourg.service.project.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import eu.strasbourg.service.project.service.ProjectFollowedServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>ProjectFollowedServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
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
 * @see ProjectFollowedServiceSoap
 * @generated
 */
public class ProjectFollowedServiceHttp {

	public static com.liferay.portal.kernel.json.JSONObject addFollowerLink(
		HttpPrincipal httpPrincipal, long projectId, long groupId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProjectFollowedServiceUtil.class, "addFollowerLink",
				_addFollowerLinkParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, projectId, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject isFollower(
		HttpPrincipal httpPrincipal, long projectId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProjectFollowedServiceUtil.class, "isFollower",
				_isFollowerParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, projectId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<eu.strasbourg.service.project.model.ProjectFollowed>
			findProjectFollowedByPublikUserId(
				HttpPrincipal httpPrincipal, String publikId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProjectFollowedServiceUtil.class,
				"findProjectFollowedByPublikUserId",
				_findProjectFollowedByPublikUserIdParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, publikId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<eu.strasbourg.service.project.model.ProjectFollowed>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ProjectFollowedServiceHttp.class);

	private static final Class<?>[] _addFollowerLinkParameterTypes0 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _isFollowerParameterTypes1 = new Class[] {
		long.class
	};
	private static final Class<?>[]
		_findProjectFollowedByPublikUserIdParameterTypes2 = new Class[] {
			String.class
		};

}