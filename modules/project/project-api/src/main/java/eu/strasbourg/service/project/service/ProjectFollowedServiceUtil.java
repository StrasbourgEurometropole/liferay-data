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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for ProjectFollowed. This utility wraps
 * <code>eu.strasbourg.service.project.service.impl.ProjectFollowedServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Cedric Henry
 * @see ProjectFollowedService
 * @generated
 */
public class ProjectFollowedServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.project.service.impl.ProjectFollowedServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Ajoute un projet à un utilisateur
	 */
	public static com.liferay.portal.kernel.json.JSONObject addFollowerLink(
		long projectId, long groupId) {

		return getService().addFollowerLink(projectId, groupId);
	}

	public static java.util.List
		<eu.strasbourg.service.project.model.ProjectFollowed>
			findProjectFollowedByPublikUserId(String publikId) {

		return getService().findProjectFollowedByPublikUserId(publikId);
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
	 * Verifie si l'utilisateur courant suit le projet
	 */
	public static com.liferay.portal.kernel.json.JSONObject isFollower(
		long projectId) {

		return getService().isFollower(projectId);
	}

	public static ProjectFollowedService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ProjectFollowedService, ProjectFollowedService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProjectFollowedService.class);

		ServiceTracker<ProjectFollowedService, ProjectFollowedService>
			serviceTracker =
				new ServiceTracker
					<ProjectFollowedService, ProjectFollowedService>(
						bundle.getBundleContext(), ProjectFollowedService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}