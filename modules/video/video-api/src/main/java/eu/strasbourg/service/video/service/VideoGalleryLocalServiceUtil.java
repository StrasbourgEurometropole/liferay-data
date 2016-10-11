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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for VideoGallery. This utility wraps
 * {@link eu.strasbourg.service.video.service.impl.VideoGalleryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author BenjaminBini
 * @see VideoGalleryLocalService
 * @see eu.strasbourg.service.video.service.base.VideoGalleryLocalServiceBaseImpl
 * @see eu.strasbourg.service.video.service.impl.VideoGalleryLocalServiceImpl
 * @generated
 */
@ProviderType
public class VideoGalleryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.video.service.impl.VideoGalleryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasVideoVideoGalleries(long videoId) {
		return getService().hasVideoVideoGalleries(videoId);
	}

	public static boolean hasVideoVideoGallery(long videoId, long galleryId) {
		return getService().hasVideoVideoGallery(videoId, galleryId);
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
	* Recherche par le moteur de recherche
	*/
	public static com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return getService().search(searchContext);
	}

	/**
	* Adds the video gallery to the database. Also notifies the appropriate model listeners.
	*
	* @param videoGallery the video gallery
	* @return the video gallery that was added
	*/
	public static eu.strasbourg.service.video.model.VideoGallery addVideoGallery(
		eu.strasbourg.service.video.model.VideoGallery videoGallery) {
		return getService().addVideoGallery(videoGallery);
	}

	/**
	* Crée un lien vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.video.model.VideoGallery createVideoGallery(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createVideoGallery(sc);
	}

	/**
	* Creates a new video gallery with the primary key. Does not add the video gallery to the database.
	*
	* @param galleryId the primary key for the new video gallery
	* @return the new video gallery
	*/
	public static eu.strasbourg.service.video.model.VideoGallery createVideoGallery(
		long galleryId) {
		return getService().createVideoGallery(galleryId);
	}

	/**
	* Deletes the video gallery from the database. Also notifies the appropriate model listeners.
	*
	* @param videoGallery the video gallery
	* @return the video gallery that was removed
	*/
	public static eu.strasbourg.service.video.model.VideoGallery deleteVideoGallery(
		eu.strasbourg.service.video.model.VideoGallery videoGallery) {
		return getService().deleteVideoGallery(videoGallery);
	}

	/**
	* Deletes the video gallery with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param galleryId the primary key of the video gallery
	* @return the video gallery that was removed
	* @throws PortalException if a video gallery with the primary key could not be found
	*/
	public static eu.strasbourg.service.video.model.VideoGallery deleteVideoGallery(
		long galleryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteVideoGallery(galleryId);
	}

	public static eu.strasbourg.service.video.model.VideoGallery fetchVideoGallery(
		long galleryId) {
		return getService().fetchVideoGallery(galleryId);
	}

	/**
	* Returns the video gallery matching the UUID and group.
	*
	* @param uuid the video gallery's UUID
	* @param groupId the primary key of the group
	* @return the matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public static eu.strasbourg.service.video.model.VideoGallery fetchVideoGalleryByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchVideoGalleryByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the video gallery with the primary key.
	*
	* @param galleryId the primary key of the video gallery
	* @return the video gallery
	* @throws PortalException if a video gallery with the primary key could not be found
	*/
	public static eu.strasbourg.service.video.model.VideoGallery getVideoGallery(
		long galleryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getVideoGallery(galleryId);
	}

	/**
	* Returns the video gallery matching the UUID and group.
	*
	* @param uuid the video gallery's UUID
	* @param groupId the primary key of the group
	* @return the matching video gallery
	* @throws PortalException if a matching video gallery could not be found
	*/
	public static eu.strasbourg.service.video.model.VideoGallery getVideoGalleryByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getVideoGalleryByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Delete an Video Gallery
	*
	* @param galleryId
	The ID of the video gallery to delete
	* @return The deleted VideoGallery
	* @throws PortalException
	*/
	public static eu.strasbourg.service.video.model.VideoGallery removeGallery(
		long galleryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeGallery(galleryId);
	}

	/**
	* Met à jour le statut de la galerie par le framework workflow
	*/
	public static eu.strasbourg.service.video.model.VideoGallery updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, entryId, status, sc, workflowContext);
	}

	/**
	* Met à jour un lien et l'enregistre en base de données
	*/
	public static eu.strasbourg.service.video.model.VideoGallery updateVideoGallery(
		eu.strasbourg.service.video.model.VideoGallery gallery,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateVideoGallery(gallery, sc);
	}

	/**
	* Updates the video gallery in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param videoGallery the video gallery
	* @return the video gallery that was updated
	*/
	public static eu.strasbourg.service.video.model.VideoGallery updateVideoGallery(
		eu.strasbourg.service.video.model.VideoGallery videoGallery) {
		return getService().updateVideoGallery(videoGallery);
	}

	/**
	* Returns the number of video galleries.
	*
	* @return the number of video galleries
	*/
	public static int getVideoGalleriesCount() {
		return getService().getVideoGalleriesCount();
	}

	public static int getVideoVideoGalleriesCount(long videoId) {
		return getService().getVideoVideoGalleriesCount(videoId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.video.model.impl.VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.video.model.impl.VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Recherche par mots clés
	*/
	public static java.util.List<eu.strasbourg.service.video.model.VideoGallery> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return getService().findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Retourne la liste des vocabulaires rattachés à l'entité
	*/
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les galeries éditions d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.video.model.VideoGallery> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

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
	public static java.util.List<eu.strasbourg.service.video.model.VideoGallery> getVideoGalleries(
		int start, int end) {
		return getService().getVideoGalleries(start, end);
	}

	/**
	* Returns all the video galleries matching the UUID and company.
	*
	* @param uuid the UUID of the video galleries
	* @param companyId the primary key of the company
	* @return the matching video galleries, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.video.model.VideoGallery> getVideoGalleriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getVideoGalleriesByUuidAndCompanyId(uuid, companyId);
	}

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
	public static java.util.List<eu.strasbourg.service.video.model.VideoGallery> getVideoGalleriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.video.model.VideoGallery> orderByComparator) {
		return getService()
				   .getVideoGalleriesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	public static java.util.List<eu.strasbourg.service.video.model.VideoGallery> getVideoVideoGalleries(
		long videoId) {
		return getService().getVideoVideoGalleries(videoId);
	}

	public static java.util.List<eu.strasbourg.service.video.model.VideoGallery> getVideoVideoGalleries(
		long videoId, int start, int end) {
		return getService().getVideoVideoGalleries(videoId, start, end);
	}

	public static java.util.List<eu.strasbourg.service.video.model.VideoGallery> getVideoVideoGalleries(
		long videoId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.video.model.VideoGallery> orderByComparator) {
		return getService()
				   .getVideoVideoGalleries(videoId, start, end,
			orderByComparator);
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

	/**
	* Compte de la recherche par mots-clés
	*/
	public static long findByKeywordCount(java.lang.String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	/**
	* Returns the videoIds of the videos associated with the video gallery.
	*
	* @param galleryId the galleryId of the video gallery
	* @return long[] the videoIds of videos associated with the video gallery
	*/
	public static long[] getVideoPrimaryKeys(long galleryId) {
		return getService().getVideoPrimaryKeys(galleryId);
	}

	public static void addVideoVideoGalleries(long videoId,
		java.util.List<eu.strasbourg.service.video.model.VideoGallery> videoGalleries) {
		getService().addVideoVideoGalleries(videoId, videoGalleries);
	}

	public static void addVideoVideoGalleries(long videoId, long[] galleryIds) {
		getService().addVideoVideoGalleries(videoId, galleryIds);
	}

	public static void addVideoVideoGallery(long videoId,
		eu.strasbourg.service.video.model.VideoGallery videoGallery) {
		getService().addVideoVideoGallery(videoId, videoGallery);
	}

	public static void addVideoVideoGallery(long videoId, long galleryId) {
		getService().addVideoVideoGallery(videoId, galleryId);
	}

	public static void clearVideoVideoGalleries(long videoId) {
		getService().clearVideoVideoGalleries(videoId);
	}

	public static void deleteVideoVideoGalleries(long videoId,
		java.util.List<eu.strasbourg.service.video.model.VideoGallery> videoGalleries) {
		getService().deleteVideoVideoGalleries(videoId, videoGalleries);
	}

	public static void deleteVideoVideoGalleries(long videoId, long[] galleryIds) {
		getService().deleteVideoVideoGalleries(videoId, galleryIds);
	}

	public static void deleteVideoVideoGallery(long videoId,
		eu.strasbourg.service.video.model.VideoGallery videoGallery) {
		getService().deleteVideoVideoGallery(videoId, videoGallery);
	}

	public static void deleteVideoVideoGallery(long videoId, long galleryId) {
		getService().deleteVideoVideoGallery(videoId, galleryId);
	}

	public static void setVideoVideoGalleries(long videoId, long[] galleryIds) {
		getService().setVideoVideoGalleries(videoId, galleryIds);
	}

	/**
	* Met à jour le statut de la galerie "manuellement" (pas via le workflow)
	*/
	public static void updateStatus(
		eu.strasbourg.service.video.model.VideoGallery gallery, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateStatus(gallery, status);
	}

	public static VideoGalleryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VideoGalleryLocalService, VideoGalleryLocalService> _serviceTracker =
		ServiceTrackerFactory.open(VideoGalleryLocalService.class);
}