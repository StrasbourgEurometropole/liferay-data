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

package eu.strasbourg.service.video.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.video.exception.NoSuchVideoException;
import eu.strasbourg.service.video.model.Video;

/**
 * The persistence interface for the video service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.video.service.persistence.impl.VideoPersistenceImpl
 * @see VideoUtil
 * @generated
 */
@ProviderType
public interface VideoPersistence extends BasePersistence<Video> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VideoUtil} to access the video persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the videos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching videos
	*/
	public java.util.List<Video> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the videos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @return the range of matching videos
	*/
	public java.util.List<Video> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the videos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching videos
	*/
	public java.util.List<Video> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator);

	/**
	* Returns an ordered range of all the videos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching videos
	*/
	public java.util.List<Video> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first video in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public Video findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator)
		throws NoSuchVideoException;

	/**
	* Returns the first video in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video, or <code>null</code> if a matching video could not be found
	*/
	public Video fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator);

	/**
	* Returns the last video in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public Video findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator)
		throws NoSuchVideoException;

	/**
	* Returns the last video in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video, or <code>null</code> if a matching video could not be found
	*/
	public Video fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator);

	/**
	* Returns the videos before and after the current video in the ordered set where uuid = &#63;.
	*
	* @param videoId the primary key of the current video
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next video
	* @throws NoSuchVideoException if a video with the primary key could not be found
	*/
	public Video[] findByUuid_PrevAndNext(long videoId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator)
		throws NoSuchVideoException;

	/**
	* Removes all the videos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of videos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching videos
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the video where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVideoException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public Video findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchVideoException;

	/**
	* Returns the video where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching video, or <code>null</code> if a matching video could not be found
	*/
	public Video fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the video where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching video, or <code>null</code> if a matching video could not be found
	*/
	public Video fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the video where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the video that was removed
	*/
	public Video removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchVideoException;

	/**
	* Returns the number of videos where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching videos
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the videos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching videos
	*/
	public java.util.List<Video> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the videos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @return the range of matching videos
	*/
	public java.util.List<Video> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the videos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching videos
	*/
	public java.util.List<Video> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator);

	/**
	* Returns an ordered range of all the videos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching videos
	*/
	public java.util.List<Video> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first video in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public Video findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator)
		throws NoSuchVideoException;

	/**
	* Returns the first video in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video, or <code>null</code> if a matching video could not be found
	*/
	public Video fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator);

	/**
	* Returns the last video in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public Video findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator)
		throws NoSuchVideoException;

	/**
	* Returns the last video in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video, or <code>null</code> if a matching video could not be found
	*/
	public Video fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator);

	/**
	* Returns the videos before and after the current video in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param videoId the primary key of the current video
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next video
	* @throws NoSuchVideoException if a video with the primary key could not be found
	*/
	public Video[] findByUuid_C_PrevAndNext(long videoId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator)
		throws NoSuchVideoException;

	/**
	* Removes all the videos where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of videos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching videos
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the videos where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching videos
	*/
	public java.util.List<Video> findByGroupId(long groupId);

	/**
	* Returns a range of all the videos where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @return the range of matching videos
	*/
	public java.util.List<Video> findByGroupId(long groupId, int start, int end);

	/**
	* Returns an ordered range of all the videos where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching videos
	*/
	public java.util.List<Video> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator);

	/**
	* Returns an ordered range of all the videos where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching videos
	*/
	public java.util.List<Video> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first video in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public Video findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator)
		throws NoSuchVideoException;

	/**
	* Returns the first video in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video, or <code>null</code> if a matching video could not be found
	*/
	public Video fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator);

	/**
	* Returns the last video in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public Video findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator)
		throws NoSuchVideoException;

	/**
	* Returns the last video in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video, or <code>null</code> if a matching video could not be found
	*/
	public Video fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator);

	/**
	* Returns the videos before and after the current video in the ordered set where groupId = &#63;.
	*
	* @param videoId the primary key of the current video
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next video
	* @throws NoSuchVideoException if a video with the primary key could not be found
	*/
	public Video[] findByGroupId_PrevAndNext(long videoId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator)
		throws NoSuchVideoException;

	/**
	* Removes all the videos where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of videos where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching videos
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the video in the entity cache if it is enabled.
	*
	* @param video the video
	*/
	public void cacheResult(Video video);

	/**
	* Caches the videos in the entity cache if it is enabled.
	*
	* @param videos the videos
	*/
	public void cacheResult(java.util.List<Video> videos);

	/**
	* Creates a new video with the primary key. Does not add the video to the database.
	*
	* @param videoId the primary key for the new video
	* @return the new video
	*/
	public Video create(long videoId);

	/**
	* Removes the video with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param videoId the primary key of the video
	* @return the video that was removed
	* @throws NoSuchVideoException if a video with the primary key could not be found
	*/
	public Video remove(long videoId) throws NoSuchVideoException;

	public Video updateImpl(Video video);

	/**
	* Returns the video with the primary key or throws a {@link NoSuchVideoException} if it could not be found.
	*
	* @param videoId the primary key of the video
	* @return the video
	* @throws NoSuchVideoException if a video with the primary key could not be found
	*/
	public Video findByPrimaryKey(long videoId) throws NoSuchVideoException;

	/**
	* Returns the video with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param videoId the primary key of the video
	* @return the video, or <code>null</code> if a video with the primary key could not be found
	*/
	public Video fetchByPrimaryKey(long videoId);

	@Override
	public java.util.Map<java.io.Serializable, Video> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the videos.
	*
	* @return the videos
	*/
	public java.util.List<Video> findAll();

	/**
	* Returns a range of all the videos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @return the range of videos
	*/
	public java.util.List<Video> findAll(int start, int end);

	/**
	* Returns an ordered range of all the videos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of videos
	*/
	public java.util.List<Video> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator);

	/**
	* Returns an ordered range of all the videos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of videos
	*/
	public java.util.List<Video> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Video> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the videos from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of videos.
	*
	* @return the number of videos
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of video galleries associated with the video.
	*
	* @param pk the primary key of the video
	* @return long[] of the primaryKeys of video galleries associated with the video
	*/
	public long[] getVideoGalleryPrimaryKeys(long pk);

	/**
	* Returns all the video galleries associated with the video.
	*
	* @param pk the primary key of the video
	* @return the video galleries associated with the video
	*/
	public java.util.List<eu.strasbourg.service.video.model.VideoGallery> getVideoGalleries(
		long pk);

	/**
	* Returns a range of all the video galleries associated with the video.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the video
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @return the range of video galleries associated with the video
	*/
	public java.util.List<eu.strasbourg.service.video.model.VideoGallery> getVideoGalleries(
		long pk, int start, int end);

	/**
	* Returns an ordered range of all the video galleries associated with the video.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the video
	* @param start the lower bound of the range of videos
	* @param end the upper bound of the range of videos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of video galleries associated with the video
	*/
	public java.util.List<eu.strasbourg.service.video.model.VideoGallery> getVideoGalleries(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.video.model.VideoGallery> orderByComparator);

	/**
	* Returns the number of video galleries associated with the video.
	*
	* @param pk the primary key of the video
	* @return the number of video galleries associated with the video
	*/
	public int getVideoGalleriesSize(long pk);

	/**
	* Returns <code>true</code> if the video gallery is associated with the video.
	*
	* @param pk the primary key of the video
	* @param videoGalleryPK the primary key of the video gallery
	* @return <code>true</code> if the video gallery is associated with the video; <code>false</code> otherwise
	*/
	public boolean containsVideoGallery(long pk, long videoGalleryPK);

	/**
	* Returns <code>true</code> if the video has any video galleries associated with it.
	*
	* @param pk the primary key of the video to check for associations with video galleries
	* @return <code>true</code> if the video has any video galleries associated with it; <code>false</code> otherwise
	*/
	public boolean containsVideoGalleries(long pk);

	/**
	* Adds an association between the video and the video gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleryPK the primary key of the video gallery
	*/
	public void addVideoGallery(long pk, long videoGalleryPK);

	/**
	* Adds an association between the video and the video gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGallery the video gallery
	*/
	public void addVideoGallery(long pk,
		eu.strasbourg.service.video.model.VideoGallery videoGallery);

	/**
	* Adds an association between the video and the video galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleryPKs the primary keys of the video galleries
	*/
	public void addVideoGalleries(long pk, long[] videoGalleryPKs);

	/**
	* Adds an association between the video and the video galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleries the video galleries
	*/
	public void addVideoGalleries(long pk,
		java.util.List<eu.strasbourg.service.video.model.VideoGallery> videoGalleries);

	/**
	* Clears all associations between the video and its video galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video to clear the associated video galleries from
	*/
	public void clearVideoGalleries(long pk);

	/**
	* Removes the association between the video and the video gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleryPK the primary key of the video gallery
	*/
	public void removeVideoGallery(long pk, long videoGalleryPK);

	/**
	* Removes the association between the video and the video gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGallery the video gallery
	*/
	public void removeVideoGallery(long pk,
		eu.strasbourg.service.video.model.VideoGallery videoGallery);

	/**
	* Removes the association between the video and the video galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleryPKs the primary keys of the video galleries
	*/
	public void removeVideoGalleries(long pk, long[] videoGalleryPKs);

	/**
	* Removes the association between the video and the video galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleries the video galleries
	*/
	public void removeVideoGalleries(long pk,
		java.util.List<eu.strasbourg.service.video.model.VideoGallery> videoGalleries);

	/**
	* Sets the video galleries associated with the video, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleryPKs the primary keys of the video galleries to be associated with the video
	*/
	public void setVideoGalleries(long pk, long[] videoGalleryPKs);

	/**
	* Sets the video galleries associated with the video, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleries the video galleries to be associated with the video
	*/
	public void setVideoGalleries(long pk,
		java.util.List<eu.strasbourg.service.video.model.VideoGallery> videoGalleries);

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}