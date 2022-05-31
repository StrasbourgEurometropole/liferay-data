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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.gtfs.exception.NoSuchDirectionException;
import eu.strasbourg.service.gtfs.model.Direction;
import eu.strasbourg.service.gtfs.model.impl.DirectionImpl;
import eu.strasbourg.service.gtfs.model.impl.DirectionModelImpl;
import eu.strasbourg.service.gtfs.service.persistence.DirectionPersistence;

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
 * The persistence implementation for the direction service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @generated
 */
public class DirectionPersistenceImpl
	extends BasePersistenceImpl<Direction> implements DirectionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DirectionUtil</code> to access the direction persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DirectionImpl.class.getName();

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
	 * Returns all the directions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching directions
	 */
	@Override
	public List<Direction> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the directions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	@Override
	public List<Direction> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the directions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	@Override
	public List<Direction> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Direction> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the directions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching directions
	 */
	@Override
	public List<Direction> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Direction> orderByComparator,
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

		List<Direction> list = null;

		if (useFinderCache) {
			list = (List<Direction>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Direction direction : list) {
					if (!uuid.equals(direction.getUuid())) {
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

			sb.append(_SQL_SELECT_DIRECTION_WHERE);

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
				sb.append(DirectionModelImpl.ORDER_BY_JPQL);
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

				list = (List<Direction>)QueryUtil.list(
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
	 * Returns the first direction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	@Override
	public Direction findByUuid_First(
			String uuid, OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		Direction direction = fetchByUuid_First(uuid, orderByComparator);

		if (direction != null) {
			return direction;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchDirectionException(sb.toString());
	}

	/**
	 * Returns the first direction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByUuid_First(
		String uuid, OrderByComparator<Direction> orderByComparator) {

		List<Direction> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last direction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	@Override
	public Direction findByUuid_Last(
			String uuid, OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		Direction direction = fetchByUuid_Last(uuid, orderByComparator);

		if (direction != null) {
			return direction;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchDirectionException(sb.toString());
	}

	/**
	 * Returns the last direction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByUuid_Last(
		String uuid, OrderByComparator<Direction> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Direction> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the directions before and after the current direction in the ordered set where uuid = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	@Override
	public Direction[] findByUuid_PrevAndNext(
			long directionId, String uuid,
			OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		uuid = Objects.toString(uuid, "");

		Direction direction = findByPrimaryKey(directionId);

		Session session = null;

		try {
			session = openSession();

			Direction[] array = new DirectionImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, direction, uuid, orderByComparator, true);

			array[1] = direction;

			array[2] = getByUuid_PrevAndNext(
				session, direction, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Direction getByUuid_PrevAndNext(
		Session session, Direction direction, String uuid,
		OrderByComparator<Direction> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DIRECTION_WHERE);

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
			sb.append(DirectionModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(direction)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Direction> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the directions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Direction direction :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(direction);
		}
	}

	/**
	 * Returns the number of directions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching directions
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DIRECTION_WHERE);

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
		"direction.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(direction.uuid IS NULL OR direction.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the direction where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchDirectionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	@Override
	public Direction findByUUID_G(String uuid, long groupId)
		throws NoSuchDirectionException {

		Direction direction = fetchByUUID_G(uuid, groupId);

		if (direction == null) {
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

			throw new NoSuchDirectionException(sb.toString());
		}

		return direction;
	}

	/**
	 * Returns the direction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the direction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByUUID_G(
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

		if (result instanceof Direction) {
			Direction direction = (Direction)result;

			if (!Objects.equals(uuid, direction.getUuid()) ||
				(groupId != direction.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_DIRECTION_WHERE);

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

				List<Direction> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					Direction direction = list.get(0);

					result = direction;

					cacheResult(direction);
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
			return (Direction)result;
		}
	}

	/**
	 * Removes the direction where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the direction that was removed
	 */
	@Override
	public Direction removeByUUID_G(String uuid, long groupId)
		throws NoSuchDirectionException {

		Direction direction = findByUUID_G(uuid, groupId);

		return remove(direction);
	}

	/**
	 * Returns the number of directions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching directions
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DIRECTION_WHERE);

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
		"direction.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(direction.uuid IS NULL OR direction.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"direction.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the directions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching directions
	 */
	@Override
	public List<Direction> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the directions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	@Override
	public List<Direction> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the directions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	@Override
	public List<Direction> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Direction> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the directions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching directions
	 */
	@Override
	public List<Direction> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Direction> orderByComparator,
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

		List<Direction> list = null;

		if (useFinderCache) {
			list = (List<Direction>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Direction direction : list) {
					if (!uuid.equals(direction.getUuid()) ||
						(companyId != direction.getCompanyId())) {

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

			sb.append(_SQL_SELECT_DIRECTION_WHERE);

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
				sb.append(DirectionModelImpl.ORDER_BY_JPQL);
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

				list = (List<Direction>)QueryUtil.list(
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
	 * Returns the first direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	@Override
	public Direction findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		Direction direction = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (direction != null) {
			return direction;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchDirectionException(sb.toString());
	}

	/**
	 * Returns the first direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Direction> orderByComparator) {

		List<Direction> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	@Override
	public Direction findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		Direction direction = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (direction != null) {
			return direction;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchDirectionException(sb.toString());
	}

	/**
	 * Returns the last direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Direction> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Direction> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the directions before and after the current direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	@Override
	public Direction[] findByUuid_C_PrevAndNext(
			long directionId, String uuid, long companyId,
			OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		uuid = Objects.toString(uuid, "");

		Direction direction = findByPrimaryKey(directionId);

		Session session = null;

		try {
			session = openSession();

			Direction[] array = new DirectionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, direction, uuid, companyId, orderByComparator, true);

			array[1] = direction;

			array[2] = getByUuid_C_PrevAndNext(
				session, direction, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Direction getByUuid_C_PrevAndNext(
		Session session, Direction direction, String uuid, long companyId,
		OrderByComparator<Direction> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_DIRECTION_WHERE);

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
			sb.append(DirectionModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(direction)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Direction> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the directions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Direction direction :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(direction);
		}
	}

	/**
	 * Returns the number of directions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching directions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DIRECTION_WHERE);

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
		"direction.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(direction.uuid IS NULL OR direction.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"direction.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the directions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching directions
	 */
	@Override
	public List<Direction> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the directions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	@Override
	public List<Direction> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the directions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	@Override
	public List<Direction> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Direction> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the directions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching directions
	 */
	@Override
	public List<Direction> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Direction> orderByComparator,
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

		List<Direction> list = null;

		if (useFinderCache) {
			list = (List<Direction>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Direction direction : list) {
					if (groupId != direction.getGroupId()) {
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

			sb.append(_SQL_SELECT_DIRECTION_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DirectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<Direction>)QueryUtil.list(
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
	 * Returns the first direction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	@Override
	public Direction findByGroupId_First(
			long groupId, OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		Direction direction = fetchByGroupId_First(groupId, orderByComparator);

		if (direction != null) {
			return direction;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchDirectionException(sb.toString());
	}

	/**
	 * Returns the first direction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByGroupId_First(
		long groupId, OrderByComparator<Direction> orderByComparator) {

		List<Direction> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last direction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	@Override
	public Direction findByGroupId_Last(
			long groupId, OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		Direction direction = fetchByGroupId_Last(groupId, orderByComparator);

		if (direction != null) {
			return direction;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchDirectionException(sb.toString());
	}

	/**
	 * Returns the last direction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByGroupId_Last(
		long groupId, OrderByComparator<Direction> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Direction> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the directions before and after the current direction in the ordered set where groupId = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	@Override
	public Direction[] findByGroupId_PrevAndNext(
			long directionId, long groupId,
			OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		Direction direction = findByPrimaryKey(directionId);

		Session session = null;

		try {
			session = openSession();

			Direction[] array = new DirectionImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, direction, groupId, orderByComparator, true);

			array[1] = direction;

			array[2] = getByGroupId_PrevAndNext(
				session, direction, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Direction getByGroupId_PrevAndNext(
		Session session, Direction direction, long groupId,
		OrderByComparator<Direction> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DIRECTION_WHERE);

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
			sb.append(DirectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(direction)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Direction> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the directions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (Direction direction :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(direction);
		}
	}

	/**
	 * Returns the number of directions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching directions
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DIRECTION_WHERE);

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
		"direction.groupId = ?";

	private FinderPath _finderPathFetchByTripId;
	private FinderPath _finderPathCountByTripId;

	/**
	 * Returns the direction where tripId = &#63; or throws a <code>NoSuchDirectionException</code> if it could not be found.
	 *
	 * @param tripId the trip ID
	 * @return the matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	@Override
	public Direction findByTripId(String tripId)
		throws NoSuchDirectionException {

		Direction direction = fetchByTripId(tripId);

		if (direction == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("tripId=");
			sb.append(tripId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchDirectionException(sb.toString());
		}

		return direction;
	}

	/**
	 * Returns the direction where tripId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param tripId the trip ID
	 * @return the matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByTripId(String tripId) {
		return fetchByTripId(tripId, true);
	}

	/**
	 * Returns the direction where tripId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param tripId the trip ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByTripId(String tripId, boolean useFinderCache) {
		tripId = Objects.toString(tripId, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {tripId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByTripId, finderArgs, this);
		}

		if (result instanceof Direction) {
			Direction direction = (Direction)result;

			if (!Objects.equals(tripId, direction.getTripId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_DIRECTION_WHERE);

			boolean bindTripId = false;

			if (tripId.isEmpty()) {
				sb.append(_FINDER_COLUMN_TRIPID_TRIPID_3);
			}
			else {
				bindTripId = true;

				sb.append(_FINDER_COLUMN_TRIPID_TRIPID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindTripId) {
					queryPos.add(tripId);
				}

				List<Direction> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByTripId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {tripId};
							}

							_log.warn(
								"DirectionPersistenceImpl.fetchByTripId(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Direction direction = list.get(0);

					result = direction;

					cacheResult(direction);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByTripId, finderArgs);
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
			return (Direction)result;
		}
	}

	/**
	 * Removes the direction where tripId = &#63; from the database.
	 *
	 * @param tripId the trip ID
	 * @return the direction that was removed
	 */
	@Override
	public Direction removeByTripId(String tripId)
		throws NoSuchDirectionException {

		Direction direction = findByTripId(tripId);

		return remove(direction);
	}

	/**
	 * Returns the number of directions where tripId = &#63;.
	 *
	 * @param tripId the trip ID
	 * @return the number of matching directions
	 */
	@Override
	public int countByTripId(String tripId) {
		tripId = Objects.toString(tripId, "");

		FinderPath finderPath = _finderPathCountByTripId;

		Object[] finderArgs = new Object[] {tripId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DIRECTION_WHERE);

			boolean bindTripId = false;

			if (tripId.isEmpty()) {
				sb.append(_FINDER_COLUMN_TRIPID_TRIPID_3);
			}
			else {
				bindTripId = true;

				sb.append(_FINDER_COLUMN_TRIPID_TRIPID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindTripId) {
					queryPos.add(tripId);
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

	private static final String _FINDER_COLUMN_TRIPID_TRIPID_2 =
		"direction.tripId = ?";

	private static final String _FINDER_COLUMN_TRIPID_TRIPID_3 =
		"(direction.tripId IS NULL OR direction.tripId = '')";

	private FinderPath _finderPathWithPaginationFindByStopId;
	private FinderPath _finderPathWithoutPaginationFindByStopId;
	private FinderPath _finderPathCountByStopId;

	/**
	 * Returns all the directions where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @return the matching directions
	 */
	@Override
	public List<Direction> findByStopId(String stopId) {
		return findByStopId(stopId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the directions where stopId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param stopId the stop ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	@Override
	public List<Direction> findByStopId(String stopId, int start, int end) {
		return findByStopId(stopId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the directions where stopId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param stopId the stop ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	@Override
	public List<Direction> findByStopId(
		String stopId, int start, int end,
		OrderByComparator<Direction> orderByComparator) {

		return findByStopId(stopId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the directions where stopId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param stopId the stop ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching directions
	 */
	@Override
	public List<Direction> findByStopId(
		String stopId, int start, int end,
		OrderByComparator<Direction> orderByComparator,
		boolean useFinderCache) {

		stopId = Objects.toString(stopId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByStopId;
				finderArgs = new Object[] {stopId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByStopId;
			finderArgs = new Object[] {stopId, start, end, orderByComparator};
		}

		List<Direction> list = null;

		if (useFinderCache) {
			list = (List<Direction>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Direction direction : list) {
					if (!stopId.equals(direction.getStopId())) {
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

			sb.append(_SQL_SELECT_DIRECTION_WHERE);

			boolean bindStopId = false;

			if (stopId.isEmpty()) {
				sb.append(_FINDER_COLUMN_STOPID_STOPID_3);
			}
			else {
				bindStopId = true;

				sb.append(_FINDER_COLUMN_STOPID_STOPID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DirectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindStopId) {
					queryPos.add(stopId);
				}

				list = (List<Direction>)QueryUtil.list(
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
	 * Returns the first direction in the ordered set where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	@Override
	public Direction findByStopId_First(
			String stopId, OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		Direction direction = fetchByStopId_First(stopId, orderByComparator);

		if (direction != null) {
			return direction;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("stopId=");
		sb.append(stopId);

		sb.append("}");

		throw new NoSuchDirectionException(sb.toString());
	}

	/**
	 * Returns the first direction in the ordered set where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByStopId_First(
		String stopId, OrderByComparator<Direction> orderByComparator) {

		List<Direction> list = findByStopId(stopId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last direction in the ordered set where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	@Override
	public Direction findByStopId_Last(
			String stopId, OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		Direction direction = fetchByStopId_Last(stopId, orderByComparator);

		if (direction != null) {
			return direction;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("stopId=");
		sb.append(stopId);

		sb.append("}");

		throw new NoSuchDirectionException(sb.toString());
	}

	/**
	 * Returns the last direction in the ordered set where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByStopId_Last(
		String stopId, OrderByComparator<Direction> orderByComparator) {

		int count = countByStopId(stopId);

		if (count == 0) {
			return null;
		}

		List<Direction> list = findByStopId(
			stopId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the directions before and after the current direction in the ordered set where stopId = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	@Override
	public Direction[] findByStopId_PrevAndNext(
			long directionId, String stopId,
			OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		stopId = Objects.toString(stopId, "");

		Direction direction = findByPrimaryKey(directionId);

		Session session = null;

		try {
			session = openSession();

			Direction[] array = new DirectionImpl[3];

			array[0] = getByStopId_PrevAndNext(
				session, direction, stopId, orderByComparator, true);

			array[1] = direction;

			array[2] = getByStopId_PrevAndNext(
				session, direction, stopId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Direction getByStopId_PrevAndNext(
		Session session, Direction direction, String stopId,
		OrderByComparator<Direction> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DIRECTION_WHERE);

		boolean bindStopId = false;

		if (stopId.isEmpty()) {
			sb.append(_FINDER_COLUMN_STOPID_STOPID_3);
		}
		else {
			bindStopId = true;

			sb.append(_FINDER_COLUMN_STOPID_STOPID_2);
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
			sb.append(DirectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindStopId) {
			queryPos.add(stopId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(direction)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Direction> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the directions where stopId = &#63; from the database.
	 *
	 * @param stopId the stop ID
	 */
	@Override
	public void removeByStopId(String stopId) {
		for (Direction direction :
				findByStopId(
					stopId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(direction);
		}
	}

	/**
	 * Returns the number of directions where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @return the number of matching directions
	 */
	@Override
	public int countByStopId(String stopId) {
		stopId = Objects.toString(stopId, "");

		FinderPath finderPath = _finderPathCountByStopId;

		Object[] finderArgs = new Object[] {stopId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DIRECTION_WHERE);

			boolean bindStopId = false;

			if (stopId.isEmpty()) {
				sb.append(_FINDER_COLUMN_STOPID_STOPID_3);
			}
			else {
				bindStopId = true;

				sb.append(_FINDER_COLUMN_STOPID_STOPID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindStopId) {
					queryPos.add(stopId);
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

	private static final String _FINDER_COLUMN_STOPID_STOPID_2 =
		"direction.stopId = ?";

	private static final String _FINDER_COLUMN_STOPID_STOPID_3 =
		"(direction.stopId IS NULL OR direction.stopId = '')";

	private FinderPath _finderPathWithPaginationFindByRouteId;
	private FinderPath _finderPathWithoutPaginationFindByRouteId;
	private FinderPath _finderPathCountByRouteId;

	/**
	 * Returns all the directions where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @return the matching directions
	 */
	@Override
	public List<Direction> findByRouteId(String routeId) {
		return findByRouteId(
			routeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the directions where routeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param routeId the route ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	@Override
	public List<Direction> findByRouteId(String routeId, int start, int end) {
		return findByRouteId(routeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the directions where routeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param routeId the route ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	@Override
	public List<Direction> findByRouteId(
		String routeId, int start, int end,
		OrderByComparator<Direction> orderByComparator) {

		return findByRouteId(routeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the directions where routeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param routeId the route ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching directions
	 */
	@Override
	public List<Direction> findByRouteId(
		String routeId, int start, int end,
		OrderByComparator<Direction> orderByComparator,
		boolean useFinderCache) {

		routeId = Objects.toString(routeId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByRouteId;
				finderArgs = new Object[] {routeId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByRouteId;
			finderArgs = new Object[] {routeId, start, end, orderByComparator};
		}

		List<Direction> list = null;

		if (useFinderCache) {
			list = (List<Direction>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Direction direction : list) {
					if (!routeId.equals(direction.getRouteId())) {
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

			sb.append(_SQL_SELECT_DIRECTION_WHERE);

			boolean bindRouteId = false;

			if (routeId.isEmpty()) {
				sb.append(_FINDER_COLUMN_ROUTEID_ROUTEID_3);
			}
			else {
				bindRouteId = true;

				sb.append(_FINDER_COLUMN_ROUTEID_ROUTEID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DirectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindRouteId) {
					queryPos.add(routeId);
				}

				list = (List<Direction>)QueryUtil.list(
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
	 * Returns the first direction in the ordered set where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	@Override
	public Direction findByRouteId_First(
			String routeId, OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		Direction direction = fetchByRouteId_First(routeId, orderByComparator);

		if (direction != null) {
			return direction;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("routeId=");
		sb.append(routeId);

		sb.append("}");

		throw new NoSuchDirectionException(sb.toString());
	}

	/**
	 * Returns the first direction in the ordered set where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByRouteId_First(
		String routeId, OrderByComparator<Direction> orderByComparator) {

		List<Direction> list = findByRouteId(routeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last direction in the ordered set where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	@Override
	public Direction findByRouteId_Last(
			String routeId, OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		Direction direction = fetchByRouteId_Last(routeId, orderByComparator);

		if (direction != null) {
			return direction;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("routeId=");
		sb.append(routeId);

		sb.append("}");

		throw new NoSuchDirectionException(sb.toString());
	}

	/**
	 * Returns the last direction in the ordered set where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Override
	public Direction fetchByRouteId_Last(
		String routeId, OrderByComparator<Direction> orderByComparator) {

		int count = countByRouteId(routeId);

		if (count == 0) {
			return null;
		}

		List<Direction> list = findByRouteId(
			routeId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the directions before and after the current direction in the ordered set where routeId = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	@Override
	public Direction[] findByRouteId_PrevAndNext(
			long directionId, String routeId,
			OrderByComparator<Direction> orderByComparator)
		throws NoSuchDirectionException {

		routeId = Objects.toString(routeId, "");

		Direction direction = findByPrimaryKey(directionId);

		Session session = null;

		try {
			session = openSession();

			Direction[] array = new DirectionImpl[3];

			array[0] = getByRouteId_PrevAndNext(
				session, direction, routeId, orderByComparator, true);

			array[1] = direction;

			array[2] = getByRouteId_PrevAndNext(
				session, direction, routeId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Direction getByRouteId_PrevAndNext(
		Session session, Direction direction, String routeId,
		OrderByComparator<Direction> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DIRECTION_WHERE);

		boolean bindRouteId = false;

		if (routeId.isEmpty()) {
			sb.append(_FINDER_COLUMN_ROUTEID_ROUTEID_3);
		}
		else {
			bindRouteId = true;

			sb.append(_FINDER_COLUMN_ROUTEID_ROUTEID_2);
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
			sb.append(DirectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindRouteId) {
			queryPos.add(routeId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(direction)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Direction> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the directions where routeId = &#63; from the database.
	 *
	 * @param routeId the route ID
	 */
	@Override
	public void removeByRouteId(String routeId) {
		for (Direction direction :
				findByRouteId(
					routeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(direction);
		}
	}

	/**
	 * Returns the number of directions where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @return the number of matching directions
	 */
	@Override
	public int countByRouteId(String routeId) {
		routeId = Objects.toString(routeId, "");

		FinderPath finderPath = _finderPathCountByRouteId;

		Object[] finderArgs = new Object[] {routeId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DIRECTION_WHERE);

			boolean bindRouteId = false;

			if (routeId.isEmpty()) {
				sb.append(_FINDER_COLUMN_ROUTEID_ROUTEID_3);
			}
			else {
				bindRouteId = true;

				sb.append(_FINDER_COLUMN_ROUTEID_ROUTEID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindRouteId) {
					queryPos.add(routeId);
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

	private static final String _FINDER_COLUMN_ROUTEID_ROUTEID_2 =
		"direction.routeId = ?";

	private static final String _FINDER_COLUMN_ROUTEID_ROUTEID_3 =
		"(direction.routeId IS NULL OR direction.routeId = '')";

	public DirectionPersistenceImpl() {
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

		setModelClass(Direction.class);
	}

	/**
	 * Caches the direction in the entity cache if it is enabled.
	 *
	 * @param direction the direction
	 */
	@Override
	public void cacheResult(Direction direction) {
		entityCache.putResult(
			DirectionModelImpl.ENTITY_CACHE_ENABLED, DirectionImpl.class,
			direction.getPrimaryKey(), direction);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {direction.getUuid(), direction.getGroupId()},
			direction);

		finderCache.putResult(
			_finderPathFetchByTripId, new Object[] {direction.getTripId()},
			direction);

		direction.resetOriginalValues();
	}

	/**
	 * Caches the directions in the entity cache if it is enabled.
	 *
	 * @param directions the directions
	 */
	@Override
	public void cacheResult(List<Direction> directions) {
		for (Direction direction : directions) {
			if (entityCache.getResult(
					DirectionModelImpl.ENTITY_CACHE_ENABLED,
					DirectionImpl.class, direction.getPrimaryKey()) == null) {

				cacheResult(direction);
			}
			else {
				direction.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all directions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DirectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the direction.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Direction direction) {
		entityCache.removeResult(
			DirectionModelImpl.ENTITY_CACHE_ENABLED, DirectionImpl.class,
			direction.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DirectionModelImpl)direction, true);
	}

	@Override
	public void clearCache(List<Direction> directions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Direction direction : directions) {
			entityCache.removeResult(
				DirectionModelImpl.ENTITY_CACHE_ENABLED, DirectionImpl.class,
				direction.getPrimaryKey());

			clearUniqueFindersCache((DirectionModelImpl)direction, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				DirectionModelImpl.ENTITY_CACHE_ENABLED, DirectionImpl.class,
				primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DirectionModelImpl directionModelImpl) {

		Object[] args = new Object[] {
			directionModelImpl.getUuid(), directionModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, directionModelImpl, false);

		args = new Object[] {directionModelImpl.getTripId()};

		finderCache.putResult(
			_finderPathCountByTripId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByTripId, args, directionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DirectionModelImpl directionModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				directionModelImpl.getUuid(), directionModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((directionModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				directionModelImpl.getOriginalUuid(),
				directionModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {directionModelImpl.getTripId()};

			finderCache.removeResult(_finderPathCountByTripId, args);
			finderCache.removeResult(_finderPathFetchByTripId, args);
		}

		if ((directionModelImpl.getColumnBitmask() &
			 _finderPathFetchByTripId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				directionModelImpl.getOriginalTripId()
			};

			finderCache.removeResult(_finderPathCountByTripId, args);
			finderCache.removeResult(_finderPathFetchByTripId, args);
		}
	}

	/**
	 * Creates a new direction with the primary key. Does not add the direction to the database.
	 *
	 * @param directionId the primary key for the new direction
	 * @return the new direction
	 */
	@Override
	public Direction create(long directionId) {
		Direction direction = new DirectionImpl();

		direction.setNew(true);
		direction.setPrimaryKey(directionId);

		String uuid = PortalUUIDUtil.generate();

		direction.setUuid(uuid);

		direction.setCompanyId(CompanyThreadLocal.getCompanyId());

		return direction;
	}

	/**
	 * Removes the direction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param directionId the primary key of the direction
	 * @return the direction that was removed
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	@Override
	public Direction remove(long directionId) throws NoSuchDirectionException {
		return remove((Serializable)directionId);
	}

	/**
	 * Removes the direction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the direction
	 * @return the direction that was removed
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	@Override
	public Direction remove(Serializable primaryKey)
		throws NoSuchDirectionException {

		Session session = null;

		try {
			session = openSession();

			Direction direction = (Direction)session.get(
				DirectionImpl.class, primaryKey);

			if (direction == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDirectionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(direction);
		}
		catch (NoSuchDirectionException noSuchEntityException) {
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
	protected Direction removeImpl(Direction direction) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(direction)) {
				direction = (Direction)session.get(
					DirectionImpl.class, direction.getPrimaryKeyObj());
			}

			if (direction != null) {
				session.delete(direction);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (direction != null) {
			clearCache(direction);
		}

		return direction;
	}

	@Override
	public Direction updateImpl(Direction direction) {
		boolean isNew = direction.isNew();

		if (!(direction instanceof DirectionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(direction.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(direction);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in direction proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Direction implementation " +
					direction.getClass());
		}

		DirectionModelImpl directionModelImpl = (DirectionModelImpl)direction;

		if (Validator.isNull(direction.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			direction.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (direction.isNew()) {
				session.save(direction);

				direction.setNew(false);
			}
			else {
				direction = (Direction)session.merge(direction);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DirectionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {directionModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				directionModelImpl.getUuid(), directionModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {directionModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {directionModelImpl.getStopId()};

			finderCache.removeResult(_finderPathCountByStopId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByStopId, args);

			args = new Object[] {directionModelImpl.getRouteId()};

			finderCache.removeResult(_finderPathCountByRouteId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByRouteId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((directionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					directionModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {directionModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((directionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					directionModelImpl.getOriginalUuid(),
					directionModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					directionModelImpl.getUuid(),
					directionModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((directionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					directionModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {directionModelImpl.getGroupId()};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((directionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByStopId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					directionModelImpl.getOriginalStopId()
				};

				finderCache.removeResult(_finderPathCountByStopId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByStopId, args);

				args = new Object[] {directionModelImpl.getStopId()};

				finderCache.removeResult(_finderPathCountByStopId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByStopId, args);
			}

			if ((directionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByRouteId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					directionModelImpl.getOriginalRouteId()
				};

				finderCache.removeResult(_finderPathCountByRouteId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByRouteId, args);

				args = new Object[] {directionModelImpl.getRouteId()};

				finderCache.removeResult(_finderPathCountByRouteId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByRouteId, args);
			}
		}

		entityCache.putResult(
			DirectionModelImpl.ENTITY_CACHE_ENABLED, DirectionImpl.class,
			direction.getPrimaryKey(), direction, false);

		clearUniqueFindersCache(directionModelImpl, false);
		cacheUniqueFindersCache(directionModelImpl);

		direction.resetOriginalValues();

		return direction;
	}

	/**
	 * Returns the direction with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the direction
	 * @return the direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	@Override
	public Direction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDirectionException {

		Direction direction = fetchByPrimaryKey(primaryKey);

		if (direction == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDirectionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return direction;
	}

	/**
	 * Returns the direction with the primary key or throws a <code>NoSuchDirectionException</code> if it could not be found.
	 *
	 * @param directionId the primary key of the direction
	 * @return the direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	@Override
	public Direction findByPrimaryKey(long directionId)
		throws NoSuchDirectionException {

		return findByPrimaryKey((Serializable)directionId);
	}

	/**
	 * Returns the direction with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the direction
	 * @return the direction, or <code>null</code> if a direction with the primary key could not be found
	 */
	@Override
	public Direction fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			DirectionModelImpl.ENTITY_CACHE_ENABLED, DirectionImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Direction direction = (Direction)serializable;

		if (direction == null) {
			Session session = null;

			try {
				session = openSession();

				direction = (Direction)session.get(
					DirectionImpl.class, primaryKey);

				if (direction != null) {
					cacheResult(direction);
				}
				else {
					entityCache.putResult(
						DirectionModelImpl.ENTITY_CACHE_ENABLED,
						DirectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					DirectionModelImpl.ENTITY_CACHE_ENABLED,
					DirectionImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return direction;
	}

	/**
	 * Returns the direction with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param directionId the primary key of the direction
	 * @return the direction, or <code>null</code> if a direction with the primary key could not be found
	 */
	@Override
	public Direction fetchByPrimaryKey(long directionId) {
		return fetchByPrimaryKey((Serializable)directionId);
	}

	@Override
	public Map<Serializable, Direction> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Direction> map =
			new HashMap<Serializable, Direction>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Direction direction = fetchByPrimaryKey(primaryKey);

			if (direction != null) {
				map.put(primaryKey, direction);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				DirectionModelImpl.ENTITY_CACHE_ENABLED, DirectionImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Direction)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_DIRECTION_WHERE_PKS_IN);

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

			for (Direction direction : (List<Direction>)query.list()) {
				map.put(direction.getPrimaryKeyObj(), direction);

				cacheResult(direction);

				uncachedPrimaryKeys.remove(direction.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					DirectionModelImpl.ENTITY_CACHE_ENABLED,
					DirectionImpl.class, primaryKey, nullModel);
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
	 * Returns all the directions.
	 *
	 * @return the directions
	 */
	@Override
	public List<Direction> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the directions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of directions
	 */
	@Override
	public List<Direction> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the directions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of directions
	 */
	@Override
	public List<Direction> findAll(
		int start, int end, OrderByComparator<Direction> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the directions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of directions
	 */
	@Override
	public List<Direction> findAll(
		int start, int end, OrderByComparator<Direction> orderByComparator,
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

		List<Direction> list = null;

		if (useFinderCache) {
			list = (List<Direction>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DIRECTION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DIRECTION;

				sql = sql.concat(DirectionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Direction>)QueryUtil.list(
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
	 * Removes all the directions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Direction direction : findAll()) {
			remove(direction);
		}
	}

	/**
	 * Returns the number of directions.
	 *
	 * @return the number of directions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DIRECTION);

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
		return DirectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the direction persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			DirectionModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			DirectionModelImpl.UUID_COLUMN_BITMASK |
			DirectionModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			DirectionModelImpl.UUID_COLUMN_BITMASK |
			DirectionModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			DirectionModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathFetchByTripId = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByTripId",
			new String[] {String.class.getName()},
			DirectionModelImpl.TRIPID_COLUMN_BITMASK);

		_finderPathCountByTripId = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTripId",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByStopId = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStopId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByStopId = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStopId",
			new String[] {String.class.getName()},
			DirectionModelImpl.STOPID_COLUMN_BITMASK);

		_finderPathCountByStopId = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStopId",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByRouteId = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRouteId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByRouteId = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, DirectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRouteId",
			new String[] {String.class.getName()},
			DirectionModelImpl.ROUTEID_COLUMN_BITMASK);

		_finderPathCountByRouteId = new FinderPath(
			DirectionModelImpl.ENTITY_CACHE_ENABLED,
			DirectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRouteId",
			new String[] {String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(DirectionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_DIRECTION =
		"SELECT direction FROM Direction direction";

	private static final String _SQL_SELECT_DIRECTION_WHERE_PKS_IN =
		"SELECT direction FROM Direction direction WHERE directionId IN (";

	private static final String _SQL_SELECT_DIRECTION_WHERE =
		"SELECT direction FROM Direction direction WHERE ";

	private static final String _SQL_COUNT_DIRECTION =
		"SELECT COUNT(direction) FROM Direction direction";

	private static final String _SQL_COUNT_DIRECTION_WHERE =
		"SELECT COUNT(direction) FROM Direction direction WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "direction.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Direction exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Direction exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DirectionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}