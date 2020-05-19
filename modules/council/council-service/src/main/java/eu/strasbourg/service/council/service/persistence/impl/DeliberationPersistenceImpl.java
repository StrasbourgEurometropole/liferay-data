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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.council.exception.NoSuchDeliberationException;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.impl.DeliberationImpl;
import eu.strasbourg.service.council.model.impl.DeliberationModelImpl;
import eu.strasbourg.service.council.service.persistence.DeliberationPersistence;

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
 * The persistence implementation for the deliberation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class DeliberationPersistenceImpl
	extends BasePersistenceImpl<Deliberation>
	implements DeliberationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DeliberationUtil</code> to access the deliberation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DeliberationImpl.class.getName();

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
	 * Returns all the deliberations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching deliberations
	 */
	@Override
	public List<Deliberation> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliberations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DeliberationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @return the range of matching deliberations
	 */
	@Override
	public List<Deliberation> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliberations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DeliberationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching deliberations
	 */
	@Override
	public List<Deliberation> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Deliberation> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliberations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DeliberationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching deliberations
	 */
	@Override
	public List<Deliberation> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Deliberation> orderByComparator,
		boolean retrieveFromCache) {

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

		List<Deliberation> list = null;

		if (retrieveFromCache) {
			list = (List<Deliberation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Deliberation deliberation : list) {
					if (!uuid.equals(deliberation.getUuid())) {
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

			query.append(_SQL_SELECT_DELIBERATION_WHERE);

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
				query.append(DeliberationModelImpl.ORDER_BY_JPQL);
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
					list = (List<Deliberation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Deliberation>)QueryUtil.list(
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
	 * Returns the first deliberation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliberation
	 * @throws NoSuchDeliberationException if a matching deliberation could not be found
	 */
	@Override
	public Deliberation findByUuid_First(
			String uuid, OrderByComparator<Deliberation> orderByComparator)
		throws NoSuchDeliberationException {

		Deliberation deliberation = fetchByUuid_First(uuid, orderByComparator);

		if (deliberation != null) {
			return deliberation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDeliberationException(msg.toString());
	}

	/**
	 * Returns the first deliberation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliberation, or <code>null</code> if a matching deliberation could not be found
	 */
	@Override
	public Deliberation fetchByUuid_First(
		String uuid, OrderByComparator<Deliberation> orderByComparator) {

		List<Deliberation> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last deliberation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliberation
	 * @throws NoSuchDeliberationException if a matching deliberation could not be found
	 */
	@Override
	public Deliberation findByUuid_Last(
			String uuid, OrderByComparator<Deliberation> orderByComparator)
		throws NoSuchDeliberationException {

		Deliberation deliberation = fetchByUuid_Last(uuid, orderByComparator);

		if (deliberation != null) {
			return deliberation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDeliberationException(msg.toString());
	}

	/**
	 * Returns the last deliberation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliberation, or <code>null</code> if a matching deliberation could not be found
	 */
	@Override
	public Deliberation fetchByUuid_Last(
		String uuid, OrderByComparator<Deliberation> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Deliberation> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the deliberations before and after the current deliberation in the ordered set where uuid = &#63;.
	 *
	 * @param deliberationId the primary key of the current deliberation
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next deliberation
	 * @throws NoSuchDeliberationException if a deliberation with the primary key could not be found
	 */
	@Override
	public Deliberation[] findByUuid_PrevAndNext(
			long deliberationId, String uuid,
			OrderByComparator<Deliberation> orderByComparator)
		throws NoSuchDeliberationException {

		uuid = Objects.toString(uuid, "");

		Deliberation deliberation = findByPrimaryKey(deliberationId);

		Session session = null;

		try {
			session = openSession();

			Deliberation[] array = new DeliberationImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, deliberation, uuid, orderByComparator, true);

			array[1] = deliberation;

			array[2] = getByUuid_PrevAndNext(
				session, deliberation, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Deliberation getByUuid_PrevAndNext(
		Session session, Deliberation deliberation, String uuid,
		OrderByComparator<Deliberation> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DELIBERATION_WHERE);

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
			query.append(DeliberationModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(deliberation)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Deliberation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the deliberations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Deliberation deliberation :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(deliberation);
		}
	}

	/**
	 * Returns the number of deliberations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching deliberations
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DELIBERATION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"deliberation.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(deliberation.uuid IS NULL OR deliberation.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the deliberation where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchDeliberationException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching deliberation
	 * @throws NoSuchDeliberationException if a matching deliberation could not be found
	 */
	@Override
	public Deliberation findByUUID_G(String uuid, long groupId)
		throws NoSuchDeliberationException {

		Deliberation deliberation = fetchByUUID_G(uuid, groupId);

		if (deliberation == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDeliberationException(msg.toString());
		}

		return deliberation;
	}

	/**
	 * Returns the deliberation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching deliberation, or <code>null</code> if a matching deliberation could not be found
	 */
	@Override
	public Deliberation fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the deliberation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching deliberation, or <code>null</code> if a matching deliberation could not be found
	 */
	@Override
	public Deliberation fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] {uuid, groupId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof Deliberation) {
			Deliberation deliberation = (Deliberation)result;

			if (!Objects.equals(uuid, deliberation.getUuid()) ||
				(groupId != deliberation.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DELIBERATION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
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

				List<Deliberation> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByUUID_G, finderArgs, list);
				}
				else {
					Deliberation deliberation = list.get(0);

					result = deliberation;

					cacheResult(deliberation);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByUUID_G, finderArgs);

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
			return (Deliberation)result;
		}
	}

	/**
	 * Removes the deliberation where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the deliberation that was removed
	 */
	@Override
	public Deliberation removeByUUID_G(String uuid, long groupId)
		throws NoSuchDeliberationException {

		Deliberation deliberation = findByUUID_G(uuid, groupId);

		return remove(deliberation);
	}

	/**
	 * Returns the number of deliberations where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching deliberations
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DELIBERATION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
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

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"deliberation.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(deliberation.uuid IS NULL OR deliberation.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"deliberation.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the deliberations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching deliberations
	 */
	@Override
	public List<Deliberation> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliberations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DeliberationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @return the range of matching deliberations
	 */
	@Override
	public List<Deliberation> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliberations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DeliberationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching deliberations
	 */
	@Override
	public List<Deliberation> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Deliberation> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliberations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DeliberationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching deliberations
	 */
	@Override
	public List<Deliberation> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Deliberation> orderByComparator,
		boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid_C;
			finderArgs = new Object[] {uuid, companyId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<Deliberation> list = null;

		if (retrieveFromCache) {
			list = (List<Deliberation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Deliberation deliberation : list) {
					if (!uuid.equals(deliberation.getUuid()) ||
						(companyId != deliberation.getCompanyId())) {

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
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DELIBERATION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(DeliberationModelImpl.ORDER_BY_JPQL);
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
					list = (List<Deliberation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Deliberation>)QueryUtil.list(
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
	 * Returns the first deliberation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliberation
	 * @throws NoSuchDeliberationException if a matching deliberation could not be found
	 */
	@Override
	public Deliberation findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Deliberation> orderByComparator)
		throws NoSuchDeliberationException {

		Deliberation deliberation = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (deliberation != null) {
			return deliberation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDeliberationException(msg.toString());
	}

	/**
	 * Returns the first deliberation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliberation, or <code>null</code> if a matching deliberation could not be found
	 */
	@Override
	public Deliberation fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Deliberation> orderByComparator) {

		List<Deliberation> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last deliberation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliberation
	 * @throws NoSuchDeliberationException if a matching deliberation could not be found
	 */
	@Override
	public Deliberation findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Deliberation> orderByComparator)
		throws NoSuchDeliberationException {

		Deliberation deliberation = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (deliberation != null) {
			return deliberation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDeliberationException(msg.toString());
	}

	/**
	 * Returns the last deliberation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliberation, or <code>null</code> if a matching deliberation could not be found
	 */
	@Override
	public Deliberation fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Deliberation> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Deliberation> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the deliberations before and after the current deliberation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param deliberationId the primary key of the current deliberation
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next deliberation
	 * @throws NoSuchDeliberationException if a deliberation with the primary key could not be found
	 */
	@Override
	public Deliberation[] findByUuid_C_PrevAndNext(
			long deliberationId, String uuid, long companyId,
			OrderByComparator<Deliberation> orderByComparator)
		throws NoSuchDeliberationException {

		uuid = Objects.toString(uuid, "");

		Deliberation deliberation = findByPrimaryKey(deliberationId);

		Session session = null;

		try {
			session = openSession();

			Deliberation[] array = new DeliberationImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, deliberation, uuid, companyId, orderByComparator,
				true);

			array[1] = deliberation;

			array[2] = getByUuid_C_PrevAndNext(
				session, deliberation, uuid, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Deliberation getByUuid_C_PrevAndNext(
		Session session, Deliberation deliberation, String uuid, long companyId,
		OrderByComparator<Deliberation> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DELIBERATION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(DeliberationModelImpl.ORDER_BY_JPQL);
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
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(deliberation)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Deliberation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the deliberations where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Deliberation deliberation :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(deliberation);
		}
	}

	/**
	 * Returns the number of deliberations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching deliberations
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DELIBERATION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"deliberation.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(deliberation.uuid IS NULL OR deliberation.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"deliberation.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCouncilSessionId;
	private FinderPath _finderPathWithoutPaginationFindByCouncilSessionId;
	private FinderPath _finderPathCountByCouncilSessionId;

	/**
	 * Returns all the deliberations where councilSessionId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @return the matching deliberations
	 */
	@Override
	public List<Deliberation> findByCouncilSessionId(long councilSessionId) {
		return findByCouncilSessionId(
			councilSessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliberations where councilSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DeliberationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param councilSessionId the council session ID
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @return the range of matching deliberations
	 */
	@Override
	public List<Deliberation> findByCouncilSessionId(
		long councilSessionId, int start, int end) {

		return findByCouncilSessionId(councilSessionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliberations where councilSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DeliberationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param councilSessionId the council session ID
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching deliberations
	 */
	@Override
	public List<Deliberation> findByCouncilSessionId(
		long councilSessionId, int start, int end,
		OrderByComparator<Deliberation> orderByComparator) {

		return findByCouncilSessionId(
			councilSessionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliberations where councilSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DeliberationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param councilSessionId the council session ID
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching deliberations
	 */
	@Override
	public List<Deliberation> findByCouncilSessionId(
		long councilSessionId, int start, int end,
		OrderByComparator<Deliberation> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCouncilSessionId;
			finderArgs = new Object[] {councilSessionId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCouncilSessionId;
			finderArgs = new Object[] {
				councilSessionId, start, end, orderByComparator
			};
		}

		List<Deliberation> list = null;

		if (retrieveFromCache) {
			list = (List<Deliberation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Deliberation deliberation : list) {
					if ((councilSessionId !=
							deliberation.getCouncilSessionId())) {

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

			query.append(_SQL_SELECT_DELIBERATION_WHERE);

			query.append(_FINDER_COLUMN_COUNCILSESSIONID_COUNCILSESSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(DeliberationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(councilSessionId);

				if (!pagination) {
					list = (List<Deliberation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Deliberation>)QueryUtil.list(
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
	 * Returns the first deliberation in the ordered set where councilSessionId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliberation
	 * @throws NoSuchDeliberationException if a matching deliberation could not be found
	 */
	@Override
	public Deliberation findByCouncilSessionId_First(
			long councilSessionId,
			OrderByComparator<Deliberation> orderByComparator)
		throws NoSuchDeliberationException {

		Deliberation deliberation = fetchByCouncilSessionId_First(
			councilSessionId, orderByComparator);

		if (deliberation != null) {
			return deliberation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("councilSessionId=");
		msg.append(councilSessionId);

		msg.append("}");

		throw new NoSuchDeliberationException(msg.toString());
	}

	/**
	 * Returns the first deliberation in the ordered set where councilSessionId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliberation, or <code>null</code> if a matching deliberation could not be found
	 */
	@Override
	public Deliberation fetchByCouncilSessionId_First(
		long councilSessionId,
		OrderByComparator<Deliberation> orderByComparator) {

		List<Deliberation> list = findByCouncilSessionId(
			councilSessionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last deliberation in the ordered set where councilSessionId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliberation
	 * @throws NoSuchDeliberationException if a matching deliberation could not be found
	 */
	@Override
	public Deliberation findByCouncilSessionId_Last(
			long councilSessionId,
			OrderByComparator<Deliberation> orderByComparator)
		throws NoSuchDeliberationException {

		Deliberation deliberation = fetchByCouncilSessionId_Last(
			councilSessionId, orderByComparator);

		if (deliberation != null) {
			return deliberation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("councilSessionId=");
		msg.append(councilSessionId);

		msg.append("}");

		throw new NoSuchDeliberationException(msg.toString());
	}

	/**
	 * Returns the last deliberation in the ordered set where councilSessionId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliberation, or <code>null</code> if a matching deliberation could not be found
	 */
	@Override
	public Deliberation fetchByCouncilSessionId_Last(
		long councilSessionId,
		OrderByComparator<Deliberation> orderByComparator) {

		int count = countByCouncilSessionId(councilSessionId);

		if (count == 0) {
			return null;
		}

		List<Deliberation> list = findByCouncilSessionId(
			councilSessionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the deliberations before and after the current deliberation in the ordered set where councilSessionId = &#63;.
	 *
	 * @param deliberationId the primary key of the current deliberation
	 * @param councilSessionId the council session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next deliberation
	 * @throws NoSuchDeliberationException if a deliberation with the primary key could not be found
	 */
	@Override
	public Deliberation[] findByCouncilSessionId_PrevAndNext(
			long deliberationId, long councilSessionId,
			OrderByComparator<Deliberation> orderByComparator)
		throws NoSuchDeliberationException {

		Deliberation deliberation = findByPrimaryKey(deliberationId);

		Session session = null;

		try {
			session = openSession();

			Deliberation[] array = new DeliberationImpl[3];

			array[0] = getByCouncilSessionId_PrevAndNext(
				session, deliberation, councilSessionId, orderByComparator,
				true);

			array[1] = deliberation;

			array[2] = getByCouncilSessionId_PrevAndNext(
				session, deliberation, councilSessionId, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Deliberation getByCouncilSessionId_PrevAndNext(
		Session session, Deliberation deliberation, long councilSessionId,
		OrderByComparator<Deliberation> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DELIBERATION_WHERE);

		query.append(_FINDER_COLUMN_COUNCILSESSIONID_COUNCILSESSIONID_2);

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
			query.append(DeliberationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(councilSessionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(deliberation)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Deliberation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the deliberations where councilSessionId = &#63; from the database.
	 *
	 * @param councilSessionId the council session ID
	 */
	@Override
	public void removeByCouncilSessionId(long councilSessionId) {
		for (Deliberation deliberation :
				findByCouncilSessionId(
					councilSessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(deliberation);
		}
	}

	/**
	 * Returns the number of deliberations where councilSessionId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @return the number of matching deliberations
	 */
	@Override
	public int countByCouncilSessionId(long councilSessionId) {
		FinderPath finderPath = _finderPathCountByCouncilSessionId;

		Object[] finderArgs = new Object[] {councilSessionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DELIBERATION_WHERE);

			query.append(_FINDER_COLUMN_COUNCILSESSIONID_COUNCILSESSIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(councilSessionId);

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

	private static final String
		_FINDER_COLUMN_COUNCILSESSIONID_COUNCILSESSIONID_2 =
			"deliberation.councilSessionId = ?";

	public DeliberationPersistenceImpl() {
		setModelClass(Deliberation.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("order", "order_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the deliberation in the entity cache if it is enabled.
	 *
	 * @param deliberation the deliberation
	 */
	@Override
	public void cacheResult(Deliberation deliberation) {
		entityCache.putResult(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED, DeliberationImpl.class,
			deliberation.getPrimaryKey(), deliberation);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {deliberation.getUuid(), deliberation.getGroupId()},
			deliberation);

		deliberation.resetOriginalValues();
	}

	/**
	 * Caches the deliberations in the entity cache if it is enabled.
	 *
	 * @param deliberations the deliberations
	 */
	@Override
	public void cacheResult(List<Deliberation> deliberations) {
		for (Deliberation deliberation : deliberations) {
			if (entityCache.getResult(
					DeliberationModelImpl.ENTITY_CACHE_ENABLED,
					DeliberationImpl.class, deliberation.getPrimaryKey()) ==
						null) {

				cacheResult(deliberation);
			}
			else {
				deliberation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all deliberations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DeliberationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the deliberation.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Deliberation deliberation) {
		entityCache.removeResult(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED, DeliberationImpl.class,
			deliberation.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DeliberationModelImpl)deliberation, true);
	}

	@Override
	public void clearCache(List<Deliberation> deliberations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Deliberation deliberation : deliberations) {
			entityCache.removeResult(
				DeliberationModelImpl.ENTITY_CACHE_ENABLED,
				DeliberationImpl.class, deliberation.getPrimaryKey());

			clearUniqueFindersCache((DeliberationModelImpl)deliberation, true);
		}
	}

	protected void cacheUniqueFindersCache(
		DeliberationModelImpl deliberationModelImpl) {

		Object[] args = new Object[] {
			deliberationModelImpl.getUuid(), deliberationModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, deliberationModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DeliberationModelImpl deliberationModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				deliberationModelImpl.getUuid(),
				deliberationModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((deliberationModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				deliberationModelImpl.getOriginalUuid(),
				deliberationModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new deliberation with the primary key. Does not add the deliberation to the database.
	 *
	 * @param deliberationId the primary key for the new deliberation
	 * @return the new deliberation
	 */
	@Override
	public Deliberation create(long deliberationId) {
		Deliberation deliberation = new DeliberationImpl();

		deliberation.setNew(true);
		deliberation.setPrimaryKey(deliberationId);

		String uuid = PortalUUIDUtil.generate();

		deliberation.setUuid(uuid);

		deliberation.setCompanyId(companyProvider.getCompanyId());

		return deliberation;
	}

	/**
	 * Removes the deliberation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deliberationId the primary key of the deliberation
	 * @return the deliberation that was removed
	 * @throws NoSuchDeliberationException if a deliberation with the primary key could not be found
	 */
	@Override
	public Deliberation remove(long deliberationId)
		throws NoSuchDeliberationException {

		return remove((Serializable)deliberationId);
	}

	/**
	 * Removes the deliberation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the deliberation
	 * @return the deliberation that was removed
	 * @throws NoSuchDeliberationException if a deliberation with the primary key could not be found
	 */
	@Override
	public Deliberation remove(Serializable primaryKey)
		throws NoSuchDeliberationException {

		Session session = null;

		try {
			session = openSession();

			Deliberation deliberation = (Deliberation)session.get(
				DeliberationImpl.class, primaryKey);

			if (deliberation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDeliberationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(deliberation);
		}
		catch (NoSuchDeliberationException nsee) {
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
	protected Deliberation removeImpl(Deliberation deliberation) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(deliberation)) {
				deliberation = (Deliberation)session.get(
					DeliberationImpl.class, deliberation.getPrimaryKeyObj());
			}

			if (deliberation != null) {
				session.delete(deliberation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (deliberation != null) {
			clearCache(deliberation);
		}

		return deliberation;
	}

	@Override
	public Deliberation updateImpl(Deliberation deliberation) {
		boolean isNew = deliberation.isNew();

		if (!(deliberation instanceof DeliberationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(deliberation.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					deliberation);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in deliberation proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Deliberation implementation " +
					deliberation.getClass());
		}

		DeliberationModelImpl deliberationModelImpl =
			(DeliberationModelImpl)deliberation;

		if (Validator.isNull(deliberation.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			deliberation.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (deliberation.getCreateDate() == null)) {
			if (serviceContext == null) {
				deliberation.setCreateDate(now);
			}
			else {
				deliberation.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!deliberationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				deliberation.setModifiedDate(now);
			}
			else {
				deliberation.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (deliberation.isNew()) {
				session.save(deliberation);

				deliberation.setNew(false);
			}
			else {
				deliberation = (Deliberation)session.merge(deliberation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DeliberationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {deliberationModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				deliberationModelImpl.getUuid(),
				deliberationModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {deliberationModelImpl.getCouncilSessionId()};

			finderCache.removeResult(_finderPathCountByCouncilSessionId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCouncilSessionId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((deliberationModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					deliberationModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {deliberationModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((deliberationModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					deliberationModelImpl.getOriginalUuid(),
					deliberationModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					deliberationModelImpl.getUuid(),
					deliberationModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((deliberationModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCouncilSessionId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					deliberationModelImpl.getOriginalCouncilSessionId()
				};

				finderCache.removeResult(
					_finderPathCountByCouncilSessionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCouncilSessionId, args);

				args = new Object[] {
					deliberationModelImpl.getCouncilSessionId()
				};

				finderCache.removeResult(
					_finderPathCountByCouncilSessionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCouncilSessionId, args);
			}
		}

		entityCache.putResult(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED, DeliberationImpl.class,
			deliberation.getPrimaryKey(), deliberation, false);

		clearUniqueFindersCache(deliberationModelImpl, false);
		cacheUniqueFindersCache(deliberationModelImpl);

		deliberation.resetOriginalValues();

		return deliberation;
	}

	/**
	 * Returns the deliberation with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the deliberation
	 * @return the deliberation
	 * @throws NoSuchDeliberationException if a deliberation with the primary key could not be found
	 */
	@Override
	public Deliberation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDeliberationException {

		Deliberation deliberation = fetchByPrimaryKey(primaryKey);

		if (deliberation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDeliberationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return deliberation;
	}

	/**
	 * Returns the deliberation with the primary key or throws a <code>NoSuchDeliberationException</code> if it could not be found.
	 *
	 * @param deliberationId the primary key of the deliberation
	 * @return the deliberation
	 * @throws NoSuchDeliberationException if a deliberation with the primary key could not be found
	 */
	@Override
	public Deliberation findByPrimaryKey(long deliberationId)
		throws NoSuchDeliberationException {

		return findByPrimaryKey((Serializable)deliberationId);
	}

	/**
	 * Returns the deliberation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the deliberation
	 * @return the deliberation, or <code>null</code> if a deliberation with the primary key could not be found
	 */
	@Override
	public Deliberation fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED, DeliberationImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Deliberation deliberation = (Deliberation)serializable;

		if (deliberation == null) {
			Session session = null;

			try {
				session = openSession();

				deliberation = (Deliberation)session.get(
					DeliberationImpl.class, primaryKey);

				if (deliberation != null) {
					cacheResult(deliberation);
				}
				else {
					entityCache.putResult(
						DeliberationModelImpl.ENTITY_CACHE_ENABLED,
						DeliberationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					DeliberationModelImpl.ENTITY_CACHE_ENABLED,
					DeliberationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return deliberation;
	}

	/**
	 * Returns the deliberation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deliberationId the primary key of the deliberation
	 * @return the deliberation, or <code>null</code> if a deliberation with the primary key could not be found
	 */
	@Override
	public Deliberation fetchByPrimaryKey(long deliberationId) {
		return fetchByPrimaryKey((Serializable)deliberationId);
	}

	@Override
	public Map<Serializable, Deliberation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Deliberation> map =
			new HashMap<Serializable, Deliberation>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Deliberation deliberation = fetchByPrimaryKey(primaryKey);

			if (deliberation != null) {
				map.put(primaryKey, deliberation);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				DeliberationModelImpl.ENTITY_CACHE_ENABLED,
				DeliberationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Deliberation)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_DELIBERATION_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Deliberation deliberation : (List<Deliberation>)q.list()) {
				map.put(deliberation.getPrimaryKeyObj(), deliberation);

				cacheResult(deliberation);

				uncachedPrimaryKeys.remove(deliberation.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					DeliberationModelImpl.ENTITY_CACHE_ENABLED,
					DeliberationImpl.class, primaryKey, nullModel);
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
	 * Returns all the deliberations.
	 *
	 * @return the deliberations
	 */
	@Override
	public List<Deliberation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliberations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DeliberationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @return the range of deliberations
	 */
	@Override
	public List<Deliberation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliberations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DeliberationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of deliberations
	 */
	@Override
	public List<Deliberation> findAll(
		int start, int end, OrderByComparator<Deliberation> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliberations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DeliberationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of deliberations
	 */
	@Override
	public List<Deliberation> findAll(
		int start, int end, OrderByComparator<Deliberation> orderByComparator,
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

		List<Deliberation> list = null;

		if (retrieveFromCache) {
			list = (List<Deliberation>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DELIBERATION);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DELIBERATION;

				if (pagination) {
					sql = sql.concat(DeliberationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Deliberation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Deliberation>)QueryUtil.list(
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
	 * Removes all the deliberations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Deliberation deliberation : findAll()) {
			remove(deliberation);
		}
	}

	/**
	 * Returns the number of deliberations.
	 *
	 * @return the number of deliberations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DELIBERATION);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return DeliberationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the deliberation persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, DeliberationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, DeliberationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, DeliberationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, DeliberationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			DeliberationModelImpl.UUID_COLUMN_BITMASK |
			DeliberationModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, DeliberationImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			DeliberationModelImpl.UUID_COLUMN_BITMASK |
			DeliberationModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, DeliberationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, DeliberationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			DeliberationModelImpl.UUID_COLUMN_BITMASK |
			DeliberationModelImpl.COMPANYID_COLUMN_BITMASK |
			DeliberationModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCouncilSessionId = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, DeliberationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCouncilSessionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCouncilSessionId = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, DeliberationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCouncilSessionId",
			new String[] {Long.class.getName()},
			DeliberationModelImpl.COUNCILSESSIONID_COLUMN_BITMASK |
			DeliberationModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByCouncilSessionId = new FinderPath(
			DeliberationModelImpl.ENTITY_CACHE_ENABLED,
			DeliberationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCouncilSessionId", new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(DeliberationImpl.class.getName());
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

	private static final String _SQL_SELECT_DELIBERATION =
		"SELECT deliberation FROM Deliberation deliberation";

	private static final String _SQL_SELECT_DELIBERATION_WHERE_PKS_IN =
		"SELECT deliberation FROM Deliberation deliberation WHERE deliberationId IN (";

	private static final String _SQL_SELECT_DELIBERATION_WHERE =
		"SELECT deliberation FROM Deliberation deliberation WHERE ";

	private static final String _SQL_COUNT_DELIBERATION =
		"SELECT COUNT(deliberation) FROM Deliberation deliberation";

	private static final String _SQL_COUNT_DELIBERATION_WHERE =
		"SELECT COUNT(deliberation) FROM Deliberation deliberation WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "deliberation.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Deliberation exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Deliberation exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DeliberationPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "order"});

}