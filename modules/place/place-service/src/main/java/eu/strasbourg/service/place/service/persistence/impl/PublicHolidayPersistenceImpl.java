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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.place.exception.NoSuchPublicHolidayException;
import eu.strasbourg.service.place.model.PublicHoliday;
import eu.strasbourg.service.place.model.impl.PublicHolidayImpl;
import eu.strasbourg.service.place.model.impl.PublicHolidayModelImpl;
import eu.strasbourg.service.place.service.persistence.PublicHolidayPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the public holiday service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @generated
 */
@ProviderType
public class PublicHolidayPersistenceImpl
	extends BasePersistenceImpl<PublicHoliday>
	implements PublicHolidayPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>PublicHolidayUtil</code> to access the public holiday persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		PublicHolidayImpl.class.getName();

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
	 * Returns all the public holidaies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching public holidaies
	 */
	@Override
	public List<PublicHoliday> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the public holidaies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @return the range of matching public holidaies
	 */
	@Override
	public List<PublicHoliday> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the public holidaies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching public holidaies
	 */
	@Override
	public List<PublicHoliday> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<PublicHoliday> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the public holidaies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching public holidaies
	 */
	@Override
	public List<PublicHoliday> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<PublicHoliday> orderByComparator,
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

		List<PublicHoliday> list = null;

		if (retrieveFromCache) {
			list = (List<PublicHoliday>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PublicHoliday publicHoliday : list) {
					if (!uuid.equals(publicHoliday.getUuid())) {
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

			query.append(_SQL_SELECT_PUBLICHOLIDAY_WHERE);

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
				query.append(PublicHolidayModelImpl.ORDER_BY_JPQL);
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
					list = (List<PublicHoliday>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PublicHoliday>)QueryUtil.list(
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
	 * Returns the first public holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching public holiday
	 * @throws NoSuchPublicHolidayException if a matching public holiday could not be found
	 */
	@Override
	public PublicHoliday findByUuid_First(
			String uuid, OrderByComparator<PublicHoliday> orderByComparator)
		throws NoSuchPublicHolidayException {

		PublicHoliday publicHoliday = fetchByUuid_First(
			uuid, orderByComparator);

		if (publicHoliday != null) {
			return publicHoliday;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPublicHolidayException(msg.toString());
	}

	/**
	 * Returns the first public holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching public holiday, or <code>null</code> if a matching public holiday could not be found
	 */
	@Override
	public PublicHoliday fetchByUuid_First(
		String uuid, OrderByComparator<PublicHoliday> orderByComparator) {

		List<PublicHoliday> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last public holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching public holiday
	 * @throws NoSuchPublicHolidayException if a matching public holiday could not be found
	 */
	@Override
	public PublicHoliday findByUuid_Last(
			String uuid, OrderByComparator<PublicHoliday> orderByComparator)
		throws NoSuchPublicHolidayException {

		PublicHoliday publicHoliday = fetchByUuid_Last(uuid, orderByComparator);

		if (publicHoliday != null) {
			return publicHoliday;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPublicHolidayException(msg.toString());
	}

	/**
	 * Returns the last public holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching public holiday, or <code>null</code> if a matching public holiday could not be found
	 */
	@Override
	public PublicHoliday fetchByUuid_Last(
		String uuid, OrderByComparator<PublicHoliday> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PublicHoliday> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the public holidaies before and after the current public holiday in the ordered set where uuid = &#63;.
	 *
	 * @param publicHolidayId the primary key of the current public holiday
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next public holiday
	 * @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	 */
	@Override
	public PublicHoliday[] findByUuid_PrevAndNext(
			long publicHolidayId, String uuid,
			OrderByComparator<PublicHoliday> orderByComparator)
		throws NoSuchPublicHolidayException {

		uuid = Objects.toString(uuid, "");

		PublicHoliday publicHoliday = findByPrimaryKey(publicHolidayId);

		Session session = null;

		try {
			session = openSession();

			PublicHoliday[] array = new PublicHolidayImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, publicHoliday, uuid, orderByComparator, true);

			array[1] = publicHoliday;

			array[2] = getByUuid_PrevAndNext(
				session, publicHoliday, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PublicHoliday getByUuid_PrevAndNext(
		Session session, PublicHoliday publicHoliday, String uuid,
		OrderByComparator<PublicHoliday> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PUBLICHOLIDAY_WHERE);

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
			query.append(PublicHolidayModelImpl.ORDER_BY_JPQL);
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
						publicHoliday)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<PublicHoliday> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the public holidaies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PublicHoliday publicHoliday :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(publicHoliday);
		}
	}

	/**
	 * Returns the number of public holidaies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching public holidaies
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PUBLICHOLIDAY_WHERE);

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
		"publicHoliday.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(publicHoliday.uuid IS NULL OR publicHoliday.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByRecurrent;
	private FinderPath _finderPathWithoutPaginationFindByRecurrent;
	private FinderPath _finderPathCountByRecurrent;

	/**
	 * Returns all the public holidaies where recurrent = &#63;.
	 *
	 * @param recurrent the recurrent
	 * @return the matching public holidaies
	 */
	@Override
	public List<PublicHoliday> findByRecurrent(boolean recurrent) {
		return findByRecurrent(
			recurrent, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the public holidaies where recurrent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param recurrent the recurrent
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @return the range of matching public holidaies
	 */
	@Override
	public List<PublicHoliday> findByRecurrent(
		boolean recurrent, int start, int end) {

		return findByRecurrent(recurrent, start, end, null);
	}

	/**
	 * Returns an ordered range of all the public holidaies where recurrent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param recurrent the recurrent
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching public holidaies
	 */
	@Override
	public List<PublicHoliday> findByRecurrent(
		boolean recurrent, int start, int end,
		OrderByComparator<PublicHoliday> orderByComparator) {

		return findByRecurrent(recurrent, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the public holidaies where recurrent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param recurrent the recurrent
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching public holidaies
	 */
	@Override
	public List<PublicHoliday> findByRecurrent(
		boolean recurrent, int start, int end,
		OrderByComparator<PublicHoliday> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByRecurrent;
			finderArgs = new Object[] {recurrent};
		}
		else {
			finderPath = _finderPathWithPaginationFindByRecurrent;
			finderArgs = new Object[] {
				recurrent, start, end, orderByComparator
			};
		}

		List<PublicHoliday> list = null;

		if (retrieveFromCache) {
			list = (List<PublicHoliday>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PublicHoliday publicHoliday : list) {
					if ((recurrent != publicHoliday.isRecurrent())) {
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

			query.append(_SQL_SELECT_PUBLICHOLIDAY_WHERE);

			query.append(_FINDER_COLUMN_RECURRENT_RECURRENT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(PublicHolidayModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(recurrent);

				if (!pagination) {
					list = (List<PublicHoliday>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PublicHoliday>)QueryUtil.list(
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
	 * Returns the first public holiday in the ordered set where recurrent = &#63;.
	 *
	 * @param recurrent the recurrent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching public holiday
	 * @throws NoSuchPublicHolidayException if a matching public holiday could not be found
	 */
	@Override
	public PublicHoliday findByRecurrent_First(
			boolean recurrent,
			OrderByComparator<PublicHoliday> orderByComparator)
		throws NoSuchPublicHolidayException {

		PublicHoliday publicHoliday = fetchByRecurrent_First(
			recurrent, orderByComparator);

		if (publicHoliday != null) {
			return publicHoliday;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("recurrent=");
		msg.append(recurrent);

		msg.append("}");

		throw new NoSuchPublicHolidayException(msg.toString());
	}

	/**
	 * Returns the first public holiday in the ordered set where recurrent = &#63;.
	 *
	 * @param recurrent the recurrent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching public holiday, or <code>null</code> if a matching public holiday could not be found
	 */
	@Override
	public PublicHoliday fetchByRecurrent_First(
		boolean recurrent, OrderByComparator<PublicHoliday> orderByComparator) {

		List<PublicHoliday> list = findByRecurrent(
			recurrent, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last public holiday in the ordered set where recurrent = &#63;.
	 *
	 * @param recurrent the recurrent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching public holiday
	 * @throws NoSuchPublicHolidayException if a matching public holiday could not be found
	 */
	@Override
	public PublicHoliday findByRecurrent_Last(
			boolean recurrent,
			OrderByComparator<PublicHoliday> orderByComparator)
		throws NoSuchPublicHolidayException {

		PublicHoliday publicHoliday = fetchByRecurrent_Last(
			recurrent, orderByComparator);

		if (publicHoliday != null) {
			return publicHoliday;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("recurrent=");
		msg.append(recurrent);

		msg.append("}");

		throw new NoSuchPublicHolidayException(msg.toString());
	}

	/**
	 * Returns the last public holiday in the ordered set where recurrent = &#63;.
	 *
	 * @param recurrent the recurrent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching public holiday, or <code>null</code> if a matching public holiday could not be found
	 */
	@Override
	public PublicHoliday fetchByRecurrent_Last(
		boolean recurrent, OrderByComparator<PublicHoliday> orderByComparator) {

		int count = countByRecurrent(recurrent);

		if (count == 0) {
			return null;
		}

		List<PublicHoliday> list = findByRecurrent(
			recurrent, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the public holidaies before and after the current public holiday in the ordered set where recurrent = &#63;.
	 *
	 * @param publicHolidayId the primary key of the current public holiday
	 * @param recurrent the recurrent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next public holiday
	 * @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	 */
	@Override
	public PublicHoliday[] findByRecurrent_PrevAndNext(
			long publicHolidayId, boolean recurrent,
			OrderByComparator<PublicHoliday> orderByComparator)
		throws NoSuchPublicHolidayException {

		PublicHoliday publicHoliday = findByPrimaryKey(publicHolidayId);

		Session session = null;

		try {
			session = openSession();

			PublicHoliday[] array = new PublicHolidayImpl[3];

			array[0] = getByRecurrent_PrevAndNext(
				session, publicHoliday, recurrent, orderByComparator, true);

			array[1] = publicHoliday;

			array[2] = getByRecurrent_PrevAndNext(
				session, publicHoliday, recurrent, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PublicHoliday getByRecurrent_PrevAndNext(
		Session session, PublicHoliday publicHoliday, boolean recurrent,
		OrderByComparator<PublicHoliday> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PUBLICHOLIDAY_WHERE);

		query.append(_FINDER_COLUMN_RECURRENT_RECURRENT_2);

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
			query.append(PublicHolidayModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(recurrent);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						publicHoliday)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<PublicHoliday> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the public holidaies where recurrent = &#63; from the database.
	 *
	 * @param recurrent the recurrent
	 */
	@Override
	public void removeByRecurrent(boolean recurrent) {
		for (PublicHoliday publicHoliday :
				findByRecurrent(
					recurrent, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(publicHoliday);
		}
	}

	/**
	 * Returns the number of public holidaies where recurrent = &#63;.
	 *
	 * @param recurrent the recurrent
	 * @return the number of matching public holidaies
	 */
	@Override
	public int countByRecurrent(boolean recurrent) {
		FinderPath finderPath = _finderPathCountByRecurrent;

		Object[] finderArgs = new Object[] {recurrent};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PUBLICHOLIDAY_WHERE);

			query.append(_FINDER_COLUMN_RECURRENT_RECURRENT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(recurrent);

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

	private static final String _FINDER_COLUMN_RECURRENT_RECURRENT_2 =
		"publicHoliday.recurrent = ?";

	public PublicHolidayPersistenceImpl() {
		setModelClass(PublicHoliday.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("date", "date_");

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
	 * Caches the public holiday in the entity cache if it is enabled.
	 *
	 * @param publicHoliday the public holiday
	 */
	@Override
	public void cacheResult(PublicHoliday publicHoliday) {
		entityCache.putResult(
			PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
			PublicHolidayImpl.class, publicHoliday.getPrimaryKey(),
			publicHoliday);

		publicHoliday.resetOriginalValues();
	}

	/**
	 * Caches the public holidaies in the entity cache if it is enabled.
	 *
	 * @param publicHolidaies the public holidaies
	 */
	@Override
	public void cacheResult(List<PublicHoliday> publicHolidaies) {
		for (PublicHoliday publicHoliday : publicHolidaies) {
			if (entityCache.getResult(
					PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
					PublicHolidayImpl.class, publicHoliday.getPrimaryKey()) ==
						null) {

				cacheResult(publicHoliday);
			}
			else {
				publicHoliday.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all public holidaies.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PublicHolidayImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the public holiday.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PublicHoliday publicHoliday) {
		entityCache.removeResult(
			PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
			PublicHolidayImpl.class, publicHoliday.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<PublicHoliday> publicHolidaies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PublicHoliday publicHoliday : publicHolidaies) {
			entityCache.removeResult(
				PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
				PublicHolidayImpl.class, publicHoliday.getPrimaryKey());
		}
	}

	/**
	 * Creates a new public holiday with the primary key. Does not add the public holiday to the database.
	 *
	 * @param publicHolidayId the primary key for the new public holiday
	 * @return the new public holiday
	 */
	@Override
	public PublicHoliday create(long publicHolidayId) {
		PublicHoliday publicHoliday = new PublicHolidayImpl();

		publicHoliday.setNew(true);
		publicHoliday.setPrimaryKey(publicHolidayId);

		String uuid = PortalUUIDUtil.generate();

		publicHoliday.setUuid(uuid);

		return publicHoliday;
	}

	/**
	 * Removes the public holiday with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param publicHolidayId the primary key of the public holiday
	 * @return the public holiday that was removed
	 * @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	 */
	@Override
	public PublicHoliday remove(long publicHolidayId)
		throws NoSuchPublicHolidayException {

		return remove((Serializable)publicHolidayId);
	}

	/**
	 * Removes the public holiday with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the public holiday
	 * @return the public holiday that was removed
	 * @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	 */
	@Override
	public PublicHoliday remove(Serializable primaryKey)
		throws NoSuchPublicHolidayException {

		Session session = null;

		try {
			session = openSession();

			PublicHoliday publicHoliday = (PublicHoliday)session.get(
				PublicHolidayImpl.class, primaryKey);

			if (publicHoliday == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPublicHolidayException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(publicHoliday);
		}
		catch (NoSuchPublicHolidayException nsee) {
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
	protected PublicHoliday removeImpl(PublicHoliday publicHoliday) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(publicHoliday)) {
				publicHoliday = (PublicHoliday)session.get(
					PublicHolidayImpl.class, publicHoliday.getPrimaryKeyObj());
			}

			if (publicHoliday != null) {
				session.delete(publicHoliday);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (publicHoliday != null) {
			clearCache(publicHoliday);
		}

		return publicHoliday;
	}

	@Override
	public PublicHoliday updateImpl(PublicHoliday publicHoliday) {
		boolean isNew = publicHoliday.isNew();

		if (!(publicHoliday instanceof PublicHolidayModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(publicHoliday.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					publicHoliday);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in publicHoliday proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PublicHoliday implementation " +
					publicHoliday.getClass());
		}

		PublicHolidayModelImpl publicHolidayModelImpl =
			(PublicHolidayModelImpl)publicHoliday;

		if (Validator.isNull(publicHoliday.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			publicHoliday.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (publicHoliday.isNew()) {
				session.save(publicHoliday);

				publicHoliday.setNew(false);
			}
			else {
				publicHoliday = (PublicHoliday)session.merge(publicHoliday);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PublicHolidayModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {publicHolidayModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {publicHolidayModelImpl.isRecurrent()};

			finderCache.removeResult(_finderPathCountByRecurrent, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByRecurrent, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((publicHolidayModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					publicHolidayModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {publicHolidayModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((publicHolidayModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByRecurrent.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					publicHolidayModelImpl.getOriginalRecurrent()
				};

				finderCache.removeResult(_finderPathCountByRecurrent, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByRecurrent, args);

				args = new Object[] {publicHolidayModelImpl.isRecurrent()};

				finderCache.removeResult(_finderPathCountByRecurrent, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByRecurrent, args);
			}
		}

		entityCache.putResult(
			PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
			PublicHolidayImpl.class, publicHoliday.getPrimaryKey(),
			publicHoliday, false);

		publicHoliday.resetOriginalValues();

		return publicHoliday;
	}

	/**
	 * Returns the public holiday with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the public holiday
	 * @return the public holiday
	 * @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	 */
	@Override
	public PublicHoliday findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPublicHolidayException {

		PublicHoliday publicHoliday = fetchByPrimaryKey(primaryKey);

		if (publicHoliday == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPublicHolidayException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return publicHoliday;
	}

	/**
	 * Returns the public holiday with the primary key or throws a <code>NoSuchPublicHolidayException</code> if it could not be found.
	 *
	 * @param publicHolidayId the primary key of the public holiday
	 * @return the public holiday
	 * @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	 */
	@Override
	public PublicHoliday findByPrimaryKey(long publicHolidayId)
		throws NoSuchPublicHolidayException {

		return findByPrimaryKey((Serializable)publicHolidayId);
	}

	/**
	 * Returns the public holiday with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the public holiday
	 * @return the public holiday, or <code>null</code> if a public holiday with the primary key could not be found
	 */
	@Override
	public PublicHoliday fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
			PublicHolidayImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PublicHoliday publicHoliday = (PublicHoliday)serializable;

		if (publicHoliday == null) {
			Session session = null;

			try {
				session = openSession();

				publicHoliday = (PublicHoliday)session.get(
					PublicHolidayImpl.class, primaryKey);

				if (publicHoliday != null) {
					cacheResult(publicHoliday);
				}
				else {
					entityCache.putResult(
						PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
						PublicHolidayImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
					PublicHolidayImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return publicHoliday;
	}

	/**
	 * Returns the public holiday with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param publicHolidayId the primary key of the public holiday
	 * @return the public holiday, or <code>null</code> if a public holiday with the primary key could not be found
	 */
	@Override
	public PublicHoliday fetchByPrimaryKey(long publicHolidayId) {
		return fetchByPrimaryKey((Serializable)publicHolidayId);
	}

	@Override
	public Map<Serializable, PublicHoliday> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PublicHoliday> map =
			new HashMap<Serializable, PublicHoliday>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PublicHoliday publicHoliday = fetchByPrimaryKey(primaryKey);

			if (publicHoliday != null) {
				map.put(primaryKey, publicHoliday);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
				PublicHolidayImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PublicHoliday)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_PUBLICHOLIDAY_WHERE_PKS_IN);

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

			for (PublicHoliday publicHoliday : (List<PublicHoliday>)q.list()) {
				map.put(publicHoliday.getPrimaryKeyObj(), publicHoliday);

				cacheResult(publicHoliday);

				uncachedPrimaryKeys.remove(publicHoliday.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
					PublicHolidayImpl.class, primaryKey, nullModel);
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
	 * Returns all the public holidaies.
	 *
	 * @return the public holidaies
	 */
	@Override
	public List<PublicHoliday> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the public holidaies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @return the range of public holidaies
	 */
	@Override
	public List<PublicHoliday> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the public holidaies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of public holidaies
	 */
	@Override
	public List<PublicHoliday> findAll(
		int start, int end,
		OrderByComparator<PublicHoliday> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the public holidaies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of public holidaies
	 */
	@Override
	public List<PublicHoliday> findAll(
		int start, int end, OrderByComparator<PublicHoliday> orderByComparator,
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

		List<PublicHoliday> list = null;

		if (retrieveFromCache) {
			list = (List<PublicHoliday>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PUBLICHOLIDAY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PUBLICHOLIDAY;

				if (pagination) {
					sql = sql.concat(PublicHolidayModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PublicHoliday>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PublicHoliday>)QueryUtil.list(
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
	 * Removes all the public holidaies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PublicHoliday publicHoliday : findAll()) {
			remove(publicHoliday);
		}
	}

	/**
	 * Returns the number of public holidaies.
	 *
	 * @return the number of public holidaies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PUBLICHOLIDAY);

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

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return PublicHolidayModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the public holiday persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
			PublicHolidayModelImpl.FINDER_CACHE_ENABLED,
			PublicHolidayImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
			PublicHolidayModelImpl.FINDER_CACHE_ENABLED,
			PublicHolidayImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
			PublicHolidayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
			PublicHolidayModelImpl.FINDER_CACHE_ENABLED,
			PublicHolidayImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
			PublicHolidayModelImpl.FINDER_CACHE_ENABLED,
			PublicHolidayImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] {String.class.getName()},
			PublicHolidayModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
			PublicHolidayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByRecurrent = new FinderPath(
			PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
			PublicHolidayModelImpl.FINDER_CACHE_ENABLED,
			PublicHolidayImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByRecurrent",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByRecurrent = new FinderPath(
			PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
			PublicHolidayModelImpl.FINDER_CACHE_ENABLED,
			PublicHolidayImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByRecurrent", new String[] {Boolean.class.getName()},
			PublicHolidayModelImpl.RECURRENT_COLUMN_BITMASK);

		_finderPathCountByRecurrent = new FinderPath(
			PublicHolidayModelImpl.ENTITY_CACHE_ENABLED,
			PublicHolidayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRecurrent",
			new String[] {Boolean.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(PublicHolidayImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_PUBLICHOLIDAY =
		"SELECT publicHoliday FROM PublicHoliday publicHoliday";

	private static final String _SQL_SELECT_PUBLICHOLIDAY_WHERE_PKS_IN =
		"SELECT publicHoliday FROM PublicHoliday publicHoliday WHERE publicHolidayId IN (";

	private static final String _SQL_SELECT_PUBLICHOLIDAY_WHERE =
		"SELECT publicHoliday FROM PublicHoliday publicHoliday WHERE ";

	private static final String _SQL_COUNT_PUBLICHOLIDAY =
		"SELECT COUNT(publicHoliday) FROM PublicHoliday publicHoliday";

	private static final String _SQL_COUNT_PUBLICHOLIDAY_WHERE =
		"SELECT COUNT(publicHoliday) FROM PublicHoliday publicHoliday WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "publicHoliday.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No PublicHoliday exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No PublicHoliday exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		PublicHolidayPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "date"});

}