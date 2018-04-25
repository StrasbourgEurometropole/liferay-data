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

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.project.model.ProjectTimeline;
import eu.strasbourg.service.project.service.base.ProjectTimelineLocalServiceBaseImpl;

/**
 * The implementation of the project timeline local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.ProjectTimelineLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see ProjectTimelineLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.ProjectTimelineLocalServiceUtil
 */
public class ProjectTimelineLocalServiceImpl
	extends ProjectTimelineLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.ProjectTimelineLocalServiceUtil} to access the project timeline local service.
	 */
	
	/**
	 * Crée une une entrée de timeline vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public ProjectTimeline createProjectTimeline() throws PortalException {
		long pk = counterLocalService.increment();

		ProjectTimeline eventPeriod = this.projectTimelineLocalService
			.createProjectTimeline(pk);

		return eventPeriod;
	}

	/**
	 * Retourne les périodes d'un événement
	 */
	@Override
	public List<ProjectTimeline> getByProjectId(long projectIdId) {
		return this.projectTimelinePersistence.findByProjectId(projectIdId, -1, -1, null, false);
	}
	
}