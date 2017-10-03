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
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.agenda.exception.NoSuchEventPeriodException;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.model.impl.EventPeriodImpl;
import eu.strasbourg.service.agenda.model.impl.EventPeriodModelImpl;
import eu.strasbourg.service.agenda.service.persistence.EventPeriodPersistence;

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
 * The persistence implementation for the event period service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see EventPeriodPersistence
 * @see eu.strasbourg.service.agenda.service.persistence.EventPeriodUtil
 * @generated
 */
@ProviderType
public class EventPeriodPersistenceImpl extends BasePersistenceImpl<EventPeriod>
	implements EventPeriodPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EventPeriodUtil} to access the event period persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EventPeriodImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodModelImpl.FINDER_CACHE_ENABLED, EventPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodModelImpl.FINDER_CACHE_ENABLED, EventPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodModelImpl.FINDER_CACHE_ENABLED, EventPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodModelImpl.FINDER_CACHE_ENABLED, EventPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			EventPeriodModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the event periods where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching event periods
	 */
	@Override
	public List<EventPeriod> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the event periods where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of event periods
	 * @param end the upper bound of the range of event periods (not inclusive)
	 * @return the range of matching event periods
	 */
	@Override
	public List<EventPeriod> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the event periods where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of event periods
	 * @param end the upper bound of the range of event periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching event periods
	 */
	@Override
	public List<EventPeriod> findByUuid(String uuid, int start, int end,
		OrderByComparator<EventPeriod> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the event periods where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of event periods
	 * @param end the upper bound of the range of event periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching event periods
	 */
	@Override
	public List<EventPeriod> findByUuid(String uuid, int start, int end,
		OrderByComparator<EventPeriod> orderByComparator,
		boolean retrieveFromCache) {
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

		List<EventPeriod> list = null;

		if (retrieveFromCache) {
			list = (List<EventPeriod>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EventPeriod eventPeriod : list) {
					if (!Objects.equals(uuid, eventPeriod.getUuid())) {
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

			query.append(_SQL_SELECT_EVENTPERIOD_WHERE);

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
				query.append(EventPeriodModelImpl.ORDER_BY_JPQL);
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
					list = (List<EventPeriod>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EventPeriod>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first event period in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event period
	 * @throws NoSuchEventPeriodException if a matching event period could not be found
	 */
	@Override
	public EventPeriod findByUuid_First(String uuid,
		OrderByComparator<EventPeriod> orderByComparator)
		throws NoSuchEventPeriodException {
		EventPeriod eventPeriod = fetchByUuid_First(uuid, orderByComparator);

		if (eventPeriod != null) {
			return eventPeriod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventPeriodException(msg.toString());
	}

	/**
	 * Returns the first event period in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event period, or <code>null</code> if a matching event period could not be found
	 */
	@Override
	public EventPeriod fetchByUuid_First(String uuid,
		OrderByComparator<EventPeriod> orderByComparator) {
		List<EventPeriod> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last event period in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event period
	 * @throws NoSuchEventPeriodException if a matching event period could not be found
	 */
	@Override
	public EventPeriod findByUuid_Last(String uuid,
		OrderByComparator<EventPeriod> orderByComparator)
		throws NoSuchEventPeriodException {
		EventPeriod eventPeriod = fetchByUuid_Last(uuid, orderByComparator);

		if (eventPeriod != null) {
			return eventPeriod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventPeriodException(msg.toString());
	}

	/**
	 * Returns the last event period in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event period, or <code>null</code> if a matching event period could not be found
	 */
	@Override
	public EventPeriod fetchByUuid_Last(String uuid,
		OrderByComparator<EventPeriod> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<EventPeriod> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the event periods before and after the current event period in the ordered set where uuid = &#63;.
	 *
	 * @param eventPeriodId the primary key of the current event period
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event period
	 * @throws NoSuchEventPeriodException if a event period with the primary key could not be found
	 */
	@Override
	public EventPeriod[] findByUuid_PrevAndNext(long eventPeriodId,
		String uuid, OrderByComparator<EventPeriod> orderByComparator)
		throws NoSuchEventPeriodException {
		EventPeriod eventPeriod = findByPrimaryKey(eventPeriodId);

		Session session = null;

		try {
			session = openSession();

			EventPeriod[] array = new EventPeriodImpl[3];

			array[0] = getByUuid_PrevAndNext(session, eventPeriod, uuid,
					orderByComparator, true);

			array[1] = eventPeriod;

			array[2] = getByUuid_PrevAndNext(session, eventPeriod, uuid,
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

	protected EventPeriod getByUuid_PrevAndNext(Session session,
		EventPeriod eventPeriod, String uuid,
		OrderByComparator<EventPeriod> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTPERIOD_WHERE);

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
			query.append(EventPeriodModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(eventPeriod);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventPeriod> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the event periods where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (EventPeriod eventPeriod : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(eventPeriod);
		}
	}

	/**
	 * Returns the number of event periods where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching event periods
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EVENTPERIOD_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "eventPeriod.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "eventPeriod.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(eventPeriod.uuid IS NULL OR eventPeriod.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENTID = new FinderPath(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodModelImpl.FINDER_CACHE_ENABLED, EventPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEventId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTID =
		new FinderPath(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodModelImpl.FINDER_CACHE_ENABLED, EventPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEventId",
			new String[] { Long.class.getName() },
			EventPeriodModelImpl.EVENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTID = new FinderPath(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEventId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the event periods where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching event periods
	 */
	@Override
	public List<EventPeriod> findByEventId(long eventId) {
		return findByEventId(eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the event periods where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event periods
	 * @param end the upper bound of the range of event periods (not inclusive)
	 * @return the range of matching event periods
	 */
	@Override
	public List<EventPeriod> findByEventId(long eventId, int start, int end) {
		return findByEventId(eventId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the event periods where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event periods
	 * @param end the upper bound of the range of event periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching event periods
	 */
	@Override
	public List<EventPeriod> findByEventId(long eventId, int start, int end,
		OrderByComparator<EventPeriod> orderByComparator) {
		return findByEventId(eventId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the event periods where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event periods
	 * @param end the upper bound of the range of event periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching event periods
	 */
	@Override
	public List<EventPeriod> findByEventId(long eventId, int start, int end,
		OrderByComparator<EventPeriod> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTID;
			finderArgs = new Object[] { eventId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENTID;
			finderArgs = new Object[] { eventId, start, end, orderByComparator };
		}

		List<EventPeriod> list = null;

		if (retrieveFromCache) {
			list = (List<EventPeriod>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EventPeriod eventPeriod : list) {
					if ((eventId != eventPeriod.getEventId())) {
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

			query.append(_SQL_SELECT_EVENTPERIOD_WHERE);

			query.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EventPeriodModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				if (!pagination) {
					list = (List<EventPeriod>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EventPeriod>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first event period in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event period
	 * @throws NoSuchEventPeriodException if a matching event period could not be found
	 */
	@Override
	public EventPeriod findByEventId_First(long eventId,
		OrderByComparator<EventPeriod> orderByComparator)
		throws NoSuchEventPeriodException {
		EventPeriod eventPeriod = fetchByEventId_First(eventId,
				orderByComparator);

		if (eventPeriod != null) {
			return eventPeriod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventId=");
		msg.append(eventId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventPeriodException(msg.toString());
	}

	/**
	 * Returns the first event period in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event period, or <code>null</code> if a matching event period could not be found
	 */
	@Override
	public EventPeriod fetchByEventId_First(long eventId,
		OrderByComparator<EventPeriod> orderByComparator) {
		List<EventPeriod> list = findByEventId(eventId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last event period in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event period
	 * @throws NoSuchEventPeriodException if a matching event period could not be found
	 */
	@Override
	public EventPeriod findByEventId_Last(long eventId,
		OrderByComparator<EventPeriod> orderByComparator)
		throws NoSuchEventPeriodException {
		EventPeriod eventPeriod = fetchByEventId_Last(eventId, orderByComparator);

		if (eventPeriod != null) {
			return eventPeriod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventId=");
		msg.append(eventId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventPeriodException(msg.toString());
	}

	/**
	 * Returns the last event period in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event period, or <code>null</code> if a matching event period could not be found
	 */
	@Override
	public EventPeriod fetchByEventId_Last(long eventId,
		OrderByComparator<EventPeriod> orderByComparator) {
		int count = countByEventId(eventId);

		if (count == 0) {
			return null;
		}

		List<EventPeriod> list = findByEventId(eventId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the event periods before and after the current event period in the ordered set where eventId = &#63;.
	 *
	 * @param eventPeriodId the primary key of the current event period
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event period
	 * @throws NoSuchEventPeriodException if a event period with the primary key could not be found
	 */
	@Override
	public EventPeriod[] findByEventId_PrevAndNext(long eventPeriodId,
		long eventId, OrderByComparator<EventPeriod> orderByComparator)
		throws NoSuchEventPeriodException {
		EventPeriod eventPeriod = findByPrimaryKey(eventPeriodId);

		Session session = null;

		try {
			session = openSession();

			EventPeriod[] array = new EventPeriodImpl[3];

			array[0] = getByEventId_PrevAndNext(session, eventPeriod, eventId,
					orderByComparator, true);

			array[1] = eventPeriod;

			array[2] = getByEventId_PrevAndNext(session, eventPeriod, eventId,
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

	protected EventPeriod getByEventId_PrevAndNext(Session session,
		EventPeriod eventPeriod, long eventId,
		OrderByComparator<EventPeriod> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTPERIOD_WHERE);

		query.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

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
			query.append(EventPeriodModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(eventId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(eventPeriod);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventPeriod> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the event periods where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 */
	@Override
	public void removeByEventId(long eventId) {
		for (EventPeriod eventPeriod : findByEventId(eventId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(eventPeriod);
		}
	}

	/**
	 * Returns the number of event periods where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching event periods
	 */
	@Override
	public int countByEventId(long eventId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_EVENTID;

		Object[] finderArgs = new Object[] { eventId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EVENTPERIOD_WHERE);

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

	private static final String _FINDER_COLUMN_EVENTID_EVENTID_2 = "eventPeriod.eventId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CAMPAIGNEVENTID =
		new FinderPath(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodModelImpl.FINDER_CACHE_ENABLED, EventPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCampaignEventId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNEVENTID =
		new FinderPath(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodModelImpl.FINDER_CACHE_ENABLED, EventPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCampaignEventId",
			new String[] { Long.class.getName() },
			EventPeriodModelImpl.CAMPAIGNEVENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CAMPAIGNEVENTID = new FinderPath(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCampaignEventId", new String[] { Long.class.getName() });

	/**
	 * Returns all the event periods where campaignEventId = &#63;.
	 *
	 * @param campaignEventId the campaign event ID
	 * @return the matching event periods
	 */
	@Override
	public List<EventPeriod> findByCampaignEventId(long campaignEventId) {
		return findByCampaignEventId(campaignEventId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the event periods where campaignEventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param campaignEventId the campaign event ID
	 * @param start the lower bound of the range of event periods
	 * @param end the upper bound of the range of event periods (not inclusive)
	 * @return the range of matching event periods
	 */
	@Override
	public List<EventPeriod> findByCampaignEventId(long campaignEventId,
		int start, int end) {
		return findByCampaignEventId(campaignEventId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the event periods where campaignEventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param campaignEventId the campaign event ID
	 * @param start the lower bound of the range of event periods
	 * @param end the upper bound of the range of event periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching event periods
	 */
	@Override
	public List<EventPeriod> findByCampaignEventId(long campaignEventId,
		int start, int end, OrderByComparator<EventPeriod> orderByComparator) {
		return findByCampaignEventId(campaignEventId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the event periods where campaignEventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param campaignEventId the campaign event ID
	 * @param start the lower bound of the range of event periods
	 * @param end the upper bound of the range of event periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching event periods
	 */
	@Override
	public List<EventPeriod> findByCampaignEventId(long campaignEventId,
		int start, int end, OrderByComparator<EventPeriod> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNEVENTID;
			finderArgs = new Object[] { campaignEventId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CAMPAIGNEVENTID;
			finderArgs = new Object[] {
					campaignEventId,
					
					start, end, orderByComparator
				};
		}

		List<EventPeriod> list = null;

		if (retrieveFromCache) {
			list = (List<EventPeriod>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EventPeriod eventPeriod : list) {
					if ((campaignEventId != eventPeriod.getCampaignEventId())) {
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

			query.append(_SQL_SELECT_EVENTPERIOD_WHERE);

			query.append(_FINDER_COLUMN_CAMPAIGNEVENTID_CAMPAIGNEVENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EventPeriodModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignEventId);

				if (!pagination) {
					list = (List<EventPeriod>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EventPeriod>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first event period in the ordered set where campaignEventId = &#63;.
	 *
	 * @param campaignEventId the campaign event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event period
	 * @throws NoSuchEventPeriodException if a matching event period could not be found
	 */
	@Override
	public EventPeriod findByCampaignEventId_First(long campaignEventId,
		OrderByComparator<EventPeriod> orderByComparator)
		throws NoSuchEventPeriodException {
		EventPeriod eventPeriod = fetchByCampaignEventId_First(campaignEventId,
				orderByComparator);

		if (eventPeriod != null) {
			return eventPeriod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("campaignEventId=");
		msg.append(campaignEventId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventPeriodException(msg.toString());
	}

	/**
	 * Returns the first event period in the ordered set where campaignEventId = &#63;.
	 *
	 * @param campaignEventId the campaign event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event period, or <code>null</code> if a matching event period could not be found
	 */
	@Override
	public EventPeriod fetchByCampaignEventId_First(long campaignEventId,
		OrderByComparator<EventPeriod> orderByComparator) {
		List<EventPeriod> list = findByCampaignEventId(campaignEventId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last event period in the ordered set where campaignEventId = &#63;.
	 *
	 * @param campaignEventId the campaign event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event period
	 * @throws NoSuchEventPeriodException if a matching event period could not be found
	 */
	@Override
	public EventPeriod findByCampaignEventId_Last(long campaignEventId,
		OrderByComparator<EventPeriod> orderByComparator)
		throws NoSuchEventPeriodException {
		EventPeriod eventPeriod = fetchByCampaignEventId_Last(campaignEventId,
				orderByComparator);

		if (eventPeriod != null) {
			return eventPeriod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("campaignEventId=");
		msg.append(campaignEventId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventPeriodException(msg.toString());
	}

	/**
	 * Returns the last event period in the ordered set where campaignEventId = &#63;.
	 *
	 * @param campaignEventId the campaign event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event period, or <code>null</code> if a matching event period could not be found
	 */
	@Override
	public EventPeriod fetchByCampaignEventId_Last(long campaignEventId,
		OrderByComparator<EventPeriod> orderByComparator) {
		int count = countByCampaignEventId(campaignEventId);

		if (count == 0) {
			return null;
		}

		List<EventPeriod> list = findByCampaignEventId(campaignEventId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the event periods before and after the current event period in the ordered set where campaignEventId = &#63;.
	 *
	 * @param eventPeriodId the primary key of the current event period
	 * @param campaignEventId the campaign event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event period
	 * @throws NoSuchEventPeriodException if a event period with the primary key could not be found
	 */
	@Override
	public EventPeriod[] findByCampaignEventId_PrevAndNext(long eventPeriodId,
		long campaignEventId, OrderByComparator<EventPeriod> orderByComparator)
		throws NoSuchEventPeriodException {
		EventPeriod eventPeriod = findByPrimaryKey(eventPeriodId);

		Session session = null;

		try {
			session = openSession();

			EventPeriod[] array = new EventPeriodImpl[3];

			array[0] = getByCampaignEventId_PrevAndNext(session, eventPeriod,
					campaignEventId, orderByComparator, true);

			array[1] = eventPeriod;

			array[2] = getByCampaignEventId_PrevAndNext(session, eventPeriod,
					campaignEventId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventPeriod getByCampaignEventId_PrevAndNext(Session session,
		EventPeriod eventPeriod, long campaignEventId,
		OrderByComparator<EventPeriod> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTPERIOD_WHERE);

		query.append(_FINDER_COLUMN_CAMPAIGNEVENTID_CAMPAIGNEVENTID_2);

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
			query.append(EventPeriodModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(campaignEventId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(eventPeriod);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventPeriod> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the event periods where campaignEventId = &#63; from the database.
	 *
	 * @param campaignEventId the campaign event ID
	 */
	@Override
	public void removeByCampaignEventId(long campaignEventId) {
		for (EventPeriod eventPeriod : findByCampaignEventId(campaignEventId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(eventPeriod);
		}
	}

	/**
	 * Returns the number of event periods where campaignEventId = &#63;.
	 *
	 * @param campaignEventId the campaign event ID
	 * @return the number of matching event periods
	 */
	@Override
	public int countByCampaignEventId(long campaignEventId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CAMPAIGNEVENTID;

		Object[] finderArgs = new Object[] { campaignEventId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EVENTPERIOD_WHERE);

			query.append(_FINDER_COLUMN_CAMPAIGNEVENTID_CAMPAIGNEVENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignEventId);

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

	private static final String _FINDER_COLUMN_CAMPAIGNEVENTID_CAMPAIGNEVENTID_2 =
		"eventPeriod.campaignEventId = ?";

	public EventPeriodPersistenceImpl() {
		setModelClass(EventPeriod.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the event period in the entity cache if it is enabled.
	 *
	 * @param eventPeriod the event period
	 */
	@Override
	public void cacheResult(EventPeriod eventPeriod) {
		entityCache.putResult(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodImpl.class, eventPeriod.getPrimaryKey(), eventPeriod);

		eventPeriod.resetOriginalValues();
	}

	/**
	 * Caches the event periods in the entity cache if it is enabled.
	 *
	 * @param eventPeriods the event periods
	 */
	@Override
	public void cacheResult(List<EventPeriod> eventPeriods) {
		for (EventPeriod eventPeriod : eventPeriods) {
			if (entityCache.getResult(
						EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
						EventPeriodImpl.class, eventPeriod.getPrimaryKey()) == null) {
				cacheResult(eventPeriod);
			}
			else {
				eventPeriod.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all event periods.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EventPeriodImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the event period.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EventPeriod eventPeriod) {
		entityCache.removeResult(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodImpl.class, eventPeriod.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<EventPeriod> eventPeriods) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EventPeriod eventPeriod : eventPeriods) {
			entityCache.removeResult(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
				EventPeriodImpl.class, eventPeriod.getPrimaryKey());
		}
	}

	/**
	 * Creates a new event period with the primary key. Does not add the event period to the database.
	 *
	 * @param eventPeriodId the primary key for the new event period
	 * @return the new event period
	 */
	@Override
	public EventPeriod create(long eventPeriodId) {
		EventPeriod eventPeriod = new EventPeriodImpl();

		eventPeriod.setNew(true);
		eventPeriod.setPrimaryKey(eventPeriodId);

		String uuid = PortalUUIDUtil.generate();

		eventPeriod.setUuid(uuid);

		return eventPeriod;
	}

	/**
	 * Removes the event period with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventPeriodId the primary key of the event period
	 * @return the event period that was removed
	 * @throws NoSuchEventPeriodException if a event period with the primary key could not be found
	 */
	@Override
	public EventPeriod remove(long eventPeriodId)
		throws NoSuchEventPeriodException {
		return remove((Serializable)eventPeriodId);
	}

	/**
	 * Removes the event period with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the event period
	 * @return the event period that was removed
	 * @throws NoSuchEventPeriodException if a event period with the primary key could not be found
	 */
	@Override
	public EventPeriod remove(Serializable primaryKey)
		throws NoSuchEventPeriodException {
		Session session = null;

		try {
			session = openSession();

			EventPeriod eventPeriod = (EventPeriod)session.get(EventPeriodImpl.class,
					primaryKey);

			if (eventPeriod == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEventPeriodException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(eventPeriod);
		}
		catch (NoSuchEventPeriodException nsee) {
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
	protected EventPeriod removeImpl(EventPeriod eventPeriod) {
		eventPeriod = toUnwrappedModel(eventPeriod);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(eventPeriod)) {
				eventPeriod = (EventPeriod)session.get(EventPeriodImpl.class,
						eventPeriod.getPrimaryKeyObj());
			}

			if (eventPeriod != null) {
				session.delete(eventPeriod);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (eventPeriod != null) {
			clearCache(eventPeriod);
		}

		return eventPeriod;
	}

	@Override
	public EventPeriod updateImpl(EventPeriod eventPeriod) {
		eventPeriod = toUnwrappedModel(eventPeriod);

		boolean isNew = eventPeriod.isNew();

		EventPeriodModelImpl eventPeriodModelImpl = (EventPeriodModelImpl)eventPeriod;

		if (Validator.isNull(eventPeriod.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			eventPeriod.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (eventPeriod.isNew()) {
				session.save(eventPeriod);

				eventPeriod.setNew(false);
			}
			else {
				eventPeriod = (EventPeriod)session.merge(eventPeriod);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!EventPeriodModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { eventPeriodModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { eventPeriodModelImpl.getEventId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_EVENTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTID,
				args);

			args = new Object[] { eventPeriodModelImpl.getCampaignEventId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CAMPAIGNEVENTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNEVENTID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((eventPeriodModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						eventPeriodModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { eventPeriodModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((eventPeriodModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						eventPeriodModelImpl.getOriginalEventId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EVENTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTID,
					args);

				args = new Object[] { eventPeriodModelImpl.getEventId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EVENTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTID,
					args);
			}

			if ((eventPeriodModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNEVENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						eventPeriodModelImpl.getOriginalCampaignEventId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CAMPAIGNEVENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNEVENTID,
					args);

				args = new Object[] { eventPeriodModelImpl.getCampaignEventId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CAMPAIGNEVENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNEVENTID,
					args);
			}
		}

		entityCache.putResult(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
			EventPeriodImpl.class, eventPeriod.getPrimaryKey(), eventPeriod,
			false);

		eventPeriod.resetOriginalValues();

		return eventPeriod;
	}

	protected EventPeriod toUnwrappedModel(EventPeriod eventPeriod) {
		if (eventPeriod instanceof EventPeriodImpl) {
			return eventPeriod;
		}

		EventPeriodImpl eventPeriodImpl = new EventPeriodImpl();

		eventPeriodImpl.setNew(eventPeriod.isNew());
		eventPeriodImpl.setPrimaryKey(eventPeriod.getPrimaryKey());

		eventPeriodImpl.setUuid(eventPeriod.getUuid());
		eventPeriodImpl.setEventPeriodId(eventPeriod.getEventPeriodId());
		eventPeriodImpl.setStartDate(eventPeriod.getStartDate());
		eventPeriodImpl.setEndDate(eventPeriod.getEndDate());
		eventPeriodImpl.setTimeDetail(eventPeriod.getTimeDetail());
		eventPeriodImpl.setEventId(eventPeriod.getEventId());
		eventPeriodImpl.setCampaignEventId(eventPeriod.getCampaignEventId());

		return eventPeriodImpl;
	}

	/**
	 * Returns the event period with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the event period
	 * @return the event period
	 * @throws NoSuchEventPeriodException if a event period with the primary key could not be found
	 */
	@Override
	public EventPeriod findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEventPeriodException {
		EventPeriod eventPeriod = fetchByPrimaryKey(primaryKey);

		if (eventPeriod == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEventPeriodException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return eventPeriod;
	}

	/**
	 * Returns the event period with the primary key or throws a {@link NoSuchEventPeriodException} if it could not be found.
	 *
	 * @param eventPeriodId the primary key of the event period
	 * @return the event period
	 * @throws NoSuchEventPeriodException if a event period with the primary key could not be found
	 */
	@Override
	public EventPeriod findByPrimaryKey(long eventPeriodId)
		throws NoSuchEventPeriodException {
		return findByPrimaryKey((Serializable)eventPeriodId);
	}

	/**
	 * Returns the event period with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the event period
	 * @return the event period, or <code>null</code> if a event period with the primary key could not be found
	 */
	@Override
	public EventPeriod fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
				EventPeriodImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		EventPeriod eventPeriod = (EventPeriod)serializable;

		if (eventPeriod == null) {
			Session session = null;

			try {
				session = openSession();

				eventPeriod = (EventPeriod)session.get(EventPeriodImpl.class,
						primaryKey);

				if (eventPeriod != null) {
					cacheResult(eventPeriod);
				}
				else {
					entityCache.putResult(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
						EventPeriodImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
					EventPeriodImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return eventPeriod;
	}

	/**
	 * Returns the event period with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventPeriodId the primary key of the event period
	 * @return the event period, or <code>null</code> if a event period with the primary key could not be found
	 */
	@Override
	public EventPeriod fetchByPrimaryKey(long eventPeriodId) {
		return fetchByPrimaryKey((Serializable)eventPeriodId);
	}

	@Override
	public Map<Serializable, EventPeriod> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, EventPeriod> map = new HashMap<Serializable, EventPeriod>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			EventPeriod eventPeriod = fetchByPrimaryKey(primaryKey);

			if (eventPeriod != null) {
				map.put(primaryKey, eventPeriod);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
					EventPeriodImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (EventPeriod)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_EVENTPERIOD_WHERE_PKS_IN);

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

			for (EventPeriod eventPeriod : (List<EventPeriod>)q.list()) {
				map.put(eventPeriod.getPrimaryKeyObj(), eventPeriod);

				cacheResult(eventPeriod);

				uncachedPrimaryKeys.remove(eventPeriod.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EventPeriodModelImpl.ENTITY_CACHE_ENABLED,
					EventPeriodImpl.class, primaryKey, nullModel);
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
	 * Returns all the event periods.
	 *
	 * @return the event periods
	 */
	@Override
	public List<EventPeriod> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the event periods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of event periods
	 * @param end the upper bound of the range of event periods (not inclusive)
	 * @return the range of event periods
	 */
	@Override
	public List<EventPeriod> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the event periods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of event periods
	 * @param end the upper bound of the range of event periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of event periods
	 */
	@Override
	public List<EventPeriod> findAll(int start, int end,
		OrderByComparator<EventPeriod> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the event periods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of event periods
	 * @param end the upper bound of the range of event periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of event periods
	 */
	@Override
	public List<EventPeriod> findAll(int start, int end,
		OrderByComparator<EventPeriod> orderByComparator,
		boolean retrieveFromCache) {
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

		List<EventPeriod> list = null;

		if (retrieveFromCache) {
			list = (List<EventPeriod>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_EVENTPERIOD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EVENTPERIOD;

				if (pagination) {
					sql = sql.concat(EventPeriodModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<EventPeriod>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EventPeriod>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the event periods from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EventPeriod eventPeriod : findAll()) {
			remove(eventPeriod);
		}
	}

	/**
	 * Returns the number of event periods.
	 *
	 * @return the number of event periods
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EVENTPERIOD);

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
		return EventPeriodModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the event period persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(EventPeriodImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_EVENTPERIOD = "SELECT eventPeriod FROM EventPeriod eventPeriod";
	private static final String _SQL_SELECT_EVENTPERIOD_WHERE_PKS_IN = "SELECT eventPeriod FROM EventPeriod eventPeriod WHERE eventPeriodId IN (";
	private static final String _SQL_SELECT_EVENTPERIOD_WHERE = "SELECT eventPeriod FROM EventPeriod eventPeriod WHERE ";
	private static final String _SQL_COUNT_EVENTPERIOD = "SELECT COUNT(eventPeriod) FROM EventPeriod eventPeriod";
	private static final String _SQL_COUNT_EVENTPERIOD_WHERE = "SELECT COUNT(eventPeriod) FROM EventPeriod eventPeriod WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "eventPeriod.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EventPeriod exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EventPeriod exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(EventPeriodPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}