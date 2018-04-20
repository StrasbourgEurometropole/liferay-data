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
 * Provides a wrapper for {@link ProjectTimelineLocalService}.
 *
 * @author Cedric Henry
 * @see ProjectTimelineLocalService
 * @generated
 */
@ProviderType
public class ProjectTimelineLocalServiceWrapper
	implements ProjectTimelineLocalService,
		ServiceWrapper<ProjectTimelineLocalService> {
	public ProjectTimelineLocalServiceWrapper(
		ProjectTimelineLocalService projectTimelineLocalService) {
		_projectTimelineLocalService = projectTimelineLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _projectTimelineLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _projectTimelineLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _projectTimelineLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectTimelineLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectTimelineLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the project timeline to the database. Also notifies the appropriate model listeners.
	*
	* @param projectTimeline the project timeline
	* @return the project timeline that was added
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectTimeline addProjectTimeline(
		eu.strasbourg.service.project.model.ProjectTimeline projectTimeline) {
		return _projectTimelineLocalService.addProjectTimeline(projectTimeline);
	}

	/**
	* Creates a new project timeline with the primary key. Does not add the project timeline to the database.
	*
	* @param projectTimelineId the primary key for the new project timeline
	* @return the new project timeline
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectTimeline createProjectTimeline(
		long projectTimelineId) {
		return _projectTimelineLocalService.createProjectTimeline(projectTimelineId);
	}

	/**
	* Deletes the project timeline from the database. Also notifies the appropriate model listeners.
	*
	* @param projectTimeline the project timeline
	* @return the project timeline that was removed
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectTimeline deleteProjectTimeline(
		eu.strasbourg.service.project.model.ProjectTimeline projectTimeline) {
		return _projectTimelineLocalService.deleteProjectTimeline(projectTimeline);
	}

	/**
	* Deletes the project timeline with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectTimelineId the primary key of the project timeline
	* @return the project timeline that was removed
	* @throws PortalException if a project timeline with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectTimeline deleteProjectTimeline(
		long projectTimelineId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectTimelineLocalService.deleteProjectTimeline(projectTimelineId);
	}

	@Override
	public eu.strasbourg.service.project.model.ProjectTimeline fetchProjectTimeline(
		long projectTimelineId) {
		return _projectTimelineLocalService.fetchProjectTimeline(projectTimelineId);
	}

	/**
	* Returns the project timeline with the primary key.
	*
	* @param projectTimelineId the primary key of the project timeline
	* @return the project timeline
	* @throws PortalException if a project timeline with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectTimeline getProjectTimeline(
		long projectTimelineId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectTimelineLocalService.getProjectTimeline(projectTimelineId);
	}

	/**
	* Updates the project timeline in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectTimeline the project timeline
	* @return the project timeline that was updated
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectTimeline updateProjectTimeline(
		eu.strasbourg.service.project.model.ProjectTimeline projectTimeline) {
		return _projectTimelineLocalService.updateProjectTimeline(projectTimeline);
	}

	/**
	* Returns the number of project timelines.
	*
	* @return the number of project timelines
	*/
	@Override
	public int getProjectTimelinesCount() {
		return _projectTimelineLocalService.getProjectTimelinesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _projectTimelineLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _projectTimelineLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.ProjectTimelineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _projectTimelineLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.ProjectTimelineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _projectTimelineLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the project timelines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.ProjectTimelineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of project timelines
	* @param end the upper bound of the range of project timelines (not inclusive)
	* @return the range of project timelines
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.ProjectTimeline> getProjectTimelines(
		int start, int end) {
		return _projectTimelineLocalService.getProjectTimelines(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _projectTimelineLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _projectTimelineLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public ProjectTimelineLocalService getWrappedService() {
		return _projectTimelineLocalService;
	}

	@Override
	public void setWrappedService(
		ProjectTimelineLocalService projectTimelineLocalService) {
		_projectTimelineLocalService = projectTimelineLocalService;
	}

	private ProjectTimelineLocalService _projectTimelineLocalService;
}