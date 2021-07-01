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

package eu.strasbourg.service.agenda.service.persistence.impl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException;
import eu.strasbourg.service.agenda.model.CacheJson;
import eu.strasbourg.service.agenda.model.impl.CacheJsonImpl;
import eu.strasbourg.service.agenda.model.impl.CacheJsonModelImpl;
import eu.strasbourg.service.agenda.service.persistence.CacheJsonPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the cache json service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @generated
 */
@ProviderType
public class CacheJsonPersistenceImpl
	extends BasePersistenceImpl<CacheJson> implements CacheJsonPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CacheJsonUtil</code> to access the cache json persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CacheJsonImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the cache jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cache jsons
	 */
	@Override
	public List<CacheJson> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CacheJson> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid;
			finderArgs = new Object[] {uuid};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<CacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CacheJson cacheJson : list) {
					if (!uuid.equals(cacheJson.getUuid())) {
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

			query.append(_SQL_SELECT_CACHEJSON_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CacheJsonModelImpl.ORDER_BY_JPQL);
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
					list = (List<CacheJson>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CacheJson>)QueryUtil.list(
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
	 * Returns the first cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByUuid_First(
			String uuid, OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByUuid_First(uuid, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByUuid_First(
		String uuid, OrderByComparator<CacheJson> orderByComparator) {

		List<CacheJson> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByUuid_Last(
			String uuid, OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByUuid_Last(uuid, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByUuid_Last(
		String uuid, OrderByComparator<CacheJson> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CacheJson> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cache jsons before and after the current cache json in the ordered set where uuid = &#63;.
	 *
	 * @param eventId the primary key of the current cache json
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson[] findByUuid_PrevAndNext(
			long eventId, String uuid,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		uuid = Objects.toString(uuid, "");

		CacheJson cacheJson = findByPrimaryKey(eventId);

		Session session = null;

		try {
			session = openSession();

			CacheJson[] array = new CacheJsonImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, cacheJson, uuid, orderByComparator, true);

			array[1] = cacheJson;

			array[2] = getByUuid_PrevAndNext(
				session, cacheJson, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CacheJson getByUuid_PrevAndNext(
		Session session, CacheJson cacheJson, String uuid,
		OrderByComparator<CacheJson> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CACHEJSON_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
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
			query.append(CacheJsonModelImpl.ORDER_BY_JPQL);
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
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cacheJson)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CacheJson> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cache jsons where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CacheJson cacheJson :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cacheJson);
		}
	}

	/**
	 * Returns the number of cache jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cache jsons
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CACHEJSON_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"cacheJson.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(cacheJson.uuid IS NULL OR cacheJson.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByeventId;
	private FinderPath _finderPathWithoutPaginationFindByeventId;
	private FinderPath _finderPathCountByeventId;

	/**
	 * Returns all the cache jsons where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching cache jsons
	 */
	@Override
	public List<CacheJson> findByeventId(long eventId) {
		return findByeventId(
			eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cache jsons where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByeventId(long eventId, int start, int end) {
		return findByeventId(eventId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cache jsons where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<CacheJson> orderByComparator) {

		return findByeventId(eventId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cache jsons where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByeventId;
			finderArgs = new Object[] {eventId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByeventId;
			finderArgs = new Object[] {eventId, start, end, orderByComparator};
		}

		List<CacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CacheJson cacheJson : list) {
					if ((eventId != cacheJson.getEventId())) {
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

			query.append(_SQL_SELECT_CACHEJSON_WHERE);

			query.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CacheJsonModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				if (!pagination) {
					list = (List<CacheJson>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CacheJson>)QueryUtil.list(
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
	 * Returns the first cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByeventId_First(
			long eventId, OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByeventId_First(eventId, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventId=");
		msg.append(eventId);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByeventId_First(
		long eventId, OrderByComparator<CacheJson> orderByComparator) {

		List<CacheJson> list = findByeventId(eventId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByeventId_Last(
			long eventId, OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByeventId_Last(eventId, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventId=");
		msg.append(eventId);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByeventId_Last(
		long eventId, OrderByComparator<CacheJson> orderByComparator) {

		int count = countByeventId(eventId);

		if (count == 0) {
			return null;
		}

		List<CacheJson> list = findByeventId(
			eventId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the cache jsons where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 */
	@Override
	public void removeByeventId(long eventId) {
		for (CacheJson cacheJson :
				findByeventId(
					eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cacheJson);
		}
	}

	/**
	 * Returns the number of cache jsons where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching cache jsons
	 */
	@Override
	public int countByeventId(long eventId) {
		FinderPath finderPath = _finderPathCountByeventId;

		Object[] finderArgs = new Object[] {eventId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CACHEJSON_WHERE);

			query.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

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

	private static final String _FINDER_COLUMN_EVENTID_EVENTID_2 =
		"cacheJson.eventId = ?";

	private FinderPath _finderPathWithPaginationFindByCreatedDateAndIsActive;
	private FinderPath _finderPathWithPaginationCountByCreatedDateAndIsActive;

	/**
	 * Returns all the cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @return the matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive) {

		return findByCreatedDateAndIsActive(
			createEvent, isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive, int start, int end) {

		return findByCreatedDateAndIsActive(
			createEvent, isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive, int start, int end,
		OrderByComparator<CacheJson> orderByComparator) {

		return findByCreatedDateAndIsActive(
			createEvent, isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive, int start, int end,
		OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByCreatedDateAndIsActive;
		finderArgs = new Object[] {
			_getTime(createEvent), isActive, start, end, orderByComparator
		};

		List<CacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CacheJson cacheJson : list) {
					if ((createEvent.getTime() >
							cacheJson.getCreateEvent().getTime()) ||
						(isActive != cacheJson.isIsActive())) {

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
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_CACHEJSON_WHERE);

			boolean bindCreateEvent = false;

			if (createEvent == null) {
				query.append(
					_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEEVENT_1);
			}
			else {
				bindCreateEvent = true;

				query.append(
					_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEEVENT_2);
			}

			query.append(_FINDER_COLUMN_CREATEDDATEANDISACTIVE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CacheJsonModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateEvent) {
					qPos.add(new Timestamp(createEvent.getTime()));
				}

				qPos.add(isActive);

				if (!pagination) {
					list = (List<CacheJson>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CacheJson>)QueryUtil.list(
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
	 * Returns the first cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByCreatedDateAndIsActive_First(
			Date createEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByCreatedDateAndIsActive_First(
			createEvent, isActive, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createEvent=");
		msg.append(createEvent);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByCreatedDateAndIsActive_First(
		Date createEvent, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		List<CacheJson> list = findByCreatedDateAndIsActive(
			createEvent, isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByCreatedDateAndIsActive_Last(
			Date createEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByCreatedDateAndIsActive_Last(
			createEvent, isActive, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createEvent=");
		msg.append(createEvent);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByCreatedDateAndIsActive_Last(
		Date createEvent, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		int count = countByCreatedDateAndIsActive(createEvent, isActive);

		if (count == 0) {
			return null;
		}

		List<CacheJson> list = findByCreatedDateAndIsActive(
			createEvent, isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cache jsons before and after the current cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current cache json
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson[] findByCreatedDateAndIsActive_PrevAndNext(
			long eventId, Date createEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = findByPrimaryKey(eventId);

		Session session = null;

		try {
			session = openSession();

			CacheJson[] array = new CacheJsonImpl[3];

			array[0] = getByCreatedDateAndIsActive_PrevAndNext(
				session, cacheJson, createEvent, isActive, orderByComparator,
				true);

			array[1] = cacheJson;

			array[2] = getByCreatedDateAndIsActive_PrevAndNext(
				session, cacheJson, createEvent, isActive, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CacheJson getByCreatedDateAndIsActive_PrevAndNext(
		Session session, CacheJson cacheJson, Date createEvent,
		boolean isActive, OrderByComparator<CacheJson> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CACHEJSON_WHERE);

		boolean bindCreateEvent = false;

		if (createEvent == null) {
			query.append(_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEEVENT_1);
		}
		else {
			bindCreateEvent = true;

			query.append(_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEEVENT_2);
		}

		query.append(_FINDER_COLUMN_CREATEDDATEANDISACTIVE_ISACTIVE_2);

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
			query.append(CacheJsonModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCreateEvent) {
			qPos.add(new Timestamp(createEvent.getTime()));
		}

		qPos.add(isActive);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cacheJson)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CacheJson> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cache jsons where createEvent &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 */
	@Override
	public void removeByCreatedDateAndIsActive(
		Date createEvent, boolean isActive) {

		for (CacheJson cacheJson :
				findByCreatedDateAndIsActive(
					createEvent, isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cacheJson);
		}
	}

	/**
	 * Returns the number of cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @return the number of matching cache jsons
	 */
	@Override
	public int countByCreatedDateAndIsActive(
		Date createEvent, boolean isActive) {

		FinderPath finderPath =
			_finderPathWithPaginationCountByCreatedDateAndIsActive;

		Object[] finderArgs = new Object[] {_getTime(createEvent), isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CACHEJSON_WHERE);

			boolean bindCreateEvent = false;

			if (createEvent == null) {
				query.append(
					_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEEVENT_1);
			}
			else {
				bindCreateEvent = true;

				query.append(
					_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEEVENT_2);
			}

			query.append(_FINDER_COLUMN_CREATEDDATEANDISACTIVE_ISACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateEvent) {
					qPos.add(new Timestamp(createEvent.getTime()));
				}

				qPos.add(isActive);

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

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEEVENT_1 =
			"cacheJson.createEvent IS NULL AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEEVENT_2 =
			"cacheJson.createEvent >= ? AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDISACTIVE_ISACTIVE_2 =
			"cacheJson.isActive = ?";

	private FinderPath
		_finderPathWithPaginationFindByCreatedDateAndModifiedDateAndIsActive;
	private FinderPath
		_finderPathWithPaginationCountByCreatedDateAndModifiedDateAndIsActive;

	/**
	 * Returns all the cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive) {

		return findByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive, int start,
		int end) {

		return findByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive, int start,
		int end, OrderByComparator<CacheJson> orderByComparator) {

		return findByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive, int start,
		int end, OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath =
			_finderPathWithPaginationFindByCreatedDateAndModifiedDateAndIsActive;
		finderArgs = new Object[] {
			_getTime(createEvent), _getTime(modifiedEvent), isActive, start,
			end, orderByComparator
		};

		List<CacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CacheJson cacheJson : list) {
					if ((createEvent.getTime() <=
							cacheJson.getCreateEvent().getTime()) ||
						(modifiedEvent.getTime() >
							cacheJson.getModifiedEvent().getTime()) ||
						(isActive != cacheJson.isIsActive())) {

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
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_CACHEJSON_WHERE);

			boolean bindCreateEvent = false;

			if (createEvent == null) {
				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEEVENT_1);
			}
			else {
				bindCreateEvent = true;

				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEEVENT_2);
			}

			boolean bindModifiedEvent = false;

			if (modifiedEvent == null) {
				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_1);
			}
			else {
				bindModifiedEvent = true;

				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_2);
			}

			query.append(
				_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CacheJsonModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateEvent) {
					qPos.add(new Timestamp(createEvent.getTime()));
				}

				if (bindModifiedEvent) {
					qPos.add(new Timestamp(modifiedEvent.getTime()));
				}

				qPos.add(isActive);

				if (!pagination) {
					list = (List<CacheJson>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CacheJson>)QueryUtil.list(
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
	 * Returns the first cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByCreatedDateAndModifiedDateAndIsActive_First(
			Date createEvent, Date modifiedEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson =
			fetchByCreatedDateAndModifiedDateAndIsActive_First(
				createEvent, modifiedEvent, isActive, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createEvent=");
		msg.append(createEvent);

		msg.append(", modifiedEvent=");
		msg.append(modifiedEvent);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByCreatedDateAndModifiedDateAndIsActive_First(
		Date createEvent, Date modifiedEvent, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		List<CacheJson> list = findByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByCreatedDateAndModifiedDateAndIsActive_Last(
			Date createEvent, Date modifiedEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByCreatedDateAndModifiedDateAndIsActive_Last(
			createEvent, modifiedEvent, isActive, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createEvent=");
		msg.append(createEvent);

		msg.append(", modifiedEvent=");
		msg.append(modifiedEvent);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByCreatedDateAndModifiedDateAndIsActive_Last(
		Date createEvent, Date modifiedEvent, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		int count = countByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive);

		if (count == 0) {
			return null;
		}

		List<CacheJson> list = findByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cache jsons before and after the current cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current cache json
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson[] findByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
			long eventId, Date createEvent, Date modifiedEvent,
			boolean isActive, OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = findByPrimaryKey(eventId);

		Session session = null;

		try {
			session = openSession();

			CacheJson[] array = new CacheJsonImpl[3];

			array[0] = getByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				session, cacheJson, createEvent, modifiedEvent, isActive,
				orderByComparator, true);

			array[1] = cacheJson;

			array[2] = getByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				session, cacheJson, createEvent, modifiedEvent, isActive,
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

	protected CacheJson getByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
		Session session, CacheJson cacheJson, Date createEvent,
		Date modifiedEvent, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_CACHEJSON_WHERE);

		boolean bindCreateEvent = false;

		if (createEvent == null) {
			query.append(
				_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEEVENT_1);
		}
		else {
			bindCreateEvent = true;

			query.append(
				_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEEVENT_2);
		}

		boolean bindModifiedEvent = false;

		if (modifiedEvent == null) {
			query.append(
				_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_1);
		}
		else {
			bindModifiedEvent = true;

			query.append(
				_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_2);
		}

		query.append(
			_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_ISACTIVE_2);

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
			query.append(CacheJsonModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCreateEvent) {
			qPos.add(new Timestamp(createEvent.getTime()));
		}

		if (bindModifiedEvent) {
			qPos.add(new Timestamp(modifiedEvent.getTime()));
		}

		qPos.add(isActive);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cacheJson)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CacheJson> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 */
	@Override
	public void removeByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive) {

		for (CacheJson cacheJson :
				findByCreatedDateAndModifiedDateAndIsActive(
					createEvent, modifiedEvent, isActive, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(cacheJson);
		}
	}

	/**
	 * Returns the number of cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the number of matching cache jsons
	 */
	@Override
	public int countByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive) {

		FinderPath finderPath =
			_finderPathWithPaginationCountByCreatedDateAndModifiedDateAndIsActive;

		Object[] finderArgs = new Object[] {
			_getTime(createEvent), _getTime(modifiedEvent), isActive
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CACHEJSON_WHERE);

			boolean bindCreateEvent = false;

			if (createEvent == null) {
				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEEVENT_1);
			}
			else {
				bindCreateEvent = true;

				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEEVENT_2);
			}

			boolean bindModifiedEvent = false;

			if (modifiedEvent == null) {
				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_1);
			}
			else {
				bindModifiedEvent = true;

				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_2);
			}

			query.append(
				_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_ISACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateEvent) {
					qPos.add(new Timestamp(createEvent.getTime()));
				}

				if (bindModifiedEvent) {
					qPos.add(new Timestamp(modifiedEvent.getTime()));
				}

				qPos.add(isActive);

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

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEEVENT_1 =
			"cacheJson.createEvent IS NULL AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEEVENT_2 =
			"cacheJson.createEvent < ? AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_1 =
			"cacheJson.modifiedEvent IS NULL AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_2 =
			"cacheJson.modifiedEvent >= ? AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_ISACTIVE_2 =
			"cacheJson.isActive = ?";

	private FinderPath _finderPathWithPaginationFindByModifiedDateAndIsActive;
	private FinderPath _finderPathWithPaginationCountByModifiedDateAndIsActive;

	/**
	 * Returns all the cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the matching cache jsons
	 */
	@Override
	public List<CacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive) {

		return findByModifiedDateAndIsActive(
			modifiedEvent, isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive, int start, int end) {

		return findByModifiedDateAndIsActive(
			modifiedEvent, isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive, int start, int end,
		OrderByComparator<CacheJson> orderByComparator) {

		return findByModifiedDateAndIsActive(
			modifiedEvent, isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive, int start, int end,
		OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByModifiedDateAndIsActive;
		finderArgs = new Object[] {
			_getTime(modifiedEvent), isActive, start, end, orderByComparator
		};

		List<CacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CacheJson cacheJson : list) {
					if ((modifiedEvent.getTime() >
							cacheJson.getModifiedEvent().getTime()) ||
						(isActive != cacheJson.isIsActive())) {

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
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_CACHEJSON_WHERE);

			boolean bindModifiedEvent = false;

			if (modifiedEvent == null) {
				query.append(
					_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_1);
			}
			else {
				bindModifiedEvent = true;

				query.append(
					_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_2);
			}

			query.append(_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CacheJsonModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedEvent) {
					qPos.add(new Timestamp(modifiedEvent.getTime()));
				}

				qPos.add(isActive);

				if (!pagination) {
					list = (List<CacheJson>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CacheJson>)QueryUtil.list(
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
	 * Returns the first cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByModifiedDateAndIsActive_First(
			Date modifiedEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByModifiedDateAndIsActive_First(
			modifiedEvent, isActive, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedEvent=");
		msg.append(modifiedEvent);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByModifiedDateAndIsActive_First(
		Date modifiedEvent, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		List<CacheJson> list = findByModifiedDateAndIsActive(
			modifiedEvent, isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByModifiedDateAndIsActive_Last(
			Date modifiedEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByModifiedDateAndIsActive_Last(
			modifiedEvent, isActive, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedEvent=");
		msg.append(modifiedEvent);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByModifiedDateAndIsActive_Last(
		Date modifiedEvent, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		int count = countByModifiedDateAndIsActive(modifiedEvent, isActive);

		if (count == 0) {
			return null;
		}

		List<CacheJson> list = findByModifiedDateAndIsActive(
			modifiedEvent, isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cache jsons before and after the current cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current cache json
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson[] findByModifiedDateAndIsActive_PrevAndNext(
			long eventId, Date modifiedEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = findByPrimaryKey(eventId);

		Session session = null;

		try {
			session = openSession();

			CacheJson[] array = new CacheJsonImpl[3];

			array[0] = getByModifiedDateAndIsActive_PrevAndNext(
				session, cacheJson, modifiedEvent, isActive, orderByComparator,
				true);

			array[1] = cacheJson;

			array[2] = getByModifiedDateAndIsActive_PrevAndNext(
				session, cacheJson, modifiedEvent, isActive, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CacheJson getByModifiedDateAndIsActive_PrevAndNext(
		Session session, CacheJson cacheJson, Date modifiedEvent,
		boolean isActive, OrderByComparator<CacheJson> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CACHEJSON_WHERE);

		boolean bindModifiedEvent = false;

		if (modifiedEvent == null) {
			query.append(
				_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_1);
		}
		else {
			bindModifiedEvent = true;

			query.append(
				_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_2);
		}

		query.append(_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_ISACTIVE_2);

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
			query.append(CacheJsonModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindModifiedEvent) {
			qPos.add(new Timestamp(modifiedEvent.getTime()));
		}

		qPos.add(isActive);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cacheJson)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CacheJson> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cache jsons where modifiedEvent &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 */
	@Override
	public void removeByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive) {

		for (CacheJson cacheJson :
				findByModifiedDateAndIsActive(
					modifiedEvent, isActive, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(cacheJson);
		}
	}

	/**
	 * Returns the number of cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the number of matching cache jsons
	 */
	@Override
	public int countByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive) {

		FinderPath finderPath =
			_finderPathWithPaginationCountByModifiedDateAndIsActive;

		Object[] finderArgs = new Object[] {_getTime(modifiedEvent), isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CACHEJSON_WHERE);

			boolean bindModifiedEvent = false;

			if (modifiedEvent == null) {
				query.append(
					_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_1);
			}
			else {
				bindModifiedEvent = true;

				query.append(
					_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_2);
			}

			query.append(_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_ISACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedEvent) {
					qPos.add(new Timestamp(modifiedEvent.getTime()));
				}

				qPos.add(isActive);

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

	private static final String
		_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_1 =
			"cacheJson.modifiedEvent IS NULL AND ";

	private static final String
		_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDEVENT_2 =
			"cacheJson.modifiedEvent >= ? AND ";

	private static final String
		_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_ISACTIVE_2 =
			"cacheJson.isActive = ?";

	public CacheJsonPersistenceImpl() {
		setModelClass(CacheJson.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

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
	 * Caches the cache json in the entity cache if it is enabled.
	 *
	 * @param cacheJson the cache json
	 */
	@Override
	public void cacheResult(CacheJson cacheJson) {
		entityCache.putResult(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED, CacheJsonImpl.class,
			cacheJson.getPrimaryKey(), cacheJson);

		cacheJson.resetOriginalValues();
	}

	/**
	 * Caches the cache jsons in the entity cache if it is enabled.
	 *
	 * @param cacheJsons the cache jsons
	 */
	@Override
	public void cacheResult(List<CacheJson> cacheJsons) {
		for (CacheJson cacheJson : cacheJsons) {
			if (entityCache.getResult(
					CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
					CacheJsonImpl.class, cacheJson.getPrimaryKey()) == null) {

				cacheResult(cacheJson);
			}
			else {
				cacheJson.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cache jsons.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CacheJsonImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cache json.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CacheJson cacheJson) {
		entityCache.removeResult(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED, CacheJsonImpl.class,
			cacheJson.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CacheJson> cacheJsons) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CacheJson cacheJson : cacheJsons) {
			entityCache.removeResult(
				CacheJsonModelImpl.ENTITY_CACHE_ENABLED, CacheJsonImpl.class,
				cacheJson.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cache json with the primary key. Does not add the cache json to the database.
	 *
	 * @param eventId the primary key for the new cache json
	 * @return the new cache json
	 */
	@Override
	public CacheJson create(long eventId) {
		CacheJson cacheJson = new CacheJsonImpl();

		cacheJson.setNew(true);
		cacheJson.setPrimaryKey(eventId);

		String uuid = PortalUUIDUtil.generate();

		cacheJson.setUuid(uuid);

		return cacheJson;
	}

	/**
	 * Removes the cache json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventId the primary key of the cache json
	 * @return the cache json that was removed
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson remove(long eventId) throws NoSuchCacheJsonException {
		return remove((Serializable)eventId);
	}

	/**
	 * Removes the cache json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cache json
	 * @return the cache json that was removed
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson remove(Serializable primaryKey)
		throws NoSuchCacheJsonException {

		Session session = null;

		try {
			session = openSession();

			CacheJson cacheJson = (CacheJson)session.get(
				CacheJsonImpl.class, primaryKey);

			if (cacheJson == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCacheJsonException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cacheJson);
		}
		catch (NoSuchCacheJsonException nsee) {
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
	protected CacheJson removeImpl(CacheJson cacheJson) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cacheJson)) {
				cacheJson = (CacheJson)session.get(
					CacheJsonImpl.class, cacheJson.getPrimaryKeyObj());
			}

			if (cacheJson != null) {
				session.delete(cacheJson);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cacheJson != null) {
			clearCache(cacheJson);
		}

		return cacheJson;
	}

	@Override
	public CacheJson updateImpl(CacheJson cacheJson) {
		boolean isNew = cacheJson.isNew();

		if (!(cacheJson instanceof CacheJsonModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cacheJson.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(cacheJson);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cacheJson proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CacheJson implementation " +
					cacheJson.getClass());
		}

		CacheJsonModelImpl cacheJsonModelImpl = (CacheJsonModelImpl)cacheJson;

		if (Validator.isNull(cacheJson.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cacheJson.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (cacheJson.isNew()) {
				session.save(cacheJson);

				cacheJson.setNew(false);
			}
			else {
				cacheJson = (CacheJson)session.merge(cacheJson);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CacheJsonModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {cacheJsonModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {cacheJsonModelImpl.getEventId()};

			finderCache.removeResult(_finderPathCountByeventId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByeventId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((cacheJsonModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cacheJsonModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {cacheJsonModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((cacheJsonModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByeventId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cacheJsonModelImpl.getOriginalEventId()
				};

				finderCache.removeResult(_finderPathCountByeventId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByeventId, args);

				args = new Object[] {cacheJsonModelImpl.getEventId()};

				finderCache.removeResult(_finderPathCountByeventId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByeventId, args);
			}
		}

		entityCache.putResult(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED, CacheJsonImpl.class,
			cacheJson.getPrimaryKey(), cacheJson, false);

		cacheJson.resetOriginalValues();

		return cacheJson;
	}

	/**
	 * Returns the cache json with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cache json
	 * @return the cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByPrimaryKey(primaryKey);

		if (cacheJson == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCacheJsonException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cacheJson;
	}

	/**
	 * Returns the cache json with the primary key or throws a <code>NoSuchCacheJsonException</code> if it could not be found.
	 *
	 * @param eventId the primary key of the cache json
	 * @return the cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson findByPrimaryKey(long eventId)
		throws NoSuchCacheJsonException {

		return findByPrimaryKey((Serializable)eventId);
	}

	/**
	 * Returns the cache json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cache json
	 * @return the cache json, or <code>null</code> if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED, CacheJsonImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CacheJson cacheJson = (CacheJson)serializable;

		if (cacheJson == null) {
			Session session = null;

			try {
				session = openSession();

				cacheJson = (CacheJson)session.get(
					CacheJsonImpl.class, primaryKey);

				if (cacheJson != null) {
					cacheResult(cacheJson);
				}
				else {
					entityCache.putResult(
						CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
						CacheJsonImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
					CacheJsonImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cacheJson;
	}

	/**
	 * Returns the cache json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventId the primary key of the cache json
	 * @return the cache json, or <code>null</code> if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson fetchByPrimaryKey(long eventId) {
		return fetchByPrimaryKey((Serializable)eventId);
	}

	@Override
	public Map<Serializable, CacheJson> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CacheJson> map =
			new HashMap<Serializable, CacheJson>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CacheJson cacheJson = fetchByPrimaryKey(primaryKey);

			if (cacheJson != null) {
				map.put(primaryKey, cacheJson);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CacheJsonModelImpl.ENTITY_CACHE_ENABLED, CacheJsonImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CacheJson)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_CACHEJSON_WHERE_PKS_IN);

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

			for (CacheJson cacheJson : (List<CacheJson>)q.list()) {
				map.put(cacheJson.getPrimaryKeyObj(), cacheJson);

				cacheResult(cacheJson);

				uncachedPrimaryKeys.remove(cacheJson.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
					CacheJsonImpl.class, primaryKey, nullModel);
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
	 * Returns all the cache jsons.
	 *
	 * @return the cache jsons
	 */
	@Override
	public List<CacheJson> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of cache jsons
	 */
	@Override
	public List<CacheJson> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cache jsons
	 */
	@Override
	public List<CacheJson> findAll(
		int start, int end, OrderByComparator<CacheJson> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cache jsons
	 */
	@Override
	public List<CacheJson> findAll(
		int start, int end, OrderByComparator<CacheJson> orderByComparator,
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

		List<CacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CACHEJSON);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CACHEJSON;

				if (pagination) {
					sql = sql.concat(CacheJsonModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CacheJson>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CacheJson>)QueryUtil.list(
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
	 * Removes all the cache jsons from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CacheJson cacheJson : findAll()) {
			remove(cacheJson);
		}
	}

	/**
	 * Returns the number of cache jsons.
	 *
	 * @return the number of cache jsons
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CACHEJSON);

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
		return CacheJsonModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cache json persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, CacheJsonImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, CacheJsonImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, CacheJsonImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, CacheJsonImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CacheJsonModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByeventId = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, CacheJsonImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByeventId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByeventId = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, CacheJsonImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByeventId",
			new String[] {Long.class.getName()},
			CacheJsonModelImpl.EVENTID_COLUMN_BITMASK);

		_finderPathCountByeventId = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByeventId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCreatedDateAndIsActive = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, CacheJsonImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCreatedDateAndIsActive",
			new String[] {
				Date.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByCreatedDateAndIsActive = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByCreatedDateAndIsActive",
			new String[] {Date.class.getName(), Boolean.class.getName()});

		_finderPathWithPaginationFindByCreatedDateAndModifiedDateAndIsActive =
			new FinderPath(
				CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
				CacheJsonModelImpl.FINDER_CACHE_ENABLED, CacheJsonImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByCreatedDateAndModifiedDateAndIsActive",
				new String[] {
					Date.class.getName(), Date.class.getName(),
					Boolean.class.getName(), Integer.class.getName(),
					Integer.class.getName(), OrderByComparator.class.getName()
				});

		_finderPathWithPaginationCountByCreatedDateAndModifiedDateAndIsActive =
			new FinderPath(
				CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
				CacheJsonModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"countByCreatedDateAndModifiedDateAndIsActive",
				new String[] {
					Date.class.getName(), Date.class.getName(),
					Boolean.class.getName()
				});

		_finderPathWithPaginationFindByModifiedDateAndIsActive = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, CacheJsonImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByModifiedDateAndIsActive",
			new String[] {
				Date.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByModifiedDateAndIsActive =
			new FinderPath(
				CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
				CacheJsonModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"countByModifiedDateAndIsActive",
				new String[] {Date.class.getName(), Boolean.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CacheJsonImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_CACHEJSON =
		"SELECT cacheJson FROM CacheJson cacheJson";

	private static final String _SQL_SELECT_CACHEJSON_WHERE_PKS_IN =
		"SELECT cacheJson FROM CacheJson cacheJson WHERE eventId IN (";

	private static final String _SQL_SELECT_CACHEJSON_WHERE =
		"SELECT cacheJson FROM CacheJson cacheJson WHERE ";

	private static final String _SQL_COUNT_CACHEJSON =
		"SELECT COUNT(cacheJson) FROM CacheJson cacheJson";

	private static final String _SQL_COUNT_CACHEJSON_WHERE =
		"SELECT COUNT(cacheJson) FROM CacheJson cacheJson WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "cacheJson.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CacheJson exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CacheJson exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CacheJsonPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}