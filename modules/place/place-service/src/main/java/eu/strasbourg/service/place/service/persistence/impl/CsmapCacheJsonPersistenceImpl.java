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

import eu.strasbourg.service.place.exception.NoSuchCsmapCacheJsonException;
import eu.strasbourg.service.place.model.CsmapCacheJson;
import eu.strasbourg.service.place.model.impl.CsmapCacheJsonImpl;
import eu.strasbourg.service.place.model.impl.CsmapCacheJsonModelImpl;
import eu.strasbourg.service.place.service.persistence.CsmapCacheJsonPersistence;

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
 * The persistence implementation for the csmap cache json service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @generated
 */
@ProviderType
public class CsmapCacheJsonPersistenceImpl
	extends BasePersistenceImpl<CsmapCacheJson>
	implements CsmapCacheJsonPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CsmapCacheJsonUtil</code> to access the csmap cache json persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CsmapCacheJsonImpl.class.getName();

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
	 * Returns all the csmap cache jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the csmap cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator,
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

		List<CsmapCacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CsmapCacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CsmapCacheJson csmapCacheJson : list) {
					if (!uuid.equals(csmapCacheJson.getUuid())) {
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

			query.append(_SQL_SELECT_CSMAPCACHEJSON_WHERE);

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
				query.append(CsmapCacheJsonModelImpl.ORDER_BY_JPQL);
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
					list = (List<CsmapCacheJson>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CsmapCacheJson>)QueryUtil.list(
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
	 * Returns the first csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson findByUuid_First(
			String uuid, OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson = fetchByUuid_First(
			uuid, orderByComparator);

		if (csmapCacheJson != null) {
			return csmapCacheJson;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCsmapCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson fetchByUuid_First(
		String uuid, OrderByComparator<CsmapCacheJson> orderByComparator) {

		List<CsmapCacheJson> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson findByUuid_Last(
			String uuid, OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson = fetchByUuid_Last(
			uuid, orderByComparator);

		if (csmapCacheJson != null) {
			return csmapCacheJson;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCsmapCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson fetchByUuid_Last(
		String uuid, OrderByComparator<CsmapCacheJson> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CsmapCacheJson> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param sigId the primary key of the current csmap cache json
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	@Override
	public CsmapCacheJson[] findByUuid_PrevAndNext(
			String sigId, String uuid,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		uuid = Objects.toString(uuid, "");

		CsmapCacheJson csmapCacheJson = findByPrimaryKey(sigId);

		Session session = null;

		try {
			session = openSession();

			CsmapCacheJson[] array = new CsmapCacheJsonImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, csmapCacheJson, uuid, orderByComparator, true);

			array[1] = csmapCacheJson;

			array[2] = getByUuid_PrevAndNext(
				session, csmapCacheJson, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CsmapCacheJson getByUuid_PrevAndNext(
		Session session, CsmapCacheJson csmapCacheJson, String uuid,
		OrderByComparator<CsmapCacheJson> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CSMAPCACHEJSON_WHERE);

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
			query.append(CsmapCacheJsonModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(
						csmapCacheJson)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CsmapCacheJson> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the csmap cache jsons where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CsmapCacheJson csmapCacheJson :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(csmapCacheJson);
		}
	}

	/**
	 * Returns the number of csmap cache jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching csmap cache jsons
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CSMAPCACHEJSON_WHERE);

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
		"csmapCacheJson.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(csmapCacheJson.uuid IS NULL OR csmapCacheJson.uuid = '')";

	private FinderPath _finderPathWithPaginationFindBysigId;
	private FinderPath _finderPathWithoutPaginationFindBysigId;
	private FinderPath _finderPathCountBysigId;

	/**
	 * Returns all the csmap cache jsons where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @return the matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findBysigId(String sigId) {
		return findBysigId(sigId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the csmap cache jsons where sigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sigId the sig ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findBysigId(String sigId, int start, int end) {
		return findBysigId(sigId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where sigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sigId the sig ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findBysigId(
		String sigId, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return findBysigId(sigId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where sigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sigId the sig ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findBysigId(
		String sigId, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator,
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

		List<CsmapCacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CsmapCacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CsmapCacheJson csmapCacheJson : list) {
					if (!sigId.equals(csmapCacheJson.getSigId())) {
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

			query.append(_SQL_SELECT_CSMAPCACHEJSON_WHERE);

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
				query.append(CsmapCacheJsonModelImpl.ORDER_BY_JPQL);
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
					list = (List<CsmapCacheJson>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CsmapCacheJson>)QueryUtil.list(
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
	 * Returns the first csmap cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson findBysigId_First(
			String sigId, OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson = fetchBysigId_First(
			sigId, orderByComparator);

		if (csmapCacheJson != null) {
			return csmapCacheJson;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sigId=");
		msg.append(sigId);

		msg.append("}");

		throw new NoSuchCsmapCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first csmap cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson fetchBysigId_First(
		String sigId, OrderByComparator<CsmapCacheJson> orderByComparator) {

		List<CsmapCacheJson> list = findBysigId(sigId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last csmap cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson findBysigId_Last(
			String sigId, OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson = fetchBysigId_Last(
			sigId, orderByComparator);

		if (csmapCacheJson != null) {
			return csmapCacheJson;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sigId=");
		msg.append(sigId);

		msg.append("}");

		throw new NoSuchCsmapCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last csmap cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson fetchBysigId_Last(
		String sigId, OrderByComparator<CsmapCacheJson> orderByComparator) {

		int count = countBysigId(sigId);

		if (count == 0) {
			return null;
		}

		List<CsmapCacheJson> list = findBysigId(
			sigId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the csmap cache jsons where sigId = &#63; from the database.
	 *
	 * @param sigId the sig ID
	 */
	@Override
	public void removeBysigId(String sigId) {
		for (CsmapCacheJson csmapCacheJson :
				findBysigId(
					sigId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(csmapCacheJson);
		}
	}

	/**
	 * Returns the number of csmap cache jsons where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @return the number of matching csmap cache jsons
	 */
	@Override
	public int countBysigId(String sigId) {
		sigId = Objects.toString(sigId, "");

		FinderPath finderPath = _finderPathCountBysigId;

		Object[] finderArgs = new Object[] {sigId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CSMAPCACHEJSON_WHERE);

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
		"csmapCacheJson.sigId = ?";

	private static final String _FINDER_COLUMN_SIGID_SIGID_3 =
		"(csmapCacheJson.sigId IS NULL OR csmapCacheJson.sigId = '')";

	private FinderPath _finderPathWithPaginationFindByCreatedDateAndIsActive;
	private FinderPath _finderPathWithPaginationCountByCreatedDateAndIsActive;

	/**
	 * Returns all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive) {

		return findByCreatedDateAndIsActive(
			createPlace, isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive, int start, int end) {

		return findByCreatedDateAndIsActive(
			createPlace, isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return findByCreatedDateAndIsActive(
			createPlace, isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByCreatedDateAndIsActive;
		finderArgs = new Object[] {
			_getTime(createPlace), isActive, start, end, orderByComparator
		};

		List<CsmapCacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CsmapCacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CsmapCacheJson csmapCacheJson : list) {
					if ((createPlace.getTime() >
							csmapCacheJson.getCreatePlace().getTime()) ||
						(isActive != csmapCacheJson.isIsActive())) {

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

			query.append(_SQL_SELECT_CSMAPCACHEJSON_WHERE);

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
				query.append(CsmapCacheJsonModelImpl.ORDER_BY_JPQL);
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
					list = (List<CsmapCacheJson>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CsmapCacheJson>)QueryUtil.list(
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
	 * Returns the first csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson findByCreatedDateAndIsActive_First(
			Date createPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson = fetchByCreatedDateAndIsActive_First(
			createPlace, isActive, orderByComparator);

		if (csmapCacheJson != null) {
			return csmapCacheJson;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createPlace=");
		msg.append(createPlace);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCsmapCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson fetchByCreatedDateAndIsActive_First(
		Date createPlace, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		List<CsmapCacheJson> list = findByCreatedDateAndIsActive(
			createPlace, isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson findByCreatedDateAndIsActive_Last(
			Date createPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson = fetchByCreatedDateAndIsActive_Last(
			createPlace, isActive, orderByComparator);

		if (csmapCacheJson != null) {
			return csmapCacheJson;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createPlace=");
		msg.append(createPlace);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCsmapCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson fetchByCreatedDateAndIsActive_Last(
		Date createPlace, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		int count = countByCreatedDateAndIsActive(createPlace, isActive);

		if (count == 0) {
			return null;
		}

		List<CsmapCacheJson> list = findByCreatedDateAndIsActive(
			createPlace, isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param sigId the primary key of the current csmap cache json
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	@Override
	public CsmapCacheJson[] findByCreatedDateAndIsActive_PrevAndNext(
			String sigId, Date createPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson = findByPrimaryKey(sigId);

		Session session = null;

		try {
			session = openSession();

			CsmapCacheJson[] array = new CsmapCacheJsonImpl[3];

			array[0] = getByCreatedDateAndIsActive_PrevAndNext(
				session, csmapCacheJson, createPlace, isActive,
				orderByComparator, true);

			array[1] = csmapCacheJson;

			array[2] = getByCreatedDateAndIsActive_PrevAndNext(
				session, csmapCacheJson, createPlace, isActive,
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

	protected CsmapCacheJson getByCreatedDateAndIsActive_PrevAndNext(
		Session session, CsmapCacheJson csmapCacheJson, Date createPlace,
		boolean isActive, OrderByComparator<CsmapCacheJson> orderByComparator,
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

		query.append(_SQL_SELECT_CSMAPCACHEJSON_WHERE);

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
			query.append(CsmapCacheJsonModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(
						csmapCacheJson)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CsmapCacheJson> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 */
	@Override
	public void removeByCreatedDateAndIsActive(
		Date createPlace, boolean isActive) {

		for (CsmapCacheJson csmapCacheJson :
				findByCreatedDateAndIsActive(
					createPlace, isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(csmapCacheJson);
		}
	}

	/**
	 * Returns the number of csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
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

			query.append(_SQL_COUNT_CSMAPCACHEJSON_WHERE);

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
			"csmapCacheJson.createPlace IS NULL AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDISACTIVE_CREATEPLACE_2 =
			"csmapCacheJson.createPlace >= ? AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDISACTIVE_ISACTIVE_2 =
			"csmapCacheJson.isActive = ?";

	private FinderPath
		_finderPathWithPaginationFindByCreatedDateAndModifiedDateAndIsActive;
	private FinderPath
		_finderPathWithPaginationCountByCreatedDateAndModifiedDateAndIsActive;

	/**
	 * Returns all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive) {

		return findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive, int start,
		int end) {

		return findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive, int start,
		int end, OrderByComparator<CsmapCacheJson> orderByComparator) {

		return findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive, int start,
		int end, OrderByComparator<CsmapCacheJson> orderByComparator,
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

		List<CsmapCacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CsmapCacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CsmapCacheJson csmapCacheJson : list) {
					if ((createPlace.getTime() <=
							csmapCacheJson.getCreatePlace().getTime()) ||
						(modifiedPlace.getTime() >
							csmapCacheJson.getModifiedPlace().getTime()) ||
						(isActive != csmapCacheJson.isIsActive())) {

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

			query.append(_SQL_SELECT_CSMAPCACHEJSON_WHERE);

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
				query.append(CsmapCacheJsonModelImpl.ORDER_BY_JPQL);
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
					list = (List<CsmapCacheJson>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CsmapCacheJson>)QueryUtil.list(
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
	 * Returns the first csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson findByCreatedDateAndModifiedDateAndIsActive_First(
			Date createPlace, Date modifiedPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson =
			fetchByCreatedDateAndModifiedDateAndIsActive_First(
				createPlace, modifiedPlace, isActive, orderByComparator);

		if (csmapCacheJson != null) {
			return csmapCacheJson;
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

		throw new NoSuchCsmapCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson fetchByCreatedDateAndModifiedDateAndIsActive_First(
		Date createPlace, Date modifiedPlace, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		List<CsmapCacheJson> list = findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson findByCreatedDateAndModifiedDateAndIsActive_Last(
			Date createPlace, Date modifiedPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson =
			fetchByCreatedDateAndModifiedDateAndIsActive_Last(
				createPlace, modifiedPlace, isActive, orderByComparator);

		if (csmapCacheJson != null) {
			return csmapCacheJson;
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

		throw new NoSuchCsmapCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson fetchByCreatedDateAndModifiedDateAndIsActive_Last(
		Date createPlace, Date modifiedPlace, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		int count = countByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive);

		if (count == 0) {
			return null;
		}

		List<CsmapCacheJson> list = findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param sigId the primary key of the current csmap cache json
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	@Override
	public CsmapCacheJson[]
			findByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				String sigId, Date createPlace, Date modifiedPlace,
				boolean isActive,
				OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson = findByPrimaryKey(sigId);

		Session session = null;

		try {
			session = openSession();

			CsmapCacheJson[] array = new CsmapCacheJsonImpl[3];

			array[0] = getByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				session, csmapCacheJson, createPlace, modifiedPlace, isActive,
				orderByComparator, true);

			array[1] = csmapCacheJson;

			array[2] = getByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				session, csmapCacheJson, createPlace, modifiedPlace, isActive,
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

	protected CsmapCacheJson
		getByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
			Session session, CsmapCacheJson csmapCacheJson, Date createPlace,
			Date modifiedPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator,
			boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_CSMAPCACHEJSON_WHERE);

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
			query.append(CsmapCacheJsonModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(
						csmapCacheJson)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CsmapCacheJson> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 */
	@Override
	public void removeByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive) {

		for (CsmapCacheJson csmapCacheJson :
				findByCreatedDateAndModifiedDateAndIsActive(
					createPlace, modifiedPlace, isActive, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(csmapCacheJson);
		}
	}

	/**
	 * Returns the number of csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
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

			query.append(_SQL_COUNT_CSMAPCACHEJSON_WHERE);

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
			"csmapCacheJson.createPlace IS NULL AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_CREATEPLACE_2 =
			"csmapCacheJson.createPlace < ? AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_1 =
			"csmapCacheJson.modifiedPlace IS NULL AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_2 =
			"csmapCacheJson.modifiedPlace >= ? AND ";

	private static final String
		_FINDER_COLUMN_CREATEDDATEANDMODIFIEDDATEANDISACTIVE_ISACTIVE_2 =
			"csmapCacheJson.isActive = ?";

	private FinderPath _finderPathWithPaginationFindByModifiedDateAndIsActive;
	private FinderPath _finderPathWithPaginationCountByModifiedDateAndIsActive;

	/**
	 * Returns all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive) {

		return findByModifiedDateAndIsActive(
			modifiedPlace, isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive, int start, int end) {

		return findByModifiedDateAndIsActive(
			modifiedPlace, isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return findByModifiedDateAndIsActive(
			modifiedPlace, isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByModifiedDateAndIsActive;
		finderArgs = new Object[] {
			_getTime(modifiedPlace), isActive, start, end, orderByComparator
		};

		List<CsmapCacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CsmapCacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CsmapCacheJson csmapCacheJson : list) {
					if ((modifiedPlace.getTime() >
							csmapCacheJson.getModifiedPlace().getTime()) ||
						(isActive != csmapCacheJson.isIsActive())) {

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

			query.append(_SQL_SELECT_CSMAPCACHEJSON_WHERE);

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
				query.append(CsmapCacheJsonModelImpl.ORDER_BY_JPQL);
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
					list = (List<CsmapCacheJson>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CsmapCacheJson>)QueryUtil.list(
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
	 * Returns the first csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson findByModifiedDateAndIsActive_First(
			Date modifiedPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson = fetchByModifiedDateAndIsActive_First(
			modifiedPlace, isActive, orderByComparator);

		if (csmapCacheJson != null) {
			return csmapCacheJson;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedPlace=");
		msg.append(modifiedPlace);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCsmapCacheJsonException(msg.toString());
	}

	/**
	 * Returns the first csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson fetchByModifiedDateAndIsActive_First(
		Date modifiedPlace, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		List<CsmapCacheJson> list = findByModifiedDateAndIsActive(
			modifiedPlace, isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson findByModifiedDateAndIsActive_Last(
			Date modifiedPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson = fetchByModifiedDateAndIsActive_Last(
			modifiedPlace, isActive, orderByComparator);

		if (csmapCacheJson != null) {
			return csmapCacheJson;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedPlace=");
		msg.append(modifiedPlace);

		msg.append(", isActive=");
		msg.append(isActive);

		msg.append("}");

		throw new NoSuchCsmapCacheJsonException(msg.toString());
	}

	/**
	 * Returns the last csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	@Override
	public CsmapCacheJson fetchByModifiedDateAndIsActive_Last(
		Date modifiedPlace, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		int count = countByModifiedDateAndIsActive(modifiedPlace, isActive);

		if (count == 0) {
			return null;
		}

		List<CsmapCacheJson> list = findByModifiedDateAndIsActive(
			modifiedPlace, isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param sigId the primary key of the current csmap cache json
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	@Override
	public CsmapCacheJson[] findByModifiedDateAndIsActive_PrevAndNext(
			String sigId, Date modifiedPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson = findByPrimaryKey(sigId);

		Session session = null;

		try {
			session = openSession();

			CsmapCacheJson[] array = new CsmapCacheJsonImpl[3];

			array[0] = getByModifiedDateAndIsActive_PrevAndNext(
				session, csmapCacheJson, modifiedPlace, isActive,
				orderByComparator, true);

			array[1] = csmapCacheJson;

			array[2] = getByModifiedDateAndIsActive_PrevAndNext(
				session, csmapCacheJson, modifiedPlace, isActive,
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

	protected CsmapCacheJson getByModifiedDateAndIsActive_PrevAndNext(
		Session session, CsmapCacheJson csmapCacheJson, Date modifiedPlace,
		boolean isActive, OrderByComparator<CsmapCacheJson> orderByComparator,
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

		query.append(_SQL_SELECT_CSMAPCACHEJSON_WHERE);

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
			query.append(CsmapCacheJsonModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(
						csmapCacheJson)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CsmapCacheJson> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 */
	@Override
	public void removeByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive) {

		for (CsmapCacheJson csmapCacheJson :
				findByModifiedDateAndIsActive(
					modifiedPlace, isActive, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(csmapCacheJson);
		}
	}

	/**
	 * Returns the number of csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
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

			query.append(_SQL_COUNT_CSMAPCACHEJSON_WHERE);

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
			"csmapCacheJson.modifiedPlace IS NULL AND ";

	private static final String
		_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_MODIFIEDPLACE_2 =
			"csmapCacheJson.modifiedPlace >= ? AND ";

	private static final String
		_FINDER_COLUMN_MODIFIEDDATEANDISACTIVE_ISACTIVE_2 =
			"csmapCacheJson.isActive = ?";

	public CsmapCacheJsonPersistenceImpl() {
		setModelClass(CsmapCacheJson.class);

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
	 * Caches the csmap cache json in the entity cache if it is enabled.
	 *
	 * @param csmapCacheJson the csmap cache json
	 */
	@Override
	public void cacheResult(CsmapCacheJson csmapCacheJson) {
		entityCache.putResult(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonImpl.class, csmapCacheJson.getPrimaryKey(),
			csmapCacheJson);

		csmapCacheJson.resetOriginalValues();
	}

	/**
	 * Caches the csmap cache jsons in the entity cache if it is enabled.
	 *
	 * @param csmapCacheJsons the csmap cache jsons
	 */
	@Override
	public void cacheResult(List<CsmapCacheJson> csmapCacheJsons) {
		for (CsmapCacheJson csmapCacheJson : csmapCacheJsons) {
			if (entityCache.getResult(
					CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
					CsmapCacheJsonImpl.class, csmapCacheJson.getPrimaryKey()) ==
						null) {

				cacheResult(csmapCacheJson);
			}
			else {
				csmapCacheJson.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all csmap cache jsons.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CsmapCacheJsonImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the csmap cache json.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CsmapCacheJson csmapCacheJson) {
		entityCache.removeResult(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonImpl.class, csmapCacheJson.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CsmapCacheJson> csmapCacheJsons) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CsmapCacheJson csmapCacheJson : csmapCacheJsons) {
			entityCache.removeResult(
				CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
				CsmapCacheJsonImpl.class, csmapCacheJson.getPrimaryKey());
		}
	}

	/**
	 * Creates a new csmap cache json with the primary key. Does not add the csmap cache json to the database.
	 *
	 * @param sigId the primary key for the new csmap cache json
	 * @return the new csmap cache json
	 */
	@Override
	public CsmapCacheJson create(String sigId) {
		CsmapCacheJson csmapCacheJson = new CsmapCacheJsonImpl();

		csmapCacheJson.setNew(true);
		csmapCacheJson.setPrimaryKey(sigId);

		String uuid = PortalUUIDUtil.generate();

		csmapCacheJson.setUuid(uuid);

		return csmapCacheJson;
	}

	/**
	 * Removes the csmap cache json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sigId the primary key of the csmap cache json
	 * @return the csmap cache json that was removed
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	@Override
	public CsmapCacheJson remove(String sigId)
		throws NoSuchCsmapCacheJsonException {

		return remove((Serializable)sigId);
	}

	/**
	 * Removes the csmap cache json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the csmap cache json
	 * @return the csmap cache json that was removed
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	@Override
	public CsmapCacheJson remove(Serializable primaryKey)
		throws NoSuchCsmapCacheJsonException {

		Session session = null;

		try {
			session = openSession();

			CsmapCacheJson csmapCacheJson = (CsmapCacheJson)session.get(
				CsmapCacheJsonImpl.class, primaryKey);

			if (csmapCacheJson == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCsmapCacheJsonException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(csmapCacheJson);
		}
		catch (NoSuchCsmapCacheJsonException nsee) {
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
	protected CsmapCacheJson removeImpl(CsmapCacheJson csmapCacheJson) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(csmapCacheJson)) {
				csmapCacheJson = (CsmapCacheJson)session.get(
					CsmapCacheJsonImpl.class,
					csmapCacheJson.getPrimaryKeyObj());
			}

			if (csmapCacheJson != null) {
				session.delete(csmapCacheJson);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (csmapCacheJson != null) {
			clearCache(csmapCacheJson);
		}

		return csmapCacheJson;
	}

	@Override
	public CsmapCacheJson updateImpl(CsmapCacheJson csmapCacheJson) {
		boolean isNew = csmapCacheJson.isNew();

		if (!(csmapCacheJson instanceof CsmapCacheJsonModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(csmapCacheJson.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					csmapCacheJson);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in csmapCacheJson proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CsmapCacheJson implementation " +
					csmapCacheJson.getClass());
		}

		CsmapCacheJsonModelImpl csmapCacheJsonModelImpl =
			(CsmapCacheJsonModelImpl)csmapCacheJson;

		if (Validator.isNull(csmapCacheJson.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			csmapCacheJson.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (csmapCacheJson.isNew()) {
				session.save(csmapCacheJson);

				csmapCacheJson.setNew(false);
			}
			else {
				csmapCacheJson = (CsmapCacheJson)session.merge(csmapCacheJson);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CsmapCacheJsonModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {csmapCacheJsonModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {csmapCacheJsonModelImpl.getSigId()};

			finderCache.removeResult(_finderPathCountBysigId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindBysigId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((csmapCacheJsonModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					csmapCacheJsonModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {csmapCacheJsonModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((csmapCacheJsonModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindBysigId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					csmapCacheJsonModelImpl.getOriginalSigId()
				};

				finderCache.removeResult(_finderPathCountBysigId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBysigId, args);

				args = new Object[] {csmapCacheJsonModelImpl.getSigId()};

				finderCache.removeResult(_finderPathCountBysigId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBysigId, args);
			}
		}

		entityCache.putResult(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonImpl.class, csmapCacheJson.getPrimaryKey(),
			csmapCacheJson, false);

		csmapCacheJson.resetOriginalValues();

		return csmapCacheJson;
	}

	/**
	 * Returns the csmap cache json with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the csmap cache json
	 * @return the csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	@Override
	public CsmapCacheJson findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCsmapCacheJsonException {

		CsmapCacheJson csmapCacheJson = fetchByPrimaryKey(primaryKey);

		if (csmapCacheJson == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCsmapCacheJsonException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return csmapCacheJson;
	}

	/**
	 * Returns the csmap cache json with the primary key or throws a <code>NoSuchCsmapCacheJsonException</code> if it could not be found.
	 *
	 * @param sigId the primary key of the csmap cache json
	 * @return the csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	@Override
	public CsmapCacheJson findByPrimaryKey(String sigId)
		throws NoSuchCsmapCacheJsonException {

		return findByPrimaryKey((Serializable)sigId);
	}

	/**
	 * Returns the csmap cache json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the csmap cache json
	 * @return the csmap cache json, or <code>null</code> if a csmap cache json with the primary key could not be found
	 */
	@Override
	public CsmapCacheJson fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CsmapCacheJson csmapCacheJson = (CsmapCacheJson)serializable;

		if (csmapCacheJson == null) {
			Session session = null;

			try {
				session = openSession();

				csmapCacheJson = (CsmapCacheJson)session.get(
					CsmapCacheJsonImpl.class, primaryKey);

				if (csmapCacheJson != null) {
					cacheResult(csmapCacheJson);
				}
				else {
					entityCache.putResult(
						CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
						CsmapCacheJsonImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
					CsmapCacheJsonImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return csmapCacheJson;
	}

	/**
	 * Returns the csmap cache json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sigId the primary key of the csmap cache json
	 * @return the csmap cache json, or <code>null</code> if a csmap cache json with the primary key could not be found
	 */
	@Override
	public CsmapCacheJson fetchByPrimaryKey(String sigId) {
		return fetchByPrimaryKey((Serializable)sigId);
	}

	@Override
	public Map<Serializable, CsmapCacheJson> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CsmapCacheJson> map =
			new HashMap<Serializable, CsmapCacheJson>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CsmapCacheJson csmapCacheJson = fetchByPrimaryKey(primaryKey);

			if (csmapCacheJson != null) {
				map.put(primaryKey, csmapCacheJson);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
				CsmapCacheJsonImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CsmapCacheJson)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_CSMAPCACHEJSON_WHERE_PKS_IN);

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

			for (CsmapCacheJson csmapCacheJson :
					(List<CsmapCacheJson>)q.list()) {

				map.put(csmapCacheJson.getPrimaryKeyObj(), csmapCacheJson);

				cacheResult(csmapCacheJson);

				uncachedPrimaryKeys.remove(csmapCacheJson.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
					CsmapCacheJsonImpl.class, primaryKey, nullModel);
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
	 * Returns all the csmap cache jsons.
	 *
	 * @return the csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findAll(
		int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of csmap cache jsons
	 */
	@Override
	public List<CsmapCacheJson> findAll(
		int start, int end, OrderByComparator<CsmapCacheJson> orderByComparator,
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

		List<CsmapCacheJson> list = null;

		if (retrieveFromCache) {
			list = (List<CsmapCacheJson>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CSMAPCACHEJSON);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CSMAPCACHEJSON;

				if (pagination) {
					sql = sql.concat(CsmapCacheJsonModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CsmapCacheJson>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CsmapCacheJson>)QueryUtil.list(
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
	 * Removes all the csmap cache jsons from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CsmapCacheJson csmapCacheJson : findAll()) {
			remove(csmapCacheJson);
		}
	}

	/**
	 * Returns the number of csmap cache jsons.
	 *
	 * @return the number of csmap cache jsons
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CSMAPCACHEJSON);

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
		return CsmapCacheJsonModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the csmap cache json persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED,
			CsmapCacheJsonImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED,
			CsmapCacheJsonImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED,
			CsmapCacheJsonImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED,
			CsmapCacheJsonImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] {String.class.getName()},
			CsmapCacheJsonModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindBysigId = new FinderPath(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED,
			CsmapCacheJsonImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBysigId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindBysigId = new FinderPath(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED,
			CsmapCacheJsonImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBysigId", new String[] {String.class.getName()},
			CsmapCacheJsonModelImpl.SIGID_COLUMN_BITMASK);

		_finderPathCountBysigId = new FinderPath(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBysigId",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByCreatedDateAndIsActive = new FinderPath(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED,
			CsmapCacheJsonImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCreatedDateAndIsActive",
			new String[] {
				Date.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByCreatedDateAndIsActive = new FinderPath(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByCreatedDateAndIsActive",
			new String[] {Date.class.getName(), Boolean.class.getName()});

		_finderPathWithPaginationFindByCreatedDateAndModifiedDateAndIsActive =
			new FinderPath(
				CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
				CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED,
				CsmapCacheJsonImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByCreatedDateAndModifiedDateAndIsActive",
				new String[] {
					Date.class.getName(), Date.class.getName(),
					Boolean.class.getName(), Integer.class.getName(),
					Integer.class.getName(), OrderByComparator.class.getName()
				});

		_finderPathWithPaginationCountByCreatedDateAndModifiedDateAndIsActive =
			new FinderPath(
				CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
				CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"countByCreatedDateAndModifiedDateAndIsActive",
				new String[] {
					Date.class.getName(), Date.class.getName(),
					Boolean.class.getName()
				});

		_finderPathWithPaginationFindByModifiedDateAndIsActive = new FinderPath(
			CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
			CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED,
			CsmapCacheJsonImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByModifiedDateAndIsActive",
			new String[] {
				Date.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByModifiedDateAndIsActive =
			new FinderPath(
				CsmapCacheJsonModelImpl.ENTITY_CACHE_ENABLED,
				CsmapCacheJsonModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"countByModifiedDateAndIsActive",
				new String[] {Date.class.getName(), Boolean.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CsmapCacheJsonImpl.class.getName());
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

	private static final String _SQL_SELECT_CSMAPCACHEJSON =
		"SELECT csmapCacheJson FROM CsmapCacheJson csmapCacheJson";

	private static final String _SQL_SELECT_CSMAPCACHEJSON_WHERE_PKS_IN =
		"SELECT csmapCacheJson FROM CsmapCacheJson csmapCacheJson WHERE sigId IN (";

	private static final String _SQL_SELECT_CSMAPCACHEJSON_WHERE =
		"SELECT csmapCacheJson FROM CsmapCacheJson csmapCacheJson WHERE ";

	private static final String _SQL_COUNT_CSMAPCACHEJSON =
		"SELECT COUNT(csmapCacheJson) FROM CsmapCacheJson csmapCacheJson";

	private static final String _SQL_COUNT_CSMAPCACHEJSON_WHERE =
		"SELECT COUNT(csmapCacheJson) FROM CsmapCacheJson csmapCacheJson WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "csmapCacheJson.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CsmapCacheJson exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CsmapCacheJson exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CsmapCacheJsonPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}