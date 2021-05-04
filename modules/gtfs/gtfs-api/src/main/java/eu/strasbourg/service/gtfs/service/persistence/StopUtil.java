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

import eu.strasbourg.service.gtfs.model.Stop;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the stop service. This utility wraps <code>eu.strasbourg.service.gtfs.service.persistence.impl.StopPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see StopPersistence
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
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Stop> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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
	public static List<Stop> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Stop> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Stop> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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
	 * Returns the stop where stop_id = &#63; or throws a <code>NoSuchStopException</code> if it could not be found.
	 *
	 * @param stop_id the stop_id
	 * @return the matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	public static Stop findByStopId(String stop_id)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {

		return getPersistence().findByStopId(stop_id);
	}

	/**
	 * Returns the stop where stop_id = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param stop_id the stop_id
	 * @return the matching stop, or <code>null</code> if a matching stop could not be found
	 */
	public static Stop fetchByStopId(String stop_id) {
		return getPersistence().fetchByStopId(stop_id);
	}

	/**
	 * Returns the stop where stop_id = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param stop_id the stop_id
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching stop, or <code>null</code> if a matching stop could not be found
	 */
	public static Stop fetchByStopId(
		String stop_id, boolean retrieveFromCache) {

		return getPersistence().fetchByStopId(stop_id, retrieveFromCache);
	}

	/**
	 * Removes the stop where stop_id = &#63; from the database.
	 *
	 * @param stop_id the stop_id
	 * @return the stop that was removed
	 */
	public static Stop removeByStopId(String stop_id)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {

		return getPersistence().removeByStopId(stop_id);
	}

	/**
	 * Returns the number of stops where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @return the number of matching stops
	 */
	public static int countByStopId(String stop_id) {
		return getPersistence().countByStopId(stop_id);
	}

	/**
	 * Returns all the stops where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @return the matching stops
	 */
	public static List<Stop> findByStopCode(String stop_code) {
		return getPersistence().findByStopCode(stop_code);
	}

	/**
	 * Returns a range of all the stops where stop_code = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>StopModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stop_code the stop_code
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @return the range of matching stops
	 */
	public static List<Stop> findByStopCode(
		String stop_code, int start, int end) {

		return getPersistence().findByStopCode(stop_code, start, end);
	}

	/**
	 * Returns an ordered range of all the stops where stop_code = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>StopModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stop_code the stop_code
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching stops
	 */
	public static List<Stop> findByStopCode(
		String stop_code, int start, int end,
		OrderByComparator<Stop> orderByComparator) {

		return getPersistence().findByStopCode(
			stop_code, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the stops where stop_code = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>StopModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stop_code the stop_code
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching stops
	 */
	public static List<Stop> findByStopCode(
		String stop_code, int start, int end,
		OrderByComparator<Stop> orderByComparator, boolean retrieveFromCache) {

		return getPersistence().findByStopCode(
			stop_code, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first stop in the ordered set where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	public static Stop findByStopCode_First(
			String stop_code, OrderByComparator<Stop> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {

		return getPersistence().findByStopCode_First(
			stop_code, orderByComparator);
	}

	/**
	 * Returns the first stop in the ordered set where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop, or <code>null</code> if a matching stop could not be found
	 */
	public static Stop fetchByStopCode_First(
		String stop_code, OrderByComparator<Stop> orderByComparator) {

		return getPersistence().fetchByStopCode_First(
			stop_code, orderByComparator);
	}

	/**
	 * Returns the last stop in the ordered set where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	public static Stop findByStopCode_Last(
			String stop_code, OrderByComparator<Stop> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {

		return getPersistence().findByStopCode_Last(
			stop_code, orderByComparator);
	}

	/**
	 * Returns the last stop in the ordered set where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop, or <code>null</code> if a matching stop could not be found
	 */
	public static Stop fetchByStopCode_Last(
		String stop_code, OrderByComparator<Stop> orderByComparator) {

		return getPersistence().fetchByStopCode_Last(
			stop_code, orderByComparator);
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
	public static Stop[] findByStopCode_PrevAndNext(
			long id, String stop_code,
			OrderByComparator<Stop> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchStopException {

		return getPersistence().findByStopCode_PrevAndNext(
			id, stop_code, orderByComparator);
	}

	/**
	 * Removes all the stops where stop_code = &#63; from the database.
	 *
	 * @param stop_code the stop_code
	 */
	public static void removeByStopCode(String stop_code) {
		getPersistence().removeByStopCode(stop_code);
	}

	/**
	 * Returns the number of stops where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @return the number of matching stops
	 */
	public static int countByStopCode(String stop_code) {
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
	 * Returns the stop with the primary key or throws a <code>NoSuchStopException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>StopModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>StopModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of stops
	 */
	public static List<Stop> findAll(
		int start, int end, OrderByComparator<Stop> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the stops.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>StopModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of stops
	 */
	public static List<Stop> findAll(
		int start, int end, OrderByComparator<Stop> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
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

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StopPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StopPersistence, StopPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(StopPersistence.class);

		ServiceTracker<StopPersistence, StopPersistence> serviceTracker =
			new ServiceTracker<StopPersistence, StopPersistence>(
				bundle.getBundleContext(), StopPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}