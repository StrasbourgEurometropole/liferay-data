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

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
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
import java.lang.reflect.InvocationHandler;

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
 * @generated
 */
public class ActivityCourseSchedulePersistenceImpl
	extends BasePersistenceImpl<ActivityCourseSchedule>
	implements ActivityCourseSchedulePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ActivityCourseScheduleUtil</code> to access the activity course schedule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ActivityCourseScheduleImpl.class.getName();

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @return the range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
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

		List<ActivityCourseSchedule> list = null;

		if (useFinderCache) {
			list = (List<ActivityCourseSchedule>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCourseSchedule activityCourseSchedule : list) {
					if (!uuid.equals(activityCourseSchedule.getUuid())) {
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

			sb.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

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
				sb.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
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

				list = (List<ActivityCourseSchedule>)QueryUtil.list(
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
	 * Returns the first activity course schedule in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule findByUuid_First(
			String uuid,
			OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {

		ActivityCourseSchedule activityCourseSchedule = fetchByUuid_First(
			uuid, orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchActivityCourseScheduleException(sb.toString());
	}

	/**
	 * Returns the first activity course schedule in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByUuid_First(
		String uuid,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {

		List<ActivityCourseSchedule> list = findByUuid(
			uuid, 0, 1, orderByComparator);

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
	public ActivityCourseSchedule findByUuid_Last(
			String uuid,
			OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {

		ActivityCourseSchedule activityCourseSchedule = fetchByUuid_Last(
			uuid, orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchActivityCourseScheduleException(sb.toString());
	}

	/**
	 * Returns the last activity course schedule in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByUuid_Last(
		String uuid,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ActivityCourseSchedule> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

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

		uuid = Objects.toString(uuid, "");

		ActivityCourseSchedule activityCourseSchedule = findByPrimaryKey(
			activityCourseScheduleId);

		Session session = null;

		try {
			session = openSession();

			ActivityCourseSchedule[] array = new ActivityCourseScheduleImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, activityCourseSchedule, uuid, orderByComparator, true);

			array[1] = activityCourseSchedule;

			array[2] = getByUuid_PrevAndNext(
				session, activityCourseSchedule, uuid, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActivityCourseSchedule getByUuid_PrevAndNext(
		Session session, ActivityCourseSchedule activityCourseSchedule,
		String uuid,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

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
			sb.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
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
						activityCourseSchedule)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ActivityCourseSchedule> list = query.list();

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
		for (ActivityCourseSchedule activityCourseSchedule :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

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
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ACTIVITYCOURSESCHEDULE_WHERE);

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
		"activityCourseSchedule.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(activityCourseSchedule.uuid IS NULL OR activityCourseSchedule.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the activity course schedule where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchActivityCourseScheduleException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule findByUUID_G(String uuid, long groupId)
		throws NoSuchActivityCourseScheduleException {

		ActivityCourseSchedule activityCourseSchedule = fetchByUUID_G(
			uuid, groupId);

		if (activityCourseSchedule == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchActivityCourseScheduleException(sb.toString());
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
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof ActivityCourseSchedule) {
			ActivityCourseSchedule activityCourseSchedule =
				(ActivityCourseSchedule)result;

			if (!Objects.equals(uuid, activityCourseSchedule.getUuid()) ||
				(groupId != activityCourseSchedule.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<ActivityCourseSchedule> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					ActivityCourseSchedule activityCourseSchedule = list.get(0);

					result = activityCourseSchedule;

					cacheResult(activityCourseSchedule);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByUUID_G, finderArgs);
				}

				throw processException(exception);
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

		ActivityCourseSchedule activityCourseSchedule = findByUUID_G(
			uuid, groupId);

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
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ACTIVITYCOURSESCHEDULE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"activityCourseSchedule.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(activityCourseSchedule.uuid IS NULL OR activityCourseSchedule.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"activityCourseSchedule.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the activity course schedules where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity course schedules where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @return the range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
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
	public List<ActivityCourseSchedule> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<ActivityCourseSchedule> list = null;

		if (useFinderCache) {
			list = (List<ActivityCourseSchedule>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCourseSchedule activityCourseSchedule : list) {
					if (!uuid.equals(activityCourseSchedule.getUuid()) ||
						(companyId != activityCourseSchedule.getCompanyId())) {

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
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
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

				queryPos.add(companyId);

				list = (List<ActivityCourseSchedule>)QueryUtil.list(
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
	 * Returns the first activity course schedule in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {

		ActivityCourseSchedule activityCourseSchedule = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchActivityCourseScheduleException(sb.toString());
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
	public ActivityCourseSchedule fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {

		List<ActivityCourseSchedule> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

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
	public ActivityCourseSchedule findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {

		ActivityCourseSchedule activityCourseSchedule = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchActivityCourseScheduleException(sb.toString());
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
	public ActivityCourseSchedule fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ActivityCourseSchedule> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

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

		uuid = Objects.toString(uuid, "");

		ActivityCourseSchedule activityCourseSchedule = findByPrimaryKey(
			activityCourseScheduleId);

		Session session = null;

		try {
			session = openSession();

			ActivityCourseSchedule[] array = new ActivityCourseScheduleImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, activityCourseSchedule, uuid, companyId,
				orderByComparator, true);

			array[1] = activityCourseSchedule;

			array[2] = getByUuid_C_PrevAndNext(
				session, activityCourseSchedule, uuid, companyId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActivityCourseSchedule getByUuid_C_PrevAndNext(
		Session session, ActivityCourseSchedule activityCourseSchedule,
		String uuid, long companyId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			sb.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						activityCourseSchedule)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ActivityCourseSchedule> list = query.list();

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
		for (ActivityCourseSchedule activityCourseSchedule :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

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
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ACTIVITYCOURSESCHEDULE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"activityCourseSchedule.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(activityCourseSchedule.uuid IS NULL OR activityCourseSchedule.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"activityCourseSchedule.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the activity course schedules where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity course schedules where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @return the range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<ActivityCourseSchedule> list = null;

		if (useFinderCache) {
			list = (List<ActivityCourseSchedule>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCourseSchedule activityCourseSchedule : list) {
					if (groupId != activityCourseSchedule.getGroupId()) {
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

			sb.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<ActivityCourseSchedule>)QueryUtil.list(
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
	 * Returns the first activity course schedule in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule findByGroupId_First(
			long groupId,
			OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {

		ActivityCourseSchedule activityCourseSchedule = fetchByGroupId_First(
			groupId, orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchActivityCourseScheduleException(sb.toString());
	}

	/**
	 * Returns the first activity course schedule in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByGroupId_First(
		long groupId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {

		List<ActivityCourseSchedule> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

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
	public ActivityCourseSchedule findByGroupId_Last(
			long groupId,
			OrderByComparator<ActivityCourseSchedule> orderByComparator)
		throws NoSuchActivityCourseScheduleException {

		ActivityCourseSchedule activityCourseSchedule = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchActivityCourseScheduleException(sb.toString());
	}

	/**
	 * Returns the last activity course schedule in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	 */
	@Override
	public ActivityCourseSchedule fetchByGroupId_Last(
		long groupId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ActivityCourseSchedule> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

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

		ActivityCourseSchedule activityCourseSchedule = findByPrimaryKey(
			activityCourseScheduleId);

		Session session = null;

		try {
			session = openSession();

			ActivityCourseSchedule[] array = new ActivityCourseScheduleImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, activityCourseSchedule, groupId, orderByComparator,
				true);

			array[1] = activityCourseSchedule;

			array[2] = getByGroupId_PrevAndNext(
				session, activityCourseSchedule, groupId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActivityCourseSchedule getByGroupId_PrevAndNext(
		Session session, ActivityCourseSchedule activityCourseSchedule,
		long groupId,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			sb.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						activityCourseSchedule)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ActivityCourseSchedule> list = query.list();

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
		for (ActivityCourseSchedule activityCourseSchedule :
				findByGroupId(
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
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ACTIVITYCOURSESCHEDULE_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"activityCourseSchedule.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByActivityCoursePlace;
	private FinderPath _finderPathWithoutPaginationFindByActivityCoursePlace;
	private FinderPath _finderPathCountByActivityCoursePlace;

	/**
	 * Returns all the activity course schedules where activityCoursePlaceId = &#63;.
	 *
	 * @param activityCoursePlaceId the activity course place ID
	 * @return the matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByActivityCoursePlace(
		long activityCoursePlaceId) {

		return findByActivityCoursePlace(
			activityCoursePlaceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity course schedules where activityCoursePlaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
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

		return findByActivityCoursePlace(
			activityCoursePlaceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where activityCoursePlaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
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

		return findByActivityCoursePlace(
			activityCoursePlaceId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course schedules where activityCoursePlaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param activityCoursePlaceId the activity course place ID
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findByActivityCoursePlace(
		long activityCoursePlaceId, int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByActivityCoursePlace;
				finderArgs = new Object[] {activityCoursePlaceId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByActivityCoursePlace;
			finderArgs = new Object[] {
				activityCoursePlaceId, start, end, orderByComparator
			};
		}

		List<ActivityCourseSchedule> list = null;

		if (useFinderCache) {
			list = (List<ActivityCourseSchedule>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCourseSchedule activityCourseSchedule : list) {
					if (activityCoursePlaceId !=
							activityCourseSchedule.getActivityCoursePlaceId()) {

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

			sb.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

			sb.append(
				_FINDER_COLUMN_ACTIVITYCOURSEPLACE_ACTIVITYCOURSEPLACEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(activityCoursePlaceId);

				list = (List<ActivityCourseSchedule>)QueryUtil.list(
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

		ActivityCourseSchedule activityCourseSchedule =
			fetchByActivityCoursePlace_First(
				activityCoursePlaceId, orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("activityCoursePlaceId=");
		sb.append(activityCoursePlaceId);

		sb.append("}");

		throw new NoSuchActivityCourseScheduleException(sb.toString());
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

		List<ActivityCourseSchedule> list = findByActivityCoursePlace(
			activityCoursePlaceId, 0, 1, orderByComparator);

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

		ActivityCourseSchedule activityCourseSchedule =
			fetchByActivityCoursePlace_Last(
				activityCoursePlaceId, orderByComparator);

		if (activityCourseSchedule != null) {
			return activityCourseSchedule;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("activityCoursePlaceId=");
		sb.append(activityCoursePlaceId);

		sb.append("}");

		throw new NoSuchActivityCourseScheduleException(sb.toString());
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

		List<ActivityCourseSchedule> list = findByActivityCoursePlace(
			activityCoursePlaceId, count - 1, count, orderByComparator);

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

		ActivityCourseSchedule activityCourseSchedule = findByPrimaryKey(
			activityCourseScheduleId);

		Session session = null;

		try {
			session = openSession();

			ActivityCourseSchedule[] array = new ActivityCourseScheduleImpl[3];

			array[0] = getByActivityCoursePlace_PrevAndNext(
				session, activityCourseSchedule, activityCoursePlaceId,
				orderByComparator, true);

			array[1] = activityCourseSchedule;

			array[2] = getByActivityCoursePlace_PrevAndNext(
				session, activityCourseSchedule, activityCoursePlaceId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
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

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE);

		sb.append(_FINDER_COLUMN_ACTIVITYCOURSEPLACE_ACTIVITYCOURSEPLACEID_2);

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
			sb.append(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(activityCoursePlaceId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						activityCourseSchedule)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ActivityCourseSchedule> list = query.list();

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
		for (ActivityCourseSchedule activityCourseSchedule :
				findByActivityCoursePlace(
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
		FinderPath finderPath = _finderPathCountByActivityCoursePlace;

		Object[] finderArgs = new Object[] {activityCoursePlaceId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ACTIVITYCOURSESCHEDULE_WHERE);

			sb.append(
				_FINDER_COLUMN_ACTIVITYCOURSEPLACE_ACTIVITYCOURSEPLACEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(activityCoursePlaceId);

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

	private static final String
		_FINDER_COLUMN_ACTIVITYCOURSEPLACE_ACTIVITYCOURSEPLACEID_2 =
			"activityCourseSchedule.activityCoursePlaceId = ?";

	public ActivityCourseSchedulePersistenceImpl() {
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

		setModelClass(ActivityCourseSchedule.class);
	}

	/**
	 * Caches the activity course schedule in the entity cache if it is enabled.
	 *
	 * @param activityCourseSchedule the activity course schedule
	 */
	@Override
	public void cacheResult(ActivityCourseSchedule activityCourseSchedule) {
		entityCache.putResult(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			activityCourseSchedule.getPrimaryKey(), activityCourseSchedule);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				activityCourseSchedule.getUuid(),
				activityCourseSchedule.getGroupId()
			},
			activityCourseSchedule);

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

		for (ActivityCourseSchedule activityCourseSchedule :
				activityCourseSchedules) {

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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ActivityCourseSchedule activityCourseSchedule) {
		entityCache.removeResult(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			activityCourseSchedule.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(ActivityCourseScheduleModelImpl)activityCourseSchedule, true);
	}

	@Override
	public void clearCache(
		List<ActivityCourseSchedule> activityCourseSchedules) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ActivityCourseSchedule activityCourseSchedule :
				activityCourseSchedules) {

			entityCache.removeResult(
				ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
				ActivityCourseScheduleImpl.class,
				activityCourseSchedule.getPrimaryKey());

			clearUniqueFindersCache(
				(ActivityCourseScheduleModelImpl)activityCourseSchedule, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
				ActivityCourseScheduleImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ActivityCourseScheduleModelImpl activityCourseScheduleModelImpl) {

		Object[] args = new Object[] {
			activityCourseScheduleModelImpl.getUuid(),
			activityCourseScheduleModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, activityCourseScheduleModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		ActivityCourseScheduleModelImpl activityCourseScheduleModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				activityCourseScheduleModelImpl.getUuid(),
				activityCourseScheduleModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((activityCourseScheduleModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				activityCourseScheduleModelImpl.getOriginalUuid(),
				activityCourseScheduleModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
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
		ActivityCourseSchedule activityCourseSchedule =
			new ActivityCourseScheduleImpl();

		activityCourseSchedule.setNew(true);
		activityCourseSchedule.setPrimaryKey(activityCourseScheduleId);

		String uuid = PortalUUIDUtil.generate();

		activityCourseSchedule.setUuid(uuid);

		activityCourseSchedule.setCompanyId(CompanyThreadLocal.getCompanyId());

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

			ActivityCourseSchedule activityCourseSchedule =
				(ActivityCourseSchedule)session.get(
					ActivityCourseScheduleImpl.class, primaryKey);

			if (activityCourseSchedule == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActivityCourseScheduleException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(activityCourseSchedule);
		}
		catch (NoSuchActivityCourseScheduleException noSuchEntityException) {
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
	protected ActivityCourseSchedule removeImpl(
		ActivityCourseSchedule activityCourseSchedule) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(activityCourseSchedule)) {
				activityCourseSchedule = (ActivityCourseSchedule)session.get(
					ActivityCourseScheduleImpl.class,
					activityCourseSchedule.getPrimaryKeyObj());
			}

			if (activityCourseSchedule != null) {
				session.delete(activityCourseSchedule);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
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

		boolean isNew = activityCourseSchedule.isNew();

		if (!(activityCourseSchedule instanceof
				ActivityCourseScheduleModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(activityCourseSchedule.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					activityCourseSchedule);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in activityCourseSchedule proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ActivityCourseSchedule implementation " +
					activityCourseSchedule.getClass());
		}

		ActivityCourseScheduleModelImpl activityCourseScheduleModelImpl =
			(ActivityCourseScheduleModelImpl)activityCourseSchedule;

		if (Validator.isNull(activityCourseSchedule.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			activityCourseSchedule.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (activityCourseSchedule.getCreateDate() == null)) {
			if (serviceContext == null) {
				activityCourseSchedule.setCreateDate(now);
			}
			else {
				activityCourseSchedule.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!activityCourseScheduleModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				activityCourseSchedule.setModifiedDate(now);
			}
			else {
				activityCourseSchedule.setModifiedDate(
					serviceContext.getModifiedDate(now));
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
				activityCourseSchedule = (ActivityCourseSchedule)session.merge(
					activityCourseSchedule);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ActivityCourseScheduleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				activityCourseScheduleModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				activityCourseScheduleModelImpl.getUuid(),
				activityCourseScheduleModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {activityCourseScheduleModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {
				activityCourseScheduleModelImpl.getActivityCoursePlaceId()
			};

			finderCache.removeResult(
				_finderPathCountByActivityCoursePlace, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByActivityCoursePlace, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((activityCourseScheduleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					activityCourseScheduleModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {activityCourseScheduleModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((activityCourseScheduleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					activityCourseScheduleModelImpl.getOriginalUuid(),
					activityCourseScheduleModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					activityCourseScheduleModelImpl.getUuid(),
					activityCourseScheduleModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((activityCourseScheduleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					activityCourseScheduleModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {
					activityCourseScheduleModelImpl.getGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((activityCourseScheduleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByActivityCoursePlace.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					activityCourseScheduleModelImpl.
						getOriginalActivityCoursePlaceId()
				};

				finderCache.removeResult(
					_finderPathCountByActivityCoursePlace, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActivityCoursePlace,
					args);

				args = new Object[] {
					activityCourseScheduleModelImpl.getActivityCoursePlaceId()
				};

				finderCache.removeResult(
					_finderPathCountByActivityCoursePlace, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActivityCoursePlace,
					args);
			}
		}

		entityCache.putResult(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			activityCourseSchedule.getPrimaryKey(), activityCourseSchedule,
			false);

		clearUniqueFindersCache(activityCourseScheduleModelImpl, false);
		cacheUniqueFindersCache(activityCourseScheduleModelImpl);

		activityCourseSchedule.resetOriginalValues();

		return activityCourseSchedule;
	}

	/**
	 * Returns the activity course schedule with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the activity course schedule
	 * @return the activity course schedule
	 * @throws NoSuchActivityCourseScheduleException if a activity course schedule with the primary key could not be found
	 */
	@Override
	public ActivityCourseSchedule findByPrimaryKey(Serializable primaryKey)
		throws NoSuchActivityCourseScheduleException {

		ActivityCourseSchedule activityCourseSchedule = fetchByPrimaryKey(
			primaryKey);

		if (activityCourseSchedule == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchActivityCourseScheduleException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return activityCourseSchedule;
	}

	/**
	 * Returns the activity course schedule with the primary key or throws a <code>NoSuchActivityCourseScheduleException</code> if it could not be found.
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
		Serializable serializable = entityCache.getResult(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ActivityCourseSchedule activityCourseSchedule =
			(ActivityCourseSchedule)serializable;

		if (activityCourseSchedule == null) {
			Session session = null;

			try {
				session = openSession();

				activityCourseSchedule = (ActivityCourseSchedule)session.get(
					ActivityCourseScheduleImpl.class, primaryKey);

				if (activityCourseSchedule != null) {
					cacheResult(activityCourseSchedule);
				}
				else {
					entityCache.putResult(
						ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
						ActivityCourseScheduleImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
					ActivityCourseScheduleImpl.class, primaryKey);

				throw processException(exception);
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

		Map<Serializable, ActivityCourseSchedule> map =
			new HashMap<Serializable, ActivityCourseSchedule>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ActivityCourseSchedule activityCourseSchedule = fetchByPrimaryKey(
				primaryKey);

			if (activityCourseSchedule != null) {
				map.put(primaryKey, activityCourseSchedule);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
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

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE_PKS_IN);

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

			for (ActivityCourseSchedule activityCourseSchedule :
					(List<ActivityCourseSchedule>)query.list()) {

				map.put(
					activityCourseSchedule.getPrimaryKeyObj(),
					activityCourseSchedule);

				cacheResult(activityCourseSchedule);

				uncachedPrimaryKeys.remove(
					activityCourseSchedule.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
					ActivityCourseScheduleImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findAll(
		int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ActivityCourseScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of activity course schedules
	 * @param end the upper bound of the range of activity course schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of activity course schedules
	 */
	@Override
	public List<ActivityCourseSchedule> findAll(
		int start, int end,
		OrderByComparator<ActivityCourseSchedule> orderByComparator,
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

		List<ActivityCourseSchedule> list = null;

		if (useFinderCache) {
			list = (List<ActivityCourseSchedule>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ACTIVITYCOURSESCHEDULE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ACTIVITYCOURSESCHEDULE;

				sql = sql.concat(ActivityCourseScheduleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ActivityCourseSchedule>)QueryUtil.list(
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
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_ACTIVITYCOURSESCHEDULE);

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
		return ActivityCourseScheduleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the activity course schedule persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			ActivityCourseScheduleModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			ActivityCourseScheduleModelImpl.UUID_COLUMN_BITMASK |
			ActivityCourseScheduleModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			ActivityCourseScheduleModelImpl.UUID_COLUMN_BITMASK |
			ActivityCourseScheduleModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			ActivityCourseScheduleModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByActivityCoursePlace = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActivityCoursePlace",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByActivityCoursePlace = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByActivityCoursePlace", new String[] {Long.class.getName()},
			ActivityCourseScheduleModelImpl.
				ACTIVITYCOURSEPLACEID_COLUMN_BITMASK);

		_finderPathCountByActivityCoursePlace = new FinderPath(
			ActivityCourseScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByActivityCoursePlace", new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(ActivityCourseScheduleImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ACTIVITYCOURSESCHEDULE =
		"SELECT activityCourseSchedule FROM ActivityCourseSchedule activityCourseSchedule";

	private static final String
		_SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE_PKS_IN =
			"SELECT activityCourseSchedule FROM ActivityCourseSchedule activityCourseSchedule WHERE activityCourseScheduleId IN (";

	private static final String _SQL_SELECT_ACTIVITYCOURSESCHEDULE_WHERE =
		"SELECT activityCourseSchedule FROM ActivityCourseSchedule activityCourseSchedule WHERE ";

	private static final String _SQL_COUNT_ACTIVITYCOURSESCHEDULE =
		"SELECT COUNT(activityCourseSchedule) FROM ActivityCourseSchedule activityCourseSchedule";

	private static final String _SQL_COUNT_ACTIVITYCOURSESCHEDULE_WHERE =
		"SELECT COUNT(activityCourseSchedule) FROM ActivityCourseSchedule activityCourseSchedule WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"activityCourseSchedule.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ActivityCourseSchedule exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ActivityCourseSchedule exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ActivityCourseSchedulePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}