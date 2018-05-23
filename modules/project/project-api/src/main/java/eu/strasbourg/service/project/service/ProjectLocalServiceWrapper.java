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
 * Provides a wrapper for {@link ProjectLocalService}.
 *
 * @author Cedric Henry
 * @see ProjectLocalService
 * @generated
 */
@ProviderType
public class ProjectLocalServiceWrapper implements ProjectLocalService,
	ServiceWrapper<ProjectLocalService> {
	public ProjectLocalServiceWrapper(ProjectLocalService projectLocalService) {
		_projectLocalService = projectLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _projectLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _projectLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _projectLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _projectLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the project to the database. Also notifies the appropriate model listeners.
	*
	* @param project the project
	* @return the project that was added
	*/
	@Override
	public eu.strasbourg.service.project.model.Project addProject(
		eu.strasbourg.service.project.model.Project project) {
		return _projectLocalService.addProject(project);
	}

	/**
	* Crée un projet vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.project.model.Project createProject(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectLocalService.createProject(sc);
	}

	/**
	* Creates a new project with the primary key. Does not add the project to the database.
	*
	* @param projectId the primary key for the new project
	* @return the new project
	*/
	@Override
	public eu.strasbourg.service.project.model.Project createProject(
		long projectId) {
		return _projectLocalService.createProject(projectId);
	}

	/**
	* Deletes the project from the database. Also notifies the appropriate model listeners.
	*
	* @param project the project
	* @return the project that was removed
	*/
	@Override
	public eu.strasbourg.service.project.model.Project deleteProject(
		eu.strasbourg.service.project.model.Project project) {
		return _projectLocalService.deleteProject(project);
	}

	/**
	* Deletes the project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectId the primary key of the project
	* @return the project that was removed
	* @throws PortalException if a project with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Project deleteProject(
		long projectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectLocalService.deleteProject(projectId);
	}

	@Override
	public eu.strasbourg.service.project.model.Project fetchProject(
		long projectId) {
		return _projectLocalService.fetchProject(projectId);
	}

	/**
	* Returns the project matching the UUID and group.
	*
	* @param uuid the project's UUID
	* @param groupId the primary key of the group
	* @return the matching project, or <code>null</code> if a matching project could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Project fetchProjectByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _projectLocalService.fetchProjectByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the project with the primary key.
	*
	* @param projectId the primary key of the project
	* @return the project
	* @throws PortalException if a project with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Project getProject(
		long projectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectLocalService.getProject(projectId);
	}

	/**
	* Returns the project matching the UUID and group.
	*
	* @param uuid the project's UUID
	* @param groupId the primary key of the group
	* @return the matching project
	* @throws PortalException if a matching project could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Project getProjectByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectLocalService.getProjectByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime un projet
	*/
	@Override
	public eu.strasbourg.service.project.model.Project removeProject(
		long projectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectLocalService.removeProject(projectId);
	}

	/**
	* Updates the project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param project the project
	* @return the project that was updated
	*/
	@Override
	public eu.strasbourg.service.project.model.Project updateProject(
		eu.strasbourg.service.project.model.Project project) {
		return _projectLocalService.updateProject(project);
	}

	/**
	* Met à jour un projet et l'enregistre en base de données
	*
	* @throws IOException
	*/
	@Override
	public eu.strasbourg.service.project.model.Project updateProject(
		eu.strasbourg.service.project.model.Project project,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			java.io.IOException {
		return _projectLocalService.updateProject(project, sc);
	}

	/**
	* Met à jour le statut du projet par le framework workflow
	*/
	@Override
	public eu.strasbourg.service.project.model.Project updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectLocalService.updateStatus(userId, entryId, status, sc,
			workflowContext);
	}

	/**
	* Returns the number of projects.
	*
	* @return the number of projects
	*/
	@Override
	public int getProjectsCount() {
		return _projectLocalService.getProjectsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _projectLocalService.getOSGiServiceIdentifier();
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
		return _projectLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Recherche par mot clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Project> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _projectLocalService.findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à un projet
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _projectLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne tous les projets d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Project> getByGroupId(
		long groupId) {
		return _projectLocalService.getByGroupId(groupId);
	}

	/**
	* Returns a range of all the projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @return the range of projects
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Project> getProjects(
		int start, int end) {
		return _projectLocalService.getProjects(start, end);
	}

	/**
	* Returns all the projects matching the UUID and company.
	*
	* @param uuid the UUID of the projects
	* @param companyId the primary key of the company
	* @return the matching projects, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Project> getProjectsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _projectLocalService.getProjectsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of projects matching the UUID and company.
	*
	* @param uuid the UUID of the projects
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching projects, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Project> getProjectsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.project.model.Project> orderByComparator) {
		return _projectLocalService.getProjectsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
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
		return _projectLocalService.dynamicQueryCount(dynamicQuery);
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
		return _projectLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Recherche par mot clés (compte)
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _projectLocalService.findByKeywordCount(keyword, groupId);
	}

	/**
	* Met à jour le statut du projet "manuellement" (pas via le workflow)
	*/
	@Override
	public void updateStatus(
		eu.strasbourg.service.project.model.Project project, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		_projectLocalService.updateStatus(project, status);
	}

	@Override
	public ProjectLocalService getWrappedService() {
		return _projectLocalService;
	}

	@Override
	public void setWrappedService(ProjectLocalService projectLocalService) {
		_projectLocalService = projectLocalService;
	}

	private ProjectLocalService _projectLocalService;
}