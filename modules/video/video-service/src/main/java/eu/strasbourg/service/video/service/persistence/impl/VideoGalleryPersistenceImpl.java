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

package eu.strasbourg.service.video.service.persistence.impl;

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

import eu.strasbourg.service.video.exception.NoSuchVideoGalleryException;
import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.model.impl.VideoGalleryImpl;
import eu.strasbourg.service.video.model.impl.VideoGalleryModelImpl;
import eu.strasbourg.service.video.service.persistence.VideoGalleryPersistence;
import eu.strasbourg.service.video.service.persistence.VideoPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the video gallery service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @generated
 */
public class VideoGalleryPersistenceImpl
	extends BasePersistenceImpl<VideoGallery>
	implements VideoGalleryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>VideoGalleryUtil</code> to access the video gallery persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		VideoGalleryImpl.class.getName();

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
	 * Returns all the video galleries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching video galleries
	 */
	@Override
	public List<VideoGallery> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the video galleries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @return the range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the video galleries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the video galleries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator,
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

		List<VideoGallery> list = null;

		if (useFinderCache) {
			list = (List<VideoGallery>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (VideoGallery videoGallery : list) {
					if (!uuid.equals(videoGallery.getUuid())) {
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

			sb.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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
				sb.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
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

				list = (List<VideoGallery>)QueryUtil.list(
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
	 * Returns the first video gallery in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching video gallery
	 * @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery findByUuid_First(
			String uuid, OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {

		VideoGallery videoGallery = fetchByUuid_First(uuid, orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchVideoGalleryException(sb.toString());
	}

	/**
	 * Returns the first video gallery in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByUuid_First(
		String uuid, OrderByComparator<VideoGallery> orderByComparator) {

		List<VideoGallery> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last video gallery in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching video gallery
	 * @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery findByUuid_Last(
			String uuid, OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {

		VideoGallery videoGallery = fetchByUuid_Last(uuid, orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchVideoGalleryException(sb.toString());
	}

	/**
	 * Returns the last video gallery in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByUuid_Last(
		String uuid, OrderByComparator<VideoGallery> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<VideoGallery> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the video galleries before and after the current video gallery in the ordered set where uuid = &#63;.
	 *
	 * @param galleryId the primary key of the current video gallery
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next video gallery
	 * @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	 */
	@Override
	public VideoGallery[] findByUuid_PrevAndNext(
			long galleryId, String uuid,
			OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {

		uuid = Objects.toString(uuid, "");

		VideoGallery videoGallery = findByPrimaryKey(galleryId);

		Session session = null;

		try {
			session = openSession();

			VideoGallery[] array = new VideoGalleryImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, videoGallery, uuid, orderByComparator, true);

			array[1] = videoGallery;

			array[2] = getByUuid_PrevAndNext(
				session, videoGallery, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected VideoGallery getByUuid_PrevAndNext(
		Session session, VideoGallery videoGallery, String uuid,
		OrderByComparator<VideoGallery> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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
			sb.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(videoGallery)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<VideoGallery> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the video galleries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (VideoGallery videoGallery :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(videoGallery);
		}
	}

	/**
	 * Returns the number of video galleries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching video galleries
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_VIDEOGALLERY_WHERE);

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
		"videoGallery.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(videoGallery.uuid IS NULL OR videoGallery.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the video gallery where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchVideoGalleryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching video gallery
	 * @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery findByUUID_G(String uuid, long groupId)
		throws NoSuchVideoGalleryException {

		VideoGallery videoGallery = fetchByUUID_G(uuid, groupId);

		if (videoGallery == null) {
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

			throw new NoSuchVideoGalleryException(sb.toString());
		}

		return videoGallery;
	}

	/**
	 * Returns the video gallery where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the video gallery where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByUUID_G(
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

		if (result instanceof VideoGallery) {
			VideoGallery videoGallery = (VideoGallery)result;

			if (!Objects.equals(uuid, videoGallery.getUuid()) ||
				(groupId != videoGallery.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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

				List<VideoGallery> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					VideoGallery videoGallery = list.get(0);

					result = videoGallery;

					cacheResult(videoGallery);
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
			return (VideoGallery)result;
		}
	}

	/**
	 * Removes the video gallery where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the video gallery that was removed
	 */
	@Override
	public VideoGallery removeByUUID_G(String uuid, long groupId)
		throws NoSuchVideoGalleryException {

		VideoGallery videoGallery = findByUUID_G(uuid, groupId);

		return remove(videoGallery);
	}

	/**
	 * Returns the number of video galleries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching video galleries
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_VIDEOGALLERY_WHERE);

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
		"videoGallery.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(videoGallery.uuid IS NULL OR videoGallery.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"videoGallery.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the video galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching video galleries
	 */
	@Override
	public List<VideoGallery> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the video galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @return the range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the video galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the video galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator,
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

		List<VideoGallery> list = null;

		if (useFinderCache) {
			list = (List<VideoGallery>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (VideoGallery videoGallery : list) {
					if (!uuid.equals(videoGallery.getUuid()) ||
						(companyId != videoGallery.getCompanyId())) {

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

			sb.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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
				sb.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
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

				list = (List<VideoGallery>)QueryUtil.list(
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
	 * Returns the first video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching video gallery
	 * @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {

		VideoGallery videoGallery = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchVideoGalleryException(sb.toString());
	}

	/**
	 * Returns the first video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<VideoGallery> orderByComparator) {

		List<VideoGallery> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching video gallery
	 * @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {

		VideoGallery videoGallery = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchVideoGalleryException(sb.toString());
	}

	/**
	 * Returns the last video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<VideoGallery> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<VideoGallery> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the video galleries before and after the current video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param galleryId the primary key of the current video gallery
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next video gallery
	 * @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	 */
	@Override
	public VideoGallery[] findByUuid_C_PrevAndNext(
			long galleryId, String uuid, long companyId,
			OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {

		uuid = Objects.toString(uuid, "");

		VideoGallery videoGallery = findByPrimaryKey(galleryId);

		Session session = null;

		try {
			session = openSession();

			VideoGallery[] array = new VideoGalleryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, videoGallery, uuid, companyId, orderByComparator,
				true);

			array[1] = videoGallery;

			array[2] = getByUuid_C_PrevAndNext(
				session, videoGallery, uuid, companyId, orderByComparator,
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

	protected VideoGallery getByUuid_C_PrevAndNext(
		Session session, VideoGallery videoGallery, String uuid, long companyId,
		OrderByComparator<VideoGallery> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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
			sb.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(videoGallery)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<VideoGallery> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the video galleries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (VideoGallery videoGallery :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(videoGallery);
		}
	}

	/**
	 * Returns the number of video galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching video galleries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_VIDEOGALLERY_WHERE);

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
		"videoGallery.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(videoGallery.uuid IS NULL OR videoGallery.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"videoGallery.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the video galleries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching video galleries
	 */
	@Override
	public List<VideoGallery> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the video galleries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @return the range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the video galleries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the video galleries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator,
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

		List<VideoGallery> list = null;

		if (useFinderCache) {
			list = (List<VideoGallery>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (VideoGallery videoGallery : list) {
					if (groupId != videoGallery.getGroupId()) {
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

			sb.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<VideoGallery>)QueryUtil.list(
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
	 * Returns the first video gallery in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching video gallery
	 * @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery findByGroupId_First(
			long groupId, OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {

		VideoGallery videoGallery = fetchByGroupId_First(
			groupId, orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchVideoGalleryException(sb.toString());
	}

	/**
	 * Returns the first video gallery in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByGroupId_First(
		long groupId, OrderByComparator<VideoGallery> orderByComparator) {

		List<VideoGallery> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last video gallery in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching video gallery
	 * @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery findByGroupId_Last(
			long groupId, OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {

		VideoGallery videoGallery = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchVideoGalleryException(sb.toString());
	}

	/**
	 * Returns the last video gallery in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByGroupId_Last(
		long groupId, OrderByComparator<VideoGallery> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<VideoGallery> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the video galleries before and after the current video gallery in the ordered set where groupId = &#63;.
	 *
	 * @param galleryId the primary key of the current video gallery
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next video gallery
	 * @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	 */
	@Override
	public VideoGallery[] findByGroupId_PrevAndNext(
			long galleryId, long groupId,
			OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {

		VideoGallery videoGallery = findByPrimaryKey(galleryId);

		Session session = null;

		try {
			session = openSession();

			VideoGallery[] array = new VideoGalleryImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, videoGallery, groupId, orderByComparator, true);

			array[1] = videoGallery;

			array[2] = getByGroupId_PrevAndNext(
				session, videoGallery, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected VideoGallery getByGroupId_PrevAndNext(
		Session session, VideoGallery videoGallery, long groupId,
		OrderByComparator<VideoGallery> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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
			sb.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(videoGallery)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<VideoGallery> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the video galleries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (VideoGallery videoGallery :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(videoGallery);
		}
	}

	/**
	 * Returns the number of video galleries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching video galleries
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_VIDEOGALLERY_WHERE);

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
		"videoGallery.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByPublicationDateAndStatus;
	private FinderPath _finderPathWithPaginationCountByPublicationDateAndStatus;

	/**
	 * Returns all the video galleries where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @return the matching video galleries
	 */
	@Override
	public List<VideoGallery> findByPublicationDateAndStatus(
		Date publicationDate, int status) {

		return findByPublicationDateAndStatus(
			publicationDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the video galleries where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @return the range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end) {

		return findByPublicationDateAndStatus(
			publicationDate, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the video galleries where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator) {

		return findByPublicationDateAndStatus(
			publicationDate, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the video galleries where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByPublicationDateAndStatus;
		finderArgs = new Object[] {
			_getTime(publicationDate), status, start, end, orderByComparator
		};

		List<VideoGallery> list = null;

		if (useFinderCache) {
			list = (List<VideoGallery>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (VideoGallery videoGallery : list) {
					if ((publicationDate.getTime() <=
							videoGallery.getPublicationDate().getTime()) ||
						(status != videoGallery.getStatus())) {

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

			sb.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

			boolean bindPublicationDate = false;

			if (publicationDate == null) {
				sb.append(
					_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_1);
			}
			else {
				bindPublicationDate = true;

				sb.append(
					_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_2);
			}

			sb.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublicationDate) {
					queryPos.add(new Timestamp(publicationDate.getTime()));
				}

				queryPos.add(status);

				list = (List<VideoGallery>)QueryUtil.list(
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
	 * Returns the first video gallery in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching video gallery
	 * @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery findByPublicationDateAndStatus_First(
			Date publicationDate, int status,
			OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {

		VideoGallery videoGallery = fetchByPublicationDateAndStatus_First(
			publicationDate, status, orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publicationDate<");
		sb.append(publicationDate);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchVideoGalleryException(sb.toString());
	}

	/**
	 * Returns the first video gallery in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByPublicationDateAndStatus_First(
		Date publicationDate, int status,
		OrderByComparator<VideoGallery> orderByComparator) {

		List<VideoGallery> list = findByPublicationDateAndStatus(
			publicationDate, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last video gallery in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching video gallery
	 * @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery findByPublicationDateAndStatus_Last(
			Date publicationDate, int status,
			OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {

		VideoGallery videoGallery = fetchByPublicationDateAndStatus_Last(
			publicationDate, status, orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publicationDate<");
		sb.append(publicationDate);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchVideoGalleryException(sb.toString());
	}

	/**
	 * Returns the last video gallery in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByPublicationDateAndStatus_Last(
		Date publicationDate, int status,
		OrderByComparator<VideoGallery> orderByComparator) {

		int count = countByPublicationDateAndStatus(publicationDate, status);

		if (count == 0) {
			return null;
		}

		List<VideoGallery> list = findByPublicationDateAndStatus(
			publicationDate, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the video galleries before and after the current video gallery in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param galleryId the primary key of the current video gallery
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next video gallery
	 * @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	 */
	@Override
	public VideoGallery[] findByPublicationDateAndStatus_PrevAndNext(
			long galleryId, Date publicationDate, int status,
			OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {

		VideoGallery videoGallery = findByPrimaryKey(galleryId);

		Session session = null;

		try {
			session = openSession();

			VideoGallery[] array = new VideoGalleryImpl[3];

			array[0] = getByPublicationDateAndStatus_PrevAndNext(
				session, videoGallery, publicationDate, status,
				orderByComparator, true);

			array[1] = videoGallery;

			array[2] = getByPublicationDateAndStatus_PrevAndNext(
				session, videoGallery, publicationDate, status,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected VideoGallery getByPublicationDateAndStatus_PrevAndNext(
		Session session, VideoGallery videoGallery, Date publicationDate,
		int status, OrderByComparator<VideoGallery> orderByComparator,
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

		sb.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

		boolean bindPublicationDate = false;

		if (publicationDate == null) {
			sb.append(
				_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_1);
		}
		else {
			bindPublicationDate = true;

			sb.append(
				_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_2);
		}

		sb.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_STATUS_2);

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
			sb.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindPublicationDate) {
			queryPos.add(new Timestamp(publicationDate.getTime()));
		}

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(videoGallery)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<VideoGallery> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the video galleries where publicationDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 */
	@Override
	public void removeByPublicationDateAndStatus(
		Date publicationDate, int status) {

		for (VideoGallery videoGallery :
				findByPublicationDateAndStatus(
					publicationDate, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(videoGallery);
		}
	}

	/**
	 * Returns the number of video galleries where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @return the number of matching video galleries
	 */
	@Override
	public int countByPublicationDateAndStatus(
		Date publicationDate, int status) {

		FinderPath finderPath =
			_finderPathWithPaginationCountByPublicationDateAndStatus;

		Object[] finderArgs = new Object[] {_getTime(publicationDate), status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_VIDEOGALLERY_WHERE);

			boolean bindPublicationDate = false;

			if (publicationDate == null) {
				sb.append(
					_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_1);
			}
			else {
				bindPublicationDate = true;

				sb.append(
					_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_2);
			}

			sb.append(_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublicationDate) {
					queryPos.add(new Timestamp(publicationDate.getTime()));
				}

				queryPos.add(status);

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

	private static final String
		_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_1 =
			"videoGallery.publicationDate IS NULL AND ";

	private static final String
		_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_2 =
			"videoGallery.publicationDate < ? AND ";

	private static final String
		_FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_STATUS_2 =
			"videoGallery.status = ?";

	public VideoGalleryPersistenceImpl() {
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

		setModelClass(VideoGallery.class);
	}

	/**
	 * Caches the video gallery in the entity cache if it is enabled.
	 *
	 * @param videoGallery the video gallery
	 */
	@Override
	public void cacheResult(VideoGallery videoGallery) {
		entityCache.putResult(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED, VideoGalleryImpl.class,
			videoGallery.getPrimaryKey(), videoGallery);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {videoGallery.getUuid(), videoGallery.getGroupId()},
			videoGallery);

		videoGallery.resetOriginalValues();
	}

	/**
	 * Caches the video galleries in the entity cache if it is enabled.
	 *
	 * @param videoGalleries the video galleries
	 */
	@Override
	public void cacheResult(List<VideoGallery> videoGalleries) {
		for (VideoGallery videoGallery : videoGalleries) {
			if (entityCache.getResult(
					VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
					VideoGalleryImpl.class, videoGallery.getPrimaryKey()) ==
						null) {

				cacheResult(videoGallery);
			}
			else {
				videoGallery.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all video galleries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VideoGalleryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the video gallery.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VideoGallery videoGallery) {
		entityCache.removeResult(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED, VideoGalleryImpl.class,
			videoGallery.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((VideoGalleryModelImpl)videoGallery, true);
	}

	@Override
	public void clearCache(List<VideoGallery> videoGalleries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VideoGallery videoGallery : videoGalleries) {
			entityCache.removeResult(
				VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
				VideoGalleryImpl.class, videoGallery.getPrimaryKey());

			clearUniqueFindersCache((VideoGalleryModelImpl)videoGallery, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
				VideoGalleryImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		VideoGalleryModelImpl videoGalleryModelImpl) {

		Object[] args = new Object[] {
			videoGalleryModelImpl.getUuid(), videoGalleryModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, videoGalleryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		VideoGalleryModelImpl videoGalleryModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				videoGalleryModelImpl.getUuid(),
				videoGalleryModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((videoGalleryModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				videoGalleryModelImpl.getOriginalUuid(),
				videoGalleryModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new video gallery with the primary key. Does not add the video gallery to the database.
	 *
	 * @param galleryId the primary key for the new video gallery
	 * @return the new video gallery
	 */
	@Override
	public VideoGallery create(long galleryId) {
		VideoGallery videoGallery = new VideoGalleryImpl();

		videoGallery.setNew(true);
		videoGallery.setPrimaryKey(galleryId);

		String uuid = PortalUUIDUtil.generate();

		videoGallery.setUuid(uuid);

		videoGallery.setCompanyId(CompanyThreadLocal.getCompanyId());

		return videoGallery;
	}

	/**
	 * Removes the video gallery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param galleryId the primary key of the video gallery
	 * @return the video gallery that was removed
	 * @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	 */
	@Override
	public VideoGallery remove(long galleryId)
		throws NoSuchVideoGalleryException {

		return remove((Serializable)galleryId);
	}

	/**
	 * Removes the video gallery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the video gallery
	 * @return the video gallery that was removed
	 * @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	 */
	@Override
	public VideoGallery remove(Serializable primaryKey)
		throws NoSuchVideoGalleryException {

		Session session = null;

		try {
			session = openSession();

			VideoGallery videoGallery = (VideoGallery)session.get(
				VideoGalleryImpl.class, primaryKey);

			if (videoGallery == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVideoGalleryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(videoGallery);
		}
		catch (NoSuchVideoGalleryException noSuchEntityException) {
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
	protected VideoGallery removeImpl(VideoGallery videoGallery) {
		videoGalleryToVideoTableMapper.deleteLeftPrimaryKeyTableMappings(
			videoGallery.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(videoGallery)) {
				videoGallery = (VideoGallery)session.get(
					VideoGalleryImpl.class, videoGallery.getPrimaryKeyObj());
			}

			if (videoGallery != null) {
				session.delete(videoGallery);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (videoGallery != null) {
			clearCache(videoGallery);
		}

		return videoGallery;
	}

	@Override
	public VideoGallery updateImpl(VideoGallery videoGallery) {
		boolean isNew = videoGallery.isNew();

		if (!(videoGallery instanceof VideoGalleryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(videoGallery.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					videoGallery);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in videoGallery proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom VideoGallery implementation " +
					videoGallery.getClass());
		}

		VideoGalleryModelImpl videoGalleryModelImpl =
			(VideoGalleryModelImpl)videoGallery;

		if (Validator.isNull(videoGallery.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			videoGallery.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (videoGallery.getCreateDate() == null)) {
			if (serviceContext == null) {
				videoGallery.setCreateDate(now);
			}
			else {
				videoGallery.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!videoGalleryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				videoGallery.setModifiedDate(now);
			}
			else {
				videoGallery.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (videoGallery.isNew()) {
				session.save(videoGallery);

				videoGallery.setNew(false);
			}
			else {
				videoGallery = (VideoGallery)session.merge(videoGallery);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!VideoGalleryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {videoGalleryModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				videoGalleryModelImpl.getUuid(),
				videoGalleryModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {videoGalleryModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((videoGalleryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					videoGalleryModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {videoGalleryModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((videoGalleryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					videoGalleryModelImpl.getOriginalUuid(),
					videoGalleryModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					videoGalleryModelImpl.getUuid(),
					videoGalleryModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((videoGalleryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					videoGalleryModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {videoGalleryModelImpl.getGroupId()};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}
		}

		entityCache.putResult(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED, VideoGalleryImpl.class,
			videoGallery.getPrimaryKey(), videoGallery, false);

		clearUniqueFindersCache(videoGalleryModelImpl, false);
		cacheUniqueFindersCache(videoGalleryModelImpl);

		videoGallery.resetOriginalValues();

		return videoGallery;
	}

	/**
	 * Returns the video gallery with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the video gallery
	 * @return the video gallery
	 * @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	 */
	@Override
	public VideoGallery findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVideoGalleryException {

		VideoGallery videoGallery = fetchByPrimaryKey(primaryKey);

		if (videoGallery == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVideoGalleryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return videoGallery;
	}

	/**
	 * Returns the video gallery with the primary key or throws a <code>NoSuchVideoGalleryException</code> if it could not be found.
	 *
	 * @param galleryId the primary key of the video gallery
	 * @return the video gallery
	 * @throws NoSuchVideoGalleryException if a video gallery with the primary key could not be found
	 */
	@Override
	public VideoGallery findByPrimaryKey(long galleryId)
		throws NoSuchVideoGalleryException {

		return findByPrimaryKey((Serializable)galleryId);
	}

	/**
	 * Returns the video gallery with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the video gallery
	 * @return the video gallery, or <code>null</code> if a video gallery with the primary key could not be found
	 */
	@Override
	public VideoGallery fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED, VideoGalleryImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VideoGallery videoGallery = (VideoGallery)serializable;

		if (videoGallery == null) {
			Session session = null;

			try {
				session = openSession();

				videoGallery = (VideoGallery)session.get(
					VideoGalleryImpl.class, primaryKey);

				if (videoGallery != null) {
					cacheResult(videoGallery);
				}
				else {
					entityCache.putResult(
						VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
						VideoGalleryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
					VideoGalleryImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return videoGallery;
	}

	/**
	 * Returns the video gallery with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param galleryId the primary key of the video gallery
	 * @return the video gallery, or <code>null</code> if a video gallery with the primary key could not be found
	 */
	@Override
	public VideoGallery fetchByPrimaryKey(long galleryId) {
		return fetchByPrimaryKey((Serializable)galleryId);
	}

	@Override
	public Map<Serializable, VideoGallery> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VideoGallery> map =
			new HashMap<Serializable, VideoGallery>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VideoGallery videoGallery = fetchByPrimaryKey(primaryKey);

			if (videoGallery != null) {
				map.put(primaryKey, videoGallery);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
				VideoGalleryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VideoGallery)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_VIDEOGALLERY_WHERE_PKS_IN);

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

			for (VideoGallery videoGallery : (List<VideoGallery>)query.list()) {
				map.put(videoGallery.getPrimaryKeyObj(), videoGallery);

				cacheResult(videoGallery);

				uncachedPrimaryKeys.remove(videoGallery.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
					VideoGalleryImpl.class, primaryKey, nullModel);
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
	 * Returns all the video galleries.
	 *
	 * @return the video galleries
	 */
	@Override
	public List<VideoGallery> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the video galleries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @return the range of video galleries
	 */
	@Override
	public List<VideoGallery> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the video galleries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of video galleries
	 */
	@Override
	public List<VideoGallery> findAll(
		int start, int end, OrderByComparator<VideoGallery> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the video galleries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of video galleries
	 */
	@Override
	public List<VideoGallery> findAll(
		int start, int end, OrderByComparator<VideoGallery> orderByComparator,
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

		List<VideoGallery> list = null;

		if (useFinderCache) {
			list = (List<VideoGallery>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_VIDEOGALLERY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_VIDEOGALLERY;

				sql = sql.concat(VideoGalleryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<VideoGallery>)QueryUtil.list(
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
	 * Removes all the video galleries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VideoGallery videoGallery : findAll()) {
			remove(videoGallery);
		}
	}

	/**
	 * Returns the number of video galleries.
	 *
	 * @return the number of video galleries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_VIDEOGALLERY);

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
	 * Returns the primaryKeys of videos associated with the video gallery.
	 *
	 * @param pk the primary key of the video gallery
	 * @return long[] of the primaryKeys of videos associated with the video gallery
	 */
	@Override
	public long[] getVideoPrimaryKeys(long pk) {
		long[] pks = videoGalleryToVideoTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the videos associated with the video gallery.
	 *
	 * @param pk the primary key of the video gallery
	 * @return the videos associated with the video gallery
	 */
	@Override
	public List<eu.strasbourg.service.video.model.Video> getVideos(long pk) {
		return getVideos(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the videos associated with the video gallery.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the video gallery
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @return the range of videos associated with the video gallery
	 */
	@Override
	public List<eu.strasbourg.service.video.model.Video> getVideos(
		long pk, int start, int end) {

		return getVideos(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the videos associated with the video gallery.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VideoGalleryModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the video gallery
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of videos associated with the video gallery
	 */
	@Override
	public List<eu.strasbourg.service.video.model.Video> getVideos(
		long pk, int start, int end,
		OrderByComparator<eu.strasbourg.service.video.model.Video>
			orderByComparator) {

		return videoGalleryToVideoTableMapper.getRightBaseModels(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of videos associated with the video gallery.
	 *
	 * @param pk the primary key of the video gallery
	 * @return the number of videos associated with the video gallery
	 */
	@Override
	public int getVideosSize(long pk) {
		long[] pks = videoGalleryToVideoTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the video is associated with the video gallery.
	 *
	 * @param pk the primary key of the video gallery
	 * @param videoPK the primary key of the video
	 * @return <code>true</code> if the video is associated with the video gallery; <code>false</code> otherwise
	 */
	@Override
	public boolean containsVideo(long pk, long videoPK) {
		return videoGalleryToVideoTableMapper.containsTableMapping(pk, videoPK);
	}

	/**
	 * Returns <code>true</code> if the video gallery has any videos associated with it.
	 *
	 * @param pk the primary key of the video gallery to check for associations with videos
	 * @return <code>true</code> if the video gallery has any videos associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsVideos(long pk) {
		if (getVideosSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the video gallery and the video. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery
	 * @param videoPK the primary key of the video
	 */
	@Override
	public void addVideo(long pk, long videoPK) {
		VideoGallery videoGallery = fetchByPrimaryKey(pk);

		if (videoGallery == null) {
			videoGalleryToVideoTableMapper.addTableMapping(
				CompanyThreadLocal.getCompanyId(), pk, videoPK);
		}
		else {
			videoGalleryToVideoTableMapper.addTableMapping(
				videoGallery.getCompanyId(), pk, videoPK);
		}
	}

	/**
	 * Adds an association between the video gallery and the video. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery
	 * @param video the video
	 */
	@Override
	public void addVideo(
		long pk, eu.strasbourg.service.video.model.Video video) {

		VideoGallery videoGallery = fetchByPrimaryKey(pk);

		if (videoGallery == null) {
			videoGalleryToVideoTableMapper.addTableMapping(
				CompanyThreadLocal.getCompanyId(), pk, video.getPrimaryKey());
		}
		else {
			videoGalleryToVideoTableMapper.addTableMapping(
				videoGallery.getCompanyId(), pk, video.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the video gallery and the videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery
	 * @param videoPKs the primary keys of the videos
	 */
	@Override
	public void addVideos(long pk, long[] videoPKs) {
		long companyId = 0;

		VideoGallery videoGallery = fetchByPrimaryKey(pk);

		if (videoGallery == null) {
			companyId = CompanyThreadLocal.getCompanyId();
		}
		else {
			companyId = videoGallery.getCompanyId();
		}

		videoGalleryToVideoTableMapper.addTableMappings(
			companyId, pk, videoPKs);
	}

	/**
	 * Adds an association between the video gallery and the videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery
	 * @param videos the videos
	 */
	@Override
	public void addVideos(
		long pk, List<eu.strasbourg.service.video.model.Video> videos) {

		addVideos(
			pk,
			ListUtil.toLongArray(
				videos,
				eu.strasbourg.service.video.model.Video.VIDEO_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the video gallery and its videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery to clear the associated videos from
	 */
	@Override
	public void clearVideos(long pk) {
		videoGalleryToVideoTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the video gallery and the video. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery
	 * @param videoPK the primary key of the video
	 */
	@Override
	public void removeVideo(long pk, long videoPK) {
		videoGalleryToVideoTableMapper.deleteTableMapping(pk, videoPK);
	}

	/**
	 * Removes the association between the video gallery and the video. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery
	 * @param video the video
	 */
	@Override
	public void removeVideo(
		long pk, eu.strasbourg.service.video.model.Video video) {

		videoGalleryToVideoTableMapper.deleteTableMapping(
			pk, video.getPrimaryKey());
	}

	/**
	 * Removes the association between the video gallery and the videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery
	 * @param videoPKs the primary keys of the videos
	 */
	@Override
	public void removeVideos(long pk, long[] videoPKs) {
		videoGalleryToVideoTableMapper.deleteTableMappings(pk, videoPKs);
	}

	/**
	 * Removes the association between the video gallery and the videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery
	 * @param videos the videos
	 */
	@Override
	public void removeVideos(
		long pk, List<eu.strasbourg.service.video.model.Video> videos) {

		removeVideos(
			pk,
			ListUtil.toLongArray(
				videos,
				eu.strasbourg.service.video.model.Video.VIDEO_ID_ACCESSOR));
	}

	/**
	 * Sets the videos associated with the video gallery, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery
	 * @param videoPKs the primary keys of the videos to be associated with the video gallery
	 */
	@Override
	public void setVideos(long pk, long[] videoPKs) {
		Set<Long> newVideoPKsSet = SetUtil.fromArray(videoPKs);
		Set<Long> oldVideoPKsSet = SetUtil.fromArray(
			videoGalleryToVideoTableMapper.getRightPrimaryKeys(pk));

		Set<Long> removeVideoPKsSet = new HashSet<Long>(oldVideoPKsSet);

		removeVideoPKsSet.removeAll(newVideoPKsSet);

		videoGalleryToVideoTableMapper.deleteTableMappings(
			pk, ArrayUtil.toLongArray(removeVideoPKsSet));

		newVideoPKsSet.removeAll(oldVideoPKsSet);

		long companyId = 0;

		VideoGallery videoGallery = fetchByPrimaryKey(pk);

		if (videoGallery == null) {
			companyId = CompanyThreadLocal.getCompanyId();
		}
		else {
			companyId = videoGallery.getCompanyId();
		}

		videoGalleryToVideoTableMapper.addTableMappings(
			companyId, pk, ArrayUtil.toLongArray(newVideoPKsSet));
	}

	/**
	 * Sets the videos associated with the video gallery, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery
	 * @param videos the videos to be associated with the video gallery
	 */
	@Override
	public void setVideos(
		long pk, List<eu.strasbourg.service.video.model.Video> videos) {

		try {
			long[] videoPKs = new long[videos.size()];

			for (int i = 0; i < videos.size(); i++) {
				eu.strasbourg.service.video.model.Video video = videos.get(i);

				videoPKs[i] = video.getPrimaryKey();
			}

			setVideos(pk, videoPKs);
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
		return VideoGalleryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the video gallery persistence.
	 */
	public void afterPropertiesSet() {
		videoGalleryToVideoTableMapper = TableMapperFactory.getTableMapper(
			"video_VideoToVideoGallery", "companyId", "galleryId", "videoId",
			this, videoPersistence);

		_finderPathWithPaginationFindAll = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			VideoGalleryModelImpl.UUID_COLUMN_BITMASK |
			VideoGalleryModelImpl.MODIFIEDDATE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			VideoGalleryModelImpl.UUID_COLUMN_BITMASK |
			VideoGalleryModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			VideoGalleryModelImpl.UUID_COLUMN_BITMASK |
			VideoGalleryModelImpl.COMPANYID_COLUMN_BITMASK |
			VideoGalleryModelImpl.MODIFIEDDATE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			VideoGalleryModelImpl.GROUPID_COLUMN_BITMASK |
			VideoGalleryModelImpl.MODIFIEDDATE_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByPublicationDateAndStatus =
			new FinderPath(
				VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
				VideoGalleryModelImpl.FINDER_CACHE_ENABLED,
				VideoGalleryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByPublicationDateAndStatus",
				new String[] {
					Date.class.getName(), Integer.class.getName(),
					Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithPaginationCountByPublicationDateAndStatus =
			new FinderPath(
				VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
				VideoGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"countByPublicationDateAndStatus",
				new String[] {Date.class.getName(), Integer.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(VideoGalleryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("video_VideoToVideoGallery");
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	@BeanReference(type = VideoPersistence.class)
	protected VideoPersistence videoPersistence;

	protected TableMapper<VideoGallery, eu.strasbourg.service.video.model.Video>
		videoGalleryToVideoTableMapper;

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_VIDEOGALLERY =
		"SELECT videoGallery FROM VideoGallery videoGallery";

	private static final String _SQL_SELECT_VIDEOGALLERY_WHERE_PKS_IN =
		"SELECT videoGallery FROM VideoGallery videoGallery WHERE galleryId IN (";

	private static final String _SQL_SELECT_VIDEOGALLERY_WHERE =
		"SELECT videoGallery FROM VideoGallery videoGallery WHERE ";

	private static final String _SQL_COUNT_VIDEOGALLERY =
		"SELECT COUNT(videoGallery) FROM VideoGallery videoGallery";

	private static final String _SQL_COUNT_VIDEOGALLERY_WHERE =
		"SELECT COUNT(videoGallery) FROM VideoGallery videoGallery WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "videoGallery.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No VideoGallery exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No VideoGallery exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		VideoGalleryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}