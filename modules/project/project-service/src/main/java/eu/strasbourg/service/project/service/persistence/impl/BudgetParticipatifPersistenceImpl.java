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

package eu.strasbourg.service.project.service.persistence.impl;

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

import eu.strasbourg.service.project.exception.NoSuchBudgetParticipatifException;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.impl.BudgetParticipatifImpl;
import eu.strasbourg.service.project.model.impl.BudgetParticipatifModelImpl;
import eu.strasbourg.service.project.service.persistence.BudgetParticipatifPersistence;

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
 * The persistence implementation for the budget participatif service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see BudgetParticipatifPersistence
 * @see eu.strasbourg.service.project.service.persistence.BudgetParticipatifUtil
 * @generated
 */
@ProviderType
public class BudgetParticipatifPersistenceImpl extends BasePersistenceImpl<BudgetParticipatif>
	implements BudgetParticipatifPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BudgetParticipatifUtil} to access the budget participatif persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BudgetParticipatifImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			BudgetParticipatifModelImpl.UUID_COLUMN_BITMASK |
			BudgetParticipatifModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the budget participatifs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget participatifs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByUuid(String uuid, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByUuid(String uuid, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
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

		List<BudgetParticipatif> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetParticipatif>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetParticipatif budgetParticipatif : list) {
					if (!Objects.equals(uuid, budgetParticipatif.getUuid())) {
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

			query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

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
				query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
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
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
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
	 * Returns the first budget participatif in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByUuid_First(String uuid,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByUuid_First(uuid,
				orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the first budget participatif in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByUuid_First(String uuid,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		List<BudgetParticipatif> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget participatif in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByUuid_Last(String uuid,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByUuid_Last(uuid,
				orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the last budget participatif in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByUuid_Last(String uuid,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<BudgetParticipatif> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget participatifs before and after the current budget participatif in the ordered set where uuid = &#63;.
	 *
	 * @param budgetParticipatifId the primary key of the current budget participatif
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	@Override
	public BudgetParticipatif[] findByUuid_PrevAndNext(
		long budgetParticipatifId, String uuid,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = findByPrimaryKey(budgetParticipatifId);

		Session session = null;

		try {
			session = openSession();

			BudgetParticipatif[] array = new BudgetParticipatifImpl[3];

			array[0] = getByUuid_PrevAndNext(session, budgetParticipatif, uuid,
					orderByComparator, true);

			array[1] = budgetParticipatif;

			array[2] = getByUuid_PrevAndNext(session, budgetParticipatif, uuid,
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

	protected BudgetParticipatif getByUuid_PrevAndNext(Session session,
		BudgetParticipatif budgetParticipatif, String uuid,
		OrderByComparator<BudgetParticipatif> orderByComparator,
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

		query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

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
			query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(budgetParticipatif);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetParticipatif> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget participatifs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (BudgetParticipatif budgetParticipatif : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(budgetParticipatif);
		}
	}

	/**
	 * Returns the number of budget participatifs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching budget participatifs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUDGETPARTICIPATIF_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "budgetParticipatif.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "budgetParticipatif.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(budgetParticipatif.uuid IS NULL OR budgetParticipatif.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			BudgetParticipatifModelImpl.UUID_COLUMN_BITMASK |
			BudgetParticipatifModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the budget participatif where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBudgetParticipatifException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByUUID_G(String uuid, long groupId)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByUUID_G(uuid, groupId);

		if (budgetParticipatif == null) {
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

			throw new NoSuchBudgetParticipatifException(msg.toString());
		}

		return budgetParticipatif;
	}

	/**
	 * Returns the budget participatif where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the budget participatif where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof BudgetParticipatif) {
			BudgetParticipatif budgetParticipatif = (BudgetParticipatif)result;

			if (!Objects.equals(uuid, budgetParticipatif.getUuid()) ||
					(groupId != budgetParticipatif.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

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

				List<BudgetParticipatif> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					BudgetParticipatif budgetParticipatif = list.get(0);

					result = budgetParticipatif;

					cacheResult(budgetParticipatif);

					if ((budgetParticipatif.getUuid() == null) ||
							!budgetParticipatif.getUuid().equals(uuid) ||
							(budgetParticipatif.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, budgetParticipatif);
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
			return (BudgetParticipatif)result;
		}
	}

	/**
	 * Removes the budget participatif where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the budget participatif that was removed
	 */
	@Override
	public BudgetParticipatif removeByUUID_G(String uuid, long groupId)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = findByUUID_G(uuid, groupId);

		return remove(budgetParticipatif);
	}

	/**
	 * Returns the number of budget participatifs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching budget participatifs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BUDGETPARTICIPATIF_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "budgetParticipatif.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "budgetParticipatif.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(budgetParticipatif.uuid IS NULL OR budgetParticipatif.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "budgetParticipatif.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			BudgetParticipatifModelImpl.UUID_COLUMN_BITMASK |
			BudgetParticipatifModelImpl.COMPANYID_COLUMN_BITMASK |
			BudgetParticipatifModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the budget participatifs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget participatifs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
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

		List<BudgetParticipatif> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetParticipatif>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetParticipatif budgetParticipatif : list) {
					if (!Objects.equals(uuid, budgetParticipatif.getUuid()) ||
							(companyId != budgetParticipatif.getCompanyId())) {
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

			query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

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
				query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
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
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
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
	 * Returns the first budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the first budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		List<BudgetParticipatif> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the last budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<BudgetParticipatif> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget participatifs before and after the current budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param budgetParticipatifId the primary key of the current budget participatif
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	@Override
	public BudgetParticipatif[] findByUuid_C_PrevAndNext(
		long budgetParticipatifId, String uuid, long companyId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = findByPrimaryKey(budgetParticipatifId);

		Session session = null;

		try {
			session = openSession();

			BudgetParticipatif[] array = new BudgetParticipatifImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, budgetParticipatif,
					uuid, companyId, orderByComparator, true);

			array[1] = budgetParticipatif;

			array[2] = getByUuid_C_PrevAndNext(session, budgetParticipatif,
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

	protected BudgetParticipatif getByUuid_C_PrevAndNext(Session session,
		BudgetParticipatif budgetParticipatif, String uuid, long companyId,
		OrderByComparator<BudgetParticipatif> orderByComparator,
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

		query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

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
			query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(budgetParticipatif);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetParticipatif> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget participatifs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (BudgetParticipatif budgetParticipatif : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(budgetParticipatif);
		}
	}

	/**
	 * Returns the number of budget participatifs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching budget participatifs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BUDGETPARTICIPATIF_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "budgetParticipatif.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "budgetParticipatif.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(budgetParticipatif.uuid IS NULL OR budgetParticipatif.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "budgetParticipatif.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			BudgetParticipatifModelImpl.GROUPID_COLUMN_BITMASK |
			BudgetParticipatifModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the budget participatifs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget participatifs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByGroupId(long groupId, int start,
		int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByGroupId(long groupId, int start,
		int end, OrderByComparator<BudgetParticipatif> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByGroupId(long groupId, int start,
		int end, OrderByComparator<BudgetParticipatif> orderByComparator,
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

		List<BudgetParticipatif> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetParticipatif>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetParticipatif budgetParticipatif : list) {
					if ((groupId != budgetParticipatif.getGroupId())) {
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

			query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
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
	 * Returns the first budget participatif in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByGroupId_First(long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByGroupId_First(groupId,
				orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the first budget participatif in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByGroupId_First(long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		List<BudgetParticipatif> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget participatif in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByGroupId_Last(long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the last budget participatif in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByGroupId_Last(long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<BudgetParticipatif> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget participatifs before and after the current budget participatif in the ordered set where groupId = &#63;.
	 *
	 * @param budgetParticipatifId the primary key of the current budget participatif
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	@Override
	public BudgetParticipatif[] findByGroupId_PrevAndNext(
		long budgetParticipatifId, long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = findByPrimaryKey(budgetParticipatifId);

		Session session = null;

		try {
			session = openSession();

			BudgetParticipatif[] array = new BudgetParticipatifImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, budgetParticipatif,
					groupId, orderByComparator, true);

			array[1] = budgetParticipatif;

			array[2] = getByGroupId_PrevAndNext(session, budgetParticipatif,
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

	protected BudgetParticipatif getByGroupId_PrevAndNext(Session session,
		BudgetParticipatif budgetParticipatif, long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator,
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

		query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

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
			query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(budgetParticipatif);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetParticipatif> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget participatifs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (BudgetParticipatif budgetParticipatif : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(budgetParticipatif);
		}
	}

	/**
	 * Returns the number of budget participatifs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching budget participatifs
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUDGETPARTICIPATIF_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "budgetParticipatif.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUSANDGROUPID =
		new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatusAndGroupId",
			new String[] {
				Integer.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUSANDGROUPID =
		new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByStatusAndGroupId",
			new String[] { Integer.class.getName(), Long.class.getName() },
			BudgetParticipatifModelImpl.STATUS_COLUMN_BITMASK |
			BudgetParticipatifModelImpl.GROUPID_COLUMN_BITMASK |
			BudgetParticipatifModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUSANDGROUPID = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByStatusAndGroupId",
			new String[] { Integer.class.getName(), Long.class.getName() });

	/**
	 * Returns all the budget participatifs where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @return the matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByStatusAndGroupId(int status,
		long groupId) {
		return findByStatusAndGroupId(status, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget participatifs where status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByStatusAndGroupId(int status,
		long groupId, int start, int end) {
		return findByStatusAndGroupId(status, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByStatusAndGroupId(int status,
		long groupId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		return findByStatusAndGroupId(status, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByStatusAndGroupId(int status,
		long groupId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUSANDGROUPID;
			finderArgs = new Object[] { status, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUSANDGROUPID;
			finderArgs = new Object[] {
					status, groupId,
					
					start, end, orderByComparator
				};
		}

		List<BudgetParticipatif> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetParticipatif>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetParticipatif budgetParticipatif : list) {
					if ((status != budgetParticipatif.getStatus()) ||
							(groupId != budgetParticipatif.getGroupId())) {
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

			query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

			query.append(_FINDER_COLUMN_STATUSANDGROUPID_STATUS_2);

			query.append(_FINDER_COLUMN_STATUSANDGROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
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
	 * Returns the first budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByStatusAndGroupId_First(int status,
		long groupId, OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByStatusAndGroupId_First(status,
				groupId, orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the first budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByStatusAndGroupId_First(int status,
		long groupId, OrderByComparator<BudgetParticipatif> orderByComparator) {
		List<BudgetParticipatif> list = findByStatusAndGroupId(status, groupId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByStatusAndGroupId_Last(int status,
		long groupId, OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByStatusAndGroupId_Last(status,
				groupId, orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the last budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByStatusAndGroupId_Last(int status,
		long groupId, OrderByComparator<BudgetParticipatif> orderByComparator) {
		int count = countByStatusAndGroupId(status, groupId);

		if (count == 0) {
			return null;
		}

		List<BudgetParticipatif> list = findByStatusAndGroupId(status, groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget participatifs before and after the current budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param budgetParticipatifId the primary key of the current budget participatif
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	@Override
	public BudgetParticipatif[] findByStatusAndGroupId_PrevAndNext(
		long budgetParticipatifId, int status, long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = findByPrimaryKey(budgetParticipatifId);

		Session session = null;

		try {
			session = openSession();

			BudgetParticipatif[] array = new BudgetParticipatifImpl[3];

			array[0] = getByStatusAndGroupId_PrevAndNext(session,
					budgetParticipatif, status, groupId, orderByComparator, true);

			array[1] = budgetParticipatif;

			array[2] = getByStatusAndGroupId_PrevAndNext(session,
					budgetParticipatif, status, groupId, orderByComparator,
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

	protected BudgetParticipatif getByStatusAndGroupId_PrevAndNext(
		Session session, BudgetParticipatif budgetParticipatif, int status,
		long groupId, OrderByComparator<BudgetParticipatif> orderByComparator,
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

		query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

		query.append(_FINDER_COLUMN_STATUSANDGROUPID_STATUS_2);

		query.append(_FINDER_COLUMN_STATUSANDGROUPID_GROUPID_2);

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
			query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(budgetParticipatif);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetParticipatif> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget participatifs where status = &#63; and groupId = &#63; from the database.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 */
	@Override
	public void removeByStatusAndGroupId(int status, long groupId) {
		for (BudgetParticipatif budgetParticipatif : findByStatusAndGroupId(
				status, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(budgetParticipatif);
		}
	}

	/**
	 * Returns the number of budget participatifs where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @return the number of matching budget participatifs
	 */
	@Override
	public int countByStatusAndGroupId(int status, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUSANDGROUPID;

		Object[] finderArgs = new Object[] { status, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BUDGETPARTICIPATIF_WHERE);

			query.append(_FINDER_COLUMN_STATUSANDGROUPID_STATUS_2);

			query.append(_FINDER_COLUMN_STATUSANDGROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_STATUSANDGROUPID_STATUS_2 = "budgetParticipatif.status = ? AND ";
	private static final String _FINDER_COLUMN_STATUSANDGROUPID_GROUPID_2 = "budgetParticipatif.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKID = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPublikId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID =
		new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikId",
			new String[] { String.class.getName() },
			BudgetParticipatifModelImpl.PUBLIKID_COLUMN_BITMASK |
			BudgetParticipatifModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKID = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the budget participatifs where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByPublikId(String publikId) {
		return findByPublikId(publikId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the budget participatifs where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByPublikId(String publikId, int start,
		int end) {
		return findByPublikId(publikId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByPublikId(String publikId, int start,
		int end, OrderByComparator<BudgetParticipatif> orderByComparator) {
		return findByPublikId(publikId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByPublikId(String publikId, int start,
		int end, OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID;
			finderArgs = new Object[] { publikId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKID;
			finderArgs = new Object[] { publikId, start, end, orderByComparator };
		}

		List<BudgetParticipatif> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetParticipatif>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetParticipatif budgetParticipatif : list) {
					if (!Objects.equals(publikId,
								budgetParticipatif.getPublikId())) {
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

			query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

			boolean bindPublikId = false;

			if (publikId == null) {
				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_1);
			}
			else if (publikId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_3);
			}
			else {
				bindPublikId = true;

				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikId) {
					qPos.add(publikId);
				}

				if (!pagination) {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
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
	 * Returns the first budget participatif in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByPublikId_First(String publikId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByPublikId_First(publikId,
				orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikId=");
		msg.append(publikId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the first budget participatif in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByPublikId_First(String publikId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		List<BudgetParticipatif> list = findByPublikId(publikId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget participatif in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByPublikId_Last(String publikId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByPublikId_Last(publikId,
				orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikId=");
		msg.append(publikId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the last budget participatif in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByPublikId_Last(String publikId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		int count = countByPublikId(publikId);

		if (count == 0) {
			return null;
		}

		List<BudgetParticipatif> list = findByPublikId(publikId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget participatifs before and after the current budget participatif in the ordered set where publikId = &#63;.
	 *
	 * @param budgetParticipatifId the primary key of the current budget participatif
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	@Override
	public BudgetParticipatif[] findByPublikId_PrevAndNext(
		long budgetParticipatifId, String publikId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = findByPrimaryKey(budgetParticipatifId);

		Session session = null;

		try {
			session = openSession();

			BudgetParticipatif[] array = new BudgetParticipatifImpl[3];

			array[0] = getByPublikId_PrevAndNext(session, budgetParticipatif,
					publikId, orderByComparator, true);

			array[1] = budgetParticipatif;

			array[2] = getByPublikId_PrevAndNext(session, budgetParticipatif,
					publikId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BudgetParticipatif getByPublikId_PrevAndNext(Session session,
		BudgetParticipatif budgetParticipatif, String publikId,
		OrderByComparator<BudgetParticipatif> orderByComparator,
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

		query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

		boolean bindPublikId = false;

		if (publikId == null) {
			query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_1);
		}
		else if (publikId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_3);
		}
		else {
			bindPublikId = true;

			query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_2);
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
			query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPublikId) {
			qPos.add(publikId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(budgetParticipatif);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetParticipatif> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget participatifs where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 */
	@Override
	public void removeByPublikId(String publikId) {
		for (BudgetParticipatif budgetParticipatif : findByPublikId(publikId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(budgetParticipatif);
		}
	}

	/**
	 * Returns the number of budget participatifs where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching budget participatifs
	 */
	@Override
	public int countByPublikId(String publikId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKID;

		Object[] finderArgs = new Object[] { publikId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUDGETPARTICIPATIF_WHERE);

			boolean bindPublikId = false;

			if (publikId == null) {
				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_1);
			}
			else if (publikId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_3);
			}
			else {
				bindPublikId = true;

				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikId) {
					qPos.add(publikId);
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

	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_1 = "budgetParticipatif.publikId IS NULL";
	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_2 = "budgetParticipatif.publikId = ?";
	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_3 = "(budgetParticipatif.publikId IS NULL OR budgetParticipatif.publikId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BUDGETPHASEID =
		new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBudgetPhaseId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPHASEID =
		new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBudgetPhaseId",
			new String[] { Long.class.getName() },
			BudgetParticipatifModelImpl.BUDGETPHASEID_COLUMN_BITMASK |
			BudgetParticipatifModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BUDGETPHASEID = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBudgetPhaseId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the budget participatifs where budgetPhaseId = &#63;.
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @return the matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByBudgetPhaseId(long budgetPhaseId) {
		return findByBudgetPhaseId(budgetPhaseId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget participatifs where budgetPhaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByBudgetPhaseId(long budgetPhaseId,
		int start, int end) {
		return findByBudgetPhaseId(budgetPhaseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where budgetPhaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByBudgetPhaseId(long budgetPhaseId,
		int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		return findByBudgetPhaseId(budgetPhaseId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where budgetPhaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByBudgetPhaseId(long budgetPhaseId,
		int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPHASEID;
			finderArgs = new Object[] { budgetPhaseId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BUDGETPHASEID;
			finderArgs = new Object[] {
					budgetPhaseId,
					
					start, end, orderByComparator
				};
		}

		List<BudgetParticipatif> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetParticipatif>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetParticipatif budgetParticipatif : list) {
					if ((budgetPhaseId != budgetParticipatif.getBudgetPhaseId())) {
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

			query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

			query.append(_FINDER_COLUMN_BUDGETPHASEID_BUDGETPHASEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(budgetPhaseId);

				if (!pagination) {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
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
	 * Returns the first budget participatif in the ordered set where budgetPhaseId = &#63;.
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByBudgetPhaseId_First(long budgetPhaseId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByBudgetPhaseId_First(budgetPhaseId,
				orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("budgetPhaseId=");
		msg.append(budgetPhaseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the first budget participatif in the ordered set where budgetPhaseId = &#63;.
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByBudgetPhaseId_First(long budgetPhaseId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		List<BudgetParticipatif> list = findByBudgetPhaseId(budgetPhaseId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget participatif in the ordered set where budgetPhaseId = &#63;.
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByBudgetPhaseId_Last(long budgetPhaseId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByBudgetPhaseId_Last(budgetPhaseId,
				orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("budgetPhaseId=");
		msg.append(budgetPhaseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the last budget participatif in the ordered set where budgetPhaseId = &#63;.
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByBudgetPhaseId_Last(long budgetPhaseId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		int count = countByBudgetPhaseId(budgetPhaseId);

		if (count == 0) {
			return null;
		}

		List<BudgetParticipatif> list = findByBudgetPhaseId(budgetPhaseId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget participatifs before and after the current budget participatif in the ordered set where budgetPhaseId = &#63;.
	 *
	 * @param budgetParticipatifId the primary key of the current budget participatif
	 * @param budgetPhaseId the budget phase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	@Override
	public BudgetParticipatif[] findByBudgetPhaseId_PrevAndNext(
		long budgetParticipatifId, long budgetPhaseId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = findByPrimaryKey(budgetParticipatifId);

		Session session = null;

		try {
			session = openSession();

			BudgetParticipatif[] array = new BudgetParticipatifImpl[3];

			array[0] = getByBudgetPhaseId_PrevAndNext(session,
					budgetParticipatif, budgetPhaseId, orderByComparator, true);

			array[1] = budgetParticipatif;

			array[2] = getByBudgetPhaseId_PrevAndNext(session,
					budgetParticipatif, budgetPhaseId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BudgetParticipatif getByBudgetPhaseId_PrevAndNext(
		Session session, BudgetParticipatif budgetParticipatif,
		long budgetPhaseId,
		OrderByComparator<BudgetParticipatif> orderByComparator,
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

		query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

		query.append(_FINDER_COLUMN_BUDGETPHASEID_BUDGETPHASEID_2);

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
			query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(budgetPhaseId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(budgetParticipatif);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetParticipatif> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget participatifs where budgetPhaseId = &#63; from the database.
	 *
	 * @param budgetPhaseId the budget phase ID
	 */
	@Override
	public void removeByBudgetPhaseId(long budgetPhaseId) {
		for (BudgetParticipatif budgetParticipatif : findByBudgetPhaseId(
				budgetPhaseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(budgetParticipatif);
		}
	}

	/**
	 * Returns the number of budget participatifs where budgetPhaseId = &#63;.
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @return the number of matching budget participatifs
	 */
	@Override
	public int countByBudgetPhaseId(long budgetPhaseId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BUDGETPHASEID;

		Object[] finderArgs = new Object[] { budgetPhaseId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUDGETPARTICIPATIF_WHERE);

			query.append(_FINDER_COLUMN_BUDGETPHASEID_BUDGETPHASEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(budgetPhaseId);

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

	private static final String _FINDER_COLUMN_BUDGETPHASEID_BUDGETPHASEID_2 = "budgetParticipatif.budgetPhaseId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ISCRUSHANDPUBLISHED =
		new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByisCrushAndPublished",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ISCRUSHANDPUBLISHED =
		new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED,
			BudgetParticipatifImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByisCrushAndPublished",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Long.class.getName()
			},
			BudgetParticipatifModelImpl.ISCRUSH_COLUMN_BITMASK |
			BudgetParticipatifModelImpl.STATUS_COLUMN_BITMASK |
			BudgetParticipatifModelImpl.GROUPID_COLUMN_BITMASK |
			BudgetParticipatifModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ISCRUSHANDPUBLISHED = new FinderPath(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByisCrushAndPublished",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @return the matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByisCrushAndPublished(boolean isCrush,
		int status, long groupId) {
		return findByisCrushAndPublished(isCrush, status, groupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByisCrushAndPublished(boolean isCrush,
		int status, long groupId, int start, int end) {
		return findByisCrushAndPublished(isCrush, status, groupId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByisCrushAndPublished(boolean isCrush,
		int status, long groupId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		return findByisCrushAndPublished(isCrush, status, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findByisCrushAndPublished(boolean isCrush,
		int status, long groupId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ISCRUSHANDPUBLISHED;
			finderArgs = new Object[] { isCrush, status, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ISCRUSHANDPUBLISHED;
			finderArgs = new Object[] {
					isCrush, status, groupId,
					
					start, end, orderByComparator
				};
		}

		List<BudgetParticipatif> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetParticipatif>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetParticipatif budgetParticipatif : list) {
					if ((isCrush != budgetParticipatif.getIsCrush()) ||
							(status != budgetParticipatif.getStatus()) ||
							(groupId != budgetParticipatif.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

			query.append(_FINDER_COLUMN_ISCRUSHANDPUBLISHED_ISCRUSH_2);

			query.append(_FINDER_COLUMN_ISCRUSHANDPUBLISHED_STATUS_2);

			query.append(_FINDER_COLUMN_ISCRUSHANDPUBLISHED_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(isCrush);

				qPos.add(status);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
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
	 * Returns the first budget participatif in the ordered set where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByisCrushAndPublished_First(boolean isCrush,
		int status, long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByisCrushAndPublished_First(isCrush,
				status, groupId, orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("isCrush=");
		msg.append(isCrush);

		msg.append(", status=");
		msg.append(status);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the first budget participatif in the ordered set where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByisCrushAndPublished_First(
		boolean isCrush, int status, long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		List<BudgetParticipatif> list = findByisCrushAndPublished(isCrush,
				status, groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget participatif in the ordered set where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif findByisCrushAndPublished_Last(boolean isCrush,
		int status, long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByisCrushAndPublished_Last(isCrush,
				status, groupId, orderByComparator);

		if (budgetParticipatif != null) {
			return budgetParticipatif;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("isCrush=");
		msg.append(isCrush);

		msg.append(", status=");
		msg.append(status);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetParticipatifException(msg.toString());
	}

	/**
	 * Returns the last budget participatif in the ordered set where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	@Override
	public BudgetParticipatif fetchByisCrushAndPublished_Last(boolean isCrush,
		int status, long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		int count = countByisCrushAndPublished(isCrush, status, groupId);

		if (count == 0) {
			return null;
		}

		List<BudgetParticipatif> list = findByisCrushAndPublished(isCrush,
				status, groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget participatifs before and after the current budget participatif in the ordered set where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * @param budgetParticipatifId the primary key of the current budget participatif
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	@Override
	public BudgetParticipatif[] findByisCrushAndPublished_PrevAndNext(
		long budgetParticipatifId, boolean isCrush, int status, long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = findByPrimaryKey(budgetParticipatifId);

		Session session = null;

		try {
			session = openSession();

			BudgetParticipatif[] array = new BudgetParticipatifImpl[3];

			array[0] = getByisCrushAndPublished_PrevAndNext(session,
					budgetParticipatif, isCrush, status, groupId,
					orderByComparator, true);

			array[1] = budgetParticipatif;

			array[2] = getByisCrushAndPublished_PrevAndNext(session,
					budgetParticipatif, isCrush, status, groupId,
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

	protected BudgetParticipatif getByisCrushAndPublished_PrevAndNext(
		Session session, BudgetParticipatif budgetParticipatif,
		boolean isCrush, int status, long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE);

		query.append(_FINDER_COLUMN_ISCRUSHANDPUBLISHED_ISCRUSH_2);

		query.append(_FINDER_COLUMN_ISCRUSHANDPUBLISHED_STATUS_2);

		query.append(_FINDER_COLUMN_ISCRUSHANDPUBLISHED_GROUPID_2);

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
			query.append(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(isCrush);

		qPos.add(status);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(budgetParticipatif);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetParticipatif> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63; from the database.
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 */
	@Override
	public void removeByisCrushAndPublished(boolean isCrush, int status,
		long groupId) {
		for (BudgetParticipatif budgetParticipatif : findByisCrushAndPublished(
				isCrush, status, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(budgetParticipatif);
		}
	}

	/**
	 * Returns the number of budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @return the number of matching budget participatifs
	 */
	@Override
	public int countByisCrushAndPublished(boolean isCrush, int status,
		long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ISCRUSHANDPUBLISHED;

		Object[] finderArgs = new Object[] { isCrush, status, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_BUDGETPARTICIPATIF_WHERE);

			query.append(_FINDER_COLUMN_ISCRUSHANDPUBLISHED_ISCRUSH_2);

			query.append(_FINDER_COLUMN_ISCRUSHANDPUBLISHED_STATUS_2);

			query.append(_FINDER_COLUMN_ISCRUSHANDPUBLISHED_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(isCrush);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_ISCRUSHANDPUBLISHED_ISCRUSH_2 = "budgetParticipatif.isCrush = ? AND ";
	private static final String _FINDER_COLUMN_ISCRUSHANDPUBLISHED_STATUS_2 = "budgetParticipatif.status = ? AND ";
	private static final String _FINDER_COLUMN_ISCRUSHANDPUBLISHED_GROUPID_2 = "budgetParticipatif.groupId = ?";

	public BudgetParticipatifPersistenceImpl() {
		setModelClass(BudgetParticipatif.class);

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
	 * Caches the budget participatif in the entity cache if it is enabled.
	 *
	 * @param budgetParticipatif the budget participatif
	 */
	@Override
	public void cacheResult(BudgetParticipatif budgetParticipatif) {
		entityCache.putResult(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifImpl.class, budgetParticipatif.getPrimaryKey(),
			budgetParticipatif);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				budgetParticipatif.getUuid(), budgetParticipatif.getGroupId()
			}, budgetParticipatif);

		budgetParticipatif.resetOriginalValues();
	}

	/**
	 * Caches the budget participatifs in the entity cache if it is enabled.
	 *
	 * @param budgetParticipatifs the budget participatifs
	 */
	@Override
	public void cacheResult(List<BudgetParticipatif> budgetParticipatifs) {
		for (BudgetParticipatif budgetParticipatif : budgetParticipatifs) {
			if (entityCache.getResult(
						BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
						BudgetParticipatifImpl.class,
						budgetParticipatif.getPrimaryKey()) == null) {
				cacheResult(budgetParticipatif);
			}
			else {
				budgetParticipatif.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all budget participatifs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BudgetParticipatifImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the budget participatif.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BudgetParticipatif budgetParticipatif) {
		entityCache.removeResult(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifImpl.class, budgetParticipatif.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((BudgetParticipatifModelImpl)budgetParticipatif,
			true);
	}

	@Override
	public void clearCache(List<BudgetParticipatif> budgetParticipatifs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BudgetParticipatif budgetParticipatif : budgetParticipatifs) {
			entityCache.removeResult(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
				BudgetParticipatifImpl.class, budgetParticipatif.getPrimaryKey());

			clearUniqueFindersCache((BudgetParticipatifModelImpl)budgetParticipatif,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		BudgetParticipatifModelImpl budgetParticipatifModelImpl) {
		Object[] args = new Object[] {
				budgetParticipatifModelImpl.getUuid(),
				budgetParticipatifModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			budgetParticipatifModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		BudgetParticipatifModelImpl budgetParticipatifModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					budgetParticipatifModelImpl.getUuid(),
					budgetParticipatifModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((budgetParticipatifModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					budgetParticipatifModelImpl.getOriginalUuid(),
					budgetParticipatifModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new budget participatif with the primary key. Does not add the budget participatif to the database.
	 *
	 * @param budgetParticipatifId the primary key for the new budget participatif
	 * @return the new budget participatif
	 */
	@Override
	public BudgetParticipatif create(long budgetParticipatifId) {
		BudgetParticipatif budgetParticipatif = new BudgetParticipatifImpl();

		budgetParticipatif.setNew(true);
		budgetParticipatif.setPrimaryKey(budgetParticipatifId);

		String uuid = PortalUUIDUtil.generate();

		budgetParticipatif.setUuid(uuid);

		budgetParticipatif.setCompanyId(companyProvider.getCompanyId());

		return budgetParticipatif;
	}

	/**
	 * Removes the budget participatif with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param budgetParticipatifId the primary key of the budget participatif
	 * @return the budget participatif that was removed
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	@Override
	public BudgetParticipatif remove(long budgetParticipatifId)
		throws NoSuchBudgetParticipatifException {
		return remove((Serializable)budgetParticipatifId);
	}

	/**
	 * Removes the budget participatif with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the budget participatif
	 * @return the budget participatif that was removed
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	@Override
	public BudgetParticipatif remove(Serializable primaryKey)
		throws NoSuchBudgetParticipatifException {
		Session session = null;

		try {
			session = openSession();

			BudgetParticipatif budgetParticipatif = (BudgetParticipatif)session.get(BudgetParticipatifImpl.class,
					primaryKey);

			if (budgetParticipatif == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBudgetParticipatifException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(budgetParticipatif);
		}
		catch (NoSuchBudgetParticipatifException nsee) {
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
	protected BudgetParticipatif removeImpl(
		BudgetParticipatif budgetParticipatif) {
		budgetParticipatif = toUnwrappedModel(budgetParticipatif);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(budgetParticipatif)) {
				budgetParticipatif = (BudgetParticipatif)session.get(BudgetParticipatifImpl.class,
						budgetParticipatif.getPrimaryKeyObj());
			}

			if (budgetParticipatif != null) {
				session.delete(budgetParticipatif);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (budgetParticipatif != null) {
			clearCache(budgetParticipatif);
		}

		return budgetParticipatif;
	}

	@Override
	public BudgetParticipatif updateImpl(BudgetParticipatif budgetParticipatif) {
		budgetParticipatif = toUnwrappedModel(budgetParticipatif);

		boolean isNew = budgetParticipatif.isNew();

		BudgetParticipatifModelImpl budgetParticipatifModelImpl = (BudgetParticipatifModelImpl)budgetParticipatif;

		if (Validator.isNull(budgetParticipatif.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			budgetParticipatif.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (budgetParticipatif.getCreateDate() == null)) {
			if (serviceContext == null) {
				budgetParticipatif.setCreateDate(now);
			}
			else {
				budgetParticipatif.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!budgetParticipatifModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				budgetParticipatif.setModifiedDate(now);
			}
			else {
				budgetParticipatif.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (budgetParticipatif.isNew()) {
				session.save(budgetParticipatif);

				budgetParticipatif.setNew(false);
			}
			else {
				budgetParticipatif = (BudgetParticipatif)session.merge(budgetParticipatif);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!BudgetParticipatifModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { budgetParticipatifModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					budgetParticipatifModelImpl.getUuid(),
					budgetParticipatifModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { budgetParticipatifModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					budgetParticipatifModelImpl.getStatus(),
					budgetParticipatifModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUSANDGROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUSANDGROUPID,
				args);

			args = new Object[] { budgetParticipatifModelImpl.getPublikId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID,
				args);

			args = new Object[] { budgetParticipatifModelImpl.getBudgetPhaseId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_BUDGETPHASEID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPHASEID,
				args);

			args = new Object[] {
					budgetParticipatifModelImpl.getIsCrush(),
					budgetParticipatifModelImpl.getStatus(),
					budgetParticipatifModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ISCRUSHANDPUBLISHED,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ISCRUSHANDPUBLISHED,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((budgetParticipatifModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetParticipatifModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { budgetParticipatifModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((budgetParticipatifModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetParticipatifModelImpl.getOriginalUuid(),
						budgetParticipatifModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						budgetParticipatifModelImpl.getUuid(),
						budgetParticipatifModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((budgetParticipatifModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetParticipatifModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { budgetParticipatifModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((budgetParticipatifModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUSANDGROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetParticipatifModelImpl.getOriginalStatus(),
						budgetParticipatifModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUSANDGROUPID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUSANDGROUPID,
					args);

				args = new Object[] {
						budgetParticipatifModelImpl.getStatus(),
						budgetParticipatifModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUSANDGROUPID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUSANDGROUPID,
					args);
			}

			if ((budgetParticipatifModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetParticipatifModelImpl.getOriginalPublikId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID,
					args);

				args = new Object[] { budgetParticipatifModelImpl.getPublikId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID,
					args);
			}

			if ((budgetParticipatifModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPHASEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetParticipatifModelImpl.getOriginalBudgetPhaseId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUDGETPHASEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPHASEID,
					args);

				args = new Object[] {
						budgetParticipatifModelImpl.getBudgetPhaseId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUDGETPHASEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPHASEID,
					args);
			}

			if ((budgetParticipatifModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ISCRUSHANDPUBLISHED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetParticipatifModelImpl.getOriginalIsCrush(),
						budgetParticipatifModelImpl.getOriginalStatus(),
						budgetParticipatifModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ISCRUSHANDPUBLISHED,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ISCRUSHANDPUBLISHED,
					args);

				args = new Object[] {
						budgetParticipatifModelImpl.getIsCrush(),
						budgetParticipatifModelImpl.getStatus(),
						budgetParticipatifModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ISCRUSHANDPUBLISHED,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ISCRUSHANDPUBLISHED,
					args);
			}
		}

		entityCache.putResult(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
			BudgetParticipatifImpl.class, budgetParticipatif.getPrimaryKey(),
			budgetParticipatif, false);

		clearUniqueFindersCache(budgetParticipatifModelImpl, false);
		cacheUniqueFindersCache(budgetParticipatifModelImpl);

		budgetParticipatif.resetOriginalValues();

		return budgetParticipatif;
	}

	protected BudgetParticipatif toUnwrappedModel(
		BudgetParticipatif budgetParticipatif) {
		if (budgetParticipatif instanceof BudgetParticipatifImpl) {
			return budgetParticipatif;
		}

		BudgetParticipatifImpl budgetParticipatifImpl = new BudgetParticipatifImpl();

		budgetParticipatifImpl.setNew(budgetParticipatif.isNew());
		budgetParticipatifImpl.setPrimaryKey(budgetParticipatif.getPrimaryKey());

		budgetParticipatifImpl.setUuid(budgetParticipatif.getUuid());
		budgetParticipatifImpl.setBudgetParticipatifId(budgetParticipatif.getBudgetParticipatifId());
		budgetParticipatifImpl.setGroupId(budgetParticipatif.getGroupId());
		budgetParticipatifImpl.setCompanyId(budgetParticipatif.getCompanyId());
		budgetParticipatifImpl.setUserId(budgetParticipatif.getUserId());
		budgetParticipatifImpl.setUserName(budgetParticipatif.getUserName());
		budgetParticipatifImpl.setCreateDate(budgetParticipatif.getCreateDate());
		budgetParticipatifImpl.setModifiedDate(budgetParticipatif.getModifiedDate());
		budgetParticipatifImpl.setStatus(budgetParticipatif.getStatus());
		budgetParticipatifImpl.setStatusByUserId(budgetParticipatif.getStatusByUserId());
		budgetParticipatifImpl.setStatusByUserName(budgetParticipatif.getStatusByUserName());
		budgetParticipatifImpl.setStatusDate(budgetParticipatif.getStatusDate());
		budgetParticipatifImpl.setTitle(budgetParticipatif.getTitle());
		budgetParticipatifImpl.setDescription(budgetParticipatif.getDescription());
		budgetParticipatifImpl.setSummary(budgetParticipatif.getSummary());
		budgetParticipatifImpl.setBudget(budgetParticipatif.getBudget());
		budgetParticipatifImpl.setMotif(budgetParticipatif.getMotif());
		budgetParticipatifImpl.setPlaceTextArea(budgetParticipatif.getPlaceTextArea());
		budgetParticipatifImpl.setInTheNameOf(budgetParticipatif.getInTheNameOf());
		budgetParticipatifImpl.setCitoyenLastname(budgetParticipatif.getCitoyenLastname());
		budgetParticipatifImpl.setCitoyenFirstname(budgetParticipatif.getCitoyenFirstname());
		budgetParticipatifImpl.setCitoyenAdresse(budgetParticipatif.getCitoyenAdresse());
		budgetParticipatifImpl.setCitoyenPostalCode(budgetParticipatif.getCitoyenPostalCode());
		budgetParticipatifImpl.setCitoyenCity(budgetParticipatif.getCitoyenCity());
		budgetParticipatifImpl.setCitoyenPhone(budgetParticipatif.getCitoyenPhone());
		budgetParticipatifImpl.setCitoyenMobile(budgetParticipatif.getCitoyenMobile());
		budgetParticipatifImpl.setCitoyenEmail(budgetParticipatif.getCitoyenEmail());
		budgetParticipatifImpl.setCitoyenBirthday(budgetParticipatif.getCitoyenBirthday());
		budgetParticipatifImpl.setHasCopyright(budgetParticipatif.isHasCopyright());
		budgetParticipatifImpl.setVideoUrl(budgetParticipatif.getVideoUrl());
		budgetParticipatifImpl.setIsCrush(budgetParticipatif.isIsCrush());
		budgetParticipatifImpl.setCrushComment(budgetParticipatif.getCrushComment());
		budgetParticipatifImpl.setPublikId(budgetParticipatif.getPublikId());
		budgetParticipatifImpl.setImageId(budgetParticipatif.getImageId());
		budgetParticipatifImpl.setFilesIds(budgetParticipatif.getFilesIds());
		budgetParticipatifImpl.setBudgetPhaseId(budgetParticipatif.getBudgetPhaseId());

		return budgetParticipatifImpl;
	}

	/**
	 * Returns the budget participatif with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the budget participatif
	 * @return the budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	@Override
	public BudgetParticipatif findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBudgetParticipatifException {
		BudgetParticipatif budgetParticipatif = fetchByPrimaryKey(primaryKey);

		if (budgetParticipatif == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBudgetParticipatifException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return budgetParticipatif;
	}

	/**
	 * Returns the budget participatif with the primary key or throws a {@link NoSuchBudgetParticipatifException} if it could not be found.
	 *
	 * @param budgetParticipatifId the primary key of the budget participatif
	 * @return the budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	@Override
	public BudgetParticipatif findByPrimaryKey(long budgetParticipatifId)
		throws NoSuchBudgetParticipatifException {
		return findByPrimaryKey((Serializable)budgetParticipatifId);
	}

	/**
	 * Returns the budget participatif with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the budget participatif
	 * @return the budget participatif, or <code>null</code> if a budget participatif with the primary key could not be found
	 */
	@Override
	public BudgetParticipatif fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
				BudgetParticipatifImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		BudgetParticipatif budgetParticipatif = (BudgetParticipatif)serializable;

		if (budgetParticipatif == null) {
			Session session = null;

			try {
				session = openSession();

				budgetParticipatif = (BudgetParticipatif)session.get(BudgetParticipatifImpl.class,
						primaryKey);

				if (budgetParticipatif != null) {
					cacheResult(budgetParticipatif);
				}
				else {
					entityCache.putResult(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
						BudgetParticipatifImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
					BudgetParticipatifImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return budgetParticipatif;
	}

	/**
	 * Returns the budget participatif with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param budgetParticipatifId the primary key of the budget participatif
	 * @return the budget participatif, or <code>null</code> if a budget participatif with the primary key could not be found
	 */
	@Override
	public BudgetParticipatif fetchByPrimaryKey(long budgetParticipatifId) {
		return fetchByPrimaryKey((Serializable)budgetParticipatifId);
	}

	@Override
	public Map<Serializable, BudgetParticipatif> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, BudgetParticipatif> map = new HashMap<Serializable, BudgetParticipatif>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			BudgetParticipatif budgetParticipatif = fetchByPrimaryKey(primaryKey);

			if (budgetParticipatif != null) {
				map.put(primaryKey, budgetParticipatif);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
					BudgetParticipatifImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (BudgetParticipatif)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_BUDGETPARTICIPATIF_WHERE_PKS_IN);

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

			for (BudgetParticipatif budgetParticipatif : (List<BudgetParticipatif>)q.list()) {
				map.put(budgetParticipatif.getPrimaryKeyObj(),
					budgetParticipatif);

				cacheResult(budgetParticipatif);

				uncachedPrimaryKeys.remove(budgetParticipatif.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(BudgetParticipatifModelImpl.ENTITY_CACHE_ENABLED,
					BudgetParticipatifImpl.class, primaryKey, nullModel);
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
	 * Returns all the budget participatifs.
	 *
	 * @return the budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget participatifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget participatifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findAll(int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget participatifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of budget participatifs
	 */
	@Override
	public List<BudgetParticipatif> findAll(int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
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

		List<BudgetParticipatif> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetParticipatif>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_BUDGETPARTICIPATIF);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BUDGETPARTICIPATIF;

				if (pagination) {
					sql = sql.concat(BudgetParticipatifModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetParticipatif>)QueryUtil.list(q,
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
	 * Removes all the budget participatifs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BudgetParticipatif budgetParticipatif : findAll()) {
			remove(budgetParticipatif);
		}
	}

	/**
	 * Returns the number of budget participatifs.
	 *
	 * @return the number of budget participatifs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BUDGETPARTICIPATIF);

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
		return BudgetParticipatifModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the budget participatif persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(BudgetParticipatifImpl.class.getName());
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
	private static final String _SQL_SELECT_BUDGETPARTICIPATIF = "SELECT budgetParticipatif FROM BudgetParticipatif budgetParticipatif";
	private static final String _SQL_SELECT_BUDGETPARTICIPATIF_WHERE_PKS_IN = "SELECT budgetParticipatif FROM BudgetParticipatif budgetParticipatif WHERE budgetParticipatifId IN (";
	private static final String _SQL_SELECT_BUDGETPARTICIPATIF_WHERE = "SELECT budgetParticipatif FROM BudgetParticipatif budgetParticipatif WHERE ";
	private static final String _SQL_COUNT_BUDGETPARTICIPATIF = "SELECT COUNT(budgetParticipatif) FROM BudgetParticipatif budgetParticipatif";
	private static final String _SQL_COUNT_BUDGETPARTICIPATIF_WHERE = "SELECT COUNT(budgetParticipatif) FROM BudgetParticipatif budgetParticipatif WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "budgetParticipatif.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BudgetParticipatif exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BudgetParticipatif exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(BudgetParticipatifPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}