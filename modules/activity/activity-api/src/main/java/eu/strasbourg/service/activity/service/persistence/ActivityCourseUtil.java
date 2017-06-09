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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.activity.model.ActivityCourse;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the activity course service. This utility wraps {@link eu.strasbourg.service.activity.service.persistence.impl.ActivityCoursePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCoursePersistence
 * @see eu.strasbourg.service.activity.service.persistence.impl.ActivityCoursePersistenceImpl
 * @generated
 */
@ProviderType
public class ActivityCourseUtil {
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
	public static void clearCache(ActivityCourse activityCourse) {
		getPersistence().clearCache(activityCourse);
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
	public static List<ActivityCourse> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ActivityCourse> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ActivityCourse> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ActivityCourse update(ActivityCourse activityCourse) {
		return getPersistence().update(activityCourse);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ActivityCourse update(ActivityCourse activityCourse,
		ServiceContext serviceContext) {
		return getPersistence().update(activityCourse, serviceContext);
	}

	/**
	* Returns all the activity courses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching activity courses
	*/
	public static List<ActivityCourse> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<ActivityCourse> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<ActivityCourse> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<ActivityCourse> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first activity course in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public static ActivityCourse findByUuid_First(java.lang.String uuid,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first activity course in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public static ActivityCourse fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last activity course in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public static ActivityCourse findByUuid_Last(java.lang.String uuid,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last activity course in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public static ActivityCourse fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the activity courses before and after the current activity course in the ordered set where uuid = &#63;.
	*
	* @param activityCourseId the primary key of the current activity course
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course
	* @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	*/
	public static ActivityCourse[] findByUuid_PrevAndNext(
		long activityCourseId, java.lang.String uuid,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence()
				   .findByUuid_PrevAndNext(activityCourseId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the activity courses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of activity courses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching activity courses
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the activity course where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchActivityCourseException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public static ActivityCourse findByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the activity course where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public static ActivityCourse fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the activity course where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public static ActivityCourse fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the activity course where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the activity course that was removed
	*/
	public static ActivityCourse removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of activity courses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching activity courses
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the activity courses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching activity courses
	*/
	public static List<ActivityCourse> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<ActivityCourse> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<ActivityCourse> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<ActivityCourse> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public static ActivityCourse findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ActivityCourse> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public static ActivityCourse fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public static ActivityCourse findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ActivityCourse> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public static ActivityCourse fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static ActivityCourse[] findByUuid_C_PrevAndNext(
		long activityCourseId, java.lang.String uuid, long companyId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(activityCourseId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the activity courses where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of activity courses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching activity courses
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the activity courses where activityId = &#63;.
	*
	* @param activityId the activity ID
	* @return the matching activity courses
	*/
	public static List<ActivityCourse> findByActivity(long activityId) {
		return getPersistence().findByActivity(activityId);
	}

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
	public static List<ActivityCourse> findByActivity(long activityId,
		int start, int end) {
		return getPersistence().findByActivity(activityId, start, end);
	}

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
	public static List<ActivityCourse> findByActivity(long activityId,
		int start, int end, OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence()
				   .findByActivity(activityId, start, end, orderByComparator);
	}

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
	public static List<ActivityCourse> findByActivity(long activityId,
		int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByActivity(activityId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first activity course in the ordered set where activityId = &#63;.
	*
	* @param activityId the activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public static ActivityCourse findByActivity_First(long activityId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence()
				   .findByActivity_First(activityId, orderByComparator);
	}

	/**
	* Returns the first activity course in the ordered set where activityId = &#63;.
	*
	* @param activityId the activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public static ActivityCourse fetchByActivity_First(long activityId,
		OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence()
				   .fetchByActivity_First(activityId, orderByComparator);
	}

	/**
	* Returns the last activity course in the ordered set where activityId = &#63;.
	*
	* @param activityId the activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public static ActivityCourse findByActivity_Last(long activityId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence()
				   .findByActivity_Last(activityId, orderByComparator);
	}

	/**
	* Returns the last activity course in the ordered set where activityId = &#63;.
	*
	* @param activityId the activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public static ActivityCourse fetchByActivity_Last(long activityId,
		OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence()
				   .fetchByActivity_Last(activityId, orderByComparator);
	}

	/**
	* Returns the activity courses before and after the current activity course in the ordered set where activityId = &#63;.
	*
	* @param activityCourseId the primary key of the current activity course
	* @param activityId the activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course
	* @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	*/
	public static ActivityCourse[] findByActivity_PrevAndNext(
		long activityCourseId, long activityId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence()
				   .findByActivity_PrevAndNext(activityCourseId, activityId,
			orderByComparator);
	}

	/**
	* Removes all the activity courses where activityId = &#63; from the database.
	*
	* @param activityId the activity ID
	*/
	public static void removeByActivity(long activityId) {
		getPersistence().removeByActivity(activityId);
	}

	/**
	* Returns the number of activity courses where activityId = &#63;.
	*
	* @param activityId the activity ID
	* @return the number of matching activity courses
	*/
	public static int countByActivity(long activityId) {
		return getPersistence().countByActivity(activityId);
	}

	/**
	* Returns all the activity courses where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching activity courses
	*/
	public static List<ActivityCourse> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

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
	public static List<ActivityCourse> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

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
	public static List<ActivityCourse> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

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
	public static List<ActivityCourse> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ActivityCourse> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first activity course in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public static ActivityCourse findByGroupId_First(long groupId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first activity course in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public static ActivityCourse fetchByGroupId_First(long groupId,
		OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last activity course in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course
	* @throws NoSuchActivityCourseException if a matching activity course could not be found
	*/
	public static ActivityCourse findByGroupId_Last(long groupId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last activity course in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public static ActivityCourse fetchByGroupId_Last(long groupId,
		OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the activity courses before and after the current activity course in the ordered set where groupId = &#63;.
	*
	* @param activityCourseId the primary key of the current activity course
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity course
	* @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	*/
	public static ActivityCourse[] findByGroupId_PrevAndNext(
		long activityCourseId, long groupId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(activityCourseId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the activity courses where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of activity courses where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching activity courses
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Caches the activity course in the entity cache if it is enabled.
	*
	* @param activityCourse the activity course
	*/
	public static void cacheResult(ActivityCourse activityCourse) {
		getPersistence().cacheResult(activityCourse);
	}

	/**
	* Caches the activity courses in the entity cache if it is enabled.
	*
	* @param activityCourses the activity courses
	*/
	public static void cacheResult(List<ActivityCourse> activityCourses) {
		getPersistence().cacheResult(activityCourses);
	}

	/**
	* Creates a new activity course with the primary key. Does not add the activity course to the database.
	*
	* @param activityCourseId the primary key for the new activity course
	* @return the new activity course
	*/
	public static ActivityCourse create(long activityCourseId) {
		return getPersistence().create(activityCourseId);
	}

	/**
	* Removes the activity course with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourseId the primary key of the activity course
	* @return the activity course that was removed
	* @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	*/
	public static ActivityCourse remove(long activityCourseId)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence().remove(activityCourseId);
	}

	public static ActivityCourse updateImpl(ActivityCourse activityCourse) {
		return getPersistence().updateImpl(activityCourse);
	}

	/**
	* Returns the activity course with the primary key or throws a {@link NoSuchActivityCourseException} if it could not be found.
	*
	* @param activityCourseId the primary key of the activity course
	* @return the activity course
	* @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	*/
	public static ActivityCourse findByPrimaryKey(long activityCourseId)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityCourseException {
		return getPersistence().findByPrimaryKey(activityCourseId);
	}

	/**
	* Returns the activity course with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param activityCourseId the primary key of the activity course
	* @return the activity course, or <code>null</code> if a activity course with the primary key could not be found
	*/
	public static ActivityCourse fetchByPrimaryKey(long activityCourseId) {
		return getPersistence().fetchByPrimaryKey(activityCourseId);
	}

	public static java.util.Map<java.io.Serializable, ActivityCourse> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the activity courses.
	*
	* @return the activity courses
	*/
	public static List<ActivityCourse> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ActivityCourse> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ActivityCourse> findAll(int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ActivityCourse> findAll(int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the activity courses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of activity courses.
	*
	* @return the number of activity courses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ActivityCoursePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ActivityCoursePersistence, ActivityCoursePersistence> _serviceTracker =
		ServiceTrackerFactory.open(ActivityCoursePersistence.class);
}