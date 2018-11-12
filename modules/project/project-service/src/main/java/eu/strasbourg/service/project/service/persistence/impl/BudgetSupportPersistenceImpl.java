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

import eu.strasbourg.service.project.exception.NoSuchBudgetSupportException;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.model.impl.BudgetSupportImpl;
import eu.strasbourg.service.project.model.impl.BudgetSupportModelImpl;
import eu.strasbourg.service.project.service.persistence.BudgetSupportPersistence;

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
 * The persistence implementation for the budget support service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see BudgetSupportPersistence
 * @see eu.strasbourg.service.project.service.persistence.BudgetSupportUtil
 * @generated
 */
@ProviderType
public class BudgetSupportPersistenceImpl extends BasePersistenceImpl<BudgetSupport>
	implements BudgetSupportPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BudgetSupportUtil} to access the budget support persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BudgetSupportImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			BudgetSupportModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the budget supports where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget supports where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget supports where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByUuid(String uuid, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget supports where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByUuid(String uuid, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
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

		List<BudgetSupport> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetSupport>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetSupport budgetSupport : list) {
					if (!Objects.equals(uuid, budgetSupport.getUuid())) {
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

			query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

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
				query.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
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
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first budget support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByUuid_First(String uuid,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByUuid_First(uuid, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetSupportException(msg.toString());
	}

	/**
	 * Returns the first budget support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByUuid_First(String uuid,
		OrderByComparator<BudgetSupport> orderByComparator) {
		List<BudgetSupport> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByUuid_Last(String uuid,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByUuid_Last(uuid, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetSupportException(msg.toString());
	}

	/**
	 * Returns the last budget support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByUuid_Last(String uuid,
		OrderByComparator<BudgetSupport> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<BudgetSupport> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget supports before and after the current budget support in the ordered set where uuid = &#63;.
	 *
	 * @param budgetSupportId the primary key of the current budget support
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	@Override
	public BudgetSupport[] findByUuid_PrevAndNext(long budgetSupportId,
		String uuid, OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = findByPrimaryKey(budgetSupportId);

		Session session = null;

		try {
			session = openSession();

			BudgetSupport[] array = new BudgetSupportImpl[3];

			array[0] = getByUuid_PrevAndNext(session, budgetSupport, uuid,
					orderByComparator, true);

			array[1] = budgetSupport;

			array[2] = getByUuid_PrevAndNext(session, budgetSupport, uuid,
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

	protected BudgetSupport getByUuid_PrevAndNext(Session session,
		BudgetSupport budgetSupport, String uuid,
		OrderByComparator<BudgetSupport> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

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
			query.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(budgetSupport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetSupport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget supports where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (BudgetSupport budgetSupport : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(budgetSupport);
		}
	}

	/**
	 * Returns the number of budget supports where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching budget supports
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "budgetSupport.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "budgetSupport.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(budgetSupport.uuid IS NULL OR budgetSupport.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			BudgetSupportModelImpl.UUID_COLUMN_BITMASK |
			BudgetSupportModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the budget support where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBudgetSupportException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByUUID_G(String uuid, long groupId)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByUUID_G(uuid, groupId);

		if (budgetSupport == null) {
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

			throw new NoSuchBudgetSupportException(msg.toString());
		}

		return budgetSupport;
	}

	/**
	 * Returns the budget support where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the budget support where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof BudgetSupport) {
			BudgetSupport budgetSupport = (BudgetSupport)result;

			if (!Objects.equals(uuid, budgetSupport.getUuid()) ||
					(groupId != budgetSupport.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

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

				List<BudgetSupport> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					BudgetSupport budgetSupport = list.get(0);

					result = budgetSupport;

					cacheResult(budgetSupport);

					if ((budgetSupport.getUuid() == null) ||
							!budgetSupport.getUuid().equals(uuid) ||
							(budgetSupport.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, budgetSupport);
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
			return (BudgetSupport)result;
		}
	}

	/**
	 * Removes the budget support where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the budget support that was removed
	 */
	@Override
	public BudgetSupport removeByUUID_G(String uuid, long groupId)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = findByUUID_G(uuid, groupId);

		return remove(budgetSupport);
	}

	/**
	 * Returns the number of budget supports where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching budget supports
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "budgetSupport.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "budgetSupport.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(budgetSupport.uuid IS NULL OR budgetSupport.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "budgetSupport.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			BudgetSupportModelImpl.UUID_COLUMN_BITMASK |
			BudgetSupportModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<BudgetSupport> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<BudgetSupport> orderByComparator,
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

		List<BudgetSupport> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetSupport>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetSupport budgetSupport : list) {
					if (!Objects.equals(uuid, budgetSupport.getUuid()) ||
							(companyId != budgetSupport.getCompanyId())) {
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

			query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

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
				query.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
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
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetSupportException(msg.toString());
	}

	/**
	 * Returns the first budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		List<BudgetSupport> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetSupportException(msg.toString());
	}

	/**
	 * Returns the last budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<BudgetSupport> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget supports before and after the current budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param budgetSupportId the primary key of the current budget support
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	@Override
	public BudgetSupport[] findByUuid_C_PrevAndNext(long budgetSupportId,
		String uuid, long companyId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = findByPrimaryKey(budgetSupportId);

		Session session = null;

		try {
			session = openSession();

			BudgetSupport[] array = new BudgetSupportImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, budgetSupport, uuid,
					companyId, orderByComparator, true);

			array[1] = budgetSupport;

			array[2] = getByUuid_C_PrevAndNext(session, budgetSupport, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BudgetSupport getByUuid_C_PrevAndNext(Session session,
		BudgetSupport budgetSupport, String uuid, long companyId,
		OrderByComparator<BudgetSupport> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

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
			query.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(budgetSupport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetSupport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget supports where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (BudgetSupport budgetSupport : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(budgetSupport);
		}
	}

	/**
	 * Returns the number of budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching budget supports
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "budgetSupport.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "budgetSupport.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(budgetSupport.uuid IS NULL OR budgetSupport.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "budgetSupport.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupId", new String[] { Long.class.getName() },
			BudgetSupportModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the budget supports where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget supports where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget supports where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByGroupId(long groupId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget supports where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByGroupId(long groupId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
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

		List<BudgetSupport> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetSupport>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetSupport budgetSupport : list) {
					if ((groupId != budgetSupport.getGroupId())) {
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

			query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first budget support in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByGroupId_First(long groupId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByGroupId_First(groupId,
				orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetSupportException(msg.toString());
	}

	/**
	 * Returns the first budget support in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByGroupId_First(long groupId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		List<BudgetSupport> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget support in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByGroupId_Last(long groupId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetSupportException(msg.toString());
	}

	/**
	 * Returns the last budget support in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByGroupId_Last(long groupId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<BudgetSupport> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget supports before and after the current budget support in the ordered set where groupId = &#63;.
	 *
	 * @param budgetSupportId the primary key of the current budget support
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	@Override
	public BudgetSupport[] findByGroupId_PrevAndNext(long budgetSupportId,
		long groupId, OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = findByPrimaryKey(budgetSupportId);

		Session session = null;

		try {
			session = openSession();

			BudgetSupport[] array = new BudgetSupportImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, budgetSupport,
					groupId, orderByComparator, true);

			array[1] = budgetSupport;

			array[2] = getByGroupId_PrevAndNext(session, budgetSupport,
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

	protected BudgetSupport getByGroupId_PrevAndNext(Session session,
		BudgetSupport budgetSupport, long groupId,
		OrderByComparator<BudgetSupport> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

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
			query.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(budgetSupport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetSupport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget supports where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (BudgetSupport budgetSupport : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(budgetSupport);
		}
	}

	/**
	 * Returns the number of budget supports where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching budget supports
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "budgetSupport.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BUDGETPARTICIPATIF =
		new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByBudgetParticipatif",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIF =
		new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByBudgetParticipatif", new String[] { Long.class.getName() },
			BudgetSupportModelImpl.BUDGETPARTICIPATIFID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIF = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByBudgetParticipatif", new String[] { Long.class.getName() });

	/**
	 * Returns all the budget supports where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @return the matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId) {
		return findByBudgetParticipatif(budgetParticipatifId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget supports where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId, int start, int end) {
		return findByBudgetParticipatif(budgetParticipatifId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget supports where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return findByBudgetParticipatif(budgetParticipatifId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget supports where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIF;
			finderArgs = new Object[] { budgetParticipatifId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BUDGETPARTICIPATIF;
			finderArgs = new Object[] {
					budgetParticipatifId,
					
					start, end, orderByComparator
				};
		}

		List<BudgetSupport> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetSupport>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetSupport budgetSupport : list) {
					if ((budgetParticipatifId != budgetSupport.getBudgetParticipatifId())) {
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

			query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_BUDGETPARTICIPATIF_BUDGETPARTICIPATIFID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(budgetParticipatifId);

				if (!pagination) {
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first budget support in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByBudgetParticipatif_First(
		long budgetParticipatifId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByBudgetParticipatif_First(budgetParticipatifId,
				orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("budgetParticipatifId=");
		msg.append(budgetParticipatifId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetSupportException(msg.toString());
	}

	/**
	 * Returns the first budget support in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByBudgetParticipatif_First(
		long budgetParticipatifId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		List<BudgetSupport> list = findByBudgetParticipatif(budgetParticipatifId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget support in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByBudgetParticipatif_Last(
		long budgetParticipatifId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByBudgetParticipatif_Last(budgetParticipatifId,
				orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("budgetParticipatifId=");
		msg.append(budgetParticipatifId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetSupportException(msg.toString());
	}

	/**
	 * Returns the last budget support in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByBudgetParticipatif_Last(
		long budgetParticipatifId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		int count = countByBudgetParticipatif(budgetParticipatifId);

		if (count == 0) {
			return null;
		}

		List<BudgetSupport> list = findByBudgetParticipatif(budgetParticipatifId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget supports before and after the current budget support in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetSupportId the primary key of the current budget support
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	@Override
	public BudgetSupport[] findByBudgetParticipatif_PrevAndNext(
		long budgetSupportId, long budgetParticipatifId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = findByPrimaryKey(budgetSupportId);

		Session session = null;

		try {
			session = openSession();

			BudgetSupport[] array = new BudgetSupportImpl[3];

			array[0] = getByBudgetParticipatif_PrevAndNext(session,
					budgetSupport, budgetParticipatifId, orderByComparator, true);

			array[1] = budgetSupport;

			array[2] = getByBudgetParticipatif_PrevAndNext(session,
					budgetSupport, budgetParticipatifId, orderByComparator,
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

	protected BudgetSupport getByBudgetParticipatif_PrevAndNext(
		Session session, BudgetSupport budgetSupport,
		long budgetParticipatifId,
		OrderByComparator<BudgetSupport> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

		query.append(_FINDER_COLUMN_BUDGETPARTICIPATIF_BUDGETPARTICIPATIFID_2);

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
			query.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(budgetParticipatifId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(budgetSupport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetSupport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget supports where budgetParticipatifId = &#63; from the database.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 */
	@Override
	public void removeByBudgetParticipatif(long budgetParticipatifId) {
		for (BudgetSupport budgetSupport : findByBudgetParticipatif(
				budgetParticipatifId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(budgetSupport);
		}
	}

	/**
	 * Returns the number of budget supports where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @return the number of matching budget supports
	 */
	@Override
	public int countByBudgetParticipatif(long budgetParticipatifId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIF;

		Object[] finderArgs = new Object[] { budgetParticipatifId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_BUDGETPARTICIPATIF_BUDGETPARTICIPATIFID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(budgetParticipatifId);

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

	private static final String _FINDER_COLUMN_BUDGETPARTICIPATIF_BUDGETPARTICIPATIFID_2 =
		"budgetSupport.budgetParticipatifId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPublikUserId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPublikUserId", new String[] { String.class.getName() },
			BudgetSupportModelImpl.PUBLIKUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKUSERID = new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikUserId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the budget supports where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByPublikUserId(String publikUserId) {
		return findByPublikUserId(publikUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget supports where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByPublikUserId(String publikUserId,
		int start, int end) {
		return findByPublikUserId(publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget supports where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByPublikUserId(String publikUserId,
		int start, int end, OrderByComparator<BudgetSupport> orderByComparator) {
		return findByPublikUserId(publikUserId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the budget supports where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByPublikUserId(String publikUserId,
		int start, int end, OrderByComparator<BudgetSupport> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID;
			finderArgs = new Object[] { publikUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKUSERID;
			finderArgs = new Object[] {
					publikUserId,
					
					start, end, orderByComparator
				};
		}

		List<BudgetSupport> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetSupport>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetSupport budgetSupport : list) {
					if (!Objects.equals(publikUserId,
								budgetSupport.getPublikUserId())) {
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

			query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
				}

				if (!pagination) {
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first budget support in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByPublikUserId_First(String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByPublikUserId_First(publikUserId,
				orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetSupportException(msg.toString());
	}

	/**
	 * Returns the first budget support in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByPublikUserId_First(String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		List<BudgetSupport> list = findByPublikUserId(publikUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget support in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByPublikUserId_Last(String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByPublikUserId_Last(publikUserId,
				orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetSupportException(msg.toString());
	}

	/**
	 * Returns the last budget support in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByPublikUserId_Last(String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		int count = countByPublikUserId(publikUserId);

		if (count == 0) {
			return null;
		}

		List<BudgetSupport> list = findByPublikUserId(publikUserId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget supports before and after the current budget support in the ordered set where publikUserId = &#63;.
	 *
	 * @param budgetSupportId the primary key of the current budget support
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	@Override
	public BudgetSupport[] findByPublikUserId_PrevAndNext(
		long budgetSupportId, String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = findByPrimaryKey(budgetSupportId);

		Session session = null;

		try {
			session = openSession();

			BudgetSupport[] array = new BudgetSupportImpl[3];

			array[0] = getByPublikUserId_PrevAndNext(session, budgetSupport,
					publikUserId, orderByComparator, true);

			array[1] = budgetSupport;

			array[2] = getByPublikUserId_PrevAndNext(session, budgetSupport,
					publikUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BudgetSupport getByPublikUserId_PrevAndNext(Session session,
		BudgetSupport budgetSupport, String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

		boolean bindPublikUserId = false;

		if (publikUserId == null) {
			query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1);
		}
		else if (publikUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
		}
		else {
			bindPublikUserId = true;

			query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
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
			query.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPublikUserId) {
			qPos.add(publikUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(budgetSupport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetSupport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget supports where publikUserId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 */
	@Override
	public void removeByPublikUserId(String publikUserId) {
		for (BudgetSupport budgetSupport : findByPublikUserId(publikUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(budgetSupport);
		}
	}

	/**
	 * Returns the number of budget supports where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the number of matching budget supports
	 */
	@Override
	public int countByPublikUserId(String publikUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKUSERID;

		Object[] finderArgs = new Object[] { publikUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
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

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1 = "budgetSupport.publikUserId IS NULL";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2 = "budgetSupport.publikUserId = ?";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3 = "(budgetSupport.publikUserId IS NULL OR budgetSupport.publikUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BUDGETPARTICIPATIFIDANDPUBLIKUSERID =
		new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByBudgetParticipatifIdAndPublikUserId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIFIDANDPUBLIKUSERID =
		new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByBudgetParticipatifIdAndPublikUserId",
			new String[] { Long.class.getName(), String.class.getName() },
			BudgetSupportModelImpl.BUDGETPARTICIPATIFID_COLUMN_BITMASK |
			BudgetSupportModelImpl.PUBLIKUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIFIDANDPUBLIKUSERID =
		new FinderPath(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByBudgetParticipatifIdAndPublikUserId",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @return the matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, String publikUserId) {
		return findByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId,
			publikUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, String publikUserId, int start, int end) {
		return findByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId,
			publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, String publikUserId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return findByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId,
			publikUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, String publikUserId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIFIDANDPUBLIKUSERID;
			finderArgs = new Object[] { budgetParticipatifId, publikUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BUDGETPARTICIPATIFIDANDPUBLIKUSERID;
			finderArgs = new Object[] {
					budgetParticipatifId, publikUserId,
					
					start, end, orderByComparator
				};
		}

		List<BudgetSupport> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetSupport>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetSupport budgetSupport : list) {
					if ((budgetParticipatifId != budgetSupport.getBudgetParticipatifId()) ||
							!Objects.equals(publikUserId,
								budgetSupport.getPublikUserId())) {
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

			query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_BUDGETPARTICIPATIFID_2);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(budgetParticipatifId);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
				}

				if (!pagination) {
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByBudgetParticipatifIdAndPublikUserId_First(
		long budgetParticipatifId, String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByBudgetParticipatifIdAndPublikUserId_First(budgetParticipatifId,
				publikUserId, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("budgetParticipatifId=");
		msg.append(budgetParticipatifId);

		msg.append(", publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetSupportException(msg.toString());
	}

	/**
	 * Returns the first budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByBudgetParticipatifIdAndPublikUserId_First(
		long budgetParticipatifId, String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		List<BudgetSupport> list = findByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId,
				publikUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByBudgetParticipatifIdAndPublikUserId_Last(
		long budgetParticipatifId, String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByBudgetParticipatifIdAndPublikUserId_Last(budgetParticipatifId,
				publikUserId, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("budgetParticipatifId=");
		msg.append(budgetParticipatifId);

		msg.append(", publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetSupportException(msg.toString());
	}

	/**
	 * Returns the last budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByBudgetParticipatifIdAndPublikUserId_Last(
		long budgetParticipatifId, String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		int count = countByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId,
				publikUserId);

		if (count == 0) {
			return null;
		}

		List<BudgetSupport> list = findByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId,
				publikUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget supports before and after the current budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetSupportId the primary key of the current budget support
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	@Override
	public BudgetSupport[] findByBudgetParticipatifIdAndPublikUserId_PrevAndNext(
		long budgetSupportId, long budgetParticipatifId, String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = findByPrimaryKey(budgetSupportId);

		Session session = null;

		try {
			session = openSession();

			BudgetSupport[] array = new BudgetSupportImpl[3];

			array[0] = getByBudgetParticipatifIdAndPublikUserId_PrevAndNext(session,
					budgetSupport, budgetParticipatifId, publikUserId,
					orderByComparator, true);

			array[1] = budgetSupport;

			array[2] = getByBudgetParticipatifIdAndPublikUserId_PrevAndNext(session,
					budgetSupport, budgetParticipatifId, publikUserId,
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

	protected BudgetSupport getByBudgetParticipatifIdAndPublikUserId_PrevAndNext(
		Session session, BudgetSupport budgetSupport,
		long budgetParticipatifId, String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

		query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_BUDGETPARTICIPATIFID_2);

		boolean bindPublikUserId = false;

		if (publikUserId == null) {
			query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_1);
		}
		else if (publikUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_3);
		}
		else {
			bindPublikUserId = true;

			query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_2);
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
			query.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(budgetParticipatifId);

		if (bindPublikUserId) {
			qPos.add(publikUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(budgetSupport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetSupport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63; from the database.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 */
	@Override
	public void removeByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, String publikUserId) {
		for (BudgetSupport budgetSupport : findByBudgetParticipatifIdAndPublikUserId(
				budgetParticipatifId, publikUserId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(budgetSupport);
		}
	}

	/**
	 * Returns the number of budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @return the number of matching budget supports
	 */
	@Override
	public int countByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, String publikUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIFIDANDPUBLIKUSERID;

		Object[] finderArgs = new Object[] { budgetParticipatifId, publikUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_BUDGETPARTICIPATIFID_2);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(budgetParticipatifId);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
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

	private static final String _FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_BUDGETPARTICIPATIFID_2 =
		"budgetSupport.budgetParticipatifId = ? AND ";
	private static final String _FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_1 =
		"budgetSupport.publikUserId IS NULL";
	private static final String _FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_2 =
		"budgetSupport.publikUserId = ?";
	private static final String _FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_3 =
		"(budgetSupport.publikUserId IS NULL OR budgetSupport.publikUserId = '')";

	public BudgetSupportPersistenceImpl() {
		setModelClass(BudgetSupport.class);

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
	 * Caches the budget support in the entity cache if it is enabled.
	 *
	 * @param budgetSupport the budget support
	 */
	@Override
	public void cacheResult(BudgetSupport budgetSupport) {
		entityCache.putResult(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportImpl.class, budgetSupport.getPrimaryKey(),
			budgetSupport);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { budgetSupport.getUuid(), budgetSupport.getGroupId() },
			budgetSupport);

		budgetSupport.resetOriginalValues();
	}

	/**
	 * Caches the budget supports in the entity cache if it is enabled.
	 *
	 * @param budgetSupports the budget supports
	 */
	@Override
	public void cacheResult(List<BudgetSupport> budgetSupports) {
		for (BudgetSupport budgetSupport : budgetSupports) {
			if (entityCache.getResult(
						BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
						BudgetSupportImpl.class, budgetSupport.getPrimaryKey()) == null) {
				cacheResult(budgetSupport);
			}
			else {
				budgetSupport.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all budget supports.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BudgetSupportImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the budget support.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BudgetSupport budgetSupport) {
		entityCache.removeResult(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportImpl.class, budgetSupport.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((BudgetSupportModelImpl)budgetSupport, true);
	}

	@Override
	public void clearCache(List<BudgetSupport> budgetSupports) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BudgetSupport budgetSupport : budgetSupports) {
			entityCache.removeResult(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
				BudgetSupportImpl.class, budgetSupport.getPrimaryKey());

			clearUniqueFindersCache((BudgetSupportModelImpl)budgetSupport, true);
		}
	}

	protected void cacheUniqueFindersCache(
		BudgetSupportModelImpl budgetSupportModelImpl) {
		Object[] args = new Object[] {
				budgetSupportModelImpl.getUuid(),
				budgetSupportModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			budgetSupportModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		BudgetSupportModelImpl budgetSupportModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					budgetSupportModelImpl.getUuid(),
					budgetSupportModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((budgetSupportModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					budgetSupportModelImpl.getOriginalUuid(),
					budgetSupportModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new budget support with the primary key. Does not add the budget support to the database.
	 *
	 * @param budgetSupportId the primary key for the new budget support
	 * @return the new budget support
	 */
	@Override
	public BudgetSupport create(long budgetSupportId) {
		BudgetSupport budgetSupport = new BudgetSupportImpl();

		budgetSupport.setNew(true);
		budgetSupport.setPrimaryKey(budgetSupportId);

		String uuid = PortalUUIDUtil.generate();

		budgetSupport.setUuid(uuid);

		budgetSupport.setCompanyId(companyProvider.getCompanyId());

		return budgetSupport;
	}

	/**
	 * Removes the budget support with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param budgetSupportId the primary key of the budget support
	 * @return the budget support that was removed
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	@Override
	public BudgetSupport remove(long budgetSupportId)
		throws NoSuchBudgetSupportException {
		return remove((Serializable)budgetSupportId);
	}

	/**
	 * Removes the budget support with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the budget support
	 * @return the budget support that was removed
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	@Override
	public BudgetSupport remove(Serializable primaryKey)
		throws NoSuchBudgetSupportException {
		Session session = null;

		try {
			session = openSession();

			BudgetSupport budgetSupport = (BudgetSupport)session.get(BudgetSupportImpl.class,
					primaryKey);

			if (budgetSupport == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBudgetSupportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(budgetSupport);
		}
		catch (NoSuchBudgetSupportException nsee) {
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
	protected BudgetSupport removeImpl(BudgetSupport budgetSupport) {
		budgetSupport = toUnwrappedModel(budgetSupport);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(budgetSupport)) {
				budgetSupport = (BudgetSupport)session.get(BudgetSupportImpl.class,
						budgetSupport.getPrimaryKeyObj());
			}

			if (budgetSupport != null) {
				session.delete(budgetSupport);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (budgetSupport != null) {
			clearCache(budgetSupport);
		}

		return budgetSupport;
	}

	@Override
	public BudgetSupport updateImpl(BudgetSupport budgetSupport) {
		budgetSupport = toUnwrappedModel(budgetSupport);

		boolean isNew = budgetSupport.isNew();

		BudgetSupportModelImpl budgetSupportModelImpl = (BudgetSupportModelImpl)budgetSupport;

		if (Validator.isNull(budgetSupport.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			budgetSupport.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (budgetSupport.getCreateDate() == null)) {
			if (serviceContext == null) {
				budgetSupport.setCreateDate(now);
			}
			else {
				budgetSupport.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!budgetSupportModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				budgetSupport.setModifiedDate(now);
			}
			else {
				budgetSupport.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (budgetSupport.isNew()) {
				session.save(budgetSupport);

				budgetSupport.setNew(false);
			}
			else {
				budgetSupport = (BudgetSupport)session.merge(budgetSupport);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!BudgetSupportModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { budgetSupportModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					budgetSupportModelImpl.getUuid(),
					budgetSupportModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { budgetSupportModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] { budgetSupportModelImpl.getBudgetParticipatifId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIF,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIF,
				args);

			args = new Object[] { budgetSupportModelImpl.getPublikUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
				args);

			args = new Object[] {
					budgetSupportModelImpl.getBudgetParticipatifId(),
					budgetSupportModelImpl.getPublikUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIFIDANDPUBLIKUSERID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIFIDANDPUBLIKUSERID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((budgetSupportModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetSupportModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { budgetSupportModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((budgetSupportModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetSupportModelImpl.getOriginalUuid(),
						budgetSupportModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						budgetSupportModelImpl.getUuid(),
						budgetSupportModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((budgetSupportModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetSupportModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { budgetSupportModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((budgetSupportModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIF.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetSupportModelImpl.getOriginalBudgetParticipatifId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIF,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIF,
					args);

				args = new Object[] {
						budgetSupportModelImpl.getBudgetParticipatifId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIF,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIF,
					args);
			}

			if ((budgetSupportModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetSupportModelImpl.getOriginalPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);

				args = new Object[] { budgetSupportModelImpl.getPublikUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);
			}

			if ((budgetSupportModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIFIDANDPUBLIKUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetSupportModelImpl.getOriginalBudgetParticipatifId(),
						budgetSupportModelImpl.getOriginalPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIFIDANDPUBLIKUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIFIDANDPUBLIKUSERID,
					args);

				args = new Object[] {
						budgetSupportModelImpl.getBudgetParticipatifId(),
						budgetSupportModelImpl.getPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIFIDANDPUBLIKUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIFIDANDPUBLIKUSERID,
					args);
			}
		}

		entityCache.putResult(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportImpl.class, budgetSupport.getPrimaryKey(),
			budgetSupport, false);

		clearUniqueFindersCache(budgetSupportModelImpl, false);
		cacheUniqueFindersCache(budgetSupportModelImpl);

		budgetSupport.resetOriginalValues();

		return budgetSupport;
	}

	protected BudgetSupport toUnwrappedModel(BudgetSupport budgetSupport) {
		if (budgetSupport instanceof BudgetSupportImpl) {
			return budgetSupport;
		}

		BudgetSupportImpl budgetSupportImpl = new BudgetSupportImpl();

		budgetSupportImpl.setNew(budgetSupport.isNew());
		budgetSupportImpl.setPrimaryKey(budgetSupport.getPrimaryKey());

		budgetSupportImpl.setUuid(budgetSupport.getUuid());
		budgetSupportImpl.setBudgetSupportId(budgetSupport.getBudgetSupportId());
		budgetSupportImpl.setGroupId(budgetSupport.getGroupId());
		budgetSupportImpl.setCompanyId(budgetSupport.getCompanyId());
		budgetSupportImpl.setCreateDate(budgetSupport.getCreateDate());
		budgetSupportImpl.setModifiedDate(budgetSupport.getModifiedDate());
		budgetSupportImpl.setStatus(budgetSupport.getStatus());
		budgetSupportImpl.setStatusByUserId(budgetSupport.getStatusByUserId());
		budgetSupportImpl.setStatusByUserName(budgetSupport.getStatusByUserName());
		budgetSupportImpl.setStatusDate(budgetSupport.getStatusDate());
		budgetSupportImpl.setCitoyenLastName(budgetSupport.getCitoyenLastName());
		budgetSupportImpl.setCitoyenFirstname(budgetSupport.getCitoyenFirstname());
		budgetSupportImpl.setCitoyenBirthday(budgetSupport.getCitoyenBirthday());
		budgetSupportImpl.setCitoyenAddress(budgetSupport.getCitoyenAddress());
		budgetSupportImpl.setCitoyenMail(budgetSupport.getCitoyenMail());
		budgetSupportImpl.setCitoyenPostalCode(budgetSupport.getCitoyenPostalCode());
		budgetSupportImpl.setCitoyenMobilePhone(budgetSupport.getCitoyenMobilePhone());
		budgetSupportImpl.setCitoyenPhone(budgetSupport.getCitoyenPhone());
		budgetSupportImpl.setCitoyenCity(budgetSupport.getCitoyenCity());
		budgetSupportImpl.setPublikUserId(budgetSupport.getPublikUserId());
		budgetSupportImpl.setBudgetParticipatifId(budgetSupport.getBudgetParticipatifId());

		return budgetSupportImpl;
	}

	/**
	 * Returns the budget support with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the budget support
	 * @return the budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	@Override
	public BudgetSupport findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBudgetSupportException {
		BudgetSupport budgetSupport = fetchByPrimaryKey(primaryKey);

		if (budgetSupport == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBudgetSupportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return budgetSupport;
	}

	/**
	 * Returns the budget support with the primary key or throws a {@link NoSuchBudgetSupportException} if it could not be found.
	 *
	 * @param budgetSupportId the primary key of the budget support
	 * @return the budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	@Override
	public BudgetSupport findByPrimaryKey(long budgetSupportId)
		throws NoSuchBudgetSupportException {
		return findByPrimaryKey((Serializable)budgetSupportId);
	}

	/**
	 * Returns the budget support with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the budget support
	 * @return the budget support, or <code>null</code> if a budget support with the primary key could not be found
	 */
	@Override
	public BudgetSupport fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
				BudgetSupportImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		BudgetSupport budgetSupport = (BudgetSupport)serializable;

		if (budgetSupport == null) {
			Session session = null;

			try {
				session = openSession();

				budgetSupport = (BudgetSupport)session.get(BudgetSupportImpl.class,
						primaryKey);

				if (budgetSupport != null) {
					cacheResult(budgetSupport);
				}
				else {
					entityCache.putResult(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
						BudgetSupportImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
					BudgetSupportImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return budgetSupport;
	}

	/**
	 * Returns the budget support with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param budgetSupportId the primary key of the budget support
	 * @return the budget support, or <code>null</code> if a budget support with the primary key could not be found
	 */
	@Override
	public BudgetSupport fetchByPrimaryKey(long budgetSupportId) {
		return fetchByPrimaryKey((Serializable)budgetSupportId);
	}

	@Override
	public Map<Serializable, BudgetSupport> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, BudgetSupport> map = new HashMap<Serializable, BudgetSupport>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			BudgetSupport budgetSupport = fetchByPrimaryKey(primaryKey);

			if (budgetSupport != null) {
				map.put(primaryKey, budgetSupport);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
					BudgetSupportImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (BudgetSupport)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_BUDGETSUPPORT_WHERE_PKS_IN);

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

			for (BudgetSupport budgetSupport : (List<BudgetSupport>)q.list()) {
				map.put(budgetSupport.getPrimaryKeyObj(), budgetSupport);

				cacheResult(budgetSupport);

				uncachedPrimaryKeys.remove(budgetSupport.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
					BudgetSupportImpl.class, primaryKey, nullModel);
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
	 * Returns all the budget supports.
	 *
	 * @return the budget supports
	 */
	@Override
	public List<BudgetSupport> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget supports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of budget supports
	 */
	@Override
	public List<BudgetSupport> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget supports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of budget supports
	 */
	@Override
	public List<BudgetSupport> findAll(int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget supports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of budget supports
	 */
	@Override
	public List<BudgetSupport> findAll(int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
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

		List<BudgetSupport> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetSupport>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_BUDGETSUPPORT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BUDGETSUPPORT;

				if (pagination) {
					sql = sql.concat(BudgetSupportModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetSupport>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the budget supports from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BudgetSupport budgetSupport : findAll()) {
			remove(budgetSupport);
		}
	}

	/**
	 * Returns the number of budget supports.
	 *
	 * @return the number of budget supports
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BUDGETSUPPORT);

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
		return BudgetSupportModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the budget support persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(BudgetSupportImpl.class.getName());
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
	private static final String _SQL_SELECT_BUDGETSUPPORT = "SELECT budgetSupport FROM BudgetSupport budgetSupport";
	private static final String _SQL_SELECT_BUDGETSUPPORT_WHERE_PKS_IN = "SELECT budgetSupport FROM BudgetSupport budgetSupport WHERE budgetSupportId IN (";
	private static final String _SQL_SELECT_BUDGETSUPPORT_WHERE = "SELECT budgetSupport FROM BudgetSupport budgetSupport WHERE ";
	private static final String _SQL_COUNT_BUDGETSUPPORT = "SELECT COUNT(budgetSupport) FROM BudgetSupport budgetSupport";
	private static final String _SQL_COUNT_BUDGETSUPPORT_WHERE = "SELECT COUNT(budgetSupport) FROM BudgetSupport budgetSupport WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "budgetSupport.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BudgetSupport exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BudgetSupport exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(BudgetSupportPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}