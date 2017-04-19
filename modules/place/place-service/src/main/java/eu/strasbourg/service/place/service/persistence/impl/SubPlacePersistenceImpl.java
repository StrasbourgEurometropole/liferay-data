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

import eu.strasbourg.service.place.exception.NoSuchSubPlaceException;
import eu.strasbourg.service.place.model.SubPlace;
import eu.strasbourg.service.place.model.impl.SubPlaceImpl;
import eu.strasbourg.service.place.model.impl.SubPlaceModelImpl;
import eu.strasbourg.service.place.service.persistence.SubPlacePersistence;

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
 * The persistence implementation for the sub place service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see SubPlacePersistence
 * @see eu.strasbourg.service.place.service.persistence.SubPlaceUtil
 * @generated
 */
@ProviderType
public class SubPlacePersistenceImpl extends BasePersistenceImpl<SubPlace>
	implements SubPlacePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SubPlaceUtil} to access the sub place persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SubPlaceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, SubPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, SubPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, SubPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, SubPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SubPlaceModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sub places
	 */
	@Override
	public List<SubPlace> findByUuid(String uuid, int start, int end,
		OrderByComparator<SubPlace> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sub places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sub places
	 */
	@Override
	public List<SubPlace> findByUuid(String uuid, int start, int end,
		OrderByComparator<SubPlace> orderByComparator, boolean retrieveFromCache) {
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

		List<SubPlace> list = null;

		if (retrieveFromCache) {
			list = (List<SubPlace>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SubPlace subPlace : list) {
					if (!Objects.equals(uuid, subPlace.getUuid())) {
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

			query.append(_SQL_SELECT_SUBPLACE_WHERE);

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
				query.append(SubPlaceModelImpl.ORDER_BY_JPQL);
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
					list = (List<SubPlace>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SubPlace>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sub place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sub place
	 * @throws NoSuchSubPlaceException if a matching sub place could not be found
	 */
	@Override
	public SubPlace findByUuid_First(String uuid,
		OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException {
		SubPlace subPlace = fetchByUuid_First(uuid, orderByComparator);

		if (subPlace != null) {
			return subPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSubPlaceException(msg.toString());
	}

	/**
	 * Returns the first sub place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sub place, or <code>null</code> if a matching sub place could not be found
	 */
	@Override
	public SubPlace fetchByUuid_First(String uuid,
		OrderByComparator<SubPlace> orderByComparator) {
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
	public SubPlace findByUuid_Last(String uuid,
		OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException {
		SubPlace subPlace = fetchByUuid_Last(uuid, orderByComparator);

		if (subPlace != null) {
			return subPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSubPlaceException(msg.toString());
	}

	/**
	 * Returns the last sub place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sub place, or <code>null</code> if a matching sub place could not be found
	 */
	@Override
	public SubPlace fetchByUuid_Last(String uuid,
		OrderByComparator<SubPlace> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SubPlace> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

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
	public SubPlace[] findByUuid_PrevAndNext(long subPlaceId, String uuid,
		OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException {
		SubPlace subPlace = findByPrimaryKey(subPlaceId);

		Session session = null;

		try {
			session = openSession();

			SubPlace[] array = new SubPlaceImpl[3];

			array[0] = getByUuid_PrevAndNext(session, subPlace, uuid,
					orderByComparator, true);

			array[1] = subPlace;

			array[2] = getByUuid_PrevAndNext(session, subPlace, uuid,
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

	protected SubPlace getByUuid_PrevAndNext(Session session,
		SubPlace subPlace, String uuid,
		OrderByComparator<SubPlace> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUBPLACE_WHERE);

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
			query.append(SubPlaceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(subPlace);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SubPlace> list = q.list();

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
		for (SubPlace subPlace : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUBPLACE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "subPlace.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "subPlace.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(subPlace.uuid IS NULL OR subPlace.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PLACEID = new FinderPath(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, SubPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPlaceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLACEID =
		new FinderPath(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, SubPlaceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlaceId",
			new String[] { Long.class.getName() },
			SubPlaceModelImpl.PLACEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PLACEID = new FinderPath(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlaceId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the sub places where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @return the matching sub places
	 */
	@Override
	public List<SubPlace> findByPlaceId(long placeId) {
		return findByPlaceId(placeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sub places where placeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param placeId the place ID
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sub places
	 */
	@Override
	public List<SubPlace> findByPlaceId(long placeId, int start, int end,
		OrderByComparator<SubPlace> orderByComparator) {
		return findByPlaceId(placeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sub places where placeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param placeId the place ID
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sub places
	 */
	@Override
	public List<SubPlace> findByPlaceId(long placeId, int start, int end,
		OrderByComparator<SubPlace> orderByComparator, boolean retrieveFromCache) {
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

		List<SubPlace> list = null;

		if (retrieveFromCache) {
			list = (List<SubPlace>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SubPlace subPlace : list) {
					if ((placeId != subPlace.getPlaceId())) {
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

			query.append(_SQL_SELECT_SUBPLACE_WHERE);

			query.append(_FINDER_COLUMN_PLACEID_PLACEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SubPlaceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(placeId);

				if (!pagination) {
					list = (List<SubPlace>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SubPlace>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sub place in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sub place
	 * @throws NoSuchSubPlaceException if a matching sub place could not be found
	 */
	@Override
	public SubPlace findByPlaceId_First(long placeId,
		OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException {
		SubPlace subPlace = fetchByPlaceId_First(placeId, orderByComparator);

		if (subPlace != null) {
			return subPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("placeId=");
		msg.append(placeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSubPlaceException(msg.toString());
	}

	/**
	 * Returns the first sub place in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sub place, or <code>null</code> if a matching sub place could not be found
	 */
	@Override
	public SubPlace fetchByPlaceId_First(long placeId,
		OrderByComparator<SubPlace> orderByComparator) {
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
	public SubPlace findByPlaceId_Last(long placeId,
		OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException {
		SubPlace subPlace = fetchByPlaceId_Last(placeId, orderByComparator);

		if (subPlace != null) {
			return subPlace;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("placeId=");
		msg.append(placeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSubPlaceException(msg.toString());
	}

	/**
	 * Returns the last sub place in the ordered set where placeId = &#63;.
	 *
	 * @param placeId the place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sub place, or <code>null</code> if a matching sub place could not be found
	 */
	@Override
	public SubPlace fetchByPlaceId_Last(long placeId,
		OrderByComparator<SubPlace> orderByComparator) {
		int count = countByPlaceId(placeId);

		if (count == 0) {
			return null;
		}

		List<SubPlace> list = findByPlaceId(placeId, count - 1, count,
				orderByComparator);

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
	public SubPlace[] findByPlaceId_PrevAndNext(long subPlaceId, long placeId,
		OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException {
		SubPlace subPlace = findByPrimaryKey(subPlaceId);

		Session session = null;

		try {
			session = openSession();

			SubPlace[] array = new SubPlaceImpl[3];

			array[0] = getByPlaceId_PrevAndNext(session, subPlace, placeId,
					orderByComparator, true);

			array[1] = subPlace;

			array[2] = getByPlaceId_PrevAndNext(session, subPlace, placeId,
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

	protected SubPlace getByPlaceId_PrevAndNext(Session session,
		SubPlace subPlace, long placeId,
		OrderByComparator<SubPlace> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUBPLACE_WHERE);

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
			query.append(SubPlaceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(placeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(subPlace);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SubPlace> list = q.list();

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
		for (SubPlace subPlace : findByPlaceId(placeId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PLACEID;

		Object[] finderArgs = new Object[] { placeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUBPLACE_WHERE);

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

	private static final String _FINDER_COLUMN_PLACEID_PLACEID_2 = "subPlace.placeId = ?";

	public SubPlacePersistenceImpl() {
		setModelClass(SubPlace.class);
	}

	/**
	 * Caches the sub place in the entity cache if it is enabled.
	 *
	 * @param subPlace the sub place
	 */
	@Override
	public void cacheResult(SubPlace subPlace) {
		entityCache.putResult(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceImpl.class, subPlace.getPrimaryKey(), subPlace);

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
			if (entityCache.getResult(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
						SubPlaceImpl.class, subPlace.getPrimaryKey()) == null) {
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SubPlace subPlace) {
		entityCache.removeResult(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceImpl.class, subPlace.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SubPlace> subPlaces) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SubPlace subPlace : subPlaces) {
			entityCache.removeResult(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
				SubPlaceImpl.class, subPlace.getPrimaryKey());
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

			SubPlace subPlace = (SubPlace)session.get(SubPlaceImpl.class,
					primaryKey);

			if (subPlace == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSubPlaceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(subPlace);
		}
		catch (NoSuchSubPlaceException nsee) {
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
	protected SubPlace removeImpl(SubPlace subPlace) {
		subPlace = toUnwrappedModel(subPlace);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(subPlace)) {
				subPlace = (SubPlace)session.get(SubPlaceImpl.class,
						subPlace.getPrimaryKeyObj());
			}

			if (subPlace != null) {
				session.delete(subPlace);
			}
		}
		catch (Exception e) {
			throw processException(e);
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
		subPlace = toUnwrappedModel(subPlace);

		boolean isNew = subPlace.isNew();

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
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SubPlaceModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((subPlaceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { subPlaceModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { subPlaceModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((subPlaceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLACEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						subPlaceModelImpl.getOriginalPlaceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PLACEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLACEID,
					args);

				args = new Object[] { subPlaceModelImpl.getPlaceId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PLACEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLACEID,
					args);
			}
		}

		entityCache.putResult(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
			SubPlaceImpl.class, subPlace.getPrimaryKey(), subPlace, false);

		subPlace.resetOriginalValues();

		return subPlace;
	}

	protected SubPlace toUnwrappedModel(SubPlace subPlace) {
		if (subPlace instanceof SubPlaceImpl) {
			return subPlace;
		}

		SubPlaceImpl subPlaceImpl = new SubPlaceImpl();

		subPlaceImpl.setNew(subPlace.isNew());
		subPlaceImpl.setPrimaryKey(subPlace.getPrimaryKey());

		subPlaceImpl.setUuid(subPlace.getUuid());
		subPlaceImpl.setSubPlaceId(subPlace.getSubPlaceId());
		subPlaceImpl.setStatus(subPlace.getStatus());
		subPlaceImpl.setStatusByUserId(subPlace.getStatusByUserId());
		subPlaceImpl.setStatusByUserName(subPlace.getStatusByUserName());
		subPlaceImpl.setStatusDate(subPlace.getStatusDate());
		subPlaceImpl.setName(subPlace.getName());
		subPlaceImpl.setDescription(subPlace.getDescription());
		subPlaceImpl.setPlaceId(subPlace.getPlaceId());

		return subPlaceImpl;
	}

	/**
	 * Returns the sub place with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
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

			throw new NoSuchSubPlaceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return subPlace;
	}

	/**
	 * Returns the sub place with the primary key or throws a {@link NoSuchSubPlaceException} if it could not be found.
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
		Serializable serializable = entityCache.getResult(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
				SubPlaceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SubPlace subPlace = (SubPlace)serializable;

		if (subPlace == null) {
			Session session = null;

			try {
				session = openSession();

				subPlace = (SubPlace)session.get(SubPlaceImpl.class, primaryKey);

				if (subPlace != null) {
					cacheResult(subPlace);
				}
				else {
					entityCache.putResult(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
						SubPlaceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
					SubPlaceImpl.class, primaryKey);

				throw processException(e);
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
			Serializable serializable = entityCache.getResult(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
					SubPlaceImpl.class, primaryKey);

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

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SUBPLACE_WHERE_PKS_IN);

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

			for (SubPlace subPlace : (List<SubPlace>)q.list()) {
				map.put(subPlace.getPrimaryKeyObj(), subPlace);

				cacheResult(subPlace);

				uncachedPrimaryKeys.remove(subPlace.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SubPlaceModelImpl.ENTITY_CACHE_ENABLED,
					SubPlaceImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sub places
	 */
	@Override
	public List<SubPlace> findAll(int start, int end,
		OrderByComparator<SubPlace> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sub places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of sub places
	 */
	@Override
	public List<SubPlace> findAll(int start, int end,
		OrderByComparator<SubPlace> orderByComparator, boolean retrieveFromCache) {
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

		List<SubPlace> list = null;

		if (retrieveFromCache) {
			list = (List<SubPlace>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SUBPLACE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUBPLACE;

				if (pagination) {
					sql = sql.concat(SubPlaceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SubPlace>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SubPlace>)QueryUtil.list(q, getDialect(),
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
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUBPLACE);

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
		return SubPlaceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sub place persistence.
	 */
	public void afterPropertiesSet() {
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
	private static final String _SQL_SELECT_SUBPLACE = "SELECT subPlace FROM SubPlace subPlace";
	private static final String _SQL_SELECT_SUBPLACE_WHERE_PKS_IN = "SELECT subPlace FROM SubPlace subPlace WHERE subPlaceId IN (";
	private static final String _SQL_SELECT_SUBPLACE_WHERE = "SELECT subPlace FROM SubPlace subPlace WHERE ";
	private static final String _SQL_COUNT_SUBPLACE = "SELECT COUNT(subPlace) FROM SubPlace subPlace";
	private static final String _SQL_COUNT_SUBPLACE_WHERE = "SELECT COUNT(subPlace) FROM SubPlace subPlace WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "subPlace.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SubPlace exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SubPlace exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SubPlacePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}