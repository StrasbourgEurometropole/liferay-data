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

package eu.strasbourg.service.gtfs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LigneLocalService}.
 *
 * @author Cedric Henry
 * @see LigneLocalService
 * @generated
 */
@ProviderType
public class LigneLocalServiceWrapper implements LigneLocalService,
	ServiceWrapper<LigneLocalService> {
	public LigneLocalServiceWrapper(LigneLocalService ligneLocalService) {
		_ligneLocalService = ligneLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ligneLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ligneLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _ligneLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ligneLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the ligne to the database. Also notifies the appropriate model listeners.
	*
	* @param ligne the ligne
	* @return the ligne that was added
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne addLigne(
		eu.strasbourg.service.gtfs.model.Ligne ligne) {
		return _ligneLocalService.addLigne(ligne);
	}

	/**
	* Creates a new ligne with the primary key. Does not add the ligne to the database.
	*
	* @param ligneId the primary key for the new ligne
	* @return the new ligne
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne createLigne(long ligneId) {
		return _ligneLocalService.createLigne(ligneId);
	}

	/**
	* Deletes the ligne from the database. Also notifies the appropriate model listeners.
	*
	* @param ligne the ligne
	* @return the ligne that was removed
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne deleteLigne(
		eu.strasbourg.service.gtfs.model.Ligne ligne) {
		return _ligneLocalService.deleteLigne(ligne);
	}

	/**
	* Deletes the ligne with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ligneId the primary key of the ligne
	* @return the ligne that was removed
	* @throws PortalException if a ligne with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne deleteLigne(long ligneId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.deleteLigne(ligneId);
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Ligne fetchLigne(long ligneId) {
		return _ligneLocalService.fetchLigne(ligneId);
	}

	/**
	* Returns the ligne matching the UUID and group.
	*
	* @param uuid the ligne's UUID
	* @param groupId the primary key of the group
	* @return the matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne fetchLigneByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _ligneLocalService.fetchLigneByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the ligne with the primary key.
	*
	* @param ligneId the primary key of the ligne
	* @return the ligne
	* @throws PortalException if a ligne with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne getLigne(long ligneId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.getLigne(ligneId);
	}

	/**
	* Returns the ligne matching the UUID and group.
	*
	* @param uuid the ligne's UUID
	* @param groupId the primary key of the group
	* @return the matching ligne
	* @throws PortalException if a matching ligne could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne getLigneByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.getLigneByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the ligne in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ligne the ligne
	* @return the ligne that was updated
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne updateLigne(
		eu.strasbourg.service.gtfs.model.Ligne ligne) {
		return _ligneLocalService.updateLigne(ligne);
	}

	/**
	* Returns the number of lignes.
	*
	* @return the number of lignes
	*/
	@Override
	public int getLignesCount() {
		return _ligneLocalService.getLignesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ligneLocalService.getOSGiServiceIdentifier();
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
		return _ligneLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ligneLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ligneLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the lignes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @return the range of lignes
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Ligne> getLignes(
		int start, int end) {
		return _ligneLocalService.getLignes(start, end);
	}

	/**
	* Returns all the lignes matching the UUID and company.
	*
	* @param uuid the UUID of the lignes
	* @param companyId the primary key of the company
	* @return the matching lignes, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Ligne> getLignesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _ligneLocalService.getLignesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of lignes matching the UUID and company.
	*
	* @param uuid the UUID of the lignes
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching lignes, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Ligne> getLignesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.gtfs.model.Ligne> orderByComparator) {
		return _ligneLocalService.getLignesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
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
		return _ligneLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ligneLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public LigneLocalService getWrappedService() {
		return _ligneLocalService;
	}

	@Override
	public void setWrappedService(LigneLocalService ligneLocalService) {
		_ligneLocalService = ligneLocalService;
	}

	private LigneLocalService _ligneLocalService;
}