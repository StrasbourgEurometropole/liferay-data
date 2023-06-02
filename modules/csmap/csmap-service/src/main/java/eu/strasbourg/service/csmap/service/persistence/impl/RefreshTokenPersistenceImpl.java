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

package eu.strasbourg.service.csmap.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import eu.strasbourg.service.csmap.exception.NoSuchRefreshTokenException;
import eu.strasbourg.service.csmap.model.RefreshToken;
import eu.strasbourg.service.csmap.model.impl.RefreshTokenImpl;
import eu.strasbourg.service.csmap.model.impl.RefreshTokenModelImpl;
import eu.strasbourg.service.csmap.service.persistence.RefreshTokenPersistence;
import eu.strasbourg.service.csmap.service.persistence.impl.constants.csmapPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the refresh token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = RefreshTokenPersistence.class)
public class RefreshTokenPersistenceImpl
	extends BasePersistenceImpl<RefreshToken>
	implements RefreshTokenPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>RefreshTokenUtil</code> to access the refresh token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		RefreshTokenImpl.class.getName();

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
	 * Returns all the refresh tokens where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching refresh tokens
	 */
	@Override
	public List<RefreshToken> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the refresh tokens where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @return the range of matching refresh tokens
	 */
	@Override
	public List<RefreshToken> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the refresh tokens where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching refresh tokens
	 */
	@Override
	public List<RefreshToken> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RefreshToken> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the refresh tokens where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching refresh tokens
	 */
	@Override
	public List<RefreshToken> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RefreshToken> orderByComparator,
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

		List<RefreshToken> list = null;

		if (useFinderCache) {
			list = (List<RefreshToken>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RefreshToken refreshToken : list) {
					if (!uuid.equals(refreshToken.getUuid())) {
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

			sb.append(_SQL_SELECT_REFRESHTOKEN_WHERE);

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
				sb.append(RefreshTokenModelImpl.ORDER_BY_JPQL);
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

				list = (List<RefreshToken>)QueryUtil.list(
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
	 * Returns the first refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	@Override
	public RefreshToken findByUuid_First(
			String uuid, OrderByComparator<RefreshToken> orderByComparator)
		throws NoSuchRefreshTokenException {

		RefreshToken refreshToken = fetchByUuid_First(uuid, orderByComparator);

		if (refreshToken != null) {
			return refreshToken;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchRefreshTokenException(sb.toString());
	}

	/**
	 * Returns the first refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	@Override
	public RefreshToken fetchByUuid_First(
		String uuid, OrderByComparator<RefreshToken> orderByComparator) {

		List<RefreshToken> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	@Override
	public RefreshToken findByUuid_Last(
			String uuid, OrderByComparator<RefreshToken> orderByComparator)
		throws NoSuchRefreshTokenException {

		RefreshToken refreshToken = fetchByUuid_Last(uuid, orderByComparator);

		if (refreshToken != null) {
			return refreshToken;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchRefreshTokenException(sb.toString());
	}

	/**
	 * Returns the last refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	@Override
	public RefreshToken fetchByUuid_Last(
		String uuid, OrderByComparator<RefreshToken> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<RefreshToken> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the refresh tokens before and after the current refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param refreshTokenId the primary key of the current refresh token
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next refresh token
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	@Override
	public RefreshToken[] findByUuid_PrevAndNext(
			long refreshTokenId, String uuid,
			OrderByComparator<RefreshToken> orderByComparator)
		throws NoSuchRefreshTokenException {

		uuid = Objects.toString(uuid, "");

		RefreshToken refreshToken = findByPrimaryKey(refreshTokenId);

		Session session = null;

		try {
			session = openSession();

			RefreshToken[] array = new RefreshTokenImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, refreshToken, uuid, orderByComparator, true);

			array[1] = refreshToken;

			array[2] = getByUuid_PrevAndNext(
				session, refreshToken, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected RefreshToken getByUuid_PrevAndNext(
		Session session, RefreshToken refreshToken, String uuid,
		OrderByComparator<RefreshToken> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_REFRESHTOKEN_WHERE);

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
			sb.append(RefreshTokenModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(refreshToken)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<RefreshToken> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the refresh tokens where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (RefreshToken refreshToken :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(refreshToken);
		}
	}

	/**
	 * Returns the number of refresh tokens where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching refresh tokens
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_REFRESHTOKEN_WHERE);

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
		"refreshToken.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(refreshToken.uuid IS NULL OR refreshToken.uuid = '')";

	private FinderPath _finderPathFetchByValue;
	private FinderPath _finderPathCountByValue;

	/**
	 * Returns the refresh token where value = &#63; or throws a <code>NoSuchRefreshTokenException</code> if it could not be found.
	 *
	 * @param value the value
	 * @return the matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	@Override
	public RefreshToken findByValue(String value)
		throws NoSuchRefreshTokenException {

		RefreshToken refreshToken = fetchByValue(value);

		if (refreshToken == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("value=");
			sb.append(value);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchRefreshTokenException(sb.toString());
		}

		return refreshToken;
	}

	/**
	 * Returns the refresh token where value = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param value the value
	 * @return the matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	@Override
	public RefreshToken fetchByValue(String value) {
		return fetchByValue(value, true);
	}

	/**
	 * Returns the refresh token where value = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param value the value
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	@Override
	public RefreshToken fetchByValue(String value, boolean useFinderCache) {
		value = Objects.toString(value, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {value};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByValue, finderArgs, this);
		}

		if (result instanceof RefreshToken) {
			RefreshToken refreshToken = (RefreshToken)result;

			if (!Objects.equals(value, refreshToken.getValue())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_REFRESHTOKEN_WHERE);

			boolean bindValue = false;

			if (value.isEmpty()) {
				sb.append(_FINDER_COLUMN_VALUE_VALUE_3);
			}
			else {
				bindValue = true;

				sb.append(_FINDER_COLUMN_VALUE_VALUE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindValue) {
					queryPos.add(value);
				}

				List<RefreshToken> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByValue, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {value};
							}

							_log.warn(
								"RefreshTokenPersistenceImpl.fetchByValue(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					RefreshToken refreshToken = list.get(0);

					result = refreshToken;

					cacheResult(refreshToken);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByValue, finderArgs);
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
			return (RefreshToken)result;
		}
	}

	/**
	 * Removes the refresh token where value = &#63; from the database.
	 *
	 * @param value the value
	 * @return the refresh token that was removed
	 */
	@Override
	public RefreshToken removeByValue(String value)
		throws NoSuchRefreshTokenException {

		RefreshToken refreshToken = findByValue(value);

		return remove(refreshToken);
	}

	/**
	 * Returns the number of refresh tokens where value = &#63;.
	 *
	 * @param value the value
	 * @return the number of matching refresh tokens
	 */
	@Override
	public int countByValue(String value) {
		value = Objects.toString(value, "");

		FinderPath finderPath = _finderPathCountByValue;

		Object[] finderArgs = new Object[] {value};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_REFRESHTOKEN_WHERE);

			boolean bindValue = false;

			if (value.isEmpty()) {
				sb.append(_FINDER_COLUMN_VALUE_VALUE_3);
			}
			else {
				bindValue = true;

				sb.append(_FINDER_COLUMN_VALUE_VALUE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindValue) {
					queryPos.add(value);
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

	private static final String _FINDER_COLUMN_VALUE_VALUE_2 =
		"refreshToken.value = ?";

	private static final String _FINDER_COLUMN_VALUE_VALUE_3 =
		"(refreshToken.value IS NULL OR refreshToken.value = '')";

	private FinderPath _finderPathWithPaginationFindByPublikId;
	private FinderPath _finderPathWithoutPaginationFindByPublikId;
	private FinderPath _finderPathCountByPublikId;

	/**
	 * Returns all the refresh tokens where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the matching refresh tokens
	 */
	@Override
	public List<RefreshToken> findByPublikId(String publikId) {
		return findByPublikId(
			publikId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the refresh tokens where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @return the range of matching refresh tokens
	 */
	@Override
	public List<RefreshToken> findByPublikId(
		String publikId, int start, int end) {

		return findByPublikId(publikId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the refresh tokens where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching refresh tokens
	 */
	@Override
	public List<RefreshToken> findByPublikId(
		String publikId, int start, int end,
		OrderByComparator<RefreshToken> orderByComparator) {

		return findByPublikId(publikId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the refresh tokens where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching refresh tokens
	 */
	@Override
	public List<RefreshToken> findByPublikId(
		String publikId, int start, int end,
		OrderByComparator<RefreshToken> orderByComparator,
		boolean useFinderCache) {

		publikId = Objects.toString(publikId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPublikId;
				finderArgs = new Object[] {publikId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPublikId;
			finderArgs = new Object[] {publikId, start, end, orderByComparator};
		}

		List<RefreshToken> list = null;

		if (useFinderCache) {
			list = (List<RefreshToken>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RefreshToken refreshToken : list) {
					if (!publikId.equals(refreshToken.getPublikId())) {
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

			sb.append(_SQL_SELECT_REFRESHTOKEN_WHERE);

			boolean bindPublikId = false;

			if (publikId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_3);
			}
			else {
				bindPublikId = true;

				sb.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(RefreshTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublikId) {
					queryPos.add(publikId);
				}

				list = (List<RefreshToken>)QueryUtil.list(
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
	 * Returns the first refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	@Override
	public RefreshToken findByPublikId_First(
			String publikId, OrderByComparator<RefreshToken> orderByComparator)
		throws NoSuchRefreshTokenException {

		RefreshToken refreshToken = fetchByPublikId_First(
			publikId, orderByComparator);

		if (refreshToken != null) {
			return refreshToken;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publikId=");
		sb.append(publikId);

		sb.append("}");

		throw new NoSuchRefreshTokenException(sb.toString());
	}

	/**
	 * Returns the first refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	@Override
	public RefreshToken fetchByPublikId_First(
		String publikId, OrderByComparator<RefreshToken> orderByComparator) {

		List<RefreshToken> list = findByPublikId(
			publikId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	@Override
	public RefreshToken findByPublikId_Last(
			String publikId, OrderByComparator<RefreshToken> orderByComparator)
		throws NoSuchRefreshTokenException {

		RefreshToken refreshToken = fetchByPublikId_Last(
			publikId, orderByComparator);

		if (refreshToken != null) {
			return refreshToken;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publikId=");
		sb.append(publikId);

		sb.append("}");

		throw new NoSuchRefreshTokenException(sb.toString());
	}

	/**
	 * Returns the last refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	@Override
	public RefreshToken fetchByPublikId_Last(
		String publikId, OrderByComparator<RefreshToken> orderByComparator) {

		int count = countByPublikId(publikId);

		if (count == 0) {
			return null;
		}

		List<RefreshToken> list = findByPublikId(
			publikId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the refresh tokens before and after the current refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param refreshTokenId the primary key of the current refresh token
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next refresh token
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	@Override
	public RefreshToken[] findByPublikId_PrevAndNext(
			long refreshTokenId, String publikId,
			OrderByComparator<RefreshToken> orderByComparator)
		throws NoSuchRefreshTokenException {

		publikId = Objects.toString(publikId, "");

		RefreshToken refreshToken = findByPrimaryKey(refreshTokenId);

		Session session = null;

		try {
			session = openSession();

			RefreshToken[] array = new RefreshTokenImpl[3];

			array[0] = getByPublikId_PrevAndNext(
				session, refreshToken, publikId, orderByComparator, true);

			array[1] = refreshToken;

			array[2] = getByPublikId_PrevAndNext(
				session, refreshToken, publikId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected RefreshToken getByPublikId_PrevAndNext(
		Session session, RefreshToken refreshToken, String publikId,
		OrderByComparator<RefreshToken> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_REFRESHTOKEN_WHERE);

		boolean bindPublikId = false;

		if (publikId.isEmpty()) {
			sb.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_3);
		}
		else {
			bindPublikId = true;

			sb.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_2);
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
			sb.append(RefreshTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindPublikId) {
			queryPos.add(publikId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(refreshToken)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<RefreshToken> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the refresh tokens where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 */
	@Override
	public void removeByPublikId(String publikId) {
		for (RefreshToken refreshToken :
				findByPublikId(
					publikId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(refreshToken);
		}
	}

	/**
	 * Returns the number of refresh tokens where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching refresh tokens
	 */
	@Override
	public int countByPublikId(String publikId) {
		publikId = Objects.toString(publikId, "");

		FinderPath finderPath = _finderPathCountByPublikId;

		Object[] finderArgs = new Object[] {publikId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_REFRESHTOKEN_WHERE);

			boolean bindPublikId = false;

			if (publikId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_3);
			}
			else {
				bindPublikId = true;

				sb.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublikId) {
					queryPos.add(publikId);
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

	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_2 =
		"refreshToken.publikId = ?";

	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_3 =
		"(refreshToken.publikId IS NULL OR refreshToken.publikId = '')";

	public RefreshTokenPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(RefreshToken.class);

		setModelImplClass(RefreshTokenImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the refresh token in the entity cache if it is enabled.
	 *
	 * @param refreshToken the refresh token
	 */
	@Override
	public void cacheResult(RefreshToken refreshToken) {
		entityCache.putResult(
			entityCacheEnabled, RefreshTokenImpl.class,
			refreshToken.getPrimaryKey(), refreshToken);

		finderCache.putResult(
			_finderPathFetchByValue, new Object[] {refreshToken.getValue()},
			refreshToken);

		refreshToken.resetOriginalValues();
	}

	/**
	 * Caches the refresh tokens in the entity cache if it is enabled.
	 *
	 * @param refreshTokens the refresh tokens
	 */
	@Override
	public void cacheResult(List<RefreshToken> refreshTokens) {
		for (RefreshToken refreshToken : refreshTokens) {
			if (entityCache.getResult(
					entityCacheEnabled, RefreshTokenImpl.class,
					refreshToken.getPrimaryKey()) == null) {

				cacheResult(refreshToken);
			}
			else {
				refreshToken.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all refresh tokens.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RefreshTokenImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the refresh token.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RefreshToken refreshToken) {
		entityCache.removeResult(
			entityCacheEnabled, RefreshTokenImpl.class,
			refreshToken.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((RefreshTokenModelImpl)refreshToken, true);
	}

	@Override
	public void clearCache(List<RefreshToken> refreshTokens) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RefreshToken refreshToken : refreshTokens) {
			entityCache.removeResult(
				entityCacheEnabled, RefreshTokenImpl.class,
				refreshToken.getPrimaryKey());

			clearUniqueFindersCache((RefreshTokenModelImpl)refreshToken, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, RefreshTokenImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		RefreshTokenModelImpl refreshTokenModelImpl) {

		Object[] args = new Object[] {refreshTokenModelImpl.getValue()};

		finderCache.putResult(
			_finderPathCountByValue, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByValue, args, refreshTokenModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		RefreshTokenModelImpl refreshTokenModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {refreshTokenModelImpl.getValue()};

			finderCache.removeResult(_finderPathCountByValue, args);
			finderCache.removeResult(_finderPathFetchByValue, args);
		}

		if ((refreshTokenModelImpl.getColumnBitmask() &
			 _finderPathFetchByValue.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				refreshTokenModelImpl.getOriginalValue()
			};

			finderCache.removeResult(_finderPathCountByValue, args);
			finderCache.removeResult(_finderPathFetchByValue, args);
		}
	}

	/**
	 * Creates a new refresh token with the primary key. Does not add the refresh token to the database.
	 *
	 * @param refreshTokenId the primary key for the new refresh token
	 * @return the new refresh token
	 */
	@Override
	public RefreshToken create(long refreshTokenId) {
		RefreshToken refreshToken = new RefreshTokenImpl();

		refreshToken.setNew(true);
		refreshToken.setPrimaryKey(refreshTokenId);

		String uuid = PortalUUIDUtil.generate();

		refreshToken.setUuid(uuid);

		return refreshToken;
	}

	/**
	 * Removes the refresh token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param refreshTokenId the primary key of the refresh token
	 * @return the refresh token that was removed
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	@Override
	public RefreshToken remove(long refreshTokenId)
		throws NoSuchRefreshTokenException {

		return remove((Serializable)refreshTokenId);
	}

	/**
	 * Removes the refresh token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the refresh token
	 * @return the refresh token that was removed
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	@Override
	public RefreshToken remove(Serializable primaryKey)
		throws NoSuchRefreshTokenException {

		Session session = null;

		try {
			session = openSession();

			RefreshToken refreshToken = (RefreshToken)session.get(
				RefreshTokenImpl.class, primaryKey);

			if (refreshToken == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRefreshTokenException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(refreshToken);
		}
		catch (NoSuchRefreshTokenException noSuchEntityException) {
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
	protected RefreshToken removeImpl(RefreshToken refreshToken) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(refreshToken)) {
				refreshToken = (RefreshToken)session.get(
					RefreshTokenImpl.class, refreshToken.getPrimaryKeyObj());
			}

			if (refreshToken != null) {
				session.delete(refreshToken);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (refreshToken != null) {
			clearCache(refreshToken);
		}

		return refreshToken;
	}

	@Override
	public RefreshToken updateImpl(RefreshToken refreshToken) {
		boolean isNew = refreshToken.isNew();

		if (!(refreshToken instanceof RefreshTokenModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(refreshToken.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					refreshToken);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in refreshToken proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom RefreshToken implementation " +
					refreshToken.getClass());
		}

		RefreshTokenModelImpl refreshTokenModelImpl =
			(RefreshTokenModelImpl)refreshToken;

		if (Validator.isNull(refreshToken.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			refreshToken.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (refreshToken.isNew()) {
				session.save(refreshToken);

				refreshToken.setNew(false);
			}
			else {
				refreshToken = (RefreshToken)session.merge(refreshToken);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {refreshTokenModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {refreshTokenModelImpl.getPublikId()};

			finderCache.removeResult(_finderPathCountByPublikId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPublikId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((refreshTokenModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					refreshTokenModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {refreshTokenModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((refreshTokenModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPublikId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					refreshTokenModelImpl.getOriginalPublikId()
				};

				finderCache.removeResult(_finderPathCountByPublikId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPublikId, args);

				args = new Object[] {refreshTokenModelImpl.getPublikId()};

				finderCache.removeResult(_finderPathCountByPublikId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPublikId, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, RefreshTokenImpl.class,
			refreshToken.getPrimaryKey(), refreshToken, false);

		clearUniqueFindersCache(refreshTokenModelImpl, false);
		cacheUniqueFindersCache(refreshTokenModelImpl);

		refreshToken.resetOriginalValues();

		return refreshToken;
	}

	/**
	 * Returns the refresh token with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the refresh token
	 * @return the refresh token
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	@Override
	public RefreshToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRefreshTokenException {

		RefreshToken refreshToken = fetchByPrimaryKey(primaryKey);

		if (refreshToken == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRefreshTokenException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return refreshToken;
	}

	/**
	 * Returns the refresh token with the primary key or throws a <code>NoSuchRefreshTokenException</code> if it could not be found.
	 *
	 * @param refreshTokenId the primary key of the refresh token
	 * @return the refresh token
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	@Override
	public RefreshToken findByPrimaryKey(long refreshTokenId)
		throws NoSuchRefreshTokenException {

		return findByPrimaryKey((Serializable)refreshTokenId);
	}

	/**
	 * Returns the refresh token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param refreshTokenId the primary key of the refresh token
	 * @return the refresh token, or <code>null</code> if a refresh token with the primary key could not be found
	 */
	@Override
	public RefreshToken fetchByPrimaryKey(long refreshTokenId) {
		return fetchByPrimaryKey((Serializable)refreshTokenId);
	}

	/**
	 * Returns all the refresh tokens.
	 *
	 * @return the refresh tokens
	 */
	@Override
	public List<RefreshToken> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the refresh tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @return the range of refresh tokens
	 */
	@Override
	public List<RefreshToken> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the refresh tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of refresh tokens
	 */
	@Override
	public List<RefreshToken> findAll(
		int start, int end, OrderByComparator<RefreshToken> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the refresh tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of refresh tokens
	 */
	@Override
	public List<RefreshToken> findAll(
		int start, int end, OrderByComparator<RefreshToken> orderByComparator,
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

		List<RefreshToken> list = null;

		if (useFinderCache) {
			list = (List<RefreshToken>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_REFRESHTOKEN);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_REFRESHTOKEN;

				sql = sql.concat(RefreshTokenModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<RefreshToken>)QueryUtil.list(
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
	 * Removes all the refresh tokens from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RefreshToken refreshToken : findAll()) {
			remove(refreshToken);
		}
	}

	/**
	 * Returns the number of refresh tokens.
	 *
	 * @return the number of refresh tokens
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_REFRESHTOKEN);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "refreshTokenId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_REFRESHTOKEN;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return RefreshTokenModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the refresh token persistence.
	 */
	@Activate
	public void activate() {
		RefreshTokenModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		RefreshTokenModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, RefreshTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, RefreshTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, RefreshTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, RefreshTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			RefreshTokenModelImpl.UUID_COLUMN_BITMASK |
			RefreshTokenModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByValue = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, RefreshTokenImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByValue",
			new String[] {String.class.getName()},
			RefreshTokenModelImpl.VALUE_COLUMN_BITMASK);

		_finderPathCountByValue = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByValue",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByPublikId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, RefreshTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPublikId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPublikId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, RefreshTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikId",
			new String[] {String.class.getName()},
			RefreshTokenModelImpl.PUBLIKID_COLUMN_BITMASK |
			RefreshTokenModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByPublikId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikId",
			new String[] {String.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(RefreshTokenImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = csmapPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.eu.strasbourg.service.csmap.model.RefreshToken"),
			true);
	}

	@Override
	@Reference(
		target = csmapPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = csmapPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_REFRESHTOKEN =
		"SELECT refreshToken FROM RefreshToken refreshToken";

	private static final String _SQL_SELECT_REFRESHTOKEN_WHERE =
		"SELECT refreshToken FROM RefreshToken refreshToken WHERE ";

	private static final String _SQL_COUNT_REFRESHTOKEN =
		"SELECT COUNT(refreshToken) FROM RefreshToken refreshToken";

	private static final String _SQL_COUNT_REFRESHTOKEN_WHERE =
		"SELECT COUNT(refreshToken) FROM RefreshToken refreshToken WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "refreshToken.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No RefreshToken exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No RefreshToken exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		RefreshTokenPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	static {
		try {
			Class.forName(csmapPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

}