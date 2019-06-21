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

import eu.strasbourg.service.gtfs.exception.NoSuchRouteException;
import eu.strasbourg.service.gtfs.model.Route;

/**
 * The persistence interface for the route service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.gtfs.service.persistence.impl.RoutePersistenceImpl
 * @see RouteUtil
 * @generated
 */
@ProviderType
public interface RoutePersistence extends BasePersistence<Route> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RouteUtil} to access the route persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the routes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching routes
	*/
	public java.util.List<Route> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the routes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RouteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of routes
	* @param end the upper bound of the range of routes (not inclusive)
	* @return the range of matching routes
	*/
	public java.util.List<Route> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the routes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RouteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of routes
	* @param end the upper bound of the range of routes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching routes
	*/
	public java.util.List<Route> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator);

	/**
	* Returns an ordered range of all the routes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RouteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of routes
	* @param end the upper bound of the range of routes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching routes
	*/
	public java.util.List<Route> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first route in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching route
	* @throws NoSuchRouteException if a matching route could not be found
	*/
	public Route findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator)
		throws NoSuchRouteException;

	/**
	* Returns the first route in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching route, or <code>null</code> if a matching route could not be found
	*/
	public Route fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator);

	/**
	* Returns the last route in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching route
	* @throws NoSuchRouteException if a matching route could not be found
	*/
	public Route findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator)
		throws NoSuchRouteException;

	/**
	* Returns the last route in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching route, or <code>null</code> if a matching route could not be found
	*/
	public Route fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator);

	/**
	* Returns the routes before and after the current route in the ordered set where uuid = &#63;.
	*
	* @param id the primary key of the current route
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next route
	* @throws NoSuchRouteException if a route with the primary key could not be found
	*/
	public Route[] findByUuid_PrevAndNext(long id, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator)
		throws NoSuchRouteException;

	/**
	* Removes all the routes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of routes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching routes
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the routes where route_id = &#63;.
	*
	* @param route_id the route_id
	* @return the matching routes
	*/
	public java.util.List<Route> findByRouteId(java.lang.String route_id);

	/**
	* Returns a range of all the routes where route_id = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RouteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param route_id the route_id
	* @param start the lower bound of the range of routes
	* @param end the upper bound of the range of routes (not inclusive)
	* @return the range of matching routes
	*/
	public java.util.List<Route> findByRouteId(java.lang.String route_id,
		int start, int end);

	/**
	* Returns an ordered range of all the routes where route_id = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RouteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param route_id the route_id
	* @param start the lower bound of the range of routes
	* @param end the upper bound of the range of routes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching routes
	*/
	public java.util.List<Route> findByRouteId(java.lang.String route_id,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator);

	/**
	* Returns an ordered range of all the routes where route_id = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RouteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param route_id the route_id
	* @param start the lower bound of the range of routes
	* @param end the upper bound of the range of routes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching routes
	*/
	public java.util.List<Route> findByRouteId(java.lang.String route_id,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first route in the ordered set where route_id = &#63;.
	*
	* @param route_id the route_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching route
	* @throws NoSuchRouteException if a matching route could not be found
	*/
	public Route findByRouteId_First(java.lang.String route_id,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator)
		throws NoSuchRouteException;

	/**
	* Returns the first route in the ordered set where route_id = &#63;.
	*
	* @param route_id the route_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching route, or <code>null</code> if a matching route could not be found
	*/
	public Route fetchByRouteId_First(java.lang.String route_id,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator);

	/**
	* Returns the last route in the ordered set where route_id = &#63;.
	*
	* @param route_id the route_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching route
	* @throws NoSuchRouteException if a matching route could not be found
	*/
	public Route findByRouteId_Last(java.lang.String route_id,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator)
		throws NoSuchRouteException;

	/**
	* Returns the last route in the ordered set where route_id = &#63;.
	*
	* @param route_id the route_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching route, or <code>null</code> if a matching route could not be found
	*/
	public Route fetchByRouteId_Last(java.lang.String route_id,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator);

	/**
	* Returns the routes before and after the current route in the ordered set where route_id = &#63;.
	*
	* @param id the primary key of the current route
	* @param route_id the route_id
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next route
	* @throws NoSuchRouteException if a route with the primary key could not be found
	*/
	public Route[] findByRouteId_PrevAndNext(long id,
		java.lang.String route_id,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator)
		throws NoSuchRouteException;

	/**
	* Removes all the routes where route_id = &#63; from the database.
	*
	* @param route_id the route_id
	*/
	public void removeByRouteId(java.lang.String route_id);

	/**
	* Returns the number of routes where route_id = &#63;.
	*
	* @param route_id the route_id
	* @return the number of matching routes
	*/
	public int countByRouteId(java.lang.String route_id);

	/**
	* Caches the route in the entity cache if it is enabled.
	*
	* @param route the route
	*/
	public void cacheResult(Route route);

	/**
	* Caches the routes in the entity cache if it is enabled.
	*
	* @param routes the routes
	*/
	public void cacheResult(java.util.List<Route> routes);

	/**
	* Creates a new route with the primary key. Does not add the route to the database.
	*
	* @param id the primary key for the new route
	* @return the new route
	*/
	public Route create(long id);

	/**
	* Removes the route with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the route
	* @return the route that was removed
	* @throws NoSuchRouteException if a route with the primary key could not be found
	*/
	public Route remove(long id) throws NoSuchRouteException;

	public Route updateImpl(Route route);

	/**
	* Returns the route with the primary key or throws a {@link NoSuchRouteException} if it could not be found.
	*
	* @param id the primary key of the route
	* @return the route
	* @throws NoSuchRouteException if a route with the primary key could not be found
	*/
	public Route findByPrimaryKey(long id) throws NoSuchRouteException;

	/**
	* Returns the route with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the route
	* @return the route, or <code>null</code> if a route with the primary key could not be found
	*/
	public Route fetchByPrimaryKey(long id);

	@Override
	public java.util.Map<java.io.Serializable, Route> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the routes.
	*
	* @return the routes
	*/
	public java.util.List<Route> findAll();

	/**
	* Returns a range of all the routes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RouteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of routes
	* @param end the upper bound of the range of routes (not inclusive)
	* @return the range of routes
	*/
	public java.util.List<Route> findAll(int start, int end);

	/**
	* Returns an ordered range of all the routes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RouteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of routes
	* @param end the upper bound of the range of routes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of routes
	*/
	public java.util.List<Route> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator);

	/**
	* Returns an ordered range of all the routes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RouteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of routes
	* @param end the upper bound of the range of routes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of routes
	*/
	public java.util.List<Route> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Route> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the routes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of routes.
	*
	* @return the number of routes
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}