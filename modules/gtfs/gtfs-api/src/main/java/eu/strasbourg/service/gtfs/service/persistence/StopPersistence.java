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

import eu.strasbourg.service.gtfs.exception.NoSuchStopException;
import eu.strasbourg.service.gtfs.model.Stop;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the stop service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see StopUtil
 * @generated
 */
@ProviderType
public interface StopPersistence extends BasePersistence<Stop> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StopUtil} to access the stop persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, Stop> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the stops where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching stops
	 */
	public java.util.List<Stop> findByUuid(String uuid);

	/**
	 * Returns a range of all the stops where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @return the range of matching stops
	 */
	public java.util.List<Stop> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the stops where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching stops
	 */
	public java.util.List<Stop> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Stop>
			orderByComparator);

	/**
	 * Returns an ordered range of all the stops where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching stops
	 */
	public java.util.List<Stop> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Stop>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first stop in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	public Stop findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Stop>
				orderByComparator)
		throws NoSuchStopException;

	/**
	 * Returns the first stop in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop, or <code>null</code> if a matching stop could not be found
	 */
	public Stop fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Stop>
			orderByComparator);

	/**
	 * Returns the last stop in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	public Stop findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Stop>
				orderByComparator)
		throws NoSuchStopException;

	/**
	 * Returns the last stop in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop, or <code>null</code> if a matching stop could not be found
	 */
	public Stop fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Stop>
			orderByComparator);

	/**
	 * Returns the stops before and after the current stop in the ordered set where uuid = &#63;.
	 *
	 * @param id the primary key of the current stop
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next stop
	 * @throws NoSuchStopException if a stop with the primary key could not be found
	 */
	public Stop[] findByUuid_PrevAndNext(
			long id, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Stop>
				orderByComparator)
		throws NoSuchStopException;

	/**
	 * Removes all the stops where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of stops where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching stops
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the stop where stop_id = &#63; or throws a <code>NoSuchStopException</code> if it could not be found.
	 *
	 * @param stop_id the stop_id
	 * @return the matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	public Stop findByStopId(String stop_id) throws NoSuchStopException;

	/**
	 * Returns the stop where stop_id = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param stop_id the stop_id
	 * @return the matching stop, or <code>null</code> if a matching stop could not be found
	 */
	public Stop fetchByStopId(String stop_id);

	/**
	 * Returns the stop where stop_id = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param stop_id the stop_id
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching stop, or <code>null</code> if a matching stop could not be found
	 */
	public Stop fetchByStopId(String stop_id, boolean useFinderCache);

	/**
	 * Removes the stop where stop_id = &#63; from the database.
	 *
	 * @param stop_id the stop_id
	 * @return the stop that was removed
	 */
	public Stop removeByStopId(String stop_id) throws NoSuchStopException;

	/**
	 * Returns the number of stops where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @return the number of matching stops
	 */
	public int countByStopId(String stop_id);

	/**
	 * Returns all the stops where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @return the matching stops
	 */
	public java.util.List<Stop> findByStopCode(String stop_code);

	/**
	 * Returns a range of all the stops where stop_code = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopModelImpl</code>.
	 * </p>
	 *
	 * @param stop_code the stop_code
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @return the range of matching stops
	 */
	public java.util.List<Stop> findByStopCode(
		String stop_code, int start, int end);

	/**
	 * Returns an ordered range of all the stops where stop_code = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopModelImpl</code>.
	 * </p>
	 *
	 * @param stop_code the stop_code
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching stops
	 */
	public java.util.List<Stop> findByStopCode(
		String stop_code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Stop>
			orderByComparator);

	/**
	 * Returns an ordered range of all the stops where stop_code = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopModelImpl</code>.
	 * </p>
	 *
	 * @param stop_code the stop_code
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching stops
	 */
	public java.util.List<Stop> findByStopCode(
		String stop_code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Stop>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first stop in the ordered set where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	public Stop findByStopCode_First(
			String stop_code,
			com.liferay.portal.kernel.util.OrderByComparator<Stop>
				orderByComparator)
		throws NoSuchStopException;

	/**
	 * Returns the first stop in the ordered set where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop, or <code>null</code> if a matching stop could not be found
	 */
	public Stop fetchByStopCode_First(
		String stop_code,
		com.liferay.portal.kernel.util.OrderByComparator<Stop>
			orderByComparator);

	/**
	 * Returns the last stop in the ordered set where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	public Stop findByStopCode_Last(
			String stop_code,
			com.liferay.portal.kernel.util.OrderByComparator<Stop>
				orderByComparator)
		throws NoSuchStopException;

	/**
	 * Returns the last stop in the ordered set where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop, or <code>null</code> if a matching stop could not be found
	 */
	public Stop fetchByStopCode_Last(
		String stop_code,
		com.liferay.portal.kernel.util.OrderByComparator<Stop>
			orderByComparator);

	/**
	 * Returns the stops before and after the current stop in the ordered set where stop_code = &#63;.
	 *
	 * @param id the primary key of the current stop
	 * @param stop_code the stop_code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next stop
	 * @throws NoSuchStopException if a stop with the primary key could not be found
	 */
	public Stop[] findByStopCode_PrevAndNext(
			long id, String stop_code,
			com.liferay.portal.kernel.util.OrderByComparator<Stop>
				orderByComparator)
		throws NoSuchStopException;

	/**
	 * Removes all the stops where stop_code = &#63; from the database.
	 *
	 * @param stop_code the stop_code
	 */
	public void removeByStopCode(String stop_code);

	/**
	 * Returns the number of stops where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @return the number of matching stops
	 */
	public int countByStopCode(String stop_code);

	/**
	 * Caches the stop in the entity cache if it is enabled.
	 *
	 * @param stop the stop
	 */
	public void cacheResult(Stop stop);

	/**
	 * Caches the stops in the entity cache if it is enabled.
	 *
	 * @param stops the stops
	 */
	public void cacheResult(java.util.List<Stop> stops);

	/**
	 * Creates a new stop with the primary key. Does not add the stop to the database.
	 *
	 * @param id the primary key for the new stop
	 * @return the new stop
	 */
	public Stop create(long id);

	/**
	 * Removes the stop with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the stop
	 * @return the stop that was removed
	 * @throws NoSuchStopException if a stop with the primary key could not be found
	 */
	public Stop remove(long id) throws NoSuchStopException;

	public Stop updateImpl(Stop stop);

	/**
	 * Returns the stop with the primary key or throws a <code>NoSuchStopException</code> if it could not be found.
	 *
	 * @param id the primary key of the stop
	 * @return the stop
	 * @throws NoSuchStopException if a stop with the primary key could not be found
	 */
	public Stop findByPrimaryKey(long id) throws NoSuchStopException;

	/**
	 * Returns the stop with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the stop
	 * @return the stop, or <code>null</code> if a stop with the primary key could not be found
	 */
	public Stop fetchByPrimaryKey(long id);

	/**
	 * Returns all the stops.
	 *
	 * @return the stops
	 */
	public java.util.List<Stop> findAll();

	/**
	 * Returns a range of all the stops.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @return the range of stops
	 */
	public java.util.List<Stop> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the stops.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of stops
	 */
	public java.util.List<Stop> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Stop>
			orderByComparator);

	/**
	 * Returns an ordered range of all the stops.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StopModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of stops
	 */
	public java.util.List<Stop> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Stop>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the stops from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of stops.
	 *
	 * @return the number of stops
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}