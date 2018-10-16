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
import eu.strasbourg.service.project.exception.NoSuchBudgetPhaseException;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.model.impl.BudgetPhaseImpl;
import eu.strasbourg.service.project.model.impl.BudgetPhaseModelImpl;
import eu.strasbourg.service.project.service.persistence.BudgetPhasePersistence;

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
 * The persistence implementation for the budget phase service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see BudgetPhasePersistence
 * @see eu.strasbourg.service.project.service.persistence.BudgetPhaseUtil
 * @generated
 */
@ProviderType
public class BudgetPhasePersistenceImpl extends BasePersistenceImpl<BudgetPhase>
	implements BudgetPhasePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BudgetPhaseUtil} to access the budget phase persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BudgetPhaseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			BudgetPhaseModelImpl.UUID_COLUMN_BITMASK |
			BudgetPhaseModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByUuid(String uuid, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget phases where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByUuid(String uuid, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator,
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

		List<BudgetPhase> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetPhase>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetPhase budgetPhase : list) {
					if (!Objects.equals(uuid, budgetPhase.getUuid())) {
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

			query.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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
				query.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
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
					list = (List<BudgetPhase>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetPhase>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first budget phase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByUuid_First(String uuid,
		OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = fetchByUuid_First(uuid, orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetPhaseException(msg.toString());
	}

	/**
	 * Returns the first budget phase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByUuid_First(String uuid,
		OrderByComparator<BudgetPhase> orderByComparator) {
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
	public BudgetPhase findByUuid_Last(String uuid,
		OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = fetchByUuid_Last(uuid, orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetPhaseException(msg.toString());
	}

	/**
	 * Returns the last budget phase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByUuid_Last(String uuid,
		OrderByComparator<BudgetPhase> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<BudgetPhase> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

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
	public BudgetPhase[] findByUuid_PrevAndNext(long budgetPhaseId,
		String uuid, OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = findByPrimaryKey(budgetPhaseId);

		Session session = null;

		try {
			session = openSession();

			BudgetPhase[] array = new BudgetPhaseImpl[3];

			array[0] = getByUuid_PrevAndNext(session, budgetPhase, uuid,
					orderByComparator, true);

			array[1] = budgetPhase;

			array[2] = getByUuid_PrevAndNext(session, budgetPhase, uuid,
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

	protected BudgetPhase getByUuid_PrevAndNext(Session session,
		BudgetPhase budgetPhase, String uuid,
		OrderByComparator<BudgetPhase> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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
			query.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(budgetPhase);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetPhase> list = q.list();

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
		for (BudgetPhase budgetPhase : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUDGETPHASE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "budgetPhase.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "budgetPhase.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(budgetPhase.uuid IS NULL OR budgetPhase.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			BudgetPhaseModelImpl.UUID_COLUMN_BITMASK |
			BudgetPhaseModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the budget phase where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBudgetPhaseException} if it could not be found.
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

			throw new NoSuchBudgetPhaseException(msg.toString());
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
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof BudgetPhase) {
			BudgetPhase budgetPhase = (BudgetPhase)result;

			if (!Objects.equals(uuid, budgetPhase.getUuid()) ||
					(groupId != budgetPhase.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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

				List<BudgetPhase> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					BudgetPhase budgetPhase = list.get(0);

					result = budgetPhase;

					cacheResult(budgetPhase);

					if ((budgetPhase.getUuid() == null) ||
							!budgetPhase.getUuid().equals(uuid) ||
							(budgetPhase.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, budgetPhase);
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BUDGETPHASE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "budgetPhase.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "budgetPhase.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(budgetPhase.uuid IS NULL OR budgetPhase.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "budgetPhase.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			BudgetPhaseModelImpl.UUID_COLUMN_BITMASK |
			BudgetPhaseModelImpl.COMPANYID_COLUMN_BITMASK |
			BudgetPhaseModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the budget phases where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget phases where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @return the range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget phases where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<BudgetPhase> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<BudgetPhase> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget phases where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<BudgetPhase> orderByComparator,
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

		List<BudgetPhase> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetPhase>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetPhase budgetPhase : list) {
					if (!Objects.equals(uuid, budgetPhase.getUuid()) ||
							(companyId != budgetPhase.getCompanyId())) {
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

			query.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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
				query.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
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
					list = (List<BudgetPhase>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetPhase>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first budget phase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetPhaseException(msg.toString());
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
	public BudgetPhase fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<BudgetPhase> orderByComparator) {
		List<BudgetPhase> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

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
	public BudgetPhase findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetPhaseException(msg.toString());
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
	public BudgetPhase fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<BudgetPhase> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<BudgetPhase> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

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
	public BudgetPhase[] findByUuid_C_PrevAndNext(long budgetPhaseId,
		String uuid, long companyId,
		OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = findByPrimaryKey(budgetPhaseId);

		Session session = null;

		try {
			session = openSession();

			BudgetPhase[] array = new BudgetPhaseImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, budgetPhase, uuid,
					companyId, orderByComparator, true);

			array[1] = budgetPhase;

			array[2] = getByUuid_C_PrevAndNext(session, budgetPhase, uuid,
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

	protected BudgetPhase getByUuid_C_PrevAndNext(Session session,
		BudgetPhase budgetPhase, String uuid, long companyId,
		OrderByComparator<BudgetPhase> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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
			query.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(budgetPhase);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetPhase> list = q.list();

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
		for (BudgetPhase budgetPhase : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BUDGETPHASE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "budgetPhase.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "budgetPhase.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(budgetPhase.uuid IS NULL OR budgetPhase.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "budgetPhase.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			BudgetPhaseModelImpl.GROUPID_COLUMN_BITMASK |
			BudgetPhaseModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the budget phases where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget phases where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByGroupId(long groupId, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget phases where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByGroupId(long groupId, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator,
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

		List<BudgetPhase> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetPhase>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetPhase budgetPhase : list) {
					if ((groupId != budgetPhase.getGroupId())) {
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

			query.append(_SQL_SELECT_BUDGETPHASE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<BudgetPhase>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetPhase>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first budget phase in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByGroupId_First(long groupId,
		OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = fetchByGroupId_First(groupId,
				orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetPhaseException(msg.toString());
	}

	/**
	 * Returns the first budget phase in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByGroupId_First(long groupId,
		OrderByComparator<BudgetPhase> orderByComparator) {
		List<BudgetPhase> list = findByGroupId(groupId, 0, 1, orderByComparator);

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
	public BudgetPhase findByGroupId_Last(long groupId,
		OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = fetchByGroupId_Last(groupId, orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetPhaseException(msg.toString());
	}

	/**
	 * Returns the last budget phase in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByGroupId_Last(long groupId,
		OrderByComparator<BudgetPhase> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<BudgetPhase> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

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
	public BudgetPhase[] findByGroupId_PrevAndNext(long budgetPhaseId,
		long groupId, OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = findByPrimaryKey(budgetPhaseId);

		Session session = null;

		try {
			session = openSession();

			BudgetPhase[] array = new BudgetPhaseImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, budgetPhase, groupId,
					orderByComparator, true);

			array[1] = budgetPhase;

			array[2] = getByGroupId_PrevAndNext(session, budgetPhase, groupId,
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

	protected BudgetPhase getByGroupId_PrevAndNext(Session session,
		BudgetPhase budgetPhase, long groupId,
		OrderByComparator<BudgetPhase> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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
			query.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(budgetPhase);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetPhase> list = q.list();

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
		for (BudgetPhase budgetPhase : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUDGETPHASE_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "budgetPhase.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BUDGETPARTICIPATIFID =
		new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBybudgetParticipatifId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIFID =
		new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBybudgetParticipatifId",
			new String[] { Long.class.getName() },
			BudgetPhaseModelImpl.BUDGETPARTICIPATIFID_COLUMN_BITMASK |
			BudgetPhaseModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIFID = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBybudgetParticipatifId", new String[] { Long.class.getName() });

	/**
	 * Returns all the budget phases where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @return the matching budget phases
	 */
	@Override
	public List<BudgetPhase> findBybudgetParticipatifId(
		long budgetParticipatifId) {
		return findBybudgetParticipatifId(budgetParticipatifId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budget phases where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @return the range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findBybudgetParticipatifId(
		long budgetParticipatifId, int start, int end) {
		return findBybudgetParticipatifId(budgetParticipatifId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget phases where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findBybudgetParticipatifId(
		long budgetParticipatifId, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator) {
		return findBybudgetParticipatifId(budgetParticipatifId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget phases where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findBybudgetParticipatifId(
		long budgetParticipatifId, int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIFID;
			finderArgs = new Object[] { budgetParticipatifId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BUDGETPARTICIPATIFID;
			finderArgs = new Object[] {
					budgetParticipatifId,
					
					start, end, orderByComparator
				};
		}

		List<BudgetPhase> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetPhase>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetPhase budgetPhase : list) {
					if ((budgetParticipatifId != budgetPhase.getBudgetParticipatifId())) {
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

			query.append(_SQL_SELECT_BUDGETPHASE_WHERE);

			query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFID_BUDGETPARTICIPATIFID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(budgetParticipatifId);

				if (!pagination) {
					list = (List<BudgetPhase>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetPhase>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first budget phase in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findBybudgetParticipatifId_First(
		long budgetParticipatifId,
		OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = fetchBybudgetParticipatifId_First(budgetParticipatifId,
				orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("budgetParticipatifId=");
		msg.append(budgetParticipatifId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetPhaseException(msg.toString());
	}

	/**
	 * Returns the first budget phase in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchBybudgetParticipatifId_First(
		long budgetParticipatifId,
		OrderByComparator<BudgetPhase> orderByComparator) {
		List<BudgetPhase> list = findBybudgetParticipatifId(budgetParticipatifId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget phase in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findBybudgetParticipatifId_Last(
		long budgetParticipatifId,
		OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = fetchBybudgetParticipatifId_Last(budgetParticipatifId,
				orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("budgetParticipatifId=");
		msg.append(budgetParticipatifId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetPhaseException(msg.toString());
	}

	/**
	 * Returns the last budget phase in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchBybudgetParticipatifId_Last(
		long budgetParticipatifId,
		OrderByComparator<BudgetPhase> orderByComparator) {
		int count = countBybudgetParticipatifId(budgetParticipatifId);

		if (count == 0) {
			return null;
		}

		List<BudgetPhase> list = findBybudgetParticipatifId(budgetParticipatifId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget phases before and after the current budget phase in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetPhaseId the primary key of the current budget phase
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget phase
	 * @throws NoSuchBudgetPhaseException if a budget phase with the primary key could not be found
	 */
	@Override
	public BudgetPhase[] findBybudgetParticipatifId_PrevAndNext(
		long budgetPhaseId, long budgetParticipatifId,
		OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = findByPrimaryKey(budgetPhaseId);

		Session session = null;

		try {
			session = openSession();

			BudgetPhase[] array = new BudgetPhaseImpl[3];

			array[0] = getBybudgetParticipatifId_PrevAndNext(session,
					budgetPhase, budgetParticipatifId, orderByComparator, true);

			array[1] = budgetPhase;

			array[2] = getBybudgetParticipatifId_PrevAndNext(session,
					budgetPhase, budgetParticipatifId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BudgetPhase getBybudgetParticipatifId_PrevAndNext(
		Session session, BudgetPhase budgetPhase, long budgetParticipatifId,
		OrderByComparator<BudgetPhase> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BUDGETPHASE_WHERE);

		query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFID_BUDGETPARTICIPATIFID_2);

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
			query.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(budgetParticipatifId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(budgetPhase);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetPhase> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget phases where budgetParticipatifId = &#63; from the database.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 */
	@Override
	public void removeBybudgetParticipatifId(long budgetParticipatifId) {
		for (BudgetPhase budgetPhase : findBybudgetParticipatifId(
				budgetParticipatifId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(budgetPhase);
		}
	}

	/**
	 * Returns the number of budget phases where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @return the number of matching budget phases
	 */
	@Override
	public int countBybudgetParticipatifId(long budgetParticipatifId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIFID;

		Object[] finderArgs = new Object[] { budgetParticipatifId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUDGETPHASE_WHERE);

			query.append(_FINDER_COLUMN_BUDGETPARTICIPATIFID_BUDGETPARTICIPATIFID_2);

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

	private static final String _FINDER_COLUMN_BUDGETPARTICIPATIFID_BUDGETPARTICIPATIFID_2 =
		"budgetPhase.budgetParticipatifId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKID = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPublikId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID =
		new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, BudgetPhaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikId",
			new String[] { String.class.getName() },
			BudgetPhaseModelImpl.PUBLIKID_COLUMN_BITMASK |
			BudgetPhaseModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKID = new FinderPath(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the budget phases where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByPublikId(String publikId) {
		return findByPublikId(publikId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the budget phases where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @return the range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByPublikId(String publikId, int start, int end) {
		return findByPublikId(publikId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budget phases where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByPublikId(String publikId, int start,
		int end, OrderByComparator<BudgetPhase> orderByComparator) {
		return findByPublikId(publikId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget phases where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget phases
	 */
	@Override
	public List<BudgetPhase> findByPublikId(String publikId, int start,
		int end, OrderByComparator<BudgetPhase> orderByComparator,
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

		List<BudgetPhase> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetPhase>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BudgetPhase budgetPhase : list) {
					if (!Objects.equals(publikId, budgetPhase.getPublikId())) {
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

			query.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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
				query.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
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
					list = (List<BudgetPhase>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetPhase>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first budget phase in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByPublikId_First(String publikId,
		OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = fetchByPublikId_First(publikId,
				orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikId=");
		msg.append(publikId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetPhaseException(msg.toString());
	}

	/**
	 * Returns the first budget phase in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByPublikId_First(String publikId,
		OrderByComparator<BudgetPhase> orderByComparator) {
		List<BudgetPhase> list = findByPublikId(publikId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget phase in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase
	 * @throws NoSuchBudgetPhaseException if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase findByPublikId_Last(String publikId,
		OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = fetchByPublikId_Last(publikId,
				orderByComparator);

		if (budgetPhase != null) {
			return budgetPhase;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikId=");
		msg.append(publikId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBudgetPhaseException(msg.toString());
	}

	/**
	 * Returns the last budget phase in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget phase, or <code>null</code> if a matching budget phase could not be found
	 */
	@Override
	public BudgetPhase fetchByPublikId_Last(String publikId,
		OrderByComparator<BudgetPhase> orderByComparator) {
		int count = countByPublikId(publikId);

		if (count == 0) {
			return null;
		}

		List<BudgetPhase> list = findByPublikId(publikId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budget phases before and after the current budget phase in the ordered set where publikId = &#63;.
	 *
	 * @param budgetPhaseId the primary key of the current budget phase
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget phase
	 * @throws NoSuchBudgetPhaseException if a budget phase with the primary key could not be found
	 */
	@Override
	public BudgetPhase[] findByPublikId_PrevAndNext(long budgetPhaseId,
		String publikId, OrderByComparator<BudgetPhase> orderByComparator)
		throws NoSuchBudgetPhaseException {
		BudgetPhase budgetPhase = findByPrimaryKey(budgetPhaseId);

		Session session = null;

		try {
			session = openSession();

			BudgetPhase[] array = new BudgetPhaseImpl[3];

			array[0] = getByPublikId_PrevAndNext(session, budgetPhase,
					publikId, orderByComparator, true);

			array[1] = budgetPhase;

			array[2] = getByPublikId_PrevAndNext(session, budgetPhase,
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

	protected BudgetPhase getByPublikId_PrevAndNext(Session session,
		BudgetPhase budgetPhase, String publikId,
		OrderByComparator<BudgetPhase> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BUDGETPHASE_WHERE);

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
			query.append(BudgetPhaseModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(budgetPhase);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BudgetPhase> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budget phases where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 */
	@Override
	public void removeByPublikId(String publikId) {
		for (BudgetPhase budgetPhase : findByPublikId(publikId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(budgetPhase);
		}
	}

	/**
	 * Returns the number of budget phases where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching budget phases
	 */
	@Override
	public int countByPublikId(String publikId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKID;

		Object[] finderArgs = new Object[] { publikId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUDGETPHASE_WHERE);

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

	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_1 = "budgetPhase.publikId IS NULL";
	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_2 = "budgetPhase.publikId = ?";
	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_3 = "(budgetPhase.publikId IS NULL OR budgetPhase.publikId = '')";

	public BudgetPhasePersistenceImpl() {
		setModelClass(BudgetPhase.class);

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
	 * Caches the budget phase in the entity cache if it is enabled.
	 *
	 * @param budgetPhase the budget phase
	 */
	@Override
	public void cacheResult(BudgetPhase budgetPhase) {
		entityCache.putResult(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseImpl.class, budgetPhase.getPrimaryKey(), budgetPhase);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { budgetPhase.getUuid(), budgetPhase.getGroupId() },
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
						BudgetPhaseImpl.class, budgetPhase.getPrimaryKey()) == null) {
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BudgetPhase budgetPhase) {
		entityCache.removeResult(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseImpl.class, budgetPhase.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((BudgetPhaseModelImpl)budgetPhase, true);
	}

	@Override
	public void clearCache(List<BudgetPhase> budgetPhases) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BudgetPhase budgetPhase : budgetPhases) {
			entityCache.removeResult(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
				BudgetPhaseImpl.class, budgetPhase.getPrimaryKey());

			clearUniqueFindersCache((BudgetPhaseModelImpl)budgetPhase, true);
		}
	}

	protected void cacheUniqueFindersCache(
		BudgetPhaseModelImpl budgetPhaseModelImpl) {
		Object[] args = new Object[] {
				budgetPhaseModelImpl.getUuid(),
				budgetPhaseModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			budgetPhaseModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		BudgetPhaseModelImpl budgetPhaseModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					budgetPhaseModelImpl.getUuid(),
					budgetPhaseModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((budgetPhaseModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					budgetPhaseModelImpl.getOriginalUuid(),
					budgetPhaseModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
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

		budgetPhase.setCompanyId(companyProvider.getCompanyId());

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

			BudgetPhase budgetPhase = (BudgetPhase)session.get(BudgetPhaseImpl.class,
					primaryKey);

			if (budgetPhase == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBudgetPhaseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(budgetPhase);
		}
		catch (NoSuchBudgetPhaseException nsee) {
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
	protected BudgetPhase removeImpl(BudgetPhase budgetPhase) {
		budgetPhase = toUnwrappedModel(budgetPhase);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(budgetPhase)) {
				budgetPhase = (BudgetPhase)session.get(BudgetPhaseImpl.class,
						budgetPhase.getPrimaryKeyObj());
			}

			if (budgetPhase != null) {
				session.delete(budgetPhase);
			}
		}
		catch (Exception e) {
			throw processException(e);
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
		budgetPhase = toUnwrappedModel(budgetPhase);

		boolean isNew = budgetPhase.isNew();

		BudgetPhaseModelImpl budgetPhaseModelImpl = (BudgetPhaseModelImpl)budgetPhase;

		if (Validator.isNull(budgetPhase.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			budgetPhase.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

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
				budgetPhase.setModifiedDate(serviceContext.getModifiedDate(now));
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
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!BudgetPhaseModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { budgetPhaseModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					budgetPhaseModelImpl.getUuid(),
					budgetPhaseModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { budgetPhaseModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] { budgetPhaseModelImpl.getBudgetParticipatifId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIFID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIFID,
				args);

			args = new Object[] { budgetPhaseModelImpl.getPublikId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((budgetPhaseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetPhaseModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { budgetPhaseModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((budgetPhaseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetPhaseModelImpl.getOriginalUuid(),
						budgetPhaseModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						budgetPhaseModelImpl.getUuid(),
						budgetPhaseModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((budgetPhaseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetPhaseModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { budgetPhaseModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((budgetPhaseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIFID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetPhaseModelImpl.getOriginalBudgetParticipatifId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIFID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIFID,
					args);

				args = new Object[] {
						budgetPhaseModelImpl.getBudgetParticipatifId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUDGETPARTICIPATIFID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUDGETPARTICIPATIFID,
					args);
			}

			if ((budgetPhaseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						budgetPhaseModelImpl.getOriginalPublikId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID,
					args);

				args = new Object[] { budgetPhaseModelImpl.getPublikId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID,
					args);
			}
		}

		entityCache.putResult(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
			BudgetPhaseImpl.class, budgetPhase.getPrimaryKey(), budgetPhase,
			false);

		clearUniqueFindersCache(budgetPhaseModelImpl, false);
		cacheUniqueFindersCache(budgetPhaseModelImpl);

		budgetPhase.resetOriginalValues();

		return budgetPhase;
	}

	protected BudgetPhase toUnwrappedModel(BudgetPhase budgetPhase) {
		if (budgetPhase instanceof BudgetPhaseImpl) {
			return budgetPhase;
		}

		BudgetPhaseImpl budgetPhaseImpl = new BudgetPhaseImpl();

		budgetPhaseImpl.setNew(budgetPhase.isNew());
		budgetPhaseImpl.setPrimaryKey(budgetPhase.getPrimaryKey());

		budgetPhaseImpl.setUuid(budgetPhase.getUuid());
		budgetPhaseImpl.setBudgetPhaseId(budgetPhase.getBudgetPhaseId());
		budgetPhaseImpl.setGroupId(budgetPhase.getGroupId());
		budgetPhaseImpl.setCompanyId(budgetPhase.getCompanyId());
		budgetPhaseImpl.setUserId(budgetPhase.getUserId());
		budgetPhaseImpl.setUserName(budgetPhase.getUserName());
		budgetPhaseImpl.setCreateDate(budgetPhase.getCreateDate());
		budgetPhaseImpl.setModifiedDate(budgetPhase.getModifiedDate());
		budgetPhaseImpl.setStatus(budgetPhase.getStatus());
		budgetPhaseImpl.setStatusByUserId(budgetPhase.getStatusByUserId());
		budgetPhaseImpl.setStatusByUserName(budgetPhase.getStatusByUserName());
		budgetPhaseImpl.setStatusDate(budgetPhase.getStatusDate());
		budgetPhaseImpl.setName(budgetPhase.getName());
		budgetPhaseImpl.setDescription(budgetPhase.getDescription());
		budgetPhaseImpl.setNumberOfVote(budgetPhase.getNumberOfVote());
		budgetPhaseImpl.setIsActive(budgetPhase.isIsActive());
		budgetPhaseImpl.setBeginDate(budgetPhase.getBeginDate());
		budgetPhaseImpl.setEndDate(budgetPhase.getEndDate());
		budgetPhaseImpl.setPublikId(budgetPhase.getPublikId());
		budgetPhaseImpl.setBudgetParticipatifId(budgetPhase.getBudgetParticipatifId());

		return budgetPhaseImpl;
	}

	/**
	 * Returns the budget phase with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
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

			throw new NoSuchBudgetPhaseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return budgetPhase;
	}

	/**
	 * Returns the budget phase with the primary key or throws a {@link NoSuchBudgetPhaseException} if it could not be found.
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
		Serializable serializable = entityCache.getResult(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
				BudgetPhaseImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		BudgetPhase budgetPhase = (BudgetPhase)serializable;

		if (budgetPhase == null) {
			Session session = null;

			try {
				session = openSession();

				budgetPhase = (BudgetPhase)session.get(BudgetPhaseImpl.class,
						primaryKey);

				if (budgetPhase != null) {
					cacheResult(budgetPhase);
				}
				else {
					entityCache.putResult(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
						BudgetPhaseImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
					BudgetPhaseImpl.class, primaryKey);

				throw processException(e);
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

		Map<Serializable, BudgetPhase> map = new HashMap<Serializable, BudgetPhase>();

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
			Serializable serializable = entityCache.getResult(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
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

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_BUDGETPHASE_WHERE_PKS_IN);

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

			for (BudgetPhase budgetPhase : (List<BudgetPhase>)q.list()) {
				map.put(budgetPhase.getPrimaryKeyObj(), budgetPhase);

				cacheResult(budgetPhase);

				uncachedPrimaryKeys.remove(budgetPhase.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(BudgetPhaseModelImpl.ENTITY_CACHE_ENABLED,
					BudgetPhaseImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of budget phases
	 */
	@Override
	public List<BudgetPhase> findAll(int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budget phases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget phases
	 * @param end the upper bound of the range of budget phases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of budget phases
	 */
	@Override
	public List<BudgetPhase> findAll(int start, int end,
		OrderByComparator<BudgetPhase> orderByComparator,
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

		List<BudgetPhase> list = null;

		if (retrieveFromCache) {
			list = (List<BudgetPhase>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_BUDGETPHASE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BUDGETPHASE;

				if (pagination) {
					sql = sql.concat(BudgetPhaseModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BudgetPhase>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BudgetPhase>)QueryUtil.list(q, getDialect(),
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
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BUDGETPHASE);

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
		return BudgetPhaseModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the budget phase persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(BudgetPhaseImpl.class.getName());
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
	private static final String _SQL_SELECT_BUDGETPHASE = "SELECT budgetPhase FROM BudgetPhase budgetPhase";
	private static final String _SQL_SELECT_BUDGETPHASE_WHERE_PKS_IN = "SELECT budgetPhase FROM BudgetPhase budgetPhase WHERE budgetPhaseId IN (";
	private static final String _SQL_SELECT_BUDGETPHASE_WHERE = "SELECT budgetPhase FROM BudgetPhase budgetPhase WHERE ";
	private static final String _SQL_COUNT_BUDGETPHASE = "SELECT COUNT(budgetPhase) FROM BudgetPhase budgetPhase";
	private static final String _SQL_COUNT_BUDGETPHASE_WHERE = "SELECT COUNT(budgetPhase) FROM BudgetPhase budgetPhase WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "budgetPhase.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BudgetPhase exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BudgetPhase exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(BudgetPhasePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}