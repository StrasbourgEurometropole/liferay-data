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

package eu.strasbourg.service.gtfs.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.gtfs.exception.NoSuchRouteException;
import eu.strasbourg.service.gtfs.model.Route;
import eu.strasbourg.service.gtfs.model.impl.RouteImpl;
import eu.strasbourg.service.gtfs.model.impl.RouteModelImpl;
import eu.strasbourg.service.gtfs.service.persistence.RoutePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the route service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @generated
 */
@ProviderType
public class RoutePersistenceImpl
	extends BasePersistenceImpl<Route> implements RoutePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>RouteUtil</code> to access the route persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		RouteImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByRouteId;
	private FinderPath _finderPathCountByRouteId;

	/**
	 * Returns the route where route_id = &#63; or throws a <code>NoSuchRouteException</code> if it could not be found.
	 *
	 * @param route_id the route_id
	 * @return the matching route
	 * @throws NoSuchRouteException if a matching route could not be found
	 */
	@Override
	public Route findByRouteId(String route_id) throws NoSuchRouteException {
		Route route = fetchByRouteId(route_id);

		if (route == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("route_id=");
			msg.append(route_id);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchRouteException(msg.toString());
		}

		return route;
	}

	/**
	 * Returns the route where route_id = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param route_id the route_id
	 * @return the matching route, or <code>null</code> if a matching route could not be found
	 */
	@Override
	public Route fetchByRouteId(String route_id) {
		return fetchByRouteId(route_id, true);
	}

	/**
	 * Returns the route where route_id = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param route_id the route_id
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching route, or <code>null</code> if a matching route could not be found
	 */
	@Override
	public Route fetchByRouteId(String route_id, boolean retrieveFromCache) {
		route_id = Objects.toString(route_id, "");

		Object[] finderArgs = new Object[] {route_id};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByRouteId, finderArgs, this);
		}

		if (result instanceof Route) {
			Route route = (Route)result;

			if (!Objects.equals(route_id, route.getRoute_id())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ROUTE_WHERE);

			boolean bindRoute_id = false;

			if (route_id.isEmpty()) {
				query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_3);
			}
			else {
				bindRoute_id = true;

				query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRoute_id) {
					qPos.add(route_id);
				}

				List<Route> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByRouteId, finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"RoutePersistenceImpl.fetchByRouteId(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Route route = list.get(0);

					result = route;

					cacheResult(route);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByRouteId, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Route)result;
		}
	}

	/**
	 * Removes the route where route_id = &#63; from the database.
	 *
	 * @param route_id the route_id
	 * @return the route that was removed
	 */
	@Override
	public Route removeByRouteId(String route_id) throws NoSuchRouteException {
		Route route = findByRouteId(route_id);

		return remove(route);
	}

	/**
	 * Returns the number of routes where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @return the number of matching routes
	 */
	@Override
	public int countByRouteId(String route_id) {
		route_id = Objects.toString(route_id, "");

		FinderPath finderPath = _finderPathCountByRouteId;

		Object[] finderArgs = new Object[] {route_id};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ROUTE_WHERE);

			boolean bindRoute_id = false;

			if (route_id.isEmpty()) {
				query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_3);
			}
			else {
				bindRoute_id = true;

				query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRoute_id) {
					qPos.add(route_id);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ROUTEID_ROUTE_ID_2 =
		"route.route_id = ?";

	private static final String _FINDER_COLUMN_ROUTEID_ROUTE_ID_3 =
		"(route.route_id IS NULL OR route.route_id = '')";

	public RoutePersistenceImpl() {
		setModelClass(Route.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the route in the entity cache if it is enabled.
	 *
	 * @param route the route
	 */
	@Override
	public void cacheResult(Route route) {
		entityCache.putResult(
			RouteModelImpl.ENTITY_CACHE_ENABLED, RouteImpl.class,
			route.getPrimaryKey(), route);

		finderCache.putResult(
			_finderPathFetchByRouteId, new Object[] {route.getRoute_id()},
			route);

		route.resetOriginalValues();
	}

	/**
	 * Caches the routes in the entity cache if it is enabled.
	 *
	 * @param routes the routes
	 */
	@Override
	public void cacheResult(List<Route> routes) {
		for (Route route : routes) {
			if (entityCache.getResult(
					RouteModelImpl.ENTITY_CACHE_ENABLED, RouteImpl.class,
					route.getPrimaryKey()) == null) {

				cacheResult(route);
			}
			else {
				route.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all routes.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RouteImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the route.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Route route) {
		entityCache.removeResult(
			RouteModelImpl.ENTITY_CACHE_ENABLED, RouteImpl.class,
			route.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((RouteModelImpl)route, true);
	}

	@Override
	public void clearCache(List<Route> routes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Route route : routes) {
			entityCache.removeResult(
				RouteModelImpl.ENTITY_CACHE_ENABLED, RouteImpl.class,
				route.getPrimaryKey());

			clearUniqueFindersCache((RouteModelImpl)route, true);
		}
	}

	protected void cacheUniqueFindersCache(RouteModelImpl routeModelImpl) {
		Object[] args = new Object[] {routeModelImpl.getRoute_id()};

		finderCache.putResult(
			_finderPathCountByRouteId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByRouteId, args, routeModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		RouteModelImpl routeModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {routeModelImpl.getRoute_id()};

			finderCache.removeResult(_finderPathCountByRouteId, args);
			finderCache.removeResult(_finderPathFetchByRouteId, args);
		}

		if ((routeModelImpl.getColumnBitmask() &
			 _finderPathFetchByRouteId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {routeModelImpl.getOriginalRoute_id()};

			finderCache.removeResult(_finderPathCountByRouteId, args);
			finderCache.removeResult(_finderPathFetchByRouteId, args);
		}
	}

	/**
	 * Creates a new route with the primary key. Does not add the route to the database.
	 *
	 * @param id the primary key for the new route
	 * @return the new route
	 */
	@Override
	public Route create(long id) {
		Route route = new RouteImpl();

		route.setNew(true);
		route.setPrimaryKey(id);

		return route;
	}

	/**
	 * Removes the route with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the route
	 * @return the route that was removed
	 * @throws NoSuchRouteException if a route with the primary key could not be found
	 */
	@Override
	public Route remove(long id) throws NoSuchRouteException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the route with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the route
	 * @return the route that was removed
	 * @throws NoSuchRouteException if a route with the primary key could not be found
	 */
	@Override
	public Route remove(Serializable primaryKey) throws NoSuchRouteException {
		Session session = null;

		try {
			session = openSession();

			Route route = (Route)session.get(RouteImpl.class, primaryKey);

			if (route == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRouteException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(route);
		}
		catch (NoSuchRouteException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Route removeImpl(Route route) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(route)) {
				route = (Route)session.get(
					RouteImpl.class, route.getPrimaryKeyObj());
			}

			if (route != null) {
				session.delete(route);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (route != null) {
			clearCache(route);
		}

		return route;
	}

	@Override
	public Route updateImpl(Route route) {
		boolean isNew = route.isNew();

		if (!(route instanceof RouteModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(route.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(route);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in route proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Route implementation " +
					route.getClass());
		}

		RouteModelImpl routeModelImpl = (RouteModelImpl)route;

		Session session = null;

		try {
			session = openSession();

			if (route.isNew()) {
				session.save(route);

				route.setNew(false);
			}
			else {
				route = (Route)session.merge(route);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!RouteModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			RouteModelImpl.ENTITY_CACHE_ENABLED, RouteImpl.class,
			route.getPrimaryKey(), route, false);

		clearUniqueFindersCache(routeModelImpl, false);
		cacheUniqueFindersCache(routeModelImpl);

		route.resetOriginalValues();

		return route;
	}

	/**
	 * Returns the route with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the route
	 * @return the route
	 * @throws NoSuchRouteException if a route with the primary key could not be found
	 */
	@Override
	public Route findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRouteException {

		Route route = fetchByPrimaryKey(primaryKey);

		if (route == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRouteException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return route;
	}

	/**
	 * Returns the route with the primary key or throws a <code>NoSuchRouteException</code> if it could not be found.
	 *
	 * @param id the primary key of the route
	 * @return the route
	 * @throws NoSuchRouteException if a route with the primary key could not be found
	 */
	@Override
	public Route findByPrimaryKey(long id) throws NoSuchRouteException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the route with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the route
	 * @return the route, or <code>null</code> if a route with the primary key could not be found
	 */
	@Override
	public Route fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			RouteModelImpl.ENTITY_CACHE_ENABLED, RouteImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Route route = (Route)serializable;

		if (route == null) {
			Session session = null;

			try {
				session = openSession();

				route = (Route)session.get(RouteImpl.class, primaryKey);

				if (route != null) {
					cacheResult(route);
				}
				else {
					entityCache.putResult(
						RouteModelImpl.ENTITY_CACHE_ENABLED, RouteImpl.class,
						primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					RouteModelImpl.ENTITY_CACHE_ENABLED, RouteImpl.class,
					primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return route;
	}

	/**
	 * Returns the route with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the route
	 * @return the route, or <code>null</code> if a route with the primary key could not be found
	 */
	@Override
	public Route fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, Route> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Route> map = new HashMap<Serializable, Route>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Route route = fetchByPrimaryKey(primaryKey);

			if (route != null) {
				map.put(primaryKey, route);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				RouteModelImpl.ENTITY_CACHE_ENABLED, RouteImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Route)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_ROUTE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Route route : (List<Route>)q.list()) {
				map.put(route.getPrimaryKeyObj(), route);

				cacheResult(route);

				uncachedPrimaryKeys.remove(route.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					RouteModelImpl.ENTITY_CACHE_ENABLED, RouteImpl.class,
					primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the routes.
	 *
	 * @return the routes
	 */
	@Override
	public List<Route> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Route> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Route> findAll(
		int start, int end, OrderByComparator<Route> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<Route> findAll(
		int start, int end, OrderByComparator<Route> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Route> list = null;

		if (retrieveFromCache) {
			list = (List<Route>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ROUTE);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ROUTE;

				if (pagination) {
					sql = sql.concat(RouteModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Route>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Route>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the routes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Route route : findAll()) {
			remove(route);
		}
	}

	/**
	 * Returns the number of routes.
	 *
	 * @return the number of routes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ROUTE);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return RouteModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the route persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, RouteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, RouteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByRouteId = new FinderPath(
			RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, RouteImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByRouteId",
			new String[] {String.class.getName()},
			RouteModelImpl.ROUTE_ID_COLUMN_BITMASK);

		_finderPathCountByRouteId = new FinderPath(
			RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRouteId",
			new String[] {String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(RouteImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ROUTE =
		"SELECT route FROM Route route";

	private static final String _SQL_SELECT_ROUTE_WHERE_PKS_IN =
		"SELECT route FROM Route route WHERE id_ IN (";

	private static final String _SQL_SELECT_ROUTE_WHERE =
		"SELECT route FROM Route route WHERE ";

	private static final String _SQL_COUNT_ROUTE =
		"SELECT COUNT(route) FROM Route route";

	private static final String _SQL_COUNT_ROUTE_WHERE =
		"SELECT COUNT(route) FROM Route route WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "route.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Route exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Route exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		RoutePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id"});

}