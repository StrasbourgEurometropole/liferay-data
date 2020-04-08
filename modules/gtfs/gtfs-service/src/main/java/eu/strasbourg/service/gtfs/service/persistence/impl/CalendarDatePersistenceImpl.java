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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.gtfs.exception.NoSuchCalendarDateException;
import eu.strasbourg.service.gtfs.model.CalendarDate;
import eu.strasbourg.service.gtfs.model.impl.CalendarDateImpl;
import eu.strasbourg.service.gtfs.model.impl.CalendarDateModelImpl;
import eu.strasbourg.service.gtfs.service.persistence.CalendarDatePersistence;

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
 * The persistence implementation for the calendar date service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @generated
 */
@ProviderType
public class CalendarDatePersistenceImpl
	extends BasePersistenceImpl<CalendarDate>
	implements CalendarDatePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CalendarDateUtil</code> to access the calendar date persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CalendarDateImpl.class.getName();

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
	 * Returns all the calendar dates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching calendar dates
	 */
	@Override
	public List<CalendarDate> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar dates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @return the range of matching calendar dates
	 */
	@Override
	public List<CalendarDate> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar dates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar dates
	 */
	@Override
	public List<CalendarDate> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the calendar dates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching calendar dates
	 */
	@Override
	public List<CalendarDate> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator,
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

		List<CalendarDate> list = null;

		if (retrieveFromCache) {
			list = (List<CalendarDate>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CalendarDate calendarDate : list) {
					if (!uuid.equals(calendarDate.getUuid())) {
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

			query.append(_SQL_SELECT_CALENDARDATE_WHERE);

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
				query.append(CalendarDateModelImpl.ORDER_BY_JPQL);
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
					list = (List<CalendarDate>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarDate>)QueryUtil.list(
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
	 * Returns the first calendar date in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar date
	 * @throws NoSuchCalendarDateException if a matching calendar date could not be found
	 */
	@Override
	public CalendarDate findByUuid_First(
			String uuid, OrderByComparator<CalendarDate> orderByComparator)
		throws NoSuchCalendarDateException {

		CalendarDate calendarDate = fetchByUuid_First(uuid, orderByComparator);

		if (calendarDate != null) {
			return calendarDate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCalendarDateException(msg.toString());
	}

	/**
	 * Returns the first calendar date in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar date, or <code>null</code> if a matching calendar date could not be found
	 */
	@Override
	public CalendarDate fetchByUuid_First(
		String uuid, OrderByComparator<CalendarDate> orderByComparator) {

		List<CalendarDate> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar date in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar date
	 * @throws NoSuchCalendarDateException if a matching calendar date could not be found
	 */
	@Override
	public CalendarDate findByUuid_Last(
			String uuid, OrderByComparator<CalendarDate> orderByComparator)
		throws NoSuchCalendarDateException {

		CalendarDate calendarDate = fetchByUuid_Last(uuid, orderByComparator);

		if (calendarDate != null) {
			return calendarDate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCalendarDateException(msg.toString());
	}

	/**
	 * Returns the last calendar date in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar date, or <code>null</code> if a matching calendar date could not be found
	 */
	@Override
	public CalendarDate fetchByUuid_Last(
		String uuid, OrderByComparator<CalendarDate> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CalendarDate> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar dates before and after the current calendar date in the ordered set where uuid = &#63;.
	 *
	 * @param id the primary key of the current calendar date
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar date
	 * @throws NoSuchCalendarDateException if a calendar date with the primary key could not be found
	 */
	@Override
	public CalendarDate[] findByUuid_PrevAndNext(
			long id, String uuid,
			OrderByComparator<CalendarDate> orderByComparator)
		throws NoSuchCalendarDateException {

		uuid = Objects.toString(uuid, "");

		CalendarDate calendarDate = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			CalendarDate[] array = new CalendarDateImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, calendarDate, uuid, orderByComparator, true);

			array[1] = calendarDate;

			array[2] = getByUuid_PrevAndNext(
				session, calendarDate, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarDate getByUuid_PrevAndNext(
		Session session, CalendarDate calendarDate, String uuid,
		OrderByComparator<CalendarDate> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARDATE_WHERE);

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
			query.append(CalendarDateModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(calendarDate)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CalendarDate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar dates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CalendarDate calendarDate :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(calendarDate);
		}
	}

	/**
	 * Returns the number of calendar dates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching calendar dates
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARDATE_WHERE);

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
		"calendarDate.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(calendarDate.uuid IS NULL OR calendarDate.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByServiceId;
	private FinderPath _finderPathWithoutPaginationFindByServiceId;
	private FinderPath _finderPathCountByServiceId;

	/**
	 * Returns all the calendar dates where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @return the matching calendar dates
	 */
	@Override
	public List<CalendarDate> findByServiceId(String service_id) {
		return findByServiceId(
			service_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar dates where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @return the range of matching calendar dates
	 */
	@Override
	public List<CalendarDate> findByServiceId(
		String service_id, int start, int end) {

		return findByServiceId(service_id, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar dates where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar dates
	 */
	@Override
	public List<CalendarDate> findByServiceId(
		String service_id, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator) {

		return findByServiceId(service_id, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the calendar dates where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching calendar dates
	 */
	@Override
	public List<CalendarDate> findByServiceId(
		String service_id, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator,
		boolean retrieveFromCache) {

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

		List<CalendarDate> list = null;

		if (retrieveFromCache) {
			list = (List<CalendarDate>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CalendarDate calendarDate : list) {
					if (!service_id.equals(calendarDate.getService_id())) {
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

			query.append(_SQL_SELECT_CALENDARDATE_WHERE);

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
				query.append(CalendarDateModelImpl.ORDER_BY_JPQL);
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
					list = (List<CalendarDate>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarDate>)QueryUtil.list(
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
	 * Returns the first calendar date in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar date
	 * @throws NoSuchCalendarDateException if a matching calendar date could not be found
	 */
	@Override
	public CalendarDate findByServiceId_First(
			String service_id,
			OrderByComparator<CalendarDate> orderByComparator)
		throws NoSuchCalendarDateException {

		CalendarDate calendarDate = fetchByServiceId_First(
			service_id, orderByComparator);

		if (calendarDate != null) {
			return calendarDate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("service_id=");
		msg.append(service_id);

		msg.append("}");

		throw new NoSuchCalendarDateException(msg.toString());
	}

	/**
	 * Returns the first calendar date in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar date, or <code>null</code> if a matching calendar date could not be found
	 */
	@Override
	public CalendarDate fetchByServiceId_First(
		String service_id, OrderByComparator<CalendarDate> orderByComparator) {

		List<CalendarDate> list = findByServiceId(
			service_id, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar date in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar date
	 * @throws NoSuchCalendarDateException if a matching calendar date could not be found
	 */
	@Override
	public CalendarDate findByServiceId_Last(
			String service_id,
			OrderByComparator<CalendarDate> orderByComparator)
		throws NoSuchCalendarDateException {

		CalendarDate calendarDate = fetchByServiceId_Last(
			service_id, orderByComparator);

		if (calendarDate != null) {
			return calendarDate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("service_id=");
		msg.append(service_id);

		msg.append("}");

		throw new NoSuchCalendarDateException(msg.toString());
	}

	/**
	 * Returns the last calendar date in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar date, or <code>null</code> if a matching calendar date could not be found
	 */
	@Override
	public CalendarDate fetchByServiceId_Last(
		String service_id, OrderByComparator<CalendarDate> orderByComparator) {

		int count = countByServiceId(service_id);

		if (count == 0) {
			return null;
		}

		List<CalendarDate> list = findByServiceId(
			service_id, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar dates before and after the current calendar date in the ordered set where service_id = &#63;.
	 *
	 * @param id the primary key of the current calendar date
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar date
	 * @throws NoSuchCalendarDateException if a calendar date with the primary key could not be found
	 */
	@Override
	public CalendarDate[] findByServiceId_PrevAndNext(
			long id, String service_id,
			OrderByComparator<CalendarDate> orderByComparator)
		throws NoSuchCalendarDateException {

		service_id = Objects.toString(service_id, "");

		CalendarDate calendarDate = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			CalendarDate[] array = new CalendarDateImpl[3];

			array[0] = getByServiceId_PrevAndNext(
				session, calendarDate, service_id, orderByComparator, true);

			array[1] = calendarDate;

			array[2] = getByServiceId_PrevAndNext(
				session, calendarDate, service_id, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarDate getByServiceId_PrevAndNext(
		Session session, CalendarDate calendarDate, String service_id,
		OrderByComparator<CalendarDate> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARDATE_WHERE);

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
			query.append(CalendarDateModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(calendarDate)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CalendarDate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar dates where service_id = &#63; from the database.
	 *
	 * @param service_id the service_id
	 */
	@Override
	public void removeByServiceId(String service_id) {
		for (CalendarDate calendarDate :
				findByServiceId(
					service_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(calendarDate);
		}
	}

	/**
	 * Returns the number of calendar dates where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @return the number of matching calendar dates
	 */
	@Override
	public int countByServiceId(String service_id) {
		service_id = Objects.toString(service_id, "");

		FinderPath finderPath = _finderPathCountByServiceId;

		Object[] finderArgs = new Object[] {service_id};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARDATE_WHERE);

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
		"calendarDate.service_id = ?";

	private static final String _FINDER_COLUMN_SERVICEID_SERVICE_ID_3 =
		"(calendarDate.service_id IS NULL OR calendarDate.service_id = '')";

	private FinderPath _finderPathWithPaginationFindByDate;
	private FinderPath _finderPathWithoutPaginationFindByDate;
	private FinderPath _finderPathCountByDate;

	/**
	 * Returns all the calendar dates where date = &#63;.
	 *
	 * @param date the date
	 * @return the matching calendar dates
	 */
	@Override
	public List<CalendarDate> findByDate(Date date) {
		return findByDate(date, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar dates where date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param date the date
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @return the range of matching calendar dates
	 */
	@Override
	public List<CalendarDate> findByDate(Date date, int start, int end) {
		return findByDate(date, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar dates where date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param date the date
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar dates
	 */
	@Override
	public List<CalendarDate> findByDate(
		Date date, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator) {

		return findByDate(date, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the calendar dates where date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param date the date
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching calendar dates
	 */
	@Override
	public List<CalendarDate> findByDate(
		Date date, int start, int end,
		OrderByComparator<CalendarDate> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByDate;
			finderArgs = new Object[] {_getTime(date)};
		}
		else {
			finderPath = _finderPathWithPaginationFindByDate;
			finderArgs = new Object[] {
				_getTime(date), start, end, orderByComparator
			};
		}

		List<CalendarDate> list = null;

		if (retrieveFromCache) {
			list = (List<CalendarDate>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CalendarDate calendarDate : list) {
					if (!Objects.equals(date, calendarDate.getDate())) {
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

			query.append(_SQL_SELECT_CALENDARDATE_WHERE);

			boolean bindDate = false;

			if (date == null) {
				query.append(_FINDER_COLUMN_DATE_DATE_1);
			}
			else {
				bindDate = true;

				query.append(_FINDER_COLUMN_DATE_DATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CalendarDateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDate) {
					qPos.add(new Timestamp(date.getTime()));
				}

				if (!pagination) {
					list = (List<CalendarDate>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarDate>)QueryUtil.list(
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
	 * Returns the first calendar date in the ordered set where date = &#63;.
	 *
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar date
	 * @throws NoSuchCalendarDateException if a matching calendar date could not be found
	 */
	@Override
	public CalendarDate findByDate_First(
			Date date, OrderByComparator<CalendarDate> orderByComparator)
		throws NoSuchCalendarDateException {

		CalendarDate calendarDate = fetchByDate_First(date, orderByComparator);

		if (calendarDate != null) {
			return calendarDate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("date=");
		msg.append(date);

		msg.append("}");

		throw new NoSuchCalendarDateException(msg.toString());
	}

	/**
	 * Returns the first calendar date in the ordered set where date = &#63;.
	 *
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar date, or <code>null</code> if a matching calendar date could not be found
	 */
	@Override
	public CalendarDate fetchByDate_First(
		Date date, OrderByComparator<CalendarDate> orderByComparator) {

		List<CalendarDate> list = findByDate(date, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar date in the ordered set where date = &#63;.
	 *
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar date
	 * @throws NoSuchCalendarDateException if a matching calendar date could not be found
	 */
	@Override
	public CalendarDate findByDate_Last(
			Date date, OrderByComparator<CalendarDate> orderByComparator)
		throws NoSuchCalendarDateException {

		CalendarDate calendarDate = fetchByDate_Last(date, orderByComparator);

		if (calendarDate != null) {
			return calendarDate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("date=");
		msg.append(date);

		msg.append("}");

		throw new NoSuchCalendarDateException(msg.toString());
	}

	/**
	 * Returns the last calendar date in the ordered set where date = &#63;.
	 *
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar date, or <code>null</code> if a matching calendar date could not be found
	 */
	@Override
	public CalendarDate fetchByDate_Last(
		Date date, OrderByComparator<CalendarDate> orderByComparator) {

		int count = countByDate(date);

		if (count == 0) {
			return null;
		}

		List<CalendarDate> list = findByDate(
			date, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar dates before and after the current calendar date in the ordered set where date = &#63;.
	 *
	 * @param id the primary key of the current calendar date
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar date
	 * @throws NoSuchCalendarDateException if a calendar date with the primary key could not be found
	 */
	@Override
	public CalendarDate[] findByDate_PrevAndNext(
			long id, Date date,
			OrderByComparator<CalendarDate> orderByComparator)
		throws NoSuchCalendarDateException {

		CalendarDate calendarDate = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			CalendarDate[] array = new CalendarDateImpl[3];

			array[0] = getByDate_PrevAndNext(
				session, calendarDate, date, orderByComparator, true);

			array[1] = calendarDate;

			array[2] = getByDate_PrevAndNext(
				session, calendarDate, date, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarDate getByDate_PrevAndNext(
		Session session, CalendarDate calendarDate, Date date,
		OrderByComparator<CalendarDate> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARDATE_WHERE);

		boolean bindDate = false;

		if (date == null) {
			query.append(_FINDER_COLUMN_DATE_DATE_1);
		}
		else {
			bindDate = true;

			query.append(_FINDER_COLUMN_DATE_DATE_2);
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
			query.append(CalendarDateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindDate) {
			qPos.add(new Timestamp(date.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(calendarDate)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CalendarDate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar dates where date = &#63; from the database.
	 *
	 * @param date the date
	 */
	@Override
	public void removeByDate(Date date) {
		for (CalendarDate calendarDate :
				findByDate(date, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(calendarDate);
		}
	}

	/**
	 * Returns the number of calendar dates where date = &#63;.
	 *
	 * @param date the date
	 * @return the number of matching calendar dates
	 */
	@Override
	public int countByDate(Date date) {
		FinderPath finderPath = _finderPathCountByDate;

		Object[] finderArgs = new Object[] {_getTime(date)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARDATE_WHERE);

			boolean bindDate = false;

			if (date == null) {
				query.append(_FINDER_COLUMN_DATE_DATE_1);
			}
			else {
				bindDate = true;

				query.append(_FINDER_COLUMN_DATE_DATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDate) {
					qPos.add(new Timestamp(date.getTime()));
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

	private static final String _FINDER_COLUMN_DATE_DATE_1 =
		"calendarDate.date IS NULL";

	private static final String _FINDER_COLUMN_DATE_DATE_2 =
		"calendarDate.date = ?";

	public CalendarDatePersistenceImpl() {
		setModelClass(CalendarDate.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("id", "id_");
		dbColumnNames.put("date", "date_");

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
	 * Caches the calendar date in the entity cache if it is enabled.
	 *
	 * @param calendarDate the calendar date
	 */
	@Override
	public void cacheResult(CalendarDate calendarDate) {
		entityCache.putResult(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED, CalendarDateImpl.class,
			calendarDate.getPrimaryKey(), calendarDate);

		calendarDate.resetOriginalValues();
	}

	/**
	 * Caches the calendar dates in the entity cache if it is enabled.
	 *
	 * @param calendarDates the calendar dates
	 */
	@Override
	public void cacheResult(List<CalendarDate> calendarDates) {
		for (CalendarDate calendarDate : calendarDates) {
			if (entityCache.getResult(
					CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
					CalendarDateImpl.class, calendarDate.getPrimaryKey()) ==
						null) {

				cacheResult(calendarDate);
			}
			else {
				calendarDate.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all calendar dates.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CalendarDateImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the calendar date.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CalendarDate calendarDate) {
		entityCache.removeResult(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED, CalendarDateImpl.class,
			calendarDate.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CalendarDate> calendarDates) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CalendarDate calendarDate : calendarDates) {
			entityCache.removeResult(
				CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
				CalendarDateImpl.class, calendarDate.getPrimaryKey());
		}
	}

	/**
	 * Creates a new calendar date with the primary key. Does not add the calendar date to the database.
	 *
	 * @param id the primary key for the new calendar date
	 * @return the new calendar date
	 */
	@Override
	public CalendarDate create(long id) {
		CalendarDate calendarDate = new CalendarDateImpl();

		calendarDate.setNew(true);
		calendarDate.setPrimaryKey(id);

		String uuid = PortalUUIDUtil.generate();

		calendarDate.setUuid(uuid);

		return calendarDate;
	}

	/**
	 * Removes the calendar date with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the calendar date
	 * @return the calendar date that was removed
	 * @throws NoSuchCalendarDateException if a calendar date with the primary key could not be found
	 */
	@Override
	public CalendarDate remove(long id) throws NoSuchCalendarDateException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the calendar date with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the calendar date
	 * @return the calendar date that was removed
	 * @throws NoSuchCalendarDateException if a calendar date with the primary key could not be found
	 */
	@Override
	public CalendarDate remove(Serializable primaryKey)
		throws NoSuchCalendarDateException {

		Session session = null;

		try {
			session = openSession();

			CalendarDate calendarDate = (CalendarDate)session.get(
				CalendarDateImpl.class, primaryKey);

			if (calendarDate == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCalendarDateException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(calendarDate);
		}
		catch (NoSuchCalendarDateException nsee) {
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
	protected CalendarDate removeImpl(CalendarDate calendarDate) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(calendarDate)) {
				calendarDate = (CalendarDate)session.get(
					CalendarDateImpl.class, calendarDate.getPrimaryKeyObj());
			}

			if (calendarDate != null) {
				session.delete(calendarDate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (calendarDate != null) {
			clearCache(calendarDate);
		}

		return calendarDate;
	}

	@Override
	public CalendarDate updateImpl(CalendarDate calendarDate) {
		boolean isNew = calendarDate.isNew();

		if (!(calendarDate instanceof CalendarDateModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(calendarDate.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					calendarDate);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in calendarDate proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CalendarDate implementation " +
					calendarDate.getClass());
		}

		CalendarDateModelImpl calendarDateModelImpl =
			(CalendarDateModelImpl)calendarDate;

		if (Validator.isNull(calendarDate.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			calendarDate.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (calendarDate.isNew()) {
				session.save(calendarDate);

				calendarDate.setNew(false);
			}
			else {
				calendarDate = (CalendarDate)session.merge(calendarDate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CalendarDateModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {calendarDateModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {calendarDateModelImpl.getService_id()};

			finderCache.removeResult(_finderPathCountByServiceId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByServiceId, args);

			args = new Object[] {calendarDateModelImpl.getDate()};

			finderCache.removeResult(_finderPathCountByDate, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByDate, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((calendarDateModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					calendarDateModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {calendarDateModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((calendarDateModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByServiceId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					calendarDateModelImpl.getOriginalService_id()
				};

				finderCache.removeResult(_finderPathCountByServiceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByServiceId, args);

				args = new Object[] {calendarDateModelImpl.getService_id()};

				finderCache.removeResult(_finderPathCountByServiceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByServiceId, args);
			}

			if ((calendarDateModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByDate.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					calendarDateModelImpl.getOriginalDate()
				};

				finderCache.removeResult(_finderPathCountByDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByDate, args);

				args = new Object[] {calendarDateModelImpl.getDate()};

				finderCache.removeResult(_finderPathCountByDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByDate, args);
			}
		}

		entityCache.putResult(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED, CalendarDateImpl.class,
			calendarDate.getPrimaryKey(), calendarDate, false);

		calendarDate.resetOriginalValues();

		return calendarDate;
	}

	/**
	 * Returns the calendar date with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar date
	 * @return the calendar date
	 * @throws NoSuchCalendarDateException if a calendar date with the primary key could not be found
	 */
	@Override
	public CalendarDate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCalendarDateException {

		CalendarDate calendarDate = fetchByPrimaryKey(primaryKey);

		if (calendarDate == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCalendarDateException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return calendarDate;
	}

	/**
	 * Returns the calendar date with the primary key or throws a <code>NoSuchCalendarDateException</code> if it could not be found.
	 *
	 * @param id the primary key of the calendar date
	 * @return the calendar date
	 * @throws NoSuchCalendarDateException if a calendar date with the primary key could not be found
	 */
	@Override
	public CalendarDate findByPrimaryKey(long id)
		throws NoSuchCalendarDateException {

		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the calendar date with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar date
	 * @return the calendar date, or <code>null</code> if a calendar date with the primary key could not be found
	 */
	@Override
	public CalendarDate fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED, CalendarDateImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CalendarDate calendarDate = (CalendarDate)serializable;

		if (calendarDate == null) {
			Session session = null;

			try {
				session = openSession();

				calendarDate = (CalendarDate)session.get(
					CalendarDateImpl.class, primaryKey);

				if (calendarDate != null) {
					cacheResult(calendarDate);
				}
				else {
					entityCache.putResult(
						CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
						CalendarDateImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
					CalendarDateImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return calendarDate;
	}

	/**
	 * Returns the calendar date with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the calendar date
	 * @return the calendar date, or <code>null</code> if a calendar date with the primary key could not be found
	 */
	@Override
	public CalendarDate fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, CalendarDate> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CalendarDate> map =
			new HashMap<Serializable, CalendarDate>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CalendarDate calendarDate = fetchByPrimaryKey(primaryKey);

			if (calendarDate != null) {
				map.put(primaryKey, calendarDate);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
				CalendarDateImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CalendarDate)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_CALENDARDATE_WHERE_PKS_IN);

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

			for (CalendarDate calendarDate : (List<CalendarDate>)q.list()) {
				map.put(calendarDate.getPrimaryKeyObj(), calendarDate);

				cacheResult(calendarDate);

				uncachedPrimaryKeys.remove(calendarDate.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
					CalendarDateImpl.class, primaryKey, nullModel);
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
	 * Returns all the calendar dates.
	 *
	 * @return the calendar dates
	 */
	@Override
	public List<CalendarDate> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar dates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @return the range of calendar dates
	 */
	@Override
	public List<CalendarDate> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar dates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calendar dates
	 */
	@Override
	public List<CalendarDate> findAll(
		int start, int end, OrderByComparator<CalendarDate> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the calendar dates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarDateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar dates
	 * @param end the upper bound of the range of calendar dates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of calendar dates
	 */
	@Override
	public List<CalendarDate> findAll(
		int start, int end, OrderByComparator<CalendarDate> orderByComparator,
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

		List<CalendarDate> list = null;

		if (retrieveFromCache) {
			list = (List<CalendarDate>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CALENDARDATE);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CALENDARDATE;

				if (pagination) {
					sql = sql.concat(CalendarDateModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CalendarDate>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarDate>)QueryUtil.list(
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
	 * Removes all the calendar dates from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CalendarDate calendarDate : findAll()) {
			remove(calendarDate);
		}
	}

	/**
	 * Returns the number of calendar dates.
	 *
	 * @return the number of calendar dates
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CALENDARDATE);

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
		return CalendarDateModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the calendar date persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarDateModelImpl.FINDER_CACHE_ENABLED, CalendarDateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarDateModelImpl.FINDER_CACHE_ENABLED, CalendarDateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarDateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarDateModelImpl.FINDER_CACHE_ENABLED, CalendarDateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarDateModelImpl.FINDER_CACHE_ENABLED, CalendarDateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CalendarDateModelImpl.UUID_COLUMN_BITMASK |
			CalendarDateModelImpl.SERVICE_ID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarDateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByServiceId = new FinderPath(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarDateModelImpl.FINDER_CACHE_ENABLED, CalendarDateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByServiceId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByServiceId = new FinderPath(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarDateModelImpl.FINDER_CACHE_ENABLED, CalendarDateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByServiceId",
			new String[] {String.class.getName()},
			CalendarDateModelImpl.SERVICE_ID_COLUMN_BITMASK);

		_finderPathCountByServiceId = new FinderPath(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarDateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByServiceId",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByDate = new FinderPath(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarDateModelImpl.FINDER_CACHE_ENABLED, CalendarDateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDate",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByDate = new FinderPath(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarDateModelImpl.FINDER_CACHE_ENABLED, CalendarDateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDate",
			new String[] {Date.class.getName()},
			CalendarDateModelImpl.DATE_COLUMN_BITMASK |
			CalendarDateModelImpl.SERVICE_ID_COLUMN_BITMASK);

		_finderPathCountByDate = new FinderPath(
			CalendarDateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarDateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDate",
			new String[] {Date.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CalendarDateImpl.class.getName());
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

	private static final String _SQL_SELECT_CALENDARDATE =
		"SELECT calendarDate FROM CalendarDate calendarDate";

	private static final String _SQL_SELECT_CALENDARDATE_WHERE_PKS_IN =
		"SELECT calendarDate FROM CalendarDate calendarDate WHERE id_ IN (";

	private static final String _SQL_SELECT_CALENDARDATE_WHERE =
		"SELECT calendarDate FROM CalendarDate calendarDate WHERE ";

	private static final String _SQL_COUNT_CALENDARDATE =
		"SELECT COUNT(calendarDate) FROM CalendarDate calendarDate";

	private static final String _SQL_COUNT_CALENDARDATE_WHERE =
		"SELECT COUNT(calendarDate) FROM CalendarDate calendarDate WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "calendarDate.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CalendarDate exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CalendarDate exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CalendarDatePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "id", "date"});

}