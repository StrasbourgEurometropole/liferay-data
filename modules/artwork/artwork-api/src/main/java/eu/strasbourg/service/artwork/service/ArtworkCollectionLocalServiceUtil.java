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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ArtworkCollection. This utility wraps
 * {@link eu.strasbourg.service.artwork.service.impl.ArtworkCollectionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author BenjaminBini
 * @see ArtworkCollectionLocalService
 * @see eu.strasbourg.service.artwork.service.base.ArtworkCollectionLocalServiceBaseImpl
 * @see eu.strasbourg.service.artwork.service.impl.ArtworkCollectionLocalServiceImpl
 * @generated
 */
@ProviderType
public class ArtworkCollectionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.artwork.service.impl.ArtworkCollectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasArtworkArtworkCollection(long artworkId,
		long collectionId) {
		return getService().hasArtworkArtworkCollection(artworkId, collectionId);
	}

	public static boolean hasArtworkArtworkCollections(long artworkId) {
		return getService().hasArtworkArtworkCollections(artworkId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
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
	* Search
	*/
	public static com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return getService().search(searchContext);
	}

	/**
	* Adds the artwork collection to the database. Also notifies the appropriate model listeners.
	*
	* @param artworkCollection the artwork collection
	* @return the artwork collection that was added
	*/
	public static eu.strasbourg.service.artwork.model.ArtworkCollection addArtworkCollection(
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		return getService().addArtworkCollection(artworkCollection);
	}

	/**
	* Crée une édition vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.artwork.model.ArtworkCollection createArtworkCollection(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createArtworkCollection(sc);
	}

	/**
	* Creates a new artwork collection with the primary key. Does not add the artwork collection to the database.
	*
	* @param collectionId the primary key for the new artwork collection
	* @return the new artwork collection
	*/
	public static eu.strasbourg.service.artwork.model.ArtworkCollection createArtworkCollection(
		long collectionId) {
		return getService().createArtworkCollection(collectionId);
	}

	/**
	* Deletes the artwork collection from the database. Also notifies the appropriate model listeners.
	*
	* @param artworkCollection the artwork collection
	* @return the artwork collection that was removed
	*/
	public static eu.strasbourg.service.artwork.model.ArtworkCollection deleteArtworkCollection(
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		return getService().deleteArtworkCollection(artworkCollection);
	}

	/**
	* Deletes the artwork collection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param collectionId the primary key of the artwork collection
	* @return the artwork collection that was removed
	* @throws PortalException if a artwork collection with the primary key could not be found
	*/
	public static eu.strasbourg.service.artwork.model.ArtworkCollection deleteArtworkCollection(
		long collectionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteArtworkCollection(collectionId);
	}

	public static eu.strasbourg.service.artwork.model.ArtworkCollection fetchArtworkCollection(
		long collectionId) {
		return getService().fetchArtworkCollection(collectionId);
	}

	/**
	* Returns the artwork collection matching the UUID and group.
	*
	* @param uuid the artwork collection's UUID
	* @param groupId the primary key of the group
	* @return the matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public static eu.strasbourg.service.artwork.model.ArtworkCollection fetchArtworkCollectionByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchArtworkCollectionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the artwork collection with the primary key.
	*
	* @param collectionId the primary key of the artwork collection
	* @return the artwork collection
	* @throws PortalException if a artwork collection with the primary key could not be found
	*/
	public static eu.strasbourg.service.artwork.model.ArtworkCollection getArtworkCollection(
		long collectionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getArtworkCollection(collectionId);
	}

	/**
	* Returns the artwork collection matching the UUID and group.
	*
	* @param uuid the artwork collection's UUID
	* @param groupId the primary key of the group
	* @return the matching artwork collection
	* @throws PortalException if a matching artwork collection could not be found
	*/
	public static eu.strasbourg.service.artwork.model.ArtworkCollection getArtworkCollectionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getArtworkCollectionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Delete an artworkCollection
	*/
	public static eu.strasbourg.service.artwork.model.ArtworkCollection removeArtworkCollection(
		long artworkCollectionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeArtworkCollection(artworkCollectionId);
	}

	/**
	* Updates the artwork collection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param artworkCollection the artwork collection
	* @return the artwork collection that was updated
	*/
	public static eu.strasbourg.service.artwork.model.ArtworkCollection updateArtworkCollection(
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		return getService().updateArtworkCollection(artworkCollection);
	}

	/**
	* Met à jour une édition et l'enregistre en base de données
	*/
	public static eu.strasbourg.service.artwork.model.ArtworkCollection updateArtworkCollection(
		eu.strasbourg.service.artwork.model.ArtworkCollection collection,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateArtworkCollection(collection, sc);
	}

	/**
	* /**
	* Met à jour le statut de l'oeuvre par le framework workflow
	*/
	public static eu.strasbourg.service.artwork.model.ArtworkCollection updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, entryId, status, sc, workflowContext);
	}

	public static int getArtworkArtworkCollectionsCount(long artworkId) {
		return getService().getArtworkArtworkCollectionsCount(artworkId);
	}

	/**
	* Returns the number of artwork collections.
	*
	* @return the number of artwork collections
	*/
	public static int getArtworkCollectionsCount() {
		return getService().getArtworkCollectionsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.artwork.model.impl.ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.artwork.model.impl.ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return getService().findByKeyword(keyword, groupId, start, end);
	}

	public static java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkArtworkCollections(
		long artworkId) {
		return getService().getArtworkArtworkCollections(artworkId);
	}

	public static java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkArtworkCollections(
		long artworkId, int start, int end) {
		return getService().getArtworkArtworkCollections(artworkId, start, end);
	}

	public static java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkArtworkCollections(
		long artworkId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.artwork.model.ArtworkCollection> orderByComparator) {
		return getService()
				   .getArtworkArtworkCollections(artworkId, start, end,
			orderByComparator);
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
	public static java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkCollections(
		int start, int end) {
		return getService().getArtworkCollections(start, end);
	}

	/**
	* Returns all the artwork collections matching the UUID and company.
	*
	* @param uuid the UUID of the artwork collections
	* @param companyId the primary key of the company
	* @return the matching artwork collections, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkCollectionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getArtworkCollectionsByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkCollectionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.artwork.model.ArtworkCollection> orderByComparator) {
		return getService()
				   .getArtworkCollectionsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Return the vocabularies attached to the ArtworkCollection entity
	*/
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les collections d'oeuvres d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
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

	public static long findByKeywordCount(java.lang.String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	/**
	* Returns the artworkIds of the artworks associated with the artwork collection.
	*
	* @param collectionId the collectionId of the artwork collection
	* @return long[] the artworkIds of artworks associated with the artwork collection
	*/
	public static long[] getArtworkPrimaryKeys(long collectionId) {
		return getService().getArtworkPrimaryKeys(collectionId);
	}

	public static void addArtworkArtworkCollection(long artworkId,
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		getService().addArtworkArtworkCollection(artworkId, artworkCollection);
	}

	public static void addArtworkArtworkCollection(long artworkId,
		long collectionId) {
		getService().addArtworkArtworkCollection(artworkId, collectionId);
	}

	public static void addArtworkArtworkCollections(long artworkId,
		java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> artworkCollections) {
		getService().addArtworkArtworkCollections(artworkId, artworkCollections);
	}

	public static void addArtworkArtworkCollections(long artworkId,
		long[] collectionIds) {
		getService().addArtworkArtworkCollections(artworkId, collectionIds);
	}

	public static void clearArtworkArtworkCollections(long artworkId) {
		getService().clearArtworkArtworkCollections(artworkId);
	}

	public static void deleteArtworkArtworkCollection(long artworkId,
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		getService().deleteArtworkArtworkCollection(artworkId, artworkCollection);
	}

	public static void deleteArtworkArtworkCollection(long artworkId,
		long collectionId) {
		getService().deleteArtworkArtworkCollection(artworkId, collectionId);
	}

	public static void deleteArtworkArtworkCollections(long artworkId,
		java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> artworkCollections) {
		getService()
			.deleteArtworkArtworkCollections(artworkId, artworkCollections);
	}

	public static void deleteArtworkArtworkCollections(long artworkId,
		long[] collectionIds) {
		getService().deleteArtworkArtworkCollections(artworkId, collectionIds);
	}

	public static void setArtworkArtworkCollections(long artworkId,
		long[] collectionIds) {
		getService().setArtworkArtworkCollections(artworkId, collectionIds);
	}

	/**
	* Met à jour le statut de l'oeuvre "manuellement" (pas via le workflow)
	*/
	public static void updateStatus(
		eu.strasbourg.service.artwork.model.ArtworkCollection collection,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateStatus(collection, status);
	}

	public static ArtworkCollectionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ArtworkCollectionLocalService, ArtworkCollectionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ArtworkCollectionLocalService.class);
}