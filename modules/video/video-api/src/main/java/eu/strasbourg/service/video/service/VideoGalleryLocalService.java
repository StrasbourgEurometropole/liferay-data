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

package eu.strasbourg.service.video.service;

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

import eu.strasbourg.service.video.model.VideoGallery;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for VideoGallery. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author BenjaminBini
 * @see VideoGalleryLocalServiceUtil
 * @see eu.strasbourg.service.video.service.base.VideoGalleryLocalServiceBaseImpl
 * @see eu.strasbourg.service.video.service.impl.VideoGalleryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface VideoGalleryLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VideoGalleryLocalServiceUtil} to access the video gallery local service. Add custom service methods to {@link eu.strasbourg.service.video.service.impl.VideoGalleryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasVideoVideoGalleries(long videoId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasVideoVideoGallery(long videoId, long galleryId);

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
	* Recherche par le moteur de recherche
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(SearchContext searchContext) throws SearchException;

	/**
	* Adds the video gallery to the database. Also notifies the appropriate model listeners.
	*
	* @param videoGallery the video gallery
	* @return the video gallery that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public VideoGallery addVideoGallery(VideoGallery videoGallery);

	/**
	* Crée un lien vide avec une PK, non ajouté à la base de donnée
	*/
	public VideoGallery createVideoGallery(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new video gallery with the primary key. Does not add the video gallery to the database.
	*
	* @param galleryId the primary key for the new video gallery
	* @return the new video gallery
	*/
	public VideoGallery createVideoGallery(long galleryId);

	/**
	* Deletes the video gallery from the database. Also notifies the appropriate model listeners.
	*
	* @param videoGallery the video gallery
	* @return the video gallery that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public VideoGallery deleteVideoGallery(VideoGallery videoGallery);

	/**
	* Deletes the video gallery with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param galleryId the primary key of the video gallery
	* @return the video gallery that was removed
	* @throws PortalException if a video gallery with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public VideoGallery deleteVideoGallery(long galleryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public VideoGallery fetchVideoGallery(long galleryId);

	/**
	* Returns the video gallery matching the UUID and group.
	*
	* @param uuid the video gallery's UUID
	* @param groupId the primary key of the group
	* @return the matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public VideoGallery fetchVideoGalleryByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the video gallery with the primary key.
	*
	* @param galleryId the primary key of the video gallery
	* @return the video gallery
	* @throws PortalException if a video gallery with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public VideoGallery getVideoGallery(long galleryId)
		throws PortalException;

	/**
	* Returns the video gallery matching the UUID and group.
	*
	* @param uuid the video gallery's UUID
	* @param groupId the primary key of the group
	* @return the matching video gallery
	* @throws PortalException if a matching video gallery could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public VideoGallery getVideoGalleryByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Delete an Video Gallery
	*
	* @param galleryId
	The ID of the video gallery to delete
	* @return The deleted VideoGallery
	* @throws PortalException
	*/
	public VideoGallery removeGallery(long galleryId) throws PortalException;

	/**
	* Met à jour le statut de la galerie par le framework workflow
	*/
	public VideoGallery updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<java.lang.String, Serializable> workflowContext)
		throws PortalException;

	/**
	* Met à jour un lien et l'enregistre en base de données
	*/
	public VideoGallery updateVideoGallery(VideoGallery gallery,
		ServiceContext sc) throws PortalException;

	/**
	* Updates the video gallery in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param videoGallery the video gallery
	* @return the video gallery that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public VideoGallery updateVideoGallery(VideoGallery videoGallery);

	/**
	* Returns the number of video galleries.
	*
	* @return the number of video galleries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getVideoGalleriesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getVideoVideoGalleriesCount(long videoId);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.video.model.impl.VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.video.model.impl.VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Recherche par mots clés
	*/
	public List<VideoGallery> findByKeyword(java.lang.String keyword,
		long groupId, int start, int end);

	/**
	* Retourne la liste des vocabulaires rattachés à l'entité
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne toutes les galeries éditions d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<VideoGallery> getByGroupId(long groupId);

	/**
	* Returns a range of all the video galleries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.video.model.impl.VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @return the range of video galleries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<VideoGallery> getVideoGalleries(int start, int end);

	/**
	* Returns all the video galleries matching the UUID and company.
	*
	* @param uuid the UUID of the video galleries
	* @param companyId the primary key of the company
	* @return the matching video galleries, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<VideoGallery> getVideoGalleriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of video galleries matching the UUID and company.
	*
	* @param uuid the UUID of the video galleries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching video galleries, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<VideoGallery> getVideoGalleriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<VideoGallery> getVideoVideoGalleries(long videoId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<VideoGallery> getVideoVideoGalleries(long videoId, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<VideoGallery> getVideoVideoGalleries(long videoId, int start,
		int end, OrderByComparator<VideoGallery> orderByComparator);

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

	/**
	* Compte de la recherche par mots-clés
	*/
	public long findByKeywordCount(java.lang.String keyword, long groupId);

	/**
	* Returns the videoIds of the videos associated with the video gallery.
	*
	* @param galleryId the galleryId of the video gallery
	* @return long[] the videoIds of videos associated with the video gallery
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getVideoPrimaryKeys(long galleryId);

	public void addVideoVideoGalleries(long videoId,
		List<VideoGallery> videoGalleries);

	public void addVideoVideoGalleries(long videoId, long[] galleryIds);

	public void addVideoVideoGallery(long videoId, VideoGallery videoGallery);

	public void addVideoVideoGallery(long videoId, long galleryId);

	public void clearVideoVideoGalleries(long videoId);

	public void deleteVideoVideoGalleries(long videoId,
		List<VideoGallery> videoGalleries);

	public void deleteVideoVideoGalleries(long videoId, long[] galleryIds);

	public void deleteVideoVideoGallery(long videoId, VideoGallery videoGallery);

	public void deleteVideoVideoGallery(long videoId, long galleryId);

	public void setVideoVideoGalleries(long videoId, long[] galleryIds);

	/**
	* Met à jour le statut de la galerie "manuellement" (pas via le workflow)
	*/
	public void updateStatus(VideoGallery gallery, int status)
		throws PortalException;
}