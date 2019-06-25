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

import eu.strasbourg.service.gtfs.model.Stop;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the stop service. This utility wraps {@link eu.strasbourg.service.gtfs.service.persistence.impl.StopPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see StopPersistence
 * @see eu.strasbourg.service.gtfs.service.persistence.impl.StopPersistenceImpl
 * @generated
 */
@ProviderType
public class StopUtil {
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
	public static void clearCache(Stop stop) {
		getPersistence().clearCache(stop);
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
	public static List<Stop> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Stop> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Stop> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Stop> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Stop update(Stop stop) {
		return getPersistence().update(stop);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Stop update(Stop stop, ServiceContext serviceContext) {
		return getPersistence().update(stop, serviceContext);
	}

	/**
	* Returns all the stops where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching stops
	*/
	public static List<Stop> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the stops where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of stops
	* @param end the upper bound of the range of stops (not inclusive)
	* @return the range of matching stops
	*/
	public static List<Stop> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the stops where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of stops
	* @param end the upper bound of the range of stops (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching stops
	*/
	public static List<Stop> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Stop> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the stops where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of stops
	* @param end the upper bound of the range of stops (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching stops
	*/
	public static List<Stop> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Stop> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first stop in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching stop
	* @throws NoSuchStopException if a matching stop could not be found
	*/
	public static Stop findByUuid_First(java.lang.String uuid,
		OrderByComparator<Stop> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first stop in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching stop, or <code>null</code> if a matching stop could not be found
	*/
	public static Stop fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Stop> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last stop in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching stop
	* @throws NoSuchStopException if a matching stop could not be found
	*/
	public static Stop findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Stop> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last stop in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching stop, or <code>null</code> if a matching stop could not be found
	*/
	public static Stop fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Stop> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the stops before and after the current stop in the ordered set where uuid = &#63;.
	*
	* @param id the primary key of the current stop
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next stop
	* @throws NoSuchStopException if a stop with the primary key could not be found
	*/
	public static Stop[] findByUuid_PrevAndNext(long id, java.lang.String uuid,
		OrderByComparator<Stop> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {
		return getPersistence()
				   .findByUuid_PrevAndNext(id, uuid, orderByComparator);
	}

	/**
	* Removes all the stops where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of stops where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching stops
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the stops where stop_id = &#63;.
	*
	* @param stop_id the stop_id
	* @return the matching stops
	*/
	public static List<Stop> findByStopId(java.lang.String stop_id) {
		return getPersistence().findByStopId(stop_id);
	}

	/**
	* Returns a range of all the stops where stop_id = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param stop_id the stop_id
	* @param start the lower bound of the range of stops
	* @param end the upper bound of the range of stops (not inclusive)
	* @return the range of matching stops
	*/
	public static List<Stop> findByStopId(java.lang.String stop_id, int start,
		int end) {
		return getPersistence().findByStopId(stop_id, start, end);
	}

	/**
	* Returns an ordered range of all the stops where stop_id = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param stop_id the stop_id
	* @param start the lower bound of the range of stops
	* @param end the upper bound of the range of stops (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching stops
	*/
	public static List<Stop> findByStopId(java.lang.String stop_id, int start,
		int end, OrderByComparator<Stop> orderByComparator) {
		return getPersistence()
				   .findByStopId(stop_id, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the stops where stop_id = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param stop_id the stop_id
	* @param start the lower bound of the range of stops
	* @param end the upper bound of the range of stops (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching stops
	*/
	public static List<Stop> findByStopId(java.lang.String stop_id, int start,
		int end, OrderByComparator<Stop> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByStopId(stop_id, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first stop in the ordered set where stop_id = &#63;.
	*
	* @param stop_id the stop_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching stop
	* @throws NoSuchStopException if a matching stop could not be found
	*/
	public static Stop findByStopId_First(java.lang.String stop_id,
		OrderByComparator<Stop> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {
		return getPersistence().findByStopId_First(stop_id, orderByComparator);
	}

	/**
	* Returns the first stop in the ordered set where stop_id = &#63;.
	*
	* @param stop_id the stop_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching stop, or <code>null</code> if a matching stop could not be found
	*/
	public static Stop fetchByStopId_First(java.lang.String stop_id,
		OrderByComparator<Stop> orderByComparator) {
		return getPersistence().fetchByStopId_First(stop_id, orderByComparator);
	}

	/**
	* Returns the last stop in the ordered set where stop_id = &#63;.
	*
	* @param stop_id the stop_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching stop
	* @throws NoSuchStopException if a matching stop could not be found
	*/
	public static Stop findByStopId_Last(java.lang.String stop_id,
		OrderByComparator<Stop> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {
		return getPersistence().findByStopId_Last(stop_id, orderByComparator);
	}

	/**
	* Returns the last stop in the ordered set where stop_id = &#63;.
	*
	* @param stop_id the stop_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching stop, or <code>null</code> if a matching stop could not be found
	*/
	public static Stop fetchByStopId_Last(java.lang.String stop_id,
		OrderByComparator<Stop> orderByComparator) {
		return getPersistence().fetchByStopId_Last(stop_id, orderByComparator);
	}

	/**
	* Returns the stops before and after the current stop in the ordered set where stop_id = &#63;.
	*
	* @param id the primary key of the current stop
	* @param stop_id the stop_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next stop
	* @throws NoSuchStopException if a stop with the primary key could not be found
	*/
	public static Stop[] findByStopId_PrevAndNext(long id,
		java.lang.String stop_id, OrderByComparator<Stop> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {
		return getPersistence()
				   .findByStopId_PrevAndNext(id, stop_id, orderByComparator);
	}

	/**
	* Removes all the stops where stop_id = &#63; from the database.
	*
	* @param stop_id the stop_id
	*/
	public static void removeByStopId(java.lang.String stop_id) {
		getPersistence().removeByStopId(stop_id);
	}

	/**
	* Returns the number of stops where stop_id = &#63;.
	*
	* @param stop_id the stop_id
	* @return the number of matching stops
	*/
	public static int countByStopId(java.lang.String stop_id) {
		return getPersistence().countByStopId(stop_id);
	}

	/**
	* Returns all the stops where stop_code = &#63;.
	*
	* @param stop_code the stop_code
	* @return the matching stops
	*/
	public static List<Stop> findByStopCode(java.lang.String stop_code) {
		return getPersistence().findByStopCode(stop_code);
	}

	/**
	* Returns a range of all the stops where stop_code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param stop_code the stop_code
	* @param start the lower bound of the range of stops
	* @param end the upper bound of the range of stops (not inclusive)
	* @return the range of matching stops
	*/
	public static List<Stop> findByStopCode(java.lang.String stop_code,
		int start, int end) {
		return getPersistence().findByStopCode(stop_code, start, end);
	}

	/**
	* Returns an ordered range of all the stops where stop_code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param stop_code the stop_code
	* @param start the lower bound of the range of stops
	* @param end the upper bound of the range of stops (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching stops
	*/
	public static List<Stop> findByStopCode(java.lang.String stop_code,
		int start, int end, OrderByComparator<Stop> orderByComparator) {
		return getPersistence()
				   .findByStopCode(stop_code, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the stops where stop_code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param stop_code the stop_code
	* @param start the lower bound of the range of stops
	* @param end the upper bound of the range of stops (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching stops
	*/
	public static List<Stop> findByStopCode(java.lang.String stop_code,
		int start, int end, OrderByComparator<Stop> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByStopCode(stop_code, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first stop in the ordered set where stop_code = &#63;.
	*
	* @param stop_code the stop_code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching stop
	* @throws NoSuchStopException if a matching stop could not be found
	*/
	public static Stop findByStopCode_First(java.lang.String stop_code,
		OrderByComparator<Stop> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {
		return getPersistence()
				   .findByStopCode_First(stop_code, orderByComparator);
	}

	/**
	* Returns the first stop in the ordered set where stop_code = &#63;.
	*
	* @param stop_code the stop_code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching stop, or <code>null</code> if a matching stop could not be found
	*/
	public static Stop fetchByStopCode_First(java.lang.String stop_code,
		OrderByComparator<Stop> orderByComparator) {
		return getPersistence()
				   .fetchByStopCode_First(stop_code, orderByComparator);
	}

	/**
	* Returns the last stop in the ordered set where stop_code = &#63;.
	*
	* @param stop_code the stop_code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching stop
	* @throws NoSuchStopException if a matching stop could not be found
	*/
	public static Stop findByStopCode_Last(java.lang.String stop_code,
		OrderByComparator<Stop> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {
		return getPersistence().findByStopCode_Last(stop_code, orderByComparator);
	}

	/**
	* Returns the last stop in the ordered set where stop_code = &#63;.
	*
	* @param stop_code the stop_code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching stop, or <code>null</code> if a matching stop could not be found
	*/
	public static Stop fetchByStopCode_Last(java.lang.String stop_code,
		OrderByComparator<Stop> orderByComparator) {
		return getPersistence()
				   .fetchByStopCode_Last(stop_code, orderByComparator);
	}

	/**
	* Returns the stops before and after the current stop in the ordered set where stop_code = &#63;.
	*
	* @param id the primary key of the current stop
	* @param stop_code the stop_code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next stop
	* @throws NoSuchStopException if a stop with the primary key could not be found
	*/
	public static Stop[] findByStopCode_PrevAndNext(long id,
		java.lang.String stop_code, OrderByComparator<Stop> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {
		return getPersistence()
				   .findByStopCode_PrevAndNext(id, stop_code, orderByComparator);
	}

	/**
	* Removes all the stops where stop_code = &#63; from the database.
	*
	* @param stop_code the stop_code
	*/
	public static void removeByStopCode(java.lang.String stop_code) {
		getPersistence().removeByStopCode(stop_code);
	}

	/**
	* Returns the number of stops where stop_code = &#63;.
	*
	* @param stop_code the stop_code
	* @return the number of matching stops
	*/
	public static int countByStopCode(java.lang.String stop_code) {
		return getPersistence().countByStopCode(stop_code);
	}

	/**
	* Caches the stop in the entity cache if it is enabled.
	*
	* @param stop the stop
	*/
	public static void cacheResult(Stop stop) {
		getPersistence().cacheResult(stop);
	}

	/**
	* Caches the stops in the entity cache if it is enabled.
	*
	* @param stops the stops
	*/
	public static void cacheResult(List<Stop> stops) {
		getPersistence().cacheResult(stops);
	}

	/**
	* Creates a new stop with the primary key. Does not add the stop to the database.
	*
	* @param id the primary key for the new stop
	* @return the new stop
	*/
	public static Stop create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the stop with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the stop
	* @return the stop that was removed
	* @throws NoSuchStopException if a stop with the primary key could not be found
	*/
	public static Stop remove(long id)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {
		return getPersistence().remove(id);
	}

	public static Stop updateImpl(Stop stop) {
		return getPersistence().updateImpl(stop);
	}

	/**
	* Returns the stop with the primary key or throws a {@link NoSuchStopException} if it could not be found.
	*
	* @param id the primary key of the stop
	* @return the stop
	* @throws NoSuchStopException if a stop with the primary key could not be found
	*/
	public static Stop findByPrimaryKey(long id)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the stop with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the stop
	* @return the stop, or <code>null</code> if a stop with the primary key could not be found
	*/
	public static Stop fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	public static java.util.Map<java.io.Serializable, Stop> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the stops.
	*
	* @return the stops
	*/
	public static List<Stop> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the stops.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of stops
	* @param end the upper bound of the range of stops (not inclusive)
	* @return the range of stops
	*/
	public static List<Stop> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the stops.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of stops
	* @param end the upper bound of the range of stops (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of stops
	*/
	public static List<Stop> findAll(int start, int end,
		OrderByComparator<Stop> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the stops.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of stops
	* @param end the upper bound of the range of stops (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of stops
	*/
	public static List<Stop> findAll(int start, int end,
		OrderByComparator<Stop> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the stops from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of stops.
	*
	* @return the number of stops
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StopPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StopPersistence, StopPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StopPersistence.class);
}