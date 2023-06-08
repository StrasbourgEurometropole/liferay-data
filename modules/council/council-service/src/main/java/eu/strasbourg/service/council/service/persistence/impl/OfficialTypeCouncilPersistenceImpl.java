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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.council.exception.NoSuchOfficialTypeCouncilException;
import eu.strasbourg.service.council.model.OfficialTypeCouncil;
import eu.strasbourg.service.council.model.impl.OfficialTypeCouncilImpl;
import eu.strasbourg.service.council.model.impl.OfficialTypeCouncilModelImpl;
import eu.strasbourg.service.council.service.persistence.OfficialTypeCouncilPK;
import eu.strasbourg.service.council.service.persistence.OfficialTypeCouncilPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the official type council service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class OfficialTypeCouncilPersistenceImpl
	extends BasePersistenceImpl<OfficialTypeCouncil>
	implements OfficialTypeCouncilPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>OfficialTypeCouncilUtil</code> to access the official type council persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		OfficialTypeCouncilImpl.class.getName();

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
	 * Returns all the official type councils where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the official type councils where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the official type councils where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the official type councils where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
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

		List<OfficialTypeCouncil> list = null;

		if (useFinderCache) {
			list = (List<OfficialTypeCouncil>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OfficialTypeCouncil officialTypeCouncil : list) {
					if (!uuid.equals(officialTypeCouncil.getUuid())) {
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

			sb.append(_SQL_SELECT_OFFICIALTYPECOUNCIL_WHERE);

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
				sb.append(OfficialTypeCouncilModelImpl.ORDER_BY_JPQL);
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

				list = (List<OfficialTypeCouncil>)QueryUtil.list(
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
	 * Returns the first official type council in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil findByUuid_First(
			String uuid,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = fetchByUuid_First(
			uuid, orderByComparator);

		if (officialTypeCouncil != null) {
			return officialTypeCouncil;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchOfficialTypeCouncilException(sb.toString());
	}

	/**
	 * Returns the first official type council in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByUuid_First(
		String uuid, OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		List<OfficialTypeCouncil> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last official type council in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil findByUuid_Last(
			String uuid,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = fetchByUuid_Last(
			uuid, orderByComparator);

		if (officialTypeCouncil != null) {
			return officialTypeCouncil;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchOfficialTypeCouncilException(sb.toString());
	}

	/**
	 * Returns the last official type council in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByUuid_Last(
		String uuid, OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<OfficialTypeCouncil> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the official type councils before and after the current official type council in the ordered set where uuid = &#63;.
	 *
	 * @param officialTypeCouncilPK the primary key of the current official type council
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	@Override
	public OfficialTypeCouncil[] findByUuid_PrevAndNext(
			OfficialTypeCouncilPK officialTypeCouncilPK, String uuid,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException {

		uuid = Objects.toString(uuid, "");

		OfficialTypeCouncil officialTypeCouncil = findByPrimaryKey(
			officialTypeCouncilPK);

		Session session = null;

		try {
			session = openSession();

			OfficialTypeCouncil[] array = new OfficialTypeCouncilImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, officialTypeCouncil, uuid, orderByComparator, true);

			array[1] = officialTypeCouncil;

			array[2] = getByUuid_PrevAndNext(
				session, officialTypeCouncil, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected OfficialTypeCouncil getByUuid_PrevAndNext(
		Session session, OfficialTypeCouncil officialTypeCouncil, String uuid,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
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

		sb.append(_SQL_SELECT_OFFICIALTYPECOUNCIL_WHERE);

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
			sb.append(OfficialTypeCouncilModelImpl.ORDER_BY_JPQL);
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
						officialTypeCouncil)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<OfficialTypeCouncil> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the official type councils where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (OfficialTypeCouncil officialTypeCouncil :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(officialTypeCouncil);
		}
	}

	/**
	 * Returns the number of official type councils where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching official type councils
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_OFFICIALTYPECOUNCIL_WHERE);

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
		"officialTypeCouncil.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(officialTypeCouncil.uuid IS NULL OR officialTypeCouncil.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the official type council where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchOfficialTypeCouncilException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil findByUUID_G(String uuid, long groupId)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = fetchByUUID_G(uuid, groupId);

		if (officialTypeCouncil == null) {
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

			throw new NoSuchOfficialTypeCouncilException(sb.toString());
		}

		return officialTypeCouncil;
	}

	/**
	 * Returns the official type council where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the official type council where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByUUID_G(
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

		if (result instanceof OfficialTypeCouncil) {
			OfficialTypeCouncil officialTypeCouncil =
				(OfficialTypeCouncil)result;

			if (!Objects.equals(uuid, officialTypeCouncil.getUuid()) ||
				(groupId != officialTypeCouncil.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_OFFICIALTYPECOUNCIL_WHERE);

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

				List<OfficialTypeCouncil> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					OfficialTypeCouncil officialTypeCouncil = list.get(0);

					result = officialTypeCouncil;

					cacheResult(officialTypeCouncil);
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
			return (OfficialTypeCouncil)result;
		}
	}

	/**
	 * Removes the official type council where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the official type council that was removed
	 */
	@Override
	public OfficialTypeCouncil removeByUUID_G(String uuid, long groupId)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = findByUUID_G(uuid, groupId);

		return remove(officialTypeCouncil);
	}

	/**
	 * Returns the number of official type councils where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching official type councils
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_OFFICIALTYPECOUNCIL_WHERE);

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
		"officialTypeCouncil.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(officialTypeCouncil.uuid IS NULL OR officialTypeCouncil.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"officialTypeCouncil.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
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

		List<OfficialTypeCouncil> list = null;

		if (useFinderCache) {
			list = (List<OfficialTypeCouncil>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OfficialTypeCouncil officialTypeCouncil : list) {
					if (!uuid.equals(officialTypeCouncil.getUuid()) ||
						(companyId != officialTypeCouncil.getCompanyId())) {

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

			sb.append(_SQL_SELECT_OFFICIALTYPECOUNCIL_WHERE);

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
				sb.append(OfficialTypeCouncilModelImpl.ORDER_BY_JPQL);
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

				list = (List<OfficialTypeCouncil>)QueryUtil.list(
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
	 * Returns the first official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (officialTypeCouncil != null) {
			return officialTypeCouncil;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchOfficialTypeCouncilException(sb.toString());
	}

	/**
	 * Returns the first official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		List<OfficialTypeCouncil> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (officialTypeCouncil != null) {
			return officialTypeCouncil;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchOfficialTypeCouncilException(sb.toString());
	}

	/**
	 * Returns the last official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<OfficialTypeCouncil> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the official type councils before and after the current official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param officialTypeCouncilPK the primary key of the current official type council
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	@Override
	public OfficialTypeCouncil[] findByUuid_C_PrevAndNext(
			OfficialTypeCouncilPK officialTypeCouncilPK, String uuid,
			long companyId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException {

		uuid = Objects.toString(uuid, "");

		OfficialTypeCouncil officialTypeCouncil = findByPrimaryKey(
			officialTypeCouncilPK);

		Session session = null;

		try {
			session = openSession();

			OfficialTypeCouncil[] array = new OfficialTypeCouncilImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, officialTypeCouncil, uuid, companyId,
				orderByComparator, true);

			array[1] = officialTypeCouncil;

			array[2] = getByUuid_C_PrevAndNext(
				session, officialTypeCouncil, uuid, companyId,
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

	protected OfficialTypeCouncil getByUuid_C_PrevAndNext(
		Session session, OfficialTypeCouncil officialTypeCouncil, String uuid,
		long companyId,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
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

		sb.append(_SQL_SELECT_OFFICIALTYPECOUNCIL_WHERE);

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
			sb.append(OfficialTypeCouncilModelImpl.ORDER_BY_JPQL);
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
						officialTypeCouncil)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<OfficialTypeCouncil> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the official type councils where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (OfficialTypeCouncil officialTypeCouncil :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(officialTypeCouncil);
		}
	}

	/**
	 * Returns the number of official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching official type councils
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_OFFICIALTYPECOUNCIL_WHERE);

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
		"officialTypeCouncil.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(officialTypeCouncil.uuid IS NULL OR officialTypeCouncil.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"officialTypeCouncil.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByOfficialId;
	private FinderPath _finderPathWithoutPaginationFindByOfficialId;
	private FinderPath _finderPathCountByOfficialId;

	/**
	 * Returns all the official type councils where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @return the matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByOfficialId(long officialId) {
		return findByOfficialId(
			officialId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the official type councils where officialId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param officialId the official ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByOfficialId(
		long officialId, int start, int end) {

		return findByOfficialId(officialId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the official type councils where officialId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param officialId the official ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByOfficialId(
		long officialId, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return findByOfficialId(
			officialId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the official type councils where officialId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param officialId the official ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByOfficialId(
		long officialId, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByOfficialId;
				finderArgs = new Object[] {officialId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByOfficialId;
			finderArgs = new Object[] {
				officialId, start, end, orderByComparator
			};
		}

		List<OfficialTypeCouncil> list = null;

		if (useFinderCache) {
			list = (List<OfficialTypeCouncil>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OfficialTypeCouncil officialTypeCouncil : list) {
					if (officialId != officialTypeCouncil.getOfficialId()) {
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

			sb.append(_SQL_SELECT_OFFICIALTYPECOUNCIL_WHERE);

			sb.append(_FINDER_COLUMN_OFFICIALID_OFFICIALID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OfficialTypeCouncilModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(officialId);

				list = (List<OfficialTypeCouncil>)QueryUtil.list(
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
	 * Returns the first official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil findByOfficialId_First(
			long officialId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = fetchByOfficialId_First(
			officialId, orderByComparator);

		if (officialTypeCouncil != null) {
			return officialTypeCouncil;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("officialId=");
		sb.append(officialId);

		sb.append("}");

		throw new NoSuchOfficialTypeCouncilException(sb.toString());
	}

	/**
	 * Returns the first official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByOfficialId_First(
		long officialId,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		List<OfficialTypeCouncil> list = findByOfficialId(
			officialId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil findByOfficialId_Last(
			long officialId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = fetchByOfficialId_Last(
			officialId, orderByComparator);

		if (officialTypeCouncil != null) {
			return officialTypeCouncil;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("officialId=");
		sb.append(officialId);

		sb.append("}");

		throw new NoSuchOfficialTypeCouncilException(sb.toString());
	}

	/**
	 * Returns the last official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByOfficialId_Last(
		long officialId,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		int count = countByOfficialId(officialId);

		if (count == 0) {
			return null;
		}

		List<OfficialTypeCouncil> list = findByOfficialId(
			officialId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the official type councils before and after the current official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialTypeCouncilPK the primary key of the current official type council
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	@Override
	public OfficialTypeCouncil[] findByOfficialId_PrevAndNext(
			OfficialTypeCouncilPK officialTypeCouncilPK, long officialId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = findByPrimaryKey(
			officialTypeCouncilPK);

		Session session = null;

		try {
			session = openSession();

			OfficialTypeCouncil[] array = new OfficialTypeCouncilImpl[3];

			array[0] = getByOfficialId_PrevAndNext(
				session, officialTypeCouncil, officialId, orderByComparator,
				true);

			array[1] = officialTypeCouncil;

			array[2] = getByOfficialId_PrevAndNext(
				session, officialTypeCouncil, officialId, orderByComparator,
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

	protected OfficialTypeCouncil getByOfficialId_PrevAndNext(
		Session session, OfficialTypeCouncil officialTypeCouncil,
		long officialId,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
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

		sb.append(_SQL_SELECT_OFFICIALTYPECOUNCIL_WHERE);

		sb.append(_FINDER_COLUMN_OFFICIALID_OFFICIALID_2);

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
			sb.append(OfficialTypeCouncilModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(officialId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						officialTypeCouncil)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<OfficialTypeCouncil> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the official type councils where officialId = &#63; from the database.
	 *
	 * @param officialId the official ID
	 */
	@Override
	public void removeByOfficialId(long officialId) {
		for (OfficialTypeCouncil officialTypeCouncil :
				findByOfficialId(
					officialId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(officialTypeCouncil);
		}
	}

	/**
	 * Returns the number of official type councils where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @return the number of matching official type councils
	 */
	@Override
	public int countByOfficialId(long officialId) {
		FinderPath finderPath = _finderPathCountByOfficialId;

		Object[] finderArgs = new Object[] {officialId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_OFFICIALTYPECOUNCIL_WHERE);

			sb.append(_FINDER_COLUMN_OFFICIALID_OFFICIALID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(officialId);

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

	private static final String _FINDER_COLUMN_OFFICIALID_OFFICIALID_2 =
		"officialTypeCouncil.id.officialId = ?";

	private FinderPath _finderPathWithPaginationFindByTypeId;
	private FinderPath _finderPathWithoutPaginationFindByTypeId;
	private FinderPath _finderPathCountByTypeId;

	/**
	 * Returns all the official type councils where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByTypeId(long typeId) {
		return findByTypeId(typeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the official type councils where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByTypeId(
		long typeId, int start, int end) {

		return findByTypeId(typeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the official type councils where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByTypeId(
		long typeId, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return findByTypeId(typeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the official type councils where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findByTypeId(
		long typeId, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
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

		List<OfficialTypeCouncil> list = null;

		if (useFinderCache) {
			list = (List<OfficialTypeCouncil>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OfficialTypeCouncil officialTypeCouncil : list) {
					if (typeId != officialTypeCouncil.getTypeId()) {
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

			sb.append(_SQL_SELECT_OFFICIALTYPECOUNCIL_WHERE);

			sb.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OfficialTypeCouncilModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(typeId);

				list = (List<OfficialTypeCouncil>)QueryUtil.list(
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
	 * Returns the first official type council in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil findByTypeId_First(
			long typeId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = fetchByTypeId_First(
			typeId, orderByComparator);

		if (officialTypeCouncil != null) {
			return officialTypeCouncil;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("typeId=");
		sb.append(typeId);

		sb.append("}");

		throw new NoSuchOfficialTypeCouncilException(sb.toString());
	}

	/**
	 * Returns the first official type council in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByTypeId_First(
		long typeId, OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		List<OfficialTypeCouncil> list = findByTypeId(
			typeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last official type council in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil findByTypeId_Last(
			long typeId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = fetchByTypeId_Last(
			typeId, orderByComparator);

		if (officialTypeCouncil != null) {
			return officialTypeCouncil;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("typeId=");
		sb.append(typeId);

		sb.append("}");

		throw new NoSuchOfficialTypeCouncilException(sb.toString());
	}

	/**
	 * Returns the last official type council in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByTypeId_Last(
		long typeId, OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		int count = countByTypeId(typeId);

		if (count == 0) {
			return null;
		}

		List<OfficialTypeCouncil> list = findByTypeId(
			typeId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the official type councils before and after the current official type council in the ordered set where typeId = &#63;.
	 *
	 * @param officialTypeCouncilPK the primary key of the current official type council
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	@Override
	public OfficialTypeCouncil[] findByTypeId_PrevAndNext(
			OfficialTypeCouncilPK officialTypeCouncilPK, long typeId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = findByPrimaryKey(
			officialTypeCouncilPK);

		Session session = null;

		try {
			session = openSession();

			OfficialTypeCouncil[] array = new OfficialTypeCouncilImpl[3];

			array[0] = getByTypeId_PrevAndNext(
				session, officialTypeCouncil, typeId, orderByComparator, true);

			array[1] = officialTypeCouncil;

			array[2] = getByTypeId_PrevAndNext(
				session, officialTypeCouncil, typeId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected OfficialTypeCouncil getByTypeId_PrevAndNext(
		Session session, OfficialTypeCouncil officialTypeCouncil, long typeId,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
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

		sb.append(_SQL_SELECT_OFFICIALTYPECOUNCIL_WHERE);

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
			sb.append(OfficialTypeCouncilModelImpl.ORDER_BY_JPQL);
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
						officialTypeCouncil)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<OfficialTypeCouncil> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the official type councils where typeId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 */
	@Override
	public void removeByTypeId(long typeId) {
		for (OfficialTypeCouncil officialTypeCouncil :
				findByTypeId(
					typeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(officialTypeCouncil);
		}
	}

	/**
	 * Returns the number of official type councils where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the number of matching official type councils
	 */
	@Override
	public int countByTypeId(long typeId) {
		FinderPath finderPath = _finderPathCountByTypeId;

		Object[] finderArgs = new Object[] {typeId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_OFFICIALTYPECOUNCIL_WHERE);

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
		"officialTypeCouncil.id.typeId = ?";

	private FinderPath _finderPathFetchByTypeIdAndOfficialId;
	private FinderPath _finderPathCountByTypeIdAndOfficialId;

	/**
	 * Returns the official type council where typeId = &#63; and officialId = &#63; or throws a <code>NoSuchOfficialTypeCouncilException</code> if it could not be found.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @return the matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil findByTypeIdAndOfficialId(
			long typeId, long officialId)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = fetchByTypeIdAndOfficialId(
			typeId, officialId);

		if (officialTypeCouncil == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("typeId=");
			sb.append(typeId);

			sb.append(", officialId=");
			sb.append(officialId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchOfficialTypeCouncilException(sb.toString());
		}

		return officialTypeCouncil;
	}

	/**
	 * Returns the official type council where typeId = &#63; and officialId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByTypeIdAndOfficialId(
		long typeId, long officialId) {

		return fetchByTypeIdAndOfficialId(typeId, officialId, true);
	}

	/**
	 * Returns the official type council where typeId = &#63; and officialId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByTypeIdAndOfficialId(
		long typeId, long officialId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {typeId, officialId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByTypeIdAndOfficialId, finderArgs, this);
		}

		if (result instanceof OfficialTypeCouncil) {
			OfficialTypeCouncil officialTypeCouncil =
				(OfficialTypeCouncil)result;

			if ((typeId != officialTypeCouncil.getTypeId()) ||
				(officialId != officialTypeCouncil.getOfficialId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_OFFICIALTYPECOUNCIL_WHERE);

			sb.append(_FINDER_COLUMN_TYPEIDANDOFFICIALID_TYPEID_2);

			sb.append(_FINDER_COLUMN_TYPEIDANDOFFICIALID_OFFICIALID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(typeId);

				queryPos.add(officialId);

				List<OfficialTypeCouncil> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByTypeIdAndOfficialId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {typeId, officialId};
							}

							_log.warn(
								"OfficialTypeCouncilPersistenceImpl.fetchByTypeIdAndOfficialId(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OfficialTypeCouncil officialTypeCouncil = list.get(0);

					result = officialTypeCouncil;

					cacheResult(officialTypeCouncil);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByTypeIdAndOfficialId, finderArgs);
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
			return (OfficialTypeCouncil)result;
		}
	}

	/**
	 * Removes the official type council where typeId = &#63; and officialId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @return the official type council that was removed
	 */
	@Override
	public OfficialTypeCouncil removeByTypeIdAndOfficialId(
			long typeId, long officialId)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = findByTypeIdAndOfficialId(
			typeId, officialId);

		return remove(officialTypeCouncil);
	}

	/**
	 * Returns the number of official type councils where typeId = &#63; and officialId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @return the number of matching official type councils
	 */
	@Override
	public int countByTypeIdAndOfficialId(long typeId, long officialId) {
		FinderPath finderPath = _finderPathCountByTypeIdAndOfficialId;

		Object[] finderArgs = new Object[] {typeId, officialId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_OFFICIALTYPECOUNCIL_WHERE);

			sb.append(_FINDER_COLUMN_TYPEIDANDOFFICIALID_TYPEID_2);

			sb.append(_FINDER_COLUMN_TYPEIDANDOFFICIALID_OFFICIALID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(typeId);

				queryPos.add(officialId);

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

	private static final String _FINDER_COLUMN_TYPEIDANDOFFICIALID_TYPEID_2 =
		"officialTypeCouncil.id.typeId = ? AND ";

	private static final String
		_FINDER_COLUMN_TYPEIDANDOFFICIALID_OFFICIALID_2 =
			"officialTypeCouncil.id.officialId = ?";

	public OfficialTypeCouncilPersistenceImpl() {
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

		setModelClass(OfficialTypeCouncil.class);
	}

	/**
	 * Caches the official type council in the entity cache if it is enabled.
	 *
	 * @param officialTypeCouncil the official type council
	 */
	@Override
	public void cacheResult(OfficialTypeCouncil officialTypeCouncil) {
		entityCache.putResult(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class, officialTypeCouncil.getPrimaryKey(),
			officialTypeCouncil);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				officialTypeCouncil.getUuid(), officialTypeCouncil.getGroupId()
			},
			officialTypeCouncil);

		finderCache.putResult(
			_finderPathFetchByTypeIdAndOfficialId,
			new Object[] {
				officialTypeCouncil.getTypeId(),
				officialTypeCouncil.getOfficialId()
			},
			officialTypeCouncil);

		officialTypeCouncil.resetOriginalValues();
	}

	/**
	 * Caches the official type councils in the entity cache if it is enabled.
	 *
	 * @param officialTypeCouncils the official type councils
	 */
	@Override
	public void cacheResult(List<OfficialTypeCouncil> officialTypeCouncils) {
		for (OfficialTypeCouncil officialTypeCouncil : officialTypeCouncils) {
			if (entityCache.getResult(
					OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
					OfficialTypeCouncilImpl.class,
					officialTypeCouncil.getPrimaryKey()) == null) {

				cacheResult(officialTypeCouncil);
			}
			else {
				officialTypeCouncil.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all official type councils.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OfficialTypeCouncilImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the official type council.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OfficialTypeCouncil officialTypeCouncil) {
		entityCache.removeResult(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class, officialTypeCouncil.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(OfficialTypeCouncilModelImpl)officialTypeCouncil, true);
	}

	@Override
	public void clearCache(List<OfficialTypeCouncil> officialTypeCouncils) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OfficialTypeCouncil officialTypeCouncil : officialTypeCouncils) {
			entityCache.removeResult(
				OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
				OfficialTypeCouncilImpl.class,
				officialTypeCouncil.getPrimaryKey());

			clearUniqueFindersCache(
				(OfficialTypeCouncilModelImpl)officialTypeCouncil, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
				OfficialTypeCouncilImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		OfficialTypeCouncilModelImpl officialTypeCouncilModelImpl) {

		Object[] args = new Object[] {
			officialTypeCouncilModelImpl.getUuid(),
			officialTypeCouncilModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, officialTypeCouncilModelImpl,
			false);

		args = new Object[] {
			officialTypeCouncilModelImpl.getTypeId(),
			officialTypeCouncilModelImpl.getOfficialId()
		};

		finderCache.putResult(
			_finderPathCountByTypeIdAndOfficialId, args, Long.valueOf(1),
			false);
		finderCache.putResult(
			_finderPathFetchByTypeIdAndOfficialId, args,
			officialTypeCouncilModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OfficialTypeCouncilModelImpl officialTypeCouncilModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				officialTypeCouncilModelImpl.getUuid(),
				officialTypeCouncilModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((officialTypeCouncilModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				officialTypeCouncilModelImpl.getOriginalUuid(),
				officialTypeCouncilModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				officialTypeCouncilModelImpl.getTypeId(),
				officialTypeCouncilModelImpl.getOfficialId()
			};

			finderCache.removeResult(
				_finderPathCountByTypeIdAndOfficialId, args);
			finderCache.removeResult(
				_finderPathFetchByTypeIdAndOfficialId, args);
		}

		if ((officialTypeCouncilModelImpl.getColumnBitmask() &
			 _finderPathFetchByTypeIdAndOfficialId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				officialTypeCouncilModelImpl.getOriginalTypeId(),
				officialTypeCouncilModelImpl.getOriginalOfficialId()
			};

			finderCache.removeResult(
				_finderPathCountByTypeIdAndOfficialId, args);
			finderCache.removeResult(
				_finderPathFetchByTypeIdAndOfficialId, args);
		}
	}

	/**
	 * Creates a new official type council with the primary key. Does not add the official type council to the database.
	 *
	 * @param officialTypeCouncilPK the primary key for the new official type council
	 * @return the new official type council
	 */
	@Override
	public OfficialTypeCouncil create(
		OfficialTypeCouncilPK officialTypeCouncilPK) {

		OfficialTypeCouncil officialTypeCouncil = new OfficialTypeCouncilImpl();

		officialTypeCouncil.setNew(true);
		officialTypeCouncil.setPrimaryKey(officialTypeCouncilPK);

		String uuid = PortalUUIDUtil.generate();

		officialTypeCouncil.setUuid(uuid);

		officialTypeCouncil.setCompanyId(CompanyThreadLocal.getCompanyId());

		return officialTypeCouncil;
	}

	/**
	 * Removes the official type council with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param officialTypeCouncilPK the primary key of the official type council
	 * @return the official type council that was removed
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	@Override
	public OfficialTypeCouncil remove(
			OfficialTypeCouncilPK officialTypeCouncilPK)
		throws NoSuchOfficialTypeCouncilException {

		return remove((Serializable)officialTypeCouncilPK);
	}

	/**
	 * Removes the official type council with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the official type council
	 * @return the official type council that was removed
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	@Override
	public OfficialTypeCouncil remove(Serializable primaryKey)
		throws NoSuchOfficialTypeCouncilException {

		Session session = null;

		try {
			session = openSession();

			OfficialTypeCouncil officialTypeCouncil =
				(OfficialTypeCouncil)session.get(
					OfficialTypeCouncilImpl.class, primaryKey);

			if (officialTypeCouncil == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOfficialTypeCouncilException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(officialTypeCouncil);
		}
		catch (NoSuchOfficialTypeCouncilException noSuchEntityException) {
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
	protected OfficialTypeCouncil removeImpl(
		OfficialTypeCouncil officialTypeCouncil) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(officialTypeCouncil)) {
				officialTypeCouncil = (OfficialTypeCouncil)session.get(
					OfficialTypeCouncilImpl.class,
					officialTypeCouncil.getPrimaryKeyObj());
			}

			if (officialTypeCouncil != null) {
				session.delete(officialTypeCouncil);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (officialTypeCouncil != null) {
			clearCache(officialTypeCouncil);
		}

		return officialTypeCouncil;
	}

	@Override
	public OfficialTypeCouncil updateImpl(
		OfficialTypeCouncil officialTypeCouncil) {

		boolean isNew = officialTypeCouncil.isNew();

		if (!(officialTypeCouncil instanceof OfficialTypeCouncilModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(officialTypeCouncil.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					officialTypeCouncil);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in officialTypeCouncil proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OfficialTypeCouncil implementation " +
					officialTypeCouncil.getClass());
		}

		OfficialTypeCouncilModelImpl officialTypeCouncilModelImpl =
			(OfficialTypeCouncilModelImpl)officialTypeCouncil;

		if (Validator.isNull(officialTypeCouncil.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			officialTypeCouncil.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (officialTypeCouncil.isNew()) {
				session.save(officialTypeCouncil);

				officialTypeCouncil.setNew(false);
			}
			else {
				officialTypeCouncil = (OfficialTypeCouncil)session.merge(
					officialTypeCouncil);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OfficialTypeCouncilModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				officialTypeCouncilModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				officialTypeCouncilModelImpl.getUuid(),
				officialTypeCouncilModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {officialTypeCouncilModelImpl.getOfficialId()};

			finderCache.removeResult(_finderPathCountByOfficialId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByOfficialId, args);

			args = new Object[] {officialTypeCouncilModelImpl.getTypeId()};

			finderCache.removeResult(_finderPathCountByTypeId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByTypeId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((officialTypeCouncilModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					officialTypeCouncilModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {officialTypeCouncilModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((officialTypeCouncilModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					officialTypeCouncilModelImpl.getOriginalUuid(),
					officialTypeCouncilModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					officialTypeCouncilModelImpl.getUuid(),
					officialTypeCouncilModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((officialTypeCouncilModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByOfficialId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					officialTypeCouncilModelImpl.getOriginalOfficialId()
				};

				finderCache.removeResult(_finderPathCountByOfficialId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOfficialId, args);

				args = new Object[] {
					officialTypeCouncilModelImpl.getOfficialId()
				};

				finderCache.removeResult(_finderPathCountByOfficialId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOfficialId, args);
			}

			if ((officialTypeCouncilModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByTypeId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					officialTypeCouncilModelImpl.getOriginalTypeId()
				};

				finderCache.removeResult(_finderPathCountByTypeId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTypeId, args);

				args = new Object[] {officialTypeCouncilModelImpl.getTypeId()};

				finderCache.removeResult(_finderPathCountByTypeId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTypeId, args);
			}
		}

		entityCache.putResult(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class, officialTypeCouncil.getPrimaryKey(),
			officialTypeCouncil, false);

		clearUniqueFindersCache(officialTypeCouncilModelImpl, false);
		cacheUniqueFindersCache(officialTypeCouncilModelImpl);

		officialTypeCouncil.resetOriginalValues();

		return officialTypeCouncil;
	}

	/**
	 * Returns the official type council with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the official type council
	 * @return the official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	@Override
	public OfficialTypeCouncil findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOfficialTypeCouncilException {

		OfficialTypeCouncil officialTypeCouncil = fetchByPrimaryKey(primaryKey);

		if (officialTypeCouncil == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOfficialTypeCouncilException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return officialTypeCouncil;
	}

	/**
	 * Returns the official type council with the primary key or throws a <code>NoSuchOfficialTypeCouncilException</code> if it could not be found.
	 *
	 * @param officialTypeCouncilPK the primary key of the official type council
	 * @return the official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	@Override
	public OfficialTypeCouncil findByPrimaryKey(
			OfficialTypeCouncilPK officialTypeCouncilPK)
		throws NoSuchOfficialTypeCouncilException {

		return findByPrimaryKey((Serializable)officialTypeCouncilPK);
	}

	/**
	 * Returns the official type council with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the official type council
	 * @return the official type council, or <code>null</code> if a official type council with the primary key could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OfficialTypeCouncil officialTypeCouncil =
			(OfficialTypeCouncil)serializable;

		if (officialTypeCouncil == null) {
			Session session = null;

			try {
				session = openSession();

				officialTypeCouncil = (OfficialTypeCouncil)session.get(
					OfficialTypeCouncilImpl.class, primaryKey);

				if (officialTypeCouncil != null) {
					cacheResult(officialTypeCouncil);
				}
				else {
					entityCache.putResult(
						OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
						OfficialTypeCouncilImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
					OfficialTypeCouncilImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return officialTypeCouncil;
	}

	/**
	 * Returns the official type council with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param officialTypeCouncilPK the primary key of the official type council
	 * @return the official type council, or <code>null</code> if a official type council with the primary key could not be found
	 */
	@Override
	public OfficialTypeCouncil fetchByPrimaryKey(
		OfficialTypeCouncilPK officialTypeCouncilPK) {

		return fetchByPrimaryKey((Serializable)officialTypeCouncilPK);
	}

	@Override
	public Map<Serializable, OfficialTypeCouncil> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OfficialTypeCouncil> map =
			new HashMap<Serializable, OfficialTypeCouncil>();

		for (Serializable primaryKey : primaryKeys) {
			OfficialTypeCouncil officialTypeCouncil = fetchByPrimaryKey(
				primaryKey);

			if (officialTypeCouncil != null) {
				map.put(primaryKey, officialTypeCouncil);
			}
		}

		return map;
	}

	/**
	 * Returns all the official type councils.
	 *
	 * @return the official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the official type councils.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the official type councils.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findAll(
		int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the official type councils.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of official type councils
	 */
	@Override
	public List<OfficialTypeCouncil> findAll(
		int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
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

		List<OfficialTypeCouncil> list = null;

		if (useFinderCache) {
			list = (List<OfficialTypeCouncil>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_OFFICIALTYPECOUNCIL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_OFFICIALTYPECOUNCIL;

				sql = sql.concat(OfficialTypeCouncilModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<OfficialTypeCouncil>)QueryUtil.list(
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
	 * Removes all the official type councils from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OfficialTypeCouncil officialTypeCouncil : findAll()) {
			remove(officialTypeCouncil);
		}
	}

	/**
	 * Returns the number of official type councils.
	 *
	 * @return the number of official type councils
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
					_SQL_COUNT_OFFICIALTYPECOUNCIL);

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
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return OfficialTypeCouncilModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the official type council persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			OfficialTypeCouncilModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			OfficialTypeCouncilModelImpl.UUID_COLUMN_BITMASK |
			OfficialTypeCouncilModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			OfficialTypeCouncilModelImpl.UUID_COLUMN_BITMASK |
			OfficialTypeCouncilModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByOfficialId = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOfficialId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByOfficialId = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOfficialId",
			new String[] {Long.class.getName()},
			OfficialTypeCouncilModelImpl.OFFICIALID_COLUMN_BITMASK);

		_finderPathCountByOfficialId = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOfficialId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByTypeId = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTypeId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByTypeId = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTypeId",
			new String[] {Long.class.getName()},
			OfficialTypeCouncilModelImpl.TYPEID_COLUMN_BITMASK);

		_finderPathCountByTypeId = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTypeId",
			new String[] {Long.class.getName()});

		_finderPathFetchByTypeIdAndOfficialId = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED,
			OfficialTypeCouncilImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByTypeIdAndOfficialId",
			new String[] {Long.class.getName(), Long.class.getName()},
			OfficialTypeCouncilModelImpl.TYPEID_COLUMN_BITMASK |
			OfficialTypeCouncilModelImpl.OFFICIALID_COLUMN_BITMASK);

		_finderPathCountByTypeIdAndOfficialId = new FinderPath(
			OfficialTypeCouncilModelImpl.ENTITY_CACHE_ENABLED,
			OfficialTypeCouncilModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByTypeIdAndOfficialId",
			new String[] {Long.class.getName(), Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(OfficialTypeCouncilImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_OFFICIALTYPECOUNCIL =
		"SELECT officialTypeCouncil FROM OfficialTypeCouncil officialTypeCouncil";

	private static final String _SQL_SELECT_OFFICIALTYPECOUNCIL_WHERE =
		"SELECT officialTypeCouncil FROM OfficialTypeCouncil officialTypeCouncil WHERE ";

	private static final String _SQL_COUNT_OFFICIALTYPECOUNCIL =
		"SELECT COUNT(officialTypeCouncil) FROM OfficialTypeCouncil officialTypeCouncil";

	private static final String _SQL_COUNT_OFFICIALTYPECOUNCIL_WHERE =
		"SELECT COUNT(officialTypeCouncil) FROM OfficialTypeCouncil officialTypeCouncil WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "officialTypeCouncil.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No OfficialTypeCouncil exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No OfficialTypeCouncil exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		OfficialTypeCouncilPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});
	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"officialId", "typeId"});

}