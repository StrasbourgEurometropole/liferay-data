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
 * Provides a wrapper for {@link InitiativeLocalService}.
 *
 * @author Cedric Henry
 * @see InitiativeLocalService
 * @generated
 */
@ProviderType
public class InitiativeLocalServiceWrapper implements InitiativeLocalService,
	ServiceWrapper<InitiativeLocalService> {
	public InitiativeLocalServiceWrapper(
		InitiativeLocalService initiativeLocalService) {
		_initiativeLocalService = initiativeLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _initiativeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _initiativeLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _initiativeLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _initiativeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _initiativeLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _initiativeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the initiative to the database. Also notifies the appropriate model listeners.
	*
	* @param initiative the initiative
	* @return the initiative that was added
	*/
	@Override
	public eu.strasbourg.service.project.model.Initiative addInitiative(
		eu.strasbourg.service.project.model.Initiative initiative) {
		return _initiativeLocalService.addInitiative(initiative);
	}

	/**
	* Crée une initiative vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.project.model.Initiative createInitiative(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _initiativeLocalService.createInitiative(sc);
	}

	/**
	* Creates a new initiative with the primary key. Does not add the initiative to the database.
	*
	* @param initiativeId the primary key for the new initiative
	* @return the new initiative
	*/
	@Override
	public eu.strasbourg.service.project.model.Initiative createInitiative(
		long initiativeId) {
		return _initiativeLocalService.createInitiative(initiativeId);
	}

	/**
	* Deletes the initiative from the database. Also notifies the appropriate model listeners.
	*
	* @param initiative the initiative
	* @return the initiative that was removed
	*/
	@Override
	public eu.strasbourg.service.project.model.Initiative deleteInitiative(
		eu.strasbourg.service.project.model.Initiative initiative) {
		return _initiativeLocalService.deleteInitiative(initiative);
	}

	/**
	* Deletes the initiative with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param initiativeId the primary key of the initiative
	* @return the initiative that was removed
	* @throws PortalException if a initiative with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Initiative deleteInitiative(
		long initiativeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _initiativeLocalService.deleteInitiative(initiativeId);
	}

	@Override
	public eu.strasbourg.service.project.model.Initiative fetchInitiative(
		long initiativeId) {
		return _initiativeLocalService.fetchInitiative(initiativeId);
	}

	/**
	* Returns the initiative matching the UUID and group.
	*
	* @param uuid the initiative's UUID
	* @param groupId the primary key of the group
	* @return the matching initiative, or <code>null</code> if a matching initiative could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Initiative fetchInitiativeByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _initiativeLocalService.fetchInitiativeByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the initiative with the primary key.
	*
	* @param initiativeId the primary key of the initiative
	* @return the initiative
	* @throws PortalException if a initiative with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Initiative getInitiative(
		long initiativeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _initiativeLocalService.getInitiative(initiativeId);
	}

	/**
	* Returns the initiative matching the UUID and group.
	*
	* @param uuid the initiative's UUID
	* @param groupId the primary key of the group
	* @return the matching initiative
	* @throws PortalException if a matching initiative could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Initiative getInitiativeByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _initiativeLocalService.getInitiativeByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Supprime une initiative
	*/
	@Override
	public eu.strasbourg.service.project.model.Initiative removeInitiative(
		long initiativeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _initiativeLocalService.removeInitiative(initiativeId);
	}

	/**
	* Updates the initiative in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param initiative the initiative
	* @return the initiative that was updated
	*/
	@Override
	public eu.strasbourg.service.project.model.Initiative updateInitiative(
		eu.strasbourg.service.project.model.Initiative initiative) {
		return _initiativeLocalService.updateInitiative(initiative);
	}

	/**
	* Met à jour une initiative et l'enregistre en base de données
	*
	* @throws IOException
	*/
	@Override
	public eu.strasbourg.service.project.model.Initiative updateInitiative(
		eu.strasbourg.service.project.model.Initiative initiative,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _initiativeLocalService.updateInitiative(initiative, sc);
	}

	/**
	* Returns the number of initiatives.
	*
	* @return the number of initiatives
	*/
	@Override
	public int getInitiativesCount() {
		return _initiativeLocalService.getInitiativesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _initiativeLocalService.getOSGiServiceIdentifier();
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
		return _initiativeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _initiativeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _initiativeLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Retourne toutes les initiatives d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative> getByGroupId(
		long groupId) {
		return _initiativeLocalService.getByGroupId(groupId);
	}

	/**
	* Returns a range of all the initiatives.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @return the range of initiatives
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative> getInitiatives(
		int start, int end) {
		return _initiativeLocalService.getInitiatives(start, end);
	}

	/**
	* Returns all the initiatives matching the UUID and company.
	*
	* @param uuid the UUID of the initiatives
	* @param companyId the primary key of the company
	* @return the matching initiatives, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative> getInitiativesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _initiativeLocalService.getInitiativesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of initiatives matching the UUID and company.
	*
	* @param uuid the UUID of the initiatives
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching initiatives, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative> getInitiativesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.project.model.Initiative> orderByComparator) {
		return _initiativeLocalService.getInitiativesByUuidAndCompanyId(uuid,
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
		return _initiativeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _initiativeLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public InitiativeLocalService getWrappedService() {
		return _initiativeLocalService;
	}

	@Override
	public void setWrappedService(InitiativeLocalService initiativeLocalService) {
		_initiativeLocalService = initiativeLocalService;
	}

	private InitiativeLocalService _initiativeLocalService;
}