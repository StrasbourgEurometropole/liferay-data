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

import eu.strasbourg.service.artwork.model.Artwork;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for Artwork. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author BenjaminBini
 * @see ArtworkLocalServiceUtil
 * @see eu.strasbourg.service.artwork.service.base.ArtworkLocalServiceBaseImpl
 * @see eu.strasbourg.service.artwork.service.impl.ArtworkLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ArtworkLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ArtworkLocalServiceUtil} to access the artwork local service. Add custom service methods to {@link eu.strasbourg.service.artwork.service.impl.ArtworkLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasArtworkCollectionArtwork(long collectionId, long artworkId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasArtworkCollectionArtworks(long collectionId);

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
	* Adds the artwork to the database. Also notifies the appropriate model listeners.
	*
	* @param artwork the artwork
	* @return the artwork that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Artwork addArtwork(Artwork artwork);

	/**
	* Crée une édition vide avec une PK, non ajouté à la base de donnée
	*/
	public Artwork createArtwork(ServiceContext sc) throws PortalException;

	/**
	* Creates a new artwork with the primary key. Does not add the artwork to the database.
	*
	* @param artworkId the primary key for the new artwork
	* @return the new artwork
	*/
	public Artwork createArtwork(long artworkId);

	/**
	* Deletes the artwork from the database. Also notifies the appropriate model listeners.
	*
	* @param artwork the artwork
	* @return the artwork that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Artwork deleteArtwork(Artwork artwork);

	/**
	* Deletes the artwork with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param artworkId the primary key of the artwork
	* @return the artwork that was removed
	* @throws PortalException if a artwork with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Artwork deleteArtwork(long artworkId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Artwork fetchArtwork(long artworkId);

	/**
	* Returns the artwork matching the UUID and group.
	*
	* @param uuid the artwork's UUID
	* @param groupId the primary key of the group
	* @return the matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Artwork fetchArtworkByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Returns the artwork with the primary key.
	*
	* @param artworkId the primary key of the artwork
	* @return the artwork
	* @throws PortalException if a artwork with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Artwork getArtwork(long artworkId) throws PortalException;

	/**
	* Returns the artwork matching the UUID and group.
	*
	* @param uuid the artwork's UUID
	* @param groupId the primary key of the group
	* @return the matching artwork
	* @throws PortalException if a matching artwork could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Artwork getArtworkByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Delete an artwork
	*/
	public Artwork removeArtwork(long artworkId) throws PortalException;

	/**
	* Updates the artwork in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param artwork the artwork
	* @return the artwork that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Artwork updateArtwork(Artwork artwork);

	/**
	* Met à jour une édition et l'enregistre en base de données
	*/
	public Artwork updateArtwork(Artwork artwork, ServiceContext sc)
		throws PortalException;

	/**
	* Met à jour le statut de l'oeuvre par le framework workflow
	*/
	public Artwork updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<java.lang.String, Serializable> workflowContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getArtworkCollectionArtworksCount(long collectionId);

	/**
	* Returns the number of artworks.
	*
	* @return the number of artworks
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getArtworksCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.artwork.model.impl.ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.artwork.model.impl.ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public List<Artwork> findByKeyword(java.lang.String keyword, long groupId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Artwork> getArtworkCollectionArtworks(long collectionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Artwork> getArtworkCollectionArtworks(long collectionId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Artwork> getArtworkCollectionArtworks(long collectionId,
		int start, int end, OrderByComparator<Artwork> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Artwork> getArtworks(int start, int end);

	/**
	* Returns all the artworks matching the UUID and company.
	*
	* @param uuid the UUID of the artworks
	* @param companyId the primary key of the company
	* @return the matching artworks, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Artwork> getArtworksByUuidAndCompanyId(java.lang.String uuid,
		long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Artwork> getArtworksByUuidAndCompanyId(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Artwork> orderByComparator);

	/**
	* Return the vocabularies attached to the Artwork entity
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne toutes les oeuvres d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Artwork> getByGroupId(long groupId);

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
	* Returns the collectionIds of the artwork collections associated with the artwork.
	*
	* @param artworkId the artworkId of the artwork
	* @return long[] the collectionIds of artwork collections associated with the artwork
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getArtworkCollectionPrimaryKeys(long artworkId);

	public void addArtworkCollectionArtwork(long collectionId, Artwork artwork);

	public void addArtworkCollectionArtwork(long collectionId, long artworkId);

	public void addArtworkCollectionArtworks(long collectionId,
		List<Artwork> artworks);

	public void addArtworkCollectionArtworks(long collectionId,
		long[] artworkIds);

	public void clearArtworkCollectionArtworks(long collectionId);

	public void deleteArtworkCollectionArtwork(long collectionId,
		Artwork artwork);

	public void deleteArtworkCollectionArtwork(long collectionId, long artworkId);

	public void deleteArtworkCollectionArtworks(long collectionId,
		List<Artwork> artworks);

	public void deleteArtworkCollectionArtworks(long collectionId,
		long[] artworkIds);

	public void setArtworkCollectionArtworks(long collectionId,
		long[] artworkIds);

	/**
	* Met à jour le statut de l'oeuvre "manuellement" (pas via le workflow)
	*/
	public void updateStatus(Artwork artwork, int status)
		throws PortalException;
}