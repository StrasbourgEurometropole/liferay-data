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
 * Provides the local service utility for ProjectFollowed. This utility wraps
 * {@link eu.strasbourg.service.project.service.impl.ProjectFollowedLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see ProjectFollowedLocalService
 * @see eu.strasbourg.service.project.service.base.ProjectFollowedLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.impl.ProjectFollowedLocalServiceImpl
 * @generated
 */
@ProviderType
public class ProjectFollowedLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.project.service.impl.ProjectFollowedLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the project followed to the database. Also notifies the appropriate model listeners.
	*
	* @param projectFollowed the project followed
	* @return the project followed that was added
	*/
	public static eu.strasbourg.service.project.model.ProjectFollowed addProjectFollowed(
		eu.strasbourg.service.project.model.ProjectFollowed projectFollowed) {
		return getService().addProjectFollowed(projectFollowed);
	}

	/**
	* Cree un nouveau follower a un projet
	*/
	public static eu.strasbourg.service.project.model.ProjectFollowed createProjectFollowed() {
		return getService().createProjectFollowed();
	}

	/**
	* Creates a new project followed with the primary key. Does not add the project followed to the database.
	*
	* @param projectFollowedId the primary key for the new project followed
	* @return the new project followed
	*/
	public static eu.strasbourg.service.project.model.ProjectFollowed createProjectFollowed(
		long projectFollowedId) {
		return getService().createProjectFollowed(projectFollowedId);
	}

	/**
	* Deletes the project followed from the database. Also notifies the appropriate model listeners.
	*
	* @param projectFollowed the project followed
	* @return the project followed that was removed
	*/
	public static eu.strasbourg.service.project.model.ProjectFollowed deleteProjectFollowed(
		eu.strasbourg.service.project.model.ProjectFollowed projectFollowed) {
		return getService().deleteProjectFollowed(projectFollowed);
	}

	/**
	* Deletes the project followed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectFollowedId the primary key of the project followed
	* @return the project followed that was removed
	* @throws PortalException if a project followed with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.ProjectFollowed deleteProjectFollowed(
		long projectFollowedId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteProjectFollowed(projectFollowedId);
	}

	public static eu.strasbourg.service.project.model.ProjectFollowed fetchProjectFollowed(
		long projectFollowedId) {
		return getService().fetchProjectFollowed(projectFollowedId);
	}

	/**
	* Retourne le suivi d'un utilisateur et d'un projet donné
	*/
	public static eu.strasbourg.service.project.model.ProjectFollowed getByPublikUserIdAndProjectId(
		java.lang.String publikUserId, long projectId) {
		return getService()
				   .getByPublikUserIdAndProjectId(publikUserId, projectId);
	}

	/**
	* Returns the project followed with the primary key.
	*
	* @param projectFollowedId the primary key of the project followed
	* @return the project followed
	* @throws PortalException if a project followed with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.ProjectFollowed getProjectFollowed(
		long projectFollowedId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProjectFollowed(projectFollowedId);
	}

	/**
	* Updates the project followed in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectFollowed the project followed
	* @return the project followed that was updated
	*/
	public static eu.strasbourg.service.project.model.ProjectFollowed updateProjectFollowed(
		eu.strasbourg.service.project.model.ProjectFollowed projectFollowed) {
		return getService().updateProjectFollowed(projectFollowed);
	}

	/**
	* Returns the number of project followeds.
	*
	* @return the number of project followeds
	*/
	public static int getProjectFollowedsCount() {
		return getService().getProjectFollowedsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.ProjectFollowedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.ProjectFollowedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Retourne la liste des likes/dislikes d'un evenement
	*/
	public static java.util.List<eu.strasbourg.service.project.model.ProjectFollowed> getByProjectId(
		long projectId) {
		return getService().getByProjectId(projectId);
	}

	/**
	* Retourne tous les projets suivis par un utilisateur
	*/
	public static java.util.List<eu.strasbourg.service.project.model.ProjectFollowed> getByPublikId(
		java.lang.String publikId) {
		return getService().getByPublikId(publikId);
	}

	/**
	* Returns a range of all the project followeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.ProjectFollowedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of project followeds
	* @param end the upper bound of the range of project followeds (not inclusive)
	* @return the range of project followeds
	*/
	public static java.util.List<eu.strasbourg.service.project.model.ProjectFollowed> getProjectFolloweds(
		int start, int end) {
		return getService().getProjectFolloweds(start, end);
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

	public static ProjectFollowedLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProjectFollowedLocalService, ProjectFollowedLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ProjectFollowedLocalService.class);
}