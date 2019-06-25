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

import eu.strasbourg.service.gtfs.exception.NoSuchStopException;
import eu.strasbourg.service.gtfs.model.Stop;
import eu.strasbourg.service.gtfs.model.impl.StopImpl;
import eu.strasbourg.service.gtfs.model.impl.StopModelImpl;
import eu.strasbourg.service.gtfs.service.persistence.StopPersistence;

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
 * The persistence implementation for the stop service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see StopPersistence
 * @see eu.strasbourg.service.gtfs.service.persistence.StopUtil
 * @generated
 */
@ProviderType
public class StopPersistenceImpl extends BasePersistenceImpl<Stop>
	implements StopPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StopUtil} to access the stop persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StopImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopModelImpl.FINDER_CACHE_ENABLED, StopImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopModelImpl.FINDER_CACHE_ENABLED, StopImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopModelImpl.FINDER_CACHE_ENABLED, StopImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopModelImpl.FINDER_CACHE_ENABLED, StopImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			StopModelImpl.UUID_COLUMN_BITMASK |
			StopModelImpl.STOP_ID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the stops where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching stops
	 */
	@Override
	public List<Stop> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Stop> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<Stop> findByUuid(String uuid, int start, int end,
		OrderByComparator<Stop> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<Stop> findByUuid(String uuid, int start, int end,
		OrderByComparator<Stop> orderByComparator, boolean retrieveFromCache) {
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

		List<Stop> list = null;

		if (retrieveFromCache) {
			list = (List<Stop>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Stop stop : list) {
					if (!Objects.equals(uuid, stop.getUuid())) {
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

			query.append(_SQL_SELECT_STOP_WHERE);

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
				query.append(StopModelImpl.ORDER_BY_JPQL);
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
					list = (List<Stop>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Stop>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first stop in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	@Override
	public Stop findByUuid_First(String uuid,
		OrderByComparator<Stop> orderByComparator) throws NoSuchStopException {
		Stop stop = fetchByUuid_First(uuid, orderByComparator);

		if (stop != null) {
			return stop;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStopException(msg.toString());
	}

	/**
	 * Returns the first stop in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop, or <code>null</code> if a matching stop could not be found
	 */
	@Override
	public Stop fetchByUuid_First(String uuid,
		OrderByComparator<Stop> orderByComparator) {
		List<Stop> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last stop in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	@Override
	public Stop findByUuid_Last(String uuid,
		OrderByComparator<Stop> orderByComparator) throws NoSuchStopException {
		Stop stop = fetchByUuid_Last(uuid, orderByComparator);

		if (stop != null) {
			return stop;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStopException(msg.toString());
	}

	/**
	 * Returns the last stop in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop, or <code>null</code> if a matching stop could not be found
	 */
	@Override
	public Stop fetchByUuid_Last(String uuid,
		OrderByComparator<Stop> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Stop> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Stop[] findByUuid_PrevAndNext(long id, String uuid,
		OrderByComparator<Stop> orderByComparator) throws NoSuchStopException {
		Stop stop = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Stop[] array = new StopImpl[3];

			array[0] = getByUuid_PrevAndNext(session, stop, uuid,
					orderByComparator, true);

			array[1] = stop;

			array[2] = getByUuid_PrevAndNext(session, stop, uuid,
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

	protected Stop getByUuid_PrevAndNext(Session session, Stop stop,
		String uuid, OrderByComparator<Stop> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_STOP_WHERE);

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
			query.append(StopModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(stop);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Stop> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the stops where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Stop stop : findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(stop);
		}
	}

	/**
	 * Returns the number of stops where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching stops
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_STOP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "stop.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "stop.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(stop.uuid IS NULL OR stop.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STOPID = new FinderPath(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopModelImpl.FINDER_CACHE_ENABLED, StopImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStopId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STOPID =
		new FinderPath(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopModelImpl.FINDER_CACHE_ENABLED, StopImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStopId",
			new String[] { String.class.getName() },
			StopModelImpl.STOP_ID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STOPID = new FinderPath(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStopId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the stops where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @return the matching stops
	 */
	@Override
	public List<Stop> findByStopId(String stop_id) {
		return findByStopId(stop_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Stop> findByStopId(String stop_id, int start, int end) {
		return findByStopId(stop_id, start, end, null);
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
	@Override
	public List<Stop> findByStopId(String stop_id, int start, int end,
		OrderByComparator<Stop> orderByComparator) {
		return findByStopId(stop_id, start, end, orderByComparator, true);
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
	@Override
	public List<Stop> findByStopId(String stop_id, int start, int end,
		OrderByComparator<Stop> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STOPID;
			finderArgs = new Object[] { stop_id };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STOPID;
			finderArgs = new Object[] { stop_id, start, end, orderByComparator };
		}

		List<Stop> list = null;

		if (retrieveFromCache) {
			list = (List<Stop>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Stop stop : list) {
					if (!Objects.equals(stop_id, stop.getStop_id())) {
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

			query.append(_SQL_SELECT_STOP_WHERE);

			boolean bindStop_id = false;

			if (stop_id == null) {
				query.append(_FINDER_COLUMN_STOPID_STOP_ID_1);
			}
			else if (stop_id.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_STOPID_STOP_ID_3);
			}
			else {
				bindStop_id = true;

				query.append(_FINDER_COLUMN_STOPID_STOP_ID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(StopModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStop_id) {
					qPos.add(stop_id);
				}

				if (!pagination) {
					list = (List<Stop>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Stop>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first stop in the ordered set where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	@Override
	public Stop findByStopId_First(String stop_id,
		OrderByComparator<Stop> orderByComparator) throws NoSuchStopException {
		Stop stop = fetchByStopId_First(stop_id, orderByComparator);

		if (stop != null) {
			return stop;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stop_id=");
		msg.append(stop_id);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStopException(msg.toString());
	}

	/**
	 * Returns the first stop in the ordered set where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop, or <code>null</code> if a matching stop could not be found
	 */
	@Override
	public Stop fetchByStopId_First(String stop_id,
		OrderByComparator<Stop> orderByComparator) {
		List<Stop> list = findByStopId(stop_id, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last stop in the ordered set where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	@Override
	public Stop findByStopId_Last(String stop_id,
		OrderByComparator<Stop> orderByComparator) throws NoSuchStopException {
		Stop stop = fetchByStopId_Last(stop_id, orderByComparator);

		if (stop != null) {
			return stop;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stop_id=");
		msg.append(stop_id);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStopException(msg.toString());
	}

	/**
	 * Returns the last stop in the ordered set where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop, or <code>null</code> if a matching stop could not be found
	 */
	@Override
	public Stop fetchByStopId_Last(String stop_id,
		OrderByComparator<Stop> orderByComparator) {
		int count = countByStopId(stop_id);

		if (count == 0) {
			return null;
		}

		List<Stop> list = findByStopId(stop_id, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Stop[] findByStopId_PrevAndNext(long id, String stop_id,
		OrderByComparator<Stop> orderByComparator) throws NoSuchStopException {
		Stop stop = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Stop[] array = new StopImpl[3];

			array[0] = getByStopId_PrevAndNext(session, stop, stop_id,
					orderByComparator, true);

			array[1] = stop;

			array[2] = getByStopId_PrevAndNext(session, stop, stop_id,
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

	protected Stop getByStopId_PrevAndNext(Session session, Stop stop,
		String stop_id, OrderByComparator<Stop> orderByComparator,
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

		query.append(_SQL_SELECT_STOP_WHERE);

		boolean bindStop_id = false;

		if (stop_id == null) {
			query.append(_FINDER_COLUMN_STOPID_STOP_ID_1);
		}
		else if (stop_id.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_STOPID_STOP_ID_3);
		}
		else {
			bindStop_id = true;

			query.append(_FINDER_COLUMN_STOPID_STOP_ID_2);
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
			query.append(StopModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindStop_id) {
			qPos.add(stop_id);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(stop);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Stop> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the stops where stop_id = &#63; from the database.
	 *
	 * @param stop_id the stop_id
	 */
	@Override
	public void removeByStopId(String stop_id) {
		for (Stop stop : findByStopId(stop_id, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(stop);
		}
	}

	/**
	 * Returns the number of stops where stop_id = &#63;.
	 *
	 * @param stop_id the stop_id
	 * @return the number of matching stops
	 */
	@Override
	public int countByStopId(String stop_id) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STOPID;

		Object[] finderArgs = new Object[] { stop_id };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_STOP_WHERE);

			boolean bindStop_id = false;

			if (stop_id == null) {
				query.append(_FINDER_COLUMN_STOPID_STOP_ID_1);
			}
			else if (stop_id.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_STOPID_STOP_ID_3);
			}
			else {
				bindStop_id = true;

				query.append(_FINDER_COLUMN_STOPID_STOP_ID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStop_id) {
					qPos.add(stop_id);
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

	private static final String _FINDER_COLUMN_STOPID_STOP_ID_1 = "stop.stop_id IS NULL";
	private static final String _FINDER_COLUMN_STOPID_STOP_ID_2 = "stop.stop_id = ?";
	private static final String _FINDER_COLUMN_STOPID_STOP_ID_3 = "(stop.stop_id IS NULL OR stop.stop_id = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STOPCODE = new FinderPath(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopModelImpl.FINDER_CACHE_ENABLED, StopImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStopCode",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STOPCODE =
		new FinderPath(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopModelImpl.FINDER_CACHE_ENABLED, StopImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStopCode",
			new String[] { String.class.getName() },
			StopModelImpl.STOP_CODE_COLUMN_BITMASK |
			StopModelImpl.STOP_ID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STOPCODE = new FinderPath(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStopCode",
			new String[] { String.class.getName() });

	/**
	 * Returns all the stops where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @return the matching stops
	 */
	@Override
	public List<Stop> findByStopCode(String stop_code) {
		return findByStopCode(stop_code, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<Stop> findByStopCode(String stop_code, int start, int end) {
		return findByStopCode(stop_code, start, end, null);
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
	@Override
	public List<Stop> findByStopCode(String stop_code, int start, int end,
		OrderByComparator<Stop> orderByComparator) {
		return findByStopCode(stop_code, start, end, orderByComparator, true);
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
	@Override
	public List<Stop> findByStopCode(String stop_code, int start, int end,
		OrderByComparator<Stop> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STOPCODE;
			finderArgs = new Object[] { stop_code };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STOPCODE;
			finderArgs = new Object[] { stop_code, start, end, orderByComparator };
		}

		List<Stop> list = null;

		if (retrieveFromCache) {
			list = (List<Stop>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Stop stop : list) {
					if (!Objects.equals(stop_code, stop.getStop_code())) {
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

			query.append(_SQL_SELECT_STOP_WHERE);

			boolean bindStop_code = false;

			if (stop_code == null) {
				query.append(_FINDER_COLUMN_STOPCODE_STOP_CODE_1);
			}
			else if (stop_code.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_STOPCODE_STOP_CODE_3);
			}
			else {
				bindStop_code = true;

				query.append(_FINDER_COLUMN_STOPCODE_STOP_CODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(StopModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStop_code) {
					qPos.add(stop_code);
				}

				if (!pagination) {
					list = (List<Stop>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Stop>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first stop in the ordered set where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	@Override
	public Stop findByStopCode_First(String stop_code,
		OrderByComparator<Stop> orderByComparator) throws NoSuchStopException {
		Stop stop = fetchByStopCode_First(stop_code, orderByComparator);

		if (stop != null) {
			return stop;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stop_code=");
		msg.append(stop_code);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStopException(msg.toString());
	}

	/**
	 * Returns the first stop in the ordered set where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stop, or <code>null</code> if a matching stop could not be found
	 */
	@Override
	public Stop fetchByStopCode_First(String stop_code,
		OrderByComparator<Stop> orderByComparator) {
		List<Stop> list = findByStopCode(stop_code, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last stop in the ordered set where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop
	 * @throws NoSuchStopException if a matching stop could not be found
	 */
	@Override
	public Stop findByStopCode_Last(String stop_code,
		OrderByComparator<Stop> orderByComparator) throws NoSuchStopException {
		Stop stop = fetchByStopCode_Last(stop_code, orderByComparator);

		if (stop != null) {
			return stop;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stop_code=");
		msg.append(stop_code);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStopException(msg.toString());
	}

	/**
	 * Returns the last stop in the ordered set where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stop, or <code>null</code> if a matching stop could not be found
	 */
	@Override
	public Stop fetchByStopCode_Last(String stop_code,
		OrderByComparator<Stop> orderByComparator) {
		int count = countByStopCode(stop_code);

		if (count == 0) {
			return null;
		}

		List<Stop> list = findByStopCode(stop_code, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Stop[] findByStopCode_PrevAndNext(long id, String stop_code,
		OrderByComparator<Stop> orderByComparator) throws NoSuchStopException {
		Stop stop = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Stop[] array = new StopImpl[3];

			array[0] = getByStopCode_PrevAndNext(session, stop, stop_code,
					orderByComparator, true);

			array[1] = stop;

			array[2] = getByStopCode_PrevAndNext(session, stop, stop_code,
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

	protected Stop getByStopCode_PrevAndNext(Session session, Stop stop,
		String stop_code, OrderByComparator<Stop> orderByComparator,
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

		query.append(_SQL_SELECT_STOP_WHERE);

		boolean bindStop_code = false;

		if (stop_code == null) {
			query.append(_FINDER_COLUMN_STOPCODE_STOP_CODE_1);
		}
		else if (stop_code.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_STOPCODE_STOP_CODE_3);
		}
		else {
			bindStop_code = true;

			query.append(_FINDER_COLUMN_STOPCODE_STOP_CODE_2);
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
			query.append(StopModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindStop_code) {
			qPos.add(stop_code);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(stop);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Stop> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the stops where stop_code = &#63; from the database.
	 *
	 * @param stop_code the stop_code
	 */
	@Override
	public void removeByStopCode(String stop_code) {
		for (Stop stop : findByStopCode(stop_code, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(stop);
		}
	}

	/**
	 * Returns the number of stops where stop_code = &#63;.
	 *
	 * @param stop_code the stop_code
	 * @return the number of matching stops
	 */
	@Override
	public int countByStopCode(String stop_code) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STOPCODE;

		Object[] finderArgs = new Object[] { stop_code };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_STOP_WHERE);

			boolean bindStop_code = false;

			if (stop_code == null) {
				query.append(_FINDER_COLUMN_STOPCODE_STOP_CODE_1);
			}
			else if (stop_code.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_STOPCODE_STOP_CODE_3);
			}
			else {
				bindStop_code = true;

				query.append(_FINDER_COLUMN_STOPCODE_STOP_CODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStop_code) {
					qPos.add(stop_code);
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

	private static final String _FINDER_COLUMN_STOPCODE_STOP_CODE_1 = "stop.stop_code IS NULL";
	private static final String _FINDER_COLUMN_STOPCODE_STOP_CODE_2 = "stop.stop_code = ?";
	private static final String _FINDER_COLUMN_STOPCODE_STOP_CODE_3 = "(stop.stop_code IS NULL OR stop.stop_code = '')";

	public StopPersistenceImpl() {
		setModelClass(Stop.class);

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
	 * Caches the stop in the entity cache if it is enabled.
	 *
	 * @param stop the stop
	 */
	@Override
	public void cacheResult(Stop stop) {
		entityCache.putResult(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopImpl.class, stop.getPrimaryKey(), stop);

		stop.resetOriginalValues();
	}

	/**
	 * Caches the stops in the entity cache if it is enabled.
	 *
	 * @param stops the stops
	 */
	@Override
	public void cacheResult(List<Stop> stops) {
		for (Stop stop : stops) {
			if (entityCache.getResult(StopModelImpl.ENTITY_CACHE_ENABLED,
						StopImpl.class, stop.getPrimaryKey()) == null) {
				cacheResult(stop);
			}
			else {
				stop.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all stops.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StopImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the stop.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Stop stop) {
		entityCache.removeResult(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopImpl.class, stop.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Stop> stops) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Stop stop : stops) {
			entityCache.removeResult(StopModelImpl.ENTITY_CACHE_ENABLED,
				StopImpl.class, stop.getPrimaryKey());
		}
	}

	/**
	 * Creates a new stop with the primary key. Does not add the stop to the database.
	 *
	 * @param id the primary key for the new stop
	 * @return the new stop
	 */
	@Override
	public Stop create(long id) {
		Stop stop = new StopImpl();

		stop.setNew(true);
		stop.setPrimaryKey(id);

		String uuid = PortalUUIDUtil.generate();

		stop.setUuid(uuid);

		return stop;
	}

	/**
	 * Removes the stop with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the stop
	 * @return the stop that was removed
	 * @throws NoSuchStopException if a stop with the primary key could not be found
	 */
	@Override
	public Stop remove(long id) throws NoSuchStopException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the stop with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the stop
	 * @return the stop that was removed
	 * @throws NoSuchStopException if a stop with the primary key could not be found
	 */
	@Override
	public Stop remove(Serializable primaryKey) throws NoSuchStopException {
		Session session = null;

		try {
			session = openSession();

			Stop stop = (Stop)session.get(StopImpl.class, primaryKey);

			if (stop == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStopException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stop);
		}
		catch (NoSuchStopException nsee) {
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
	protected Stop removeImpl(Stop stop) {
		stop = toUnwrappedModel(stop);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stop)) {
				stop = (Stop)session.get(StopImpl.class, stop.getPrimaryKeyObj());
			}

			if (stop != null) {
				session.delete(stop);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stop != null) {
			clearCache(stop);
		}

		return stop;
	}

	@Override
	public Stop updateImpl(Stop stop) {
		stop = toUnwrappedModel(stop);

		boolean isNew = stop.isNew();

		StopModelImpl stopModelImpl = (StopModelImpl)stop;

		if (Validator.isNull(stop.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			stop.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (stop.isNew()) {
				session.save(stop);

				stop.setNew(false);
			}
			else {
				stop = (Stop)session.merge(stop);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!StopModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { stopModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { stopModelImpl.getStop_id() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_STOPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STOPID,
				args);

			args = new Object[] { stopModelImpl.getStop_code() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_STOPCODE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STOPCODE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((stopModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { stopModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { stopModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((stopModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STOPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { stopModelImpl.getOriginalStop_id() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STOPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STOPID,
					args);

				args = new Object[] { stopModelImpl.getStop_id() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STOPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STOPID,
					args);
			}

			if ((stopModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STOPCODE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						stopModelImpl.getOriginalStop_code()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STOPCODE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STOPCODE,
					args);

				args = new Object[] { stopModelImpl.getStop_code() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STOPCODE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STOPCODE,
					args);
			}
		}

		entityCache.putResult(StopModelImpl.ENTITY_CACHE_ENABLED,
			StopImpl.class, stop.getPrimaryKey(), stop, false);

		stop.resetOriginalValues();

		return stop;
	}

	protected Stop toUnwrappedModel(Stop stop) {
		if (stop instanceof StopImpl) {
			return stop;
		}

		StopImpl stopImpl = new StopImpl();

		stopImpl.setNew(stop.isNew());
		stopImpl.setPrimaryKey(stop.getPrimaryKey());

		stopImpl.setUuid(stop.getUuid());
		stopImpl.setId(stop.getId());
		stopImpl.setStop_id(stop.getStop_id());
		stopImpl.setStop_code(stop.getStop_code());
		stopImpl.setStop_lat(stop.getStop_lat());
		stopImpl.setStop_lon(stop.getStop_lon());
		stopImpl.setStop_name(stop.getStop_name());
		stopImpl.setStop_url(stop.getStop_url());
		stopImpl.setStop_desc(stop.getStop_desc());

		return stopImpl;
	}

	/**
	 * Returns the stop with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the stop
	 * @return the stop
	 * @throws NoSuchStopException if a stop with the primary key could not be found
	 */
	@Override
	public Stop findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStopException {
		Stop stop = fetchByPrimaryKey(primaryKey);

		if (stop == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStopException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stop;
	}

	/**
	 * Returns the stop with the primary key or throws a {@link NoSuchStopException} if it could not be found.
	 *
	 * @param id the primary key of the stop
	 * @return the stop
	 * @throws NoSuchStopException if a stop with the primary key could not be found
	 */
	@Override
	public Stop findByPrimaryKey(long id) throws NoSuchStopException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the stop with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the stop
	 * @return the stop, or <code>null</code> if a stop with the primary key could not be found
	 */
	@Override
	public Stop fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StopModelImpl.ENTITY_CACHE_ENABLED,
				StopImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Stop stop = (Stop)serializable;

		if (stop == null) {
			Session session = null;

			try {
				session = openSession();

				stop = (Stop)session.get(StopImpl.class, primaryKey);

				if (stop != null) {
					cacheResult(stop);
				}
				else {
					entityCache.putResult(StopModelImpl.ENTITY_CACHE_ENABLED,
						StopImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StopModelImpl.ENTITY_CACHE_ENABLED,
					StopImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stop;
	}

	/**
	 * Returns the stop with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the stop
	 * @return the stop, or <code>null</code> if a stop with the primary key could not be found
	 */
	@Override
	public Stop fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, Stop> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Stop> map = new HashMap<Serializable, Stop>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Stop stop = fetchByPrimaryKey(primaryKey);

			if (stop != null) {
				map.put(primaryKey, stop);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(StopModelImpl.ENTITY_CACHE_ENABLED,
					StopImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Stop)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_STOP_WHERE_PKS_IN);

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

			for (Stop stop : (List<Stop>)q.list()) {
				map.put(stop.getPrimaryKeyObj(), stop);

				cacheResult(stop);

				uncachedPrimaryKeys.remove(stop.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(StopModelImpl.ENTITY_CACHE_ENABLED,
					StopImpl.class, primaryKey, nullModel);
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
	 * Returns all the stops.
	 *
	 * @return the stops
	 */
	@Override
	public List<Stop> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Stop> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Stop> findAll(int start, int end,
		OrderByComparator<Stop> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<Stop> findAll(int start, int end,
		OrderByComparator<Stop> orderByComparator, boolean retrieveFromCache) {
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

		List<Stop> list = null;

		if (retrieveFromCache) {
			list = (List<Stop>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STOP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STOP;

				if (pagination) {
					sql = sql.concat(StopModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Stop>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Stop>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the stops from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Stop stop : findAll()) {
			remove(stop);
		}
	}

	/**
	 * Returns the number of stops.
	 *
	 * @return the number of stops
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STOP);

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
		return StopModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the stop persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StopImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STOP = "SELECT stop FROM Stop stop";
	private static final String _SQL_SELECT_STOP_WHERE_PKS_IN = "SELECT stop FROM Stop stop WHERE id_ IN (";
	private static final String _SQL_SELECT_STOP_WHERE = "SELECT stop FROM Stop stop WHERE ";
	private static final String _SQL_COUNT_STOP = "SELECT COUNT(stop) FROM Stop stop";
	private static final String _SQL_COUNT_STOP_WHERE = "SELECT COUNT(stop) FROM Stop stop WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stop.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Stop exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Stop exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(StopPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "id"
			});
}