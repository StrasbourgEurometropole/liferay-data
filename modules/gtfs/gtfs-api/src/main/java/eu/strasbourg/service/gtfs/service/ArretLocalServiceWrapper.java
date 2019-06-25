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
 * Provides a wrapper for {@link ArretLocalService}.
 *
 * @author Cedric Henry
 * @see ArretLocalService
 * @generated
 */
@ProviderType
public class ArretLocalServiceWrapper implements ArretLocalService,
	ServiceWrapper<ArretLocalService> {
	public ArretLocalServiceWrapper(ArretLocalService arretLocalService) {
		_arretLocalService = arretLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _arretLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _arretLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _arretLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _arretLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _arretLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _arretLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the arret to the database. Also notifies the appropriate model listeners.
	*
	* @param arret the arret
	* @return the arret that was added
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Arret addArret(
		eu.strasbourg.service.gtfs.model.Arret arret) {
		return _arretLocalService.addArret(arret);
	}

	/**
	* Crée une entree avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Arret createArret(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _arretLocalService.createArret(sc);
	}

	/**
	* Creates a new arret with the primary key. Does not add the arret to the database.
	*
	* @param arretId the primary key for the new arret
	* @return the new arret
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Arret createArret(long arretId) {
		return _arretLocalService.createArret(arretId);
	}

	/**
	* Deletes the arret from the database. Also notifies the appropriate model listeners.
	*
	* @param arret the arret
	* @return the arret that was removed
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Arret deleteArret(
		eu.strasbourg.service.gtfs.model.Arret arret) {
		return _arretLocalService.deleteArret(arret);
	}

	/**
	* Deletes the arret with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param arretId the primary key of the arret
	* @return the arret that was removed
	* @throws PortalException if a arret with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Arret deleteArret(long arretId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _arretLocalService.deleteArret(arretId);
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Arret fetchArret(long arretId) {
		return _arretLocalService.fetchArret(arretId);
	}

	/**
	* Returns the arret matching the UUID and group.
	*
	* @param uuid the arret's UUID
	* @param groupId the primary key of the group
	* @return the matching arret, or <code>null</code> if a matching arret could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Arret fetchArretByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _arretLocalService.fetchArretByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the arret with the primary key.
	*
	* @param arretId the primary key of the arret
	* @return the arret
	* @throws PortalException if a arret with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Arret getArret(long arretId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _arretLocalService.getArret(arretId);
	}

	/**
	* Returns the arret matching the UUID and group.
	*
	* @param uuid the arret's UUID
	* @param groupId the primary key of the group
	* @return the matching arret
	* @throws PortalException if a matching arret could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Arret getArretByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _arretLocalService.getArretByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Retourne un arret via son stopId CTS
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Arret getByStopId(
		java.lang.String stopId) {
		return _arretLocalService.getByStopId(stopId);
	}

	/**
	* Supprime l'entree
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Arret removeArret(long arretId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _arretLocalService.removeArret(arretId);
	}

	/**
	* Updates the arret in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param arret the arret
	* @return the arret that was updated
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Arret updateArret(
		eu.strasbourg.service.gtfs.model.Arret arret) {
		return _arretLocalService.updateArret(arret);
	}

	/**
	* Met à jour une entree et l'enregistre en base de données
	*
	* @throws IOException
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Arret updateArret(
		eu.strasbourg.service.gtfs.model.Arret arret,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _arretLocalService.updateArret(arret, sc);
	}

	/**
	* Met à jour le statut de l'entree par le framework workflow
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Arret updateStatus(long userId,
		long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _arretLocalService.updateStatus(userId, entryId, status, sc,
			workflowContext);
	}

	/**
	* Returns the number of arrets.
	*
	* @return the number of arrets
	*/
	@Override
	public int getArretsCount() {
		return _arretLocalService.getArretsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _arretLocalService.getOSGiServiceIdentifier();
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
		return _arretLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ArretModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _arretLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ArretModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _arretLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the arrets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ArretModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of arrets
	* @param end the upper bound of the range of arrets (not inclusive)
	* @return the range of arrets
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Arret> getArrets(
		int start, int end) {
		return _arretLocalService.getArrets(start, end);
	}

	/**
	* Returns all the arrets matching the UUID and company.
	*
	* @param uuid the UUID of the arrets
	* @param companyId the primary key of the company
	* @return the matching arrets, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Arret> getArretsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _arretLocalService.getArretsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of arrets matching the UUID and company.
	*
	* @param uuid the UUID of the arrets
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of arrets
	* @param end the upper bound of the range of arrets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching arrets, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Arret> getArretsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.gtfs.model.Arret> orderByComparator) {
		return _arretLocalService.getArretsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entree
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _arretLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les entrees d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Arret> getByGroupId(
		long groupId) {
		return _arretLocalService.getByGroupId(groupId);
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
		return _arretLocalService.dynamicQueryCount(dynamicQuery);
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
		return _arretLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public ArretLocalService getWrappedService() {
		return _arretLocalService;
	}

	@Override
	public void setWrappedService(ArretLocalService arretLocalService) {
		_arretLocalService = arretLocalService;
	}

	private ArretLocalService _arretLocalService;
}