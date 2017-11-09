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
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.model.impl.EditionGalleryImpl;
import eu.strasbourg.service.edition.model.impl.EditionGalleryModelImpl;
import eu.strasbourg.service.edition.service.persistence.EditionGalleryPersistence;
import eu.strasbourg.service.edition.service.persistence.EditionPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.sql.Timestamp;

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
 * The persistence implementation for the edition gallery service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see EditionGalleryPersistence
 * @see eu.strasbourg.service.edition.service.persistence.EditionGalleryUtil
 * @generated
 */
@ProviderType
public class EditionGalleryPersistenceImpl extends BasePersistenceImpl<EditionGallery>
	implements EditionGalleryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EditionGalleryUtil} to access the edition gallery persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EditionGalleryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED,
			EditionGalleryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED,
			EditionGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED,
			EditionGalleryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED,
			EditionGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			EditionGalleryModelImpl.UUID_COLUMN_BITMASK |
			EditionGalleryModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the edition galleries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the edition galleries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @return the range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the edition galleries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByUuid(String uuid, int start, int end,
		OrderByComparator<EditionGallery> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the edition galleries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByUuid(String uuid, int start, int end,
		OrderByComparator<EditionGallery> orderByComparator,
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

		List<EditionGallery> list = null;

		if (retrieveFromCache) {
			list = (List<EditionGallery>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EditionGallery editionGallery : list) {
					if (!Objects.equals(uuid, editionGallery.getUuid())) {
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

			query.append(_SQL_SELECT_EDITIONGALLERY_WHERE);

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
				query.append(EditionGalleryModelImpl.ORDER_BY_JPQL);
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
					list = (List<EditionGallery>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EditionGallery>)QueryUtil.list(q,
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
	 * Returns the first edition gallery in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition gallery
	 * @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery findByUuid_First(String uuid,
		OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = fetchByUuid_First(uuid,
				orderByComparator);

		if (editionGallery != null) {
			return editionGallery;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionGalleryException(msg.toString());
	}

	/**
	 * Returns the first edition gallery in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery fetchByUuid_First(String uuid,
		OrderByComparator<EditionGallery> orderByComparator) {
		List<EditionGallery> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last edition gallery in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition gallery
	 * @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery findByUuid_Last(String uuid,
		OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = fetchByUuid_Last(uuid, orderByComparator);

		if (editionGallery != null) {
			return editionGallery;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionGalleryException(msg.toString());
	}

	/**
	 * Returns the last edition gallery in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery fetchByUuid_Last(String uuid,
		OrderByComparator<EditionGallery> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<EditionGallery> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the edition galleries before and after the current edition gallery in the ordered set where uuid = &#63;.
	 *
	 * @param galleryId the primary key of the current edition gallery
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition gallery
	 * @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	 */
	@Override
	public EditionGallery[] findByUuid_PrevAndNext(long galleryId, String uuid,
		OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = findByPrimaryKey(galleryId);

		Session session = null;

		try {
			session = openSession();

			EditionGallery[] array = new EditionGalleryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, editionGallery, uuid,
					orderByComparator, true);

			array[1] = editionGallery;

			array[2] = getByUuid_PrevAndNext(session, editionGallery, uuid,
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

	protected EditionGallery getByUuid_PrevAndNext(Session session,
		EditionGallery editionGallery, String uuid,
		OrderByComparator<EditionGallery> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EDITIONGALLERY_WHERE);

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
			query.append(EditionGalleryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(editionGallery);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EditionGallery> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the edition galleries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (EditionGallery editionGallery : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(editionGallery);
		}
	}

	/**
	 * Returns the number of edition galleries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching edition galleries
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EDITIONGALLERY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "editionGallery.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "editionGallery.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(editionGallery.uuid IS NULL OR editionGallery.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED,
			EditionGalleryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			EditionGalleryModelImpl.UUID_COLUMN_BITMASK |
			EditionGalleryModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the edition gallery where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEditionGalleryException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching edition gallery
	 * @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery findByUUID_G(String uuid, long groupId)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = fetchByUUID_G(uuid, groupId);

		if (editionGallery == null) {
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

			throw new NoSuchEditionGalleryException(msg.toString());
		}

		return editionGallery;
	}

	/**
	 * Returns the edition gallery where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the edition gallery where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof EditionGallery) {
			EditionGallery editionGallery = (EditionGallery)result;

			if (!Objects.equals(uuid, editionGallery.getUuid()) ||
					(groupId != editionGallery.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_EDITIONGALLERY_WHERE);

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

				List<EditionGallery> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					EditionGallery editionGallery = list.get(0);

					result = editionGallery;

					cacheResult(editionGallery);

					if ((editionGallery.getUuid() == null) ||
							!editionGallery.getUuid().equals(uuid) ||
							(editionGallery.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, editionGallery);
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
			return (EditionGallery)result;
		}
	}

	/**
	 * Removes the edition gallery where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the edition gallery that was removed
	 */
	@Override
	public EditionGallery removeByUUID_G(String uuid, long groupId)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = findByUUID_G(uuid, groupId);

		return remove(editionGallery);
	}

	/**
	 * Returns the number of edition galleries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching edition galleries
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EDITIONGALLERY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "editionGallery.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "editionGallery.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(editionGallery.uuid IS NULL OR editionGallery.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "editionGallery.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED,
			EditionGalleryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED,
			EditionGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			EditionGalleryModelImpl.UUID_COLUMN_BITMASK |
			EditionGalleryModelImpl.COMPANYID_COLUMN_BITMASK |
			EditionGalleryModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the edition galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the edition galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @return the range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the edition galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<EditionGallery> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the edition galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<EditionGallery> orderByComparator,
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

		List<EditionGallery> list = null;

		if (retrieveFromCache) {
			list = (List<EditionGallery>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EditionGallery editionGallery : list) {
					if (!Objects.equals(uuid, editionGallery.getUuid()) ||
							(companyId != editionGallery.getCompanyId())) {
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

			query.append(_SQL_SELECT_EDITIONGALLERY_WHERE);

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
				query.append(EditionGalleryModelImpl.ORDER_BY_JPQL);
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
					list = (List<EditionGallery>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EditionGallery>)QueryUtil.list(q,
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
	 * Returns the first edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition gallery
	 * @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (editionGallery != null) {
			return editionGallery;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionGalleryException(msg.toString());
	}

	/**
	 * Returns the first edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<EditionGallery> orderByComparator) {
		List<EditionGallery> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition gallery
	 * @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (editionGallery != null) {
			return editionGallery;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionGalleryException(msg.toString());
	}

	/**
	 * Returns the last edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<EditionGallery> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<EditionGallery> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the edition galleries before and after the current edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param galleryId the primary key of the current edition gallery
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition gallery
	 * @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	 */
	@Override
	public EditionGallery[] findByUuid_C_PrevAndNext(long galleryId,
		String uuid, long companyId,
		OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = findByPrimaryKey(galleryId);

		Session session = null;

		try {
			session = openSession();

			EditionGallery[] array = new EditionGalleryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, editionGallery, uuid,
					companyId, orderByComparator, true);

			array[1] = editionGallery;

			array[2] = getByUuid_C_PrevAndNext(session, editionGallery, uuid,
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

	protected EditionGallery getByUuid_C_PrevAndNext(Session session,
		EditionGallery editionGallery, String uuid, long companyId,
		OrderByComparator<EditionGallery> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EDITIONGALLERY_WHERE);

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
			query.append(EditionGalleryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(editionGallery);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EditionGallery> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the edition galleries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (EditionGallery editionGallery : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(editionGallery);
		}
	}

	/**
	 * Returns the number of edition galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching edition galleries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EDITIONGALLERY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "editionGallery.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "editionGallery.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(editionGallery.uuid IS NULL OR editionGallery.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "editionGallery.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED,
			EditionGalleryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBytitle",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TITLE = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED,
			EditionGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBytitle",
			new String[] { String.class.getName() },
			EditionGalleryModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TITLE = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBytitle",
			new String[] { String.class.getName() });

	/**
	 * Returns all the edition galleries where title = &#63;.
	 *
	 * @param title the title
	 * @return the matching edition galleries
	 */
	@Override
	public List<EditionGallery> findBytitle(String title) {
		return findBytitle(title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the edition galleries where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @return the range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findBytitle(String title, int start, int end) {
		return findBytitle(title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the edition galleries where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findBytitle(String title, int start, int end,
		OrderByComparator<EditionGallery> orderByComparator) {
		return findBytitle(title, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the edition galleries where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findBytitle(String title, int start, int end,
		OrderByComparator<EditionGallery> orderByComparator,
		boolean retrieveFromCache) {
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

		List<EditionGallery> list = null;

		if (retrieveFromCache) {
			list = (List<EditionGallery>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EditionGallery editionGallery : list) {
					if (!Objects.equals(title, editionGallery.getTitle())) {
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

			query.append(_SQL_SELECT_EDITIONGALLERY_WHERE);

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
				query.append(EditionGalleryModelImpl.ORDER_BY_JPQL);
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
					list = (List<EditionGallery>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EditionGallery>)QueryUtil.list(q,
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
	 * Returns the first edition gallery in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition gallery
	 * @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery findBytitle_First(String title,
		OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = fetchBytitle_First(title,
				orderByComparator);

		if (editionGallery != null) {
			return editionGallery;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionGalleryException(msg.toString());
	}

	/**
	 * Returns the first edition gallery in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery fetchBytitle_First(String title,
		OrderByComparator<EditionGallery> orderByComparator) {
		List<EditionGallery> list = findBytitle(title, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last edition gallery in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition gallery
	 * @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery findBytitle_Last(String title,
		OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = fetchBytitle_Last(title,
				orderByComparator);

		if (editionGallery != null) {
			return editionGallery;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionGalleryException(msg.toString());
	}

	/**
	 * Returns the last edition gallery in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery fetchBytitle_Last(String title,
		OrderByComparator<EditionGallery> orderByComparator) {
		int count = countBytitle(title);

		if (count == 0) {
			return null;
		}

		List<EditionGallery> list = findBytitle(title, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the edition galleries before and after the current edition gallery in the ordered set where title = &#63;.
	 *
	 * @param galleryId the primary key of the current edition gallery
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition gallery
	 * @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	 */
	@Override
	public EditionGallery[] findBytitle_PrevAndNext(long galleryId,
		String title, OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = findByPrimaryKey(galleryId);

		Session session = null;

		try {
			session = openSession();

			EditionGallery[] array = new EditionGalleryImpl[3];

			array[0] = getBytitle_PrevAndNext(session, editionGallery, title,
					orderByComparator, true);

			array[1] = editionGallery;

			array[2] = getBytitle_PrevAndNext(session, editionGallery, title,
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

	protected EditionGallery getBytitle_PrevAndNext(Session session,
		EditionGallery editionGallery, String title,
		OrderByComparator<EditionGallery> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EDITIONGALLERY_WHERE);

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
			query.append(EditionGalleryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(editionGallery);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EditionGallery> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the edition galleries where title = &#63; from the database.
	 *
	 * @param title the title
	 */
	@Override
	public void removeBytitle(String title) {
		for (EditionGallery editionGallery : findBytitle(title,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(editionGallery);
		}
	}

	/**
	 * Returns the number of edition galleries where title = &#63;.
	 *
	 * @param title the title
	 * @return the number of matching edition galleries
	 */
	@Override
	public int countBytitle(String title) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TITLE;

		Object[] finderArgs = new Object[] { title };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EDITIONGALLERY_WHERE);

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

	private static final String _FINDER_COLUMN_TITLE_TITLE_1 = "editionGallery.title IS NULL";
	private static final String _FINDER_COLUMN_TITLE_TITLE_2 = "editionGallery.title = ?";
	private static final String _FINDER_COLUMN_TITLE_TITLE_3 = "(editionGallery.title IS NULL OR editionGallery.title = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED,
			EditionGalleryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED,
			EditionGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			EditionGalleryModelImpl.GROUPID_COLUMN_BITMASK |
			EditionGalleryModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the edition galleries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the edition galleries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @return the range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the edition galleries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByGroupId(long groupId, int start, int end,
		OrderByComparator<EditionGallery> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the edition galleries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByGroupId(long groupId, int start, int end,
		OrderByComparator<EditionGallery> orderByComparator,
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

		List<EditionGallery> list = null;

		if (retrieveFromCache) {
			list = (List<EditionGallery>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EditionGallery editionGallery : list) {
					if ((groupId != editionGallery.getGroupId())) {
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

			query.append(_SQL_SELECT_EDITIONGALLERY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EditionGalleryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<EditionGallery>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EditionGallery>)QueryUtil.list(q,
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
	 * Returns the first edition gallery in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition gallery
	 * @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery findByGroupId_First(long groupId,
		OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = fetchByGroupId_First(groupId,
				orderByComparator);

		if (editionGallery != null) {
			return editionGallery;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionGalleryException(msg.toString());
	}

	/**
	 * Returns the first edition gallery in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery fetchByGroupId_First(long groupId,
		OrderByComparator<EditionGallery> orderByComparator) {
		List<EditionGallery> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last edition gallery in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition gallery
	 * @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery findByGroupId_Last(long groupId,
		OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (editionGallery != null) {
			return editionGallery;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionGalleryException(msg.toString());
	}

	/**
	 * Returns the last edition gallery in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery fetchByGroupId_Last(long groupId,
		OrderByComparator<EditionGallery> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<EditionGallery> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the edition galleries before and after the current edition gallery in the ordered set where groupId = &#63;.
	 *
	 * @param galleryId the primary key of the current edition gallery
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition gallery
	 * @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	 */
	@Override
	public EditionGallery[] findByGroupId_PrevAndNext(long galleryId,
		long groupId, OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = findByPrimaryKey(galleryId);

		Session session = null;

		try {
			session = openSession();

			EditionGallery[] array = new EditionGalleryImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, editionGallery,
					groupId, orderByComparator, true);

			array[1] = editionGallery;

			array[2] = getByGroupId_PrevAndNext(session, editionGallery,
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

	protected EditionGallery getByGroupId_PrevAndNext(Session session,
		EditionGallery editionGallery, long groupId,
		OrderByComparator<EditionGallery> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EDITIONGALLERY_WHERE);

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
			query.append(EditionGalleryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(editionGallery);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EditionGallery> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the edition galleries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (EditionGallery editionGallery : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(editionGallery);
		}
	}

	/**
	 * Returns the number of edition galleries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching edition galleries
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EDITIONGALLERY_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "editionGallery.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLICATIONDATEANDSTATUS =
		new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED,
			EditionGalleryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPublicationDateAndStatus",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_PUBLICATIONDATEANDSTATUS =
		new FinderPath(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByPublicationDateAndStatus",
			new String[] { Date.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the edition galleries where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @return the matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByPublicationDateAndStatus(
		Date publicationDate, int status) {
		return findByPublicationDateAndStatus(publicationDate, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the edition galleries where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @return the range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end) {
		return findByPublicationDateAndStatus(publicationDate, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the edition galleries where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		OrderByComparator<EditionGallery> orderByComparator) {
		return findByPublicationDateAndStatus(publicationDate, status, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the edition galleries where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching edition galleries
	 */
	@Override
	public List<EditionGallery> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		OrderByComparator<EditionGallery> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLICATIONDATEANDSTATUS;
		finderArgs = new Object[] {
				publicationDate, status,
				
				start, end, orderByComparator
			};

		List<EditionGallery> list = null;

		if (retrieveFromCache) {
			list = (List<EditionGallery>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EditionGallery editionGallery : list) {
					if ((publicationDate.getTime() <= editionGallery.getPublicationDate()
																		.getTime()) ||
							(status != editionGallery.getStatus())) {
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

			query.append(_SQL_SELECT_EDITIONGALLERY_WHERE);

			boolean bindPublicationDate = false;

			if (publicationDate == null) {
				query.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_1);
			}
			else {
				bindPublicationDate = true;

				query.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_2);
			}

			query.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EditionGalleryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublicationDate) {
					qPos.add(new Timestamp(publicationDate.getTime()));
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<EditionGallery>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EditionGallery>)QueryUtil.list(q,
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
	 * Returns the first edition gallery in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition gallery
	 * @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery findByPublicationDateAndStatus_First(
		Date publicationDate, int status,
		OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = fetchByPublicationDateAndStatus_First(publicationDate,
				status, orderByComparator);

		if (editionGallery != null) {
			return editionGallery;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publicationDate=");
		msg.append(publicationDate);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionGalleryException(msg.toString());
	}

	/**
	 * Returns the first edition gallery in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery fetchByPublicationDateAndStatus_First(
		Date publicationDate, int status,
		OrderByComparator<EditionGallery> orderByComparator) {
		List<EditionGallery> list = findByPublicationDateAndStatus(publicationDate,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last edition gallery in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition gallery
	 * @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery findByPublicationDateAndStatus_Last(
		Date publicationDate, int status,
		OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = fetchByPublicationDateAndStatus_Last(publicationDate,
				status, orderByComparator);

		if (editionGallery != null) {
			return editionGallery;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publicationDate=");
		msg.append(publicationDate);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEditionGalleryException(msg.toString());
	}

	/**
	 * Returns the last edition gallery in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	 */
	@Override
	public EditionGallery fetchByPublicationDateAndStatus_Last(
		Date publicationDate, int status,
		OrderByComparator<EditionGallery> orderByComparator) {
		int count = countByPublicationDateAndStatus(publicationDate, status);

		if (count == 0) {
			return null;
		}

		List<EditionGallery> list = findByPublicationDateAndStatus(publicationDate,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the edition galleries before and after the current edition gallery in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param galleryId the primary key of the current edition gallery
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition gallery
	 * @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	 */
	@Override
	public EditionGallery[] findByPublicationDateAndStatus_PrevAndNext(
		long galleryId, Date publicationDate, int status,
		OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = findByPrimaryKey(galleryId);

		Session session = null;

		try {
			session = openSession();

			EditionGallery[] array = new EditionGalleryImpl[3];

			array[0] = getByPublicationDateAndStatus_PrevAndNext(session,
					editionGallery, publicationDate, status, orderByComparator,
					true);

			array[1] = editionGallery;

			array[2] = getByPublicationDateAndStatus_PrevAndNext(session,
					editionGallery, publicationDate, status, orderByComparator,
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

	protected EditionGallery getByPublicationDateAndStatus_PrevAndNext(
		Session session, EditionGallery editionGallery, Date publicationDate,
		int status, OrderByComparator<EditionGallery> orderByComparator,
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

		query.append(_SQL_SELECT_EDITIONGALLERY_WHERE);

		boolean bindPublicationDate = false;

		if (publicationDate == null) {
			query.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_1);
		}
		else {
			bindPublicationDate = true;

			query.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_2);
		}

		query.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_STATUS_2);

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
			query.append(EditionGalleryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPublicationDate) {
			qPos.add(new Timestamp(publicationDate.getTime()));
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(editionGallery);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EditionGallery> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the edition galleries where publicationDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 */
	@Override
	public void removeByPublicationDateAndStatus(Date publicationDate,
		int status) {
		for (EditionGallery editionGallery : findByPublicationDateAndStatus(
				publicationDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(editionGallery);
		}
	}

	/**
	 * Returns the number of edition galleries where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @return the number of matching edition galleries
	 */
	@Override
	public int countByPublicationDateAndStatus(Date publicationDate, int status) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_PUBLICATIONDATEANDSTATUS;

		Object[] finderArgs = new Object[] { publicationDate, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EDITIONGALLERY_WHERE);

			boolean bindPublicationDate = false;

			if (publicationDate == null) {
				query.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_1);
			}
			else {
				bindPublicationDate = true;

				query.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_2);
			}

			query.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublicationDate) {
					qPos.add(new Timestamp(publicationDate.getTime()));
				}

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_1 =
		"editionGallery.publicationDate IS NULL AND ";
	private static final String _FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_2 =
		"editionGallery.publicationDate < ? AND ";
	private static final String _FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_STATUS_2 =
		"editionGallery.status = ?";

	public EditionGalleryPersistenceImpl() {
		setModelClass(EditionGallery.class);

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
	 * Caches the edition gallery in the entity cache if it is enabled.
	 *
	 * @param editionGallery the edition gallery
	 */
	@Override
	public void cacheResult(EditionGallery editionGallery) {
		entityCache.putResult(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryImpl.class, editionGallery.getPrimaryKey(),
			editionGallery);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { editionGallery.getUuid(), editionGallery.getGroupId() },
			editionGallery);

		editionGallery.resetOriginalValues();
	}

	/**
	 * Caches the edition galleries in the entity cache if it is enabled.
	 *
	 * @param editionGalleries the edition galleries
	 */
	@Override
	public void cacheResult(List<EditionGallery> editionGalleries) {
		for (EditionGallery editionGallery : editionGalleries) {
			if (entityCache.getResult(
						EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
						EditionGalleryImpl.class, editionGallery.getPrimaryKey()) == null) {
				cacheResult(editionGallery);
			}
			else {
				editionGallery.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all edition galleries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EditionGalleryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the edition gallery.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EditionGallery editionGallery) {
		entityCache.removeResult(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryImpl.class, editionGallery.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((EditionGalleryModelImpl)editionGallery, true);
	}

	@Override
	public void clearCache(List<EditionGallery> editionGalleries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EditionGallery editionGallery : editionGalleries) {
			entityCache.removeResult(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
				EditionGalleryImpl.class, editionGallery.getPrimaryKey());

			clearUniqueFindersCache((EditionGalleryModelImpl)editionGallery,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		EditionGalleryModelImpl editionGalleryModelImpl) {
		Object[] args = new Object[] {
				editionGalleryModelImpl.getUuid(),
				editionGalleryModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			editionGalleryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		EditionGalleryModelImpl editionGalleryModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					editionGalleryModelImpl.getUuid(),
					editionGalleryModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((editionGalleryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					editionGalleryModelImpl.getOriginalUuid(),
					editionGalleryModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new edition gallery with the primary key. Does not add the edition gallery to the database.
	 *
	 * @param galleryId the primary key for the new edition gallery
	 * @return the new edition gallery
	 */
	@Override
	public EditionGallery create(long galleryId) {
		EditionGallery editionGallery = new EditionGalleryImpl();

		editionGallery.setNew(true);
		editionGallery.setPrimaryKey(galleryId);

		String uuid = PortalUUIDUtil.generate();

		editionGallery.setUuid(uuid);

		editionGallery.setCompanyId(companyProvider.getCompanyId());

		return editionGallery;
	}

	/**
	 * Removes the edition gallery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param galleryId the primary key of the edition gallery
	 * @return the edition gallery that was removed
	 * @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	 */
	@Override
	public EditionGallery remove(long galleryId)
		throws NoSuchEditionGalleryException {
		return remove((Serializable)galleryId);
	}

	/**
	 * Removes the edition gallery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the edition gallery
	 * @return the edition gallery that was removed
	 * @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	 */
	@Override
	public EditionGallery remove(Serializable primaryKey)
		throws NoSuchEditionGalleryException {
		Session session = null;

		try {
			session = openSession();

			EditionGallery editionGallery = (EditionGallery)session.get(EditionGalleryImpl.class,
					primaryKey);

			if (editionGallery == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEditionGalleryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(editionGallery);
		}
		catch (NoSuchEditionGalleryException nsee) {
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
	protected EditionGallery removeImpl(EditionGallery editionGallery) {
		editionGallery = toUnwrappedModel(editionGallery);

		editionGalleryToEditionTableMapper.deleteLeftPrimaryKeyTableMappings(editionGallery.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(editionGallery)) {
				editionGallery = (EditionGallery)session.get(EditionGalleryImpl.class,
						editionGallery.getPrimaryKeyObj());
			}

			if (editionGallery != null) {
				session.delete(editionGallery);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (editionGallery != null) {
			clearCache(editionGallery);
		}

		return editionGallery;
	}

	@Override
	public EditionGallery updateImpl(EditionGallery editionGallery) {
		editionGallery = toUnwrappedModel(editionGallery);

		boolean isNew = editionGallery.isNew();

		EditionGalleryModelImpl editionGalleryModelImpl = (EditionGalleryModelImpl)editionGallery;

		if (Validator.isNull(editionGallery.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			editionGallery.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (editionGallery.getCreateDate() == null)) {
			if (serviceContext == null) {
				editionGallery.setCreateDate(now);
			}
			else {
				editionGallery.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!editionGalleryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				editionGallery.setModifiedDate(now);
			}
			else {
				editionGallery.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (editionGallery.isNew()) {
				session.save(editionGallery);

				editionGallery.setNew(false);
			}
			else {
				editionGallery = (EditionGallery)session.merge(editionGallery);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!EditionGalleryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { editionGalleryModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					editionGalleryModelImpl.getUuid(),
					editionGalleryModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { editionGalleryModelImpl.getTitle() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TITLE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TITLE,
				args);

			args = new Object[] { editionGalleryModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((editionGalleryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						editionGalleryModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { editionGalleryModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((editionGalleryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						editionGalleryModelImpl.getOriginalUuid(),
						editionGalleryModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						editionGalleryModelImpl.getUuid(),
						editionGalleryModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((editionGalleryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TITLE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						editionGalleryModelImpl.getOriginalTitle()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TITLE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TITLE,
					args);

				args = new Object[] { editionGalleryModelImpl.getTitle() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TITLE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TITLE,
					args);
			}

			if ((editionGalleryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						editionGalleryModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { editionGalleryModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
			EditionGalleryImpl.class, editionGallery.getPrimaryKey(),
			editionGallery, false);

		clearUniqueFindersCache(editionGalleryModelImpl, false);
		cacheUniqueFindersCache(editionGalleryModelImpl);

		editionGallery.resetOriginalValues();

		return editionGallery;
	}

	protected EditionGallery toUnwrappedModel(EditionGallery editionGallery) {
		if (editionGallery instanceof EditionGalleryImpl) {
			return editionGallery;
		}

		EditionGalleryImpl editionGalleryImpl = new EditionGalleryImpl();

		editionGalleryImpl.setNew(editionGallery.isNew());
		editionGalleryImpl.setPrimaryKey(editionGallery.getPrimaryKey());

		editionGalleryImpl.setUuid(editionGallery.getUuid());
		editionGalleryImpl.setGalleryId(editionGallery.getGalleryId());
		editionGalleryImpl.setGroupId(editionGallery.getGroupId());
		editionGalleryImpl.setCompanyId(editionGallery.getCompanyId());
		editionGalleryImpl.setUserId(editionGallery.getUserId());
		editionGalleryImpl.setUserName(editionGallery.getUserName());
		editionGalleryImpl.setCreateDate(editionGallery.getCreateDate());
		editionGalleryImpl.setModifiedDate(editionGallery.getModifiedDate());
		editionGalleryImpl.setLastPublishDate(editionGallery.getLastPublishDate());
		editionGalleryImpl.setStatus(editionGallery.getStatus());
		editionGalleryImpl.setStatusByUserId(editionGallery.getStatusByUserId());
		editionGalleryImpl.setStatusByUserName(editionGallery.getStatusByUserName());
		editionGalleryImpl.setStatusDate(editionGallery.getStatusDate());
		editionGalleryImpl.setImageId(editionGallery.getImageId());
		editionGalleryImpl.setTitle(editionGallery.getTitle());
		editionGalleryImpl.setDescription(editionGallery.getDescription());
		editionGalleryImpl.setPublicationDate(editionGallery.getPublicationDate());

		return editionGalleryImpl;
	}

	/**
	 * Returns the edition gallery with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the edition gallery
	 * @return the edition gallery
	 * @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	 */
	@Override
	public EditionGallery findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEditionGalleryException {
		EditionGallery editionGallery = fetchByPrimaryKey(primaryKey);

		if (editionGallery == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEditionGalleryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return editionGallery;
	}

	/**
	 * Returns the edition gallery with the primary key or throws a {@link NoSuchEditionGalleryException} if it could not be found.
	 *
	 * @param galleryId the primary key of the edition gallery
	 * @return the edition gallery
	 * @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	 */
	@Override
	public EditionGallery findByPrimaryKey(long galleryId)
		throws NoSuchEditionGalleryException {
		return findByPrimaryKey((Serializable)galleryId);
	}

	/**
	 * Returns the edition gallery with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the edition gallery
	 * @return the edition gallery, or <code>null</code> if a edition gallery with the primary key could not be found
	 */
	@Override
	public EditionGallery fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
				EditionGalleryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		EditionGallery editionGallery = (EditionGallery)serializable;

		if (editionGallery == null) {
			Session session = null;

			try {
				session = openSession();

				editionGallery = (EditionGallery)session.get(EditionGalleryImpl.class,
						primaryKey);

				if (editionGallery != null) {
					cacheResult(editionGallery);
				}
				else {
					entityCache.putResult(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
						EditionGalleryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
					EditionGalleryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return editionGallery;
	}

	/**
	 * Returns the edition gallery with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param galleryId the primary key of the edition gallery
	 * @return the edition gallery, or <code>null</code> if a edition gallery with the primary key could not be found
	 */
	@Override
	public EditionGallery fetchByPrimaryKey(long galleryId) {
		return fetchByPrimaryKey((Serializable)galleryId);
	}

	@Override
	public Map<Serializable, EditionGallery> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, EditionGallery> map = new HashMap<Serializable, EditionGallery>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			EditionGallery editionGallery = fetchByPrimaryKey(primaryKey);

			if (editionGallery != null) {
				map.put(primaryKey, editionGallery);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
					EditionGalleryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (EditionGallery)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_EDITIONGALLERY_WHERE_PKS_IN);

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

			for (EditionGallery editionGallery : (List<EditionGallery>)q.list()) {
				map.put(editionGallery.getPrimaryKeyObj(), editionGallery);

				cacheResult(editionGallery);

				uncachedPrimaryKeys.remove(editionGallery.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EditionGalleryModelImpl.ENTITY_CACHE_ENABLED,
					EditionGalleryImpl.class, primaryKey, nullModel);
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
	 * Returns all the edition galleries.
	 *
	 * @return the edition galleries
	 */
	@Override
	public List<EditionGallery> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the edition galleries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @return the range of edition galleries
	 */
	@Override
	public List<EditionGallery> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the edition galleries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of edition galleries
	 */
	@Override
	public List<EditionGallery> findAll(int start, int end,
		OrderByComparator<EditionGallery> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the edition galleries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of edition galleries
	 */
	@Override
	public List<EditionGallery> findAll(int start, int end,
		OrderByComparator<EditionGallery> orderByComparator,
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

		List<EditionGallery> list = null;

		if (retrieveFromCache) {
			list = (List<EditionGallery>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_EDITIONGALLERY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EDITIONGALLERY;

				if (pagination) {
					sql = sql.concat(EditionGalleryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<EditionGallery>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EditionGallery>)QueryUtil.list(q,
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
	 * Removes all the edition galleries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EditionGallery editionGallery : findAll()) {
			remove(editionGallery);
		}
	}

	/**
	 * Returns the number of edition galleries.
	 *
	 * @return the number of edition galleries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EDITIONGALLERY);

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
	 * Returns the primaryKeys of editions associated with the edition gallery.
	 *
	 * @param pk the primary key of the edition gallery
	 * @return long[] of the primaryKeys of editions associated with the edition gallery
	 */
	@Override
	public long[] getEditionPrimaryKeys(long pk) {
		long[] pks = editionGalleryToEditionTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the editions associated with the edition gallery.
	 *
	 * @param pk the primary key of the edition gallery
	 * @return the editions associated with the edition gallery
	 */
	@Override
	public List<eu.strasbourg.service.edition.model.Edition> getEditions(
		long pk) {
		return getEditions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the editions associated with the edition gallery.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the edition gallery
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @return the range of editions associated with the edition gallery
	 */
	@Override
	public List<eu.strasbourg.service.edition.model.Edition> getEditions(
		long pk, int start, int end) {
		return getEditions(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the editions associated with the edition gallery.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the edition gallery
	 * @param start the lower bound of the range of edition galleries
	 * @param end the upper bound of the range of edition galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of editions associated with the edition gallery
	 */
	@Override
	public List<eu.strasbourg.service.edition.model.Edition> getEditions(
		long pk, int start, int end,
		OrderByComparator<eu.strasbourg.service.edition.model.Edition> orderByComparator) {
		return editionGalleryToEditionTableMapper.getRightBaseModels(pk, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of editions associated with the edition gallery.
	 *
	 * @param pk the primary key of the edition gallery
	 * @return the number of editions associated with the edition gallery
	 */
	@Override
	public int getEditionsSize(long pk) {
		long[] pks = editionGalleryToEditionTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the edition is associated with the edition gallery.
	 *
	 * @param pk the primary key of the edition gallery
	 * @param editionPK the primary key of the edition
	 * @return <code>true</code> if the edition is associated with the edition gallery; <code>false</code> otherwise
	 */
	@Override
	public boolean containsEdition(long pk, long editionPK) {
		return editionGalleryToEditionTableMapper.containsTableMapping(pk,
			editionPK);
	}

	/**
	 * Returns <code>true</code> if the edition gallery has any editions associated with it.
	 *
	 * @param pk the primary key of the edition gallery to check for associations with editions
	 * @return <code>true</code> if the edition gallery has any editions associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsEditions(long pk) {
		if (getEditionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the edition gallery and the edition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition gallery
	 * @param editionPK the primary key of the edition
	 */
	@Override
	public void addEdition(long pk, long editionPK) {
		EditionGallery editionGallery = fetchByPrimaryKey(pk);

		if (editionGallery == null) {
			editionGalleryToEditionTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, editionPK);
		}
		else {
			editionGalleryToEditionTableMapper.addTableMapping(editionGallery.getCompanyId(),
				pk, editionPK);
		}
	}

	/**
	 * Adds an association between the edition gallery and the edition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition gallery
	 * @param edition the edition
	 */
	@Override
	public void addEdition(long pk,
		eu.strasbourg.service.edition.model.Edition edition) {
		EditionGallery editionGallery = fetchByPrimaryKey(pk);

		if (editionGallery == null) {
			editionGalleryToEditionTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, edition.getPrimaryKey());
		}
		else {
			editionGalleryToEditionTableMapper.addTableMapping(editionGallery.getCompanyId(),
				pk, edition.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the edition gallery and the editions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition gallery
	 * @param editionPKs the primary keys of the editions
	 */
	@Override
	public void addEditions(long pk, long[] editionPKs) {
		long companyId = 0;

		EditionGallery editionGallery = fetchByPrimaryKey(pk);

		if (editionGallery == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = editionGallery.getCompanyId();
		}

		editionGalleryToEditionTableMapper.addTableMappings(companyId, pk,
			editionPKs);
	}

	/**
	 * Adds an association between the edition gallery and the editions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition gallery
	 * @param editions the editions
	 */
	@Override
	public void addEditions(long pk,
		List<eu.strasbourg.service.edition.model.Edition> editions) {
		addEditions(pk,
			ListUtil.toLongArray(editions,
				eu.strasbourg.service.edition.model.Edition.EDITION_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the edition gallery and its editions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition gallery to clear the associated editions from
	 */
	@Override
	public void clearEditions(long pk) {
		editionGalleryToEditionTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the edition gallery and the edition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition gallery
	 * @param editionPK the primary key of the edition
	 */
	@Override
	public void removeEdition(long pk, long editionPK) {
		editionGalleryToEditionTableMapper.deleteTableMapping(pk, editionPK);
	}

	/**
	 * Removes the association between the edition gallery and the edition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition gallery
	 * @param edition the edition
	 */
	@Override
	public void removeEdition(long pk,
		eu.strasbourg.service.edition.model.Edition edition) {
		editionGalleryToEditionTableMapper.deleteTableMapping(pk,
			edition.getPrimaryKey());
	}

	/**
	 * Removes the association between the edition gallery and the editions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition gallery
	 * @param editionPKs the primary keys of the editions
	 */
	@Override
	public void removeEditions(long pk, long[] editionPKs) {
		editionGalleryToEditionTableMapper.deleteTableMappings(pk, editionPKs);
	}

	/**
	 * Removes the association between the edition gallery and the editions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition gallery
	 * @param editions the editions
	 */
	@Override
	public void removeEditions(long pk,
		List<eu.strasbourg.service.edition.model.Edition> editions) {
		removeEditions(pk,
			ListUtil.toLongArray(editions,
				eu.strasbourg.service.edition.model.Edition.EDITION_ID_ACCESSOR));
	}

	/**
	 * Sets the editions associated with the edition gallery, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition gallery
	 * @param editionPKs the primary keys of the editions to be associated with the edition gallery
	 */
	@Override
	public void setEditions(long pk, long[] editionPKs) {
		Set<Long> newEditionPKsSet = SetUtil.fromArray(editionPKs);
		Set<Long> oldEditionPKsSet = SetUtil.fromArray(editionGalleryToEditionTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeEditionPKsSet = new HashSet<Long>(oldEditionPKsSet);

		removeEditionPKsSet.removeAll(newEditionPKsSet);

		editionGalleryToEditionTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeEditionPKsSet));

		newEditionPKsSet.removeAll(oldEditionPKsSet);

		long companyId = 0;

		EditionGallery editionGallery = fetchByPrimaryKey(pk);

		if (editionGallery == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = editionGallery.getCompanyId();
		}

		editionGalleryToEditionTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newEditionPKsSet));
	}

	/**
	 * Sets the editions associated with the edition gallery, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition gallery
	 * @param editions the editions to be associated with the edition gallery
	 */
	@Override
	public void setEditions(long pk,
		List<eu.strasbourg.service.edition.model.Edition> editions) {
		try {
			long[] editionPKs = new long[editions.size()];

			for (int i = 0; i < editions.size(); i++) {
				eu.strasbourg.service.edition.model.Edition edition = editions.get(i);

				editionPKs[i] = edition.getPrimaryKey();
			}

			setEditions(pk, editionPKs);
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
		return EditionGalleryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the edition gallery persistence.
	 */
	public void afterPropertiesSet() {
		editionGalleryToEditionTableMapper = TableMapperFactory.getTableMapper("edition_EditionToEditionGallery",
				"companyId", "galleryId", "editionId", this, editionPersistence);
	}

	public void destroy() {
		entityCache.removeCache(EditionGalleryImpl.class.getName());
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
	@BeanReference(type = EditionPersistence.class)
	protected EditionPersistence editionPersistence;
	protected TableMapper<EditionGallery, eu.strasbourg.service.edition.model.Edition> editionGalleryToEditionTableMapper;
	private static final String _SQL_SELECT_EDITIONGALLERY = "SELECT editionGallery FROM EditionGallery editionGallery";
	private static final String _SQL_SELECT_EDITIONGALLERY_WHERE_PKS_IN = "SELECT editionGallery FROM EditionGallery editionGallery WHERE galleryId IN (";
	private static final String _SQL_SELECT_EDITIONGALLERY_WHERE = "SELECT editionGallery FROM EditionGallery editionGallery WHERE ";
	private static final String _SQL_COUNT_EDITIONGALLERY = "SELECT COUNT(editionGallery) FROM EditionGallery editionGallery";
	private static final String _SQL_COUNT_EDITIONGALLERY_WHERE = "SELECT COUNT(editionGallery) FROM EditionGallery editionGallery WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "editionGallery.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EditionGallery exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EditionGallery exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(EditionGalleryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}