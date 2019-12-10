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
 * @see AnonymisationHistoricPersistence
 * @see eu.strasbourg.service.oidc.service.persistence.AnonymisationHistoricUtil
 * @generated
 */
@ProviderType
public class AnonymisationHistoricPersistenceImpl extends BasePersistenceImpl<AnonymisationHistoric>
	implements AnonymisationHistoricPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AnonymisationHistoricUtil} to access the anonymisation historic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AnonymisationHistoricImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			AnonymisationHistoricModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @return the range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid(String uuid, int start,
		int end, OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid(String uuid, int start,
		int end, OrderByComparator<AnonymisationHistoric> orderByComparator,
		boolean retrieveFromCache) {
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

		List<AnonymisationHistoric> list = null;

		if (retrieveFromCache) {
			list = (List<AnonymisationHistoric>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AnonymisationHistoric anonymisationHistoric : list) {
					if (!Objects.equals(uuid, anonymisationHistoric.getUuid())) {
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

			query.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

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
				query.append(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
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
					list = (List<AnonymisationHistoric>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AnonymisationHistoric>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first anonymisation historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric findByUuid_First(String uuid,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {
		AnonymisationHistoric anonymisationHistoric = fetchByUuid_First(uuid,
				orderByComparator);

		if (anonymisationHistoric != null) {
			return anonymisationHistoric;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymisationHistoricException(msg.toString());
	}

	/**
	 * Returns the first anonymisation historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByUuid_First(String uuid,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		List<AnonymisationHistoric> list = findByUuid(uuid, 0, 1,
				orderByComparator);

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
	public AnonymisationHistoric findByUuid_Last(String uuid,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {
		AnonymisationHistoric anonymisationHistoric = fetchByUuid_Last(uuid,
				orderByComparator);

		if (anonymisationHistoric != null) {
			return anonymisationHistoric;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymisationHistoricException(msg.toString());
	}

	/**
	 * Returns the last anonymisation historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByUuid_Last(String uuid,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<AnonymisationHistoric> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

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
		AnonymisationHistoric anonymisationHistoric = findByPrimaryKey(anonymisationHistoricId);

		Session session = null;

		try {
			session = openSession();

			AnonymisationHistoric[] array = new AnonymisationHistoricImpl[3];

			array[0] = getByUuid_PrevAndNext(session, anonymisationHistoric,
					uuid, orderByComparator, true);

			array[1] = anonymisationHistoric;

			array[2] = getByUuid_PrevAndNext(session, anonymisationHistoric,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AnonymisationHistoric getByUuid_PrevAndNext(Session session,
		AnonymisationHistoric anonymisationHistoric, String uuid,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
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

		query.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

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
			query.append(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(anonymisationHistoric);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AnonymisationHistoric> list = q.list();

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
		for (AnonymisationHistoric anonymisationHistoric : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ANONYMISATIONHISTORIC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "anonymisationHistoric.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "anonymisationHistoric.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(anonymisationHistoric.uuid IS NULL OR anonymisationHistoric.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			AnonymisationHistoricModelImpl.UUID_COLUMN_BITMASK |
			AnonymisationHistoricModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the anonymisation historic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAnonymisationHistoricException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric findByUUID_G(String uuid, long groupId)
		throws NoSuchAnonymisationHistoricException {
		AnonymisationHistoric anonymisationHistoric = fetchByUUID_G(uuid,
				groupId);

		if (anonymisationHistoric == null) {
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

			throw new NoSuchAnonymisationHistoricException(msg.toString());
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
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof AnonymisationHistoric) {
			AnonymisationHistoric anonymisationHistoric = (AnonymisationHistoric)result;

			if (!Objects.equals(uuid, anonymisationHistoric.getUuid()) ||
					(groupId != anonymisationHistoric.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

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

				List<AnonymisationHistoric> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					AnonymisationHistoric anonymisationHistoric = list.get(0);

					result = anonymisationHistoric;

					cacheResult(anonymisationHistoric);

					if ((anonymisationHistoric.getUuid() == null) ||
							!anonymisationHistoric.getUuid().equals(uuid) ||
							(anonymisationHistoric.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, anonymisationHistoric);
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
		AnonymisationHistoric anonymisationHistoric = findByUUID_G(uuid, groupId);

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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ANONYMISATIONHISTORIC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "anonymisationHistoric.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "anonymisationHistoric.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(anonymisationHistoric.uuid IS NULL OR anonymisationHistoric.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "anonymisationHistoric.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			AnonymisationHistoricModelImpl.UUID_COLUMN_BITMASK |
			AnonymisationHistoricModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @return the range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<AnonymisationHistoric> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
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

		List<AnonymisationHistoric> list = null;

		if (retrieveFromCache) {
			list = (List<AnonymisationHistoric>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AnonymisationHistoric anonymisationHistoric : list) {
					if (!Objects.equals(uuid, anonymisationHistoric.getUuid()) ||
							(companyId != anonymisationHistoric.getCompanyId())) {
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

			query.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

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
				query.append(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
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
					list = (List<AnonymisationHistoric>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AnonymisationHistoric>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {
		AnonymisationHistoric anonymisationHistoric = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (anonymisationHistoric != null) {
			return anonymisationHistoric;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymisationHistoricException(msg.toString());
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
	public AnonymisationHistoric fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		List<AnonymisationHistoric> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

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
	public AnonymisationHistoric findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {
		AnonymisationHistoric anonymisationHistoric = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (anonymisationHistoric != null) {
			return anonymisationHistoric;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymisationHistoricException(msg.toString());
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
	public AnonymisationHistoric fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<AnonymisationHistoric> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

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
		AnonymisationHistoric anonymisationHistoric = findByPrimaryKey(anonymisationHistoricId);

		Session session = null;

		try {
			session = openSession();

			AnonymisationHistoric[] array = new AnonymisationHistoricImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, anonymisationHistoric,
					uuid, companyId, orderByComparator, true);

			array[1] = anonymisationHistoric;

			array[2] = getByUuid_C_PrevAndNext(session, anonymisationHistoric,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AnonymisationHistoric getByUuid_C_PrevAndNext(Session session,
		AnonymisationHistoric anonymisationHistoric, String uuid,
		long companyId,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

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
			query.append(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(anonymisationHistoric);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AnonymisationHistoric> list = q.list();

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
		for (AnonymisationHistoric anonymisationHistoric : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ANONYMISATIONHISTORIC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "anonymisationHistoric.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "anonymisationHistoric.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(anonymisationHistoric.uuid IS NULL OR anonymisationHistoric.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "anonymisationHistoric.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			AnonymisationHistoricModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the anonymisation historics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the anonymisation historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @return the range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByGroupId(long groupId, int start,
		int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByGroupId(long groupId, int start,
		int end, OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findByGroupId(long groupId, int start,
		int end, OrderByComparator<AnonymisationHistoric> orderByComparator,
		boolean retrieveFromCache) {
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

		List<AnonymisationHistoric> list = null;

		if (retrieveFromCache) {
			list = (List<AnonymisationHistoric>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AnonymisationHistoric anonymisationHistoric : list) {
					if ((groupId != anonymisationHistoric.getGroupId())) {
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

			query.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<AnonymisationHistoric>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AnonymisationHistoric>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first anonymisation historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric findByGroupId_First(long groupId,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {
		AnonymisationHistoric anonymisationHistoric = fetchByGroupId_First(groupId,
				orderByComparator);

		if (anonymisationHistoric != null) {
			return anonymisationHistoric;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymisationHistoricException(msg.toString());
	}

	/**
	 * Returns the first anonymisation historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByGroupId_First(long groupId,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		List<AnonymisationHistoric> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

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
	public AnonymisationHistoric findByGroupId_Last(long groupId,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException {
		AnonymisationHistoric anonymisationHistoric = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (anonymisationHistoric != null) {
			return anonymisationHistoric;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymisationHistoricException(msg.toString());
	}

	/**
	 * Returns the last anonymisation historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	@Override
	public AnonymisationHistoric fetchByGroupId_Last(long groupId,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<AnonymisationHistoric> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

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
		AnonymisationHistoric anonymisationHistoric = findByPrimaryKey(anonymisationHistoricId);

		Session session = null;

		try {
			session = openSession();

			AnonymisationHistoric[] array = new AnonymisationHistoricImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, anonymisationHistoric,
					groupId, orderByComparator, true);

			array[1] = anonymisationHistoric;

			array[2] = getByGroupId_PrevAndNext(session, anonymisationHistoric,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AnonymisationHistoric getByGroupId_PrevAndNext(Session session,
		AnonymisationHistoric anonymisationHistoric, long groupId,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
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

		query.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE);

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
			query.append(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(anonymisationHistoric);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AnonymisationHistoric> list = q.list();

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
		for (AnonymisationHistoric anonymisationHistoric : findByGroupId(
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ANONYMISATIONHISTORIC_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "anonymisationHistoric.groupId = ?";

	public AnonymisationHistoricPersistenceImpl() {
		setModelClass(AnonymisationHistoric.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the anonymisation historic in the entity cache if it is enabled.
	 *
	 * @param anonymisationHistoric the anonymisation historic
	 */
	@Override
	public void cacheResult(AnonymisationHistoric anonymisationHistoric) {
		entityCache.putResult(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			anonymisationHistoric.getPrimaryKey(), anonymisationHistoric);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				anonymisationHistoric.getUuid(),
				anonymisationHistoric.getGroupId()
			}, anonymisationHistoric);

		anonymisationHistoric.resetOriginalValues();
	}

	/**
	 * Caches the anonymisation historics in the entity cache if it is enabled.
	 *
	 * @param anonymisationHistorics the anonymisation historics
	 */
	@Override
	public void cacheResult(List<AnonymisationHistoric> anonymisationHistorics) {
		for (AnonymisationHistoric anonymisationHistoric : anonymisationHistorics) {
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AnonymisationHistoric anonymisationHistoric) {
		entityCache.removeResult(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			anonymisationHistoric.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AnonymisationHistoricModelImpl)anonymisationHistoric,
			true);
	}

	@Override
	public void clearCache(List<AnonymisationHistoric> anonymisationHistorics) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AnonymisationHistoric anonymisationHistoric : anonymisationHistorics) {
			entityCache.removeResult(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
				AnonymisationHistoricImpl.class,
				anonymisationHistoric.getPrimaryKey());

			clearUniqueFindersCache((AnonymisationHistoricModelImpl)anonymisationHistoric,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		AnonymisationHistoricModelImpl anonymisationHistoricModelImpl) {
		Object[] args = new Object[] {
				anonymisationHistoricModelImpl.getUuid(),
				anonymisationHistoricModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			anonymisationHistoricModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AnonymisationHistoricModelImpl anonymisationHistoricModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					anonymisationHistoricModelImpl.getUuid(),
					anonymisationHistoricModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((anonymisationHistoricModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					anonymisationHistoricModelImpl.getOriginalUuid(),
					anonymisationHistoricModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
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
		AnonymisationHistoric anonymisationHistoric = new AnonymisationHistoricImpl();

		anonymisationHistoric.setNew(true);
		anonymisationHistoric.setPrimaryKey(anonymisationHistoricId);

		String uuid = PortalUUIDUtil.generate();

		anonymisationHistoric.setUuid(uuid);

		anonymisationHistoric.setCompanyId(companyProvider.getCompanyId());

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

			AnonymisationHistoric anonymisationHistoric = (AnonymisationHistoric)session.get(AnonymisationHistoricImpl.class,
					primaryKey);

			if (anonymisationHistoric == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAnonymisationHistoricException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(anonymisationHistoric);
		}
		catch (NoSuchAnonymisationHistoricException nsee) {
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
	protected AnonymisationHistoric removeImpl(
		AnonymisationHistoric anonymisationHistoric) {
		anonymisationHistoric = toUnwrappedModel(anonymisationHistoric);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(anonymisationHistoric)) {
				anonymisationHistoric = (AnonymisationHistoric)session.get(AnonymisationHistoricImpl.class,
						anonymisationHistoric.getPrimaryKeyObj());
			}

			if (anonymisationHistoric != null) {
				session.delete(anonymisationHistoric);
			}
		}
		catch (Exception e) {
			throw processException(e);
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
		anonymisationHistoric = toUnwrappedModel(anonymisationHistoric);

		boolean isNew = anonymisationHistoric.isNew();

		AnonymisationHistoricModelImpl anonymisationHistoricModelImpl = (AnonymisationHistoricModelImpl)anonymisationHistoric;

		if (Validator.isNull(anonymisationHistoric.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			anonymisationHistoric.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (anonymisationHistoric.getCreateDate() == null)) {
			if (serviceContext == null) {
				anonymisationHistoric.setCreateDate(now);
			}
			else {
				anonymisationHistoric.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!anonymisationHistoricModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				anonymisationHistoric.setModifiedDate(now);
			}
			else {
				anonymisationHistoric.setModifiedDate(serviceContext.getModifiedDate(
						now));
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
				anonymisationHistoric = (AnonymisationHistoric)session.merge(anonymisationHistoric);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AnonymisationHistoricModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					anonymisationHistoricModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					anonymisationHistoricModelImpl.getUuid(),
					anonymisationHistoricModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { anonymisationHistoricModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((anonymisationHistoricModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						anonymisationHistoricModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { anonymisationHistoricModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((anonymisationHistoricModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						anonymisationHistoricModelImpl.getOriginalUuid(),
						anonymisationHistoricModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						anonymisationHistoricModelImpl.getUuid(),
						anonymisationHistoricModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((anonymisationHistoricModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						anonymisationHistoricModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { anonymisationHistoricModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
			AnonymisationHistoricImpl.class,
			anonymisationHistoric.getPrimaryKey(), anonymisationHistoric, false);

		clearUniqueFindersCache(anonymisationHistoricModelImpl, false);
		cacheUniqueFindersCache(anonymisationHistoricModelImpl);

		anonymisationHistoric.resetOriginalValues();

		return anonymisationHistoric;
	}

	protected AnonymisationHistoric toUnwrappedModel(
		AnonymisationHistoric anonymisationHistoric) {
		if (anonymisationHistoric instanceof AnonymisationHistoricImpl) {
			return anonymisationHistoric;
		}

		AnonymisationHistoricImpl anonymisationHistoricImpl = new AnonymisationHistoricImpl();

		anonymisationHistoricImpl.setNew(anonymisationHistoric.isNew());
		anonymisationHistoricImpl.setPrimaryKey(anonymisationHistoric.getPrimaryKey());

		anonymisationHistoricImpl.setUuid(anonymisationHistoric.getUuid());
		anonymisationHistoricImpl.setAnonymisationHistoricId(anonymisationHistoric.getAnonymisationHistoricId());
		anonymisationHistoricImpl.setGroupId(anonymisationHistoric.getGroupId());
		anonymisationHistoricImpl.setCompanyId(anonymisationHistoric.getCompanyId());
		anonymisationHistoricImpl.setUserId(anonymisationHistoric.getUserId());
		anonymisationHistoricImpl.setUserName(anonymisationHistoric.getUserName());
		anonymisationHistoricImpl.setCreateDate(anonymisationHistoric.getCreateDate());
		anonymisationHistoricImpl.setModifiedDate(anonymisationHistoric.getModifiedDate());
		anonymisationHistoricImpl.setLastPublishDate(anonymisationHistoric.getLastPublishDate());
		anonymisationHistoricImpl.setStatus(anonymisationHistoric.getStatus());
		anonymisationHistoricImpl.setStatusByUserId(anonymisationHistoric.getStatusByUserId());
		anonymisationHistoricImpl.setStatusByUserName(anonymisationHistoric.getStatusByUserName());
		anonymisationHistoricImpl.setStatusDate(anonymisationHistoric.getStatusDate());
		anonymisationHistoricImpl.setResult(anonymisationHistoric.getResult());
		anonymisationHistoricImpl.setOperations(anonymisationHistoric.getOperations());
		anonymisationHistoricImpl.setErrorDescription(anonymisationHistoric.getErrorDescription());
		anonymisationHistoricImpl.setErrorStackTrace(anonymisationHistoric.getErrorStackTrace());
		anonymisationHistoricImpl.setStartDate(anonymisationHistoric.getStartDate());
		anonymisationHistoricImpl.setFinishDate(anonymisationHistoric.getFinishDate());

		return anonymisationHistoricImpl;
	}

	/**
	 * Returns the anonymisation historic with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the anonymisation historic
	 * @return the anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	 */
	@Override
	public AnonymisationHistoric findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAnonymisationHistoricException {
		AnonymisationHistoric anonymisationHistoric = fetchByPrimaryKey(primaryKey);

		if (anonymisationHistoric == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAnonymisationHistoricException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return anonymisationHistoric;
	}

	/**
	 * Returns the anonymisation historic with the primary key or throws a {@link NoSuchAnonymisationHistoricException} if it could not be found.
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
		Serializable serializable = entityCache.getResult(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
				AnonymisationHistoricImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AnonymisationHistoric anonymisationHistoric = (AnonymisationHistoric)serializable;

		if (anonymisationHistoric == null) {
			Session session = null;

			try {
				session = openSession();

				anonymisationHistoric = (AnonymisationHistoric)session.get(AnonymisationHistoricImpl.class,
						primaryKey);

				if (anonymisationHistoric != null) {
					cacheResult(anonymisationHistoric);
				}
				else {
					entityCache.putResult(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
						AnonymisationHistoricImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
					AnonymisationHistoricImpl.class, primaryKey);

				throw processException(e);
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
	public AnonymisationHistoric fetchByPrimaryKey(long anonymisationHistoricId) {
		return fetchByPrimaryKey((Serializable)anonymisationHistoricId);
	}

	@Override
	public Map<Serializable, AnonymisationHistoric> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AnonymisationHistoric> map = new HashMap<Serializable, AnonymisationHistoric>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AnonymisationHistoric anonymisationHistoric = fetchByPrimaryKey(primaryKey);

			if (anonymisationHistoric != null) {
				map.put(primaryKey, anonymisationHistoric);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
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

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ANONYMISATIONHISTORIC_WHERE_PKS_IN);

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

			for (AnonymisationHistoric anonymisationHistoric : (List<AnonymisationHistoric>)q.list()) {
				map.put(anonymisationHistoric.getPrimaryKeyObj(),
					anonymisationHistoric);

				cacheResult(anonymisationHistoric);

				uncachedPrimaryKeys.remove(anonymisationHistoric.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AnonymisationHistoricModelImpl.ENTITY_CACHE_ENABLED,
					AnonymisationHistoricImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findAll(int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the anonymisation historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of anonymisation historics
	 */
	@Override
	public List<AnonymisationHistoric> findAll(int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
		boolean retrieveFromCache) {
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

		List<AnonymisationHistoric> list = null;

		if (retrieveFromCache) {
			list = (List<AnonymisationHistoric>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ANONYMISATIONHISTORIC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ANONYMISATIONHISTORIC;

				if (pagination) {
					sql = sql.concat(AnonymisationHistoricModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AnonymisationHistoric>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AnonymisationHistoric>)QueryUtil.list(q,
							getDialect(), start, end);
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
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ANONYMISATIONHISTORIC);

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
		return AnonymisationHistoricModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the anonymisation historic persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AnonymisationHistoricImpl.class.getName());
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
	private static final String _SQL_SELECT_ANONYMISATIONHISTORIC = "SELECT anonymisationHistoric FROM AnonymisationHistoric anonymisationHistoric";
	private static final String _SQL_SELECT_ANONYMISATIONHISTORIC_WHERE_PKS_IN = "SELECT anonymisationHistoric FROM AnonymisationHistoric anonymisationHistoric WHERE anonymisationHistoricId IN (";
	private static final String _SQL_SELECT_ANONYMISATIONHISTORIC_WHERE = "SELECT anonymisationHistoric FROM AnonymisationHistoric anonymisationHistoric WHERE ";
	private static final String _SQL_COUNT_ANONYMISATIONHISTORIC = "SELECT COUNT(anonymisationHistoric) FROM AnonymisationHistoric anonymisationHistoric";
	private static final String _SQL_COUNT_ANONYMISATIONHISTORIC_WHERE = "SELECT COUNT(anonymisationHistoric) FROM AnonymisationHistoric anonymisationHistoric WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "anonymisationHistoric.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AnonymisationHistoric exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AnonymisationHistoric exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AnonymisationHistoricPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}