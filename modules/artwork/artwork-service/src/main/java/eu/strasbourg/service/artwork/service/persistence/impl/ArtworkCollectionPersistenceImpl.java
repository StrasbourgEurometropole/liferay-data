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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException;
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.model.impl.ArtworkCollectionImpl;
import eu.strasbourg.service.artwork.model.impl.ArtworkCollectionModelImpl;
import eu.strasbourg.service.artwork.service.persistence.ArtworkCollectionPersistence;
import eu.strasbourg.service.artwork.service.persistence.ArtworkPersistence;

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
 * The persistence implementation for the artwork collection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @generated
 */
public class ArtworkCollectionPersistenceImpl
	extends BasePersistenceImpl<ArtworkCollection>
	implements ArtworkCollectionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ArtworkCollectionUtil</code> to access the artwork collection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ArtworkCollectionImpl.class.getName();

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
	 * Returns all the artwork collections where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching artwork collections
	 */
	@Override
	public List<ArtworkCollection> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the artwork collections where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @return the range of matching artwork collections
	 */
	@Override
	public List<ArtworkCollection> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the artwork collections where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching artwork collections
	 */
	@Override
	public List<ArtworkCollection> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the artwork collections where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching artwork collections
	 */
	@Override
	public List<ArtworkCollection> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator,
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

		List<ArtworkCollection> list = null;

		if (useFinderCache) {
			list = (List<ArtworkCollection>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ArtworkCollection artworkCollection : list) {
					if (!uuid.equals(artworkCollection.getUuid())) {
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

			sb.append(_SQL_SELECT_ARTWORKCOLLECTION_WHERE);

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
				sb.append(ArtworkCollectionModelImpl.ORDER_BY_JPQL);
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

				list = (List<ArtworkCollection>)QueryUtil.list(
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
	 * Returns the first artwork collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artwork collection
	 * @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection findByUuid_First(
			String uuid, OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException {

		ArtworkCollection artworkCollection = fetchByUuid_First(
			uuid, orderByComparator);

		if (artworkCollection != null) {
			return artworkCollection;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchArtworkCollectionException(sb.toString());
	}

	/**
	 * Returns the first artwork collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection fetchByUuid_First(
		String uuid, OrderByComparator<ArtworkCollection> orderByComparator) {

		List<ArtworkCollection> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last artwork collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artwork collection
	 * @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection findByUuid_Last(
			String uuid, OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException {

		ArtworkCollection artworkCollection = fetchByUuid_Last(
			uuid, orderByComparator);

		if (artworkCollection != null) {
			return artworkCollection;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchArtworkCollectionException(sb.toString());
	}

	/**
	 * Returns the last artwork collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection fetchByUuid_Last(
		String uuid, OrderByComparator<ArtworkCollection> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ArtworkCollection> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the artwork collections before and after the current artwork collection in the ordered set where uuid = &#63;.
	 *
	 * @param collectionId the primary key of the current artwork collection
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next artwork collection
	 * @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	 */
	@Override
	public ArtworkCollection[] findByUuid_PrevAndNext(
			long collectionId, String uuid,
			OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException {

		uuid = Objects.toString(uuid, "");

		ArtworkCollection artworkCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			ArtworkCollection[] array = new ArtworkCollectionImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, artworkCollection, uuid, orderByComparator, true);

			array[1] = artworkCollection;

			array[2] = getByUuid_PrevAndNext(
				session, artworkCollection, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ArtworkCollection getByUuid_PrevAndNext(
		Session session, ArtworkCollection artworkCollection, String uuid,
		OrderByComparator<ArtworkCollection> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ARTWORKCOLLECTION_WHERE);

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
			sb.append(ArtworkCollectionModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(
						artworkCollection)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ArtworkCollection> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the artwork collections where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ArtworkCollection artworkCollection :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(artworkCollection);
		}
	}

	/**
	 * Returns the number of artwork collections where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching artwork collections
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ARTWORKCOLLECTION_WHERE);

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
		"artworkCollection.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(artworkCollection.uuid IS NULL OR artworkCollection.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the artwork collection where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchArtworkCollectionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching artwork collection
	 * @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection findByUUID_G(String uuid, long groupId)
		throws NoSuchArtworkCollectionException {

		ArtworkCollection artworkCollection = fetchByUUID_G(uuid, groupId);

		if (artworkCollection == null) {
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

			throw new NoSuchArtworkCollectionException(sb.toString());
		}

		return artworkCollection;
	}

	/**
	 * Returns the artwork collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the artwork collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection fetchByUUID_G(
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

		if (result instanceof ArtworkCollection) {
			ArtworkCollection artworkCollection = (ArtworkCollection)result;

			if (!Objects.equals(uuid, artworkCollection.getUuid()) ||
				(groupId != artworkCollection.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_ARTWORKCOLLECTION_WHERE);

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

				List<ArtworkCollection> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					ArtworkCollection artworkCollection = list.get(0);

					result = artworkCollection;

					cacheResult(artworkCollection);
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
			return (ArtworkCollection)result;
		}
	}

	/**
	 * Removes the artwork collection where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the artwork collection that was removed
	 */
	@Override
	public ArtworkCollection removeByUUID_G(String uuid, long groupId)
		throws NoSuchArtworkCollectionException {

		ArtworkCollection artworkCollection = findByUUID_G(uuid, groupId);

		return remove(artworkCollection);
	}

	/**
	 * Returns the number of artwork collections where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching artwork collections
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ARTWORKCOLLECTION_WHERE);

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
		"artworkCollection.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(artworkCollection.uuid IS NULL OR artworkCollection.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"artworkCollection.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the artwork collections where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching artwork collections
	 */
	@Override
	public List<ArtworkCollection> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the artwork collections where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @return the range of matching artwork collections
	 */
	@Override
	public List<ArtworkCollection> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the artwork collections where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching artwork collections
	 */
	@Override
	public List<ArtworkCollection> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the artwork collections where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching artwork collections
	 */
	@Override
	public List<ArtworkCollection> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator,
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

		List<ArtworkCollection> list = null;

		if (useFinderCache) {
			list = (List<ArtworkCollection>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ArtworkCollection artworkCollection : list) {
					if (!uuid.equals(artworkCollection.getUuid()) ||
						(companyId != artworkCollection.getCompanyId())) {

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

			sb.append(_SQL_SELECT_ARTWORKCOLLECTION_WHERE);

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
				sb.append(ArtworkCollectionModelImpl.ORDER_BY_JPQL);
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

				list = (List<ArtworkCollection>)QueryUtil.list(
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
	 * Returns the first artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artwork collection
	 * @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException {

		ArtworkCollection artworkCollection = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (artworkCollection != null) {
			return artworkCollection;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchArtworkCollectionException(sb.toString());
	}

	/**
	 * Returns the first artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<ArtworkCollection> orderByComparator) {

		List<ArtworkCollection> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artwork collection
	 * @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException {

		ArtworkCollection artworkCollection = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (artworkCollection != null) {
			return artworkCollection;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchArtworkCollectionException(sb.toString());
	}

	/**
	 * Returns the last artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<ArtworkCollection> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ArtworkCollection> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the artwork collections before and after the current artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param collectionId the primary key of the current artwork collection
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next artwork collection
	 * @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	 */
	@Override
	public ArtworkCollection[] findByUuid_C_PrevAndNext(
			long collectionId, String uuid, long companyId,
			OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException {

		uuid = Objects.toString(uuid, "");

		ArtworkCollection artworkCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			ArtworkCollection[] array = new ArtworkCollectionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, artworkCollection, uuid, companyId, orderByComparator,
				true);

			array[1] = artworkCollection;

			array[2] = getByUuid_C_PrevAndNext(
				session, artworkCollection, uuid, companyId, orderByComparator,
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

	protected ArtworkCollection getByUuid_C_PrevAndNext(
		Session session, ArtworkCollection artworkCollection, String uuid,
		long companyId, OrderByComparator<ArtworkCollection> orderByComparator,
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

		sb.append(_SQL_SELECT_ARTWORKCOLLECTION_WHERE);

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
			sb.append(ArtworkCollectionModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(
						artworkCollection)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ArtworkCollection> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the artwork collections where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ArtworkCollection artworkCollection :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(artworkCollection);
		}
	}

	/**
	 * Returns the number of artwork collections where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching artwork collections
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ARTWORKCOLLECTION_WHERE);

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
		"artworkCollection.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(artworkCollection.uuid IS NULL OR artworkCollection.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"artworkCollection.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the artwork collections where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching artwork collections
	 */
	@Override
	public List<ArtworkCollection> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the artwork collections where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @return the range of matching artwork collections
	 */
	@Override
	public List<ArtworkCollection> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the artwork collections where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching artwork collections
	 */
	@Override
	public List<ArtworkCollection> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the artwork collections where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching artwork collections
	 */
	@Override
	public List<ArtworkCollection> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator,
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

		List<ArtworkCollection> list = null;

		if (useFinderCache) {
			list = (List<ArtworkCollection>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ArtworkCollection artworkCollection : list) {
					if (groupId != artworkCollection.getGroupId()) {
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

			sb.append(_SQL_SELECT_ARTWORKCOLLECTION_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ArtworkCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<ArtworkCollection>)QueryUtil.list(
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
	 * Returns the first artwork collection in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artwork collection
	 * @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection findByGroupId_First(
			long groupId,
			OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException {

		ArtworkCollection artworkCollection = fetchByGroupId_First(
			groupId, orderByComparator);

		if (artworkCollection != null) {
			return artworkCollection;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchArtworkCollectionException(sb.toString());
	}

	/**
	 * Returns the first artwork collection in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection fetchByGroupId_First(
		long groupId, OrderByComparator<ArtworkCollection> orderByComparator) {

		List<ArtworkCollection> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last artwork collection in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artwork collection
	 * @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection findByGroupId_Last(
			long groupId,
			OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException {

		ArtworkCollection artworkCollection = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (artworkCollection != null) {
			return artworkCollection;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchArtworkCollectionException(sb.toString());
	}

	/**
	 * Returns the last artwork collection in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	 */
	@Override
	public ArtworkCollection fetchByGroupId_Last(
		long groupId, OrderByComparator<ArtworkCollection> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ArtworkCollection> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the artwork collections before and after the current artwork collection in the ordered set where groupId = &#63;.
	 *
	 * @param collectionId the primary key of the current artwork collection
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next artwork collection
	 * @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	 */
	@Override
	public ArtworkCollection[] findByGroupId_PrevAndNext(
			long collectionId, long groupId,
			OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException {

		ArtworkCollection artworkCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			ArtworkCollection[] array = new ArtworkCollectionImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, artworkCollection, groupId, orderByComparator, true);

			array[1] = artworkCollection;

			array[2] = getByGroupId_PrevAndNext(
				session, artworkCollection, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ArtworkCollection getByGroupId_PrevAndNext(
		Session session, ArtworkCollection artworkCollection, long groupId,
		OrderByComparator<ArtworkCollection> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ARTWORKCOLLECTION_WHERE);

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
			sb.append(ArtworkCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						artworkCollection)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ArtworkCollection> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the artwork collections where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (ArtworkCollection artworkCollection :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(artworkCollection);
		}
	}

	/**
	 * Returns the number of artwork collections where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching artwork collections
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ARTWORKCOLLECTION_WHERE);

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
		"artworkCollection.groupId = ?";

	public ArtworkCollectionPersistenceImpl() {
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

		setModelClass(ArtworkCollection.class);
	}

	/**
	 * Caches the artwork collection in the entity cache if it is enabled.
	 *
	 * @param artworkCollection the artwork collection
	 */
	@Override
	public void cacheResult(ArtworkCollection artworkCollection) {
		entityCache.putResult(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionImpl.class, artworkCollection.getPrimaryKey(),
			artworkCollection);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				artworkCollection.getUuid(), artworkCollection.getGroupId()
			},
			artworkCollection);

		artworkCollection.resetOriginalValues();
	}

	/**
	 * Caches the artwork collections in the entity cache if it is enabled.
	 *
	 * @param artworkCollections the artwork collections
	 */
	@Override
	public void cacheResult(List<ArtworkCollection> artworkCollections) {
		for (ArtworkCollection artworkCollection : artworkCollections) {
			if (entityCache.getResult(
					ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
					ArtworkCollectionImpl.class,
					artworkCollection.getPrimaryKey()) == null) {

				cacheResult(artworkCollection);
			}
			else {
				artworkCollection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all artwork collections.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ArtworkCollectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the artwork collection.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ArtworkCollection artworkCollection) {
		entityCache.removeResult(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionImpl.class, artworkCollection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(ArtworkCollectionModelImpl)artworkCollection, true);
	}

	@Override
	public void clearCache(List<ArtworkCollection> artworkCollections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ArtworkCollection artworkCollection : artworkCollections) {
			entityCache.removeResult(
				ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
				ArtworkCollectionImpl.class, artworkCollection.getPrimaryKey());

			clearUniqueFindersCache(
				(ArtworkCollectionModelImpl)artworkCollection, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
				ArtworkCollectionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ArtworkCollectionModelImpl artworkCollectionModelImpl) {

		Object[] args = new Object[] {
			artworkCollectionModelImpl.getUuid(),
			artworkCollectionModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, artworkCollectionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ArtworkCollectionModelImpl artworkCollectionModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				artworkCollectionModelImpl.getUuid(),
				artworkCollectionModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((artworkCollectionModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				artworkCollectionModelImpl.getOriginalUuid(),
				artworkCollectionModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new artwork collection with the primary key. Does not add the artwork collection to the database.
	 *
	 * @param collectionId the primary key for the new artwork collection
	 * @return the new artwork collection
	 */
	@Override
	public ArtworkCollection create(long collectionId) {
		ArtworkCollection artworkCollection = new ArtworkCollectionImpl();

		artworkCollection.setNew(true);
		artworkCollection.setPrimaryKey(collectionId);

		String uuid = PortalUUIDUtil.generate();

		artworkCollection.setUuid(uuid);

		artworkCollection.setCompanyId(CompanyThreadLocal.getCompanyId());

		return artworkCollection;
	}

	/**
	 * Removes the artwork collection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param collectionId the primary key of the artwork collection
	 * @return the artwork collection that was removed
	 * @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	 */
	@Override
	public ArtworkCollection remove(long collectionId)
		throws NoSuchArtworkCollectionException {

		return remove((Serializable)collectionId);
	}

	/**
	 * Removes the artwork collection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the artwork collection
	 * @return the artwork collection that was removed
	 * @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	 */
	@Override
	public ArtworkCollection remove(Serializable primaryKey)
		throws NoSuchArtworkCollectionException {

		Session session = null;

		try {
			session = openSession();

			ArtworkCollection artworkCollection =
				(ArtworkCollection)session.get(
					ArtworkCollectionImpl.class, primaryKey);

			if (artworkCollection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchArtworkCollectionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(artworkCollection);
		}
		catch (NoSuchArtworkCollectionException noSuchEntityException) {
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
	protected ArtworkCollection removeImpl(
		ArtworkCollection artworkCollection) {

		artworkCollectionToArtworkTableMapper.deleteLeftPrimaryKeyTableMappings(
			artworkCollection.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(artworkCollection)) {
				artworkCollection = (ArtworkCollection)session.get(
					ArtworkCollectionImpl.class,
					artworkCollection.getPrimaryKeyObj());
			}

			if (artworkCollection != null) {
				session.delete(artworkCollection);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (artworkCollection != null) {
			clearCache(artworkCollection);
		}

		return artworkCollection;
	}

	@Override
	public ArtworkCollection updateImpl(ArtworkCollection artworkCollection) {
		boolean isNew = artworkCollection.isNew();

		if (!(artworkCollection instanceof ArtworkCollectionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(artworkCollection.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					artworkCollection);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in artworkCollection proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ArtworkCollection implementation " +
					artworkCollection.getClass());
		}

		ArtworkCollectionModelImpl artworkCollectionModelImpl =
			(ArtworkCollectionModelImpl)artworkCollection;

		if (Validator.isNull(artworkCollection.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			artworkCollection.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (artworkCollection.getCreateDate() == null)) {
			if (serviceContext == null) {
				artworkCollection.setCreateDate(now);
			}
			else {
				artworkCollection.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!artworkCollectionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				artworkCollection.setModifiedDate(now);
			}
			else {
				artworkCollection.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (artworkCollection.isNew()) {
				session.save(artworkCollection);

				artworkCollection.setNew(false);
			}
			else {
				artworkCollection = (ArtworkCollection)session.merge(
					artworkCollection);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ArtworkCollectionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {artworkCollectionModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				artworkCollectionModelImpl.getUuid(),
				artworkCollectionModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {artworkCollectionModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((artworkCollectionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					artworkCollectionModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {artworkCollectionModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((artworkCollectionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					artworkCollectionModelImpl.getOriginalUuid(),
					artworkCollectionModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					artworkCollectionModelImpl.getUuid(),
					artworkCollectionModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((artworkCollectionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					artworkCollectionModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {artworkCollectionModelImpl.getGroupId()};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}
		}

		entityCache.putResult(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionImpl.class, artworkCollection.getPrimaryKey(),
			artworkCollection, false);

		clearUniqueFindersCache(artworkCollectionModelImpl, false);
		cacheUniqueFindersCache(artworkCollectionModelImpl);

		artworkCollection.resetOriginalValues();

		return artworkCollection;
	}

	/**
	 * Returns the artwork collection with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the artwork collection
	 * @return the artwork collection
	 * @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	 */
	@Override
	public ArtworkCollection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchArtworkCollectionException {

		ArtworkCollection artworkCollection = fetchByPrimaryKey(primaryKey);

		if (artworkCollection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchArtworkCollectionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return artworkCollection;
	}

	/**
	 * Returns the artwork collection with the primary key or throws a <code>NoSuchArtworkCollectionException</code> if it could not be found.
	 *
	 * @param collectionId the primary key of the artwork collection
	 * @return the artwork collection
	 * @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	 */
	@Override
	public ArtworkCollection findByPrimaryKey(long collectionId)
		throws NoSuchArtworkCollectionException {

		return findByPrimaryKey((Serializable)collectionId);
	}

	/**
	 * Returns the artwork collection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the artwork collection
	 * @return the artwork collection, or <code>null</code> if a artwork collection with the primary key could not be found
	 */
	@Override
	public ArtworkCollection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ArtworkCollection artworkCollection = (ArtworkCollection)serializable;

		if (artworkCollection == null) {
			Session session = null;

			try {
				session = openSession();

				artworkCollection = (ArtworkCollection)session.get(
					ArtworkCollectionImpl.class, primaryKey);

				if (artworkCollection != null) {
					cacheResult(artworkCollection);
				}
				else {
					entityCache.putResult(
						ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
						ArtworkCollectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
					ArtworkCollectionImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return artworkCollection;
	}

	/**
	 * Returns the artwork collection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param collectionId the primary key of the artwork collection
	 * @return the artwork collection, or <code>null</code> if a artwork collection with the primary key could not be found
	 */
	@Override
	public ArtworkCollection fetchByPrimaryKey(long collectionId) {
		return fetchByPrimaryKey((Serializable)collectionId);
	}

	@Override
	public Map<Serializable, ArtworkCollection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ArtworkCollection> map =
			new HashMap<Serializable, ArtworkCollection>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ArtworkCollection artworkCollection = fetchByPrimaryKey(primaryKey);

			if (artworkCollection != null) {
				map.put(primaryKey, artworkCollection);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
				ArtworkCollectionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ArtworkCollection)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_ARTWORKCOLLECTION_WHERE_PKS_IN);

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

			for (ArtworkCollection artworkCollection :
					(List<ArtworkCollection>)query.list()) {

				map.put(
					artworkCollection.getPrimaryKeyObj(), artworkCollection);

				cacheResult(artworkCollection);

				uncachedPrimaryKeys.remove(
					artworkCollection.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
					ArtworkCollectionImpl.class, primaryKey, nullModel);
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
	 * Returns all the artwork collections.
	 *
	 * @return the artwork collections
	 */
	@Override
	public List<ArtworkCollection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the artwork collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @return the range of artwork collections
	 */
	@Override
	public List<ArtworkCollection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the artwork collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of artwork collections
	 */
	@Override
	public List<ArtworkCollection> findAll(
		int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the artwork collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of artwork collections
	 */
	@Override
	public List<ArtworkCollection> findAll(
		int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator,
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

		List<ArtworkCollection> list = null;

		if (useFinderCache) {
			list = (List<ArtworkCollection>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ARTWORKCOLLECTION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ARTWORKCOLLECTION;

				sql = sql.concat(ArtworkCollectionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ArtworkCollection>)QueryUtil.list(
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
	 * Removes all the artwork collections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ArtworkCollection artworkCollection : findAll()) {
			remove(artworkCollection);
		}
	}

	/**
	 * Returns the number of artwork collections.
	 *
	 * @return the number of artwork collections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ARTWORKCOLLECTION);

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

	/**
	 * Returns the primaryKeys of artworks associated with the artwork collection.
	 *
	 * @param pk the primary key of the artwork collection
	 * @return long[] of the primaryKeys of artworks associated with the artwork collection
	 */
	@Override
	public long[] getArtworkPrimaryKeys(long pk) {
		long[] pks = artworkCollectionToArtworkTableMapper.getRightPrimaryKeys(
			pk);

		return pks.clone();
	}

	/**
	 * Returns all the artworks associated with the artwork collection.
	 *
	 * @param pk the primary key of the artwork collection
	 * @return the artworks associated with the artwork collection
	 */
	@Override
	public List<eu.strasbourg.service.artwork.model.Artwork> getArtworks(
		long pk) {

		return getArtworks(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the artworks associated with the artwork collection.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the artwork collection
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @return the range of artworks associated with the artwork collection
	 */
	@Override
	public List<eu.strasbourg.service.artwork.model.Artwork> getArtworks(
		long pk, int start, int end) {

		return getArtworks(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the artworks associated with the artwork collection.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArtworkCollectionModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the artwork collection
	 * @param start the lower bound of the range of artwork collections
	 * @param end the upper bound of the range of artwork collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of artworks associated with the artwork collection
	 */
	@Override
	public List<eu.strasbourg.service.artwork.model.Artwork> getArtworks(
		long pk, int start, int end,
		OrderByComparator<eu.strasbourg.service.artwork.model.Artwork>
			orderByComparator) {

		return artworkCollectionToArtworkTableMapper.getRightBaseModels(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of artworks associated with the artwork collection.
	 *
	 * @param pk the primary key of the artwork collection
	 * @return the number of artworks associated with the artwork collection
	 */
	@Override
	public int getArtworksSize(long pk) {
		long[] pks = artworkCollectionToArtworkTableMapper.getRightPrimaryKeys(
			pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the artwork is associated with the artwork collection.
	 *
	 * @param pk the primary key of the artwork collection
	 * @param artworkPK the primary key of the artwork
	 * @return <code>true</code> if the artwork is associated with the artwork collection; <code>false</code> otherwise
	 */
	@Override
	public boolean containsArtwork(long pk, long artworkPK) {
		return artworkCollectionToArtworkTableMapper.containsTableMapping(
			pk, artworkPK);
	}

	/**
	 * Returns <code>true</code> if the artwork collection has any artworks associated with it.
	 *
	 * @param pk the primary key of the artwork collection to check for associations with artworks
	 * @return <code>true</code> if the artwork collection has any artworks associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsArtworks(long pk) {
		if (getArtworksSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the artwork collection and the artwork. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork collection
	 * @param artworkPK the primary key of the artwork
	 */
	@Override
	public void addArtwork(long pk, long artworkPK) {
		ArtworkCollection artworkCollection = fetchByPrimaryKey(pk);

		if (artworkCollection == null) {
			artworkCollectionToArtworkTableMapper.addTableMapping(
				CompanyThreadLocal.getCompanyId(), pk, artworkPK);
		}
		else {
			artworkCollectionToArtworkTableMapper.addTableMapping(
				artworkCollection.getCompanyId(), pk, artworkPK);
		}
	}

	/**
	 * Adds an association between the artwork collection and the artwork. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork collection
	 * @param artwork the artwork
	 */
	@Override
	public void addArtwork(
		long pk, eu.strasbourg.service.artwork.model.Artwork artwork) {

		ArtworkCollection artworkCollection = fetchByPrimaryKey(pk);

		if (artworkCollection == null) {
			artworkCollectionToArtworkTableMapper.addTableMapping(
				CompanyThreadLocal.getCompanyId(), pk, artwork.getPrimaryKey());
		}
		else {
			artworkCollectionToArtworkTableMapper.addTableMapping(
				artworkCollection.getCompanyId(), pk, artwork.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the artwork collection and the artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork collection
	 * @param artworkPKs the primary keys of the artworks
	 */
	@Override
	public void addArtworks(long pk, long[] artworkPKs) {
		long companyId = 0;

		ArtworkCollection artworkCollection = fetchByPrimaryKey(pk);

		if (artworkCollection == null) {
			companyId = CompanyThreadLocal.getCompanyId();
		}
		else {
			companyId = artworkCollection.getCompanyId();
		}

		artworkCollectionToArtworkTableMapper.addTableMappings(
			companyId, pk, artworkPKs);
	}

	/**
	 * Adds an association between the artwork collection and the artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork collection
	 * @param artworks the artworks
	 */
	@Override
	public void addArtworks(
		long pk, List<eu.strasbourg.service.artwork.model.Artwork> artworks) {

		addArtworks(
			pk,
			ListUtil.toLongArray(
				artworks,
				eu.strasbourg.service.artwork.model.Artwork.
					ARTWORK_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the artwork collection and its artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork collection to clear the associated artworks from
	 */
	@Override
	public void clearArtworks(long pk) {
		artworkCollectionToArtworkTableMapper.deleteLeftPrimaryKeyTableMappings(
			pk);
	}

	/**
	 * Removes the association between the artwork collection and the artwork. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork collection
	 * @param artworkPK the primary key of the artwork
	 */
	@Override
	public void removeArtwork(long pk, long artworkPK) {
		artworkCollectionToArtworkTableMapper.deleteTableMapping(pk, artworkPK);
	}

	/**
	 * Removes the association between the artwork collection and the artwork. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork collection
	 * @param artwork the artwork
	 */
	@Override
	public void removeArtwork(
		long pk, eu.strasbourg.service.artwork.model.Artwork artwork) {

		artworkCollectionToArtworkTableMapper.deleteTableMapping(
			pk, artwork.getPrimaryKey());
	}

	/**
	 * Removes the association between the artwork collection and the artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork collection
	 * @param artworkPKs the primary keys of the artworks
	 */
	@Override
	public void removeArtworks(long pk, long[] artworkPKs) {
		artworkCollectionToArtworkTableMapper.deleteTableMappings(
			pk, artworkPKs);
	}

	/**
	 * Removes the association between the artwork collection and the artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork collection
	 * @param artworks the artworks
	 */
	@Override
	public void removeArtworks(
		long pk, List<eu.strasbourg.service.artwork.model.Artwork> artworks) {

		removeArtworks(
			pk,
			ListUtil.toLongArray(
				artworks,
				eu.strasbourg.service.artwork.model.Artwork.
					ARTWORK_ID_ACCESSOR));
	}

	/**
	 * Sets the artworks associated with the artwork collection, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork collection
	 * @param artworkPKs the primary keys of the artworks to be associated with the artwork collection
	 */
	@Override
	public void setArtworks(long pk, long[] artworkPKs) {
		Set<Long> newArtworkPKsSet = SetUtil.fromArray(artworkPKs);
		Set<Long> oldArtworkPKsSet = SetUtil.fromArray(
			artworkCollectionToArtworkTableMapper.getRightPrimaryKeys(pk));

		Set<Long> removeArtworkPKsSet = new HashSet<Long>(oldArtworkPKsSet);

		removeArtworkPKsSet.removeAll(newArtworkPKsSet);

		artworkCollectionToArtworkTableMapper.deleteTableMappings(
			pk, ArrayUtil.toLongArray(removeArtworkPKsSet));

		newArtworkPKsSet.removeAll(oldArtworkPKsSet);

		long companyId = 0;

		ArtworkCollection artworkCollection = fetchByPrimaryKey(pk);

		if (artworkCollection == null) {
			companyId = CompanyThreadLocal.getCompanyId();
		}
		else {
			companyId = artworkCollection.getCompanyId();
		}

		artworkCollectionToArtworkTableMapper.addTableMappings(
			companyId, pk, ArrayUtil.toLongArray(newArtworkPKsSet));
	}

	/**
	 * Sets the artworks associated with the artwork collection, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the artwork collection
	 * @param artworks the artworks to be associated with the artwork collection
	 */
	@Override
	public void setArtworks(
		long pk, List<eu.strasbourg.service.artwork.model.Artwork> artworks) {

		try {
			long[] artworkPKs = new long[artworks.size()];

			for (int i = 0; i < artworks.size(); i++) {
				eu.strasbourg.service.artwork.model.Artwork artwork =
					artworks.get(i);

				artworkPKs[i] = artwork.getPrimaryKey();
			}

			setArtworks(pk, artworkPKs);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ArtworkCollectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the artwork collection persistence.
	 */
	public void afterPropertiesSet() {
		artworkCollectionToArtworkTableMapper =
			TableMapperFactory.getTableMapper(
				"artwork_ArtworkToArtworkCollection", "companyId",
				"collectionId", "artworkId", this, artworkPersistence);

		_finderPathWithPaginationFindAll = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED,
			ArtworkCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED,
			ArtworkCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED,
			ArtworkCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED,
			ArtworkCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			ArtworkCollectionModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED,
			ArtworkCollectionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			ArtworkCollectionModelImpl.UUID_COLUMN_BITMASK |
			ArtworkCollectionModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED,
			ArtworkCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED,
			ArtworkCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			ArtworkCollectionModelImpl.UUID_COLUMN_BITMASK |
			ArtworkCollectionModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED,
			ArtworkCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED,
			ArtworkCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			ArtworkCollectionModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			ArtworkCollectionModelImpl.ENTITY_CACHE_ENABLED,
			ArtworkCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(ArtworkCollectionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper(
			"artwork_ArtworkToArtworkCollection");
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	@BeanReference(type = ArtworkPersistence.class)
	protected ArtworkPersistence artworkPersistence;

	protected TableMapper
		<ArtworkCollection, eu.strasbourg.service.artwork.model.Artwork>
			artworkCollectionToArtworkTableMapper;

	private static final String _SQL_SELECT_ARTWORKCOLLECTION =
		"SELECT artworkCollection FROM ArtworkCollection artworkCollection";

	private static final String _SQL_SELECT_ARTWORKCOLLECTION_WHERE_PKS_IN =
		"SELECT artworkCollection FROM ArtworkCollection artworkCollection WHERE collectionId IN (";

	private static final String _SQL_SELECT_ARTWORKCOLLECTION_WHERE =
		"SELECT artworkCollection FROM ArtworkCollection artworkCollection WHERE ";

	private static final String _SQL_COUNT_ARTWORKCOLLECTION =
		"SELECT COUNT(artworkCollection) FROM ArtworkCollection artworkCollection";

	private static final String _SQL_COUNT_ARTWORKCOLLECTION_WHERE =
		"SELECT COUNT(artworkCollection) FROM ArtworkCollection artworkCollection WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "artworkCollection.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ArtworkCollection exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ArtworkCollection exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ArtworkCollectionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}