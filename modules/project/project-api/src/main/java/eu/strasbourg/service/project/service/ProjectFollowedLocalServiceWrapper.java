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
 * Provides a wrapper for {@link ProjectFollowedLocalService}.
 *
 * @author Cedric Henry
 * @see ProjectFollowedLocalService
 * @generated
 */
@ProviderType
public class ProjectFollowedLocalServiceWrapper
	implements ProjectFollowedLocalService,
		ServiceWrapper<ProjectFollowedLocalService> {
	public ProjectFollowedLocalServiceWrapper(
		ProjectFollowedLocalService projectFollowedLocalService) {
		_projectFollowedLocalService = projectFollowedLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _projectFollowedLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _projectFollowedLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _projectFollowedLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectFollowedLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectFollowedLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the project followed to the database. Also notifies the appropriate model listeners.
	*
	* @param projectFollowed the project followed
	* @return the project followed that was added
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectFollowed addProjectFollowed(
		eu.strasbourg.service.project.model.ProjectFollowed projectFollowed) {
		return _projectFollowedLocalService.addProjectFollowed(projectFollowed);
	}

	/**
	* Cree un nouveau follower a un projet
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectFollowed createProjectFollowed() {
		return _projectFollowedLocalService.createProjectFollowed();
	}

	/**
	* Creates a new project followed with the primary key. Does not add the project followed to the database.
	*
	* @param projectFollowedId the primary key for the new project followed
	* @return the new project followed
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectFollowed createProjectFollowed(
		long projectFollowedId) {
		return _projectFollowedLocalService.createProjectFollowed(projectFollowedId);
	}

	/**
	* Deletes the project followed from the database. Also notifies the appropriate model listeners.
	*
	* @param projectFollowed the project followed
	* @return the project followed that was removed
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectFollowed deleteProjectFollowed(
		eu.strasbourg.service.project.model.ProjectFollowed projectFollowed) {
		return _projectFollowedLocalService.deleteProjectFollowed(projectFollowed);
	}

	/**
	* Deletes the project followed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectFollowedId the primary key of the project followed
	* @return the project followed that was removed
	* @throws PortalException if a project followed with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectFollowed deleteProjectFollowed(
		long projectFollowedId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectFollowedLocalService.deleteProjectFollowed(projectFollowedId);
	}

	@Override
	public eu.strasbourg.service.project.model.ProjectFollowed fetchProjectFollowed(
		long projectFollowedId) {
		return _projectFollowedLocalService.fetchProjectFollowed(projectFollowedId);
	}

	/**
	* Retourne le suivi d'un utilisateur et d'un projet donné
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectFollowed getByPublikUserIdAndProjectId(
		java.lang.String publikUserId, long projectId) {
		return _projectFollowedLocalService.getByPublikUserIdAndProjectId(publikUserId,
			projectId);
	}

	/**
	* Returns the project followed with the primary key.
	*
	* @param projectFollowedId the primary key of the project followed
	* @return the project followed
	* @throws PortalException if a project followed with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectFollowed getProjectFollowed(
		long projectFollowedId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectFollowedLocalService.getProjectFollowed(projectFollowedId);
	}

	/**
	* Updates the project followed in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectFollowed the project followed
	* @return the project followed that was updated
	*/
	@Override
	public eu.strasbourg.service.project.model.ProjectFollowed updateProjectFollowed(
		eu.strasbourg.service.project.model.ProjectFollowed projectFollowed) {
		return _projectFollowedLocalService.updateProjectFollowed(projectFollowed);
	}

	/**
	* Returns the number of project followeds.
	*
	* @return the number of project followeds
	*/
	@Override
	public int getProjectFollowedsCount() {
		return _projectFollowedLocalService.getProjectFollowedsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _projectFollowedLocalService.getOSGiServiceIdentifier();
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
		return _projectFollowedLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _projectFollowedLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _projectFollowedLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Retourne la liste des likes/dislikes d'un evenement
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.ProjectFollowed> getByProjectId(
		long projectId) {
		return _projectFollowedLocalService.getByProjectId(projectId);
	}

	/**
	* Retourne tous les projets suivis par un utilisateur
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.ProjectFollowed> getByPublikId(
		java.lang.String publikId) {
		return _projectFollowedLocalService.getByPublikId(publikId);
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
	@Override
	public java.util.List<eu.strasbourg.service.project.model.ProjectFollowed> getProjectFolloweds(
		int start, int end) {
		return _projectFollowedLocalService.getProjectFolloweds(start, end);
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
		return _projectFollowedLocalService.dynamicQueryCount(dynamicQuery);
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
		return _projectFollowedLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public ProjectFollowedLocalService getWrappedService() {
		return _projectFollowedLocalService;
	}

	@Override
	public void setWrappedService(
		ProjectFollowedLocalService projectFollowedLocalService) {
		_projectFollowedLocalService = projectFollowedLocalService;
	}

	private ProjectFollowedLocalService _projectFollowedLocalService;
}