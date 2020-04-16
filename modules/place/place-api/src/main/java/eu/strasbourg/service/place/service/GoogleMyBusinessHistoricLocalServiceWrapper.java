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

package eu.strasbourg.service.place.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GoogleMyBusinessHistoricLocalService}.
 *
 * @author Angelique Zunino Champougny
 * @see GoogleMyBusinessHistoricLocalService
 * @generated
 */
@ProviderType
public class GoogleMyBusinessHistoricLocalServiceWrapper
	implements GoogleMyBusinessHistoricLocalService,
			   ServiceWrapper<GoogleMyBusinessHistoricLocalService> {

	public GoogleMyBusinessHistoricLocalServiceWrapper(
		GoogleMyBusinessHistoricLocalService
			googleMyBusinessHistoricLocalService) {

		_googleMyBusinessHistoricLocalService =
			googleMyBusinessHistoricLocalService;
	}

	/**
	 * Adds the google my business historic to the database. Also notifies the appropriate model listeners.
	 *
	 * @param googleMyBusinessHistoric the google my business historic
	 * @return the google my business historic that was added
	 */
	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
		addGoogleMyBusinessHistoric(
			eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
				googleMyBusinessHistoric) {

		return _googleMyBusinessHistoricLocalService.
			addGoogleMyBusinessHistoric(googleMyBusinessHistoric);
	}

	/**
	 * Creates a new google my business historic with the primary key. Does not add the google my business historic to the database.
	 *
	 * @param googleMyBusinessHistoricId the primary key for the new google my business historic
	 * @return the new google my business historic
	 */
	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
		createGoogleMyBusinessHistoric(long googleMyBusinessHistoricId) {

		return _googleMyBusinessHistoricLocalService.
			createGoogleMyBusinessHistoric(googleMyBusinessHistoricId);
	}

	/**
	 * Crée une entree google mybusiness vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
			createGoogleMyBusinessHistoric(
				com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _googleMyBusinessHistoricLocalService.
			createGoogleMyBusinessHistoric(sc);
	}

	/**
	 * Deletes the google my business historic from the database. Also notifies the appropriate model listeners.
	 *
	 * @param googleMyBusinessHistoric the google my business historic
	 * @return the google my business historic that was removed
	 */
	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
		deleteGoogleMyBusinessHistoric(
			eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
				googleMyBusinessHistoric) {

		return _googleMyBusinessHistoricLocalService.
			deleteGoogleMyBusinessHistoric(googleMyBusinessHistoric);
	}

	/**
	 * Deletes the google my business historic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the google my business historic
	 * @return the google my business historic that was removed
	 * @throws PortalException if a google my business historic with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
			deleteGoogleMyBusinessHistoric(long googleMyBusinessHistoricId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _googleMyBusinessHistoricLocalService.
			deleteGoogleMyBusinessHistoric(googleMyBusinessHistoricId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _googleMyBusinessHistoricLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Effectue la synchronisation
	 */
	@Override
	public void doSynchronisation(
		com.liferay.portal.kernel.service.ServiceContext sc,
		eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
			googleMyBusinessHistoric) {

		_googleMyBusinessHistoricLocalService.doSynchronisation(
			sc, googleMyBusinessHistoric);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _googleMyBusinessHistoricLocalService.dynamicQuery();
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

		return _googleMyBusinessHistoricLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _googleMyBusinessHistoricLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _googleMyBusinessHistoricLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _googleMyBusinessHistoricLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _googleMyBusinessHistoricLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
		fetchGoogleMyBusinessHistoric(long googleMyBusinessHistoricId) {

		return _googleMyBusinessHistoricLocalService.
			fetchGoogleMyBusinessHistoric(googleMyBusinessHistoricId);
	}

	/**
	 * Returns the google my business historic matching the UUID and group.
	 *
	 * @param uuid the google my business historic's UUID
	 * @param groupId the primary key of the group
	 * @return the matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
		fetchGoogleMyBusinessHistoricByUuidAndGroupId(
			String uuid, long groupId) {

		return _googleMyBusinessHistoricLocalService.
			fetchGoogleMyBusinessHistoricByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Recherche par mot clés
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.place.model.GoogleMyBusinessHistoric>
			findByKeyword(String keyword, long groupId, int start, int end) {

		return _googleMyBusinessHistoricLocalService.findByKeyword(
			keyword, groupId, start, end);
	}

	/**
	 * Recherche par mot clés (compte)
	 */
	@Override
	public long findByKeywordCount(String keyword, long groupId) {
		return _googleMyBusinessHistoricLocalService.findByKeywordCount(
			keyword, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _googleMyBusinessHistoricLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Renvoie la liste des vocabulaires rattachés à google mybusiness
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary>
		getAttachedVocabularies(long groupId) {

		return _googleMyBusinessHistoricLocalService.getAttachedVocabularies(
			groupId);
	}

	/**
	 * Retourne tous les google mybusiness d'un groupe
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.place.model.GoogleMyBusinessHistoric>
			getByGroupId(long groupId) {

		return _googleMyBusinessHistoricLocalService.getByGroupId(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _googleMyBusinessHistoricLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	 * Returns the google my business historic with the primary key.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the google my business historic
	 * @return the google my business historic
	 * @throws PortalException if a google my business historic with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
			getGoogleMyBusinessHistoric(long googleMyBusinessHistoricId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _googleMyBusinessHistoricLocalService.
			getGoogleMyBusinessHistoric(googleMyBusinessHistoricId);
	}

	/**
	 * Returns the google my business historic matching the UUID and group.
	 *
	 * @param uuid the google my business historic's UUID
	 * @param groupId the primary key of the group
	 * @return the matching google my business historic
	 * @throws PortalException if a matching google my business historic could not be found
	 */
	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
			getGoogleMyBusinessHistoricByUuidAndGroupId(
				String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _googleMyBusinessHistoricLocalService.
			getGoogleMyBusinessHistoricByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the google my business historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @return the range of google my business historics
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.place.model.GoogleMyBusinessHistoric>
			getGoogleMyBusinessHistorics(int start, int end) {

		return _googleMyBusinessHistoricLocalService.
			getGoogleMyBusinessHistorics(start, end);
	}

	/**
	 * Returns all the google my business historics matching the UUID and company.
	 *
	 * @param uuid the UUID of the google my business historics
	 * @param companyId the primary key of the company
	 * @return the matching google my business historics, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.place.model.GoogleMyBusinessHistoric>
			getGoogleMyBusinessHistoricsByUuidAndCompanyId(
				String uuid, long companyId) {

		return _googleMyBusinessHistoricLocalService.
			getGoogleMyBusinessHistoricsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of google my business historics matching the UUID and company.
	 *
	 * @param uuid the UUID of the google my business historics
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching google my business historics, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.place.model.GoogleMyBusinessHistoric>
			getGoogleMyBusinessHistoricsByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<eu.strasbourg.service.place.model.GoogleMyBusinessHistoric>
						orderByComparator) {

		return _googleMyBusinessHistoricLocalService.
			getGoogleMyBusinessHistoricsByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of google my business historics.
	 *
	 * @return the number of google my business historics
	 */
	@Override
	public int getGoogleMyBusinessHistoricsCount() {
		return _googleMyBusinessHistoricLocalService.
			getGoogleMyBusinessHistoricsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _googleMyBusinessHistoricLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _googleMyBusinessHistoricLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _googleMyBusinessHistoricLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Supprime l'entree google mybusiness
	 */
	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
			removeGoogleMyBusinessHistoric(long googleMyBusinessHistoricId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _googleMyBusinessHistoricLocalService.
			removeGoogleMyBusinessHistoric(googleMyBusinessHistoricId);
	}

	/**
	 * Updates the google my business historic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param googleMyBusinessHistoric the google my business historic
	 * @return the google my business historic that was updated
	 */
	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
		updateGoogleMyBusinessHistoric(
			eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
				googleMyBusinessHistoric) {

		return _googleMyBusinessHistoricLocalService.
			updateGoogleMyBusinessHistoric(googleMyBusinessHistoric);
	}

	/**
	 * Met à jour une entree google mybusiness et l'enregistre en base de données
	 *
	 * @throws PortalException
	 */
	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
			updateGoogleMyBusinessHistoric(
				eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
					googleMyBusinessHistoric,
				com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _googleMyBusinessHistoricLocalService.
			updateGoogleMyBusinessHistoric(googleMyBusinessHistoric, sc);
	}

	/**
	 * Met à jour le statut de l'entree google mybusiness "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(
			eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
				googleMyBusinessHistoric,
			int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		_googleMyBusinessHistoricLocalService.updateStatus(
			googleMyBusinessHistoric, status);
	}

	/**
	 * Met à jour le statut de l'entree google mybusiness par le framework workflow
	 */
	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
			updateStatus(
				long userId, long entryId, int status,
				com.liferay.portal.kernel.service.ServiceContext sc,
				java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _googleMyBusinessHistoricLocalService.updateStatus(
			userId, entryId, status, sc, workflowContext);
	}

	@Override
	public GoogleMyBusinessHistoricLocalService getWrappedService() {
		return _googleMyBusinessHistoricLocalService;
	}

	@Override
	public void setWrappedService(
		GoogleMyBusinessHistoricLocalService
			googleMyBusinessHistoricLocalService) {

		_googleMyBusinessHistoricLocalService =
			googleMyBusinessHistoricLocalService;
	}

	private GoogleMyBusinessHistoricLocalService
		_googleMyBusinessHistoricLocalService;

}