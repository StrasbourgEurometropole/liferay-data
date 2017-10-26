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

package eu.strasbourg.service.activity.service.persistence.impl;

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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.activity.exception.NoSuchActivityCourseScheduleException;
import eu.strasbourg.service.activity.model.ActivityCourseSchedule;
import eu.strasbourg.service.activity.model.impl.ActivityCourseScheduleImpl;
import eu.strasbourg.service.activity.model.impl.ActivityCourseScheduleModelImpl;
import eu.strasbourg.service.activity.service.persistence.ActivityCourseSchedulePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

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
 * The persistence implementation for the activity course schedule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCourseSchedulePersistence
 * @see eu.strasbourg.service.activity.service.persistence.ActivityCourseScheduleUtil
 * @generated
 */
@ProviderType
public class ActivityCourseSchedulePersistenceImpl extends BasePersistenceImpl<ActivityCourseSchedule>
	implements ActivityCourseSchedulePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ActivityCourseScheduleUtil} to access the activity course schedule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ActivityCourseScheduleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ActivityCourseScheduleModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the activity course schedules where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity course schedules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @return the range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid(String uuid, int start,
		int end, OrderByComparator<ActivityCourseSchedule> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid(String uuid, int start,
		int end, OrderByComparator<ActivityCourseSchedule> orderByComparator,
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

		List<ActivityCourseSchedule> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCourseSchedule>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCourseSchedule activityCourseSchedule : list) {
					if (!Objects.equals(uuid, activityCourseSchedule.getUuid())) {
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

			query.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

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
				query.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
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
					list = (List<ActivityCourseSchedule>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCourseSchedule>)QueryUtil.list(q,
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
	 * Returns the first activity course schedule in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule findByUuid_First(String uuid,
		OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = fetchByUuid_First(uuid,
				orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseScheduleException(msg.toString());
	}

	/**
	 * Returns the first activity course schedule in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByUuid_First(String uuid,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {
		List<ActivityCourseSchedule> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity course schedule in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule findByUuid_Last(String uuid,
		OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = fetchByUuid_Last(uuid,
				orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseScheduleException(msg.toString());
	}

	/**
	 * Returns the last activity course schedule in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByUuid_Last(String uuid,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ActivityCourseSchedule> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activity course schedules before and after the current activity course schedule in the ordered set where uuid = &#63;.
	 *
	 * @param activityCourseScheduleId the primary key of the current activity course schedule
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	 */
	@Override
	public ActivityCourseSchedule[] findByUuid_PrevAndNext(
		long activityCourseScheduleId, String uuid,
		OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = findByPrimaryKey(activityCourseScheduleId);

		Session session = null;

		try {
			session = openSession();

			ActivityCourseSchedule[] array = new ActivityCourseScheduleImpl[3];

			array[0] = getByUuid_PrevAndNext(session, activityCourseSchedule,
					uuid, orderByComparator, true);

			array[1] = activityCourseSchedule;

			array[2] = getByUuid_PrevAndNext(session, activityCourseSchedule,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActivityCourseSchedule getByUuid_PrevAndNext(Session session,
		ActivityCourseSchedule activityCourseSchedule, String uuid,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
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

		query.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

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
			query.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(activityCourseSchedule);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActivityCourseSchedule> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activity course schedules where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ActivityCourseSchedule activityCourseSchedule : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(activityCourseSchedule);
		}
	}

	/**
	 * Returns the number of activity course schedules where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching activity course schedules
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIVITYCOURSESCHEDULE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "activityCourseSchedule.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "activityCourseSchedule.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(activityCourseSchedule.uuid IS NULL OR activityCourseSchedule.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ActivityCourseScheduleModelImpl.UUID_COLUMN_BITMASK |
			ActivityCourseScheduleModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the activity course schedule where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchActivityCourseScheduleException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule findByUUID_G(String uuid, long groupId)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = fetchByUUID_G(uuid,
				groupId);

		if (activityCourseSchedule == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchActivityCourseScheduleException(msg.toString());
		}

		return activityCourseSchedule;
	}

	/**
	 * Returns the activity course schedule where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the activity course schedule where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ActivityCourseSchedule) {
			ActivityCourseSchedule activityCourseSchedule = (ActivityCourseSchedule)result;

			if (!Objects.equals(uuid, activityCourseSchedule.getUuid()) ||
					(groupId != activityCourseSchedule.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<ActivityCourseSchedule> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ActivityCourseSchedule activityCourseSchedule = list.get(0);

					result = activityCourseSchedule;

					cacheResult(activityCourseSchedule);

					if ((activityCourseSchedule.getUuid() == null) ||
							!activityCourseSchedule.getUuid().equals(uuid) ||
							(activityCourseSchedule.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, activityCourseSchedule);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

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
			return (ActivityCourseSchedule)result;
		}
	}

	/**
	 * Removes the activity course schedule where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the activity course schedule that was removed
	 */
	@Override
	public ActivityCourseSchedule removeByUUID_G(String uuid, long groupId)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = findByUUID_G(uuid,
				groupId);

		return remove(activityCourseSchedule);
	}

	/**
	 * Returns the number of activity course schedules where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching activity course schedules
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACTIVITYCOURSESCHEDULE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "activityCourseSchedule.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "activityCourseSchedule.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(activityCourseSchedule.uuid IS NULL OR activityCourseSchedule.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "activityCourseSchedule.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ActivityCourseScheduleModelImpl.UUID_COLUMN_BITMASK |
			ActivityCourseScheduleModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the activity course schedules where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity course schedules where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @return the range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<ActivityCourseSchedule> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCourseSchedule>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCourseSchedule activityCourseSchedule : list) {
					if (!Objects.equals(uuid, activityCourseSchedule.getUuid()) ||
							(companyId != activityCourseSchedule.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
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

				qPos.add(companyId);

				if (!pagination) {
					list = (List<ActivityCourseSchedule>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCourseSchedule>)QueryUtil.list(q,
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
	 * Returns the first activity course schedule in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseScheduleException(msg.toString());
	}

	/**
	 * Returns the first activity course schedule in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {
		List<ActivityCourseSchedule> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity course schedule in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseScheduleException(msg.toString());
	}

	/**
	 * Returns the last activity course schedule in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ActivityCourseSchedule> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activity course schedules before and after the current activity course schedule in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param activityCourseScheduleId the primary key of the current activity course schedule
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	 */
	@Override
	public ActivityCourseSchedule[] findByUuid_C_PrevAndNext(
		long activityCourseScheduleId, String uuid, long companyId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = findByPrimaryKey(activityCourseScheduleId);

		Session session = null;

		try {
			session = openSession();

			ActivityCourseSchedule[] array = new ActivityCourseScheduleImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, activityCourseSchedule,
					uuid, companyId, orderByComparator, true);

			array[1] = activityCourseSchedule;

			array[2] = getByUuid_C_PrevAndNext(session, activityCourseSchedule,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActivityCourseSchedule getByUuid_C_PrevAndNext(Session session,
		ActivityCourseSchedule activityCourseSchedule, String uuid,
		long companyId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(activityCourseSchedule);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActivityCourseSchedule> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activity course schedules where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ActivityCourseSchedule activityCourseSchedule : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(activityCourseSchedule);
		}
	}

	/**
	 * Returns the number of activity course schedules where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching activity course schedules
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACTIVITYCOURSESCHEDULE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "activityCourseSchedule.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "activityCourseSchedule.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(activityCourseSchedule.uuid IS NULL OR activityCourseSchedule.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "activityCourseSchedule.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ActivityCourseScheduleModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the activity course schedules where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity course schedules where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @return the range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByGroupId(long groupId, int start,
		int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ActivityCourseSchedule> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<ActivityCourseSchedule> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCourseSchedule>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCourseSchedule activityCourseSchedule : list) {
					if ((groupId != activityCourseSchedule.getGroupId())) {
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

			query.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ActivityCourseSchedule>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCourseSchedule>)QueryUtil.list(q,
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
	 * Returns the first activity course schedule in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule findByGroupId_First(long groupId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = fetchByGroupId_First(groupId,
				orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseScheduleException(msg.toString());
	}

	/**
	 * Returns the first activity course schedule in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByGroupId_First(long groupId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {
		List<ActivityCourseSchedule> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity course schedule in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule findByGroupId_Last(long groupId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseScheduleException(msg.toString());
	}

	/**
	 * Returns the last activity course schedule in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByGroupId_Last(long groupId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ActivityCourseSchedule> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activity course schedules before and after the current activity course schedule in the ordered set where groupId = &#63;.
	 *
	 * @param activityCourseScheduleId the primary key of the current activity course schedule
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	 */
	@Override
	public ActivityCourseSchedule[] findByGroupId_PrevAndNext(
		long activityCourseScheduleId, long groupId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = findByPrimaryKey(activityCourseScheduleId);

		Session session = null;

		try {
			session = openSession();

			ActivityCourseSchedule[] array = new ActivityCourseScheduleImpl[3];

			array[0] = getByGroupId_PrevAndNext(session,
					activityCourseSchedule, groupId, orderByComparator, true);

			array[1] = activityCourseSchedule;

			array[2] = getByGroupId_PrevAndNext(session,
					activityCourseSchedule, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActivityCourseSchedule getByGroupId_PrevAndNext(Session session,
		ActivityCourseSchedule activityCourseSchedule, long groupId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
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

		query.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(activityCourseSchedule);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActivityCourseSchedule> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activity course schedules where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (ActivityCourseSchedule activityCourseSchedule : findByGroupId(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(activityCourseSchedule);
		}
	}

	/**
	 * Returns the number of activity course schedules where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching activity course schedules
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIVITYCOURSESCHEDULE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "activityCourseSchedule.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYCOURSEPLACE =
		new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByActivityCoursePlace",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYCOURSEPLACE =
		new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByActivityCoursePlace", new String[] { Long.class.getName() },
			ActivityCourseScheduleModelImpl.ACTIVITYCOURSEPLACEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVITYCOURSEPLACE = new FinderPath(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByActivityCoursePlace", new String[] { Long.class.getName() });

	/**
	 * Returns all the activity course schedules where activityCoursePlaceId = &#63;.
	 *
	 * @param activityCoursePlaceId the activity course place ID
	 * @return the matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByActivityCoursePlace(
		long activityCoursePlaceId) {
		return findByActivityCoursePlace(activityCoursePlaceId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity course schedules where activityCoursePlaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param activityCoursePlaceId the activity course place ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @return the range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByActivityCoursePlace(
		long activityCoursePlaceId, int start, int end) {
		return findByActivityCoursePlace(activityCoursePlaceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where activityCoursePlaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param activityCoursePlaceId the activity course place ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByActivityCoursePlace(
		long activityCoursePlaceId, int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {
		return findByActivityCoursePlace(activityCoursePlaceId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where activityCoursePlaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param activityCoursePlaceId the activity course place ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByActivityCoursePlace(
		long activityCoursePlaceId, int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYCOURSEPLACE;
			finderArgs = new Object[] { activityCoursePlaceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYCOURSEPLACE;
			finderArgs = new Object[] {
					activityCoursePlaceId,
					
					start, end, orderByComparator
				};
		}

		List<ActivityCourseSchedule> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCourseSchedule>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCourseSchedule activityCourseSchedule : list) {
					if ((activityCoursePlaceId != activityCourseSchedule.getActivityCoursePlaceId())) {
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

			query.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

			query.append(_FINDER_COLUMN_ACTIVITYCOURSEPLACE_ACTIVITYCOURSEPLACEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(activityCoursePlaceId);

				if (!pagination) {
					list = (List<ActivityCourseSchedule>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCourseSchedule>)QueryUtil.list(q,
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
	 * Returns the first activity course schedule in the ordered set where activityCoursePlaceId = &#63;.
	 *
	 * @param activityCoursePlaceId the activity course place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule findByActivityCoursePlace_First(
		long activityCoursePlaceId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = fetchByActivityCoursePlace_First(activityCoursePlaceId,
				orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("activityCoursePlaceId=");
		msg.append(activityCoursePlaceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseScheduleException(msg.toString());
	}

	/**
	 * Returns the first activity course schedule in the ordered set where activityCoursePlaceId = &#63;.
	 *
	 * @param activityCoursePlaceId the activity course place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByActivityCoursePlace_First(
		long activityCoursePlaceId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {
		List<ActivityCourseSchedule> list = findByActivityCoursePlace(activityCoursePlaceId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity course schedule in the ordered set where activityCoursePlaceId = &#63;.
	 *
	 * @param activityCoursePlaceId the activity course place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule findByActivityCoursePlace_Last(
		long activityCoursePlaceId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = fetchByActivityCoursePlace_Last(activityCoursePlaceId,
				orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("activityCoursePlaceId=");
		msg.append(activityCoursePlaceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseScheduleException(msg.toString());
	}

	/**
	 * Returns the last activity course schedule in the ordered set where activityCoursePlaceId = &#63;.
	 *
	 * @param activityCoursePlaceId the activity course place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByActivityCoursePlace_Last(
		long activityCoursePlaceId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {
		int count = countByActivityCoursePlace(activityCoursePlaceId);

		if (count == 0) {
			return null;
		}

		List<ActivityCourseSchedule> list = findByActivityCoursePlace(activityCoursePlaceId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activity course schedules before and after the current activity course schedule in the ordered set where activityCoursePlaceId = &#63;.
	 *
	 * @param activityCourseScheduleId the primary key of the current activity course schedule
	 * @param activityCoursePlaceId the activity course place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	 */
	@Override
	public ActivityCourseSchedule[] findByActivityCoursePlace_PrevAndNext(
		long activityCourseScheduleId, long activityCoursePlaceId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = findByPrimaryKey(activityCourseScheduleId);

		Session session = null;

		try {
			session = openSession();

			ActivityCourseSchedule[] array = new ActivityCourseScheduleImpl[3];

			array[0] = getByActivityCoursePlace_PrevAndNext(session,
					activityCourseSchedule, activityCoursePlaceId,
					orderByComparator, true);

			array[1] = activityCourseSchedule;

			array[2] = getByActivityCoursePlace_PrevAndNext(session,
					activityCourseSchedule, activityCoursePlaceId,
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

	protected ActivityCourseSchedule getByActivityCoursePlace_PrevAndNext(
		Session session, ActivityCourseSchedule activityCourseSchedule,
		long activityCoursePlaceId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
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

		query.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

		query.append(_FINDER_COLUMN_ACTIVITYCOURSEPLACE_ACTIVITYCOURSEPLACEID_2);

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
			query.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(activityCoursePlaceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(activityCourseSchedule);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActivityCourseSchedule> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activity course schedules where activityCoursePlaceId = &#63; from the database.
	 *
	 * @param activityCoursePlaceId the activity course place ID
	 */
	@Override
	public void removeByActivityCoursePlace(long activityCoursePlaceId) {
		for (ActivityCourseSchedule activityCourseSchedule : findByActivityCoursePlace(
				activityCoursePlaceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(activityCourseSchedule);
		}
	}

	/**
	 * Returns the number of activity course schedules where activityCoursePlaceId = &#63;.
	 *
	 * @param activityCoursePlaceId the activity course place ID
	 * @return the number of matching activity course schedules
	 */
	@Override
	public int countByActivityCoursePlace(long activityCoursePlaceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVITYCOURSEPLACE;

		Object[] finderArgs = new Object[] { activityCoursePlaceId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIVITYCOURSESCHEDULE_WHERE);

			query.append(_FINDER_COLUMN_ACTIVITYCOURSEPLACE_ACTIVITYCOURSEPLACEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(activityCoursePlaceId);

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

	private static final String _FINDER_COLUMN_ACTIVITYCOURSEPLACE_ACTIVITYCOURSEPLACEID_2 =
		"activityCourseSchedule.activityCoursePlaceId = ?";

	public ActivityCourseSchedulePersistenceImpl() {
		setModelClass(ActivityCourseSchedule.class);

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
	 * Caches the activity course schedule in the entity cache if it is enabled.
	 *
	 * @param activityCourseSchedule the activity course schedule
	 */
	@Override
	public void cacheResult(ActivityCourseSchedule activityCourseSchedule) {
		entityCache.putResult(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			activityCourseSchedule.getPrimaryKey(), activityCourseSchedule);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				activityCourseSchedule.getUuid(),
				activityCourseSchedule.getGroupId()
			}, activityCourseSchedule);

		activityCourseSchedule.resetOriginalValues();
	}

	/**
	 * Caches the activity course schedules in the entity cache if it is enabled.
	 *
	 * @param activityCourseSchedules the activity course schedules
	 */
	@Override
	public void cacheResult(
		List<ActivityCourseSchedule> activityCourseSchedules) {
		for (ActivityCourseSchedule activityCourseSchedule : activityCourseSchedules) {
			if (entityCache.getResult(
						ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
						ActivityCourseScheduleImpl.class,
						activityCourseSchedule.getPrimaryKey()) == null) {
				cacheResult(activityCourseSchedule);
			}
			else {
				activityCourseSchedule.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all activity course schedules.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ActivityCourseScheduleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the activity course schedule.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ActivityCourseSchedule activityCourseSchedule) {
		entityCache.removeResult(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			activityCourseSchedule.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ActivityCourseScheduleModelImpl)activityCourseSchedule,
			true);
	}

	@Override
	public void clearCache(List<ActivityCourseSchedule> activityCourseSchedules) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ActivityCourseSchedule activityCourseSchedule : activityCourseSchedules) {
			entityCache.removeResult(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
				ActivityCourseScheduleImpl.class,
				activityCourseSchedule.getPrimaryKey());

			clearUniqueFindersCache((ActivityCourseScheduleModelImpl)activityCourseSchedule,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ActivityCourseScheduleModelImpl activityCourseScheduleModelImpl) {
		Object[] args = new Object[] {
				activityCourseScheduleModelImpl.getUuid(),
				activityCourseScheduleModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			activityCourseScheduleModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ActivityCourseScheduleModelImpl activityCourseScheduleModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					activityCourseScheduleModelImpl.getUuid(),
					activityCourseScheduleModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((activityCourseScheduleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					activityCourseScheduleModelImpl.getOriginalUuid(),
					activityCourseScheduleModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new activity course schedule with the primary key. Does not add the activity course schedule to the database.
	 *
	 * @param activityCourseScheduleId the primary key for the new activity course schedule
	 * @return the new activity course schedule
	 */
	@Override
	public ActivityCourseSchedule create(long activityCourseScheduleId) {
		ActivityCourseSchedule activityCourseSchedule = new ActivityCourseScheduleImpl();

		activityCourseSchedule.setNew(true);
		activityCourseSchedule.setPrimaryKey(activityCourseScheduleId);

		String uuid = PortalUUIDUtil.generate();

		activityCourseSchedule.setUuid(uuid);

		activityCourseSchedule.setCompanyId(companyProvider.getCompanyId());

		return activityCourseSchedule;
	}

	/**
	 * Removes the activity course schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param activityCourseScheduleId the primary key of the activity course schedule
	 * @return the activity course schedule that was removed
	 * @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	 */
	@Override
	public ActivityCourseSchedule remove(long activityCourseScheduleId)
		throws NoSuchActivityCourseScheduleException {
		return remove((Serializable)activityCourseScheduleId);
	}

	/**
	 * Removes the activity course schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the activity course schedule
	 * @return the activity course schedule that was removed
	 * @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	 */
	@Override
	public ActivityCourseSchedule remove(Serializable primaryKey)
		throws NoSuchActivityCourseScheduleException {
		Session session = null;

		try {
			session = openSession();

			ActivityCourseSchedule activityCourseSchedule = (ActivityCourseSchedule)session.get(ActivityCourseScheduleImpl.class,
					primaryKey);

			if (activityCourseSchedule == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActivityCourseScheduleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(activityCourseSchedule);
		}
		catch (NoSuchActivityCourseScheduleException nsee) {
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
	protected ActivityCourseSchedule removeImpl(
		ActivityCourseSchedule activityCourseSchedule) {
		activityCourseSchedule = toUnwrappedModel(activityCourseSchedule);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(activityCourseSchedule)) {
				activityCourseSchedule = (ActivityCourseSchedule)session.get(ActivityCourseScheduleImpl.class,
						activityCourseSchedule.getPrimaryKeyObj());
			}

			if (activityCourseSchedule != null) {
				session.delete(activityCourseSchedule);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (activityCourseSchedule != null) {
			clearCache(activityCourseSchedule);
		}

		return activityCourseSchedule;
	}

	@Override
	public ActivityCourseSchedule updateImpl(
		ActivityCourseSchedule activityCourseSchedule) {
		activityCourseSchedule = toUnwrappedModel(activityCourseSchedule);

		boolean isNew = activityCourseSchedule.isNew();

		ActivityCourseScheduleModelImpl activityCourseScheduleModelImpl = (ActivityCourseScheduleModelImpl)activityCourseSchedule;

		if (Validator.isNull(activityCourseSchedule.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			activityCourseSchedule.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (activityCourseSchedule.getCreateDate() == null)) {
			if (serviceContext == null) {
				activityCourseSchedule.setCreateDate(now);
			}
			else {
				activityCourseSchedule.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!activityCourseScheduleModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				activityCourseSchedule.setModifiedDate(now);
			}
			else {
				activityCourseSchedule.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (activityCourseSchedule.isNew()) {
				session.save(activityCourseSchedule);

				activityCourseSchedule.setNew(false);
			}
			else {
				activityCourseSchedule = (ActivityCourseSchedule)session.merge(activityCourseSchedule);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ActivityCourseScheduleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					activityCourseScheduleModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					activityCourseScheduleModelImpl.getUuid(),
					activityCourseScheduleModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { activityCourseScheduleModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					activityCourseScheduleModelImpl.getActivityCoursePlaceId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVITYCOURSEPLACE,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYCOURSEPLACE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((activityCourseScheduleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						activityCourseScheduleModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { activityCourseScheduleModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((activityCourseScheduleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						activityCourseScheduleModelImpl.getOriginalUuid(),
						activityCourseScheduleModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						activityCourseScheduleModelImpl.getUuid(),
						activityCourseScheduleModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((activityCourseScheduleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						activityCourseScheduleModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { activityCourseScheduleModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((activityCourseScheduleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYCOURSEPLACE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						activityCourseScheduleModelImpl.getOriginalActivityCoursePlaceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVITYCOURSEPLACE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYCOURSEPLACE,
					args);

				args = new Object[] {
						activityCourseScheduleModelImpl.getActivityCoursePlaceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVITYCOURSEPLACE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYCOURSEPLACE,
					args);
			}
		}

		entityCache.putResult(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			activityCourseSchedule.getPrimaryKey(), activityCourseSchedule,
			false);

		clearUniqueFindersCache(activityCourseScheduleModelImpl, false);
		cacheUniqueFindersCache(activityCourseScheduleModelImpl);

		activityCourseSchedule.resetOriginalValues();

		return activityCourseSchedule;
	}

	protected ActivityCourseSchedule toUnwrappedModel(
		ActivityCourseSchedule activityCourseSchedule) {
		if (activityCourseSchedule instanceof ActivityCourseScheduleImpl) {
			return activityCourseSchedule;
		}

		ActivityCourseScheduleImpl activityCourseScheduleImpl = new ActivityCourseScheduleImpl();

		activityCourseScheduleImpl.setNew(activityCourseSchedule.isNew());
		activityCourseScheduleImpl.setPrimaryKey(activityCourseSchedule.getPrimaryKey());

		activityCourseScheduleImpl.setUuid(activityCourseSchedule.getUuid());
		activityCourseScheduleImpl.setActivityCourseScheduleId(activityCourseSchedule.getActivityCourseScheduleId());
		activityCourseScheduleImpl.setGroupId(activityCourseSchedule.getGroupId());
		activityCourseScheduleImpl.setCompanyId(activityCourseSchedule.getCompanyId());
		activityCourseScheduleImpl.setUserId(activityCourseSchedule.getUserId());
		activityCourseScheduleImpl.setUserName(activityCourseSchedule.getUserName());
		activityCourseScheduleImpl.setCreateDate(activityCourseSchedule.getCreateDate());
		activityCourseScheduleImpl.setModifiedDate(activityCourseSchedule.getModifiedDate());
		activityCourseScheduleImpl.setActivityCoursePlaceId(activityCourseSchedule.getActivityCoursePlaceId());
		activityCourseScheduleImpl.setStartTime(activityCourseSchedule.getStartTime());
		activityCourseScheduleImpl.setEndTime(activityCourseSchedule.getEndTime());
		activityCourseScheduleImpl.setMonday(activityCourseSchedule.isMonday());
		activityCourseScheduleImpl.setTuesday(activityCourseSchedule.isTuesday());
		activityCourseScheduleImpl.setWednesday(activityCourseSchedule.isWednesday());
		activityCourseScheduleImpl.setThursday(activityCourseSchedule.isThursday());
		activityCourseScheduleImpl.setFriday(activityCourseSchedule.isFriday());
		activityCourseScheduleImpl.setSaturday(activityCourseSchedule.isSaturday());
		activityCourseScheduleImpl.setSunday(activityCourseSchedule.isSunday());
		activityCourseScheduleImpl.setComments(activityCourseSchedule.getComments());
		activityCourseScheduleImpl.setPeriodsIds(activityCourseSchedule.getPeriodsIds());

		return activityCourseScheduleImpl;
	}

	/**
	 * Returns the activity course schedule with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the activity course schedule
	 * @return the activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	 */
	@Override
	public ActivityCourseSchedule findByPrimaryKey(Serializable primaryKey)
		throws NoSuchActivityCourseScheduleException {
		ActivityCourseSchedule activityCourseSchedule = fetchByPrimaryKey(primaryKey);

		if (activityCourseSchedule == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchActivityCourseScheduleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return activityCourseSchedule;
	}

	/**
	 * Returns the activity course schedule with the primary key or throws a {@link NoSuchActivityCourseScheduleException} if it could not be found.
	 *
	 * @param activityCourseScheduleId the primary key of the activity course schedule
	 * @return the activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	 */
	@Override
	public ActivityCourseSchedule findByPrimaryKey(
		long activityCourseScheduleId)
		throws NoSuchActivityCourseScheduleException {
		return findByPrimaryKey((Serializable)activityCourseScheduleId);
	}

	/**
	 * Returns the activity course schedule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the activity course schedule
	 * @return the activity course schedule, or <code>null</code> if a activity course schedule with the primary key could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
				ActivityCourseScheduleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ActivityCourseSchedule activityCourseSchedule = (ActivityCourseSchedule)serializable;

		if (activityCourseSchedule == null) {
			Session session = null;

			try {
				session = openSession();

				activityCourseSchedule = (ActivityCourseSchedule)session.get(ActivityCourseScheduleImpl.class,
						primaryKey);

				if (activityCourseSchedule != null) {
					cacheResult(activityCourseSchedule);
				}
				else {
					entityCache.putResult(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
						ActivityCourseScheduleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
					ActivityCourseScheduleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return activityCourseSchedule;
	}

	/**
	 * Returns the activity course schedule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param activityCourseScheduleId the primary key of the activity course schedule
	 * @return the activity course schedule, or <code>null</code> if a activity course schedule with the primary key could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByPrimaryKey(
		long activityCourseScheduleId) {
		return fetchByPrimaryKey((Serializable)activityCourseScheduleId);
	}

	@Override
	public Map<Serializable, ActivityCourseSchedule> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ActivityCourseSchedule> map = new HashMap<Serializable, ActivityCourseSchedule>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ActivityCourseSchedule activityCourseSchedule = fetchByPrimaryKey(primaryKey);

			if (activityCourseSchedule != null) {
				map.put(primaryKey, activityCourseSchedule);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
					ActivityCourseScheduleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ActivityCourseSchedule)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE_PKS_IN);

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

			for (ActivityCourseSchedule activityCourseSchedule : (List<ActivityCourseSchedule>)q.list()) {
				map.put(activityCourseSchedule.getPrimaryKeyObj(),
					activityCourseSchedule);

				cacheResult(activityCourseSchedule);

				uncachedPrimaryKeys.remove(activityCourseSchedule.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
					ActivityCourseScheduleImpl.class, primaryKey, nullModel);
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
	 * Returns all the activity course schedules.
	 *
	 * @return the activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity course schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @return the range of activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findAll(int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findAll(int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
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

		List<ActivityCourseSchedule> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCourseSchedule>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACTIVITYCOURSESCHEDULE;

				if (pagination) {
					sql = sql.concat(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ActivityCourseSchedule>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCourseSchedule>)QueryUtil.list(q,
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
	 * Removes all the activity course schedules from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ActivityCourseSchedule activityCourseSchedule : findAll()) {
			remove(activityCourseSchedule);
		}
	}

	/**
	 * Returns the number of activity course schedules.
	 *
	 * @return the number of activity course schedules
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACTIVITYCOURSESCHEDULE);

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
		return ActivityCourseScheduleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the activity course schedule persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ActivityCourseScheduleImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ACTIVITYCOURSESCHEDULE = "SELECT activityCourseSchedule FROM ActivityCourseSchedule activityCourseSchedule";
	private static final String _SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE_PKS_IN = "SELECT activityCourseSchedule FROM ActivityCourseSchedule activityCourseSchedule WHERE activityCourseScheduleId IN (";
	private static final String _SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE = "SELECT activityCourseSchedule FROM ActivityCourseSchedule activityCourseSchedule WHERE ";
	private static final String _SQL_COUNT_ACTIVITYCOURSESCHEDULE = "SELECT COUNT(activityCourseSchedule) FROM ActivityCourseSchedule activityCourseSchedule";
	private static final String _SQL_COUNT_ACTIVITYCOURSESCHEDULE_WHERE = "SELECT COUNT(activityCourseSchedule) FROM ActivityCourseSchedule activityCourseSchedule WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "activityCourseSchedule.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ActivityCourseSchedule exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ActivityCourseSchedule exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ActivityCourseSchedulePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}