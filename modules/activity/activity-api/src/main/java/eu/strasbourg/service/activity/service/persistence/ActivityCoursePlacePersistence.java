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

package eu.strasbourg.service.activity.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.activity.exception.NoSuchActivityCoursePlaceException;
import eu.strasbourg.service.activity.model.ActivityCoursePlace;

/**
 * The persistence interface for the activity course place service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.activity.service.persistence.impl.ActivityCoursePlacePersistenceImpl
 * @see ActivityCoursePlaceUtil
 * @generated
 */
@ProviderType
public interface ActivityCoursePlacePersistence extends BasePersistence<ActivityCoursePlace> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ActivityCoursePlaceUtil} to access the activity course place persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the activity course places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the activity course places where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @return the range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByUuid(
		java.lang.String uuid, int start, int end);

	/**
	* Returns an ordered range of all the activity course places where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns an ordered range of all the activity course places where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course place
	* @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	*/
	public ActivityCoursePlace findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Returns the first activity course place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course place, or <code>null</code> if a matching activity course place could not be found
	*/
	public ActivityCoursePlace fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns the last activity course place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course place
	* @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	*/
	public ActivityCoursePlace findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Returns the last activity course place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course place, or <code>null</code> if a matching activity course place could not be found
	*/
	public ActivityCoursePlace fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns the activity course places before and after the current activity course place in the ordered set where uuid = &#63;.
	*
	* @param activityCoursePlaceId the primary key of the current activity course place
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course place
	* @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	*/
	public ActivityCoursePlace[] findByUuid_PrevAndNext(
		long activityCoursePlaceId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Removes all the activity course places where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of activity course places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching activity course places
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the activity course place where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchActivityCoursePlaceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching activity course place
	* @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	*/
	public ActivityCoursePlace findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Returns the activity course place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching activity course place, or <code>null</code> if a matching activity course place could not be found
	*/
	public ActivityCoursePlace fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the activity course place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching activity course place, or <code>null</code> if a matching activity course place could not be found
	*/
	public ActivityCoursePlace fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the activity course place where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the activity course place that was removed
	*/
	public ActivityCoursePlace removeByUUID_G(java.lang.String uuid,
		long groupId) throws NoSuchActivityCoursePlaceException;

	/**
	* Returns the number of activity course places where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching activity course places
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the activity course places where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByUuid_C(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of all the activity course places where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @return the range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the activity course places where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns an ordered range of all the activity course places where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course place
	* @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	*/
	public ActivityCoursePlace findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Returns the first activity course place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course place, or <code>null</code> if a matching activity course place could not be found
	*/
	public ActivityCoursePlace fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns the last activity course place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course place
	* @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	*/
	public ActivityCoursePlace findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Returns the last activity course place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course place, or <code>null</code> if a matching activity course place could not be found
	*/
	public ActivityCoursePlace fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns the activity course places before and after the current activity course place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param activityCoursePlaceId the primary key of the current activity course place
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course place
	* @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	*/
	public ActivityCoursePlace[] findByUuid_C_PrevAndNext(
		long activityCoursePlaceId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Removes all the activity course places where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of activity course places where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching activity course places
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the activity course places where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByGroupId(long groupId);

	/**
	* Returns a range of all the activity course places where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @return the range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the activity course places where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns an ordered range of all the activity course places where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course place
	* @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	*/
	public ActivityCoursePlace findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Returns the first activity course place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course place, or <code>null</code> if a matching activity course place could not be found
	*/
	public ActivityCoursePlace fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns the last activity course place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course place
	* @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	*/
	public ActivityCoursePlace findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Returns the last activity course place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course place, or <code>null</code> if a matching activity course place could not be found
	*/
	public ActivityCoursePlace fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns the activity course places before and after the current activity course place in the ordered set where groupId = &#63;.
	*
	* @param activityCoursePlaceId the primary key of the current activity course place
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course place
	* @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	*/
	public ActivityCoursePlace[] findByGroupId_PrevAndNext(
		long activityCoursePlaceId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Removes all the activity course places where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of activity course places where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching activity course places
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the activity course places where activityCourseId = &#63;.
	*
	* @param activityCourseId the activity course ID
	* @return the matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByActivityCourse(
		long activityCourseId);

	/**
	* Returns a range of all the activity course places where activityCourseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param activityCourseId the activity course ID
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @return the range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByActivityCourse(
		long activityCourseId, int start, int end);

	/**
	* Returns an ordered range of all the activity course places where activityCourseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param activityCourseId the activity course ID
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByActivityCourse(
		long activityCourseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns an ordered range of all the activity course places where activityCourseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param activityCourseId the activity course ID
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findByActivityCourse(
		long activityCourseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course place in the ordered set where activityCourseId = &#63;.
	*
	* @param activityCourseId the activity course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course place
	* @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	*/
	public ActivityCoursePlace findByActivityCourse_First(
		long activityCourseId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Returns the first activity course place in the ordered set where activityCourseId = &#63;.
	*
	* @param activityCourseId the activity course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course place, or <code>null</code> if a matching activity course place could not be found
	*/
	public ActivityCoursePlace fetchByActivityCourse_First(
		long activityCourseId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns the last activity course place in the ordered set where activityCourseId = &#63;.
	*
	* @param activityCourseId the activity course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course place
	* @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	*/
	public ActivityCoursePlace findByActivityCourse_Last(
		long activityCourseId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Returns the last activity course place in the ordered set where activityCourseId = &#63;.
	*
	* @param activityCourseId the activity course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course place, or <code>null</code> if a matching activity course place could not be found
	*/
	public ActivityCoursePlace fetchByActivityCourse_Last(
		long activityCourseId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns the activity course places before and after the current activity course place in the ordered set where activityCourseId = &#63;.
	*
	* @param activityCoursePlaceId the primary key of the current activity course place
	* @param activityCourseId the activity course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course place
	* @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	*/
	public ActivityCoursePlace[] findByActivityCourse_PrevAndNext(
		long activityCoursePlaceId, long activityCourseId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Removes all the activity course places where activityCourseId = &#63; from the database.
	*
	* @param activityCourseId the activity course ID
	*/
	public void removeByActivityCourse(long activityCourseId);

	/**
	* Returns the number of activity course places where activityCourseId = &#63;.
	*
	* @param activityCourseId the activity course ID
	* @return the number of matching activity course places
	*/
	public int countByActivityCourse(long activityCourseId);

	/**
	* Returns all the activity course places where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @return the matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findBySigId(
		java.lang.String placeSIGId);

	/**
	* Returns a range of all the activity course places where placeSIGId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeSIGId the place sig ID
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @return the range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findBySigId(
		java.lang.String placeSIGId, int start, int end);

	/**
	* Returns an ordered range of all the activity course places where placeSIGId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeSIGId the place sig ID
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findBySigId(
		java.lang.String placeSIGId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns an ordered range of all the activity course places where placeSIGId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeSIGId the place sig ID
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity course places
	*/
	public java.util.List<ActivityCoursePlace> findBySigId(
		java.lang.String placeSIGId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course place in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course place
	* @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	*/
	public ActivityCoursePlace findBySigId_First(java.lang.String placeSIGId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Returns the first activity course place in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course place, or <code>null</code> if a matching activity course place could not be found
	*/
	public ActivityCoursePlace fetchBySigId_First(java.lang.String placeSIGId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns the last activity course place in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course place
	* @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	*/
	public ActivityCoursePlace findBySigId_Last(java.lang.String placeSIGId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Returns the last activity course place in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course place, or <code>null</code> if a matching activity course place could not be found
	*/
	public ActivityCoursePlace fetchBySigId_Last(java.lang.String placeSIGId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns the activity course places before and after the current activity course place in the ordered set where placeSIGId = &#63;.
	*
	* @param activityCoursePlaceId the primary key of the current activity course place
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course place
	* @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	*/
	public ActivityCoursePlace[] findBySigId_PrevAndNext(
		long activityCoursePlaceId, java.lang.String placeSIGId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Removes all the activity course places where placeSIGId = &#63; from the database.
	*
	* @param placeSIGId the place sig ID
	*/
	public void removeBySigId(java.lang.String placeSIGId);

	/**
	* Returns the number of activity course places where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @return the number of matching activity course places
	*/
	public int countBySigId(java.lang.String placeSIGId);

	/**
	* Caches the activity course place in the entity cache if it is enabled.
	*
	* @param activityCoursePlace the activity course place
	*/
	public void cacheResult(ActivityCoursePlace activityCoursePlace);

	/**
	* Caches the activity course places in the entity cache if it is enabled.
	*
	* @param activityCoursePlaces the activity course places
	*/
	public void cacheResult(
		java.util.List<ActivityCoursePlace> activityCoursePlaces);

	/**
	* Creates a new activity course place with the primary key. Does not add the activity course place to the database.
	*
	* @param activityCoursePlaceId the primary key for the new activity course place
	* @return the new activity course place
	*/
	public ActivityCoursePlace create(long activityCoursePlaceId);

	/**
	* Removes the activity course place with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCoursePlaceId the primary key of the activity course place
	* @return the activity course place that was removed
	* @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	*/
	public ActivityCoursePlace remove(long activityCoursePlaceId)
		throws NoSuchActivityCoursePlaceException;

	public ActivityCoursePlace updateImpl(
		ActivityCoursePlace activityCoursePlace);

	/**
	* Returns the activity course place with the primary key or throws a {@link NoSuchActivityCoursePlaceException} if it could not be found.
	*
	* @param activityCoursePlaceId the primary key of the activity course place
	* @return the activity course place
	* @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	*/
	public ActivityCoursePlace findByPrimaryKey(long activityCoursePlaceId)
		throws NoSuchActivityCoursePlaceException;

	/**
	* Returns the activity course place with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param activityCoursePlaceId the primary key of the activity course place
	* @return the activity course place, or <code>null</code> if a activity course place with the primary key could not be found
	*/
	public ActivityCoursePlace fetchByPrimaryKey(long activityCoursePlaceId);

	@Override
	public java.util.Map<java.io.Serializable, ActivityCoursePlace> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the activity course places.
	*
	* @return the activity course places
	*/
	public java.util.List<ActivityCoursePlace> findAll();

	/**
	* Returns a range of all the activity course places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @return the range of activity course places
	*/
	public java.util.List<ActivityCoursePlace> findAll(int start, int end);

	/**
	* Returns an ordered range of all the activity course places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of activity course places
	*/
	public java.util.List<ActivityCoursePlace> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator);

	/**
	* Returns an ordered range of all the activity course places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of activity course places
	*/
	public java.util.List<ActivityCoursePlace> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCoursePlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the activity course places from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of activity course places.
	*
	* @return the number of activity course places
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}