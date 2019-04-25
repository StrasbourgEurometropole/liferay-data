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

import eu.strasbourg.service.gtfs.exception.NoSuchTripException;
import eu.strasbourg.service.gtfs.model.Trip;

/**
 * The persistence interface for the trip service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.gtfs.service.persistence.impl.TripPersistenceImpl
 * @see TripUtil
 * @generated
 */
@ProviderType
public interface TripPersistence extends BasePersistence<Trip> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TripUtil} to access the trip persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the trips where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching trips
	*/
	public java.util.List<Trip> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the trips where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TripModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of trips
	* @param end the upper bound of the range of trips (not inclusive)
	* @return the range of matching trips
	*/
	public java.util.List<Trip> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the trips where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TripModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of trips
	* @param end the upper bound of the range of trips (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching trips
	*/
	public java.util.List<Trip> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Trip> orderByComparator);

	/**
	* Returns an ordered range of all the trips where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TripModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of trips
	* @param end the upper bound of the range of trips (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching trips
	*/
	public java.util.List<Trip> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Trip> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first trip in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching trip
	* @throws NoSuchTripException if a matching trip could not be found
	*/
	public Trip findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Trip> orderByComparator)
		throws NoSuchTripException;

	/**
	* Returns the first trip in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching trip, or <code>null</code> if a matching trip could not be found
	*/
	public Trip fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Trip> orderByComparator);

	/**
	* Returns the last trip in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching trip
	* @throws NoSuchTripException if a matching trip could not be found
	*/
	public Trip findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Trip> orderByComparator)
		throws NoSuchTripException;

	/**
	* Returns the last trip in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching trip, or <code>null</code> if a matching trip could not be found
	*/
	public Trip fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Trip> orderByComparator);

	/**
	* Returns the trips before and after the current trip in the ordered set where uuid = &#63;.
	*
	* @param id the primary key of the current trip
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next trip
	* @throws NoSuchTripException if a trip with the primary key could not be found
	*/
	public Trip[] findByUuid_PrevAndNext(long id, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Trip> orderByComparator)
		throws NoSuchTripException;

	/**
	* Removes all the trips where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of trips where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching trips
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Caches the trip in the entity cache if it is enabled.
	*
	* @param trip the trip
	*/
	public void cacheResult(Trip trip);

	/**
	* Caches the trips in the entity cache if it is enabled.
	*
	* @param trips the trips
	*/
	public void cacheResult(java.util.List<Trip> trips);

	/**
	* Creates a new trip with the primary key. Does not add the trip to the database.
	*
	* @param id the primary key for the new trip
	* @return the new trip
	*/
	public Trip create(long id);

	/**
	* Removes the trip with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the trip
	* @return the trip that was removed
	* @throws NoSuchTripException if a trip with the primary key could not be found
	*/
	public Trip remove(long id) throws NoSuchTripException;

	public Trip updateImpl(Trip trip);

	/**
	* Returns the trip with the primary key or throws a {@link NoSuchTripException} if it could not be found.
	*
	* @param id the primary key of the trip
	* @return the trip
	* @throws NoSuchTripException if a trip with the primary key could not be found
	*/
	public Trip findByPrimaryKey(long id) throws NoSuchTripException;

	/**
	* Returns the trip with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the trip
	* @return the trip, or <code>null</code> if a trip with the primary key could not be found
	*/
	public Trip fetchByPrimaryKey(long id);

	@Override
	public java.util.Map<java.io.Serializable, Trip> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the trips.
	*
	* @return the trips
	*/
	public java.util.List<Trip> findAll();

	/**
	* Returns a range of all the trips.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TripModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of trips
	* @param end the upper bound of the range of trips (not inclusive)
	* @return the range of trips
	*/
	public java.util.List<Trip> findAll(int start, int end);

	/**
	* Returns an ordered range of all the trips.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TripModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of trips
	* @param end the upper bound of the range of trips (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of trips
	*/
	public java.util.List<Trip> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Trip> orderByComparator);

	/**
	* Returns an ordered range of all the trips.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TripModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of trips
	* @param end the upper bound of the range of trips (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of trips
	*/
	public java.util.List<Trip> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Trip> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the trips from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of trips.
	*
	* @return the number of trips
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}