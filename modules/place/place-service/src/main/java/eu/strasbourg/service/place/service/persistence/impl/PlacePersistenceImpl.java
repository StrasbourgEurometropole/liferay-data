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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.place.exception.NoSuchPlaceException;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.impl.PlaceImpl;
import eu.strasbourg.service.place.model.impl.PlaceModelImpl;
import eu.strasbourg.service.place.service.persistence.PlacePersistence;

import java.io.Serializable;

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
 * The persistence implementation for the place service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see PlacePersistence
 * @see eu.strasbourg.service.place.service.persistence.PlaceUtil
 * @generated
 */
@ProviderType
public class PlacePersistenceImpl extends BasePersistenceImpl<Place>
	implements PlacePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PlaceUtil} to access the place persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PlaceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, PlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, PlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, PlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, PlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PlaceModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the places where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching places
	 */
	@Override
	public List<Place> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @return the range of matching places
	 */
	@Override
	public List<Place> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching places
	 */
	@Override
	public List<Place> findByUuid(String uuid, int start, int end,
		OrderByComparator<Place> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching places
	 */
	@Override
	public List<Place> findByUuid(String uuid, int start, int end,
		OrderByComparator<Place> orderByComparator, boolean retrieveFromCache) {
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

		List<Place> list = null;

		if (retrieveFromCache) {
			list = (List<Place>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Place place : list) {
					if (!Objects.equals(uuid, place.getUuid())) {
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

			query.append(_SQL_SELECT_PLACE_WHERE);

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
				query.append(PlaceModelImpl.ORDER_BY_JPQL);
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
					list = (List<Place>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Place>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching place
	 * @throws NoSuchPlaceException if a matching place could not be found
	 */
	@Override
	public Place findByUuid_First(String uuid,
		OrderByComparator<Place> orderByComparator) throws NoSuchPlaceException {
		Place place = fetchByUuid_First(uuid, orderByComparator);

		if (place != null) {
			return place;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlaceException(msg.toString());
	}

	/**
	 * Returns the first place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching place, or <code>null</code> if a matching place could not be found
	 */
	@Override
	public Place fetchByUuid_First(String uuid,
		OrderByComparator<Place> orderByComparator) {
		List<Place> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching place
	 * @throws NoSuchPlaceException if a matching place could not be found
	 */
	@Override
	public Place findByUuid_Last(String uuid,
		OrderByComparator<Place> orderByComparator) throws NoSuchPlaceException {
		Place place = fetchByUuid_Last(uuid, orderByComparator);

		if (place != null) {
			return place;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlaceException(msg.toString());
	}

	/**
	 * Returns the last place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching place, or <code>null</code> if a matching place could not be found
	 */
	@Override
	public Place fetchByUuid_Last(String uuid,
		OrderByComparator<Place> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Place> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the places before and after the current place in the ordered set where uuid = &#63;.
	 *
	 * @param placeId the primary key of the current place
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next place
	 * @throws NoSuchPlaceException if a place with the primary key could not be found
	 */
	@Override
	public Place[] findByUuid_PrevAndNext(long placeId, String uuid,
		OrderByComparator<Place> orderByComparator) throws NoSuchPlaceException {
		Place place = findByPrimaryKey(placeId);

		Session session = null;

		try {
			session = openSession();

			Place[] array = new PlaceImpl[3];

			array[0] = getByUuid_PrevAndNext(session, place, uuid,
					orderByComparator, true);

			array[1] = place;

			array[2] = getByUuid_PrevAndNext(session, place, uuid,
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

	protected Place getByUuid_PrevAndNext(Session session, Place place,
		String uuid, OrderByComparator<Place> orderByComparator,
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

		query.append(_SQL_SELECT_PLACE_WHERE);

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
			query.append(PlaceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(place);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Place> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the places where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Place place : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(place);
		}
	}

	/**
	 * Returns the number of places where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching places
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PLACE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "place.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "place.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(place.uuid IS NULL OR place.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, PlaceImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PlaceModelImpl.UUID_COLUMN_BITMASK |
			PlaceModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the place where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPlaceException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching place
	 * @throws NoSuchPlaceException if a matching place could not be found
	 */
	@Override
	public Place findByUUID_G(String uuid, long groupId)
		throws NoSuchPlaceException {
		Place place = fetchByUUID_G(uuid, groupId);

		if (place == null) {
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

			throw new NoSuchPlaceException(msg.toString());
		}

		return place;
	}

	/**
	 * Returns the place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching place, or <code>null</code> if a matching place could not be found
	 */
	@Override
	public Place fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching place, or <code>null</code> if a matching place could not be found
	 */
	@Override
	public Place fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Place) {
			Place place = (Place)result;

			if (!Objects.equals(uuid, place.getUuid()) ||
					(groupId != place.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PLACE_WHERE);

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

				List<Place> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Place place = list.get(0);

					result = place;

					cacheResult(place);

					if ((place.getUuid() == null) ||
							!place.getUuid().equals(uuid) ||
							(place.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, place);
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
			return (Place)result;
		}
	}

	/**
	 * Removes the place where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the place that was removed
	 */
	@Override
	public Place removeByUUID_G(String uuid, long groupId)
		throws NoSuchPlaceException {
		Place place = findByUUID_G(uuid, groupId);

		return remove(place);
	}

	/**
	 * Returns the number of places where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching places
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PLACE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "place.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "place.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(place.uuid IS NULL OR place.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "place.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, PlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, PlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PlaceModelImpl.UUID_COLUMN_BITMASK |
			PlaceModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the places where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching places
	 */
	@Override
	public List<Place> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the places where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @return the range of matching places
	 */
	@Override
	public List<Place> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the places where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching places
	 */
	@Override
	public List<Place> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Place> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the places where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching places
	 */
	@Override
	public List<Place> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Place> orderByComparator,
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

		List<Place> list = null;

		if (retrieveFromCache) {
			list = (List<Place>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Place place : list) {
					if (!Objects.equals(uuid, place.getUuid()) ||
							(companyId != place.getCompanyId())) {
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

			query.append(_SQL_SELECT_PLACE_WHERE);

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
				query.append(PlaceModelImpl.ORDER_BY_JPQL);
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
					list = (List<Place>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Place>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching place
	 * @throws NoSuchPlaceException if a matching place could not be found
	 */
	@Override
	public Place findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Place> orderByComparator) throws NoSuchPlaceException {
		Place place = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (place != null) {
			return place;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlaceException(msg.toString());
	}

	/**
	 * Returns the first place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching place, or <code>null</code> if a matching place could not be found
	 */
	@Override
	public Place fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Place> orderByComparator) {
		List<Place> list = findByUuid_C(uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching place
	 * @throws NoSuchPlaceException if a matching place could not be found
	 */
	@Override
	public Place findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Place> orderByComparator) throws NoSuchPlaceException {
		Place place = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (place != null) {
			return place;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlaceException(msg.toString());
	}

	/**
	 * Returns the last place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching place, or <code>null</code> if a matching place could not be found
	 */
	@Override
	public Place fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Place> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Place> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the places before and after the current place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param placeId the primary key of the current place
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next place
	 * @throws NoSuchPlaceException if a place with the primary key could not be found
	 */
	@Override
	public Place[] findByUuid_C_PrevAndNext(long placeId, String uuid,
		long companyId, OrderByComparator<Place> orderByComparator)
		throws NoSuchPlaceException {
		Place place = findByPrimaryKey(placeId);

		Session session = null;

		try {
			session = openSession();

			Place[] array = new PlaceImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, place, uuid, companyId,
					orderByComparator, true);

			array[1] = place;

			array[2] = getByUuid_C_PrevAndNext(session, place, uuid, companyId,
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

	protected Place getByUuid_C_PrevAndNext(Session session, Place place,
		String uuid, long companyId,
		OrderByComparator<Place> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PLACE_WHERE);

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
			query.append(PlaceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(place);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Place> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the places where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Place place : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(place);
		}
	}

	/**
	 * Returns the number of places where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching places
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PLACE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "place.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "place.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(place.uuid IS NULL OR place.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "place.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, PlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, PlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			PlaceModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the places where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching places
	 */
	@Override
	public List<Place> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the places where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @return the range of matching places
	 */
	@Override
	public List<Place> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the places where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching places
	 */
	@Override
	public List<Place> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Place> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the places where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching places
	 */
	@Override
	public List<Place> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Place> orderByComparator, boolean retrieveFromCache) {
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

		List<Place> list = null;

		if (retrieveFromCache) {
			list = (List<Place>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Place place : list) {
					if ((groupId != place.getGroupId())) {
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

			query.append(_SQL_SELECT_PLACE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PlaceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Place>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Place>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching place
	 * @throws NoSuchPlaceException if a matching place could not be found
	 */
	@Override
	public Place findByGroupId_First(long groupId,
		OrderByComparator<Place> orderByComparator) throws NoSuchPlaceException {
		Place place = fetchByGroupId_First(groupId, orderByComparator);

		if (place != null) {
			return place;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlaceException(msg.toString());
	}

	/**
	 * Returns the first place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching place, or <code>null</code> if a matching place could not be found
	 */
	@Override
	public Place fetchByGroupId_First(long groupId,
		OrderByComparator<Place> orderByComparator) {
		List<Place> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching place
	 * @throws NoSuchPlaceException if a matching place could not be found
	 */
	@Override
	public Place findByGroupId_Last(long groupId,
		OrderByComparator<Place> orderByComparator) throws NoSuchPlaceException {
		Place place = fetchByGroupId_Last(groupId, orderByComparator);

		if (place != null) {
			return place;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlaceException(msg.toString());
	}

	/**
	 * Returns the last place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching place, or <code>null</code> if a matching place could not be found
	 */
	@Override
	public Place fetchByGroupId_Last(long groupId,
		OrderByComparator<Place> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Place> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the places before and after the current place in the ordered set where groupId = &#63;.
	 *
	 * @param placeId the primary key of the current place
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next place
	 * @throws NoSuchPlaceException if a place with the primary key could not be found
	 */
	@Override
	public Place[] findByGroupId_PrevAndNext(long placeId, long groupId,
		OrderByComparator<Place> orderByComparator) throws NoSuchPlaceException {
		Place place = findByPrimaryKey(placeId);

		Session session = null;

		try {
			session = openSession();

			Place[] array = new PlaceImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, place, groupId,
					orderByComparator, true);

			array[1] = place;

			array[2] = getByGroupId_PrevAndNext(session, place, groupId,
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

	protected Place getByGroupId_PrevAndNext(Session session, Place place,
		long groupId, OrderByComparator<Place> orderByComparator,
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

		query.append(_SQL_SELECT_PLACE_WHERE);

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
			query.append(PlaceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(place);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Place> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the places where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (Place place : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(place);
		}
	}

	/**
	 * Returns the number of places where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching places
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PLACE_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "place.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICEID = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, PlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPriceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICEID =
		new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, PlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPriceId",
			new String[] { Long.class.getName() },
			PlaceModelImpl.PRICEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRICEID = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPriceId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the places where priceId = &#63;.
	 *
	 * @param priceId the price ID
	 * @return the matching places
	 */
	@Override
	public List<Place> findByPriceId(long priceId) {
		return findByPriceId(priceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the places where priceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceId the price ID
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @return the range of matching places
	 */
	@Override
	public List<Place> findByPriceId(long priceId, int start, int end) {
		return findByPriceId(priceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the places where priceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceId the price ID
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching places
	 */
	@Override
	public List<Place> findByPriceId(long priceId, int start, int end,
		OrderByComparator<Place> orderByComparator) {
		return findByPriceId(priceId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the places where priceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceId the price ID
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching places
	 */
	@Override
	public List<Place> findByPriceId(long priceId, int start, int end,
		OrderByComparator<Place> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICEID;
			finderArgs = new Object[] { priceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICEID;
			finderArgs = new Object[] { priceId, start, end, orderByComparator };
		}

		List<Place> list = null;

		if (retrieveFromCache) {
			list = (List<Place>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Place place : list) {
					if ((priceId != place.getPriceId())) {
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

			query.append(_SQL_SELECT_PLACE_WHERE);

			query.append(_FINDER_COLUMN_PRICEID_PRICEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PlaceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(priceId);

				if (!pagination) {
					list = (List<Place>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Place>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first place in the ordered set where priceId = &#63;.
	 *
	 * @param priceId the price ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching place
	 * @throws NoSuchPlaceException if a matching place could not be found
	 */
	@Override
	public Place findByPriceId_First(long priceId,
		OrderByComparator<Place> orderByComparator) throws NoSuchPlaceException {
		Place place = fetchByPriceId_First(priceId, orderByComparator);

		if (place != null) {
			return place;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceId=");
		msg.append(priceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlaceException(msg.toString());
	}

	/**
	 * Returns the first place in the ordered set where priceId = &#63;.
	 *
	 * @param priceId the price ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching place, or <code>null</code> if a matching place could not be found
	 */
	@Override
	public Place fetchByPriceId_First(long priceId,
		OrderByComparator<Place> orderByComparator) {
		List<Place> list = findByPriceId(priceId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last place in the ordered set where priceId = &#63;.
	 *
	 * @param priceId the price ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching place
	 * @throws NoSuchPlaceException if a matching place could not be found
	 */
	@Override
	public Place findByPriceId_Last(long priceId,
		OrderByComparator<Place> orderByComparator) throws NoSuchPlaceException {
		Place place = fetchByPriceId_Last(priceId, orderByComparator);

		if (place != null) {
			return place;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceId=");
		msg.append(priceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlaceException(msg.toString());
	}

	/**
	 * Returns the last place in the ordered set where priceId = &#63;.
	 *
	 * @param priceId the price ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching place, or <code>null</code> if a matching place could not be found
	 */
	@Override
	public Place fetchByPriceId_Last(long priceId,
		OrderByComparator<Place> orderByComparator) {
		int count = countByPriceId(priceId);

		if (count == 0) {
			return null;
		}

		List<Place> list = findByPriceId(priceId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the places before and after the current place in the ordered set where priceId = &#63;.
	 *
	 * @param placeId the primary key of the current place
	 * @param priceId the price ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next place
	 * @throws NoSuchPlaceException if a place with the primary key could not be found
	 */
	@Override
	public Place[] findByPriceId_PrevAndNext(long placeId, long priceId,
		OrderByComparator<Place> orderByComparator) throws NoSuchPlaceException {
		Place place = findByPrimaryKey(placeId);

		Session session = null;

		try {
			session = openSession();

			Place[] array = new PlaceImpl[3];

			array[0] = getByPriceId_PrevAndNext(session, place, priceId,
					orderByComparator, true);

			array[1] = place;

			array[2] = getByPriceId_PrevAndNext(session, place, priceId,
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

	protected Place getByPriceId_PrevAndNext(Session session, Place place,
		long priceId, OrderByComparator<Place> orderByComparator,
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

		query.append(_SQL_SELECT_PLACE_WHERE);

		query.append(_FINDER_COLUMN_PRICEID_PRICEID_2);

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
			query.append(PlaceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(priceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(place);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Place> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the places where priceId = &#63; from the database.
	 *
	 * @param priceId the price ID
	 */
	@Override
	public void removeByPriceId(long priceId) {
		for (Place place : findByPriceId(priceId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(place);
		}
	}

	/**
	 * Returns the number of places where priceId = &#63;.
	 *
	 * @param priceId the price ID
	 * @return the number of matching places
	 */
	@Override
	public int countByPriceId(long priceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PRICEID;

		Object[] finderArgs = new Object[] { priceId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PLACE_WHERE);

			query.append(_FINDER_COLUMN_PRICEID_PRICEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(priceId);

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

	private static final String _FINDER_COLUMN_PRICEID_PRICEID_2 = "place.priceId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_SIGID = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, PlaceImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchBySIGId",
			new String[] { String.class.getName() },
			PlaceModelImpl.SIGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIGID = new FinderPath(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySIGId",
			new String[] { String.class.getName() });

	/**
	 * Returns the place where SIGid = &#63; or throws a {@link NoSuchPlaceException} if it could not be found.
	 *
	 * @param SIGid the s i gid
	 * @return the matching place
	 * @throws NoSuchPlaceException if a matching place could not be found
	 */
	@Override
	public Place findBySIGId(String SIGid) throws NoSuchPlaceException {
		Place place = fetchBySIGId(SIGid);

		if (place == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("SIGid=");
			msg.append(SIGid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPlaceException(msg.toString());
		}

		return place;
	}

	/**
	 * Returns the place where SIGid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param SIGid the s i gid
	 * @return the matching place, or <code>null</code> if a matching place could not be found
	 */
	@Override
	public Place fetchBySIGId(String SIGid) {
		return fetchBySIGId(SIGid, true);
	}

	/**
	 * Returns the place where SIGid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param SIGid the s i gid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching place, or <code>null</code> if a matching place could not be found
	 */
	@Override
	public Place fetchBySIGId(String SIGid, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { SIGid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SIGID,
					finderArgs, this);
		}

		if (result instanceof Place) {
			Place place = (Place)result;

			if (!Objects.equals(SIGid, place.getSIGid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PLACE_WHERE);

			boolean bindSIGid = false;

			if (SIGid == null) {
				query.append(_FINDER_COLUMN_SIGID_SIGID_1);
			}
			else if (SIGid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIGID_SIGID_3);
			}
			else {
				bindSIGid = true;

				query.append(_FINDER_COLUMN_SIGID_SIGID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSIGid) {
					qPos.add(SIGid);
				}

				List<Place> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SIGID,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"PlacePersistenceImpl.fetchBySIGId(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Place place = list.get(0);

					result = place;

					cacheResult(place);

					if ((place.getSIGid() == null) ||
							!place.getSIGid().equals(SIGid)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_SIGID,
							finderArgs, place);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SIGID, finderArgs);

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
			return (Place)result;
		}
	}

	/**
	 * Removes the place where SIGid = &#63; from the database.
	 *
	 * @param SIGid the s i gid
	 * @return the place that was removed
	 */
	@Override
	public Place removeBySIGId(String SIGid) throws NoSuchPlaceException {
		Place place = findBySIGId(SIGid);

		return remove(place);
	}

	/**
	 * Returns the number of places where SIGid = &#63;.
	 *
	 * @param SIGid the s i gid
	 * @return the number of matching places
	 */
	@Override
	public int countBySIGId(String SIGid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIGID;

		Object[] finderArgs = new Object[] { SIGid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PLACE_WHERE);

			boolean bindSIGid = false;

			if (SIGid == null) {
				query.append(_FINDER_COLUMN_SIGID_SIGID_1);
			}
			else if (SIGid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIGID_SIGID_3);
			}
			else {
				bindSIGid = true;

				query.append(_FINDER_COLUMN_SIGID_SIGID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSIGid) {
					qPos.add(SIGid);
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

	private static final String _FINDER_COLUMN_SIGID_SIGID_1 = "place.SIGid IS NULL";
	private static final String _FINDER_COLUMN_SIGID_SIGID_2 = "place.SIGid = ?";
	private static final String _FINDER_COLUMN_SIGID_SIGID_3 = "(place.SIGid IS NULL OR place.SIGid = '')";

	public PlacePersistenceImpl() {
		setModelClass(Place.class);
	}

	/**
	 * Caches the place in the entity cache if it is enabled.
	 *
	 * @param place the place
	 */
	@Override
	public void cacheResult(Place place) {
		entityCache.putResult(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceImpl.class, place.getPrimaryKey(), place);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { place.getUuid(), place.getGroupId() }, place);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SIGID,
			new Object[] { place.getSIGid() }, place);

		place.resetOriginalValues();
	}

	/**
	 * Caches the places in the entity cache if it is enabled.
	 *
	 * @param places the places
	 */
	@Override
	public void cacheResult(List<Place> places) {
		for (Place place : places) {
			if (entityCache.getResult(PlaceModelImpl.ENTITY_CACHE_ENABLED,
						PlaceImpl.class, place.getPrimaryKey()) == null) {
				cacheResult(place);
			}
			else {
				place.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all places.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PlaceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the place.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Place place) {
		entityCache.removeResult(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceImpl.class, place.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PlaceModelImpl)place);
	}

	@Override
	public void clearCache(List<Place> places) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Place place : places) {
			entityCache.removeResult(PlaceModelImpl.ENTITY_CACHE_ENABLED,
				PlaceImpl.class, place.getPrimaryKey());

			clearUniqueFindersCache((PlaceModelImpl)place);
		}
	}

	protected void cacheUniqueFindersCache(PlaceModelImpl placeModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					placeModelImpl.getUuid(), placeModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				placeModelImpl);

			args = new Object[] { placeModelImpl.getSIGid() };

			finderCache.putResult(FINDER_PATH_COUNT_BY_SIGID, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_SIGID, args,
				placeModelImpl);
		}
		else {
			if ((placeModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						placeModelImpl.getUuid(), placeModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					placeModelImpl);
			}

			if ((placeModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SIGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { placeModelImpl.getSIGid() };

				finderCache.putResult(FINDER_PATH_COUNT_BY_SIGID, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_SIGID, args,
					placeModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(PlaceModelImpl placeModelImpl) {
		Object[] args = new Object[] {
				placeModelImpl.getUuid(), placeModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((placeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					placeModelImpl.getOriginalUuid(),
					placeModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] { placeModelImpl.getSIGid() };

		finderCache.removeResult(FINDER_PATH_COUNT_BY_SIGID, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_SIGID, args);

		if ((placeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SIGID.getColumnBitmask()) != 0) {
			args = new Object[] { placeModelImpl.getOriginalSIGid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SIGID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SIGID, args);
		}
	}

	/**
	 * Creates a new place with the primary key. Does not add the place to the database.
	 *
	 * @param placeId the primary key for the new place
	 * @return the new place
	 */
	@Override
	public Place create(long placeId) {
		Place place = new PlaceImpl();

		place.setNew(true);
		place.setPrimaryKey(placeId);

		String uuid = PortalUUIDUtil.generate();

		place.setUuid(uuid);

		place.setCompanyId(companyProvider.getCompanyId());

		return place;
	}

	/**
	 * Removes the place with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param placeId the primary key of the place
	 * @return the place that was removed
	 * @throws NoSuchPlaceException if a place with the primary key could not be found
	 */
	@Override
	public Place remove(long placeId) throws NoSuchPlaceException {
		return remove((Serializable)placeId);
	}

	/**
	 * Removes the place with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the place
	 * @return the place that was removed
	 * @throws NoSuchPlaceException if a place with the primary key could not be found
	 */
	@Override
	public Place remove(Serializable primaryKey) throws NoSuchPlaceException {
		Session session = null;

		try {
			session = openSession();

			Place place = (Place)session.get(PlaceImpl.class, primaryKey);

			if (place == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPlaceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(place);
		}
		catch (NoSuchPlaceException nsee) {
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
	protected Place removeImpl(Place place) {
		place = toUnwrappedModel(place);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(place)) {
				place = (Place)session.get(PlaceImpl.class,
						place.getPrimaryKeyObj());
			}

			if (place != null) {
				session.delete(place);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (place != null) {
			clearCache(place);
		}

		return place;
	}

	@Override
	public Place updateImpl(Place place) {
		place = toUnwrappedModel(place);

		boolean isNew = place.isNew();

		PlaceModelImpl placeModelImpl = (PlaceModelImpl)place;

		if (Validator.isNull(place.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			place.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (place.getCreateDate() == null)) {
			if (serviceContext == null) {
				place.setCreateDate(now);
			}
			else {
				place.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!placeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				place.setModifiedDate(now);
			}
			else {
				place.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (place.isNew()) {
				session.save(place);

				place.setNew(false);
			}
			else {
				place = (Place)session.merge(place);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PlaceModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((placeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { placeModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { placeModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((placeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						placeModelImpl.getOriginalUuid(),
						placeModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						placeModelImpl.getUuid(), placeModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((placeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { placeModelImpl.getOriginalGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { placeModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((placeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { placeModelImpl.getOriginalPriceId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICEID,
					args);

				args = new Object[] { placeModelImpl.getPriceId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICEID,
					args);
			}
		}

		entityCache.putResult(PlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlaceImpl.class, place.getPrimaryKey(), place, false);

		clearUniqueFindersCache(placeModelImpl);
		cacheUniqueFindersCache(placeModelImpl, isNew);

		place.resetOriginalValues();

		return place;
	}

	protected Place toUnwrappedModel(Place place) {
		if (place instanceof PlaceImpl) {
			return place;
		}

		PlaceImpl placeImpl = new PlaceImpl();

		placeImpl.setNew(place.isNew());
		placeImpl.setPrimaryKey(place.getPrimaryKey());

		placeImpl.setUuid(place.getUuid());
		placeImpl.setPlaceId(place.getPlaceId());
		placeImpl.setGroupId(place.getGroupId());
		placeImpl.setCompanyId(place.getCompanyId());
		placeImpl.setUserId(place.getUserId());
		placeImpl.setUserName(place.getUserName());
		placeImpl.setCreateDate(place.getCreateDate());
		placeImpl.setModifiedDate(place.getModifiedDate());
		placeImpl.setLastPublishDate(place.getLastPublishDate());
		placeImpl.setStatus(place.getStatus());
		placeImpl.setStatusByUserId(place.getStatusByUserId());
		placeImpl.setStatusByUserName(place.getStatusByUserName());
		placeImpl.setStatusDate(place.getStatusDate());
		placeImpl.setSIGid(place.getSIGid());
		placeImpl.setName(place.getName());
		placeImpl.setAddressComplement(place.getAddressComplement());
		placeImpl.setAddressStreet(place.getAddressStreet());
		placeImpl.setAddressDistribution(place.getAddressDistribution());
		placeImpl.setAddressZipCode(place.getAddressZipCode());
		placeImpl.setAddressCountry(place.getAddressCountry());
		placeImpl.setMercatorX(place.getMercatorX());
		placeImpl.setMercatorY(place.getMercatorY());
		placeImpl.setRGF93X(place.getRGF93X());
		placeImpl.setRGF93Y(place.getRGF93Y());
		placeImpl.setAlias(place.getAlias());
		placeImpl.setPresentation(place.getPresentation());
		placeImpl.setServiceAndActivities(place.getServiceAndActivities());
		placeImpl.setCharacteristics(place.getCharacteristics());
		placeImpl.setSubjectToPublicHoliday(place.isSubjectToPublicHoliday());
		placeImpl.setExceptionalSchedule(place.getExceptionalSchedule());
		placeImpl.setDisplayEvents(place.isDisplayEvents());
		placeImpl.setAdditionalInformation(place.getAdditionalInformation());
		placeImpl.setPhone(place.getPhone());
		placeImpl.setMail(place.getMail());
		placeImpl.setSiteURL(place.getSiteURL());
		placeImpl.setSiteLabel(place.getSiteLabel());
		placeImpl.setFacebookURL(place.getFacebookURL());
		placeImpl.setFacebookLabel(place.getFacebookLabel());
		placeImpl.setAccesMap(place.getAccesMap());
		placeImpl.setAccess(place.getAccess());
		placeImpl.setAccessForDisabled(place.getAccessForDisabled());
		placeImpl.setAccessForBlind(place.getAccessForBlind());
		placeImpl.setAccessForDeaf(place.getAccessForDeaf());
		placeImpl.setAccessForWheelchair(place.getAccessForWheelchair());
		placeImpl.setAccessForElder(place.getAccessForElder());
		placeImpl.setAccessForDeficient(place.getAccessForDeficient());
		placeImpl.setRTEnabled(place.isRTEnabled());
		placeImpl.setRTExternalId(place.getRTExternalId());
		placeImpl.setRTGreenThreshold(place.getRTGreenThreshold());
		placeImpl.setRTOrangeThreshold(place.getRTOrangeThreshold());
		placeImpl.setRTRedThreshold(place.getRTRedThreshold());
		placeImpl.setRTMaxThreshold(place.getRTMaxThreshold());
		placeImpl.setImageId(place.getImageId());
		placeImpl.setImageIds(place.getImageIds());
		placeImpl.setVideosIds(place.getVideosIds());
		placeImpl.setPriceId(place.getPriceId());
		placeImpl.setDocumentsIds(place.getDocumentsIds());

		return placeImpl;
	}

	/**
	 * Returns the place with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the place
	 * @return the place
	 * @throws NoSuchPlaceException if a place with the primary key could not be found
	 */
	@Override
	public Place findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPlaceException {
		Place place = fetchByPrimaryKey(primaryKey);

		if (place == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPlaceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return place;
	}

	/**
	 * Returns the place with the primary key or throws a {@link NoSuchPlaceException} if it could not be found.
	 *
	 * @param placeId the primary key of the place
	 * @return the place
	 * @throws NoSuchPlaceException if a place with the primary key could not be found
	 */
	@Override
	public Place findByPrimaryKey(long placeId) throws NoSuchPlaceException {
		return findByPrimaryKey((Serializable)placeId);
	}

	/**
	 * Returns the place with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the place
	 * @return the place, or <code>null</code> if a place with the primary key could not be found
	 */
	@Override
	public Place fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PlaceModelImpl.ENTITY_CACHE_ENABLED,
				PlaceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Place place = (Place)serializable;

		if (place == null) {
			Session session = null;

			try {
				session = openSession();

				place = (Place)session.get(PlaceImpl.class, primaryKey);

				if (place != null) {
					cacheResult(place);
				}
				else {
					entityCache.putResult(PlaceModelImpl.ENTITY_CACHE_ENABLED,
						PlaceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PlaceModelImpl.ENTITY_CACHE_ENABLED,
					PlaceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return place;
	}

	/**
	 * Returns the place with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param placeId the primary key of the place
	 * @return the place, or <code>null</code> if a place with the primary key could not be found
	 */
	@Override
	public Place fetchByPrimaryKey(long placeId) {
		return fetchByPrimaryKey((Serializable)placeId);
	}

	@Override
	public Map<Serializable, Place> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Place> map = new HashMap<Serializable, Place>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Place place = fetchByPrimaryKey(primaryKey);

			if (place != null) {
				map.put(primaryKey, place);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PlaceModelImpl.ENTITY_CACHE_ENABLED,
					PlaceImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Place)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PLACE_WHERE_PKS_IN);

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

			for (Place place : (List<Place>)q.list()) {
				map.put(place.getPrimaryKeyObj(), place);

				cacheResult(place);

				uncachedPrimaryKeys.remove(place.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PlaceModelImpl.ENTITY_CACHE_ENABLED,
					PlaceImpl.class, primaryKey, nullModel);
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
	 * Returns all the places.
	 *
	 * @return the places
	 */
	@Override
	public List<Place> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @return the range of places
	 */
	@Override
	public List<Place> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of places
	 */
	@Override
	public List<Place> findAll(int start, int end,
		OrderByComparator<Place> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of places
	 * @param end the upper bound of the range of places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of places
	 */
	@Override
	public List<Place> findAll(int start, int end,
		OrderByComparator<Place> orderByComparator, boolean retrieveFromCache) {
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

		List<Place> list = null;

		if (retrieveFromCache) {
			list = (List<Place>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PLACE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PLACE;

				if (pagination) {
					sql = sql.concat(PlaceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Place>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Place>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the places from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Place place : findAll()) {
			remove(place);
		}
	}

	/**
	 * Returns the number of places.
	 *
	 * @return the number of places
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PLACE);

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
		return PlaceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the place persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PlaceImpl.class.getName());
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
	private static final String _SQL_SELECT_PLACE = "SELECT place FROM Place place";
	private static final String _SQL_SELECT_PLACE_WHERE_PKS_IN = "SELECT place FROM Place place WHERE placeId IN (";
	private static final String _SQL_SELECT_PLACE_WHERE = "SELECT place FROM Place place WHERE ";
	private static final String _SQL_COUNT_PLACE = "SELECT COUNT(place) FROM Place place";
	private static final String _SQL_COUNT_PLACE_WHERE = "SELECT COUNT(place) FROM Place place WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "place.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Place exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Place exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PlacePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "alias", "access"
			});
}