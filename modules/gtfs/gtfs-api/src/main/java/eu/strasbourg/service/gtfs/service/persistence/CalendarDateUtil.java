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

package eu.strasbourg.service.gtfs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.gtfs.model.CalendarDate;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the calendar date service. This utility wraps {@link eu.strasbourg.service.gtfs.service.persistence.impl.CalendarDatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see CalendarDatePersistence
 * @see eu.strasbourg.service.gtfs.service.persistence.impl.CalendarDatePersistenceImpl
 * @generated
 */
@ProviderType
public class CalendarDateUtil {
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
	public static void clearCache(CalendarDate calendarDate) {
		getPersistence().clearCache(calendarDate);
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
	public static List<CalendarDate> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CalendarDate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CalendarDate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CalendarDate update(CalendarDate calendarDate) {
		return getPersistence().update(calendarDate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CalendarDate update(CalendarDate calendarDate,
		ServiceContext serviceContext) {
		return getPersistence().update(calendarDate, serviceContext);
	}

	/**
	* Returns all the calendar dates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching calendar dates
	*/
	public static List<CalendarDate> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the calendar dates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar dates
	* @param end the upper bound of the range of calendar dates (not inclusive)
	* @return the range of matching calendar dates
	*/
	public static List<CalendarDate> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the calendar dates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar dates
	* @param end the upper bound of the range of calendar dates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar dates
	*/
	public static List<CalendarDate> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<CalendarDate> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the calendar dates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar dates
	* @param end the upper bound of the range of calendar dates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching calendar dates
	*/
	public static List<CalendarDate> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<CalendarDate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first calendar date in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar date
	* @throws NoSuchCalendarDateException if a matching calendar date could not be found
	*/
	public static CalendarDate findByUuid_First(java.lang.String uuid,
		OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarDateException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first calendar date in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar date, or <code>null</code> if a matching calendar date could not be found
	*/
	public static CalendarDate fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<CalendarDate> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last calendar date in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar date
	* @throws NoSuchCalendarDateException if a matching calendar date could not be found
	*/
	public static CalendarDate findByUuid_Last(java.lang.String uuid,
		OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarDateException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last calendar date in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar date, or <code>null</code> if a matching calendar date could not be found
	*/
	public static CalendarDate fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<CalendarDate> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the calendar dates before and after the current calendar date in the ordered set where uuid = &#63;.
	*
	* @param id the primary key of the current calendar date
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar date
	* @throws NoSuchCalendarDateException if a calendar date with the primary key could not be found
	*/
	public static CalendarDate[] findByUuid_PrevAndNext(long id,
		java.lang.String uuid, OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarDateException {
		return getPersistence()
				   .findByUuid_PrevAndNext(id, uuid, orderByComparator);
	}

	/**
	* Removes all the calendar dates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of calendar dates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching calendar dates
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the calendar dates where service_id = &#63;.
	*
	* @param service_id the service_id
	* @return the matching calendar dates
	*/
	public static List<CalendarDate> findByServiceId(
		java.lang.String service_id) {
		return getPersistence().findByServiceId(service_id);
	}

	/**
	* Returns a range of all the calendar dates where service_id = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param service_id the service_id
	* @param start the lower bound of the range of calendar dates
	* @param end the upper bound of the range of calendar dates (not inclusive)
	* @return the range of matching calendar dates
	*/
	public static List<CalendarDate> findByServiceId(
		java.lang.String service_id, int start, int end) {
		return getPersistence().findByServiceId(service_id, start, end);
	}

	/**
	* Returns an ordered range of all the calendar dates where service_id = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param service_id the service_id
	* @param start the lower bound of the range of calendar dates
	* @param end the upper bound of the range of calendar dates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar dates
	*/
	public static List<CalendarDate> findByServiceId(
		java.lang.String service_id, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator) {
		return getPersistence()
				   .findByServiceId(service_id, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the calendar dates where service_id = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param service_id the service_id
	* @param start the lower bound of the range of calendar dates
	* @param end the upper bound of the range of calendar dates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching calendar dates
	*/
	public static List<CalendarDate> findByServiceId(
		java.lang.String service_id, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByServiceId(service_id, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first calendar date in the ordered set where service_id = &#63;.
	*
	* @param service_id the service_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar date
	* @throws NoSuchCalendarDateException if a matching calendar date could not be found
	*/
	public static CalendarDate findByServiceId_First(
		java.lang.String service_id,
		OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarDateException {
		return getPersistence()
				   .findByServiceId_First(service_id, orderByComparator);
	}

	/**
	* Returns the first calendar date in the ordered set where service_id = &#63;.
	*
	* @param service_id the service_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar date, or <code>null</code> if a matching calendar date could not be found
	*/
	public static CalendarDate fetchByServiceId_First(
		java.lang.String service_id,
		OrderByComparator<CalendarDate> orderByComparator) {
		return getPersistence()
				   .fetchByServiceId_First(service_id, orderByComparator);
	}

	/**
	* Returns the last calendar date in the ordered set where service_id = &#63;.
	*
	* @param service_id the service_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar date
	* @throws NoSuchCalendarDateException if a matching calendar date could not be found
	*/
	public static CalendarDate findByServiceId_Last(
		java.lang.String service_id,
		OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarDateException {
		return getPersistence()
				   .findByServiceId_Last(service_id, orderByComparator);
	}

	/**
	* Returns the last calendar date in the ordered set where service_id = &#63;.
	*
	* @param service_id the service_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar date, or <code>null</code> if a matching calendar date could not be found
	*/
	public static CalendarDate fetchByServiceId_Last(
		java.lang.String service_id,
		OrderByComparator<CalendarDate> orderByComparator) {
		return getPersistence()
				   .fetchByServiceId_Last(service_id, orderByComparator);
	}

	/**
	* Returns the calendar dates before and after the current calendar date in the ordered set where service_id = &#63;.
	*
	* @param id the primary key of the current calendar date
	* @param service_id the service_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar date
	* @throws NoSuchCalendarDateException if a calendar date with the primary key could not be found
	*/
	public static CalendarDate[] findByServiceId_PrevAndNext(long id,
		java.lang.String service_id,
		OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarDateException {
		return getPersistence()
				   .findByServiceId_PrevAndNext(id, service_id,
			orderByComparator);
	}

	/**
	* Removes all the calendar dates where service_id = &#63; from the database.
	*
	* @param service_id the service_id
	*/
	public static void removeByServiceId(java.lang.String service_id) {
		getPersistence().removeByServiceId(service_id);
	}

	/**
	* Returns the number of calendar dates where service_id = &#63;.
	*
	* @param service_id the service_id
	* @return the number of matching calendar dates
	*/
	public static int countByServiceId(java.lang.String service_id) {
		return getPersistence().countByServiceId(service_id);
	}

	/**
	* Caches the calendar date in the entity cache if it is enabled.
	*
	* @param calendarDate the calendar date
	*/
	public static void cacheResult(CalendarDate calendarDate) {
		getPersistence().cacheResult(calendarDate);
	}

	/**
	* Caches the calendar dates in the entity cache if it is enabled.
	*
	* @param calendarDates the calendar dates
	*/
	public static void cacheResult(List<CalendarDate> calendarDates) {
		getPersistence().cacheResult(calendarDates);
	}

	/**
	* Creates a new calendar date with the primary key. Does not add the calendar date to the database.
	*
	* @param id the primary key for the new calendar date
	* @return the new calendar date
	*/
	public static CalendarDate create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the calendar date with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the calendar date
	* @return the calendar date that was removed
	* @throws NoSuchCalendarDateException if a calendar date with the primary key could not be found
	*/
	public static CalendarDate remove(long id)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarDateException {
		return getPersistence().remove(id);
	}

	public static CalendarDate updateImpl(CalendarDate calendarDate) {
		return getPersistence().updateImpl(calendarDate);
	}

	/**
	* Returns the calendar date with the primary key or throws a {@link NoSuchCalendarDateException} if it could not be found.
	*
	* @param id the primary key of the calendar date
	* @return the calendar date
	* @throws NoSuchCalendarDateException if a calendar date with the primary key could not be found
	*/
	public static CalendarDate findByPrimaryKey(long id)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarDateException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the calendar date with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the calendar date
	* @return the calendar date, or <code>null</code> if a calendar date with the primary key could not be found
	*/
	public static CalendarDate fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	public static java.util.Map<java.io.Serializable, CalendarDate> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the calendar dates.
	*
	* @return the calendar dates
	*/
	public static List<CalendarDate> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the calendar dates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar dates
	* @param end the upper bound of the range of calendar dates (not inclusive)
	* @return the range of calendar dates
	*/
	public static List<CalendarDate> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the calendar dates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar dates
	* @param end the upper bound of the range of calendar dates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of calendar dates
	*/
	public static List<CalendarDate> findAll(int start, int end,
		OrderByComparator<CalendarDate> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the calendar dates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar dates
	* @param end the upper bound of the range of calendar dates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of calendar dates
	*/
	public static List<CalendarDate> findAll(int start, int end,
		OrderByComparator<CalendarDate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the calendar dates from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of calendar dates.
	*
	* @return the number of calendar dates
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CalendarDatePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CalendarDatePersistence, CalendarDatePersistence> _serviceTracker =
		ServiceTrackerFactory.open(CalendarDatePersistence.class);
}