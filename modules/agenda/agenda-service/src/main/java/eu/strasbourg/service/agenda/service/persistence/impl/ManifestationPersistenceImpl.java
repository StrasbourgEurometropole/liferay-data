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

package eu.strasbourg.service.agenda.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.agenda.exception.NoSuchManifestationException;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.model.impl.ManifestationImpl;
import eu.strasbourg.service.agenda.model.impl.ManifestationModelImpl;
import eu.strasbourg.service.agenda.service.persistence.EventPersistence;
import eu.strasbourg.service.agenda.service.persistence.ManifestationPersistence;

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
 * The persistence implementation for the manifestation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @generated
 */
@ProviderType
public class ManifestationPersistenceImpl
	extends BasePersistenceImpl<Manifestation>
	implements ManifestationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ManifestationUtil</code> to access the manifestation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ManifestationImpl.class.getName();

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
	 * Returns all the manifestations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching manifestations
	 */
	@Override
	public List<Manifestation> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the manifestations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @return the range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the manifestations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Manifestation> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the manifestations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Manifestation> orderByComparator,
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

		List<Manifestation> list = null;

		if (retrieveFromCache) {
			list = (List<Manifestation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Manifestation manifestation : list) {
					if (!uuid.equals(manifestation.getUuid())) {
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

			query.append(_SQL_SELECT_MANIFESTATION_WHERE);

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
				query.append(ManifestationModelImpl.ORDER_BY_JPQL);
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
					list = (List<Manifestation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Manifestation>)QueryUtil.list(
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
	 * Returns the first manifestation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByUuid_First(
			String uuid, OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByUuid_First(
			uuid, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the first manifestation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByUuid_First(
		String uuid, OrderByComparator<Manifestation> orderByComparator) {

		List<Manifestation> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last manifestation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByUuid_Last(
			String uuid, OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByUuid_Last(uuid, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the last manifestation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByUuid_Last(
		String uuid, OrderByComparator<Manifestation> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Manifestation> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the manifestations before and after the current manifestation in the ordered set where uuid = &#63;.
	 *
	 * @param manifestationId the primary key of the current manifestation
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next manifestation
	 * @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	 */
	@Override
	public Manifestation[] findByUuid_PrevAndNext(
			long manifestationId, String uuid,
			OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		uuid = Objects.toString(uuid, "");

		Manifestation manifestation = findByPrimaryKey(manifestationId);

		Session session = null;

		try {
			session = openSession();

			Manifestation[] array = new ManifestationImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, manifestation, uuid, orderByComparator, true);

			array[1] = manifestation;

			array[2] = getByUuid_PrevAndNext(
				session, manifestation, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Manifestation getByUuid_PrevAndNext(
		Session session, Manifestation manifestation, String uuid,
		OrderByComparator<Manifestation> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MANIFESTATION_WHERE);

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
			query.append(ManifestationModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(
						manifestation)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Manifestation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the manifestations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Manifestation manifestation :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(manifestation);
		}
	}

	/**
	 * Returns the number of manifestations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching manifestations
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MANIFESTATION_WHERE);

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
		"manifestation.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(manifestation.uuid IS NULL OR manifestation.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the manifestation where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchManifestationException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByUUID_G(String uuid, long groupId)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByUUID_G(uuid, groupId);

		if (manifestation == null) {
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

			throw new NoSuchManifestationException(msg.toString());
		}

		return manifestation;
	}

	/**
	 * Returns the manifestation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the manifestation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] {uuid, groupId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof Manifestation) {
			Manifestation manifestation = (Manifestation)result;

			if (!Objects.equals(uuid, manifestation.getUuid()) ||
				(groupId != manifestation.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MANIFESTATION_WHERE);

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

				List<Manifestation> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByUUID_G, finderArgs, list);
				}
				else {
					Manifestation manifestation = list.get(0);

					result = manifestation;

					cacheResult(manifestation);
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
			return (Manifestation)result;
		}
	}

	/**
	 * Removes the manifestation where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the manifestation that was removed
	 */
	@Override
	public Manifestation removeByUUID_G(String uuid, long groupId)
		throws NoSuchManifestationException {

		Manifestation manifestation = findByUUID_G(uuid, groupId);

		return remove(manifestation);
	}

	/**
	 * Returns the number of manifestations where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching manifestations
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MANIFESTATION_WHERE);

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
		"manifestation.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(manifestation.uuid IS NULL OR manifestation.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"manifestation.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the manifestations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching manifestations
	 */
	@Override
	public List<Manifestation> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the manifestations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @return the range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the manifestations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Manifestation> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the manifestations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Manifestation> orderByComparator,
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

		List<Manifestation> list = null;

		if (retrieveFromCache) {
			list = (List<Manifestation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Manifestation manifestation : list) {
					if (!uuid.equals(manifestation.getUuid()) ||
						(companyId != manifestation.getCompanyId())) {

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

			query.append(_SQL_SELECT_MANIFESTATION_WHERE);

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
				query.append(ManifestationModelImpl.ORDER_BY_JPQL);
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
					list = (List<Manifestation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Manifestation>)QueryUtil.list(
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
	 * Returns the first manifestation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the first manifestation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Manifestation> orderByComparator) {

		List<Manifestation> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last manifestation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the last manifestation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Manifestation> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Manifestation> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the manifestations before and after the current manifestation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param manifestationId the primary key of the current manifestation
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next manifestation
	 * @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	 */
	@Override
	public Manifestation[] findByUuid_C_PrevAndNext(
			long manifestationId, String uuid, long companyId,
			OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		uuid = Objects.toString(uuid, "");

		Manifestation manifestation = findByPrimaryKey(manifestationId);

		Session session = null;

		try {
			session = openSession();

			Manifestation[] array = new ManifestationImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, manifestation, uuid, companyId, orderByComparator,
				true);

			array[1] = manifestation;

			array[2] = getByUuid_C_PrevAndNext(
				session, manifestation, uuid, companyId, orderByComparator,
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

	protected Manifestation getByUuid_C_PrevAndNext(
		Session session, Manifestation manifestation, String uuid,
		long companyId, OrderByComparator<Manifestation> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MANIFESTATION_WHERE);

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
			query.append(ManifestationModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(
						manifestation)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Manifestation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the manifestations where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Manifestation manifestation :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(manifestation);
		}
	}

	/**
	 * Returns the number of manifestations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching manifestations
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MANIFESTATION_WHERE);

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
		"manifestation.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(manifestation.uuid IS NULL OR manifestation.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"manifestation.companyId = ?";

	private FinderPath _finderPathWithPaginationFindBytitle;
	private FinderPath _finderPathWithoutPaginationFindBytitle;
	private FinderPath _finderPathCountBytitle;

	/**
	 * Returns all the manifestations where title = &#63;.
	 *
	 * @param title the title
	 * @return the matching manifestations
	 */
	@Override
	public List<Manifestation> findBytitle(String title) {
		return findBytitle(title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the manifestations where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @return the range of matching manifestations
	 */
	@Override
	public List<Manifestation> findBytitle(String title, int start, int end) {
		return findBytitle(title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the manifestations where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findBytitle(
		String title, int start, int end,
		OrderByComparator<Manifestation> orderByComparator) {

		return findBytitle(title, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the manifestations where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findBytitle(
		String title, int start, int end,
		OrderByComparator<Manifestation> orderByComparator,
		boolean retrieveFromCache) {

		title = Objects.toString(title, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindBytitle;
			finderArgs = new Object[] {title};
		}
		else {
			finderPath = _finderPathWithPaginationFindBytitle;
			finderArgs = new Object[] {title, start, end, orderByComparator};
		}

		List<Manifestation> list = null;

		if (retrieveFromCache) {
			list = (List<Manifestation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Manifestation manifestation : list) {
					if (!title.equals(manifestation.getTitle())) {
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

			query.append(_SQL_SELECT_MANIFESTATION_WHERE);

			boolean bindTitle = false;

			if (title.isEmpty()) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_TITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(ManifestationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
				}

				if (!pagination) {
					list = (List<Manifestation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Manifestation>)QueryUtil.list(
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
	 * Returns the first manifestation in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findBytitle_First(
			String title, OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchBytitle_First(
			title, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the first manifestation in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchBytitle_First(
		String title, OrderByComparator<Manifestation> orderByComparator) {

		List<Manifestation> list = findBytitle(title, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last manifestation in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findBytitle_Last(
			String title, OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchBytitle_Last(
			title, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the last manifestation in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchBytitle_Last(
		String title, OrderByComparator<Manifestation> orderByComparator) {

		int count = countBytitle(title);

		if (count == 0) {
			return null;
		}

		List<Manifestation> list = findBytitle(
			title, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the manifestations before and after the current manifestation in the ordered set where title = &#63;.
	 *
	 * @param manifestationId the primary key of the current manifestation
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next manifestation
	 * @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	 */
	@Override
	public Manifestation[] findBytitle_PrevAndNext(
			long manifestationId, String title,
			OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		title = Objects.toString(title, "");

		Manifestation manifestation = findByPrimaryKey(manifestationId);

		Session session = null;

		try {
			session = openSession();

			Manifestation[] array = new ManifestationImpl[3];

			array[0] = getBytitle_PrevAndNext(
				session, manifestation, title, orderByComparator, true);

			array[1] = manifestation;

			array[2] = getBytitle_PrevAndNext(
				session, manifestation, title, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Manifestation getBytitle_PrevAndNext(
		Session session, Manifestation manifestation, String title,
		OrderByComparator<Manifestation> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MANIFESTATION_WHERE);

		boolean bindTitle = false;

		if (title.isEmpty()) {
			query.append(_FINDER_COLUMN_TITLE_TITLE_3);
		}
		else {
			bindTitle = true;

			query.append(_FINDER_COLUMN_TITLE_TITLE_2);
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
			query.append(ManifestationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindTitle) {
			qPos.add(title);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						manifestation)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Manifestation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the manifestations where title = &#63; from the database.
	 *
	 * @param title the title
	 */
	@Override
	public void removeBytitle(String title) {
		for (Manifestation manifestation :
				findBytitle(
					title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(manifestation);
		}
	}

	/**
	 * Returns the number of manifestations where title = &#63;.
	 *
	 * @param title the title
	 * @return the number of matching manifestations
	 */
	@Override
	public int countBytitle(String title) {
		title = Objects.toString(title, "");

		FinderPath finderPath = _finderPathCountBytitle;

		Object[] finderArgs = new Object[] {title};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MANIFESTATION_WHERE);

			boolean bindTitle = false;

			if (title.isEmpty()) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_TITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
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

	private static final String _FINDER_COLUMN_TITLE_TITLE_2 =
		"manifestation.title = ?";

	private static final String _FINDER_COLUMN_TITLE_TITLE_3 =
		"(manifestation.title IS NULL OR manifestation.title = '')";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the manifestations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching manifestations
	 */
	@Override
	public List<Manifestation> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the manifestations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @return the range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the manifestations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Manifestation> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the manifestations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Manifestation> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByGroupId;
			finderArgs = new Object[] {groupId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<Manifestation> list = null;

		if (retrieveFromCache) {
			list = (List<Manifestation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Manifestation manifestation : list) {
					if ((groupId != manifestation.getGroupId())) {
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

			query.append(_SQL_SELECT_MANIFESTATION_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(ManifestationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Manifestation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Manifestation>)QueryUtil.list(
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
	 * Returns the first manifestation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByGroupId_First(
			long groupId, OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByGroupId_First(
			groupId, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the first manifestation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByGroupId_First(
		long groupId, OrderByComparator<Manifestation> orderByComparator) {

		List<Manifestation> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last manifestation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByGroupId_Last(
			long groupId, OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the last manifestation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByGroupId_Last(
		long groupId, OrderByComparator<Manifestation> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Manifestation> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the manifestations before and after the current manifestation in the ordered set where groupId = &#63;.
	 *
	 * @param manifestationId the primary key of the current manifestation
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next manifestation
	 * @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	 */
	@Override
	public Manifestation[] findByGroupId_PrevAndNext(
			long manifestationId, long groupId,
			OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = findByPrimaryKey(manifestationId);

		Session session = null;

		try {
			session = openSession();

			Manifestation[] array = new ManifestationImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, manifestation, groupId, orderByComparator, true);

			array[1] = manifestation;

			array[2] = getByGroupId_PrevAndNext(
				session, manifestation, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Manifestation getByGroupId_PrevAndNext(
		Session session, Manifestation manifestation, long groupId,
		OrderByComparator<Manifestation> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MANIFESTATION_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(ManifestationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						manifestation)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Manifestation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the manifestations where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (Manifestation manifestation :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(manifestation);
		}
	}

	/**
	 * Returns the number of manifestations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching manifestations
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MANIFESTATION_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"manifestation.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByPublicationDateAndStatus;
	private FinderPath _finderPathWithPaginationCountByPublicationDateAndStatus;

	/**
	 * Returns all the manifestations where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @return the matching manifestations
	 */
	@Override
	public List<Manifestation> findByPublicationDateAndStatus(
		Date publicationDate, int status) {

		return findByPublicationDateAndStatus(
			publicationDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the manifestations where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @return the range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end) {

		return findByPublicationDateAndStatus(
			publicationDate, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the manifestations where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		OrderByComparator<Manifestation> orderByComparator) {

		return findByPublicationDateAndStatus(
			publicationDate, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the manifestations where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		OrderByComparator<Manifestation> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByPublicationDateAndStatus;
		finderArgs = new Object[] {
			_getTime(publicationDate), status, start, end, orderByComparator
		};

		List<Manifestation> list = null;

		if (retrieveFromCache) {
			list = (List<Manifestation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Manifestation manifestation : list) {
					if ((publicationDate.getTime() <=
							manifestation.getPublicationDate().getTime()) ||
						(status != manifestation.getStatus())) {

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

			query.append(_SQL_SELECT_MANIFESTATION_WHERE);

			boolean bindPublicationDate = false;

			if (publicationDate == null) {
				query.append(
					_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_1);
			}
			else {
				bindPublicationDate = true;

				query.append(
					_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_2);
			}

			query.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(ManifestationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublicationDate) {
					qPos.add(new Timestamp(publicationDate.getTime()));
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<Manifestation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Manifestation>)QueryUtil.list(
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
	 * Returns the first manifestation in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByPublicationDateAndStatus_First(
			Date publicationDate, int status,
			OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByPublicationDateAndStatus_First(
			publicationDate, status, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publicationDate=");
		msg.append(publicationDate);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the first manifestation in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByPublicationDateAndStatus_First(
		Date publicationDate, int status,
		OrderByComparator<Manifestation> orderByComparator) {

		List<Manifestation> list = findByPublicationDateAndStatus(
			publicationDate, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last manifestation in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByPublicationDateAndStatus_Last(
			Date publicationDate, int status,
			OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByPublicationDateAndStatus_Last(
			publicationDate, status, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publicationDate=");
		msg.append(publicationDate);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the last manifestation in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByPublicationDateAndStatus_Last(
		Date publicationDate, int status,
		OrderByComparator<Manifestation> orderByComparator) {

		int count = countByPublicationDateAndStatus(publicationDate, status);

		if (count == 0) {
			return null;
		}

		List<Manifestation> list = findByPublicationDateAndStatus(
			publicationDate, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the manifestations before and after the current manifestation in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param manifestationId the primary key of the current manifestation
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next manifestation
	 * @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	 */
	@Override
	public Manifestation[] findByPublicationDateAndStatus_PrevAndNext(
			long manifestationId, Date publicationDate, int status,
			OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = findByPrimaryKey(manifestationId);

		Session session = null;

		try {
			session = openSession();

			Manifestation[] array = new ManifestationImpl[3];

			array[0] = getByPublicationDateAndStatus_PrevAndNext(
				session, manifestation, publicationDate, status,
				orderByComparator, true);

			array[1] = manifestation;

			array[2] = getByPublicationDateAndStatus_PrevAndNext(
				session, manifestation, publicationDate, status,
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

	protected Manifestation getByPublicationDateAndStatus_PrevAndNext(
		Session session, Manifestation manifestation, Date publicationDate,
		int status, OrderByComparator<Manifestation> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MANIFESTATION_WHERE);

		boolean bindPublicationDate = false;

		if (publicationDate == null) {
			query.append(
				_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_1);
		}
		else {
			bindPublicationDate = true;

			query.append(
				_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_2);
		}

		query.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_STATUS_2);

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
			query.append(ManifestationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPublicationDate) {
			qPos.add(new Timestamp(publicationDate.getTime()));
		}

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						manifestation)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Manifestation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the manifestations where publicationDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 */
	@Override
	public void removeByPublicationDateAndStatus(
		Date publicationDate, int status) {

		for (Manifestation manifestation :
				findByPublicationDateAndStatus(
					publicationDate, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(manifestation);
		}
	}

	/**
	 * Returns the number of manifestations where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @return the number of matching manifestations
	 */
	@Override
	public int countByPublicationDateAndStatus(
		Date publicationDate, int status) {

		FinderPath finderPath =
			_finderPathWithPaginationCountByPublicationDateAndStatus;

		Object[] finderArgs = new Object[] {_getTime(publicationDate), status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MANIFESTATION_WHERE);

			boolean bindPublicationDate = false;

			if (publicationDate == null) {
				query.append(
					_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_1);
			}
			else {
				bindPublicationDate = true;

				query.append(
					_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_2);
			}

			query.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublicationDate) {
					qPos.add(new Timestamp(publicationDate.getTime()));
				}

				qPos.add(status);

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
		_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_1 =
			"manifestation.publicationDate IS NULL AND ";

	private static final String
		_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_2 =
			"manifestation.publicationDate < ? AND ";

	private static final String
		_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_STATUS_2 =
			"manifestation.status = ?";

	private FinderPath _finderPathWithPaginationFindByEndDate;
	private FinderPath _finderPathWithPaginationCountByEndDate;

	/**
	 * Returns all the manifestations where endDate &lt; &#63;.
	 *
	 * @param endDate the end date
	 * @return the matching manifestations
	 */
	@Override
	public List<Manifestation> findByEndDate(Date endDate) {
		return findByEndDate(
			endDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the manifestations where endDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param endDate the end date
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @return the range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByEndDate(Date endDate, int start, int end) {
		return findByEndDate(endDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the manifestations where endDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param endDate the end date
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByEndDate(
		Date endDate, int start, int end,
		OrderByComparator<Manifestation> orderByComparator) {

		return findByEndDate(endDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the manifestations where endDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param endDate the end date
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByEndDate(
		Date endDate, int start, int end,
		OrderByComparator<Manifestation> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByEndDate;
		finderArgs = new Object[] {
			_getTime(endDate), start, end, orderByComparator
		};

		List<Manifestation> list = null;

		if (retrieveFromCache) {
			list = (List<Manifestation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Manifestation manifestation : list) {
					if ((endDate.getTime() <=
							manifestation.getEndDate().getTime())) {

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

			query.append(_SQL_SELECT_MANIFESTATION_WHERE);

			boolean bindEndDate = false;

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ENDDATE_ENDDATE_1);
			}
			else {
				bindEndDate = true;

				query.append(_FINDER_COLUMN_ENDDATE_ENDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(ManifestationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEndDate) {
					qPos.add(new Timestamp(endDate.getTime()));
				}

				if (!pagination) {
					list = (List<Manifestation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Manifestation>)QueryUtil.list(
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
	 * Returns the first manifestation in the ordered set where endDate &lt; &#63;.
	 *
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByEndDate_First(
			Date endDate, OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByEndDate_First(
			endDate, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("endDate=");
		msg.append(endDate);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the first manifestation in the ordered set where endDate &lt; &#63;.
	 *
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByEndDate_First(
		Date endDate, OrderByComparator<Manifestation> orderByComparator) {

		List<Manifestation> list = findByEndDate(
			endDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last manifestation in the ordered set where endDate &lt; &#63;.
	 *
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByEndDate_Last(
			Date endDate, OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByEndDate_Last(
			endDate, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("endDate=");
		msg.append(endDate);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the last manifestation in the ordered set where endDate &lt; &#63;.
	 *
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByEndDate_Last(
		Date endDate, OrderByComparator<Manifestation> orderByComparator) {

		int count = countByEndDate(endDate);

		if (count == 0) {
			return null;
		}

		List<Manifestation> list = findByEndDate(
			endDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the manifestations before and after the current manifestation in the ordered set where endDate &lt; &#63;.
	 *
	 * @param manifestationId the primary key of the current manifestation
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next manifestation
	 * @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	 */
	@Override
	public Manifestation[] findByEndDate_PrevAndNext(
			long manifestationId, Date endDate,
			OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = findByPrimaryKey(manifestationId);

		Session session = null;

		try {
			session = openSession();

			Manifestation[] array = new ManifestationImpl[3];

			array[0] = getByEndDate_PrevAndNext(
				session, manifestation, endDate, orderByComparator, true);

			array[1] = manifestation;

			array[2] = getByEndDate_PrevAndNext(
				session, manifestation, endDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Manifestation getByEndDate_PrevAndNext(
		Session session, Manifestation manifestation, Date endDate,
		OrderByComparator<Manifestation> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MANIFESTATION_WHERE);

		boolean bindEndDate = false;

		if (endDate == null) {
			query.append(_FINDER_COLUMN_ENDDATE_ENDDATE_1);
		}
		else {
			bindEndDate = true;

			query.append(_FINDER_COLUMN_ENDDATE_ENDDATE_2);
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
			query.append(ManifestationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindEndDate) {
			qPos.add(new Timestamp(endDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						manifestation)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Manifestation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the manifestations where endDate &lt; &#63; from the database.
	 *
	 * @param endDate the end date
	 */
	@Override
	public void removeByEndDate(Date endDate) {
		for (Manifestation manifestation :
				findByEndDate(
					endDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(manifestation);
		}
	}

	/**
	 * Returns the number of manifestations where endDate &lt; &#63;.
	 *
	 * @param endDate the end date
	 * @return the number of matching manifestations
	 */
	@Override
	public int countByEndDate(Date endDate) {
		FinderPath finderPath = _finderPathWithPaginationCountByEndDate;

		Object[] finderArgs = new Object[] {_getTime(endDate)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MANIFESTATION_WHERE);

			boolean bindEndDate = false;

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ENDDATE_ENDDATE_1);
			}
			else {
				bindEndDate = true;

				query.append(_FINDER_COLUMN_ENDDATE_ENDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEndDate) {
					qPos.add(new Timestamp(endDate.getTime()));
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

	private static final String _FINDER_COLUMN_ENDDATE_ENDDATE_1 =
		"manifestation.endDate IS NULL";

	private static final String _FINDER_COLUMN_ENDDATE_ENDDATE_2 =
		"manifestation.endDate < ?";

	private FinderPath _finderPathWithPaginationFindByStatusDateAndStatus;
	private FinderPath _finderPathWithPaginationCountByStatusDateAndStatus;

	/**
	 * Returns all the manifestations where statusDate &lt; &#63; and status = &#63;.
	 *
	 * @param statusDate the status date
	 * @param status the status
	 * @return the matching manifestations
	 */
	@Override
	public List<Manifestation> findByStatusDateAndStatus(
		Date statusDate, int status) {

		return findByStatusDateAndStatus(
			statusDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the manifestations where statusDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param statusDate the status date
	 * @param status the status
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @return the range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByStatusDateAndStatus(
		Date statusDate, int status, int start, int end) {

		return findByStatusDateAndStatus(statusDate, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the manifestations where statusDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param statusDate the status date
	 * @param status the status
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByStatusDateAndStatus(
		Date statusDate, int status, int start, int end,
		OrderByComparator<Manifestation> orderByComparator) {

		return findByStatusDateAndStatus(
			statusDate, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the manifestations where statusDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param statusDate the status date
	 * @param status the status
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching manifestations
	 */
	@Override
	public List<Manifestation> findByStatusDateAndStatus(
		Date statusDate, int status, int start, int end,
		OrderByComparator<Manifestation> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByStatusDateAndStatus;
		finderArgs = new Object[] {
			_getTime(statusDate), status, start, end, orderByComparator
		};

		List<Manifestation> list = null;

		if (retrieveFromCache) {
			list = (List<Manifestation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Manifestation manifestation : list) {
					if ((statusDate.getTime() <=
							manifestation.getStatusDate().getTime()) ||
						(status != manifestation.getStatus())) {

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

			query.append(_SQL_SELECT_MANIFESTATION_WHERE);

			boolean bindStatusDate = false;

			if (statusDate == null) {
				query.append(_FINDER_COLUMN_STATUSDATEANDSTATUS_STATUSDATE_1);
			}
			else {
				bindStatusDate = true;

				query.append(_FINDER_COLUMN_STATUSDATEANDSTATUS_STATUSDATE_2);
			}

			query.append(_FINDER_COLUMN_STATUSDATEANDSTATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(ManifestationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStatusDate) {
					qPos.add(new Timestamp(statusDate.getTime()));
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<Manifestation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Manifestation>)QueryUtil.list(
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
	 * Returns the first manifestation in the ordered set where statusDate &lt; &#63; and status = &#63;.
	 *
	 * @param statusDate the status date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByStatusDateAndStatus_First(
			Date statusDate, int status,
			OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByStatusDateAndStatus_First(
			statusDate, status, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("statusDate=");
		msg.append(statusDate);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the first manifestation in the ordered set where statusDate &lt; &#63; and status = &#63;.
	 *
	 * @param statusDate the status date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByStatusDateAndStatus_First(
		Date statusDate, int status,
		OrderByComparator<Manifestation> orderByComparator) {

		List<Manifestation> list = findByStatusDateAndStatus(
			statusDate, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last manifestation in the ordered set where statusDate &lt; &#63; and status = &#63;.
	 *
	 * @param statusDate the status date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByStatusDateAndStatus_Last(
			Date statusDate, int status,
			OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByStatusDateAndStatus_Last(
			statusDate, status, orderByComparator);

		if (manifestation != null) {
			return manifestation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("statusDate=");
		msg.append(statusDate);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchManifestationException(msg.toString());
	}

	/**
	 * Returns the last manifestation in the ordered set where statusDate &lt; &#63; and status = &#63;.
	 *
	 * @param statusDate the status date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByStatusDateAndStatus_Last(
		Date statusDate, int status,
		OrderByComparator<Manifestation> orderByComparator) {

		int count = countByStatusDateAndStatus(statusDate, status);

		if (count == 0) {
			return null;
		}

		List<Manifestation> list = findByStatusDateAndStatus(
			statusDate, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the manifestations before and after the current manifestation in the ordered set where statusDate &lt; &#63; and status = &#63;.
	 *
	 * @param manifestationId the primary key of the current manifestation
	 * @param statusDate the status date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next manifestation
	 * @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	 */
	@Override
	public Manifestation[] findByStatusDateAndStatus_PrevAndNext(
			long manifestationId, Date statusDate, int status,
			OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException {

		Manifestation manifestation = findByPrimaryKey(manifestationId);

		Session session = null;

		try {
			session = openSession();

			Manifestation[] array = new ManifestationImpl[3];

			array[0] = getByStatusDateAndStatus_PrevAndNext(
				session, manifestation, statusDate, status, orderByComparator,
				true);

			array[1] = manifestation;

			array[2] = getByStatusDateAndStatus_PrevAndNext(
				session, manifestation, statusDate, status, orderByComparator,
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

	protected Manifestation getByStatusDateAndStatus_PrevAndNext(
		Session session, Manifestation manifestation, Date statusDate,
		int status, OrderByComparator<Manifestation> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MANIFESTATION_WHERE);

		boolean bindStatusDate = false;

		if (statusDate == null) {
			query.append(_FINDER_COLUMN_STATUSDATEANDSTATUS_STATUSDATE_1);
		}
		else {
			bindStatusDate = true;

			query.append(_FINDER_COLUMN_STATUSDATEANDSTATUS_STATUSDATE_2);
		}

		query.append(_FINDER_COLUMN_STATUSDATEANDSTATUS_STATUS_2);

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
			query.append(ManifestationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindStatusDate) {
			qPos.add(new Timestamp(statusDate.getTime()));
		}

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						manifestation)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Manifestation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the manifestations where statusDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param statusDate the status date
	 * @param status the status
	 */
	@Override
	public void removeByStatusDateAndStatus(Date statusDate, int status) {
		for (Manifestation manifestation :
				findByStatusDateAndStatus(
					statusDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(manifestation);
		}
	}

	/**
	 * Returns the number of manifestations where statusDate &lt; &#63; and status = &#63;.
	 *
	 * @param statusDate the status date
	 * @param status the status
	 * @return the number of matching manifestations
	 */
	@Override
	public int countByStatusDateAndStatus(Date statusDate, int status) {
		FinderPath finderPath =
			_finderPathWithPaginationCountByStatusDateAndStatus;

		Object[] finderArgs = new Object[] {_getTime(statusDate), status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MANIFESTATION_WHERE);

			boolean bindStatusDate = false;

			if (statusDate == null) {
				query.append(_FINDER_COLUMN_STATUSDATEANDSTATUS_STATUSDATE_1);
			}
			else {
				bindStatusDate = true;

				query.append(_FINDER_COLUMN_STATUSDATEANDSTATUS_STATUSDATE_2);
			}

			query.append(_FINDER_COLUMN_STATUSDATEANDSTATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStatusDate) {
					qPos.add(new Timestamp(statusDate.getTime()));
				}

				qPos.add(status);

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
		_FINDER_COLUMN_STATUSDATEANDSTATUS_STATUSDATE_1 =
			"manifestation.statusDate IS NULL AND ";

	private static final String
		_FINDER_COLUMN_STATUSDATEANDSTATUS_STATUSDATE_2 =
			"manifestation.statusDate < ? AND ";

	private static final String _FINDER_COLUMN_STATUSDATEANDSTATUS_STATUS_2 =
		"manifestation.status = ?";

	private FinderPath _finderPathFetchBySourceAndIdSource;
	private FinderPath _finderPathCountBySourceAndIdSource;

	/**
	 * Returns the manifestation where source = &#63; and idSource = &#63; or throws a <code>NoSuchManifestationException</code> if it could not be found.
	 *
	 * @param source the source
	 * @param idSource the id source
	 * @return the matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findBySourceAndIdSource(String source, String idSource)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchBySourceAndIdSource(
			source, idSource);

		if (manifestation == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("source=");
			msg.append(source);

			msg.append(", idSource=");
			msg.append(idSource);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchManifestationException(msg.toString());
		}

		return manifestation;
	}

	/**
	 * Returns the manifestation where source = &#63; and idSource = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param source the source
	 * @param idSource the id source
	 * @return the matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchBySourceAndIdSource(
		String source, String idSource) {

		return fetchBySourceAndIdSource(source, idSource, true);
	}

	/**
	 * Returns the manifestation where source = &#63; and idSource = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param source the source
	 * @param idSource the id source
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchBySourceAndIdSource(
		String source, String idSource, boolean retrieveFromCache) {

		source = Objects.toString(source, "");
		idSource = Objects.toString(idSource, "");

		Object[] finderArgs = new Object[] {source, idSource};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchBySourceAndIdSource, finderArgs, this);
		}

		if (result instanceof Manifestation) {
			Manifestation manifestation = (Manifestation)result;

			if (!Objects.equals(source, manifestation.getSource()) ||
				!Objects.equals(idSource, manifestation.getIdSource())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MANIFESTATION_WHERE);

			boolean bindSource = false;

			if (source.isEmpty()) {
				query.append(_FINDER_COLUMN_SOURCEANDIDSOURCE_SOURCE_3);
			}
			else {
				bindSource = true;

				query.append(_FINDER_COLUMN_SOURCEANDIDSOURCE_SOURCE_2);
			}

			boolean bindIdSource = false;

			if (idSource.isEmpty()) {
				query.append(_FINDER_COLUMN_SOURCEANDIDSOURCE_IDSOURCE_3);
			}
			else {
				bindIdSource = true;

				query.append(_FINDER_COLUMN_SOURCEANDIDSOURCE_IDSOURCE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSource) {
					qPos.add(source);
				}

				if (bindIdSource) {
					qPos.add(idSource);
				}

				List<Manifestation> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchBySourceAndIdSource, finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ManifestationPersistenceImpl.fetchBySourceAndIdSource(String, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Manifestation manifestation = list.get(0);

					result = manifestation;

					cacheResult(manifestation);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathFetchBySourceAndIdSource, finderArgs);

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
			return (Manifestation)result;
		}
	}

	/**
	 * Removes the manifestation where source = &#63; and idSource = &#63; from the database.
	 *
	 * @param source the source
	 * @param idSource the id source
	 * @return the manifestation that was removed
	 */
	@Override
	public Manifestation removeBySourceAndIdSource(
			String source, String idSource)
		throws NoSuchManifestationException {

		Manifestation manifestation = findBySourceAndIdSource(source, idSource);

		return remove(manifestation);
	}

	/**
	 * Returns the number of manifestations where source = &#63; and idSource = &#63;.
	 *
	 * @param source the source
	 * @param idSource the id source
	 * @return the number of matching manifestations
	 */
	@Override
	public int countBySourceAndIdSource(String source, String idSource) {
		source = Objects.toString(source, "");
		idSource = Objects.toString(idSource, "");

		FinderPath finderPath = _finderPathCountBySourceAndIdSource;

		Object[] finderArgs = new Object[] {source, idSource};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MANIFESTATION_WHERE);

			boolean bindSource = false;

			if (source.isEmpty()) {
				query.append(_FINDER_COLUMN_SOURCEANDIDSOURCE_SOURCE_3);
			}
			else {
				bindSource = true;

				query.append(_FINDER_COLUMN_SOURCEANDIDSOURCE_SOURCE_2);
			}

			boolean bindIdSource = false;

			if (idSource.isEmpty()) {
				query.append(_FINDER_COLUMN_SOURCEANDIDSOURCE_IDSOURCE_3);
			}
			else {
				bindIdSource = true;

				query.append(_FINDER_COLUMN_SOURCEANDIDSOURCE_IDSOURCE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSource) {
					qPos.add(source);
				}

				if (bindIdSource) {
					qPos.add(idSource);
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

	private static final String _FINDER_COLUMN_SOURCEANDIDSOURCE_SOURCE_2 =
		"manifestation.source = ? AND ";

	private static final String _FINDER_COLUMN_SOURCEANDIDSOURCE_SOURCE_3 =
		"(manifestation.source IS NULL OR manifestation.source = '') AND ";

	private static final String _FINDER_COLUMN_SOURCEANDIDSOURCE_IDSOURCE_2 =
		"manifestation.idSource = ?";

	private static final String _FINDER_COLUMN_SOURCEANDIDSOURCE_IDSOURCE_3 =
		"(manifestation.idSource IS NULL OR manifestation.idSource = '')";

	private FinderPath _finderPathFetchByIdSource;
	private FinderPath _finderPathCountByIdSource;

	/**
	 * Returns the manifestation where idSource = &#63; or throws a <code>NoSuchManifestationException</code> if it could not be found.
	 *
	 * @param idSource the id source
	 * @return the matching manifestation
	 * @throws NoSuchManifestationException if a matching manifestation could not be found
	 */
	@Override
	public Manifestation findByIdSource(String idSource)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByIdSource(idSource);

		if (manifestation == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("idSource=");
			msg.append(idSource);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchManifestationException(msg.toString());
		}

		return manifestation;
	}

	/**
	 * Returns the manifestation where idSource = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param idSource the id source
	 * @return the matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByIdSource(String idSource) {
		return fetchByIdSource(idSource, true);
	}

	/**
	 * Returns the manifestation where idSource = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param idSource the id source
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	@Override
	public Manifestation fetchByIdSource(
		String idSource, boolean retrieveFromCache) {

		idSource = Objects.toString(idSource, "");

		Object[] finderArgs = new Object[] {idSource};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByIdSource, finderArgs, this);
		}

		if (result instanceof Manifestation) {
			Manifestation manifestation = (Manifestation)result;

			if (!Objects.equals(idSource, manifestation.getIdSource())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_MANIFESTATION_WHERE);

			boolean bindIdSource = false;

			if (idSource.isEmpty()) {
				query.append(_FINDER_COLUMN_IDSOURCE_IDSOURCE_3);
			}
			else {
				bindIdSource = true;

				query.append(_FINDER_COLUMN_IDSOURCE_IDSOURCE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIdSource) {
					qPos.add(idSource);
				}

				List<Manifestation> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByIdSource, finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ManifestationPersistenceImpl.fetchByIdSource(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Manifestation manifestation = list.get(0);

					result = manifestation;

					cacheResult(manifestation);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathFetchByIdSource, finderArgs);

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
			return (Manifestation)result;
		}
	}

	/**
	 * Removes the manifestation where idSource = &#63; from the database.
	 *
	 * @param idSource the id source
	 * @return the manifestation that was removed
	 */
	@Override
	public Manifestation removeByIdSource(String idSource)
		throws NoSuchManifestationException {

		Manifestation manifestation = findByIdSource(idSource);

		return remove(manifestation);
	}

	/**
	 * Returns the number of manifestations where idSource = &#63;.
	 *
	 * @param idSource the id source
	 * @return the number of matching manifestations
	 */
	@Override
	public int countByIdSource(String idSource) {
		idSource = Objects.toString(idSource, "");

		FinderPath finderPath = _finderPathCountByIdSource;

		Object[] finderArgs = new Object[] {idSource};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MANIFESTATION_WHERE);

			boolean bindIdSource = false;

			if (idSource.isEmpty()) {
				query.append(_FINDER_COLUMN_IDSOURCE_IDSOURCE_3);
			}
			else {
				bindIdSource = true;

				query.append(_FINDER_COLUMN_IDSOURCE_IDSOURCE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIdSource) {
					qPos.add(idSource);
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

	private static final String _FINDER_COLUMN_IDSOURCE_IDSOURCE_2 =
		"manifestation.idSource = ?";

	private static final String _FINDER_COLUMN_IDSOURCE_IDSOURCE_3 =
		"(manifestation.idSource IS NULL OR manifestation.idSource = '')";

	public ManifestationPersistenceImpl() {
		setModelClass(Manifestation.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

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
	 * Caches the manifestation in the entity cache if it is enabled.
	 *
	 * @param manifestation the manifestation
	 */
	@Override
	public void cacheResult(Manifestation manifestation) {
		entityCache.putResult(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationImpl.class, manifestation.getPrimaryKey(),
			manifestation);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {manifestation.getUuid(), manifestation.getGroupId()},
			manifestation);

		finderCache.putResult(
			_finderPathFetchBySourceAndIdSource,
			new Object[] {
				manifestation.getSource(), manifestation.getIdSource()
			},
			manifestation);

		finderCache.putResult(
			_finderPathFetchByIdSource,
			new Object[] {manifestation.getIdSource()}, manifestation);

		manifestation.resetOriginalValues();
	}

	/**
	 * Caches the manifestations in the entity cache if it is enabled.
	 *
	 * @param manifestations the manifestations
	 */
	@Override
	public void cacheResult(List<Manifestation> manifestations) {
		for (Manifestation manifestation : manifestations) {
			if (entityCache.getResult(
					ManifestationModelImpl.ENTITY_CACHE_ENABLED,
					ManifestationImpl.class, manifestation.getPrimaryKey()) ==
						null) {

				cacheResult(manifestation);
			}
			else {
				manifestation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all manifestations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ManifestationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the manifestation.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Manifestation manifestation) {
		entityCache.removeResult(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationImpl.class, manifestation.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ManifestationModelImpl)manifestation, true);
	}

	@Override
	public void clearCache(List<Manifestation> manifestations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Manifestation manifestation : manifestations) {
			entityCache.removeResult(
				ManifestationModelImpl.ENTITY_CACHE_ENABLED,
				ManifestationImpl.class, manifestation.getPrimaryKey());

			clearUniqueFindersCache(
				(ManifestationModelImpl)manifestation, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ManifestationModelImpl manifestationModelImpl) {

		Object[] args = new Object[] {
			manifestationModelImpl.getUuid(),
			manifestationModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, manifestationModelImpl, false);

		args = new Object[] {
			manifestationModelImpl.getSource(),
			manifestationModelImpl.getIdSource()
		};

		finderCache.putResult(
			_finderPathCountBySourceAndIdSource, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchBySourceAndIdSource, args, manifestationModelImpl,
			false);

		args = new Object[] {manifestationModelImpl.getIdSource()};

		finderCache.putResult(
			_finderPathCountByIdSource, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByIdSource, args, manifestationModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ManifestationModelImpl manifestationModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				manifestationModelImpl.getUuid(),
				manifestationModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((manifestationModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				manifestationModelImpl.getOriginalUuid(),
				manifestationModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				manifestationModelImpl.getSource(),
				manifestationModelImpl.getIdSource()
			};

			finderCache.removeResult(_finderPathCountBySourceAndIdSource, args);
			finderCache.removeResult(_finderPathFetchBySourceAndIdSource, args);
		}

		if ((manifestationModelImpl.getColumnBitmask() &
			 _finderPathFetchBySourceAndIdSource.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				manifestationModelImpl.getOriginalSource(),
				manifestationModelImpl.getOriginalIdSource()
			};

			finderCache.removeResult(_finderPathCountBySourceAndIdSource, args);
			finderCache.removeResult(_finderPathFetchBySourceAndIdSource, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {manifestationModelImpl.getIdSource()};

			finderCache.removeResult(_finderPathCountByIdSource, args);
			finderCache.removeResult(_finderPathFetchByIdSource, args);
		}

		if ((manifestationModelImpl.getColumnBitmask() &
			 _finderPathFetchByIdSource.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				manifestationModelImpl.getOriginalIdSource()
			};

			finderCache.removeResult(_finderPathCountByIdSource, args);
			finderCache.removeResult(_finderPathFetchByIdSource, args);
		}
	}

	/**
	 * Creates a new manifestation with the primary key. Does not add the manifestation to the database.
	 *
	 * @param manifestationId the primary key for the new manifestation
	 * @return the new manifestation
	 */
	@Override
	public Manifestation create(long manifestationId) {
		Manifestation manifestation = new ManifestationImpl();

		manifestation.setNew(true);
		manifestation.setPrimaryKey(manifestationId);

		String uuid = PortalUUIDUtil.generate();

		manifestation.setUuid(uuid);

		manifestation.setCompanyId(companyProvider.getCompanyId());

		return manifestation;
	}

	/**
	 * Removes the manifestation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param manifestationId the primary key of the manifestation
	 * @return the manifestation that was removed
	 * @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	 */
	@Override
	public Manifestation remove(long manifestationId)
		throws NoSuchManifestationException {

		return remove((Serializable)manifestationId);
	}

	/**
	 * Removes the manifestation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the manifestation
	 * @return the manifestation that was removed
	 * @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	 */
	@Override
	public Manifestation remove(Serializable primaryKey)
		throws NoSuchManifestationException {

		Session session = null;

		try {
			session = openSession();

			Manifestation manifestation = (Manifestation)session.get(
				ManifestationImpl.class, primaryKey);

			if (manifestation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchManifestationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(manifestation);
		}
		catch (NoSuchManifestationException nsee) {
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
	protected Manifestation removeImpl(Manifestation manifestation) {
		manifestationToEventTableMapper.deleteLeftPrimaryKeyTableMappings(
			manifestation.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(manifestation)) {
				manifestation = (Manifestation)session.get(
					ManifestationImpl.class, manifestation.getPrimaryKeyObj());
			}

			if (manifestation != null) {
				session.delete(manifestation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (manifestation != null) {
			clearCache(manifestation);
		}

		return manifestation;
	}

	@Override
	public Manifestation updateImpl(Manifestation manifestation) {
		boolean isNew = manifestation.isNew();

		if (!(manifestation instanceof ManifestationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(manifestation.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					manifestation);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in manifestation proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Manifestation implementation " +
					manifestation.getClass());
		}

		ManifestationModelImpl manifestationModelImpl =
			(ManifestationModelImpl)manifestation;

		if (Validator.isNull(manifestation.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			manifestation.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (manifestation.getCreateDate() == null)) {
			if (serviceContext == null) {
				manifestation.setCreateDate(now);
			}
			else {
				manifestation.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!manifestationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				manifestation.setModifiedDate(now);
			}
			else {
				manifestation.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (manifestation.isNew()) {
				session.save(manifestation);

				manifestation.setNew(false);
			}
			else {
				manifestation = (Manifestation)session.merge(manifestation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ManifestationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {manifestationModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				manifestationModelImpl.getUuid(),
				manifestationModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {manifestationModelImpl.getTitle()};

			finderCache.removeResult(_finderPathCountBytitle, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindBytitle, args);

			args = new Object[] {manifestationModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((manifestationModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					manifestationModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {manifestationModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((manifestationModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					manifestationModelImpl.getOriginalUuid(),
					manifestationModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					manifestationModelImpl.getUuid(),
					manifestationModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((manifestationModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindBytitle.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					manifestationModelImpl.getOriginalTitle()
				};

				finderCache.removeResult(_finderPathCountBytitle, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBytitle, args);

				args = new Object[] {manifestationModelImpl.getTitle()};

				finderCache.removeResult(_finderPathCountBytitle, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBytitle, args);
			}

			if ((manifestationModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					manifestationModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {manifestationModelImpl.getGroupId()};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}
		}

		entityCache.putResult(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationImpl.class, manifestation.getPrimaryKey(),
			manifestation, false);

		clearUniqueFindersCache(manifestationModelImpl, false);
		cacheUniqueFindersCache(manifestationModelImpl);

		manifestation.resetOriginalValues();

		return manifestation;
	}

	/**
	 * Returns the manifestation with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the manifestation
	 * @return the manifestation
	 * @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	 */
	@Override
	public Manifestation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchManifestationException {

		Manifestation manifestation = fetchByPrimaryKey(primaryKey);

		if (manifestation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchManifestationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return manifestation;
	}

	/**
	 * Returns the manifestation with the primary key or throws a <code>NoSuchManifestationException</code> if it could not be found.
	 *
	 * @param manifestationId the primary key of the manifestation
	 * @return the manifestation
	 * @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	 */
	@Override
	public Manifestation findByPrimaryKey(long manifestationId)
		throws NoSuchManifestationException {

		return findByPrimaryKey((Serializable)manifestationId);
	}

	/**
	 * Returns the manifestation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the manifestation
	 * @return the manifestation, or <code>null</code> if a manifestation with the primary key could not be found
	 */
	@Override
	public Manifestation fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Manifestation manifestation = (Manifestation)serializable;

		if (manifestation == null) {
			Session session = null;

			try {
				session = openSession();

				manifestation = (Manifestation)session.get(
					ManifestationImpl.class, primaryKey);

				if (manifestation != null) {
					cacheResult(manifestation);
				}
				else {
					entityCache.putResult(
						ManifestationModelImpl.ENTITY_CACHE_ENABLED,
						ManifestationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					ManifestationModelImpl.ENTITY_CACHE_ENABLED,
					ManifestationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return manifestation;
	}

	/**
	 * Returns the manifestation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param manifestationId the primary key of the manifestation
	 * @return the manifestation, or <code>null</code> if a manifestation with the primary key could not be found
	 */
	@Override
	public Manifestation fetchByPrimaryKey(long manifestationId) {
		return fetchByPrimaryKey((Serializable)manifestationId);
	}

	@Override
	public Map<Serializable, Manifestation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Manifestation> map =
			new HashMap<Serializable, Manifestation>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Manifestation manifestation = fetchByPrimaryKey(primaryKey);

			if (manifestation != null) {
				map.put(primaryKey, manifestation);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				ManifestationModelImpl.ENTITY_CACHE_ENABLED,
				ManifestationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Manifestation)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_MANIFESTATION_WHERE_PKS_IN);

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

			for (Manifestation manifestation : (List<Manifestation>)q.list()) {
				map.put(manifestation.getPrimaryKeyObj(), manifestation);

				cacheResult(manifestation);

				uncachedPrimaryKeys.remove(manifestation.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					ManifestationModelImpl.ENTITY_CACHE_ENABLED,
					ManifestationImpl.class, primaryKey, nullModel);
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
	 * Returns all the manifestations.
	 *
	 * @return the manifestations
	 */
	@Override
	public List<Manifestation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the manifestations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @return the range of manifestations
	 */
	@Override
	public List<Manifestation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the manifestations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of manifestations
	 */
	@Override
	public List<Manifestation> findAll(
		int start, int end,
		OrderByComparator<Manifestation> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the manifestations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of manifestations
	 */
	@Override
	public List<Manifestation> findAll(
		int start, int end, OrderByComparator<Manifestation> orderByComparator,
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

		List<Manifestation> list = null;

		if (retrieveFromCache) {
			list = (List<Manifestation>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MANIFESTATION);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MANIFESTATION;

				if (pagination) {
					sql = sql.concat(ManifestationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Manifestation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Manifestation>)QueryUtil.list(
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
	 * Removes all the manifestations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Manifestation manifestation : findAll()) {
			remove(manifestation);
		}
	}

	/**
	 * Returns the number of manifestations.
	 *
	 * @return the number of manifestations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MANIFESTATION);

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

	/**
	 * Returns the primaryKeys of events associated with the manifestation.
	 *
	 * @param pk the primary key of the manifestation
	 * @return long[] of the primaryKeys of events associated with the manifestation
	 */
	@Override
	public long[] getEventPrimaryKeys(long pk) {
		long[] pks = manifestationToEventTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the events associated with the manifestation.
	 *
	 * @param pk the primary key of the manifestation
	 * @return the events associated with the manifestation
	 */
	@Override
	public List<eu.strasbourg.service.agenda.model.Event> getEvents(long pk) {
		return getEvents(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the events associated with the manifestation.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the manifestation
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @return the range of events associated with the manifestation
	 */
	@Override
	public List<eu.strasbourg.service.agenda.model.Event> getEvents(
		long pk, int start, int end) {

		return getEvents(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the events associated with the manifestation.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the manifestation
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of events associated with the manifestation
	 */
	@Override
	public List<eu.strasbourg.service.agenda.model.Event> getEvents(
		long pk, int start, int end,
		OrderByComparator<eu.strasbourg.service.agenda.model.Event>
			orderByComparator) {

		return manifestationToEventTableMapper.getRightBaseModels(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of events associated with the manifestation.
	 *
	 * @param pk the primary key of the manifestation
	 * @return the number of events associated with the manifestation
	 */
	@Override
	public int getEventsSize(long pk) {
		long[] pks = manifestationToEventTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the event is associated with the manifestation.
	 *
	 * @param pk the primary key of the manifestation
	 * @param eventPK the primary key of the event
	 * @return <code>true</code> if the event is associated with the manifestation; <code>false</code> otherwise
	 */
	@Override
	public boolean containsEvent(long pk, long eventPK) {
		return manifestationToEventTableMapper.containsTableMapping(
			pk, eventPK);
	}

	/**
	 * Returns <code>true</code> if the manifestation has any events associated with it.
	 *
	 * @param pk the primary key of the manifestation to check for associations with events
	 * @return <code>true</code> if the manifestation has any events associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsEvents(long pk) {
		if (getEventsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the manifestation and the event. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the manifestation
	 * @param eventPK the primary key of the event
	 */
	@Override
	public void addEvent(long pk, long eventPK) {
		Manifestation manifestation = fetchByPrimaryKey(pk);

		if (manifestation == null) {
			manifestationToEventTableMapper.addTableMapping(
				companyProvider.getCompanyId(), pk, eventPK);
		}
		else {
			manifestationToEventTableMapper.addTableMapping(
				manifestation.getCompanyId(), pk, eventPK);
		}
	}

	/**
	 * Adds an association between the manifestation and the event. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the manifestation
	 * @param event the event
	 */
	@Override
	public void addEvent(
		long pk, eu.strasbourg.service.agenda.model.Event event) {

		Manifestation manifestation = fetchByPrimaryKey(pk);

		if (manifestation == null) {
			manifestationToEventTableMapper.addTableMapping(
				companyProvider.getCompanyId(), pk, event.getPrimaryKey());
		}
		else {
			manifestationToEventTableMapper.addTableMapping(
				manifestation.getCompanyId(), pk, event.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the manifestation and the events. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the manifestation
	 * @param eventPKs the primary keys of the events
	 */
	@Override
	public void addEvents(long pk, long[] eventPKs) {
		long companyId = 0;

		Manifestation manifestation = fetchByPrimaryKey(pk);

		if (manifestation == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = manifestation.getCompanyId();
		}

		manifestationToEventTableMapper.addTableMappings(
			companyId, pk, eventPKs);
	}

	/**
	 * Adds an association between the manifestation and the events. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the manifestation
	 * @param events the events
	 */
	@Override
	public void addEvents(
		long pk, List<eu.strasbourg.service.agenda.model.Event> events) {

		addEvents(
			pk,
			ListUtil.toLongArray(
				events,
				eu.strasbourg.service.agenda.model.Event.EVENT_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the manifestation and its events. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the manifestation to clear the associated events from
	 */
	@Override
	public void clearEvents(long pk) {
		manifestationToEventTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the manifestation and the event. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the manifestation
	 * @param eventPK the primary key of the event
	 */
	@Override
	public void removeEvent(long pk, long eventPK) {
		manifestationToEventTableMapper.deleteTableMapping(pk, eventPK);
	}

	/**
	 * Removes the association between the manifestation and the event. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the manifestation
	 * @param event the event
	 */
	@Override
	public void removeEvent(
		long pk, eu.strasbourg.service.agenda.model.Event event) {

		manifestationToEventTableMapper.deleteTableMapping(
			pk, event.getPrimaryKey());
	}

	/**
	 * Removes the association between the manifestation and the events. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the manifestation
	 * @param eventPKs the primary keys of the events
	 */
	@Override
	public void removeEvents(long pk, long[] eventPKs) {
		manifestationToEventTableMapper.deleteTableMappings(pk, eventPKs);
	}

	/**
	 * Removes the association between the manifestation and the events. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the manifestation
	 * @param events the events
	 */
	@Override
	public void removeEvents(
		long pk, List<eu.strasbourg.service.agenda.model.Event> events) {

		removeEvents(
			pk,
			ListUtil.toLongArray(
				events,
				eu.strasbourg.service.agenda.model.Event.EVENT_ID_ACCESSOR));
	}

	/**
	 * Sets the events associated with the manifestation, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the manifestation
	 * @param eventPKs the primary keys of the events to be associated with the manifestation
	 */
	@Override
	public void setEvents(long pk, long[] eventPKs) {
		Set<Long> newEventPKsSet = SetUtil.fromArray(eventPKs);
		Set<Long> oldEventPKsSet = SetUtil.fromArray(
			manifestationToEventTableMapper.getRightPrimaryKeys(pk));

		Set<Long> removeEventPKsSet = new HashSet<Long>(oldEventPKsSet);

		removeEventPKsSet.removeAll(newEventPKsSet);

		manifestationToEventTableMapper.deleteTableMappings(
			pk, ArrayUtil.toLongArray(removeEventPKsSet));

		newEventPKsSet.removeAll(oldEventPKsSet);

		long companyId = 0;

		Manifestation manifestation = fetchByPrimaryKey(pk);

		if (manifestation == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = manifestation.getCompanyId();
		}

		manifestationToEventTableMapper.addTableMappings(
			companyId, pk, ArrayUtil.toLongArray(newEventPKsSet));
	}

	/**
	 * Sets the events associated with the manifestation, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the manifestation
	 * @param events the events to be associated with the manifestation
	 */
	@Override
	public void setEvents(
		long pk, List<eu.strasbourg.service.agenda.model.Event> events) {

		try {
			long[] eventPKs = new long[events.size()];

			for (int i = 0; i < events.size(); i++) {
				eu.strasbourg.service.agenda.model.Event event = events.get(i);

				eventPKs[i] = event.getPrimaryKey();
			}

			setEvents(pk, eventPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ManifestationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the manifestation persistence.
	 */
	public void afterPropertiesSet() {
		manifestationToEventTableMapper = TableMapperFactory.getTableMapper(
			"agenda_EventToManifestation", "companyId", "manifestationId",
			"eventId", this, eventPersistence);

		_finderPathWithPaginationFindAll = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] {String.class.getName()},
			ManifestationModelImpl.UUID_COLUMN_BITMASK |
			ManifestationModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			ManifestationModelImpl.UUID_COLUMN_BITMASK |
			ManifestationModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			ManifestationModelImpl.UUID_COLUMN_BITMASK |
			ManifestationModelImpl.COMPANYID_COLUMN_BITMASK |
			ManifestationModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindBytitle = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBytitle",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindBytitle = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBytitle", new String[] {String.class.getName()},
			ManifestationModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountBytitle = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBytitle",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupId", new String[] {Long.class.getName()},
			ManifestationModelImpl.GROUPID_COLUMN_BITMASK |
			ManifestationModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByPublicationDateAndStatus =
			new FinderPath(
				ManifestationModelImpl.ENTITY_CACHE_ENABLED,
				ManifestationModelImpl.FINDER_CACHE_ENABLED,
				ManifestationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByPublicationDateAndStatus",
				new String[] {
					Date.class.getName(), Integer.class.getName(),
					Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithPaginationCountByPublicationDateAndStatus =
			new FinderPath(
				ManifestationModelImpl.ENTITY_CACHE_ENABLED,
				ManifestationModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"countByPublicationDateAndStatus",
				new String[] {Date.class.getName(), Integer.class.getName()});

		_finderPathWithPaginationFindByEndDate = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEndDate",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByEndDate = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByEndDate",
			new String[] {Date.class.getName()});

		_finderPathWithPaginationFindByStatusDateAndStatus = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByStatusDateAndStatus",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByStatusDateAndStatus = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByStatusDateAndStatus",
			new String[] {Date.class.getName(), Integer.class.getName()});

		_finderPathFetchBySourceAndIdSource = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySourceAndIdSource",
			new String[] {String.class.getName(), String.class.getName()},
			ManifestationModelImpl.SOURCE_COLUMN_BITMASK |
			ManifestationModelImpl.IDSOURCE_COLUMN_BITMASK);

		_finderPathCountBySourceAndIdSource = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySourceAndIdSource",
			new String[] {String.class.getName(), String.class.getName()});

		_finderPathFetchByIdSource = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED,
			ManifestationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByIdSource", new String[] {String.class.getName()},
			ManifestationModelImpl.IDSOURCE_COLUMN_BITMASK);

		_finderPathCountByIdSource = new FinderPath(
			ManifestationModelImpl.ENTITY_CACHE_ENABLED,
			ManifestationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByIdSource",
			new String[] {String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(ManifestationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("agenda_EventToManifestation");
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	@BeanReference(type = EventPersistence.class)
	protected EventPersistence eventPersistence;

	protected TableMapper
		<Manifestation, eu.strasbourg.service.agenda.model.Event>
			manifestationToEventTableMapper;

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_MANIFESTATION =
		"SELECT manifestation FROM Manifestation manifestation";

	private static final String _SQL_SELECT_MANIFESTATION_WHERE_PKS_IN =
		"SELECT manifestation FROM Manifestation manifestation WHERE manifestationId IN (";

	private static final String _SQL_SELECT_MANIFESTATION_WHERE =
		"SELECT manifestation FROM Manifestation manifestation WHERE ";

	private static final String _SQL_COUNT_MANIFESTATION =
		"SELECT COUNT(manifestation) FROM Manifestation manifestation";

	private static final String _SQL_COUNT_MANIFESTATION_WHERE =
		"SELECT COUNT(manifestation) FROM Manifestation manifestation WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "manifestation.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Manifestation exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Manifestation exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ManifestationPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}