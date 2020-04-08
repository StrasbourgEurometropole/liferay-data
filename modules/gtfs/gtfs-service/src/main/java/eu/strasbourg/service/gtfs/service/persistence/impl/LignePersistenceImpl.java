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

package eu.strasbourg.service.gtfs.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.gtfs.exception.NoSuchLigneException;
import eu.strasbourg.service.gtfs.model.Ligne;
import eu.strasbourg.service.gtfs.model.impl.LigneImpl;
import eu.strasbourg.service.gtfs.model.impl.LigneModelImpl;
import eu.strasbourg.service.gtfs.service.persistence.LignePersistence;

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
 * The persistence implementation for the ligne service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see LignePersistence
 * @see eu.strasbourg.service.gtfs.service.persistence.LigneUtil
 * @generated
 */
@ProviderType
public class LignePersistenceImpl extends BasePersistenceImpl<Ligne>
	implements LignePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LigneUtil} to access the ligne persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LigneImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, LigneImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, LigneImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, LigneImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, LigneImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			LigneModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the lignes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching lignes
	 */
	@Override
	public List<Ligne> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lignes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of lignes
	 * @param end the upper bound of the range of lignes (not inclusive)
	 * @return the range of matching lignes
	 */
	@Override
	public List<Ligne> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lignes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of lignes
	 * @param end the upper bound of the range of lignes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lignes
	 */
	@Override
	public List<Ligne> findByUuid(String uuid, int start, int end,
		OrderByComparator<Ligne> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the lignes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of lignes
	 * @param end the upper bound of the range of lignes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching lignes
	 */
	@Override
	public List<Ligne> findByUuid(String uuid, int start, int end,
		OrderByComparator<Ligne> orderByComparator, boolean retrieveFromCache) {
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

		List<Ligne> list = null;

		if (retrieveFromCache) {
			list = (List<Ligne>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Ligne ligne : list) {
					if (!Objects.equals(uuid, ligne.getUuid())) {
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

			query.append(_SQL_SELECT_LIGNE_WHERE);

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
				query.append(LigneModelImpl.ORDER_BY_JPQL);
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
					list = (List<Ligne>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Ligne>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first ligne in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ligne
	 * @throws NoSuchLigneException if a matching ligne could not be found
	 */
	@Override
	public Ligne findByUuid_First(String uuid,
		OrderByComparator<Ligne> orderByComparator) throws NoSuchLigneException {
		Ligne ligne = fetchByUuid_First(uuid, orderByComparator);

		if (ligne != null) {
			return ligne;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLigneException(msg.toString());
	}

	/**
	 * Returns the first ligne in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ligne, or <code>null</code> if a matching ligne could not be found
	 */
	@Override
	public Ligne fetchByUuid_First(String uuid,
		OrderByComparator<Ligne> orderByComparator) {
		List<Ligne> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ligne in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ligne
	 * @throws NoSuchLigneException if a matching ligne could not be found
	 */
	@Override
	public Ligne findByUuid_Last(String uuid,
		OrderByComparator<Ligne> orderByComparator) throws NoSuchLigneException {
		Ligne ligne = fetchByUuid_Last(uuid, orderByComparator);

		if (ligne != null) {
			return ligne;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLigneException(msg.toString());
	}

	/**
	 * Returns the last ligne in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ligne, or <code>null</code> if a matching ligne could not be found
	 */
	@Override
	public Ligne fetchByUuid_Last(String uuid,
		OrderByComparator<Ligne> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Ligne> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lignes before and after the current ligne in the ordered set where uuid = &#63;.
	 *
	 * @param ligneId the primary key of the current ligne
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ligne
	 * @throws NoSuchLigneException if a ligne with the primary key could not be found
	 */
	@Override
	public Ligne[] findByUuid_PrevAndNext(long ligneId, String uuid,
		OrderByComparator<Ligne> orderByComparator) throws NoSuchLigneException {
		Ligne ligne = findByPrimaryKey(ligneId);

		Session session = null;

		try {
			session = openSession();

			Ligne[] array = new LigneImpl[3];

			array[0] = getByUuid_PrevAndNext(session, ligne, uuid,
					orderByComparator, true);

			array[1] = ligne;

			array[2] = getByUuid_PrevAndNext(session, ligne, uuid,
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

	protected Ligne getByUuid_PrevAndNext(Session session, Ligne ligne,
		String uuid, OrderByComparator<Ligne> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LIGNE_WHERE);

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
			query.append(LigneModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(ligne);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Ligne> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lignes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Ligne ligne : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(ligne);
		}
	}

	/**
	 * Returns the number of lignes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching lignes
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LIGNE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "ligne.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "ligne.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(ligne.uuid IS NULL OR ligne.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, LigneImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			LigneModelImpl.UUID_COLUMN_BITMASK |
			LigneModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the ligne where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchLigneException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching ligne
	 * @throws NoSuchLigneException if a matching ligne could not be found
	 */
	@Override
	public Ligne findByUUID_G(String uuid, long groupId)
		throws NoSuchLigneException {
		Ligne ligne = fetchByUUID_G(uuid, groupId);

		if (ligne == null) {
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

			throw new NoSuchLigneException(msg.toString());
		}

		return ligne;
	}

	/**
	 * Returns the ligne where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching ligne, or <code>null</code> if a matching ligne could not be found
	 */
	@Override
	public Ligne fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the ligne where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching ligne, or <code>null</code> if a matching ligne could not be found
	 */
	@Override
	public Ligne fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Ligne) {
			Ligne ligne = (Ligne)result;

			if (!Objects.equals(uuid, ligne.getUuid()) ||
					(groupId != ligne.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LIGNE_WHERE);

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

				List<Ligne> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Ligne ligne = list.get(0);

					result = ligne;

					cacheResult(ligne);

					if ((ligne.getUuid() == null) ||
							!ligne.getUuid().equals(uuid) ||
							(ligne.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, ligne);
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
			return (Ligne)result;
		}
	}

	/**
	 * Removes the ligne where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the ligne that was removed
	 */
	@Override
	public Ligne removeByUUID_G(String uuid, long groupId)
		throws NoSuchLigneException {
		Ligne ligne = findByUUID_G(uuid, groupId);

		return remove(ligne);
	}

	/**
	 * Returns the number of lignes where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching lignes
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LIGNE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "ligne.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "ligne.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(ligne.uuid IS NULL OR ligne.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "ligne.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, LigneImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, LigneImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			LigneModelImpl.UUID_COLUMN_BITMASK |
			LigneModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the lignes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching lignes
	 */
	@Override
	public List<Ligne> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lignes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of lignes
	 * @param end the upper bound of the range of lignes (not inclusive)
	 * @return the range of matching lignes
	 */
	@Override
	public List<Ligne> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lignes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of lignes
	 * @param end the upper bound of the range of lignes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lignes
	 */
	@Override
	public List<Ligne> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Ligne> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the lignes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of lignes
	 * @param end the upper bound of the range of lignes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching lignes
	 */
	@Override
	public List<Ligne> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Ligne> orderByComparator,
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

		List<Ligne> list = null;

		if (retrieveFromCache) {
			list = (List<Ligne>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Ligne ligne : list) {
					if (!Objects.equals(uuid, ligne.getUuid()) ||
							(companyId != ligne.getCompanyId())) {
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

			query.append(_SQL_SELECT_LIGNE_WHERE);

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
				query.append(LigneModelImpl.ORDER_BY_JPQL);
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
					list = (List<Ligne>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Ligne>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ligne
	 * @throws NoSuchLigneException if a matching ligne could not be found
	 */
	@Override
	public Ligne findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Ligne> orderByComparator) throws NoSuchLigneException {
		Ligne ligne = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (ligne != null) {
			return ligne;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLigneException(msg.toString());
	}

	/**
	 * Returns the first ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ligne, or <code>null</code> if a matching ligne could not be found
	 */
	@Override
	public Ligne fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Ligne> orderByComparator) {
		List<Ligne> list = findByUuid_C(uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ligne
	 * @throws NoSuchLigneException if a matching ligne could not be found
	 */
	@Override
	public Ligne findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Ligne> orderByComparator) throws NoSuchLigneException {
		Ligne ligne = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (ligne != null) {
			return ligne;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLigneException(msg.toString());
	}

	/**
	 * Returns the last ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ligne, or <code>null</code> if a matching ligne could not be found
	 */
	@Override
	public Ligne fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Ligne> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Ligne> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lignes before and after the current ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param ligneId the primary key of the current ligne
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ligne
	 * @throws NoSuchLigneException if a ligne with the primary key could not be found
	 */
	@Override
	public Ligne[] findByUuid_C_PrevAndNext(long ligneId, String uuid,
		long companyId, OrderByComparator<Ligne> orderByComparator)
		throws NoSuchLigneException {
		Ligne ligne = findByPrimaryKey(ligneId);

		Session session = null;

		try {
			session = openSession();

			Ligne[] array = new LigneImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, ligne, uuid, companyId,
					orderByComparator, true);

			array[1] = ligne;

			array[2] = getByUuid_C_PrevAndNext(session, ligne, uuid, companyId,
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

	protected Ligne getByUuid_C_PrevAndNext(Session session, Ligne ligne,
		String uuid, long companyId,
		OrderByComparator<Ligne> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LIGNE_WHERE);

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
			query.append(LigneModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(ligne);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Ligne> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lignes where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Ligne ligne : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(ligne);
		}
	}

	/**
	 * Returns the number of lignes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching lignes
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LIGNE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "ligne.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "ligne.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(ligne.uuid IS NULL OR ligne.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "ligne.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, LigneImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, LigneImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			LigneModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the lignes where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching lignes
	 */
	@Override
	public List<Ligne> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lignes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of lignes
	 * @param end the upper bound of the range of lignes (not inclusive)
	 * @return the range of matching lignes
	 */
	@Override
	public List<Ligne> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lignes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of lignes
	 * @param end the upper bound of the range of lignes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lignes
	 */
	@Override
	public List<Ligne> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Ligne> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the lignes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of lignes
	 * @param end the upper bound of the range of lignes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching lignes
	 */
	@Override
	public List<Ligne> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Ligne> orderByComparator, boolean retrieveFromCache) {
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

		List<Ligne> list = null;

		if (retrieveFromCache) {
			list = (List<Ligne>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Ligne ligne : list) {
					if ((groupId != ligne.getGroupId())) {
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

			query.append(_SQL_SELECT_LIGNE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LigneModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Ligne>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Ligne>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first ligne in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ligne
	 * @throws NoSuchLigneException if a matching ligne could not be found
	 */
	@Override
	public Ligne findByGroupId_First(long groupId,
		OrderByComparator<Ligne> orderByComparator) throws NoSuchLigneException {
		Ligne ligne = fetchByGroupId_First(groupId, orderByComparator);

		if (ligne != null) {
			return ligne;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLigneException(msg.toString());
	}

	/**
	 * Returns the first ligne in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ligne, or <code>null</code> if a matching ligne could not be found
	 */
	@Override
	public Ligne fetchByGroupId_First(long groupId,
		OrderByComparator<Ligne> orderByComparator) {
		List<Ligne> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ligne in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ligne
	 * @throws NoSuchLigneException if a matching ligne could not be found
	 */
	@Override
	public Ligne findByGroupId_Last(long groupId,
		OrderByComparator<Ligne> orderByComparator) throws NoSuchLigneException {
		Ligne ligne = fetchByGroupId_Last(groupId, orderByComparator);

		if (ligne != null) {
			return ligne;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLigneException(msg.toString());
	}

	/**
	 * Returns the last ligne in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ligne, or <code>null</code> if a matching ligne could not be found
	 */
	@Override
	public Ligne fetchByGroupId_Last(long groupId,
		OrderByComparator<Ligne> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Ligne> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lignes before and after the current ligne in the ordered set where groupId = &#63;.
	 *
	 * @param ligneId the primary key of the current ligne
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ligne
	 * @throws NoSuchLigneException if a ligne with the primary key could not be found
	 */
	@Override
	public Ligne[] findByGroupId_PrevAndNext(long ligneId, long groupId,
		OrderByComparator<Ligne> orderByComparator) throws NoSuchLigneException {
		Ligne ligne = findByPrimaryKey(ligneId);

		Session session = null;

		try {
			session = openSession();

			Ligne[] array = new LigneImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, ligne, groupId,
					orderByComparator, true);

			array[1] = ligne;

			array[2] = getByGroupId_PrevAndNext(session, ligne, groupId,
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

	protected Ligne getByGroupId_PrevAndNext(Session session, Ligne ligne,
		long groupId, OrderByComparator<Ligne> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LIGNE_WHERE);

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
			query.append(LigneModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ligne);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Ligne> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lignes where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (Ligne ligne : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(ligne);
		}
	}

	/**
	 * Returns the number of lignes where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching lignes
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LIGNE_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "ligne.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_ROUTEID = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, LigneImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByRouteId",
			new String[] { String.class.getName() },
			LigneModelImpl.ROUTEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ROUTEID = new FinderPath(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRouteId",
			new String[] { String.class.getName() });

	/**
	 * Returns the ligne where routeId = &#63; or throws a {@link NoSuchLigneException} if it could not be found.
	 *
	 * @param routeId the route ID
	 * @return the matching ligne
	 * @throws NoSuchLigneException if a matching ligne could not be found
	 */
	@Override
	public Ligne findByRouteId(String routeId) throws NoSuchLigneException {
		Ligne ligne = fetchByRouteId(routeId);

		if (ligne == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("routeId=");
			msg.append(routeId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLigneException(msg.toString());
		}

		return ligne;
	}

	/**
	 * Returns the ligne where routeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param routeId the route ID
	 * @return the matching ligne, or <code>null</code> if a matching ligne could not be found
	 */
	@Override
	public Ligne fetchByRouteId(String routeId) {
		return fetchByRouteId(routeId, true);
	}

	/**
	 * Returns the ligne where routeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param routeId the route ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching ligne, or <code>null</code> if a matching ligne could not be found
	 */
	@Override
	public Ligne fetchByRouteId(String routeId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { routeId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_ROUTEID,
					finderArgs, this);
		}

		if (result instanceof Ligne) {
			Ligne ligne = (Ligne)result;

			if (!Objects.equals(routeId, ligne.getRouteId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_LIGNE_WHERE);

			boolean bindRouteId = false;

			if (routeId == null) {
				query.append(_FINDER_COLUMN_ROUTEID_ROUTEID_1);
			}
			else if (routeId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ROUTEID_ROUTEID_3);
			}
			else {
				bindRouteId = true;

				query.append(_FINDER_COLUMN_ROUTEID_ROUTEID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRouteId) {
					qPos.add(routeId);
				}

				List<Ligne> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_ROUTEID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"LignePersistenceImpl.fetchByRouteId(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Ligne ligne = list.get(0);

					result = ligne;

					cacheResult(ligne);

					if ((ligne.getRouteId() == null) ||
							!ligne.getRouteId().equals(routeId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_ROUTEID,
							finderArgs, ligne);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_ROUTEID,
					finderArgs);

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
			return (Ligne)result;
		}
	}

	/**
	 * Removes the ligne where routeId = &#63; from the database.
	 *
	 * @param routeId the route ID
	 * @return the ligne that was removed
	 */
	@Override
	public Ligne removeByRouteId(String routeId) throws NoSuchLigneException {
		Ligne ligne = findByRouteId(routeId);

		return remove(ligne);
	}

	/**
	 * Returns the number of lignes where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @return the number of matching lignes
	 */
	@Override
	public int countByRouteId(String routeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ROUTEID;

		Object[] finderArgs = new Object[] { routeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LIGNE_WHERE);

			boolean bindRouteId = false;

			if (routeId == null) {
				query.append(_FINDER_COLUMN_ROUTEID_ROUTEID_1);
			}
			else if (routeId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ROUTEID_ROUTEID_3);
			}
			else {
				bindRouteId = true;

				query.append(_FINDER_COLUMN_ROUTEID_ROUTEID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRouteId) {
					qPos.add(routeId);
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

	private static final String _FINDER_COLUMN_ROUTEID_ROUTEID_1 = "ligne.routeId IS NULL";
	private static final String _FINDER_COLUMN_ROUTEID_ROUTEID_2 = "ligne.routeId = ?";
	private static final String _FINDER_COLUMN_ROUTEID_ROUTEID_3 = "(ligne.routeId IS NULL OR ligne.routeId = '')";

	public LignePersistenceImpl() {
		setModelClass(Ligne.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("type", "type_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ligne in the entity cache if it is enabled.
	 *
	 * @param ligne the ligne
	 */
	@Override
	public void cacheResult(Ligne ligne) {
		entityCache.putResult(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneImpl.class, ligne.getPrimaryKey(), ligne);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { ligne.getUuid(), ligne.getGroupId() }, ligne);

		finderCache.putResult(FINDER_PATH_FETCH_BY_ROUTEID,
			new Object[] { ligne.getRouteId() }, ligne);

		ligne.resetOriginalValues();
	}

	/**
	 * Caches the lignes in the entity cache if it is enabled.
	 *
	 * @param lignes the lignes
	 */
	@Override
	public void cacheResult(List<Ligne> lignes) {
		for (Ligne ligne : lignes) {
			if (entityCache.getResult(LigneModelImpl.ENTITY_CACHE_ENABLED,
						LigneImpl.class, ligne.getPrimaryKey()) == null) {
				cacheResult(ligne);
			}
			else {
				ligne.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all lignes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LigneImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ligne.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Ligne ligne) {
		entityCache.removeResult(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneImpl.class, ligne.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LigneModelImpl)ligne, true);
	}

	@Override
	public void clearCache(List<Ligne> lignes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Ligne ligne : lignes) {
			entityCache.removeResult(LigneModelImpl.ENTITY_CACHE_ENABLED,
				LigneImpl.class, ligne.getPrimaryKey());

			clearUniqueFindersCache((LigneModelImpl)ligne, true);
		}
	}

	protected void cacheUniqueFindersCache(LigneModelImpl ligneModelImpl) {
		Object[] args = new Object[] {
				ligneModelImpl.getUuid(), ligneModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			ligneModelImpl, false);

		args = new Object[] { ligneModelImpl.getRouteId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_ROUTEID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_ROUTEID, args,
			ligneModelImpl, false);
	}

	protected void clearUniqueFindersCache(LigneModelImpl ligneModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					ligneModelImpl.getUuid(), ligneModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((ligneModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					ligneModelImpl.getOriginalUuid(),
					ligneModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { ligneModelImpl.getRouteId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ROUTEID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ROUTEID, args);
		}

		if ((ligneModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ROUTEID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { ligneModelImpl.getOriginalRouteId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ROUTEID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ROUTEID, args);
		}
	}

	/**
	 * Creates a new ligne with the primary key. Does not add the ligne to the database.
	 *
	 * @param ligneId the primary key for the new ligne
	 * @return the new ligne
	 */
	@Override
	public Ligne create(long ligneId) {
		Ligne ligne = new LigneImpl();

		ligne.setNew(true);
		ligne.setPrimaryKey(ligneId);

		String uuid = PortalUUIDUtil.generate();

		ligne.setUuid(uuid);

		ligne.setCompanyId(companyProvider.getCompanyId());

		return ligne;
	}

	/**
	 * Removes the ligne with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ligneId the primary key of the ligne
	 * @return the ligne that was removed
	 * @throws NoSuchLigneException if a ligne with the primary key could not be found
	 */
	@Override
	public Ligne remove(long ligneId) throws NoSuchLigneException {
		return remove((Serializable)ligneId);
	}

	/**
	 * Removes the ligne with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ligne
	 * @return the ligne that was removed
	 * @throws NoSuchLigneException if a ligne with the primary key could not be found
	 */
	@Override
	public Ligne remove(Serializable primaryKey) throws NoSuchLigneException {
		Session session = null;

		try {
			session = openSession();

			Ligne ligne = (Ligne)session.get(LigneImpl.class, primaryKey);

			if (ligne == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLigneException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ligne);
		}
		catch (NoSuchLigneException nsee) {
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
	protected Ligne removeImpl(Ligne ligne) {
		ligne = toUnwrappedModel(ligne);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ligne)) {
				ligne = (Ligne)session.get(LigneImpl.class,
						ligne.getPrimaryKeyObj());
			}

			if (ligne != null) {
				session.delete(ligne);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ligne != null) {
			clearCache(ligne);
		}

		return ligne;
	}

	@Override
	public Ligne updateImpl(Ligne ligne) {
		ligne = toUnwrappedModel(ligne);

		boolean isNew = ligne.isNew();

		LigneModelImpl ligneModelImpl = (LigneModelImpl)ligne;

		if (Validator.isNull(ligne.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			ligne.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (ligne.getCreateDate() == null)) {
			if (serviceContext == null) {
				ligne.setCreateDate(now);
			}
			else {
				ligne.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!ligneModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				ligne.setModifiedDate(now);
			}
			else {
				ligne.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (ligne.isNew()) {
				session.save(ligne);

				ligne.setNew(false);
			}
			else {
				ligne = (Ligne)session.merge(ligne);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LigneModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { ligneModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					ligneModelImpl.getUuid(), ligneModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { ligneModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((ligneModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { ligneModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { ligneModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((ligneModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ligneModelImpl.getOriginalUuid(),
						ligneModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						ligneModelImpl.getUuid(), ligneModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((ligneModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { ligneModelImpl.getOriginalGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { ligneModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(LigneModelImpl.ENTITY_CACHE_ENABLED,
			LigneImpl.class, ligne.getPrimaryKey(), ligne, false);

		clearUniqueFindersCache(ligneModelImpl, false);
		cacheUniqueFindersCache(ligneModelImpl);

		ligne.resetOriginalValues();

		return ligne;
	}

	protected Ligne toUnwrappedModel(Ligne ligne) {
		if (ligne instanceof LigneImpl) {
			return ligne;
		}

		LigneImpl ligneImpl = new LigneImpl();

		ligneImpl.setNew(ligne.isNew());
		ligneImpl.setPrimaryKey(ligne.getPrimaryKey());

		ligneImpl.setUuid(ligne.getUuid());
		ligneImpl.setLigneId(ligne.getLigneId());
		ligneImpl.setGroupId(ligne.getGroupId());
		ligneImpl.setCompanyId(ligne.getCompanyId());
		ligneImpl.setUserId(ligne.getUserId());
		ligneImpl.setUserName(ligne.getUserName());
		ligneImpl.setCreateDate(ligne.getCreateDate());
		ligneImpl.setModifiedDate(ligne.getModifiedDate());
		ligneImpl.setStatus(ligne.getStatus());
		ligneImpl.setStatusByUserId(ligne.getStatusByUserId());
		ligneImpl.setStatusByUserName(ligne.getStatusByUserName());
		ligneImpl.setStatusDate(ligne.getStatusDate());
		ligneImpl.setRouteId(ligne.getRouteId());
		ligneImpl.setShortName(ligne.getShortName());
		ligneImpl.setTitle(ligne.getTitle());
		ligneImpl.setType(ligne.getType());
		ligneImpl.setBackgroundColor(ligne.getBackgroundColor());
		ligneImpl.setTextColor(ligne.getTextColor());

		return ligneImpl;
	}

	/**
	 * Returns the ligne with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ligne
	 * @return the ligne
	 * @throws NoSuchLigneException if a ligne with the primary key could not be found
	 */
	@Override
	public Ligne findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLigneException {
		Ligne ligne = fetchByPrimaryKey(primaryKey);

		if (ligne == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLigneException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ligne;
	}

	/**
	 * Returns the ligne with the primary key or throws a {@link NoSuchLigneException} if it could not be found.
	 *
	 * @param ligneId the primary key of the ligne
	 * @return the ligne
	 * @throws NoSuchLigneException if a ligne with the primary key could not be found
	 */
	@Override
	public Ligne findByPrimaryKey(long ligneId) throws NoSuchLigneException {
		return findByPrimaryKey((Serializable)ligneId);
	}

	/**
	 * Returns the ligne with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ligne
	 * @return the ligne, or <code>null</code> if a ligne with the primary key could not be found
	 */
	@Override
	public Ligne fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LigneModelImpl.ENTITY_CACHE_ENABLED,
				LigneImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Ligne ligne = (Ligne)serializable;

		if (ligne == null) {
			Session session = null;

			try {
				session = openSession();

				ligne = (Ligne)session.get(LigneImpl.class, primaryKey);

				if (ligne != null) {
					cacheResult(ligne);
				}
				else {
					entityCache.putResult(LigneModelImpl.ENTITY_CACHE_ENABLED,
						LigneImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LigneModelImpl.ENTITY_CACHE_ENABLED,
					LigneImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ligne;
	}

	/**
	 * Returns the ligne with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ligneId the primary key of the ligne
	 * @return the ligne, or <code>null</code> if a ligne with the primary key could not be found
	 */
	@Override
	public Ligne fetchByPrimaryKey(long ligneId) {
		return fetchByPrimaryKey((Serializable)ligneId);
	}

	@Override
	public Map<Serializable, Ligne> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Ligne> map = new HashMap<Serializable, Ligne>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Ligne ligne = fetchByPrimaryKey(primaryKey);

			if (ligne != null) {
				map.put(primaryKey, ligne);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LigneModelImpl.ENTITY_CACHE_ENABLED,
					LigneImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Ligne)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LIGNE_WHERE_PKS_IN);

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

			for (Ligne ligne : (List<Ligne>)q.list()) {
				map.put(ligne.getPrimaryKeyObj(), ligne);

				cacheResult(ligne);

				uncachedPrimaryKeys.remove(ligne.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LigneModelImpl.ENTITY_CACHE_ENABLED,
					LigneImpl.class, primaryKey, nullModel);
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
	 * Returns all the lignes.
	 *
	 * @return the lignes
	 */
	@Override
	public List<Ligne> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lignes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of lignes
	 * @param end the upper bound of the range of lignes (not inclusive)
	 * @return the range of lignes
	 */
	@Override
	public List<Ligne> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the lignes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of lignes
	 * @param end the upper bound of the range of lignes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of lignes
	 */
	@Override
	public List<Ligne> findAll(int start, int end,
		OrderByComparator<Ligne> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the lignes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of lignes
	 * @param end the upper bound of the range of lignes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of lignes
	 */
	@Override
	public List<Ligne> findAll(int start, int end,
		OrderByComparator<Ligne> orderByComparator, boolean retrieveFromCache) {
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

		List<Ligne> list = null;

		if (retrieveFromCache) {
			list = (List<Ligne>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LIGNE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LIGNE;

				if (pagination) {
					sql = sql.concat(LigneModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Ligne>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Ligne>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the lignes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Ligne ligne : findAll()) {
			remove(ligne);
		}
	}

	/**
	 * Returns the number of lignes.
	 *
	 * @return the number of lignes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LIGNE);

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
		return LigneModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ligne persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LigneImpl.class.getName());
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
	private static final String _SQL_SELECT_LIGNE = "SELECT ligne FROM Ligne ligne";
	private static final String _SQL_SELECT_LIGNE_WHERE_PKS_IN = "SELECT ligne FROM Ligne ligne WHERE ligneId IN (";
	private static final String _SQL_SELECT_LIGNE_WHERE = "SELECT ligne FROM Ligne ligne WHERE ";
	private static final String _SQL_COUNT_LIGNE = "SELECT COUNT(ligne) FROM Ligne ligne";
	private static final String _SQL_COUNT_LIGNE_WHERE = "SELECT COUNT(ligne) FROM Ligne ligne WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ligne.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Ligne exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Ligne exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LignePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "type"
			});
}