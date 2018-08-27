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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProjectFollowedService}.
 *
 * @author Cedric Henry
 * @see ProjectFollowedService
 * @generated
 */
@ProviderType
public class ProjectFollowedServiceWrapper implements ProjectFollowedService,
	ServiceWrapper<ProjectFollowedService> {
	public ProjectFollowedServiceWrapper(
		ProjectFollowedService projectFollowedService) {
		_projectFollowedService = projectFollowedService;
	}

	/**
	* Ajoute un projet à un utilisateur
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject addFollowerLink(
		long projectId, long groupId) {
		return _projectFollowedService.addFollowerLink(projectId, groupId);
	}

	/**
	* Verifie si l'utilisateur courant suit le projet
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject isFollower(long projectId) {
		return _projectFollowedService.isFollower(projectId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _projectFollowedService.getOSGiServiceIdentifier();
	}

	@Override
	public ProjectFollowedService getWrappedService() {
		return _projectFollowedService;
	}

	@Override
	public void setWrappedService(ProjectFollowedService projectFollowedService) {
		_projectFollowedService = projectFollowedService;
	}

	private ProjectFollowedService _projectFollowedService;
}