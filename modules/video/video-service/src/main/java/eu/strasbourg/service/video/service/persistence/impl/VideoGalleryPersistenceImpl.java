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

import eu.strasbourg.service.video.exception.NoSuchVideoGalleryException;
import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.model.impl.VideoGalleryImpl;
import eu.strasbourg.service.video.model.impl.VideoGalleryModelImpl;
import eu.strasbourg.service.video.service.persistence.VideoGalleryPersistence;
import eu.strasbourg.service.video.service.persistence.VideoPersistence;

import java.io.Serializable;

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
 * @see VideoGalleryPersistence
 * @see eu.strasbourg.service.video.service.persistence.VideoGalleryUtil
 * @generated
 */
@ProviderType
public class VideoGalleryPersistenceImpl extends BasePersistenceImpl<VideoGallery>
	implements VideoGalleryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VideoGalleryUtil} to access the video gallery persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VideoGalleryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			VideoGalleryModelImpl.UUID_COLUMN_BITMASK |
			VideoGalleryModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByUuid(String uuid, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the video galleries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByUuid(String uuid, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator,
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

		List<VideoGallery> list = null;

		if (retrieveFromCache) {
			list = (List<VideoGallery>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (VideoGallery videoGallery : list) {
					if (!Objects.equals(uuid, videoGallery.getUuid())) {
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

			query.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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
				query.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
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
					list = (List<VideoGallery>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VideoGallery>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first video gallery in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching video gallery
	 * @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery findByUuid_First(String uuid,
		OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {
		VideoGallery videoGallery = fetchByUuid_First(uuid, orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVideoGalleryException(msg.toString());
	}

	/**
	 * Returns the first video gallery in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByUuid_First(String uuid,
		OrderByComparator<VideoGallery> orderByComparator) {
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
	public VideoGallery findByUuid_Last(String uuid,
		OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {
		VideoGallery videoGallery = fetchByUuid_Last(uuid, orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVideoGalleryException(msg.toString());
	}

	/**
	 * Returns the last video gallery in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByUuid_Last(String uuid,
		OrderByComparator<VideoGallery> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<VideoGallery> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

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
	public VideoGallery[] findByUuid_PrevAndNext(long galleryId, String uuid,
		OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {
		VideoGallery videoGallery = findByPrimaryKey(galleryId);

		Session session = null;

		try {
			session = openSession();

			VideoGallery[] array = new VideoGalleryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, videoGallery, uuid,
					orderByComparator, true);

			array[1] = videoGallery;

			array[2] = getByUuid_PrevAndNext(session, videoGallery, uuid,
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

	protected VideoGallery getByUuid_PrevAndNext(Session session,
		VideoGallery videoGallery, String uuid,
		OrderByComparator<VideoGallery> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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
			query.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(videoGallery);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<VideoGallery> list = q.list();

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
		for (VideoGallery videoGallery : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VIDEOGALLERY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "videoGallery.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "videoGallery.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(videoGallery.uuid IS NULL OR videoGallery.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			VideoGalleryModelImpl.UUID_COLUMN_BITMASK |
			VideoGalleryModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the video gallery where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVideoGalleryException} if it could not be found.
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

			throw new NoSuchVideoGalleryException(msg.toString());
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
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof VideoGallery) {
			VideoGallery videoGallery = (VideoGallery)result;

			if (!Objects.equals(uuid, videoGallery.getUuid()) ||
					(groupId != videoGallery.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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

				List<VideoGallery> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					VideoGallery videoGallery = list.get(0);

					result = videoGallery;

					cacheResult(videoGallery);

					if ((videoGallery.getUuid() == null) ||
							!videoGallery.getUuid().equals(uuid) ||
							(videoGallery.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, videoGallery);
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VIDEOGALLERY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "videoGallery.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "videoGallery.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(videoGallery.uuid IS NULL OR videoGallery.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "videoGallery.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			VideoGalleryModelImpl.UUID_COLUMN_BITMASK |
			VideoGalleryModelImpl.COMPANYID_COLUMN_BITMASK |
			VideoGalleryModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the video galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching video galleries
	 */
	@Override
	public List<VideoGallery> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the video galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @return the range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the video galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<VideoGallery> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<VideoGallery> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the video galleries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<VideoGallery> orderByComparator,
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

		List<VideoGallery> list = null;

		if (retrieveFromCache) {
			list = (List<VideoGallery>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (VideoGallery videoGallery : list) {
					if (!Objects.equals(uuid, videoGallery.getUuid()) ||
							(companyId != videoGallery.getCompanyId())) {
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

			query.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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
				query.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
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
					list = (List<VideoGallery>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VideoGallery>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first video gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching video gallery
	 * @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {
		VideoGallery videoGallery = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVideoGalleryException(msg.toString());
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
	public VideoGallery fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<VideoGallery> orderByComparator) {
		List<VideoGallery> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

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
	public VideoGallery findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {
		VideoGallery videoGallery = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVideoGalleryException(msg.toString());
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
	public VideoGallery fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<VideoGallery> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<VideoGallery> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

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
	public VideoGallery[] findByUuid_C_PrevAndNext(long galleryId, String uuid,
		long companyId, OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {
		VideoGallery videoGallery = findByPrimaryKey(galleryId);

		Session session = null;

		try {
			session = openSession();

			VideoGallery[] array = new VideoGalleryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, videoGallery, uuid,
					companyId, orderByComparator, true);

			array[1] = videoGallery;

			array[2] = getByUuid_C_PrevAndNext(session, videoGallery, uuid,
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

	protected VideoGallery getByUuid_C_PrevAndNext(Session session,
		VideoGallery videoGallery, String uuid, long companyId,
		OrderByComparator<VideoGallery> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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
			query.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(videoGallery);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<VideoGallery> list = q.list();

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
		for (VideoGallery videoGallery : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VIDEOGALLERY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "videoGallery.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "videoGallery.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(videoGallery.uuid IS NULL OR videoGallery.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "videoGallery.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			VideoGalleryModelImpl.GROUPID_COLUMN_BITMASK |
			VideoGalleryModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the video galleries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching video galleries
	 */
	@Override
	public List<VideoGallery> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the video galleries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByGroupId(long groupId, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the video galleries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByGroupId(long groupId, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator,
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

		List<VideoGallery> list = null;

		if (retrieveFromCache) {
			list = (List<VideoGallery>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (VideoGallery videoGallery : list) {
					if ((groupId != videoGallery.getGroupId())) {
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

			query.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<VideoGallery>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VideoGallery>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first video gallery in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching video gallery
	 * @throws NoSuchVideoGalleryException if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery findByGroupId_First(long groupId,
		OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {
		VideoGallery videoGallery = fetchByGroupId_First(groupId,
				orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVideoGalleryException(msg.toString());
	}

	/**
	 * Returns the first video gallery in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByGroupId_First(long groupId,
		OrderByComparator<VideoGallery> orderByComparator) {
		List<VideoGallery> list = findByGroupId(groupId, 0, 1, orderByComparator);

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
	public VideoGallery findByGroupId_Last(long groupId,
		OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {
		VideoGallery videoGallery = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVideoGalleryException(msg.toString());
	}

	/**
	 * Returns the last video gallery in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching video gallery, or <code>null</code> if a matching video gallery could not be found
	 */
	@Override
	public VideoGallery fetchByGroupId_Last(long groupId,
		OrderByComparator<VideoGallery> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<VideoGallery> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

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
	public VideoGallery[] findByGroupId_PrevAndNext(long galleryId,
		long groupId, OrderByComparator<VideoGallery> orderByComparator)
		throws NoSuchVideoGalleryException {
		VideoGallery videoGallery = findByPrimaryKey(galleryId);

		Session session = null;

		try {
			session = openSession();

			VideoGallery[] array = new VideoGalleryImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, videoGallery, groupId,
					orderByComparator, true);

			array[1] = videoGallery;

			array[2] = getByGroupId_PrevAndNext(session, videoGallery, groupId,
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

	protected VideoGallery getByGroupId_PrevAndNext(Session session,
		VideoGallery videoGallery, long groupId,
		OrderByComparator<VideoGallery> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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
			query.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(videoGallery);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<VideoGallery> list = q.list();

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
		for (VideoGallery videoGallery : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VIDEOGALLERY_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "videoGallery.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLICATIONDATEANDSTATUS =
		new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, VideoGalleryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPublicationDateAndStatus",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_PUBLICATIONDATEANDSTATUS =
		new FinderPath(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByPublicationDateAndStatus",
			new String[] { Date.class.getName(), Integer.class.getName() });

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
		return findByPublicationDateAndStatus(publicationDate, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the video galleries where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return findByPublicationDateAndStatus(publicationDate, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the video galleries where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return findByPublicationDateAndStatus(publicationDate, status, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the video galleries where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching video galleries
	 */
	@Override
	public List<VideoGallery> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		OrderByComparator<VideoGallery> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLICATIONDATEANDSTATUS;
		finderArgs = new Object[] {
				publicationDate, status,
				
				start, end, orderByComparator
			};

		List<VideoGallery> list = null;

		if (retrieveFromCache) {
			list = (List<VideoGallery>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (VideoGallery videoGallery : list) {
					if ((publicationDate.getTime() <= videoGallery.getPublicationDate()
																	  .getTime()) ||
							(status != videoGallery.getStatus())) {
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

			query.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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
				query.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
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
					list = (List<VideoGallery>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VideoGallery>)QueryUtil.list(q, getDialect(),
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
		VideoGallery videoGallery = fetchByPublicationDateAndStatus_First(publicationDate,
				status, orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publicationDate=");
		msg.append(publicationDate);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVideoGalleryException(msg.toString());
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
		List<VideoGallery> list = findByPublicationDateAndStatus(publicationDate,
				status, 0, 1, orderByComparator);

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
		VideoGallery videoGallery = fetchByPublicationDateAndStatus_Last(publicationDate,
				status, orderByComparator);

		if (videoGallery != null) {
			return videoGallery;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publicationDate=");
		msg.append(publicationDate);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVideoGalleryException(msg.toString());
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

		List<VideoGallery> list = findByPublicationDateAndStatus(publicationDate,
				status, count - 1, count, orderByComparator);

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

			array[0] = getByPublicationDateAndStatus_PrevAndNext(session,
					videoGallery, publicationDate, status, orderByComparator,
					true);

			array[1] = videoGallery;

			array[2] = getByPublicationDateAndStatus_PrevAndNext(session,
					videoGallery, publicationDate, status, orderByComparator,
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

	protected VideoGallery getByPublicationDateAndStatus_PrevAndNext(
		Session session, VideoGallery videoGallery, Date publicationDate,
		int status, OrderByComparator<VideoGallery> orderByComparator,
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

		query.append(_SQL_SELECT_VIDEOGALLERY_WHERE);

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
			query.append(VideoGalleryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(videoGallery);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<VideoGallery> list = q.list();

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
	public void removeByPublicationDateAndStatus(Date publicationDate,
		int status) {
		for (VideoGallery videoGallery : findByPublicationDateAndStatus(
				publicationDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
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
	public int countByPublicationDateAndStatus(Date publicationDate, int status) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_PUBLICATIONDATEANDSTATUS;

		Object[] finderArgs = new Object[] { publicationDate, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VIDEOGALLERY_WHERE);

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
		"videoGallery.publicationDate IS NULL AND ";
	private static final String _FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_PUBLICATIONDATE_2 =
		"videoGallery.publicationDate < ? AND ";
	private static final String _FINDER_COLUMN_PUBLICATIONDATEANDSTATUS_STATUS_2 =
		"videoGallery.status = ?";

	public VideoGalleryPersistenceImpl() {
		setModelClass(VideoGallery.class);
	}

	/**
	 * Caches the video gallery in the entity cache if it is enabled.
	 *
	 * @param videoGallery the video gallery
	 */
	@Override
	public void cacheResult(VideoGallery videoGallery) {
		entityCache.putResult(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryImpl.class, videoGallery.getPrimaryKey(), videoGallery);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { videoGallery.getUuid(), videoGallery.getGroupId() },
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
						VideoGalleryImpl.class, videoGallery.getPrimaryKey()) == null) {
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VideoGallery videoGallery) {
		entityCache.removeResult(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryImpl.class, videoGallery.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((VideoGalleryModelImpl)videoGallery);
	}

	@Override
	public void clearCache(List<VideoGallery> videoGalleries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VideoGallery videoGallery : videoGalleries) {
			entityCache.removeResult(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
				VideoGalleryImpl.class, videoGallery.getPrimaryKey());

			clearUniqueFindersCache((VideoGalleryModelImpl)videoGallery);
		}
	}

	protected void cacheUniqueFindersCache(
		VideoGalleryModelImpl videoGalleryModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					videoGalleryModelImpl.getUuid(),
					videoGalleryModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				videoGalleryModelImpl);
		}
		else {
			if ((videoGalleryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						videoGalleryModelImpl.getUuid(),
						videoGalleryModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					videoGalleryModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		VideoGalleryModelImpl videoGalleryModelImpl) {
		Object[] args = new Object[] {
				videoGalleryModelImpl.getUuid(),
				videoGalleryModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((videoGalleryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					videoGalleryModelImpl.getOriginalUuid(),
					videoGalleryModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
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

		videoGallery.setCompanyId(companyProvider.getCompanyId());

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

			VideoGallery videoGallery = (VideoGallery)session.get(VideoGalleryImpl.class,
					primaryKey);

			if (videoGallery == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVideoGalleryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(videoGallery);
		}
		catch (NoSuchVideoGalleryException nsee) {
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
	protected VideoGallery removeImpl(VideoGallery videoGallery) {
		videoGallery = toUnwrappedModel(videoGallery);

		videoGalleryToVideoTableMapper.deleteLeftPrimaryKeyTableMappings(videoGallery.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(videoGallery)) {
				videoGallery = (VideoGallery)session.get(VideoGalleryImpl.class,
						videoGallery.getPrimaryKeyObj());
			}

			if (videoGallery != null) {
				session.delete(videoGallery);
			}
		}
		catch (Exception e) {
			throw processException(e);
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
		videoGallery = toUnwrappedModel(videoGallery);

		boolean isNew = videoGallery.isNew();

		VideoGalleryModelImpl videoGalleryModelImpl = (VideoGalleryModelImpl)videoGallery;

		if (Validator.isNull(videoGallery.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			videoGallery.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

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
				videoGallery.setModifiedDate(serviceContext.getModifiedDate(now));
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
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !VideoGalleryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((videoGalleryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						videoGalleryModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { videoGalleryModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((videoGalleryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						videoGalleryModelImpl.getOriginalUuid(),
						videoGalleryModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						videoGalleryModelImpl.getUuid(),
						videoGalleryModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((videoGalleryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						videoGalleryModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { videoGalleryModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
			VideoGalleryImpl.class, videoGallery.getPrimaryKey(), videoGallery,
			false);

		clearUniqueFindersCache(videoGalleryModelImpl);
		cacheUniqueFindersCache(videoGalleryModelImpl, isNew);

		videoGallery.resetOriginalValues();

		return videoGallery;
	}

	protected VideoGallery toUnwrappedModel(VideoGallery videoGallery) {
		if (videoGallery instanceof VideoGalleryImpl) {
			return videoGallery;
		}

		VideoGalleryImpl videoGalleryImpl = new VideoGalleryImpl();

		videoGalleryImpl.setNew(videoGallery.isNew());
		videoGalleryImpl.setPrimaryKey(videoGallery.getPrimaryKey());

		videoGalleryImpl.setUuid(videoGallery.getUuid());
		videoGalleryImpl.setGalleryId(videoGallery.getGalleryId());
		videoGalleryImpl.setGroupId(videoGallery.getGroupId());
		videoGalleryImpl.setCompanyId(videoGallery.getCompanyId());
		videoGalleryImpl.setUserId(videoGallery.getUserId());
		videoGalleryImpl.setUserName(videoGallery.getUserName());
		videoGalleryImpl.setCreateDate(videoGallery.getCreateDate());
		videoGalleryImpl.setModifiedDate(videoGallery.getModifiedDate());
		videoGalleryImpl.setLastPublishDate(videoGallery.getLastPublishDate());
		videoGalleryImpl.setStatus(videoGallery.getStatus());
		videoGalleryImpl.setStatusByUserId(videoGallery.getStatusByUserId());
		videoGalleryImpl.setStatusByUserName(videoGallery.getStatusByUserName());
		videoGalleryImpl.setStatusDate(videoGallery.getStatusDate());
		videoGalleryImpl.setTitle(videoGallery.getTitle());
		videoGalleryImpl.setDescription(videoGallery.getDescription());
		videoGalleryImpl.setPublicationDate(videoGallery.getPublicationDate());
		videoGalleryImpl.setImageId(videoGallery.getImageId());

		return videoGalleryImpl;
	}

	/**
	 * Returns the video gallery with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
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

			throw new NoSuchVideoGalleryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return videoGallery;
	}

	/**
	 * Returns the video gallery with the primary key or throws a {@link NoSuchVideoGalleryException} if it could not be found.
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
		Serializable serializable = entityCache.getResult(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
				VideoGalleryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VideoGallery videoGallery = (VideoGallery)serializable;

		if (videoGallery == null) {
			Session session = null;

			try {
				session = openSession();

				videoGallery = (VideoGallery)session.get(VideoGalleryImpl.class,
						primaryKey);

				if (videoGallery != null) {
					cacheResult(videoGallery);
				}
				else {
					entityCache.putResult(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
						VideoGalleryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
					VideoGalleryImpl.class, primaryKey);

				throw processException(e);
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

		Map<Serializable, VideoGallery> map = new HashMap<Serializable, VideoGallery>();

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
			Serializable serializable = entityCache.getResult(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
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

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VIDEOGALLERY_WHERE_PKS_IN);

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

			for (VideoGallery videoGallery : (List<VideoGallery>)q.list()) {
				map.put(videoGallery.getPrimaryKeyObj(), videoGallery);

				cacheResult(videoGallery);

				uncachedPrimaryKeys.remove(videoGallery.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VideoGalleryModelImpl.ENTITY_CACHE_ENABLED,
					VideoGalleryImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of video galleries
	 */
	@Override
	public List<VideoGallery> findAll(int start, int end,
		OrderByComparator<VideoGallery> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the video galleries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of video galleries
	 */
	@Override
	public List<VideoGallery> findAll(int start, int end,
		OrderByComparator<VideoGallery> orderByComparator,
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

		List<VideoGallery> list = null;

		if (retrieveFromCache) {
			list = (List<VideoGallery>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VIDEOGALLERY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VIDEOGALLERY;

				if (pagination) {
					sql = sql.concat(VideoGalleryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VideoGallery>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VideoGallery>)QueryUtil.list(q, getDialect(),
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
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VIDEOGALLERY);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the video gallery
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @return the range of videos associated with the video gallery
	 */
	@Override
	public List<eu.strasbourg.service.video.model.Video> getVideos(long pk,
		int start, int end) {
		return getVideos(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the videos associated with the video gallery.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VideoGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the video gallery
	 * @param start the lower bound of the range of video galleries
	 * @param end the upper bound of the range of video galleries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of videos associated with the video gallery
	 */
	@Override
	public List<eu.strasbourg.service.video.model.Video> getVideos(long pk,
		int start, int end,
		OrderByComparator<eu.strasbourg.service.video.model.Video> orderByComparator) {
		return videoGalleryToVideoTableMapper.getRightBaseModels(pk, start,
			end, orderByComparator);
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
			videoGalleryToVideoTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, videoPK);
		}
		else {
			videoGalleryToVideoTableMapper.addTableMapping(videoGallery.getCompanyId(),
				pk, videoPK);
		}
	}

	/**
	 * Adds an association between the video gallery and the video. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery
	 * @param video the video
	 */
	@Override
	public void addVideo(long pk, eu.strasbourg.service.video.model.Video video) {
		VideoGallery videoGallery = fetchByPrimaryKey(pk);

		if (videoGallery == null) {
			videoGalleryToVideoTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, video.getPrimaryKey());
		}
		else {
			videoGalleryToVideoTableMapper.addTableMapping(videoGallery.getCompanyId(),
				pk, video.getPrimaryKey());
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
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = videoGallery.getCompanyId();
		}

		videoGalleryToVideoTableMapper.addTableMappings(companyId, pk, videoPKs);
	}

	/**
	 * Adds an association between the video gallery and the videos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery
	 * @param videos the videos
	 */
	@Override
	public void addVideos(long pk,
		List<eu.strasbourg.service.video.model.Video> videos) {
		addVideos(pk,
			ListUtil.toLongArray(videos,
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
	public void removeVideo(long pk,
		eu.strasbourg.service.video.model.Video video) {
		videoGalleryToVideoTableMapper.deleteTableMapping(pk,
			video.getPrimaryKey());
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
	public void removeVideos(long pk,
		List<eu.strasbourg.service.video.model.Video> videos) {
		removeVideos(pk,
			ListUtil.toLongArray(videos,
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
		Set<Long> oldVideoPKsSet = SetUtil.fromArray(videoGalleryToVideoTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeVideoPKsSet = new HashSet<Long>(oldVideoPKsSet);

		removeVideoPKsSet.removeAll(newVideoPKsSet);

		videoGalleryToVideoTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeVideoPKsSet));

		newVideoPKsSet.removeAll(oldVideoPKsSet);

		long companyId = 0;

		VideoGallery videoGallery = fetchByPrimaryKey(pk);

		if (videoGallery == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = videoGallery.getCompanyId();
		}

		videoGalleryToVideoTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newVideoPKsSet));
	}

	/**
	 * Sets the videos associated with the video gallery, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the video gallery
	 * @param videos the videos to be associated with the video gallery
	 */
	@Override
	public void setVideos(long pk,
		List<eu.strasbourg.service.video.model.Video> videos) {
		try {
			long[] videoPKs = new long[videos.size()];

			for (int i = 0; i < videos.size(); i++) {
				eu.strasbourg.service.video.model.Video video = videos.get(i);

				videoPKs[i] = video.getPrimaryKey();
			}

			setVideos(pk, videoPKs);
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
		return VideoGalleryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the video gallery persistence.
	 */
	public void afterPropertiesSet() {
		videoGalleryToVideoTableMapper = TableMapperFactory.getTableMapper("video_VideoToVideoGallery",
				"companyId", "galleryId", "videoId", this, videoPersistence);
	}

	public void destroy() {
		entityCache.removeCache(VideoGalleryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("video_VideoToVideoGallery");
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	@BeanReference(type = VideoPersistence.class)
	protected VideoPersistence videoPersistence;
	protected TableMapper<VideoGallery, eu.strasbourg.service.video.model.Video> videoGalleryToVideoTableMapper;
	private static final String _SQL_SELECT_VIDEOGALLERY = "SELECT videoGallery FROM VideoGallery videoGallery";
	private static final String _SQL_SELECT_VIDEOGALLERY_WHERE_PKS_IN = "SELECT videoGallery FROM VideoGallery videoGallery WHERE galleryId IN (";
	private static final String _SQL_SELECT_VIDEOGALLERY_WHERE = "SELECT videoGallery FROM VideoGallery videoGallery WHERE ";
	private static final String _SQL_COUNT_VIDEOGALLERY = "SELECT COUNT(videoGallery) FROM VideoGallery videoGallery";
	private static final String _SQL_COUNT_VIDEOGALLERY_WHERE = "SELECT COUNT(videoGallery) FROM VideoGallery videoGallery WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "videoGallery.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VideoGallery exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VideoGallery exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(VideoGalleryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}