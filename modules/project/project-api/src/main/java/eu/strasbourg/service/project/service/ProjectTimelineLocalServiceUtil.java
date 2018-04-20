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
 * Provides the local service utility for ProjectTimeline. This utility wraps
 * {@link eu.strasbourg.service.project.service.impl.ProjectTimelineLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see ProjectTimelineLocalService
 * @see eu.strasbourg.service.project.service.base.ProjectTimelineLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.impl.ProjectTimelineLocalServiceImpl
 * @generated
 */
@ProviderType
public class ProjectTimelineLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.project.service.impl.ProjectTimelineLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the project timeline to the database. Also notifies the appropriate model listeners.
	*
	* @param projectTimeline the project timeline
	* @return the project timeline that was added
	*/
	public static eu.strasbourg.service.project.model.ProjectTimeline addProjectTimeline(
		eu.strasbourg.service.project.model.ProjectTimeline projectTimeline) {
		return getService().addProjectTimeline(projectTimeline);
	}

	/**
	* Creates a new project timeline with the primary key. Does not add the project timeline to the database.
	*
	* @param projectTimelineId the primary key for the new project timeline
	* @return the new project timeline
	*/
	public static eu.strasbourg.service.project.model.ProjectTimeline createProjectTimeline(
		long projectTimelineId) {
		return getService().createProjectTimeline(projectTimelineId);
	}

	/**
	* Deletes the project timeline from the database. Also notifies the appropriate model listeners.
	*
	* @param projectTimeline the project timeline
	* @return the project timeline that was removed
	*/
	public static eu.strasbourg.service.project.model.ProjectTimeline deleteProjectTimeline(
		eu.strasbourg.service.project.model.ProjectTimeline projectTimeline) {
		return getService().deleteProjectTimeline(projectTimeline);
	}

	/**
	* Deletes the project timeline with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectTimelineId the primary key of the project timeline
	* @return the project timeline that was removed
	* @throws PortalException if a project timeline with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.ProjectTimeline deleteProjectTimeline(
		long projectTimelineId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteProjectTimeline(projectTimelineId);
	}

	public static eu.strasbourg.service.project.model.ProjectTimeline fetchProjectTimeline(
		long projectTimelineId) {
		return getService().fetchProjectTimeline(projectTimelineId);
	}

	/**
	* Returns the project timeline with the primary key.
	*
	* @param projectTimelineId the primary key of the project timeline
	* @return the project timeline
	* @throws PortalException if a project timeline with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.ProjectTimeline getProjectTimeline(
		long projectTimelineId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProjectTimeline(projectTimelineId);
	}

	/**
	* Updates the project timeline in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectTimeline the project timeline
	* @return the project timeline that was updated
	*/
	public static eu.strasbourg.service.project.model.ProjectTimeline updateProjectTimeline(
		eu.strasbourg.service.project.model.ProjectTimeline projectTimeline) {
		return getService().updateProjectTimeline(projectTimeline);
	}

	/**
	* Returns the number of project timelines.
	*
	* @return the number of project timelines
	*/
	public static int getProjectTimelinesCount() {
		return getService().getProjectTimelinesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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
	public static java.util.List<eu.strasbourg.service.project.model.ProjectTimeline> getProjectTimelines(
		int start, int end) {
		return getService().getProjectTimelines(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static ProjectTimelineLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProjectTimelineLocalService, ProjectTimelineLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ProjectTimelineLocalService.class);
}