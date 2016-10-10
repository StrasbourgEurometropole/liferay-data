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

import com.liferay.asset.kernel.model.AssetVocabulary;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.artwork.model.ArtworkCollection;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for ArtworkCollection. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author BenjaminBini
 * @see ArtworkCollectionLocalServiceUtil
 * @see eu.strasbourg.service.artwork.service.base.ArtworkCollectionLocalServiceBaseImpl
 * @see eu.strasbourg.service.artwork.service.impl.ArtworkCollectionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ArtworkCollectionLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ArtworkCollectionLocalServiceUtil} to access the artwork collection local service. Add custom service methods to {@link eu.strasbourg.service.artwork.service.impl.ArtworkCollectionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasArtworkArtworkCollection(long artworkId, long collectionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasArtworkArtworkCollections(long artworkId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Search
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(SearchContext searchContext) throws SearchException;

	/**
	* Adds the artwork collection to the database. Also notifies the appropriate model listeners.
	*
	* @param artworkCollection the artwork collection
	* @return the artwork collection that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ArtworkCollection addArtworkCollection(
		ArtworkCollection artworkCollection);

	/**
	* Crée une édition vide avec une PK, non ajouté à la base de donnée
	*/
	public ArtworkCollection createArtworkCollection(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new artwork collection with the primary key. Does not add the artwork collection to the database.
	*
	* @param collectionId the primary key for the new artwork collection
	* @return the new artwork collection
	*/
	public ArtworkCollection createArtworkCollection(long collectionId);

	/**
	* Deletes the artwork collection from the database. Also notifies the appropriate model listeners.
	*
	* @param artworkCollection the artwork collection
	* @return the artwork collection that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ArtworkCollection deleteArtworkCollection(
		ArtworkCollection artworkCollection);

	/**
	* Deletes the artwork collection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param collectionId the primary key of the artwork collection
	* @return the artwork collection that was removed
	* @throws PortalException if a artwork collection with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ArtworkCollection deleteArtworkCollection(long collectionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ArtworkCollection fetchArtworkCollection(long collectionId);

	/**
	* Returns the artwork collection matching the UUID and group.
	*
	* @param uuid the artwork collection's UUID
	* @param groupId the primary key of the group
	* @return the matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ArtworkCollection fetchArtworkCollectionByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the artwork collection with the primary key.
	*
	* @param collectionId the primary key of the artwork collection
	* @return the artwork collection
	* @throws PortalException if a artwork collection with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ArtworkCollection getArtworkCollection(long collectionId)
		throws PortalException;

	/**
	* Returns the artwork collection matching the UUID and group.
	*
	* @param uuid the artwork collection's UUID
	* @param groupId the primary key of the group
	* @return the matching artwork collection
	* @throws PortalException if a matching artwork collection could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ArtworkCollection getArtworkCollectionByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Delete an artworkCollection
	*/
	public ArtworkCollection removeArtworkCollection(long artworkCollectionId)
		throws PortalException;

	/**
	* Updates the artwork collection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param artworkCollection the artwork collection
	* @return the artwork collection that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ArtworkCollection updateArtworkCollection(
		ArtworkCollection artworkCollection);

	/**
	* Met à jour une édition et l'enregistre en base de données
	*/
	public ArtworkCollection updateArtworkCollection(
		ArtworkCollection collection, ServiceContext sc)
		throws PortalException;

	/**
	* /**
	* Met à jour le statut de l'oeuvre par le framework workflow
	*/
	public ArtworkCollection updateStatus(long userId, long entryId,
		int status, ServiceContext sc,
		Map<java.lang.String, Serializable> workflowContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getArtworkArtworkCollectionsCount(long artworkId);

	/**
	* Returns the number of artwork collections.
	*
	* @return the number of artwork collections
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getArtworkCollectionsCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	public List<ArtworkCollection> findByKeyword(java.lang.String keyword,
		long groupId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ArtworkCollection> getArtworkArtworkCollections(long artworkId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ArtworkCollection> getArtworkArtworkCollections(
		long artworkId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ArtworkCollection> getArtworkArtworkCollections(
		long artworkId, int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ArtworkCollection> getArtworkCollections(int start, int end);

	/**
	* Returns all the artwork collections matching the UUID and company.
	*
	* @param uuid the UUID of the artwork collections
	* @param companyId the primary key of the company
	* @return the matching artwork collections, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ArtworkCollection> getArtworkCollectionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ArtworkCollection> getArtworkCollectionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator);

	/**
	* Return the vocabularies attached to the ArtworkCollection entity
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne toutes les collections d'oeuvres d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ArtworkCollection> getByGroupId(long groupId);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public long findByKeywordCount(java.lang.String keyword, long groupId);

	/**
	* Returns the artworkIds of the artworks associated with the artwork collection.
	*
	* @param collectionId the collectionId of the artwork collection
	* @return long[] the artworkIds of artworks associated with the artwork collection
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getArtworkPrimaryKeys(long collectionId);

	public void addArtworkArtworkCollection(long artworkId,
		ArtworkCollection artworkCollection);

	public void addArtworkArtworkCollection(long artworkId, long collectionId);

	public void addArtworkArtworkCollections(long artworkId,
		List<ArtworkCollection> artworkCollections);

	public void addArtworkArtworkCollections(long artworkId,
		long[] collectionIds);

	public void clearArtworkArtworkCollections(long artworkId);

	public void deleteArtworkArtworkCollection(long artworkId,
		ArtworkCollection artworkCollection);

	public void deleteArtworkArtworkCollection(long artworkId, long collectionId);

	public void deleteArtworkArtworkCollections(long artworkId,
		List<ArtworkCollection> artworkCollections);

	public void deleteArtworkArtworkCollections(long artworkId,
		long[] collectionIds);

	public void setArtworkArtworkCollections(long artworkId,
		long[] collectionIds);

	/**
	* Met à jour le statut de l'oeuvre "manuellement" (pas via le workflow)
	*/
	public void updateStatus(ArtworkCollection collection, int status)
		throws PortalException;
}