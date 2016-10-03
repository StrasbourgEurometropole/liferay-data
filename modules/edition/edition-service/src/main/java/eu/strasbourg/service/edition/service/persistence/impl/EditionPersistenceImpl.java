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

package eu.strasbourg.service.edition.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.edition.exception.NoSuchEditionException;
import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.impl.EditionImpl;
import eu.strasbourg.service.edition.model.impl.EditionModelImpl;
import eu.strasbourg.service.edition.service.persistence.EditionGalleryPersistence;
import eu.strasbourg.service.edition.service.persistence.EditionPersistence;

import java.io.Serializable;

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
 * The persistence implementation for the edition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see EditionPersistence
 * @see eu.strasbourg.service.edition.service.persistence.EditionUtil
 * @generated
 */
@ProviderType
public class EditionPersistenceImpl extends BasePersistenceImpl<Edition>
	implements EditionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EditionUtil} to access the edition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EditionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, EditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, EditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, EditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, EditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			EditionModelImpl.UUID_COLUMN_BITMASK |
			EditionModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the editions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching editions
	 */
	@Override
	public List<Edition> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the editions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of matching editions
	 */
	@Override
	public List<Edition> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the editions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching editions
	 */
	@Override
	public List<Edition> findByUuid(String uuid, int start, int end,
		OrderByComparator<Edition> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the editions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching editions
	 */
	@Override
	public List<Edition> findByUuid(String uuid, int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean retrieveFromCache) {
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

		List<Edition> list = null;

		if (retrieveFromCache) {
			list = (List<Edition>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Edition edition : list) {
					if (!Objects.equals(uuid, edition.getUuid())) {
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

			query.append(_SQL_SELECT_EDITION_WHERE);

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
				query.append(EditionModelImpl.ORDER_BY_JPQL);
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
					list = (List<Edition>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Edition>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first edition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	@Override
	public Edition findByUuid_First(String uuid,
		OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = fetchByUuid_First(uuid, orderByComparator);

		if (edition != null) {
			return edition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionException(msg.toString());
	}

	/**
	 * Returns the first edition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition, or <code>null</code> if a matching edition could not be found
	 */
	@Override
	public Edition fetchByUuid_First(String uuid,
		OrderByComparator<Edition> orderByComparator) {
		List<Edition> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last edition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	@Override
	public Edition findByUuid_Last(String uuid,
		OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = fetchByUuid_Last(uuid, orderByComparator);

		if (edition != null) {
			return edition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionException(msg.toString());
	}

	/**
	 * Returns the last edition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition, or <code>null</code> if a matching edition could not be found
	 */
	@Override
	public Edition fetchByUuid_Last(String uuid,
		OrderByComparator<Edition> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Edition> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the editions before and after the current edition in the ordered set where uuid = &#63;.
	 *
	 * @param editionId the primary key of the current edition
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	@Override
	public Edition[] findByUuid_PrevAndNext(long editionId, String uuid,
		OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = findByPrimaryKey(editionId);

		Session session = null;

		try {
			session = openSession();

			Edition[] array = new EditionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, edition, uuid,
					orderByComparator, true);

			array[1] = edition;

			array[2] = getByUuid_PrevAndNext(session, edition, uuid,
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

	protected Edition getByUuid_PrevAndNext(Session session, Edition edition,
		String uuid, OrderByComparator<Edition> orderByComparator,
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

		query.append(_SQL_SELECT_EDITION_WHERE);

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
			query.append(EditionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(edition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Edition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the editions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Edition edition : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(edition);
		}
	}

	/**
	 * Returns the number of editions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching editions
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EDITION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "edition.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "edition.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(edition.uuid IS NULL OR edition.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, EditionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			EditionModelImpl.UUID_COLUMN_BITMASK |
			EditionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the edition where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEditionException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	@Override
	public Edition findByUUID_G(String uuid, long groupId)
		throws NoSuchEditionException {
		Edition edition = fetchByUUID_G(uuid, groupId);

		if (edition == null) {
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

			throw new NoSuchEditionException(msg.toString());
		}

		return edition;
	}

	/**
	 * Returns the edition where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching edition, or <code>null</code> if a matching edition could not be found
	 */
	@Override
	public Edition fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the edition where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching edition, or <code>null</code> if a matching edition could not be found
	 */
	@Override
	public Edition fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Edition) {
			Edition edition = (Edition)result;

			if (!Objects.equals(uuid, edition.getUuid()) ||
					(groupId != edition.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_EDITION_WHERE);

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

				List<Edition> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Edition edition = list.get(0);

					result = edition;

					cacheResult(edition);

					if ((edition.getUuid() == null) ||
							!edition.getUuid().equals(uuid) ||
							(edition.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, edition);
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
			return (Edition)result;
		}
	}

	/**
	 * Removes the edition where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the edition that was removed
	 */
	@Override
	public Edition removeByUUID_G(String uuid, long groupId)
		throws NoSuchEditionException {
		Edition edition = findByUUID_G(uuid, groupId);

		return remove(edition);
	}

	/**
	 * Returns the number of editions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching editions
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EDITION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "edition.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "edition.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(edition.uuid IS NULL OR edition.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "edition.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, EditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, EditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			EditionModelImpl.UUID_COLUMN_BITMASK |
			EditionModelImpl.COMPANYID_COLUMN_BITMASK |
			EditionModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the editions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching editions
	 */
	@Override
	public List<Edition> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the editions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of matching editions
	 */
	@Override
	public List<Edition> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the editions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching editions
	 */
	@Override
	public List<Edition> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Edition> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the editions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching editions
	 */
	@Override
	public List<Edition> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Edition> orderByComparator,
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

		List<Edition> list = null;

		if (retrieveFromCache) {
			list = (List<Edition>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Edition edition : list) {
					if (!Objects.equals(uuid, edition.getUuid()) ||
							(companyId != edition.getCompanyId())) {
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

			query.append(_SQL_SELECT_EDITION_WHERE);

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
				query.append(EditionModelImpl.ORDER_BY_JPQL);
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
					list = (List<Edition>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Edition>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first edition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	@Override
	public Edition findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (edition != null) {
			return edition;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionException(msg.toString());
	}

	/**
	 * Returns the first edition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition, or <code>null</code> if a matching edition could not be found
	 */
	@Override
	public Edition fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Edition> orderByComparator) {
		List<Edition> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last edition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	@Override
	public Edition findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (edition != null) {
			return edition;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionException(msg.toString());
	}

	/**
	 * Returns the last edition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition, or <code>null</code> if a matching edition could not be found
	 */
	@Override
	public Edition fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Edition> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Edition> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the editions before and after the current edition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param editionId the primary key of the current edition
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	@Override
	public Edition[] findByUuid_C_PrevAndNext(long editionId, String uuid,
		long companyId, OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = findByPrimaryKey(editionId);

		Session session = null;

		try {
			session = openSession();

			Edition[] array = new EditionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, edition, uuid,
					companyId, orderByComparator, true);

			array[1] = edition;

			array[2] = getByUuid_C_PrevAndNext(session, edition, uuid,
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

	protected Edition getByUuid_C_PrevAndNext(Session session, Edition edition,
		String uuid, long companyId,
		OrderByComparator<Edition> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EDITION_WHERE);

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
			query.append(EditionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(edition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Edition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the editions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Edition edition : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(edition);
		}
	}

	/**
	 * Returns the number of editions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching editions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EDITION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "edition.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "edition.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(edition.uuid IS NULL OR edition.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "edition.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, EditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTitle",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TITLE = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, EditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTitle",
			new String[] { String.class.getName() },
			EditionModelImpl.TITLE_COLUMN_BITMASK |
			EditionModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TITLE = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTitle",
			new String[] { String.class.getName() });

	/**
	 * Returns all the editions where title = &#63;.
	 *
	 * @param title the title
	 * @return the matching editions
	 */
	@Override
	public List<Edition> findByTitle(String title) {
		return findByTitle(title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the editions where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of matching editions
	 */
	@Override
	public List<Edition> findByTitle(String title, int start, int end) {
		return findByTitle(title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the editions where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching editions
	 */
	@Override
	public List<Edition> findByTitle(String title, int start, int end,
		OrderByComparator<Edition> orderByComparator) {
		return findByTitle(title, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the editions where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching editions
	 */
	@Override
	public List<Edition> findByTitle(String title, int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TITLE;
			finderArgs = new Object[] { title };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE;
			finderArgs = new Object[] { title, start, end, orderByComparator };
		}

		List<Edition> list = null;

		if (retrieveFromCache) {
			list = (List<Edition>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Edition edition : list) {
					if (!Objects.equals(title, edition.getTitle())) {
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

			query.append(_SQL_SELECT_EDITION_WHERE);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_TITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EditionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
				}

				if (!pagination) {
					list = (List<Edition>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Edition>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first edition in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	@Override
	public Edition findByTitle_First(String title,
		OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = fetchByTitle_First(title, orderByComparator);

		if (edition != null) {
			return edition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionException(msg.toString());
	}

	/**
	 * Returns the first edition in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition, or <code>null</code> if a matching edition could not be found
	 */
	@Override
	public Edition fetchByTitle_First(String title,
		OrderByComparator<Edition> orderByComparator) {
		List<Edition> list = findByTitle(title, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last edition in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	@Override
	public Edition findByTitle_Last(String title,
		OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = fetchByTitle_Last(title, orderByComparator);

		if (edition != null) {
			return edition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionException(msg.toString());
	}

	/**
	 * Returns the last edition in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition, or <code>null</code> if a matching edition could not be found
	 */
	@Override
	public Edition fetchByTitle_Last(String title,
		OrderByComparator<Edition> orderByComparator) {
		int count = countByTitle(title);

		if (count == 0) {
			return null;
		}

		List<Edition> list = findByTitle(title, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the editions before and after the current edition in the ordered set where title = &#63;.
	 *
	 * @param editionId the primary key of the current edition
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	@Override
	public Edition[] findByTitle_PrevAndNext(long editionId, String title,
		OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = findByPrimaryKey(editionId);

		Session session = null;

		try {
			session = openSession();

			Edition[] array = new EditionImpl[3];

			array[0] = getByTitle_PrevAndNext(session, edition, title,
					orderByComparator, true);

			array[1] = edition;

			array[2] = getByTitle_PrevAndNext(session, edition, title,
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

	protected Edition getByTitle_PrevAndNext(Session session, Edition edition,
		String title, OrderByComparator<Edition> orderByComparator,
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

		query.append(_SQL_SELECT_EDITION_WHERE);

		boolean bindTitle = false;

		if (title == null) {
			query.append(_FINDER_COLUMN_TITLE_TITLE_1);
		}
		else if (title.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_TITLE_TITLE_3);
		}
		else {
			bindTitle = true;

			query.append(_FINDER_COLUMN_TITLE_TITLE_2);
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
			query.append(EditionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindTitle) {
			qPos.add(title);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(edition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Edition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the editions where title = &#63; from the database.
	 *
	 * @param title the title
	 */
	@Override
	public void removeByTitle(String title) {
		for (Edition edition : findByTitle(title, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(edition);
		}
	}

	/**
	 * Returns the number of editions where title = &#63;.
	 *
	 * @param title the title
	 * @return the number of matching editions
	 */
	@Override
	public int countByTitle(String title) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TITLE;

		Object[] finderArgs = new Object[] { title };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EDITION_WHERE);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_TITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
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

	private static final String _FINDER_COLUMN_TITLE_TITLE_1 = "edition.title IS NULL";
	private static final String _FINDER_COLUMN_TITLE_TITLE_2 = "edition.title = ?";
	private static final String _FINDER_COLUMN_TITLE_TITLE_3 = "(edition.title IS NULL OR edition.title = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, EditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, EditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			EditionModelImpl.GROUPID_COLUMN_BITMASK |
			EditionModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the editions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching editions
	 */
	@Override
	public List<Edition> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the editions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of matching editions
	 */
	@Override
	public List<Edition> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the editions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching editions
	 */
	@Override
	public List<Edition> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Edition> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the editions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching editions
	 */
	@Override
	public List<Edition> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean retrieveFromCache) {
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

		List<Edition> list = null;

		if (retrieveFromCache) {
			list = (List<Edition>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Edition edition : list) {
					if ((groupId != edition.getGroupId())) {
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

			query.append(_SQL_SELECT_EDITION_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EditionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Edition>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Edition>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first edition in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	@Override
	public Edition findByGroupId_First(long groupId,
		OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = fetchByGroupId_First(groupId, orderByComparator);

		if (edition != null) {
			return edition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionException(msg.toString());
	}

	/**
	 * Returns the first edition in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition, or <code>null</code> if a matching edition could not be found
	 */
	@Override
	public Edition fetchByGroupId_First(long groupId,
		OrderByComparator<Edition> orderByComparator) {
		List<Edition> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last edition in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	@Override
	public Edition findByGroupId_Last(long groupId,
		OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = fetchByGroupId_Last(groupId, orderByComparator);

		if (edition != null) {
			return edition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionException(msg.toString());
	}

	/**
	 * Returns the last edition in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition, or <code>null</code> if a matching edition could not be found
	 */
	@Override
	public Edition fetchByGroupId_Last(long groupId,
		OrderByComparator<Edition> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Edition> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the editions before and after the current edition in the ordered set where groupId = &#63;.
	 *
	 * @param editionId the primary key of the current edition
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	@Override
	public Edition[] findByGroupId_PrevAndNext(long editionId, long groupId,
		OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = findByPrimaryKey(editionId);

		Session session = null;

		try {
			session = openSession();

			Edition[] array = new EditionImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, edition, groupId,
					orderByComparator, true);

			array[1] = edition;

			array[2] = getByGroupId_PrevAndNext(session, edition, groupId,
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

	protected Edition getByGroupId_PrevAndNext(Session session,
		Edition edition, long groupId,
		OrderByComparator<Edition> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EDITION_WHERE);

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
			query.append(EditionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(edition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Edition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the editions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (Edition edition : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(edition);
		}
	}

	/**
	 * Returns the number of editions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching editions
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EDITION_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "edition.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDTITLE =
		new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, EditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupIdAndTitle",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDTITLE =
		new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, EditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupIdAndTitle",
			new String[] { Long.class.getName(), String.class.getName() },
			EditionModelImpl.GROUPID_COLUMN_BITMASK |
			EditionModelImpl.TITLE_COLUMN_BITMASK |
			EditionModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDTITLE = new FinderPath(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndTitle",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the editions where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the matching editions
	 */
	@Override
	public List<Edition> findByGroupIdAndTitle(long groupId, String title) {
		return findByGroupIdAndTitle(groupId, title, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the editions where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of matching editions
	 */
	@Override
	public List<Edition> findByGroupIdAndTitle(long groupId, String title,
		int start, int end) {
		return findByGroupIdAndTitle(groupId, title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the editions where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching editions
	 */
	@Override
	public List<Edition> findByGroupIdAndTitle(long groupId, String title,
		int start, int end, OrderByComparator<Edition> orderByComparator) {
		return findByGroupIdAndTitle(groupId, title, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the editions where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching editions
	 */
	@Override
	public List<Edition> findByGroupIdAndTitle(long groupId, String title,
		int start, int end, OrderByComparator<Edition> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDTITLE;
			finderArgs = new Object[] { groupId, title };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDTITLE;
			finderArgs = new Object[] {
					groupId, title,
					
					start, end, orderByComparator
				};
		}

		List<Edition> list = null;

		if (retrieveFromCache) {
			list = (List<Edition>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Edition edition : list) {
					if ((groupId != edition.getGroupId()) ||
							!Objects.equals(title, edition.getTitle())) {
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

			query.append(_SQL_SELECT_EDITION_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDTITLE_GROUPID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_GROUPIDANDTITLE_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUPIDANDTITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_GROUPIDANDTITLE_TITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EditionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTitle) {
					qPos.add(title);
				}

				if (!pagination) {
					list = (List<Edition>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Edition>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first edition in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	@Override
	public Edition findByGroupIdAndTitle_First(long groupId, String title,
		OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = fetchByGroupIdAndTitle_First(groupId, title,
				orderByComparator);

		if (edition != null) {
			return edition;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionException(msg.toString());
	}

	/**
	 * Returns the first edition in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition, or <code>null</code> if a matching edition could not be found
	 */
	@Override
	public Edition fetchByGroupIdAndTitle_First(long groupId, String title,
		OrderByComparator<Edition> orderByComparator) {
		List<Edition> list = findByGroupIdAndTitle(groupId, title, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last edition in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	@Override
	public Edition findByGroupIdAndTitle_Last(long groupId, String title,
		OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = fetchByGroupIdAndTitle_Last(groupId, title,
				orderByComparator);

		if (edition != null) {
			return edition;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionException(msg.toString());
	}

	/**
	 * Returns the last edition in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition, or <code>null</code> if a matching edition could not be found
	 */
	@Override
	public Edition fetchByGroupIdAndTitle_Last(long groupId, String title,
		OrderByComparator<Edition> orderByComparator) {
		int count = countByGroupIdAndTitle(groupId, title);

		if (count == 0) {
			return null;
		}

		List<Edition> list = findByGroupIdAndTitle(groupId, title, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the editions before and after the current edition in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param editionId the primary key of the current edition
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	@Override
	public Edition[] findByGroupIdAndTitle_PrevAndNext(long editionId,
		long groupId, String title, OrderByComparator<Edition> orderByComparator)
		throws NoSuchEditionException {
		Edition edition = findByPrimaryKey(editionId);

		Session session = null;

		try {
			session = openSession();

			Edition[] array = new EditionImpl[3];

			array[0] = getByGroupIdAndTitle_PrevAndNext(session, edition,
					groupId, title, orderByComparator, true);

			array[1] = edition;

			array[2] = getByGroupIdAndTitle_PrevAndNext(session, edition,
					groupId, title, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Edition getByGroupIdAndTitle_PrevAndNext(Session session,
		Edition edition, long groupId, String title,
		OrderByComparator<Edition> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EDITION_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDTITLE_GROUPID_2);

		boolean bindTitle = false;

		if (title == null) {
			query.append(_FINDER_COLUMN_GROUPIDANDTITLE_TITLE_1);
		}
		else if (title.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPIDANDTITLE_TITLE_3);
		}
		else {
			bindTitle = true;

			query.append(_FINDER_COLUMN_GROUPIDANDTITLE_TITLE_2);
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
			query.append(EditionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindTitle) {
			qPos.add(title);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(edition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Edition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the editions where groupId = &#63; and title = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 */
	@Override
	public void removeByGroupIdAndTitle(long groupId, String title) {
		for (Edition edition : findByGroupIdAndTitle(groupId, title,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(edition);
		}
	}

	/**
	 * Returns the number of editions where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the number of matching editions
	 */
	@Override
	public int countByGroupIdAndTitle(long groupId, String title) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDTITLE;

		Object[] finderArgs = new Object[] { groupId, title };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EDITION_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDTITLE_GROUPID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_GROUPIDANDTITLE_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUPIDANDTITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_GROUPIDANDTITLE_TITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTitle) {
					qPos.add(title);
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

	private static final String _FINDER_COLUMN_GROUPIDANDTITLE_GROUPID_2 = "edition.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDTITLE_TITLE_1 = "edition.title IS NULL";
	private static final String _FINDER_COLUMN_GROUPIDANDTITLE_TITLE_2 = "edition.title = ?";
	private static final String _FINDER_COLUMN_GROUPIDANDTITLE_TITLE_3 = "(edition.title IS NULL OR edition.title = '')";

	public EditionPersistenceImpl() {
		setModelClass(Edition.class);
	}

	/**
	 * Caches the edition in the entity cache if it is enabled.
	 *
	 * @param edition the edition
	 */
	@Override
	public void cacheResult(Edition edition) {
		entityCache.putResult(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionImpl.class, edition.getPrimaryKey(), edition);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { edition.getUuid(), edition.getGroupId() }, edition);

		edition.resetOriginalValues();
	}

	/**
	 * Caches the editions in the entity cache if it is enabled.
	 *
	 * @param editions the editions
	 */
	@Override
	public void cacheResult(List<Edition> editions) {
		for (Edition edition : editions) {
			if (entityCache.getResult(EditionModelImpl.ENTITY_CACHE_ENABLED,
						EditionImpl.class, edition.getPrimaryKey()) == null) {
				cacheResult(edition);
			}
			else {
				edition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all editions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EditionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the edition.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Edition edition) {
		entityCache.removeResult(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionImpl.class, edition.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((EditionModelImpl)edition);
	}

	@Override
	public void clearCache(List<Edition> editions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Edition edition : editions) {
			entityCache.removeResult(EditionModelImpl.ENTITY_CACHE_ENABLED,
				EditionImpl.class, edition.getPrimaryKey());

			clearUniqueFindersCache((EditionModelImpl)edition);
		}
	}

	protected void cacheUniqueFindersCache(EditionModelImpl editionModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					editionModelImpl.getUuid(), editionModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				editionModelImpl);
		}
		else {
			if ((editionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						editionModelImpl.getUuid(),
						editionModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					editionModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(EditionModelImpl editionModelImpl) {
		Object[] args = new Object[] {
				editionModelImpl.getUuid(), editionModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((editionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					editionModelImpl.getOriginalUuid(),
					editionModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new edition with the primary key. Does not add the edition to the database.
	 *
	 * @param editionId the primary key for the new edition
	 * @return the new edition
	 */
	@Override
	public Edition create(long editionId) {
		Edition edition = new EditionImpl();

		edition.setNew(true);
		edition.setPrimaryKey(editionId);

		String uuid = PortalUUIDUtil.generate();

		edition.setUuid(uuid);

		edition.setCompanyId(companyProvider.getCompanyId());

		return edition;
	}

	/**
	 * Removes the edition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param editionId the primary key of the edition
	 * @return the edition that was removed
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	@Override
	public Edition remove(long editionId) throws NoSuchEditionException {
		return remove((Serializable)editionId);
	}

	/**
	 * Removes the edition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the edition
	 * @return the edition that was removed
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	@Override
	public Edition remove(Serializable primaryKey)
		throws NoSuchEditionException {
		Session session = null;

		try {
			session = openSession();

			Edition edition = (Edition)session.get(EditionImpl.class, primaryKey);

			if (edition == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEditionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(edition);
		}
		catch (NoSuchEditionException nsee) {
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
	protected Edition removeImpl(Edition edition) {
		edition = toUnwrappedModel(edition);

		editionToEditionGalleryTableMapper.deleteLeftPrimaryKeyTableMappings(edition.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(edition)) {
				edition = (Edition)session.get(EditionImpl.class,
						edition.getPrimaryKeyObj());
			}

			if (edition != null) {
				session.delete(edition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (edition != null) {
			clearCache(edition);
		}

		return edition;
	}

	@Override
	public Edition updateImpl(Edition edition) {
		edition = toUnwrappedModel(edition);

		boolean isNew = edition.isNew();

		EditionModelImpl editionModelImpl = (EditionModelImpl)edition;

		if (Validator.isNull(edition.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			edition.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (edition.getCreateDate() == null)) {
			if (serviceContext == null) {
				edition.setCreateDate(now);
			}
			else {
				edition.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!editionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				edition.setModifiedDate(now);
			}
			else {
				edition.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (edition.isNew()) {
				session.save(edition);

				edition.setNew(false);
			}
			else {
				edition = (Edition)session.merge(edition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !EditionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((editionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { editionModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { editionModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((editionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						editionModelImpl.getOriginalUuid(),
						editionModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						editionModelImpl.getUuid(),
						editionModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((editionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TITLE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { editionModelImpl.getOriginalTitle() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TITLE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TITLE,
					args);

				args = new Object[] { editionModelImpl.getTitle() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TITLE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TITLE,
					args);
			}

			if ((editionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						editionModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { editionModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((editionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDTITLE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						editionModelImpl.getOriginalGroupId(),
						editionModelImpl.getOriginalTitle()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDTITLE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDTITLE,
					args);

				args = new Object[] {
						editionModelImpl.getGroupId(),
						editionModelImpl.getTitle()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDTITLE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDTITLE,
					args);
			}
		}

		entityCache.putResult(EditionModelImpl.ENTITY_CACHE_ENABLED,
			EditionImpl.class, edition.getPrimaryKey(), edition, false);

		clearUniqueFindersCache(editionModelImpl);
		cacheUniqueFindersCache(editionModelImpl, isNew);

		edition.resetOriginalValues();

		return edition;
	}

	protected Edition toUnwrappedModel(Edition edition) {
		if (edition instanceof EditionImpl) {
			return edition;
		}

		EditionImpl editionImpl = new EditionImpl();

		editionImpl.setNew(edition.isNew());
		editionImpl.setPrimaryKey(edition.getPrimaryKey());

		editionImpl.setUuid(edition.getUuid());
		editionImpl.setEditionId(edition.getEditionId());
		editionImpl.setGroupId(edition.getGroupId());
		editionImpl.setCompanyId(edition.getCompanyId());
		editionImpl.setUserId(edition.getUserId());
		editionImpl.setUserName(edition.getUserName());
		editionImpl.setCreateDate(edition.getCreateDate());
		editionImpl.setModifiedDate(edition.getModifiedDate());
		editionImpl.setLastPublishDate(edition.getLastPublishDate());
		editionImpl.setTitle(edition.getTitle());
		editionImpl.setSubtitle(edition.getSubtitle());
		editionImpl.setDescription(edition.getDescription());
		editionImpl.setURL(edition.getURL());
		editionImpl.setAuthor(edition.getAuthor());
		editionImpl.setEditor(edition.getEditor());
		editionImpl.setDistribution(edition.getDistribution());
		editionImpl.setISBN(edition.getISBN());
		editionImpl.setPrice(edition.getPrice());
		editionImpl.setAvailableForExchange(edition.isAvailableForExchange());
		editionImpl.setInStock(edition.isInStock());
		editionImpl.setDiffusionDate(edition.getDiffusionDate());
		editionImpl.setPageNumber(edition.getPageNumber());
		editionImpl.setPictureNumber(edition.getPictureNumber());
		editionImpl.setPublicationDate(edition.getPublicationDate());
		editionImpl.setStatus(edition.isStatus());
		editionImpl.setImageId(edition.getImageId());
		editionImpl.setFileId(edition.getFileId());

		return editionImpl;
	}

	/**
	 * Returns the edition with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the edition
	 * @return the edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	@Override
	public Edition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEditionException {
		Edition edition = fetchByPrimaryKey(primaryKey);

		if (edition == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEditionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return edition;
	}

	/**
	 * Returns the edition with the primary key or throws a {@link NoSuchEditionException} if it could not be found.
	 *
	 * @param editionId the primary key of the edition
	 * @return the edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	@Override
	public Edition findByPrimaryKey(long editionId)
		throws NoSuchEditionException {
		return findByPrimaryKey((Serializable)editionId);
	}

	/**
	 * Returns the edition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the edition
	 * @return the edition, or <code>null</code> if a edition with the primary key could not be found
	 */
	@Override
	public Edition fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EditionModelImpl.ENTITY_CACHE_ENABLED,
				EditionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Edition edition = (Edition)serializable;

		if (edition == null) {
			Session session = null;

			try {
				session = openSession();

				edition = (Edition)session.get(EditionImpl.class, primaryKey);

				if (edition != null) {
					cacheResult(edition);
				}
				else {
					entityCache.putResult(EditionModelImpl.ENTITY_CACHE_ENABLED,
						EditionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EditionModelImpl.ENTITY_CACHE_ENABLED,
					EditionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return edition;
	}

	/**
	 * Returns the edition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param editionId the primary key of the edition
	 * @return the edition, or <code>null</code> if a edition with the primary key could not be found
	 */
	@Override
	public Edition fetchByPrimaryKey(long editionId) {
		return fetchByPrimaryKey((Serializable)editionId);
	}

	@Override
	public Map<Serializable, Edition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Edition> map = new HashMap<Serializable, Edition>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Edition edition = fetchByPrimaryKey(primaryKey);

			if (edition != null) {
				map.put(primaryKey, edition);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EditionModelImpl.ENTITY_CACHE_ENABLED,
					EditionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Edition)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_EDITION_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Edition edition : (List<Edition>)q.list()) {
				map.put(edition.getPrimaryKeyObj(), edition);

				cacheResult(edition);

				uncachedPrimaryKeys.remove(edition.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EditionModelImpl.ENTITY_CACHE_ENABLED,
					EditionImpl.class, primaryKey, nullModel);
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
	 * Returns all the editions.
	 *
	 * @return the editions
	 */
	@Override
	public List<Edition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the editions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of editions
	 */
	@Override
	public List<Edition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the editions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of editions
	 */
	@Override
	public List<Edition> findAll(int start, int end,
		OrderByComparator<Edition> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the editions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of editions
	 */
	@Override
	public List<Edition> findAll(int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean retrieveFromCache) {
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

		List<Edition> list = null;

		if (retrieveFromCache) {
			list = (List<Edition>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_EDITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EDITION;

				if (pagination) {
					sql = sql.concat(EditionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Edition>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Edition>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the editions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Edition edition : findAll()) {
			remove(edition);
		}
	}

	/**
	 * Returns the number of editions.
	 *
	 * @return the number of editions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EDITION);

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

	/**
	 * Returns the primaryKeys of edition galleries associated with the edition.
	 *
	 * @param pk the primary key of the edition
	 * @return long[] of the primaryKeys of edition galleries associated with the edition
	 */
	@Override
	public long[] getEditionGalleryPrimaryKeys(long pk) {
		long[] pks = editionToEditionGalleryTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the edition galleries associated with the edition.
	 *
	 * @param pk the primary key of the edition
	 * @return the edition galleries associated with the edition
	 */
	@Override
	public List<eu.strasbourg.service.edition.model.EditionGallery> getEditionGalleries(
		long pk) {
		return getEditionGalleries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the edition galleries associated with the edition.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the edition
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of edition galleries associated with the edition
	 */
	@Override
	public List<eu.strasbourg.service.edition.model.EditionGallery> getEditionGalleries(
		long pk, int start, int end) {
		return getEditionGalleries(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the edition galleries associated with the edition.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the edition
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of edition galleries associated with the edition
	 */
	@Override
	public List<eu.strasbourg.service.edition.model.EditionGallery> getEditionGalleries(
		long pk, int start, int end,
		OrderByComparator<eu.strasbourg.service.edition.model.EditionGallery> orderByComparator) {
		return editionToEditionGalleryTableMapper.getRightBaseModels(pk, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of edition galleries associated with the edition.
	 *
	 * @param pk the primary key of the edition
	 * @return the number of edition galleries associated with the edition
	 */
	@Override
	public int getEditionGalleriesSize(long pk) {
		long[] pks = editionToEditionGalleryTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the edition gallery is associated with the edition.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleryPK the primary key of the edition gallery
	 * @return <code>true</code> if the edition gallery is associated with the edition; <code>false</code> otherwise
	 */
	@Override
	public boolean containsEditionGallery(long pk, long editionGalleryPK) {
		return editionToEditionGalleryTableMapper.containsTableMapping(pk,
			editionGalleryPK);
	}

	/**
	 * Returns <code>true</code> if the edition has any edition galleries associated with it.
	 *
	 * @param pk the primary key of the edition to check for associations with edition galleries
	 * @return <code>true</code> if the edition has any edition galleries associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsEditionGalleries(long pk) {
		if (getEditionGalleriesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the edition and the edition gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleryPK the primary key of the edition gallery
	 */
	@Override
	public void addEditionGallery(long pk, long editionGalleryPK) {
		Edition edition = fetchByPrimaryKey(pk);

		if (edition == null) {
			editionToEditionGalleryTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, editionGalleryPK);
		}
		else {
			editionToEditionGalleryTableMapper.addTableMapping(edition.getCompanyId(),
				pk, editionGalleryPK);
		}
	}

	/**
	 * Adds an association between the edition and the edition gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGallery the edition gallery
	 */
	@Override
	public void addEditionGallery(long pk,
		eu.strasbourg.service.edition.model.EditionGallery editionGallery) {
		Edition edition = fetchByPrimaryKey(pk);

		if (edition == null) {
			editionToEditionGalleryTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, editionGallery.getPrimaryKey());
		}
		else {
			editionToEditionGalleryTableMapper.addTableMapping(edition.getCompanyId(),
				pk, editionGallery.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the edition and the edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleryPKs the primary keys of the edition galleries
	 */
	@Override
	public void addEditionGalleries(long pk, long[] editionGalleryPKs) {
		long companyId = 0;

		Edition edition = fetchByPrimaryKey(pk);

		if (edition == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = edition.getCompanyId();
		}

		editionToEditionGalleryTableMapper.addTableMappings(companyId, pk,
			editionGalleryPKs);
	}

	/**
	 * Adds an association between the edition and the edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleries the edition galleries
	 */
	@Override
	public void addEditionGalleries(long pk,
		List<eu.strasbourg.service.edition.model.EditionGallery> editionGalleries) {
		addEditionGalleries(pk,
			ListUtil.toLongArray(editionGalleries,
				eu.strasbourg.service.edition.model.EditionGallery.GALLERY_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the edition and its edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition to clear the associated edition galleries from
	 */
	@Override
	public void clearEditionGalleries(long pk) {
		editionToEditionGalleryTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the edition and the edition gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleryPK the primary key of the edition gallery
	 */
	@Override
	public void removeEditionGallery(long pk, long editionGalleryPK) {
		editionToEditionGalleryTableMapper.deleteTableMapping(pk,
			editionGalleryPK);
	}

	/**
	 * Removes the association between the edition and the edition gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGallery the edition gallery
	 */
	@Override
	public void removeEditionGallery(long pk,
		eu.strasbourg.service.edition.model.EditionGallery editionGallery) {
		editionToEditionGalleryTableMapper.deleteTableMapping(pk,
			editionGallery.getPrimaryKey());
	}

	/**
	 * Removes the association between the edition and the edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleryPKs the primary keys of the edition galleries
	 */
	@Override
	public void removeEditionGalleries(long pk, long[] editionGalleryPKs) {
		editionToEditionGalleryTableMapper.deleteTableMappings(pk,
			editionGalleryPKs);
	}

	/**
	 * Removes the association between the edition and the edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleries the edition galleries
	 */
	@Override
	public void removeEditionGalleries(long pk,
		List<eu.strasbourg.service.edition.model.EditionGallery> editionGalleries) {
		removeEditionGalleries(pk,
			ListUtil.toLongArray(editionGalleries,
				eu.strasbourg.service.edition.model.EditionGallery.GALLERY_ID_ACCESSOR));
	}

	/**
	 * Sets the edition galleries associated with the edition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleryPKs the primary keys of the edition galleries to be associated with the edition
	 */
	@Override
	public void setEditionGalleries(long pk, long[] editionGalleryPKs) {
		Set<Long> newEditionGalleryPKsSet = SetUtil.fromArray(editionGalleryPKs);
		Set<Long> oldEditionGalleryPKsSet = SetUtil.fromArray(editionToEditionGalleryTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeEditionGalleryPKsSet = new HashSet<Long>(oldEditionGalleryPKsSet);

		removeEditionGalleryPKsSet.removeAll(newEditionGalleryPKsSet);

		editionToEditionGalleryTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeEditionGalleryPKsSet));

		newEditionGalleryPKsSet.removeAll(oldEditionGalleryPKsSet);

		long companyId = 0;

		Edition edition = fetchByPrimaryKey(pk);

		if (edition == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = edition.getCompanyId();
		}

		editionToEditionGalleryTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newEditionGalleryPKsSet));
	}

	/**
	 * Sets the edition galleries associated with the edition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleries the edition galleries to be associated with the edition
	 */
	@Override
	public void setEditionGalleries(long pk,
		List<eu.strasbourg.service.edition.model.EditionGallery> editionGalleries) {
		try {
			long[] editionGalleryPKs = new long[editionGalleries.size()];

			for (int i = 0; i < editionGalleries.size(); i++) {
				eu.strasbourg.service.edition.model.EditionGallery editionGallery =
					editionGalleries.get(i);

				editionGalleryPKs[i] = editionGallery.getPrimaryKey();
			}

			setEditionGalleries(pk, editionGalleryPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EditionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the edition persistence.
	 */
	public void afterPropertiesSet() {
		editionToEditionGalleryTableMapper = TableMapperFactory.getTableMapper("edition_EditionToEditionGallery",
				"companyId", "editionId", "galleryId", this,
				editionGalleryPersistence);
	}

	public void destroy() {
		entityCache.removeCache(EditionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("edition_EditionToEditionGallery");
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	@BeanReference(type = EditionGalleryPersistence.class)
	protected EditionGalleryPersistence editionGalleryPersistence;
	protected TableMapper<Edition, eu.strasbourg.service.edition.model.EditionGallery> editionToEditionGalleryTableMapper;
	private static final String _SQL_SELECT_EDITION = "SELECT edition FROM Edition edition";
	private static final String _SQL_SELECT_EDITION_WHERE_PKS_IN = "SELECT edition FROM Edition edition WHERE editionId IN (";
	private static final String _SQL_SELECT_EDITION_WHERE = "SELECT edition FROM Edition edition WHERE ";
	private static final String _SQL_COUNT_EDITION = "SELECT COUNT(edition) FROM Edition edition";
	private static final String _SQL_COUNT_EDITION_WHERE = "SELECT COUNT(edition) FROM Edition edition WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "edition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Edition exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Edition exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(EditionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}