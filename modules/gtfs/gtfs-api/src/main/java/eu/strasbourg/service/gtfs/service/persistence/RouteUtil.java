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

import eu.strasbourg.service.gtfs.model.Route;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the route service. This utility wraps <code>eu.strasbourg.service.gtfs.service.persistence.impl.RoutePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see RoutePersistence
 * @generated
 */
@ProviderType
public class RouteUtil {

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
	public static void clearCache(Route route) {
		getPersistence().clearCache(route);
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
	public static Map<Serializable, Route> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Route> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Route> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Route> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Route> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Route update(Route route) {
		return getPersistence().update(route);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Route update(Route route, ServiceContext serviceContext) {
		return getPersistence().update(route, serviceContext);
	}

	/**
	 * Returns all the routes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching routes
	 */
	public static List<Route> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the routes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>RouteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of routes
	 * @param end the upper bound of the range of routes (not inclusive)
	 * @return the range of matching routes
	 */
	public static List<Route> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the routes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>RouteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of routes
	 * @param end the upper bound of the range of routes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching routes
	 */
	public static List<Route> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Route> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the routes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>RouteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of routes
	 * @param end the upper bound of the range of routes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching routes
	 */
	public static List<Route> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Route> orderByComparator, boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first route in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching route
	 * @throws NoSuchRouteException if a matching route could not be found
	 */
	public static Route findByUuid_First(
			String uuid, OrderByComparator<Route> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchRouteException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first route in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching route, or <code>null</code> if a matching route could not be found
	 */
	public static Route fetchByUuid_First(
		String uuid, OrderByComparator<Route> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last route in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching route
	 * @throws NoSuchRouteException if a matching route could not be found
	 */
	public static Route findByUuid_Last(
			String uuid, OrderByComparator<Route> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchRouteException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last route in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching route, or <code>null</code> if a matching route could not be found
	 */
	public static Route fetchByUuid_Last(
		String uuid, OrderByComparator<Route> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the routes before and after the current route in the ordered set where uuid = &#63;.
	 *
	 * @param id the primary key of the current route
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next route
	 * @throws NoSuchRouteException if a route with the primary key could not be found
	 */
	public static Route[] findByUuid_PrevAndNext(
			long id, String uuid, OrderByComparator<Route> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchRouteException {

		return getPersistence().findByUuid_PrevAndNext(
			id, uuid, orderByComparator);
	}

	/**
	 * Removes all the routes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of routes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching routes
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the route where route_id = &#63; or throws a <code>NoSuchRouteException</code> if it could not be found.
	 *
	 * @param route_id the route_id
	 * @return the matching route
	 * @throws NoSuchRouteException if a matching route could not be found
	 */
	public static Route findByRouteId(String route_id)
		throws eu.strasbourg.service.gtfs.exception.NoSuchRouteException {

		return getPersistence().findByRouteId(route_id);
	}

	/**
	 * Returns the route where route_id = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param route_id the route_id
	 * @return the matching route, or <code>null</code> if a matching route could not be found
	 */
	public static Route fetchByRouteId(String route_id) {
		return getPersistence().fetchByRouteId(route_id);
	}

	/**
	 * Returns the route where route_id = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param route_id the route_id
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching route, or <code>null</code> if a matching route could not be found
	 */
	public static Route fetchByRouteId(
		String route_id, boolean retrieveFromCache) {

		return getPersistence().fetchByRouteId(route_id, retrieveFromCache);
	}

	/**
	 * Removes the route where route_id = &#63; from the database.
	 *
	 * @param route_id the route_id
	 * @return the route that was removed
	 */
	public static Route removeByRouteId(String route_id)
		throws eu.strasbourg.service.gtfs.exception.NoSuchRouteException {

		return getPersistence().removeByRouteId(route_id);
	}

	/**
	 * Returns the number of routes where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @return the number of matching routes
	 */
	public static int countByRouteId(String route_id) {
		return getPersistence().countByRouteId(route_id);
	}

	/**
	 * Caches the route in the entity cache if it is enabled.
	 *
	 * @param route the route
	 */
	public static void cacheResult(Route route) {
		getPersistence().cacheResult(route);
	}

	/**
	 * Caches the routes in the entity cache if it is enabled.
	 *
	 * @param routes the routes
	 */
	public static void cacheResult(List<Route> routes) {
		getPersistence().cacheResult(routes);
	}

	/**
	 * Creates a new route with the primary key. Does not add the route to the database.
	 *
	 * @param id the primary key for the new route
	 * @return the new route
	 */
	public static Route create(long id) {
		return getPersistence().create(id);
	}

	/**
	 * Removes the route with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the route
	 * @return the route that was removed
	 * @throws NoSuchRouteException if a route with the primary key could not be found
	 */
	public static Route remove(long id)
		throws eu.strasbourg.service.gtfs.exception.NoSuchRouteException {

		return getPersistence().remove(id);
	}

	public static Route updateImpl(Route route) {
		return getPersistence().updateImpl(route);
	}

	/**
	 * Returns the route with the primary key or throws a <code>NoSuchRouteException</code> if it could not be found.
	 *
	 * @param id the primary key of the route
	 * @return the route
	 * @throws NoSuchRouteException if a route with the primary key could not be found
	 */
	public static Route findByPrimaryKey(long id)
		throws eu.strasbourg.service.gtfs.exception.NoSuchRouteException {

		return getPersistence().findByPrimaryKey(id);
	}

	/**
	 * Returns the route with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the route
	 * @return the route, or <code>null</code> if a route with the primary key could not be found
	 */
	public static Route fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	 * Returns all the routes.
	 *
	 * @return the routes
	 */
	public static List<Route> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the routes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>RouteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of routes
	 * @param end the upper bound of the range of routes (not inclusive)
	 * @return the range of routes
	 */
	public static List<Route> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the routes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>RouteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of routes
	 * @param end the upper bound of the range of routes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of routes
	 */
	public static List<Route> findAll(
		int start, int end, OrderByComparator<Route> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the routes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>RouteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of routes
	 * @param end the upper bound of the range of routes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of routes
	 */
	public static List<Route> findAll(
		int start, int end, OrderByComparator<Route> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the routes from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of routes.
	 *
	 * @return the number of routes
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RoutePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RoutePersistence, RoutePersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RoutePersistence.class);

		ServiceTracker<RoutePersistence, RoutePersistence> serviceTracker =
			new ServiceTracker<RoutePersistence, RoutePersistence>(
				bundle.getBundleContext(), RoutePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}