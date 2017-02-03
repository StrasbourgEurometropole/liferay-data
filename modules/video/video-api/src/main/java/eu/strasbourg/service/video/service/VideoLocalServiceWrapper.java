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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VideoLocalService}.
 *
 * @author BenjaminBini
 * @see VideoLocalService
 * @generated
 */
@ProviderType
public class VideoLocalServiceWrapper implements VideoLocalService,
	ServiceWrapper<VideoLocalService> {
	public VideoLocalServiceWrapper(VideoLocalService videoLocalService) {
		_videoLocalService = videoLocalService;
	}

	@Override
	public boolean hasVideoGalleryVideo(long galleryId, long videoId) {
		return _videoLocalService.hasVideoGalleryVideo(galleryId, videoId);
	}

	@Override
	public boolean hasVideoGalleryVideos(long galleryId) {
		return _videoLocalService.hasVideoGalleryVideos(galleryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _videoLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _videoLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _videoLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _videoLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _videoLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _videoLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Lance une recherche selon le searchContext
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _videoLocalService.search(searchContext);
	}

	/**
	* Adds the video to the database. Also notifies the appropriate model listeners.
	*
	* @param video the video
	* @return the video that was added
	*/
	@Override
	public eu.strasbourg.service.video.model.Video addVideo(
		eu.strasbourg.service.video.model.Video video) {
		return _videoLocalService.addVideo(video);
	}

	/**
	* Crée une vidéo vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.video.model.Video createVideo(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _videoLocalService.createVideo(sc);
	}

	/**
	* Creates a new video with the primary key. Does not add the video to the database.
	*
	* @param videoId the primary key for the new video
	* @return the new video
	*/
	@Override
	public eu.strasbourg.service.video.model.Video createVideo(long videoId) {
		return _videoLocalService.createVideo(videoId);
	}

	/**
	* Deletes the video from the database. Also notifies the appropriate model listeners.
	*
	* @param video the video
	* @return the video that was removed
	*/
	@Override
	public eu.strasbourg.service.video.model.Video deleteVideo(
		eu.strasbourg.service.video.model.Video video) {
		return _videoLocalService.deleteVideo(video);
	}

	/**
	* Deletes the video with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param videoId the primary key of the video
	* @return the video that was removed
	* @throws PortalException if a video with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.video.model.Video deleteVideo(long videoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _videoLocalService.deleteVideo(videoId);
	}

	@Override
	public eu.strasbourg.service.video.model.Video fetchVideo(long videoId) {
		return _videoLocalService.fetchVideo(videoId);
	}

	/**
	* Returns the video matching the UUID and group.
	*
	* @param uuid the video's UUID
	* @param groupId the primary key of the group
	* @return the matching video, or <code>null</code> if a matching video could not be found
	*/
	@Override
	public eu.strasbourg.service.video.model.Video fetchVideoByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _videoLocalService.fetchVideoByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the video with the primary key.
	*
	* @param videoId the primary key of the video
	* @return the video
	* @throws PortalException if a video with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.video.model.Video getVideo(long videoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _videoLocalService.getVideo(videoId);
	}

	/**
	* Returns the video matching the UUID and group.
	*
	* @param uuid the video's UUID
	* @param groupId the primary key of the group
	* @return the matching video
	* @throws PortalException if a matching video could not be found
	*/
	@Override
	public eu.strasbourg.service.video.model.Video getVideoByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _videoLocalService.getVideoByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une vidéo
	*/
	@Override
	public eu.strasbourg.service.video.model.Video removeVideo(long videoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _videoLocalService.removeVideo(videoId);
	}

	/**
	* Met à jour le statut de la vidéo par le framework workflow
	*/
	@Override
	public eu.strasbourg.service.video.model.Video updateStatus(long userId,
		long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _videoLocalService.updateStatus(userId, entryId, status, sc,
			workflowContext);
	}

	/**
	* Updates the video in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param video the video
	* @return the video that was updated
	*/
	@Override
	public eu.strasbourg.service.video.model.Video updateVideo(
		eu.strasbourg.service.video.model.Video video) {
		return _videoLocalService.updateVideo(video);
	}

	/**
	* Met à jour une vidéo et l'enregistre en base de données
	*/
	@Override
	public eu.strasbourg.service.video.model.Video updateVideo(
		eu.strasbourg.service.video.model.Video video,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _videoLocalService.updateVideo(video, sc);
	}

	@Override
	public int getVideoGalleryVideosCount(long galleryId) {
		return _videoLocalService.getVideoGalleryVideosCount(galleryId);
	}

	/**
	* Returns the number of videos.
	*
	* @return the number of videos
	*/
	@Override
	public int getVideosCount() {
		return _videoLocalService.getVideosCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _videoLocalService.getOSGiServiceIdentifier();
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
		return _videoLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.video.model.impl.VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _videoLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.video.model.impl.VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _videoLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Lance une recherche par mots-clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.video.model.Video> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _videoLocalService.findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Retourne la liste des vocabulaires rattachés à l'entité
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _videoLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les vidéos d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.video.model.Video> getByGroupId(
		long groupId) {
		return _videoLocalService.getByGroupId(groupId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideoGalleryVideos(
		long galleryId) {
		return _videoLocalService.getVideoGalleryVideos(galleryId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideoGalleryVideos(
		long galleryId, int start, int end) {
		return _videoLocalService.getVideoGalleryVideos(galleryId, start, end);
	}

	@Override
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideoGalleryVideos(
		long galleryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.video.model.Video> orderByComparator) {
		return _videoLocalService.getVideoGalleryVideos(galleryId, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the videos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.video.model.impl.VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @return the range of videos
	*/
	@Override
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideos(
		int start, int end) {
		return _videoLocalService.getVideos(start, end);
	}

	/**
	* Returns all the videos matching the UUID and company.
	*
	* @param uuid the UUID of the videos
	* @param companyId the primary key of the company
	* @return the matching videos, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideosByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _videoLocalService.getVideosByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of videos matching the UUID and company.
	*
	* @param uuid the UUID of the videos
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching videos, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideosByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.video.model.Video> orderByComparator) {
		return _videoLocalService.getVideosByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
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
		return _videoLocalService.dynamicQueryCount(dynamicQuery);
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
		return _videoLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Compte de la recherche par mots-clés
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _videoLocalService.findByKeywordCount(keyword, groupId);
	}

	/**
	* Returns the galleryIds of the video galleries associated with the video.
	*
	* @param videoId the videoId of the video
	* @return long[] the galleryIds of video galleries associated with the video
	*/
	@Override
	public long[] getVideoGalleryPrimaryKeys(long videoId) {
		return _videoLocalService.getVideoGalleryPrimaryKeys(videoId);
	}

	@Override
	public void addVideoGalleryVideo(long galleryId,
		eu.strasbourg.service.video.model.Video video) {
		_videoLocalService.addVideoGalleryVideo(galleryId, video);
	}

	@Override
	public void addVideoGalleryVideo(long galleryId, long videoId) {
		_videoLocalService.addVideoGalleryVideo(galleryId, videoId);
	}

	@Override
	public void addVideoGalleryVideos(long galleryId,
		java.util.List<eu.strasbourg.service.video.model.Video> videos) {
		_videoLocalService.addVideoGalleryVideos(galleryId, videos);
	}

	@Override
	public void addVideoGalleryVideos(long galleryId, long[] videoIds) {
		_videoLocalService.addVideoGalleryVideos(galleryId, videoIds);
	}

	/**
	* Modifie le statut de toutes les vidéos au statut "SCHEDULED" qui ont une
	* date de publication dans le futur
	*/
	@Override
	public void checkVideos()
		throws com.liferay.portal.kernel.exception.PortalException {
		_videoLocalService.checkVideos();
	}

	@Override
	public void clearVideoGalleryVideos(long galleryId) {
		_videoLocalService.clearVideoGalleryVideos(galleryId);
	}

	@Override
	public void deleteVideoGalleryVideo(long galleryId,
		eu.strasbourg.service.video.model.Video video) {
		_videoLocalService.deleteVideoGalleryVideo(galleryId, video);
	}

	@Override
	public void deleteVideoGalleryVideo(long galleryId, long videoId) {
		_videoLocalService.deleteVideoGalleryVideo(galleryId, videoId);
	}

	@Override
	public void deleteVideoGalleryVideos(long galleryId,
		java.util.List<eu.strasbourg.service.video.model.Video> videos) {
		_videoLocalService.deleteVideoGalleryVideos(galleryId, videos);
	}

	@Override
	public void deleteVideoGalleryVideos(long galleryId, long[] videoIds) {
		_videoLocalService.deleteVideoGalleryVideos(galleryId, videoIds);
	}

	@Override
	public void setVideoGalleryVideos(long galleryId, long[] videoIds) {
		_videoLocalService.setVideoGalleryVideos(galleryId, videoIds);
	}

	/**
	* Met à jour le statut de la vidéo "manuellement" (pas via le workflow)
	*/
	@Override
	public void updateStatus(eu.strasbourg.service.video.model.Video video,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		_videoLocalService.updateStatus(video, status);
	}

	@Override
	public VideoLocalService getWrappedService() {
		return _videoLocalService;
	}

	@Override
	public void setWrappedService(VideoLocalService videoLocalService) {
		_videoLocalService = videoLocalService;
	}

	private VideoLocalService _videoLocalService;
}