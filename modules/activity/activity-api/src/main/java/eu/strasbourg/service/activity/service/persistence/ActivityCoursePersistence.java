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

import eu.strasbourg.service.activity.exception.NoSuchActivityCourseException;
import eu.strasbourg.service.activity.model.ActivityCourse;

/**
 * The persistence interface for the activity course service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.activity.service.persistence.impl.ActivityCoursePersistenceImpl
 * @see ActivityCourseUtil
 * @generated
 */
@ProviderType
public interface ActivityCoursePersistence extends BasePersistence<ActivityCourse> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ActivityCourseUtil} to access the activity course persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the activity courses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching activity courses
	*/
	public java.util.List<ActivityCourse> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the activity courses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @return the range of matching activity courses
	*/
	public java.util.List<ActivityCourse> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the activity courses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity courses
	*/
	public java.util.List<ActivityCourse> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Returns an ordered range of all the activity courses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity courses
	*/
	public java.util.List<ActivityCourse> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public ActivityCourse findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException;

	/**
	* Returns the first activity course in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public ActivityCourse fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Returns the last activity course in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public ActivityCourse findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException;

	/**
	* Returns the last activity course in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public ActivityCourse fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Returns the activity courses before and after the current activity course in the ordered set where uuid = &#63;.
	*
	* @param activityCourseId the primary key of the current activity course
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course
	* @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	*/
	public ActivityCourse[] findByUuid_PrevAndNext(long activityCourseId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException;

	/**
	* Removes all the activity courses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of activity courses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching activity courses
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the activity course where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchActivityCourseException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public ActivityCourse findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchActivityCourseException;

	/**
	* Returns the activity course where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public ActivityCourse fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the activity course where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public ActivityCourse fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the activity course where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the activity course that was removed
	*/
	public ActivityCourse removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchActivityCourseException;

	/**
	* Returns the number of activity courses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching activity courses
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the activity courses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching activity courses
	*/
	public java.util.List<ActivityCourse> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the activity courses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @return the range of matching activity courses
	*/
	public java.util.List<ActivityCourse> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the activity courses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity courses
	*/
	public java.util.List<ActivityCourse> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Returns an ordered range of all the activity courses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity courses
	*/
	public java.util.List<ActivityCourse> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public ActivityCourse findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException;

	/**
	* Returns the first activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public ActivityCourse fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Returns the last activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public ActivityCourse findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException;

	/**
	* Returns the last activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public ActivityCourse fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Returns the activity courses before and after the current activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param activityCourseId the primary key of the current activity course
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course
	* @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	*/
	public ActivityCourse[] findByUuid_C_PrevAndNext(long activityCourseId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException;

	/**
	* Removes all the activity courses where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of activity courses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching activity courses
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the activity courses where activityId = &#63;.
	*
	* @param activityId the activity ID
	* @return the matching activity courses
	*/
	public java.util.List<ActivityCourse> findByActivity(long activityId);

	/**
	* Returns a range of all the activity courses where activityId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param activityId the activity ID
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @return the range of matching activity courses
	*/
	public java.util.List<ActivityCourse> findByActivity(long activityId,
		int start, int end);

	/**
	* Returns an ordered range of all the activity courses where activityId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param activityId the activity ID
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity courses
	*/
	public java.util.List<ActivityCourse> findByActivity(long activityId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Returns an ordered range of all the activity courses where activityId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param activityId the activity ID
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity courses
	*/
	public java.util.List<ActivityCourse> findByActivity(long activityId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course in the ordered set where activityId = &#63;.
	*
	* @param activityId the activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public ActivityCourse findByActivity_First(long activityId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException;

	/**
	* Returns the first activity course in the ordered set where activityId = &#63;.
	*
	* @param activityId the activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public ActivityCourse fetchByActivity_First(long activityId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Returns the last activity course in the ordered set where activityId = &#63;.
	*
	* @param activityId the activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public ActivityCourse findByActivity_Last(long activityId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException;

	/**
	* Returns the last activity course in the ordered set where activityId = &#63;.
	*
	* @param activityId the activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public ActivityCourse fetchByActivity_Last(long activityId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Returns the activity courses before and after the current activity course in the ordered set where activityId = &#63;.
	*
	* @param activityCourseId the primary key of the current activity course
	* @param activityId the activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course
	* @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	*/
	public ActivityCourse[] findByActivity_PrevAndNext(long activityCourseId,
		long activityId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException;

	/**
	* Removes all the activity courses where activityId = &#63; from the database.
	*
	* @param activityId the activity ID
	*/
	public void removeByActivity(long activityId);

	/**
	* Returns the number of activity courses where activityId = &#63;.
	*
	* @param activityId the activity ID
	* @return the number of matching activity courses
	*/
	public int countByActivity(long activityId);

	/**
	* Returns all the activity courses where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching activity courses
	*/
	public java.util.List<ActivityCourse> findByGroupId(long groupId);

	/**
	* Returns a range of all the activity courses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @return the range of matching activity courses
	*/
	public java.util.List<ActivityCourse> findByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the activity courses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity courses
	*/
	public java.util.List<ActivityCourse> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Returns an ordered range of all the activity courses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity courses
	*/
	public java.util.List<ActivityCourse> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public ActivityCourse findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException;

	/**
	* Returns the first activity course in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public ActivityCourse fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Returns the last activity course in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public ActivityCourse findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException;

	/**
	* Returns the last activity course in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public ActivityCourse fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Returns the activity courses before and after the current activity course in the ordered set where groupId = &#63;.
	*
	* @param activityCourseId the primary key of the current activity course
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course
	* @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	*/
	public ActivityCourse[] findByGroupId_PrevAndNext(long activityCourseId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException;

	/**
	* Removes all the activity courses where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of activity courses where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching activity courses
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the activity course in the entity cache if it is enabled.
	*
	* @param activityCourse the activity course
	*/
	public void cacheResult(ActivityCourse activityCourse);

	/**
	* Caches the activity courses in the entity cache if it is enabled.
	*
	* @param activityCourses the activity courses
	*/
	public void cacheResult(java.util.List<ActivityCourse> activityCourses);

	/**
	* Creates a new activity course with the primary key. Does not add the activity course to the database.
	*
	* @param activityCourseId the primary key for the new activity course
	* @return the new activity course
	*/
	public ActivityCourse create(long activityCourseId);

	/**
	* Removes the activity course with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourseId the primary key of the activity course
	* @return the activity course that was removed
	* @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	*/
	public ActivityCourse remove(long activityCourseId)
		throws NoSuchActivityCourseException;

	public ActivityCourse updateImpl(ActivityCourse activityCourse);

	/**
	* Returns the activity course with the primary key or throws a {@link NoSuchActivityCourseException} if it could not be found.
	*
	* @param activityCourseId the primary key of the activity course
	* @return the activity course
	* @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	*/
	public ActivityCourse findByPrimaryKey(long activityCourseId)
		throws NoSuchActivityCourseException;

	/**
	* Returns the activity course with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param activityCourseId the primary key of the activity course
	* @return the activity course, or <code>null</code> if a activity course with the primary key could not be found
	*/
	public ActivityCourse fetchByPrimaryKey(long activityCourseId);

	@Override
	public java.util.Map<java.io.Serializable, ActivityCourse> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the activity courses.
	*
	* @return the activity courses
	*/
	public java.util.List<ActivityCourse> findAll();

	/**
	* Returns a range of all the activity courses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @return the range of activity courses
	*/
	public java.util.List<ActivityCourse> findAll(int start, int end);

	/**
	* Returns an ordered range of all the activity courses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of activity courses
	*/
	public java.util.List<ActivityCourse> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Returns an ordered range of all the activity courses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of activity courses
	*/
	public java.util.List<ActivityCourse> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourse> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the activity courses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of activity courses.
	*
	* @return the number of activity courses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}