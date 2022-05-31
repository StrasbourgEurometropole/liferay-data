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

import eu.strasbourg.service.project.exception.NoSuchBudgetSupportException;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.model.impl.BudgetSupportImpl;
import eu.strasbourg.service.project.model.impl.BudgetSupportModelImpl;
import eu.strasbourg.service.project.service.persistence.BudgetSupportPersistence;

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
 * The persistence implementation for the budget support service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @generated
 */
public class BudgetSupportPersistenceImpl
	extends BasePersistenceImpl<BudgetSupport>
	implements BudgetSupportPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>BudgetSupportUtil</code> to access the budget support persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		BudgetSupportImpl.class.getName();

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget supports where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
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

		List<BudgetSupport> list = null;

		if (useFinderCache) {
			list = (List<BudgetSupport>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetSupport budgetSupport : list) {
					if (!uuid.equals(budgetSupport.getUuid())) {
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

			sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

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
				sb.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
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

				list = (List<BudgetSupport>)QueryUtil.list(
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
	 * Returns the first budget support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByUuid_First(
			String uuid, OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {

		BudgetSupport budgetSupport = fetchByUuid_First(
			uuid, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchBudgetSupportException(sb.toString());
	}

	/**
	 * Returns the first budget support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByUuid_First(
		String uuid, OrderByComparator<BudgetSupport> orderByComparator) {

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
	public BudgetSupport findByUuid_Last(
			String uuid, OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {

		BudgetSupport budgetSupport = fetchByUuid_Last(uuid, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchBudgetSupportException(sb.toString());
	}

	/**
	 * Returns the last budget support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByUuid_Last(
		String uuid, OrderByComparator<BudgetSupport> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<BudgetSupport> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

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
	public BudgetSupport[] findByUuid_PrevAndNext(
			long budgetSupportId, String uuid,
			OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {

		uuid = Objects.toString(uuid, "");

		BudgetSupport budgetSupport = findByPrimaryKey(budgetSupportId);

		Session session = null;

		try {
			session = openSession();

			BudgetSupport[] array = new BudgetSupportImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, budgetSupport, uuid, orderByComparator, true);

			array[1] = budgetSupport;

			array[2] = getByUuid_PrevAndNext(
				session, budgetSupport, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected BudgetSupport getByUuid_PrevAndNext(
		Session session, BudgetSupport budgetSupport, String uuid,
		OrderByComparator<BudgetSupport> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

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
			sb.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
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
						budgetSupport)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BudgetSupport> list = query.list();

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
		for (BudgetSupport budgetSupport :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

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
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

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
		"budgetSupport.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(budgetSupport.uuid IS NULL OR budgetSupport.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the budget support where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchBudgetSupportException</code> if it could not be found.
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

			throw new NoSuchBudgetSupportException(sb.toString());
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
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByUUID_G(
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

		if (result instanceof BudgetSupport) {
			BudgetSupport budgetSupport = (BudgetSupport)result;

			if (!Objects.equals(uuid, budgetSupport.getUuid()) ||
				(groupId != budgetSupport.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

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

				List<BudgetSupport> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					BudgetSupport budgetSupport = list.get(0);

					result = budgetSupport;

					cacheResult(budgetSupport);
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
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

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
		"budgetSupport.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(budgetSupport.uuid IS NULL OR budgetSupport.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"budgetSupport.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
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
	public List<BudgetSupport> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
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

		List<BudgetSupport> list = null;

		if (useFinderCache) {
			list = (List<BudgetSupport>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetSupport budgetSupport : list) {
					if (!uuid.equals(budgetSupport.getUuid()) ||
						(companyId != budgetSupport.getCompanyId())) {

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

			sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

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
				sb.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
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

				list = (List<BudgetSupport>)QueryUtil.list(
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
	 * Returns the first budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {

		BudgetSupport budgetSupport = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchBudgetSupportException(sb.toString());
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
	public BudgetSupport fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<BudgetSupport> orderByComparator) {

		List<BudgetSupport> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

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
	public BudgetSupport findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {

		BudgetSupport budgetSupport = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchBudgetSupportException(sb.toString());
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
	public BudgetSupport fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<BudgetSupport> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<BudgetSupport> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

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
	public BudgetSupport[] findByUuid_C_PrevAndNext(
			long budgetSupportId, String uuid, long companyId,
			OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {

		uuid = Objects.toString(uuid, "");

		BudgetSupport budgetSupport = findByPrimaryKey(budgetSupportId);

		Session session = null;

		try {
			session = openSession();

			BudgetSupport[] array = new BudgetSupportImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, budgetSupport, uuid, companyId, orderByComparator,
				true);

			array[1] = budgetSupport;

			array[2] = getByUuid_C_PrevAndNext(
				session, budgetSupport, uuid, companyId, orderByComparator,
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

	protected BudgetSupport getByUuid_C_PrevAndNext(
		Session session, BudgetSupport budgetSupport, String uuid,
		long companyId, OrderByComparator<BudgetSupport> orderByComparator,
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

		sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

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
			sb.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
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
						budgetSupport)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BudgetSupport> list = query.list();

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
		for (BudgetSupport budgetSupport :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

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
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

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
		"budgetSupport.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(budgetSupport.uuid IS NULL OR budgetSupport.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"budgetSupport.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the budget supports where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget supports where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget supports where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<BudgetSupport> list = null;

		if (useFinderCache) {
			list = (List<BudgetSupport>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetSupport budgetSupport : list) {
					if (groupId != budgetSupport.getGroupId()) {
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

			sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<BudgetSupport>)QueryUtil.list(
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
	 * Returns the first budget support in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByGroupId_First(
			long groupId, OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {

		BudgetSupport budgetSupport = fetchByGroupId_First(
			groupId, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchBudgetSupportException(sb.toString());
	}

	/**
	 * Returns the first budget support in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByGroupId_First(
		long groupId, OrderByComparator<BudgetSupport> orderByComparator) {

		List<BudgetSupport> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

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
	public BudgetSupport findByGroupId_Last(
			long groupId, OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {

		BudgetSupport budgetSupport = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchBudgetSupportException(sb.toString());
	}

	/**
	 * Returns the last budget support in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByGroupId_Last(
		long groupId, OrderByComparator<BudgetSupport> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<BudgetSupport> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

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
	public BudgetSupport[] findByGroupId_PrevAndNext(
			long budgetSupportId, long groupId,
			OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {

		BudgetSupport budgetSupport = findByPrimaryKey(budgetSupportId);

		Session session = null;

		try {
			session = openSession();

			BudgetSupport[] array = new BudgetSupportImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, budgetSupport, groupId, orderByComparator, true);

			array[1] = budgetSupport;

			array[2] = getByGroupId_PrevAndNext(
				session, budgetSupport, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected BudgetSupport getByGroupId_PrevAndNext(
		Session session, BudgetSupport budgetSupport, long groupId,
		OrderByComparator<BudgetSupport> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			sb.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						budgetSupport)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BudgetSupport> list = query.list();

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
		for (BudgetSupport budgetSupport :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

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
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"budgetSupport.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByBudgetParticipatif;
	private FinderPath _finderPathWithoutPaginationFindByBudgetParticipatif;
	private FinderPath _finderPathCountByBudgetParticipatif;

	/**
	 * Returns all the budget supports where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @return the matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId) {

		return findByBudgetParticipatif(
			budgetParticipatifId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget supports where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
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

		return findByBudgetParticipatif(
			budgetParticipatifId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget supports where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByBudgetParticipatif;
				finderArgs = new Object[] {budgetParticipatifId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByBudgetParticipatif;
			finderArgs = new Object[] {
				budgetParticipatifId, start, end, orderByComparator
			};
		}

		List<BudgetSupport> list = null;

		if (useFinderCache) {
			list = (List<BudgetSupport>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetSupport budgetSupport : list) {
					if (budgetParticipatifId !=
							budgetSupport.getBudgetParticipatifId()) {

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

			sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

			sb.append(_FINDER_COLUMN_BUDGETPARTICIPATIF_BUDGETPARTICIPATIFID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(budgetParticipatifId);

				list = (List<BudgetSupport>)QueryUtil.list(
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

		BudgetSupport budgetSupport = fetchByBudgetParticipatif_First(
			budgetParticipatifId, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("budgetParticipatifId=");
		sb.append(budgetParticipatifId);

		sb.append("}");

		throw new NoSuchBudgetSupportException(sb.toString());
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

		List<BudgetSupport> list = findByBudgetParticipatif(
			budgetParticipatifId, 0, 1, orderByComparator);

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

		BudgetSupport budgetSupport = fetchByBudgetParticipatif_Last(
			budgetParticipatifId, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("budgetParticipatifId=");
		sb.append(budgetParticipatifId);

		sb.append("}");

		throw new NoSuchBudgetSupportException(sb.toString());
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

		List<BudgetSupport> list = findByBudgetParticipatif(
			budgetParticipatifId, count - 1, count, orderByComparator);

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

			array[0] = getByBudgetParticipatif_PrevAndNext(
				session, budgetSupport, budgetParticipatifId, orderByComparator,
				true);

			array[1] = budgetSupport;

			array[2] = getByBudgetParticipatif_PrevAndNext(
				session, budgetSupport, budgetParticipatifId, orderByComparator,
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

	protected BudgetSupport getByBudgetParticipatif_PrevAndNext(
		Session session, BudgetSupport budgetSupport, long budgetParticipatifId,
		OrderByComparator<BudgetSupport> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

		sb.append(_FINDER_COLUMN_BUDGETPARTICIPATIF_BUDGETPARTICIPATIFID_2);

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
			sb.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(budgetParticipatifId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						budgetSupport)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BudgetSupport> list = query.list();

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
		for (BudgetSupport budgetSupport :
				findByBudgetParticipatif(
					budgetParticipatifId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

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
		FinderPath finderPath = _finderPathCountByBudgetParticipatif;

		Object[] finderArgs = new Object[] {budgetParticipatifId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

			sb.append(_FINDER_COLUMN_BUDGETPARTICIPATIF_BUDGETPARTICIPATIFID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(budgetParticipatifId);

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

	private static final String
		_FINDER_COLUMN_BUDGETPARTICIPATIF_BUDGETPARTICIPATIFID_2 =
			"budgetSupport.budgetParticipatifId = ?";

	private FinderPath _finderPathWithPaginationFindByPublikUserId;
	private FinderPath _finderPathWithoutPaginationFindByPublikUserId;
	private FinderPath _finderPathCountByPublikUserId;

	/**
	 * Returns all the budget supports where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByPublikUserId(String publikUserId) {
		return findByPublikUserId(
			publikUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget supports where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByPublikUserId(
		String publikUserId, int start, int end) {

		return findByPublikUserId(publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget supports where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByPublikUserId(
		String publikUserId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {

		return findByPublikUserId(
			publikUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget supports where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByPublikUserId(
		String publikUserId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
		boolean useFinderCache) {

		publikUserId = Objects.toString(publikUserId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPublikUserId;
				finderArgs = new Object[] {publikUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPublikUserId;
			finderArgs = new Object[] {
				publikUserId, start, end, orderByComparator
			};
		}

		List<BudgetSupport> list = null;

		if (useFinderCache) {
			list = (List<BudgetSupport>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetSupport budgetSupport : list) {
					if (!publikUserId.equals(budgetSupport.getPublikUserId())) {
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

			sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublikUserId) {
					queryPos.add(publikUserId);
				}

				list = (List<BudgetSupport>)QueryUtil.list(
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
	 * Returns the first budget support in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport findByPublikUserId_First(
			String publikUserId,
			OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {

		BudgetSupport budgetSupport = fetchByPublikUserId_First(
			publikUserId, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publikUserId=");
		sb.append(publikUserId);

		sb.append("}");

		throw new NoSuchBudgetSupportException(sb.toString());
	}

	/**
	 * Returns the first budget support in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByPublikUserId_First(
		String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator) {

		List<BudgetSupport> list = findByPublikUserId(
			publikUserId, 0, 1, orderByComparator);

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
	public BudgetSupport findByPublikUserId_Last(
			String publikUserId,
			OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {

		BudgetSupport budgetSupport = fetchByPublikUserId_Last(
			publikUserId, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publikUserId=");
		sb.append(publikUserId);

		sb.append("}");

		throw new NoSuchBudgetSupportException(sb.toString());
	}

	/**
	 * Returns the last budget support in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	@Override
	public BudgetSupport fetchByPublikUserId_Last(
		String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator) {

		int count = countByPublikUserId(publikUserId);

		if (count == 0) {
			return null;
		}

		List<BudgetSupport> list = findByPublikUserId(
			publikUserId, count - 1, count, orderByComparator);

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

		publikUserId = Objects.toString(publikUserId, "");

		BudgetSupport budgetSupport = findByPrimaryKey(budgetSupportId);

		Session session = null;

		try {
			session = openSession();

			BudgetSupport[] array = new BudgetSupportImpl[3];

			array[0] = getByPublikUserId_PrevAndNext(
				session, budgetSupport, publikUserId, orderByComparator, true);

			array[1] = budgetSupport;

			array[2] = getByPublikUserId_PrevAndNext(
				session, budgetSupport, publikUserId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected BudgetSupport getByPublikUserId_PrevAndNext(
		Session session, BudgetSupport budgetSupport, String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

		boolean bindPublikUserId = false;

		if (publikUserId.isEmpty()) {
			sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
		}
		else {
			bindPublikUserId = true;

			sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
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
			sb.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindPublikUserId) {
			queryPos.add(publikUserId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						budgetSupport)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BudgetSupport> list = query.list();

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
		for (BudgetSupport budgetSupport :
				findByPublikUserId(
					publikUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

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
		publikUserId = Objects.toString(publikUserId, "");

		FinderPath finderPath = _finderPathCountByPublikUserId;

		Object[] finderArgs = new Object[] {publikUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublikUserId) {
					queryPos.add(publikUserId);
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

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2 =
		"budgetSupport.publikUserId = ?";

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3 =
		"(budgetSupport.publikUserId IS NULL OR budgetSupport.publikUserId = '')";

	private FinderPath
		_finderPathWithPaginationFindByBudgetParticipatifIdAndPublikUserId;
	private FinderPath
		_finderPathWithoutPaginationFindByBudgetParticipatifIdAndPublikUserId;
	private FinderPath _finderPathCountByBudgetParticipatifIdAndPublikUserId;

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

		return findByBudgetParticipatifIdAndPublikUserId(
			budgetParticipatifId, publikUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
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

		return findByBudgetParticipatifIdAndPublikUserId(
			budgetParticipatifId, publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
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

		return findByBudgetParticipatifIdAndPublikUserId(
			budgetParticipatifId, publikUserId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget supports
	 */
	@Override
	public List<BudgetSupport> findByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, String publikUserId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
		boolean useFinderCache) {

		publikUserId = Objects.toString(publikUserId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByBudgetParticipatifIdAndPublikUserId;
				finderArgs = new Object[] {budgetParticipatifId, publikUserId};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByBudgetParticipatifIdAndPublikUserId;
			finderArgs = new Object[] {
				budgetParticipatifId, publikUserId, start, end,
				orderByComparator
			};
		}

		List<BudgetSupport> list = null;

		if (useFinderCache) {
			list = (List<BudgetSupport>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetSupport budgetSupport : list) {
					if ((budgetParticipatifId !=
							budgetSupport.getBudgetParticipatifId()) ||
						!publikUserId.equals(budgetSupport.getPublikUserId())) {

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

			sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

			sb.append(
				_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_BUDGETPARTICIPATIFID_2);

			boolean bindPublikUserId = false;

			if (publikUserId.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				sb.append(
					_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(budgetParticipatifId);

				if (bindPublikUserId) {
					queryPos.add(publikUserId);
				}

				list = (List<BudgetSupport>)QueryUtil.list(
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

		BudgetSupport budgetSupport =
			fetchByBudgetParticipatifIdAndPublikUserId_First(
				budgetParticipatifId, publikUserId, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("budgetParticipatifId=");
		sb.append(budgetParticipatifId);

		sb.append(", publikUserId=");
		sb.append(publikUserId);

		sb.append("}");

		throw new NoSuchBudgetSupportException(sb.toString());
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

		List<BudgetSupport> list = findByBudgetParticipatifIdAndPublikUserId(
			budgetParticipatifId, publikUserId, 0, 1, orderByComparator);

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

		BudgetSupport budgetSupport =
			fetchByBudgetParticipatifIdAndPublikUserId_Last(
				budgetParticipatifId, publikUserId, orderByComparator);

		if (budgetSupport != null) {
			return budgetSupport;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("budgetParticipatifId=");
		sb.append(budgetParticipatifId);

		sb.append(", publikUserId=");
		sb.append(publikUserId);

		sb.append("}");

		throw new NoSuchBudgetSupportException(sb.toString());
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

		int count = countByBudgetParticipatifIdAndPublikUserId(
			budgetParticipatifId, publikUserId);

		if (count == 0) {
			return null;
		}

		List<BudgetSupport> list = findByBudgetParticipatifIdAndPublikUserId(
			budgetParticipatifId, publikUserId, count - 1, count,
			orderByComparator);

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
	public BudgetSupport[]
			findByBudgetParticipatifIdAndPublikUserId_PrevAndNext(
				long budgetSupportId, long budgetParticipatifId,
				String publikUserId,
				OrderByComparator<BudgetSupport> orderByComparator)
		throws NoSuchBudgetSupportException {

		publikUserId = Objects.toString(publikUserId, "");

		BudgetSupport budgetSupport = findByPrimaryKey(budgetSupportId);

		Session session = null;

		try {
			session = openSession();

			BudgetSupport[] array = new BudgetSupportImpl[3];

			array[0] = getByBudgetParticipatifIdAndPublikUserId_PrevAndNext(
				session, budgetSupport, budgetParticipatifId, publikUserId,
				orderByComparator, true);

			array[1] = budgetSupport;

			array[2] = getByBudgetParticipatifIdAndPublikUserId_PrevAndNext(
				session, budgetSupport, budgetParticipatifId, publikUserId,
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

	protected BudgetSupport
		getByBudgetParticipatifIdAndPublikUserId_PrevAndNext(
			Session session, BudgetSupport budgetSupport,
			long budgetParticipatifId, String publikUserId,
			OrderByComparator<BudgetSupport> orderByComparator,
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

		sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE);

		sb.append(
			_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_BUDGETPARTICIPATIFID_2);

		boolean bindPublikUserId = false;

		if (publikUserId.isEmpty()) {
			sb.append(
				_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_3);
		}
		else {
			bindPublikUserId = true;

			sb.append(
				_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_2);
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
			sb.append(BudgetSupportModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(budgetParticipatifId);

		if (bindPublikUserId) {
			queryPos.add(publikUserId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						budgetSupport)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BudgetSupport> list = query.list();

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

		for (BudgetSupport budgetSupport :
				findByBudgetParticipatifIdAndPublikUserId(
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

		publikUserId = Objects.toString(publikUserId, "");

		FinderPath finderPath =
			_finderPathCountByBudgetParticipatifIdAndPublikUserId;

		Object[] finderArgs = new Object[] {budgetParticipatifId, publikUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_BUDGETSUPPORT_WHERE);

			sb.append(
				_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_BUDGETPARTICIPATIFID_2);

			boolean bindPublikUserId = false;

			if (publikUserId.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				sb.append(
					_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(budgetParticipatifId);

				if (bindPublikUserId) {
					queryPos.add(publikUserId);
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

	private static final String
		_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_BUDGETPARTICIPATIFID_2 =
			"budgetSupport.budgetParticipatifId = ? AND ";

	private static final String
		_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_2 =
			"budgetSupport.publikUserId = ?";

	private static final String
		_FINDER_COLUMN_BUDGETPARTICIPATIFIDANDPUBLIKUSERID_PUBLIKUSERID_3 =
			"(budgetSupport.publikUserId IS NULL OR budgetSupport.publikUserId = '')";

	public BudgetSupportPersistenceImpl() {
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

		setModelClass(BudgetSupport.class);
	}

	/**
	 * Caches the budget support in the entity cache if it is enabled.
	 *
	 * @param budgetSupport the budget support
	 */
	@Override
	public void cacheResult(BudgetSupport budgetSupport) {
		entityCache.putResult(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportImpl.class, budgetSupport.getPrimaryKey(),
			budgetSupport);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {budgetSupport.getUuid(), budgetSupport.getGroupId()},
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
					BudgetSupportImpl.class, budgetSupport.getPrimaryKey()) ==
						null) {

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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BudgetSupport budgetSupport) {
		entityCache.removeResult(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
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
			entityCache.removeResult(
				BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
				BudgetSupportImpl.class, budgetSupport.getPrimaryKey());

			clearUniqueFindersCache(
				(BudgetSupportModelImpl)budgetSupport, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
				BudgetSupportImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		BudgetSupportModelImpl budgetSupportModelImpl) {

		Object[] args = new Object[] {
			budgetSupportModelImpl.getUuid(),
			budgetSupportModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, budgetSupportModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		BudgetSupportModelImpl budgetSupportModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				budgetSupportModelImpl.getUuid(),
				budgetSupportModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((budgetSupportModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				budgetSupportModelImpl.getOriginalUuid(),
				budgetSupportModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
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

		budgetSupport.setCompanyId(CompanyThreadLocal.getCompanyId());

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

			BudgetSupport budgetSupport = (BudgetSupport)session.get(
				BudgetSupportImpl.class, primaryKey);

			if (budgetSupport == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBudgetSupportException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(budgetSupport);
		}
		catch (NoSuchBudgetSupportException noSuchEntityException) {
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
	protected BudgetSupport removeImpl(BudgetSupport budgetSupport) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(budgetSupport)) {
				budgetSupport = (BudgetSupport)session.get(
					BudgetSupportImpl.class, budgetSupport.getPrimaryKeyObj());
			}

			if (budgetSupport != null) {
				session.delete(budgetSupport);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
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
		boolean isNew = budgetSupport.isNew();

		if (!(budgetSupport instanceof BudgetSupportModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(budgetSupport.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					budgetSupport);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in budgetSupport proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom BudgetSupport implementation " +
					budgetSupport.getClass());
		}

		BudgetSupportModelImpl budgetSupportModelImpl =
			(BudgetSupportModelImpl)budgetSupport;

		if (Validator.isNull(budgetSupport.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			budgetSupport.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

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
				budgetSupport.setModifiedDate(
					serviceContext.getModifiedDate(now));
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
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!BudgetSupportModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {budgetSupportModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				budgetSupportModelImpl.getUuid(),
				budgetSupportModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {budgetSupportModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {
				budgetSupportModelImpl.getBudgetParticipatifId()
			};

			finderCache.removeResult(
				_finderPathCountByBudgetParticipatif, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByBudgetParticipatif, args);

			args = new Object[] {budgetSupportModelImpl.getPublikUserId()};

			finderCache.removeResult(_finderPathCountByPublikUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPublikUserId, args);

			args = new Object[] {
				budgetSupportModelImpl.getBudgetParticipatifId(),
				budgetSupportModelImpl.getPublikUserId()
			};

			finderCache.removeResult(
				_finderPathCountByBudgetParticipatifIdAndPublikUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByBudgetParticipatifIdAndPublikUserId,
				args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((budgetSupportModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					budgetSupportModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {budgetSupportModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((budgetSupportModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					budgetSupportModelImpl.getOriginalUuid(),
					budgetSupportModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					budgetSupportModelImpl.getUuid(),
					budgetSupportModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((budgetSupportModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					budgetSupportModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {budgetSupportModelImpl.getGroupId()};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((budgetSupportModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByBudgetParticipatif.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					budgetSupportModelImpl.getOriginalBudgetParticipatifId()
				};

				finderCache.removeResult(
					_finderPathCountByBudgetParticipatif, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByBudgetParticipatif, args);

				args = new Object[] {
					budgetSupportModelImpl.getBudgetParticipatifId()
				};

				finderCache.removeResult(
					_finderPathCountByBudgetParticipatif, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByBudgetParticipatif, args);
			}

			if ((budgetSupportModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPublikUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					budgetSupportModelImpl.getOriginalPublikUserId()
				};

				finderCache.removeResult(_finderPathCountByPublikUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPublikUserId, args);

				args = new Object[] {budgetSupportModelImpl.getPublikUserId()};

				finderCache.removeResult(_finderPathCountByPublikUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPublikUserId, args);
			}

			if ((budgetSupportModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByBudgetParticipatifIdAndPublikUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					budgetSupportModelImpl.getOriginalBudgetParticipatifId(),
					budgetSupportModelImpl.getOriginalPublikUserId()
				};

				finderCache.removeResult(
					_finderPathCountByBudgetParticipatifIdAndPublikUserId,
					args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByBudgetParticipatifIdAndPublikUserId,
					args);

				args = new Object[] {
					budgetSupportModelImpl.getBudgetParticipatifId(),
					budgetSupportModelImpl.getPublikUserId()
				};

				finderCache.removeResult(
					_finderPathCountByBudgetParticipatifIdAndPublikUserId,
					args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByBudgetParticipatifIdAndPublikUserId,
					args);
			}
		}

		entityCache.putResult(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportImpl.class, budgetSupport.getPrimaryKey(),
			budgetSupport, false);

		clearUniqueFindersCache(budgetSupportModelImpl, false);
		cacheUniqueFindersCache(budgetSupportModelImpl);

		budgetSupport.resetOriginalValues();

		return budgetSupport;
	}

	/**
	 * Returns the budget support with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
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

			throw new NoSuchBudgetSupportException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return budgetSupport;
	}

	/**
	 * Returns the budget support with the primary key or throws a <code>NoSuchBudgetSupportException</code> if it could not be found.
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
		Serializable serializable = entityCache.getResult(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		BudgetSupport budgetSupport = (BudgetSupport)serializable;

		if (budgetSupport == null) {
			Session session = null;

			try {
				session = openSession();

				budgetSupport = (BudgetSupport)session.get(
					BudgetSupportImpl.class, primaryKey);

				if (budgetSupport != null) {
					cacheResult(budgetSupport);
				}
				else {
					entityCache.putResult(
						BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
						BudgetSupportImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
					BudgetSupportImpl.class, primaryKey);

				throw processException(exception);
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

		Map<Serializable, BudgetSupport> map =
			new HashMap<Serializable, BudgetSupport>();

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
			Serializable serializable = entityCache.getResult(
				BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
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

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_BUDGETSUPPORT_WHERE_PKS_IN);

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

			for (BudgetSupport budgetSupport :
					(List<BudgetSupport>)query.list()) {

				map.put(budgetSupport.getPrimaryKeyObj(), budgetSupport);

				cacheResult(budgetSupport);

				uncachedPrimaryKeys.remove(budgetSupport.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
					BudgetSupportImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of budget supports
	 */
	@Override
	public List<BudgetSupport> findAll(
		int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget supports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of budget supports
	 */
	@Override
	public List<BudgetSupport> findAll(
		int start, int end, OrderByComparator<BudgetSupport> orderByComparator,
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

		List<BudgetSupport> list = null;

		if (useFinderCache) {
			list = (List<BudgetSupport>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_BUDGETSUPPORT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_BUDGETSUPPORT;

				sql = sql.concat(BudgetSupportModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<BudgetSupport>)QueryUtil.list(
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
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_BUDGETSUPPORT);

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
		return BudgetSupportModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the budget support persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] {String.class.getName()},
			BudgetSupportModelImpl.UUID_COLUMN_BITMASK |
			BudgetSupportModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			BudgetSupportModelImpl.UUID_COLUMN_BITMASK |
			BudgetSupportModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			BudgetSupportModelImpl.UUID_COLUMN_BITMASK |
			BudgetSupportModelImpl.COMPANYID_COLUMN_BITMASK |
			BudgetSupportModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupId", new String[] {Long.class.getName()},
			BudgetSupportModelImpl.GROUPID_COLUMN_BITMASK |
			BudgetSupportModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByBudgetParticipatif = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByBudgetParticipatif",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByBudgetParticipatif = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByBudgetParticipatif", new String[] {Long.class.getName()},
			BudgetSupportModelImpl.BUDGETPARTICIPATIFID_COLUMN_BITMASK |
			BudgetSupportModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByBudgetParticipatif = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByBudgetParticipatif", new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByPublikUserId = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPublikUserId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPublikUserId = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
			BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPublikUserId", new String[] {String.class.getName()},
			BudgetSupportModelImpl.PUBLIKUSERID_COLUMN_BITMASK |
			BudgetSupportModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByPublikUserId = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikUserId",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByBudgetParticipatifIdAndPublikUserId =
			new FinderPath(
				BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
				BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
				BudgetSupportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByBudgetParticipatifIdAndPublikUserId",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByBudgetParticipatifIdAndPublikUserId =
			new FinderPath(
				BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
				BudgetSupportModelImpl.FINDER_CACHE_ENABLED,
				BudgetSupportImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByBudgetParticipatifIdAndPublikUserId",
				new String[] {Long.class.getName(), String.class.getName()},
				BudgetSupportModelImpl.BUDGETPARTICIPATIFID_COLUMN_BITMASK |
				BudgetSupportModelImpl.PUBLIKUSERID_COLUMN_BITMASK |
				BudgetSupportModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByBudgetParticipatifIdAndPublikUserId = new FinderPath(
			BudgetSupportModelImpl.ENTITY_CACHE_ENABLED,
			BudgetSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByBudgetParticipatifIdAndPublikUserId",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(BudgetSupportImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_BUDGETSUPPORT =
		"SELECT budgetSupport FROM BudgetSupport budgetSupport";

	private static final String _SQL_SELECT_BUDGETSUPPORT_WHERE_PKS_IN =
		"SELECT budgetSupport FROM BudgetSupport budgetSupport WHERE budgetSupportId IN (";

	private static final String _SQL_SELECT_BUDGETSUPPORT_WHERE =
		"SELECT budgetSupport FROM BudgetSupport budgetSupport WHERE ";

	private static final String _SQL_COUNT_BUDGETSUPPORT =
		"SELECT COUNT(budgetSupport) FROM BudgetSupport budgetSupport";

	private static final String _SQL_COUNT_BUDGETSUPPORT_WHERE =
		"SELECT COUNT(budgetSupport) FROM BudgetSupport budgetSupport WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "budgetSupport.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No BudgetSupport exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No BudgetSupport exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		BudgetSupportPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}