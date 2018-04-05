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

package eu.strasbourg.service.place.service.persistence.impl;

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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.place.exception.NoSuchSlotException;
import eu.strasbourg.service.place.model.Slot;
import eu.strasbourg.service.place.model.impl.SlotImpl;
import eu.strasbourg.service.place.model.impl.SlotModelImpl;
import eu.strasbourg.service.place.service.persistence.SlotPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the slot service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see SlotPersistence
 * @see eu.strasbourg.service.place.service.persistence.SlotUtil
 * @generated
 */
@ProviderType
public class SlotPersistenceImpl extends BasePersistenceImpl<Slot>
	implements SlotPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SlotUtil} to access the slot persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SlotImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotModelImpl.FINDER_CACHE_ENABLED, SlotImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotModelImpl.FINDER_CACHE_ENABLED, SlotImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotModelImpl.FINDER_CACHE_ENABLED, SlotImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotModelImpl.FINDER_CACHE_ENABLED, SlotImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SlotModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the slots where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching slots
	 */
	@Override
	public List<Slot> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the slots where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of slots
	 * @param end the upper bound of the range of slots (not inclusive)
	 * @return the range of matching slots
	 */
	@Override
	public List<Slot> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the slots where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of slots
	 * @param end the upper bound of the range of slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching slots
	 */
	@Override
	public List<Slot> findByUuid(String uuid, int start, int end,
		OrderByComparator<Slot> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the slots where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of slots
	 * @param end the upper bound of the range of slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching slots
	 */
	@Override
	public List<Slot> findByUuid(String uuid, int start, int end,
		OrderByComparator<Slot> orderByComparator, boolean retrieveFromCache) {
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

		List<Slot> list = null;

		if (retrieveFromCache) {
			list = (List<Slot>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Slot slot : list) {
					if (!Objects.equals(uuid, slot.getUuid())) {
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

			query.append(_SQL_SELECT_SLOT_WHERE);

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
				query.append(SlotModelImpl.ORDER_BY_JPQL);
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
					list = (List<Slot>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Slot>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first slot in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching slot
	 * @throws NoSuchSlotException if a matching slot could not be found
	 */
	@Override
	public Slot findByUuid_First(String uuid,
		OrderByComparator<Slot> orderByComparator) throws NoSuchSlotException {
		Slot slot = fetchByUuid_First(uuid, orderByComparator);

		if (slot != null) {
			return slot;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSlotException(msg.toString());
	}

	/**
	 * Returns the first slot in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching slot, or <code>null</code> if a matching slot could not be found
	 */
	@Override
	public Slot fetchByUuid_First(String uuid,
		OrderByComparator<Slot> orderByComparator) {
		List<Slot> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last slot in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching slot
	 * @throws NoSuchSlotException if a matching slot could not be found
	 */
	@Override
	public Slot findByUuid_Last(String uuid,
		OrderByComparator<Slot> orderByComparator) throws NoSuchSlotException {
		Slot slot = fetchByUuid_Last(uuid, orderByComparator);

		if (slot != null) {
			return slot;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSlotException(msg.toString());
	}

	/**
	 * Returns the last slot in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching slot, or <code>null</code> if a matching slot could not be found
	 */
	@Override
	public Slot fetchByUuid_Last(String uuid,
		OrderByComparator<Slot> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Slot> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the slots before and after the current slot in the ordered set where uuid = &#63;.
	 *
	 * @param slotId the primary key of the current slot
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next slot
	 * @throws NoSuchSlotException if a slot with the primary key could not be found
	 */
	@Override
	public Slot[] findByUuid_PrevAndNext(long slotId, String uuid,
		OrderByComparator<Slot> orderByComparator) throws NoSuchSlotException {
		Slot slot = findByPrimaryKey(slotId);

		Session session = null;

		try {
			session = openSession();

			Slot[] array = new SlotImpl[3];

			array[0] = getByUuid_PrevAndNext(session, slot, uuid,
					orderByComparator, true);

			array[1] = slot;

			array[2] = getByUuid_PrevAndNext(session, slot, uuid,
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

	protected Slot getByUuid_PrevAndNext(Session session, Slot slot,
		String uuid, OrderByComparator<Slot> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SLOT_WHERE);

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
			query.append(SlotModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(slot);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Slot> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the slots where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Slot slot : findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(slot);
		}
	}

	/**
	 * Returns the number of slots where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching slots
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SLOT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "slot.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "slot.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(slot.uuid IS NULL OR slot.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PERIODID = new FinderPath(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotModelImpl.FINDER_CACHE_ENABLED, SlotImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPeriodId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIODID =
		new FinderPath(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotModelImpl.FINDER_CACHE_ENABLED, SlotImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPeriodId",
			new String[] { Long.class.getName() },
			SlotModelImpl.PERIODID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PERIODID = new FinderPath(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPeriodId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the slots where periodId = &#63;.
	 *
	 * @param periodId the period ID
	 * @return the matching slots
	 */
	@Override
	public List<Slot> findByPeriodId(long periodId) {
		return findByPeriodId(periodId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the slots where periodId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param periodId the period ID
	 * @param start the lower bound of the range of slots
	 * @param end the upper bound of the range of slots (not inclusive)
	 * @return the range of matching slots
	 */
	@Override
	public List<Slot> findByPeriodId(long periodId, int start, int end) {
		return findByPeriodId(periodId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the slots where periodId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param periodId the period ID
	 * @param start the lower bound of the range of slots
	 * @param end the upper bound of the range of slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching slots
	 */
	@Override
	public List<Slot> findByPeriodId(long periodId, int start, int end,
		OrderByComparator<Slot> orderByComparator) {
		return findByPeriodId(periodId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the slots where periodId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param periodId the period ID
	 * @param start the lower bound of the range of slots
	 * @param end the upper bound of the range of slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching slots
	 */
	@Override
	public List<Slot> findByPeriodId(long periodId, int start, int end,
		OrderByComparator<Slot> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIODID;
			finderArgs = new Object[] { periodId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PERIODID;
			finderArgs = new Object[] { periodId, start, end, orderByComparator };
		}

		List<Slot> list = null;

		if (retrieveFromCache) {
			list = (List<Slot>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Slot slot : list) {
					if ((periodId != slot.getPeriodId())) {
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

			query.append(_SQL_SELECT_SLOT_WHERE);

			query.append(_FINDER_COLUMN_PERIODID_PERIODID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SlotModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(periodId);

				if (!pagination) {
					list = (List<Slot>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Slot>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first slot in the ordered set where periodId = &#63;.
	 *
	 * @param periodId the period ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching slot
	 * @throws NoSuchSlotException if a matching slot could not be found
	 */
	@Override
	public Slot findByPeriodId_First(long periodId,
		OrderByComparator<Slot> orderByComparator) throws NoSuchSlotException {
		Slot slot = fetchByPeriodId_First(periodId, orderByComparator);

		if (slot != null) {
			return slot;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("periodId=");
		msg.append(periodId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSlotException(msg.toString());
	}

	/**
	 * Returns the first slot in the ordered set where periodId = &#63;.
	 *
	 * @param periodId the period ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching slot, or <code>null</code> if a matching slot could not be found
	 */
	@Override
	public Slot fetchByPeriodId_First(long periodId,
		OrderByComparator<Slot> orderByComparator) {
		List<Slot> list = findByPeriodId(periodId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last slot in the ordered set where periodId = &#63;.
	 *
	 * @param periodId the period ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching slot
	 * @throws NoSuchSlotException if a matching slot could not be found
	 */
	@Override
	public Slot findByPeriodId_Last(long periodId,
		OrderByComparator<Slot> orderByComparator) throws NoSuchSlotException {
		Slot slot = fetchByPeriodId_Last(periodId, orderByComparator);

		if (slot != null) {
			return slot;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("periodId=");
		msg.append(periodId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSlotException(msg.toString());
	}

	/**
	 * Returns the last slot in the ordered set where periodId = &#63;.
	 *
	 * @param periodId the period ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching slot, or <code>null</code> if a matching slot could not be found
	 */
	@Override
	public Slot fetchByPeriodId_Last(long periodId,
		OrderByComparator<Slot> orderByComparator) {
		int count = countByPeriodId(periodId);

		if (count == 0) {
			return null;
		}

		List<Slot> list = findByPeriodId(periodId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the slots before and after the current slot in the ordered set where periodId = &#63;.
	 *
	 * @param slotId the primary key of the current slot
	 * @param periodId the period ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next slot
	 * @throws NoSuchSlotException if a slot with the primary key could not be found
	 */
	@Override
	public Slot[] findByPeriodId_PrevAndNext(long slotId, long periodId,
		OrderByComparator<Slot> orderByComparator) throws NoSuchSlotException {
		Slot slot = findByPrimaryKey(slotId);

		Session session = null;

		try {
			session = openSession();

			Slot[] array = new SlotImpl[3];

			array[0] = getByPeriodId_PrevAndNext(session, slot, periodId,
					orderByComparator, true);

			array[1] = slot;

			array[2] = getByPeriodId_PrevAndNext(session, slot, periodId,
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

	protected Slot getByPeriodId_PrevAndNext(Session session, Slot slot,
		long periodId, OrderByComparator<Slot> orderByComparator,
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

		query.append(_SQL_SELECT_SLOT_WHERE);

		query.append(_FINDER_COLUMN_PERIODID_PERIODID_2);

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
			query.append(SlotModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(periodId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(slot);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Slot> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the slots where periodId = &#63; from the database.
	 *
	 * @param periodId the period ID
	 */
	@Override
	public void removeByPeriodId(long periodId) {
		for (Slot slot : findByPeriodId(periodId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(slot);
		}
	}

	/**
	 * Returns the number of slots where periodId = &#63;.
	 *
	 * @param periodId the period ID
	 * @return the number of matching slots
	 */
	@Override
	public int countByPeriodId(long periodId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PERIODID;

		Object[] finderArgs = new Object[] { periodId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SLOT_WHERE);

			query.append(_FINDER_COLUMN_PERIODID_PERIODID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(periodId);

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

	private static final String _FINDER_COLUMN_PERIODID_PERIODID_2 = "slot.periodId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUBPLACEID =
		new FinderPath(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotModelImpl.FINDER_CACHE_ENABLED, SlotImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySubPlaceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPLACEID =
		new FinderPath(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotModelImpl.FINDER_CACHE_ENABLED, SlotImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySubPlaceId",
			new String[] { Long.class.getName() },
			SlotModelImpl.SUBPLACEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUBPLACEID = new FinderPath(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySubPlaceId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the slots where subPlaceId = &#63;.
	 *
	 * @param subPlaceId the sub place ID
	 * @return the matching slots
	 */
	@Override
	public List<Slot> findBySubPlaceId(long subPlaceId) {
		return findBySubPlaceId(subPlaceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the slots where subPlaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param subPlaceId the sub place ID
	 * @param start the lower bound of the range of slots
	 * @param end the upper bound of the range of slots (not inclusive)
	 * @return the range of matching slots
	 */
	@Override
	public List<Slot> findBySubPlaceId(long subPlaceId, int start, int end) {
		return findBySubPlaceId(subPlaceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the slots where subPlaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param subPlaceId the sub place ID
	 * @param start the lower bound of the range of slots
	 * @param end the upper bound of the range of slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching slots
	 */
	@Override
	public List<Slot> findBySubPlaceId(long subPlaceId, int start, int end,
		OrderByComparator<Slot> orderByComparator) {
		return findBySubPlaceId(subPlaceId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the slots where subPlaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param subPlaceId the sub place ID
	 * @param start the lower bound of the range of slots
	 * @param end the upper bound of the range of slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching slots
	 */
	@Override
	public List<Slot> findBySubPlaceId(long subPlaceId, int start, int end,
		OrderByComparator<Slot> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPLACEID;
			finderArgs = new Object[] { subPlaceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUBPLACEID;
			finderArgs = new Object[] { subPlaceId, start, end, orderByComparator };
		}

		List<Slot> list = null;

		if (retrieveFromCache) {
			list = (List<Slot>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Slot slot : list) {
					if ((subPlaceId != slot.getSubPlaceId())) {
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

			query.append(_SQL_SELECT_SLOT_WHERE);

			query.append(_FINDER_COLUMN_SUBPLACEID_SUBPLACEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SlotModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(subPlaceId);

				if (!pagination) {
					list = (List<Slot>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Slot>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first slot in the ordered set where subPlaceId = &#63;.
	 *
	 * @param subPlaceId the sub place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching slot
	 * @throws NoSuchSlotException if a matching slot could not be found
	 */
	@Override
	public Slot findBySubPlaceId_First(long subPlaceId,
		OrderByComparator<Slot> orderByComparator) throws NoSuchSlotException {
		Slot slot = fetchBySubPlaceId_First(subPlaceId, orderByComparator);

		if (slot != null) {
			return slot;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("subPlaceId=");
		msg.append(subPlaceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSlotException(msg.toString());
	}

	/**
	 * Returns the first slot in the ordered set where subPlaceId = &#63;.
	 *
	 * @param subPlaceId the sub place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching slot, or <code>null</code> if a matching slot could not be found
	 */
	@Override
	public Slot fetchBySubPlaceId_First(long subPlaceId,
		OrderByComparator<Slot> orderByComparator) {
		List<Slot> list = findBySubPlaceId(subPlaceId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last slot in the ordered set where subPlaceId = &#63;.
	 *
	 * @param subPlaceId the sub place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching slot
	 * @throws NoSuchSlotException if a matching slot could not be found
	 */
	@Override
	public Slot findBySubPlaceId_Last(long subPlaceId,
		OrderByComparator<Slot> orderByComparator) throws NoSuchSlotException {
		Slot slot = fetchBySubPlaceId_Last(subPlaceId, orderByComparator);

		if (slot != null) {
			return slot;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("subPlaceId=");
		msg.append(subPlaceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSlotException(msg.toString());
	}

	/**
	 * Returns the last slot in the ordered set where subPlaceId = &#63;.
	 *
	 * @param subPlaceId the sub place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching slot, or <code>null</code> if a matching slot could not be found
	 */
	@Override
	public Slot fetchBySubPlaceId_Last(long subPlaceId,
		OrderByComparator<Slot> orderByComparator) {
		int count = countBySubPlaceId(subPlaceId);

		if (count == 0) {
			return null;
		}

		List<Slot> list = findBySubPlaceId(subPlaceId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the slots before and after the current slot in the ordered set where subPlaceId = &#63;.
	 *
	 * @param slotId the primary key of the current slot
	 * @param subPlaceId the sub place ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next slot
	 * @throws NoSuchSlotException if a slot with the primary key could not be found
	 */
	@Override
	public Slot[] findBySubPlaceId_PrevAndNext(long slotId, long subPlaceId,
		OrderByComparator<Slot> orderByComparator) throws NoSuchSlotException {
		Slot slot = findByPrimaryKey(slotId);

		Session session = null;

		try {
			session = openSession();

			Slot[] array = new SlotImpl[3];

			array[0] = getBySubPlaceId_PrevAndNext(session, slot, subPlaceId,
					orderByComparator, true);

			array[1] = slot;

			array[2] = getBySubPlaceId_PrevAndNext(session, slot, subPlaceId,
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

	protected Slot getBySubPlaceId_PrevAndNext(Session session, Slot slot,
		long subPlaceId, OrderByComparator<Slot> orderByComparator,
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

		query.append(_SQL_SELECT_SLOT_WHERE);

		query.append(_FINDER_COLUMN_SUBPLACEID_SUBPLACEID_2);

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
			query.append(SlotModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(subPlaceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(slot);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Slot> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the slots where subPlaceId = &#63; from the database.
	 *
	 * @param subPlaceId the sub place ID
	 */
	@Override
	public void removeBySubPlaceId(long subPlaceId) {
		for (Slot slot : findBySubPlaceId(subPlaceId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(slot);
		}
	}

	/**
	 * Returns the number of slots where subPlaceId = &#63;.
	 *
	 * @param subPlaceId the sub place ID
	 * @return the number of matching slots
	 */
	@Override
	public int countBySubPlaceId(long subPlaceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SUBPLACEID;

		Object[] finderArgs = new Object[] { subPlaceId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SLOT_WHERE);

			query.append(_FINDER_COLUMN_SUBPLACEID_SUBPLACEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(subPlaceId);

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

	private static final String _FINDER_COLUMN_SUBPLACEID_SUBPLACEID_2 = "slot.subPlaceId = ?";

	public SlotPersistenceImpl() {
		setModelClass(Slot.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("comment", "comment_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the slot in the entity cache if it is enabled.
	 *
	 * @param slot the slot
	 */
	@Override
	public void cacheResult(Slot slot) {
		entityCache.putResult(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotImpl.class, slot.getPrimaryKey(), slot);

		slot.resetOriginalValues();
	}

	/**
	 * Caches the slots in the entity cache if it is enabled.
	 *
	 * @param slots the slots
	 */
	@Override
	public void cacheResult(List<Slot> slots) {
		for (Slot slot : slots) {
			if (entityCache.getResult(SlotModelImpl.ENTITY_CACHE_ENABLED,
						SlotImpl.class, slot.getPrimaryKey()) == null) {
				cacheResult(slot);
			}
			else {
				slot.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all slots.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SlotImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the slot.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Slot slot) {
		entityCache.removeResult(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotImpl.class, slot.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Slot> slots) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Slot slot : slots) {
			entityCache.removeResult(SlotModelImpl.ENTITY_CACHE_ENABLED,
				SlotImpl.class, slot.getPrimaryKey());
		}
	}

	/**
	 * Creates a new slot with the primary key. Does not add the slot to the database.
	 *
	 * @param slotId the primary key for the new slot
	 * @return the new slot
	 */
	@Override
	public Slot create(long slotId) {
		Slot slot = new SlotImpl();

		slot.setNew(true);
		slot.setPrimaryKey(slotId);

		String uuid = PortalUUIDUtil.generate();

		slot.setUuid(uuid);

		return slot;
	}

	/**
	 * Removes the slot with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param slotId the primary key of the slot
	 * @return the slot that was removed
	 * @throws NoSuchSlotException if a slot with the primary key could not be found
	 */
	@Override
	public Slot remove(long slotId) throws NoSuchSlotException {
		return remove((Serializable)slotId);
	}

	/**
	 * Removes the slot with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the slot
	 * @return the slot that was removed
	 * @throws NoSuchSlotException if a slot with the primary key could not be found
	 */
	@Override
	public Slot remove(Serializable primaryKey) throws NoSuchSlotException {
		Session session = null;

		try {
			session = openSession();

			Slot slot = (Slot)session.get(SlotImpl.class, primaryKey);

			if (slot == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSlotException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(slot);
		}
		catch (NoSuchSlotException nsee) {
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
	protected Slot removeImpl(Slot slot) {
		slot = toUnwrappedModel(slot);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(slot)) {
				slot = (Slot)session.get(SlotImpl.class, slot.getPrimaryKeyObj());
			}

			if (slot != null) {
				session.delete(slot);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (slot != null) {
			clearCache(slot);
		}

		return slot;
	}

	@Override
	public Slot updateImpl(Slot slot) {
		slot = toUnwrappedModel(slot);

		boolean isNew = slot.isNew();

		SlotModelImpl slotModelImpl = (SlotModelImpl)slot;

		if (Validator.isNull(slot.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			slot.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (slot.isNew()) {
				session.save(slot);

				slot.setNew(false);
			}
			else {
				slot = (Slot)session.merge(slot);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SlotModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { slotModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { slotModelImpl.getPeriodId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PERIODID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIODID,
				args);

			args = new Object[] { slotModelImpl.getSubPlaceId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SUBPLACEID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPLACEID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((slotModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { slotModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { slotModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((slotModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIODID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { slotModelImpl.getOriginalPeriodId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PERIODID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIODID,
					args);

				args = new Object[] { slotModelImpl.getPeriodId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PERIODID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIODID,
					args);
			}

			if ((slotModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPLACEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						slotModelImpl.getOriginalSubPlaceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUBPLACEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPLACEID,
					args);

				args = new Object[] { slotModelImpl.getSubPlaceId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUBPLACEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPLACEID,
					args);
			}
		}

		entityCache.putResult(SlotModelImpl.ENTITY_CACHE_ENABLED,
			SlotImpl.class, slot.getPrimaryKey(), slot, false);

		slot.resetOriginalValues();

		return slot;
	}

	protected Slot toUnwrappedModel(Slot slot) {
		if (slot instanceof SlotImpl) {
			return slot;
		}

		SlotImpl slotImpl = new SlotImpl();

		slotImpl.setNew(slot.isNew());
		slotImpl.setPrimaryKey(slot.getPrimaryKey());

		slotImpl.setUuid(slot.getUuid());
		slotImpl.setSlotId(slot.getSlotId());
		slotImpl.setDayOfWeek(slot.getDayOfWeek());
		slotImpl.setStartHour(slot.getStartHour());
		slotImpl.setEndHour(slot.getEndHour());
		slotImpl.setComment(slot.getComment());
		slotImpl.setPeriodId(slot.getPeriodId());
		slotImpl.setSubPlaceId(slot.getSubPlaceId());

		return slotImpl;
	}

	/**
	 * Returns the slot with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the slot
	 * @return the slot
	 * @throws NoSuchSlotException if a slot with the primary key could not be found
	 */
	@Override
	public Slot findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSlotException {
		Slot slot = fetchByPrimaryKey(primaryKey);

		if (slot == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSlotException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return slot;
	}

	/**
	 * Returns the slot with the primary key or throws a {@link NoSuchSlotException} if it could not be found.
	 *
	 * @param slotId the primary key of the slot
	 * @return the slot
	 * @throws NoSuchSlotException if a slot with the primary key could not be found
	 */
	@Override
	public Slot findByPrimaryKey(long slotId) throws NoSuchSlotException {
		return findByPrimaryKey((Serializable)slotId);
	}

	/**
	 * Returns the slot with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the slot
	 * @return the slot, or <code>null</code> if a slot with the primary key could not be found
	 */
	@Override
	public Slot fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SlotModelImpl.ENTITY_CACHE_ENABLED,
				SlotImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Slot slot = (Slot)serializable;

		if (slot == null) {
			Session session = null;

			try {
				session = openSession();

				slot = (Slot)session.get(SlotImpl.class, primaryKey);

				if (slot != null) {
					cacheResult(slot);
				}
				else {
					entityCache.putResult(SlotModelImpl.ENTITY_CACHE_ENABLED,
						SlotImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SlotModelImpl.ENTITY_CACHE_ENABLED,
					SlotImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return slot;
	}

	/**
	 * Returns the slot with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param slotId the primary key of the slot
	 * @return the slot, or <code>null</code> if a slot with the primary key could not be found
	 */
	@Override
	public Slot fetchByPrimaryKey(long slotId) {
		return fetchByPrimaryKey((Serializable)slotId);
	}

	@Override
	public Map<Serializable, Slot> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Slot> map = new HashMap<Serializable, Slot>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Slot slot = fetchByPrimaryKey(primaryKey);

			if (slot != null) {
				map.put(primaryKey, slot);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SlotModelImpl.ENTITY_CACHE_ENABLED,
					SlotImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Slot)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SLOT_WHERE_PKS_IN);

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

			for (Slot slot : (List<Slot>)q.list()) {
				map.put(slot.getPrimaryKeyObj(), slot);

				cacheResult(slot);

				uncachedPrimaryKeys.remove(slot.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SlotModelImpl.ENTITY_CACHE_ENABLED,
					SlotImpl.class, primaryKey, nullModel);
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
	 * Returns all the slots.
	 *
	 * @return the slots
	 */
	@Override
	public List<Slot> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the slots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of slots
	 * @param end the upper bound of the range of slots (not inclusive)
	 * @return the range of slots
	 */
	@Override
	public List<Slot> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the slots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of slots
	 * @param end the upper bound of the range of slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of slots
	 */
	@Override
	public List<Slot> findAll(int start, int end,
		OrderByComparator<Slot> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the slots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of slots
	 * @param end the upper bound of the range of slots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of slots
	 */
	@Override
	public List<Slot> findAll(int start, int end,
		OrderByComparator<Slot> orderByComparator, boolean retrieveFromCache) {
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

		List<Slot> list = null;

		if (retrieveFromCache) {
			list = (List<Slot>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SLOT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SLOT;

				if (pagination) {
					sql = sql.concat(SlotModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Slot>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Slot>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the slots from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Slot slot : findAll()) {
			remove(slot);
		}
	}

	/**
	 * Returns the number of slots.
	 *
	 * @return the number of slots
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SLOT);

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
		return SlotModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the slot persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SlotImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SLOT = "SELECT slot FROM Slot slot";
	private static final String _SQL_SELECT_SLOT_WHERE_PKS_IN = "SELECT slot FROM Slot slot WHERE slotId IN (";
	private static final String _SQL_SELECT_SLOT_WHERE = "SELECT slot FROM Slot slot WHERE ";
	private static final String _SQL_COUNT_SLOT = "SELECT COUNT(slot) FROM Slot slot";
	private static final String _SQL_COUNT_SLOT_WHERE = "SELECT COUNT(slot) FROM Slot slot WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "slot.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Slot exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Slot exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SlotPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "comment"
			});
}