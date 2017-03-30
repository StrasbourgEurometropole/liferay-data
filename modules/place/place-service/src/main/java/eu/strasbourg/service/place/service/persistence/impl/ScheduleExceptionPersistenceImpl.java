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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.place.exception.NoSuchScheduleExceptionException;
import eu.strasbourg.service.place.model.ScheduleException;
import eu.strasbourg.service.place.model.impl.ScheduleExceptionImpl;
import eu.strasbourg.service.place.model.impl.ScheduleExceptionModelImpl;
import eu.strasbourg.service.place.service.persistence.ScheduleExceptionPersistence;

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
 * The persistence implementation for the schedule exception service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see ScheduleExceptionPersistence
 * @see eu.strasbourg.service.place.service.persistence.ScheduleExceptionUtil
 * @generated
 */
@ProviderType
public class ScheduleExceptionPersistenceImpl extends BasePersistenceImpl<ScheduleException>
	implements ScheduleExceptionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ScheduleExceptionUtil} to access the schedule exception persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ScheduleExceptionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionModelImpl.FINDER_CACHE_ENABLED,
			ScheduleExceptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionModelImpl.FINDER_CACHE_ENABLED,
			ScheduleExceptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionModelImpl.FINDER_CACHE_ENABLED,
			ScheduleExceptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionModelImpl.FINDER_CACHE_ENABLED,
			ScheduleExceptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ScheduleExceptionModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the schedule exceptions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching schedule exceptions
	 */
	@Override
	public List<ScheduleException> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the schedule exceptions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of schedule exceptions
	 * @param end the upper bound of the range of schedule exceptions (not inclusive)
	 * @return the range of matching schedule exceptions
	 */
	@Override
	public List<ScheduleException> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the schedule exceptions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of schedule exceptions
	 * @param end the upper bound of the range of schedule exceptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schedule exceptions
	 */
	@Override
	public List<ScheduleException> findByUuid(String uuid, int start, int end,
		OrderByComparator<ScheduleException> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the schedule exceptions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of schedule exceptions
	 * @param end the upper bound of the range of schedule exceptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching schedule exceptions
	 */
	@Override
	public List<ScheduleException> findByUuid(String uuid, int start, int end,
		OrderByComparator<ScheduleException> orderByComparator,
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

		List<ScheduleException> list = null;

		if (retrieveFromCache) {
			list = (List<ScheduleException>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScheduleException scheduleException : list) {
					if (!Objects.equals(uuid, scheduleException.getUuid())) {
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

			query.append(_SQL_SELECT_SCHEDULEEXCEPTION_WHERE);

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
				query.append(ScheduleExceptionModelImpl.ORDER_BY_JPQL);
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
					list = (List<ScheduleException>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScheduleException>)QueryUtil.list(q,
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
	 * Returns the first schedule exception in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schedule exception
	 * @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	 */
	@Override
	public ScheduleException findByUuid_First(String uuid,
		OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException {
		ScheduleException scheduleException = fetchByUuid_First(uuid,
				orderByComparator);

		if (scheduleException != null) {
			return scheduleException;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScheduleExceptionException(msg.toString());
	}

	/**
	 * Returns the first schedule exception in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	 */
	@Override
	public ScheduleException fetchByUuid_First(String uuid,
		OrderByComparator<ScheduleException> orderByComparator) {
		List<ScheduleException> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last schedule exception in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schedule exception
	 * @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	 */
	@Override
	public ScheduleException findByUuid_Last(String uuid,
		OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException {
		ScheduleException scheduleException = fetchByUuid_Last(uuid,
				orderByComparator);

		if (scheduleException != null) {
			return scheduleException;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScheduleExceptionException(msg.toString());
	}

	/**
	 * Returns the last schedule exception in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	 */
	@Override
	public ScheduleException fetchByUuid_Last(String uuid,
		OrderByComparator<ScheduleException> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ScheduleException> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the schedule exceptions before and after the current schedule exception in the ordered set where uuid = &#63;.
	 *
	 * @param exceptionId the primary key of the current schedule exception
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schedule exception
	 * @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	 */
	@Override
	public ScheduleException[] findByUuid_PrevAndNext(long exceptionId,
		String uuid, OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException {
		ScheduleException scheduleException = findByPrimaryKey(exceptionId);

		Session session = null;

		try {
			session = openSession();

			ScheduleException[] array = new ScheduleExceptionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, scheduleException, uuid,
					orderByComparator, true);

			array[1] = scheduleException;

			array[2] = getByUuid_PrevAndNext(session, scheduleException, uuid,
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

	protected ScheduleException getByUuid_PrevAndNext(Session session,
		ScheduleException scheduleException, String uuid,
		OrderByComparator<ScheduleException> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCHEDULEEXCEPTION_WHERE);

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
			query.append(ScheduleExceptionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(scheduleException);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduleException> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the schedule exceptions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ScheduleException scheduleException : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scheduleException);
		}
	}

	/**
	 * Returns the number of schedule exceptions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching schedule exceptions
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCHEDULEEXCEPTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "scheduleException.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "scheduleException.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(scheduleException.uuid IS NULL OR scheduleException.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PLACEID = new FinderPath(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionModelImpl.FINDER_CACHE_ENABLED,
			ScheduleExceptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPlaceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLACEID =
		new FinderPath(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionModelImpl.FINDER_CACHE_ENABLED,
			ScheduleExceptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlaceId",
			new String[] { Long.class.getName() },
			ScheduleExceptionModelImpl.PLACEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PLACEID = new FinderPath(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlaceId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the schedule exceptions where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @return the matching schedule exceptions
	 */
	@Override
	public List<ScheduleException> findByPlaceId(long placeId) {
		return findByPlaceId(placeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the schedule exceptions where placeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param placeId the place ID
	 * @param start the lower bound of the range of schedule exceptions
	 * @param end the upper bound of the range of schedule exceptions (not inclusive)
	 * @return the range of matching schedule exceptions
	 */
	@Override
	public List<ScheduleException> findByPlaceId(long placeId, int start,
		int end) {
		return findByPlaceId(placeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the schedule exceptions where placeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param placeId the place ID
	 * @param start the lower bound of the range of schedule exceptions
	 * @param end the upper bound of the range of schedule exceptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schedule exceptions
	 */
	@Override
	public List<ScheduleException> findByPlaceId(long placeId, int start,
		int end, OrderByComparator<ScheduleException> orderByComparator) {
		return findByPlaceId(placeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the schedule exceptions where placeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param placeId the place ID
	 * @param start the lower bound of the range of schedule exceptions
	 * @param end the upper bound of the range of schedule exceptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching schedule exceptions
	 */
	@Override
	public List<ScheduleException> findByPlaceId(long placeId, int start,
		int end, OrderByComparator<ScheduleException> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLACEID;
			finderArgs = new Object[] { placeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PLACEID;
			finderArgs = new Object[] { placeId, start, end, orderByComparator };
		}

		List<ScheduleException> list = null;

		if (retrieveFromCache) {
			list = (List<ScheduleException>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScheduleException scheduleException : list) {
					if ((placeId != scheduleException.getPlaceId())) {
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

			query.append(_SQL_SELECT_SCHEDULEEXCEPTION_WHERE);

			query.append(_FINDER_COLUMN_PLACEID_PLACEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScheduleExceptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(placeId);

				if (!pagination) {
					list = (List<ScheduleException>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScheduleException>)QueryUtil.list(q,
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
	 * Returns the first schedule exception in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schedule exception
	 * @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	 */
	@Override
	public ScheduleException findByPlaceId_First(long placeId,
		OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException {
		ScheduleException scheduleException = fetchByPlaceId_First(placeId,
				orderByComparator);

		if (scheduleException != null) {
			return scheduleException;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("placeId=");
		msg.append(placeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScheduleExceptionException(msg.toString());
	}

	/**
	 * Returns the first schedule exception in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	 */
	@Override
	public ScheduleException fetchByPlaceId_First(long placeId,
		OrderByComparator<ScheduleException> orderByComparator) {
		List<ScheduleException> list = findByPlaceId(placeId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last schedule exception in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schedule exception
	 * @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	 */
	@Override
	public ScheduleException findByPlaceId_Last(long placeId,
		OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException {
		ScheduleException scheduleException = fetchByPlaceId_Last(placeId,
				orderByComparator);

		if (scheduleException != null) {
			return scheduleException;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("placeId=");
		msg.append(placeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScheduleExceptionException(msg.toString());
	}

	/**
	 * Returns the last schedule exception in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	 */
	@Override
	public ScheduleException fetchByPlaceId_Last(long placeId,
		OrderByComparator<ScheduleException> orderByComparator) {
		int count = countByPlaceId(placeId);

		if (count == 0) {
			return null;
		}

		List<ScheduleException> list = findByPlaceId(placeId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the schedule exceptions before and after the current schedule exception in the ordered set where placeId = &#63;.
	 *
	 * @param exceptionId the primary key of the current schedule exception
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schedule exception
	 * @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	 */
	@Override
	public ScheduleException[] findByPlaceId_PrevAndNext(long exceptionId,
		long placeId, OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException {
		ScheduleException scheduleException = findByPrimaryKey(exceptionId);

		Session session = null;

		try {
			session = openSession();

			ScheduleException[] array = new ScheduleExceptionImpl[3];

			array[0] = getByPlaceId_PrevAndNext(session, scheduleException,
					placeId, orderByComparator, true);

			array[1] = scheduleException;

			array[2] = getByPlaceId_PrevAndNext(session, scheduleException,
					placeId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScheduleException getByPlaceId_PrevAndNext(Session session,
		ScheduleException scheduleException, long placeId,
		OrderByComparator<ScheduleException> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCHEDULEEXCEPTION_WHERE);

		query.append(_FINDER_COLUMN_PLACEID_PLACEID_2);

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
			query.append(ScheduleExceptionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(placeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scheduleException);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduleException> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the schedule exceptions where placeId = &#63; from the database.
	 *
	 * @param placeId the place ID
	 */
	@Override
	public void removeByPlaceId(long placeId) {
		for (ScheduleException scheduleException : findByPlaceId(placeId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scheduleException);
		}
	}

	/**
	 * Returns the number of schedule exceptions where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @return the number of matching schedule exceptions
	 */
	@Override
	public int countByPlaceId(long placeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PLACEID;

		Object[] finderArgs = new Object[] { placeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCHEDULEEXCEPTION_WHERE);

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

	private static final String _FINDER_COLUMN_PLACEID_PLACEID_2 = "scheduleException.placeId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUBPLACEID =
		new FinderPath(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionModelImpl.FINDER_CACHE_ENABLED,
			ScheduleExceptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySubPlaceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPLACEID =
		new FinderPath(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionModelImpl.FINDER_CACHE_ENABLED,
			ScheduleExceptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySubPlaceId",
			new String[] { Long.class.getName() },
			ScheduleExceptionModelImpl.SUBPLACEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUBPLACEID = new FinderPath(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySubPlaceId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the schedule exceptions where subPlaceId = &#63;.
	 *
	 * @param subPlaceId the sub place ID
	 * @return the matching schedule exceptions
	 */
	@Override
	public List<ScheduleException> findBySubPlaceId(long subPlaceId) {
		return findBySubPlaceId(subPlaceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the schedule exceptions where subPlaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param subPlaceId the sub place ID
	 * @param start the lower bound of the range of schedule exceptions
	 * @param end the upper bound of the range of schedule exceptions (not inclusive)
	 * @return the range of matching schedule exceptions
	 */
	@Override
	public List<ScheduleException> findBySubPlaceId(long subPlaceId, int start,
		int end) {
		return findBySubPlaceId(subPlaceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the schedule exceptions where subPlaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param subPlaceId the sub place ID
	 * @param start the lower bound of the range of schedule exceptions
	 * @param end the upper bound of the range of schedule exceptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching schedule exceptions
	 */
	@Override
	public List<ScheduleException> findBySubPlaceId(long subPlaceId, int start,
		int end, OrderByComparator<ScheduleException> orderByComparator) {
		return findBySubPlaceId(subPlaceId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the schedule exceptions where subPlaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param subPlaceId the sub place ID
	 * @param start the lower bound of the range of schedule exceptions
	 * @param end the upper bound of the range of schedule exceptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching schedule exceptions
	 */
	@Override
	public List<ScheduleException> findBySubPlaceId(long subPlaceId, int start,
		int end, OrderByComparator<ScheduleException> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPLACEID;
			finderArgs = new Object[] { subPlaceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUBPLACEID;
			finderArgs = new Object[] { subPlaceId, start, end, orderByComparator };
		}

		List<ScheduleException> list = null;

		if (retrieveFromCache) {
			list = (List<ScheduleException>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScheduleException scheduleException : list) {
					if ((subPlaceId != scheduleException.getSubPlaceId())) {
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

			query.append(_SQL_SELECT_SCHEDULEEXCEPTION_WHERE);

			query.append(_FINDER_COLUMN_SUBPLACEID_SUBPLACEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScheduleExceptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(subPlaceId);

				if (!pagination) {
					list = (List<ScheduleException>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScheduleException>)QueryUtil.list(q,
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
	 * Returns the first schedule exception in the ordered set where subPlaceId = &#63;.
	 *
	 * @param subPlaceId the sub place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schedule exception
	 * @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	 */
	@Override
	public ScheduleException findBySubPlaceId_First(long subPlaceId,
		OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException {
		ScheduleException scheduleException = fetchBySubPlaceId_First(subPlaceId,
				orderByComparator);

		if (scheduleException != null) {
			return scheduleException;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("subPlaceId=");
		msg.append(subPlaceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScheduleExceptionException(msg.toString());
	}

	/**
	 * Returns the first schedule exception in the ordered set where subPlaceId = &#63;.
	 *
	 * @param subPlaceId the sub place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	 */
	@Override
	public ScheduleException fetchBySubPlaceId_First(long subPlaceId,
		OrderByComparator<ScheduleException> orderByComparator) {
		List<ScheduleException> list = findBySubPlaceId(subPlaceId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last schedule exception in the ordered set where subPlaceId = &#63;.
	 *
	 * @param subPlaceId the sub place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schedule exception
	 * @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	 */
	@Override
	public ScheduleException findBySubPlaceId_Last(long subPlaceId,
		OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException {
		ScheduleException scheduleException = fetchBySubPlaceId_Last(subPlaceId,
				orderByComparator);

		if (scheduleException != null) {
			return scheduleException;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("subPlaceId=");
		msg.append(subPlaceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScheduleExceptionException(msg.toString());
	}

	/**
	 * Returns the last schedule exception in the ordered set where subPlaceId = &#63;.
	 *
	 * @param subPlaceId the sub place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	 */
	@Override
	public ScheduleException fetchBySubPlaceId_Last(long subPlaceId,
		OrderByComparator<ScheduleException> orderByComparator) {
		int count = countBySubPlaceId(subPlaceId);

		if (count == 0) {
			return null;
		}

		List<ScheduleException> list = findBySubPlaceId(subPlaceId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the schedule exceptions before and after the current schedule exception in the ordered set where subPlaceId = &#63;.
	 *
	 * @param exceptionId the primary key of the current schedule exception
	 * @param subPlaceId the sub place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next schedule exception
	 * @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	 */
	@Override
	public ScheduleException[] findBySubPlaceId_PrevAndNext(long exceptionId,
		long subPlaceId, OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException {
		ScheduleException scheduleException = findByPrimaryKey(exceptionId);

		Session session = null;

		try {
			session = openSession();

			ScheduleException[] array = new ScheduleExceptionImpl[3];

			array[0] = getBySubPlaceId_PrevAndNext(session, scheduleException,
					subPlaceId, orderByComparator, true);

			array[1] = scheduleException;

			array[2] = getBySubPlaceId_PrevAndNext(session, scheduleException,
					subPlaceId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScheduleException getBySubPlaceId_PrevAndNext(Session session,
		ScheduleException scheduleException, long subPlaceId,
		OrderByComparator<ScheduleException> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCHEDULEEXCEPTION_WHERE);

		query.append(_FINDER_COLUMN_SUBPLACEID_SUBPLACEID_2);

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
			query.append(ScheduleExceptionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(subPlaceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scheduleException);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduleException> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the schedule exceptions where subPlaceId = &#63; from the database.
	 *
	 * @param subPlaceId the sub place ID
	 */
	@Override
	public void removeBySubPlaceId(long subPlaceId) {
		for (ScheduleException scheduleException : findBySubPlaceId(
				subPlaceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scheduleException);
		}
	}

	/**
	 * Returns the number of schedule exceptions where subPlaceId = &#63;.
	 *
	 * @param subPlaceId the sub place ID
	 * @return the number of matching schedule exceptions
	 */
	@Override
	public int countBySubPlaceId(long subPlaceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SUBPLACEID;

		Object[] finderArgs = new Object[] { subPlaceId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCHEDULEEXCEPTION_WHERE);

			query.append(_FINDER_COLUMN_SUBPLACEID_SUBPLACEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(subPlaceId);

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

	private static final String _FINDER_COLUMN_SUBPLACEID_SUBPLACEID_2 = "scheduleException.subPlaceId = ?";

	public ScheduleExceptionPersistenceImpl() {
		setModelClass(ScheduleException.class);
	}

	/**
	 * Caches the schedule exception in the entity cache if it is enabled.
	 *
	 * @param scheduleException the schedule exception
	 */
	@Override
	public void cacheResult(ScheduleException scheduleException) {
		entityCache.putResult(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionImpl.class, scheduleException.getPrimaryKey(),
			scheduleException);

		scheduleException.resetOriginalValues();
	}

	/**
	 * Caches the schedule exceptions in the entity cache if it is enabled.
	 *
	 * @param scheduleExceptions the schedule exceptions
	 */
	@Override
	public void cacheResult(List<ScheduleException> scheduleExceptions) {
		for (ScheduleException scheduleException : scheduleExceptions) {
			if (entityCache.getResult(
						ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
						ScheduleExceptionImpl.class,
						scheduleException.getPrimaryKey()) == null) {
				cacheResult(scheduleException);
			}
			else {
				scheduleException.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all schedule exceptions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ScheduleExceptionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the schedule exception.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ScheduleException scheduleException) {
		entityCache.removeResult(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionImpl.class, scheduleException.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ScheduleException> scheduleExceptions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ScheduleException scheduleException : scheduleExceptions) {
			entityCache.removeResult(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
				ScheduleExceptionImpl.class, scheduleException.getPrimaryKey());
		}
	}

	/**
	 * Creates a new schedule exception with the primary key. Does not add the schedule exception to the database.
	 *
	 * @param exceptionId the primary key for the new schedule exception
	 * @return the new schedule exception
	 */
	@Override
	public ScheduleException create(long exceptionId) {
		ScheduleException scheduleException = new ScheduleExceptionImpl();

		scheduleException.setNew(true);
		scheduleException.setPrimaryKey(exceptionId);

		String uuid = PortalUUIDUtil.generate();

		scheduleException.setUuid(uuid);

		return scheduleException;
	}

	/**
	 * Removes the schedule exception with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param exceptionId the primary key of the schedule exception
	 * @return the schedule exception that was removed
	 * @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	 */
	@Override
	public ScheduleException remove(long exceptionId)
		throws NoSuchScheduleExceptionException {
		return remove((Serializable)exceptionId);
	}

	/**
	 * Removes the schedule exception with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the schedule exception
	 * @return the schedule exception that was removed
	 * @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	 */
	@Override
	public ScheduleException remove(Serializable primaryKey)
		throws NoSuchScheduleExceptionException {
		Session session = null;

		try {
			session = openSession();

			ScheduleException scheduleException = (ScheduleException)session.get(ScheduleExceptionImpl.class,
					primaryKey);

			if (scheduleException == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchScheduleExceptionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(scheduleException);
		}
		catch (NoSuchScheduleExceptionException nsee) {
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
	protected ScheduleException removeImpl(ScheduleException scheduleException) {
		scheduleException = toUnwrappedModel(scheduleException);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(scheduleException)) {
				scheduleException = (ScheduleException)session.get(ScheduleExceptionImpl.class,
						scheduleException.getPrimaryKeyObj());
			}

			if (scheduleException != null) {
				session.delete(scheduleException);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (scheduleException != null) {
			clearCache(scheduleException);
		}

		return scheduleException;
	}

	@Override
	public ScheduleException updateImpl(ScheduleException scheduleException) {
		scheduleException = toUnwrappedModel(scheduleException);

		boolean isNew = scheduleException.isNew();

		ScheduleExceptionModelImpl scheduleExceptionModelImpl = (ScheduleExceptionModelImpl)scheduleException;

		if (Validator.isNull(scheduleException.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			scheduleException.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (scheduleException.isNew()) {
				session.save(scheduleException);

				scheduleException.setNew(false);
			}
			else {
				scheduleException = (ScheduleException)session.merge(scheduleException);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ScheduleExceptionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((scheduleExceptionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scheduleExceptionModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { scheduleExceptionModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((scheduleExceptionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLACEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scheduleExceptionModelImpl.getOriginalPlaceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PLACEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLACEID,
					args);

				args = new Object[] { scheduleExceptionModelImpl.getPlaceId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PLACEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLACEID,
					args);
			}

			if ((scheduleExceptionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPLACEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scheduleExceptionModelImpl.getOriginalSubPlaceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUBPLACEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPLACEID,
					args);

				args = new Object[] { scheduleExceptionModelImpl.getSubPlaceId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUBPLACEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPLACEID,
					args);
			}
		}

		entityCache.putResult(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleExceptionImpl.class, scheduleException.getPrimaryKey(),
			scheduleException, false);

		scheduleException.resetOriginalValues();

		return scheduleException;
	}

	protected ScheduleException toUnwrappedModel(
		ScheduleException scheduleException) {
		if (scheduleException instanceof ScheduleExceptionImpl) {
			return scheduleException;
		}

		ScheduleExceptionImpl scheduleExceptionImpl = new ScheduleExceptionImpl();

		scheduleExceptionImpl.setNew(scheduleException.isNew());
		scheduleExceptionImpl.setPrimaryKey(scheduleException.getPrimaryKey());

		scheduleExceptionImpl.setUuid(scheduleException.getUuid());
		scheduleExceptionImpl.setExceptionId(scheduleException.getExceptionId());
		scheduleExceptionImpl.setStartDate(scheduleException.getStartDate());
		scheduleExceptionImpl.setEndDate(scheduleException.getEndDate());
		scheduleExceptionImpl.setStartHour(scheduleException.getStartHour());
		scheduleExceptionImpl.setEndHour(scheduleException.getEndHour());
		scheduleExceptionImpl.setComment(scheduleException.getComment());
		scheduleExceptionImpl.setClosed(scheduleException.isClosed());
		scheduleExceptionImpl.setPlaceId(scheduleException.getPlaceId());
		scheduleExceptionImpl.setSubPlaceId(scheduleException.getSubPlaceId());

		return scheduleExceptionImpl;
	}

	/**
	 * Returns the schedule exception with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the schedule exception
	 * @return the schedule exception
	 * @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	 */
	@Override
	public ScheduleException findByPrimaryKey(Serializable primaryKey)
		throws NoSuchScheduleExceptionException {
		ScheduleException scheduleException = fetchByPrimaryKey(primaryKey);

		if (scheduleException == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchScheduleExceptionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return scheduleException;
	}

	/**
	 * Returns the schedule exception with the primary key or throws a {@link NoSuchScheduleExceptionException} if it could not be found.
	 *
	 * @param exceptionId the primary key of the schedule exception
	 * @return the schedule exception
	 * @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	 */
	@Override
	public ScheduleException findByPrimaryKey(long exceptionId)
		throws NoSuchScheduleExceptionException {
		return findByPrimaryKey((Serializable)exceptionId);
	}

	/**
	 * Returns the schedule exception with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the schedule exception
	 * @return the schedule exception, or <code>null</code> if a schedule exception with the primary key could not be found
	 */
	@Override
	public ScheduleException fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
				ScheduleExceptionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ScheduleException scheduleException = (ScheduleException)serializable;

		if (scheduleException == null) {
			Session session = null;

			try {
				session = openSession();

				scheduleException = (ScheduleException)session.get(ScheduleExceptionImpl.class,
						primaryKey);

				if (scheduleException != null) {
					cacheResult(scheduleException);
				}
				else {
					entityCache.putResult(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
						ScheduleExceptionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
					ScheduleExceptionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return scheduleException;
	}

	/**
	 * Returns the schedule exception with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param exceptionId the primary key of the schedule exception
	 * @return the schedule exception, or <code>null</code> if a schedule exception with the primary key could not be found
	 */
	@Override
	public ScheduleException fetchByPrimaryKey(long exceptionId) {
		return fetchByPrimaryKey((Serializable)exceptionId);
	}

	@Override
	public Map<Serializable, ScheduleException> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ScheduleException> map = new HashMap<Serializable, ScheduleException>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ScheduleException scheduleException = fetchByPrimaryKey(primaryKey);

			if (scheduleException != null) {
				map.put(primaryKey, scheduleException);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
					ScheduleExceptionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ScheduleException)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SCHEDULEEXCEPTION_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (ScheduleException scheduleException : (List<ScheduleException>)q.list()) {
				map.put(scheduleException.getPrimaryKeyObj(), scheduleException);

				cacheResult(scheduleException);

				uncachedPrimaryKeys.remove(scheduleException.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ScheduleExceptionModelImpl.ENTITY_CACHE_ENABLED,
					ScheduleExceptionImpl.class, primaryKey, nullModel);
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
	 * Returns all the schedule exceptions.
	 *
	 * @return the schedule exceptions
	 */
	@Override
	public List<ScheduleException> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the schedule exceptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedule exceptions
	 * @param end the upper bound of the range of schedule exceptions (not inclusive)
	 * @return the range of schedule exceptions
	 */
	@Override
	public List<ScheduleException> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the schedule exceptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedule exceptions
	 * @param end the upper bound of the range of schedule exceptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of schedule exceptions
	 */
	@Override
	public List<ScheduleException> findAll(int start, int end,
		OrderByComparator<ScheduleException> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the schedule exceptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedule exceptions
	 * @param end the upper bound of the range of schedule exceptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of schedule exceptions
	 */
	@Override
	public List<ScheduleException> findAll(int start, int end,
		OrderByComparator<ScheduleException> orderByComparator,
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

		List<ScheduleException> list = null;

		if (retrieveFromCache) {
			list = (List<ScheduleException>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SCHEDULEEXCEPTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SCHEDULEEXCEPTION;

				if (pagination) {
					sql = sql.concat(ScheduleExceptionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ScheduleException>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScheduleException>)QueryUtil.list(q,
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
	 * Removes all the schedule exceptions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ScheduleException scheduleException : findAll()) {
			remove(scheduleException);
		}
	}

	/**
	 * Returns the number of schedule exceptions.
	 *
	 * @return the number of schedule exceptions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SCHEDULEEXCEPTION);

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
		return ScheduleExceptionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the schedule exception persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ScheduleExceptionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SCHEDULEEXCEPTION = "SELECT scheduleException FROM ScheduleException scheduleException";
	private static final String _SQL_SELECT_SCHEDULEEXCEPTION_WHERE_PKS_IN = "SELECT scheduleException FROM ScheduleException scheduleException WHERE exceptionId IN (";
	private static final String _SQL_SELECT_SCHEDULEEXCEPTION_WHERE = "SELECT scheduleException FROM ScheduleException scheduleException WHERE ";
	private static final String _SQL_COUNT_SCHEDULEEXCEPTION = "SELECT COUNT(scheduleException) FROM ScheduleException scheduleException";
	private static final String _SQL_COUNT_SCHEDULEEXCEPTION_WHERE = "SELECT COUNT(scheduleException) FROM ScheduleException scheduleException WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "scheduleException.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ScheduleException exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ScheduleException exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ScheduleExceptionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "comment"
			});
}