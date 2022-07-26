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

import eu.strasbourg.service.gtfs.exception.NoSuchCacheAlertJSONException;
import eu.strasbourg.service.gtfs.model.CacheAlertJSON;
import eu.strasbourg.service.gtfs.model.impl.CacheAlertJSONImpl;
import eu.strasbourg.service.gtfs.model.impl.CacheAlertJSONModelImpl;
import eu.strasbourg.service.gtfs.service.persistence.CacheAlertJSONPersistence;

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
 * The persistence implementation for the cache alert json service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @generated
 */
public class CacheAlertJSONPersistenceImpl
	extends BasePersistenceImpl<CacheAlertJSON>
	implements CacheAlertJSONPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CacheAlertJSONUtil</code> to access the cache alert json persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CacheAlertJSONImpl.class.getName();

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
	 * Returns all the cache alert jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cache alert jsons
	 */
	@Override
	public List<CacheAlertJSON> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cache alert jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @return the range of matching cache alert jsons
	 */
	@Override
	public List<CacheAlertJSON> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cache alert jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache alert jsons
	 */
	@Override
	public List<CacheAlertJSON> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CacheAlertJSON> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cache alert jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cache alert jsons
	 */
	@Override
	public List<CacheAlertJSON> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CacheAlertJSON> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<CacheAlertJSON> list = null;

		if (useFinderCache) {
			list = (List<CacheAlertJSON>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CacheAlertJSON cacheAlertJSON : list) {
					if (!uuid.equals(cacheAlertJSON.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_CACHEALERTJSON_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CacheAlertJSONModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<CacheAlertJSON>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache alert json
	 * @throws NoSuchCacheAlertJSONException if a matching cache alert json could not be found
	 */
	@Override
	public CacheAlertJSON findByUuid_First(
			String uuid, OrderByComparator<CacheAlertJSON> orderByComparator)
		throws NoSuchCacheAlertJSONException {

		CacheAlertJSON cacheAlertJSON = fetchByUuid_First(
			uuid, orderByComparator);

		if (cacheAlertJSON != null) {
			return cacheAlertJSON;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchCacheAlertJSONException(sb.toString());
	}

	/**
	 * Returns the first cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache alert json, or <code>null</code> if a matching cache alert json could not be found
	 */
	@Override
	public CacheAlertJSON fetchByUuid_First(
		String uuid, OrderByComparator<CacheAlertJSON> orderByComparator) {

		List<CacheAlertJSON> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache alert json
	 * @throws NoSuchCacheAlertJSONException if a matching cache alert json could not be found
	 */
	@Override
	public CacheAlertJSON findByUuid_Last(
			String uuid, OrderByComparator<CacheAlertJSON> orderByComparator)
		throws NoSuchCacheAlertJSONException {

		CacheAlertJSON cacheAlertJSON = fetchByUuid_Last(
			uuid, orderByComparator);

		if (cacheAlertJSON != null) {
			return cacheAlertJSON;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchCacheAlertJSONException(sb.toString());
	}

	/**
	 * Returns the last cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache alert json, or <code>null</code> if a matching cache alert json could not be found
	 */
	@Override
	public CacheAlertJSON fetchByUuid_Last(
		String uuid, OrderByComparator<CacheAlertJSON> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CacheAlertJSON> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cache alert jsons before and after the current cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param cacheId the primary key of the current cache alert json
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache alert json
	 * @throws NoSuchCacheAlertJSONException if a cache alert json with the primary key could not be found
	 */
	@Override
	public CacheAlertJSON[] findByUuid_PrevAndNext(
			long cacheId, String uuid,
			OrderByComparator<CacheAlertJSON> orderByComparator)
		throws NoSuchCacheAlertJSONException {

		uuid = Objects.toString(uuid, "");

		CacheAlertJSON cacheAlertJSON = findByPrimaryKey(cacheId);

		Session session = null;

		try {
			session = openSession();

			CacheAlertJSON[] array = new CacheAlertJSONImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, cacheAlertJSON, uuid, orderByComparator, true);

			array[1] = cacheAlertJSON;

			array[2] = getByUuid_PrevAndNext(
				session, cacheAlertJSON, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CacheAlertJSON getByUuid_PrevAndNext(
		Session session, CacheAlertJSON cacheAlertJSON, String uuid,
		OrderByComparator<CacheAlertJSON> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_CACHEALERTJSON_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CacheAlertJSONModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cacheAlertJSON)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CacheAlertJSON> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cache alert jsons where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CacheAlertJSON cacheAlertJSON :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cacheAlertJSON);
		}
	}

	/**
	 * Returns the number of cache alert jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cache alert jsons
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CACHEALERTJSON_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"cacheAlertJSON.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(cacheAlertJSON.uuid IS NULL OR cacheAlertJSON.uuid = '')";

	private FinderPath _finderPathWithPaginationFindBycacheId;
	private FinderPath _finderPathWithoutPaginationFindBycacheId;
	private FinderPath _finderPathCountBycacheId;

	/**
	 * Returns all the cache alert jsons where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @return the matching cache alert jsons
	 */
	@Override
	public List<CacheAlertJSON> findBycacheId(long cacheId) {
		return findBycacheId(
			cacheId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cache alert jsons where cacheId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param cacheId the cache ID
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @return the range of matching cache alert jsons
	 */
	@Override
	public List<CacheAlertJSON> findBycacheId(
		long cacheId, int start, int end) {

		return findBycacheId(cacheId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cache alert jsons where cacheId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param cacheId the cache ID
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache alert jsons
	 */
	@Override
	public List<CacheAlertJSON> findBycacheId(
		long cacheId, int start, int end,
		OrderByComparator<CacheAlertJSON> orderByComparator) {

		return findBycacheId(cacheId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cache alert jsons where cacheId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param cacheId the cache ID
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cache alert jsons
	 */
	@Override
	public List<CacheAlertJSON> findBycacheId(
		long cacheId, int start, int end,
		OrderByComparator<CacheAlertJSON> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBycacheId;
				finderArgs = new Object[] {cacheId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBycacheId;
			finderArgs = new Object[] {cacheId, start, end, orderByComparator};
		}

		List<CacheAlertJSON> list = null;

		if (useFinderCache) {
			list = (List<CacheAlertJSON>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CacheAlertJSON cacheAlertJSON : list) {
					if (cacheId != cacheAlertJSON.getCacheId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_CACHEALERTJSON_WHERE);

			sb.append(_FINDER_COLUMN_CACHEID_CACHEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CacheAlertJSONModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(cacheId);

				list = (List<CacheAlertJSON>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cache alert json in the ordered set where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache alert json
	 * @throws NoSuchCacheAlertJSONException if a matching cache alert json could not be found
	 */
	@Override
	public CacheAlertJSON findBycacheId_First(
			long cacheId, OrderByComparator<CacheAlertJSON> orderByComparator)
		throws NoSuchCacheAlertJSONException {

		CacheAlertJSON cacheAlertJSON = fetchBycacheId_First(
			cacheId, orderByComparator);

		if (cacheAlertJSON != null) {
			return cacheAlertJSON;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("cacheId=");
		sb.append(cacheId);

		sb.append("}");

		throw new NoSuchCacheAlertJSONException(sb.toString());
	}

	/**
	 * Returns the first cache alert json in the ordered set where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache alert json, or <code>null</code> if a matching cache alert json could not be found
	 */
	@Override
	public CacheAlertJSON fetchBycacheId_First(
		long cacheId, OrderByComparator<CacheAlertJSON> orderByComparator) {

		List<CacheAlertJSON> list = findBycacheId(
			cacheId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cache alert json in the ordered set where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache alert json
	 * @throws NoSuchCacheAlertJSONException if a matching cache alert json could not be found
	 */
	@Override
	public CacheAlertJSON findBycacheId_Last(
			long cacheId, OrderByComparator<CacheAlertJSON> orderByComparator)
		throws NoSuchCacheAlertJSONException {

		CacheAlertJSON cacheAlertJSON = fetchBycacheId_Last(
			cacheId, orderByComparator);

		if (cacheAlertJSON != null) {
			return cacheAlertJSON;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("cacheId=");
		sb.append(cacheId);

		sb.append("}");

		throw new NoSuchCacheAlertJSONException(sb.toString());
	}

	/**
	 * Returns the last cache alert json in the ordered set where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache alert json, or <code>null</code> if a matching cache alert json could not be found
	 */
	@Override
	public CacheAlertJSON fetchBycacheId_Last(
		long cacheId, OrderByComparator<CacheAlertJSON> orderByComparator) {

		int count = countBycacheId(cacheId);

		if (count == 0) {
			return null;
		}

		List<CacheAlertJSON> list = findBycacheId(
			cacheId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the cache alert jsons where cacheId = &#63; from the database.
	 *
	 * @param cacheId the cache ID
	 */
	@Override
	public void removeBycacheId(long cacheId) {
		for (CacheAlertJSON cacheAlertJSON :
				findBycacheId(
					cacheId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cacheAlertJSON);
		}
	}

	/**
	 * Returns the number of cache alert jsons where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @return the number of matching cache alert jsons
	 */
	@Override
	public int countBycacheId(long cacheId) {
		FinderPath finderPath = _finderPathCountBycacheId;

		Object[] finderArgs = new Object[] {cacheId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CACHEALERTJSON_WHERE);

			sb.append(_FINDER_COLUMN_CACHEID_CACHEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(cacheId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CACHEID_CACHEID_2 =
		"cacheAlertJSON.cacheId = ?";

	public CacheAlertJSONPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}

		setModelClass(CacheAlertJSON.class);
	}

	/**
	 * Caches the cache alert json in the entity cache if it is enabled.
	 *
	 * @param cacheAlertJSON the cache alert json
	 */
	@Override
	public void cacheResult(CacheAlertJSON cacheAlertJSON) {
		entityCache.putResult(
			CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
			CacheAlertJSONImpl.class, cacheAlertJSON.getPrimaryKey(),
			cacheAlertJSON);

		cacheAlertJSON.resetOriginalValues();
	}

	/**
	 * Caches the cache alert jsons in the entity cache if it is enabled.
	 *
	 * @param cacheAlertJSONs the cache alert jsons
	 */
	@Override
	public void cacheResult(List<CacheAlertJSON> cacheAlertJSONs) {
		for (CacheAlertJSON cacheAlertJSON : cacheAlertJSONs) {
			if (entityCache.getResult(
					CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
					CacheAlertJSONImpl.class, cacheAlertJSON.getPrimaryKey()) ==
						null) {

				cacheResult(cacheAlertJSON);
			}
			else {
				cacheAlertJSON.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cache alert jsons.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CacheAlertJSONImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cache alert json.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CacheAlertJSON cacheAlertJSON) {
		entityCache.removeResult(
			CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
			CacheAlertJSONImpl.class, cacheAlertJSON.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CacheAlertJSON> cacheAlertJSONs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CacheAlertJSON cacheAlertJSON : cacheAlertJSONs) {
			entityCache.removeResult(
				CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
				CacheAlertJSONImpl.class, cacheAlertJSON.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
				CacheAlertJSONImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new cache alert json with the primary key. Does not add the cache alert json to the database.
	 *
	 * @param cacheId the primary key for the new cache alert json
	 * @return the new cache alert json
	 */
	@Override
	public CacheAlertJSON create(long cacheId) {
		CacheAlertJSON cacheAlertJSON = new CacheAlertJSONImpl();

		cacheAlertJSON.setNew(true);
		cacheAlertJSON.setPrimaryKey(cacheId);

		String uuid = PortalUUIDUtil.generate();

		cacheAlertJSON.setUuid(uuid);

		return cacheAlertJSON;
	}

	/**
	 * Removes the cache alert json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheId the primary key of the cache alert json
	 * @return the cache alert json that was removed
	 * @throws NoSuchCacheAlertJSONException if a cache alert json with the primary key could not be found
	 */
	@Override
	public CacheAlertJSON remove(long cacheId)
		throws NoSuchCacheAlertJSONException {

		return remove((Serializable)cacheId);
	}

	/**
	 * Removes the cache alert json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cache alert json
	 * @return the cache alert json that was removed
	 * @throws NoSuchCacheAlertJSONException if a cache alert json with the primary key could not be found
	 */
	@Override
	public CacheAlertJSON remove(Serializable primaryKey)
		throws NoSuchCacheAlertJSONException {

		Session session = null;

		try {
			session = openSession();

			CacheAlertJSON cacheAlertJSON = (CacheAlertJSON)session.get(
				CacheAlertJSONImpl.class, primaryKey);

			if (cacheAlertJSON == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCacheAlertJSONException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cacheAlertJSON);
		}
		catch (NoSuchCacheAlertJSONException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CacheAlertJSON removeImpl(CacheAlertJSON cacheAlertJSON) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cacheAlertJSON)) {
				cacheAlertJSON = (CacheAlertJSON)session.get(
					CacheAlertJSONImpl.class,
					cacheAlertJSON.getPrimaryKeyObj());
			}

			if (cacheAlertJSON != null) {
				session.delete(cacheAlertJSON);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (cacheAlertJSON != null) {
			clearCache(cacheAlertJSON);
		}

		return cacheAlertJSON;
	}

	@Override
	public CacheAlertJSON updateImpl(CacheAlertJSON cacheAlertJSON) {
		boolean isNew = cacheAlertJSON.isNew();

		if (!(cacheAlertJSON instanceof CacheAlertJSONModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cacheAlertJSON.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					cacheAlertJSON);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cacheAlertJSON proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CacheAlertJSON implementation " +
					cacheAlertJSON.getClass());
		}

		CacheAlertJSONModelImpl cacheAlertJSONModelImpl =
			(CacheAlertJSONModelImpl)cacheAlertJSON;

		if (Validator.isNull(cacheAlertJSON.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cacheAlertJSON.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (cacheAlertJSON.isNew()) {
				session.save(cacheAlertJSON);

				cacheAlertJSON.setNew(false);
			}
			else {
				cacheAlertJSON = (CacheAlertJSON)session.merge(cacheAlertJSON);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CacheAlertJSONModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {cacheAlertJSONModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {cacheAlertJSONModelImpl.getCacheId()};

			finderCache.removeResult(_finderPathCountBycacheId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindBycacheId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((cacheAlertJSONModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cacheAlertJSONModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {cacheAlertJSONModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((cacheAlertJSONModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindBycacheId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cacheAlertJSONModelImpl.getOriginalCacheId()
				};

				finderCache.removeResult(_finderPathCountBycacheId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBycacheId, args);

				args = new Object[] {cacheAlertJSONModelImpl.getCacheId()};

				finderCache.removeResult(_finderPathCountBycacheId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBycacheId, args);
			}
		}

		entityCache.putResult(
			CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
			CacheAlertJSONImpl.class, cacheAlertJSON.getPrimaryKey(),
			cacheAlertJSON, false);

		cacheAlertJSON.resetOriginalValues();

		return cacheAlertJSON;
	}

	/**
	 * Returns the cache alert json with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cache alert json
	 * @return the cache alert json
	 * @throws NoSuchCacheAlertJSONException if a cache alert json with the primary key could not be found
	 */
	@Override
	public CacheAlertJSON findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCacheAlertJSONException {

		CacheAlertJSON cacheAlertJSON = fetchByPrimaryKey(primaryKey);

		if (cacheAlertJSON == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCacheAlertJSONException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cacheAlertJSON;
	}

	/**
	 * Returns the cache alert json with the primary key or throws a <code>NoSuchCacheAlertJSONException</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the cache alert json
	 * @return the cache alert json
	 * @throws NoSuchCacheAlertJSONException if a cache alert json with the primary key could not be found
	 */
	@Override
	public CacheAlertJSON findByPrimaryKey(long cacheId)
		throws NoSuchCacheAlertJSONException {

		return findByPrimaryKey((Serializable)cacheId);
	}

	/**
	 * Returns the cache alert json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cache alert json
	 * @return the cache alert json, or <code>null</code> if a cache alert json with the primary key could not be found
	 */
	@Override
	public CacheAlertJSON fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
			CacheAlertJSONImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CacheAlertJSON cacheAlertJSON = (CacheAlertJSON)serializable;

		if (cacheAlertJSON == null) {
			Session session = null;

			try {
				session = openSession();

				cacheAlertJSON = (CacheAlertJSON)session.get(
					CacheAlertJSONImpl.class, primaryKey);

				if (cacheAlertJSON != null) {
					cacheResult(cacheAlertJSON);
				}
				else {
					entityCache.putResult(
						CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
						CacheAlertJSONImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
					CacheAlertJSONImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return cacheAlertJSON;
	}

	/**
	 * Returns the cache alert json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the cache alert json
	 * @return the cache alert json, or <code>null</code> if a cache alert json with the primary key could not be found
	 */
	@Override
	public CacheAlertJSON fetchByPrimaryKey(long cacheId) {
		return fetchByPrimaryKey((Serializable)cacheId);
	}

	@Override
	public Map<Serializable, CacheAlertJSON> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CacheAlertJSON> map =
			new HashMap<Serializable, CacheAlertJSON>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CacheAlertJSON cacheAlertJSON = fetchByPrimaryKey(primaryKey);

			if (cacheAlertJSON != null) {
				map.put(primaryKey, cacheAlertJSON);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
				CacheAlertJSONImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CacheAlertJSON)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_CACHEALERTJSON_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			sb.append((long)primaryKey);

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			for (CacheAlertJSON cacheAlertJSON :
					(List<CacheAlertJSON>)query.list()) {

				map.put(cacheAlertJSON.getPrimaryKeyObj(), cacheAlertJSON);

				cacheResult(cacheAlertJSON);

				uncachedPrimaryKeys.remove(cacheAlertJSON.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
					CacheAlertJSONImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the cache alert jsons.
	 *
	 * @return the cache alert jsons
	 */
	@Override
	public List<CacheAlertJSON> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cache alert jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @return the range of cache alert jsons
	 */
	@Override
	public List<CacheAlertJSON> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cache alert jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cache alert jsons
	 */
	@Override
	public List<CacheAlertJSON> findAll(
		int start, int end,
		OrderByComparator<CacheAlertJSON> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cache alert jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cache alert jsons
	 */
	@Override
	public List<CacheAlertJSON> findAll(
		int start, int end, OrderByComparator<CacheAlertJSON> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CacheAlertJSON> list = null;

		if (useFinderCache) {
			list = (List<CacheAlertJSON>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CACHEALERTJSON);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CACHEALERTJSON;

				sql = sql.concat(CacheAlertJSONModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CacheAlertJSON>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the cache alert jsons from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CacheAlertJSON cacheAlertJSON : findAll()) {
			remove(cacheAlertJSON);
		}
	}

	/**
	 * Returns the number of cache alert jsons.
	 *
	 * @return the number of cache alert jsons
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_CACHEALERTJSON);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
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
		return CacheAlertJSONModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cache alert json persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
			CacheAlertJSONModelImpl.FINDER_CACHE_ENABLED,
			CacheAlertJSONImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
			CacheAlertJSONModelImpl.FINDER_CACHE_ENABLED,
			CacheAlertJSONImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
			CacheAlertJSONModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
			CacheAlertJSONModelImpl.FINDER_CACHE_ENABLED,
			CacheAlertJSONImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
			CacheAlertJSONModelImpl.FINDER_CACHE_ENABLED,
			CacheAlertJSONImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] {String.class.getName()},
			CacheAlertJSONModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
			CacheAlertJSONModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindBycacheId = new FinderPath(
			CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
			CacheAlertJSONModelImpl.FINDER_CACHE_ENABLED,
			CacheAlertJSONImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBycacheId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindBycacheId = new FinderPath(
			CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
			CacheAlertJSONModelImpl.FINDER_CACHE_ENABLED,
			CacheAlertJSONImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBycacheId", new String[] {Long.class.getName()},
			CacheAlertJSONModelImpl.CACHEID_COLUMN_BITMASK);

		_finderPathCountBycacheId = new FinderPath(
			CacheAlertJSONModelImpl.ENTITY_CACHE_ENABLED,
			CacheAlertJSONModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBycacheId",
			new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CacheAlertJSONImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CACHEALERTJSON =
		"SELECT cacheAlertJSON FROM CacheAlertJSON cacheAlertJSON";

	private static final String _SQL_SELECT_CACHEALERTJSON_WHERE_PKS_IN =
		"SELECT cacheAlertJSON FROM CacheAlertJSON cacheAlertJSON WHERE cacheId IN (";

	private static final String _SQL_SELECT_CACHEALERTJSON_WHERE =
		"SELECT cacheAlertJSON FROM CacheAlertJSON cacheAlertJSON WHERE ";

	private static final String _SQL_COUNT_CACHEALERTJSON =
		"SELECT COUNT(cacheAlertJSON) FROM CacheAlertJSON cacheAlertJSON";

	private static final String _SQL_COUNT_CACHEALERTJSON_WHERE =
		"SELECT COUNT(cacheAlertJSON) FROM CacheAlertJSON cacheAlertJSON WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "cacheAlertJSON.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CacheAlertJSON exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CacheAlertJSON exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CacheAlertJSONPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}