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

package eu.strasbourg.service.place.service.persistence.impl;

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

import eu.strasbourg.service.place.exception.NoSuchCacheJsonException;
import eu.strasbourg.service.place.model.CacheJson;
import eu.strasbourg.service.place.model.impl.CacheJsonImpl;
import eu.strasbourg.service.place.model.impl.CacheJsonModelImpl;
import eu.strasbourg.service.place.service.persistence.CacheJsonPersistence;

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
 * @author Angelique Zunino Champougny
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
	 * @param sigId the primary key of the current cache json
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson[] findByUuid_PrevAndNext(
			String sigId, String uuid,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		uuid = Objects.toString(uuid, "");

		CacheJson cacheJson = findByPrimaryKey(sigId);

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

	private FinderPath _finderPathWithPaginationFindBysigId;
	private FinderPath _finderPathWithoutPaginationFindBysigId;
	private FinderPath _finderPathCountBysigId;

	/**
	 * Returns all the cache jsons where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @return the matching cache jsons
	 */
	@Override
	public List<CacheJson> findBysigId(String sigId) {
		return findBysigId(sigId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cache jsons where sigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sigId the sig ID
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findBysigId(String sigId, int start, int end) {
		return findBysigId(sigId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cache jsons where sigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sigId the sig ID
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findBysigId(
		String sigId, int start, int end,
		OrderByComparator<CacheJson> orderByComparator) {

		return findBysigId(sigId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cache jsons where sigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sigId the sig ID
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findBysigId(
		String sigId, int start, int end,
		OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		sigId = Objects.toString(sigId, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindBysigId;
			finderArgs = new Object[] {sigId};
		}
		else {
			finderPath = _finderPathWithPaginationFindBysigId;
			finderArgs = new Object[] {sigId, start, end, orderByComparator};
		}

		List<CacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CacheJson cacheJson : list) {
					if (!sigId.equals(cacheJson.getSigId())) {
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

			boolean bindSigId = false;

			if (sigId.isEmpty()) {
				query.append(_FINDER_COLUMN_SIGID_SIGID_3);
			}
			else {
				bindSigId = true;

				query.append(_FINDER_COLUMN_SIGID_SIGID_2);
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

				if (bindSigId) {
					qPos.add(sigId);
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
	 * Returns the first cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findBysigId_First(
			String sigId, OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchBysigId_First(sigId, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sigId=");
		msg.append(sigId);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchBysigId_First(
		String sigId, OrderByComparator<CacheJson> orderByComparator) {

		List<CacheJson> list = findBysigId(sigId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findBysigId_Last(
			String sigId, OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchBysigId_Last(sigId, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sigId=");
		msg.append(sigId);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchBysigId_Last(
		String sigId, OrderByComparator<CacheJson> orderByComparator) {

		int count = countBysigId(sigId);

		if (count == 0) {
			return null;
		}

		List<CacheJson> list = findBysigId(
			sigId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the cache jsons where sigId = &#63; from the database.
	 *
	 * @param sigId the sig ID
	 */
	@Override
	public void removeBysigId(String sigId) {
		for (CacheJson cacheJson :
				findBysigId(
					sigId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cacheJson);
		}
	}

	/**
	 * Returns the number of cache jsons where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @return the number of matching cache jsons
	 */
	@Override
	public int countBysigId(String sigId) {
		sigId = Objects.toString(sigId, "");

		FinderPath finderPath = _finderPathCountBysigId;

		Object[] finderArgs = new Object[] {sigId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CACHEJSON_WHERE);

			boolean bindSigId = false;

			if (sigId.isEmpty()) {
				query.append(_FINDER_COLUMN_SIGID_SIGID_3);
			}
			else {
				bindSigId = true;

				query.append(_FINDER_COLUMN_SIGID_SIGID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSigId) {
					qPos.add(sigId);
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

	private static final String _FINDER_COLUMN_SIGID_SIGID_2 =
		"cacheJson.sigId = ?";

	private static final String _FINDER_COLUMN_SIGID_SIGID_3 =
		"(cacheJson.sigId IS NULL OR cacheJson.sigId = '')";

	private FinderPath _finderPathWithPaginationFindByCreatedDateAndIsActive;
	private FinderPath _finderPathWithPaginationCountByCreatedDateAndIsActive;

	/**
	 * Returns all the cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @return the matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive) {

		return findByCreatedDateAndIsActive(
			createPlace, isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive, int start, int end) {

		return findByCreatedDateAndIsActive(
			createPlace, isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive, int start, int end,
		OrderByComparator<CacheJson> orderByComparator) {

		return findByCreatedDateAndIsActive(
			createPlace, isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive, int start, int end,
		OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByCreatedDateAndIsActive;
		finderArgs = new Object[] {
			_getTime(createPlace), isActive, start, end, orderByComparator
		};

		List<CacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CacheJson cacheJson : list) {
					if ((createPlace.getTime() >
							cacheJson.getCreatePlace().getTime()) ||
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

			boolean bindCreatePlace = false;

			if (createPlace == null) {
				query.append(
					_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEPLACE_1);
			}
			else {
				bindCreatePlace = true;

				query.append(
					_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEPLACE_2);
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

				if (bindCreatePlace) {
					qPos.add(new Timestamp(createPlace.getTime()));
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
	 * Returns the first cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByCreatedDateAndIsActive_First(
			Date createPlace, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByCreatedDateAndIsActive_First(
			createPlace, isActive, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createPlace=");
		msg.append(createPlace);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByCreatedDateAndIsActive_First(
		Date createPlace, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		List<CacheJson> list = findByCreatedDateAndIsActive(
			createPlace, isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByCreatedDateAndIsActive_Last(
			Date createPlace, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByCreatedDateAndIsActive_Last(
			createPlace, isActive, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createPlace=");
		msg.append(createPlace);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByCreatedDateAndIsActive_Last(
		Date createPlace, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		int count = countByCreatedDateAndIsActive(createPlace, isActive);

		if (count == 0) {
			return null;
		}

		List<CacheJson> list = findByCreatedDateAndIsActive(
			createPlace, isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cache jsons before and after the current cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param sigId the primary key of the current cache json
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson[] findByCreatedDateAndIsActive_PrevAndNext(
			String sigId, Date createPlace, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = findByPrimaryKey(sigId);

		Session session = null;

		try {
			session = openSession();

			CacheJson[] array = new CacheJsonImpl[3];

			array[0] = getByCreatedDateAndIsActive_PrevAndNext(
				session, cacheJson, createPlace, isActive, orderByComparator,
				true);

			array[1] = cacheJson;

			array[2] = getByCreatedDateAndIsActive_PrevAndNext(
				session, cacheJson, createPlace, isActive, orderByComparator,
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
		Session session, CacheJson cacheJson, Date createPlace,
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

		boolean bindCreatePlace = false;

		if (createPlace == null) {
			query.append(_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEPLACE_1);
		}
		else {
			bindCreatePlace = true;

			query.append(_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEPLACE_2);
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

		if (bindCreatePlace) {
			qPos.add(new Timestamp(createPlace.getTime()));
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
	 * Removes all the cache jsons where createPlace &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 */
	@Override
	public void removeByCreatedDateAndIsActive(
		Date createPlace, boolean isActive) {

		for (CacheJson cacheJson :
				findByCreatedDateAndIsActive(
					createPlace, isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cacheJson);
		}
	}

	/**
	 * Returns the number of cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @return the number of matching cache jsons
	 */
	@Override
	public int countByCreatedDateAndIsActive(
		Date createPlace, boolean isActive) {

		FinderPath finderPath =
			_finderPathWithPaginationCountByCreatedDateAndIsActive;

		Object[] finderArgs = new Object[] {_getTime(createPlace), isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CACHEJSON_WHERE);

			boolean bindCreatePlace = false;

			if (createPlace == null) {
				query.append(
					_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEPLACE_1);
			}
			else {
				bindCreatePlace = true;

				query.append(
					_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEPLACE_2);
			}

			query.append(_FINDER_COLUMN_CREATEDDATEANDISACTIVE_ISACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreatePlace) {
					qPos.add(new Timestamp(createPlace.getTime()));
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
		_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEPLACE_1 =
			"cacheJson.createPlace IS NULL AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEPLACE_2 =
			"cacheJson.createPlace >= ? AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDISACTIVE_ISACTIVE_2 =
			"cacheJson.isActive = ?";

	private FinderPath
		_finderPathWithPaginationFindByCreatedDateAndModifiedDateAndIsActive;
	private FinderPath
		_finderPathWithPaginationCountByCreatedDateAndModifiedDateAndIsActive;

	/**
	 * Returns all the cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive) {

		return findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive, int start,
		int end) {

		return findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive, int start,
		int end, OrderByComparator<CacheJson> orderByComparator) {

		return findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive, int start,
		int end, OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath =
			_finderPathWithPaginationFindByCreatedDateAndModifiedDateAndIsActive;
		finderArgs = new Object[] {
			_getTime(createPlace), _getTime(modifiedPlace), isActive, start,
			end, orderByComparator
		};

		List<CacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CacheJson cacheJson : list) {
					if ((createPlace.getTime() <=
							cacheJson.getCreatePlace().getTime()) ||
						(modifiedPlace.getTime() >
							cacheJson.getModifiedPlace().getTime()) ||
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

			boolean bindCreatePlace = false;

			if (createPlace == null) {
				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEPLACE_1);
			}
			else {
				bindCreatePlace = true;

				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEPLACE_2);
			}

			boolean bindModifiedPlace = false;

			if (modifiedPlace == null) {
				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_1);
			}
			else {
				bindModifiedPlace = true;

				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_2);
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

				if (bindCreatePlace) {
					qPos.add(new Timestamp(createPlace.getTime()));
				}

				if (bindModifiedPlace) {
					qPos.add(new Timestamp(modifiedPlace.getTime()));
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
	 * Returns the first cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByCreatedDateAndModifiedDateAndIsActive_First(
			Date createPlace, Date modifiedPlace, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson =
			fetchByCreatedDateAndModifiedDateAndIsActive_First(
				createPlace, modifiedPlace, isActive, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createPlace=");
		msg.append(createPlace);

		msg.append(", modifiedPlace=");
		msg.append(modifiedPlace);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByCreatedDateAndModifiedDateAndIsActive_First(
		Date createPlace, Date modifiedPlace, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		List<CacheJson> list = findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByCreatedDateAndModifiedDateAndIsActive_Last(
			Date createPlace, Date modifiedPlace, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByCreatedDateAndModifiedDateAndIsActive_Last(
			createPlace, modifiedPlace, isActive, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createPlace=");
		msg.append(createPlace);

		msg.append(", modifiedPlace=");
		msg.append(modifiedPlace);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByCreatedDateAndModifiedDateAndIsActive_Last(
		Date createPlace, Date modifiedPlace, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		int count = countByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive);

		if (count == 0) {
			return null;
		}

		List<CacheJson> list = findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cache jsons before and after the current cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param sigId the primary key of the current cache json
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson[] findByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
			String sigId, Date createPlace, Date modifiedPlace,
			boolean isActive, OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = findByPrimaryKey(sigId);

		Session session = null;

		try {
			session = openSession();

			CacheJson[] array = new CacheJsonImpl[3];

			array[0] = getByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				session, cacheJson, createPlace, modifiedPlace, isActive,
				orderByComparator, true);

			array[1] = cacheJson;

			array[2] = getByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				session, cacheJson, createPlace, modifiedPlace, isActive,
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
		Session session, CacheJson cacheJson, Date createPlace,
		Date modifiedPlace, boolean isActive,
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

		boolean bindCreatePlace = false;

		if (createPlace == null) {
			query.append(
				_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEPLACE_1);
		}
		else {
			bindCreatePlace = true;

			query.append(
				_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEPLACE_2);
		}

		boolean bindModifiedPlace = false;

		if (modifiedPlace == null) {
			query.append(
				_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_1);
		}
		else {
			bindModifiedPlace = true;

			query.append(
				_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_2);
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

		if (bindCreatePlace) {
			qPos.add(new Timestamp(createPlace.getTime()));
		}

		if (bindModifiedPlace) {
			qPos.add(new Timestamp(modifiedPlace.getTime()));
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
	 * Removes all the cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 */
	@Override
	public void removeByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive) {

		for (CacheJson cacheJson :
				findByCreatedDateAndModifiedDateAndIsActive(
					createPlace, modifiedPlace, isActive, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(cacheJson);
		}
	}

	/**
	 * Returns the number of cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the number of matching cache jsons
	 */
	@Override
	public int countByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive) {

		FinderPath finderPath =
			_finderPathWithPaginationCountByCreatedDateAndModifiedDateAndIsActive;

		Object[] finderArgs = new Object[] {
			_getTime(createPlace), _getTime(modifiedPlace), isActive
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CACHEJSON_WHERE);

			boolean bindCreatePlace = false;

			if (createPlace == null) {
				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEPLACE_1);
			}
			else {
				bindCreatePlace = true;

				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEPLACE_2);
			}

			boolean bindModifiedPlace = false;

			if (modifiedPlace == null) {
				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_1);
			}
			else {
				bindModifiedPlace = true;

				query.append(
					_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_2);
			}

			query.append(
				_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_ISACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreatePlace) {
					qPos.add(new Timestamp(createPlace.getTime()));
				}

				if (bindModifiedPlace) {
					qPos.add(new Timestamp(modifiedPlace.getTime()));
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
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEPLACE_1 =
			"cacheJson.createPlace IS NULL AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEPLACE_2 =
			"cacheJson.createPlace < ? AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_1 =
			"cacheJson.modifiedPlace IS NULL AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_2 =
			"cacheJson.modifiedPlace >= ? AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_ISACTIVE_2 =
			"cacheJson.isActive = ?";

	private FinderPath _finderPathWithPaginationFindByModifiedDateAndIsActive;
	private FinderPath _finderPathWithPaginationCountByModifiedDateAndIsActive;

	/**
	 * Returns all the cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the matching cache jsons
	 */
	@Override
	public List<CacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive) {

		return findByModifiedDateAndIsActive(
			modifiedPlace, isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive, int start, int end) {

		return findByModifiedDateAndIsActive(
			modifiedPlace, isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive, int start, int end,
		OrderByComparator<CacheJson> orderByComparator) {

		return findByModifiedDateAndIsActive(
			modifiedPlace, isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	@Override
	public List<CacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive, int start, int end,
		OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByModifiedDateAndIsActive;
		finderArgs = new Object[] {
			_getTime(modifiedPlace), isActive, start, end, orderByComparator
		};

		List<CacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CacheJson cacheJson : list) {
					if ((modifiedPlace.getTime() >
							cacheJson.getModifiedPlace().getTime()) ||
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

			boolean bindModifiedPlace = false;

			if (modifiedPlace == null) {
				query.append(
					_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_1);
			}
			else {
				bindModifiedPlace = true;

				query.append(
					_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_2);
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

				if (bindModifiedPlace) {
					qPos.add(new Timestamp(modifiedPlace.getTime()));
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
	 * Returns the first cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByModifiedDateAndIsActive_First(
			Date modifiedPlace, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByModifiedDateAndIsActive_First(
			modifiedPlace, isActive, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedPlace=");
		msg.append(modifiedPlace);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByModifiedDateAndIsActive_First(
		Date modifiedPlace, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		List<CacheJson> list = findByModifiedDateAndIsActive(
			modifiedPlace, isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	@Override
	public CacheJson findByModifiedDateAndIsActive_Last(
			Date modifiedPlace, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = fetchByModifiedDateAndIsActive_Last(
			modifiedPlace, isActive, orderByComparator);

		if (cacheJson != null) {
			return cacheJson;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedPlace=");
		msg.append(modifiedPlace);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	@Override
	public CacheJson fetchByModifiedDateAndIsActive_Last(
		Date modifiedPlace, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		int count = countByModifiedDateAndIsActive(modifiedPlace, isActive);

		if (count == 0) {
			return null;
		}

		List<CacheJson> list = findByModifiedDateAndIsActive(
			modifiedPlace, isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cache jsons before and after the current cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param sigId the primary key of the current cache json
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson[] findByModifiedDateAndIsActive_PrevAndNext(
			String sigId, Date modifiedPlace, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws NoSuchCacheJsonException {

		CacheJson cacheJson = findByPrimaryKey(sigId);

		Session session = null;

		try {
			session = openSession();

			CacheJson[] array = new CacheJsonImpl[3];

			array[0] = getByModifiedDateAndIsActive_PrevAndNext(
				session, cacheJson, modifiedPlace, isActive, orderByComparator,
				true);

			array[1] = cacheJson;

			array[2] = getByModifiedDateAndIsActive_PrevAndNext(
				session, cacheJson, modifiedPlace, isActive, orderByComparator,
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
		Session session, CacheJson cacheJson, Date modifiedPlace,
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

		boolean bindModifiedPlace = false;

		if (modifiedPlace == null) {
			query.append(
				_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_1);
		}
		else {
			bindModifiedPlace = true;

			query.append(
				_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_2);
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

		if (bindModifiedPlace) {
			qPos.add(new Timestamp(modifiedPlace.getTime()));
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
	 * Removes all the cache jsons where modifiedPlace &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 */
	@Override
	public void removeByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive) {

		for (CacheJson cacheJson :
				findByModifiedDateAndIsActive(
					modifiedPlace, isActive, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(cacheJson);
		}
	}

	/**
	 * Returns the number of cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the number of matching cache jsons
	 */
	@Override
	public int countByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive) {

		FinderPath finderPath =
			_finderPathWithPaginationCountByModifiedDateAndIsActive;

		Object[] finderArgs = new Object[] {_getTime(modifiedPlace), isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CACHEJSON_WHERE);

			boolean bindModifiedPlace = false;

			if (modifiedPlace == null) {
				query.append(
					_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_1);
			}
			else {
				bindModifiedPlace = true;

				query.append(
					_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_2);
			}

			query.append(_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_ISACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedPlace) {
					qPos.add(new Timestamp(modifiedPlace.getTime()));
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
		_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_1 =
			"cacheJson.modifiedPlace IS NULL AND ";

	private static final String
		_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_2 =
			"cacheJson.modifiedPlace >= ? AND ";

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
	 * @param sigId the primary key for the new cache json
	 * @return the new cache json
	 */
	@Override
	public CacheJson create(String sigId) {
		CacheJson cacheJson = new CacheJsonImpl();

		cacheJson.setNew(true);
		cacheJson.setPrimaryKey(sigId);

		String uuid = PortalUUIDUtil.generate();

		cacheJson.setUuid(uuid);

		return cacheJson;
	}

	/**
	 * Removes the cache json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sigId the primary key of the cache json
	 * @return the cache json that was removed
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson remove(String sigId) throws NoSuchCacheJsonException {
		return remove((Serializable)sigId);
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

			args = new Object[] {cacheJsonModelImpl.getSigId()};

			finderCache.removeResult(_finderPathCountBysigId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindBysigId, args);

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
				 _finderPathWithoutPaginationFindBysigId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cacheJsonModelImpl.getOriginalSigId()
				};

				finderCache.removeResult(_finderPathCountBysigId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBysigId, args);

				args = new Object[] {cacheJsonModelImpl.getSigId()};

				finderCache.removeResult(_finderPathCountBysigId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBysigId, args);
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
	 * @param sigId the primary key of the cache json
	 * @return the cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson findByPrimaryKey(String sigId)
		throws NoSuchCacheJsonException {

		return findByPrimaryKey((Serializable)sigId);
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
	 * @param sigId the primary key of the cache json
	 * @return the cache json, or <code>null</code> if a cache json with the primary key could not be found
	 */
	@Override
	public CacheJson fetchByPrimaryKey(String sigId) {
		return fetchByPrimaryKey((Serializable)sigId);
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

		for (int i = 0; i < uncachedPrimaryKeys.size(); i++) {
			query.append("?");

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				qPos.add((String)primaryKey);
			}

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

		_finderPathWithPaginationFindBysigId = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, CacheJsonImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBysigId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindBysigId = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, CacheJsonImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBysigId",
			new String[] {String.class.getName()},
			CacheJsonModelImpl.SIGID_COLUMN_BITMASK);

		_finderPathCountBysigId = new FinderPath(
			CacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CacheJsonModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBysigId",
			new String[] {String.class.getName()});

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
		"SELECT cacheJson FROM CacheJson cacheJson WHERE sigId IN (";

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