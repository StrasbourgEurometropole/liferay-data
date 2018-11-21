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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import eu.strasbourg.service.project.service.ProjectFollowedServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link ProjectFollowedServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link eu.strasbourg.service.project.model.ProjectFollowedSoap}.
 * If the method in the service utility returns a
 * {@link eu.strasbourg.service.project.model.ProjectFollowed}, that is translated to a
 * {@link eu.strasbourg.service.project.model.ProjectFollowedSoap}. Methods that SOAP cannot
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
 * @author Cedric Henry
 * @see ProjectFollowedServiceHttp
 * @see eu.strasbourg.service.project.model.ProjectFollowedSoap
 * @see ProjectFollowedServiceUtil
 * @generated
 */
@ProviderType
public class ProjectFollowedServiceSoap {
	/**
	* Ajoute un projet à un utilisateur
	*/
	public static java.lang.String addFollowerLink(long projectId, long groupId)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = ProjectFollowedServiceUtil.addFollowerLink(projectId,
					groupId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Verifie si l'utilisateur courant suit le projet
	*/
	public static java.lang.String isFollower(long projectId)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = ProjectFollowedServiceUtil.isFollower(projectId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static eu.strasbourg.service.project.model.ProjectFollowedSoap[] findProjectFollowedByPublikUserId(
		java.lang.String publikId) throws RemoteException {
		try {
			java.util.List<eu.strasbourg.service.project.model.ProjectFollowed> returnValue =
				ProjectFollowedServiceUtil.findProjectFollowedByPublikUserId(publikId);

			return eu.strasbourg.service.project.model.ProjectFollowedSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ProjectFollowedServiceSoap.class);
}