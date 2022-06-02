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

import eu.strasbourg.service.place.exception.NoSuchGoogleMyBusinessHistoricException;
import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;
import eu.strasbourg.service.place.model.impl.GoogleMyBusinessHistoricImpl;
import eu.strasbourg.service.place.model.impl.GoogleMyBusinessHistoricModelImpl;
import eu.strasbourg.service.place.service.persistence.GoogleMyBusinessHistoricPersistence;

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
 * The persistence implementation for the google my business historic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @generated
 */
public class GoogleMyBusinessHistoricPersistenceImpl
	extends BasePersistenceImpl<GoogleMyBusinessHistoric>
	implements GoogleMyBusinessHistoricPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>GoogleMyBusinessHistoricUtil</code> to access the google my business historic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		GoogleMyBusinessHistoricImpl.class.getName();

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
	 * Returns all the google my business historics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the google my business historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @return the range of matching google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the google my business historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the google my business historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator,
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

		List<GoogleMyBusinessHistoric> list = null;

		if (useFinderCache) {
			list = (List<GoogleMyBusinessHistoric>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (GoogleMyBusinessHistoric googleMyBusinessHistoric : list) {
					if (!uuid.equals(googleMyBusinessHistoric.getUuid())) {
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

			sb.append(_SQL_SELECT_GOOGLEMYBUSINESSHISTORIC_WHERE);

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
				sb.append(GoogleMyBusinessHistoricModelImpl.ORDER_BY_JPQL);
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

				list = (List<GoogleMyBusinessHistoric>)QueryUtil.list(
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
	 * Returns the first google my business historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric findByUuid_First(
			String uuid,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws NoSuchGoogleMyBusinessHistoricException {

		GoogleMyBusinessHistoric googleMyBusinessHistoric = fetchByUuid_First(
			uuid, orderByComparator);

		if (googleMyBusinessHistoric != null) {
			return googleMyBusinessHistoric;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchGoogleMyBusinessHistoricException(sb.toString());
	}

	/**
	 * Returns the first google my business historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric fetchByUuid_First(
		String uuid,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		List<GoogleMyBusinessHistoric> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last google my business historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric findByUuid_Last(
			String uuid,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws NoSuchGoogleMyBusinessHistoricException {

		GoogleMyBusinessHistoric googleMyBusinessHistoric = fetchByUuid_Last(
			uuid, orderByComparator);

		if (googleMyBusinessHistoric != null) {
			return googleMyBusinessHistoric;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchGoogleMyBusinessHistoricException(sb.toString());
	}

	/**
	 * Returns the last google my business historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric fetchByUuid_Last(
		String uuid,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<GoogleMyBusinessHistoric> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the google my business historics before and after the current google my business historic in the ordered set where uuid = &#63;.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the current google my business historic
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a google my business historic with the primary key could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric[] findByUuid_PrevAndNext(
			long googleMyBusinessHistoricId, String uuid,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws NoSuchGoogleMyBusinessHistoricException {

		uuid = Objects.toString(uuid, "");

		GoogleMyBusinessHistoric googleMyBusinessHistoric = findByPrimaryKey(
			googleMyBusinessHistoricId);

		Session session = null;

		try {
			session = openSession();

			GoogleMyBusinessHistoric[] array =
				new GoogleMyBusinessHistoricImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, googleMyBusinessHistoric, uuid, orderByComparator,
				true);

			array[1] = googleMyBusinessHistoric;

			array[2] = getByUuid_PrevAndNext(
				session, googleMyBusinessHistoric, uuid, orderByComparator,
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

	protected GoogleMyBusinessHistoric getByUuid_PrevAndNext(
		Session session, GoogleMyBusinessHistoric googleMyBusinessHistoric,
		String uuid,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator,
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

		sb.append(_SQL_SELECT_GOOGLEMYBUSINESSHISTORIC_WHERE);

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
			sb.append(GoogleMyBusinessHistoricModelImpl.ORDER_BY_JPQL);
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
						googleMyBusinessHistoric)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<GoogleMyBusinessHistoric> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the google my business historics where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (GoogleMyBusinessHistoric googleMyBusinessHistoric :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(googleMyBusinessHistoric);
		}
	}

	/**
	 * Returns the number of google my business historics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching google my business historics
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_GOOGLEMYBUSINESSHISTORIC_WHERE);

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
		"googleMyBusinessHistoric.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(googleMyBusinessHistoric.uuid IS NULL OR googleMyBusinessHistoric.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the google my business historic where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchGoogleMyBusinessHistoricException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric findByUUID_G(String uuid, long groupId)
		throws NoSuchGoogleMyBusinessHistoricException {

		GoogleMyBusinessHistoric googleMyBusinessHistoric = fetchByUUID_G(
			uuid, groupId);

		if (googleMyBusinessHistoric == null) {
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

			throw new NoSuchGoogleMyBusinessHistoricException(sb.toString());
		}

		return googleMyBusinessHistoric;
	}

	/**
	 * Returns the google my business historic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the google my business historic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric fetchByUUID_G(
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

		if (result instanceof GoogleMyBusinessHistoric) {
			GoogleMyBusinessHistoric googleMyBusinessHistoric =
				(GoogleMyBusinessHistoric)result;

			if (!Objects.equals(uuid, googleMyBusinessHistoric.getUuid()) ||
				(groupId != googleMyBusinessHistoric.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_GOOGLEMYBUSINESSHISTORIC_WHERE);

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

				List<GoogleMyBusinessHistoric> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					GoogleMyBusinessHistoric googleMyBusinessHistoric =
						list.get(0);

					result = googleMyBusinessHistoric;

					cacheResult(googleMyBusinessHistoric);
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
			return (GoogleMyBusinessHistoric)result;
		}
	}

	/**
	 * Removes the google my business historic where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the google my business historic that was removed
	 */
	@Override
	public GoogleMyBusinessHistoric removeByUUID_G(String uuid, long groupId)
		throws NoSuchGoogleMyBusinessHistoricException {

		GoogleMyBusinessHistoric googleMyBusinessHistoric = findByUUID_G(
			uuid, groupId);

		return remove(googleMyBusinessHistoric);
	}

	/**
	 * Returns the number of google my business historics where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching google my business historics
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_GOOGLEMYBUSINESSHISTORIC_WHERE);

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
		"googleMyBusinessHistoric.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(googleMyBusinessHistoric.uuid IS NULL OR googleMyBusinessHistoric.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"googleMyBusinessHistoric.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the google my business historics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the google my business historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @return the range of matching google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the google my business historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the google my business historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator,
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

		List<GoogleMyBusinessHistoric> list = null;

		if (useFinderCache) {
			list = (List<GoogleMyBusinessHistoric>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (GoogleMyBusinessHistoric googleMyBusinessHistoric : list) {
					if (!uuid.equals(googleMyBusinessHistoric.getUuid()) ||
						(companyId !=
							googleMyBusinessHistoric.getCompanyId())) {

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

			sb.append(_SQL_SELECT_GOOGLEMYBUSINESSHISTORIC_WHERE);

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
				sb.append(GoogleMyBusinessHistoricModelImpl.ORDER_BY_JPQL);
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

				list = (List<GoogleMyBusinessHistoric>)QueryUtil.list(
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
	 * Returns the first google my business historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws NoSuchGoogleMyBusinessHistoricException {

		GoogleMyBusinessHistoric googleMyBusinessHistoric = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (googleMyBusinessHistoric != null) {
			return googleMyBusinessHistoric;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchGoogleMyBusinessHistoricException(sb.toString());
	}

	/**
	 * Returns the first google my business historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		List<GoogleMyBusinessHistoric> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last google my business historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws NoSuchGoogleMyBusinessHistoricException {

		GoogleMyBusinessHistoric googleMyBusinessHistoric = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (googleMyBusinessHistoric != null) {
			return googleMyBusinessHistoric;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchGoogleMyBusinessHistoricException(sb.toString());
	}

	/**
	 * Returns the last google my business historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<GoogleMyBusinessHistoric> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the google my business historics before and after the current google my business historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the current google my business historic
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a google my business historic with the primary key could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric[] findByUuid_C_PrevAndNext(
			long googleMyBusinessHistoricId, String uuid, long companyId,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws NoSuchGoogleMyBusinessHistoricException {

		uuid = Objects.toString(uuid, "");

		GoogleMyBusinessHistoric googleMyBusinessHistoric = findByPrimaryKey(
			googleMyBusinessHistoricId);

		Session session = null;

		try {
			session = openSession();

			GoogleMyBusinessHistoric[] array =
				new GoogleMyBusinessHistoricImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, googleMyBusinessHistoric, uuid, companyId,
				orderByComparator, true);

			array[1] = googleMyBusinessHistoric;

			array[2] = getByUuid_C_PrevAndNext(
				session, googleMyBusinessHistoric, uuid, companyId,
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

	protected GoogleMyBusinessHistoric getByUuid_C_PrevAndNext(
		Session session, GoogleMyBusinessHistoric googleMyBusinessHistoric,
		String uuid, long companyId,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator,
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

		sb.append(_SQL_SELECT_GOOGLEMYBUSINESSHISTORIC_WHERE);

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
			sb.append(GoogleMyBusinessHistoricModelImpl.ORDER_BY_JPQL);
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
						googleMyBusinessHistoric)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<GoogleMyBusinessHistoric> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the google my business historics where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (GoogleMyBusinessHistoric googleMyBusinessHistoric :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(googleMyBusinessHistoric);
		}
	}

	/**
	 * Returns the number of google my business historics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching google my business historics
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_GOOGLEMYBUSINESSHISTORIC_WHERE);

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
		"googleMyBusinessHistoric.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(googleMyBusinessHistoric.uuid IS NULL OR googleMyBusinessHistoric.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"googleMyBusinessHistoric.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the google my business historics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the google my business historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @return the range of matching google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the google my business historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the google my business historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator,
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

		List<GoogleMyBusinessHistoric> list = null;

		if (useFinderCache) {
			list = (List<GoogleMyBusinessHistoric>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (GoogleMyBusinessHistoric googleMyBusinessHistoric : list) {
					if (groupId != googleMyBusinessHistoric.getGroupId()) {
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

			sb.append(_SQL_SELECT_GOOGLEMYBUSINESSHISTORIC_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(GoogleMyBusinessHistoricModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<GoogleMyBusinessHistoric>)QueryUtil.list(
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
	 * Returns the first google my business historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric findByGroupId_First(
			long groupId,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws NoSuchGoogleMyBusinessHistoricException {

		GoogleMyBusinessHistoric googleMyBusinessHistoric =
			fetchByGroupId_First(groupId, orderByComparator);

		if (googleMyBusinessHistoric != null) {
			return googleMyBusinessHistoric;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchGoogleMyBusinessHistoricException(sb.toString());
	}

	/**
	 * Returns the first google my business historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric fetchByGroupId_First(
		long groupId,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		List<GoogleMyBusinessHistoric> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last google my business historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric findByGroupId_Last(
			long groupId,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws NoSuchGoogleMyBusinessHistoricException {

		GoogleMyBusinessHistoric googleMyBusinessHistoric = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (googleMyBusinessHistoric != null) {
			return googleMyBusinessHistoric;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchGoogleMyBusinessHistoricException(sb.toString());
	}

	/**
	 * Returns the last google my business historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric fetchByGroupId_Last(
		long groupId,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<GoogleMyBusinessHistoric> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the google my business historics before and after the current google my business historic in the ordered set where groupId = &#63;.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the current google my business historic
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a google my business historic with the primary key could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric[] findByGroupId_PrevAndNext(
			long googleMyBusinessHistoricId, long groupId,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws NoSuchGoogleMyBusinessHistoricException {

		GoogleMyBusinessHistoric googleMyBusinessHistoric = findByPrimaryKey(
			googleMyBusinessHistoricId);

		Session session = null;

		try {
			session = openSession();

			GoogleMyBusinessHistoric[] array =
				new GoogleMyBusinessHistoricImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, googleMyBusinessHistoric, groupId, orderByComparator,
				true);

			array[1] = googleMyBusinessHistoric;

			array[2] = getByGroupId_PrevAndNext(
				session, googleMyBusinessHistoric, groupId, orderByComparator,
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

	protected GoogleMyBusinessHistoric getByGroupId_PrevAndNext(
		Session session, GoogleMyBusinessHistoric googleMyBusinessHistoric,
		long groupId,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator,
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

		sb.append(_SQL_SELECT_GOOGLEMYBUSINESSHISTORIC_WHERE);

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
			sb.append(GoogleMyBusinessHistoricModelImpl.ORDER_BY_JPQL);
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
						googleMyBusinessHistoric)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<GoogleMyBusinessHistoric> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the google my business historics where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (GoogleMyBusinessHistoric googleMyBusinessHistoric :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(googleMyBusinessHistoric);
		}
	}

	/**
	 * Returns the number of google my business historics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching google my business historics
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_GOOGLEMYBUSINESSHISTORIC_WHERE);

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
		"googleMyBusinessHistoric.groupId = ?";

	public GoogleMyBusinessHistoricPersistenceImpl() {
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

		setModelClass(GoogleMyBusinessHistoric.class);
	}

	/**
	 * Caches the google my business historic in the entity cache if it is enabled.
	 *
	 * @param googleMyBusinessHistoric the google my business historic
	 */
	@Override
	public void cacheResult(GoogleMyBusinessHistoric googleMyBusinessHistoric) {
		entityCache.putResult(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricImpl.class,
			googleMyBusinessHistoric.getPrimaryKey(), googleMyBusinessHistoric);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				googleMyBusinessHistoric.getUuid(),
				googleMyBusinessHistoric.getGroupId()
			},
			googleMyBusinessHistoric);

		googleMyBusinessHistoric.resetOriginalValues();
	}

	/**
	 * Caches the google my business historics in the entity cache if it is enabled.
	 *
	 * @param googleMyBusinessHistorics the google my business historics
	 */
	@Override
	public void cacheResult(
		List<GoogleMyBusinessHistoric> googleMyBusinessHistorics) {

		for (GoogleMyBusinessHistoric googleMyBusinessHistoric :
				googleMyBusinessHistorics) {

			if (entityCache.getResult(
					GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
					GoogleMyBusinessHistoricImpl.class,
					googleMyBusinessHistoric.getPrimaryKey()) == null) {

				cacheResult(googleMyBusinessHistoric);
			}
			else {
				googleMyBusinessHistoric.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all google my business historics.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GoogleMyBusinessHistoricImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the google my business historic.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GoogleMyBusinessHistoric googleMyBusinessHistoric) {
		entityCache.removeResult(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricImpl.class,
			googleMyBusinessHistoric.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(GoogleMyBusinessHistoricModelImpl)googleMyBusinessHistoric, true);
	}

	@Override
	public void clearCache(
		List<GoogleMyBusinessHistoric> googleMyBusinessHistorics) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GoogleMyBusinessHistoric googleMyBusinessHistoric :
				googleMyBusinessHistorics) {

			entityCache.removeResult(
				GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
				GoogleMyBusinessHistoricImpl.class,
				googleMyBusinessHistoric.getPrimaryKey());

			clearUniqueFindersCache(
				(GoogleMyBusinessHistoricModelImpl)googleMyBusinessHistoric,
				true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
				GoogleMyBusinessHistoricImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		GoogleMyBusinessHistoricModelImpl googleMyBusinessHistoricModelImpl) {

		Object[] args = new Object[] {
			googleMyBusinessHistoricModelImpl.getUuid(),
			googleMyBusinessHistoricModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, googleMyBusinessHistoricModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		GoogleMyBusinessHistoricModelImpl googleMyBusinessHistoricModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				googleMyBusinessHistoricModelImpl.getUuid(),
				googleMyBusinessHistoricModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((googleMyBusinessHistoricModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				googleMyBusinessHistoricModelImpl.getOriginalUuid(),
				googleMyBusinessHistoricModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new google my business historic with the primary key. Does not add the google my business historic to the database.
	 *
	 * @param googleMyBusinessHistoricId the primary key for the new google my business historic
	 * @return the new google my business historic
	 */
	@Override
	public GoogleMyBusinessHistoric create(long googleMyBusinessHistoricId) {
		GoogleMyBusinessHistoric googleMyBusinessHistoric =
			new GoogleMyBusinessHistoricImpl();

		googleMyBusinessHistoric.setNew(true);
		googleMyBusinessHistoric.setPrimaryKey(googleMyBusinessHistoricId);

		String uuid = PortalUUIDUtil.generate();

		googleMyBusinessHistoric.setUuid(uuid);

		googleMyBusinessHistoric.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return googleMyBusinessHistoric;
	}

	/**
	 * Removes the google my business historic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the google my business historic
	 * @return the google my business historic that was removed
	 * @throws NoSuchGoogleMyBusinessHistoricException if a google my business historic with the primary key could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric remove(long googleMyBusinessHistoricId)
		throws NoSuchGoogleMyBusinessHistoricException {

		return remove((Serializable)googleMyBusinessHistoricId);
	}

	/**
	 * Removes the google my business historic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the google my business historic
	 * @return the google my business historic that was removed
	 * @throws NoSuchGoogleMyBusinessHistoricException if a google my business historic with the primary key could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric remove(Serializable primaryKey)
		throws NoSuchGoogleMyBusinessHistoricException {

		Session session = null;

		try {
			session = openSession();

			GoogleMyBusinessHistoric googleMyBusinessHistoric =
				(GoogleMyBusinessHistoric)session.get(
					GoogleMyBusinessHistoricImpl.class, primaryKey);

			if (googleMyBusinessHistoric == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGoogleMyBusinessHistoricException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(googleMyBusinessHistoric);
		}
		catch (NoSuchGoogleMyBusinessHistoricException noSuchEntityException) {
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
	protected GoogleMyBusinessHistoric removeImpl(
		GoogleMyBusinessHistoric googleMyBusinessHistoric) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(googleMyBusinessHistoric)) {
				googleMyBusinessHistoric =
					(GoogleMyBusinessHistoric)session.get(
						GoogleMyBusinessHistoricImpl.class,
						googleMyBusinessHistoric.getPrimaryKeyObj());
			}

			if (googleMyBusinessHistoric != null) {
				session.delete(googleMyBusinessHistoric);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (googleMyBusinessHistoric != null) {
			clearCache(googleMyBusinessHistoric);
		}

		return googleMyBusinessHistoric;
	}

	@Override
	public GoogleMyBusinessHistoric updateImpl(
		GoogleMyBusinessHistoric googleMyBusinessHistoric) {

		boolean isNew = googleMyBusinessHistoric.isNew();

		if (!(googleMyBusinessHistoric instanceof
				GoogleMyBusinessHistoricModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(googleMyBusinessHistoric.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					googleMyBusinessHistoric);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in googleMyBusinessHistoric proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom GoogleMyBusinessHistoric implementation " +
					googleMyBusinessHistoric.getClass());
		}

		GoogleMyBusinessHistoricModelImpl googleMyBusinessHistoricModelImpl =
			(GoogleMyBusinessHistoricModelImpl)googleMyBusinessHistoric;

		if (Validator.isNull(googleMyBusinessHistoric.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			googleMyBusinessHistoric.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (googleMyBusinessHistoric.getCreateDate() == null)) {
			if (serviceContext == null) {
				googleMyBusinessHistoric.setCreateDate(now);
			}
			else {
				googleMyBusinessHistoric.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!googleMyBusinessHistoricModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				googleMyBusinessHistoric.setModifiedDate(now);
			}
			else {
				googleMyBusinessHistoric.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (googleMyBusinessHistoric.isNew()) {
				session.save(googleMyBusinessHistoric);

				googleMyBusinessHistoric.setNew(false);
			}
			else {
				googleMyBusinessHistoric =
					(GoogleMyBusinessHistoric)session.merge(
						googleMyBusinessHistoric);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!GoogleMyBusinessHistoricModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				googleMyBusinessHistoricModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				googleMyBusinessHistoricModelImpl.getUuid(),
				googleMyBusinessHistoricModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {
				googleMyBusinessHistoricModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((googleMyBusinessHistoricModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					googleMyBusinessHistoricModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {
					googleMyBusinessHistoricModelImpl.getUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((googleMyBusinessHistoricModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					googleMyBusinessHistoricModelImpl.getOriginalUuid(),
					googleMyBusinessHistoricModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					googleMyBusinessHistoricModelImpl.getUuid(),
					googleMyBusinessHistoricModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((googleMyBusinessHistoricModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					googleMyBusinessHistoricModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {
					googleMyBusinessHistoricModelImpl.getGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}
		}

		entityCache.putResult(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricImpl.class,
			googleMyBusinessHistoric.getPrimaryKey(), googleMyBusinessHistoric,
			false);

		clearUniqueFindersCache(googleMyBusinessHistoricModelImpl, false);
		cacheUniqueFindersCache(googleMyBusinessHistoricModelImpl);

		googleMyBusinessHistoric.resetOriginalValues();

		return googleMyBusinessHistoric;
	}

	/**
	 * Returns the google my business historic with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the google my business historic
	 * @return the google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a google my business historic with the primary key could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGoogleMyBusinessHistoricException {

		GoogleMyBusinessHistoric googleMyBusinessHistoric = fetchByPrimaryKey(
			primaryKey);

		if (googleMyBusinessHistoric == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGoogleMyBusinessHistoricException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return googleMyBusinessHistoric;
	}

	/**
	 * Returns the google my business historic with the primary key or throws a <code>NoSuchGoogleMyBusinessHistoricException</code> if it could not be found.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the google my business historic
	 * @return the google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a google my business historic with the primary key could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric findByPrimaryKey(
			long googleMyBusinessHistoricId)
		throws NoSuchGoogleMyBusinessHistoricException {

		return findByPrimaryKey((Serializable)googleMyBusinessHistoricId);
	}

	/**
	 * Returns the google my business historic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the google my business historic
	 * @return the google my business historic, or <code>null</code> if a google my business historic with the primary key could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		GoogleMyBusinessHistoric googleMyBusinessHistoric =
			(GoogleMyBusinessHistoric)serializable;

		if (googleMyBusinessHistoric == null) {
			Session session = null;

			try {
				session = openSession();

				googleMyBusinessHistoric =
					(GoogleMyBusinessHistoric)session.get(
						GoogleMyBusinessHistoricImpl.class, primaryKey);

				if (googleMyBusinessHistoric != null) {
					cacheResult(googleMyBusinessHistoric);
				}
				else {
					entityCache.putResult(
						GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
						GoogleMyBusinessHistoricImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
					GoogleMyBusinessHistoricImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return googleMyBusinessHistoric;
	}

	/**
	 * Returns the google my business historic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the google my business historic
	 * @return the google my business historic, or <code>null</code> if a google my business historic with the primary key could not be found
	 */
	@Override
	public GoogleMyBusinessHistoric fetchByPrimaryKey(
		long googleMyBusinessHistoricId) {

		return fetchByPrimaryKey((Serializable)googleMyBusinessHistoricId);
	}

	@Override
	public Map<Serializable, GoogleMyBusinessHistoric> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, GoogleMyBusinessHistoric> map =
			new HashMap<Serializable, GoogleMyBusinessHistoric>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			GoogleMyBusinessHistoric googleMyBusinessHistoric =
				fetchByPrimaryKey(primaryKey);

			if (googleMyBusinessHistoric != null) {
				map.put(primaryKey, googleMyBusinessHistoric);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
				GoogleMyBusinessHistoricImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (GoogleMyBusinessHistoric)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_GOOGLEMYBUSINESSHISTORIC_WHERE_PKS_IN);

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

			for (GoogleMyBusinessHistoric googleMyBusinessHistoric :
					(List<GoogleMyBusinessHistoric>)query.list()) {

				map.put(
					googleMyBusinessHistoric.getPrimaryKeyObj(),
					googleMyBusinessHistoric);

				cacheResult(googleMyBusinessHistoric);

				uncachedPrimaryKeys.remove(
					googleMyBusinessHistoric.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
					GoogleMyBusinessHistoricImpl.class, primaryKey, nullModel);
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
	 * Returns all the google my business historics.
	 *
	 * @return the google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the google my business historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @return the range of google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the google my business historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findAll(
		int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the google my business historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of google my business historics
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findAll(
		int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator,
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

		List<GoogleMyBusinessHistoric> list = null;

		if (useFinderCache) {
			list = (List<GoogleMyBusinessHistoric>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_GOOGLEMYBUSINESSHISTORIC);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_GOOGLEMYBUSINESSHISTORIC;

				sql = sql.concat(
					GoogleMyBusinessHistoricModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<GoogleMyBusinessHistoric>)QueryUtil.list(
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
	 * Removes all the google my business historics from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GoogleMyBusinessHistoric googleMyBusinessHistoric : findAll()) {
			remove(googleMyBusinessHistoric);
		}
	}

	/**
	 * Returns the number of google my business historics.
	 *
	 * @return the number of google my business historics
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
					_SQL_COUNT_GOOGLEMYBUSINESSHISTORIC);

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
		return GoogleMyBusinessHistoricModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the google my business historic persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED,
			GoogleMyBusinessHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED,
			GoogleMyBusinessHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED,
			GoogleMyBusinessHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED,
			GoogleMyBusinessHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			GoogleMyBusinessHistoricModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED,
			GoogleMyBusinessHistoricImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			GoogleMyBusinessHistoricModelImpl.UUID_COLUMN_BITMASK |
			GoogleMyBusinessHistoricModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED,
			GoogleMyBusinessHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED,
			GoogleMyBusinessHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			GoogleMyBusinessHistoricModelImpl.UUID_COLUMN_BITMASK |
			GoogleMyBusinessHistoricModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED,
			GoogleMyBusinessHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED,
			GoogleMyBusinessHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			GoogleMyBusinessHistoricModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			GoogleMyBusinessHistoricModelImpl.ENTITY_CACHE_ENABLED,
			GoogleMyBusinessHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(GoogleMyBusinessHistoricImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_GOOGLEMYBUSINESSHISTORIC =
		"SELECT googleMyBusinessHistoric FROM GoogleMyBusinessHistoric googleMyBusinessHistoric";

	private static final String
		_SQL_SELECT_GOOGLEMYBUSINESSHISTORIC_WHERE_PKS_IN =
			"SELECT googleMyBusinessHistoric FROM GoogleMyBusinessHistoric googleMyBusinessHistoric WHERE googleMyBusinessHistoricId IN (";

	private static final String _SQL_SELECT_GOOGLEMYBUSINESSHISTORIC_WHERE =
		"SELECT googleMyBusinessHistoric FROM GoogleMyBusinessHistoric googleMyBusinessHistoric WHERE ";

	private static final String _SQL_COUNT_GOOGLEMYBUSINESSHISTORIC =
		"SELECT COUNT(googleMyBusinessHistoric) FROM GoogleMyBusinessHistoric googleMyBusinessHistoric";

	private static final String _SQL_COUNT_GOOGLEMYBUSINESSHISTORIC_WHERE =
		"SELECT COUNT(googleMyBusinessHistoric) FROM GoogleMyBusinessHistoric googleMyBusinessHistoric WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"googleMyBusinessHistoric.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No GoogleMyBusinessHistoric exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No GoogleMyBusinessHistoric exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		GoogleMyBusinessHistoricPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}