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

package eu.strasbourg.service.place.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.place.model.ScheduleException;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the schedule exception service. This utility wraps {@link eu.strasbourg.service.place.service.persistence.impl.ScheduleExceptionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see ScheduleExceptionPersistence
 * @see eu.strasbourg.service.place.service.persistence.impl.ScheduleExceptionPersistenceImpl
 * @generated
 */
@ProviderType
public class ScheduleExceptionUtil {
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
	public static void clearCache(ScheduleException scheduleException) {
		getPersistence().clearCache(scheduleException);
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
	public static List<ScheduleException> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ScheduleException> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ScheduleException> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ScheduleException> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ScheduleException update(ScheduleException scheduleException) {
		return getPersistence().update(scheduleException);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ScheduleException update(
		ScheduleException scheduleException, ServiceContext serviceContext) {
		return getPersistence().update(scheduleException, serviceContext);
	}

	/**
	* Returns all the schedule exceptions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching schedule exceptions
	*/
	public static List<ScheduleException> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the schedule exceptions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @return the range of matching schedule exceptions
	*/
	public static List<ScheduleException> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the schedule exceptions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching schedule exceptions
	*/
	public static List<ScheduleException> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<ScheduleException> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the schedule exceptions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching schedule exceptions
	*/
	public static List<ScheduleException> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<ScheduleException> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first schedule exception in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching schedule exception
	* @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	*/
	public static ScheduleException findByUuid_First(java.lang.String uuid,
		OrderByComparator<ScheduleException> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchScheduleExceptionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first schedule exception in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	*/
	public static ScheduleException fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<ScheduleException> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last schedule exception in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching schedule exception
	* @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	*/
	public static ScheduleException findByUuid_Last(java.lang.String uuid,
		OrderByComparator<ScheduleException> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchScheduleExceptionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last schedule exception in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	*/
	public static ScheduleException fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<ScheduleException> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the schedule exceptions before and after the current schedule exception in the ordered set where uuid = &#63;.
	*
	* @param exceptionId the primary key of the current schedule exception
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next schedule exception
	* @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	*/
	public static ScheduleException[] findByUuid_PrevAndNext(long exceptionId,
		java.lang.String uuid,
		OrderByComparator<ScheduleException> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchScheduleExceptionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(exceptionId, uuid, orderByComparator);
	}

	/**
	* Removes all the schedule exceptions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of schedule exceptions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching schedule exceptions
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the schedule exceptions where placeId = &#63;.
	*
	* @param placeId the place ID
	* @return the matching schedule exceptions
	*/
	public static List<ScheduleException> findByPlaceId(long placeId) {
		return getPersistence().findByPlaceId(placeId);
	}

	/**
	* Returns a range of all the schedule exceptions where placeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeId the place ID
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @return the range of matching schedule exceptions
	*/
	public static List<ScheduleException> findByPlaceId(long placeId,
		int start, int end) {
		return getPersistence().findByPlaceId(placeId, start, end);
	}

	/**
	* Returns an ordered range of all the schedule exceptions where placeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeId the place ID
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching schedule exceptions
	*/
	public static List<ScheduleException> findByPlaceId(long placeId,
		int start, int end,
		OrderByComparator<ScheduleException> orderByComparator) {
		return getPersistence()
				   .findByPlaceId(placeId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the schedule exceptions where placeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeId the place ID
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching schedule exceptions
	*/
	public static List<ScheduleException> findByPlaceId(long placeId,
		int start, int end,
		OrderByComparator<ScheduleException> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPlaceId(placeId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first schedule exception in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching schedule exception
	* @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	*/
	public static ScheduleException findByPlaceId_First(long placeId,
		OrderByComparator<ScheduleException> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchScheduleExceptionException {
		return getPersistence().findByPlaceId_First(placeId, orderByComparator);
	}

	/**
	* Returns the first schedule exception in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	*/
	public static ScheduleException fetchByPlaceId_First(long placeId,
		OrderByComparator<ScheduleException> orderByComparator) {
		return getPersistence().fetchByPlaceId_First(placeId, orderByComparator);
	}

	/**
	* Returns the last schedule exception in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching schedule exception
	* @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	*/
	public static ScheduleException findByPlaceId_Last(long placeId,
		OrderByComparator<ScheduleException> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchScheduleExceptionException {
		return getPersistence().findByPlaceId_Last(placeId, orderByComparator);
	}

	/**
	* Returns the last schedule exception in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	*/
	public static ScheduleException fetchByPlaceId_Last(long placeId,
		OrderByComparator<ScheduleException> orderByComparator) {
		return getPersistence().fetchByPlaceId_Last(placeId, orderByComparator);
	}

	/**
	* Returns the schedule exceptions before and after the current schedule exception in the ordered set where placeId = &#63;.
	*
	* @param exceptionId the primary key of the current schedule exception
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next schedule exception
	* @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	*/
	public static ScheduleException[] findByPlaceId_PrevAndNext(
		long exceptionId, long placeId,
		OrderByComparator<ScheduleException> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchScheduleExceptionException {
		return getPersistence()
				   .findByPlaceId_PrevAndNext(exceptionId, placeId,
			orderByComparator);
	}

	/**
	* Removes all the schedule exceptions where placeId = &#63; from the database.
	*
	* @param placeId the place ID
	*/
	public static void removeByPlaceId(long placeId) {
		getPersistence().removeByPlaceId(placeId);
	}

	/**
	* Returns the number of schedule exceptions where placeId = &#63;.
	*
	* @param placeId the place ID
	* @return the number of matching schedule exceptions
	*/
	public static int countByPlaceId(long placeId) {
		return getPersistence().countByPlaceId(placeId);
	}

	/**
	* Returns all the schedule exceptions where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @return the matching schedule exceptions
	*/
	public static List<ScheduleException> findBySubPlaceId(long subPlaceId) {
		return getPersistence().findBySubPlaceId(subPlaceId);
	}

	/**
	* Returns a range of all the schedule exceptions where subPlaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param subPlaceId the sub place ID
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @return the range of matching schedule exceptions
	*/
	public static List<ScheduleException> findBySubPlaceId(long subPlaceId,
		int start, int end) {
		return getPersistence().findBySubPlaceId(subPlaceId, start, end);
	}

	/**
	* Returns an ordered range of all the schedule exceptions where subPlaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param subPlaceId the sub place ID
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching schedule exceptions
	*/
	public static List<ScheduleException> findBySubPlaceId(long subPlaceId,
		int start, int end,
		OrderByComparator<ScheduleException> orderByComparator) {
		return getPersistence()
				   .findBySubPlaceId(subPlaceId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the schedule exceptions where subPlaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param subPlaceId the sub place ID
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching schedule exceptions
	*/
	public static List<ScheduleException> findBySubPlaceId(long subPlaceId,
		int start, int end,
		OrderByComparator<ScheduleException> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySubPlaceId(subPlaceId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first schedule exception in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching schedule exception
	* @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	*/
	public static ScheduleException findBySubPlaceId_First(long subPlaceId,
		OrderByComparator<ScheduleException> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchScheduleExceptionException {
		return getPersistence()
				   .findBySubPlaceId_First(subPlaceId, orderByComparator);
	}

	/**
	* Returns the first schedule exception in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	*/
	public static ScheduleException fetchBySubPlaceId_First(long subPlaceId,
		OrderByComparator<ScheduleException> orderByComparator) {
		return getPersistence()
				   .fetchBySubPlaceId_First(subPlaceId, orderByComparator);
	}

	/**
	* Returns the last schedule exception in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching schedule exception
	* @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	*/
	public static ScheduleException findBySubPlaceId_Last(long subPlaceId,
		OrderByComparator<ScheduleException> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchScheduleExceptionException {
		return getPersistence()
				   .findBySubPlaceId_Last(subPlaceId, orderByComparator);
	}

	/**
	* Returns the last schedule exception in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	*/
	public static ScheduleException fetchBySubPlaceId_Last(long subPlaceId,
		OrderByComparator<ScheduleException> orderByComparator) {
		return getPersistence()
				   .fetchBySubPlaceId_Last(subPlaceId, orderByComparator);
	}

	/**
	* Returns the schedule exceptions before and after the current schedule exception in the ordered set where subPlaceId = &#63;.
	*
	* @param exceptionId the primary key of the current schedule exception
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next schedule exception
	* @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	*/
	public static ScheduleException[] findBySubPlaceId_PrevAndNext(
		long exceptionId, long subPlaceId,
		OrderByComparator<ScheduleException> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchScheduleExceptionException {
		return getPersistence()
				   .findBySubPlaceId_PrevAndNext(exceptionId, subPlaceId,
			orderByComparator);
	}

	/**
	* Removes all the schedule exceptions where subPlaceId = &#63; from the database.
	*
	* @param subPlaceId the sub place ID
	*/
	public static void removeBySubPlaceId(long subPlaceId) {
		getPersistence().removeBySubPlaceId(subPlaceId);
	}

	/**
	* Returns the number of schedule exceptions where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @return the number of matching schedule exceptions
	*/
	public static int countBySubPlaceId(long subPlaceId) {
		return getPersistence().countBySubPlaceId(subPlaceId);
	}

	/**
	* Caches the schedule exception in the entity cache if it is enabled.
	*
	* @param scheduleException the schedule exception
	*/
	public static void cacheResult(ScheduleException scheduleException) {
		getPersistence().cacheResult(scheduleException);
	}

	/**
	* Caches the schedule exceptions in the entity cache if it is enabled.
	*
	* @param scheduleExceptions the schedule exceptions
	*/
	public static void cacheResult(List<ScheduleException> scheduleExceptions) {
		getPersistence().cacheResult(scheduleExceptions);
	}

	/**
	* Creates a new schedule exception with the primary key. Does not add the schedule exception to the database.
	*
	* @param exceptionId the primary key for the new schedule exception
	* @return the new schedule exception
	*/
	public static ScheduleException create(long exceptionId) {
		return getPersistence().create(exceptionId);
	}

	/**
	* Removes the schedule exception with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param exceptionId the primary key of the schedule exception
	* @return the schedule exception that was removed
	* @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	*/
	public static ScheduleException remove(long exceptionId)
		throws eu.strasbourg.service.place.exception.NoSuchScheduleExceptionException {
		return getPersistence().remove(exceptionId);
	}

	public static ScheduleException updateImpl(
		ScheduleException scheduleException) {
		return getPersistence().updateImpl(scheduleException);
	}

	/**
	* Returns the schedule exception with the primary key or throws a {@link NoSuchScheduleExceptionException} if it could not be found.
	*
	* @param exceptionId the primary key of the schedule exception
	* @return the schedule exception
	* @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	*/
	public static ScheduleException findByPrimaryKey(long exceptionId)
		throws eu.strasbourg.service.place.exception.NoSuchScheduleExceptionException {
		return getPersistence().findByPrimaryKey(exceptionId);
	}

	/**
	* Returns the schedule exception with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param exceptionId the primary key of the schedule exception
	* @return the schedule exception, or <code>null</code> if a schedule exception with the primary key could not be found
	*/
	public static ScheduleException fetchByPrimaryKey(long exceptionId) {
		return getPersistence().fetchByPrimaryKey(exceptionId);
	}

	public static java.util.Map<java.io.Serializable, ScheduleException> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the schedule exceptions.
	*
	* @return the schedule exceptions
	*/
	public static List<ScheduleException> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the schedule exceptions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @return the range of schedule exceptions
	*/
	public static List<ScheduleException> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the schedule exceptions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of schedule exceptions
	*/
	public static List<ScheduleException> findAll(int start, int end,
		OrderByComparator<ScheduleException> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the schedule exceptions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of schedule exceptions
	*/
	public static List<ScheduleException> findAll(int start, int end,
		OrderByComparator<ScheduleException> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the schedule exceptions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of schedule exceptions.
	*
	* @return the number of schedule exceptions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ScheduleExceptionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ScheduleExceptionPersistence, ScheduleExceptionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ScheduleExceptionPersistence.class);
}