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

package eu.strasbourg.service.agenda.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ManifestationLocalService}.
 *
 * @author BenjaminBini
 * @see ManifestationLocalService
 * @generated
 */
@ProviderType
public class ManifestationLocalServiceWrapper
	implements ManifestationLocalService,
		ServiceWrapper<ManifestationLocalService> {
	public ManifestationLocalServiceWrapper(
		ManifestationLocalService manifestationLocalService) {
		_manifestationLocalService = manifestationLocalService;
	}

	@Override
	public boolean hasEventManifestation(long eventId, long manifestationId) {
		return _manifestationLocalService.hasEventManifestation(eventId,
			manifestationId);
	}

	@Override
	public boolean hasEventManifestations(long eventId) {
		return _manifestationLocalService.hasEventManifestations(eventId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _manifestationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _manifestationLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _manifestationLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _manifestationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _manifestationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _manifestationLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _manifestationLocalService.search(searchContext);
	}

	/**
	* Adds the manifestation to the database. Also notifies the appropriate model listeners.
	*
	* @param manifestation the manifestation
	* @return the manifestation that was added
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Manifestation addManifestation(
		eu.strasbourg.service.agenda.model.Manifestation manifestation) {
		return _manifestationLocalService.addManifestation(manifestation);
	}

	/**
	* Crée un lien vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Manifestation createManifestation(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _manifestationLocalService.createManifestation(sc);
	}

	/**
	* Creates a new manifestation with the primary key. Does not add the manifestation to the database.
	*
	* @param manifestationId the primary key for the new manifestation
	* @return the new manifestation
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Manifestation createManifestation(
		long manifestationId) {
		return _manifestationLocalService.createManifestation(manifestationId);
	}

	/**
	* Deletes the manifestation from the database. Also notifies the appropriate model listeners.
	*
	* @param manifestation the manifestation
	* @return the manifestation that was removed
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Manifestation deleteManifestation(
		eu.strasbourg.service.agenda.model.Manifestation manifestation) {
		return _manifestationLocalService.deleteManifestation(manifestation);
	}

	/**
	* Deletes the manifestation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param manifestationId the primary key of the manifestation
	* @return the manifestation that was removed
	* @throws PortalException if a manifestation with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Manifestation deleteManifestation(
		long manifestationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _manifestationLocalService.deleteManifestation(manifestationId);
	}

	@Override
	public eu.strasbourg.service.agenda.model.Manifestation fetchManifestation(
		long manifestationId) {
		return _manifestationLocalService.fetchManifestation(manifestationId);
	}

	/**
	* Returns the manifestation matching the UUID and group.
	*
	* @param uuid the manifestation's UUID
	* @param groupId the primary key of the group
	* @return the matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Manifestation fetchManifestationByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _manifestationLocalService.fetchManifestationByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public eu.strasbourg.service.agenda.model.Manifestation findBySourceAndIdSource(
		java.lang.String source, java.lang.String idSource) {
		return _manifestationLocalService.findBySourceAndIdSource(source,
			idSource);
	}

	/**
	* Returns the manifestation with the primary key.
	*
	* @param manifestationId the primary key of the manifestation
	* @return the manifestation
	* @throws PortalException if a manifestation with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Manifestation getManifestation(
		long manifestationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _manifestationLocalService.getManifestation(manifestationId);
	}

	/**
	* Returns the manifestation matching the UUID and group.
	*
	* @param uuid the manifestation's UUID
	* @param groupId the primary key of the group
	* @return the matching manifestation
	* @throws PortalException if a matching manifestation could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Manifestation getManifestationByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _manifestationLocalService.getManifestationByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Delete an Event Manifestation
	*
	* @param manifestationId
	The ID of the event manifestation to delete
	* @return The deleted Manifestation
	* @throws PortalException
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Manifestation removeManifestation(
		long manifestationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _manifestationLocalService.removeManifestation(manifestationId);
	}

	/**
	* Updates the manifestation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param manifestation the manifestation
	* @return the manifestation that was updated
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Manifestation updateManifestation(
		eu.strasbourg.service.agenda.model.Manifestation manifestation) {
		return _manifestationLocalService.updateManifestation(manifestation);
	}

	/**
	* Met à jour un lien et l'enregistre en base de données
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Manifestation updateManifestation(
		eu.strasbourg.service.agenda.model.Manifestation manifestation,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _manifestationLocalService.updateManifestation(manifestation, sc);
	}

	/**
	* Met à jour le statut de la galerie par le framework workflow
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Manifestation updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _manifestationLocalService.updateStatus(userId, entryId, status,
			sc, workflowContext);
	}

	@Override
	public int getEventManifestationsCount(long eventId) {
		return _manifestationLocalService.getEventManifestationsCount(eventId);
	}

	/**
	* Returns the number of manifestations.
	*
	* @return the number of manifestations
	*/
	@Override
	public int getManifestationsCount() {
		return _manifestationLocalService.getManifestationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _manifestationLocalService.getOSGiServiceIdentifier();
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
		return _manifestationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _manifestationLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _manifestationLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _manifestationLocalService.findByKeyword(keyword, groupId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _manifestationLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les galeries éditions d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getByGroupId(
		long groupId) {
		return _manifestationLocalService.getByGroupId(groupId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getEventManifestations(
		long eventId) {
		return _manifestationLocalService.getEventManifestations(eventId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getEventManifestations(
		long eventId, int start, int end) {
		return _manifestationLocalService.getEventManifestations(eventId,
			start, end);
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getEventManifestations(
		long eventId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.agenda.model.Manifestation> orderByComparator) {
		return _manifestationLocalService.getEventManifestations(eventId,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the manifestations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @return the range of manifestations
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getManifestations(
		int start, int end) {
		return _manifestationLocalService.getManifestations(start, end);
	}

	/**
	* Returns all the manifestations matching the UUID and company.
	*
	* @param uuid the UUID of the manifestations
	* @param companyId the primary key of the company
	* @return the matching manifestations, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getManifestationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _manifestationLocalService.getManifestationsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of manifestations matching the UUID and company.
	*
	* @param uuid the UUID of the manifestations
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching manifestations, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getManifestationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.agenda.model.Manifestation> orderByComparator) {
		return _manifestationLocalService.getManifestationsByUuidAndCompanyId(uuid,
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
		return _manifestationLocalService.dynamicQueryCount(dynamicQuery);
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
		return _manifestationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _manifestationLocalService.findByKeywordCount(keyword, groupId);
	}

	/**
	* Returns the eventIds of the events associated with the manifestation.
	*
	* @param manifestationId the manifestationId of the manifestation
	* @return long[] the eventIds of events associated with the manifestation
	*/
	@Override
	public long[] getEventPrimaryKeys(long manifestationId) {
		return _manifestationLocalService.getEventPrimaryKeys(manifestationId);
	}

	@Override
	public void addEventManifestation(long eventId,
		eu.strasbourg.service.agenda.model.Manifestation manifestation) {
		_manifestationLocalService.addEventManifestation(eventId, manifestation);
	}

	@Override
	public void addEventManifestation(long eventId, long manifestationId) {
		_manifestationLocalService.addEventManifestation(eventId,
			manifestationId);
	}

	@Override
	public void addEventManifestations(long eventId,
		java.util.List<eu.strasbourg.service.agenda.model.Manifestation> manifestations) {
		_manifestationLocalService.addEventManifestations(eventId,
			manifestations);
	}

	@Override
	public void addEventManifestations(long eventId, long[] manifestationIds) {
		_manifestationLocalService.addEventManifestations(eventId,
			manifestationIds);
	}

	/**
	* Modifie le statut de tous les manifestations au statut "SCHEDULED" qui
	* ont une date de publication dans le futur
	*/
	@Override
	public void checkManifestations()
		throws com.liferay.portal.kernel.exception.PortalException {
		_manifestationLocalService.checkManifestations();
	}

	@Override
	public void clearEventManifestations(long eventId) {
		_manifestationLocalService.clearEventManifestations(eventId);
	}

	@Override
	public void deleteEventManifestation(long eventId,
		eu.strasbourg.service.agenda.model.Manifestation manifestation) {
		_manifestationLocalService.deleteEventManifestation(eventId,
			manifestation);
	}

	@Override
	public void deleteEventManifestation(long eventId, long manifestationId) {
		_manifestationLocalService.deleteEventManifestation(eventId,
			manifestationId);
	}

	@Override
	public void deleteEventManifestations(long eventId,
		java.util.List<eu.strasbourg.service.agenda.model.Manifestation> manifestations) {
		_manifestationLocalService.deleteEventManifestations(eventId,
			manifestations);
	}

	@Override
	public void deleteEventManifestations(long eventId, long[] manifestationIds) {
		_manifestationLocalService.deleteEventManifestations(eventId,
			manifestationIds);
	}

	@Override
	public void setEventManifestations(long eventId, long[] manifestationIds) {
		_manifestationLocalService.setEventManifestations(eventId,
			manifestationIds);
	}

	/**
	* Met à jour le statut de la galerie "manuellement" (pas via le workflow)
	*/
	@Override
	public void updateStatus(
		eu.strasbourg.service.agenda.model.Manifestation manifestation,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		_manifestationLocalService.updateStatus(manifestation, status);
	}

	@Override
	public ManifestationLocalService getWrappedService() {
		return _manifestationLocalService;
	}

	@Override
	public void setWrappedService(
		ManifestationLocalService manifestationLocalService) {
		_manifestationLocalService = manifestationLocalService;
	}

	private ManifestationLocalService _manifestationLocalService;
}