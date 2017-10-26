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

import eu.strasbourg.service.activity.exception.NoSuchActivityCourseException;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.model.impl.ActivityCourseImpl;
import eu.strasbourg.service.activity.model.impl.ActivityCourseModelImpl;
import eu.strasbourg.service.activity.service.persistence.ActivityCoursePersistence;

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
 * The persistence implementation for the activity course service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCoursePersistence
 * @see eu.strasbourg.service.activity.service.persistence.ActivityCourseUtil
 * @generated
 */
@ProviderType
public class ActivityCoursePersistenceImpl extends BasePersistenceImpl<ActivityCourse>
	implements ActivityCoursePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ActivityCourseUtil} to access the activity course persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ActivityCourseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ActivityCourseModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the activity courses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity courses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @return the range of matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity courses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByUuid(String uuid, int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity courses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByUuid(String uuid, int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator,
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

		List<ActivityCourse> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCourse>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCourse activityCourse : list) {
					if (!Objects.equals(uuid, activityCourse.getUuid())) {
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

			query.append(_SQL_SELECT_ACTIVITYCOURSE_WHERE);

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
				query.append(ActivityCourseModelImpl.ORDER_BY_JPQL);
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
					list = (List<ActivityCourse>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCourse>)QueryUtil.list(q,
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
	 * Returns the first activity course in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course
	 * @throws NoSuchActivityCourseException if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse findByUuid_First(String uuid,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = fetchByUuid_First(uuid,
				orderByComparator);

		if (activityCourse != null) {
			return activityCourse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseException(msg.toString());
	}

	/**
	 * Returns the first activity course in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course, or <code>null</code> if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse fetchByUuid_First(String uuid,
		OrderByComparator<ActivityCourse> orderByComparator) {
		List<ActivityCourse> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity course in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course
	 * @throws NoSuchActivityCourseException if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse findByUuid_Last(String uuid,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = fetchByUuid_Last(uuid, orderByComparator);

		if (activityCourse != null) {
			return activityCourse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseException(msg.toString());
	}

	/**
	 * Returns the last activity course in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course, or <code>null</code> if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse fetchByUuid_Last(String uuid,
		OrderByComparator<ActivityCourse> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ActivityCourse> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activity courses before and after the current activity course in the ordered set where uuid = &#63;.
	 *
	 * @param activityCourseId the primary key of the current activity course
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity course
	 * @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	 */
	@Override
	public ActivityCourse[] findByUuid_PrevAndNext(long activityCourseId,
		String uuid, OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = findByPrimaryKey(activityCourseId);

		Session session = null;

		try {
			session = openSession();

			ActivityCourse[] array = new ActivityCourseImpl[3];

			array[0] = getByUuid_PrevAndNext(session, activityCourse, uuid,
					orderByComparator, true);

			array[1] = activityCourse;

			array[2] = getByUuid_PrevAndNext(session, activityCourse, uuid,
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

	protected ActivityCourse getByUuid_PrevAndNext(Session session,
		ActivityCourse activityCourse, String uuid,
		OrderByComparator<ActivityCourse> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIVITYCOURSE_WHERE);

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
			query.append(ActivityCourseModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(activityCourse);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActivityCourse> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activity courses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ActivityCourse activityCourse : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(activityCourse);
		}
	}

	/**
	 * Returns the number of activity courses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching activity courses
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIVITYCOURSE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "activityCourse.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "activityCourse.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(activityCourse.uuid IS NULL OR activityCourse.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ActivityCourseModelImpl.UUID_COLUMN_BITMASK |
			ActivityCourseModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the activity course where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchActivityCourseException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching activity course
	 * @throws NoSuchActivityCourseException if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse findByUUID_G(String uuid, long groupId)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = fetchByUUID_G(uuid, groupId);

		if (activityCourse == null) {
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

			throw new NoSuchActivityCourseException(msg.toString());
		}

		return activityCourse;
	}

	/**
	 * Returns the activity course where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching activity course, or <code>null</code> if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the activity course where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching activity course, or <code>null</code> if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ActivityCourse) {
			ActivityCourse activityCourse = (ActivityCourse)result;

			if (!Objects.equals(uuid, activityCourse.getUuid()) ||
					(groupId != activityCourse.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ACTIVITYCOURSE_WHERE);

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

				List<ActivityCourse> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ActivityCourse activityCourse = list.get(0);

					result = activityCourse;

					cacheResult(activityCourse);

					if ((activityCourse.getUuid() == null) ||
							!activityCourse.getUuid().equals(uuid) ||
							(activityCourse.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, activityCourse);
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
			return (ActivityCourse)result;
		}
	}

	/**
	 * Removes the activity course where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the activity course that was removed
	 */
	@Override
	public ActivityCourse removeByUUID_G(String uuid, long groupId)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = findByUUID_G(uuid, groupId);

		return remove(activityCourse);
	}

	/**
	 * Returns the number of activity courses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching activity courses
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACTIVITYCOURSE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "activityCourse.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "activityCourse.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(activityCourse.uuid IS NULL OR activityCourse.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "activityCourse.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ActivityCourseModelImpl.UUID_COLUMN_BITMASK |
			ActivityCourseModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the activity courses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity courses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @return the range of matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity courses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ActivityCourse> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity courses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator,
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

		List<ActivityCourse> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCourse>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCourse activityCourse : list) {
					if (!Objects.equals(uuid, activityCourse.getUuid()) ||
							(companyId != activityCourse.getCompanyId())) {
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

			query.append(_SQL_SELECT_ACTIVITYCOURSE_WHERE);

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
				query.append(ActivityCourseModelImpl.ORDER_BY_JPQL);
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
					list = (List<ActivityCourse>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCourse>)QueryUtil.list(q,
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
	 * Returns the first activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course
	 * @throws NoSuchActivityCourseException if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (activityCourse != null) {
			return activityCourse;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseException(msg.toString());
	}

	/**
	 * Returns the first activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course, or <code>null</code> if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ActivityCourse> orderByComparator) {
		List<ActivityCourse> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course
	 * @throws NoSuchActivityCourseException if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (activityCourse != null) {
			return activityCourse;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseException(msg.toString());
	}

	/**
	 * Returns the last activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course, or <code>null</code> if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ActivityCourse> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ActivityCourse> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activity courses before and after the current activity course in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param activityCourseId the primary key of the current activity course
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity course
	 * @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	 */
	@Override
	public ActivityCourse[] findByUuid_C_PrevAndNext(long activityCourseId,
		String uuid, long companyId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = findByPrimaryKey(activityCourseId);

		Session session = null;

		try {
			session = openSession();

			ActivityCourse[] array = new ActivityCourseImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, activityCourse, uuid,
					companyId, orderByComparator, true);

			array[1] = activityCourse;

			array[2] = getByUuid_C_PrevAndNext(session, activityCourse, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActivityCourse getByUuid_C_PrevAndNext(Session session,
		ActivityCourse activityCourse, String uuid, long companyId,
		OrderByComparator<ActivityCourse> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ACTIVITYCOURSE_WHERE);

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
			query.append(ActivityCourseModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(activityCourse);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActivityCourse> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activity courses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ActivityCourse activityCourse : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(activityCourse);
		}
	}

	/**
	 * Returns the number of activity courses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching activity courses
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACTIVITYCOURSE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "activityCourse.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "activityCourse.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(activityCourse.uuid IS NULL OR activityCourse.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "activityCourse.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITY = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByActivity",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITY =
		new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActivity",
			new String[] { Long.class.getName() },
			ActivityCourseModelImpl.ACTIVITYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVITY = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActivity",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the activity courses where activityId = &#63;.
	 *
	 * @param activityId the activity ID
	 * @return the matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByActivity(long activityId) {
		return findByActivity(activityId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the activity courses where activityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param activityId the activity ID
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @return the range of matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByActivity(long activityId, int start,
		int end) {
		return findByActivity(activityId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity courses where activityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param activityId the activity ID
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByActivity(long activityId, int start,
		int end, OrderByComparator<ActivityCourse> orderByComparator) {
		return findByActivity(activityId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity courses where activityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param activityId the activity ID
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByActivity(long activityId, int start,
		int end, OrderByComparator<ActivityCourse> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITY;
			finderArgs = new Object[] { activityId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITY;
			finderArgs = new Object[] { activityId, start, end, orderByComparator };
		}

		List<ActivityCourse> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCourse>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCourse activityCourse : list) {
					if ((activityId != activityCourse.getActivityId())) {
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

			query.append(_SQL_SELECT_ACTIVITYCOURSE_WHERE);

			query.append(_FINDER_COLUMN_ACTIVITY_ACTIVITYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActivityCourseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(activityId);

				if (!pagination) {
					list = (List<ActivityCourse>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCourse>)QueryUtil.list(q,
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
	 * Returns the first activity course in the ordered set where activityId = &#63;.
	 *
	 * @param activityId the activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course
	 * @throws NoSuchActivityCourseException if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse findByActivity_First(long activityId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = fetchByActivity_First(activityId,
				orderByComparator);

		if (activityCourse != null) {
			return activityCourse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("activityId=");
		msg.append(activityId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseException(msg.toString());
	}

	/**
	 * Returns the first activity course in the ordered set where activityId = &#63;.
	 *
	 * @param activityId the activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course, or <code>null</code> if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse fetchByActivity_First(long activityId,
		OrderByComparator<ActivityCourse> orderByComparator) {
		List<ActivityCourse> list = findByActivity(activityId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity course in the ordered set where activityId = &#63;.
	 *
	 * @param activityId the activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course
	 * @throws NoSuchActivityCourseException if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse findByActivity_Last(long activityId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = fetchByActivity_Last(activityId,
				orderByComparator);

		if (activityCourse != null) {
			return activityCourse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("activityId=");
		msg.append(activityId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseException(msg.toString());
	}

	/**
	 * Returns the last activity course in the ordered set where activityId = &#63;.
	 *
	 * @param activityId the activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course, or <code>null</code> if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse fetchByActivity_Last(long activityId,
		OrderByComparator<ActivityCourse> orderByComparator) {
		int count = countByActivity(activityId);

		if (count == 0) {
			return null;
		}

		List<ActivityCourse> list = findByActivity(activityId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activity courses before and after the current activity course in the ordered set where activityId = &#63;.
	 *
	 * @param activityCourseId the primary key of the current activity course
	 * @param activityId the activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity course
	 * @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	 */
	@Override
	public ActivityCourse[] findByActivity_PrevAndNext(long activityCourseId,
		long activityId, OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = findByPrimaryKey(activityCourseId);

		Session session = null;

		try {
			session = openSession();

			ActivityCourse[] array = new ActivityCourseImpl[3];

			array[0] = getByActivity_PrevAndNext(session, activityCourse,
					activityId, orderByComparator, true);

			array[1] = activityCourse;

			array[2] = getByActivity_PrevAndNext(session, activityCourse,
					activityId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActivityCourse getByActivity_PrevAndNext(Session session,
		ActivityCourse activityCourse, long activityId,
		OrderByComparator<ActivityCourse> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIVITYCOURSE_WHERE);

		query.append(_FINDER_COLUMN_ACTIVITY_ACTIVITYID_2);

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
			query.append(ActivityCourseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(activityId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(activityCourse);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActivityCourse> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activity courses where activityId = &#63; from the database.
	 *
	 * @param activityId the activity ID
	 */
	@Override
	public void removeByActivity(long activityId) {
		for (ActivityCourse activityCourse : findByActivity(activityId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(activityCourse);
		}
	}

	/**
	 * Returns the number of activity courses where activityId = &#63;.
	 *
	 * @param activityId the activity ID
	 * @return the number of matching activity courses
	 */
	@Override
	public int countByActivity(long activityId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVITY;

		Object[] finderArgs = new Object[] { activityId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIVITYCOURSE_WHERE);

			query.append(_FINDER_COLUMN_ACTIVITY_ACTIVITYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(activityId);

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

	private static final String _FINDER_COLUMN_ACTIVITY_ACTIVITYID_2 = "activityCourse.activityId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED,
			ActivityCourseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ActivityCourseModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the activity courses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity courses where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @return the range of matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity courses where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByGroupId(long groupId, int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity courses where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching activity courses
	 */
	@Override
	public List<ActivityCourse> findByGroupId(long groupId, int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator,
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

		List<ActivityCourse> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCourse>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCourse activityCourse : list) {
					if ((groupId != activityCourse.getGroupId())) {
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

			query.append(_SQL_SELECT_ACTIVITYCOURSE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActivityCourseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ActivityCourse>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCourse>)QueryUtil.list(q,
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
	 * Returns the first activity course in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course
	 * @throws NoSuchActivityCourseException if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse findByGroupId_First(long groupId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = fetchByGroupId_First(groupId,
				orderByComparator);

		if (activityCourse != null) {
			return activityCourse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseException(msg.toString());
	}

	/**
	 * Returns the first activity course in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course, or <code>null</code> if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse fetchByGroupId_First(long groupId,
		OrderByComparator<ActivityCourse> orderByComparator) {
		List<ActivityCourse> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity course in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course
	 * @throws NoSuchActivityCourseException if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse findByGroupId_Last(long groupId,
		OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (activityCourse != null) {
			return activityCourse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCourseException(msg.toString());
	}

	/**
	 * Returns the last activity course in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course, or <code>null</code> if a matching activity course could not be found
	 */
	@Override
	public ActivityCourse fetchByGroupId_Last(long groupId,
		OrderByComparator<ActivityCourse> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ActivityCourse> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activity courses before and after the current activity course in the ordered set where groupId = &#63;.
	 *
	 * @param activityCourseId the primary key of the current activity course
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity course
	 * @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	 */
	@Override
	public ActivityCourse[] findByGroupId_PrevAndNext(long activityCourseId,
		long groupId, OrderByComparator<ActivityCourse> orderByComparator)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = findByPrimaryKey(activityCourseId);

		Session session = null;

		try {
			session = openSession();

			ActivityCourse[] array = new ActivityCourseImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, activityCourse,
					groupId, orderByComparator, true);

			array[1] = activityCourse;

			array[2] = getByGroupId_PrevAndNext(session, activityCourse,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActivityCourse getByGroupId_PrevAndNext(Session session,
		ActivityCourse activityCourse, long groupId,
		OrderByComparator<ActivityCourse> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIVITYCOURSE_WHERE);

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
			query.append(ActivityCourseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(activityCourse);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActivityCourse> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activity courses where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (ActivityCourse activityCourse : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(activityCourse);
		}
	}

	/**
	 * Returns the number of activity courses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching activity courses
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIVITYCOURSE_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "activityCourse.groupId = ?";

	public ActivityCoursePersistenceImpl() {
		setModelClass(ActivityCourse.class);

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
	 * Caches the activity course in the entity cache if it is enabled.
	 *
	 * @param activityCourse the activity course
	 */
	@Override
	public void cacheResult(ActivityCourse activityCourse) {
		entityCache.putResult(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseImpl.class, activityCourse.getPrimaryKey(),
			activityCourse);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { activityCourse.getUuid(), activityCourse.getGroupId() },
			activityCourse);

		activityCourse.resetOriginalValues();
	}

	/**
	 * Caches the activity courses in the entity cache if it is enabled.
	 *
	 * @param activityCourses the activity courses
	 */
	@Override
	public void cacheResult(List<ActivityCourse> activityCourses) {
		for (ActivityCourse activityCourse : activityCourses) {
			if (entityCache.getResult(
						ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
						ActivityCourseImpl.class, activityCourse.getPrimaryKey()) == null) {
				cacheResult(activityCourse);
			}
			else {
				activityCourse.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all activity courses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ActivityCourseImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the activity course.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ActivityCourse activityCourse) {
		entityCache.removeResult(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseImpl.class, activityCourse.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ActivityCourseModelImpl)activityCourse, true);
	}

	@Override
	public void clearCache(List<ActivityCourse> activityCourses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ActivityCourse activityCourse : activityCourses) {
			entityCache.removeResult(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
				ActivityCourseImpl.class, activityCourse.getPrimaryKey());

			clearUniqueFindersCache((ActivityCourseModelImpl)activityCourse,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ActivityCourseModelImpl activityCourseModelImpl) {
		Object[] args = new Object[] {
				activityCourseModelImpl.getUuid(),
				activityCourseModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			activityCourseModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ActivityCourseModelImpl activityCourseModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					activityCourseModelImpl.getUuid(),
					activityCourseModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((activityCourseModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					activityCourseModelImpl.getOriginalUuid(),
					activityCourseModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new activity course with the primary key. Does not add the activity course to the database.
	 *
	 * @param activityCourseId the primary key for the new activity course
	 * @return the new activity course
	 */
	@Override
	public ActivityCourse create(long activityCourseId) {
		ActivityCourse activityCourse = new ActivityCourseImpl();

		activityCourse.setNew(true);
		activityCourse.setPrimaryKey(activityCourseId);

		String uuid = PortalUUIDUtil.generate();

		activityCourse.setUuid(uuid);

		activityCourse.setCompanyId(companyProvider.getCompanyId());

		return activityCourse;
	}

	/**
	 * Removes the activity course with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param activityCourseId the primary key of the activity course
	 * @return the activity course that was removed
	 * @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	 */
	@Override
	public ActivityCourse remove(long activityCourseId)
		throws NoSuchActivityCourseException {
		return remove((Serializable)activityCourseId);
	}

	/**
	 * Removes the activity course with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the activity course
	 * @return the activity course that was removed
	 * @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	 */
	@Override
	public ActivityCourse remove(Serializable primaryKey)
		throws NoSuchActivityCourseException {
		Session session = null;

		try {
			session = openSession();

			ActivityCourse activityCourse = (ActivityCourse)session.get(ActivityCourseImpl.class,
					primaryKey);

			if (activityCourse == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActivityCourseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(activityCourse);
		}
		catch (NoSuchActivityCourseException nsee) {
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
	protected ActivityCourse removeImpl(ActivityCourse activityCourse) {
		activityCourse = toUnwrappedModel(activityCourse);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(activityCourse)) {
				activityCourse = (ActivityCourse)session.get(ActivityCourseImpl.class,
						activityCourse.getPrimaryKeyObj());
			}

			if (activityCourse != null) {
				session.delete(activityCourse);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (activityCourse != null) {
			clearCache(activityCourse);
		}

		return activityCourse;
	}

	@Override
	public ActivityCourse updateImpl(ActivityCourse activityCourse) {
		activityCourse = toUnwrappedModel(activityCourse);

		boolean isNew = activityCourse.isNew();

		ActivityCourseModelImpl activityCourseModelImpl = (ActivityCourseModelImpl)activityCourse;

		if (Validator.isNull(activityCourse.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			activityCourse.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (activityCourse.getCreateDate() == null)) {
			if (serviceContext == null) {
				activityCourse.setCreateDate(now);
			}
			else {
				activityCourse.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!activityCourseModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				activityCourse.setModifiedDate(now);
			}
			else {
				activityCourse.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (activityCourse.isNew()) {
				session.save(activityCourse);

				activityCourse.setNew(false);
			}
			else {
				activityCourse = (ActivityCourse)session.merge(activityCourse);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ActivityCourseModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { activityCourseModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					activityCourseModelImpl.getUuid(),
					activityCourseModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { activityCourseModelImpl.getActivityId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVITY, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITY,
				args);

			args = new Object[] { activityCourseModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((activityCourseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						activityCourseModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { activityCourseModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((activityCourseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						activityCourseModelImpl.getOriginalUuid(),
						activityCourseModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						activityCourseModelImpl.getUuid(),
						activityCourseModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((activityCourseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						activityCourseModelImpl.getOriginalActivityId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVITY, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITY,
					args);

				args = new Object[] { activityCourseModelImpl.getActivityId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVITY, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITY,
					args);
			}

			if ((activityCourseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						activityCourseModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { activityCourseModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCourseImpl.class, activityCourse.getPrimaryKey(),
			activityCourse, false);

		clearUniqueFindersCache(activityCourseModelImpl, false);
		cacheUniqueFindersCache(activityCourseModelImpl);

		activityCourse.resetOriginalValues();

		return activityCourse;
	}

	protected ActivityCourse toUnwrappedModel(ActivityCourse activityCourse) {
		if (activityCourse instanceof ActivityCourseImpl) {
			return activityCourse;
		}

		ActivityCourseImpl activityCourseImpl = new ActivityCourseImpl();

		activityCourseImpl.setNew(activityCourse.isNew());
		activityCourseImpl.setPrimaryKey(activityCourse.getPrimaryKey());

		activityCourseImpl.setUuid(activityCourse.getUuid());
		activityCourseImpl.setActivityCourseId(activityCourse.getActivityCourseId());
		activityCourseImpl.setGroupId(activityCourse.getGroupId());
		activityCourseImpl.setCompanyId(activityCourse.getCompanyId());
		activityCourseImpl.setUserId(activityCourse.getUserId());
		activityCourseImpl.setUserName(activityCourse.getUserName());
		activityCourseImpl.setCreateDate(activityCourse.getCreateDate());
		activityCourseImpl.setModifiedDate(activityCourse.getModifiedDate());
		activityCourseImpl.setStatus(activityCourse.getStatus());
		activityCourseImpl.setStatusByUserId(activityCourse.getStatusByUserId());
		activityCourseImpl.setStatusByUserName(activityCourse.getStatusByUserName());
		activityCourseImpl.setStatusDate(activityCourse.getStatusDate());
		activityCourseImpl.setName(activityCourse.getName());
		activityCourseImpl.setArrangements(activityCourse.getArrangements());
		activityCourseImpl.setPrice(activityCourse.getPrice());
		activityCourseImpl.setActivityId(activityCourse.getActivityId());
		activityCourseImpl.setServiceId(activityCourse.getServiceId());
		activityCourseImpl.setOrganizerId(activityCourse.getOrganizerId());

		return activityCourseImpl;
	}

	/**
	 * Returns the activity course with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the activity course
	 * @return the activity course
	 * @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	 */
	@Override
	public ActivityCourse findByPrimaryKey(Serializable primaryKey)
		throws NoSuchActivityCourseException {
		ActivityCourse activityCourse = fetchByPrimaryKey(primaryKey);

		if (activityCourse == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchActivityCourseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return activityCourse;
	}

	/**
	 * Returns the activity course with the primary key or throws a {@link NoSuchActivityCourseException} if it could not be found.
	 *
	 * @param activityCourseId the primary key of the activity course
	 * @return the activity course
	 * @throws NoSuchActivityCourseException if a activity course with the primary key could not be found
	 */
	@Override
	public ActivityCourse findByPrimaryKey(long activityCourseId)
		throws NoSuchActivityCourseException {
		return findByPrimaryKey((Serializable)activityCourseId);
	}

	/**
	 * Returns the activity course with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the activity course
	 * @return the activity course, or <code>null</code> if a activity course with the primary key could not be found
	 */
	@Override
	public ActivityCourse fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
				ActivityCourseImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ActivityCourse activityCourse = (ActivityCourse)serializable;

		if (activityCourse == null) {
			Session session = null;

			try {
				session = openSession();

				activityCourse = (ActivityCourse)session.get(ActivityCourseImpl.class,
						primaryKey);

				if (activityCourse != null) {
					cacheResult(activityCourse);
				}
				else {
					entityCache.putResult(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
						ActivityCourseImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
					ActivityCourseImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return activityCourse;
	}

	/**
	 * Returns the activity course with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param activityCourseId the primary key of the activity course
	 * @return the activity course, or <code>null</code> if a activity course with the primary key could not be found
	 */
	@Override
	public ActivityCourse fetchByPrimaryKey(long activityCourseId) {
		return fetchByPrimaryKey((Serializable)activityCourseId);
	}

	@Override
	public Map<Serializable, ActivityCourse> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ActivityCourse> map = new HashMap<Serializable, ActivityCourse>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ActivityCourse activityCourse = fetchByPrimaryKey(primaryKey);

			if (activityCourse != null) {
				map.put(primaryKey, activityCourse);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
					ActivityCourseImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ActivityCourse)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACTIVITYCOURSE_WHERE_PKS_IN);

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

			for (ActivityCourse activityCourse : (List<ActivityCourse>)q.list()) {
				map.put(activityCourse.getPrimaryKeyObj(), activityCourse);

				cacheResult(activityCourse);

				uncachedPrimaryKeys.remove(activityCourse.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ActivityCourseModelImpl.ENTITY_CACHE_ENABLED,
					ActivityCourseImpl.class, primaryKey, nullModel);
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
	 * Returns all the activity courses.
	 *
	 * @return the activity courses
	 */
	@Override
	public List<ActivityCourse> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity courses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @return the range of activity courses
	 */
	@Override
	public List<ActivityCourse> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity courses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of activity courses
	 */
	@Override
	public List<ActivityCourse> findAll(int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity courses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of activity courses
	 * @param end the upper bound of the range of activity courses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of activity courses
	 */
	@Override
	public List<ActivityCourse> findAll(int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator,
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

		List<ActivityCourse> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCourse>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACTIVITYCOURSE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACTIVITYCOURSE;

				if (pagination) {
					sql = sql.concat(ActivityCourseModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ActivityCourse>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCourse>)QueryUtil.list(q,
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
	 * Removes all the activity courses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ActivityCourse activityCourse : findAll()) {
			remove(activityCourse);
		}
	}

	/**
	 * Returns the number of activity courses.
	 *
	 * @return the number of activity courses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACTIVITYCOURSE);

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
		return ActivityCourseModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the activity course persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ActivityCourseImpl.class.getName());
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
	private static final String _SQL_SELECT_ACTIVITYCOURSE = "SELECT activityCourse FROM ActivityCourse activityCourse";
	private static final String _SQL_SELECT_ACTIVITYCOURSE_WHERE_PKS_IN = "SELECT activityCourse FROM ActivityCourse activityCourse WHERE activityCourseId IN (";
	private static final String _SQL_SELECT_ACTIVITYCOURSE_WHERE = "SELECT activityCourse FROM ActivityCourse activityCourse WHERE ";
	private static final String _SQL_COUNT_ACTIVITYCOURSE = "SELECT COUNT(activityCourse) FROM ActivityCourse activityCourse";
	private static final String _SQL_COUNT_ACTIVITYCOURSE_WHERE = "SELECT COUNT(activityCourse) FROM ActivityCourse activityCourse WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "activityCourse.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ActivityCourse exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ActivityCourse exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ActivityCoursePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}