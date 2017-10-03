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

import eu.strasbourg.service.agenda.exception.NoSuchCampaignEventStatusException;
import eu.strasbourg.service.agenda.model.CampaignEventStatus;
import eu.strasbourg.service.agenda.model.impl.CampaignEventStatusImpl;
import eu.strasbourg.service.agenda.model.impl.CampaignEventStatusModelImpl;
import eu.strasbourg.service.agenda.service.persistence.CampaignEventStatusPersistence;

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
 * The persistence implementation for the campaign event status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see CampaignEventStatusPersistence
 * @see eu.strasbourg.service.agenda.service.persistence.CampaignEventStatusUtil
 * @generated
 */
@ProviderType
public class CampaignEventStatusPersistenceImpl extends BasePersistenceImpl<CampaignEventStatus>
	implements CampaignEventStatusPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CampaignEventStatusUtil} to access the campaign event status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CampaignEventStatusImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
			CampaignEventStatusModelImpl.FINDER_CACHE_ENABLED,
			CampaignEventStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
			CampaignEventStatusModelImpl.FINDER_CACHE_ENABLED,
			CampaignEventStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
			CampaignEventStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
			CampaignEventStatusModelImpl.FINDER_CACHE_ENABLED,
			CampaignEventStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
			CampaignEventStatusModelImpl.FINDER_CACHE_ENABLED,
			CampaignEventStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CampaignEventStatusModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
			CampaignEventStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the campaign event statuses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching campaign event statuses
	 */
	@Override
	public List<CampaignEventStatus> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the campaign event statuses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of campaign event statuses
	 * @param end the upper bound of the range of campaign event statuses (not inclusive)
	 * @return the range of matching campaign event statuses
	 */
	@Override
	public List<CampaignEventStatus> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the campaign event statuses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of campaign event statuses
	 * @param end the upper bound of the range of campaign event statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching campaign event statuses
	 */
	@Override
	public List<CampaignEventStatus> findByUuid(String uuid, int start,
		int end, OrderByComparator<CampaignEventStatus> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the campaign event statuses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of campaign event statuses
	 * @param end the upper bound of the range of campaign event statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching campaign event statuses
	 */
	@Override
	public List<CampaignEventStatus> findByUuid(String uuid, int start,
		int end, OrderByComparator<CampaignEventStatus> orderByComparator,
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

		List<CampaignEventStatus> list = null;

		if (retrieveFromCache) {
			list = (List<CampaignEventStatus>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CampaignEventStatus campaignEventStatus : list) {
					if (!Objects.equals(uuid, campaignEventStatus.getUuid())) {
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

			query.append(_SQL_SELECT_CAMPAIGNEVENTSTATUS_WHERE);

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
				query.append(CampaignEventStatusModelImpl.ORDER_BY_JPQL);
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
					list = (List<CampaignEventStatus>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CampaignEventStatus>)QueryUtil.list(q,
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
	 * Returns the first campaign event status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign event status
	 * @throws NoSuchCampaignEventStatusException if a matching campaign event status could not be found
	 */
	@Override
	public CampaignEventStatus findByUuid_First(String uuid,
		OrderByComparator<CampaignEventStatus> orderByComparator)
		throws NoSuchCampaignEventStatusException {
		CampaignEventStatus campaignEventStatus = fetchByUuid_First(uuid,
				orderByComparator);

		if (campaignEventStatus != null) {
			return campaignEventStatus;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCampaignEventStatusException(msg.toString());
	}

	/**
	 * Returns the first campaign event status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign event status, or <code>null</code> if a matching campaign event status could not be found
	 */
	@Override
	public CampaignEventStatus fetchByUuid_First(String uuid,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		List<CampaignEventStatus> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last campaign event status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign event status
	 * @throws NoSuchCampaignEventStatusException if a matching campaign event status could not be found
	 */
	@Override
	public CampaignEventStatus findByUuid_Last(String uuid,
		OrderByComparator<CampaignEventStatus> orderByComparator)
		throws NoSuchCampaignEventStatusException {
		CampaignEventStatus campaignEventStatus = fetchByUuid_Last(uuid,
				orderByComparator);

		if (campaignEventStatus != null) {
			return campaignEventStatus;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCampaignEventStatusException(msg.toString());
	}

	/**
	 * Returns the last campaign event status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign event status, or <code>null</code> if a matching campaign event status could not be found
	 */
	@Override
	public CampaignEventStatus fetchByUuid_Last(String uuid,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CampaignEventStatus> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the campaign event statuses before and after the current campaign event status in the ordered set where uuid = &#63;.
	 *
	 * @param statusId the primary key of the current campaign event status
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next campaign event status
	 * @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	 */
	@Override
	public CampaignEventStatus[] findByUuid_PrevAndNext(long statusId,
		String uuid, OrderByComparator<CampaignEventStatus> orderByComparator)
		throws NoSuchCampaignEventStatusException {
		CampaignEventStatus campaignEventStatus = findByPrimaryKey(statusId);

		Session session = null;

		try {
			session = openSession();

			CampaignEventStatus[] array = new CampaignEventStatusImpl[3];

			array[0] = getByUuid_PrevAndNext(session, campaignEventStatus,
					uuid, orderByComparator, true);

			array[1] = campaignEventStatus;

			array[2] = getByUuid_PrevAndNext(session, campaignEventStatus,
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

	protected CampaignEventStatus getByUuid_PrevAndNext(Session session,
		CampaignEventStatus campaignEventStatus, String uuid,
		OrderByComparator<CampaignEventStatus> orderByComparator,
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

		query.append(_SQL_SELECT_CAMPAIGNEVENTSTATUS_WHERE);

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
			query.append(CampaignEventStatusModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(campaignEventStatus);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CampaignEventStatus> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the campaign event statuses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CampaignEventStatus campaignEventStatus : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(campaignEventStatus);
		}
	}

	/**
	 * Returns the number of campaign event statuses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching campaign event statuses
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CAMPAIGNEVENTSTATUS_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "campaignEventStatus.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "campaignEventStatus.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(campaignEventStatus.uuid IS NULL OR campaignEventStatus.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CAMPAIGNEVENTID =
		new FinderPath(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
			CampaignEventStatusModelImpl.FINDER_CACHE_ENABLED,
			CampaignEventStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCampaignEventId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNEVENTID =
		new FinderPath(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
			CampaignEventStatusModelImpl.FINDER_CACHE_ENABLED,
			CampaignEventStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCampaignEventId",
			new String[] { Long.class.getName() },
			CampaignEventStatusModelImpl.CAMPAIGNEVENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CAMPAIGNEVENTID = new FinderPath(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
			CampaignEventStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCampaignEventId", new String[] { Long.class.getName() });

	/**
	 * Returns all the campaign event statuses where campaignEventId = &#63;.
	 *
	 * @param campaignEventId the campaign event ID
	 * @return the matching campaign event statuses
	 */
	@Override
	public List<CampaignEventStatus> findByCampaignEventId(long campaignEventId) {
		return findByCampaignEventId(campaignEventId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the campaign event statuses where campaignEventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param campaignEventId the campaign event ID
	 * @param start the lower bound of the range of campaign event statuses
	 * @param end the upper bound of the range of campaign event statuses (not inclusive)
	 * @return the range of matching campaign event statuses
	 */
	@Override
	public List<CampaignEventStatus> findByCampaignEventId(
		long campaignEventId, int start, int end) {
		return findByCampaignEventId(campaignEventId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the campaign event statuses where campaignEventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param campaignEventId the campaign event ID
	 * @param start the lower bound of the range of campaign event statuses
	 * @param end the upper bound of the range of campaign event statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching campaign event statuses
	 */
	@Override
	public List<CampaignEventStatus> findByCampaignEventId(
		long campaignEventId, int start, int end,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		return findByCampaignEventId(campaignEventId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the campaign event statuses where campaignEventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param campaignEventId the campaign event ID
	 * @param start the lower bound of the range of campaign event statuses
	 * @param end the upper bound of the range of campaign event statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching campaign event statuses
	 */
	@Override
	public List<CampaignEventStatus> findByCampaignEventId(
		long campaignEventId, int start, int end,
		OrderByComparator<CampaignEventStatus> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNEVENTID;
			finderArgs = new Object[] { campaignEventId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CAMPAIGNEVENTID;
			finderArgs = new Object[] {
					campaignEventId,
					
					start, end, orderByComparator
				};
		}

		List<CampaignEventStatus> list = null;

		if (retrieveFromCache) {
			list = (List<CampaignEventStatus>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CampaignEventStatus campaignEventStatus : list) {
					if ((campaignEventId != campaignEventStatus.getCampaignEventId())) {
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

			query.append(_SQL_SELECT_CAMPAIGNEVENTSTATUS_WHERE);

			query.append(_FINDER_COLUMN_CAMPAIGNEVENTID_CAMPAIGNEVENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CampaignEventStatusModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignEventId);

				if (!pagination) {
					list = (List<CampaignEventStatus>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CampaignEventStatus>)QueryUtil.list(q,
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
	 * Returns the first campaign event status in the ordered set where campaignEventId = &#63;.
	 *
	 * @param campaignEventId the campaign event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign event status
	 * @throws NoSuchCampaignEventStatusException if a matching campaign event status could not be found
	 */
	@Override
	public CampaignEventStatus findByCampaignEventId_First(
		long campaignEventId,
		OrderByComparator<CampaignEventStatus> orderByComparator)
		throws NoSuchCampaignEventStatusException {
		CampaignEventStatus campaignEventStatus = fetchByCampaignEventId_First(campaignEventId,
				orderByComparator);

		if (campaignEventStatus != null) {
			return campaignEventStatus;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("campaignEventId=");
		msg.append(campaignEventId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCampaignEventStatusException(msg.toString());
	}

	/**
	 * Returns the first campaign event status in the ordered set where campaignEventId = &#63;.
	 *
	 * @param campaignEventId the campaign event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign event status, or <code>null</code> if a matching campaign event status could not be found
	 */
	@Override
	public CampaignEventStatus fetchByCampaignEventId_First(
		long campaignEventId,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		List<CampaignEventStatus> list = findByCampaignEventId(campaignEventId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last campaign event status in the ordered set where campaignEventId = &#63;.
	 *
	 * @param campaignEventId the campaign event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign event status
	 * @throws NoSuchCampaignEventStatusException if a matching campaign event status could not be found
	 */
	@Override
	public CampaignEventStatus findByCampaignEventId_Last(
		long campaignEventId,
		OrderByComparator<CampaignEventStatus> orderByComparator)
		throws NoSuchCampaignEventStatusException {
		CampaignEventStatus campaignEventStatus = fetchByCampaignEventId_Last(campaignEventId,
				orderByComparator);

		if (campaignEventStatus != null) {
			return campaignEventStatus;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("campaignEventId=");
		msg.append(campaignEventId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCampaignEventStatusException(msg.toString());
	}

	/**
	 * Returns the last campaign event status in the ordered set where campaignEventId = &#63;.
	 *
	 * @param campaignEventId the campaign event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign event status, or <code>null</code> if a matching campaign event status could not be found
	 */
	@Override
	public CampaignEventStatus fetchByCampaignEventId_Last(
		long campaignEventId,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		int count = countByCampaignEventId(campaignEventId);

		if (count == 0) {
			return null;
		}

		List<CampaignEventStatus> list = findByCampaignEventId(campaignEventId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the campaign event statuses before and after the current campaign event status in the ordered set where campaignEventId = &#63;.
	 *
	 * @param statusId the primary key of the current campaign event status
	 * @param campaignEventId the campaign event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next campaign event status
	 * @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	 */
	@Override
	public CampaignEventStatus[] findByCampaignEventId_PrevAndNext(
		long statusId, long campaignEventId,
		OrderByComparator<CampaignEventStatus> orderByComparator)
		throws NoSuchCampaignEventStatusException {
		CampaignEventStatus campaignEventStatus = findByPrimaryKey(statusId);

		Session session = null;

		try {
			session = openSession();

			CampaignEventStatus[] array = new CampaignEventStatusImpl[3];

			array[0] = getByCampaignEventId_PrevAndNext(session,
					campaignEventStatus, campaignEventId, orderByComparator,
					true);

			array[1] = campaignEventStatus;

			array[2] = getByCampaignEventId_PrevAndNext(session,
					campaignEventStatus, campaignEventId, orderByComparator,
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

	protected CampaignEventStatus getByCampaignEventId_PrevAndNext(
		Session session, CampaignEventStatus campaignEventStatus,
		long campaignEventId,
		OrderByComparator<CampaignEventStatus> orderByComparator,
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

		query.append(_SQL_SELECT_CAMPAIGNEVENTSTATUS_WHERE);

		query.append(_FINDER_COLUMN_CAMPAIGNEVENTID_CAMPAIGNEVENTID_2);

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
			query.append(CampaignEventStatusModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(campaignEventId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(campaignEventStatus);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CampaignEventStatus> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the campaign event statuses where campaignEventId = &#63; from the database.
	 *
	 * @param campaignEventId the campaign event ID
	 */
	@Override
	public void removeByCampaignEventId(long campaignEventId) {
		for (CampaignEventStatus campaignEventStatus : findByCampaignEventId(
				campaignEventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(campaignEventStatus);
		}
	}

	/**
	 * Returns the number of campaign event statuses where campaignEventId = &#63;.
	 *
	 * @param campaignEventId the campaign event ID
	 * @return the number of matching campaign event statuses
	 */
	@Override
	public int countByCampaignEventId(long campaignEventId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CAMPAIGNEVENTID;

		Object[] finderArgs = new Object[] { campaignEventId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CAMPAIGNEVENTSTATUS_WHERE);

			query.append(_FINDER_COLUMN_CAMPAIGNEVENTID_CAMPAIGNEVENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignEventId);

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

	private static final String _FINDER_COLUMN_CAMPAIGNEVENTID_CAMPAIGNEVENTID_2 =
		"campaignEventStatus.campaignEventId = ?";

	public CampaignEventStatusPersistenceImpl() {
		setModelClass(CampaignEventStatus.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("comment", "comment_");
			dbColumnNames.put("date", "date_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the campaign event status in the entity cache if it is enabled.
	 *
	 * @param campaignEventStatus the campaign event status
	 */
	@Override
	public void cacheResult(CampaignEventStatus campaignEventStatus) {
		entityCache.putResult(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
			CampaignEventStatusImpl.class, campaignEventStatus.getPrimaryKey(),
			campaignEventStatus);

		campaignEventStatus.resetOriginalValues();
	}

	/**
	 * Caches the campaign event statuses in the entity cache if it is enabled.
	 *
	 * @param campaignEventStatuses the campaign event statuses
	 */
	@Override
	public void cacheResult(List<CampaignEventStatus> campaignEventStatuses) {
		for (CampaignEventStatus campaignEventStatus : campaignEventStatuses) {
			if (entityCache.getResult(
						CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
						CampaignEventStatusImpl.class,
						campaignEventStatus.getPrimaryKey()) == null) {
				cacheResult(campaignEventStatus);
			}
			else {
				campaignEventStatus.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all campaign event statuses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CampaignEventStatusImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the campaign event status.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CampaignEventStatus campaignEventStatus) {
		entityCache.removeResult(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
			CampaignEventStatusImpl.class, campaignEventStatus.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CampaignEventStatus> campaignEventStatuses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CampaignEventStatus campaignEventStatus : campaignEventStatuses) {
			entityCache.removeResult(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
				CampaignEventStatusImpl.class,
				campaignEventStatus.getPrimaryKey());
		}
	}

	/**
	 * Creates a new campaign event status with the primary key. Does not add the campaign event status to the database.
	 *
	 * @param statusId the primary key for the new campaign event status
	 * @return the new campaign event status
	 */
	@Override
	public CampaignEventStatus create(long statusId) {
		CampaignEventStatus campaignEventStatus = new CampaignEventStatusImpl();

		campaignEventStatus.setNew(true);
		campaignEventStatus.setPrimaryKey(statusId);

		String uuid = PortalUUIDUtil.generate();

		campaignEventStatus.setUuid(uuid);

		return campaignEventStatus;
	}

	/**
	 * Removes the campaign event status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param statusId the primary key of the campaign event status
	 * @return the campaign event status that was removed
	 * @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	 */
	@Override
	public CampaignEventStatus remove(long statusId)
		throws NoSuchCampaignEventStatusException {
		return remove((Serializable)statusId);
	}

	/**
	 * Removes the campaign event status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the campaign event status
	 * @return the campaign event status that was removed
	 * @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	 */
	@Override
	public CampaignEventStatus remove(Serializable primaryKey)
		throws NoSuchCampaignEventStatusException {
		Session session = null;

		try {
			session = openSession();

			CampaignEventStatus campaignEventStatus = (CampaignEventStatus)session.get(CampaignEventStatusImpl.class,
					primaryKey);

			if (campaignEventStatus == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCampaignEventStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(campaignEventStatus);
		}
		catch (NoSuchCampaignEventStatusException nsee) {
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
	protected CampaignEventStatus removeImpl(
		CampaignEventStatus campaignEventStatus) {
		campaignEventStatus = toUnwrappedModel(campaignEventStatus);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(campaignEventStatus)) {
				campaignEventStatus = (CampaignEventStatus)session.get(CampaignEventStatusImpl.class,
						campaignEventStatus.getPrimaryKeyObj());
			}

			if (campaignEventStatus != null) {
				session.delete(campaignEventStatus);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (campaignEventStatus != null) {
			clearCache(campaignEventStatus);
		}

		return campaignEventStatus;
	}

	@Override
	public CampaignEventStatus updateImpl(
		CampaignEventStatus campaignEventStatus) {
		campaignEventStatus = toUnwrappedModel(campaignEventStatus);

		boolean isNew = campaignEventStatus.isNew();

		CampaignEventStatusModelImpl campaignEventStatusModelImpl = (CampaignEventStatusModelImpl)campaignEventStatus;

		if (Validator.isNull(campaignEventStatus.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			campaignEventStatus.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (campaignEventStatus.isNew()) {
				session.save(campaignEventStatus);

				campaignEventStatus.setNew(false);
			}
			else {
				campaignEventStatus = (CampaignEventStatus)session.merge(campaignEventStatus);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CampaignEventStatusModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { campaignEventStatusModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					campaignEventStatusModelImpl.getCampaignEventId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CAMPAIGNEVENTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNEVENTID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((campaignEventStatusModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						campaignEventStatusModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { campaignEventStatusModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((campaignEventStatusModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNEVENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						campaignEventStatusModelImpl.getOriginalCampaignEventId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CAMPAIGNEVENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNEVENTID,
					args);

				args = new Object[] {
						campaignEventStatusModelImpl.getCampaignEventId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CAMPAIGNEVENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNEVENTID,
					args);
			}
		}

		entityCache.putResult(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
			CampaignEventStatusImpl.class, campaignEventStatus.getPrimaryKey(),
			campaignEventStatus, false);

		campaignEventStatus.resetOriginalValues();

		return campaignEventStatus;
	}

	protected CampaignEventStatus toUnwrappedModel(
		CampaignEventStatus campaignEventStatus) {
		if (campaignEventStatus instanceof CampaignEventStatusImpl) {
			return campaignEventStatus;
		}

		CampaignEventStatusImpl campaignEventStatusImpl = new CampaignEventStatusImpl();

		campaignEventStatusImpl.setNew(campaignEventStatus.isNew());
		campaignEventStatusImpl.setPrimaryKey(campaignEventStatus.getPrimaryKey());

		campaignEventStatusImpl.setUuid(campaignEventStatus.getUuid());
		campaignEventStatusImpl.setStatusId(campaignEventStatus.getStatusId());
		campaignEventStatusImpl.setStatus(campaignEventStatus.getStatus());
		campaignEventStatusImpl.setComment(campaignEventStatus.getComment());
		campaignEventStatusImpl.setDeletionDenied(campaignEventStatus.getDeletionDenied());
		campaignEventStatusImpl.setDate(campaignEventStatus.getDate());
		campaignEventStatusImpl.setCampaignEventId(campaignEventStatus.getCampaignEventId());
		campaignEventStatusImpl.setPreviousStatusId(campaignEventStatus.getPreviousStatusId());
		campaignEventStatusImpl.setUserId(campaignEventStatus.getUserId());
		campaignEventStatusImpl.setUserName(campaignEventStatus.getUserName());

		return campaignEventStatusImpl;
	}

	/**
	 * Returns the campaign event status with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the campaign event status
	 * @return the campaign event status
	 * @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	 */
	@Override
	public CampaignEventStatus findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCampaignEventStatusException {
		CampaignEventStatus campaignEventStatus = fetchByPrimaryKey(primaryKey);

		if (campaignEventStatus == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCampaignEventStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return campaignEventStatus;
	}

	/**
	 * Returns the campaign event status with the primary key or throws a {@link NoSuchCampaignEventStatusException} if it could not be found.
	 *
	 * @param statusId the primary key of the campaign event status
	 * @return the campaign event status
	 * @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	 */
	@Override
	public CampaignEventStatus findByPrimaryKey(long statusId)
		throws NoSuchCampaignEventStatusException {
		return findByPrimaryKey((Serializable)statusId);
	}

	/**
	 * Returns the campaign event status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the campaign event status
	 * @return the campaign event status, or <code>null</code> if a campaign event status with the primary key could not be found
	 */
	@Override
	public CampaignEventStatus fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
				CampaignEventStatusImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CampaignEventStatus campaignEventStatus = (CampaignEventStatus)serializable;

		if (campaignEventStatus == null) {
			Session session = null;

			try {
				session = openSession();

				campaignEventStatus = (CampaignEventStatus)session.get(CampaignEventStatusImpl.class,
						primaryKey);

				if (campaignEventStatus != null) {
					cacheResult(campaignEventStatus);
				}
				else {
					entityCache.putResult(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
						CampaignEventStatusImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
					CampaignEventStatusImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return campaignEventStatus;
	}

	/**
	 * Returns the campaign event status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param statusId the primary key of the campaign event status
	 * @return the campaign event status, or <code>null</code> if a campaign event status with the primary key could not be found
	 */
	@Override
	public CampaignEventStatus fetchByPrimaryKey(long statusId) {
		return fetchByPrimaryKey((Serializable)statusId);
	}

	@Override
	public Map<Serializable, CampaignEventStatus> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CampaignEventStatus> map = new HashMap<Serializable, CampaignEventStatus>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CampaignEventStatus campaignEventStatus = fetchByPrimaryKey(primaryKey);

			if (campaignEventStatus != null) {
				map.put(primaryKey, campaignEventStatus);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
					CampaignEventStatusImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CampaignEventStatus)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CAMPAIGNEVENTSTATUS_WHERE_PKS_IN);

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

			for (CampaignEventStatus campaignEventStatus : (List<CampaignEventStatus>)q.list()) {
				map.put(campaignEventStatus.getPrimaryKeyObj(),
					campaignEventStatus);

				cacheResult(campaignEventStatus);

				uncachedPrimaryKeys.remove(campaignEventStatus.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CampaignEventStatusModelImpl.ENTITY_CACHE_ENABLED,
					CampaignEventStatusImpl.class, primaryKey, nullModel);
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
	 * Returns all the campaign event statuses.
	 *
	 * @return the campaign event statuses
	 */
	@Override
	public List<CampaignEventStatus> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the campaign event statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign event statuses
	 * @param end the upper bound of the range of campaign event statuses (not inclusive)
	 * @return the range of campaign event statuses
	 */
	@Override
	public List<CampaignEventStatus> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the campaign event statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign event statuses
	 * @param end the upper bound of the range of campaign event statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of campaign event statuses
	 */
	@Override
	public List<CampaignEventStatus> findAll(int start, int end,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the campaign event statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign event statuses
	 * @param end the upper bound of the range of campaign event statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of campaign event statuses
	 */
	@Override
	public List<CampaignEventStatus> findAll(int start, int end,
		OrderByComparator<CampaignEventStatus> orderByComparator,
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

		List<CampaignEventStatus> list = null;

		if (retrieveFromCache) {
			list = (List<CampaignEventStatus>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CAMPAIGNEVENTSTATUS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CAMPAIGNEVENTSTATUS;

				if (pagination) {
					sql = sql.concat(CampaignEventStatusModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CampaignEventStatus>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CampaignEventStatus>)QueryUtil.list(q,
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
	 * Removes all the campaign event statuses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CampaignEventStatus campaignEventStatus : findAll()) {
			remove(campaignEventStatus);
		}
	}

	/**
	 * Returns the number of campaign event statuses.
	 *
	 * @return the number of campaign event statuses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CAMPAIGNEVENTSTATUS);

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
		return CampaignEventStatusModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the campaign event status persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CampaignEventStatusImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CAMPAIGNEVENTSTATUS = "SELECT campaignEventStatus FROM CampaignEventStatus campaignEventStatus";
	private static final String _SQL_SELECT_CAMPAIGNEVENTSTATUS_WHERE_PKS_IN = "SELECT campaignEventStatus FROM CampaignEventStatus campaignEventStatus WHERE statusId IN (";
	private static final String _SQL_SELECT_CAMPAIGNEVENTSTATUS_WHERE = "SELECT campaignEventStatus FROM CampaignEventStatus campaignEventStatus WHERE ";
	private static final String _SQL_COUNT_CAMPAIGNEVENTSTATUS = "SELECT COUNT(campaignEventStatus) FROM CampaignEventStatus campaignEventStatus";
	private static final String _SQL_COUNT_CAMPAIGNEVENTSTATUS_WHERE = "SELECT COUNT(campaignEventStatus) FROM CampaignEventStatus campaignEventStatus WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "campaignEventStatus.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CampaignEventStatus exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CampaignEventStatus exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CampaignEventStatusPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "comment", "date"
			});
}