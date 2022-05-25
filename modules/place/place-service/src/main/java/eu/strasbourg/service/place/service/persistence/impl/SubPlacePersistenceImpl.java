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

import eu.strasbourg.service.place.exception.NoSuchSubPlaceException;
import eu.strasbourg.service.place.model.SubPlace;
import eu.strasbourg.service.place.model.impl.SubPlaceImpl;
import eu.strasbourg.service.place.model.impl.SubPlaceModelImpl;
import eu.strasbourg.service.place.service.persistence.SubPlacePersistence;

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
 * The persistence implementation for the sub place service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @generated
 */
public class SubPlacePersistenceImpl
	extends BasePersistenceImpl<SubPlace> implements SubPlacePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SubPlaceUtil</code> to access the sub place persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SubPlaceImpl.class.getName();

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
	 * Returns all the sub places where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching sub places
	 */
	@Override
	public List<SubPlace> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sub places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @return the range of matching sub places
	 */
	@Override
	public List<SubPlace> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sub places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sub places
	 */
	@Override
	public List<SubPlace> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SubPlace> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sub places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sub places
	 */
	@Override
	public List<SubPlace> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SubPlace> orderByComparator, boolean useFinderCache) {

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

		List<SubPlace> list = null;

		if (useFinderCache) {
			list = (List<SubPlace>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SubPlace subPlace : list) {
					if (!uuid.equals(subPlace.getUuid())) {
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

			sb.append(_SQL_SELECT_SUBPLACE_WHERE);

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
				sb.append(SubPlaceModelImpl.ORDER_BY_JPQL);
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

				list = (List<SubPlace>)QueryUtil.list(
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
	 * Returns the first sub place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sub place
	 * @throws NoSuchSubPlaceException if a matching sub place could not be found
	 */
	@Override
	public SubPlace findByUuid_First(
			String uuid, OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException {

		SubPlace subPlace = fetchByUuid_First(uuid, orderByComparator);

		if (subPlace != null) {
			return subPlace;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchSubPlaceException(sb.toString());
	}

	/**
	 * Returns the first sub place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sub place, or <code>null</code> if a matching sub place could not be found
	 */
	@Override
	public SubPlace fetchByUuid_First(
		String uuid, OrderByComparator<SubPlace> orderByComparator) {

		List<SubPlace> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sub place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sub place
	 * @throws NoSuchSubPlaceException if a matching sub place could not be found
	 */
	@Override
	public SubPlace findByUuid_Last(
			String uuid, OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException {

		SubPlace subPlace = fetchByUuid_Last(uuid, orderByComparator);

		if (subPlace != null) {
			return subPlace;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchSubPlaceException(sb.toString());
	}

	/**
	 * Returns the last sub place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sub place, or <code>null</code> if a matching sub place could not be found
	 */
	@Override
	public SubPlace fetchByUuid_Last(
		String uuid, OrderByComparator<SubPlace> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SubPlace> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sub places before and after the current sub place in the ordered set where uuid = &#63;.
	 *
	 * @param subPlaceId the primary key of the current sub place
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sub place
	 * @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	 */
	@Override
	public SubPlace[] findByUuid_PrevAndNext(
			long subPlaceId, String uuid,
			OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException {

		uuid = Objects.toString(uuid, "");

		SubPlace subPlace = findByPrimaryKey(subPlaceId);

		Session session = null;

		try {
			session = openSession();

			SubPlace[] array = new SubPlaceImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, subPlace, uuid, orderByComparator, true);

			array[1] = subPlace;

			array[2] = getByUuid_PrevAndNext(
				session, subPlace, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SubPlace getByUuid_PrevAndNext(
		Session session, SubPlace subPlace, String uuid,
		OrderByComparator<SubPlace> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SUBPLACE_WHERE);

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
			sb.append(SubPlaceModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(subPlace)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SubPlace> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sub places where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SubPlace subPlace :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(subPlace);
		}
	}

	/**
	 * Returns the number of sub places where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching sub places
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUBPLACE_WHERE);

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
		"subPlace.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(subPlace.uuid IS NULL OR subPlace.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByPlaceId;
	private FinderPath _finderPathWithoutPaginationFindByPlaceId;
	private FinderPath _finderPathCountByPlaceId;

	/**
	 * Returns all the sub places where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @return the matching sub places
	 */
	@Override
	public List<SubPlace> findByPlaceId(long placeId) {
		return findByPlaceId(
			placeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sub places where placeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param placeId the place ID
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @return the range of matching sub places
	 */
	@Override
	public List<SubPlace> findByPlaceId(long placeId, int start, int end) {
		return findByPlaceId(placeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sub places where placeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param placeId the place ID
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sub places
	 */
	@Override
	public List<SubPlace> findByPlaceId(
		long placeId, int start, int end,
		OrderByComparator<SubPlace> orderByComparator) {

		return findByPlaceId(placeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sub places where placeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param placeId the place ID
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sub places
	 */
	@Override
	public List<SubPlace> findByPlaceId(
		long placeId, int start, int end,
		OrderByComparator<SubPlace> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPlaceId;
				finderArgs = new Object[] {placeId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPlaceId;
			finderArgs = new Object[] {placeId, start, end, orderByComparator};
		}

		List<SubPlace> list = null;

		if (useFinderCache) {
			list = (List<SubPlace>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SubPlace subPlace : list) {
					if (placeId != subPlace.getPlaceId()) {
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

			sb.append(_SQL_SELECT_SUBPLACE_WHERE);

			sb.append(_FINDER_COLUMN_PLACEID_PLACEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SubPlaceModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(placeId);

				list = (List<SubPlace>)QueryUtil.list(
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
	 * Returns the first sub place in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sub place
	 * @throws NoSuchSubPlaceException if a matching sub place could not be found
	 */
	@Override
	public SubPlace findByPlaceId_First(
			long placeId, OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException {

		SubPlace subPlace = fetchByPlaceId_First(placeId, orderByComparator);

		if (subPlace != null) {
			return subPlace;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("placeId=");
		sb.append(placeId);

		sb.append("}");

		throw new NoSuchSubPlaceException(sb.toString());
	}

	/**
	 * Returns the first sub place in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sub place, or <code>null</code> if a matching sub place could not be found
	 */
	@Override
	public SubPlace fetchByPlaceId_First(
		long placeId, OrderByComparator<SubPlace> orderByComparator) {

		List<SubPlace> list = findByPlaceId(placeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sub place in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sub place
	 * @throws NoSuchSubPlaceException if a matching sub place could not be found
	 */
	@Override
	public SubPlace findByPlaceId_Last(
			long placeId, OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException {

		SubPlace subPlace = fetchByPlaceId_Last(placeId, orderByComparator);

		if (subPlace != null) {
			return subPlace;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("placeId=");
		sb.append(placeId);

		sb.append("}");

		throw new NoSuchSubPlaceException(sb.toString());
	}

	/**
	 * Returns the last sub place in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sub place, or <code>null</code> if a matching sub place could not be found
	 */
	@Override
	public SubPlace fetchByPlaceId_Last(
		long placeId, OrderByComparator<SubPlace> orderByComparator) {

		int count = countByPlaceId(placeId);

		if (count == 0) {
			return null;
		}

		List<SubPlace> list = findByPlaceId(
			placeId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sub places before and after the current sub place in the ordered set where placeId = &#63;.
	 *
	 * @param subPlaceId the primary key of the current sub place
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sub place
	 * @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	 */
	@Override
	public SubPlace[] findByPlaceId_PrevAndNext(
			long subPlaceId, long placeId,
			OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException {

		SubPlace subPlace = findByPrimaryKey(subPlaceId);

		Session session = null;

		try {
			session = openSession();

			SubPlace[] array = new SubPlaceImpl[3];

			array[0] = getByPlaceId_PrevAndNext(
				session, subPlace, placeId, orderByComparator, true);

			array[1] = subPlace;

			array[2] = getByPlaceId_PrevAndNext(
				session, subPlace, placeId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SubPlace getByPlaceId_PrevAndNext(
		Session session, SubPlace subPlace, long placeId,
		OrderByComparator<SubPlace> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SUBPLACE_WHERE);

		sb.append(_FINDER_COLUMN_PLACEID_PLACEID_2);

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
			sb.append(SubPlaceModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(placeId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(subPlace)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SubPlace> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sub places where placeId = &#63; from the database.
	 *
	 * @param placeId the place ID
	 */
	@Override
	public void removeByPlaceId(long placeId) {
		for (SubPlace subPlace :
				findByPlaceId(
					placeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(subPlace);
		}
	}

	/**
	 * Returns the number of sub places where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @return the number of matching sub places
	 */
	@Override
	public int countByPlaceId(long placeId) {
		FinderPath finderPath = _finderPathCountByPlaceId;

		Object[] finderArgs = new Object[] {placeId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUBPLACE_WHERE);

			sb.append(_FINDER_COLUMN_PLACEID_PLACEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(placeId);

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

	private static final String _FINDER_COLUMN_PLACEID_PLACEID_2 =
		"subPlace.placeId = ?";

	public SubPlacePersistenceImpl() {
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

		setModelClass(SubPlace.class);
	}

	/**
	 * Caches the sub place in the entity cache if it is enabled.
	 *
	 * @param subPlace the sub place
	 */
	@Override
	public void cacheResult(SubPlace subPlace) {
		entityCache.putResult(
			SubPlaceModelImpl.ENTITY_CACHE_ENABLED, SubPlaceImpl.class,
			subPlace.getPrimaryKey(), subPlace);

		subPlace.resetOriginalValues();
	}

	/**
	 * Caches the sub places in the entity cache if it is enabled.
	 *
	 * @param subPlaces the sub places
	 */
	@Override
	public void cacheResult(List<SubPlace> subPlaces) {
		for (SubPlace subPlace : subPlaces) {
			if (entityCache.getResult(
					SubPlaceModelImpl.ENTITY_CACHE_ENABLED, SubPlaceImpl.class,
					subPlace.getPrimaryKey()) == null) {

				cacheResult(subPlace);
			}
			else {
				subPlace.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sub places.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SubPlaceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sub place.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SubPlace subPlace) {
		entityCache.removeResult(
			SubPlaceModelImpl.ENTITY_CACHE_ENABLED, SubPlaceImpl.class,
			subPlace.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SubPlace> subPlaces) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SubPlace subPlace : subPlaces) {
			entityCache.removeResult(
				SubPlaceModelImpl.ENTITY_CACHE_ENABLED, SubPlaceImpl.class,
				subPlace.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				SubPlaceModelImpl.ENTITY_CACHE_ENABLED, SubPlaceImpl.class,
				primaryKey);
		}
	}

	/**
	 * Creates a new sub place with the primary key. Does not add the sub place to the database.
	 *
	 * @param subPlaceId the primary key for the new sub place
	 * @return the new sub place
	 */
	@Override
	public SubPlace create(long subPlaceId) {
		SubPlace subPlace = new SubPlaceImpl();

		subPlace.setNew(true);
		subPlace.setPrimaryKey(subPlaceId);

		String uuid = PortalUUIDUtil.generate();

		subPlace.setUuid(uuid);

		return subPlace;
	}

	/**
	 * Removes the sub place with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param subPlaceId the primary key of the sub place
	 * @return the sub place that was removed
	 * @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	 */
	@Override
	public SubPlace remove(long subPlaceId) throws NoSuchSubPlaceException {
		return remove((Serializable)subPlaceId);
	}

	/**
	 * Removes the sub place with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sub place
	 * @return the sub place that was removed
	 * @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	 */
	@Override
	public SubPlace remove(Serializable primaryKey)
		throws NoSuchSubPlaceException {

		Session session = null;

		try {
			session = openSession();

			SubPlace subPlace = (SubPlace)session.get(
				SubPlaceImpl.class, primaryKey);

			if (subPlace == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSubPlaceException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(subPlace);
		}
		catch (NoSuchSubPlaceException noSuchEntityException) {
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
	protected SubPlace removeImpl(SubPlace subPlace) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(subPlace)) {
				subPlace = (SubPlace)session.get(
					SubPlaceImpl.class, subPlace.getPrimaryKeyObj());
			}

			if (subPlace != null) {
				session.delete(subPlace);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (subPlace != null) {
			clearCache(subPlace);
		}

		return subPlace;
	}

	@Override
	public SubPlace updateImpl(SubPlace subPlace) {
		boolean isNew = subPlace.isNew();

		if (!(subPlace instanceof SubPlaceModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(subPlace.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(subPlace);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in subPlace proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SubPlace implementation " +
					subPlace.getClass());
		}

		SubPlaceModelImpl subPlaceModelImpl = (SubPlaceModelImpl)subPlace;

		if (Validator.isNull(subPlace.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			subPlace.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (subPlace.isNew()) {
				session.save(subPlace);

				subPlace.setNew(false);
			}
			else {
				subPlace = (SubPlace)session.merge(subPlace);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SubPlaceModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {subPlaceModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {subPlaceModelImpl.getPlaceId()};

			finderCache.removeResult(_finderPathCountByPlaceId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPlaceId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((subPlaceModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					subPlaceModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {subPlaceModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((subPlaceModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPlaceId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					subPlaceModelImpl.getOriginalPlaceId()
				};

				finderCache.removeResult(_finderPathCountByPlaceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPlaceId, args);

				args = new Object[] {subPlaceModelImpl.getPlaceId()};

				finderCache.removeResult(_finderPathCountByPlaceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPlaceId, args);
			}
		}

		entityCache.putResult(
			SubPlaceModelImpl.ENTITY_CACHE_ENABLED, SubPlaceImpl.class,
			subPlace.getPrimaryKey(), subPlace, false);

		subPlace.resetOriginalValues();

		return subPlace;
	}

	/**
	 * Returns the sub place with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sub place
	 * @return the sub place
	 * @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	 */
	@Override
	public SubPlace findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSubPlaceException {

		SubPlace subPlace = fetchByPrimaryKey(primaryKey);

		if (subPlace == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSubPlaceException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return subPlace;
	}

	/**
	 * Returns the sub place with the primary key or throws a <code>NoSuchSubPlaceException</code> if it could not be found.
	 *
	 * @param subPlaceId the primary key of the sub place
	 * @return the sub place
	 * @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	 */
	@Override
	public SubPlace findByPrimaryKey(long subPlaceId)
		throws NoSuchSubPlaceException {

		return findByPrimaryKey((Serializable)subPlaceId);
	}

	/**
	 * Returns the sub place with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sub place
	 * @return the sub place, or <code>null</code> if a sub place with the primary key could not be found
	 */
	@Override
	public SubPlace fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			SubPlaceModelImpl.ENTITY_CACHE_ENABLED, SubPlaceImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SubPlace subPlace = (SubPlace)serializable;

		if (subPlace == null) {
			Session session = null;

			try {
				session = openSession();

				subPlace = (SubPlace)session.get(
					SubPlaceImpl.class, primaryKey);

				if (subPlace != null) {
					cacheResult(subPlace);
				}
				else {
					entityCache.putResult(
						SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
						SubPlaceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					SubPlaceModelImpl.ENTITY_CACHE_ENABLED, SubPlaceImpl.class,
					primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return subPlace;
	}

	/**
	 * Returns the sub place with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param subPlaceId the primary key of the sub place
	 * @return the sub place, or <code>null</code> if a sub place with the primary key could not be found
	 */
	@Override
	public SubPlace fetchByPrimaryKey(long subPlaceId) {
		return fetchByPrimaryKey((Serializable)subPlaceId);
	}

	@Override
	public Map<Serializable, SubPlace> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SubPlace> map = new HashMap<Serializable, SubPlace>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SubPlace subPlace = fetchByPrimaryKey(primaryKey);

			if (subPlace != null) {
				map.put(primaryKey, subPlace);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				SubPlaceModelImpl.ENTITY_CACHE_ENABLED, SubPlaceImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SubPlace)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_SUBPLACE_WHERE_PKS_IN);

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

			for (SubPlace subPlace : (List<SubPlace>)query.list()) {
				map.put(subPlace.getPrimaryKeyObj(), subPlace);

				cacheResult(subPlace);

				uncachedPrimaryKeys.remove(subPlace.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					SubPlaceModelImpl.ENTITY_CACHE_ENABLED, SubPlaceImpl.class,
					primaryKey, nullModel);
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
	 * Returns all the sub places.
	 *
	 * @return the sub places
	 */
	@Override
	public List<SubPlace> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sub places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @return the range of sub places
	 */
	@Override
	public List<SubPlace> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sub places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sub places
	 */
	@Override
	public List<SubPlace> findAll(
		int start, int end, OrderByComparator<SubPlace> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sub places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sub places
	 */
	@Override
	public List<SubPlace> findAll(
		int start, int end, OrderByComparator<SubPlace> orderByComparator,
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

		List<SubPlace> list = null;

		if (useFinderCache) {
			list = (List<SubPlace>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SUBPLACE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SUBPLACE;

				sql = sql.concat(SubPlaceModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SubPlace>)QueryUtil.list(
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
	 * Removes all the sub places from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SubPlace subPlace : findAll()) {
			remove(subPlace);
		}
	}

	/**
	 * Returns the number of sub places.
	 *
	 * @return the number of sub places
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SUBPLACE);

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
		return SubPlaceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sub place persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, SubPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, SubPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, SubPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, SubPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			SubPlaceModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByPlaceId = new FinderPath(
			SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, SubPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPlaceId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPlaceId = new FinderPath(
			SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, SubPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlaceId",
			new String[] {Long.class.getName()},
			SubPlaceModelImpl.PLACEID_COLUMN_BITMASK);

		_finderPathCountByPlaceId = new FinderPath(
			SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlaceId",
			new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(SubPlaceImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SUBPLACE =
		"SELECT subPlace FROM SubPlace subPlace";

	private static final String _SQL_SELECT_SUBPLACE_WHERE_PKS_IN =
		"SELECT subPlace FROM SubPlace subPlace WHERE subPlaceId IN (";

	private static final String _SQL_SELECT_SUBPLACE_WHERE =
		"SELECT subPlace FROM SubPlace subPlace WHERE ";

	private static final String _SQL_COUNT_SUBPLACE =
		"SELECT COUNT(subPlace) FROM SubPlace subPlace";

	private static final String _SQL_COUNT_SUBPLACE_WHERE =
		"SELECT COUNT(subPlace) FROM SubPlace subPlace WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "subPlace.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SubPlace exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SubPlace exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SubPlacePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}