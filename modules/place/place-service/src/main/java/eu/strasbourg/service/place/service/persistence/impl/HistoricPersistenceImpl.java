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

import eu.strasbourg.service.place.exception.NoSuchHistoricException;
import eu.strasbourg.service.place.model.Historic;
import eu.strasbourg.service.place.model.impl.HistoricImpl;
import eu.strasbourg.service.place.model.impl.HistoricModelImpl;
import eu.strasbourg.service.place.service.persistence.HistoricPersistence;

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
 * The persistence implementation for the historic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @generated
 */
@ProviderType
public class HistoricPersistenceImpl
	extends BasePersistenceImpl<Historic> implements HistoricPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>HistoricUtil</code> to access the historic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		HistoricImpl.class.getName();

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
	 * Returns all the historics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching historics
	 */
	@Override
	public List<Historic> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @return the range of matching historics
	 */
	@Override
	public List<Historic> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching historics
	 */
	@Override
	public List<Historic> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Historic> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching historics
	 */
	@Override
	public List<Historic> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Historic> orderByComparator,
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

		List<Historic> list = null;

		if (retrieveFromCache) {
			list = (List<Historic>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Historic historic : list) {
					if (!uuid.equals(historic.getUuid())) {
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

			query.append(_SQL_SELECT_HISTORIC_WHERE);

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
				query.append(HistoricModelImpl.ORDER_BY_JPQL);
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
					list = (List<Historic>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Historic>)QueryUtil.list(
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
	 * Returns the first historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historic
	 * @throws NoSuchHistoricException if a matching historic could not be found
	 */
	@Override
	public Historic findByUuid_First(
			String uuid, OrderByComparator<Historic> orderByComparator)
		throws NoSuchHistoricException {

		Historic historic = fetchByUuid_First(uuid, orderByComparator);

		if (historic != null) {
			return historic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchHistoricException(msg.toString());
	}

	/**
	 * Returns the first historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historic, or <code>null</code> if a matching historic could not be found
	 */
	@Override
	public Historic fetchByUuid_First(
		String uuid, OrderByComparator<Historic> orderByComparator) {

		List<Historic> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historic
	 * @throws NoSuchHistoricException if a matching historic could not be found
	 */
	@Override
	public Historic findByUuid_Last(
			String uuid, OrderByComparator<Historic> orderByComparator)
		throws NoSuchHistoricException {

		Historic historic = fetchByUuid_Last(uuid, orderByComparator);

		if (historic != null) {
			return historic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchHistoricException(msg.toString());
	}

	/**
	 * Returns the last historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historic, or <code>null</code> if a matching historic could not be found
	 */
	@Override
	public Historic fetchByUuid_Last(
		String uuid, OrderByComparator<Historic> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Historic> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the historics before and after the current historic in the ordered set where uuid = &#63;.
	 *
	 * @param sigId the primary key of the current historic
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next historic
	 * @throws NoSuchHistoricException if a historic with the primary key could not be found
	 */
	@Override
	public Historic[] findByUuid_PrevAndNext(
			String sigId, String uuid,
			OrderByComparator<Historic> orderByComparator)
		throws NoSuchHistoricException {

		uuid = Objects.toString(uuid, "");

		Historic historic = findByPrimaryKey(sigId);

		Session session = null;

		try {
			session = openSession();

			Historic[] array = new HistoricImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, historic, uuid, orderByComparator, true);

			array[1] = historic;

			array[2] = getByUuid_PrevAndNext(
				session, historic, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Historic getByUuid_PrevAndNext(
		Session session, Historic historic, String uuid,
		OrderByComparator<Historic> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_HISTORIC_WHERE);

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
			query.append(HistoricModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(historic)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Historic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the historics where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Historic historic :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(historic);
		}
	}

	/**
	 * Returns the number of historics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching historics
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HISTORIC_WHERE);

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
		"historic.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(historic.uuid IS NULL OR historic.uuid = '')";

	private FinderPath _finderPathWithPaginationFindBySuppressionDate;
	private FinderPath _finderPathWithPaginationCountBySuppressionDate;

	/**
	 * Returns all the historics where suppressionDate &ge; &#63;.
	 *
	 * @param suppressionDate the suppression date
	 * @return the matching historics
	 */
	@Override
	public List<Historic> findBySuppressionDate(Date suppressionDate) {
		return findBySuppressionDate(
			suppressionDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the historics where suppressionDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param suppressionDate the suppression date
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @return the range of matching historics
	 */
	@Override
	public List<Historic> findBySuppressionDate(
		Date suppressionDate, int start, int end) {

		return findBySuppressionDate(suppressionDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the historics where suppressionDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param suppressionDate the suppression date
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching historics
	 */
	@Override
	public List<Historic> findBySuppressionDate(
		Date suppressionDate, int start, int end,
		OrderByComparator<Historic> orderByComparator) {

		return findBySuppressionDate(
			suppressionDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the historics where suppressionDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param suppressionDate the suppression date
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching historics
	 */
	@Override
	public List<Historic> findBySuppressionDate(
		Date suppressionDate, int start, int end,
		OrderByComparator<Historic> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindBySuppressionDate;
		finderArgs = new Object[] {
			_getTime(suppressionDate), start, end, orderByComparator
		};

		List<Historic> list = null;

		if (retrieveFromCache) {
			list = (List<Historic>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Historic historic : list) {
					if ((suppressionDate.getTime() >
							historic.getSuppressionDate().getTime())) {

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

			query.append(_SQL_SELECT_HISTORIC_WHERE);

			boolean bindSuppressionDate = false;

			if (suppressionDate == null) {
				query.append(_FINDER_COLUMN_SUPPRESSIONDATE_SUPPRESSIONDATE_1);
			}
			else {
				bindSuppressionDate = true;

				query.append(_FINDER_COLUMN_SUPPRESSIONDATE_SUPPRESSIONDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(HistoricModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSuppressionDate) {
					qPos.add(new Timestamp(suppressionDate.getTime()));
				}

				if (!pagination) {
					list = (List<Historic>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Historic>)QueryUtil.list(
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
	 * Returns the first historic in the ordered set where suppressionDate &ge; &#63;.
	 *
	 * @param suppressionDate the suppression date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historic
	 * @throws NoSuchHistoricException if a matching historic could not be found
	 */
	@Override
	public Historic findBySuppressionDate_First(
			Date suppressionDate, OrderByComparator<Historic> orderByComparator)
		throws NoSuchHistoricException {

		Historic historic = fetchBySuppressionDate_First(
			suppressionDate, orderByComparator);

		if (historic != null) {
			return historic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("suppressionDate=");
		msg.append(suppressionDate);

		msg.append("}");

		throw new NoSuchHistoricException(msg.toString());
	}

	/**
	 * Returns the first historic in the ordered set where suppressionDate &ge; &#63;.
	 *
	 * @param suppressionDate the suppression date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historic, or <code>null</code> if a matching historic could not be found
	 */
	@Override
	public Historic fetchBySuppressionDate_First(
		Date suppressionDate, OrderByComparator<Historic> orderByComparator) {

		List<Historic> list = findBySuppressionDate(
			suppressionDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last historic in the ordered set where suppressionDate &ge; &#63;.
	 *
	 * @param suppressionDate the suppression date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historic
	 * @throws NoSuchHistoricException if a matching historic could not be found
	 */
	@Override
	public Historic findBySuppressionDate_Last(
			Date suppressionDate, OrderByComparator<Historic> orderByComparator)
		throws NoSuchHistoricException {

		Historic historic = fetchBySuppressionDate_Last(
			suppressionDate, orderByComparator);

		if (historic != null) {
			return historic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("suppressionDate=");
		msg.append(suppressionDate);

		msg.append("}");

		throw new NoSuchHistoricException(msg.toString());
	}

	/**
	 * Returns the last historic in the ordered set where suppressionDate &ge; &#63;.
	 *
	 * @param suppressionDate the suppression date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historic, or <code>null</code> if a matching historic could not be found
	 */
	@Override
	public Historic fetchBySuppressionDate_Last(
		Date suppressionDate, OrderByComparator<Historic> orderByComparator) {

		int count = countBySuppressionDate(suppressionDate);

		if (count == 0) {
			return null;
		}

		List<Historic> list = findBySuppressionDate(
			suppressionDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the historics before and after the current historic in the ordered set where suppressionDate &ge; &#63;.
	 *
	 * @param sigId the primary key of the current historic
	 * @param suppressionDate the suppression date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next historic
	 * @throws NoSuchHistoricException if a historic with the primary key could not be found
	 */
	@Override
	public Historic[] findBySuppressionDate_PrevAndNext(
			String sigId, Date suppressionDate,
			OrderByComparator<Historic> orderByComparator)
		throws NoSuchHistoricException {

		Historic historic = findByPrimaryKey(sigId);

		Session session = null;

		try {
			session = openSession();

			Historic[] array = new HistoricImpl[3];

			array[0] = getBySuppressionDate_PrevAndNext(
				session, historic, suppressionDate, orderByComparator, true);

			array[1] = historic;

			array[2] = getBySuppressionDate_PrevAndNext(
				session, historic, suppressionDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Historic getBySuppressionDate_PrevAndNext(
		Session session, Historic historic, Date suppressionDate,
		OrderByComparator<Historic> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_HISTORIC_WHERE);

		boolean bindSuppressionDate = false;

		if (suppressionDate == null) {
			query.append(_FINDER_COLUMN_SUPPRESSIONDATE_SUPPRESSIONDATE_1);
		}
		else {
			bindSuppressionDate = true;

			query.append(_FINDER_COLUMN_SUPPRESSIONDATE_SUPPRESSIONDATE_2);
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
			query.append(HistoricModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSuppressionDate) {
			qPos.add(new Timestamp(suppressionDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(historic)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Historic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the historics where suppressionDate &ge; &#63; from the database.
	 *
	 * @param suppressionDate the suppression date
	 */
	@Override
	public void removeBySuppressionDate(Date suppressionDate) {
		for (Historic historic :
				findBySuppressionDate(
					suppressionDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(historic);
		}
	}

	/**
	 * Returns the number of historics where suppressionDate &ge; &#63;.
	 *
	 * @param suppressionDate the suppression date
	 * @return the number of matching historics
	 */
	@Override
	public int countBySuppressionDate(Date suppressionDate) {
		FinderPath finderPath = _finderPathWithPaginationCountBySuppressionDate;

		Object[] finderArgs = new Object[] {_getTime(suppressionDate)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HISTORIC_WHERE);

			boolean bindSuppressionDate = false;

			if (suppressionDate == null) {
				query.append(_FINDER_COLUMN_SUPPRESSIONDATE_SUPPRESSIONDATE_1);
			}
			else {
				bindSuppressionDate = true;

				query.append(_FINDER_COLUMN_SUPPRESSIONDATE_SUPPRESSIONDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSuppressionDate) {
					qPos.add(new Timestamp(suppressionDate.getTime()));
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

	private static final String
		_FINDER_COLUMN_SUPPRESSIONDATE_SUPPRESSIONDATE_1 =
			"historic.suppressionDate IS NULL";

	private static final String
		_FINDER_COLUMN_SUPPRESSIONDATE_SUPPRESSIONDATE_2 =
			"historic.suppressionDate >= ?";

	public HistoricPersistenceImpl() {
		setModelClass(Historic.class);

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
	 * Caches the historic in the entity cache if it is enabled.
	 *
	 * @param historic the historic
	 */
	@Override
	public void cacheResult(Historic historic) {
		entityCache.putResult(
			HistoricModelImpl.ENTITY_CACHE_ENABLED, HistoricImpl.class,
			historic.getPrimaryKey(), historic);

		historic.resetOriginalValues();
	}

	/**
	 * Caches the historics in the entity cache if it is enabled.
	 *
	 * @param historics the historics
	 */
	@Override
	public void cacheResult(List<Historic> historics) {
		for (Historic historic : historics) {
			if (entityCache.getResult(
					HistoricModelImpl.ENTITY_CACHE_ENABLED, HistoricImpl.class,
					historic.getPrimaryKey()) == null) {

				cacheResult(historic);
			}
			else {
				historic.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all historics.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HistoricImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the historic.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Historic historic) {
		entityCache.removeResult(
			HistoricModelImpl.ENTITY_CACHE_ENABLED, HistoricImpl.class,
			historic.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Historic> historics) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Historic historic : historics) {
			entityCache.removeResult(
				HistoricModelImpl.ENTITY_CACHE_ENABLED, HistoricImpl.class,
				historic.getPrimaryKey());
		}
	}

	/**
	 * Creates a new historic with the primary key. Does not add the historic to the database.
	 *
	 * @param sigId the primary key for the new historic
	 * @return the new historic
	 */
	@Override
	public Historic create(String sigId) {
		Historic historic = new HistoricImpl();

		historic.setNew(true);
		historic.setPrimaryKey(sigId);

		String uuid = PortalUUIDUtil.generate();

		historic.setUuid(uuid);

		return historic;
	}

	/**
	 * Removes the historic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sigId the primary key of the historic
	 * @return the historic that was removed
	 * @throws NoSuchHistoricException if a historic with the primary key could not be found
	 */
	@Override
	public Historic remove(String sigId) throws NoSuchHistoricException {
		return remove((Serializable)sigId);
	}

	/**
	 * Removes the historic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the historic
	 * @return the historic that was removed
	 * @throws NoSuchHistoricException if a historic with the primary key could not be found
	 */
	@Override
	public Historic remove(Serializable primaryKey)
		throws NoSuchHistoricException {

		Session session = null;

		try {
			session = openSession();

			Historic historic = (Historic)session.get(
				HistoricImpl.class, primaryKey);

			if (historic == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistoricException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(historic);
		}
		catch (NoSuchHistoricException nsee) {
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
	protected Historic removeImpl(Historic historic) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(historic)) {
				historic = (Historic)session.get(
					HistoricImpl.class, historic.getPrimaryKeyObj());
			}

			if (historic != null) {
				session.delete(historic);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (historic != null) {
			clearCache(historic);
		}

		return historic;
	}

	@Override
	public Historic updateImpl(Historic historic) {
		boolean isNew = historic.isNew();

		if (!(historic instanceof HistoricModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(historic.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(historic);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in historic proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Historic implementation " +
					historic.getClass());
		}

		HistoricModelImpl historicModelImpl = (HistoricModelImpl)historic;

		if (Validator.isNull(historic.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			historic.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (historic.isNew()) {
				session.save(historic);

				historic.setNew(false);
			}
			else {
				historic = (Historic)session.merge(historic);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!HistoricModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {historicModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((historicModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					historicModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {historicModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}
		}

		entityCache.putResult(
			HistoricModelImpl.ENTITY_CACHE_ENABLED, HistoricImpl.class,
			historic.getPrimaryKey(), historic, false);

		historic.resetOriginalValues();

		return historic;
	}

	/**
	 * Returns the historic with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the historic
	 * @return the historic
	 * @throws NoSuchHistoricException if a historic with the primary key could not be found
	 */
	@Override
	public Historic findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistoricException {

		Historic historic = fetchByPrimaryKey(primaryKey);

		if (historic == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistoricException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return historic;
	}

	/**
	 * Returns the historic with the primary key or throws a <code>NoSuchHistoricException</code> if it could not be found.
	 *
	 * @param sigId the primary key of the historic
	 * @return the historic
	 * @throws NoSuchHistoricException if a historic with the primary key could not be found
	 */
	@Override
	public Historic findByPrimaryKey(String sigId)
		throws NoSuchHistoricException {

		return findByPrimaryKey((Serializable)sigId);
	}

	/**
	 * Returns the historic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the historic
	 * @return the historic, or <code>null</code> if a historic with the primary key could not be found
	 */
	@Override
	public Historic fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			HistoricModelImpl.ENTITY_CACHE_ENABLED, HistoricImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Historic historic = (Historic)serializable;

		if (historic == null) {
			Session session = null;

			try {
				session = openSession();

				historic = (Historic)session.get(
					HistoricImpl.class, primaryKey);

				if (historic != null) {
					cacheResult(historic);
				}
				else {
					entityCache.putResult(
						HistoricModelImpl.ENTITY_CACHE_ENABLED,
						HistoricImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					HistoricModelImpl.ENTITY_CACHE_ENABLED, HistoricImpl.class,
					primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return historic;
	}

	/**
	 * Returns the historic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sigId the primary key of the historic
	 * @return the historic, or <code>null</code> if a historic with the primary key could not be found
	 */
	@Override
	public Historic fetchByPrimaryKey(String sigId) {
		return fetchByPrimaryKey((Serializable)sigId);
	}

	@Override
	public Map<Serializable, Historic> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Historic> map = new HashMap<Serializable, Historic>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Historic historic = fetchByPrimaryKey(primaryKey);

			if (historic != null) {
				map.put(primaryKey, historic);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				HistoricModelImpl.ENTITY_CACHE_ENABLED, HistoricImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Historic)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_HISTORIC_WHERE_PKS_IN);

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

			for (Historic historic : (List<Historic>)q.list()) {
				map.put(historic.getPrimaryKeyObj(), historic);

				cacheResult(historic);

				uncachedPrimaryKeys.remove(historic.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					HistoricModelImpl.ENTITY_CACHE_ENABLED, HistoricImpl.class,
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
	 * Returns all the historics.
	 *
	 * @return the historics
	 */
	@Override
	public List<Historic> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @return the range of historics
	 */
	@Override
	public List<Historic> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of historics
	 */
	@Override
	public List<Historic> findAll(
		int start, int end, OrderByComparator<Historic> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of historics
	 */
	@Override
	public List<Historic> findAll(
		int start, int end, OrderByComparator<Historic> orderByComparator,
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

		List<Historic> list = null;

		if (retrieveFromCache) {
			list = (List<Historic>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HISTORIC);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTORIC;

				if (pagination) {
					sql = sql.concat(HistoricModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Historic>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Historic>)QueryUtil.list(
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
	 * Removes all the historics from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Historic historic : findAll()) {
			remove(historic);
		}
	}

	/**
	 * Returns the number of historics.
	 *
	 * @return the number of historics
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HISTORIC);

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
		return HistoricModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the historic persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			HistoricModelImpl.ENTITY_CACHE_ENABLED,
			HistoricModelImpl.FINDER_CACHE_ENABLED, HistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			HistoricModelImpl.ENTITY_CACHE_ENABLED,
			HistoricModelImpl.FINDER_CACHE_ENABLED, HistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			HistoricModelImpl.ENTITY_CACHE_ENABLED,
			HistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			HistoricModelImpl.ENTITY_CACHE_ENABLED,
			HistoricModelImpl.FINDER_CACHE_ENABLED, HistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			HistoricModelImpl.ENTITY_CACHE_ENABLED,
			HistoricModelImpl.FINDER_CACHE_ENABLED, HistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			HistoricModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			HistoricModelImpl.ENTITY_CACHE_ENABLED,
			HistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindBySuppressionDate = new FinderPath(
			HistoricModelImpl.ENTITY_CACHE_ENABLED,
			HistoricModelImpl.FINDER_CACHE_ENABLED, HistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySuppressionDate",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountBySuppressionDate = new FinderPath(
			HistoricModelImpl.ENTITY_CACHE_ENABLED,
			HistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countBySuppressionDate",
			new String[] {Date.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(HistoricImpl.class.getName());
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

	private static final String _SQL_SELECT_HISTORIC =
		"SELECT historic FROM Historic historic";

	private static final String _SQL_SELECT_HISTORIC_WHERE_PKS_IN =
		"SELECT historic FROM Historic historic WHERE sigId IN (";

	private static final String _SQL_SELECT_HISTORIC_WHERE =
		"SELECT historic FROM Historic historic WHERE ";

	private static final String _SQL_COUNT_HISTORIC =
		"SELECT COUNT(historic) FROM Historic historic";

	private static final String _SQL_COUNT_HISTORIC_WHERE =
		"SELECT COUNT(historic) FROM Historic historic WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "historic.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Historic exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Historic exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		HistoricPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}