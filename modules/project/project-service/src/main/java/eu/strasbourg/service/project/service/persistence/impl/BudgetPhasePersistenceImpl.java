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

import eu.strasbourg.service.project.exception.NoSuchBudgetPhaseException;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.model.impl.BudgetPhaseImpl;
import eu.strasbourg.service.project.model.impl.BudgetPhaseModelImpl;
import eu.strasbourg.service.project.service.persistence.BudgetPhasePersistence;

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
 * The persistence implementation for the budget phase service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @generated
 */
public class BudgetPhasePersistenceImpl
	extends BasePersistenceImpl<BudgetPhase> implements BudgetPhasePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>BudgetPhaseUtil</code> to access the budget phase persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		BudgetPhaseImpl.class.getName();

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
	 * Returns all the budget phases where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget phases where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @return the range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget phases where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget phases where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator,
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

		List<BudgetPhase> list = null;

		if (useFinderCache) {
			list = (List<BudgetPhase>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetPhase budgetPhase : list) {
					if (!uuid.equals(budgetPhase.getUuid())) {
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

			sb.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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
				sb.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
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

				list = (List<BudgetPhase>)QueryUtil.list(
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
	 * Returns the first budget phase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByUuid_First(
			String uuid, OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {

		BudgetPhase budgetPhase = fetchByUuid_First(uuid, orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchBudgetPhaseException(sb.toString());
	}

	/**
	 * Returns the first budget phase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByUuid_First(
		String uuid, OrderByComparator<BudgetPhase> orderByComparator) {

		List<BudgetPhase> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget phase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByUuid_Last(
			String uuid, OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {

		BudgetPhase budgetPhase = fetchByUuid_Last(uuid, orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchBudgetPhaseException(sb.toString());
	}

	/**
	 * Returns the last budget phase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByUuid_Last(
		String uuid, OrderByComparator<BudgetPhase> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<BudgetPhase> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget phases before and after the current budget phase in the ordered set where uuid = &#63;.
	 *
	 * @param budgetPhaseId the primary key of the current budget phase
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget phase
	 * @throws NoSuchBudgetPhaseException if a budget phase with the primary key could not be found
	 */
	@Override
	public BudgetPhase[] findByUuid_PrevAndNext(
			long budgetPhaseId, String uuid,
			OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {

		uuid = Objects.toString(uuid, "");

		BudgetPhase budgetPhase = findByPrimaryKey(budgetPhaseId);

		Session session = null;

		try {
			session = openSession();

			BudgetPhase[] array = new BudgetPhaseImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, budgetPhase, uuid, orderByComparator, true);

			array[1] = budgetPhase;

			array[2] = getByUuid_PrevAndNext(
				session, budgetPhase, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected BudgetPhase getByUuid_PrevAndNext(
		Session session, BudgetPhase budgetPhase, String uuid,
		OrderByComparator<BudgetPhase> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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
			sb.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(budgetPhase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BudgetPhase> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget phases where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (BudgetPhase budgetPhase :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(budgetPhase);
		}
	}

	/**
	 * Returns the number of budget phases where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching budget phases
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BUDGETPHASE_WHERE);

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
		"budgetPhase.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(budgetPhase.uuid IS NULL OR budgetPhase.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the budget phase where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchBudgetPhaseException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByUUID_G(String uuid, long groupId)
		throws NoSuchBudgetPhaseException {

		BudgetPhase budgetPhase = fetchByUUID_G(uuid, groupId);

		if (budgetPhase == null) {
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

			throw new NoSuchBudgetPhaseException(sb.toString());
		}

		return budgetPhase;
	}

	/**
	 * Returns the budget phase where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the budget phase where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByUUID_G(
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

		if (result instanceof BudgetPhase) {
			BudgetPhase budgetPhase = (BudgetPhase)result;

			if (!Objects.equals(uuid, budgetPhase.getUuid()) ||
				(groupId != budgetPhase.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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

				List<BudgetPhase> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					BudgetPhase budgetPhase = list.get(0);

					result = budgetPhase;

					cacheResult(budgetPhase);
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
			return (BudgetPhase)result;
		}
	}

	/**
	 * Removes the budget phase where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the budget phase that was removed
	 */
	@Override
	public BudgetPhase removeByUUID_G(String uuid, long groupId)
		throws NoSuchBudgetPhaseException {

		BudgetPhase budgetPhase = findByUUID_G(uuid, groupId);

		return remove(budgetPhase);
	}

	/**
	 * Returns the number of budget phases where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching budget phases
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_BUDGETPHASE_WHERE);

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
		"budgetPhase.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(budgetPhase.uuid IS NULL OR budgetPhase.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"budgetPhase.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the budget phases where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget phases where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @return the range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget phases where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget phases where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator,
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

		List<BudgetPhase> list = null;

		if (useFinderCache) {
			list = (List<BudgetPhase>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetPhase budgetPhase : list) {
					if (!uuid.equals(budgetPhase.getUuid()) ||
						(companyId != budgetPhase.getCompanyId())) {

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

			sb.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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
				sb.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
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

				list = (List<BudgetPhase>)QueryUtil.list(
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
	 * Returns the first budget phase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {

		BudgetPhase budgetPhase = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchBudgetPhaseException(sb.toString());
	}

	/**
	 * Returns the first budget phase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<BudgetPhase> orderByComparator) {

		List<BudgetPhase> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget phase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {

		BudgetPhase budgetPhase = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchBudgetPhaseException(sb.toString());
	}

	/**
	 * Returns the last budget phase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<BudgetPhase> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<BudgetPhase> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget phases before and after the current budget phase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param budgetPhaseId the primary key of the current budget phase
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget phase
	 * @throws NoSuchBudgetPhaseException if a budget phase with the primary key could not be found
	 */
	@Override
	public BudgetPhase[] findByUuid_C_PrevAndNext(
			long budgetPhaseId, String uuid, long companyId,
			OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {

		uuid = Objects.toString(uuid, "");

		BudgetPhase budgetPhase = findByPrimaryKey(budgetPhaseId);

		Session session = null;

		try {
			session = openSession();

			BudgetPhase[] array = new BudgetPhaseImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, budgetPhase, uuid, companyId, orderByComparator, true);

			array[1] = budgetPhase;

			array[2] = getByUuid_C_PrevAndNext(
				session, budgetPhase, uuid, companyId, orderByComparator,
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

	protected BudgetPhase getByUuid_C_PrevAndNext(
		Session session, BudgetPhase budgetPhase, String uuid, long companyId,
		OrderByComparator<BudgetPhase> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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
			sb.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(budgetPhase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BudgetPhase> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget phases where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (BudgetPhase budgetPhase :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(budgetPhase);
		}
	}

	/**
	 * Returns the number of budget phases where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching budget phases
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_BUDGETPHASE_WHERE);

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
		"budgetPhase.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(budgetPhase.uuid IS NULL OR budgetPhase.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"budgetPhase.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the budget phases where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget phases where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @return the range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget phases where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget phases where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator,
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

		List<BudgetPhase> list = null;

		if (useFinderCache) {
			list = (List<BudgetPhase>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetPhase budgetPhase : list) {
					if (groupId != budgetPhase.getGroupId()) {
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

			sb.append(_SQL_SELECT_BUDGETPHASE_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<BudgetPhase>)QueryUtil.list(
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
	 * Returns the first budget phase in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByGroupId_First(
			long groupId, OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {

		BudgetPhase budgetPhase = fetchByGroupId_First(
			groupId, orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchBudgetPhaseException(sb.toString());
	}

	/**
	 * Returns the first budget phase in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByGroupId_First(
		long groupId, OrderByComparator<BudgetPhase> orderByComparator) {

		List<BudgetPhase> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget phase in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByGroupId_Last(
			long groupId, OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {

		BudgetPhase budgetPhase = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchBudgetPhaseException(sb.toString());
	}

	/**
	 * Returns the last budget phase in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByGroupId_Last(
		long groupId, OrderByComparator<BudgetPhase> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<BudgetPhase> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget phases before and after the current budget phase in the ordered set where groupId = &#63;.
	 *
	 * @param budgetPhaseId the primary key of the current budget phase
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget phase
	 * @throws NoSuchBudgetPhaseException if a budget phase with the primary key could not be found
	 */
	@Override
	public BudgetPhase[] findByGroupId_PrevAndNext(
			long budgetPhaseId, long groupId,
			OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {

		BudgetPhase budgetPhase = findByPrimaryKey(budgetPhaseId);

		Session session = null;

		try {
			session = openSession();

			BudgetPhase[] array = new BudgetPhaseImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, budgetPhase, groupId, orderByComparator, true);

			array[1] = budgetPhase;

			array[2] = getByGroupId_PrevAndNext(
				session, budgetPhase, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected BudgetPhase getByGroupId_PrevAndNext(
		Session session, BudgetPhase budgetPhase, long groupId,
		OrderByComparator<BudgetPhase> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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
			sb.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(budgetPhase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BudgetPhase> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget phases where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (BudgetPhase budgetPhase :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(budgetPhase);
		}
	}

	/**
	 * Returns the number of budget phases where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching budget phases
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BUDGETPHASE_WHERE);

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
		"budgetPhase.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByIsActiveAndGroupId;
	private FinderPath _finderPathWithoutPaginationFindByIsActiveAndGroupId;
	private FinderPath _finderPathCountByIsActiveAndGroupId;

	/**
	 * Returns all the budget phases where isActive = &#63; and groupId = &#63;.
	 *
	 * @param isActive the is active
	 * @param groupId the group ID
	 * @return the matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByIsActiveAndGroupId(
		boolean isActive, long groupId) {

		return findByIsActiveAndGroupId(
			isActive, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget phases where isActive = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @return the range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByIsActiveAndGroupId(
		boolean isActive, long groupId, int start, int end) {

		return findByIsActiveAndGroupId(isActive, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget phases where isActive = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByIsActiveAndGroupId(
		boolean isActive, long groupId, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator) {

		return findByIsActiveAndGroupId(
			isActive, groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget phases where isActive = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByIsActiveAndGroupId(
		boolean isActive, long groupId, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByIsActiveAndGroupId;
				finderArgs = new Object[] {isActive, groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByIsActiveAndGroupId;
			finderArgs = new Object[] {
				isActive, groupId, start, end, orderByComparator
			};
		}

		List<BudgetPhase> list = null;

		if (useFinderCache) {
			list = (List<BudgetPhase>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetPhase budgetPhase : list) {
					if ((isActive != budgetPhase.isIsActive()) ||
						(groupId != budgetPhase.getGroupId())) {

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

			sb.append(_SQL_SELECT_BUDGETPHASE_WHERE);

			sb.append(_FINDER_COLUMN_ISACTIVEANDGROUPID_ISACTIVE_2);

			sb.append(_FINDER_COLUMN_ISACTIVEANDGROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

				queryPos.add(groupId);

				list = (List<BudgetPhase>)QueryUtil.list(
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
	 * Returns the first budget phase in the ordered set where isActive = &#63; and groupId = &#63;.
	 *
	 * @param isActive the is active
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByIsActiveAndGroupId_First(
			boolean isActive, long groupId,
			OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {

		BudgetPhase budgetPhase = fetchByIsActiveAndGroupId_First(
			isActive, groupId, orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchBudgetPhaseException(sb.toString());
	}

	/**
	 * Returns the first budget phase in the ordered set where isActive = &#63; and groupId = &#63;.
	 *
	 * @param isActive the is active
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByIsActiveAndGroupId_First(
		boolean isActive, long groupId,
		OrderByComparator<BudgetPhase> orderByComparator) {

		List<BudgetPhase> list = findByIsActiveAndGroupId(
			isActive, groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget phase in the ordered set where isActive = &#63; and groupId = &#63;.
	 *
	 * @param isActive the is active
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByIsActiveAndGroupId_Last(
			boolean isActive, long groupId,
			OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {

		BudgetPhase budgetPhase = fetchByIsActiveAndGroupId_Last(
			isActive, groupId, orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchBudgetPhaseException(sb.toString());
	}

	/**
	 * Returns the last budget phase in the ordered set where isActive = &#63; and groupId = &#63;.
	 *
	 * @param isActive the is active
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByIsActiveAndGroupId_Last(
		boolean isActive, long groupId,
		OrderByComparator<BudgetPhase> orderByComparator) {

		int count = countByIsActiveAndGroupId(isActive, groupId);

		if (count == 0) {
			return null;
		}

		List<BudgetPhase> list = findByIsActiveAndGroupId(
			isActive, groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget phases before and after the current budget phase in the ordered set where isActive = &#63; and groupId = &#63;.
	 *
	 * @param budgetPhaseId the primary key of the current budget phase
	 * @param isActive the is active
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget phase
	 * @throws NoSuchBudgetPhaseException if a budget phase with the primary key could not be found
	 */
	@Override
	public BudgetPhase[] findByIsActiveAndGroupId_PrevAndNext(
			long budgetPhaseId, boolean isActive, long groupId,
			OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {

		BudgetPhase budgetPhase = findByPrimaryKey(budgetPhaseId);

		Session session = null;

		try {
			session = openSession();

			BudgetPhase[] array = new BudgetPhaseImpl[3];

			array[0] = getByIsActiveAndGroupId_PrevAndNext(
				session, budgetPhase, isActive, groupId, orderByComparator,
				true);

			array[1] = budgetPhase;

			array[2] = getByIsActiveAndGroupId_PrevAndNext(
				session, budgetPhase, isActive, groupId, orderByComparator,
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

	protected BudgetPhase getByIsActiveAndGroupId_PrevAndNext(
		Session session, BudgetPhase budgetPhase, boolean isActive,
		long groupId, OrderByComparator<BudgetPhase> orderByComparator,
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

		sb.append(_SQL_SELECT_BUDGETPHASE_WHERE);

		sb.append(_FINDER_COLUMN_ISACTIVEANDGROUPID_ISACTIVE_2);

		sb.append(_FINDER_COLUMN_ISACTIVEANDGROUPID_GROUPID_2);

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
			sb.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(isActive);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(budgetPhase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BudgetPhase> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget phases where isActive = &#63; and groupId = &#63; from the database.
	 *
	 * @param isActive the is active
	 * @param groupId the group ID
	 */
	@Override
	public void removeByIsActiveAndGroupId(boolean isActive, long groupId) {
		for (BudgetPhase budgetPhase :
				findByIsActiveAndGroupId(
					isActive, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(budgetPhase);
		}
	}

	/**
	 * Returns the number of budget phases where isActive = &#63; and groupId = &#63;.
	 *
	 * @param isActive the is active
	 * @param groupId the group ID
	 * @return the number of matching budget phases
	 */
	@Override
	public int countByIsActiveAndGroupId(boolean isActive, long groupId) {
		FinderPath finderPath = _finderPathCountByIsActiveAndGroupId;

		Object[] finderArgs = new Object[] {isActive, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_BUDGETPHASE_WHERE);

			sb.append(_FINDER_COLUMN_ISACTIVEANDGROUPID_ISACTIVE_2);

			sb.append(_FINDER_COLUMN_ISACTIVEANDGROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

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

	private static final String _FINDER_COLUMN_ISACTIVEANDGROUPID_ISACTIVE_2 =
		"budgetPhase.isActive = ? AND ";

	private static final String _FINDER_COLUMN_ISACTIVEANDGROUPID_GROUPID_2 =
		"budgetPhase.groupId = ?";

	public BudgetPhasePersistenceImpl() {
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

		setModelClass(BudgetPhase.class);
	}

	/**
	 * Caches the budget phase in the entity cache if it is enabled.
	 *
	 * @param budgetPhase the budget phase
	 */
	@Override
	public void cacheResult(BudgetPhase budgetPhase) {
		entityCache.putResult(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED, BudgetPhaseImpl.class,
			budgetPhase.getPrimaryKey(), budgetPhase);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {budgetPhase.getUuid(), budgetPhase.getGroupId()},
			budgetPhase);

		budgetPhase.resetOriginalValues();
	}

	/**
	 * Caches the budget phases in the entity cache if it is enabled.
	 *
	 * @param budgetPhases the budget phases
	 */
	@Override
	public void cacheResult(List<BudgetPhase> budgetPhases) {
		for (BudgetPhase budgetPhase : budgetPhases) {
			if (entityCache.getResult(
					BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
					BudgetPhaseImpl.class, budgetPhase.getPrimaryKey()) ==
						null) {

				cacheResult(budgetPhase);
			}
			else {
				budgetPhase.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all budget phases.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BudgetPhaseImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the budget phase.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BudgetPhase budgetPhase) {
		entityCache.removeResult(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED, BudgetPhaseImpl.class,
			budgetPhase.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((BudgetPhaseModelImpl)budgetPhase, true);
	}

	@Override
	public void clearCache(List<BudgetPhase> budgetPhases) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BudgetPhase budgetPhase : budgetPhases) {
			entityCache.removeResult(
				BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
				BudgetPhaseImpl.class, budgetPhase.getPrimaryKey());

			clearUniqueFindersCache((BudgetPhaseModelImpl)budgetPhase, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
				BudgetPhaseImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		BudgetPhaseModelImpl budgetPhaseModelImpl) {

		Object[] args = new Object[] {
			budgetPhaseModelImpl.getUuid(), budgetPhaseModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, budgetPhaseModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		BudgetPhaseModelImpl budgetPhaseModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				budgetPhaseModelImpl.getUuid(),
				budgetPhaseModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((budgetPhaseModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				budgetPhaseModelImpl.getOriginalUuid(),
				budgetPhaseModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new budget phase with the primary key. Does not add the budget phase to the database.
	 *
	 * @param budgetPhaseId the primary key for the new budget phase
	 * @return the new budget phase
	 */
	@Override
	public BudgetPhase create(long budgetPhaseId) {
		BudgetPhase budgetPhase = new BudgetPhaseImpl();

		budgetPhase.setNew(true);
		budgetPhase.setPrimaryKey(budgetPhaseId);

		String uuid = PortalUUIDUtil.generate();

		budgetPhase.setUuid(uuid);

		budgetPhase.setCompanyId(CompanyThreadLocal.getCompanyId());

		return budgetPhase;
	}

	/**
	 * Removes the budget phase with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param budgetPhaseId the primary key of the budget phase
	 * @return the budget phase that was removed
	 * @throws NoSuchBudgetPhaseException if a budget phase with the primary key could not be found
	 */
	@Override
	public BudgetPhase remove(long budgetPhaseId)
		throws NoSuchBudgetPhaseException {

		return remove((Serializable)budgetPhaseId);
	}

	/**
	 * Removes the budget phase with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the budget phase
	 * @return the budget phase that was removed
	 * @throws NoSuchBudgetPhaseException if a budget phase with the primary key could not be found
	 */
	@Override
	public BudgetPhase remove(Serializable primaryKey)
		throws NoSuchBudgetPhaseException {

		Session session = null;

		try {
			session = openSession();

			BudgetPhase budgetPhase = (BudgetPhase)session.get(
				BudgetPhaseImpl.class, primaryKey);

			if (budgetPhase == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBudgetPhaseException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(budgetPhase);
		}
		catch (NoSuchBudgetPhaseException noSuchEntityException) {
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
	protected BudgetPhase removeImpl(BudgetPhase budgetPhase) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(budgetPhase)) {
				budgetPhase = (BudgetPhase)session.get(
					BudgetPhaseImpl.class, budgetPhase.getPrimaryKeyObj());
			}

			if (budgetPhase != null) {
				session.delete(budgetPhase);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (budgetPhase != null) {
			clearCache(budgetPhase);
		}

		return budgetPhase;
	}

	@Override
	public BudgetPhase updateImpl(BudgetPhase budgetPhase) {
		boolean isNew = budgetPhase.isNew();

		if (!(budgetPhase instanceof BudgetPhaseModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(budgetPhase.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(budgetPhase);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in budgetPhase proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom BudgetPhase implementation " +
					budgetPhase.getClass());
		}

		BudgetPhaseModelImpl budgetPhaseModelImpl =
			(BudgetPhaseModelImpl)budgetPhase;

		if (Validator.isNull(budgetPhase.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			budgetPhase.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (budgetPhase.getCreateDate() == null)) {
			if (serviceContext == null) {
				budgetPhase.setCreateDate(now);
			}
			else {
				budgetPhase.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!budgetPhaseModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				budgetPhase.setModifiedDate(now);
			}
			else {
				budgetPhase.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (budgetPhase.isNew()) {
				session.save(budgetPhase);

				budgetPhase.setNew(false);
			}
			else {
				budgetPhase = (BudgetPhase)session.merge(budgetPhase);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!BudgetPhaseModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {budgetPhaseModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				budgetPhaseModelImpl.getUuid(),
				budgetPhaseModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {budgetPhaseModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {
				budgetPhaseModelImpl.isIsActive(),
				budgetPhaseModelImpl.getGroupId()
			};

			finderCache.removeResult(
				_finderPathCountByIsActiveAndGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByIsActiveAndGroupId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((budgetPhaseModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					budgetPhaseModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {budgetPhaseModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((budgetPhaseModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					budgetPhaseModelImpl.getOriginalUuid(),
					budgetPhaseModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					budgetPhaseModelImpl.getUuid(),
					budgetPhaseModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((budgetPhaseModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					budgetPhaseModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {budgetPhaseModelImpl.getGroupId()};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((budgetPhaseModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByIsActiveAndGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					budgetPhaseModelImpl.getOriginalIsActive(),
					budgetPhaseModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(
					_finderPathCountByIsActiveAndGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByIsActiveAndGroupId, args);

				args = new Object[] {
					budgetPhaseModelImpl.isIsActive(),
					budgetPhaseModelImpl.getGroupId()
				};

				finderCache.removeResult(
					_finderPathCountByIsActiveAndGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByIsActiveAndGroupId, args);
			}
		}

		entityCache.putResult(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED, BudgetPhaseImpl.class,
			budgetPhase.getPrimaryKey(), budgetPhase, false);

		clearUniqueFindersCache(budgetPhaseModelImpl, false);
		cacheUniqueFindersCache(budgetPhaseModelImpl);

		budgetPhase.resetOriginalValues();

		return budgetPhase;
	}

	/**
	 * Returns the budget phase with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the budget phase
	 * @return the budget phase
	 * @throws NoSuchBudgetPhaseException if a budget phase with the primary key could not be found
	 */
	@Override
	public BudgetPhase findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBudgetPhaseException {

		BudgetPhase budgetPhase = fetchByPrimaryKey(primaryKey);

		if (budgetPhase == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBudgetPhaseException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return budgetPhase;
	}

	/**
	 * Returns the budget phase with the primary key or throws a <code>NoSuchBudgetPhaseException</code> if it could not be found.
	 *
	 * @param budgetPhaseId the primary key of the budget phase
	 * @return the budget phase
	 * @throws NoSuchBudgetPhaseException if a budget phase with the primary key could not be found
	 */
	@Override
	public BudgetPhase findByPrimaryKey(long budgetPhaseId)
		throws NoSuchBudgetPhaseException {

		return findByPrimaryKey((Serializable)budgetPhaseId);
	}

	/**
	 * Returns the budget phase with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the budget phase
	 * @return the budget phase, or <code>null</code> if a budget phase with the primary key could not be found
	 */
	@Override
	public BudgetPhase fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED, BudgetPhaseImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		BudgetPhase budgetPhase = (BudgetPhase)serializable;

		if (budgetPhase == null) {
			Session session = null;

			try {
				session = openSession();

				budgetPhase = (BudgetPhase)session.get(
					BudgetPhaseImpl.class, primaryKey);

				if (budgetPhase != null) {
					cacheResult(budgetPhase);
				}
				else {
					entityCache.putResult(
						BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
						BudgetPhaseImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
					BudgetPhaseImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return budgetPhase;
	}

	/**
	 * Returns the budget phase with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param budgetPhaseId the primary key of the budget phase
	 * @return the budget phase, or <code>null</code> if a budget phase with the primary key could not be found
	 */
	@Override
	public BudgetPhase fetchByPrimaryKey(long budgetPhaseId) {
		return fetchByPrimaryKey((Serializable)budgetPhaseId);
	}

	@Override
	public Map<Serializable, BudgetPhase> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, BudgetPhase> map =
			new HashMap<Serializable, BudgetPhase>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			BudgetPhase budgetPhase = fetchByPrimaryKey(primaryKey);

			if (budgetPhase != null) {
				map.put(primaryKey, budgetPhase);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
				BudgetPhaseImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (BudgetPhase)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_BUDGETPHASE_WHERE_PKS_IN);

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

			for (BudgetPhase budgetPhase : (List<BudgetPhase>)query.list()) {
				map.put(budgetPhase.getPrimaryKeyObj(), budgetPhase);

				cacheResult(budgetPhase);

				uncachedPrimaryKeys.remove(budgetPhase.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
					BudgetPhaseImpl.class, primaryKey, nullModel);
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
	 * Returns all the budget phases.
	 *
	 * @return the budget phases
	 */
	@Override
	public List<BudgetPhase> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget phases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @return the range of budget phases
	 */
	@Override
	public List<BudgetPhase> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget phases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of budget phases
	 */
	@Override
	public List<BudgetPhase> findAll(
		int start, int end, OrderByComparator<BudgetPhase> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget phases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetPhaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of budget phases
	 */
	@Override
	public List<BudgetPhase> findAll(
		int start, int end, OrderByComparator<BudgetPhase> orderByComparator,
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

		List<BudgetPhase> list = null;

		if (useFinderCache) {
			list = (List<BudgetPhase>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_BUDGETPHASE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_BUDGETPHASE;

				sql = sql.concat(BudgetPhaseModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<BudgetPhase>)QueryUtil.list(
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
	 * Removes all the budget phases from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BudgetPhase budgetPhase : findAll()) {
			remove(budgetPhase);
		}
	}

	/**
	 * Returns the number of budget phases.
	 *
	 * @return the number of budget phases
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_BUDGETPHASE);

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
		return BudgetPhaseModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the budget phase persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			BudgetPhaseModelImpl.UUID_COLUMN_BITMASK |
			BudgetPhaseModelImpl.BEGINDATE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			BudgetPhaseModelImpl.UUID_COLUMN_BITMASK |
			BudgetPhaseModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			BudgetPhaseModelImpl.UUID_COLUMN_BITMASK |
			BudgetPhaseModelImpl.COMPANYID_COLUMN_BITMASK |
			BudgetPhaseModelImpl.BEGINDATE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			BudgetPhaseModelImpl.GROUPID_COLUMN_BITMASK |
			BudgetPhaseModelImpl.BEGINDATE_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByIsActiveAndGroupId = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByIsActiveAndGroupId",
			new String[] {
				Boolean.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByIsActiveAndGroupId = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByIsActiveAndGroupId",
			new String[] {Boolean.class.getName(), Long.class.getName()},
			BudgetPhaseModelImpl.ISACTIVE_COLUMN_BITMASK |
			BudgetPhaseModelImpl.GROUPID_COLUMN_BITMASK |
			BudgetPhaseModelImpl.BEGINDATE_COLUMN_BITMASK);

		_finderPathCountByIsActiveAndGroupId = new FinderPath(
			BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByIsActiveAndGroupId",
			new String[] {Boolean.class.getName(), Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(BudgetPhaseImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_BUDGETPHASE =
		"SELECT budgetPhase FROM BudgetPhase budgetPhase";

	private static final String _SQL_SELECT_BUDGETPHASE_WHERE_PKS_IN =
		"SELECT budgetPhase FROM BudgetPhase budgetPhase WHERE budgetPhaseId IN (";

	private static final String _SQL_SELECT_BUDGETPHASE_WHERE =
		"SELECT budgetPhase FROM BudgetPhase budgetPhase WHERE ";

	private static final String _SQL_COUNT_BUDGETPHASE =
		"SELECT COUNT(budgetPhase) FROM BudgetPhase budgetPhase";

	private static final String _SQL_COUNT_BUDGETPHASE_WHERE =
		"SELECT COUNT(budgetPhase) FROM BudgetPhase budgetPhase WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "budgetPhase.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No BudgetPhase exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No BudgetPhase exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		BudgetPhasePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}