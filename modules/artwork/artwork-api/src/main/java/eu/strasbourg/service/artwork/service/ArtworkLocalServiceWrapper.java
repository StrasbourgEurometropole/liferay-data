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
 * Provides a wrapper for {@link ArtworkLocalService}.
 *
 * @author BenjaminBini
 * @see ArtworkLocalService
 * @generated
 */
@ProviderType
public class ArtworkLocalServiceWrapper implements ArtworkLocalService,
	ServiceWrapper<ArtworkLocalService> {
	public ArtworkLocalServiceWrapper(ArtworkLocalService artworkLocalService) {
		_artworkLocalService = artworkLocalService;
	}

	@Override
	public boolean hasArtworkCollectionArtwork(long collectionId, long artworkId) {
		return _artworkLocalService.hasArtworkCollectionArtwork(collectionId,
			artworkId);
	}

	@Override
	public boolean hasArtworkCollectionArtworks(long collectionId) {
		return _artworkLocalService.hasArtworkCollectionArtworks(collectionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _artworkLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _artworkLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _artworkLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _artworkLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Search
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _artworkLocalService.search(searchContext);
	}

	/**
	* Create an empty artwork
	*/
	@Override
	public eu.strasbourg.service.artwork.model.Artwork addArtwork()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkLocalService.addArtwork();
	}

	/**
	* Adds the artwork to the database. Also notifies the appropriate model listeners.
	*
	* @param artwork the artwork
	* @return the artwork that was added
	*/
	@Override
	public eu.strasbourg.service.artwork.model.Artwork addArtwork(
		eu.strasbourg.service.artwork.model.Artwork artwork) {
		return _artworkLocalService.addArtwork(artwork);
	}

	/**
	* Creates a new artwork with the primary key. Does not add the artwork to the database.
	*
	* @param artworkId the primary key for the new artwork
	* @return the new artwork
	*/
	@Override
	public eu.strasbourg.service.artwork.model.Artwork createArtwork(
		long artworkId) {
		return _artworkLocalService.createArtwork(artworkId);
	}

	/**
	* Deletes the artwork from the database. Also notifies the appropriate model listeners.
	*
	* @param artwork the artwork
	* @return the artwork that was removed
	*/
	@Override
	public eu.strasbourg.service.artwork.model.Artwork deleteArtwork(
		eu.strasbourg.service.artwork.model.Artwork artwork) {
		return _artworkLocalService.deleteArtwork(artwork);
	}

	/**
	* Deletes the artwork with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param artworkId the primary key of the artwork
	* @return the artwork that was removed
	* @throws PortalException if a artwork with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.artwork.model.Artwork deleteArtwork(
		long artworkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkLocalService.deleteArtwork(artworkId);
	}

	@Override
	public eu.strasbourg.service.artwork.model.Artwork fetchArtwork(
		long artworkId) {
		return _artworkLocalService.fetchArtwork(artworkId);
	}

	/**
	* Returns the artwork matching the UUID and group.
	*
	* @param uuid the artwork's UUID
	* @param groupId the primary key of the group
	* @return the matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	@Override
	public eu.strasbourg.service.artwork.model.Artwork fetchArtworkByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _artworkLocalService.fetchArtworkByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the artwork with the primary key.
	*
	* @param artworkId the primary key of the artwork
	* @return the artwork
	* @throws PortalException if a artwork with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.artwork.model.Artwork getArtwork(
		long artworkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkLocalService.getArtwork(artworkId);
	}

	/**
	* Returns the artwork matching the UUID and group.
	*
	* @param uuid the artwork's UUID
	* @param groupId the primary key of the group
	* @return the matching artwork
	* @throws PortalException if a matching artwork could not be found
	*/
	@Override
	public eu.strasbourg.service.artwork.model.Artwork getArtworkByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkLocalService.getArtworkByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Delete an artwork
	*/
	@Override
	public eu.strasbourg.service.artwork.model.Artwork removeArtwork(
		long artworkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkLocalService.removeArtwork(artworkId);
	}

	/**
	* Updates the artwork in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param artwork the artwork
	* @return the artwork that was updated
	*/
	@Override
	public eu.strasbourg.service.artwork.model.Artwork updateArtwork(
		eu.strasbourg.service.artwork.model.Artwork artwork) {
		return _artworkLocalService.updateArtwork(artwork);
	}

	/**
	* Update an artwork
	*/
	@Override
	public eu.strasbourg.service.artwork.model.Artwork updateArtwork(
		eu.strasbourg.service.artwork.model.Artwork artwork,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artworkLocalService.updateArtwork(artwork, sc);
	}

	@Override
	public int getArtworkCollectionArtworksCount(long collectionId) {
		return _artworkLocalService.getArtworkCollectionArtworksCount(collectionId);
	}

	/**
	* Returns the number of artworks.
	*
	* @return the number of artworks
	*/
	@Override
	public int getArtworksCount() {
		return _artworkLocalService.getArtworksCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _artworkLocalService.getOSGiServiceIdentifier();
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
		return _artworkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.artwork.model.impl.ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _artworkLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.artwork.model.impl.ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _artworkLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.Artwork> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _artworkLocalService.findByKeyword(keyword, groupId, start, end);
	}

	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.Artwork> getArtworkCollectionArtworks(
		long collectionId) {
		return _artworkLocalService.getArtworkCollectionArtworks(collectionId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.Artwork> getArtworkCollectionArtworks(
		long collectionId, int start, int end) {
		return _artworkLocalService.getArtworkCollectionArtworks(collectionId,
			start, end);
	}

	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.Artwork> getArtworkCollectionArtworks(
		long collectionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.artwork.model.Artwork> orderByComparator) {
		return _artworkLocalService.getArtworkCollectionArtworks(collectionId,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the artworks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.artwork.model.impl.ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @return the range of artworks
	*/
	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.Artwork> getArtworks(
		int start, int end) {
		return _artworkLocalService.getArtworks(start, end);
	}

	/**
	* Returns all the artworks matching the UUID and company.
	*
	* @param uuid the UUID of the artworks
	* @param companyId the primary key of the company
	* @return the matching artworks, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.Artwork> getArtworksByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _artworkLocalService.getArtworksByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of artworks matching the UUID and company.
	*
	* @param uuid the UUID of the artworks
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching artworks, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.Artwork> getArtworksByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.artwork.model.Artwork> orderByComparator) {
		return _artworkLocalService.getArtworksByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Return the vocabularies attached to the Artwork entity
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _artworkLocalService.getAttachedVocabularies(groupId);
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
		return _artworkLocalService.dynamicQueryCount(dynamicQuery);
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
		return _artworkLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _artworkLocalService.findByKeywordCount(keyword, groupId);
	}

	/**
	* Returns the collectionIds of the artwork collections associated with the artwork.
	*
	* @param artworkId the artworkId of the artwork
	* @return long[] the collectionIds of artwork collections associated with the artwork
	*/
	@Override
	public long[] getArtworkCollectionPrimaryKeys(long artworkId) {
		return _artworkLocalService.getArtworkCollectionPrimaryKeys(artworkId);
	}

	@Override
	public void addArtworkCollectionArtwork(long collectionId,
		eu.strasbourg.service.artwork.model.Artwork artwork) {
		_artworkLocalService.addArtworkCollectionArtwork(collectionId, artwork);
	}

	@Override
	public void addArtworkCollectionArtwork(long collectionId, long artworkId) {
		_artworkLocalService.addArtworkCollectionArtwork(collectionId, artworkId);
	}

	@Override
	public void addArtworkCollectionArtworks(long collectionId,
		java.util.List<eu.strasbourg.service.artwork.model.Artwork> artworks) {
		_artworkLocalService.addArtworkCollectionArtworks(collectionId, artworks);
	}

	@Override
	public void addArtworkCollectionArtworks(long collectionId,
		long[] artworkIds) {
		_artworkLocalService.addArtworkCollectionArtworks(collectionId,
			artworkIds);
	}

	/**
	* Change the publication status of the artwork
	*/
	@Override
	public void changeStatus(
		eu.strasbourg.service.artwork.model.Artwork artwork,
		boolean publicationStatus)
		throws com.liferay.portal.kernel.exception.PortalException {
		_artworkLocalService.changeStatus(artwork, publicationStatus);
	}

	@Override
	public void clearArtworkCollectionArtworks(long collectionId) {
		_artworkLocalService.clearArtworkCollectionArtworks(collectionId);
	}

	@Override
	public void deleteArtworkCollectionArtwork(long collectionId,
		eu.strasbourg.service.artwork.model.Artwork artwork) {
		_artworkLocalService.deleteArtworkCollectionArtwork(collectionId,
			artwork);
	}

	@Override
	public void deleteArtworkCollectionArtwork(long collectionId, long artworkId) {
		_artworkLocalService.deleteArtworkCollectionArtwork(collectionId,
			artworkId);
	}

	@Override
	public void deleteArtworkCollectionArtworks(long collectionId,
		java.util.List<eu.strasbourg.service.artwork.model.Artwork> artworks) {
		_artworkLocalService.deleteArtworkCollectionArtworks(collectionId,
			artworks);
	}

	@Override
	public void deleteArtworkCollectionArtworks(long collectionId,
		long[] artworkIds) {
		_artworkLocalService.deleteArtworkCollectionArtworks(collectionId,
			artworkIds);
	}

	@Override
	public void setArtworkCollectionArtworks(long collectionId,
		long[] artworkIds) {
		_artworkLocalService.setArtworkCollectionArtworks(collectionId,
			artworkIds);
	}

	@Override
	public ArtworkLocalService getWrappedService() {
		return _artworkLocalService;
	}

	@Override
	public void setWrappedService(ArtworkLocalService artworkLocalService) {
		_artworkLocalService = artworkLocalService;
	}

	private ArtworkLocalService _artworkLocalService;
}