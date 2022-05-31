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

package eu.strasbourg.service.oidc.service.persistence.impl;

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

import eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException;
import eu.strasbourg.service.oidc.model.AnonymisationHistoric;
import eu.strasbourg.service.oidc.model.impl.AnonymisationHistoricImpl;
import eu.strasbourg.service.oidc.model.impl.AnonymisationHistoricModelImpl;
import eu.strasbourg.service.oidc.service.persistence.AnonymisationHistoricPersistence;

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
 * The persistence implementation for the anonymisation historic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AnonymisationHistoricPersistenceImpl
	extends BasePersistenceImpl<AnonymisationHistoric>
	implements AnonymisationHistoricPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AnonymisationHistoricUtil</code> to access the anonymisation historic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AnonymisationHistoricImpl.class.getName();

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
	 * Returns all the anonymisation historics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the anonymisation historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @return the range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
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

		List<AnonymisationHistoric> list = null;

		if (useFinderCache) {
			list = (List<AnonymisationHistoric>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AnonymisationHistoric anonymisationHistoric : list) {
					if (!uuid.equals(anonymisationHistoric.getUuid())) {
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

			sb.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

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
				sb.append(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
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

				list = (List<AnonymisationHistoric>)QueryUtil.list(
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
	 * Returns the first anonymisation historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric findByUuid_First(
			String uuid,
			OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {

		AnonymisationHistoric anonymisationHistoric = fetchByUuid_First(
			uuid, orderByComparator);

		if (anonymisationHistoric != null) {
			return anonymisationHistoric;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchAnonymisationHistoricException(sb.toString());
	}

	/**
	 * Returns the first anonymisation historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByUuid_First(
		String uuid,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {

		List<AnonymisationHistoric> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last anonymisation historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric findByUuid_Last(
			String uuid,
			OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {

		AnonymisationHistoric anonymisationHistoric = fetchByUuid_Last(
			uuid, orderByComparator);

		if (anonymisationHistoric != null) {
			return anonymisationHistoric;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchAnonymisationHistoricException(sb.toString());
	}

	/**
	 * Returns the last anonymisation historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByUuid_Last(
		String uuid,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<AnonymisationHistoric> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the anonymisation historics before and after the current anonymisation historic in the ordered set where uuid = &#63;.
	 *
	 * @param anonymisationHistoricId the primary key of the current anonymisation historic
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	 */
	@Override
	public AnonymisationHistoric[] findByUuid_PrevAndNext(
			long anonymisationHistoricId, String uuid,
			OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {

		uuid = Objects.toString(uuid, "");

		AnonymisationHistoric anonymisationHistoric = findByPrimaryKey(
			anonymisationHistoricId);

		Session session = null;

		try {
			session = openSession();

			AnonymisationHistoric[] array = new AnonymisationHistoricImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, anonymisationHistoric, uuid, orderByComparator, true);

			array[1] = anonymisationHistoric;

			array[2] = getByUuid_PrevAndNext(
				session, anonymisationHistoric, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AnonymisationHistoric getByUuid_PrevAndNext(
		Session session, AnonymisationHistoric anonymisationHistoric,
		String uuid, OrderByComparator<AnonymisationHistoric> orderByComparator,
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

		sb.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

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
			sb.append(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
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
						anonymisationHistoric)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AnonymisationHistoric> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the anonymisation historics where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (AnonymisationHistoric anonymisationHistoric :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(anonymisationHistoric);
		}
	}

	/**
	 * Returns the number of anonymisation historics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching anonymisation historics
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ANONYMISATIONHISTORIC_WHERE);

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
		"anonymisationHistoric.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(anonymisationHistoric.uuid IS NULL OR anonymisationHistoric.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the anonymisation historic where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchAnonymisationHistoricException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric findByUUID_G(String uuid, long groupId)
		throws NoSuchAnonymisationHistoricException {

		AnonymisationHistoric anonymisationHistoric = fetchByUUID_G(
			uuid, groupId);

		if (anonymisationHistoric == null) {
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

			throw new NoSuchAnonymisationHistoricException(sb.toString());
		}

		return anonymisationHistoric;
	}

	/**
	 * Returns the anonymisation historic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the anonymisation historic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByUUID_G(
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

		if (result instanceof AnonymisationHistoric) {
			AnonymisationHistoric anonymisationHistoric =
				(AnonymisationHistoric)result;

			if (!Objects.equals(uuid, anonymisationHistoric.getUuid()) ||
				(groupId != anonymisationHistoric.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

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

				List<AnonymisationHistoric> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					AnonymisationHistoric anonymisationHistoric = list.get(0);

					result = anonymisationHistoric;

					cacheResult(anonymisationHistoric);
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
			return (AnonymisationHistoric)result;
		}
	}

	/**
	 * Removes the anonymisation historic where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the anonymisation historic that was removed
	 */
	@Override
	public AnonymisationHistoric removeByUUID_G(String uuid, long groupId)
		throws NoSuchAnonymisationHistoricException {

		AnonymisationHistoric anonymisationHistoric = findByUUID_G(
			uuid, groupId);

		return remove(anonymisationHistoric);
	}

	/**
	 * Returns the number of anonymisation historics where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching anonymisation historics
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ANONYMISATIONHISTORIC_WHERE);

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
		"anonymisationHistoric.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(anonymisationHistoric.uuid IS NULL OR anonymisationHistoric.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"anonymisationHistoric.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @return the range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
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

		List<AnonymisationHistoric> list = null;

		if (useFinderCache) {
			list = (List<AnonymisationHistoric>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AnonymisationHistoric anonymisationHistoric : list) {
					if (!uuid.equals(anonymisationHistoric.getUuid()) ||
						(companyId != anonymisationHistoric.getCompanyId())) {

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

			sb.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

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
				sb.append(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
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

				list = (List<AnonymisationHistoric>)QueryUtil.list(
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
	 * Returns the first anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {

		AnonymisationHistoric anonymisationHistoric = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (anonymisationHistoric != null) {
			return anonymisationHistoric;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchAnonymisationHistoricException(sb.toString());
	}

	/**
	 * Returns the first anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {

		List<AnonymisationHistoric> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {

		AnonymisationHistoric anonymisationHistoric = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (anonymisationHistoric != null) {
			return anonymisationHistoric;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchAnonymisationHistoricException(sb.toString());
	}

	/**
	 * Returns the last anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<AnonymisationHistoric> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the anonymisation historics before and after the current anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param anonymisationHistoricId the primary key of the current anonymisation historic
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	 */
	@Override
	public AnonymisationHistoric[] findByUuid_C_PrevAndNext(
			long anonymisationHistoricId, String uuid, long companyId,
			OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {

		uuid = Objects.toString(uuid, "");

		AnonymisationHistoric anonymisationHistoric = findByPrimaryKey(
			anonymisationHistoricId);

		Session session = null;

		try {
			session = openSession();

			AnonymisationHistoric[] array = new AnonymisationHistoricImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, anonymisationHistoric, uuid, companyId,
				orderByComparator, true);

			array[1] = anonymisationHistoric;

			array[2] = getByUuid_C_PrevAndNext(
				session, anonymisationHistoric, uuid, companyId,
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

	protected AnonymisationHistoric getByUuid_C_PrevAndNext(
		Session session, AnonymisationHistoric anonymisationHistoric,
		String uuid, long companyId,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
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

		sb.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

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
			sb.append(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
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
						anonymisationHistoric)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AnonymisationHistoric> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the anonymisation historics where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (AnonymisationHistoric anonymisationHistoric :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(anonymisationHistoric);
		}
	}

	/**
	 * Returns the number of anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching anonymisation historics
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ANONYMISATIONHISTORIC_WHERE);

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
		"anonymisationHistoric.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(anonymisationHistoric.uuid IS NULL OR anonymisationHistoric.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"anonymisationHistoric.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the anonymisation historics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the anonymisation historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @return the range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
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

		List<AnonymisationHistoric> list = null;

		if (useFinderCache) {
			list = (List<AnonymisationHistoric>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AnonymisationHistoric anonymisationHistoric : list) {
					if (groupId != anonymisationHistoric.getGroupId()) {
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

			sb.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<AnonymisationHistoric>)QueryUtil.list(
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
	 * Returns the first anonymisation historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric findByGroupId_First(
			long groupId,
			OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {

		AnonymisationHistoric anonymisationHistoric = fetchByGroupId_First(
			groupId, orderByComparator);

		if (anonymisationHistoric != null) {
			return anonymisationHistoric;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchAnonymisationHistoricException(sb.toString());
	}

	/**
	 * Returns the first anonymisation historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByGroupId_First(
		long groupId,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {

		List<AnonymisationHistoric> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last anonymisation historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric findByGroupId_Last(
			long groupId,
			OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {

		AnonymisationHistoric anonymisationHistoric = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (anonymisationHistoric != null) {
			return anonymisationHistoric;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchAnonymisationHistoricException(sb.toString());
	}

	/**
	 * Returns the last anonymisation historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByGroupId_Last(
		long groupId,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<AnonymisationHistoric> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the anonymisation historics before and after the current anonymisation historic in the ordered set where groupId = &#63;.
	 *
	 * @param anonymisationHistoricId the primary key of the current anonymisation historic
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	 */
	@Override
	public AnonymisationHistoric[] findByGroupId_PrevAndNext(
			long anonymisationHistoricId, long groupId,
			OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {

		AnonymisationHistoric anonymisationHistoric = findByPrimaryKey(
			anonymisationHistoricId);

		Session session = null;

		try {
			session = openSession();

			AnonymisationHistoric[] array = new AnonymisationHistoricImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, anonymisationHistoric, groupId, orderByComparator,
				true);

			array[1] = anonymisationHistoric;

			array[2] = getByGroupId_PrevAndNext(
				session, anonymisationHistoric, groupId, orderByComparator,
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

	protected AnonymisationHistoric getByGroupId_PrevAndNext(
		Session session, AnonymisationHistoric anonymisationHistoric,
		long groupId,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
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

		sb.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

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
			sb.append(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
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
						anonymisationHistoric)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AnonymisationHistoric> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the anonymisation historics where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (AnonymisationHistoric anonymisationHistoric :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(anonymisationHistoric);
		}
	}

	/**
	 * Returns the number of anonymisation historics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching anonymisation historics
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ANONYMISATIONHISTORIC_WHERE);

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
		"anonymisationHistoric.groupId = ?";

	public AnonymisationHistoricPersistenceImpl() {
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

		setModelClass(AnonymisationHistoric.class);
	}

	/**
	 * Caches the anonymisation historic in the entity cache if it is enabled.
	 *
	 * @param anonymisationHistoric the anonymisation historic
	 */
	@Override
	public void cacheResult(AnonymisationHistoric anonymisationHistoric) {
		entityCache.putResult(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			anonymisationHistoric.getPrimaryKey(), anonymisationHistoric);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				anonymisationHistoric.getUuid(),
				anonymisationHistoric.getGroupId()
			},
			anonymisationHistoric);

		anonymisationHistoric.resetOriginalValues();
	}

	/**
	 * Caches the anonymisation historics in the entity cache if it is enabled.
	 *
	 * @param anonymisationHistorics the anonymisation historics
	 */
	@Override
	public void cacheResult(
		List<AnonymisationHistoric> anonymisationHistorics) {

		for (AnonymisationHistoric anonymisationHistoric :
				anonymisationHistorics) {

			if (entityCache.getResult(
					AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
					AnonymisationHistoricImpl.class,
					anonymisationHistoric.getPrimaryKey()) == null) {

				cacheResult(anonymisationHistoric);
			}
			else {
				anonymisationHistoric.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all anonymisation historics.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AnonymisationHistoricImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the anonymisation historic.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AnonymisationHistoric anonymisationHistoric) {
		entityCache.removeResult(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			anonymisationHistoric.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(AnonymisationHistoricModelImpl)anonymisationHistoric, true);
	}

	@Override
	public void clearCache(List<AnonymisationHistoric> anonymisationHistorics) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AnonymisationHistoric anonymisationHistoric :
				anonymisationHistorics) {

			entityCache.removeResult(
				AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
				AnonymisationHistoricImpl.class,
				anonymisationHistoric.getPrimaryKey());

			clearUniqueFindersCache(
				(AnonymisationHistoricModelImpl)anonymisationHistoric, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
				AnonymisationHistoricImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		AnonymisationHistoricModelImpl anonymisationHistoricModelImpl) {

		Object[] args = new Object[] {
			anonymisationHistoricModelImpl.getUuid(),
			anonymisationHistoricModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, anonymisationHistoricModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		AnonymisationHistoricModelImpl anonymisationHistoricModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				anonymisationHistoricModelImpl.getUuid(),
				anonymisationHistoricModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((anonymisationHistoricModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				anonymisationHistoricModelImpl.getOriginalUuid(),
				anonymisationHistoricModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new anonymisation historic with the primary key. Does not add the anonymisation historic to the database.
	 *
	 * @param anonymisationHistoricId the primary key for the new anonymisation historic
	 * @return the new anonymisation historic
	 */
	@Override
	public AnonymisationHistoric create(long anonymisationHistoricId) {
		AnonymisationHistoric anonymisationHistoric =
			new AnonymisationHistoricImpl();

		anonymisationHistoric.setNew(true);
		anonymisationHistoric.setPrimaryKey(anonymisationHistoricId);

		String uuid = PortalUUIDUtil.generate();

		anonymisationHistoric.setUuid(uuid);

		anonymisationHistoric.setCompanyId(CompanyThreadLocal.getCompanyId());

		return anonymisationHistoric;
	}

	/**
	 * Removes the anonymisation historic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param anonymisationHistoricId the primary key of the anonymisation historic
	 * @return the anonymisation historic that was removed
	 * @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	 */
	@Override
	public AnonymisationHistoric remove(long anonymisationHistoricId)
		throws NoSuchAnonymisationHistoricException {

		return remove((Serializable)anonymisationHistoricId);
	}

	/**
	 * Removes the anonymisation historic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the anonymisation historic
	 * @return the anonymisation historic that was removed
	 * @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	 */
	@Override
	public AnonymisationHistoric remove(Serializable primaryKey)
		throws NoSuchAnonymisationHistoricException {

		Session session = null;

		try {
			session = openSession();

			AnonymisationHistoric anonymisationHistoric =
				(AnonymisationHistoric)session.get(
					AnonymisationHistoricImpl.class, primaryKey);

			if (anonymisationHistoric == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAnonymisationHistoricException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(anonymisationHistoric);
		}
		catch (NoSuchAnonymisationHistoricException noSuchEntityException) {
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
	protected AnonymisationHistoric removeImpl(
		AnonymisationHistoric anonymisationHistoric) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(anonymisationHistoric)) {
				anonymisationHistoric = (AnonymisationHistoric)session.get(
					AnonymisationHistoricImpl.class,
					anonymisationHistoric.getPrimaryKeyObj());
			}

			if (anonymisationHistoric != null) {
				session.delete(anonymisationHistoric);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (anonymisationHistoric != null) {
			clearCache(anonymisationHistoric);
		}

		return anonymisationHistoric;
	}

	@Override
	public AnonymisationHistoric updateImpl(
		AnonymisationHistoric anonymisationHistoric) {

		boolean isNew = anonymisationHistoric.isNew();

		if (!(anonymisationHistoric instanceof
				AnonymisationHistoricModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(anonymisationHistoric.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					anonymisationHistoric);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in anonymisationHistoric proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AnonymisationHistoric implementation " +
					anonymisationHistoric.getClass());
		}

		AnonymisationHistoricModelImpl anonymisationHistoricModelImpl =
			(AnonymisationHistoricModelImpl)anonymisationHistoric;

		if (Validator.isNull(anonymisationHistoric.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			anonymisationHistoric.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (anonymisationHistoric.getCreateDate() == null)) {
			if (serviceContext == null) {
				anonymisationHistoric.setCreateDate(now);
			}
			else {
				anonymisationHistoric.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!anonymisationHistoricModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				anonymisationHistoric.setModifiedDate(now);
			}
			else {
				anonymisationHistoric.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (anonymisationHistoric.isNew()) {
				session.save(anonymisationHistoric);

				anonymisationHistoric.setNew(false);
			}
			else {
				anonymisationHistoric = (AnonymisationHistoric)session.merge(
					anonymisationHistoric);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AnonymisationHistoricModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				anonymisationHistoricModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				anonymisationHistoricModelImpl.getUuid(),
				anonymisationHistoricModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {anonymisationHistoricModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((anonymisationHistoricModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					anonymisationHistoricModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {anonymisationHistoricModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((anonymisationHistoricModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					anonymisationHistoricModelImpl.getOriginalUuid(),
					anonymisationHistoricModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					anonymisationHistoricModelImpl.getUuid(),
					anonymisationHistoricModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((anonymisationHistoricModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					anonymisationHistoricModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {
					anonymisationHistoricModelImpl.getGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}
		}

		entityCache.putResult(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			anonymisationHistoric.getPrimaryKey(), anonymisationHistoric,
			false);

		clearUniqueFindersCache(anonymisationHistoricModelImpl, false);
		cacheUniqueFindersCache(anonymisationHistoricModelImpl);

		anonymisationHistoric.resetOriginalValues();

		return anonymisationHistoric;
	}

	/**
	 * Returns the anonymisation historic with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the anonymisation historic
	 * @return the anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	 */
	@Override
	public AnonymisationHistoric findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAnonymisationHistoricException {

		AnonymisationHistoric anonymisationHistoric = fetchByPrimaryKey(
			primaryKey);

		if (anonymisationHistoric == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAnonymisationHistoricException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return anonymisationHistoric;
	}

	/**
	 * Returns the anonymisation historic with the primary key or throws a <code>NoSuchAnonymisationHistoricException</code> if it could not be found.
	 *
	 * @param anonymisationHistoricId the primary key of the anonymisation historic
	 * @return the anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	 */
	@Override
	public AnonymisationHistoric findByPrimaryKey(long anonymisationHistoricId)
		throws NoSuchAnonymisationHistoricException {

		return findByPrimaryKey((Serializable)anonymisationHistoricId);
	}

	/**
	 * Returns the anonymisation historic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the anonymisation historic
	 * @return the anonymisation historic, or <code>null</code> if a anonymisation historic with the primary key could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AnonymisationHistoric anonymisationHistoric =
			(AnonymisationHistoric)serializable;

		if (anonymisationHistoric == null) {
			Session session = null;

			try {
				session = openSession();

				anonymisationHistoric = (AnonymisationHistoric)session.get(
					AnonymisationHistoricImpl.class, primaryKey);

				if (anonymisationHistoric != null) {
					cacheResult(anonymisationHistoric);
				}
				else {
					entityCache.putResult(
						AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
						AnonymisationHistoricImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
					AnonymisationHistoricImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return anonymisationHistoric;
	}

	/**
	 * Returns the anonymisation historic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param anonymisationHistoricId the primary key of the anonymisation historic
	 * @return the anonymisation historic, or <code>null</code> if a anonymisation historic with the primary key could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByPrimaryKey(
		long anonymisationHistoricId) {

		return fetchByPrimaryKey((Serializable)anonymisationHistoricId);
	}

	@Override
	public Map<Serializable, AnonymisationHistoric> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AnonymisationHistoric> map =
			new HashMap<Serializable, AnonymisationHistoric>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AnonymisationHistoric anonymisationHistoric = fetchByPrimaryKey(
				primaryKey);

			if (anonymisationHistoric != null) {
				map.put(primaryKey, anonymisationHistoric);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
				AnonymisationHistoricImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AnonymisationHistoric)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE_PKS_IN);

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

			for (AnonymisationHistoric anonymisationHistoric :
					(List<AnonymisationHistoric>)query.list()) {

				map.put(
					anonymisationHistoric.getPrimaryKeyObj(),
					anonymisationHistoric);

				cacheResult(anonymisationHistoric);

				uncachedPrimaryKeys.remove(
					anonymisationHistoric.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
					AnonymisationHistoricImpl.class, primaryKey, nullModel);
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
	 * Returns all the anonymisation historics.
	 *
	 * @return the anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the anonymisation historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @return the range of anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findAll(
		int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findAll(
		int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
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

		List<AnonymisationHistoric> list = null;

		if (useFinderCache) {
			list = (List<AnonymisationHistoric>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ANONYMISATIONHISTORIC);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ANONYMISATIONHISTORIC;

				sql = sql.concat(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AnonymisationHistoric>)QueryUtil.list(
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
	 * Removes all the anonymisation historics from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AnonymisationHistoric anonymisationHistoric : findAll()) {
			remove(anonymisationHistoric);
		}
	}

	/**
	 * Returns the number of anonymisation historics.
	 *
	 * @return the number of anonymisation historics
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
					_SQL_COUNT_ANONYMISATIONHISTORIC);

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
		return AnonymisationHistoricModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the anonymisation historic persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			AnonymisationHistoricModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			AnonymisationHistoricModelImpl.UUID_COLUMN_BITMASK |
			AnonymisationHistoricModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			AnonymisationHistoricModelImpl.UUID_COLUMN_BITMASK |
			AnonymisationHistoricModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			AnonymisationHistoricModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(AnonymisationHistoricImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ANONYMISATIONHISTORIC =
		"SELECT anonymisationHistoric FROM AnonymisationHistoric anonymisationHistoric";

	private static final String _SQL_SELECT_ANONYMISATIONHISTORIC_WHERE_PKS_IN =
		"SELECT anonymisationHistoric FROM AnonymisationHistoric anonymisationHistoric WHERE anonymisationHistoricId IN (";

	private static final String _SQL_SELECT_ANONYMISATIONHISTORIC_WHERE =
		"SELECT anonymisationHistoric FROM AnonymisationHistoric anonymisationHistoric WHERE ";

	private static final String _SQL_COUNT_ANONYMISATIONHISTORIC =
		"SELECT COUNT(anonymisationHistoric) FROM AnonymisationHistoric anonymisationHistoric";

	private static final String _SQL_COUNT_ANONYMISATIONHISTORIC_WHERE =
		"SELECT COUNT(anonymisationHistoric) FROM AnonymisationHistoric anonymisationHistoric WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"anonymisationHistoric.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AnonymisationHistoric exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AnonymisationHistoric exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AnonymisationHistoricPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}