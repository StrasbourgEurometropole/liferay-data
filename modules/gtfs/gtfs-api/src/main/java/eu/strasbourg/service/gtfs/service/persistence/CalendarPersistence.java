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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.gtfs.exception.NoSuchCalendarException;
import eu.strasbourg.service.gtfs.model.Calendar;

/**
 * The persistence interface for the calendar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.gtfs.service.persistence.impl.CalendarPersistenceImpl
 * @see CalendarUtil
 * @generated
 */
@ProviderType
public interface CalendarPersistence extends BasePersistence<Calendar> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CalendarUtil} to access the calendar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the calendars where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching calendars
	*/
	public java.util.List<Calendar> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the calendars where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of matching calendars
	*/
	public java.util.List<Calendar> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the calendars where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendars
	*/
	public java.util.List<Calendar> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns an ordered range of all the calendars where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching calendars
	*/
	public java.util.List<Calendar> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first calendar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException;

	/**
	* Returns the first calendar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the last calendar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException;

	/**
	* Returns the last calendar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the calendars before and after the current calendar in the ordered set where uuid = &#63;.
	*
	* @param id the primary key of the current calendar
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public Calendar[] findByUuid_PrevAndNext(long id, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException;

	/**
	* Removes all the calendars where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of calendars where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching calendars
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the calendars where service_id = &#63;.
	*
	* @param service_id the service_id
	* @return the matching calendars
	*/
	public java.util.List<Calendar> findByServiceId(java.lang.String service_id);

	/**
	* Returns a range of all the calendars where service_id = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param service_id the service_id
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of matching calendars
	*/
	public java.util.List<Calendar> findByServiceId(
		java.lang.String service_id, int start, int end);

	/**
	* Returns an ordered range of all the calendars where service_id = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param service_id the service_id
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendars
	*/
	public java.util.List<Calendar> findByServiceId(
		java.lang.String service_id, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns an ordered range of all the calendars where service_id = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param service_id the service_id
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching calendars
	*/
	public java.util.List<Calendar> findByServiceId(
		java.lang.String service_id, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first calendar in the ordered set where service_id = &#63;.
	*
	* @param service_id the service_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByServiceId_First(java.lang.String service_id,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException;

	/**
	* Returns the first calendar in the ordered set where service_id = &#63;.
	*
	* @param service_id the service_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByServiceId_First(java.lang.String service_id,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the last calendar in the ordered set where service_id = &#63;.
	*
	* @param service_id the service_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByServiceId_Last(java.lang.String service_id,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException;

	/**
	* Returns the last calendar in the ordered set where service_id = &#63;.
	*
	* @param service_id the service_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByServiceId_Last(java.lang.String service_id,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the calendars before and after the current calendar in the ordered set where service_id = &#63;.
	*
	* @param id the primary key of the current calendar
	* @param service_id the service_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public Calendar[] findByServiceId_PrevAndNext(long id,
		java.lang.String service_id,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException;

	/**
	* Removes all the calendars where service_id = &#63; from the database.
	*
	* @param service_id the service_id
	*/
	public void removeByServiceId(java.lang.String service_id);

	/**
	* Returns the number of calendars where service_id = &#63;.
	*
	* @param service_id the service_id
	* @return the number of matching calendars
	*/
	public int countByServiceId(java.lang.String service_id);

	/**
	* Caches the calendar in the entity cache if it is enabled.
	*
	* @param calendar the calendar
	*/
	public void cacheResult(Calendar calendar);

	/**
	* Caches the calendars in the entity cache if it is enabled.
	*
	* @param calendars the calendars
	*/
	public void cacheResult(java.util.List<Calendar> calendars);

	/**
	* Creates a new calendar with the primary key. Does not add the calendar to the database.
	*
	* @param id the primary key for the new calendar
	* @return the new calendar
	*/
	public Calendar create(long id);

	/**
	* Removes the calendar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the calendar
	* @return the calendar that was removed
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public Calendar remove(long id) throws NoSuchCalendarException;

	public Calendar updateImpl(Calendar calendar);

	/**
	* Returns the calendar with the primary key or throws a {@link NoSuchCalendarException} if it could not be found.
	*
	* @param id the primary key of the calendar
	* @return the calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public Calendar findByPrimaryKey(long id) throws NoSuchCalendarException;

	/**
	* Returns the calendar with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the calendar
	* @return the calendar, or <code>null</code> if a calendar with the primary key could not be found
	*/
	public Calendar fetchByPrimaryKey(long id);

	@Override
	public java.util.Map<java.io.Serializable, Calendar> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the calendars.
	*
	* @return the calendars
	*/
	public java.util.List<Calendar> findAll();

	/**
	* Returns a range of all the calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of calendars
	*/
	public java.util.List<Calendar> findAll(int start, int end);

	/**
	* Returns an ordered range of all the calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of calendars
	*/
	public java.util.List<Calendar> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns an ordered range of all the calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of calendars
	*/
	public java.util.List<Calendar> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the calendars from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of calendars.
	*
	* @return the number of calendars
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}