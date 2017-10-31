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

import eu.strasbourg.service.activity.exception.NoSuchActivityCoursePlaceException;
import eu.strasbourg.service.activity.model.ActivityCoursePlace;
import eu.strasbourg.service.activity.model.impl.ActivityCoursePlaceImpl;
import eu.strasbourg.service.activity.model.impl.ActivityCoursePlaceModelImpl;
import eu.strasbourg.service.activity.service.persistence.ActivityCoursePlacePersistence;

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
 * The persistence implementation for the activity course place service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCoursePlacePersistence
 * @see eu.strasbourg.service.activity.service.persistence.ActivityCoursePlaceUtil
 * @generated
 */
@ProviderType
public class ActivityCoursePlacePersistenceImpl extends BasePersistenceImpl<ActivityCoursePlace>
	implements ActivityCoursePlacePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ActivityCoursePlaceUtil} to access the activity course place persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ActivityCoursePlaceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ActivityCoursePlaceModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the activity course places where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity course places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @return the range of matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByUuid(String uuid, int start,
		int end, OrderByComparator<ActivityCoursePlace> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByUuid(String uuid, int start,
		int end, OrderByComparator<ActivityCoursePlace> orderByComparator,
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

		List<ActivityCoursePlace> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCoursePlace>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCoursePlace activityCoursePlace : list) {
					if (!Objects.equals(uuid, activityCoursePlace.getUuid())) {
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

			query.append(_SQL_SELECT_ACTIVITYCOURSEPLACE_WHERE);

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
				query.append(ActivityCoursePlaceModelImpl.ORDER_BY_JPQL);
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
					list = (List<ActivityCoursePlace>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCoursePlace>)QueryUtil.list(q,
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
	 * Returns the first activity course place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course place
	 * @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace findByUuid_First(String uuid,
		OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = fetchByUuid_First(uuid,
				orderByComparator);

		if (activityCoursePlace != null) {
			return activityCoursePlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCoursePlaceException(msg.toString());
	}

	/**
	 * Returns the first activity course place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course place, or <code>null</code> if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace fetchByUuid_First(String uuid,
		OrderByComparator<ActivityCoursePlace> orderByComparator) {
		List<ActivityCoursePlace> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity course place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course place
	 * @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace findByUuid_Last(String uuid,
		OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = fetchByUuid_Last(uuid,
				orderByComparator);

		if (activityCoursePlace != null) {
			return activityCoursePlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCoursePlaceException(msg.toString());
	}

	/**
	 * Returns the last activity course place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course place, or <code>null</code> if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace fetchByUuid_Last(String uuid,
		OrderByComparator<ActivityCoursePlace> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ActivityCoursePlace> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activity course places before and after the current activity course place in the ordered set where uuid = &#63;.
	 *
	 * @param activityCoursePlaceId the primary key of the current activity course place
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity course place
	 * @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	 */
	@Override
	public ActivityCoursePlace[] findByUuid_PrevAndNext(
		long activityCoursePlaceId, String uuid,
		OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = findByPrimaryKey(activityCoursePlaceId);

		Session session = null;

		try {
			session = openSession();

			ActivityCoursePlace[] array = new ActivityCoursePlaceImpl[3];

			array[0] = getByUuid_PrevAndNext(session, activityCoursePlace,
					uuid, orderByComparator, true);

			array[1] = activityCoursePlace;

			array[2] = getByUuid_PrevAndNext(session, activityCoursePlace,
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

	protected ActivityCoursePlace getByUuid_PrevAndNext(Session session,
		ActivityCoursePlace activityCoursePlace, String uuid,
		OrderByComparator<ActivityCoursePlace> orderByComparator,
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

		query.append(_SQL_SELECT_ACTIVITYCOURSEPLACE_WHERE);

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
			query.append(ActivityCoursePlaceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(activityCoursePlace);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActivityCoursePlace> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activity course places where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ActivityCoursePlace activityCoursePlace : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(activityCoursePlace);
		}
	}

	/**
	 * Returns the number of activity course places where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching activity course places
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIVITYCOURSEPLACE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "activityCoursePlace.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "activityCoursePlace.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(activityCoursePlace.uuid IS NULL OR activityCoursePlace.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ActivityCoursePlaceModelImpl.UUID_COLUMN_BITMASK |
			ActivityCoursePlaceModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the activity course place where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchActivityCoursePlaceException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching activity course place
	 * @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace findByUUID_G(String uuid, long groupId)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = fetchByUUID_G(uuid, groupId);

		if (activityCoursePlace == null) {
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

			throw new NoSuchActivityCoursePlaceException(msg.toString());
		}

		return activityCoursePlace;
	}

	/**
	 * Returns the activity course place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching activity course place, or <code>null</code> if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the activity course place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching activity course place, or <code>null</code> if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ActivityCoursePlace) {
			ActivityCoursePlace activityCoursePlace = (ActivityCoursePlace)result;

			if (!Objects.equals(uuid, activityCoursePlace.getUuid()) ||
					(groupId != activityCoursePlace.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ACTIVITYCOURSEPLACE_WHERE);

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

				List<ActivityCoursePlace> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ActivityCoursePlace activityCoursePlace = list.get(0);

					result = activityCoursePlace;

					cacheResult(activityCoursePlace);

					if ((activityCoursePlace.getUuid() == null) ||
							!activityCoursePlace.getUuid().equals(uuid) ||
							(activityCoursePlace.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, activityCoursePlace);
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
			return (ActivityCoursePlace)result;
		}
	}

	/**
	 * Removes the activity course place where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the activity course place that was removed
	 */
	@Override
	public ActivityCoursePlace removeByUUID_G(String uuid, long groupId)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = findByUUID_G(uuid, groupId);

		return remove(activityCoursePlace);
	}

	/**
	 * Returns the number of activity course places where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching activity course places
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACTIVITYCOURSEPLACE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "activityCoursePlace.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "activityCoursePlace.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(activityCoursePlace.uuid IS NULL OR activityCoursePlace.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "activityCoursePlace.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ActivityCoursePlaceModelImpl.UUID_COLUMN_BITMASK |
			ActivityCoursePlaceModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the activity course places where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity course places where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @return the range of matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course places where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<ActivityCoursePlace> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course places where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<ActivityCoursePlace> orderByComparator,
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

		List<ActivityCoursePlace> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCoursePlace>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCoursePlace activityCoursePlace : list) {
					if (!Objects.equals(uuid, activityCoursePlace.getUuid()) ||
							(companyId != activityCoursePlace.getCompanyId())) {
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

			query.append(_SQL_SELECT_ACTIVITYCOURSEPLACE_WHERE);

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
				query.append(ActivityCoursePlaceModelImpl.ORDER_BY_JPQL);
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
					list = (List<ActivityCoursePlace>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCoursePlace>)QueryUtil.list(q,
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
	 * Returns the first activity course place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course place
	 * @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (activityCoursePlace != null) {
			return activityCoursePlace;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCoursePlaceException(msg.toString());
	}

	/**
	 * Returns the first activity course place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course place, or <code>null</code> if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ActivityCoursePlace> orderByComparator) {
		List<ActivityCoursePlace> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity course place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course place
	 * @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (activityCoursePlace != null) {
			return activityCoursePlace;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCoursePlaceException(msg.toString());
	}

	/**
	 * Returns the last activity course place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course place, or <code>null</code> if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ActivityCoursePlace> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ActivityCoursePlace> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activity course places before and after the current activity course place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param activityCoursePlaceId the primary key of the current activity course place
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity course place
	 * @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	 */
	@Override
	public ActivityCoursePlace[] findByUuid_C_PrevAndNext(
		long activityCoursePlaceId, String uuid, long companyId,
		OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = findByPrimaryKey(activityCoursePlaceId);

		Session session = null;

		try {
			session = openSession();

			ActivityCoursePlace[] array = new ActivityCoursePlaceImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, activityCoursePlace,
					uuid, companyId, orderByComparator, true);

			array[1] = activityCoursePlace;

			array[2] = getByUuid_C_PrevAndNext(session, activityCoursePlace,
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

	protected ActivityCoursePlace getByUuid_C_PrevAndNext(Session session,
		ActivityCoursePlace activityCoursePlace, String uuid, long companyId,
		OrderByComparator<ActivityCoursePlace> orderByComparator,
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

		query.append(_SQL_SELECT_ACTIVITYCOURSEPLACE_WHERE);

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
			query.append(ActivityCoursePlaceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(activityCoursePlace);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActivityCoursePlace> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activity course places where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ActivityCoursePlace activityCoursePlace : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(activityCoursePlace);
		}
	}

	/**
	 * Returns the number of activity course places where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching activity course places
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACTIVITYCOURSEPLACE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "activityCoursePlace.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "activityCoursePlace.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(activityCoursePlace.uuid IS NULL OR activityCoursePlace.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "activityCoursePlace.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ActivityCoursePlaceModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the activity course places where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity course places where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @return the range of matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByGroupId(long groupId, int start,
		int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course places where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ActivityCoursePlace> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course places where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ActivityCoursePlace> orderByComparator,
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

		List<ActivityCoursePlace> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCoursePlace>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCoursePlace activityCoursePlace : list) {
					if ((groupId != activityCoursePlace.getGroupId())) {
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

			query.append(_SQL_SELECT_ACTIVITYCOURSEPLACE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActivityCoursePlaceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ActivityCoursePlace>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCoursePlace>)QueryUtil.list(q,
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
	 * Returns the first activity course place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course place
	 * @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace findByGroupId_First(long groupId,
		OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = fetchByGroupId_First(groupId,
				orderByComparator);

		if (activityCoursePlace != null) {
			return activityCoursePlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCoursePlaceException(msg.toString());
	}

	/**
	 * Returns the first activity course place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course place, or <code>null</code> if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace fetchByGroupId_First(long groupId,
		OrderByComparator<ActivityCoursePlace> orderByComparator) {
		List<ActivityCoursePlace> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity course place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course place
	 * @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace findByGroupId_Last(long groupId,
		OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (activityCoursePlace != null) {
			return activityCoursePlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCoursePlaceException(msg.toString());
	}

	/**
	 * Returns the last activity course place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course place, or <code>null</code> if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace fetchByGroupId_Last(long groupId,
		OrderByComparator<ActivityCoursePlace> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ActivityCoursePlace> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activity course places before and after the current activity course place in the ordered set where groupId = &#63;.
	 *
	 * @param activityCoursePlaceId the primary key of the current activity course place
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity course place
	 * @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	 */
	@Override
	public ActivityCoursePlace[] findByGroupId_PrevAndNext(
		long activityCoursePlaceId, long groupId,
		OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = findByPrimaryKey(activityCoursePlaceId);

		Session session = null;

		try {
			session = openSession();

			ActivityCoursePlace[] array = new ActivityCoursePlaceImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, activityCoursePlace,
					groupId, orderByComparator, true);

			array[1] = activityCoursePlace;

			array[2] = getByGroupId_PrevAndNext(session, activityCoursePlace,
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

	protected ActivityCoursePlace getByGroupId_PrevAndNext(Session session,
		ActivityCoursePlace activityCoursePlace, long groupId,
		OrderByComparator<ActivityCoursePlace> orderByComparator,
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

		query.append(_SQL_SELECT_ACTIVITYCOURSEPLACE_WHERE);

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
			query.append(ActivityCoursePlaceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(activityCoursePlace);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActivityCoursePlace> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activity course places where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (ActivityCoursePlace activityCoursePlace : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(activityCoursePlace);
		}
	}

	/**
	 * Returns the number of activity course places where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching activity course places
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIVITYCOURSEPLACE_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "activityCoursePlace.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYCOURSE =
		new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActivityCourse",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYCOURSE =
		new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActivityCourse",
			new String[] { Long.class.getName() },
			ActivityCoursePlaceModelImpl.ACTIVITYCOURSEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVITYCOURSE = new FinderPath(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActivityCourse",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the activity course places where activityCourseId = &#63;.
	 *
	 * @param activityCourseId the activity course ID
	 * @return the matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByActivityCourse(long activityCourseId) {
		return findByActivityCourse(activityCourseId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity course places where activityCourseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param activityCourseId the activity course ID
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @return the range of matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByActivityCourse(
		long activityCourseId, int start, int end) {
		return findByActivityCourse(activityCourseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course places where activityCourseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param activityCourseId the activity course ID
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByActivityCourse(
		long activityCourseId, int start, int end,
		OrderByComparator<ActivityCoursePlace> orderByComparator) {
		return findByActivityCourse(activityCourseId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course places where activityCourseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param activityCourseId the activity course ID
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findByActivityCourse(
		long activityCourseId, int start, int end,
		OrderByComparator<ActivityCoursePlace> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYCOURSE;
			finderArgs = new Object[] { activityCourseId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYCOURSE;
			finderArgs = new Object[] {
					activityCourseId,
					
					start, end, orderByComparator
				};
		}

		List<ActivityCoursePlace> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCoursePlace>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActivityCoursePlace activityCoursePlace : list) {
					if ((activityCourseId != activityCoursePlace.getActivityCourseId())) {
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

			query.append(_SQL_SELECT_ACTIVITYCOURSEPLACE_WHERE);

			query.append(_FINDER_COLUMN_ACTIVITYCOURSE_ACTIVITYCOURSEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActivityCoursePlaceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(activityCourseId);

				if (!pagination) {
					list = (List<ActivityCoursePlace>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCoursePlace>)QueryUtil.list(q,
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
	 * Returns the first activity course place in the ordered set where activityCourseId = &#63;.
	 *
	 * @param activityCourseId the activity course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course place
	 * @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace findByActivityCourse_First(
		long activityCourseId,
		OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = fetchByActivityCourse_First(activityCourseId,
				orderByComparator);

		if (activityCoursePlace != null) {
			return activityCoursePlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("activityCourseId=");
		msg.append(activityCourseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCoursePlaceException(msg.toString());
	}

	/**
	 * Returns the first activity course place in the ordered set where activityCourseId = &#63;.
	 *
	 * @param activityCourseId the activity course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching activity course place, or <code>null</code> if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace fetchByActivityCourse_First(
		long activityCourseId,
		OrderByComparator<ActivityCoursePlace> orderByComparator) {
		List<ActivityCoursePlace> list = findByActivityCourse(activityCourseId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last activity course place in the ordered set where activityCourseId = &#63;.
	 *
	 * @param activityCourseId the activity course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course place
	 * @throws NoSuchActivityCoursePlaceException if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace findByActivityCourse_Last(
		long activityCourseId,
		OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = fetchByActivityCourse_Last(activityCourseId,
				orderByComparator);

		if (activityCoursePlace != null) {
			return activityCoursePlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("activityCourseId=");
		msg.append(activityCourseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityCoursePlaceException(msg.toString());
	}

	/**
	 * Returns the last activity course place in the ordered set where activityCourseId = &#63;.
	 *
	 * @param activityCourseId the activity course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching activity course place, or <code>null</code> if a matching activity course place could not be found
	 */
	@Override
	public ActivityCoursePlace fetchByActivityCourse_Last(
		long activityCourseId,
		OrderByComparator<ActivityCoursePlace> orderByComparator) {
		int count = countByActivityCourse(activityCourseId);

		if (count == 0) {
			return null;
		}

		List<ActivityCoursePlace> list = findByActivityCourse(activityCourseId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the activity course places before and after the current activity course place in the ordered set where activityCourseId = &#63;.
	 *
	 * @param activityCoursePlaceId the primary key of the current activity course place
	 * @param activityCourseId the activity course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next activity course place
	 * @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	 */
	@Override
	public ActivityCoursePlace[] findByActivityCourse_PrevAndNext(
		long activityCoursePlaceId, long activityCourseId,
		OrderByComparator<ActivityCoursePlace> orderByComparator)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = findByPrimaryKey(activityCoursePlaceId);

		Session session = null;

		try {
			session = openSession();

			ActivityCoursePlace[] array = new ActivityCoursePlaceImpl[3];

			array[0] = getByActivityCourse_PrevAndNext(session,
					activityCoursePlace, activityCourseId, orderByComparator,
					true);

			array[1] = activityCoursePlace;

			array[2] = getByActivityCourse_PrevAndNext(session,
					activityCoursePlace, activityCourseId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActivityCoursePlace getByActivityCourse_PrevAndNext(
		Session session, ActivityCoursePlace activityCoursePlace,
		long activityCourseId,
		OrderByComparator<ActivityCoursePlace> orderByComparator,
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

		query.append(_SQL_SELECT_ACTIVITYCOURSEPLACE_WHERE);

		query.append(_FINDER_COLUMN_ACTIVITYCOURSE_ACTIVITYCOURSEID_2);

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
			query.append(ActivityCoursePlaceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(activityCourseId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(activityCoursePlace);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActivityCoursePlace> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the activity course places where activityCourseId = &#63; from the database.
	 *
	 * @param activityCourseId the activity course ID
	 */
	@Override
	public void removeByActivityCourse(long activityCourseId) {
		for (ActivityCoursePlace activityCoursePlace : findByActivityCourse(
				activityCourseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(activityCoursePlace);
		}
	}

	/**
	 * Returns the number of activity course places where activityCourseId = &#63;.
	 *
	 * @param activityCourseId the activity course ID
	 * @return the number of matching activity course places
	 */
	@Override
	public int countByActivityCourse(long activityCourseId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVITYCOURSE;

		Object[] finderArgs = new Object[] { activityCourseId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIVITYCOURSEPLACE_WHERE);

			query.append(_FINDER_COLUMN_ACTIVITYCOURSE_ACTIVITYCOURSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(activityCourseId);

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

	private static final String _FINDER_COLUMN_ACTIVITYCOURSE_ACTIVITYCOURSEID_2 =
		"activityCoursePlace.activityCourseId = ?";

	public ActivityCoursePlacePersistenceImpl() {
		setModelClass(ActivityCoursePlace.class);

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
	 * Caches the activity course place in the entity cache if it is enabled.
	 *
	 * @param activityCoursePlace the activity course place
	 */
	@Override
	public void cacheResult(ActivityCoursePlace activityCoursePlace) {
		entityCache.putResult(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class, activityCoursePlace.getPrimaryKey(),
			activityCoursePlace);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				activityCoursePlace.getUuid(), activityCoursePlace.getGroupId()
			}, activityCoursePlace);

		activityCoursePlace.resetOriginalValues();
	}

	/**
	 * Caches the activity course places in the entity cache if it is enabled.
	 *
	 * @param activityCoursePlaces the activity course places
	 */
	@Override
	public void cacheResult(List<ActivityCoursePlace> activityCoursePlaces) {
		for (ActivityCoursePlace activityCoursePlace : activityCoursePlaces) {
			if (entityCache.getResult(
						ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
						ActivityCoursePlaceImpl.class,
						activityCoursePlace.getPrimaryKey()) == null) {
				cacheResult(activityCoursePlace);
			}
			else {
				activityCoursePlace.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all activity course places.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ActivityCoursePlaceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the activity course place.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ActivityCoursePlace activityCoursePlace) {
		entityCache.removeResult(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class, activityCoursePlace.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ActivityCoursePlaceModelImpl)activityCoursePlace,
			true);
	}

	@Override
	public void clearCache(List<ActivityCoursePlace> activityCoursePlaces) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ActivityCoursePlace activityCoursePlace : activityCoursePlaces) {
			entityCache.removeResult(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
				ActivityCoursePlaceImpl.class,
				activityCoursePlace.getPrimaryKey());

			clearUniqueFindersCache((ActivityCoursePlaceModelImpl)activityCoursePlace,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ActivityCoursePlaceModelImpl activityCoursePlaceModelImpl) {
		Object[] args = new Object[] {
				activityCoursePlaceModelImpl.getUuid(),
				activityCoursePlaceModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			activityCoursePlaceModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ActivityCoursePlaceModelImpl activityCoursePlaceModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					activityCoursePlaceModelImpl.getUuid(),
					activityCoursePlaceModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((activityCoursePlaceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					activityCoursePlaceModelImpl.getOriginalUuid(),
					activityCoursePlaceModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new activity course place with the primary key. Does not add the activity course place to the database.
	 *
	 * @param activityCoursePlaceId the primary key for the new activity course place
	 * @return the new activity course place
	 */
	@Override
	public ActivityCoursePlace create(long activityCoursePlaceId) {
		ActivityCoursePlace activityCoursePlace = new ActivityCoursePlaceImpl();

		activityCoursePlace.setNew(true);
		activityCoursePlace.setPrimaryKey(activityCoursePlaceId);

		String uuid = PortalUUIDUtil.generate();

		activityCoursePlace.setUuid(uuid);

		activityCoursePlace.setCompanyId(companyProvider.getCompanyId());

		return activityCoursePlace;
	}

	/**
	 * Removes the activity course place with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param activityCoursePlaceId the primary key of the activity course place
	 * @return the activity course place that was removed
	 * @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	 */
	@Override
	public ActivityCoursePlace remove(long activityCoursePlaceId)
		throws NoSuchActivityCoursePlaceException {
		return remove((Serializable)activityCoursePlaceId);
	}

	/**
	 * Removes the activity course place with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the activity course place
	 * @return the activity course place that was removed
	 * @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	 */
	@Override
	public ActivityCoursePlace remove(Serializable primaryKey)
		throws NoSuchActivityCoursePlaceException {
		Session session = null;

		try {
			session = openSession();

			ActivityCoursePlace activityCoursePlace = (ActivityCoursePlace)session.get(ActivityCoursePlaceImpl.class,
					primaryKey);

			if (activityCoursePlace == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActivityCoursePlaceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(activityCoursePlace);
		}
		catch (NoSuchActivityCoursePlaceException nsee) {
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
	protected ActivityCoursePlace removeImpl(
		ActivityCoursePlace activityCoursePlace) {
		activityCoursePlace = toUnwrappedModel(activityCoursePlace);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(activityCoursePlace)) {
				activityCoursePlace = (ActivityCoursePlace)session.get(ActivityCoursePlaceImpl.class,
						activityCoursePlace.getPrimaryKeyObj());
			}

			if (activityCoursePlace != null) {
				session.delete(activityCoursePlace);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (activityCoursePlace != null) {
			clearCache(activityCoursePlace);
		}

		return activityCoursePlace;
	}

	@Override
	public ActivityCoursePlace updateImpl(
		ActivityCoursePlace activityCoursePlace) {
		activityCoursePlace = toUnwrappedModel(activityCoursePlace);

		boolean isNew = activityCoursePlace.isNew();

		ActivityCoursePlaceModelImpl activityCoursePlaceModelImpl = (ActivityCoursePlaceModelImpl)activityCoursePlace;

		if (Validator.isNull(activityCoursePlace.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			activityCoursePlace.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (activityCoursePlace.getCreateDate() == null)) {
			if (serviceContext == null) {
				activityCoursePlace.setCreateDate(now);
			}
			else {
				activityCoursePlace.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!activityCoursePlaceModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				activityCoursePlace.setModifiedDate(now);
			}
			else {
				activityCoursePlace.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (activityCoursePlace.isNew()) {
				session.save(activityCoursePlace);

				activityCoursePlace.setNew(false);
			}
			else {
				activityCoursePlace = (ActivityCoursePlace)session.merge(activityCoursePlace);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ActivityCoursePlaceModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { activityCoursePlaceModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					activityCoursePlaceModelImpl.getUuid(),
					activityCoursePlaceModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { activityCoursePlaceModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					activityCoursePlaceModelImpl.getActivityCourseId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVITYCOURSE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYCOURSE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((activityCoursePlaceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						activityCoursePlaceModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { activityCoursePlaceModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((activityCoursePlaceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						activityCoursePlaceModelImpl.getOriginalUuid(),
						activityCoursePlaceModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						activityCoursePlaceModelImpl.getUuid(),
						activityCoursePlaceModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((activityCoursePlaceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						activityCoursePlaceModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { activityCoursePlaceModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((activityCoursePlaceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYCOURSE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						activityCoursePlaceModelImpl.getOriginalActivityCourseId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVITYCOURSE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYCOURSE,
					args);

				args = new Object[] {
						activityCoursePlaceModelImpl.getActivityCourseId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVITYCOURSE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYCOURSE,
					args);
			}
		}

		entityCache.putResult(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
			ActivityCoursePlaceImpl.class, activityCoursePlace.getPrimaryKey(),
			activityCoursePlace, false);

		clearUniqueFindersCache(activityCoursePlaceModelImpl, false);
		cacheUniqueFindersCache(activityCoursePlaceModelImpl);

		activityCoursePlace.resetOriginalValues();

		return activityCoursePlace;
	}

	protected ActivityCoursePlace toUnwrappedModel(
		ActivityCoursePlace activityCoursePlace) {
		if (activityCoursePlace instanceof ActivityCoursePlaceImpl) {
			return activityCoursePlace;
		}

		ActivityCoursePlaceImpl activityCoursePlaceImpl = new ActivityCoursePlaceImpl();

		activityCoursePlaceImpl.setNew(activityCoursePlace.isNew());
		activityCoursePlaceImpl.setPrimaryKey(activityCoursePlace.getPrimaryKey());

		activityCoursePlaceImpl.setUuid(activityCoursePlace.getUuid());
		activityCoursePlaceImpl.setActivityCoursePlaceId(activityCoursePlace.getActivityCoursePlaceId());
		activityCoursePlaceImpl.setGroupId(activityCoursePlace.getGroupId());
		activityCoursePlaceImpl.setCompanyId(activityCoursePlace.getCompanyId());
		activityCoursePlaceImpl.setUserId(activityCoursePlace.getUserId());
		activityCoursePlaceImpl.setUserName(activityCoursePlace.getUserName());
		activityCoursePlaceImpl.setCreateDate(activityCoursePlace.getCreateDate());
		activityCoursePlaceImpl.setModifiedDate(activityCoursePlace.getModifiedDate());
		activityCoursePlaceImpl.setActivityCourseId(activityCoursePlace.getActivityCourseId());
		activityCoursePlaceImpl.setPlaceSIGId(activityCoursePlace.getPlaceSIGId());
		activityCoursePlaceImpl.setPlaceName(activityCoursePlace.getPlaceName());
		activityCoursePlaceImpl.setPlaceStreetNumber(activityCoursePlace.getPlaceStreetNumber());
		activityCoursePlaceImpl.setPlaceStreetName(activityCoursePlace.getPlaceStreetName());
		activityCoursePlaceImpl.setPlaceZipCode(activityCoursePlace.getPlaceZipCode());
		activityCoursePlaceImpl.setPlaceCityId(activityCoursePlace.getPlaceCityId());

		return activityCoursePlaceImpl;
	}

	/**
	 * Returns the activity course place with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the activity course place
	 * @return the activity course place
	 * @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	 */
	@Override
	public ActivityCoursePlace findByPrimaryKey(Serializable primaryKey)
		throws NoSuchActivityCoursePlaceException {
		ActivityCoursePlace activityCoursePlace = fetchByPrimaryKey(primaryKey);

		if (activityCoursePlace == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchActivityCoursePlaceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return activityCoursePlace;
	}

	/**
	 * Returns the activity course place with the primary key or throws a {@link NoSuchActivityCoursePlaceException} if it could not be found.
	 *
	 * @param activityCoursePlaceId the primary key of the activity course place
	 * @return the activity course place
	 * @throws NoSuchActivityCoursePlaceException if a activity course place with the primary key could not be found
	 */
	@Override
	public ActivityCoursePlace findByPrimaryKey(long activityCoursePlaceId)
		throws NoSuchActivityCoursePlaceException {
		return findByPrimaryKey((Serializable)activityCoursePlaceId);
	}

	/**
	 * Returns the activity course place with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the activity course place
	 * @return the activity course place, or <code>null</code> if a activity course place with the primary key could not be found
	 */
	@Override
	public ActivityCoursePlace fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
				ActivityCoursePlaceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ActivityCoursePlace activityCoursePlace = (ActivityCoursePlace)serializable;

		if (activityCoursePlace == null) {
			Session session = null;

			try {
				session = openSession();

				activityCoursePlace = (ActivityCoursePlace)session.get(ActivityCoursePlaceImpl.class,
						primaryKey);

				if (activityCoursePlace != null) {
					cacheResult(activityCoursePlace);
				}
				else {
					entityCache.putResult(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
						ActivityCoursePlaceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
					ActivityCoursePlaceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return activityCoursePlace;
	}

	/**
	 * Returns the activity course place with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param activityCoursePlaceId the primary key of the activity course place
	 * @return the activity course place, or <code>null</code> if a activity course place with the primary key could not be found
	 */
	@Override
	public ActivityCoursePlace fetchByPrimaryKey(long activityCoursePlaceId) {
		return fetchByPrimaryKey((Serializable)activityCoursePlaceId);
	}

	@Override
	public Map<Serializable, ActivityCoursePlace> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ActivityCoursePlace> map = new HashMap<Serializable, ActivityCoursePlace>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ActivityCoursePlace activityCoursePlace = fetchByPrimaryKey(primaryKey);

			if (activityCoursePlace != null) {
				map.put(primaryKey, activityCoursePlace);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
					ActivityCoursePlaceImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ActivityCoursePlace)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACTIVITYCOURSEPLACE_WHERE_PKS_IN);

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

			for (ActivityCoursePlace activityCoursePlace : (List<ActivityCoursePlace>)q.list()) {
				map.put(activityCoursePlace.getPrimaryKeyObj(),
					activityCoursePlace);

				cacheResult(activityCoursePlace);

				uncachedPrimaryKeys.remove(activityCoursePlace.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ActivityCoursePlaceModelImpl.ENTITY_CACHE_ENABLED,
					ActivityCoursePlaceImpl.class, primaryKey, nullModel);
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
	 * Returns all the activity course places.
	 *
	 * @return the activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the activity course places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @return the range of activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the activity course places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findAll(int start, int end,
		OrderByComparator<ActivityCoursePlace> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the activity course places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of activity course places
	 * @param end the upper bound of the range of activity course places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of activity course places
	 */
	@Override
	public List<ActivityCoursePlace> findAll(int start, int end,
		OrderByComparator<ActivityCoursePlace> orderByComparator,
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

		List<ActivityCoursePlace> list = null;

		if (retrieveFromCache) {
			list = (List<ActivityCoursePlace>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACTIVITYCOURSEPLACE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACTIVITYCOURSEPLACE;

				if (pagination) {
					sql = sql.concat(ActivityCoursePlaceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ActivityCoursePlace>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActivityCoursePlace>)QueryUtil.list(q,
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
	 * Removes all the activity course places from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ActivityCoursePlace activityCoursePlace : findAll()) {
			remove(activityCoursePlace);
		}
	}

	/**
	 * Returns the number of activity course places.
	 *
	 * @return the number of activity course places
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACTIVITYCOURSEPLACE);

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
		return ActivityCoursePlaceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the activity course place persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ActivityCoursePlaceImpl.class.getName());
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
	private static final String _SQL_SELECT_ACTIVITYCOURSEPLACE = "SELECT activityCoursePlace FROM ActivityCoursePlace activityCoursePlace";
	private static final String _SQL_SELECT_ACTIVITYCOURSEPLACE_WHERE_PKS_IN = "SELECT activityCoursePlace FROM ActivityCoursePlace activityCoursePlace WHERE activityCoursePlaceId IN (";
	private static final String _SQL_SELECT_ACTIVITYCOURSEPLACE_WHERE = "SELECT activityCoursePlace FROM ActivityCoursePlace activityCoursePlace WHERE ";
	private static final String _SQL_COUNT_ACTIVITYCOURSEPLACE = "SELECT COUNT(activityCoursePlace) FROM ActivityCoursePlace activityCoursePlace";
	private static final String _SQL_COUNT_ACTIVITYCOURSEPLACE_WHERE = "SELECT COUNT(activityCoursePlace) FROM ActivityCoursePlace activityCoursePlace WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "activityCoursePlace.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ActivityCoursePlace exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ActivityCoursePlace exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ActivityCoursePlacePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}