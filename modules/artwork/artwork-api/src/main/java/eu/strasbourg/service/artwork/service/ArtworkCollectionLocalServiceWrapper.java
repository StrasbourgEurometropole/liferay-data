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

package eu.strasbourg.service.artwork.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ArtworkCollectionLocalService}.
 *
 * @author BenjaminBini
 * @see ArtworkCollectionLocalService
 * @generated
 */
@ProviderType
public class ArtworkCollectionLocalServiceWrapper
	implements ArtworkCollectionLocalService,
		ServiceWrapper<ArtworkCollectionLocalService> {
	public ArtworkCollectionLocalServiceWrapper(
		ArtworkCollectionLocalService artworkCollectionLocalService) {
		_artworkCollectionLocalService = artworkCollectionLocalService;
	}

	@Override
	public boolean hasArtworkArtworkCollection(long artworkId, long collectionId) {
		return _artworkCollectionLocalService.hasArtworkArtworkCollection(artworkId,
			collectionId);
	}

	@Override
	public boolean hasArtworkArtworkCollections(long artworkId) {
		return _artworkCollectionLocalService.hasArtworkArtworkCollections(artworkId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _artworkCollectionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _artworkCollectionLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _artworkCollectionLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _artworkCollectionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkCollectionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkCollectionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Search
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _artworkCollectionLocalService.search(searchContext);
	}

	/**
	* Adds the artwork collection to the database. Also notifies the appropriate model listeners.
	*
	* @param artworkCollection the artwork collection
	* @return the artwork collection that was added
	*/
	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection addArtworkCollection(
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		return _artworkCollectionLocalService.addArtworkCollection(artworkCollection);
	}

	/**
	* Crée une édition vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection createArtworkCollection(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkCollectionLocalService.createArtworkCollection(sc);
	}

	/**
	* Creates a new artwork collection with the primary key. Does not add the artwork collection to the database.
	*
	* @param collectionId the primary key for the new artwork collection
	* @return the new artwork collection
	*/
	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection createArtworkCollection(
		long collectionId) {
		return _artworkCollectionLocalService.createArtworkCollection(collectionId);
	}

	/**
	* Deletes the artwork collection from the database. Also notifies the appropriate model listeners.
	*
	* @param artworkCollection the artwork collection
	* @return the artwork collection that was removed
	*/
	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection deleteArtworkCollection(
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		return _artworkCollectionLocalService.deleteArtworkCollection(artworkCollection);
	}

	/**
	* Deletes the artwork collection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param collectionId the primary key of the artwork collection
	* @return the artwork collection that was removed
	* @throws PortalException if a artwork collection with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection deleteArtworkCollection(
		long collectionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkCollectionLocalService.deleteArtworkCollection(collectionId);
	}

	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection fetchArtworkCollection(
		long collectionId) {
		return _artworkCollectionLocalService.fetchArtworkCollection(collectionId);
	}

	/**
	* Returns the artwork collection matching the UUID and group.
	*
	* @param uuid the artwork collection's UUID
	* @param groupId the primary key of the group
	* @return the matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection fetchArtworkCollectionByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _artworkCollectionLocalService.fetchArtworkCollectionByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the artwork collection with the primary key.
	*
	* @param collectionId the primary key of the artwork collection
	* @return the artwork collection
	* @throws PortalException if a artwork collection with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection getArtworkCollection(
		long collectionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkCollectionLocalService.getArtworkCollection(collectionId);
	}

	/**
	* Returns the artwork collection matching the UUID and group.
	*
	* @param uuid the artwork collection's UUID
	* @param groupId the primary key of the group
	* @return the matching artwork collection
	* @throws PortalException if a matching artwork collection could not be found
	*/
	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection getArtworkCollectionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkCollectionLocalService.getArtworkCollectionByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Delete an artworkCollection
	*/
	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection removeArtworkCollection(
		long artworkCollectionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkCollectionLocalService.removeArtworkCollection(artworkCollectionId);
	}

	/**
	* Updates the artwork collection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param artworkCollection the artwork collection
	* @return the artwork collection that was updated
	*/
	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection updateArtworkCollection(
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		return _artworkCollectionLocalService.updateArtworkCollection(artworkCollection);
	}

	/**
	* Met à jour une édition et l'enregistre en base de données
	*/
	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection updateArtworkCollection(
		eu.strasbourg.service.artwork.model.ArtworkCollection collection,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkCollectionLocalService.updateArtworkCollection(collection,
			sc);
	}

	/**
	* /**
	* Met à jour le statut de l'oeuvre par le framework workflow
	*/
	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkCollectionLocalService.updateStatus(userId, entryId,
			status, sc, workflowContext);
	}

	@Override
	public int getArtworkArtworkCollectionsCount(long artworkId) {
		return _artworkCollectionLocalService.getArtworkArtworkCollectionsCount(artworkId);
	}

	/**
	* Returns the number of artwork collections.
	*
	* @return the number of artwork collections
	*/
	@Override
	public int getArtworkCollectionsCount() {
		return _artworkCollectionLocalService.getArtworkCollectionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _artworkCollectionLocalService.getOSGiServiceIdentifier();
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
		return _artworkCollectionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.artwork.model.impl.ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _artworkCollectionLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.artwork.model.impl.ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _artworkCollectionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _artworkCollectionLocalService.findByKeyword(keyword, groupId,
			start, end);
	}

	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkArtworkCollections(
		long artworkId) {
		return _artworkCollectionLocalService.getArtworkArtworkCollections(artworkId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkArtworkCollections(
		long artworkId, int start, int end) {
		return _artworkCollectionLocalService.getArtworkArtworkCollections(artworkId,
			start, end);
	}

	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkArtworkCollections(
		long artworkId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.artwork.model.ArtworkCollection> orderByComparator) {
		return _artworkCollectionLocalService.getArtworkArtworkCollections(artworkId,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the artwork collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.artwork.model.impl.ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @return the range of artwork collections
	*/
	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkCollections(
		int start, int end) {
		return _artworkCollectionLocalService.getArtworkCollections(start, end);
	}

	/**
	* Returns all the artwork collections matching the UUID and company.
	*
	* @param uuid the UUID of the artwork collections
	* @param companyId the primary key of the company
	* @return the matching artwork collections, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkCollectionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _artworkCollectionLocalService.getArtworkCollectionsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of artwork collections matching the UUID and company.
	*
	* @param uuid the UUID of the artwork collections
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching artwork collections, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkCollectionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.artwork.model.ArtworkCollection> orderByComparator) {
		return _artworkCollectionLocalService.getArtworkCollectionsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Return the vocabularies attached to the ArtworkCollection entity
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _artworkCollectionLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les collections d'oeuvres d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getByGroupId(
		long groupId) {
		return _artworkCollectionLocalService.getByGroupId(groupId);
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
		return _artworkCollectionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _artworkCollectionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _artworkCollectionLocalService.findByKeywordCount(keyword,
			groupId);
	}

	/**
	* Returns the artworkIds of the artworks associated with the artwork collection.
	*
	* @param collectionId the collectionId of the artwork collection
	* @return long[] the artworkIds of artworks associated with the artwork collection
	*/
	@Override
	public long[] getArtworkPrimaryKeys(long collectionId) {
		return _artworkCollectionLocalService.getArtworkPrimaryKeys(collectionId);
	}

	@Override
	public void addArtworkArtworkCollection(long artworkId,
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		_artworkCollectionLocalService.addArtworkArtworkCollection(artworkId,
			artworkCollection);
	}

	@Override
	public void addArtworkArtworkCollection(long artworkId, long collectionId) {
		_artworkCollectionLocalService.addArtworkArtworkCollection(artworkId,
			collectionId);
	}

	@Override
	public void addArtworkArtworkCollections(long artworkId,
		java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> artworkCollections) {
		_artworkCollectionLocalService.addArtworkArtworkCollections(artworkId,
			artworkCollections);
	}

	@Override
	public void addArtworkArtworkCollections(long artworkId,
		long[] collectionIds) {
		_artworkCollectionLocalService.addArtworkArtworkCollections(artworkId,
			collectionIds);
	}

	@Override
	public void clearArtworkArtworkCollections(long artworkId) {
		_artworkCollectionLocalService.clearArtworkArtworkCollections(artworkId);
	}

	@Override
	public void deleteArtworkArtworkCollection(long artworkId,
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		_artworkCollectionLocalService.deleteArtworkArtworkCollection(artworkId,
			artworkCollection);
	}

	@Override
	public void deleteArtworkArtworkCollection(long artworkId, long collectionId) {
		_artworkCollectionLocalService.deleteArtworkArtworkCollection(artworkId,
			collectionId);
	}

	@Override
	public void deleteArtworkArtworkCollections(long artworkId,
		java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> artworkCollections) {
		_artworkCollectionLocalService.deleteArtworkArtworkCollections(artworkId,
			artworkCollections);
	}

	@Override
	public void deleteArtworkArtworkCollections(long artworkId,
		long[] collectionIds) {
		_artworkCollectionLocalService.deleteArtworkArtworkCollections(artworkId,
			collectionIds);
	}

	@Override
	public void setArtworkArtworkCollections(long artworkId,
		long[] collectionIds) {
		_artworkCollectionLocalService.setArtworkArtworkCollections(artworkId,
			collectionIds);
	}

	/**
	* Met à jour le statut de l'oeuvre "manuellement" (pas via le workflow)
	*/
	@Override
	public void updateStatus(
		eu.strasbourg.service.artwork.model.ArtworkCollection collection,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		_artworkCollectionLocalService.updateStatus(collection, status);
	}

	@Override
	public ArtworkCollectionLocalService getWrappedService() {
		return _artworkCollectionLocalService;
	}

	@Override
	public void setWrappedService(
		ArtworkCollectionLocalService artworkCollectionLocalService) {
		_artworkCollectionLocalService = artworkCollectionLocalService;
	}

	private ArtworkCollectionLocalService _artworkCollectionLocalService;
}