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

package eu.strasbourg.service.project.service.persistence.impl;

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

import eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.model.impl.PlacitPlaceImpl;
import eu.strasbourg.service.project.model.impl.PlacitPlaceModelImpl;
import eu.strasbourg.service.project.service.persistence.PlacitPlacePersistence;

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
 * The persistence implementation for the placit place service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see PlacitPlacePersistence
 * @see eu.strasbourg.service.project.service.persistence.PlacitPlaceUtil
 * @generated
 */
@ProviderType
public class PlacitPlacePersistenceImpl extends BasePersistenceImpl<PlacitPlace>
	implements PlacitPlacePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PlacitPlaceUtil} to access the placit place persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PlacitPlaceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PlacitPlaceModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the placit places where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching placit places
	 */
	@Override
	public List<PlacitPlace> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the placit places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the placit places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByUuid(String uuid, int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the placit places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByUuid(String uuid, int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator,
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

		List<PlacitPlace> list = null;

		if (retrieveFromCache) {
			list = (List<PlacitPlace>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PlacitPlace placitPlace : list) {
					if (!Objects.equals(uuid, placitPlace.getUuid())) {
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

			query.append(_SQL_SELECT_PLACITPLACE_WHERE);

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
				query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
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
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first placit place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findByUuid_First(String uuid,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByUuid_First(uuid, orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the first placit place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByUuid_First(String uuid,
		OrderByComparator<PlacitPlace> orderByComparator) {
		List<PlacitPlace> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last placit place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findByUuid_Last(String uuid,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByUuid_Last(uuid, orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the last placit place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByUuid_Last(String uuid,
		OrderByComparator<PlacitPlace> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PlacitPlace> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where uuid = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	@Override
	public PlacitPlace[] findByUuid_PrevAndNext(long placitPlaceId,
		String uuid, OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = findByPrimaryKey(placitPlaceId);

		Session session = null;

		try {
			session = openSession();

			PlacitPlace[] array = new PlacitPlaceImpl[3];

			array[0] = getByUuid_PrevAndNext(session, placitPlace, uuid,
					orderByComparator, true);

			array[1] = placitPlace;

			array[2] = getByUuid_PrevAndNext(session, placitPlace, uuid,
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

	protected PlacitPlace getByUuid_PrevAndNext(Session session,
		PlacitPlace placitPlace, String uuid,
		OrderByComparator<PlacitPlace> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PLACITPLACE_WHERE);

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
			query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(placitPlace);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PlacitPlace> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the placit places where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PlacitPlace placitPlace : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(placitPlace);
		}
	}

	/**
	 * Returns the number of placit places where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching placit places
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PLACITPLACE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "placitPlace.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "placitPlace.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(placitPlace.uuid IS NULL OR placitPlace.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PlacitPlaceModelImpl.UUID_COLUMN_BITMASK |
			PlacitPlaceModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the placit place where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPlacitPlaceException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findByUUID_G(String uuid, long groupId)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByUUID_G(uuid, groupId);

		if (placitPlace == null) {
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

			throw new NoSuchPlacitPlaceException(msg.toString());
		}

		return placitPlace;
	}

	/**
	 * Returns the placit place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the placit place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PlacitPlace) {
			PlacitPlace placitPlace = (PlacitPlace)result;

			if (!Objects.equals(uuid, placitPlace.getUuid()) ||
					(groupId != placitPlace.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PLACITPLACE_WHERE);

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

				List<PlacitPlace> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					PlacitPlace placitPlace = list.get(0);

					result = placitPlace;

					cacheResult(placitPlace);

					if ((placitPlace.getUuid() == null) ||
							!placitPlace.getUuid().equals(uuid) ||
							(placitPlace.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, placitPlace);
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
			return (PlacitPlace)result;
		}
	}

	/**
	 * Removes the placit place where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the placit place that was removed
	 */
	@Override
	public PlacitPlace removeByUUID_G(String uuid, long groupId)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = findByUUID_G(uuid, groupId);

		return remove(placitPlace);
	}

	/**
	 * Returns the number of placit places where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching placit places
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PLACITPLACE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "placitPlace.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "placitPlace.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(placitPlace.uuid IS NULL OR placitPlace.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "placitPlace.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PlacitPlaceModelImpl.UUID_COLUMN_BITMASK |
			PlacitPlaceModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the placit places where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching placit places
	 */
	@Override
	public List<PlacitPlace> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the placit places where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the placit places where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PlacitPlace> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the placit places where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PlacitPlace> orderByComparator,
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

		List<PlacitPlace> list = null;

		if (retrieveFromCache) {
			list = (List<PlacitPlace>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PlacitPlace placitPlace : list) {
					if (!Objects.equals(uuid, placitPlace.getUuid()) ||
							(companyId != placitPlace.getCompanyId())) {
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

			query.append(_SQL_SELECT_PLACITPLACE_WHERE);

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
				query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
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
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the first placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		List<PlacitPlace> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the last placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<PlacitPlace> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	@Override
	public PlacitPlace[] findByUuid_C_PrevAndNext(long placitPlaceId,
		String uuid, long companyId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = findByPrimaryKey(placitPlaceId);

		Session session = null;

		try {
			session = openSession();

			PlacitPlace[] array = new PlacitPlaceImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, placitPlace, uuid,
					companyId, orderByComparator, true);

			array[1] = placitPlace;

			array[2] = getByUuid_C_PrevAndNext(session, placitPlace, uuid,
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

	protected PlacitPlace getByUuid_C_PrevAndNext(Session session,
		PlacitPlace placitPlace, String uuid, long companyId,
		OrderByComparator<PlacitPlace> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PLACITPLACE_WHERE);

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
			query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(placitPlace);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PlacitPlace> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the placit places where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (PlacitPlace placitPlace : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(placitPlace);
		}
	}

	/**
	 * Returns the number of placit places where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching placit places
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PLACITPLACE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "placitPlace.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "placitPlace.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(placitPlace.uuid IS NULL OR placitPlace.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "placitPlace.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			PlacitPlaceModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the placit places where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching placit places
	 */
	@Override
	public List<PlacitPlace> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the placit places where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the placit places where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByGroupId(long groupId, int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the placit places where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByGroupId(long groupId, int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator,
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

		List<PlacitPlace> list = null;

		if (retrieveFromCache) {
			list = (List<PlacitPlace>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PlacitPlace placitPlace : list) {
					if ((groupId != placitPlace.getGroupId())) {
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

			query.append(_SQL_SELECT_PLACITPLACE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first placit place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findByGroupId_First(long groupId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByGroupId_First(groupId,
				orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the first placit place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByGroupId_First(long groupId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		List<PlacitPlace> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last placit place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findByGroupId_Last(long groupId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByGroupId_Last(groupId, orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the last placit place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByGroupId_Last(long groupId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<PlacitPlace> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where groupId = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	@Override
	public PlacitPlace[] findByGroupId_PrevAndNext(long placitPlaceId,
		long groupId, OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = findByPrimaryKey(placitPlaceId);

		Session session = null;

		try {
			session = openSession();

			PlacitPlace[] array = new PlacitPlaceImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, placitPlace, groupId,
					orderByComparator, true);

			array[1] = placitPlace;

			array[2] = getByGroupId_PrevAndNext(session, placitPlace, groupId,
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

	protected PlacitPlace getByGroupId_PrevAndNext(Session session,
		PlacitPlace placitPlace, long groupId,
		OrderByComparator<PlacitPlace> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PLACITPLACE_WHERE);

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
			query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(placitPlace);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PlacitPlace> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the placit places where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (PlacitPlace placitPlace : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(placitPlace);
		}
	}

	/**
	 * Returns the number of placit places where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching placit places
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PLACITPLACE_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "placitPlace.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROJECT = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProject",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECT =
		new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProject",
			new String[] { Long.class.getName() },
			PlacitPlaceModelImpl.PROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PROJECT = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProject",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the placit places where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the matching placit places
	 */
	@Override
	public List<PlacitPlace> findByProject(long projectId) {
		return findByProject(projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the placit places where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByProject(long projectId, int start, int end) {
		return findByProject(projectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the placit places where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByProject(long projectId, int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return findByProject(projectId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the placit places where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByProject(long projectId, int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECT;
			finderArgs = new Object[] { projectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROJECT;
			finderArgs = new Object[] { projectId, start, end, orderByComparator };
		}

		List<PlacitPlace> list = null;

		if (retrieveFromCache) {
			list = (List<PlacitPlace>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PlacitPlace placitPlace : list) {
					if ((projectId != placitPlace.getProjectId())) {
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

			query.append(_SQL_SELECT_PLACITPLACE_WHERE);

			query.append(_FINDER_COLUMN_PROJECT_PROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (!pagination) {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first placit place in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findByProject_First(long projectId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByProject_First(projectId,
				orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the first placit place in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByProject_First(long projectId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		List<PlacitPlace> list = findByProject(projectId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last placit place in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findByProject_Last(long projectId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByProject_Last(projectId,
				orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the last placit place in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByProject_Last(long projectId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		int count = countByProject(projectId);

		if (count == 0) {
			return null;
		}

		List<PlacitPlace> list = findByProject(projectId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where projectId = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	@Override
	public PlacitPlace[] findByProject_PrevAndNext(long placitPlaceId,
		long projectId, OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = findByPrimaryKey(placitPlaceId);

		Session session = null;

		try {
			session = openSession();

			PlacitPlace[] array = new PlacitPlaceImpl[3];

			array[0] = getByProject_PrevAndNext(session, placitPlace,
					projectId, orderByComparator, true);

			array[1] = placitPlace;

			array[2] = getByProject_PrevAndNext(session, placitPlace,
					projectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PlacitPlace getByProject_PrevAndNext(Session session,
		PlacitPlace placitPlace, long projectId,
		OrderByComparator<PlacitPlace> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PLACITPLACE_WHERE);

		query.append(_FINDER_COLUMN_PROJECT_PROJECTID_2);

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
			query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(placitPlace);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PlacitPlace> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the placit places where projectId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 */
	@Override
	public void removeByProject(long projectId) {
		for (PlacitPlace placitPlace : findByProject(projectId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(placitPlace);
		}
	}

	/**
	 * Returns the number of placit places where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the number of matching placit places
	 */
	@Override
	public int countByProject(long projectId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PROJECT;

		Object[] finderArgs = new Object[] { projectId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PLACITPLACE_WHERE);

			query.append(_FINDER_COLUMN_PROJECT_PROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

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

	private static final String _FINDER_COLUMN_PROJECT_PROJECTID_2 = "placitPlace.projectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARTICIPATION =
		new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByParticipation",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTICIPATION =
		new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByParticipation",
			new String[] { Long.class.getName() },
			PlacitPlaceModelImpl.PARTICIPATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARTICIPATION = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByParticipation",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the placit places where participationId = &#63;.
	 *
	 * @param participationId the participation ID
	 * @return the matching placit places
	 */
	@Override
	public List<PlacitPlace> findByParticipation(long participationId) {
		return findByParticipation(participationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the placit places where participationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param participationId the participation ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByParticipation(long participationId,
		int start, int end) {
		return findByParticipation(participationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the placit places where participationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param participationId the participation ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByParticipation(long participationId,
		int start, int end, OrderByComparator<PlacitPlace> orderByComparator) {
		return findByParticipation(participationId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the placit places where participationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param participationId the participation ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByParticipation(long participationId,
		int start, int end, OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTICIPATION;
			finderArgs = new Object[] { participationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARTICIPATION;
			finderArgs = new Object[] {
					participationId,
					
					start, end, orderByComparator
				};
		}

		List<PlacitPlace> list = null;

		if (retrieveFromCache) {
			list = (List<PlacitPlace>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PlacitPlace placitPlace : list) {
					if ((participationId != placitPlace.getParticipationId())) {
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

			query.append(_SQL_SELECT_PLACITPLACE_WHERE);

			query.append(_FINDER_COLUMN_PARTICIPATION_PARTICIPATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(participationId);

				if (!pagination) {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first placit place in the ordered set where participationId = &#63;.
	 *
	 * @param participationId the participation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findByParticipation_First(long participationId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByParticipation_First(participationId,
				orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("participationId=");
		msg.append(participationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the first placit place in the ordered set where participationId = &#63;.
	 *
	 * @param participationId the participation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByParticipation_First(long participationId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		List<PlacitPlace> list = findByParticipation(participationId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last placit place in the ordered set where participationId = &#63;.
	 *
	 * @param participationId the participation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findByParticipation_Last(long participationId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByParticipation_Last(participationId,
				orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("participationId=");
		msg.append(participationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the last placit place in the ordered set where participationId = &#63;.
	 *
	 * @param participationId the participation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByParticipation_Last(long participationId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		int count = countByParticipation(participationId);

		if (count == 0) {
			return null;
		}

		List<PlacitPlace> list = findByParticipation(participationId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where participationId = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param participationId the participation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	@Override
	public PlacitPlace[] findByParticipation_PrevAndNext(long placitPlaceId,
		long participationId, OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = findByPrimaryKey(placitPlaceId);

		Session session = null;

		try {
			session = openSession();

			PlacitPlace[] array = new PlacitPlaceImpl[3];

			array[0] = getByParticipation_PrevAndNext(session, placitPlace,
					participationId, orderByComparator, true);

			array[1] = placitPlace;

			array[2] = getByParticipation_PrevAndNext(session, placitPlace,
					participationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PlacitPlace getByParticipation_PrevAndNext(Session session,
		PlacitPlace placitPlace, long participationId,
		OrderByComparator<PlacitPlace> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PLACITPLACE_WHERE);

		query.append(_FINDER_COLUMN_PARTICIPATION_PARTICIPATIONID_2);

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
			query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(participationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(placitPlace);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PlacitPlace> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the placit places where participationId = &#63; from the database.
	 *
	 * @param participationId the participation ID
	 */
	@Override
	public void removeByParticipation(long participationId) {
		for (PlacitPlace placitPlace : findByParticipation(participationId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(placitPlace);
		}
	}

	/**
	 * Returns the number of placit places where participationId = &#63;.
	 *
	 * @param participationId the participation ID
	 * @return the number of matching placit places
	 */
	@Override
	public int countByParticipation(long participationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PARTICIPATION;

		Object[] finderArgs = new Object[] { participationId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PLACITPLACE_WHERE);

			query.append(_FINDER_COLUMN_PARTICIPATION_PARTICIPATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(participationId);

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

	private static final String _FINDER_COLUMN_PARTICIPATION_PARTICIPATIONID_2 = "placitPlace.participationId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PETITION = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPetition",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITION =
		new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPetition",
			new String[] { Long.class.getName() },
			PlacitPlaceModelImpl.PETITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PETITION = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPetition",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the placit places where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @return the matching placit places
	 */
	@Override
	public List<PlacitPlace> findByPetition(long petitionId) {
		return findByPetition(petitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the placit places where petitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByPetition(long petitionId, int start, int end) {
		return findByPetition(petitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the placit places where petitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByPetition(long petitionId, int start,
		int end, OrderByComparator<PlacitPlace> orderByComparator) {
		return findByPetition(petitionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the placit places where petitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findByPetition(long petitionId, int start,
		int end, OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITION;
			finderArgs = new Object[] { petitionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PETITION;
			finderArgs = new Object[] { petitionId, start, end, orderByComparator };
		}

		List<PlacitPlace> list = null;

		if (retrieveFromCache) {
			list = (List<PlacitPlace>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PlacitPlace placitPlace : list) {
					if ((petitionId != placitPlace.getPetitionId())) {
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

			query.append(_SQL_SELECT_PLACITPLACE_WHERE);

			query.append(_FINDER_COLUMN_PETITION_PETITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(petitionId);

				if (!pagination) {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first placit place in the ordered set where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findByPetition_First(long petitionId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByPetition_First(petitionId,
				orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("petitionId=");
		msg.append(petitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the first placit place in the ordered set where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByPetition_First(long petitionId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		List<PlacitPlace> list = findByPetition(petitionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last placit place in the ordered set where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findByPetition_Last(long petitionId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByPetition_Last(petitionId,
				orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("petitionId=");
		msg.append(petitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the last placit place in the ordered set where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchByPetition_Last(long petitionId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		int count = countByPetition(petitionId);

		if (count == 0) {
			return null;
		}

		List<PlacitPlace> list = findByPetition(petitionId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where petitionId = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	@Override
	public PlacitPlace[] findByPetition_PrevAndNext(long placitPlaceId,
		long petitionId, OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = findByPrimaryKey(placitPlaceId);

		Session session = null;

		try {
			session = openSession();

			PlacitPlace[] array = new PlacitPlaceImpl[3];

			array[0] = getByPetition_PrevAndNext(session, placitPlace,
					petitionId, orderByComparator, true);

			array[1] = placitPlace;

			array[2] = getByPetition_PrevAndNext(session, placitPlace,
					petitionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PlacitPlace getByPetition_PrevAndNext(Session session,
		PlacitPlace placitPlace, long petitionId,
		OrderByComparator<PlacitPlace> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PLACITPLACE_WHERE);

		query.append(_FINDER_COLUMN_PETITION_PETITIONID_2);

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
			query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(petitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(placitPlace);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PlacitPlace> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the placit places where petitionId = &#63; from the database.
	 *
	 * @param petitionId the petition ID
	 */
	@Override
	public void removeByPetition(long petitionId) {
		for (PlacitPlace placitPlace : findByPetition(petitionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(placitPlace);
		}
	}

	/**
	 * Returns the number of placit places where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @return the number of matching placit places
	 */
	@Override
	public int countByPetition(long petitionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PETITION;

		Object[] finderArgs = new Object[] { petitionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PLACITPLACE_WHERE);

			query.append(_FINDER_COLUMN_PETITION_PETITIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(petitionId);

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

	private static final String _FINDER_COLUMN_PETITION_PETITIONID_2 = "placitPlace.petitionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SIGID = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySigId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIGID = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, PlacitPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySigId",
			new String[] { String.class.getName() },
			PlacitPlaceModelImpl.PLACESIGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIGID = new FinderPath(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySigId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the placit places where placeSIGId = &#63;.
	 *
	 * @param placeSIGId the place sig ID
	 * @return the matching placit places
	 */
	@Override
	public List<PlacitPlace> findBySigId(String placeSIGId) {
		return findBySigId(placeSIGId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the placit places where placeSIGId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param placeSIGId the place sig ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findBySigId(String placeSIGId, int start, int end) {
		return findBySigId(placeSIGId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the placit places where placeSIGId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param placeSIGId the place sig ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findBySigId(String placeSIGId, int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return findBySigId(placeSIGId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the placit places where placeSIGId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param placeSIGId the place sig ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching placit places
	 */
	@Override
	public List<PlacitPlace> findBySigId(String placeSIGId, int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIGID;
			finderArgs = new Object[] { placeSIGId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SIGID;
			finderArgs = new Object[] { placeSIGId, start, end, orderByComparator };
		}

		List<PlacitPlace> list = null;

		if (retrieveFromCache) {
			list = (List<PlacitPlace>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PlacitPlace placitPlace : list) {
					if (!Objects.equals(placeSIGId, placitPlace.getPlaceSIGId())) {
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

			query.append(_SQL_SELECT_PLACITPLACE_WHERE);

			boolean bindPlaceSIGId = false;

			if (placeSIGId == null) {
				query.append(_FINDER_COLUMN_SIGID_PLACESIGID_1);
			}
			else if (placeSIGId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIGID_PLACESIGID_3);
			}
			else {
				bindPlaceSIGId = true;

				query.append(_FINDER_COLUMN_SIGID_PLACESIGID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPlaceSIGId) {
					qPos.add(placeSIGId);
				}

				if (!pagination) {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first placit place in the ordered set where placeSIGId = &#63;.
	 *
	 * @param placeSIGId the place sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findBySigId_First(String placeSIGId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchBySigId_First(placeSIGId,
				orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("placeSIGId=");
		msg.append(placeSIGId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the first placit place in the ordered set where placeSIGId = &#63;.
	 *
	 * @param placeSIGId the place sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchBySigId_First(String placeSIGId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		List<PlacitPlace> list = findBySigId(placeSIGId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last placit place in the ordered set where placeSIGId = &#63;.
	 *
	 * @param placeSIGId the place sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace findBySigId_Last(String placeSIGId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchBySigId_Last(placeSIGId,
				orderByComparator);

		if (placitPlace != null) {
			return placitPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("placeSIGId=");
		msg.append(placeSIGId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPlacitPlaceException(msg.toString());
	}

	/**
	 * Returns the last placit place in the ordered set where placeSIGId = &#63;.
	 *
	 * @param placeSIGId the place sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	@Override
	public PlacitPlace fetchBySigId_Last(String placeSIGId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		int count = countBySigId(placeSIGId);

		if (count == 0) {
			return null;
		}

		List<PlacitPlace> list = findBySigId(placeSIGId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where placeSIGId = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param placeSIGId the place sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	@Override
	public PlacitPlace[] findBySigId_PrevAndNext(long placitPlaceId,
		String placeSIGId, OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = findByPrimaryKey(placitPlaceId);

		Session session = null;

		try {
			session = openSession();

			PlacitPlace[] array = new PlacitPlaceImpl[3];

			array[0] = getBySigId_PrevAndNext(session, placitPlace, placeSIGId,
					orderByComparator, true);

			array[1] = placitPlace;

			array[2] = getBySigId_PrevAndNext(session, placitPlace, placeSIGId,
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

	protected PlacitPlace getBySigId_PrevAndNext(Session session,
		PlacitPlace placitPlace, String placeSIGId,
		OrderByComparator<PlacitPlace> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PLACITPLACE_WHERE);

		boolean bindPlaceSIGId = false;

		if (placeSIGId == null) {
			query.append(_FINDER_COLUMN_SIGID_PLACESIGID_1);
		}
		else if (placeSIGId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_SIGID_PLACESIGID_3);
		}
		else {
			bindPlaceSIGId = true;

			query.append(_FINDER_COLUMN_SIGID_PLACESIGID_2);
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
			query.append(PlacitPlaceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPlaceSIGId) {
			qPos.add(placeSIGId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(placitPlace);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PlacitPlace> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the placit places where placeSIGId = &#63; from the database.
	 *
	 * @param placeSIGId the place sig ID
	 */
	@Override
	public void removeBySigId(String placeSIGId) {
		for (PlacitPlace placitPlace : findBySigId(placeSIGId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(placitPlace);
		}
	}

	/**
	 * Returns the number of placit places where placeSIGId = &#63;.
	 *
	 * @param placeSIGId the place sig ID
	 * @return the number of matching placit places
	 */
	@Override
	public int countBySigId(String placeSIGId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIGID;

		Object[] finderArgs = new Object[] { placeSIGId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PLACITPLACE_WHERE);

			boolean bindPlaceSIGId = false;

			if (placeSIGId == null) {
				query.append(_FINDER_COLUMN_SIGID_PLACESIGID_1);
			}
			else if (placeSIGId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIGID_PLACESIGID_3);
			}
			else {
				bindPlaceSIGId = true;

				query.append(_FINDER_COLUMN_SIGID_PLACESIGID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPlaceSIGId) {
					qPos.add(placeSIGId);
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

	private static final String _FINDER_COLUMN_SIGID_PLACESIGID_1 = "placitPlace.placeSIGId IS NULL";
	private static final String _FINDER_COLUMN_SIGID_PLACESIGID_2 = "placitPlace.placeSIGId = ?";
	private static final String _FINDER_COLUMN_SIGID_PLACESIGID_3 = "(placitPlace.placeSIGId IS NULL OR placitPlace.placeSIGId = '')";

	public PlacitPlacePersistenceImpl() {
		setModelClass(PlacitPlace.class);

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
	 * Caches the placit place in the entity cache if it is enabled.
	 *
	 * @param placitPlace the placit place
	 */
	@Override
	public void cacheResult(PlacitPlace placitPlace) {
		entityCache.putResult(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceImpl.class, placitPlace.getPrimaryKey(), placitPlace);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { placitPlace.getUuid(), placitPlace.getGroupId() },
			placitPlace);

		placitPlace.resetOriginalValues();
	}

	/**
	 * Caches the placit places in the entity cache if it is enabled.
	 *
	 * @param placitPlaces the placit places
	 */
	@Override
	public void cacheResult(List<PlacitPlace> placitPlaces) {
		for (PlacitPlace placitPlace : placitPlaces) {
			if (entityCache.getResult(
						PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
						PlacitPlaceImpl.class, placitPlace.getPrimaryKey()) == null) {
				cacheResult(placitPlace);
			}
			else {
				placitPlace.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all placit places.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PlacitPlaceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the placit place.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PlacitPlace placitPlace) {
		entityCache.removeResult(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceImpl.class, placitPlace.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PlacitPlaceModelImpl)placitPlace, true);
	}

	@Override
	public void clearCache(List<PlacitPlace> placitPlaces) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PlacitPlace placitPlace : placitPlaces) {
			entityCache.removeResult(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
				PlacitPlaceImpl.class, placitPlace.getPrimaryKey());

			clearUniqueFindersCache((PlacitPlaceModelImpl)placitPlace, true);
		}
	}

	protected void cacheUniqueFindersCache(
		PlacitPlaceModelImpl placitPlaceModelImpl) {
		Object[] args = new Object[] {
				placitPlaceModelImpl.getUuid(),
				placitPlaceModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			placitPlaceModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PlacitPlaceModelImpl placitPlaceModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					placitPlaceModelImpl.getUuid(),
					placitPlaceModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((placitPlaceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					placitPlaceModelImpl.getOriginalUuid(),
					placitPlaceModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new placit place with the primary key. Does not add the placit place to the database.
	 *
	 * @param placitPlaceId the primary key for the new placit place
	 * @return the new placit place
	 */
	@Override
	public PlacitPlace create(long placitPlaceId) {
		PlacitPlace placitPlace = new PlacitPlaceImpl();

		placitPlace.setNew(true);
		placitPlace.setPrimaryKey(placitPlaceId);

		String uuid = PortalUUIDUtil.generate();

		placitPlace.setUuid(uuid);

		placitPlace.setCompanyId(companyProvider.getCompanyId());

		return placitPlace;
	}

	/**
	 * Removes the placit place with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param placitPlaceId the primary key of the placit place
	 * @return the placit place that was removed
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	@Override
	public PlacitPlace remove(long placitPlaceId)
		throws NoSuchPlacitPlaceException {
		return remove((Serializable)placitPlaceId);
	}

	/**
	 * Removes the placit place with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the placit place
	 * @return the placit place that was removed
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	@Override
	public PlacitPlace remove(Serializable primaryKey)
		throws NoSuchPlacitPlaceException {
		Session session = null;

		try {
			session = openSession();

			PlacitPlace placitPlace = (PlacitPlace)session.get(PlacitPlaceImpl.class,
					primaryKey);

			if (placitPlace == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPlacitPlaceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(placitPlace);
		}
		catch (NoSuchPlacitPlaceException nsee) {
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
	protected PlacitPlace removeImpl(PlacitPlace placitPlace) {
		placitPlace = toUnwrappedModel(placitPlace);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(placitPlace)) {
				placitPlace = (PlacitPlace)session.get(PlacitPlaceImpl.class,
						placitPlace.getPrimaryKeyObj());
			}

			if (placitPlace != null) {
				session.delete(placitPlace);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (placitPlace != null) {
			clearCache(placitPlace);
		}

		return placitPlace;
	}

	@Override
	public PlacitPlace updateImpl(PlacitPlace placitPlace) {
		placitPlace = toUnwrappedModel(placitPlace);

		boolean isNew = placitPlace.isNew();

		PlacitPlaceModelImpl placitPlaceModelImpl = (PlacitPlaceModelImpl)placitPlace;

		if (Validator.isNull(placitPlace.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			placitPlace.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (placitPlace.getCreateDate() == null)) {
			if (serviceContext == null) {
				placitPlace.setCreateDate(now);
			}
			else {
				placitPlace.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!placitPlaceModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				placitPlace.setModifiedDate(now);
			}
			else {
				placitPlace.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (placitPlace.isNew()) {
				session.save(placitPlace);

				placitPlace.setNew(false);
			}
			else {
				placitPlace = (PlacitPlace)session.merge(placitPlace);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PlacitPlaceModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { placitPlaceModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					placitPlaceModelImpl.getUuid(),
					placitPlaceModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { placitPlaceModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] { placitPlaceModelImpl.getProjectId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PROJECT, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECT,
				args);

			args = new Object[] { placitPlaceModelImpl.getParticipationId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PARTICIPATION, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTICIPATION,
				args);

			args = new Object[] { placitPlaceModelImpl.getPetitionId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PETITION, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITION,
				args);

			args = new Object[] { placitPlaceModelImpl.getPlaceSIGId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SIGID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIGID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((placitPlaceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						placitPlaceModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { placitPlaceModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((placitPlaceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						placitPlaceModelImpl.getOriginalUuid(),
						placitPlaceModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						placitPlaceModelImpl.getUuid(),
						placitPlaceModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((placitPlaceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						placitPlaceModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { placitPlaceModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((placitPlaceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						placitPlaceModelImpl.getOriginalProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PROJECT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECT,
					args);

				args = new Object[] { placitPlaceModelImpl.getProjectId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PROJECT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECT,
					args);
			}

			if ((placitPlaceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTICIPATION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						placitPlaceModelImpl.getOriginalParticipationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PARTICIPATION,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTICIPATION,
					args);

				args = new Object[] { placitPlaceModelImpl.getParticipationId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PARTICIPATION,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTICIPATION,
					args);
			}

			if ((placitPlaceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						placitPlaceModelImpl.getOriginalPetitionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PETITION, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITION,
					args);

				args = new Object[] { placitPlaceModelImpl.getPetitionId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PETITION, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITION,
					args);
			}

			if ((placitPlaceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						placitPlaceModelImpl.getOriginalPlaceSIGId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SIGID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIGID,
					args);

				args = new Object[] { placitPlaceModelImpl.getPlaceSIGId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SIGID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIGID,
					args);
			}
		}

		entityCache.putResult(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
			PlacitPlaceImpl.class, placitPlace.getPrimaryKey(), placitPlace,
			false);

		clearUniqueFindersCache(placitPlaceModelImpl, false);
		cacheUniqueFindersCache(placitPlaceModelImpl);

		placitPlace.resetOriginalValues();

		return placitPlace;
	}

	protected PlacitPlace toUnwrappedModel(PlacitPlace placitPlace) {
		if (placitPlace instanceof PlacitPlaceImpl) {
			return placitPlace;
		}

		PlacitPlaceImpl placitPlaceImpl = new PlacitPlaceImpl();

		placitPlaceImpl.setNew(placitPlace.isNew());
		placitPlaceImpl.setPrimaryKey(placitPlace.getPrimaryKey());

		placitPlaceImpl.setUuid(placitPlace.getUuid());
		placitPlaceImpl.setPlacitPlaceId(placitPlace.getPlacitPlaceId());
		placitPlaceImpl.setGroupId(placitPlace.getGroupId());
		placitPlaceImpl.setCompanyId(placitPlace.getCompanyId());
		placitPlaceImpl.setUserId(placitPlace.getUserId());
		placitPlaceImpl.setUserName(placitPlace.getUserName());
		placitPlaceImpl.setCreateDate(placitPlace.getCreateDate());
		placitPlaceImpl.setModifiedDate(placitPlace.getModifiedDate());
		placitPlaceImpl.setPlaceName(placitPlace.getPlaceName());
		placitPlaceImpl.setPlaceStreetNumber(placitPlace.getPlaceStreetNumber());
		placitPlaceImpl.setPlaceStreetName(placitPlace.getPlaceStreetName());
		placitPlaceImpl.setPlaceZipCode(placitPlace.getPlaceZipCode());
		placitPlaceImpl.setPlaceCityId(placitPlace.getPlaceCityId());
		placitPlaceImpl.setImageId(placitPlace.getImageId());
		placitPlaceImpl.setProjectId(placitPlace.getProjectId());
		placitPlaceImpl.setParticipationId(placitPlace.getParticipationId());
		placitPlaceImpl.setPetitionId(placitPlace.getPetitionId());
		placitPlaceImpl.setPlaceSIGId(placitPlace.getPlaceSIGId());

		return placitPlaceImpl;
	}

	/**
	 * Returns the placit place with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the placit place
	 * @return the placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	@Override
	public PlacitPlace findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPlacitPlaceException {
		PlacitPlace placitPlace = fetchByPrimaryKey(primaryKey);

		if (placitPlace == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPlacitPlaceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return placitPlace;
	}

	/**
	 * Returns the placit place with the primary key or throws a {@link NoSuchPlacitPlaceException} if it could not be found.
	 *
	 * @param placitPlaceId the primary key of the placit place
	 * @return the placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	@Override
	public PlacitPlace findByPrimaryKey(long placitPlaceId)
		throws NoSuchPlacitPlaceException {
		return findByPrimaryKey((Serializable)placitPlaceId);
	}

	/**
	 * Returns the placit place with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the placit place
	 * @return the placit place, or <code>null</code> if a placit place with the primary key could not be found
	 */
	@Override
	public PlacitPlace fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
				PlacitPlaceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PlacitPlace placitPlace = (PlacitPlace)serializable;

		if (placitPlace == null) {
			Session session = null;

			try {
				session = openSession();

				placitPlace = (PlacitPlace)session.get(PlacitPlaceImpl.class,
						primaryKey);

				if (placitPlace != null) {
					cacheResult(placitPlace);
				}
				else {
					entityCache.putResult(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
						PlacitPlaceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
					PlacitPlaceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return placitPlace;
	}

	/**
	 * Returns the placit place with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param placitPlaceId the primary key of the placit place
	 * @return the placit place, or <code>null</code> if a placit place with the primary key could not be found
	 */
	@Override
	public PlacitPlace fetchByPrimaryKey(long placitPlaceId) {
		return fetchByPrimaryKey((Serializable)placitPlaceId);
	}

	@Override
	public Map<Serializable, PlacitPlace> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PlacitPlace> map = new HashMap<Serializable, PlacitPlace>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PlacitPlace placitPlace = fetchByPrimaryKey(primaryKey);

			if (placitPlace != null) {
				map.put(primaryKey, placitPlace);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
					PlacitPlaceImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PlacitPlace)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PLACITPLACE_WHERE_PKS_IN);

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

			for (PlacitPlace placitPlace : (List<PlacitPlace>)q.list()) {
				map.put(placitPlace.getPrimaryKeyObj(), placitPlace);

				cacheResult(placitPlace);

				uncachedPrimaryKeys.remove(placitPlace.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PlacitPlaceModelImpl.ENTITY_CACHE_ENABLED,
					PlacitPlaceImpl.class, primaryKey, nullModel);
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
	 * Returns all the placit places.
	 *
	 * @return the placit places
	 */
	@Override
	public List<PlacitPlace> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the placit places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of placit places
	 */
	@Override
	public List<PlacitPlace> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the placit places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of placit places
	 */
	@Override
	public List<PlacitPlace> findAll(int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the placit places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of placit places
	 */
	@Override
	public List<PlacitPlace> findAll(int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator,
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

		List<PlacitPlace> list = null;

		if (retrieveFromCache) {
			list = (List<PlacitPlace>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PLACITPLACE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PLACITPLACE;

				if (pagination) {
					sql = sql.concat(PlacitPlaceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PlacitPlace>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the placit places from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PlacitPlace placitPlace : findAll()) {
			remove(placitPlace);
		}
	}

	/**
	 * Returns the number of placit places.
	 *
	 * @return the number of placit places
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PLACITPLACE);

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
		return PlacitPlaceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the placit place persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PlacitPlaceImpl.class.getName());
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
	private static final String _SQL_SELECT_PLACITPLACE = "SELECT placitPlace FROM PlacitPlace placitPlace";
	private static final String _SQL_SELECT_PLACITPLACE_WHERE_PKS_IN = "SELECT placitPlace FROM PlacitPlace placitPlace WHERE placitPlaceId IN (";
	private static final String _SQL_SELECT_PLACITPLACE_WHERE = "SELECT placitPlace FROM PlacitPlace placitPlace WHERE ";
	private static final String _SQL_COUNT_PLACITPLACE = "SELECT COUNT(placitPlace) FROM PlacitPlace placitPlace";
	private static final String _SQL_COUNT_PLACITPLACE_WHERE = "SELECT COUNT(placitPlace) FROM PlacitPlace placitPlace WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "placitPlace.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlacitPlace exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlacitPlace exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PlacitPlacePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}