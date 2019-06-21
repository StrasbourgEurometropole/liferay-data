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
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.gtfs.exception.NoSuchRouteException;
import eu.strasbourg.service.gtfs.model.Route;
import eu.strasbourg.service.gtfs.model.impl.RouteImpl;
import eu.strasbourg.service.gtfs.model.impl.RouteModelImpl;
import eu.strasbourg.service.gtfs.service.persistence.RoutePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

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
 * @see RoutePersistence
 * @see eu.strasbourg.service.gtfs.service.persistence.RouteUtil
 * @generated
 */
@ProviderType
public class RoutePersistenceImpl extends BasePersistenceImpl<Route>
	implements RoutePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RouteUtil} to access the route persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RouteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, RouteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, RouteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, RouteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, RouteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			RouteModelImpl.UUID_COLUMN_BITMASK |
			RouteModelImpl.ROUTE_ID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the routes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching routes
	 */
	@Override
	public List<Route> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<Route> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

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
	@Override
	public List<Route> findByUuid(String uuid, int start, int end,
		OrderByComparator<Route> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

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
	@Override
	public List<Route> findByUuid(String uuid, int start, int end,
		OrderByComparator<Route> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<Route> list = null;

		if (retrieveFromCache) {
			list = (List<Route>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Route route : list) {
					if (!Objects.equals(uuid, route.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ROUTE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RouteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<Route>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Route>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first route in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching route
	 * @throws NoSuchRouteException if a matching route could not be found
	 */
	@Override
	public Route findByUuid_First(String uuid,
		OrderByComparator<Route> orderByComparator) throws NoSuchRouteException {
		Route route = fetchByUuid_First(uuid, orderByComparator);

		if (route != null) {
			return route;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRouteException(msg.toString());
	}

	/**
	 * Returns the first route in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching route, or <code>null</code> if a matching route could not be found
	 */
	@Override
	public Route fetchByUuid_First(String uuid,
		OrderByComparator<Route> orderByComparator) {
		List<Route> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last route in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching route
	 * @throws NoSuchRouteException if a matching route could not be found
	 */
	@Override
	public Route findByUuid_Last(String uuid,
		OrderByComparator<Route> orderByComparator) throws NoSuchRouteException {
		Route route = fetchByUuid_Last(uuid, orderByComparator);

		if (route != null) {
			return route;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRouteException(msg.toString());
	}

	/**
	 * Returns the last route in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching route, or <code>null</code> if a matching route could not be found
	 */
	@Override
	public Route fetchByUuid_Last(String uuid,
		OrderByComparator<Route> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Route> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Route[] findByUuid_PrevAndNext(long id, String uuid,
		OrderByComparator<Route> orderByComparator) throws NoSuchRouteException {
		Route route = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Route[] array = new RouteImpl[3];

			array[0] = getByUuid_PrevAndNext(session, route, uuid,
					orderByComparator, true);

			array[1] = route;

			array[2] = getByUuid_PrevAndNext(session, route, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Route getByUuid_PrevAndNext(Session session, Route route,
		String uuid, OrderByComparator<Route> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ROUTE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(RouteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(route);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Route> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the routes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Route route : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(route);
		}
	}

	/**
	 * Returns the number of routes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching routes
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ROUTE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "route.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "route.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(route.uuid IS NULL OR route.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ROUTEID = new FinderPath(RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, RouteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRouteId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ROUTEID =
		new FinderPath(RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, RouteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRouteId",
			new String[] { String.class.getName() },
			RouteModelImpl.ROUTE_ID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ROUTEID = new FinderPath(RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRouteId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the routes where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @return the matching routes
	 */
	@Override
	public List<Route> findByRouteId(String route_id) {
		return findByRouteId(route_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

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
	@Override
	public List<Route> findByRouteId(String route_id, int start, int end) {
		return findByRouteId(route_id, start, end, null);
	}

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
	@Override
	public List<Route> findByRouteId(String route_id, int start, int end,
		OrderByComparator<Route> orderByComparator) {
		return findByRouteId(route_id, start, end, orderByComparator, true);
	}

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
	@Override
	public List<Route> findByRouteId(String route_id, int start, int end,
		OrderByComparator<Route> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ROUTEID;
			finderArgs = new Object[] { route_id };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ROUTEID;
			finderArgs = new Object[] { route_id, start, end, orderByComparator };
		}

		List<Route> list = null;

		if (retrieveFromCache) {
			list = (List<Route>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Route route : list) {
					if (!Objects.equals(route_id, route.getRoute_id())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ROUTE_WHERE);

			boolean bindRoute_id = false;

			if (route_id == null) {
				query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_1);
			}
			else if (route_id.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_3);
			}
			else {
				bindRoute_id = true;

				query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RouteModelImpl.ORDER_BY_JPQL);
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

				if (!pagination) {
					list = (List<Route>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Route>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first route in the ordered set where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching route
	 * @throws NoSuchRouteException if a matching route could not be found
	 */
	@Override
	public Route findByRouteId_First(String route_id,
		OrderByComparator<Route> orderByComparator) throws NoSuchRouteException {
		Route route = fetchByRouteId_First(route_id, orderByComparator);

		if (route != null) {
			return route;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("route_id=");
		msg.append(route_id);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRouteException(msg.toString());
	}

	/**
	 * Returns the first route in the ordered set where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching route, or <code>null</code> if a matching route could not be found
	 */
	@Override
	public Route fetchByRouteId_First(String route_id,
		OrderByComparator<Route> orderByComparator) {
		List<Route> list = findByRouteId(route_id, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last route in the ordered set where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching route
	 * @throws NoSuchRouteException if a matching route could not be found
	 */
	@Override
	public Route findByRouteId_Last(String route_id,
		OrderByComparator<Route> orderByComparator) throws NoSuchRouteException {
		Route route = fetchByRouteId_Last(route_id, orderByComparator);

		if (route != null) {
			return route;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("route_id=");
		msg.append(route_id);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRouteException(msg.toString());
	}

	/**
	 * Returns the last route in the ordered set where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching route, or <code>null</code> if a matching route could not be found
	 */
	@Override
	public Route fetchByRouteId_Last(String route_id,
		OrderByComparator<Route> orderByComparator) {
		int count = countByRouteId(route_id);

		if (count == 0) {
			return null;
		}

		List<Route> list = findByRouteId(route_id, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the routes before and after the current route in the ordered set where route_id = &#63;.
	 *
	 * @param id the primary key of the current route
	 * @param route_id the route_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next route
	 * @throws NoSuchRouteException if a route with the primary key could not be found
	 */
	@Override
	public Route[] findByRouteId_PrevAndNext(long id, String route_id,
		OrderByComparator<Route> orderByComparator) throws NoSuchRouteException {
		Route route = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Route[] array = new RouteImpl[3];

			array[0] = getByRouteId_PrevAndNext(session, route, route_id,
					orderByComparator, true);

			array[1] = route;

			array[2] = getByRouteId_PrevAndNext(session, route, route_id,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Route getByRouteId_PrevAndNext(Session session, Route route,
		String route_id, OrderByComparator<Route> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ROUTE_WHERE);

		boolean bindRoute_id = false;

		if (route_id == null) {
			query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_1);
		}
		else if (route_id.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_3);
		}
		else {
			bindRoute_id = true;

			query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(RouteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindRoute_id) {
			qPos.add(route_id);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(route);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Route> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the routes where route_id = &#63; from the database.
	 *
	 * @param route_id the route_id
	 */
	@Override
	public void removeByRouteId(String route_id) {
		for (Route route : findByRouteId(route_id, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(route);
		}
	}

	/**
	 * Returns the number of routes where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @return the number of matching routes
	 */
	@Override
	public int countByRouteId(String route_id) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ROUTEID;

		Object[] finderArgs = new Object[] { route_id };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ROUTE_WHERE);

			boolean bindRoute_id = false;

			if (route_id == null) {
				query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_1);
			}
			else if (route_id.equals(StringPool.BLANK)) {
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

	private static final String _FINDER_COLUMN_ROUTEID_ROUTE_ID_1 = "route.route_id IS NULL";
	private static final String _FINDER_COLUMN_ROUTEID_ROUTE_ID_2 = "route.route_id = ?";
	private static final String _FINDER_COLUMN_ROUTEID_ROUTE_ID_3 = "(route.route_id IS NULL OR route.route_id = '')";

	public RoutePersistenceImpl() {
		setModelClass(Route.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("id", "id_");

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
		entityCache.putResult(RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteImpl.class, route.getPrimaryKey(), route);

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
			if (entityCache.getResult(RouteModelImpl.ENTITY_CACHE_ENABLED,
						RouteImpl.class, route.getPrimaryKey()) == null) {
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Route route) {
		entityCache.removeResult(RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteImpl.class, route.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Route> routes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Route route : routes) {
			entityCache.removeResult(RouteModelImpl.ENTITY_CACHE_ENABLED,
				RouteImpl.class, route.getPrimaryKey());
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

		String uuid = PortalUUIDUtil.generate();

		route.setUuid(uuid);

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

				throw new NoSuchRouteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
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
		route = toUnwrappedModel(route);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(route)) {
				route = (Route)session.get(RouteImpl.class,
						route.getPrimaryKeyObj());
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
		route = toUnwrappedModel(route);

		boolean isNew = route.isNew();

		RouteModelImpl routeModelImpl = (RouteModelImpl)route;

		if (Validator.isNull(route.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			route.setUuid(uuid);
		}

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
		else
		 if (isNew) {
			Object[] args = new Object[] { routeModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { routeModelImpl.getRoute_id() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ROUTEID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ROUTEID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((routeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { routeModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { routeModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((routeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ROUTEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						routeModelImpl.getOriginalRoute_id()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ROUTEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ROUTEID,
					args);

				args = new Object[] { routeModelImpl.getRoute_id() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ROUTEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ROUTEID,
					args);
			}
		}

		entityCache.putResult(RouteModelImpl.ENTITY_CACHE_ENABLED,
			RouteImpl.class, route.getPrimaryKey(), route, false);

		route.resetOriginalValues();

		return route;
	}

	protected Route toUnwrappedModel(Route route) {
		if (route instanceof RouteImpl) {
			return route;
		}

		RouteImpl routeImpl = new RouteImpl();

		routeImpl.setNew(route.isNew());
		routeImpl.setPrimaryKey(route.getPrimaryKey());

		routeImpl.setUuid(route.getUuid());
		routeImpl.setId(route.getId());
		routeImpl.setRoute_id(route.getRoute_id());
		routeImpl.setRoute_short_name(route.getRoute_short_name());
		routeImpl.setRoute_long_name(route.getRoute_long_name());
		routeImpl.setRoute_desc(route.getRoute_desc());
		routeImpl.setRoute_type(route.getRoute_type());
		routeImpl.setRoute_color(route.getRoute_color());
		routeImpl.setRoute_text_color(route.getRoute_text_color());

		return routeImpl;
	}

	/**
	 * Returns the route with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
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

			throw new NoSuchRouteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return route;
	}

	/**
	 * Returns the route with the primary key or throws a {@link NoSuchRouteException} if it could not be found.
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
		Serializable serializable = entityCache.getResult(RouteModelImpl.ENTITY_CACHE_ENABLED,
				RouteImpl.class, primaryKey);

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
					entityCache.putResult(RouteModelImpl.ENTITY_CACHE_ENABLED,
						RouteImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RouteModelImpl.ENTITY_CACHE_ENABLED,
					RouteImpl.class, primaryKey);

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
			Serializable serializable = entityCache.getResult(RouteModelImpl.ENTITY_CACHE_ENABLED,
					RouteImpl.class, primaryKey);

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

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ROUTE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

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
				entityCache.putResult(RouteModelImpl.ENTITY_CACHE_ENABLED,
					RouteImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RouteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RouteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of routes
	 * @param end the upper bound of the range of routes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of routes
	 */
	@Override
	public List<Route> findAll(int start, int end,
		OrderByComparator<Route> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<Route> findAll(int start, int end,
		OrderByComparator<Route> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Route> list = null;

		if (retrieveFromCache) {
			list = (List<Route>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ROUTE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

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
					list = (List<Route>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Route>)QueryUtil.list(q, getDialect(), start,
							end);
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
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ROUTE);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

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
	private static final String _SQL_SELECT_ROUTE = "SELECT route FROM Route route";
	private static final String _SQL_SELECT_ROUTE_WHERE_PKS_IN = "SELECT route FROM Route route WHERE id_ IN (";
	private static final String _SQL_SELECT_ROUTE_WHERE = "SELECT route FROM Route route WHERE ";
	private static final String _SQL_COUNT_ROUTE = "SELECT COUNT(route) FROM Route route";
	private static final String _SQL_COUNT_ROUTE_WHERE = "SELECT COUNT(route) FROM Route route WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "route.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Route exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Route exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(RoutePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "id"
			});
}