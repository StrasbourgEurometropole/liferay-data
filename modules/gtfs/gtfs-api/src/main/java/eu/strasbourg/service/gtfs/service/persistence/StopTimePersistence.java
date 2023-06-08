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

import eu.strasbourg.service.gtfs.exception.NoSuchStopTimeException;
import eu.strasbourg.service.gtfs.model.StopTime;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the stop time service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see StopTimeUtil
 * @generated
 */
@ProviderType
public interface StopTimePersistence extends BasePersistence<StopTime> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StopTimeUtil} to access the stop time persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, StopTime> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the stop times where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching stop times
	 */
	public java.util.List<StopTime> findByUuid(String uuid);

	/**
	 * Returns a range of all the stop times where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopTimeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of stop times
	 * @param end the upper bound of the range of stop times (not inclusive)
	 * @return the range of matching stop times
	 */
	public java.util.List<StopTime> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the stop times where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopTimeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of stop times
	 * @param end the upper bound of the range of stop times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching stop times
	 */
	public java.util.List<StopTime> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator);

	/**
	 * Returns an ordered range of all the stop times where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopTimeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of stop times
	 * @param end the upper bound of the range of stop times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching stop times
	 */
	public java.util.List<StopTime> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first stop time in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop time
	 * @throws NoSuchStopTimeException if a matching stop time could not be found
	 */
	public StopTime findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<StopTime>
				orderByComparator)
		throws NoSuchStopTimeException;

	/**
	 * Returns the first stop time in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop time, or <code>null</code> if a matching stop time could not be found
	 */
	public StopTime fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator);

	/**
	 * Returns the last stop time in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop time
	 * @throws NoSuchStopTimeException if a matching stop time could not be found
	 */
	public StopTime findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<StopTime>
				orderByComparator)
		throws NoSuchStopTimeException;

	/**
	 * Returns the last stop time in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop time, or <code>null</code> if a matching stop time could not be found
	 */
	public StopTime fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator);

	/**
	 * Returns the stop times before and after the current stop time in the ordered set where uuid = &#63;.
	 *
	 * @param id the primary key of the current stop time
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next stop time
	 * @throws NoSuchStopTimeException if a stop time with the primary key could not be found
	 */
	public StopTime[] findByUuid_PrevAndNext(
			long id, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<StopTime>
				orderByComparator)
		throws NoSuchStopTimeException;

	/**
	 * Removes all the stop times where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of stop times where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching stop times
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the stop times where trip_id = &#63;.
	 *
	 * @param trip_id the trip_id
	 * @return the matching stop times
	 */
	public java.util.List<StopTime> findByTripId(String trip_id);

	/**
	 * Returns a range of all the stop times where trip_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopTimeModelImpl</code>.
	 * </p>
	 *
	 * @param trip_id the trip_id
	 * @param start the lower bound of the range of stop times
	 * @param end the upper bound of the range of stop times (not inclusive)
	 * @return the range of matching stop times
	 */
	public java.util.List<StopTime> findByTripId(
		String trip_id, int start, int end);

	/**
	 * Returns an ordered range of all the stop times where trip_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopTimeModelImpl</code>.
	 * </p>
	 *
	 * @param trip_id the trip_id
	 * @param start the lower bound of the range of stop times
	 * @param end the upper bound of the range of stop times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching stop times
	 */
	public java.util.List<StopTime> findByTripId(
		String trip_id, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator);

	/**
	 * Returns an ordered range of all the stop times where trip_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopTimeModelImpl</code>.
	 * </p>
	 *
	 * @param trip_id the trip_id
	 * @param start the lower bound of the range of stop times
	 * @param end the upper bound of the range of stop times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching stop times
	 */
	public java.util.List<StopTime> findByTripId(
		String trip_id, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first stop time in the ordered set where trip_id = &#63;.
	 *
	 * @param trip_id the trip_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop time
	 * @throws NoSuchStopTimeException if a matching stop time could not be found
	 */
	public StopTime findByTripId_First(
			String trip_id,
			com.liferay.portal.kernel.util.OrderByComparator<StopTime>
				orderByComparator)
		throws NoSuchStopTimeException;

	/**
	 * Returns the first stop time in the ordered set where trip_id = &#63;.
	 *
	 * @param trip_id the trip_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop time, or <code>null</code> if a matching stop time could not be found
	 */
	public StopTime fetchByTripId_First(
		String trip_id,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator);

	/**
	 * Returns the last stop time in the ordered set where trip_id = &#63;.
	 *
	 * @param trip_id the trip_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop time
	 * @throws NoSuchStopTimeException if a matching stop time could not be found
	 */
	public StopTime findByTripId_Last(
			String trip_id,
			com.liferay.portal.kernel.util.OrderByComparator<StopTime>
				orderByComparator)
		throws NoSuchStopTimeException;

	/**
	 * Returns the last stop time in the ordered set where trip_id = &#63;.
	 *
	 * @param trip_id the trip_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop time, or <code>null</code> if a matching stop time could not be found
	 */
	public StopTime fetchByTripId_Last(
		String trip_id,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator);

	/**
	 * Returns the stop times before and after the current stop time in the ordered set where trip_id = &#63;.
	 *
	 * @param id the primary key of the current stop time
	 * @param trip_id the trip_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next stop time
	 * @throws NoSuchStopTimeException if a stop time with the primary key could not be found
	 */
	public StopTime[] findByTripId_PrevAndNext(
			long id, String trip_id,
			com.liferay.portal.kernel.util.OrderByComparator<StopTime>
				orderByComparator)
		throws NoSuchStopTimeException;

	/**
	 * Removes all the stop times where trip_id = &#63; from the database.
	 *
	 * @param trip_id the trip_id
	 */
	public void removeByTripId(String trip_id);

	/**
	 * Returns the number of stop times where trip_id = &#63;.
	 *
	 * @param trip_id the trip_id
	 * @return the number of matching stop times
	 */
	public int countByTripId(String trip_id);

	/**
	 * Returns all the stop times where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @return the matching stop times
	 */
	public java.util.List<StopTime> findByStopId(String stop_id);

	/**
	 * Returns a range of all the stop times where stop_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopTimeModelImpl</code>.
	 * </p>
	 *
	 * @param stop_id the stop_id
	 * @param start the lower bound of the range of stop times
	 * @param end the upper bound of the range of stop times (not inclusive)
	 * @return the range of matching stop times
	 */
	public java.util.List<StopTime> findByStopId(
		String stop_id, int start, int end);

	/**
	 * Returns an ordered range of all the stop times where stop_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopTimeModelImpl</code>.
	 * </p>
	 *
	 * @param stop_id the stop_id
	 * @param start the lower bound of the range of stop times
	 * @param end the upper bound of the range of stop times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching stop times
	 */
	public java.util.List<StopTime> findByStopId(
		String stop_id, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator);

	/**
	 * Returns an ordered range of all the stop times where stop_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopTimeModelImpl</code>.
	 * </p>
	 *
	 * @param stop_id the stop_id
	 * @param start the lower bound of the range of stop times
	 * @param end the upper bound of the range of stop times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching stop times
	 */
	public java.util.List<StopTime> findByStopId(
		String stop_id, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first stop time in the ordered set where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop time
	 * @throws NoSuchStopTimeException if a matching stop time could not be found
	 */
	public StopTime findByStopId_First(
			String stop_id,
			com.liferay.portal.kernel.util.OrderByComparator<StopTime>
				orderByComparator)
		throws NoSuchStopTimeException;

	/**
	 * Returns the first stop time in the ordered set where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop time, or <code>null</code> if a matching stop time could not be found
	 */
	public StopTime fetchByStopId_First(
		String stop_id,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator);

	/**
	 * Returns the last stop time in the ordered set where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop time
	 * @throws NoSuchStopTimeException if a matching stop time could not be found
	 */
	public StopTime findByStopId_Last(
			String stop_id,
			com.liferay.portal.kernel.util.OrderByComparator<StopTime>
				orderByComparator)
		throws NoSuchStopTimeException;

	/**
	 * Returns the last stop time in the ordered set where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop time, or <code>null</code> if a matching stop time could not be found
	 */
	public StopTime fetchByStopId_Last(
		String stop_id,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator);

	/**
	 * Returns the stop times before and after the current stop time in the ordered set where stop_id = &#63;.
	 *
	 * @param id the primary key of the current stop time
	 * @param stop_id the stop_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next stop time
	 * @throws NoSuchStopTimeException if a stop time with the primary key could not be found
	 */
	public StopTime[] findByStopId_PrevAndNext(
			long id, String stop_id,
			com.liferay.portal.kernel.util.OrderByComparator<StopTime>
				orderByComparator)
		throws NoSuchStopTimeException;

	/**
	 * Removes all the stop times where stop_id = &#63; from the database.
	 *
	 * @param stop_id the stop_id
	 */
	public void removeByStopId(String stop_id);

	/**
	 * Returns the number of stop times where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @return the number of matching stop times
	 */
	public int countByStopId(String stop_id);

	/**
	 * Caches the stop time in the entity cache if it is enabled.
	 *
	 * @param stopTime the stop time
	 */
	public void cacheResult(StopTime stopTime);

	/**
	 * Caches the stop times in the entity cache if it is enabled.
	 *
	 * @param stopTimes the stop times
	 */
	public void cacheResult(java.util.List<StopTime> stopTimes);

	/**
	 * Creates a new stop time with the primary key. Does not add the stop time to the database.
	 *
	 * @param id the primary key for the new stop time
	 * @return the new stop time
	 */
	public StopTime create(long id);

	/**
	 * Removes the stop time with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the stop time
	 * @return the stop time that was removed
	 * @throws NoSuchStopTimeException if a stop time with the primary key could not be found
	 */
	public StopTime remove(long id) throws NoSuchStopTimeException;

	public StopTime updateImpl(StopTime stopTime);

	/**
	 * Returns the stop time with the primary key or throws a <code>NoSuchStopTimeException</code> if it could not be found.
	 *
	 * @param id the primary key of the stop time
	 * @return the stop time
	 * @throws NoSuchStopTimeException if a stop time with the primary key could not be found
	 */
	public StopTime findByPrimaryKey(long id) throws NoSuchStopTimeException;

	/**
	 * Returns the stop time with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the stop time
	 * @return the stop time, or <code>null</code> if a stop time with the primary key could not be found
	 */
	public StopTime fetchByPrimaryKey(long id);

	/**
	 * Returns all the stop times.
	 *
	 * @return the stop times
	 */
	public java.util.List<StopTime> findAll();

	/**
	 * Returns a range of all the stop times.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopTimeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of stop times
	 * @param end the upper bound of the range of stop times (not inclusive)
	 * @return the range of stop times
	 */
	public java.util.List<StopTime> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the stop times.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopTimeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of stop times
	 * @param end the upper bound of the range of stop times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of stop times
	 */
	public java.util.List<StopTime> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator);

	/**
	 * Returns an ordered range of all the stop times.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopTimeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of stop times
	 * @param end the upper bound of the range of stop times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of stop times
	 */
	public java.util.List<StopTime> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StopTime>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the stop times from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of stop times.
	 *
	 * @return the number of stop times
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}