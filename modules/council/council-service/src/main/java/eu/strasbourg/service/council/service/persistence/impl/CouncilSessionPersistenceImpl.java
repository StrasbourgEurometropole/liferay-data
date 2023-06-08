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

package eu.strasbourg.service.council.service.persistence.impl;

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

import eu.strasbourg.service.council.exception.NoSuchCouncilSessionException;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.impl.CouncilSessionImpl;
import eu.strasbourg.service.council.model.impl.CouncilSessionModelImpl;
import eu.strasbourg.service.council.service.persistence.CouncilSessionPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

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
 * The persistence implementation for the council session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CouncilSessionPersistenceImpl
	extends BasePersistenceImpl<CouncilSession>
	implements CouncilSessionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CouncilSessionUtil</code> to access the council session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CouncilSessionImpl.class.getName();

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
	 * Returns all the council sessions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching council sessions
	 */
	@Override
	public List<CouncilSession> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the council sessions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @return the range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the council sessions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the council sessions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator,
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

		List<CouncilSession> list = null;

		if (useFinderCache) {
			list = (List<CouncilSession>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CouncilSession councilSession : list) {
					if (!uuid.equals(councilSession.getUuid())) {
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

			sb.append(_SQL_SELECT_COUNCILSESSION_WHERE);

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
				sb.append(CouncilSessionModelImpl.ORDER_BY_JPQL);
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

				list = (List<CouncilSession>)QueryUtil.list(
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
	 * Returns the first council session in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching council session
	 * @throws NoSuchCouncilSessionException if a matching council session could not be found
	 */
	@Override
	public CouncilSession findByUuid_First(
			String uuid, OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = fetchByUuid_First(
			uuid, orderByComparator);

		if (councilSession != null) {
			return councilSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchCouncilSessionException(sb.toString());
	}

	/**
	 * Returns the first council session in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching council session, or <code>null</code> if a matching council session could not be found
	 */
	@Override
	public CouncilSession fetchByUuid_First(
		String uuid, OrderByComparator<CouncilSession> orderByComparator) {

		List<CouncilSession> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last council session in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching council session
	 * @throws NoSuchCouncilSessionException if a matching council session could not be found
	 */
	@Override
	public CouncilSession findByUuid_Last(
			String uuid, OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = fetchByUuid_Last(
			uuid, orderByComparator);

		if (councilSession != null) {
			return councilSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchCouncilSessionException(sb.toString());
	}

	/**
	 * Returns the last council session in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching council session, or <code>null</code> if a matching council session could not be found
	 */
	@Override
	public CouncilSession fetchByUuid_Last(
		String uuid, OrderByComparator<CouncilSession> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CouncilSession> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the council sessions before and after the current council session in the ordered set where uuid = &#63;.
	 *
	 * @param councilSessionId the primary key of the current council session
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next council session
	 * @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	 */
	@Override
	public CouncilSession[] findByUuid_PrevAndNext(
			long councilSessionId, String uuid,
			OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		uuid = Objects.toString(uuid, "");

		CouncilSession councilSession = findByPrimaryKey(councilSessionId);

		Session session = null;

		try {
			session = openSession();

			CouncilSession[] array = new CouncilSessionImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, councilSession, uuid, orderByComparator, true);

			array[1] = councilSession;

			array[2] = getByUuid_PrevAndNext(
				session, councilSession, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CouncilSession getByUuid_PrevAndNext(
		Session session, CouncilSession councilSession, String uuid,
		OrderByComparator<CouncilSession> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COUNCILSESSION_WHERE);

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
			sb.append(CouncilSessionModelImpl.ORDER_BY_JPQL);
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
						councilSession)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CouncilSession> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the council sessions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CouncilSession councilSession :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(councilSession);
		}
	}

	/**
	 * Returns the number of council sessions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching council sessions
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COUNCILSESSION_WHERE);

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
		"councilSession.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(councilSession.uuid IS NULL OR councilSession.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the council session where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCouncilSessionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching council session
	 * @throws NoSuchCouncilSessionException if a matching council session could not be found
	 */
	@Override
	public CouncilSession findByUUID_G(String uuid, long groupId)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = fetchByUUID_G(uuid, groupId);

		if (councilSession == null) {
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

			throw new NoSuchCouncilSessionException(sb.toString());
		}

		return councilSession;
	}

	/**
	 * Returns the council session where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching council session, or <code>null</code> if a matching council session could not be found
	 */
	@Override
	public CouncilSession fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the council session where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching council session, or <code>null</code> if a matching council session could not be found
	 */
	@Override
	public CouncilSession fetchByUUID_G(
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

		if (result instanceof CouncilSession) {
			CouncilSession councilSession = (CouncilSession)result;

			if (!Objects.equals(uuid, councilSession.getUuid()) ||
				(groupId != councilSession.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_COUNCILSESSION_WHERE);

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

				List<CouncilSession> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					CouncilSession councilSession = list.get(0);

					result = councilSession;

					cacheResult(councilSession);
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
			return (CouncilSession)result;
		}
	}

	/**
	 * Removes the council session where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the council session that was removed
	 */
	@Override
	public CouncilSession removeByUUID_G(String uuid, long groupId)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = findByUUID_G(uuid, groupId);

		return remove(councilSession);
	}

	/**
	 * Returns the number of council sessions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching council sessions
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COUNCILSESSION_WHERE);

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
		"councilSession.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(councilSession.uuid IS NULL OR councilSession.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"councilSession.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the council sessions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching council sessions
	 */
	@Override
	public List<CouncilSession> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the council sessions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @return the range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the council sessions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the council sessions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator,
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

		List<CouncilSession> list = null;

		if (useFinderCache) {
			list = (List<CouncilSession>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CouncilSession councilSession : list) {
					if (!uuid.equals(councilSession.getUuid()) ||
						(companyId != councilSession.getCompanyId())) {

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

			sb.append(_SQL_SELECT_COUNCILSESSION_WHERE);

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
				sb.append(CouncilSessionModelImpl.ORDER_BY_JPQL);
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

				list = (List<CouncilSession>)QueryUtil.list(
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
	 * Returns the first council session in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching council session
	 * @throws NoSuchCouncilSessionException if a matching council session could not be found
	 */
	@Override
	public CouncilSession findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (councilSession != null) {
			return councilSession;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchCouncilSessionException(sb.toString());
	}

	/**
	 * Returns the first council session in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching council session, or <code>null</code> if a matching council session could not be found
	 */
	@Override
	public CouncilSession fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CouncilSession> orderByComparator) {

		List<CouncilSession> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last council session in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching council session
	 * @throws NoSuchCouncilSessionException if a matching council session could not be found
	 */
	@Override
	public CouncilSession findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (councilSession != null) {
			return councilSession;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchCouncilSessionException(sb.toString());
	}

	/**
	 * Returns the last council session in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching council session, or <code>null</code> if a matching council session could not be found
	 */
	@Override
	public CouncilSession fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CouncilSession> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CouncilSession> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the council sessions before and after the current council session in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param councilSessionId the primary key of the current council session
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next council session
	 * @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	 */
	@Override
	public CouncilSession[] findByUuid_C_PrevAndNext(
			long councilSessionId, String uuid, long companyId,
			OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		uuid = Objects.toString(uuid, "");

		CouncilSession councilSession = findByPrimaryKey(councilSessionId);

		Session session = null;

		try {
			session = openSession();

			CouncilSession[] array = new CouncilSessionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, councilSession, uuid, companyId, orderByComparator,
				true);

			array[1] = councilSession;

			array[2] = getByUuid_C_PrevAndNext(
				session, councilSession, uuid, companyId, orderByComparator,
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

	protected CouncilSession getByUuid_C_PrevAndNext(
		Session session, CouncilSession councilSession, String uuid,
		long companyId, OrderByComparator<CouncilSession> orderByComparator,
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

		sb.append(_SQL_SELECT_COUNCILSESSION_WHERE);

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
			sb.append(CouncilSessionModelImpl.ORDER_BY_JPQL);
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
						councilSession)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CouncilSession> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the council sessions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CouncilSession councilSession :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(councilSession);
		}
	}

	/**
	 * Returns the number of council sessions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching council sessions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COUNCILSESSION_WHERE);

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
		"councilSession.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(councilSession.uuid IS NULL OR councilSession.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"councilSession.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByTitle;
	private FinderPath _finderPathWithoutPaginationFindByTitle;
	private FinderPath _finderPathCountByTitle;

	/**
	 * Returns all the council sessions where title = &#63;.
	 *
	 * @param title the title
	 * @return the matching council sessions
	 */
	@Override
	public List<CouncilSession> findByTitle(String title) {
		return findByTitle(title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the council sessions where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @return the range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByTitle(String title, int start, int end) {
		return findByTitle(title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the council sessions where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByTitle(
		String title, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator) {

		return findByTitle(title, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the council sessions where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByTitle(
		String title, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator,
		boolean useFinderCache) {

		title = Objects.toString(title, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByTitle;
				finderArgs = new Object[] {title};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByTitle;
			finderArgs = new Object[] {title, start, end, orderByComparator};
		}

		List<CouncilSession> list = null;

		if (useFinderCache) {
			list = (List<CouncilSession>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CouncilSession councilSession : list) {
					if (!title.equals(councilSession.getTitle())) {
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

			sb.append(_SQL_SELECT_COUNCILSESSION_WHERE);

			boolean bindTitle = false;

			if (title.isEmpty()) {
				sb.append(_FINDER_COLUMN_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				sb.append(_FINDER_COLUMN_TITLE_TITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CouncilSessionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindTitle) {
					queryPos.add(title);
				}

				list = (List<CouncilSession>)QueryUtil.list(
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
	 * Returns the first council session in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching council session
	 * @throws NoSuchCouncilSessionException if a matching council session could not be found
	 */
	@Override
	public CouncilSession findByTitle_First(
			String title, OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = fetchByTitle_First(
			title, orderByComparator);

		if (councilSession != null) {
			return councilSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("title=");
		sb.append(title);

		sb.append("}");

		throw new NoSuchCouncilSessionException(sb.toString());
	}

	/**
	 * Returns the first council session in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching council session, or <code>null</code> if a matching council session could not be found
	 */
	@Override
	public CouncilSession fetchByTitle_First(
		String title, OrderByComparator<CouncilSession> orderByComparator) {

		List<CouncilSession> list = findByTitle(title, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last council session in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching council session
	 * @throws NoSuchCouncilSessionException if a matching council session could not be found
	 */
	@Override
	public CouncilSession findByTitle_Last(
			String title, OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = fetchByTitle_Last(
			title, orderByComparator);

		if (councilSession != null) {
			return councilSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("title=");
		sb.append(title);

		sb.append("}");

		throw new NoSuchCouncilSessionException(sb.toString());
	}

	/**
	 * Returns the last council session in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching council session, or <code>null</code> if a matching council session could not be found
	 */
	@Override
	public CouncilSession fetchByTitle_Last(
		String title, OrderByComparator<CouncilSession> orderByComparator) {

		int count = countByTitle(title);

		if (count == 0) {
			return null;
		}

		List<CouncilSession> list = findByTitle(
			title, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the council sessions before and after the current council session in the ordered set where title = &#63;.
	 *
	 * @param councilSessionId the primary key of the current council session
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next council session
	 * @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	 */
	@Override
	public CouncilSession[] findByTitle_PrevAndNext(
			long councilSessionId, String title,
			OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		title = Objects.toString(title, "");

		CouncilSession councilSession = findByPrimaryKey(councilSessionId);

		Session session = null;

		try {
			session = openSession();

			CouncilSession[] array = new CouncilSessionImpl[3];

			array[0] = getByTitle_PrevAndNext(
				session, councilSession, title, orderByComparator, true);

			array[1] = councilSession;

			array[2] = getByTitle_PrevAndNext(
				session, councilSession, title, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CouncilSession getByTitle_PrevAndNext(
		Session session, CouncilSession councilSession, String title,
		OrderByComparator<CouncilSession> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COUNCILSESSION_WHERE);

		boolean bindTitle = false;

		if (title.isEmpty()) {
			sb.append(_FINDER_COLUMN_TITLE_TITLE_3);
		}
		else {
			bindTitle = true;

			sb.append(_FINDER_COLUMN_TITLE_TITLE_2);
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
			sb.append(CouncilSessionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindTitle) {
			queryPos.add(title);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						councilSession)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CouncilSession> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the council sessions where title = &#63; from the database.
	 *
	 * @param title the title
	 */
	@Override
	public void removeByTitle(String title) {
		for (CouncilSession councilSession :
				findByTitle(
					title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(councilSession);
		}
	}

	/**
	 * Returns the number of council sessions where title = &#63;.
	 *
	 * @param title the title
	 * @return the number of matching council sessions
	 */
	@Override
	public int countByTitle(String title) {
		title = Objects.toString(title, "");

		FinderPath finderPath = _finderPathCountByTitle;

		Object[] finderArgs = new Object[] {title};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COUNCILSESSION_WHERE);

			boolean bindTitle = false;

			if (title.isEmpty()) {
				sb.append(_FINDER_COLUMN_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				sb.append(_FINDER_COLUMN_TITLE_TITLE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindTitle) {
					queryPos.add(title);
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

	private static final String _FINDER_COLUMN_TITLE_TITLE_2 =
		"councilSession.title = ?";

	private static final String _FINDER_COLUMN_TITLE_TITLE_3 =
		"(councilSession.title IS NULL OR councilSession.title = '')";

	private FinderPath _finderPathWithPaginationFindByDate;
	private FinderPath _finderPathWithoutPaginationFindByDate;
	private FinderPath _finderPathCountByDate;

	/**
	 * Returns all the council sessions where date = &#63;.
	 *
	 * @param date the date
	 * @return the matching council sessions
	 */
	@Override
	public List<CouncilSession> findByDate(Date date) {
		return findByDate(date, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the council sessions where date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param date the date
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @return the range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByDate(Date date, int start, int end) {
		return findByDate(date, start, end, null);
	}

	/**
	 * Returns an ordered range of all the council sessions where date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param date the date
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByDate(
		Date date, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator) {

		return findByDate(date, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the council sessions where date = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param date the date
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByDate(
		Date date, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByDate;
				finderArgs = new Object[] {_getTime(date)};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByDate;
			finderArgs = new Object[] {
				_getTime(date), start, end, orderByComparator
			};
		}

		List<CouncilSession> list = null;

		if (useFinderCache) {
			list = (List<CouncilSession>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CouncilSession councilSession : list) {
					if (!Objects.equals(date, councilSession.getDate())) {
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

			sb.append(_SQL_SELECT_COUNCILSESSION_WHERE);

			boolean bindDate = false;

			if (date == null) {
				sb.append(_FINDER_COLUMN_DATE_DATE_1);
			}
			else {
				bindDate = true;

				sb.append(_FINDER_COLUMN_DATE_DATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CouncilSessionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDate) {
					queryPos.add(new Timestamp(date.getTime()));
				}

				list = (List<CouncilSession>)QueryUtil.list(
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
	 * Returns the first council session in the ordered set where date = &#63;.
	 *
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching council session
	 * @throws NoSuchCouncilSessionException if a matching council session could not be found
	 */
	@Override
	public CouncilSession findByDate_First(
			Date date, OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = fetchByDate_First(
			date, orderByComparator);

		if (councilSession != null) {
			return councilSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("date=");
		sb.append(date);

		sb.append("}");

		throw new NoSuchCouncilSessionException(sb.toString());
	}

	/**
	 * Returns the first council session in the ordered set where date = &#63;.
	 *
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching council session, or <code>null</code> if a matching council session could not be found
	 */
	@Override
	public CouncilSession fetchByDate_First(
		Date date, OrderByComparator<CouncilSession> orderByComparator) {

		List<CouncilSession> list = findByDate(date, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last council session in the ordered set where date = &#63;.
	 *
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching council session
	 * @throws NoSuchCouncilSessionException if a matching council session could not be found
	 */
	@Override
	public CouncilSession findByDate_Last(
			Date date, OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = fetchByDate_Last(
			date, orderByComparator);

		if (councilSession != null) {
			return councilSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("date=");
		sb.append(date);

		sb.append("}");

		throw new NoSuchCouncilSessionException(sb.toString());
	}

	/**
	 * Returns the last council session in the ordered set where date = &#63;.
	 *
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching council session, or <code>null</code> if a matching council session could not be found
	 */
	@Override
	public CouncilSession fetchByDate_Last(
		Date date, OrderByComparator<CouncilSession> orderByComparator) {

		int count = countByDate(date);

		if (count == 0) {
			return null;
		}

		List<CouncilSession> list = findByDate(
			date, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the council sessions before and after the current council session in the ordered set where date = &#63;.
	 *
	 * @param councilSessionId the primary key of the current council session
	 * @param date the date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next council session
	 * @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	 */
	@Override
	public CouncilSession[] findByDate_PrevAndNext(
			long councilSessionId, Date date,
			OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = findByPrimaryKey(councilSessionId);

		Session session = null;

		try {
			session = openSession();

			CouncilSession[] array = new CouncilSessionImpl[3];

			array[0] = getByDate_PrevAndNext(
				session, councilSession, date, orderByComparator, true);

			array[1] = councilSession;

			array[2] = getByDate_PrevAndNext(
				session, councilSession, date, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CouncilSession getByDate_PrevAndNext(
		Session session, CouncilSession councilSession, Date date,
		OrderByComparator<CouncilSession> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COUNCILSESSION_WHERE);

		boolean bindDate = false;

		if (date == null) {
			sb.append(_FINDER_COLUMN_DATE_DATE_1);
		}
		else {
			bindDate = true;

			sb.append(_FINDER_COLUMN_DATE_DATE_2);
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
			sb.append(CouncilSessionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindDate) {
			queryPos.add(new Timestamp(date.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						councilSession)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CouncilSession> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the council sessions where date = &#63; from the database.
	 *
	 * @param date the date
	 */
	@Override
	public void removeByDate(Date date) {
		for (CouncilSession councilSession :
				findByDate(date, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(councilSession);
		}
	}

	/**
	 * Returns the number of council sessions where date = &#63;.
	 *
	 * @param date the date
	 * @return the number of matching council sessions
	 */
	@Override
	public int countByDate(Date date) {
		FinderPath finderPath = _finderPathCountByDate;

		Object[] finderArgs = new Object[] {_getTime(date)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COUNCILSESSION_WHERE);

			boolean bindDate = false;

			if (date == null) {
				sb.append(_FINDER_COLUMN_DATE_DATE_1);
			}
			else {
				bindDate = true;

				sb.append(_FINDER_COLUMN_DATE_DATE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDate) {
					queryPos.add(new Timestamp(date.getTime()));
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

	private static final String _FINDER_COLUMN_DATE_DATE_1 =
		"councilSession.date IS NULL";

	private static final String _FINDER_COLUMN_DATE_DATE_2 =
		"councilSession.date = ?";

	private FinderPath _finderPathWithPaginationFindByTypeId;
	private FinderPath _finderPathWithoutPaginationFindByTypeId;
	private FinderPath _finderPathCountByTypeId;

	/**
	 * Returns all the council sessions where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the matching council sessions
	 */
	@Override
	public List<CouncilSession> findByTypeId(long typeId) {
		return findByTypeId(typeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the council sessions where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @return the range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByTypeId(long typeId, int start, int end) {
		return findByTypeId(typeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the council sessions where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByTypeId(
		long typeId, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator) {

		return findByTypeId(typeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the council sessions where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching council sessions
	 */
	@Override
	public List<CouncilSession> findByTypeId(
		long typeId, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByTypeId;
				finderArgs = new Object[] {typeId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByTypeId;
			finderArgs = new Object[] {typeId, start, end, orderByComparator};
		}

		List<CouncilSession> list = null;

		if (useFinderCache) {
			list = (List<CouncilSession>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CouncilSession councilSession : list) {
					if (typeId != councilSession.getTypeId()) {
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

			sb.append(_SQL_SELECT_COUNCILSESSION_WHERE);

			sb.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CouncilSessionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(typeId);

				list = (List<CouncilSession>)QueryUtil.list(
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
	 * Returns the first council session in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching council session
	 * @throws NoSuchCouncilSessionException if a matching council session could not be found
	 */
	@Override
	public CouncilSession findByTypeId_First(
			long typeId, OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = fetchByTypeId_First(
			typeId, orderByComparator);

		if (councilSession != null) {
			return councilSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("typeId=");
		sb.append(typeId);

		sb.append("}");

		throw new NoSuchCouncilSessionException(sb.toString());
	}

	/**
	 * Returns the first council session in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching council session, or <code>null</code> if a matching council session could not be found
	 */
	@Override
	public CouncilSession fetchByTypeId_First(
		long typeId, OrderByComparator<CouncilSession> orderByComparator) {

		List<CouncilSession> list = findByTypeId(
			typeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last council session in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching council session
	 * @throws NoSuchCouncilSessionException if a matching council session could not be found
	 */
	@Override
	public CouncilSession findByTypeId_Last(
			long typeId, OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = fetchByTypeId_Last(
			typeId, orderByComparator);

		if (councilSession != null) {
			return councilSession;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("typeId=");
		sb.append(typeId);

		sb.append("}");

		throw new NoSuchCouncilSessionException(sb.toString());
	}

	/**
	 * Returns the last council session in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching council session, or <code>null</code> if a matching council session could not be found
	 */
	@Override
	public CouncilSession fetchByTypeId_Last(
		long typeId, OrderByComparator<CouncilSession> orderByComparator) {

		int count = countByTypeId(typeId);

		if (count == 0) {
			return null;
		}

		List<CouncilSession> list = findByTypeId(
			typeId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the council sessions before and after the current council session in the ordered set where typeId = &#63;.
	 *
	 * @param councilSessionId the primary key of the current council session
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next council session
	 * @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	 */
	@Override
	public CouncilSession[] findByTypeId_PrevAndNext(
			long councilSessionId, long typeId,
			OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = findByPrimaryKey(councilSessionId);

		Session session = null;

		try {
			session = openSession();

			CouncilSession[] array = new CouncilSessionImpl[3];

			array[0] = getByTypeId_PrevAndNext(
				session, councilSession, typeId, orderByComparator, true);

			array[1] = councilSession;

			array[2] = getByTypeId_PrevAndNext(
				session, councilSession, typeId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CouncilSession getByTypeId_PrevAndNext(
		Session session, CouncilSession councilSession, long typeId,
		OrderByComparator<CouncilSession> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COUNCILSESSION_WHERE);

		sb.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

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
			sb.append(CouncilSessionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(typeId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						councilSession)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CouncilSession> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the council sessions where typeId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 */
	@Override
	public void removeByTypeId(long typeId) {
		for (CouncilSession councilSession :
				findByTypeId(
					typeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(councilSession);
		}
	}

	/**
	 * Returns the number of council sessions where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the number of matching council sessions
	 */
	@Override
	public int countByTypeId(long typeId) {
		FinderPath finderPath = _finderPathCountByTypeId;

		Object[] finderArgs = new Object[] {typeId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COUNCILSESSION_WHERE);

			sb.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(typeId);

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

	private static final String _FINDER_COLUMN_TYPEID_TYPEID_2 =
		"councilSession.typeId = ?";

	public CouncilSessionPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("date", "date_");

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

		setModelClass(CouncilSession.class);
	}

	/**
	 * Caches the council session in the entity cache if it is enabled.
	 *
	 * @param councilSession the council session
	 */
	@Override
	public void cacheResult(CouncilSession councilSession) {
		entityCache.putResult(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionImpl.class, councilSession.getPrimaryKey(),
			councilSession);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				councilSession.getUuid(), councilSession.getGroupId()
			},
			councilSession);

		councilSession.resetOriginalValues();
	}

	/**
	 * Caches the council sessions in the entity cache if it is enabled.
	 *
	 * @param councilSessions the council sessions
	 */
	@Override
	public void cacheResult(List<CouncilSession> councilSessions) {
		for (CouncilSession councilSession : councilSessions) {
			if (entityCache.getResult(
					CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
					CouncilSessionImpl.class, councilSession.getPrimaryKey()) ==
						null) {

				cacheResult(councilSession);
			}
			else {
				councilSession.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all council sessions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CouncilSessionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the council session.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CouncilSession councilSession) {
		entityCache.removeResult(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionImpl.class, councilSession.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CouncilSessionModelImpl)councilSession, true);
	}

	@Override
	public void clearCache(List<CouncilSession> councilSessions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CouncilSession councilSession : councilSessions) {
			entityCache.removeResult(
				CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
				CouncilSessionImpl.class, councilSession.getPrimaryKey());

			clearUniqueFindersCache(
				(CouncilSessionModelImpl)councilSession, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
				CouncilSessionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CouncilSessionModelImpl councilSessionModelImpl) {

		Object[] args = new Object[] {
			councilSessionModelImpl.getUuid(),
			councilSessionModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, councilSessionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CouncilSessionModelImpl councilSessionModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				councilSessionModelImpl.getUuid(),
				councilSessionModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((councilSessionModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				councilSessionModelImpl.getOriginalUuid(),
				councilSessionModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new council session with the primary key. Does not add the council session to the database.
	 *
	 * @param councilSessionId the primary key for the new council session
	 * @return the new council session
	 */
	@Override
	public CouncilSession create(long councilSessionId) {
		CouncilSession councilSession = new CouncilSessionImpl();

		councilSession.setNew(true);
		councilSession.setPrimaryKey(councilSessionId);

		String uuid = PortalUUIDUtil.generate();

		councilSession.setUuid(uuid);

		councilSession.setCompanyId(CompanyThreadLocal.getCompanyId());

		return councilSession;
	}

	/**
	 * Removes the council session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param councilSessionId the primary key of the council session
	 * @return the council session that was removed
	 * @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	 */
	@Override
	public CouncilSession remove(long councilSessionId)
		throws NoSuchCouncilSessionException {

		return remove((Serializable)councilSessionId);
	}

	/**
	 * Removes the council session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the council session
	 * @return the council session that was removed
	 * @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	 */
	@Override
	public CouncilSession remove(Serializable primaryKey)
		throws NoSuchCouncilSessionException {

		Session session = null;

		try {
			session = openSession();

			CouncilSession councilSession = (CouncilSession)session.get(
				CouncilSessionImpl.class, primaryKey);

			if (councilSession == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCouncilSessionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(councilSession);
		}
		catch (NoSuchCouncilSessionException noSuchEntityException) {
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
	protected CouncilSession removeImpl(CouncilSession councilSession) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(councilSession)) {
				councilSession = (CouncilSession)session.get(
					CouncilSessionImpl.class,
					councilSession.getPrimaryKeyObj());
			}

			if (councilSession != null) {
				session.delete(councilSession);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (councilSession != null) {
			clearCache(councilSession);
		}

		return councilSession;
	}

	@Override
	public CouncilSession updateImpl(CouncilSession councilSession) {
		boolean isNew = councilSession.isNew();

		if (!(councilSession instanceof CouncilSessionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(councilSession.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					councilSession);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in councilSession proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CouncilSession implementation " +
					councilSession.getClass());
		}

		CouncilSessionModelImpl councilSessionModelImpl =
			(CouncilSessionModelImpl)councilSession;

		if (Validator.isNull(councilSession.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			councilSession.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (councilSession.getCreateDate() == null)) {
			if (serviceContext == null) {
				councilSession.setCreateDate(now);
			}
			else {
				councilSession.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!councilSessionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				councilSession.setModifiedDate(now);
			}
			else {
				councilSession.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (councilSession.isNew()) {
				session.save(councilSession);

				councilSession.setNew(false);
			}
			else {
				councilSession = (CouncilSession)session.merge(councilSession);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CouncilSessionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {councilSessionModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				councilSessionModelImpl.getUuid(),
				councilSessionModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {councilSessionModelImpl.getTitle()};

			finderCache.removeResult(_finderPathCountByTitle, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByTitle, args);

			args = new Object[] {councilSessionModelImpl.getDate()};

			finderCache.removeResult(_finderPathCountByDate, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByDate, args);

			args = new Object[] {councilSessionModelImpl.getTypeId()};

			finderCache.removeResult(_finderPathCountByTypeId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByTypeId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((councilSessionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					councilSessionModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {councilSessionModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((councilSessionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					councilSessionModelImpl.getOriginalUuid(),
					councilSessionModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					councilSessionModelImpl.getUuid(),
					councilSessionModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((councilSessionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByTitle.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					councilSessionModelImpl.getOriginalTitle()
				};

				finderCache.removeResult(_finderPathCountByTitle, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTitle, args);

				args = new Object[] {councilSessionModelImpl.getTitle()};

				finderCache.removeResult(_finderPathCountByTitle, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTitle, args);
			}

			if ((councilSessionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByDate.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					councilSessionModelImpl.getOriginalDate()
				};

				finderCache.removeResult(_finderPathCountByDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByDate, args);

				args = new Object[] {councilSessionModelImpl.getDate()};

				finderCache.removeResult(_finderPathCountByDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByDate, args);
			}

			if ((councilSessionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByTypeId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					councilSessionModelImpl.getOriginalTypeId()
				};

				finderCache.removeResult(_finderPathCountByTypeId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTypeId, args);

				args = new Object[] {councilSessionModelImpl.getTypeId()};

				finderCache.removeResult(_finderPathCountByTypeId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTypeId, args);
			}
		}

		entityCache.putResult(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionImpl.class, councilSession.getPrimaryKey(),
			councilSession, false);

		clearUniqueFindersCache(councilSessionModelImpl, false);
		cacheUniqueFindersCache(councilSessionModelImpl);

		councilSession.resetOriginalValues();

		return councilSession;
	}

	/**
	 * Returns the council session with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the council session
	 * @return the council session
	 * @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	 */
	@Override
	public CouncilSession findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCouncilSessionException {

		CouncilSession councilSession = fetchByPrimaryKey(primaryKey);

		if (councilSession == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCouncilSessionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return councilSession;
	}

	/**
	 * Returns the council session with the primary key or throws a <code>NoSuchCouncilSessionException</code> if it could not be found.
	 *
	 * @param councilSessionId the primary key of the council session
	 * @return the council session
	 * @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	 */
	@Override
	public CouncilSession findByPrimaryKey(long councilSessionId)
		throws NoSuchCouncilSessionException {

		return findByPrimaryKey((Serializable)councilSessionId);
	}

	/**
	 * Returns the council session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the council session
	 * @return the council session, or <code>null</code> if a council session with the primary key could not be found
	 */
	@Override
	public CouncilSession fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CouncilSession councilSession = (CouncilSession)serializable;

		if (councilSession == null) {
			Session session = null;

			try {
				session = openSession();

				councilSession = (CouncilSession)session.get(
					CouncilSessionImpl.class, primaryKey);

				if (councilSession != null) {
					cacheResult(councilSession);
				}
				else {
					entityCache.putResult(
						CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
						CouncilSessionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
					CouncilSessionImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return councilSession;
	}

	/**
	 * Returns the council session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param councilSessionId the primary key of the council session
	 * @return the council session, or <code>null</code> if a council session with the primary key could not be found
	 */
	@Override
	public CouncilSession fetchByPrimaryKey(long councilSessionId) {
		return fetchByPrimaryKey((Serializable)councilSessionId);
	}

	@Override
	public Map<Serializable, CouncilSession> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CouncilSession> map =
			new HashMap<Serializable, CouncilSession>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CouncilSession councilSession = fetchByPrimaryKey(primaryKey);

			if (councilSession != null) {
				map.put(primaryKey, councilSession);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
				CouncilSessionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CouncilSession)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_COUNCILSESSION_WHERE_PKS_IN);

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

			for (CouncilSession councilSession :
					(List<CouncilSession>)query.list()) {

				map.put(councilSession.getPrimaryKeyObj(), councilSession);

				cacheResult(councilSession);

				uncachedPrimaryKeys.remove(councilSession.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
					CouncilSessionImpl.class, primaryKey, nullModel);
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
	 * Returns all the council sessions.
	 *
	 * @return the council sessions
	 */
	@Override
	public List<CouncilSession> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the council sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @return the range of council sessions
	 */
	@Override
	public List<CouncilSession> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the council sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of council sessions
	 */
	@Override
	public List<CouncilSession> findAll(
		int start, int end,
		OrderByComparator<CouncilSession> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the council sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of council sessions
	 * @param end the upper bound of the range of council sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of council sessions
	 */
	@Override
	public List<CouncilSession> findAll(
		int start, int end, OrderByComparator<CouncilSession> orderByComparator,
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

		List<CouncilSession> list = null;

		if (useFinderCache) {
			list = (List<CouncilSession>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COUNCILSESSION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COUNCILSESSION;

				sql = sql.concat(CouncilSessionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CouncilSession>)QueryUtil.list(
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
	 * Removes all the council sessions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CouncilSession councilSession : findAll()) {
			remove(councilSession);
		}
	}

	/**
	 * Returns the number of council sessions.
	 *
	 * @return the number of council sessions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_COUNCILSESSION);

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
		return CouncilSessionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the council session persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED,
			CouncilSessionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED,
			CouncilSessionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED,
			CouncilSessionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED,
			CouncilSessionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] {String.class.getName()},
			CouncilSessionModelImpl.UUID_COLUMN_BITMASK |
			CouncilSessionModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED,
			CouncilSessionImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			CouncilSessionModelImpl.UUID_COLUMN_BITMASK |
			CouncilSessionModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED,
			CouncilSessionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED,
			CouncilSessionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CouncilSessionModelImpl.UUID_COLUMN_BITMASK |
			CouncilSessionModelImpl.COMPANYID_COLUMN_BITMASK |
			CouncilSessionModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByTitle = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED,
			CouncilSessionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTitle",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByTitle = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED,
			CouncilSessionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByTitle", new String[] {String.class.getName()},
			CouncilSessionModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByTitle = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTitle",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByDate = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED,
			CouncilSessionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByDate",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByDate = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED,
			CouncilSessionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByDate", new String[] {Date.class.getName()},
			CouncilSessionModelImpl.DATE_COLUMN_BITMASK |
			CouncilSessionModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByDate = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDate",
			new String[] {Date.class.getName()});

		_finderPathWithPaginationFindByTypeId = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED,
			CouncilSessionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTypeId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByTypeId = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED,
			CouncilSessionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByTypeId", new String[] {Long.class.getName()},
			CouncilSessionModelImpl.TYPEID_COLUMN_BITMASK |
			CouncilSessionModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByTypeId = new FinderPath(
			CouncilSessionModelImpl.ENTITY_CACHE_ENABLED,
			CouncilSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTypeId",
			new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CouncilSessionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_COUNCILSESSION =
		"SELECT councilSession FROM CouncilSession councilSession";

	private static final String _SQL_SELECT_COUNCILSESSION_WHERE_PKS_IN =
		"SELECT councilSession FROM CouncilSession councilSession WHERE councilSessionId IN (";

	private static final String _SQL_SELECT_COUNCILSESSION_WHERE =
		"SELECT councilSession FROM CouncilSession councilSession WHERE ";

	private static final String _SQL_COUNT_COUNCILSESSION =
		"SELECT COUNT(councilSession) FROM CouncilSession councilSession";

	private static final String _SQL_COUNT_COUNCILSESSION_WHERE =
		"SELECT COUNT(councilSession) FROM CouncilSession councilSession WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "councilSession.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CouncilSession exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CouncilSession exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CouncilSessionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "date"});

}