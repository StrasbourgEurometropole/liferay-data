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

package eu.strasbourg.service.activity.service.persistence.impl;

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

import eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException;
import eu.strasbourg.service.activity.model.AssociationActivity;
import eu.strasbourg.service.activity.model.impl.AssociationActivityImpl;
import eu.strasbourg.service.activity.model.impl.AssociationActivityModelImpl;
import eu.strasbourg.service.activity.service.persistence.AssociationActivityPersistence;

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
 * The persistence implementation for the association activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssociationActivityPersistence
 * @see eu.strasbourg.service.activity.service.persistence.AssociationActivityUtil
 * @generated
 */
@ProviderType
public class AssociationActivityPersistenceImpl extends BasePersistenceImpl<AssociationActivity>
	implements AssociationActivityPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssociationActivityUtil} to access the association activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssociationActivityImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED,
			AssociationActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED,
			AssociationActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED,
			AssociationActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED,
			AssociationActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			AssociationActivityModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the association activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching association activities
	 */
	@Override
	public List<AssociationActivity> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the association activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @return the range of matching association activities
	 */
	@Override
	public List<AssociationActivity> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the association activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching association activities
	 */
	@Override
	public List<AssociationActivity> findByUuid(String uuid, int start,
		int end, OrderByComparator<AssociationActivity> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the association activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching association activities
	 */
	@Override
	public List<AssociationActivity> findByUuid(String uuid, int start,
		int end, OrderByComparator<AssociationActivity> orderByComparator,
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

		List<AssociationActivity> list = null;

		if (retrieveFromCache) {
			list = (List<AssociationActivity>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssociationActivity associationActivity : list) {
					if (!Objects.equals(uuid, associationActivity.getUuid())) {
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

			query.append(_SQL_SELECT_ASSOCIATIONACTIVITY_WHERE);

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
				query.append(AssociationActivityModelImpl.ORDER_BY_JPQL);
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
					list = (List<AssociationActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssociationActivity>)QueryUtil.list(q,
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
	 * Returns the first association activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association activity
	 * @throws NoSuchAssociationActivityException if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity findByUuid_First(String uuid,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = fetchByUuid_First(uuid,
				orderByComparator);

		if (associationActivity != null) {
			return associationActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssociationActivityException(msg.toString());
	}

	/**
	 * Returns the first association activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association activity, or <code>null</code> if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity fetchByUuid_First(String uuid,
		OrderByComparator<AssociationActivity> orderByComparator) {
		List<AssociationActivity> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last association activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association activity
	 * @throws NoSuchAssociationActivityException if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity findByUuid_Last(String uuid,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = fetchByUuid_Last(uuid,
				orderByComparator);

		if (associationActivity != null) {
			return associationActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssociationActivityException(msg.toString());
	}

	/**
	 * Returns the last association activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association activity, or <code>null</code> if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity fetchByUuid_Last(String uuid,
		OrderByComparator<AssociationActivity> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<AssociationActivity> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the association activities before and after the current association activity in the ordered set where uuid = &#63;.
	 *
	 * @param associationActivityId the primary key of the current association activity
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next association activity
	 * @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	 */
	@Override
	public AssociationActivity[] findByUuid_PrevAndNext(
		long associationActivityId, String uuid,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = findByPrimaryKey(associationActivityId);

		Session session = null;

		try {
			session = openSession();

			AssociationActivity[] array = new AssociationActivityImpl[3];

			array[0] = getByUuid_PrevAndNext(session, associationActivity,
					uuid, orderByComparator, true);

			array[1] = associationActivity;

			array[2] = getByUuid_PrevAndNext(session, associationActivity,
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

	protected AssociationActivity getByUuid_PrevAndNext(Session session,
		AssociationActivity associationActivity, String uuid,
		OrderByComparator<AssociationActivity> orderByComparator,
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

		query.append(_SQL_SELECT_ASSOCIATIONACTIVITY_WHERE);

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
			query.append(AssociationActivityModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(associationActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssociationActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the association activities where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (AssociationActivity associationActivity : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(associationActivity);
		}
	}

	/**
	 * Returns the number of association activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching association activities
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSOCIATIONACTIVITY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "associationActivity.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "associationActivity.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(associationActivity.uuid IS NULL OR associationActivity.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED,
			AssociationActivityImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			AssociationActivityModelImpl.UUID_COLUMN_BITMASK |
			AssociationActivityModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the association activity where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAssociationActivityException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching association activity
	 * @throws NoSuchAssociationActivityException if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity findByUUID_G(String uuid, long groupId)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = fetchByUUID_G(uuid, groupId);

		if (associationActivity == null) {
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

			throw new NoSuchAssociationActivityException(msg.toString());
		}

		return associationActivity;
	}

	/**
	 * Returns the association activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching association activity, or <code>null</code> if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the association activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching association activity, or <code>null</code> if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof AssociationActivity) {
			AssociationActivity associationActivity = (AssociationActivity)result;

			if (!Objects.equals(uuid, associationActivity.getUuid()) ||
					(groupId != associationActivity.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ASSOCIATIONACTIVITY_WHERE);

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

				List<AssociationActivity> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					AssociationActivity associationActivity = list.get(0);

					result = associationActivity;

					cacheResult(associationActivity);

					if ((associationActivity.getUuid() == null) ||
							!associationActivity.getUuid().equals(uuid) ||
							(associationActivity.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, associationActivity);
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
			return (AssociationActivity)result;
		}
	}

	/**
	 * Removes the association activity where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the association activity that was removed
	 */
	@Override
	public AssociationActivity removeByUUID_G(String uuid, long groupId)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = findByUUID_G(uuid, groupId);

		return remove(associationActivity);
	}

	/**
	 * Returns the number of association activities where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching association activities
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSOCIATIONACTIVITY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "associationActivity.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "associationActivity.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(associationActivity.uuid IS NULL OR associationActivity.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "associationActivity.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED,
			AssociationActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED,
			AssociationActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			AssociationActivityModelImpl.UUID_COLUMN_BITMASK |
			AssociationActivityModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the association activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching association activities
	 */
	@Override
	public List<AssociationActivity> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the association activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @return the range of matching association activities
	 */
	@Override
	public List<AssociationActivity> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the association activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching association activities
	 */
	@Override
	public List<AssociationActivity> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the association activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching association activities
	 */
	@Override
	public List<AssociationActivity> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator,
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

		List<AssociationActivity> list = null;

		if (retrieveFromCache) {
			list = (List<AssociationActivity>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssociationActivity associationActivity : list) {
					if (!Objects.equals(uuid, associationActivity.getUuid()) ||
							(companyId != associationActivity.getCompanyId())) {
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

			query.append(_SQL_SELECT_ASSOCIATIONACTIVITY_WHERE);

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
				query.append(AssociationActivityModelImpl.ORDER_BY_JPQL);
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
					list = (List<AssociationActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssociationActivity>)QueryUtil.list(q,
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
	 * Returns the first association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association activity
	 * @throws NoSuchAssociationActivityException if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (associationActivity != null) {
			return associationActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssociationActivityException(msg.toString());
	}

	/**
	 * Returns the first association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association activity, or <code>null</code> if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<AssociationActivity> orderByComparator) {
		List<AssociationActivity> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association activity
	 * @throws NoSuchAssociationActivityException if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (associationActivity != null) {
			return associationActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssociationActivityException(msg.toString());
	}

	/**
	 * Returns the last association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association activity, or <code>null</code> if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<AssociationActivity> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<AssociationActivity> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the association activities before and after the current association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param associationActivityId the primary key of the current association activity
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next association activity
	 * @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	 */
	@Override
	public AssociationActivity[] findByUuid_C_PrevAndNext(
		long associationActivityId, String uuid, long companyId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = findByPrimaryKey(associationActivityId);

		Session session = null;

		try {
			session = openSession();

			AssociationActivity[] array = new AssociationActivityImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, associationActivity,
					uuid, companyId, orderByComparator, true);

			array[1] = associationActivity;

			array[2] = getByUuid_C_PrevAndNext(session, associationActivity,
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

	protected AssociationActivity getByUuid_C_PrevAndNext(Session session,
		AssociationActivity associationActivity, String uuid, long companyId,
		OrderByComparator<AssociationActivity> orderByComparator,
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

		query.append(_SQL_SELECT_ASSOCIATIONACTIVITY_WHERE);

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
			query.append(AssociationActivityModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(associationActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssociationActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the association activities where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (AssociationActivity associationActivity : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(associationActivity);
		}
	}

	/**
	 * Returns the number of association activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching association activities
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSOCIATIONACTIVITY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "associationActivity.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "associationActivity.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(associationActivity.uuid IS NULL OR associationActivity.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "associationActivity.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSOCIATION =
		new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED,
			AssociationActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssociation",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSOCIATION =
		new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED,
			AssociationActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssociation",
			new String[] { Long.class.getName() },
			AssociationActivityModelImpl.ASSOCIATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSOCIATION = new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssociation",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the association activities where associationId = &#63;.
	 *
	 * @param associationId the association ID
	 * @return the matching association activities
	 */
	@Override
	public List<AssociationActivity> findByAssociation(long associationId) {
		return findByAssociation(associationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the association activities where associationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param associationId the association ID
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @return the range of matching association activities
	 */
	@Override
	public List<AssociationActivity> findByAssociation(long associationId,
		int start, int end) {
		return findByAssociation(associationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the association activities where associationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param associationId the association ID
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching association activities
	 */
	@Override
	public List<AssociationActivity> findByAssociation(long associationId,
		int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return findByAssociation(associationId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the association activities where associationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param associationId the association ID
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching association activities
	 */
	@Override
	public List<AssociationActivity> findByAssociation(long associationId,
		int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSOCIATION;
			finderArgs = new Object[] { associationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSOCIATION;
			finderArgs = new Object[] {
					associationId,
					
					start, end, orderByComparator
				};
		}

		List<AssociationActivity> list = null;

		if (retrieveFromCache) {
			list = (List<AssociationActivity>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssociationActivity associationActivity : list) {
					if ((associationId != associationActivity.getAssociationId())) {
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

			query.append(_SQL_SELECT_ASSOCIATIONACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_ASSOCIATION_ASSOCIATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssociationActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(associationId);

				if (!pagination) {
					list = (List<AssociationActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssociationActivity>)QueryUtil.list(q,
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
	 * Returns the first association activity in the ordered set where associationId = &#63;.
	 *
	 * @param associationId the association ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association activity
	 * @throws NoSuchAssociationActivityException if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity findByAssociation_First(long associationId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = fetchByAssociation_First(associationId,
				orderByComparator);

		if (associationActivity != null) {
			return associationActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("associationId=");
		msg.append(associationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssociationActivityException(msg.toString());
	}

	/**
	 * Returns the first association activity in the ordered set where associationId = &#63;.
	 *
	 * @param associationId the association ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association activity, or <code>null</code> if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity fetchByAssociation_First(long associationId,
		OrderByComparator<AssociationActivity> orderByComparator) {
		List<AssociationActivity> list = findByAssociation(associationId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last association activity in the ordered set where associationId = &#63;.
	 *
	 * @param associationId the association ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association activity
	 * @throws NoSuchAssociationActivityException if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity findByAssociation_Last(long associationId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = fetchByAssociation_Last(associationId,
				orderByComparator);

		if (associationActivity != null) {
			return associationActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("associationId=");
		msg.append(associationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssociationActivityException(msg.toString());
	}

	/**
	 * Returns the last association activity in the ordered set where associationId = &#63;.
	 *
	 * @param associationId the association ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association activity, or <code>null</code> if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity fetchByAssociation_Last(long associationId,
		OrderByComparator<AssociationActivity> orderByComparator) {
		int count = countByAssociation(associationId);

		if (count == 0) {
			return null;
		}

		List<AssociationActivity> list = findByAssociation(associationId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the association activities before and after the current association activity in the ordered set where associationId = &#63;.
	 *
	 * @param associationActivityId the primary key of the current association activity
	 * @param associationId the association ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next association activity
	 * @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	 */
	@Override
	public AssociationActivity[] findByAssociation_PrevAndNext(
		long associationActivityId, long associationId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = findByPrimaryKey(associationActivityId);

		Session session = null;

		try {
			session = openSession();

			AssociationActivity[] array = new AssociationActivityImpl[3];

			array[0] = getByAssociation_PrevAndNext(session,
					associationActivity, associationId, orderByComparator, true);

			array[1] = associationActivity;

			array[2] = getByAssociation_PrevAndNext(session,
					associationActivity, associationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssociationActivity getByAssociation_PrevAndNext(
		Session session, AssociationActivity associationActivity,
		long associationId,
		OrderByComparator<AssociationActivity> orderByComparator,
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

		query.append(_SQL_SELECT_ASSOCIATIONACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_ASSOCIATION_ASSOCIATIONID_2);

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
			query.append(AssociationActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(associationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(associationActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssociationActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the association activities where associationId = &#63; from the database.
	 *
	 * @param associationId the association ID
	 */
	@Override
	public void removeByAssociation(long associationId) {
		for (AssociationActivity associationActivity : findByAssociation(
				associationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(associationActivity);
		}
	}

	/**
	 * Returns the number of association activities where associationId = &#63;.
	 *
	 * @param associationId the association ID
	 * @return the number of matching association activities
	 */
	@Override
	public int countByAssociation(long associationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ASSOCIATION;

		Object[] finderArgs = new Object[] { associationId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSOCIATIONACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_ASSOCIATION_ASSOCIATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(associationId);

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

	private static final String _FINDER_COLUMN_ASSOCIATION_ASSOCIATIONID_2 = "associationActivity.associationId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED,
			AssociationActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED,
			AssociationActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			AssociationActivityModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the association activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching association activities
	 */
	@Override
	public List<AssociationActivity> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the association activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @return the range of matching association activities
	 */
	@Override
	public List<AssociationActivity> findByGroupId(long groupId, int start,
		int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the association activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching association activities
	 */
	@Override
	public List<AssociationActivity> findByGroupId(long groupId, int start,
		int end, OrderByComparator<AssociationActivity> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the association activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching association activities
	 */
	@Override
	public List<AssociationActivity> findByGroupId(long groupId, int start,
		int end, OrderByComparator<AssociationActivity> orderByComparator,
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

		List<AssociationActivity> list = null;

		if (retrieveFromCache) {
			list = (List<AssociationActivity>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssociationActivity associationActivity : list) {
					if ((groupId != associationActivity.getGroupId())) {
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

			query.append(_SQL_SELECT_ASSOCIATIONACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssociationActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<AssociationActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssociationActivity>)QueryUtil.list(q,
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
	 * Returns the first association activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association activity
	 * @throws NoSuchAssociationActivityException if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity findByGroupId_First(long groupId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = fetchByGroupId_First(groupId,
				orderByComparator);

		if (associationActivity != null) {
			return associationActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssociationActivityException(msg.toString());
	}

	/**
	 * Returns the first association activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association activity, or <code>null</code> if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity fetchByGroupId_First(long groupId,
		OrderByComparator<AssociationActivity> orderByComparator) {
		List<AssociationActivity> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last association activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association activity
	 * @throws NoSuchAssociationActivityException if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity findByGroupId_Last(long groupId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (associationActivity != null) {
			return associationActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssociationActivityException(msg.toString());
	}

	/**
	 * Returns the last association activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association activity, or <code>null</code> if a matching association activity could not be found
	 */
	@Override
	public AssociationActivity fetchByGroupId_Last(long groupId,
		OrderByComparator<AssociationActivity> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<AssociationActivity> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the association activities before and after the current association activity in the ordered set where groupId = &#63;.
	 *
	 * @param associationActivityId the primary key of the current association activity
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next association activity
	 * @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	 */
	@Override
	public AssociationActivity[] findByGroupId_PrevAndNext(
		long associationActivityId, long groupId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = findByPrimaryKey(associationActivityId);

		Session session = null;

		try {
			session = openSession();

			AssociationActivity[] array = new AssociationActivityImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, associationActivity,
					groupId, orderByComparator, true);

			array[1] = associationActivity;

			array[2] = getByGroupId_PrevAndNext(session, associationActivity,
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

	protected AssociationActivity getByGroupId_PrevAndNext(Session session,
		AssociationActivity associationActivity, long groupId,
		OrderByComparator<AssociationActivity> orderByComparator,
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

		query.append(_SQL_SELECT_ASSOCIATIONACTIVITY_WHERE);

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
			query.append(AssociationActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(associationActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssociationActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the association activities where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (AssociationActivity associationActivity : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(associationActivity);
		}
	}

	/**
	 * Returns the number of association activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching association activities
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSOCIATIONACTIVITY_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "associationActivity.groupId = ?";

	public AssociationActivityPersistenceImpl() {
		setModelClass(AssociationActivity.class);

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
	 * Caches the association activity in the entity cache if it is enabled.
	 *
	 * @param associationActivity the association activity
	 */
	@Override
	public void cacheResult(AssociationActivity associationActivity) {
		entityCache.putResult(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityImpl.class, associationActivity.getPrimaryKey(),
			associationActivity);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				associationActivity.getUuid(), associationActivity.getGroupId()
			}, associationActivity);

		associationActivity.resetOriginalValues();
	}

	/**
	 * Caches the association activities in the entity cache if it is enabled.
	 *
	 * @param associationActivities the association activities
	 */
	@Override
	public void cacheResult(List<AssociationActivity> associationActivities) {
		for (AssociationActivity associationActivity : associationActivities) {
			if (entityCache.getResult(
						AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
						AssociationActivityImpl.class,
						associationActivity.getPrimaryKey()) == null) {
				cacheResult(associationActivity);
			}
			else {
				associationActivity.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all association activities.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AssociationActivityImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the association activity.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssociationActivity associationActivity) {
		entityCache.removeResult(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityImpl.class, associationActivity.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AssociationActivityModelImpl)associationActivity,
			true);
	}

	@Override
	public void clearCache(List<AssociationActivity> associationActivities) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssociationActivity associationActivity : associationActivities) {
			entityCache.removeResult(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
				AssociationActivityImpl.class,
				associationActivity.getPrimaryKey());

			clearUniqueFindersCache((AssociationActivityModelImpl)associationActivity,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		AssociationActivityModelImpl associationActivityModelImpl) {
		Object[] args = new Object[] {
				associationActivityModelImpl.getUuid(),
				associationActivityModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			associationActivityModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AssociationActivityModelImpl associationActivityModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					associationActivityModelImpl.getUuid(),
					associationActivityModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((associationActivityModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					associationActivityModelImpl.getOriginalUuid(),
					associationActivityModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new association activity with the primary key. Does not add the association activity to the database.
	 *
	 * @param associationActivityId the primary key for the new association activity
	 * @return the new association activity
	 */
	@Override
	public AssociationActivity create(long associationActivityId) {
		AssociationActivity associationActivity = new AssociationActivityImpl();

		associationActivity.setNew(true);
		associationActivity.setPrimaryKey(associationActivityId);

		String uuid = PortalUUIDUtil.generate();

		associationActivity.setUuid(uuid);

		associationActivity.setCompanyId(companyProvider.getCompanyId());

		return associationActivity;
	}

	/**
	 * Removes the association activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param associationActivityId the primary key of the association activity
	 * @return the association activity that was removed
	 * @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	 */
	@Override
	public AssociationActivity remove(long associationActivityId)
		throws NoSuchAssociationActivityException {
		return remove((Serializable)associationActivityId);
	}

	/**
	 * Removes the association activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the association activity
	 * @return the association activity that was removed
	 * @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	 */
	@Override
	public AssociationActivity remove(Serializable primaryKey)
		throws NoSuchAssociationActivityException {
		Session session = null;

		try {
			session = openSession();

			AssociationActivity associationActivity = (AssociationActivity)session.get(AssociationActivityImpl.class,
					primaryKey);

			if (associationActivity == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssociationActivityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(associationActivity);
		}
		catch (NoSuchAssociationActivityException nsee) {
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
	protected AssociationActivity removeImpl(
		AssociationActivity associationActivity) {
		associationActivity = toUnwrappedModel(associationActivity);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(associationActivity)) {
				associationActivity = (AssociationActivity)session.get(AssociationActivityImpl.class,
						associationActivity.getPrimaryKeyObj());
			}

			if (associationActivity != null) {
				session.delete(associationActivity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (associationActivity != null) {
			clearCache(associationActivity);
		}

		return associationActivity;
	}

	@Override
	public AssociationActivity updateImpl(
		AssociationActivity associationActivity) {
		associationActivity = toUnwrappedModel(associationActivity);

		boolean isNew = associationActivity.isNew();

		AssociationActivityModelImpl associationActivityModelImpl = (AssociationActivityModelImpl)associationActivity;

		if (Validator.isNull(associationActivity.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			associationActivity.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (associationActivity.getCreateDate() == null)) {
			if (serviceContext == null) {
				associationActivity.setCreateDate(now);
			}
			else {
				associationActivity.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!associationActivityModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				associationActivity.setModifiedDate(now);
			}
			else {
				associationActivity.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (associationActivity.isNew()) {
				session.save(associationActivity);

				associationActivity.setNew(false);
			}
			else {
				associationActivity = (AssociationActivity)session.merge(associationActivity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AssociationActivityModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { associationActivityModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					associationActivityModelImpl.getUuid(),
					associationActivityModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { associationActivityModelImpl.getAssociationId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSOCIATION, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSOCIATION,
				args);

			args = new Object[] { associationActivityModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((associationActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						associationActivityModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { associationActivityModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((associationActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						associationActivityModelImpl.getOriginalUuid(),
						associationActivityModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						associationActivityModelImpl.getUuid(),
						associationActivityModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((associationActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSOCIATION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						associationActivityModelImpl.getOriginalAssociationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSOCIATION, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSOCIATION,
					args);

				args = new Object[] {
						associationActivityModelImpl.getAssociationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSOCIATION, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSOCIATION,
					args);
			}

			if ((associationActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						associationActivityModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { associationActivityModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
			AssociationActivityImpl.class, associationActivity.getPrimaryKey(),
			associationActivity, false);

		clearUniqueFindersCache(associationActivityModelImpl, false);
		cacheUniqueFindersCache(associationActivityModelImpl);

		associationActivity.resetOriginalValues();

		return associationActivity;
	}

	protected AssociationActivity toUnwrappedModel(
		AssociationActivity associationActivity) {
		if (associationActivity instanceof AssociationActivityImpl) {
			return associationActivity;
		}

		AssociationActivityImpl associationActivityImpl = new AssociationActivityImpl();

		associationActivityImpl.setNew(associationActivity.isNew());
		associationActivityImpl.setPrimaryKey(associationActivity.getPrimaryKey());

		associationActivityImpl.setUuid(associationActivity.getUuid());
		associationActivityImpl.setAssociationActivityId(associationActivity.getAssociationActivityId());
		associationActivityImpl.setGroupId(associationActivity.getGroupId());
		associationActivityImpl.setCompanyId(associationActivity.getCompanyId());
		associationActivityImpl.setUserId(associationActivity.getUserId());
		associationActivityImpl.setUserName(associationActivity.getUserName());
		associationActivityImpl.setCreateDate(associationActivity.getCreateDate());
		associationActivityImpl.setModifiedDate(associationActivity.getModifiedDate());
		associationActivityImpl.setStatus(associationActivity.getStatus());
		associationActivityImpl.setStatusByUserId(associationActivity.getStatusByUserId());
		associationActivityImpl.setStatusByUserName(associationActivity.getStatusByUserName());
		associationActivityImpl.setStatusDate(associationActivity.getStatusDate());
		associationActivityImpl.setAssociationId(associationActivity.getAssociationId());

		return associationActivityImpl;
	}

	/**
	 * Returns the association activity with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the association activity
	 * @return the association activity
	 * @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	 */
	@Override
	public AssociationActivity findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAssociationActivityException {
		AssociationActivity associationActivity = fetchByPrimaryKey(primaryKey);

		if (associationActivity == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAssociationActivityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return associationActivity;
	}

	/**
	 * Returns the association activity with the primary key or throws a {@link NoSuchAssociationActivityException} if it could not be found.
	 *
	 * @param associationActivityId the primary key of the association activity
	 * @return the association activity
	 * @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	 */
	@Override
	public AssociationActivity findByPrimaryKey(long associationActivityId)
		throws NoSuchAssociationActivityException {
		return findByPrimaryKey((Serializable)associationActivityId);
	}

	/**
	 * Returns the association activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the association activity
	 * @return the association activity, or <code>null</code> if a association activity with the primary key could not be found
	 */
	@Override
	public AssociationActivity fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
				AssociationActivityImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AssociationActivity associationActivity = (AssociationActivity)serializable;

		if (associationActivity == null) {
			Session session = null;

			try {
				session = openSession();

				associationActivity = (AssociationActivity)session.get(AssociationActivityImpl.class,
						primaryKey);

				if (associationActivity != null) {
					cacheResult(associationActivity);
				}
				else {
					entityCache.putResult(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
						AssociationActivityImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
					AssociationActivityImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return associationActivity;
	}

	/**
	 * Returns the association activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param associationActivityId the primary key of the association activity
	 * @return the association activity, or <code>null</code> if a association activity with the primary key could not be found
	 */
	@Override
	public AssociationActivity fetchByPrimaryKey(long associationActivityId) {
		return fetchByPrimaryKey((Serializable)associationActivityId);
	}

	@Override
	public Map<Serializable, AssociationActivity> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AssociationActivity> map = new HashMap<Serializable, AssociationActivity>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AssociationActivity associationActivity = fetchByPrimaryKey(primaryKey);

			if (associationActivity != null) {
				map.put(primaryKey, associationActivity);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
					AssociationActivityImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AssociationActivity)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ASSOCIATIONACTIVITY_WHERE_PKS_IN);

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

			for (AssociationActivity associationActivity : (List<AssociationActivity>)q.list()) {
				map.put(associationActivity.getPrimaryKeyObj(),
					associationActivity);

				cacheResult(associationActivity);

				uncachedPrimaryKeys.remove(associationActivity.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AssociationActivityModelImpl.ENTITY_CACHE_ENABLED,
					AssociationActivityImpl.class, primaryKey, nullModel);
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
	 * Returns all the association activities.
	 *
	 * @return the association activities
	 */
	@Override
	public List<AssociationActivity> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the association activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @return the range of association activities
	 */
	@Override
	public List<AssociationActivity> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the association activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of association activities
	 */
	@Override
	public List<AssociationActivity> findAll(int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the association activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of association activities
	 * @param end the upper bound of the range of association activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of association activities
	 */
	@Override
	public List<AssociationActivity> findAll(int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator,
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

		List<AssociationActivity> list = null;

		if (retrieveFromCache) {
			list = (List<AssociationActivity>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ASSOCIATIONACTIVITY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSOCIATIONACTIVITY;

				if (pagination) {
					sql = sql.concat(AssociationActivityModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssociationActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssociationActivity>)QueryUtil.list(q,
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
	 * Removes all the association activities from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AssociationActivity associationActivity : findAll()) {
			remove(associationActivity);
		}
	}

	/**
	 * Returns the number of association activities.
	 *
	 * @return the number of association activities
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSOCIATIONACTIVITY);

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
		return AssociationActivityModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the association activity persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AssociationActivityImpl.class.getName());
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
	private static final String _SQL_SELECT_ASSOCIATIONACTIVITY = "SELECT associationActivity FROM AssociationActivity associationActivity";
	private static final String _SQL_SELECT_ASSOCIATIONACTIVITY_WHERE_PKS_IN = "SELECT associationActivity FROM AssociationActivity associationActivity WHERE associationActivityId IN (";
	private static final String _SQL_SELECT_ASSOCIATIONACTIVITY_WHERE = "SELECT associationActivity FROM AssociationActivity associationActivity WHERE ";
	private static final String _SQL_COUNT_ASSOCIATIONACTIVITY = "SELECT COUNT(associationActivity) FROM AssociationActivity associationActivity";
	private static final String _SQL_COUNT_ASSOCIATIONACTIVITY_WHERE = "SELECT COUNT(associationActivity) FROM AssociationActivity associationActivity WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "associationActivity.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssociationActivity exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssociationActivity exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AssociationActivityPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}