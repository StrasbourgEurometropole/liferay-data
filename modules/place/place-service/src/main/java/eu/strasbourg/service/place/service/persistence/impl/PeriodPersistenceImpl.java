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

import eu.strasbourg.service.place.exception.NoSuchPeriodException;
import eu.strasbourg.service.place.model.Period;
import eu.strasbourg.service.place.model.impl.PeriodImpl;
import eu.strasbourg.service.place.model.impl.PeriodModelImpl;
import eu.strasbourg.service.place.service.persistence.PeriodPersistence;

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
 * The persistence implementation for the period service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @generated
 */
@ProviderType
public class PeriodPersistenceImpl
	extends BasePersistenceImpl<Period> implements PeriodPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>PeriodUtil</code> to access the period persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		PeriodImpl.class.getName();

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
	 * Returns all the periods where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching periods
	 */
	@Override
	public List<Period> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the periods where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PeriodModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of periods
	 * @param end the upper bound of the range of periods (not inclusive)
	 * @return the range of matching periods
	 */
	@Override
	public List<Period> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the periods where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PeriodModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of periods
	 * @param end the upper bound of the range of periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching periods
	 */
	@Override
	public List<Period> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Period> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the periods where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PeriodModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of periods
	 * @param end the upper bound of the range of periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching periods
	 */
	@Override
	public List<Period> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Period> orderByComparator,
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

		List<Period> list = null;

		if (retrieveFromCache) {
			list = (List<Period>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Period period : list) {
					if (!uuid.equals(period.getUuid())) {
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

			query.append(_SQL_SELECT_PERIOD_WHERE);

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
				query.append(PeriodModelImpl.ORDER_BY_JPQL);
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
					list = (List<Period>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Period>)QueryUtil.list(
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
	 * Returns the first period in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching period
	 * @throws NoSuchPeriodException if a matching period could not be found
	 */
	@Override
	public Period findByUuid_First(
			String uuid, OrderByComparator<Period> orderByComparator)
		throws NoSuchPeriodException {

		Period period = fetchByUuid_First(uuid, orderByComparator);

		if (period != null) {
			return period;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPeriodException(msg.toString());
	}

	/**
	 * Returns the first period in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching period, or <code>null</code> if a matching period could not be found
	 */
	@Override
	public Period fetchByUuid_First(
		String uuid, OrderByComparator<Period> orderByComparator) {

		List<Period> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last period in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching period
	 * @throws NoSuchPeriodException if a matching period could not be found
	 */
	@Override
	public Period findByUuid_Last(
			String uuid, OrderByComparator<Period> orderByComparator)
		throws NoSuchPeriodException {

		Period period = fetchByUuid_Last(uuid, orderByComparator);

		if (period != null) {
			return period;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPeriodException(msg.toString());
	}

	/**
	 * Returns the last period in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching period, or <code>null</code> if a matching period could not be found
	 */
	@Override
	public Period fetchByUuid_Last(
		String uuid, OrderByComparator<Period> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Period> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the periods before and after the current period in the ordered set where uuid = &#63;.
	 *
	 * @param periodId the primary key of the current period
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next period
	 * @throws NoSuchPeriodException if a period with the primary key could not be found
	 */
	@Override
	public Period[] findByUuid_PrevAndNext(
			long periodId, String uuid,
			OrderByComparator<Period> orderByComparator)
		throws NoSuchPeriodException {

		uuid = Objects.toString(uuid, "");

		Period period = findByPrimaryKey(periodId);

		Session session = null;

		try {
			session = openSession();

			Period[] array = new PeriodImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, period, uuid, orderByComparator, true);

			array[1] = period;

			array[2] = getByUuid_PrevAndNext(
				session, period, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Period getByUuid_PrevAndNext(
		Session session, Period period, String uuid,
		OrderByComparator<Period> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PERIOD_WHERE);

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
			query.append(PeriodModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(period)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Period> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the periods where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Period period :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(period);
		}
	}

	/**
	 * Returns the number of periods where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching periods
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PERIOD_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "period.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(period.uuid IS NULL OR period.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByPlaceId;
	private FinderPath _finderPathWithoutPaginationFindByPlaceId;
	private FinderPath _finderPathCountByPlaceId;

	/**
	 * Returns all the periods where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @return the matching periods
	 */
	@Override
	public List<Period> findByPlaceId(long placeId) {
		return findByPlaceId(
			placeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the periods where placeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PeriodModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param placeId the place ID
	 * @param start the lower bound of the range of periods
	 * @param end the upper bound of the range of periods (not inclusive)
	 * @return the range of matching periods
	 */
	@Override
	public List<Period> findByPlaceId(long placeId, int start, int end) {
		return findByPlaceId(placeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the periods where placeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PeriodModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param placeId the place ID
	 * @param start the lower bound of the range of periods
	 * @param end the upper bound of the range of periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching periods
	 */
	@Override
	public List<Period> findByPlaceId(
		long placeId, int start, int end,
		OrderByComparator<Period> orderByComparator) {

		return findByPlaceId(placeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the periods where placeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PeriodModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param placeId the place ID
	 * @param start the lower bound of the range of periods
	 * @param end the upper bound of the range of periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching periods
	 */
	@Override
	public List<Period> findByPlaceId(
		long placeId, int start, int end,
		OrderByComparator<Period> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByPlaceId;
			finderArgs = new Object[] {placeId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByPlaceId;
			finderArgs = new Object[] {placeId, start, end, orderByComparator};
		}

		List<Period> list = null;

		if (retrieveFromCache) {
			list = (List<Period>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Period period : list) {
					if ((placeId != period.getPlaceId())) {
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

			query.append(_SQL_SELECT_PERIOD_WHERE);

			query.append(_FINDER_COLUMN_PLACEID_PLACEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(PeriodModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(placeId);

				if (!pagination) {
					list = (List<Period>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Period>)QueryUtil.list(
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
	 * Returns the first period in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching period
	 * @throws NoSuchPeriodException if a matching period could not be found
	 */
	@Override
	public Period findByPlaceId_First(
			long placeId, OrderByComparator<Period> orderByComparator)
		throws NoSuchPeriodException {

		Period period = fetchByPlaceId_First(placeId, orderByComparator);

		if (period != null) {
			return period;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("placeId=");
		msg.append(placeId);

		msg.append("}");

		throw new NoSuchPeriodException(msg.toString());
	}

	/**
	 * Returns the first period in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching period, or <code>null</code> if a matching period could not be found
	 */
	@Override
	public Period fetchByPlaceId_First(
		long placeId, OrderByComparator<Period> orderByComparator) {

		List<Period> list = findByPlaceId(placeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last period in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching period
	 * @throws NoSuchPeriodException if a matching period could not be found
	 */
	@Override
	public Period findByPlaceId_Last(
			long placeId, OrderByComparator<Period> orderByComparator)
		throws NoSuchPeriodException {

		Period period = fetchByPlaceId_Last(placeId, orderByComparator);

		if (period != null) {
			return period;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("placeId=");
		msg.append(placeId);

		msg.append("}");

		throw new NoSuchPeriodException(msg.toString());
	}

	/**
	 * Returns the last period in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching period, or <code>null</code> if a matching period could not be found
	 */
	@Override
	public Period fetchByPlaceId_Last(
		long placeId, OrderByComparator<Period> orderByComparator) {

		int count = countByPlaceId(placeId);

		if (count == 0) {
			return null;
		}

		List<Period> list = findByPlaceId(
			placeId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the periods before and after the current period in the ordered set where placeId = &#63;.
	 *
	 * @param periodId the primary key of the current period
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next period
	 * @throws NoSuchPeriodException if a period with the primary key could not be found
	 */
	@Override
	public Period[] findByPlaceId_PrevAndNext(
			long periodId, long placeId,
			OrderByComparator<Period> orderByComparator)
		throws NoSuchPeriodException {

		Period period = findByPrimaryKey(periodId);

		Session session = null;

		try {
			session = openSession();

			Period[] array = new PeriodImpl[3];

			array[0] = getByPlaceId_PrevAndNext(
				session, period, placeId, orderByComparator, true);

			array[1] = period;

			array[2] = getByPlaceId_PrevAndNext(
				session, period, placeId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Period getByPlaceId_PrevAndNext(
		Session session, Period period, long placeId,
		OrderByComparator<Period> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PERIOD_WHERE);

		query.append(_FINDER_COLUMN_PLACEID_PLACEID_2);

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
			query.append(PeriodModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(placeId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(period)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Period> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the periods where placeId = &#63; from the database.
	 *
	 * @param placeId the place ID
	 */
	@Override
	public void removeByPlaceId(long placeId) {
		for (Period period :
				findByPlaceId(
					placeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(period);
		}
	}

	/**
	 * Returns the number of periods where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @return the number of matching periods
	 */
	@Override
	public int countByPlaceId(long placeId) {
		FinderPath finderPath = _finderPathCountByPlaceId;

		Object[] finderArgs = new Object[] {placeId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PERIOD_WHERE);

			query.append(_FINDER_COLUMN_PLACEID_PLACEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(placeId);

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

	private static final String _FINDER_COLUMN_PLACEID_PLACEID_2 =
		"period.placeId = ?";

	public PeriodPersistenceImpl() {
		setModelClass(Period.class);

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
	 * Caches the period in the entity cache if it is enabled.
	 *
	 * @param period the period
	 */
	@Override
	public void cacheResult(Period period) {
		entityCache.putResult(
			PeriodModelImpl.ENTITY_CACHE_ENABLED, PeriodImpl.class,
			period.getPrimaryKey(), period);

		period.resetOriginalValues();
	}

	/**
	 * Caches the periods in the entity cache if it is enabled.
	 *
	 * @param periods the periods
	 */
	@Override
	public void cacheResult(List<Period> periods) {
		for (Period period : periods) {
			if (entityCache.getResult(
					PeriodModelImpl.ENTITY_CACHE_ENABLED, PeriodImpl.class,
					period.getPrimaryKey()) == null) {

				cacheResult(period);
			}
			else {
				period.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all periods.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PeriodImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the period.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Period period) {
		entityCache.removeResult(
			PeriodModelImpl.ENTITY_CACHE_ENABLED, PeriodImpl.class,
			period.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Period> periods) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Period period : periods) {
			entityCache.removeResult(
				PeriodModelImpl.ENTITY_CACHE_ENABLED, PeriodImpl.class,
				period.getPrimaryKey());
		}
	}

	/**
	 * Creates a new period with the primary key. Does not add the period to the database.
	 *
	 * @param periodId the primary key for the new period
	 * @return the new period
	 */
	@Override
	public Period create(long periodId) {
		Period period = new PeriodImpl();

		period.setNew(true);
		period.setPrimaryKey(periodId);

		String uuid = PortalUUIDUtil.generate();

		period.setUuid(uuid);

		return period;
	}

	/**
	 * Removes the period with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param periodId the primary key of the period
	 * @return the period that was removed
	 * @throws NoSuchPeriodException if a period with the primary key could not be found
	 */
	@Override
	public Period remove(long periodId) throws NoSuchPeriodException {
		return remove((Serializable)periodId);
	}

	/**
	 * Removes the period with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the period
	 * @return the period that was removed
	 * @throws NoSuchPeriodException if a period with the primary key could not be found
	 */
	@Override
	public Period remove(Serializable primaryKey) throws NoSuchPeriodException {
		Session session = null;

		try {
			session = openSession();

			Period period = (Period)session.get(PeriodImpl.class, primaryKey);

			if (period == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPeriodException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(period);
		}
		catch (NoSuchPeriodException nsee) {
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
	protected Period removeImpl(Period period) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(period)) {
				period = (Period)session.get(
					PeriodImpl.class, period.getPrimaryKeyObj());
			}

			if (period != null) {
				session.delete(period);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (period != null) {
			clearCache(period);
		}

		return period;
	}

	@Override
	public Period updateImpl(Period period) {
		boolean isNew = period.isNew();

		if (!(period instanceof PeriodModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(period.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(period);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in period proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Period implementation " +
					period.getClass());
		}

		PeriodModelImpl periodModelImpl = (PeriodModelImpl)period;

		if (Validator.isNull(period.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			period.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (period.isNew()) {
				session.save(period);

				period.setNew(false);
			}
			else {
				period = (Period)session.merge(period);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PeriodModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {periodModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {periodModelImpl.getPlaceId()};

			finderCache.removeResult(_finderPathCountByPlaceId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPlaceId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((periodModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					periodModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {periodModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((periodModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPlaceId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					periodModelImpl.getOriginalPlaceId()
				};

				finderCache.removeResult(_finderPathCountByPlaceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPlaceId, args);

				args = new Object[] {periodModelImpl.getPlaceId()};

				finderCache.removeResult(_finderPathCountByPlaceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPlaceId, args);
			}
		}

		entityCache.putResult(
			PeriodModelImpl.ENTITY_CACHE_ENABLED, PeriodImpl.class,
			period.getPrimaryKey(), period, false);

		period.resetOriginalValues();

		return period;
	}

	/**
	 * Returns the period with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the period
	 * @return the period
	 * @throws NoSuchPeriodException if a period with the primary key could not be found
	 */
	@Override
	public Period findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPeriodException {

		Period period = fetchByPrimaryKey(primaryKey);

		if (period == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPeriodException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return period;
	}

	/**
	 * Returns the period with the primary key or throws a <code>NoSuchPeriodException</code> if it could not be found.
	 *
	 * @param periodId the primary key of the period
	 * @return the period
	 * @throws NoSuchPeriodException if a period with the primary key could not be found
	 */
	@Override
	public Period findByPrimaryKey(long periodId) throws NoSuchPeriodException {
		return findByPrimaryKey((Serializable)periodId);
	}

	/**
	 * Returns the period with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the period
	 * @return the period, or <code>null</code> if a period with the primary key could not be found
	 */
	@Override
	public Period fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			PeriodModelImpl.ENTITY_CACHE_ENABLED, PeriodImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Period period = (Period)serializable;

		if (period == null) {
			Session session = null;

			try {
				session = openSession();

				period = (Period)session.get(PeriodImpl.class, primaryKey);

				if (period != null) {
					cacheResult(period);
				}
				else {
					entityCache.putResult(
						PeriodModelImpl.ENTITY_CACHE_ENABLED, PeriodImpl.class,
						primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					PeriodModelImpl.ENTITY_CACHE_ENABLED, PeriodImpl.class,
					primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return period;
	}

	/**
	 * Returns the period with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param periodId the primary key of the period
	 * @return the period, or <code>null</code> if a period with the primary key could not be found
	 */
	@Override
	public Period fetchByPrimaryKey(long periodId) {
		return fetchByPrimaryKey((Serializable)periodId);
	}

	@Override
	public Map<Serializable, Period> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Period> map = new HashMap<Serializable, Period>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Period period = fetchByPrimaryKey(primaryKey);

			if (period != null) {
				map.put(primaryKey, period);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				PeriodModelImpl.ENTITY_CACHE_ENABLED, PeriodImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Period)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_PERIOD_WHERE_PKS_IN);

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

			for (Period period : (List<Period>)q.list()) {
				map.put(period.getPrimaryKeyObj(), period);

				cacheResult(period);

				uncachedPrimaryKeys.remove(period.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					PeriodModelImpl.ENTITY_CACHE_ENABLED, PeriodImpl.class,
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
	 * Returns all the periods.
	 *
	 * @return the periods
	 */
	@Override
	public List<Period> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the periods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PeriodModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of periods
	 * @param end the upper bound of the range of periods (not inclusive)
	 * @return the range of periods
	 */
	@Override
	public List<Period> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the periods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PeriodModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of periods
	 * @param end the upper bound of the range of periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of periods
	 */
	@Override
	public List<Period> findAll(
		int start, int end, OrderByComparator<Period> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the periods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PeriodModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of periods
	 * @param end the upper bound of the range of periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of periods
	 */
	@Override
	public List<Period> findAll(
		int start, int end, OrderByComparator<Period> orderByComparator,
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

		List<Period> list = null;

		if (retrieveFromCache) {
			list = (List<Period>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PERIOD);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PERIOD;

				if (pagination) {
					sql = sql.concat(PeriodModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Period>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Period>)QueryUtil.list(
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
	 * Removes all the periods from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Period period : findAll()) {
			remove(period);
		}
	}

	/**
	 * Returns the number of periods.
	 *
	 * @return the number of periods
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PERIOD);

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
		return PeriodModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the period persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			PeriodModelImpl.ENTITY_CACHE_ENABLED,
			PeriodModelImpl.FINDER_CACHE_ENABLED, PeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			PeriodModelImpl.ENTITY_CACHE_ENABLED,
			PeriodModelImpl.FINDER_CACHE_ENABLED, PeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			PeriodModelImpl.ENTITY_CACHE_ENABLED,
			PeriodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			PeriodModelImpl.ENTITY_CACHE_ENABLED,
			PeriodModelImpl.FINDER_CACHE_ENABLED, PeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			PeriodModelImpl.ENTITY_CACHE_ENABLED,
			PeriodModelImpl.FINDER_CACHE_ENABLED, PeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			PeriodModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			PeriodModelImpl.ENTITY_CACHE_ENABLED,
			PeriodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByPlaceId = new FinderPath(
			PeriodModelImpl.ENTITY_CACHE_ENABLED,
			PeriodModelImpl.FINDER_CACHE_ENABLED, PeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPlaceId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPlaceId = new FinderPath(
			PeriodModelImpl.ENTITY_CACHE_ENABLED,
			PeriodModelImpl.FINDER_CACHE_ENABLED, PeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlaceId",
			new String[] {Long.class.getName()},
			PeriodModelImpl.PLACEID_COLUMN_BITMASK);

		_finderPathCountByPlaceId = new FinderPath(
			PeriodModelImpl.ENTITY_CACHE_ENABLED,
			PeriodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlaceId",
			new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(PeriodImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_PERIOD =
		"SELECT period FROM Period period";

	private static final String _SQL_SELECT_PERIOD_WHERE_PKS_IN =
		"SELECT period FROM Period period WHERE periodId IN (";

	private static final String _SQL_SELECT_PERIOD_WHERE =
		"SELECT period FROM Period period WHERE ";

	private static final String _SQL_COUNT_PERIOD =
		"SELECT COUNT(period) FROM Period period";

	private static final String _SQL_COUNT_PERIOD_WHERE =
		"SELECT COUNT(period) FROM Period period WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "period.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Period exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Period exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		PeriodPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}