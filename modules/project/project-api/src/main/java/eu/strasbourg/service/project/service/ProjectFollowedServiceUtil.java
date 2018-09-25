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

package eu.strasbourg.service.project.service;

import aQute.bnd.annotation.ProviderType;
import com.liferay.osgi.util.ServiceTrackerFactory;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for ProjectFollowed. This utility wraps
 * {@link eu.strasbourg.service.project.service.impl.ProjectFollowedServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Cedric Henry
 * @see ProjectFollowedService
 * @see eu.strasbourg.service.project.service.base.ProjectFollowedServiceBaseImpl
 * @see eu.strasbourg.service.project.service.impl.ProjectFollowedServiceImpl
 * @generated
 */
@ProviderType
public class ProjectFollowedServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.project.service.impl.ProjectFollowedServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Ajoute un projet à un utilisateur
	*/
	public static com.liferay.portal.kernel.json.JSONObject addFollowerLink(
		long projectId, long groupId) {
		return getService().addFollowerLink(projectId, groupId);
	}

	/**
	* Verifie si l'utilisateur courant suit le projet
	*/
	public static com.liferay.portal.kernel.json.JSONObject isFollower(
		long projectId) {
		return getService().isFollower(projectId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<eu.strasbourg.service.project.model.ProjectFollowed> findProjectFollowedByPublikUserId(
		java.lang.String publikId) {
		return getService().findProjectFollowedByPublikUserId(publikId);
	}

	public static ProjectFollowedService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProjectFollowedService, ProjectFollowedService> _serviceTracker =
		ServiceTrackerFactory.open(ProjectFollowedService.class);
}