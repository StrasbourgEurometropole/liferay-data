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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException;
import eu.strasbourg.service.agenda.model.EventParticipation;
import eu.strasbourg.service.agenda.model.impl.EventParticipationImpl;
import eu.strasbourg.service.agenda.model.impl.EventParticipationModelImpl;
import eu.strasbourg.service.agenda.service.persistence.EventParticipationPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the event participation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see EventParticipationPersistence
 * @see eu.strasbourg.service.agenda.service.persistence.EventParticipationUtil
 * @generated
 */
@ProviderType
public class EventParticipationPersistenceImpl extends BasePersistenceImpl<EventParticipation>
	implements EventParticipationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EventParticipationUtil} to access the event participation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EventParticipationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationModelImpl.FINDER_CACHE_ENABLED,
			EventParticipationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationModelImpl.FINDER_CACHE_ENABLED,
			EventParticipationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationModelImpl.FINDER_CACHE_ENABLED,
			EventParticipationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPublikUserId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationModelImpl.FINDER_CACHE_ENABLED,
			EventParticipationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikUserId",
			new String[] { String.class.getName() },
			EventParticipationModelImpl.PUBLIKUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKUSERID = new FinderPath(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikUserId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the event participations where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching event participations
	 */
	@Override
	public List<EventParticipation> findByPublikUserId(String publikUserId) {
		return findByPublikUserId(publikUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the event participations where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of event participations
	 * @param end the upper bound of the range of event participations (not inclusive)
	 * @return the range of matching event participations
	 */
	@Override
	public List<EventParticipation> findByPublikUserId(String publikUserId,
		int start, int end) {
		return findByPublikUserId(publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the event participations where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of event participations
	 * @param end the upper bound of the range of event participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching event participations
	 */
	@Override
	public List<EventParticipation> findByPublikUserId(String publikUserId,
		int start, int end,
		OrderByComparator<EventParticipation> orderByComparator) {
		return findByPublikUserId(publikUserId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the event participations where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of event participations
	 * @param end the upper bound of the range of event participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching event participations
	 */
	@Override
	public List<EventParticipation> findByPublikUserId(String publikUserId,
		int start, int end,
		OrderByComparator<EventParticipation> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID;
			finderArgs = new Object[] { publikUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKUSERID;
			finderArgs = new Object[] {
					publikUserId,
					
					start, end, orderByComparator
				};
		}

		List<EventParticipation> list = null;

		if (retrieveFromCache) {
			list = (List<EventParticipation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EventParticipation eventParticipation : list) {
					if (!Objects.equals(publikUserId,
								eventParticipation.getPublikUserId())) {
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

			query.append(_SQL_SELECT_EVENTPARTICIPATION_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EventParticipationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
				}

				if (!pagination) {
					list = (List<EventParticipation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EventParticipation>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first event participation in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event participation
	 * @throws NoSuchEventParticipationException if a matching event participation could not be found
	 */
	@Override
	public EventParticipation findByPublikUserId_First(String publikUserId,
		OrderByComparator<EventParticipation> orderByComparator)
		throws NoSuchEventParticipationException {
		EventParticipation eventParticipation = fetchByPublikUserId_First(publikUserId,
				orderByComparator);

		if (eventParticipation != null) {
			return eventParticipation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventParticipationException(msg.toString());
	}

	/**
	 * Returns the first event participation in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event participation, or <code>null</code> if a matching event participation could not be found
	 */
	@Override
	public EventParticipation fetchByPublikUserId_First(String publikUserId,
		OrderByComparator<EventParticipation> orderByComparator) {
		List<EventParticipation> list = findByPublikUserId(publikUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last event participation in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event participation
	 * @throws NoSuchEventParticipationException if a matching event participation could not be found
	 */
	@Override
	public EventParticipation findByPublikUserId_Last(String publikUserId,
		OrderByComparator<EventParticipation> orderByComparator)
		throws NoSuchEventParticipationException {
		EventParticipation eventParticipation = fetchByPublikUserId_Last(publikUserId,
				orderByComparator);

		if (eventParticipation != null) {
			return eventParticipation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventParticipationException(msg.toString());
	}

	/**
	 * Returns the last event participation in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event participation, or <code>null</code> if a matching event participation could not be found
	 */
	@Override
	public EventParticipation fetchByPublikUserId_Last(String publikUserId,
		OrderByComparator<EventParticipation> orderByComparator) {
		int count = countByPublikUserId(publikUserId);

		if (count == 0) {
			return null;
		}

		List<EventParticipation> list = findByPublikUserId(publikUserId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the event participations before and after the current event participation in the ordered set where publikUserId = &#63;.
	 *
	 * @param eventParticipationId the primary key of the current event participation
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event participation
	 * @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	 */
	@Override
	public EventParticipation[] findByPublikUserId_PrevAndNext(
		long eventParticipationId, String publikUserId,
		OrderByComparator<EventParticipation> orderByComparator)
		throws NoSuchEventParticipationException {
		EventParticipation eventParticipation = findByPrimaryKey(eventParticipationId);

		Session session = null;

		try {
			session = openSession();

			EventParticipation[] array = new EventParticipationImpl[3];

			array[0] = getByPublikUserId_PrevAndNext(session,
					eventParticipation, publikUserId, orderByComparator, true);

			array[1] = eventParticipation;

			array[2] = getByPublikUserId_PrevAndNext(session,
					eventParticipation, publikUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventParticipation getByPublikUserId_PrevAndNext(
		Session session, EventParticipation eventParticipation,
		String publikUserId,
		OrderByComparator<EventParticipation> orderByComparator,
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

		query.append(_SQL_SELECT_EVENTPARTICIPATION_WHERE);

		boolean bindPublikUserId = false;

		if (publikUserId == null) {
			query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1);
		}
		else if (publikUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
		}
		else {
			bindPublikUserId = true;

			query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
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
			query.append(EventParticipationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPublikUserId) {
			qPos.add(publikUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(eventParticipation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventParticipation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the event participations where publikUserId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 */
	@Override
	public void removeByPublikUserId(String publikUserId) {
		for (EventParticipation eventParticipation : findByPublikUserId(
				publikUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(eventParticipation);
		}
	}

	/**
	 * Returns the number of event participations where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the number of matching event participations
	 */
	@Override
	public int countByPublikUserId(String publikUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKUSERID;

		Object[] finderArgs = new Object[] { publikUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EVENTPARTICIPATION_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
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

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1 = "eventParticipation.publikUserId IS NULL";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2 = "eventParticipation.publikUserId = ?";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3 = "(eventParticipation.publikUserId IS NULL OR eventParticipation.publikUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENTID = new FinderPath(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationModelImpl.FINDER_CACHE_ENABLED,
			EventParticipationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEventId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTID =
		new FinderPath(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationModelImpl.FINDER_CACHE_ENABLED,
			EventParticipationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEventId",
			new String[] { Long.class.getName() },
			EventParticipationModelImpl.EVENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTID = new FinderPath(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEventId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the event participations where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching event participations
	 */
	@Override
	public List<EventParticipation> findByEventId(long eventId) {
		return findByEventId(eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the event participations where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event participations
	 * @param end the upper bound of the range of event participations (not inclusive)
	 * @return the range of matching event participations
	 */
	@Override
	public List<EventParticipation> findByEventId(long eventId, int start,
		int end) {
		return findByEventId(eventId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the event participations where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event participations
	 * @param end the upper bound of the range of event participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching event participations
	 */
	@Override
	public List<EventParticipation> findByEventId(long eventId, int start,
		int end, OrderByComparator<EventParticipation> orderByComparator) {
		return findByEventId(eventId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the event participations where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event participations
	 * @param end the upper bound of the range of event participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching event participations
	 */
	@Override
	public List<EventParticipation> findByEventId(long eventId, int start,
		int end, OrderByComparator<EventParticipation> orderByComparator,
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

		List<EventParticipation> list = null;

		if (retrieveFromCache) {
			list = (List<EventParticipation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EventParticipation eventParticipation : list) {
					if ((eventId != eventParticipation.getEventId())) {
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

			query.append(_SQL_SELECT_EVENTPARTICIPATION_WHERE);

			query.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EventParticipationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				if (!pagination) {
					list = (List<EventParticipation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EventParticipation>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first event participation in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event participation
	 * @throws NoSuchEventParticipationException if a matching event participation could not be found
	 */
	@Override
	public EventParticipation findByEventId_First(long eventId,
		OrderByComparator<EventParticipation> orderByComparator)
		throws NoSuchEventParticipationException {
		EventParticipation eventParticipation = fetchByEventId_First(eventId,
				orderByComparator);

		if (eventParticipation != null) {
			return eventParticipation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventId=");
		msg.append(eventId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventParticipationException(msg.toString());
	}

	/**
	 * Returns the first event participation in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event participation, or <code>null</code> if a matching event participation could not be found
	 */
	@Override
	public EventParticipation fetchByEventId_First(long eventId,
		OrderByComparator<EventParticipation> orderByComparator) {
		List<EventParticipation> list = findByEventId(eventId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last event participation in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event participation
	 * @throws NoSuchEventParticipationException if a matching event participation could not be found
	 */
	@Override
	public EventParticipation findByEventId_Last(long eventId,
		OrderByComparator<EventParticipation> orderByComparator)
		throws NoSuchEventParticipationException {
		EventParticipation eventParticipation = fetchByEventId_Last(eventId,
				orderByComparator);

		if (eventParticipation != null) {
			return eventParticipation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventId=");
		msg.append(eventId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventParticipationException(msg.toString());
	}

	/**
	 * Returns the last event participation in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event participation, or <code>null</code> if a matching event participation could not be found
	 */
	@Override
	public EventParticipation fetchByEventId_Last(long eventId,
		OrderByComparator<EventParticipation> orderByComparator) {
		int count = countByEventId(eventId);

		if (count == 0) {
			return null;
		}

		List<EventParticipation> list = findByEventId(eventId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the event participations before and after the current event participation in the ordered set where eventId = &#63;.
	 *
	 * @param eventParticipationId the primary key of the current event participation
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event participation
	 * @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	 */
	@Override
	public EventParticipation[] findByEventId_PrevAndNext(
		long eventParticipationId, long eventId,
		OrderByComparator<EventParticipation> orderByComparator)
		throws NoSuchEventParticipationException {
		EventParticipation eventParticipation = findByPrimaryKey(eventParticipationId);

		Session session = null;

		try {
			session = openSession();

			EventParticipation[] array = new EventParticipationImpl[3];

			array[0] = getByEventId_PrevAndNext(session, eventParticipation,
					eventId, orderByComparator, true);

			array[1] = eventParticipation;

			array[2] = getByEventId_PrevAndNext(session, eventParticipation,
					eventId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventParticipation getByEventId_PrevAndNext(Session session,
		EventParticipation eventParticipation, long eventId,
		OrderByComparator<EventParticipation> orderByComparator,
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

		query.append(_SQL_SELECT_EVENTPARTICIPATION_WHERE);

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
			query.append(EventParticipationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(eventId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(eventParticipation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventParticipation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the event participations where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 */
	@Override
	public void removeByEventId(long eventId) {
		for (EventParticipation eventParticipation : findByEventId(eventId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(eventParticipation);
		}
	}

	/**
	 * Returns the number of event participations where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching event participations
	 */
	@Override
	public int countByEventId(long eventId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_EVENTID;

		Object[] finderArgs = new Object[] { eventId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EVENTPARTICIPATION_WHERE);

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

	private static final String _FINDER_COLUMN_EVENTID_EVENTID_2 = "eventParticipation.eventId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDEVENTID = new FinderPath(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationModelImpl.FINDER_CACHE_ENABLED,
			EventParticipationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByPublikUserIdAndEventId",
			new String[] { String.class.getName(), Long.class.getName() },
			EventParticipationModelImpl.PUBLIKUSERID_COLUMN_BITMASK |
			EventParticipationModelImpl.EVENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDEVENTID = new FinderPath(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPublikUserIdAndEventId",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the event participation where publikUserId = &#63; and eventId = &#63; or throws a {@link NoSuchEventParticipationException} if it could not be found.
	 *
	 * @param publikUserId the publik user ID
	 * @param eventId the event ID
	 * @return the matching event participation
	 * @throws NoSuchEventParticipationException if a matching event participation could not be found
	 */
	@Override
	public EventParticipation findByPublikUserIdAndEventId(
		String publikUserId, long eventId)
		throws NoSuchEventParticipationException {
		EventParticipation eventParticipation = fetchByPublikUserIdAndEventId(publikUserId,
				eventId);

		if (eventParticipation == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("publikUserId=");
			msg.append(publikUserId);

			msg.append(", eventId=");
			msg.append(eventId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchEventParticipationException(msg.toString());
		}

		return eventParticipation;
	}

	/**
	 * Returns the event participation where publikUserId = &#63; and eventId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param publikUserId the publik user ID
	 * @param eventId the event ID
	 * @return the matching event participation, or <code>null</code> if a matching event participation could not be found
	 */
	@Override
	public EventParticipation fetchByPublikUserIdAndEventId(
		String publikUserId, long eventId) {
		return fetchByPublikUserIdAndEventId(publikUserId, eventId, true);
	}

	/**
	 * Returns the event participation where publikUserId = &#63; and eventId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param publikUserId the publik user ID
	 * @param eventId the event ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching event participation, or <code>null</code> if a matching event participation could not be found
	 */
	@Override
	public EventParticipation fetchByPublikUserIdAndEventId(
		String publikUserId, long eventId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { publikUserId, eventId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDEVENTID,
					finderArgs, this);
		}

		if (result instanceof EventParticipation) {
			EventParticipation eventParticipation = (EventParticipation)result;

			if (!Objects.equals(publikUserId,
						eventParticipation.getPublikUserId()) ||
					(eventId != eventParticipation.getEventId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_EVENTPARTICIPATION_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDEVENTID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDEVENTID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDEVENTID_PUBLIKUSERID_2);
			}

			query.append(_FINDER_COLUMN_PUBLIKUSERIDANDEVENTID_EVENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
				}

				qPos.add(eventId);

				List<EventParticipation> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDEVENTID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"EventParticipationPersistenceImpl.fetchByPublikUserIdAndEventId(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					EventParticipation eventParticipation = list.get(0);

					result = eventParticipation;

					cacheResult(eventParticipation);

					if ((eventParticipation.getPublikUserId() == null) ||
							!eventParticipation.getPublikUserId()
												   .equals(publikUserId) ||
							(eventParticipation.getEventId() != eventId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDEVENTID,
							finderArgs, eventParticipation);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDEVENTID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (EventParticipation)result;
		}
	}

	/**
	 * Removes the event participation where publikUserId = &#63; and eventId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 * @param eventId the event ID
	 * @return the event participation that was removed
	 */
	@Override
	public EventParticipation removeByPublikUserIdAndEventId(
		String publikUserId, long eventId)
		throws NoSuchEventParticipationException {
		EventParticipation eventParticipation = findByPublikUserIdAndEventId(publikUserId,
				eventId);

		return remove(eventParticipation);
	}

	/**
	 * Returns the number of event participations where publikUserId = &#63; and eventId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param eventId the event ID
	 * @return the number of matching event participations
	 */
	@Override
	public int countByPublikUserIdAndEventId(String publikUserId, long eventId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDEVENTID;

		Object[] finderArgs = new Object[] { publikUserId, eventId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EVENTPARTICIPATION_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDEVENTID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDEVENTID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDEVENTID_PUBLIKUSERID_2);
			}

			query.append(_FINDER_COLUMN_PUBLIKUSERIDANDEVENTID_EVENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
				}

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

	private static final String _FINDER_COLUMN_PUBLIKUSERIDANDEVENTID_PUBLIKUSERID_1 =
		"eventParticipation.publikUserId IS NULL AND ";
	private static final String _FINDER_COLUMN_PUBLIKUSERIDANDEVENTID_PUBLIKUSERID_2 =
		"eventParticipation.publikUserId = ? AND ";
	private static final String _FINDER_COLUMN_PUBLIKUSERIDANDEVENTID_PUBLIKUSERID_3 =
		"(eventParticipation.publikUserId IS NULL OR eventParticipation.publikUserId = '') AND ";
	private static final String _FINDER_COLUMN_PUBLIKUSERIDANDEVENTID_EVENTID_2 = "eventParticipation.eventId = ?";

	public EventParticipationPersistenceImpl() {
		setModelClass(EventParticipation.class);
	}

	/**
	 * Caches the event participation in the entity cache if it is enabled.
	 *
	 * @param eventParticipation the event participation
	 */
	@Override
	public void cacheResult(EventParticipation eventParticipation) {
		entityCache.putResult(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationImpl.class, eventParticipation.getPrimaryKey(),
			eventParticipation);

		finderCache.putResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDEVENTID,
			new Object[] {
				eventParticipation.getPublikUserId(),
				eventParticipation.getEventId()
			}, eventParticipation);

		eventParticipation.resetOriginalValues();
	}

	/**
	 * Caches the event participations in the entity cache if it is enabled.
	 *
	 * @param eventParticipations the event participations
	 */
	@Override
	public void cacheResult(List<EventParticipation> eventParticipations) {
		for (EventParticipation eventParticipation : eventParticipations) {
			if (entityCache.getResult(
						EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
						EventParticipationImpl.class,
						eventParticipation.getPrimaryKey()) == null) {
				cacheResult(eventParticipation);
			}
			else {
				eventParticipation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all event participations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EventParticipationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the event participation.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EventParticipation eventParticipation) {
		entityCache.removeResult(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationImpl.class, eventParticipation.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((EventParticipationModelImpl)eventParticipation,
			true);
	}

	@Override
	public void clearCache(List<EventParticipation> eventParticipations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EventParticipation eventParticipation : eventParticipations) {
			entityCache.removeResult(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
				EventParticipationImpl.class, eventParticipation.getPrimaryKey());

			clearUniqueFindersCache((EventParticipationModelImpl)eventParticipation,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		EventParticipationModelImpl eventParticipationModelImpl) {
		Object[] args = new Object[] {
				eventParticipationModelImpl.getPublikUserId(),
				eventParticipationModelImpl.getEventId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDEVENTID,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDEVENTID,
			args, eventParticipationModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		EventParticipationModelImpl eventParticipationModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					eventParticipationModelImpl.getPublikUserId(),
					eventParticipationModelImpl.getEventId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDEVENTID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDEVENTID,
				args);
		}

		if ((eventParticipationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDEVENTID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					eventParticipationModelImpl.getOriginalPublikUserId(),
					eventParticipationModelImpl.getOriginalEventId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDEVENTID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDEVENTID,
				args);
		}
	}

	/**
	 * Creates a new event participation with the primary key. Does not add the event participation to the database.
	 *
	 * @param eventParticipationId the primary key for the new event participation
	 * @return the new event participation
	 */
	@Override
	public EventParticipation create(long eventParticipationId) {
		EventParticipation eventParticipation = new EventParticipationImpl();

		eventParticipation.setNew(true);
		eventParticipation.setPrimaryKey(eventParticipationId);

		return eventParticipation;
	}

	/**
	 * Removes the event participation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventParticipationId the primary key of the event participation
	 * @return the event participation that was removed
	 * @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	 */
	@Override
	public EventParticipation remove(long eventParticipationId)
		throws NoSuchEventParticipationException {
		return remove((Serializable)eventParticipationId);
	}

	/**
	 * Removes the event participation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the event participation
	 * @return the event participation that was removed
	 * @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	 */
	@Override
	public EventParticipation remove(Serializable primaryKey)
		throws NoSuchEventParticipationException {
		Session session = null;

		try {
			session = openSession();

			EventParticipation eventParticipation = (EventParticipation)session.get(EventParticipationImpl.class,
					primaryKey);

			if (eventParticipation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEventParticipationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(eventParticipation);
		}
		catch (NoSuchEventParticipationException nsee) {
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
	protected EventParticipation removeImpl(
		EventParticipation eventParticipation) {
		eventParticipation = toUnwrappedModel(eventParticipation);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(eventParticipation)) {
				eventParticipation = (EventParticipation)session.get(EventParticipationImpl.class,
						eventParticipation.getPrimaryKeyObj());
			}

			if (eventParticipation != null) {
				session.delete(eventParticipation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (eventParticipation != null) {
			clearCache(eventParticipation);
		}

		return eventParticipation;
	}

	@Override
	public EventParticipation updateImpl(EventParticipation eventParticipation) {
		eventParticipation = toUnwrappedModel(eventParticipation);

		boolean isNew = eventParticipation.isNew();

		EventParticipationModelImpl eventParticipationModelImpl = (EventParticipationModelImpl)eventParticipation;

		Session session = null;

		try {
			session = openSession();

			if (eventParticipation.isNew()) {
				session.save(eventParticipation);

				eventParticipation.setNew(false);
			}
			else {
				eventParticipation = (EventParticipation)session.merge(eventParticipation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!EventParticipationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					eventParticipationModelImpl.getPublikUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
				args);

			args = new Object[] { eventParticipationModelImpl.getEventId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_EVENTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((eventParticipationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						eventParticipationModelImpl.getOriginalPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);

				args = new Object[] {
						eventParticipationModelImpl.getPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);
			}

			if ((eventParticipationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						eventParticipationModelImpl.getOriginalEventId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EVENTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTID,
					args);

				args = new Object[] { eventParticipationModelImpl.getEventId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EVENTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTID,
					args);
			}
		}

		entityCache.putResult(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
			EventParticipationImpl.class, eventParticipation.getPrimaryKey(),
			eventParticipation, false);

		clearUniqueFindersCache(eventParticipationModelImpl, false);
		cacheUniqueFindersCache(eventParticipationModelImpl);

		eventParticipation.resetOriginalValues();

		return eventParticipation;
	}

	protected EventParticipation toUnwrappedModel(
		EventParticipation eventParticipation) {
		if (eventParticipation instanceof EventParticipationImpl) {
			return eventParticipation;
		}

		EventParticipationImpl eventParticipationImpl = new EventParticipationImpl();

		eventParticipationImpl.setNew(eventParticipation.isNew());
		eventParticipationImpl.setPrimaryKey(eventParticipation.getPrimaryKey());

		eventParticipationImpl.setEventParticipationId(eventParticipation.getEventParticipationId());
		eventParticipationImpl.setCreateDate(eventParticipation.getCreateDate());
		eventParticipationImpl.setPublikUserId(eventParticipation.getPublikUserId());
		eventParticipationImpl.setEventId(eventParticipation.getEventId());
		eventParticipationImpl.setGroupId(eventParticipation.getGroupId());

		return eventParticipationImpl;
	}

	/**
	 * Returns the event participation with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the event participation
	 * @return the event participation
	 * @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	 */
	@Override
	public EventParticipation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEventParticipationException {
		EventParticipation eventParticipation = fetchByPrimaryKey(primaryKey);

		if (eventParticipation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEventParticipationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return eventParticipation;
	}

	/**
	 * Returns the event participation with the primary key or throws a {@link NoSuchEventParticipationException} if it could not be found.
	 *
	 * @param eventParticipationId the primary key of the event participation
	 * @return the event participation
	 * @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	 */
	@Override
	public EventParticipation findByPrimaryKey(long eventParticipationId)
		throws NoSuchEventParticipationException {
		return findByPrimaryKey((Serializable)eventParticipationId);
	}

	/**
	 * Returns the event participation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the event participation
	 * @return the event participation, or <code>null</code> if a event participation with the primary key could not be found
	 */
	@Override
	public EventParticipation fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
				EventParticipationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		EventParticipation eventParticipation = (EventParticipation)serializable;

		if (eventParticipation == null) {
			Session session = null;

			try {
				session = openSession();

				eventParticipation = (EventParticipation)session.get(EventParticipationImpl.class,
						primaryKey);

				if (eventParticipation != null) {
					cacheResult(eventParticipation);
				}
				else {
					entityCache.putResult(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
						EventParticipationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
					EventParticipationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return eventParticipation;
	}

	/**
	 * Returns the event participation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventParticipationId the primary key of the event participation
	 * @return the event participation, or <code>null</code> if a event participation with the primary key could not be found
	 */
	@Override
	public EventParticipation fetchByPrimaryKey(long eventParticipationId) {
		return fetchByPrimaryKey((Serializable)eventParticipationId);
	}

	@Override
	public Map<Serializable, EventParticipation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, EventParticipation> map = new HashMap<Serializable, EventParticipation>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			EventParticipation eventParticipation = fetchByPrimaryKey(primaryKey);

			if (eventParticipation != null) {
				map.put(primaryKey, eventParticipation);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
					EventParticipationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (EventParticipation)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_EVENTPARTICIPATION_WHERE_PKS_IN);

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

			for (EventParticipation eventParticipation : (List<EventParticipation>)q.list()) {
				map.put(eventParticipation.getPrimaryKeyObj(),
					eventParticipation);

				cacheResult(eventParticipation);

				uncachedPrimaryKeys.remove(eventParticipation.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EventParticipationModelImpl.ENTITY_CACHE_ENABLED,
					EventParticipationImpl.class, primaryKey, nullModel);
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
	 * Returns all the event participations.
	 *
	 * @return the event participations
	 */
	@Override
	public List<EventParticipation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the event participations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of event participations
	 * @param end the upper bound of the range of event participations (not inclusive)
	 * @return the range of event participations
	 */
	@Override
	public List<EventParticipation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the event participations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of event participations
	 * @param end the upper bound of the range of event participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of event participations
	 */
	@Override
	public List<EventParticipation> findAll(int start, int end,
		OrderByComparator<EventParticipation> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the event participations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of event participations
	 * @param end the upper bound of the range of event participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of event participations
	 */
	@Override
	public List<EventParticipation> findAll(int start, int end,
		OrderByComparator<EventParticipation> orderByComparator,
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

		List<EventParticipation> list = null;

		if (retrieveFromCache) {
			list = (List<EventParticipation>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_EVENTPARTICIPATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EVENTPARTICIPATION;

				if (pagination) {
					sql = sql.concat(EventParticipationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<EventParticipation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EventParticipation>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the event participations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EventParticipation eventParticipation : findAll()) {
			remove(eventParticipation);
		}
	}

	/**
	 * Returns the number of event participations.
	 *
	 * @return the number of event participations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EVENTPARTICIPATION);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return EventParticipationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the event participation persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(EventParticipationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_EVENTPARTICIPATION = "SELECT eventParticipation FROM EventParticipation eventParticipation";
	private static final String _SQL_SELECT_EVENTPARTICIPATION_WHERE_PKS_IN = "SELECT eventParticipation FROM EventParticipation eventParticipation WHERE eventParticipationId IN (";
	private static final String _SQL_SELECT_EVENTPARTICIPATION_WHERE = "SELECT eventParticipation FROM EventParticipation eventParticipation WHERE ";
	private static final String _SQL_COUNT_EVENTPARTICIPATION = "SELECT COUNT(eventParticipation) FROM EventParticipation eventParticipation";
	private static final String _SQL_COUNT_EVENTPARTICIPATION_WHERE = "SELECT COUNT(eventParticipation) FROM EventParticipation eventParticipation WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "eventParticipation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EventParticipation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EventParticipation exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(EventParticipationPersistenceImpl.class);
}