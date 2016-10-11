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

import eu.strasbourg.service.video.model.VideoGallery;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the video gallery service. This utility wraps {@link eu.strasbourg.service.video.service.persistence.impl.VideoGalleryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see VideoGalleryPersistence
 * @see eu.strasbourg.service.video.service.persistence.impl.VideoGalleryPersistenceImpl
 * @generated
 */
@ProviderType
public class VideoGalleryUtil {
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
	public static void clearCache(VideoGallery videoGallery) {
		getPersistence().clearCache(videoGallery);
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
	public static List<VideoGallery> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VideoGallery> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VideoGallery> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VideoGallery update(VideoGallery videoGallery) {
		return getPersistence().update(videoGallery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VideoGallery update(VideoGallery videoGallery,
		ServiceContext serviceContext) {
		return getPersistence().update(videoGallery, serviceContext);
	}

	/**
	* Returns all the video galleries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching video galleries
	*/
	public static List<VideoGallery> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the video galleries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @return the range of matching video galleries
	*/
	public static List<VideoGallery> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the video galleries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching video galleries
	*/
	public static List<VideoGallery> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<VideoGallery> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the video galleries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching video galleries
	*/
	public static List<VideoGallery> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<VideoGallery> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first video gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public static VideoGallery findByUuid_First(java.lang.String uuid,
		OrderByComparator<VideoGallery> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoGalleryException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first video gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public static VideoGallery fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<VideoGallery> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last video gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public static VideoGallery findByUuid_Last(java.lang.String uuid,
		OrderByComparator<VideoGallery> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoGalleryException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last video gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public static VideoGallery fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<VideoGallery> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the video galleries before and after the current video gallery in the ordered set where uuid = &#63;.
	*
	* @param galleryId the primary key of the current video gallery
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next video gallery
	* @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	*/
	public static VideoGallery[] findByUuid_PrevAndNext(long galleryId,
		java.lang.String uuid, OrderByComparator<VideoGallery> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoGalleryException {
		return getPersistence()
				   .findByUuid_PrevAndNext(galleryId, uuid, orderByComparator);
	}

	/**
	* Removes all the video galleries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of video galleries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching video galleries
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the video gallery where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVideoGalleryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public static VideoGallery findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.video.exception.NoSuchVideoGalleryException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the video gallery where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public static VideoGallery fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the video gallery where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public static VideoGallery fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the video gallery where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the video gallery that was removed
	*/
	public static VideoGallery removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.video.exception.NoSuchVideoGalleryException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of video galleries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching video galleries
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the video galleries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching video galleries
	*/
	public static List<VideoGallery> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the video galleries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @return the range of matching video galleries
	*/
	public static List<VideoGallery> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the video galleries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching video galleries
	*/
	public static List<VideoGallery> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the video galleries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching video galleries
	*/
	public static List<VideoGallery> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public static VideoGallery findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<VideoGallery> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoGalleryException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public static VideoGallery fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<VideoGallery> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public static VideoGallery findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<VideoGallery> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoGalleryException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public static VideoGallery fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<VideoGallery> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the video galleries before and after the current video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param galleryId the primary key of the current video gallery
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next video gallery
	* @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	*/
	public static VideoGallery[] findByUuid_C_PrevAndNext(long galleryId,
		java.lang.String uuid, long companyId,
		OrderByComparator<VideoGallery> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoGalleryException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(galleryId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the video galleries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of video galleries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching video galleries
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the video galleries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching video galleries
	*/
	public static List<VideoGallery> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the video galleries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @return the range of matching video galleries
	*/
	public static List<VideoGallery> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the video galleries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching video galleries
	*/
	public static List<VideoGallery> findByGroupId(long groupId, int start,
		int end, OrderByComparator<VideoGallery> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the video galleries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching video galleries
	*/
	public static List<VideoGallery> findByGroupId(long groupId, int start,
		int end, OrderByComparator<VideoGallery> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first video gallery in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public static VideoGallery findByGroupId_First(long groupId,
		OrderByComparator<VideoGallery> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoGalleryException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first video gallery in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public static VideoGallery fetchByGroupId_First(long groupId,
		OrderByComparator<VideoGallery> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last video gallery in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public static VideoGallery findByGroupId_Last(long groupId,
		OrderByComparator<VideoGallery> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoGalleryException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last video gallery in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public static VideoGallery fetchByGroupId_Last(long groupId,
		OrderByComparator<VideoGallery> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the video galleries before and after the current video gallery in the ordered set where groupId = &#63;.
	*
	* @param galleryId the primary key of the current video gallery
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next video gallery
	* @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	*/
	public static VideoGallery[] findByGroupId_PrevAndNext(long galleryId,
		long groupId, OrderByComparator<VideoGallery> orderByComparator)
		throws eu.strasbourg.service.video.exception.NoSuchVideoGalleryException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(galleryId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the video galleries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of video galleries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching video galleries
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Caches the video gallery in the entity cache if it is enabled.
	*
	* @param videoGallery the video gallery
	*/
	public static void cacheResult(VideoGallery videoGallery) {
		getPersistence().cacheResult(videoGallery);
	}

	/**
	* Caches the video galleries in the entity cache if it is enabled.
	*
	* @param videoGalleries the video galleries
	*/
	public static void cacheResult(List<VideoGallery> videoGalleries) {
		getPersistence().cacheResult(videoGalleries);
	}

	/**
	* Creates a new video gallery with the primary key. Does not add the video gallery to the database.
	*
	* @param galleryId the primary key for the new video gallery
	* @return the new video gallery
	*/
	public static VideoGallery create(long galleryId) {
		return getPersistence().create(galleryId);
	}

	/**
	* Removes the video gallery with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param galleryId the primary key of the video gallery
	* @return the video gallery that was removed
	* @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	*/
	public static VideoGallery remove(long galleryId)
		throws eu.strasbourg.service.video.exception.NoSuchVideoGalleryException {
		return getPersistence().remove(galleryId);
	}

	public static VideoGallery updateImpl(VideoGallery videoGallery) {
		return getPersistence().updateImpl(videoGallery);
	}

	/**
	* Returns the video gallery with the primary key or throws a {@link NoSuchVideoGalleryException} if it could not be found.
	*
	* @param galleryId the primary key of the video gallery
	* @return the video gallery
	* @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	*/
	public static VideoGallery findByPrimaryKey(long galleryId)
		throws eu.strasbourg.service.video.exception.NoSuchVideoGalleryException {
		return getPersistence().findByPrimaryKey(galleryId);
	}

	/**
	* Returns the video gallery with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param galleryId the primary key of the video gallery
	* @return the video gallery, or <code>null</code> if a video gallery with the primary key could not be found
	*/
	public static VideoGallery fetchByPrimaryKey(long galleryId) {
		return getPersistence().fetchByPrimaryKey(galleryId);
	}

	public static java.util.Map<java.io.Serializable, VideoGallery> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the video galleries.
	*
	* @return the video galleries
	*/
	public static List<VideoGallery> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the video galleries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @return the range of video galleries
	*/
	public static List<VideoGallery> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the video galleries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of video galleries
	*/
	public static List<VideoGallery> findAll(int start, int end,
		OrderByComparator<VideoGallery> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the video galleries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of video galleries
	*/
	public static List<VideoGallery> findAll(int start, int end,
		OrderByComparator<VideoGallery> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the video galleries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of video galleries.
	*
	* @return the number of video galleries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of videos associated with the video gallery.
	*
	* @param pk the primary key of the video gallery
	* @return long[] of the primaryKeys of videos associated with the video gallery
	*/
	public static long[] getVideoPrimaryKeys(long pk) {
		return getPersistence().getVideoPrimaryKeys(pk);
	}

	/**
	* Returns all the videos associated with the video gallery.
	*
	* @param pk the primary key of the video gallery
	* @return the videos associated with the video gallery
	*/
	public static List<eu.strasbourg.service.video.model.Video> getVideos(
		long pk) {
		return getPersistence().getVideos(pk);
	}

	/**
	* Returns a range of all the videos associated with the video gallery.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the video gallery
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @return the range of videos associated with the video gallery
	*/
	public static List<eu.strasbourg.service.video.model.Video> getVideos(
		long pk, int start, int end) {
		return getPersistence().getVideos(pk, start, end);
	}

	/**
	* Returns an ordered range of all the videos associated with the video gallery.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the video gallery
	* @param start the lower bound of the range of video galleries
	* @param end the upper bound of the range of video galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of videos associated with the video gallery
	*/
	public static List<eu.strasbourg.service.video.model.Video> getVideos(
		long pk, int start, int end,
		OrderByComparator<eu.strasbourg.service.video.model.Video> orderByComparator) {
		return getPersistence().getVideos(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of videos associated with the video gallery.
	*
	* @param pk the primary key of the video gallery
	* @return the number of videos associated with the video gallery
	*/
	public static int getVideosSize(long pk) {
		return getPersistence().getVideosSize(pk);
	}

	/**
	* Returns <code>true</code> if the video is associated with the video gallery.
	*
	* @param pk the primary key of the video gallery
	* @param videoPK the primary key of the video
	* @return <code>true</code> if the video is associated with the video gallery; <code>false</code> otherwise
	*/
	public static boolean containsVideo(long pk, long videoPK) {
		return getPersistence().containsVideo(pk, videoPK);
	}

	/**
	* Returns <code>true</code> if the video gallery has any videos associated with it.
	*
	* @param pk the primary key of the video gallery to check for associations with videos
	* @return <code>true</code> if the video gallery has any videos associated with it; <code>false</code> otherwise
	*/
	public static boolean containsVideos(long pk) {
		return getPersistence().containsVideos(pk);
	}

	/**
	* Adds an association between the video gallery and the video. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videoPK the primary key of the video
	*/
	public static void addVideo(long pk, long videoPK) {
		getPersistence().addVideo(pk, videoPK);
	}

	/**
	* Adds an association between the video gallery and the video. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param video the video
	*/
	public static void addVideo(long pk,
		eu.strasbourg.service.video.model.Video video) {
		getPersistence().addVideo(pk, video);
	}

	/**
	* Adds an association between the video gallery and the videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videoPKs the primary keys of the videos
	*/
	public static void addVideos(long pk, long[] videoPKs) {
		getPersistence().addVideos(pk, videoPKs);
	}

	/**
	* Adds an association between the video gallery and the videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videos the videos
	*/
	public static void addVideos(long pk,
		List<eu.strasbourg.service.video.model.Video> videos) {
		getPersistence().addVideos(pk, videos);
	}

	/**
	* Clears all associations between the video gallery and its videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery to clear the associated videos from
	*/
	public static void clearVideos(long pk) {
		getPersistence().clearVideos(pk);
	}

	/**
	* Removes the association between the video gallery and the video. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videoPK the primary key of the video
	*/
	public static void removeVideo(long pk, long videoPK) {
		getPersistence().removeVideo(pk, videoPK);
	}

	/**
	* Removes the association between the video gallery and the video. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param video the video
	*/
	public static void removeVideo(long pk,
		eu.strasbourg.service.video.model.Video video) {
		getPersistence().removeVideo(pk, video);
	}

	/**
	* Removes the association between the video gallery and the videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videoPKs the primary keys of the videos
	*/
	public static void removeVideos(long pk, long[] videoPKs) {
		getPersistence().removeVideos(pk, videoPKs);
	}

	/**
	* Removes the association between the video gallery and the videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videos the videos
	*/
	public static void removeVideos(long pk,
		List<eu.strasbourg.service.video.model.Video> videos) {
		getPersistence().removeVideos(pk, videos);
	}

	/**
	* Sets the videos associated with the video gallery, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videoPKs the primary keys of the videos to be associated with the video gallery
	*/
	public static void setVideos(long pk, long[] videoPKs) {
		getPersistence().setVideos(pk, videoPKs);
	}

	/**
	* Sets the videos associated with the video gallery, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videos the videos to be associated with the video gallery
	*/
	public static void setVideos(long pk,
		List<eu.strasbourg.service.video.model.Video> videos) {
		getPersistence().setVideos(pk, videos);
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VideoGalleryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VideoGalleryPersistence, VideoGalleryPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VideoGalleryPersistence.class);
}