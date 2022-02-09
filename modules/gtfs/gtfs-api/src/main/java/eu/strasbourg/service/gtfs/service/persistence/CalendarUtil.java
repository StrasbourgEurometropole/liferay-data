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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.gtfs.model.Calendar;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the calendar service. This utility wraps <code>eu.strasbourg.service.gtfs.service.persistence.impl.CalendarPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see CalendarPersistence
 * @generated
 */
@ProviderType
public class CalendarUtil {

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
	public static void clearCache(Calendar calendar) {
		getPersistence().clearCache(calendar);
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
	public static Map<Serializable, Calendar> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Calendar> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Calendar> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Calendar> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Calendar update(Calendar calendar) {
		return getPersistence().update(calendar);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Calendar update(
		Calendar calendar, ServiceContext serviceContext) {

		return getPersistence().update(calendar, serviceContext);
	}

	/**
	 * Returns all the calendars where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @return the matching calendars
	 */
	public static List<Calendar> findByServiceId(String service_id) {
		return getPersistence().findByServiceId(service_id);
	}

	/**
	 * Returns a range of all the calendars where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 */
	public static List<Calendar> findByServiceId(
		String service_id, int start, int end) {

		return getPersistence().findByServiceId(service_id, start, end);
	}

	/**
	 * Returns an ordered range of all the calendars where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 */
	public static List<Calendar> findByServiceId(
		String service_id, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {

		return getPersistence().findByServiceId(
			service_id, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the calendars where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching calendars
	 */
	public static List<Calendar> findByServiceId(
		String service_id, int start, int end,
		OrderByComparator<Calendar> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByServiceId(
			service_id, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first calendar in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	public static Calendar findByServiceId_First(
			String service_id, OrderByComparator<Calendar> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarException {

		return getPersistence().findByServiceId_First(
			service_id, orderByComparator);
	}

	/**
	 * Returns the first calendar in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	public static Calendar fetchByServiceId_First(
		String service_id, OrderByComparator<Calendar> orderByComparator) {

		return getPersistence().fetchByServiceId_First(
			service_id, orderByComparator);
	}

	/**
	 * Returns the last calendar in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	public static Calendar findByServiceId_Last(
			String service_id, OrderByComparator<Calendar> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarException {

		return getPersistence().findByServiceId_Last(
			service_id, orderByComparator);
	}

	/**
	 * Returns the last calendar in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	public static Calendar fetchByServiceId_Last(
		String service_id, OrderByComparator<Calendar> orderByComparator) {

		return getPersistence().fetchByServiceId_Last(
			service_id, orderByComparator);
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where service_id = &#63;.
	 *
	 * @param id the primary key of the current calendar
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	public static Calendar[] findByServiceId_PrevAndNext(
			long id, String service_id,
			OrderByComparator<Calendar> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarException {

		return getPersistence().findByServiceId_PrevAndNext(
			id, service_id, orderByComparator);
	}

	/**
	 * Removes all the calendars where service_id = &#63; from the database.
	 *
	 * @param service_id the service_id
	 */
	public static void removeByServiceId(String service_id) {
		getPersistence().removeByServiceId(service_id);
	}

	/**
	 * Returns the number of calendars where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @return the number of matching calendars
	 */
	public static int countByServiceId(String service_id) {
		return getPersistence().countByServiceId(service_id);
	}

	/**
	 * Returns all the calendars where start_date = &#63;.
	 *
	 * @param start_date the start_date
	 * @return the matching calendars
	 */
	public static List<Calendar> findByStartDate(Date start_date) {
		return getPersistence().findByStartDate(start_date);
	}

	/**
	 * Returns a range of all the calendars where start_date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start_date the start_date
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 */
	public static List<Calendar> findByStartDate(
		Date start_date, int start, int end) {

		return getPersistence().findByStartDate(start_date, start, end);
	}

	/**
	 * Returns an ordered range of all the calendars where start_date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start_date the start_date
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 */
	public static List<Calendar> findByStartDate(
		Date start_date, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {

		return getPersistence().findByStartDate(
			start_date, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the calendars where start_date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start_date the start_date
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching calendars
	 */
	public static List<Calendar> findByStartDate(
		Date start_date, int start, int end,
		OrderByComparator<Calendar> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByStartDate(
			start_date, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first calendar in the ordered set where start_date = &#63;.
	 *
	 * @param start_date the start_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	public static Calendar findByStartDate_First(
			Date start_date, OrderByComparator<Calendar> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarException {

		return getPersistence().findByStartDate_First(
			start_date, orderByComparator);
	}

	/**
	 * Returns the first calendar in the ordered set where start_date = &#63;.
	 *
	 * @param start_date the start_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	public static Calendar fetchByStartDate_First(
		Date start_date, OrderByComparator<Calendar> orderByComparator) {

		return getPersistence().fetchByStartDate_First(
			start_date, orderByComparator);
	}

	/**
	 * Returns the last calendar in the ordered set where start_date = &#63;.
	 *
	 * @param start_date the start_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	public static Calendar findByStartDate_Last(
			Date start_date, OrderByComparator<Calendar> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarException {

		return getPersistence().findByStartDate_Last(
			start_date, orderByComparator);
	}

	/**
	 * Returns the last calendar in the ordered set where start_date = &#63;.
	 *
	 * @param start_date the start_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	public static Calendar fetchByStartDate_Last(
		Date start_date, OrderByComparator<Calendar> orderByComparator) {

		return getPersistence().fetchByStartDate_Last(
			start_date, orderByComparator);
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where start_date = &#63;.
	 *
	 * @param id the primary key of the current calendar
	 * @param start_date the start_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	public static Calendar[] findByStartDate_PrevAndNext(
			long id, Date start_date,
			OrderByComparator<Calendar> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarException {

		return getPersistence().findByStartDate_PrevAndNext(
			id, start_date, orderByComparator);
	}

	/**
	 * Removes all the calendars where start_date = &#63; from the database.
	 *
	 * @param start_date the start_date
	 */
	public static void removeByStartDate(Date start_date) {
		getPersistence().removeByStartDate(start_date);
	}

	/**
	 * Returns the number of calendars where start_date = &#63;.
	 *
	 * @param start_date the start_date
	 * @return the number of matching calendars
	 */
	public static int countByStartDate(Date start_date) {
		return getPersistence().countByStartDate(start_date);
	}

	/**
	 * Returns all the calendars where end_date = &#63;.
	 *
	 * @param end_date the end_date
	 * @return the matching calendars
	 */
	public static List<Calendar> findByEndDate(Date end_date) {
		return getPersistence().findByEndDate(end_date);
	}

	/**
	 * Returns a range of all the calendars where end_date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param end_date the end_date
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 */
	public static List<Calendar> findByEndDate(
		Date end_date, int start, int end) {

		return getPersistence().findByEndDate(end_date, start, end);
	}

	/**
	 * Returns an ordered range of all the calendars where end_date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param end_date the end_date
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 */
	public static List<Calendar> findByEndDate(
		Date end_date, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {

		return getPersistence().findByEndDate(
			end_date, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the calendars where end_date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param end_date the end_date
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching calendars
	 */
	public static List<Calendar> findByEndDate(
		Date end_date, int start, int end,
		OrderByComparator<Calendar> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByEndDate(
			end_date, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first calendar in the ordered set where end_date = &#63;.
	 *
	 * @param end_date the end_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	public static Calendar findByEndDate_First(
			Date end_date, OrderByComparator<Calendar> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarException {

		return getPersistence().findByEndDate_First(
			end_date, orderByComparator);
	}

	/**
	 * Returns the first calendar in the ordered set where end_date = &#63;.
	 *
	 * @param end_date the end_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	public static Calendar fetchByEndDate_First(
		Date end_date, OrderByComparator<Calendar> orderByComparator) {

		return getPersistence().fetchByEndDate_First(
			end_date, orderByComparator);
	}

	/**
	 * Returns the last calendar in the ordered set where end_date = &#63;.
	 *
	 * @param end_date the end_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	public static Calendar findByEndDate_Last(
			Date end_date, OrderByComparator<Calendar> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarException {

		return getPersistence().findByEndDate_Last(end_date, orderByComparator);
	}

	/**
	 * Returns the last calendar in the ordered set where end_date = &#63;.
	 *
	 * @param end_date the end_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	public static Calendar fetchByEndDate_Last(
		Date end_date, OrderByComparator<Calendar> orderByComparator) {

		return getPersistence().fetchByEndDate_Last(
			end_date, orderByComparator);
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where end_date = &#63;.
	 *
	 * @param id the primary key of the current calendar
	 * @param end_date the end_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	public static Calendar[] findByEndDate_PrevAndNext(
			long id, Date end_date,
			OrderByComparator<Calendar> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarException {

		return getPersistence().findByEndDate_PrevAndNext(
			id, end_date, orderByComparator);
	}

	/**
	 * Removes all the calendars where end_date = &#63; from the database.
	 *
	 * @param end_date the end_date
	 */
	public static void removeByEndDate(Date end_date) {
		getPersistence().removeByEndDate(end_date);
	}

	/**
	 * Returns the number of calendars where end_date = &#63;.
	 *
	 * @param end_date the end_date
	 * @return the number of matching calendars
	 */
	public static int countByEndDate(Date end_date) {
		return getPersistence().countByEndDate(end_date);
	}

	/**
	 * Caches the calendar in the entity cache if it is enabled.
	 *
	 * @param calendar the calendar
	 */
	public static void cacheResult(Calendar calendar) {
		getPersistence().cacheResult(calendar);
	}

	/**
	 * Caches the calendars in the entity cache if it is enabled.
	 *
	 * @param calendars the calendars
	 */
	public static void cacheResult(List<Calendar> calendars) {
		getPersistence().cacheResult(calendars);
	}

	/**
	 * Creates a new calendar with the primary key. Does not add the calendar to the database.
	 *
	 * @param id the primary key for the new calendar
	 * @return the new calendar
	 */
	public static Calendar create(long id) {
		return getPersistence().create(id);
	}

	/**
	 * Removes the calendar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the calendar
	 * @return the calendar that was removed
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	public static Calendar remove(long id)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarException {

		return getPersistence().remove(id);
	}

	public static Calendar updateImpl(Calendar calendar) {
		return getPersistence().updateImpl(calendar);
	}

	/**
	 * Returns the calendar with the primary key or throws a <code>NoSuchCalendarException</code> if it could not be found.
	 *
	 * @param id the primary key of the calendar
	 * @return the calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	public static Calendar findByPrimaryKey(long id)
		throws eu.strasbourg.service.gtfs.exception.NoSuchCalendarException {

		return getPersistence().findByPrimaryKey(id);
	}

	/**
	 * Returns the calendar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the calendar
	 * @return the calendar, or <code>null</code> if a calendar with the primary key could not be found
	 */
	public static Calendar fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	 * Returns all the calendars.
	 *
	 * @return the calendars
	 */
	public static List<Calendar> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of calendars
	 */
	public static List<Calendar> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calendars
	 */
	public static List<Calendar> findAll(
		int start, int end, OrderByComparator<Calendar> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of calendars
	 */
	public static List<Calendar> findAll(
		int start, int end, OrderByComparator<Calendar> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the calendars from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of calendars.
	 *
	 * @return the number of calendars
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CalendarPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CalendarPersistence, CalendarPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CalendarPersistence.class);

		ServiceTracker<CalendarPersistence, CalendarPersistence>
			serviceTracker =
				new ServiceTracker<CalendarPersistence, CalendarPersistence>(
					bundle.getBundleContext(), CalendarPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}