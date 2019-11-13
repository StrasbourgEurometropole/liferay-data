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

package eu.strasbourg.service.agenda.service.persistence.impl;

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

import eu.strasbourg.service.agenda.exception.NoSuchAgendaExportPeriodException;
import eu.strasbourg.service.agenda.model.AgendaExportPeriod;
import eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodImpl;
import eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodModelImpl;
import eu.strasbourg.service.agenda.service.persistence.AgendaExportPeriodPersistence;

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
 * The persistence implementation for the agenda export period service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see AgendaExportPeriodPersistence
 * @see eu.strasbourg.service.agenda.service.persistence.AgendaExportPeriodUtil
 * @generated
 */
@ProviderType
public class AgendaExportPeriodPersistenceImpl extends BasePersistenceImpl<AgendaExportPeriod>
	implements AgendaExportPeriodPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AgendaExportPeriodUtil} to access the agenda export period persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AgendaExportPeriodImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
			AgendaExportPeriodModelImpl.FINDER_CACHE_ENABLED,
			AgendaExportPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
			AgendaExportPeriodModelImpl.FINDER_CACHE_ENABLED,
			AgendaExportPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
			AgendaExportPeriodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
			AgendaExportPeriodModelImpl.FINDER_CACHE_ENABLED,
			AgendaExportPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
			AgendaExportPeriodModelImpl.FINDER_CACHE_ENABLED,
			AgendaExportPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			AgendaExportPeriodModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
			AgendaExportPeriodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the agenda export periods where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching agenda export periods
	 */
	@Override
	public List<AgendaExportPeriod> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the agenda export periods where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of agenda export periods
	 * @param end the upper bound of the range of agenda export periods (not inclusive)
	 * @return the range of matching agenda export periods
	 */
	@Override
	public List<AgendaExportPeriod> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the agenda export periods where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of agenda export periods
	 * @param end the upper bound of the range of agenda export periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching agenda export periods
	 */
	@Override
	public List<AgendaExportPeriod> findByUuid(String uuid, int start, int end,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the agenda export periods where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of agenda export periods
	 * @param end the upper bound of the range of agenda export periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching agenda export periods
	 */
	@Override
	public List<AgendaExportPeriod> findByUuid(String uuid, int start, int end,
		OrderByComparator<AgendaExportPeriod> orderByComparator,
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

		List<AgendaExportPeriod> list = null;

		if (retrieveFromCache) {
			list = (List<AgendaExportPeriod>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AgendaExportPeriod agendaExportPeriod : list) {
					if (!Objects.equals(uuid, agendaExportPeriod.getUuid())) {
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

			query.append(_SQL_SELECT_AGENDAEXPORTPERIOD_WHERE);

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
				query.append(AgendaExportPeriodModelImpl.ORDER_BY_JPQL);
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
					list = (List<AgendaExportPeriod>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AgendaExportPeriod>)QueryUtil.list(q,
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
	 * Returns the first agenda export period in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda export period
	 * @throws NoSuchAgendaExportPeriodException if a matching agenda export period could not be found
	 */
	@Override
	public AgendaExportPeriod findByUuid_First(String uuid,
		OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws NoSuchAgendaExportPeriodException {
		AgendaExportPeriod agendaExportPeriod = fetchByUuid_First(uuid,
				orderByComparator);

		if (agendaExportPeriod != null) {
			return agendaExportPeriod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAgendaExportPeriodException(msg.toString());
	}

	/**
	 * Returns the first agenda export period in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda export period, or <code>null</code> if a matching agenda export period could not be found
	 */
	@Override
	public AgendaExportPeriod fetchByUuid_First(String uuid,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		List<AgendaExportPeriod> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last agenda export period in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda export period
	 * @throws NoSuchAgendaExportPeriodException if a matching agenda export period could not be found
	 */
	@Override
	public AgendaExportPeriod findByUuid_Last(String uuid,
		OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws NoSuchAgendaExportPeriodException {
		AgendaExportPeriod agendaExportPeriod = fetchByUuid_Last(uuid,
				orderByComparator);

		if (agendaExportPeriod != null) {
			return agendaExportPeriod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAgendaExportPeriodException(msg.toString());
	}

	/**
	 * Returns the last agenda export period in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda export period, or <code>null</code> if a matching agenda export period could not be found
	 */
	@Override
	public AgendaExportPeriod fetchByUuid_Last(String uuid,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<AgendaExportPeriod> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the agenda export periods before and after the current agenda export period in the ordered set where uuid = &#63;.
	 *
	 * @param agendaExportPeriodId the primary key of the current agenda export period
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next agenda export period
	 * @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	 */
	@Override
	public AgendaExportPeriod[] findByUuid_PrevAndNext(
		long agendaExportPeriodId, String uuid,
		OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws NoSuchAgendaExportPeriodException {
		AgendaExportPeriod agendaExportPeriod = findByPrimaryKey(agendaExportPeriodId);

		Session session = null;

		try {
			session = openSession();

			AgendaExportPeriod[] array = new AgendaExportPeriodImpl[3];

			array[0] = getByUuid_PrevAndNext(session, agendaExportPeriod, uuid,
					orderByComparator, true);

			array[1] = agendaExportPeriod;

			array[2] = getByUuid_PrevAndNext(session, agendaExportPeriod, uuid,
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

	protected AgendaExportPeriod getByUuid_PrevAndNext(Session session,
		AgendaExportPeriod agendaExportPeriod, String uuid,
		OrderByComparator<AgendaExportPeriod> orderByComparator,
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

		query.append(_SQL_SELECT_AGENDAEXPORTPERIOD_WHERE);

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
			query.append(AgendaExportPeriodModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(agendaExportPeriod);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AgendaExportPeriod> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the agenda export periods where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (AgendaExportPeriod agendaExportPeriod : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(agendaExportPeriod);
		}
	}

	/**
	 * Returns the number of agenda export periods where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching agenda export periods
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_AGENDAEXPORTPERIOD_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "agendaExportPeriod.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "agendaExportPeriod.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(agendaExportPeriod.uuid IS NULL OR agendaExportPeriod.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AGENDAEXPORTID =
		new FinderPath(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
			AgendaExportPeriodModelImpl.FINDER_CACHE_ENABLED,
			AgendaExportPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAgendaExportId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AGENDAEXPORTID =
		new FinderPath(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
			AgendaExportPeriodModelImpl.FINDER_CACHE_ENABLED,
			AgendaExportPeriodImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAgendaExportId",
			new String[] { Long.class.getName() },
			AgendaExportPeriodModelImpl.AGENDAEXPORTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AGENDAEXPORTID = new FinderPath(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
			AgendaExportPeriodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAgendaExportId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the agenda export periods where agendaExportId = &#63;.
	 *
	 * @param agendaExportId the agenda export ID
	 * @return the matching agenda export periods
	 */
	@Override
	public List<AgendaExportPeriod> findByAgendaExportId(long agendaExportId) {
		return findByAgendaExportId(agendaExportId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the agenda export periods where agendaExportId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param agendaExportId the agenda export ID
	 * @param start the lower bound of the range of agenda export periods
	 * @param end the upper bound of the range of agenda export periods (not inclusive)
	 * @return the range of matching agenda export periods
	 */
	@Override
	public List<AgendaExportPeriod> findByAgendaExportId(long agendaExportId,
		int start, int end) {
		return findByAgendaExportId(agendaExportId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the agenda export periods where agendaExportId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param agendaExportId the agenda export ID
	 * @param start the lower bound of the range of agenda export periods
	 * @param end the upper bound of the range of agenda export periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching agenda export periods
	 */
	@Override
	public List<AgendaExportPeriod> findByAgendaExportId(long agendaExportId,
		int start, int end,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		return findByAgendaExportId(agendaExportId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the agenda export periods where agendaExportId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param agendaExportId the agenda export ID
	 * @param start the lower bound of the range of agenda export periods
	 * @param end the upper bound of the range of agenda export periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching agenda export periods
	 */
	@Override
	public List<AgendaExportPeriod> findByAgendaExportId(long agendaExportId,
		int start, int end,
		OrderByComparator<AgendaExportPeriod> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AGENDAEXPORTID;
			finderArgs = new Object[] { agendaExportId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AGENDAEXPORTID;
			finderArgs = new Object[] {
					agendaExportId,
					
					start, end, orderByComparator
				};
		}

		List<AgendaExportPeriod> list = null;

		if (retrieveFromCache) {
			list = (List<AgendaExportPeriod>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AgendaExportPeriod agendaExportPeriod : list) {
					if ((agendaExportId != agendaExportPeriod.getAgendaExportId())) {
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

			query.append(_SQL_SELECT_AGENDAEXPORTPERIOD_WHERE);

			query.append(_FINDER_COLUMN_AGENDAEXPORTID_AGENDAEXPORTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AgendaExportPeriodModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(agendaExportId);

				if (!pagination) {
					list = (List<AgendaExportPeriod>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AgendaExportPeriod>)QueryUtil.list(q,
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
	 * Returns the first agenda export period in the ordered set where agendaExportId = &#63;.
	 *
	 * @param agendaExportId the agenda export ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda export period
	 * @throws NoSuchAgendaExportPeriodException if a matching agenda export period could not be found
	 */
	@Override
	public AgendaExportPeriod findByAgendaExportId_First(long agendaExportId,
		OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws NoSuchAgendaExportPeriodException {
		AgendaExportPeriod agendaExportPeriod = fetchByAgendaExportId_First(agendaExportId,
				orderByComparator);

		if (agendaExportPeriod != null) {
			return agendaExportPeriod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("agendaExportId=");
		msg.append(agendaExportId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAgendaExportPeriodException(msg.toString());
	}

	/**
	 * Returns the first agenda export period in the ordered set where agendaExportId = &#63;.
	 *
	 * @param agendaExportId the agenda export ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda export period, or <code>null</code> if a matching agenda export period could not be found
	 */
	@Override
	public AgendaExportPeriod fetchByAgendaExportId_First(long agendaExportId,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		List<AgendaExportPeriod> list = findByAgendaExportId(agendaExportId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last agenda export period in the ordered set where agendaExportId = &#63;.
	 *
	 * @param agendaExportId the agenda export ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda export period
	 * @throws NoSuchAgendaExportPeriodException if a matching agenda export period could not be found
	 */
	@Override
	public AgendaExportPeriod findByAgendaExportId_Last(long agendaExportId,
		OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws NoSuchAgendaExportPeriodException {
		AgendaExportPeriod agendaExportPeriod = fetchByAgendaExportId_Last(agendaExportId,
				orderByComparator);

		if (agendaExportPeriod != null) {
			return agendaExportPeriod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("agendaExportId=");
		msg.append(agendaExportId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAgendaExportPeriodException(msg.toString());
	}

	/**
	 * Returns the last agenda export period in the ordered set where agendaExportId = &#63;.
	 *
	 * @param agendaExportId the agenda export ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda export period, or <code>null</code> if a matching agenda export period could not be found
	 */
	@Override
	public AgendaExportPeriod fetchByAgendaExportId_Last(long agendaExportId,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		int count = countByAgendaExportId(agendaExportId);

		if (count == 0) {
			return null;
		}

		List<AgendaExportPeriod> list = findByAgendaExportId(agendaExportId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the agenda export periods before and after the current agenda export period in the ordered set where agendaExportId = &#63;.
	 *
	 * @param agendaExportPeriodId the primary key of the current agenda export period
	 * @param agendaExportId the agenda export ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next agenda export period
	 * @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	 */
	@Override
	public AgendaExportPeriod[] findByAgendaExportId_PrevAndNext(
		long agendaExportPeriodId, long agendaExportId,
		OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws NoSuchAgendaExportPeriodException {
		AgendaExportPeriod agendaExportPeriod = findByPrimaryKey(agendaExportPeriodId);

		Session session = null;

		try {
			session = openSession();

			AgendaExportPeriod[] array = new AgendaExportPeriodImpl[3];

			array[0] = getByAgendaExportId_PrevAndNext(session,
					agendaExportPeriod, agendaExportId, orderByComparator, true);

			array[1] = agendaExportPeriod;

			array[2] = getByAgendaExportId_PrevAndNext(session,
					agendaExportPeriod, agendaExportId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AgendaExportPeriod getByAgendaExportId_PrevAndNext(
		Session session, AgendaExportPeriod agendaExportPeriod,
		long agendaExportId,
		OrderByComparator<AgendaExportPeriod> orderByComparator,
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

		query.append(_SQL_SELECT_AGENDAEXPORTPERIOD_WHERE);

		query.append(_FINDER_COLUMN_AGENDAEXPORTID_AGENDAEXPORTID_2);

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
			query.append(AgendaExportPeriodModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(agendaExportId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(agendaExportPeriod);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AgendaExportPeriod> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the agenda export periods where agendaExportId = &#63; from the database.
	 *
	 * @param agendaExportId the agenda export ID
	 */
	@Override
	public void removeByAgendaExportId(long agendaExportId) {
		for (AgendaExportPeriod agendaExportPeriod : findByAgendaExportId(
				agendaExportId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(agendaExportPeriod);
		}
	}

	/**
	 * Returns the number of agenda export periods where agendaExportId = &#63;.
	 *
	 * @param agendaExportId the agenda export ID
	 * @return the number of matching agenda export periods
	 */
	@Override
	public int countByAgendaExportId(long agendaExportId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AGENDAEXPORTID;

		Object[] finderArgs = new Object[] { agendaExportId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_AGENDAEXPORTPERIOD_WHERE);

			query.append(_FINDER_COLUMN_AGENDAEXPORTID_AGENDAEXPORTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(agendaExportId);

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

	private static final String _FINDER_COLUMN_AGENDAEXPORTID_AGENDAEXPORTID_2 = "agendaExportPeriod.agendaExportId = ?";

	public AgendaExportPeriodPersistenceImpl() {
		setModelClass(AgendaExportPeriod.class);

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
	 * Caches the agenda export period in the entity cache if it is enabled.
	 *
	 * @param agendaExportPeriod the agenda export period
	 */
	@Override
	public void cacheResult(AgendaExportPeriod agendaExportPeriod) {
		entityCache.putResult(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
			AgendaExportPeriodImpl.class, agendaExportPeriod.getPrimaryKey(),
			agendaExportPeriod);

		agendaExportPeriod.resetOriginalValues();
	}

	/**
	 * Caches the agenda export periods in the entity cache if it is enabled.
	 *
	 * @param agendaExportPeriods the agenda export periods
	 */
	@Override
	public void cacheResult(List<AgendaExportPeriod> agendaExportPeriods) {
		for (AgendaExportPeriod agendaExportPeriod : agendaExportPeriods) {
			if (entityCache.getResult(
						AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
						AgendaExportPeriodImpl.class,
						agendaExportPeriod.getPrimaryKey()) == null) {
				cacheResult(agendaExportPeriod);
			}
			else {
				agendaExportPeriod.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all agenda export periods.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AgendaExportPeriodImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the agenda export period.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AgendaExportPeriod agendaExportPeriod) {
		entityCache.removeResult(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
			AgendaExportPeriodImpl.class, agendaExportPeriod.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AgendaExportPeriod> agendaExportPeriods) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AgendaExportPeriod agendaExportPeriod : agendaExportPeriods) {
			entityCache.removeResult(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
				AgendaExportPeriodImpl.class, agendaExportPeriod.getPrimaryKey());
		}
	}

	/**
	 * Creates a new agenda export period with the primary key. Does not add the agenda export period to the database.
	 *
	 * @param agendaExportPeriodId the primary key for the new agenda export period
	 * @return the new agenda export period
	 */
	@Override
	public AgendaExportPeriod create(long agendaExportPeriodId) {
		AgendaExportPeriod agendaExportPeriod = new AgendaExportPeriodImpl();

		agendaExportPeriod.setNew(true);
		agendaExportPeriod.setPrimaryKey(agendaExportPeriodId);

		String uuid = PortalUUIDUtil.generate();

		agendaExportPeriod.setUuid(uuid);

		return agendaExportPeriod;
	}

	/**
	 * Removes the agenda export period with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param agendaExportPeriodId the primary key of the agenda export period
	 * @return the agenda export period that was removed
	 * @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	 */
	@Override
	public AgendaExportPeriod remove(long agendaExportPeriodId)
		throws NoSuchAgendaExportPeriodException {
		return remove((Serializable)agendaExportPeriodId);
	}

	/**
	 * Removes the agenda export period with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the agenda export period
	 * @return the agenda export period that was removed
	 * @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	 */
	@Override
	public AgendaExportPeriod remove(Serializable primaryKey)
		throws NoSuchAgendaExportPeriodException {
		Session session = null;

		try {
			session = openSession();

			AgendaExportPeriod agendaExportPeriod = (AgendaExportPeriod)session.get(AgendaExportPeriodImpl.class,
					primaryKey);

			if (agendaExportPeriod == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAgendaExportPeriodException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(agendaExportPeriod);
		}
		catch (NoSuchAgendaExportPeriodException nsee) {
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
	protected AgendaExportPeriod removeImpl(
		AgendaExportPeriod agendaExportPeriod) {
		agendaExportPeriod = toUnwrappedModel(agendaExportPeriod);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(agendaExportPeriod)) {
				agendaExportPeriod = (AgendaExportPeriod)session.get(AgendaExportPeriodImpl.class,
						agendaExportPeriod.getPrimaryKeyObj());
			}

			if (agendaExportPeriod != null) {
				session.delete(agendaExportPeriod);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (agendaExportPeriod != null) {
			clearCache(agendaExportPeriod);
		}

		return agendaExportPeriod;
	}

	@Override
	public AgendaExportPeriod updateImpl(AgendaExportPeriod agendaExportPeriod) {
		agendaExportPeriod = toUnwrappedModel(agendaExportPeriod);

		boolean isNew = agendaExportPeriod.isNew();

		AgendaExportPeriodModelImpl agendaExportPeriodModelImpl = (AgendaExportPeriodModelImpl)agendaExportPeriod;

		if (Validator.isNull(agendaExportPeriod.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			agendaExportPeriod.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (agendaExportPeriod.isNew()) {
				session.save(agendaExportPeriod);

				agendaExportPeriod.setNew(false);
			}
			else {
				agendaExportPeriod = (AgendaExportPeriod)session.merge(agendaExportPeriod);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AgendaExportPeriodModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { agendaExportPeriodModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { agendaExportPeriodModelImpl.getAgendaExportId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AGENDAEXPORTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AGENDAEXPORTID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((agendaExportPeriodModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						agendaExportPeriodModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { agendaExportPeriodModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((agendaExportPeriodModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AGENDAEXPORTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						agendaExportPeriodModelImpl.getOriginalAgendaExportId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AGENDAEXPORTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AGENDAEXPORTID,
					args);

				args = new Object[] {
						agendaExportPeriodModelImpl.getAgendaExportId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AGENDAEXPORTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AGENDAEXPORTID,
					args);
			}
		}

		entityCache.putResult(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
			AgendaExportPeriodImpl.class, agendaExportPeriod.getPrimaryKey(),
			agendaExportPeriod, false);

		agendaExportPeriod.resetOriginalValues();

		return agendaExportPeriod;
	}

	protected AgendaExportPeriod toUnwrappedModel(
		AgendaExportPeriod agendaExportPeriod) {
		if (agendaExportPeriod instanceof AgendaExportPeriodImpl) {
			return agendaExportPeriod;
		}

		AgendaExportPeriodImpl agendaExportPeriodImpl = new AgendaExportPeriodImpl();

		agendaExportPeriodImpl.setNew(agendaExportPeriod.isNew());
		agendaExportPeriodImpl.setPrimaryKey(agendaExportPeriod.getPrimaryKey());

		agendaExportPeriodImpl.setUuid(agendaExportPeriod.getUuid());
		agendaExportPeriodImpl.setAgendaExportPeriodId(agendaExportPeriod.getAgendaExportPeriodId());
		agendaExportPeriodImpl.setStartDate(agendaExportPeriod.getStartDate());
		agendaExportPeriodImpl.setEndDate(agendaExportPeriod.getEndDate());
		agendaExportPeriodImpl.setAgendaExportId(agendaExportPeriod.getAgendaExportId());

		return agendaExportPeriodImpl;
	}

	/**
	 * Returns the agenda export period with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the agenda export period
	 * @return the agenda export period
	 * @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	 */
	@Override
	public AgendaExportPeriod findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAgendaExportPeriodException {
		AgendaExportPeriod agendaExportPeriod = fetchByPrimaryKey(primaryKey);

		if (agendaExportPeriod == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAgendaExportPeriodException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return agendaExportPeriod;
	}

	/**
	 * Returns the agenda export period with the primary key or throws a {@link NoSuchAgendaExportPeriodException} if it could not be found.
	 *
	 * @param agendaExportPeriodId the primary key of the agenda export period
	 * @return the agenda export period
	 * @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	 */
	@Override
	public AgendaExportPeriod findByPrimaryKey(long agendaExportPeriodId)
		throws NoSuchAgendaExportPeriodException {
		return findByPrimaryKey((Serializable)agendaExportPeriodId);
	}

	/**
	 * Returns the agenda export period with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the agenda export period
	 * @return the agenda export period, or <code>null</code> if a agenda export period with the primary key could not be found
	 */
	@Override
	public AgendaExportPeriod fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
				AgendaExportPeriodImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AgendaExportPeriod agendaExportPeriod = (AgendaExportPeriod)serializable;

		if (agendaExportPeriod == null) {
			Session session = null;

			try {
				session = openSession();

				agendaExportPeriod = (AgendaExportPeriod)session.get(AgendaExportPeriodImpl.class,
						primaryKey);

				if (agendaExportPeriod != null) {
					cacheResult(agendaExportPeriod);
				}
				else {
					entityCache.putResult(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
						AgendaExportPeriodImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
					AgendaExportPeriodImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return agendaExportPeriod;
	}

	/**
	 * Returns the agenda export period with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param agendaExportPeriodId the primary key of the agenda export period
	 * @return the agenda export period, or <code>null</code> if a agenda export period with the primary key could not be found
	 */
	@Override
	public AgendaExportPeriod fetchByPrimaryKey(long agendaExportPeriodId) {
		return fetchByPrimaryKey((Serializable)agendaExportPeriodId);
	}

	@Override
	public Map<Serializable, AgendaExportPeriod> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AgendaExportPeriod> map = new HashMap<Serializable, AgendaExportPeriod>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AgendaExportPeriod agendaExportPeriod = fetchByPrimaryKey(primaryKey);

			if (agendaExportPeriod != null) {
				map.put(primaryKey, agendaExportPeriod);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
					AgendaExportPeriodImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AgendaExportPeriod)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_AGENDAEXPORTPERIOD_WHERE_PKS_IN);

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

			for (AgendaExportPeriod agendaExportPeriod : (List<AgendaExportPeriod>)q.list()) {
				map.put(agendaExportPeriod.getPrimaryKeyObj(),
					agendaExportPeriod);

				cacheResult(agendaExportPeriod);

				uncachedPrimaryKeys.remove(agendaExportPeriod.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AgendaExportPeriodModelImpl.ENTITY_CACHE_ENABLED,
					AgendaExportPeriodImpl.class, primaryKey, nullModel);
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
	 * Returns all the agenda export periods.
	 *
	 * @return the agenda export periods
	 */
	@Override
	public List<AgendaExportPeriod> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the agenda export periods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of agenda export periods
	 * @param end the upper bound of the range of agenda export periods (not inclusive)
	 * @return the range of agenda export periods
	 */
	@Override
	public List<AgendaExportPeriod> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the agenda export periods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of agenda export periods
	 * @param end the upper bound of the range of agenda export periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of agenda export periods
	 */
	@Override
	public List<AgendaExportPeriod> findAll(int start, int end,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the agenda export periods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of agenda export periods
	 * @param end the upper bound of the range of agenda export periods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of agenda export periods
	 */
	@Override
	public List<AgendaExportPeriod> findAll(int start, int end,
		OrderByComparator<AgendaExportPeriod> orderByComparator,
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

		List<AgendaExportPeriod> list = null;

		if (retrieveFromCache) {
			list = (List<AgendaExportPeriod>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_AGENDAEXPORTPERIOD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AGENDAEXPORTPERIOD;

				if (pagination) {
					sql = sql.concat(AgendaExportPeriodModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AgendaExportPeriod>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AgendaExportPeriod>)QueryUtil.list(q,
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
	 * Removes all the agenda export periods from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AgendaExportPeriod agendaExportPeriod : findAll()) {
			remove(agendaExportPeriod);
		}
	}

	/**
	 * Returns the number of agenda export periods.
	 *
	 * @return the number of agenda export periods
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AGENDAEXPORTPERIOD);

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
		return AgendaExportPeriodModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the agenda export period persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AgendaExportPeriodImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_AGENDAEXPORTPERIOD = "SELECT agendaExportPeriod FROM AgendaExportPeriod agendaExportPeriod";
	private static final String _SQL_SELECT_AGENDAEXPORTPERIOD_WHERE_PKS_IN = "SELECT agendaExportPeriod FROM AgendaExportPeriod agendaExportPeriod WHERE agendaExportPeriodId IN (";
	private static final String _SQL_SELECT_AGENDAEXPORTPERIOD_WHERE = "SELECT agendaExportPeriod FROM AgendaExportPeriod agendaExportPeriod WHERE ";
	private static final String _SQL_COUNT_AGENDAEXPORTPERIOD = "SELECT COUNT(agendaExportPeriod) FROM AgendaExportPeriod agendaExportPeriod";
	private static final String _SQL_COUNT_AGENDAEXPORTPERIOD_WHERE = "SELECT COUNT(agendaExportPeriod) FROM AgendaExportPeriod agendaExportPeriod WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "agendaExportPeriod.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AgendaExportPeriod exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AgendaExportPeriod exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AgendaExportPeriodPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}