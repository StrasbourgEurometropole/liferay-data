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

import eu.strasbourg.service.activity.exception.NoSuchActivityCourseScheduleException;
import eu.strasbourg.service.activity.model.ActivityCourseSchedule;

/**
 * The persistence interface for the activity course schedule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.activity.service.persistence.impl.ActivityCourseSchedulePersistenceImpl
 * @see ActivityCourseScheduleUtil
 * @generated
 */
@ProviderType
public interface ActivityCourseSchedulePersistence extends BasePersistence<ActivityCourseSchedule> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ActivityCourseScheduleUtil} to access the activity course schedule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the activity course schedules where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByUuid(
		java.lang.String uuid);

	/**
	* Returns a range of all the activity course schedules where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @return the range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByUuid(
		java.lang.String uuid, int start, int end);

	/**
	* Returns an ordered range of all the activity course schedules where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns an ordered range of all the activity course schedules where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course schedule in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Returns the first activity course schedule in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns the last activity course schedule in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Returns the last activity course schedule in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns the activity course schedules before and after the current activity course schedule in the ordered set where uuid = &#63;.
	*
	* @param activityCourseScheduleId the primary key of the current activity course schedule
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	*/
	public ActivityCourseSchedule[] findByUuid_PrevAndNext(
		long activityCourseScheduleId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Removes all the activity course schedules where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of activity course schedules where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching activity course schedules
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the activity course schedule where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchActivityCourseScheduleException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule findByUUID_G(java.lang.String uuid,
		long groupId) throws NoSuchActivityCourseScheduleException;

	/**
	* Returns the activity course schedule where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule fetchByUUID_G(java.lang.String uuid,
		long groupId);

	/**
	* Returns the activity course schedule where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the activity course schedule where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the activity course schedule that was removed
	*/
	public ActivityCourseSchedule removeByUUID_G(java.lang.String uuid,
		long groupId) throws NoSuchActivityCourseScheduleException;

	/**
	* Returns the number of activity course schedules where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching activity course schedules
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the activity course schedules where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByUuid_C(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of all the activity course schedules where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @return the range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the activity course schedules where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns an ordered range of all the activity course schedules where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course schedule in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Returns the first activity course schedule in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns the last activity course schedule in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Returns the last activity course schedule in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns the activity course schedules before and after the current activity course schedule in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param activityCourseScheduleId the primary key of the current activity course schedule
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	*/
	public ActivityCourseSchedule[] findByUuid_C_PrevAndNext(
		long activityCourseScheduleId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Removes all the activity course schedules where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of activity course schedules where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching activity course schedules
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the activity course schedules where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByGroupId(long groupId);

	/**
	* Returns a range of all the activity course schedules where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @return the range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the activity course schedules where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns an ordered range of all the activity course schedules where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course schedule in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Returns the first activity course schedule in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns the last activity course schedule in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Returns the last activity course schedule in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns the activity course schedules before and after the current activity course schedule in the ordered set where groupId = &#63;.
	*
	* @param activityCourseScheduleId the primary key of the current activity course schedule
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	*/
	public ActivityCourseSchedule[] findByGroupId_PrevAndNext(
		long activityCourseScheduleId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Removes all the activity course schedules where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of activity course schedules where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching activity course schedules
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the activity course schedules where activityCoursePlaceId = &#63;.
	*
	* @param activityCoursePlaceId the activity course place ID
	* @return the matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByActivityCoursePlace(
		long activityCoursePlaceId);

	/**
	* Returns a range of all the activity course schedules where activityCoursePlaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @return the range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByActivityCoursePlace(
		long activityCoursePlaceId, int start, int end);

	/**
	* Returns an ordered range of all the activity course schedules where activityCoursePlaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByActivityCoursePlace(
		long activityCoursePlaceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns an ordered range of all the activity course schedules where activityCoursePlaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByActivityCoursePlace(
		long activityCoursePlaceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course schedule in the ordered set where activityCoursePlaceId = &#63;.
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule findByActivityCoursePlace_First(
		long activityCoursePlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Returns the first activity course schedule in the ordered set where activityCoursePlaceId = &#63;.
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule fetchByActivityCoursePlace_First(
		long activityCoursePlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns the last activity course schedule in the ordered set where activityCoursePlaceId = &#63;.
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule findByActivityCoursePlace_Last(
		long activityCoursePlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Returns the last activity course schedule in the ordered set where activityCoursePlaceId = &#63;.
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule fetchByActivityCoursePlace_Last(
		long activityCoursePlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns the activity course schedules before and after the current activity course schedule in the ordered set where activityCoursePlaceId = &#63;.
	*
	* @param activityCourseScheduleId the primary key of the current activity course schedule
	* @param activityCoursePlaceId the activity course place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	*/
	public ActivityCourseSchedule[] findByActivityCoursePlace_PrevAndNext(
		long activityCourseScheduleId, long activityCoursePlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Removes all the activity course schedules where activityCoursePlaceId = &#63; from the database.
	*
	* @param activityCoursePlaceId the activity course place ID
	*/
	public void removeByActivityCoursePlace(long activityCoursePlaceId);

	/**
	* Returns the number of activity course schedules where activityCoursePlaceId = &#63;.
	*
	* @param activityCoursePlaceId the activity course place ID
	* @return the number of matching activity course schedules
	*/
	public int countByActivityCoursePlace(long activityCoursePlaceId);

	/**
	* Returns all the activity course schedules where activityCoursePlaceId = &#63; and periodId = &#63;.
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param periodId the period ID
	* @return the matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByActivityCoursePlaceAndPeriodId(
		long activityCoursePlaceId, long periodId);

	/**
	* Returns a range of all the activity course schedules where activityCoursePlaceId = &#63; and periodId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param periodId the period ID
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @return the range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByActivityCoursePlaceAndPeriodId(
		long activityCoursePlaceId, long periodId, int start, int end);

	/**
	* Returns an ordered range of all the activity course schedules where activityCoursePlaceId = &#63; and periodId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param periodId the period ID
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByActivityCoursePlaceAndPeriodId(
		long activityCoursePlaceId, long periodId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns an ordered range of all the activity course schedules where activityCoursePlaceId = &#63; and periodId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param periodId the period ID
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findByActivityCoursePlaceAndPeriodId(
		long activityCoursePlaceId, long periodId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity course schedule in the ordered set where activityCoursePlaceId = &#63; and periodId = &#63;.
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule findByActivityCoursePlaceAndPeriodId_First(
		long activityCoursePlaceId, long periodId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Returns the first activity course schedule in the ordered set where activityCoursePlaceId = &#63; and periodId = &#63;.
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule fetchByActivityCoursePlaceAndPeriodId_First(
		long activityCoursePlaceId, long periodId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns the last activity course schedule in the ordered set where activityCoursePlaceId = &#63; and periodId = &#63;.
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule findByActivityCoursePlaceAndPeriodId_Last(
		long activityCoursePlaceId, long periodId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Returns the last activity course schedule in the ordered set where activityCoursePlaceId = &#63; and periodId = &#63;.
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	*/
	public ActivityCourseSchedule fetchByActivityCoursePlaceAndPeriodId_Last(
		long activityCoursePlaceId, long periodId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns the activity course schedules before and after the current activity course schedule in the ordered set where activityCoursePlaceId = &#63; and periodId = &#63;.
	*
	* @param activityCourseScheduleId the primary key of the current activity course schedule
	* @param activityCoursePlaceId the activity course place ID
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	*/
	public ActivityCourseSchedule[] findByActivityCoursePlaceAndPeriodId_PrevAndNext(
		long activityCourseScheduleId, long activityCoursePlaceId,
		long periodId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Removes all the activity course schedules where activityCoursePlaceId = &#63; and periodId = &#63; from the database.
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param periodId the period ID
	*/
	public void removeByActivityCoursePlaceAndPeriodId(
		long activityCoursePlaceId, long periodId);

	/**
	* Returns the number of activity course schedules where activityCoursePlaceId = &#63; and periodId = &#63;.
	*
	* @param activityCoursePlaceId the activity course place ID
	* @param periodId the period ID
	* @return the number of matching activity course schedules
	*/
	public int countByActivityCoursePlaceAndPeriodId(
		long activityCoursePlaceId, long periodId);

	/**
	* Caches the activity course schedule in the entity cache if it is enabled.
	*
	* @param activityCourseSchedule the activity course schedule
	*/
	public void cacheResult(ActivityCourseSchedule activityCourseSchedule);

	/**
	* Caches the activity course schedules in the entity cache if it is enabled.
	*
	* @param activityCourseSchedules the activity course schedules
	*/
	public void cacheResult(
		java.util.List<ActivityCourseSchedule> activityCourseSchedules);

	/**
	* Creates a new activity course schedule with the primary key. Does not add the activity course schedule to the database.
	*
	* @param activityCourseScheduleId the primary key for the new activity course schedule
	* @return the new activity course schedule
	*/
	public ActivityCourseSchedule create(long activityCourseScheduleId);

	/**
	* Removes the activity course schedule with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourseScheduleId the primary key of the activity course schedule
	* @return the activity course schedule that was removed
	* @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	*/
	public ActivityCourseSchedule remove(long activityCourseScheduleId)
		throws NoSuchActivityCourseScheduleException;

	public ActivityCourseSchedule updateImpl(
		ActivityCourseSchedule activityCourseSchedule);

	/**
	* Returns the activity course schedule with the primary key or throws a {@link NoSuchActivityCourseScheduleException} if it could not be found.
	*
	* @param activityCourseScheduleId the primary key of the activity course schedule
	* @return the activity course schedule
	* @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	*/
	public ActivityCourseSchedule findByPrimaryKey(
		long activityCourseScheduleId)
		throws NoSuchActivityCourseScheduleException;

	/**
	* Returns the activity course schedule with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param activityCourseScheduleId the primary key of the activity course schedule
	* @return the activity course schedule, or <code>null</code> if a activity course schedule with the primary key could not be found
	*/
	public ActivityCourseSchedule fetchByPrimaryKey(
		long activityCourseScheduleId);

	@Override
	public java.util.Map<java.io.Serializable, ActivityCourseSchedule> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the activity course schedules.
	*
	* @return the activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findAll();

	/**
	* Returns a range of all the activity course schedules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @return the range of activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findAll(int start, int end);

	/**
	* Returns an ordered range of all the activity course schedules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator);

	/**
	* Returns an ordered range of all the activity course schedules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of activity course schedules
	*/
	public java.util.List<ActivityCourseSchedule> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the activity course schedules from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of activity course schedules.
	*
	* @return the number of activity course schedules
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}