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

import eu.strasbourg.service.video.exception.NoSuchVideoGalleryException;
import eu.strasbourg.service.video.model.VideoGallery;

/**
 * The persistence interface for the video gallery service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.video.service.persistence.impl.VideoGalleryPersistenceImpl
 * @see VideoGalleryUtil
 * @generated
 */
@ProviderType
public interface VideoGalleryPersistence extends BasePersistence<VideoGallery> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VideoGalleryUtil} to access the video gallery persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the video galleries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching video galleries
	*/
	public java.util.List<VideoGallery> findByUuid(java.lang.String uuid);

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
	public java.util.List<VideoGallery> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<VideoGallery> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator);

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
	public java.util.List<VideoGallery> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first video gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public VideoGallery findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException;

	/**
	* Returns the first video gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public VideoGallery fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator);

	/**
	* Returns the last video gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public VideoGallery findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException;

	/**
	* Returns the last video gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public VideoGallery fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator);

	/**
	* Returns the video galleries before and after the current video gallery in the ordered set where uuid = &#63;.
	*
	* @param galleryId the primary key of the current video gallery
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next video gallery
	* @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	*/
	public VideoGallery[] findByUuid_PrevAndNext(long galleryId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException;

	/**
	* Removes all the video galleries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of video galleries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching video galleries
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the video gallery where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVideoGalleryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public VideoGallery findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchVideoGalleryException;

	/**
	* Returns the video gallery where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public VideoGallery fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the video gallery where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public VideoGallery fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the video gallery where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the video gallery that was removed
	*/
	public VideoGallery removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchVideoGalleryException;

	/**
	* Returns the number of video galleries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching video galleries
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the video galleries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching video galleries
	*/
	public java.util.List<VideoGallery> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<VideoGallery> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<VideoGallery> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator);

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
	public java.util.List<VideoGallery> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public VideoGallery findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException;

	/**
	* Returns the first video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public VideoGallery fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator);

	/**
	* Returns the last video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public VideoGallery findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException;

	/**
	* Returns the last video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public VideoGallery fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator);

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
	public VideoGallery[] findByUuid_C_PrevAndNext(long galleryId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException;

	/**
	* Removes all the video galleries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of video galleries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching video galleries
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the video galleries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching video galleries
	*/
	public java.util.List<VideoGallery> findByGroupId(long groupId);

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
	public java.util.List<VideoGallery> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<VideoGallery> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator);

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
	public java.util.List<VideoGallery> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first video gallery in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public VideoGallery findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException;

	/**
	* Returns the first video gallery in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public VideoGallery fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator);

	/**
	* Returns the last video gallery in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video gallery
	* @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	*/
	public VideoGallery findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException;

	/**
	* Returns the last video gallery in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching video gallery, or <code>null</code> if a matching video gallery could not be found
	*/
	public VideoGallery fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator);

	/**
	* Returns the video galleries before and after the current video gallery in the ordered set where groupId = &#63;.
	*
	* @param galleryId the primary key of the current video gallery
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next video gallery
	* @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	*/
	public VideoGallery[] findByGroupId_PrevAndNext(long galleryId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException;

	/**
	* Removes all the video galleries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of video galleries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching video galleries
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the video gallery in the entity cache if it is enabled.
	*
	* @param videoGallery the video gallery
	*/
	public void cacheResult(VideoGallery videoGallery);

	/**
	* Caches the video galleries in the entity cache if it is enabled.
	*
	* @param videoGalleries the video galleries
	*/
	public void cacheResult(java.util.List<VideoGallery> videoGalleries);

	/**
	* Creates a new video gallery with the primary key. Does not add the video gallery to the database.
	*
	* @param galleryId the primary key for the new video gallery
	* @return the new video gallery
	*/
	public VideoGallery create(long galleryId);

	/**
	* Removes the video gallery with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param galleryId the primary key of the video gallery
	* @return the video gallery that was removed
	* @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	*/
	public VideoGallery remove(long galleryId)
		throws NoSuchVideoGalleryException;

	public VideoGallery updateImpl(VideoGallery videoGallery);

	/**
	* Returns the video gallery with the primary key or throws a {@link NoSuchVideoGalleryException} if it could not be found.
	*
	* @param galleryId the primary key of the video gallery
	* @return the video gallery
	* @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	*/
	public VideoGallery findByPrimaryKey(long galleryId)
		throws NoSuchVideoGalleryException;

	/**
	* Returns the video gallery with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param galleryId the primary key of the video gallery
	* @return the video gallery, or <code>null</code> if a video gallery with the primary key could not be found
	*/
	public VideoGallery fetchByPrimaryKey(long galleryId);

	@Override
	public java.util.Map<java.io.Serializable, VideoGallery> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the video galleries.
	*
	* @return the video galleries
	*/
	public java.util.List<VideoGallery> findAll();

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
	public java.util.List<VideoGallery> findAll(int start, int end);

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
	public java.util.List<VideoGallery> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator);

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
	public java.util.List<VideoGallery> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VideoGallery> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the video galleries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of video galleries.
	*
	* @return the number of video galleries
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of videos associated with the video gallery.
	*
	* @param pk the primary key of the video gallery
	* @return long[] of the primaryKeys of videos associated with the video gallery
	*/
	public long[] getVideoPrimaryKeys(long pk);

	/**
	* Returns all the videos associated with the video gallery.
	*
	* @param pk the primary key of the video gallery
	* @return the videos associated with the video gallery
	*/
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideos(
		long pk);

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
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideos(
		long pk, int start, int end);

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
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideos(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.video.model.Video> orderByComparator);

	/**
	* Returns the number of videos associated with the video gallery.
	*
	* @param pk the primary key of the video gallery
	* @return the number of videos associated with the video gallery
	*/
	public int getVideosSize(long pk);

	/**
	* Returns <code>true</code> if the video is associated with the video gallery.
	*
	* @param pk the primary key of the video gallery
	* @param videoPK the primary key of the video
	* @return <code>true</code> if the video is associated with the video gallery; <code>false</code> otherwise
	*/
	public boolean containsVideo(long pk, long videoPK);

	/**
	* Returns <code>true</code> if the video gallery has any videos associated with it.
	*
	* @param pk the primary key of the video gallery to check for associations with videos
	* @return <code>true</code> if the video gallery has any videos associated with it; <code>false</code> otherwise
	*/
	public boolean containsVideos(long pk);

	/**
	* Adds an association between the video gallery and the video. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videoPK the primary key of the video
	*/
	public void addVideo(long pk, long videoPK);

	/**
	* Adds an association between the video gallery and the video. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param video the video
	*/
	public void addVideo(long pk, eu.strasbourg.service.video.model.Video video);

	/**
	* Adds an association between the video gallery and the videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videoPKs the primary keys of the videos
	*/
	public void addVideos(long pk, long[] videoPKs);

	/**
	* Adds an association between the video gallery and the videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videos the videos
	*/
	public void addVideos(long pk,
		java.util.List<eu.strasbourg.service.video.model.Video> videos);

	/**
	* Clears all associations between the video gallery and its videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery to clear the associated videos from
	*/
	public void clearVideos(long pk);

	/**
	* Removes the association between the video gallery and the video. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videoPK the primary key of the video
	*/
	public void removeVideo(long pk, long videoPK);

	/**
	* Removes the association between the video gallery and the video. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param video the video
	*/
	public void removeVideo(long pk,
		eu.strasbourg.service.video.model.Video video);

	/**
	* Removes the association between the video gallery and the videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videoPKs the primary keys of the videos
	*/
	public void removeVideos(long pk, long[] videoPKs);

	/**
	* Removes the association between the video gallery and the videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videos the videos
	*/
	public void removeVideos(long pk,
		java.util.List<eu.strasbourg.service.video.model.Video> videos);

	/**
	* Sets the videos associated with the video gallery, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videoPKs the primary keys of the videos to be associated with the video gallery
	*/
	public void setVideos(long pk, long[] videoPKs);

	/**
	* Sets the videos associated with the video gallery, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the video gallery
	* @param videos the videos to be associated with the video gallery
	*/
	public void setVideos(long pk,
		java.util.List<eu.strasbourg.service.video.model.Video> videos);

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}