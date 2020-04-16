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

import eu.strasbourg.service.gtfs.exception.NoSuchCalendarException;
import eu.strasbourg.service.gtfs.model.Calendar;
import eu.strasbourg.service.gtfs.model.impl.CalendarImpl;
import eu.strasbourg.service.gtfs.model.impl.CalendarModelImpl;
import eu.strasbourg.service.gtfs.service.persistence.CalendarPersistence;

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
 * The persistence implementation for the calendar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @generated
 */
@ProviderType
public class CalendarPersistenceImpl
	extends BasePersistenceImpl<Calendar> implements CalendarPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CalendarUtil</code> to access the calendar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CalendarImpl.class.getName();

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
	 * Returns all the calendars where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching calendars
	 */
	@Override
	public List<Calendar> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 */
	@Override
	public List<Calendar> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 */
	@Override
	public List<Calendar> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the calendars where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching calendars
	 */
	@Override
	public List<Calendar> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Calendar> orderByComparator,
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

		List<Calendar> list = null;

		if (retrieveFromCache) {
			list = (List<Calendar>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Calendar calendar : list) {
					if (!uuid.equals(calendar.getUuid())) {
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

			query.append(_SQL_SELECT_CALENDAR_WHERE);

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
				query.append(CalendarModelImpl.ORDER_BY_JPQL);
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
					list = (List<Calendar>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Calendar>)QueryUtil.list(
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
	 * Returns the first calendar in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByUuid_First(
			String uuid, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {

		Calendar calendar = fetchByUuid_First(uuid, orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the first calendar in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByUuid_First(
		String uuid, OrderByComparator<Calendar> orderByComparator) {

		List<Calendar> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByUuid_Last(
			String uuid, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {

		Calendar calendar = fetchByUuid_Last(uuid, orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the last calendar in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByUuid_Last(
		String uuid, OrderByComparator<Calendar> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Calendar> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where uuid = &#63;.
	 *
	 * @param id the primary key of the current calendar
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar[] findByUuid_PrevAndNext(
			long id, String uuid, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {

		uuid = Objects.toString(uuid, "");

		Calendar calendar = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Calendar[] array = new CalendarImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, calendar, uuid, orderByComparator, true);

			array[1] = calendar;

			array[2] = getByUuid_PrevAndNext(
				session, calendar, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Calendar getByUuid_PrevAndNext(
		Session session, Calendar calendar, String uuid,
		OrderByComparator<Calendar> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDAR_WHERE);

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
			query.append(CalendarModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(calendar)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Calendar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendars where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Calendar calendar :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(calendar);
		}
	}

	/**
	 * Returns the number of calendars where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching calendars
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDAR_WHERE);

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
		"calendar.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(calendar.uuid IS NULL OR calendar.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByServiceId;
	private FinderPath _finderPathWithoutPaginationFindByServiceId;
	private FinderPath _finderPathCountByServiceId;

	/**
	 * Returns all the calendars where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @return the matching calendars
	 */
	@Override
	public List<Calendar> findByServiceId(String service_id) {
		return findByServiceId(
			service_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 */
	@Override
	public List<Calendar> findByServiceId(
		String service_id, int start, int end) {

		return findByServiceId(service_id, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 */
	@Override
	public List<Calendar> findByServiceId(
		String service_id, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {

		return findByServiceId(service_id, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the calendars where service_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param service_id the service_id
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching calendars
	 */
	@Override
	public List<Calendar> findByServiceId(
		String service_id, int start, int end,
		OrderByComparator<Calendar> orderByComparator,
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

		List<Calendar> list = null;

		if (retrieveFromCache) {
			list = (List<Calendar>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Calendar calendar : list) {
					if (!service_id.equals(calendar.getService_id())) {
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

			query.append(_SQL_SELECT_CALENDAR_WHERE);

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
				query.append(CalendarModelImpl.ORDER_BY_JPQL);
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
					list = (List<Calendar>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Calendar>)QueryUtil.list(
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
	 * Returns the first calendar in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByServiceId_First(
			String service_id, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {

		Calendar calendar = fetchByServiceId_First(
			service_id, orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("service_id=");
		msg.append(service_id);

		msg.append("}");

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the first calendar in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByServiceId_First(
		String service_id, OrderByComparator<Calendar> orderByComparator) {

		List<Calendar> list = findByServiceId(
			service_id, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByServiceId_Last(
			String service_id, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {

		Calendar calendar = fetchByServiceId_Last(
			service_id, orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("service_id=");
		msg.append(service_id);

		msg.append("}");

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the last calendar in the ordered set where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByServiceId_Last(
		String service_id, OrderByComparator<Calendar> orderByComparator) {

		int count = countByServiceId(service_id);

		if (count == 0) {
			return null;
		}

		List<Calendar> list = findByServiceId(
			service_id, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where service_id = &#63;.
	 *
	 * @param id the primary key of the current calendar
	 * @param service_id the service_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar[] findByServiceId_PrevAndNext(
			long id, String service_id,
			OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {

		service_id = Objects.toString(service_id, "");

		Calendar calendar = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Calendar[] array = new CalendarImpl[3];

			array[0] = getByServiceId_PrevAndNext(
				session, calendar, service_id, orderByComparator, true);

			array[1] = calendar;

			array[2] = getByServiceId_PrevAndNext(
				session, calendar, service_id, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Calendar getByServiceId_PrevAndNext(
		Session session, Calendar calendar, String service_id,
		OrderByComparator<Calendar> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDAR_WHERE);

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
			query.append(CalendarModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(calendar)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Calendar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendars where service_id = &#63; from the database.
	 *
	 * @param service_id the service_id
	 */
	@Override
	public void removeByServiceId(String service_id) {
		for (Calendar calendar :
				findByServiceId(
					service_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(calendar);
		}
	}

	/**
	 * Returns the number of calendars where service_id = &#63;.
	 *
	 * @param service_id the service_id
	 * @return the number of matching calendars
	 */
	@Override
	public int countByServiceId(String service_id) {
		service_id = Objects.toString(service_id, "");

		FinderPath finderPath = _finderPathCountByServiceId;

		Object[] finderArgs = new Object[] {service_id};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDAR_WHERE);

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
		"calendar.service_id = ?";

	private static final String _FINDER_COLUMN_SERVICEID_SERVICE_ID_3 =
		"(calendar.service_id IS NULL OR calendar.service_id = '')";

	private FinderPath _finderPathWithPaginationFindByStartDate;
	private FinderPath _finderPathWithoutPaginationFindByStartDate;
	private FinderPath _finderPathCountByStartDate;

	/**
	 * Returns all the calendars where start_date = &#63;.
	 *
	 * @param start_date the start_date
	 * @return the matching calendars
	 */
	@Override
	public List<Calendar> findByStartDate(Date start_date) {
		return findByStartDate(
			start_date, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars where start_date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start_date the start_date
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 */
	@Override
	public List<Calendar> findByStartDate(Date start_date, int start, int end) {
		return findByStartDate(start_date, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars where start_date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start_date the start_date
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 */
	@Override
	public List<Calendar> findByStartDate(
		Date start_date, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {

		return findByStartDate(start_date, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the calendars where start_date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start_date the start_date
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching calendars
	 */
	@Override
	public List<Calendar> findByStartDate(
		Date start_date, int start, int end,
		OrderByComparator<Calendar> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByStartDate;
			finderArgs = new Object[] {_getTime(start_date)};
		}
		else {
			finderPath = _finderPathWithPaginationFindByStartDate;
			finderArgs = new Object[] {
				_getTime(start_date), start, end, orderByComparator
			};
		}

		List<Calendar> list = null;

		if (retrieveFromCache) {
			list = (List<Calendar>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Calendar calendar : list) {
					if (!Objects.equals(start_date, calendar.getStart_date())) {
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

			query.append(_SQL_SELECT_CALENDAR_WHERE);

			boolean bindStart_date = false;

			if (start_date == null) {
				query.append(_FINDER_COLUMN_STARTDATE_START_DATE_1);
			}
			else {
				bindStart_date = true;

				query.append(_FINDER_COLUMN_STARTDATE_START_DATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CalendarModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStart_date) {
					qPos.add(new Timestamp(start_date.getTime()));
				}

				if (!pagination) {
					list = (List<Calendar>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Calendar>)QueryUtil.list(
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
	 * Returns the first calendar in the ordered set where start_date = &#63;.
	 *
	 * @param start_date the start_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByStartDate_First(
			Date start_date, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {

		Calendar calendar = fetchByStartDate_First(
			start_date, orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("start_date=");
		msg.append(start_date);

		msg.append("}");

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the first calendar in the ordered set where start_date = &#63;.
	 *
	 * @param start_date the start_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByStartDate_First(
		Date start_date, OrderByComparator<Calendar> orderByComparator) {

		List<Calendar> list = findByStartDate(
			start_date, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar in the ordered set where start_date = &#63;.
	 *
	 * @param start_date the start_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByStartDate_Last(
			Date start_date, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {

		Calendar calendar = fetchByStartDate_Last(
			start_date, orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("start_date=");
		msg.append(start_date);

		msg.append("}");

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the last calendar in the ordered set where start_date = &#63;.
	 *
	 * @param start_date the start_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByStartDate_Last(
		Date start_date, OrderByComparator<Calendar> orderByComparator) {

		int count = countByStartDate(start_date);

		if (count == 0) {
			return null;
		}

		List<Calendar> list = findByStartDate(
			start_date, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where start_date = &#63;.
	 *
	 * @param id the primary key of the current calendar
	 * @param start_date the start_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar[] findByStartDate_PrevAndNext(
			long id, Date start_date,
			OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {

		Calendar calendar = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Calendar[] array = new CalendarImpl[3];

			array[0] = getByStartDate_PrevAndNext(
				session, calendar, start_date, orderByComparator, true);

			array[1] = calendar;

			array[2] = getByStartDate_PrevAndNext(
				session, calendar, start_date, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Calendar getByStartDate_PrevAndNext(
		Session session, Calendar calendar, Date start_date,
		OrderByComparator<Calendar> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDAR_WHERE);

		boolean bindStart_date = false;

		if (start_date == null) {
			query.append(_FINDER_COLUMN_STARTDATE_START_DATE_1);
		}
		else {
			bindStart_date = true;

			query.append(_FINDER_COLUMN_STARTDATE_START_DATE_2);
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
			query.append(CalendarModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindStart_date) {
			qPos.add(new Timestamp(start_date.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(calendar)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Calendar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendars where start_date = &#63; from the database.
	 *
	 * @param start_date the start_date
	 */
	@Override
	public void removeByStartDate(Date start_date) {
		for (Calendar calendar :
				findByStartDate(
					start_date, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(calendar);
		}
	}

	/**
	 * Returns the number of calendars where start_date = &#63;.
	 *
	 * @param start_date the start_date
	 * @return the number of matching calendars
	 */
	@Override
	public int countByStartDate(Date start_date) {
		FinderPath finderPath = _finderPathCountByStartDate;

		Object[] finderArgs = new Object[] {_getTime(start_date)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDAR_WHERE);

			boolean bindStart_date = false;

			if (start_date == null) {
				query.append(_FINDER_COLUMN_STARTDATE_START_DATE_1);
			}
			else {
				bindStart_date = true;

				query.append(_FINDER_COLUMN_STARTDATE_START_DATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStart_date) {
					qPos.add(new Timestamp(start_date.getTime()));
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

	private static final String _FINDER_COLUMN_STARTDATE_START_DATE_1 =
		"calendar.start_date IS NULL";

	private static final String _FINDER_COLUMN_STARTDATE_START_DATE_2 =
		"calendar.start_date = ?";

	private FinderPath _finderPathWithPaginationFindByEndDate;
	private FinderPath _finderPathWithoutPaginationFindByEndDate;
	private FinderPath _finderPathCountByEndDate;

	/**
	 * Returns all the calendars where end_date = &#63;.
	 *
	 * @param end_date the end_date
	 * @return the matching calendars
	 */
	@Override
	public List<Calendar> findByEndDate(Date end_date) {
		return findByEndDate(
			end_date, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars where end_date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param end_date the end_date
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 */
	@Override
	public List<Calendar> findByEndDate(Date end_date, int start, int end) {
		return findByEndDate(end_date, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars where end_date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param end_date the end_date
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 */
	@Override
	public List<Calendar> findByEndDate(
		Date end_date, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {

		return findByEndDate(end_date, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the calendars where end_date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param end_date the end_date
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching calendars
	 */
	@Override
	public List<Calendar> findByEndDate(
		Date end_date, int start, int end,
		OrderByComparator<Calendar> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByEndDate;
			finderArgs = new Object[] {_getTime(end_date)};
		}
		else {
			finderPath = _finderPathWithPaginationFindByEndDate;
			finderArgs = new Object[] {
				_getTime(end_date), start, end, orderByComparator
			};
		}

		List<Calendar> list = null;

		if (retrieveFromCache) {
			list = (List<Calendar>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Calendar calendar : list) {
					if (!Objects.equals(end_date, calendar.getEnd_date())) {
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

			query.append(_SQL_SELECT_CALENDAR_WHERE);

			boolean bindEnd_date = false;

			if (end_date == null) {
				query.append(_FINDER_COLUMN_ENDDATE_END_DATE_1);
			}
			else {
				bindEnd_date = true;

				query.append(_FINDER_COLUMN_ENDDATE_END_DATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CalendarModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEnd_date) {
					qPos.add(new Timestamp(end_date.getTime()));
				}

				if (!pagination) {
					list = (List<Calendar>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Calendar>)QueryUtil.list(
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
	 * Returns the first calendar in the ordered set where end_date = &#63;.
	 *
	 * @param end_date the end_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByEndDate_First(
			Date end_date, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {

		Calendar calendar = fetchByEndDate_First(end_date, orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("end_date=");
		msg.append(end_date);

		msg.append("}");

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the first calendar in the ordered set where end_date = &#63;.
	 *
	 * @param end_date the end_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByEndDate_First(
		Date end_date, OrderByComparator<Calendar> orderByComparator) {

		List<Calendar> list = findByEndDate(end_date, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar in the ordered set where end_date = &#63;.
	 *
	 * @param end_date the end_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByEndDate_Last(
			Date end_date, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {

		Calendar calendar = fetchByEndDate_Last(end_date, orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("end_date=");
		msg.append(end_date);

		msg.append("}");

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the last calendar in the ordered set where end_date = &#63;.
	 *
	 * @param end_date the end_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByEndDate_Last(
		Date end_date, OrderByComparator<Calendar> orderByComparator) {

		int count = countByEndDate(end_date);

		if (count == 0) {
			return null;
		}

		List<Calendar> list = findByEndDate(
			end_date, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where end_date = &#63;.
	 *
	 * @param id the primary key of the current calendar
	 * @param end_date the end_date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar[] findByEndDate_PrevAndNext(
			long id, Date end_date,
			OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {

		Calendar calendar = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Calendar[] array = new CalendarImpl[3];

			array[0] = getByEndDate_PrevAndNext(
				session, calendar, end_date, orderByComparator, true);

			array[1] = calendar;

			array[2] = getByEndDate_PrevAndNext(
				session, calendar, end_date, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Calendar getByEndDate_PrevAndNext(
		Session session, Calendar calendar, Date end_date,
		OrderByComparator<Calendar> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDAR_WHERE);

		boolean bindEnd_date = false;

		if (end_date == null) {
			query.append(_FINDER_COLUMN_ENDDATE_END_DATE_1);
		}
		else {
			bindEnd_date = true;

			query.append(_FINDER_COLUMN_ENDDATE_END_DATE_2);
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
			query.append(CalendarModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindEnd_date) {
			qPos.add(new Timestamp(end_date.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(calendar)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Calendar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendars where end_date = &#63; from the database.
	 *
	 * @param end_date the end_date
	 */
	@Override
	public void removeByEndDate(Date end_date) {
		for (Calendar calendar :
				findByEndDate(
					end_date, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(calendar);
		}
	}

	/**
	 * Returns the number of calendars where end_date = &#63;.
	 *
	 * @param end_date the end_date
	 * @return the number of matching calendars
	 */
	@Override
	public int countByEndDate(Date end_date) {
		FinderPath finderPath = _finderPathCountByEndDate;

		Object[] finderArgs = new Object[] {_getTime(end_date)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDAR_WHERE);

			boolean bindEnd_date = false;

			if (end_date == null) {
				query.append(_FINDER_COLUMN_ENDDATE_END_DATE_1);
			}
			else {
				bindEnd_date = true;

				query.append(_FINDER_COLUMN_ENDDATE_END_DATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEnd_date) {
					qPos.add(new Timestamp(end_date.getTime()));
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

	private static final String _FINDER_COLUMN_ENDDATE_END_DATE_1 =
		"calendar.end_date IS NULL";

	private static final String _FINDER_COLUMN_ENDDATE_END_DATE_2 =
		"calendar.end_date = ?";

	public CalendarPersistenceImpl() {
		setModelClass(Calendar.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
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
	 * Caches the calendar in the entity cache if it is enabled.
	 *
	 * @param calendar the calendar
	 */
	@Override
	public void cacheResult(Calendar calendar) {
		entityCache.putResult(
			CalendarModelImpl.ENTITY_CACHE_ENABLED, CalendarImpl.class,
			calendar.getPrimaryKey(), calendar);

		calendar.resetOriginalValues();
	}

	/**
	 * Caches the calendars in the entity cache if it is enabled.
	 *
	 * @param calendars the calendars
	 */
	@Override
	public void cacheResult(List<Calendar> calendars) {
		for (Calendar calendar : calendars) {
			if (entityCache.getResult(
					CalendarModelImpl.ENTITY_CACHE_ENABLED, CalendarImpl.class,
					calendar.getPrimaryKey()) == null) {

				cacheResult(calendar);
			}
			else {
				calendar.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all calendars.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CalendarImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the calendar.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Calendar calendar) {
		entityCache.removeResult(
			CalendarModelImpl.ENTITY_CACHE_ENABLED, CalendarImpl.class,
			calendar.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Calendar> calendars) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Calendar calendar : calendars) {
			entityCache.removeResult(
				CalendarModelImpl.ENTITY_CACHE_ENABLED, CalendarImpl.class,
				calendar.getPrimaryKey());
		}
	}

	/**
	 * Creates a new calendar with the primary key. Does not add the calendar to the database.
	 *
	 * @param id the primary key for the new calendar
	 * @return the new calendar
	 */
	@Override
	public Calendar create(long id) {
		Calendar calendar = new CalendarImpl();

		calendar.setNew(true);
		calendar.setPrimaryKey(id);

		String uuid = PortalUUIDUtil.generate();

		calendar.setUuid(uuid);

		return calendar;
	}

	/**
	 * Removes the calendar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the calendar
	 * @return the calendar that was removed
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar remove(long id) throws NoSuchCalendarException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the calendar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the calendar
	 * @return the calendar that was removed
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar remove(Serializable primaryKey)
		throws NoSuchCalendarException {

		Session session = null;

		try {
			session = openSession();

			Calendar calendar = (Calendar)session.get(
				CalendarImpl.class, primaryKey);

			if (calendar == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCalendarException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(calendar);
		}
		catch (NoSuchCalendarException nsee) {
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
	protected Calendar removeImpl(Calendar calendar) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(calendar)) {
				calendar = (Calendar)session.get(
					CalendarImpl.class, calendar.getPrimaryKeyObj());
			}

			if (calendar != null) {
				session.delete(calendar);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (calendar != null) {
			clearCache(calendar);
		}

		return calendar;
	}

	@Override
	public Calendar updateImpl(Calendar calendar) {
		boolean isNew = calendar.isNew();

		if (!(calendar instanceof CalendarModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(calendar.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(calendar);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in calendar proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Calendar implementation " +
					calendar.getClass());
		}

		CalendarModelImpl calendarModelImpl = (CalendarModelImpl)calendar;

		if (Validator.isNull(calendar.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			calendar.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (calendar.isNew()) {
				session.save(calendar);

				calendar.setNew(false);
			}
			else {
				calendar = (Calendar)session.merge(calendar);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CalendarModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {calendarModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {calendarModelImpl.getService_id()};

			finderCache.removeResult(_finderPathCountByServiceId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByServiceId, args);

			args = new Object[] {calendarModelImpl.getStart_date()};

			finderCache.removeResult(_finderPathCountByStartDate, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByStartDate, args);

			args = new Object[] {calendarModelImpl.getEnd_date()};

			finderCache.removeResult(_finderPathCountByEndDate, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByEndDate, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((calendarModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					calendarModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {calendarModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((calendarModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByServiceId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					calendarModelImpl.getOriginalService_id()
				};

				finderCache.removeResult(_finderPathCountByServiceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByServiceId, args);

				args = new Object[] {calendarModelImpl.getService_id()};

				finderCache.removeResult(_finderPathCountByServiceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByServiceId, args);
			}

			if ((calendarModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByStartDate.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					calendarModelImpl.getOriginalStart_date()
				};

				finderCache.removeResult(_finderPathCountByStartDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByStartDate, args);

				args = new Object[] {calendarModelImpl.getStart_date()};

				finderCache.removeResult(_finderPathCountByStartDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByStartDate, args);
			}

			if ((calendarModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByEndDate.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					calendarModelImpl.getOriginalEnd_date()
				};

				finderCache.removeResult(_finderPathCountByEndDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByEndDate, args);

				args = new Object[] {calendarModelImpl.getEnd_date()};

				finderCache.removeResult(_finderPathCountByEndDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByEndDate, args);
			}
		}

		entityCache.putResult(
			CalendarModelImpl.ENTITY_CACHE_ENABLED, CalendarImpl.class,
			calendar.getPrimaryKey(), calendar, false);

		calendar.resetOriginalValues();

		return calendar;
	}

	/**
	 * Returns the calendar with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar
	 * @return the calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCalendarException {

		Calendar calendar = fetchByPrimaryKey(primaryKey);

		if (calendar == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCalendarException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return calendar;
	}

	/**
	 * Returns the calendar with the primary key or throws a <code>NoSuchCalendarException</code> if it could not be found.
	 *
	 * @param id the primary key of the calendar
	 * @return the calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar findByPrimaryKey(long id) throws NoSuchCalendarException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the calendar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar
	 * @return the calendar, or <code>null</code> if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CalendarModelImpl.ENTITY_CACHE_ENABLED, CalendarImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Calendar calendar = (Calendar)serializable;

		if (calendar == null) {
			Session session = null;

			try {
				session = openSession();

				calendar = (Calendar)session.get(
					CalendarImpl.class, primaryKey);

				if (calendar != null) {
					cacheResult(calendar);
				}
				else {
					entityCache.putResult(
						CalendarModelImpl.ENTITY_CACHE_ENABLED,
						CalendarImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CalendarModelImpl.ENTITY_CACHE_ENABLED, CalendarImpl.class,
					primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return calendar;
	}

	/**
	 * Returns the calendar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the calendar
	 * @return the calendar, or <code>null</code> if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, Calendar> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Calendar> map = new HashMap<Serializable, Calendar>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Calendar calendar = fetchByPrimaryKey(primaryKey);

			if (calendar != null) {
				map.put(primaryKey, calendar);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CalendarModelImpl.ENTITY_CACHE_ENABLED, CalendarImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Calendar)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_CALENDAR_WHERE_PKS_IN);

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

			for (Calendar calendar : (List<Calendar>)q.list()) {
				map.put(calendar.getPrimaryKeyObj(), calendar);

				cacheResult(calendar);

				uncachedPrimaryKeys.remove(calendar.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CalendarModelImpl.ENTITY_CACHE_ENABLED, CalendarImpl.class,
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
	 * Returns all the calendars.
	 *
	 * @return the calendars
	 */
	@Override
	public List<Calendar> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of calendars
	 */
	@Override
	public List<Calendar> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calendars
	 */
	@Override
	public List<Calendar> findAll(
		int start, int end, OrderByComparator<Calendar> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CalendarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of calendars
	 */
	@Override
	public List<Calendar> findAll(
		int start, int end, OrderByComparator<Calendar> orderByComparator,
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

		List<Calendar> list = null;

		if (retrieveFromCache) {
			list = (List<Calendar>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CALENDAR);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CALENDAR;

				if (pagination) {
					sql = sql.concat(CalendarModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Calendar>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Calendar>)QueryUtil.list(
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
	 * Removes all the calendars from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Calendar calendar : findAll()) {
			remove(calendar);
		}
	}

	/**
	 * Returns the number of calendars.
	 *
	 * @return the number of calendars
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CALENDAR);

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
		return CalendarModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the calendar persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CalendarModelImpl.UUID_COLUMN_BITMASK |
			CalendarModelImpl.SERVICE_ID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByServiceId = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByServiceId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByServiceId = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByServiceId",
			new String[] {String.class.getName()},
			CalendarModelImpl.SERVICE_ID_COLUMN_BITMASK);

		_finderPathCountByServiceId = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByServiceId",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByStartDate = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStartDate",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByStartDate = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStartDate",
			new String[] {Date.class.getName()},
			CalendarModelImpl.START_DATE_COLUMN_BITMASK |
			CalendarModelImpl.SERVICE_ID_COLUMN_BITMASK);

		_finderPathCountByStartDate = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStartDate",
			new String[] {Date.class.getName()});

		_finderPathWithPaginationFindByEndDate = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEndDate",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByEndDate = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEndDate",
			new String[] {Date.class.getName()},
			CalendarModelImpl.END_DATE_COLUMN_BITMASK |
			CalendarModelImpl.SERVICE_ID_COLUMN_BITMASK);

		_finderPathCountByEndDate = new FinderPath(
			CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEndDate",
			new String[] {Date.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CalendarImpl.class.getName());
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

	private static final String _SQL_SELECT_CALENDAR =
		"SELECT calendar FROM Calendar calendar";

	private static final String _SQL_SELECT_CALENDAR_WHERE_PKS_IN =
		"SELECT calendar FROM Calendar calendar WHERE id_ IN (";

	private static final String _SQL_SELECT_CALENDAR_WHERE =
		"SELECT calendar FROM Calendar calendar WHERE ";

	private static final String _SQL_COUNT_CALENDAR =
		"SELECT COUNT(calendar) FROM Calendar calendar";

	private static final String _SQL_COUNT_CALENDAR_WHERE =
		"SELECT COUNT(calendar) FROM Calendar calendar WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "calendar.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Calendar exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Calendar exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CalendarPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "id"});

}