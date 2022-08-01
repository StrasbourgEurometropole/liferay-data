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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.gtfs.model.CalendarDate;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the calendar date service. This utility wraps <code>eu.strasbourg.service.gtfs.service.persistence.impl.CalendarDatePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see CalendarDatePersistence
 * @generated
 */
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
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CalendarDate> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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
	public static CalendarDate update(
		CalendarDate calendarDate, ServiceContext serviceContext) {

		return getPersistence().update(calendarDate, serviceContext);
	}

	/**
	 * Returns all the calendar dates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching calendar dates
	 */
	public static List<CalendarDate> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the calendar dates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @return the range of matching calendar dates
	 */
	public static List<CalendarDate> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the calendar dates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar dates
	 */
	public static List<CalendarDate> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the calendar dates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching calendar dates
	 */
	public static List<CalendarDate> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first calendar date in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar date
	 * @throws NoSuchCalendarDateException if a matching calendar date could not be found
	 */
	public static CalendarDate findByUuid_First(
			String uuid, OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCalendarDateException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first calendar date in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar date, or <code>null</code> if a matching calendar date could not be found
	 */
	public static CalendarDate fetchByUuid_First(
		String uuid, OrderByComparator<CalendarDate> orderByComparator) {

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
	public static CalendarDate findByUuid_Last(
			String uuid, OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCalendarDateException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last calendar date in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar date, or <code>null</code> if a matching calendar date could not be found
	 */
	public static CalendarDate fetchByUuid_Last(
		String uuid, OrderByComparator<CalendarDate> orderByComparator) {

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
	public static CalendarDate[] findByUuid_PrevAndNext(
			long id, String uuid,
			OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCalendarDateException {

		return getPersistence().findByUuid_PrevAndNext(
			id, uuid, orderByComparator);
	}

	/**
	 * Removes all the calendar dates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of calendar dates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching calendar dates
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the calendar dates where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @return the matching calendar dates
	 */
	public static List<CalendarDate> findByServiceId(String service_id) {
		return getPersistence().findByServiceId(service_id);
	}

	/**
	 * Returns a range of all the calendar dates where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @return the range of matching calendar dates
	 */
	public static List<CalendarDate> findByServiceId(
		String service_id, int start, int end) {

		return getPersistence().findByServiceId(service_id, start, end);
	}

	/**
	 * Returns an ordered range of all the calendar dates where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar dates
	 */
	public static List<CalendarDate> findByServiceId(
		String service_id, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator) {

		return getPersistence().findByServiceId(
			service_id, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the calendar dates where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching calendar dates
	 */
	public static List<CalendarDate> findByServiceId(
		String service_id, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByServiceId(
			service_id, start, end, orderByComparator, useFinderCache);
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
			String service_id,
			OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCalendarDateException {

		return getPersistence().findByServiceId_First(
			service_id, orderByComparator);
	}

	/**
	 * Returns the first calendar date in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar date, or <code>null</code> if a matching calendar date could not be found
	 */
	public static CalendarDate fetchByServiceId_First(
		String service_id, OrderByComparator<CalendarDate> orderByComparator) {

		return getPersistence().fetchByServiceId_First(
			service_id, orderByComparator);
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
			String service_id,
			OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCalendarDateException {

		return getPersistence().findByServiceId_Last(
			service_id, orderByComparator);
	}

	/**
	 * Returns the last calendar date in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar date, or <code>null</code> if a matching calendar date could not be found
	 */
	public static CalendarDate fetchByServiceId_Last(
		String service_id, OrderByComparator<CalendarDate> orderByComparator) {

		return getPersistence().fetchByServiceId_Last(
			service_id, orderByComparator);
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
	public static CalendarDate[] findByServiceId_PrevAndNext(
			long id, String service_id,
			OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCalendarDateException {

		return getPersistence().findByServiceId_PrevAndNext(
			id, service_id, orderByComparator);
	}

	/**
	 * Removes all the calendar dates where service_id = &#63; from the database.
	 *
	 * @param service_id the service_id
	 */
	public static void removeByServiceId(String service_id) {
		getPersistence().removeByServiceId(service_id);
	}

	/**
	 * Returns the number of calendar dates where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @return the number of matching calendar dates
	 */
	public static int countByServiceId(String service_id) {
		return getPersistence().countByServiceId(service_id);
	}

	/**
	 * Returns all the calendar dates where date = &#63;.
	 *
	 * @param date the date
	 * @return the matching calendar dates
	 */
	public static List<CalendarDate> findByDate(Date date) {
		return getPersistence().findByDate(date);
	}

	/**
	 * Returns a range of all the calendar dates where date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>.
	 * </p>
	 *
	 * @param date the date
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @return the range of matching calendar dates
	 */
	public static List<CalendarDate> findByDate(Date date, int start, int end) {
		return getPersistence().findByDate(date, start, end);
	}

	/**
	 * Returns an ordered range of all the calendar dates where date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>.
	 * </p>
	 *
	 * @param date the date
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar dates
	 */
	public static List<CalendarDate> findByDate(
		Date date, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator) {

		return getPersistence().findByDate(date, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the calendar dates where date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>.
	 * </p>
	 *
	 * @param date the date
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching calendar dates
	 */
	public static List<CalendarDate> findByDate(
		Date date, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByDate(
			date, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first calendar date in the ordered set where date = &#63;.
	 *
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar date
	 * @throws NoSuchCalendarDateException if a matching calendar date could not be found
	 */
	public static CalendarDate findByDate_First(
			Date date, OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCalendarDateException {

		return getPersistence().findByDate_First(date, orderByComparator);
	}

	/**
	 * Returns the first calendar date in the ordered set where date = &#63;.
	 *
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar date, or <code>null</code> if a matching calendar date could not be found
	 */
	public static CalendarDate fetchByDate_First(
		Date date, OrderByComparator<CalendarDate> orderByComparator) {

		return getPersistence().fetchByDate_First(date, orderByComparator);
	}

	/**
	 * Returns the last calendar date in the ordered set where date = &#63;.
	 *
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar date
	 * @throws NoSuchCalendarDateException if a matching calendar date could not be found
	 */
	public static CalendarDate findByDate_Last(
			Date date, OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCalendarDateException {

		return getPersistence().findByDate_Last(date, orderByComparator);
	}

	/**
	 * Returns the last calendar date in the ordered set where date = &#63;.
	 *
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar date, or <code>null</code> if a matching calendar date could not be found
	 */
	public static CalendarDate fetchByDate_Last(
		Date date, OrderByComparator<CalendarDate> orderByComparator) {

		return getPersistence().fetchByDate_Last(date, orderByComparator);
	}

	/**
	 * Returns the calendar dates before and after the current calendar date in the ordered set where date = &#63;.
	 *
	 * @param id the primary key of the current calendar date
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar date
	 * @throws NoSuchCalendarDateException if a calendar date with the primary key could not be found
	 */
	public static CalendarDate[] findByDate_PrevAndNext(
			long id, Date date,
			OrderByComparator<CalendarDate> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCalendarDateException {

		return getPersistence().findByDate_PrevAndNext(
			id, date, orderByComparator);
	}

	/**
	 * Removes all the calendar dates where date = &#63; from the database.
	 *
	 * @param date the date
	 */
	public static void removeByDate(Date date) {
		getPersistence().removeByDate(date);
	}

	/**
	 * Returns the number of calendar dates where date = &#63;.
	 *
	 * @param date the date
	 * @return the number of matching calendar dates
	 */
	public static int countByDate(Date date) {
		return getPersistence().countByDate(date);
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
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCalendarDateException {

		return getPersistence().remove(id);
	}

	public static CalendarDate updateImpl(CalendarDate calendarDate) {
		return getPersistence().updateImpl(calendarDate);
	}

	/**
	 * Returns the calendar date with the primary key or throws a <code>NoSuchCalendarDateException</code> if it could not be found.
	 *
	 * @param id the primary key of the calendar date
	 * @return the calendar date
	 * @throws NoSuchCalendarDateException if a calendar date with the primary key could not be found
	 */
	public static CalendarDate findByPrimaryKey(long id)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCalendarDateException {

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calendar dates
	 */
	public static List<CalendarDate> findAll(
		int start, int end, OrderByComparator<CalendarDate> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the calendar dates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of calendar dates
	 */
	public static List<CalendarDate> findAll(
		int start, int end, OrderByComparator<CalendarDate> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
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

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CalendarDatePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CalendarDatePersistence, CalendarDatePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CalendarDatePersistence.class);

		ServiceTracker<CalendarDatePersistence, CalendarDatePersistence>
			serviceTracker =
				new ServiceTracker
					<CalendarDatePersistence, CalendarDatePersistence>(
						bundle.getBundleContext(),
						CalendarDatePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}