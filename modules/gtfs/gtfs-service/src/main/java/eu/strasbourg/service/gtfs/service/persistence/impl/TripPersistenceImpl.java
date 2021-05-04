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
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.gtfs.exception.NoSuchTripException;
import eu.strasbourg.service.gtfs.model.Trip;
import eu.strasbourg.service.gtfs.model.impl.TripImpl;
import eu.strasbourg.service.gtfs.model.impl.TripModelImpl;
import eu.strasbourg.service.gtfs.service.persistence.TripPersistence;

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
 * The persistence implementation for the trip service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @generated
 */
@ProviderType
public class TripPersistenceImpl
	extends BasePersistenceImpl<Trip> implements TripPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TripUtil</code> to access the trip persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TripImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByRouteId;
	private FinderPath _finderPathWithoutPaginationFindByRouteId;
	private FinderPath _finderPathCountByRouteId;

	/**
	 * Returns all the trips where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @return the matching trips
	 */
	@Override
	public List<Trip> findByRouteId(String route_id) {
		return findByRouteId(
			route_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the trips where route_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TripModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param route_id the route_id
	 * @param start the lower bound of the range of trips
	 * @param end the upper bound of the range of trips (not inclusive)
	 * @return the range of matching trips
	 */
	@Override
	public List<Trip> findByRouteId(String route_id, int start, int end) {
		return findByRouteId(route_id, start, end, null);
	}

	/**
	 * Returns an ordered range of all the trips where route_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TripModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param route_id the route_id
	 * @param start the lower bound of the range of trips
	 * @param end the upper bound of the range of trips (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching trips
	 */
	@Override
	public List<Trip> findByRouteId(
		String route_id, int start, int end,
		OrderByComparator<Trip> orderByComparator) {

		return findByRouteId(route_id, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the trips where route_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TripModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param route_id the route_id
	 * @param start the lower bound of the range of trips
	 * @param end the upper bound of the range of trips (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching trips
	 */
	@Override
	public List<Trip> findByRouteId(
		String route_id, int start, int end,
		OrderByComparator<Trip> orderByComparator, boolean retrieveFromCache) {

		route_id = Objects.toString(route_id, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByRouteId;
			finderArgs = new Object[] {route_id};
		}
		else {
			finderPath = _finderPathWithPaginationFindByRouteId;
			finderArgs = new Object[] {route_id, start, end, orderByComparator};
		}

		List<Trip> list = null;

		if (retrieveFromCache) {
			list = (List<Trip>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Trip trip : list) {
					if (!route_id.equals(trip.getRoute_id())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TRIP_WHERE);

			boolean bindRoute_id = false;

			if (route_id.isEmpty()) {
				query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_3);
			}
			else {
				bindRoute_id = true;

				query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(TripModelImpl.ORDER_BY_JPQL);
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
					list = (List<Trip>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Trip>)QueryUtil.list(
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
	 * Returns the first trip in the ordered set where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching trip
	 * @throws NoSuchTripException if a matching trip could not be found
	 */
	@Override
	public Trip findByRouteId_First(
			String route_id, OrderByComparator<Trip> orderByComparator)
		throws NoSuchTripException {

		Trip trip = fetchByRouteId_First(route_id, orderByComparator);

		if (trip != null) {
			return trip;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("route_id=");
		msg.append(route_id);

		msg.append("}");

		throw new NoSuchTripException(msg.toString());
	}

	/**
	 * Returns the first trip in the ordered set where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching trip, or <code>null</code> if a matching trip could not be found
	 */
	@Override
	public Trip fetchByRouteId_First(
		String route_id, OrderByComparator<Trip> orderByComparator) {

		List<Trip> list = findByRouteId(route_id, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last trip in the ordered set where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching trip
	 * @throws NoSuchTripException if a matching trip could not be found
	 */
	@Override
	public Trip findByRouteId_Last(
			String route_id, OrderByComparator<Trip> orderByComparator)
		throws NoSuchTripException {

		Trip trip = fetchByRouteId_Last(route_id, orderByComparator);

		if (trip != null) {
			return trip;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("route_id=");
		msg.append(route_id);

		msg.append("}");

		throw new NoSuchTripException(msg.toString());
	}

	/**
	 * Returns the last trip in the ordered set where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching trip, or <code>null</code> if a matching trip could not be found
	 */
	@Override
	public Trip fetchByRouteId_Last(
		String route_id, OrderByComparator<Trip> orderByComparator) {

		int count = countByRouteId(route_id);

		if (count == 0) {
			return null;
		}

		List<Trip> list = findByRouteId(
			route_id, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the trips before and after the current trip in the ordered set where route_id = &#63;.
	 *
	 * @param id the primary key of the current trip
	 * @param route_id the route_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next trip
	 * @throws NoSuchTripException if a trip with the primary key could not be found
	 */
	@Override
	public Trip[] findByRouteId_PrevAndNext(
			long id, String route_id, OrderByComparator<Trip> orderByComparator)
		throws NoSuchTripException {

		route_id = Objects.toString(route_id, "");

		Trip trip = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Trip[] array = new TripImpl[3];

			array[0] = getByRouteId_PrevAndNext(
				session, trip, route_id, orderByComparator, true);

			array[1] = trip;

			array[2] = getByRouteId_PrevAndNext(
				session, trip, route_id, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Trip getByRouteId_PrevAndNext(
		Session session, Trip trip, String route_id,
		OrderByComparator<Trip> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRIP_WHERE);

		boolean bindRoute_id = false;

		if (route_id.isEmpty()) {
			query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_3);
		}
		else {
			bindRoute_id = true;

			query.append(_FINDER_COLUMN_ROUTEID_ROUTE_ID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

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
			query.append(TripModelImpl.ORDER_BY_JPQL);
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
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(trip)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Trip> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the trips where route_id = &#63; from the database.
	 *
	 * @param route_id the route_id
	 */
	@Override
	public void removeByRouteId(String route_id) {
		for (Trip trip :
				findByRouteId(
					route_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(trip);
		}
	}

	/**
	 * Returns the number of trips where route_id = &#63;.
	 *
	 * @param route_id the route_id
	 * @return the number of matching trips
	 */
	@Override
	public int countByRouteId(String route_id) {
		route_id = Objects.toString(route_id, "");

		FinderPath finderPath = _finderPathCountByRouteId;

		Object[] finderArgs = new Object[] {route_id};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRIP_WHERE);

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
		"trip.route_id = ?";

	private static final String _FINDER_COLUMN_ROUTEID_ROUTE_ID_3 =
		"(trip.route_id IS NULL OR trip.route_id = '')";

	private FinderPath _finderPathWithPaginationFindByServiceId;
	private FinderPath _finderPathWithoutPaginationFindByServiceId;
	private FinderPath _finderPathCountByServiceId;

	/**
	 * Returns all the trips where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @return the matching trips
	 */
	@Override
	public List<Trip> findByServiceId(String service_id) {
		return findByServiceId(
			service_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the trips where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TripModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of trips
	 * @param end the upper bound of the range of trips (not inclusive)
	 * @return the range of matching trips
	 */
	@Override
	public List<Trip> findByServiceId(String service_id, int start, int end) {
		return findByServiceId(service_id, start, end, null);
	}

	/**
	 * Returns an ordered range of all the trips where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TripModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of trips
	 * @param end the upper bound of the range of trips (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching trips
	 */
	@Override
	public List<Trip> findByServiceId(
		String service_id, int start, int end,
		OrderByComparator<Trip> orderByComparator) {

		return findByServiceId(service_id, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the trips where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TripModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of trips
	 * @param end the upper bound of the range of trips (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching trips
	 */
	@Override
	public List<Trip> findByServiceId(
		String service_id, int start, int end,
		OrderByComparator<Trip> orderByComparator, boolean retrieveFromCache) {

		service_id = Objects.toString(service_id, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByServiceId;
			finderArgs = new Object[] {service_id};
		}
		else {
			finderPath = _finderPathWithPaginationFindByServiceId;
			finderArgs = new Object[] {
				service_id, start, end, orderByComparator
			};
		}

		List<Trip> list = null;

		if (retrieveFromCache) {
			list = (List<Trip>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Trip trip : list) {
					if (!service_id.equals(trip.getService_id())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TRIP_WHERE);

			boolean bindService_id = false;

			if (service_id.isEmpty()) {
				query.append(_FINDER_COLUMN_SERVICEID_SERVICE_ID_3);
			}
			else {
				bindService_id = true;

				query.append(_FINDER_COLUMN_SERVICEID_SERVICE_ID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(TripModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindService_id) {
					qPos.add(service_id);
				}

				if (!pagination) {
					list = (List<Trip>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Trip>)QueryUtil.list(
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
	 * Returns the first trip in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching trip
	 * @throws NoSuchTripException if a matching trip could not be found
	 */
	@Override
	public Trip findByServiceId_First(
			String service_id, OrderByComparator<Trip> orderByComparator)
		throws NoSuchTripException {

		Trip trip = fetchByServiceId_First(service_id, orderByComparator);

		if (trip != null) {
			return trip;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("service_id=");
		msg.append(service_id);

		msg.append("}");

		throw new NoSuchTripException(msg.toString());
	}

	/**
	 * Returns the first trip in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching trip, or <code>null</code> if a matching trip could not be found
	 */
	@Override
	public Trip fetchByServiceId_First(
		String service_id, OrderByComparator<Trip> orderByComparator) {

		List<Trip> list = findByServiceId(service_id, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last trip in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching trip
	 * @throws NoSuchTripException if a matching trip could not be found
	 */
	@Override
	public Trip findByServiceId_Last(
			String service_id, OrderByComparator<Trip> orderByComparator)
		throws NoSuchTripException {

		Trip trip = fetchByServiceId_Last(service_id, orderByComparator);

		if (trip != null) {
			return trip;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("service_id=");
		msg.append(service_id);

		msg.append("}");

		throw new NoSuchTripException(msg.toString());
	}

	/**
	 * Returns the last trip in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching trip, or <code>null</code> if a matching trip could not be found
	 */
	@Override
	public Trip fetchByServiceId_Last(
		String service_id, OrderByComparator<Trip> orderByComparator) {

		int count = countByServiceId(service_id);

		if (count == 0) {
			return null;
		}

		List<Trip> list = findByServiceId(
			service_id, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the trips before and after the current trip in the ordered set where service_id = &#63;.
	 *
	 * @param id the primary key of the current trip
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next trip
	 * @throws NoSuchTripException if a trip with the primary key could not be found
	 */
	@Override
	public Trip[] findByServiceId_PrevAndNext(
			long id, String service_id,
			OrderByComparator<Trip> orderByComparator)
		throws NoSuchTripException {

		service_id = Objects.toString(service_id, "");

		Trip trip = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Trip[] array = new TripImpl[3];

			array[0] = getByServiceId_PrevAndNext(
				session, trip, service_id, orderByComparator, true);

			array[1] = trip;

			array[2] = getByServiceId_PrevAndNext(
				session, trip, service_id, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Trip getByServiceId_PrevAndNext(
		Session session, Trip trip, String service_id,
		OrderByComparator<Trip> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRIP_WHERE);

		boolean bindService_id = false;

		if (service_id.isEmpty()) {
			query.append(_FINDER_COLUMN_SERVICEID_SERVICE_ID_3);
		}
		else {
			bindService_id = true;

			query.append(_FINDER_COLUMN_SERVICEID_SERVICE_ID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

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
			query.append(TripModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindService_id) {
			qPos.add(service_id);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(trip)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Trip> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the trips where service_id = &#63; from the database.
	 *
	 * @param service_id the service_id
	 */
	@Override
	public void removeByServiceId(String service_id) {
		for (Trip trip :
				findByServiceId(
					service_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(trip);
		}
	}

	/**
	 * Returns the number of trips where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @return the number of matching trips
	 */
	@Override
	public int countByServiceId(String service_id) {
		service_id = Objects.toString(service_id, "");

		FinderPath finderPath = _finderPathCountByServiceId;

		Object[] finderArgs = new Object[] {service_id};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRIP_WHERE);

			boolean bindService_id = false;

			if (service_id.isEmpty()) {
				query.append(_FINDER_COLUMN_SERVICEID_SERVICE_ID_3);
			}
			else {
				bindService_id = true;

				query.append(_FINDER_COLUMN_SERVICEID_SERVICE_ID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindService_id) {
					qPos.add(service_id);
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

	private static final String _FINDER_COLUMN_SERVICEID_SERVICE_ID_2 =
		"trip.service_id = ?";

	private static final String _FINDER_COLUMN_SERVICEID_SERVICE_ID_3 =
		"(trip.service_id IS NULL OR trip.service_id = '')";

	private FinderPath _finderPathWithPaginationFindByTripId;
	private FinderPath _finderPathWithoutPaginationFindByTripId;
	private FinderPath _finderPathCountByTripId;

	/**
	 * Returns all the trips where trip_id = &#63;.
	 *
	 * @param trip_id the trip_id
	 * @return the matching trips
	 */
	@Override
	public List<Trip> findByTripId(String trip_id) {
		return findByTripId(
			trip_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the trips where trip_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TripModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param trip_id the trip_id
	 * @param start the lower bound of the range of trips
	 * @param end the upper bound of the range of trips (not inclusive)
	 * @return the range of matching trips
	 */
	@Override
	public List<Trip> findByTripId(String trip_id, int start, int end) {
		return findByTripId(trip_id, start, end, null);
	}

	/**
	 * Returns an ordered range of all the trips where trip_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TripModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param trip_id the trip_id
	 * @param start the lower bound of the range of trips
	 * @param end the upper bound of the range of trips (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching trips
	 */
	@Override
	public List<Trip> findByTripId(
		String trip_id, int start, int end,
		OrderByComparator<Trip> orderByComparator) {

		return findByTripId(trip_id, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the trips where trip_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TripModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param trip_id the trip_id
	 * @param start the lower bound of the range of trips
	 * @param end the upper bound of the range of trips (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching trips
	 */
	@Override
	public List<Trip> findByTripId(
		String trip_id, int start, int end,
		OrderByComparator<Trip> orderByComparator, boolean retrieveFromCache) {

		trip_id = Objects.toString(trip_id, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByTripId;
			finderArgs = new Object[] {trip_id};
		}
		else {
			finderPath = _finderPathWithPaginationFindByTripId;
			finderArgs = new Object[] {trip_id, start, end, orderByComparator};
		}

		List<Trip> list = null;

		if (retrieveFromCache) {
			list = (List<Trip>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Trip trip : list) {
					if (!trip_id.equals(trip.getTrip_id())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TRIP_WHERE);

			boolean bindTrip_id = false;

			if (trip_id.isEmpty()) {
				query.append(_FINDER_COLUMN_TRIPID_TRIP_ID_3);
			}
			else {
				bindTrip_id = true;

				query.append(_FINDER_COLUMN_TRIPID_TRIP_ID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(TripModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTrip_id) {
					qPos.add(trip_id);
				}

				if (!pagination) {
					list = (List<Trip>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Trip>)QueryUtil.list(
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
	 * Returns the first trip in the ordered set where trip_id = &#63;.
	 *
	 * @param trip_id the trip_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching trip
	 * @throws NoSuchTripException if a matching trip could not be found
	 */
	@Override
	public Trip findByTripId_First(
			String trip_id, OrderByComparator<Trip> orderByComparator)
		throws NoSuchTripException {

		Trip trip = fetchByTripId_First(trip_id, orderByComparator);

		if (trip != null) {
			return trip;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trip_id=");
		msg.append(trip_id);

		msg.append("}");

		throw new NoSuchTripException(msg.toString());
	}

	/**
	 * Returns the first trip in the ordered set where trip_id = &#63;.
	 *
	 * @param trip_id the trip_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching trip, or <code>null</code> if a matching trip could not be found
	 */
	@Override
	public Trip fetchByTripId_First(
		String trip_id, OrderByComparator<Trip> orderByComparator) {

		List<Trip> list = findByTripId(trip_id, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last trip in the ordered set where trip_id = &#63;.
	 *
	 * @param trip_id the trip_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching trip
	 * @throws NoSuchTripException if a matching trip could not be found
	 */
	@Override
	public Trip findByTripId_Last(
			String trip_id, OrderByComparator<Trip> orderByComparator)
		throws NoSuchTripException {

		Trip trip = fetchByTripId_Last(trip_id, orderByComparator);

		if (trip != null) {
			return trip;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trip_id=");
		msg.append(trip_id);

		msg.append("}");

		throw new NoSuchTripException(msg.toString());
	}

	/**
	 * Returns the last trip in the ordered set where trip_id = &#63;.
	 *
	 * @param trip_id the trip_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching trip, or <code>null</code> if a matching trip could not be found
	 */
	@Override
	public Trip fetchByTripId_Last(
		String trip_id, OrderByComparator<Trip> orderByComparator) {

		int count = countByTripId(trip_id);

		if (count == 0) {
			return null;
		}

		List<Trip> list = findByTripId(
			trip_id, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the trips before and after the current trip in the ordered set where trip_id = &#63;.
	 *
	 * @param id the primary key of the current trip
	 * @param trip_id the trip_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next trip
	 * @throws NoSuchTripException if a trip with the primary key could not be found
	 */
	@Override
	public Trip[] findByTripId_PrevAndNext(
			long id, String trip_id, OrderByComparator<Trip> orderByComparator)
		throws NoSuchTripException {

		trip_id = Objects.toString(trip_id, "");

		Trip trip = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Trip[] array = new TripImpl[3];

			array[0] = getByTripId_PrevAndNext(
				session, trip, trip_id, orderByComparator, true);

			array[1] = trip;

			array[2] = getByTripId_PrevAndNext(
				session, trip, trip_id, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Trip getByTripId_PrevAndNext(
		Session session, Trip trip, String trip_id,
		OrderByComparator<Trip> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRIP_WHERE);

		boolean bindTrip_id = false;

		if (trip_id.isEmpty()) {
			query.append(_FINDER_COLUMN_TRIPID_TRIP_ID_3);
		}
		else {
			bindTrip_id = true;

			query.append(_FINDER_COLUMN_TRIPID_TRIP_ID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

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
			query.append(TripModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindTrip_id) {
			qPos.add(trip_id);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(trip)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Trip> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the trips where trip_id = &#63; from the database.
	 *
	 * @param trip_id the trip_id
	 */
	@Override
	public void removeByTripId(String trip_id) {
		for (Trip trip :
				findByTripId(
					trip_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(trip);
		}
	}

	/**
	 * Returns the number of trips where trip_id = &#63;.
	 *
	 * @param trip_id the trip_id
	 * @return the number of matching trips
	 */
	@Override
	public int countByTripId(String trip_id) {
		trip_id = Objects.toString(trip_id, "");

		FinderPath finderPath = _finderPathCountByTripId;

		Object[] finderArgs = new Object[] {trip_id};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRIP_WHERE);

			boolean bindTrip_id = false;

			if (trip_id.isEmpty()) {
				query.append(_FINDER_COLUMN_TRIPID_TRIP_ID_3);
			}
			else {
				bindTrip_id = true;

				query.append(_FINDER_COLUMN_TRIPID_TRIP_ID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTrip_id) {
					qPos.add(trip_id);
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

	private static final String _FINDER_COLUMN_TRIPID_TRIP_ID_2 =
		"trip.trip_id = ?";

	private static final String _FINDER_COLUMN_TRIPID_TRIP_ID_3 =
		"(trip.trip_id IS NULL OR trip.trip_id = '')";

	public TripPersistenceImpl() {
		setModelClass(Trip.class);

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
	 * Caches the trip in the entity cache if it is enabled.
	 *
	 * @param trip the trip
	 */
	@Override
	public void cacheResult(Trip trip) {
		entityCache.putResult(
			TripModelImpl.ENTITY_CACHE_ENABLED, TripImpl.class,
			trip.getPrimaryKey(), trip);

		trip.resetOriginalValues();
	}

	/**
	 * Caches the trips in the entity cache if it is enabled.
	 *
	 * @param trips the trips
	 */
	@Override
	public void cacheResult(List<Trip> trips) {
		for (Trip trip : trips) {
			if (entityCache.getResult(
					TripModelImpl.ENTITY_CACHE_ENABLED, TripImpl.class,
					trip.getPrimaryKey()) == null) {

				cacheResult(trip);
			}
			else {
				trip.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all trips.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TripImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the trip.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Trip trip) {
		entityCache.removeResult(
			TripModelImpl.ENTITY_CACHE_ENABLED, TripImpl.class,
			trip.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Trip> trips) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Trip trip : trips) {
			entityCache.removeResult(
				TripModelImpl.ENTITY_CACHE_ENABLED, TripImpl.class,
				trip.getPrimaryKey());
		}
	}

	/**
	 * Creates a new trip with the primary key. Does not add the trip to the database.
	 *
	 * @param id the primary key for the new trip
	 * @return the new trip
	 */
	@Override
	public Trip create(long id) {
		Trip trip = new TripImpl();

		trip.setNew(true);
		trip.setPrimaryKey(id);

		return trip;
	}

	/**
	 * Removes the trip with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the trip
	 * @return the trip that was removed
	 * @throws NoSuchTripException if a trip with the primary key could not be found
	 */
	@Override
	public Trip remove(long id) throws NoSuchTripException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the trip with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the trip
	 * @return the trip that was removed
	 * @throws NoSuchTripException if a trip with the primary key could not be found
	 */
	@Override
	public Trip remove(Serializable primaryKey) throws NoSuchTripException {
		Session session = null;

		try {
			session = openSession();

			Trip trip = (Trip)session.get(TripImpl.class, primaryKey);

			if (trip == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTripException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(trip);
		}
		catch (NoSuchTripException nsee) {
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
	protected Trip removeImpl(Trip trip) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(trip)) {
				trip = (Trip)session.get(
					TripImpl.class, trip.getPrimaryKeyObj());
			}

			if (trip != null) {
				session.delete(trip);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (trip != null) {
			clearCache(trip);
		}

		return trip;
	}

	@Override
	public Trip updateImpl(Trip trip) {
		boolean isNew = trip.isNew();

		if (!(trip instanceof TripModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(trip.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(trip);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in trip proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Trip implementation " +
					trip.getClass());
		}

		TripModelImpl tripModelImpl = (TripModelImpl)trip;

		Session session = null;

		try {
			session = openSession();

			if (trip.isNew()) {
				session.save(trip);

				trip.setNew(false);
			}
			else {
				trip = (Trip)session.merge(trip);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TripModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {tripModelImpl.getRoute_id()};

			finderCache.removeResult(_finderPathCountByRouteId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByRouteId, args);

			args = new Object[] {tripModelImpl.getService_id()};

			finderCache.removeResult(_finderPathCountByServiceId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByServiceId, args);

			args = new Object[] {tripModelImpl.getTrip_id()};

			finderCache.removeResult(_finderPathCountByTripId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByTripId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((tripModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByRouteId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					tripModelImpl.getOriginalRoute_id()
				};

				finderCache.removeResult(_finderPathCountByRouteId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByRouteId, args);

				args = new Object[] {tripModelImpl.getRoute_id()};

				finderCache.removeResult(_finderPathCountByRouteId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByRouteId, args);
			}

			if ((tripModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByServiceId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					tripModelImpl.getOriginalService_id()
				};

				finderCache.removeResult(_finderPathCountByServiceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByServiceId, args);

				args = new Object[] {tripModelImpl.getService_id()};

				finderCache.removeResult(_finderPathCountByServiceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByServiceId, args);
			}

			if ((tripModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByTripId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					tripModelImpl.getOriginalTrip_id()
				};

				finderCache.removeResult(_finderPathCountByTripId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTripId, args);

				args = new Object[] {tripModelImpl.getTrip_id()};

				finderCache.removeResult(_finderPathCountByTripId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTripId, args);
			}
		}

		entityCache.putResult(
			TripModelImpl.ENTITY_CACHE_ENABLED, TripImpl.class,
			trip.getPrimaryKey(), trip, false);

		trip.resetOriginalValues();

		return trip;
	}

	/**
	 * Returns the trip with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the trip
	 * @return the trip
	 * @throws NoSuchTripException if a trip with the primary key could not be found
	 */
	@Override
	public Trip findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTripException {

		Trip trip = fetchByPrimaryKey(primaryKey);

		if (trip == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTripException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return trip;
	}

	/**
	 * Returns the trip with the primary key or throws a <code>NoSuchTripException</code> if it could not be found.
	 *
	 * @param id the primary key of the trip
	 * @return the trip
	 * @throws NoSuchTripException if a trip with the primary key could not be found
	 */
	@Override
	public Trip findByPrimaryKey(long id) throws NoSuchTripException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the trip with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the trip
	 * @return the trip, or <code>null</code> if a trip with the primary key could not be found
	 */
	@Override
	public Trip fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			TripModelImpl.ENTITY_CACHE_ENABLED, TripImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Trip trip = (Trip)serializable;

		if (trip == null) {
			Session session = null;

			try {
				session = openSession();

				trip = (Trip)session.get(TripImpl.class, primaryKey);

				if (trip != null) {
					cacheResult(trip);
				}
				else {
					entityCache.putResult(
						TripModelImpl.ENTITY_CACHE_ENABLED, TripImpl.class,
						primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					TripModelImpl.ENTITY_CACHE_ENABLED, TripImpl.class,
					primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return trip;
	}

	/**
	 * Returns the trip with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the trip
	 * @return the trip, or <code>null</code> if a trip with the primary key could not be found
	 */
	@Override
	public Trip fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, Trip> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Trip> map = new HashMap<Serializable, Trip>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Trip trip = fetchByPrimaryKey(primaryKey);

			if (trip != null) {
				map.put(primaryKey, trip);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				TripModelImpl.ENTITY_CACHE_ENABLED, TripImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Trip)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_TRIP_WHERE_PKS_IN);

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

			for (Trip trip : (List<Trip>)q.list()) {
				map.put(trip.getPrimaryKeyObj(), trip);

				cacheResult(trip);

				uncachedPrimaryKeys.remove(trip.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					TripModelImpl.ENTITY_CACHE_ENABLED, TripImpl.class,
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
	 * Returns all the trips.
	 *
	 * @return the trips
	 */
	@Override
	public List<Trip> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the trips.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TripModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of trips
	 * @param end the upper bound of the range of trips (not inclusive)
	 * @return the range of trips
	 */
	@Override
	public List<Trip> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the trips.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TripModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of trips
	 * @param end the upper bound of the range of trips (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of trips
	 */
	@Override
	public List<Trip> findAll(
		int start, int end, OrderByComparator<Trip> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the trips.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TripModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of trips
	 * @param end the upper bound of the range of trips (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of trips
	 */
	@Override
	public List<Trip> findAll(
		int start, int end, OrderByComparator<Trip> orderByComparator,
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

		List<Trip> list = null;

		if (retrieveFromCache) {
			list = (List<Trip>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TRIP);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRIP;

				if (pagination) {
					sql = sql.concat(TripModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Trip>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Trip>)QueryUtil.list(
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
	 * Removes all the trips from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Trip trip : findAll()) {
			remove(trip);
		}
	}

	/**
	 * Returns the number of trips.
	 *
	 * @return the number of trips
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRIP);

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
		return TripModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the trip persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			TripModelImpl.ENTITY_CACHE_ENABLED,
			TripModelImpl.FINDER_CACHE_ENABLED, TripImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			TripModelImpl.ENTITY_CACHE_ENABLED,
			TripModelImpl.FINDER_CACHE_ENABLED, TripImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			TripModelImpl.ENTITY_CACHE_ENABLED,
			TripModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByRouteId = new FinderPath(
			TripModelImpl.ENTITY_CACHE_ENABLED,
			TripModelImpl.FINDER_CACHE_ENABLED, TripImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRouteId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByRouteId = new FinderPath(
			TripModelImpl.ENTITY_CACHE_ENABLED,
			TripModelImpl.FINDER_CACHE_ENABLED, TripImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRouteId",
			new String[] {String.class.getName()},
			TripModelImpl.ROUTE_ID_COLUMN_BITMASK |
			TripModelImpl.TRIP_ID_COLUMN_BITMASK);

		_finderPathCountByRouteId = new FinderPath(
			TripModelImpl.ENTITY_CACHE_ENABLED,
			TripModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRouteId",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByServiceId = new FinderPath(
			TripModelImpl.ENTITY_CACHE_ENABLED,
			TripModelImpl.FINDER_CACHE_ENABLED, TripImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByServiceId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByServiceId = new FinderPath(
			TripModelImpl.ENTITY_CACHE_ENABLED,
			TripModelImpl.FINDER_CACHE_ENABLED, TripImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByServiceId",
			new String[] {String.class.getName()},
			TripModelImpl.SERVICE_ID_COLUMN_BITMASK |
			TripModelImpl.TRIP_ID_COLUMN_BITMASK);

		_finderPathCountByServiceId = new FinderPath(
			TripModelImpl.ENTITY_CACHE_ENABLED,
			TripModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByServiceId",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByTripId = new FinderPath(
			TripModelImpl.ENTITY_CACHE_ENABLED,
			TripModelImpl.FINDER_CACHE_ENABLED, TripImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTripId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByTripId = new FinderPath(
			TripModelImpl.ENTITY_CACHE_ENABLED,
			TripModelImpl.FINDER_CACHE_ENABLED, TripImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTripId",
			new String[] {String.class.getName()},
			TripModelImpl.TRIP_ID_COLUMN_BITMASK);

		_finderPathCountByTripId = new FinderPath(
			TripModelImpl.ENTITY_CACHE_ENABLED,
			TripModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTripId",
			new String[] {String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(TripImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_TRIP = "SELECT trip FROM Trip trip";

	private static final String _SQL_SELECT_TRIP_WHERE_PKS_IN =
		"SELECT trip FROM Trip trip WHERE id_ IN (";

	private static final String _SQL_SELECT_TRIP_WHERE =
		"SELECT trip FROM Trip trip WHERE ";

	private static final String _SQL_COUNT_TRIP =
		"SELECT COUNT(trip) FROM Trip trip";

	private static final String _SQL_COUNT_TRIP_WHERE =
		"SELECT COUNT(trip) FROM Trip trip WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "trip.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Trip exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Trip exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TripPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id"});

}