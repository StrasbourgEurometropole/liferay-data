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

import eu.strasbourg.service.csmap.exception.NoSuchNonceException;
import eu.strasbourg.service.csmap.model.Nonce;
import eu.strasbourg.service.csmap.model.impl.NonceImpl;
import eu.strasbourg.service.csmap.model.impl.NonceModelImpl;
import eu.strasbourg.service.csmap.service.persistence.NoncePersistence;
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

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the nonce service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = NoncePersistence.class)
@ProviderType
public class NoncePersistenceImpl
	extends BasePersistenceImpl<Nonce> implements NoncePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>NonceUtil</code> to access the nonce persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		NonceImpl.class.getName();

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
	 * Returns all the nonces where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching nonces
	 */
	@Override
	public List<Nonce> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the nonces where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of nonces
	 * @param end the upper bound of the range of nonces (not inclusive)
	 * @return the range of matching nonces
	 */
	@Override
	public List<Nonce> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the nonces where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of nonces
	 * @param end the upper bound of the range of nonces (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching nonces
	 */
	@Override
	public List<Nonce> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Nonce> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the nonces where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of nonces
	 * @param end the upper bound of the range of nonces (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching nonces
	 */
	@Override
	public List<Nonce> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Nonce> orderByComparator, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid;
			finderArgs = new Object[] {uuid};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<Nonce> list = null;

		if (retrieveFromCache) {
			list = (List<Nonce>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Nonce nonce : list) {
					if (!uuid.equals(nonce.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_NONCE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(NonceModelImpl.ORDER_BY_JPQL);
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
					list = (List<Nonce>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Nonce>)QueryUtil.list(
						q, getDialect(), start, end);
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
	 * Returns the first nonce in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching nonce
	 * @throws NoSuchNonceException if a matching nonce could not be found
	 */
	@Override
	public Nonce findByUuid_First(
			String uuid, OrderByComparator<Nonce> orderByComparator)
		throws NoSuchNonceException {

		Nonce nonce = fetchByUuid_First(uuid, orderByComparator);

		if (nonce != null) {
			return nonce;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchNonceException(msg.toString());
	}

	/**
	 * Returns the first nonce in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching nonce, or <code>null</code> if a matching nonce could not be found
	 */
	@Override
	public Nonce fetchByUuid_First(
		String uuid, OrderByComparator<Nonce> orderByComparator) {

		List<Nonce> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last nonce in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching nonce
	 * @throws NoSuchNonceException if a matching nonce could not be found
	 */
	@Override
	public Nonce findByUuid_Last(
			String uuid, OrderByComparator<Nonce> orderByComparator)
		throws NoSuchNonceException {

		Nonce nonce = fetchByUuid_Last(uuid, orderByComparator);

		if (nonce != null) {
			return nonce;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchNonceException(msg.toString());
	}

	/**
	 * Returns the last nonce in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching nonce, or <code>null</code> if a matching nonce could not be found
	 */
	@Override
	public Nonce fetchByUuid_Last(
		String uuid, OrderByComparator<Nonce> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Nonce> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the nonces before and after the current nonce in the ordered set where uuid = &#63;.
	 *
	 * @param nonceId the primary key of the current nonce
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next nonce
	 * @throws NoSuchNonceException if a nonce with the primary key could not be found
	 */
	@Override
	public Nonce[] findByUuid_PrevAndNext(
			long nonceId, String uuid,
			OrderByComparator<Nonce> orderByComparator)
		throws NoSuchNonceException {

		uuid = Objects.toString(uuid, "");

		Nonce nonce = findByPrimaryKey(nonceId);

		Session session = null;

		try {
			session = openSession();

			Nonce[] array = new NonceImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, nonce, uuid, orderByComparator, true);

			array[1] = nonce;

			array[2] = getByUuid_PrevAndNext(
				session, nonce, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Nonce getByUuid_PrevAndNext(
		Session session, Nonce nonce, String uuid,
		OrderByComparator<Nonce> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NONCE_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

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
			query.append(NonceModelImpl.ORDER_BY_JPQL);
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
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(nonce)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Nonce> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the nonces where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Nonce nonce :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(nonce);
		}
	}

	/**
	 * Returns the number of nonces where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching nonces
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NONCE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
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

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "nonce.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(nonce.uuid IS NULL OR nonce.uuid = '')";

	private FinderPath _finderPathFetchByValue;
	private FinderPath _finderPathCountByValue;

	/**
	 * Returns the nonce where value = &#63; or throws a <code>NoSuchNonceException</code> if it could not be found.
	 *
	 * @param value the value
	 * @return the matching nonce
	 * @throws NoSuchNonceException if a matching nonce could not be found
	 */
	@Override
	public Nonce findByValue(String value) throws NoSuchNonceException {
		Nonce nonce = fetchByValue(value);

		if (nonce == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("value=");
			msg.append(value);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchNonceException(msg.toString());
		}

		return nonce;
	}

	/**
	 * Returns the nonce where value = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param value the value
	 * @return the matching nonce, or <code>null</code> if a matching nonce could not be found
	 */
	@Override
	public Nonce fetchByValue(String value) {
		return fetchByValue(value, true);
	}

	/**
	 * Returns the nonce where value = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param value the value
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching nonce, or <code>null</code> if a matching nonce could not be found
	 */
	@Override
	public Nonce fetchByValue(String value, boolean retrieveFromCache) {
		value = Objects.toString(value, "");

		Object[] finderArgs = new Object[] {value};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByValue, finderArgs, this);
		}

		if (result instanceof Nonce) {
			Nonce nonce = (Nonce)result;

			if (!Objects.equals(value, nonce.getValue())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_NONCE_WHERE);

			boolean bindValue = false;

			if (value.isEmpty()) {
				query.append(_FINDER_COLUMN_VALUE_VALUE_3);
			}
			else {
				bindValue = true;

				query.append(_FINDER_COLUMN_VALUE_VALUE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindValue) {
					qPos.add(value);
				}

				List<Nonce> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByValue, finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"NoncePersistenceImpl.fetchByValue(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Nonce nonce = list.get(0);

					result = nonce;

					cacheResult(nonce);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByValue, finderArgs);

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
			return (Nonce)result;
		}
	}

	/**
	 * Removes the nonce where value = &#63; from the database.
	 *
	 * @param value the value
	 * @return the nonce that was removed
	 */
	@Override
	public Nonce removeByValue(String value) throws NoSuchNonceException {
		Nonce nonce = findByValue(value);

		return remove(nonce);
	}

	/**
	 * Returns the number of nonces where value = &#63;.
	 *
	 * @param value the value
	 * @return the number of matching nonces
	 */
	@Override
	public int countByValue(String value) {
		value = Objects.toString(value, "");

		FinderPath finderPath = _finderPathCountByValue;

		Object[] finderArgs = new Object[] {value};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NONCE_WHERE);

			boolean bindValue = false;

			if (value.isEmpty()) {
				query.append(_FINDER_COLUMN_VALUE_VALUE_3);
			}
			else {
				bindValue = true;

				query.append(_FINDER_COLUMN_VALUE_VALUE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindValue) {
					qPos.add(value);
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

	private static final String _FINDER_COLUMN_VALUE_VALUE_2 =
		"nonce.value = ?";

	private static final String _FINDER_COLUMN_VALUE_VALUE_3 =
		"(nonce.value IS NULL OR nonce.value = '')";

	public NoncePersistenceImpl() {
		setModelClass(Nonce.class);

		setModelImplClass(NonceImpl.class);
		setModelPKClass(long.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the nonce in the entity cache if it is enabled.
	 *
	 * @param nonce the nonce
	 */
	@Override
	public void cacheResult(Nonce nonce) {
		entityCache.putResult(
			entityCacheEnabled, NonceImpl.class, nonce.getPrimaryKey(), nonce);

		finderCache.putResult(
			_finderPathFetchByValue, new Object[] {nonce.getValue()}, nonce);

		nonce.resetOriginalValues();
	}

	/**
	 * Caches the nonces in the entity cache if it is enabled.
	 *
	 * @param nonces the nonces
	 */
	@Override
	public void cacheResult(List<Nonce> nonces) {
		for (Nonce nonce : nonces) {
			if (entityCache.getResult(
					entityCacheEnabled, NonceImpl.class,
					nonce.getPrimaryKey()) == null) {

				cacheResult(nonce);
			}
			else {
				nonce.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all nonces.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NonceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the nonce.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Nonce nonce) {
		entityCache.removeResult(
			entityCacheEnabled, NonceImpl.class, nonce.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((NonceModelImpl)nonce, true);
	}

	@Override
	public void clearCache(List<Nonce> nonces) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Nonce nonce : nonces) {
			entityCache.removeResult(
				entityCacheEnabled, NonceImpl.class, nonce.getPrimaryKey());

			clearUniqueFindersCache((NonceModelImpl)nonce, true);
		}
	}

	protected void cacheUniqueFindersCache(NonceModelImpl nonceModelImpl) {
		Object[] args = new Object[] {nonceModelImpl.getValue()};

		finderCache.putResult(
			_finderPathCountByValue, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByValue, args, nonceModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		NonceModelImpl nonceModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {nonceModelImpl.getValue()};

			finderCache.removeResult(_finderPathCountByValue, args);
			finderCache.removeResult(_finderPathFetchByValue, args);
		}

		if ((nonceModelImpl.getColumnBitmask() &
			 _finderPathFetchByValue.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {nonceModelImpl.getOriginalValue()};

			finderCache.removeResult(_finderPathCountByValue, args);
			finderCache.removeResult(_finderPathFetchByValue, args);
		}
	}

	/**
	 * Creates a new nonce with the primary key. Does not add the nonce to the database.
	 *
	 * @param nonceId the primary key for the new nonce
	 * @return the new nonce
	 */
	@Override
	public Nonce create(long nonceId) {
		Nonce nonce = new NonceImpl();

		nonce.setNew(true);
		nonce.setPrimaryKey(nonceId);

		String uuid = PortalUUIDUtil.generate();

		nonce.setUuid(uuid);

		return nonce;
	}

	/**
	 * Removes the nonce with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nonceId the primary key of the nonce
	 * @return the nonce that was removed
	 * @throws NoSuchNonceException if a nonce with the primary key could not be found
	 */
	@Override
	public Nonce remove(long nonceId) throws NoSuchNonceException {
		return remove((Serializable)nonceId);
	}

	/**
	 * Removes the nonce with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the nonce
	 * @return the nonce that was removed
	 * @throws NoSuchNonceException if a nonce with the primary key could not be found
	 */
	@Override
	public Nonce remove(Serializable primaryKey) throws NoSuchNonceException {
		Session session = null;

		try {
			session = openSession();

			Nonce nonce = (Nonce)session.get(NonceImpl.class, primaryKey);

			if (nonce == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNonceException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(nonce);
		}
		catch (NoSuchNonceException nsee) {
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
	protected Nonce removeImpl(Nonce nonce) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nonce)) {
				nonce = (Nonce)session.get(
					NonceImpl.class, nonce.getPrimaryKeyObj());
			}

			if (nonce != null) {
				session.delete(nonce);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (nonce != null) {
			clearCache(nonce);
		}

		return nonce;
	}

	@Override
	public Nonce updateImpl(Nonce nonce) {
		boolean isNew = nonce.isNew();

		if (!(nonce instanceof NonceModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(nonce.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(nonce);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in nonce proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Nonce implementation " +
					nonce.getClass());
		}

		NonceModelImpl nonceModelImpl = (NonceModelImpl)nonce;

		if (Validator.isNull(nonce.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			nonce.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (nonce.isNew()) {
				session.save(nonce);

				nonce.setNew(false);
			}
			else {
				nonce = (Nonce)session.merge(nonce);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {nonceModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((nonceModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {nonceModelImpl.getOriginalUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {nonceModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, NonceImpl.class, nonce.getPrimaryKey(), nonce,
			false);

		clearUniqueFindersCache(nonceModelImpl, false);
		cacheUniqueFindersCache(nonceModelImpl);

		nonce.resetOriginalValues();

		return nonce;
	}

	/**
	 * Returns the nonce with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the nonce
	 * @return the nonce
	 * @throws NoSuchNonceException if a nonce with the primary key could not be found
	 */
	@Override
	public Nonce findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNonceException {

		Nonce nonce = fetchByPrimaryKey(primaryKey);

		if (nonce == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNonceException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return nonce;
	}

	/**
	 * Returns the nonce with the primary key or throws a <code>NoSuchNonceException</code> if it could not be found.
	 *
	 * @param nonceId the primary key of the nonce
	 * @return the nonce
	 * @throws NoSuchNonceException if a nonce with the primary key could not be found
	 */
	@Override
	public Nonce findByPrimaryKey(long nonceId) throws NoSuchNonceException {
		return findByPrimaryKey((Serializable)nonceId);
	}

	/**
	 * Returns the nonce with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nonceId the primary key of the nonce
	 * @return the nonce, or <code>null</code> if a nonce with the primary key could not be found
	 */
	@Override
	public Nonce fetchByPrimaryKey(long nonceId) {
		return fetchByPrimaryKey((Serializable)nonceId);
	}

	/**
	 * Returns all the nonces.
	 *
	 * @return the nonces
	 */
	@Override
	public List<Nonce> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nonces
	 * @param end the upper bound of the range of nonces (not inclusive)
	 * @return the range of nonces
	 */
	@Override
	public List<Nonce> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nonces
	 * @param end the upper bound of the range of nonces (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nonces
	 */
	@Override
	public List<Nonce> findAll(
		int start, int end, OrderByComparator<Nonce> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nonces
	 * @param end the upper bound of the range of nonces (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of nonces
	 */
	@Override
	public List<Nonce> findAll(
		int start, int end, OrderByComparator<Nonce> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Nonce> list = null;

		if (retrieveFromCache) {
			list = (List<Nonce>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NONCE);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NONCE;

				if (pagination) {
					sql = sql.concat(NonceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Nonce>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Nonce>)QueryUtil.list(
						q, getDialect(), start, end);
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
	 * Removes all the nonces from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Nonce nonce : findAll()) {
			remove(nonce);
		}
	}

	/**
	 * Returns the number of nonces.
	 *
	 * @return the number of nonces
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NONCE);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "nonceId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_NONCE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return NonceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the nonce persistence.
	 */
	@Activate
	public void activate() {
		NonceModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		NonceModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, NonceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, NonceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, NonceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, NonceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			NonceModelImpl.UUID_COLUMN_BITMASK |
			NonceModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByValue = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, NonceImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByValue",
			new String[] {String.class.getName()},
			NonceModelImpl.VALUE_COLUMN_BITMASK);

		_finderPathCountByValue = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByValue",
			new String[] {String.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(NonceImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = csmapPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.eu.strasbourg.service.csmap.model.Nonce"),
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

	private static final String _SQL_SELECT_NONCE =
		"SELECT nonce FROM Nonce nonce";

	private static final String _SQL_SELECT_NONCE_WHERE =
		"SELECT nonce FROM Nonce nonce WHERE ";

	private static final String _SQL_COUNT_NONCE =
		"SELECT COUNT(nonce) FROM Nonce nonce";

	private static final String _SQL_COUNT_NONCE_WHERE =
		"SELECT COUNT(nonce) FROM Nonce nonce WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "nonce.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Nonce exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Nonce exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		NoncePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}