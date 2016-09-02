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

package eu.strasbourg.service.artwork.service.persistence.impl;

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

import eu.strasbourg.service.artwork.exception.NoSuchArtworkException;
import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.model.impl.ArtworkImpl;
import eu.strasbourg.service.artwork.model.impl.ArtworkModelImpl;
import eu.strasbourg.service.artwork.service.persistence.ArtworkCollectionPersistence;
import eu.strasbourg.service.artwork.service.persistence.ArtworkPersistence;

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
 * The persistence implementation for the artwork service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see ArtworkPersistence
 * @see eu.strasbourg.service.artwork.service.persistence.ArtworkUtil
 * @generated
 */
@ProviderType
public class ArtworkPersistenceImpl extends BasePersistenceImpl<Artwork>
	implements ArtworkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ArtworkUtil} to access the artwork persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ArtworkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkModelImpl.FINDER_CACHE_ENABLED, ArtworkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkModelImpl.FINDER_CACHE_ENABLED, ArtworkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkModelImpl.FINDER_CACHE_ENABLED, ArtworkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkModelImpl.FINDER_CACHE_ENABLED, ArtworkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ArtworkModelImpl.UUID_COLUMN_BITMASK |
			ArtworkModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the artworks where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching artworks
	 */
	@Override
	public List<Artwork> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the artworks where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of artworks
	 * @param end the upper bound of the range of artworks (not inclusive)
	 * @return the range of matching artworks
	 */
	@Override
	public List<Artwork> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the artworks where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of artworks
	 * @param end the upper bound of the range of artworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching artworks
	 */
	@Override
	public List<Artwork> findByUuid(String uuid, int start, int end,
		OrderByComparator<Artwork> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the artworks where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of artworks
	 * @param end the upper bound of the range of artworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching artworks
	 */
	@Override
	public List<Artwork> findByUuid(String uuid, int start, int end,
		OrderByComparator<Artwork> orderByComparator, boolean retrieveFromCache) {
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

		List<Artwork> list = null;

		if (retrieveFromCache) {
			list = (List<Artwork>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Artwork artwork : list) {
					if (!Objects.equals(uuid, artwork.getUuid())) {
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

			query.append(_SQL_SELECT_ARTWORK_WHERE);

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
				query.append(ArtworkModelImpl.ORDER_BY_JPQL);
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
					list = (List<Artwork>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Artwork>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first artwork in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artwork
	 * @throws NoSuchArtworkException if a matching artwork could not be found
	 */
	@Override
	public Artwork findByUuid_First(String uuid,
		OrderByComparator<Artwork> orderByComparator)
		throws NoSuchArtworkException {
		Artwork artwork = fetchByUuid_First(uuid, orderByComparator);

		if (artwork != null) {
			return artwork;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtworkException(msg.toString());
	}

	/**
	 * Returns the first artwork in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artwork, or <code>null</code> if a matching artwork could not be found
	 */
	@Override
	public Artwork fetchByUuid_First(String uuid,
		OrderByComparator<Artwork> orderByComparator) {
		List<Artwork> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last artwork in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artwork
	 * @throws NoSuchArtworkException if a matching artwork could not be found
	 */
	@Override
	public Artwork findByUuid_Last(String uuid,
		OrderByComparator<Artwork> orderByComparator)
		throws NoSuchArtworkException {
		Artwork artwork = fetchByUuid_Last(uuid, orderByComparator);

		if (artwork != null) {
			return artwork;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtworkException(msg.toString());
	}

	/**
	 * Returns the last artwork in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artwork, or <code>null</code> if a matching artwork could not be found
	 */
	@Override
	public Artwork fetchByUuid_Last(String uuid,
		OrderByComparator<Artwork> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Artwork> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the artworks before and after the current artwork in the ordered set where uuid = &#63;.
	 *
	 * @param artworkId the primary key of the current artwork
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next artwork
	 * @throws NoSuchArtworkException if a artwork with the primary key could not be found
	 */
	@Override
	public Artwork[] findByUuid_PrevAndNext(long artworkId, String uuid,
		OrderByComparator<Artwork> orderByComparator)
		throws NoSuchArtworkException {
		Artwork artwork = findByPrimaryKey(artworkId);

		Session session = null;

		try {
			session = openSession();

			Artwork[] array = new ArtworkImpl[3];

			array[0] = getByUuid_PrevAndNext(session, artwork, uuid,
					orderByComparator, true);

			array[1] = artwork;

			array[2] = getByUuid_PrevAndNext(session, artwork, uuid,
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

	protected Artwork getByUuid_PrevAndNext(Session session, Artwork artwork,
		String uuid, OrderByComparator<Artwork> orderByComparator,
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

		query.append(_SQL_SELECT_ARTWORK_WHERE);

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
			query.append(ArtworkModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(artwork);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Artwork> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the artworks where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Artwork artwork : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(artwork);
		}
	}

	/**
	 * Returns the number of artworks where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching artworks
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ARTWORK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "artwork.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "artwork.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(artwork.uuid IS NULL OR artwork.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkModelImpl.FINDER_CACHE_ENABLED, ArtworkImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ArtworkModelImpl.UUID_COLUMN_BITMASK |
			ArtworkModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the artwork where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchArtworkException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching artwork
	 * @throws NoSuchArtworkException if a matching artwork could not be found
	 */
	@Override
	public Artwork findByUUID_G(String uuid, long groupId)
		throws NoSuchArtworkException {
		Artwork artwork = fetchByUUID_G(uuid, groupId);

		if (artwork == null) {
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

			throw new NoSuchArtworkException(msg.toString());
		}

		return artwork;
	}

	/**
	 * Returns the artwork where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching artwork, or <code>null</code> if a matching artwork could not be found
	 */
	@Override
	public Artwork fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the artwork where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching artwork, or <code>null</code> if a matching artwork could not be found
	 */
	@Override
	public Artwork fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Artwork) {
			Artwork artwork = (Artwork)result;

			if (!Objects.equals(uuid, artwork.getUuid()) ||
					(groupId != artwork.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ARTWORK_WHERE);

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

				List<Artwork> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Artwork artwork = list.get(0);

					result = artwork;

					cacheResult(artwork);

					if ((artwork.getUuid() == null) ||
							!artwork.getUuid().equals(uuid) ||
							(artwork.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, artwork);
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
			return (Artwork)result;
		}
	}

	/**
	 * Removes the artwork where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the artwork that was removed
	 */
	@Override
	public Artwork removeByUUID_G(String uuid, long groupId)
		throws NoSuchArtworkException {
		Artwork artwork = findByUUID_G(uuid, groupId);

		return remove(artwork);
	}

	/**
	 * Returns the number of artworks where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching artworks
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ARTWORK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "artwork.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "artwork.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(artwork.uuid IS NULL OR artwork.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "artwork.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkModelImpl.FINDER_CACHE_ENABLED, ArtworkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkModelImpl.FINDER_CACHE_ENABLED, ArtworkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ArtworkModelImpl.UUID_COLUMN_BITMASK |
			ArtworkModelImpl.COMPANYID_COLUMN_BITMASK |
			ArtworkModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the artworks where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching artworks
	 */
	@Override
	public List<Artwork> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the artworks where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of artworks
	 * @param end the upper bound of the range of artworks (not inclusive)
	 * @return the range of matching artworks
	 */
	@Override
	public List<Artwork> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the artworks where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of artworks
	 * @param end the upper bound of the range of artworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching artworks
	 */
	@Override
	public List<Artwork> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Artwork> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the artworks where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of artworks
	 * @param end the upper bound of the range of artworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching artworks
	 */
	@Override
	public List<Artwork> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Artwork> orderByComparator,
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

		List<Artwork> list = null;

		if (retrieveFromCache) {
			list = (List<Artwork>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Artwork artwork : list) {
					if (!Objects.equals(uuid, artwork.getUuid()) ||
							(companyId != artwork.getCompanyId())) {
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

			query.append(_SQL_SELECT_ARTWORK_WHERE);

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
				query.append(ArtworkModelImpl.ORDER_BY_JPQL);
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
					list = (List<Artwork>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Artwork>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artwork
	 * @throws NoSuchArtworkException if a matching artwork could not be found
	 */
	@Override
	public Artwork findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Artwork> orderByComparator)
		throws NoSuchArtworkException {
		Artwork artwork = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (artwork != null) {
			return artwork;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtworkException(msg.toString());
	}

	/**
	 * Returns the first artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artwork, or <code>null</code> if a matching artwork could not be found
	 */
	@Override
	public Artwork fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Artwork> orderByComparator) {
		List<Artwork> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artwork
	 * @throws NoSuchArtworkException if a matching artwork could not be found
	 */
	@Override
	public Artwork findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Artwork> orderByComparator)
		throws NoSuchArtworkException {
		Artwork artwork = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (artwork != null) {
			return artwork;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtworkException(msg.toString());
	}

	/**
	 * Returns the last artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artwork, or <code>null</code> if a matching artwork could not be found
	 */
	@Override
	public Artwork fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Artwork> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Artwork> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the artworks before and after the current artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param artworkId the primary key of the current artwork
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next artwork
	 * @throws NoSuchArtworkException if a artwork with the primary key could not be found
	 */
	@Override
	public Artwork[] findByUuid_C_PrevAndNext(long artworkId, String uuid,
		long companyId, OrderByComparator<Artwork> orderByComparator)
		throws NoSuchArtworkException {
		Artwork artwork = findByPrimaryKey(artworkId);

		Session session = null;

		try {
			session = openSession();

			Artwork[] array = new ArtworkImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, artwork, uuid,
					companyId, orderByComparator, true);

			array[1] = artwork;

			array[2] = getByUuid_C_PrevAndNext(session, artwork, uuid,
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

	protected Artwork getByUuid_C_PrevAndNext(Session session, Artwork artwork,
		String uuid, long companyId,
		OrderByComparator<Artwork> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ARTWORK_WHERE);

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
			query.append(ArtworkModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(artwork);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Artwork> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the artworks where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Artwork artwork : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(artwork);
		}
	}

	/**
	 * Returns the number of artworks where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching artworks
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ARTWORK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "artwork.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "artwork.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(artwork.uuid IS NULL OR artwork.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "artwork.companyId = ?";

	public ArtworkPersistenceImpl() {
		setModelClass(Artwork.class);
	}

	/**
	 * Caches the artwork in the entity cache if it is enabled.
	 *
	 * @param artwork the artwork
	 */
	@Override
	public void cacheResult(Artwork artwork) {
		entityCache.putResult(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkImpl.class, artwork.getPrimaryKey(), artwork);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { artwork.getUuid(), artwork.getGroupId() }, artwork);

		artwork.resetOriginalValues();
	}

	/**
	 * Caches the artworks in the entity cache if it is enabled.
	 *
	 * @param artworks the artworks
	 */
	@Override
	public void cacheResult(List<Artwork> artworks) {
		for (Artwork artwork : artworks) {
			if (entityCache.getResult(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
						ArtworkImpl.class, artwork.getPrimaryKey()) == null) {
				cacheResult(artwork);
			}
			else {
				artwork.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all artworks.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ArtworkImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the artwork.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Artwork artwork) {
		entityCache.removeResult(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkImpl.class, artwork.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ArtworkModelImpl)artwork);
	}

	@Override
	public void clearCache(List<Artwork> artworks) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Artwork artwork : artworks) {
			entityCache.removeResult(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
				ArtworkImpl.class, artwork.getPrimaryKey());

			clearUniqueFindersCache((ArtworkModelImpl)artwork);
		}
	}

	protected void cacheUniqueFindersCache(ArtworkModelImpl artworkModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					artworkModelImpl.getUuid(), artworkModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				artworkModelImpl);
		}
		else {
			if ((artworkModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						artworkModelImpl.getUuid(),
						artworkModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					artworkModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(ArtworkModelImpl artworkModelImpl) {
		Object[] args = new Object[] {
				artworkModelImpl.getUuid(), artworkModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((artworkModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					artworkModelImpl.getOriginalUuid(),
					artworkModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new artwork with the primary key. Does not add the artwork to the database.
	 *
	 * @param artworkId the primary key for the new artwork
	 * @return the new artwork
	 */
	@Override
	public Artwork create(long artworkId) {
		Artwork artwork = new ArtworkImpl();

		artwork.setNew(true);
		artwork.setPrimaryKey(artworkId);

		String uuid = PortalUUIDUtil.generate();

		artwork.setUuid(uuid);

		artwork.setCompanyId(companyProvider.getCompanyId());

		return artwork;
	}

	/**
	 * Removes the artwork with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param artworkId the primary key of the artwork
	 * @return the artwork that was removed
	 * @throws NoSuchArtworkException if a artwork with the primary key could not be found
	 */
	@Override
	public Artwork remove(long artworkId) throws NoSuchArtworkException {
		return remove((Serializable)artworkId);
	}

	/**
	 * Removes the artwork with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the artwork
	 * @return the artwork that was removed
	 * @throws NoSuchArtworkException if a artwork with the primary key could not be found
	 */
	@Override
	public Artwork remove(Serializable primaryKey)
		throws NoSuchArtworkException {
		Session session = null;

		try {
			session = openSession();

			Artwork artwork = (Artwork)session.get(ArtworkImpl.class, primaryKey);

			if (artwork == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchArtworkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(artwork);
		}
		catch (NoSuchArtworkException nsee) {
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
	protected Artwork removeImpl(Artwork artwork) {
		artwork = toUnwrappedModel(artwork);

		artworkToArtworkCollectionTableMapper.deleteLeftPrimaryKeyTableMappings(artwork.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(artwork)) {
				artwork = (Artwork)session.get(ArtworkImpl.class,
						artwork.getPrimaryKeyObj());
			}

			if (artwork != null) {
				session.delete(artwork);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (artwork != null) {
			clearCache(artwork);
		}

		return artwork;
	}

	@Override
	public Artwork updateImpl(Artwork artwork) {
		artwork = toUnwrappedModel(artwork);

		boolean isNew = artwork.isNew();

		ArtworkModelImpl artworkModelImpl = (ArtworkModelImpl)artwork;

		if (Validator.isNull(artwork.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			artwork.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (artwork.getCreateDate() == null)) {
			if (serviceContext == null) {
				artwork.setCreateDate(now);
			}
			else {
				artwork.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!artworkModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				artwork.setModifiedDate(now);
			}
			else {
				artwork.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (artwork.isNew()) {
				session.save(artwork);

				artwork.setNew(false);
			}
			else {
				artwork = (Artwork)session.merge(artwork);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ArtworkModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((artworkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { artworkModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { artworkModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((artworkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						artworkModelImpl.getOriginalUuid(),
						artworkModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						artworkModelImpl.getUuid(),
						artworkModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkImpl.class, artwork.getPrimaryKey(), artwork, false);

		clearUniqueFindersCache(artworkModelImpl);
		cacheUniqueFindersCache(artworkModelImpl, isNew);

		artwork.resetOriginalValues();

		return artwork;
	}

	protected Artwork toUnwrappedModel(Artwork artwork) {
		if (artwork instanceof ArtworkImpl) {
			return artwork;
		}

		ArtworkImpl artworkImpl = new ArtworkImpl();

		artworkImpl.setNew(artwork.isNew());
		artworkImpl.setPrimaryKey(artwork.getPrimaryKey());

		artworkImpl.setUuid(artwork.getUuid());
		artworkImpl.setArtworkId(artwork.getArtworkId());
		artworkImpl.setGroupId(artwork.getGroupId());
		artworkImpl.setCompanyId(artwork.getCompanyId());
		artworkImpl.setUserId(artwork.getUserId());
		artworkImpl.setUserName(artwork.getUserName());
		artworkImpl.setCreateDate(artwork.getCreateDate());
		artworkImpl.setModifiedDate(artwork.getModifiedDate());
		artworkImpl.setTitle(artwork.getTitle());
		artworkImpl.setDescription(artwork.getDescription());
		artworkImpl.setTechnicalInformation(artwork.getTechnicalInformation());
		artworkImpl.setNoticeLink(artwork.getNoticeLink());
		artworkImpl.setArtistName(artwork.getArtistName());
		artworkImpl.setCreationYear(artwork.getCreationYear());
		artworkImpl.setOrigin(artwork.getOrigin());
		artworkImpl.setExhibitionName(artwork.getExhibitionName());
		artworkImpl.setExhibitionPlace(artwork.getExhibitionPlace());
		artworkImpl.setLoanPeriod(artwork.getLoanPeriod());
		artworkImpl.setLinkName(artwork.getLinkName());
		artworkImpl.setLink(artwork.getLink());
		artworkImpl.setStatus(artwork.isStatus());
		artworkImpl.setImageId(artwork.getImageId());
		artworkImpl.setImagesIds(artwork.getImagesIds());

		return artworkImpl;
	}

	/**
	 * Returns the artwork with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the artwork
	 * @return the artwork
	 * @throws NoSuchArtworkException if a artwork with the primary key could not be found
	 */
	@Override
	public Artwork findByPrimaryKey(Serializable primaryKey)
		throws NoSuchArtworkException {
		Artwork artwork = fetchByPrimaryKey(primaryKey);

		if (artwork == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchArtworkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return artwork;
	}

	/**
	 * Returns the artwork with the primary key or throws a {@link NoSuchArtworkException} if it could not be found.
	 *
	 * @param artworkId the primary key of the artwork
	 * @return the artwork
	 * @throws NoSuchArtworkException if a artwork with the primary key could not be found
	 */
	@Override
	public Artwork findByPrimaryKey(long artworkId)
		throws NoSuchArtworkException {
		return findByPrimaryKey((Serializable)artworkId);
	}

	/**
	 * Returns the artwork with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the artwork
	 * @return the artwork, or <code>null</code> if a artwork with the primary key could not be found
	 */
	@Override
	public Artwork fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
				ArtworkImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Artwork artwork = (Artwork)serializable;

		if (artwork == null) {
			Session session = null;

			try {
				session = openSession();

				artwork = (Artwork)session.get(ArtworkImpl.class, primaryKey);

				if (artwork != null) {
					cacheResult(artwork);
				}
				else {
					entityCache.putResult(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
						ArtworkImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
					ArtworkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return artwork;
	}

	/**
	 * Returns the artwork with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param artworkId the primary key of the artwork
	 * @return the artwork, or <code>null</code> if a artwork with the primary key could not be found
	 */
	@Override
	public Artwork fetchByPrimaryKey(long artworkId) {
		return fetchByPrimaryKey((Serializable)artworkId);
	}

	@Override
	public Map<Serializable, Artwork> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Artwork> map = new HashMap<Serializable, Artwork>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Artwork artwork = fetchByPrimaryKey(primaryKey);

			if (artwork != null) {
				map.put(primaryKey, artwork);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
					ArtworkImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Artwork)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ARTWORK_WHERE_PKS_IN);

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

			for (Artwork artwork : (List<Artwork>)q.list()) {
				map.put(artwork.getPrimaryKeyObj(), artwork);

				cacheResult(artwork);

				uncachedPrimaryKeys.remove(artwork.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ArtworkModelImpl.ENTITY_CACHE_ENABLED,
					ArtworkImpl.class, primaryKey, nullModel);
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
	 * Returns all the artworks.
	 *
	 * @return the artworks
	 */
	@Override
	public List<Artwork> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the artworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of artworks
	 * @param end the upper bound of the range of artworks (not inclusive)
	 * @return the range of artworks
	 */
	@Override
	public List<Artwork> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the artworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of artworks
	 * @param end the upper bound of the range of artworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of artworks
	 */
	@Override
	public List<Artwork> findAll(int start, int end,
		OrderByComparator<Artwork> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the artworks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of artworks
	 * @param end the upper bound of the range of artworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of artworks
	 */
	@Override
	public List<Artwork> findAll(int start, int end,
		OrderByComparator<Artwork> orderByComparator, boolean retrieveFromCache) {
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

		List<Artwork> list = null;

		if (retrieveFromCache) {
			list = (List<Artwork>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ARTWORK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ARTWORK;

				if (pagination) {
					sql = sql.concat(ArtworkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Artwork>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Artwork>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the artworks from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Artwork artwork : findAll()) {
			remove(artwork);
		}
	}

	/**
	 * Returns the number of artworks.
	 *
	 * @return the number of artworks
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ARTWORK);

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
	 * Returns the primaryKeys of artwork collections associated with the artwork.
	 *
	 * @param pk the primary key of the artwork
	 * @return long[] of the primaryKeys of artwork collections associated with the artwork
	 */
	@Override
	public long[] getArtworkCollectionPrimaryKeys(long pk) {
		long[] pks = artworkToArtworkCollectionTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the artwork collections associated with the artwork.
	 *
	 * @param pk the primary key of the artwork
	 * @return the artwork collections associated with the artwork
	 */
	@Override
	public List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkCollections(
		long pk) {
		return getArtworkCollections(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the artwork collections associated with the artwork.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the artwork
	 * @param start the lower bound of the range of artworks
	 * @param end the upper bound of the range of artworks (not inclusive)
	 * @return the range of artwork collections associated with the artwork
	 */
	@Override
	public List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkCollections(
		long pk, int start, int end) {
		return getArtworkCollections(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the artwork collections associated with the artwork.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the artwork
	 * @param start the lower bound of the range of artworks
	 * @param end the upper bound of the range of artworks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of artwork collections associated with the artwork
	 */
	@Override
	public List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkCollections(
		long pk, int start, int end,
		OrderByComparator<eu.strasbourg.service.artwork.model.ArtworkCollection> orderByComparator) {
		return artworkToArtworkCollectionTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of artwork collections associated with the artwork.
	 *
	 * @param pk the primary key of the artwork
	 * @return the number of artwork collections associated with the artwork
	 */
	@Override
	public int getArtworkCollectionsSize(long pk) {
		long[] pks = artworkToArtworkCollectionTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the artwork collection is associated with the artwork.
	 *
	 * @param pk the primary key of the artwork
	 * @param artworkCollectionPK the primary key of the artwork collection
	 * @return <code>true</code> if the artwork collection is associated with the artwork; <code>false</code> otherwise
	 */
	@Override
	public boolean containsArtworkCollection(long pk, long artworkCollectionPK) {
		return artworkToArtworkCollectionTableMapper.containsTableMapping(pk,
			artworkCollectionPK);
	}

	/**
	 * Returns <code>true</code> if the artwork has any artwork collections associated with it.
	 *
	 * @param pk the primary key of the artwork to check for associations with artwork collections
	 * @return <code>true</code> if the artwork has any artwork collections associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsArtworkCollections(long pk) {
		if (getArtworkCollectionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the artwork and the artwork collection. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork
	 * @param artworkCollectionPK the primary key of the artwork collection
	 */
	@Override
	public void addArtworkCollection(long pk, long artworkCollectionPK) {
		Artwork artwork = fetchByPrimaryKey(pk);

		if (artwork == null) {
			artworkToArtworkCollectionTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, artworkCollectionPK);
		}
		else {
			artworkToArtworkCollectionTableMapper.addTableMapping(artwork.getCompanyId(),
				pk, artworkCollectionPK);
		}
	}

	/**
	 * Adds an association between the artwork and the artwork collection. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork
	 * @param artworkCollection the artwork collection
	 */
	@Override
	public void addArtworkCollection(long pk,
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		Artwork artwork = fetchByPrimaryKey(pk);

		if (artwork == null) {
			artworkToArtworkCollectionTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, artworkCollection.getPrimaryKey());
		}
		else {
			artworkToArtworkCollectionTableMapper.addTableMapping(artwork.getCompanyId(),
				pk, artworkCollection.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the artwork and the artwork collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork
	 * @param artworkCollectionPKs the primary keys of the artwork collections
	 */
	@Override
	public void addArtworkCollections(long pk, long[] artworkCollectionPKs) {
		long companyId = 0;

		Artwork artwork = fetchByPrimaryKey(pk);

		if (artwork == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = artwork.getCompanyId();
		}

		artworkToArtworkCollectionTableMapper.addTableMappings(companyId, pk,
			artworkCollectionPKs);
	}

	/**
	 * Adds an association between the artwork and the artwork collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork
	 * @param artworkCollections the artwork collections
	 */
	@Override
	public void addArtworkCollections(long pk,
		List<eu.strasbourg.service.artwork.model.ArtworkCollection> artworkCollections) {
		addArtworkCollections(pk,
			ListUtil.toLongArray(artworkCollections,
				eu.strasbourg.service.artwork.model.ArtworkCollection.COLLECTION_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the artwork and its artwork collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork to clear the associated artwork collections from
	 */
	@Override
	public void clearArtworkCollections(long pk) {
		artworkToArtworkCollectionTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the artwork and the artwork collection. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork
	 * @param artworkCollectionPK the primary key of the artwork collection
	 */
	@Override
	public void removeArtworkCollection(long pk, long artworkCollectionPK) {
		artworkToArtworkCollectionTableMapper.deleteTableMapping(pk,
			artworkCollectionPK);
	}

	/**
	 * Removes the association between the artwork and the artwork collection. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork
	 * @param artworkCollection the artwork collection
	 */
	@Override
	public void removeArtworkCollection(long pk,
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		artworkToArtworkCollectionTableMapper.deleteTableMapping(pk,
			artworkCollection.getPrimaryKey());
	}

	/**
	 * Removes the association between the artwork and the artwork collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork
	 * @param artworkCollectionPKs the primary keys of the artwork collections
	 */
	@Override
	public void removeArtworkCollections(long pk, long[] artworkCollectionPKs) {
		artworkToArtworkCollectionTableMapper.deleteTableMappings(pk,
			artworkCollectionPKs);
	}

	/**
	 * Removes the association between the artwork and the artwork collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork
	 * @param artworkCollections the artwork collections
	 */
	@Override
	public void removeArtworkCollections(long pk,
		List<eu.strasbourg.service.artwork.model.ArtworkCollection> artworkCollections) {
		removeArtworkCollections(pk,
			ListUtil.toLongArray(artworkCollections,
				eu.strasbourg.service.artwork.model.ArtworkCollection.COLLECTION_ID_ACCESSOR));
	}

	/**
	 * Sets the artwork collections associated with the artwork, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork
	 * @param artworkCollectionPKs the primary keys of the artwork collections to be associated with the artwork
	 */
	@Override
	public void setArtworkCollections(long pk, long[] artworkCollectionPKs) {
		Set<Long> newArtworkCollectionPKsSet = SetUtil.fromArray(artworkCollectionPKs);
		Set<Long> oldArtworkCollectionPKsSet = SetUtil.fromArray(artworkToArtworkCollectionTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeArtworkCollectionPKsSet = new HashSet<Long>(oldArtworkCollectionPKsSet);

		removeArtworkCollectionPKsSet.removeAll(newArtworkCollectionPKsSet);

		artworkToArtworkCollectionTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeArtworkCollectionPKsSet));

		newArtworkCollectionPKsSet.removeAll(oldArtworkCollectionPKsSet);

		long companyId = 0;

		Artwork artwork = fetchByPrimaryKey(pk);

		if (artwork == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = artwork.getCompanyId();
		}

		artworkToArtworkCollectionTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newArtworkCollectionPKsSet));
	}

	/**
	 * Sets the artwork collections associated with the artwork, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork
	 * @param artworkCollections the artwork collections to be associated with the artwork
	 */
	@Override
	public void setArtworkCollections(long pk,
		List<eu.strasbourg.service.artwork.model.ArtworkCollection> artworkCollections) {
		try {
			long[] artworkCollectionPKs = new long[artworkCollections.size()];

			for (int i = 0; i < artworkCollections.size(); i++) {
				eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection =
					artworkCollections.get(i);

				artworkCollectionPKs[i] = artworkCollection.getPrimaryKey();
			}

			setArtworkCollections(pk, artworkCollectionPKs);
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
		return ArtworkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the artwork persistence.
	 */
	public void afterPropertiesSet() {
		artworkToArtworkCollectionTableMapper = TableMapperFactory.getTableMapper("artwork_ArtworkToArtworkCollection",
				"companyId", "artworkId", "collectionId", this,
				artworkCollectionPersistence);
	}

	public void destroy() {
		entityCache.removeCache(ArtworkImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper(
			"artwork_ArtworkToArtworkCollection");
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	@BeanReference(type = ArtworkCollectionPersistence.class)
	protected ArtworkCollectionPersistence artworkCollectionPersistence;
	protected TableMapper<Artwork, eu.strasbourg.service.artwork.model.ArtworkCollection> artworkToArtworkCollectionTableMapper;
	private static final String _SQL_SELECT_ARTWORK = "SELECT artwork FROM Artwork artwork";
	private static final String _SQL_SELECT_ARTWORK_WHERE_PKS_IN = "SELECT artwork FROM Artwork artwork WHERE artworkId IN (";
	private static final String _SQL_SELECT_ARTWORK_WHERE = "SELECT artwork FROM Artwork artwork WHERE ";
	private static final String _SQL_COUNT_ARTWORK = "SELECT COUNT(artwork) FROM Artwork artwork";
	private static final String _SQL_COUNT_ARTWORK_WHERE = "SELECT COUNT(artwork) FROM Artwork artwork WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "artwork.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Artwork exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Artwork exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ArtworkPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}