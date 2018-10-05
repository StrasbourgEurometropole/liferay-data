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

package eu.strasbourg.service.project.service.impl;

import eu.strasbourg.service.agenda.model.EventParticipation;
import eu.strasbourg.service.project.model.ProjectFollowed;
import eu.strasbourg.service.project.service.base.ProjectFollowedLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the project followed local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.ProjectFollowedLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see ProjectFollowedLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.ProjectFollowedLocalServiceUtil
 */
public class ProjectFollowedLocalServiceImpl
	extends ProjectFollowedLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.ProjectFollowedLocalServiceUtil} to access the project followed local service.
	 */

    /**
     * Cree un nouveau follower a un projet
     */
    @Override
    public ProjectFollowed createProjectFollowed() {
        long pk = this.counterLocalService.increment();
        return this.projectFollowedLocalService.createProjectFollowed(pk);
    }

	/**
	 * Retourne la liste des likes/dislikes d'un evenement
	 */
	@Override
	public List<ProjectFollowed> getByProjectId(long projectId) {
		return this.projectFollowedPersistence.findByProjectId(projectId);
	}
	
	/**
	 * Retourne le suivi d'un utilisateur et d'un projet donné
	 */
	@Override
	public ProjectFollowed getByPublikUserIdAndProjectId(String publikUserId, long projectId) {
		return this.projectFollowedPersistence.fetchByPublikUserIdAndProjectId(publikUserId, projectId);
	}
}