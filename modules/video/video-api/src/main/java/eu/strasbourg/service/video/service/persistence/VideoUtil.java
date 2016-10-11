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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.video.model.Video;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the video service. This utility wraps {@link eu.strasbourg.service.video.service.persistence.impl.VideoPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see VideoPersistence
 * @see eu.strasbourg.service.video.service.persistence.impl.VideoPersistenceImpl
 * @generated
 */
@ProviderType
public class VideoUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Video video) {
		getPersistence().clearCache(video);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Video> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Video> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Video> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Video> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Video update(Video video) {
		return getPersistence().update(video);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Video update(Video video, ServiceContext serviceContext) {
		return getPersistence().update(video, serviceContext);
	}

	/**
	* Returns all the videos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching videos
	*/
	public static List<Video> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<Video> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<Video> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Video> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<Video> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Video> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first video in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public static Video findByUuid_First(java.lang.String uuid,
		OrderByComparator<Video> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first video in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video, or <code>null</code> if a matching video could not be found
	*/
	public static Video fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Video> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last video in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public static Video findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Video> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last video in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video, or <code>null</code> if a matching video could not be found
	*/
	public static Video fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Video> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the videos before and after the current video in the ordered set where uuid = &#63;.
	*
	* @param videoId the primary key of the current video
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next video
	* @throws NoSuchVideoException if a video with the primary key could not be found
	*/
	public static Video[] findByUuid_PrevAndNext(long videoId,
		java.lang.String uuid, OrderByComparator<Video> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoException {
		return getPersistence()
				   .findByUuid_PrevAndNext(videoId, uuid, orderByComparator);
	}

	/**
	* Removes all the videos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of videos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching videos
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the video where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVideoException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public static Video findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.video.exception.NoSuchVideoException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the video where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching video, or <code>null</code> if a matching video could not be found
	*/
	public static Video fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the video where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching video, or <code>null</code> if a matching video could not be found
	*/
	public static Video fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the video where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the video that was removed
	*/
	public static Video removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.video.exception.NoSuchVideoException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of videos where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching videos
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the videos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching videos
	*/
	public static List<Video> findByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<Video> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<Video> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Video> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<Video> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Video> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first video in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public static Video findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Video> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first video in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video, or <code>null</code> if a matching video could not be found
	*/
	public static Video fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Video> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last video in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public static Video findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Video> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last video in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video, or <code>null</code> if a matching video could not be found
	*/
	public static Video fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Video> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static Video[] findByUuid_C_PrevAndNext(long videoId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Video> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(videoId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the videos where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of videos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching videos
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the videos where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching videos
	*/
	public static List<Video> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

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
	public static List<Video> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

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
	public static List<Video> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Video> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

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
	public static List<Video> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Video> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first video in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public static Video findByGroupId_First(long groupId,
		OrderByComparator<Video> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first video in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video, or <code>null</code> if a matching video could not be found
	*/
	public static Video fetchByGroupId_First(long groupId,
		OrderByComparator<Video> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last video in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video
	* @throws NoSuchVideoException if a matching video could not be found
	*/
	public static Video findByGroupId_Last(long groupId,
		OrderByComparator<Video> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last video in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video, or <code>null</code> if a matching video could not be found
	*/
	public static Video fetchByGroupId_Last(long groupId,
		OrderByComparator<Video> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the videos before and after the current video in the ordered set where groupId = &#63;.
	*
	* @param videoId the primary key of the current video
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next video
	* @throws NoSuchVideoException if a video with the primary key could not be found
	*/
	public static Video[] findByGroupId_PrevAndNext(long videoId, long groupId,
		OrderByComparator<Video> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(videoId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the videos where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of videos where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching videos
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Caches the video in the entity cache if it is enabled.
	*
	* @param video the video
	*/
	public static void cacheResult(Video video) {
		getPersistence().cacheResult(video);
	}

	/**
	* Caches the videos in the entity cache if it is enabled.
	*
	* @param videos the videos
	*/
	public static void cacheResult(List<Video> videos) {
		getPersistence().cacheResult(videos);
	}

	/**
	* Creates a new video with the primary key. Does not add the video to the database.
	*
	* @param videoId the primary key for the new video
	* @return the new video
	*/
	public static Video create(long videoId) {
		return getPersistence().create(videoId);
	}

	/**
	* Removes the video with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param videoId the primary key of the video
	* @return the video that was removed
	* @throws NoSuchVideoException if a video with the primary key could not be found
	*/
	public static Video remove(long videoId)
		throws eu.strasbourg.service.video.exception.NoSuchVideoException {
		return getPersistence().remove(videoId);
	}

	public static Video updateImpl(Video video) {
		return getPersistence().updateImpl(video);
	}

	/**
	* Returns the video with the primary key or throws a {@link NoSuchVideoException} if it could not be found.
	*
	* @param videoId the primary key of the video
	* @return the video
	* @throws NoSuchVideoException if a video with the primary key could not be found
	*/
	public static Video findByPrimaryKey(long videoId)
		throws eu.strasbourg.service.video.exception.NoSuchVideoException {
		return getPersistence().findByPrimaryKey(videoId);
	}

	/**
	* Returns the video with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param videoId the primary key of the video
	* @return the video, or <code>null</code> if a video with the primary key could not be found
	*/
	public static Video fetchByPrimaryKey(long videoId) {
		return getPersistence().fetchByPrimaryKey(videoId);
	}

	public static java.util.Map<java.io.Serializable, Video> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the videos.
	*
	* @return the videos
	*/
	public static List<Video> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Video> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Video> findAll(int start, int end,
		OrderByComparator<Video> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Video> findAll(int start, int end,
		OrderByComparator<Video> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the videos from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of videos.
	*
	* @return the number of videos
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of video galleries associated with the video.
	*
	* @param pk the primary key of the video
	* @return long[] of the primaryKeys of video galleries associated with the video
	*/
	public static long[] getVideoGalleryPrimaryKeys(long pk) {
		return getPersistence().getVideoGalleryPrimaryKeys(pk);
	}

	/**
	* Returns all the video galleries associated with the video.
	*
	* @param pk the primary key of the video
	* @return the video galleries associated with the video
	*/
	public static List<eu.strasbourg.service.video.model.VideoGallery> getVideoGalleries(
		long pk) {
		return getPersistence().getVideoGalleries(pk);
	}

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
	public static List<eu.strasbourg.service.video.model.VideoGallery> getVideoGalleries(
		long pk, int start, int end) {
		return getPersistence().getVideoGalleries(pk, start, end);
	}

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
	public static List<eu.strasbourg.service.video.model.VideoGallery> getVideoGalleries(
		long pk, int start, int end,
		OrderByComparator<eu.strasbourg.service.video.model.VideoGallery> orderByComparator) {
		return getPersistence()
				   .getVideoGalleries(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of video galleries associated with the video.
	*
	* @param pk the primary key of the video
	* @return the number of video galleries associated with the video
	*/
	public static int getVideoGalleriesSize(long pk) {
		return getPersistence().getVideoGalleriesSize(pk);
	}

	/**
	* Returns <code>true</code> if the video gallery is associated with the video.
	*
	* @param pk the primary key of the video
	* @param videoGalleryPK the primary key of the video gallery
	* @return <code>true</code> if the video gallery is associated with the video; <code>false</code> otherwise
	*/
	public static boolean containsVideoGallery(long pk, long videoGalleryPK) {
		return getPersistence().containsVideoGallery(pk, videoGalleryPK);
	}

	/**
	* Returns <code>true</code> if the video has any video galleries associated with it.
	*
	* @param pk the primary key of the video to check for associations with video galleries
	* @return <code>true</code> if the video has any video galleries associated with it; <code>false</code> otherwise
	*/
	public static boolean containsVideoGalleries(long pk) {
		return getPersistence().containsVideoGalleries(pk);
	}

	/**
	* Adds an association between the video and the video gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleryPK the primary key of the video gallery
	*/
	public static void addVideoGallery(long pk, long videoGalleryPK) {
		getPersistence().addVideoGallery(pk, videoGalleryPK);
	}

	/**
	* Adds an association between the video and the video gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGallery the video gallery
	*/
	public static void addVideoGallery(long pk,
		eu.strasbourg.service.video.model.VideoGallery videoGallery) {
		getPersistence().addVideoGallery(pk, videoGallery);
	}

	/**
	* Adds an association between the video and the video galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleryPKs the primary keys of the video galleries
	*/
	public static void addVideoGalleries(long pk, long[] videoGalleryPKs) {
		getPersistence().addVideoGalleries(pk, videoGalleryPKs);
	}

	/**
	* Adds an association between the video and the video galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleries the video galleries
	*/
	public static void addVideoGalleries(long pk,
		List<eu.strasbourg.service.video.model.VideoGallery> videoGalleries) {
		getPersistence().addVideoGalleries(pk, videoGalleries);
	}

	/**
	* Clears all associations between the video and its video galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video to clear the associated video galleries from
	*/
	public static void clearVideoGalleries(long pk) {
		getPersistence().clearVideoGalleries(pk);
	}

	/**
	* Removes the association between the video and the video gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleryPK the primary key of the video gallery
	*/
	public static void removeVideoGallery(long pk, long videoGalleryPK) {
		getPersistence().removeVideoGallery(pk, videoGalleryPK);
	}

	/**
	* Removes the association between the video and the video gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGallery the video gallery
	*/
	public static void removeVideoGallery(long pk,
		eu.strasbourg.service.video.model.VideoGallery videoGallery) {
		getPersistence().removeVideoGallery(pk, videoGallery);
	}

	/**
	* Removes the association between the video and the video galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleryPKs the primary keys of the video galleries
	*/
	public static void removeVideoGalleries(long pk, long[] videoGalleryPKs) {
		getPersistence().removeVideoGalleries(pk, videoGalleryPKs);
	}

	/**
	* Removes the association between the video and the video galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleries the video galleries
	*/
	public static void removeVideoGalleries(long pk,
		List<eu.strasbourg.service.video.model.VideoGallery> videoGalleries) {
		getPersistence().removeVideoGalleries(pk, videoGalleries);
	}

	/**
	* Sets the video galleries associated with the video, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleryPKs the primary keys of the video galleries to be associated with the video
	*/
	public static void setVideoGalleries(long pk, long[] videoGalleryPKs) {
		getPersistence().setVideoGalleries(pk, videoGalleryPKs);
	}

	/**
	* Sets the video galleries associated with the video, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video
	* @param videoGalleries the video galleries to be associated with the video
	*/
	public static void setVideoGalleries(long pk,
		List<eu.strasbourg.service.video.model.VideoGallery> videoGalleries) {
		getPersistence().setVideoGalleries(pk, videoGalleries);
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VideoPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VideoPersistence, VideoPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VideoPersistence.class);
}