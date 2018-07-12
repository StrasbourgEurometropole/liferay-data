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

package eu.strasbourg.service.like.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.like.exception.NoSuchLikeException;
import eu.strasbourg.service.like.model.Like;
import eu.strasbourg.service.like.model.impl.LikeImpl;
import eu.strasbourg.service.like.model.impl.LikeModelImpl;
import eu.strasbourg.service.like.service.persistence.LikePersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the like service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see LikePersistence
 * @see eu.strasbourg.service.like.service.persistence.LikeUtil
 * @generated
 */
@ProviderType
public class LikePersistenceImpl extends BasePersistenceImpl<Like>
	implements LikePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LikeUtil} to access the like persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LikeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, LikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, LikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, LikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPublikUserId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, LikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikUserId",
			new String[] { String.class.getName() },
			LikeModelImpl.PUBLIKUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKUSERID = new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikUserId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the likes where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching likes
	 */
	@Override
	public List<Like> findByPublikUserId(String publikUserId) {
		return findByPublikUserId(publikUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the likes where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @return the range of matching likes
	 */
	@Override
	public List<Like> findByPublikUserId(String publikUserId, int start, int end) {
		return findByPublikUserId(publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the likes where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching likes
	 */
	@Override
	public List<Like> findByPublikUserId(String publikUserId, int start,
		int end, OrderByComparator<Like> orderByComparator) {
		return findByPublikUserId(publikUserId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the likes where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching likes
	 */
	@Override
	public List<Like> findByPublikUserId(String publikUserId, int start,
		int end, OrderByComparator<Like> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID;
			finderArgs = new Object[] { publikUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKUSERID;
			finderArgs = new Object[] {
					publikUserId,
					
					start, end, orderByComparator
				};
		}

		List<Like> list = null;

		if (retrieveFromCache) {
			list = (List<Like>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Like like : list) {
					if (!Objects.equals(publikUserId, like.getPublikUserId())) {
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

			query.append(_SQL_SELECT_LIKE__WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LikeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
				}

				if (!pagination) {
					list = (List<Like>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Like>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first like in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching like
	 * @throws NoSuchLikeException if a matching like could not be found
	 */
	@Override
	public Like findByPublikUserId_First(String publikUserId,
		OrderByComparator<Like> orderByComparator) throws NoSuchLikeException {
		Like like = fetchByPublikUserId_First(publikUserId, orderByComparator);

		if (like != null) {
			return like;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLikeException(msg.toString());
	}

	/**
	 * Returns the first like in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching like, or <code>null</code> if a matching like could not be found
	 */
	@Override
	public Like fetchByPublikUserId_First(String publikUserId,
		OrderByComparator<Like> orderByComparator) {
		List<Like> list = findByPublikUserId(publikUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last like in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching like
	 * @throws NoSuchLikeException if a matching like could not be found
	 */
	@Override
	public Like findByPublikUserId_Last(String publikUserId,
		OrderByComparator<Like> orderByComparator) throws NoSuchLikeException {
		Like like = fetchByPublikUserId_Last(publikUserId, orderByComparator);

		if (like != null) {
			return like;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLikeException(msg.toString());
	}

	/**
	 * Returns the last like in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching like, or <code>null</code> if a matching like could not be found
	 */
	@Override
	public Like fetchByPublikUserId_Last(String publikUserId,
		OrderByComparator<Like> orderByComparator) {
		int count = countByPublikUserId(publikUserId);

		if (count == 0) {
			return null;
		}

		List<Like> list = findByPublikUserId(publikUserId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the likes before and after the current like in the ordered set where publikUserId = &#63;.
	 *
	 * @param likeId the primary key of the current like
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next like
	 * @throws NoSuchLikeException if a like with the primary key could not be found
	 */
	@Override
	public Like[] findByPublikUserId_PrevAndNext(long likeId,
		String publikUserId, OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException {
		Like like = findByPrimaryKey(likeId);

		Session session = null;

		try {
			session = openSession();

			Like[] array = new LikeImpl[3];

			array[0] = getByPublikUserId_PrevAndNext(session, like,
					publikUserId, orderByComparator, true);

			array[1] = like;

			array[2] = getByPublikUserId_PrevAndNext(session, like,
					publikUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Like getByPublikUserId_PrevAndNext(Session session, Like like,
		String publikUserId, OrderByComparator<Like> orderByComparator,
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

		query.append(_SQL_SELECT_LIKE__WHERE);

		boolean bindPublikUserId = false;

		if (publikUserId == null) {
			query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1);
		}
		else if (publikUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
		}
		else {
			bindPublikUserId = true;

			query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
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
			query.append(LikeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPublikUserId) {
			qPos.add(publikUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(like);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Like> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the likes where publikUserId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 */
	@Override
	public void removeByPublikUserId(String publikUserId) {
		for (Like like : findByPublikUserId(publikUserId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(like);
		}
	}

	/**
	 * Returns the number of likes where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the number of matching likes
	 */
	@Override
	public int countByPublikUserId(String publikUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKUSERID;

		Object[] finderArgs = new Object[] { publikUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LIKE__WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
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

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1 = "like_.publikUserId IS NULL";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2 = "like_.publikUserId = ?";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3 = "(like_.publikUserId IS NULL OR like_.publikUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKUSERIDANDISDISLIKE =
		new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, LikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPublikUserIdAndIsDislike",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERIDANDISDISLIKE =
		new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, LikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPublikUserIdAndIsDislike",
			new String[] { String.class.getName(), Boolean.class.getName() },
			LikeModelImpl.PUBLIKUSERID_COLUMN_BITMASK |
			LikeModelImpl.ISDISLIKE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDISDISLIKE =
		new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPublikUserIdAndIsDislike",
			new String[] { String.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the likes where publikUserId = &#63; and isDislike = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param isDislike the is dislike
	 * @return the matching likes
	 */
	@Override
	public List<Like> findByPublikUserIdAndIsDislike(String publikUserId,
		boolean isDislike) {
		return findByPublikUserIdAndIsDislike(publikUserId, isDislike,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the likes where publikUserId = &#63; and isDislike = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param isDislike the is dislike
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @return the range of matching likes
	 */
	@Override
	public List<Like> findByPublikUserIdAndIsDislike(String publikUserId,
		boolean isDislike, int start, int end) {
		return findByPublikUserIdAndIsDislike(publikUserId, isDislike, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the likes where publikUserId = &#63; and isDislike = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param isDislike the is dislike
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching likes
	 */
	@Override
	public List<Like> findByPublikUserIdAndIsDislike(String publikUserId,
		boolean isDislike, int start, int end,
		OrderByComparator<Like> orderByComparator) {
		return findByPublikUserIdAndIsDislike(publikUserId, isDislike, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the likes where publikUserId = &#63; and isDislike = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param isDislike the is dislike
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching likes
	 */
	@Override
	public List<Like> findByPublikUserIdAndIsDislike(String publikUserId,
		boolean isDislike, int start, int end,
		OrderByComparator<Like> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERIDANDISDISLIKE;
			finderArgs = new Object[] { publikUserId, isDislike };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKUSERIDANDISDISLIKE;
			finderArgs = new Object[] {
					publikUserId, isDislike,
					
					start, end, orderByComparator
				};
		}

		List<Like> list = null;

		if (retrieveFromCache) {
			list = (List<Like>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Like like : list) {
					if (!Objects.equals(publikUserId, like.getPublikUserId()) ||
							(isDislike != like.getIsDislike())) {
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

			query.append(_SQL_SELECT_LIKE__WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_PUBLIKUSERID_2);
			}

			query.append(_FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_ISDISLIKE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LikeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
				}

				qPos.add(isDislike);

				if (!pagination) {
					list = (List<Like>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Like>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param isDislike the is dislike
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching like
	 * @throws NoSuchLikeException if a matching like could not be found
	 */
	@Override
	public Like findByPublikUserIdAndIsDislike_First(String publikUserId,
		boolean isDislike, OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException {
		Like like = fetchByPublikUserIdAndIsDislike_First(publikUserId,
				isDislike, orderByComparator);

		if (like != null) {
			return like;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(", isDislike=");
		msg.append(isDislike);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLikeException(msg.toString());
	}

	/**
	 * Returns the first like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param isDislike the is dislike
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching like, or <code>null</code> if a matching like could not be found
	 */
	@Override
	public Like fetchByPublikUserIdAndIsDislike_First(String publikUserId,
		boolean isDislike, OrderByComparator<Like> orderByComparator) {
		List<Like> list = findByPublikUserIdAndIsDislike(publikUserId,
				isDislike, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param isDislike the is dislike
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching like
	 * @throws NoSuchLikeException if a matching like could not be found
	 */
	@Override
	public Like findByPublikUserIdAndIsDislike_Last(String publikUserId,
		boolean isDislike, OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException {
		Like like = fetchByPublikUserIdAndIsDislike_Last(publikUserId,
				isDislike, orderByComparator);

		if (like != null) {
			return like;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(", isDislike=");
		msg.append(isDislike);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLikeException(msg.toString());
	}

	/**
	 * Returns the last like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param isDislike the is dislike
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching like, or <code>null</code> if a matching like could not be found
	 */
	@Override
	public Like fetchByPublikUserIdAndIsDislike_Last(String publikUserId,
		boolean isDislike, OrderByComparator<Like> orderByComparator) {
		int count = countByPublikUserIdAndIsDislike(publikUserId, isDislike);

		if (count == 0) {
			return null;
		}

		List<Like> list = findByPublikUserIdAndIsDislike(publikUserId,
				isDislike, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the likes before and after the current like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	 *
	 * @param likeId the primary key of the current like
	 * @param publikUserId the publik user ID
	 * @param isDislike the is dislike
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next like
	 * @throws NoSuchLikeException if a like with the primary key could not be found
	 */
	@Override
	public Like[] findByPublikUserIdAndIsDislike_PrevAndNext(long likeId,
		String publikUserId, boolean isDislike,
		OrderByComparator<Like> orderByComparator) throws NoSuchLikeException {
		Like like = findByPrimaryKey(likeId);

		Session session = null;

		try {
			session = openSession();

			Like[] array = new LikeImpl[3];

			array[0] = getByPublikUserIdAndIsDislike_PrevAndNext(session, like,
					publikUserId, isDislike, orderByComparator, true);

			array[1] = like;

			array[2] = getByPublikUserIdAndIsDislike_PrevAndNext(session, like,
					publikUserId, isDislike, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Like getByPublikUserIdAndIsDislike_PrevAndNext(Session session,
		Like like, String publikUserId, boolean isDislike,
		OrderByComparator<Like> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LIKE__WHERE);

		boolean bindPublikUserId = false;

		if (publikUserId == null) {
			query.append(_FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_PUBLIKUSERID_1);
		}
		else if (publikUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_PUBLIKUSERID_3);
		}
		else {
			bindPublikUserId = true;

			query.append(_FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_PUBLIKUSERID_2);
		}

		query.append(_FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_ISDISLIKE_2);

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
			query.append(LikeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPublikUserId) {
			qPos.add(publikUserId);
		}

		qPos.add(isDislike);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(like);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Like> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the likes where publikUserId = &#63; and isDislike = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 * @param isDislike the is dislike
	 */
	@Override
	public void removeByPublikUserIdAndIsDislike(String publikUserId,
		boolean isDislike) {
		for (Like like : findByPublikUserIdAndIsDislike(publikUserId,
				isDislike, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(like);
		}
	}

	/**
	 * Returns the number of likes where publikUserId = &#63; and isDislike = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param isDislike the is dislike
	 * @return the number of matching likes
	 */
	@Override
	public int countByPublikUserIdAndIsDislike(String publikUserId,
		boolean isDislike) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDISDISLIKE;

		Object[] finderArgs = new Object[] { publikUserId, isDislike };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LIKE__WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_PUBLIKUSERID_2);
			}

			query.append(_FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_ISDISLIKE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
				}

				qPos.add(isDislike);

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

	private static final String _FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_PUBLIKUSERID_1 =
		"like_.publikUserId IS NULL AND ";
	private static final String _FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_PUBLIKUSERID_2 =
		"like_.publikUserId = ? AND ";
	private static final String _FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_PUBLIKUSERID_3 =
		"(like_.publikUserId IS NULL OR like_.publikUserId = '') AND ";
	private static final String _FINDER_COLUMN_PUBLIKUSERIDANDISDISLIKE_ISDISLIKE_2 =
		"like_.isDislike = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_ALLATTRIBUTES = new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, LikeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByAllAttributes",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			LikeModelImpl.PUBLIKUSERID_COLUMN_BITMASK |
			LikeModelImpl.TITLE_COLUMN_BITMASK |
			LikeModelImpl.ISDISLIKE_COLUMN_BITMASK |
			LikeModelImpl.TYPEID_COLUMN_BITMASK |
			LikeModelImpl.ENTITYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ALLATTRIBUTES = new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAllAttributes",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns the like where publikUserId = &#63; and title = &#63; and isDislike = &#63; and typeId = &#63; and entityId = &#63; or throws a {@link NoSuchLikeException} if it could not be found.
	 *
	 * @param publikUserId the publik user ID
	 * @param title the title
	 * @param isDislike the is dislike
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @return the matching like
	 * @throws NoSuchLikeException if a matching like could not be found
	 */
	@Override
	public Like findByAllAttributes(String publikUserId, String title,
		boolean isDislike, long typeId, long entityId)
		throws NoSuchLikeException {
		Like like = fetchByAllAttributes(publikUserId, title, isDislike,
				typeId, entityId);

		if (like == null) {
			StringBundler msg = new StringBundler(12);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("publikUserId=");
			msg.append(publikUserId);

			msg.append(", title=");
			msg.append(title);

			msg.append(", isDislike=");
			msg.append(isDislike);

			msg.append(", typeId=");
			msg.append(typeId);

			msg.append(", entityId=");
			msg.append(entityId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLikeException(msg.toString());
		}

		return like;
	}

	/**
	 * Returns the like where publikUserId = &#63; and title = &#63; and isDislike = &#63; and typeId = &#63; and entityId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param publikUserId the publik user ID
	 * @param title the title
	 * @param isDislike the is dislike
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @return the matching like, or <code>null</code> if a matching like could not be found
	 */
	@Override
	public Like fetchByAllAttributes(String publikUserId, String title,
		boolean isDislike, long typeId, long entityId) {
		return fetchByAllAttributes(publikUserId, title, isDislike, typeId,
			entityId, true);
	}

	/**
	 * Returns the like where publikUserId = &#63; and title = &#63; and isDislike = &#63; and typeId = &#63; and entityId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param publikUserId the publik user ID
	 * @param title the title
	 * @param isDislike the is dislike
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching like, or <code>null</code> if a matching like could not be found
	 */
	@Override
	public Like fetchByAllAttributes(String publikUserId, String title,
		boolean isDislike, long typeId, long entityId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				publikUserId, title, isDislike, typeId, entityId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_ALLATTRIBUTES,
					finderArgs, this);
		}

		if (result instanceof Like) {
			Like like = (Like)result;

			if (!Objects.equals(publikUserId, like.getPublikUserId()) ||
					!Objects.equals(title, like.getTitle()) ||
					(isDislike != like.getIsDislike()) ||
					(typeId != like.getTypeId()) ||
					(entityId != like.getEntityId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_LIKE__WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_ALLATTRIBUTES_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ALLATTRIBUTES_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_ALLATTRIBUTES_PUBLIKUSERID_2);
			}

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_ALLATTRIBUTES_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ALLATTRIBUTES_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_ALLATTRIBUTES_TITLE_2);
			}

			query.append(_FINDER_COLUMN_ALLATTRIBUTES_ISDISLIKE_2);

			query.append(_FINDER_COLUMN_ALLATTRIBUTES_TYPEID_2);

			query.append(_FINDER_COLUMN_ALLATTRIBUTES_ENTITYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
				}

				if (bindTitle) {
					qPos.add(title);
				}

				qPos.add(isDislike);

				qPos.add(typeId);

				qPos.add(entityId);

				List<Like> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_ALLATTRIBUTES,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"LikePersistenceImpl.fetchByAllAttributes(String, String, boolean, long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Like like = list.get(0);

					result = like;

					cacheResult(like);

					if ((like.getPublikUserId() == null) ||
							!like.getPublikUserId().equals(publikUserId) ||
							(like.getTitle() == null) ||
							!like.getTitle().equals(title) ||
							(like.getIsDislike() != isDislike) ||
							(like.getTypeId() != typeId) ||
							(like.getEntityId() != entityId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_ALLATTRIBUTES,
							finderArgs, like);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_ALLATTRIBUTES,
					finderArgs);

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
			return (Like)result;
		}
	}

	/**
	 * Removes the like where publikUserId = &#63; and title = &#63; and isDislike = &#63; and typeId = &#63; and entityId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 * @param title the title
	 * @param isDislike the is dislike
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @return the like that was removed
	 */
	@Override
	public Like removeByAllAttributes(String publikUserId, String title,
		boolean isDislike, long typeId, long entityId)
		throws NoSuchLikeException {
		Like like = findByAllAttributes(publikUserId, title, isDislike, typeId,
				entityId);

		return remove(like);
	}

	/**
	 * Returns the number of likes where publikUserId = &#63; and title = &#63; and isDislike = &#63; and typeId = &#63; and entityId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param title the title
	 * @param isDislike the is dislike
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @return the number of matching likes
	 */
	@Override
	public int countByAllAttributes(String publikUserId, String title,
		boolean isDislike, long typeId, long entityId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ALLATTRIBUTES;

		Object[] finderArgs = new Object[] {
				publikUserId, title, isDislike, typeId, entityId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_LIKE__WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_ALLATTRIBUTES_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ALLATTRIBUTES_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_ALLATTRIBUTES_PUBLIKUSERID_2);
			}

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_ALLATTRIBUTES_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ALLATTRIBUTES_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_ALLATTRIBUTES_TITLE_2);
			}

			query.append(_FINDER_COLUMN_ALLATTRIBUTES_ISDISLIKE_2);

			query.append(_FINDER_COLUMN_ALLATTRIBUTES_TYPEID_2);

			query.append(_FINDER_COLUMN_ALLATTRIBUTES_ENTITYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
				}

				if (bindTitle) {
					qPos.add(title);
				}

				qPos.add(isDislike);

				qPos.add(typeId);

				qPos.add(entityId);

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

	private static final String _FINDER_COLUMN_ALLATTRIBUTES_PUBLIKUSERID_1 = "like_.publikUserId IS NULL AND ";
	private static final String _FINDER_COLUMN_ALLATTRIBUTES_PUBLIKUSERID_2 = "like_.publikUserId = ? AND ";
	private static final String _FINDER_COLUMN_ALLATTRIBUTES_PUBLIKUSERID_3 = "(like_.publikUserId IS NULL OR like_.publikUserId = '') AND ";
	private static final String _FINDER_COLUMN_ALLATTRIBUTES_TITLE_1 = "like_.title IS NULL AND ";
	private static final String _FINDER_COLUMN_ALLATTRIBUTES_TITLE_2 = "like_.title = ? AND ";
	private static final String _FINDER_COLUMN_ALLATTRIBUTES_TITLE_3 = "(like_.title IS NULL OR like_.title = '') AND ";
	private static final String _FINDER_COLUMN_ALLATTRIBUTES_ISDISLIKE_2 = "like_.isDislike = ? AND ";
	private static final String _FINDER_COLUMN_ALLATTRIBUTES_TYPEID_2 = "like_.typeId = ? AND ";
	private static final String _FINDER_COLUMN_ALLATTRIBUTES_ENTITYID_2 = "like_.entityId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ENTITYIDANDTYPEID =
		new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, LikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEntityIdAndTypeId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTITYIDANDTYPEID =
		new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, LikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByEntityIdAndTypeId",
			new String[] { Long.class.getName(), Long.class.getName() },
			LikeModelImpl.ENTITYID_COLUMN_BITMASK |
			LikeModelImpl.TYPEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ENTITYIDANDTYPEID = new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByEntityIdAndTypeId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the likes where entityId = &#63; and typeId = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @return the matching likes
	 */
	@Override
	public List<Like> findByEntityIdAndTypeId(long entityId, long typeId) {
		return findByEntityIdAndTypeId(entityId, typeId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the likes where entityId = &#63; and typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @return the range of matching likes
	 */
	@Override
	public List<Like> findByEntityIdAndTypeId(long entityId, long typeId,
		int start, int end) {
		return findByEntityIdAndTypeId(entityId, typeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the likes where entityId = &#63; and typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching likes
	 */
	@Override
	public List<Like> findByEntityIdAndTypeId(long entityId, long typeId,
		int start, int end, OrderByComparator<Like> orderByComparator) {
		return findByEntityIdAndTypeId(entityId, typeId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the likes where entityId = &#63; and typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching likes
	 */
	@Override
	public List<Like> findByEntityIdAndTypeId(long entityId, long typeId,
		int start, int end, OrderByComparator<Like> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTITYIDANDTYPEID;
			finderArgs = new Object[] { entityId, typeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ENTITYIDANDTYPEID;
			finderArgs = new Object[] {
					entityId, typeId,
					
					start, end, orderByComparator
				};
		}

		List<Like> list = null;

		if (retrieveFromCache) {
			list = (List<Like>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Like like : list) {
					if ((entityId != like.getEntityId()) ||
							(typeId != like.getTypeId())) {
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

			query.append(_SQL_SELECT_LIKE__WHERE);

			query.append(_FINDER_COLUMN_ENTITYIDANDTYPEID_ENTITYID_2);

			query.append(_FINDER_COLUMN_ENTITYIDANDTYPEID_TYPEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LikeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(entityId);

				qPos.add(typeId);

				if (!pagination) {
					list = (List<Like>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Like>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first like in the ordered set where entityId = &#63; and typeId = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching like
	 * @throws NoSuchLikeException if a matching like could not be found
	 */
	@Override
	public Like findByEntityIdAndTypeId_First(long entityId, long typeId,
		OrderByComparator<Like> orderByComparator) throws NoSuchLikeException {
		Like like = fetchByEntityIdAndTypeId_First(entityId, typeId,
				orderByComparator);

		if (like != null) {
			return like;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("entityId=");
		msg.append(entityId);

		msg.append(", typeId=");
		msg.append(typeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLikeException(msg.toString());
	}

	/**
	 * Returns the first like in the ordered set where entityId = &#63; and typeId = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching like, or <code>null</code> if a matching like could not be found
	 */
	@Override
	public Like fetchByEntityIdAndTypeId_First(long entityId, long typeId,
		OrderByComparator<Like> orderByComparator) {
		List<Like> list = findByEntityIdAndTypeId(entityId, typeId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last like in the ordered set where entityId = &#63; and typeId = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching like
	 * @throws NoSuchLikeException if a matching like could not be found
	 */
	@Override
	public Like findByEntityIdAndTypeId_Last(long entityId, long typeId,
		OrderByComparator<Like> orderByComparator) throws NoSuchLikeException {
		Like like = fetchByEntityIdAndTypeId_Last(entityId, typeId,
				orderByComparator);

		if (like != null) {
			return like;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("entityId=");
		msg.append(entityId);

		msg.append(", typeId=");
		msg.append(typeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLikeException(msg.toString());
	}

	/**
	 * Returns the last like in the ordered set where entityId = &#63; and typeId = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching like, or <code>null</code> if a matching like could not be found
	 */
	@Override
	public Like fetchByEntityIdAndTypeId_Last(long entityId, long typeId,
		OrderByComparator<Like> orderByComparator) {
		int count = countByEntityIdAndTypeId(entityId, typeId);

		if (count == 0) {
			return null;
		}

		List<Like> list = findByEntityIdAndTypeId(entityId, typeId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the likes before and after the current like in the ordered set where entityId = &#63; and typeId = &#63;.
	 *
	 * @param likeId the primary key of the current like
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next like
	 * @throws NoSuchLikeException if a like with the primary key could not be found
	 */
	@Override
	public Like[] findByEntityIdAndTypeId_PrevAndNext(long likeId,
		long entityId, long typeId, OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException {
		Like like = findByPrimaryKey(likeId);

		Session session = null;

		try {
			session = openSession();

			Like[] array = new LikeImpl[3];

			array[0] = getByEntityIdAndTypeId_PrevAndNext(session, like,
					entityId, typeId, orderByComparator, true);

			array[1] = like;

			array[2] = getByEntityIdAndTypeId_PrevAndNext(session, like,
					entityId, typeId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Like getByEntityIdAndTypeId_PrevAndNext(Session session,
		Like like, long entityId, long typeId,
		OrderByComparator<Like> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LIKE__WHERE);

		query.append(_FINDER_COLUMN_ENTITYIDANDTYPEID_ENTITYID_2);

		query.append(_FINDER_COLUMN_ENTITYIDANDTYPEID_TYPEID_2);

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
			query.append(LikeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(entityId);

		qPos.add(typeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(like);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Like> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the likes where entityId = &#63; and typeId = &#63; from the database.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 */
	@Override
	public void removeByEntityIdAndTypeId(long entityId, long typeId) {
		for (Like like : findByEntityIdAndTypeId(entityId, typeId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(like);
		}
	}

	/**
	 * Returns the number of likes where entityId = &#63; and typeId = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @return the number of matching likes
	 */
	@Override
	public int countByEntityIdAndTypeId(long entityId, long typeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ENTITYIDANDTYPEID;

		Object[] finderArgs = new Object[] { entityId, typeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LIKE__WHERE);

			query.append(_FINDER_COLUMN_ENTITYIDANDTYPEID_ENTITYID_2);

			query.append(_FINDER_COLUMN_ENTITYIDANDTYPEID_TYPEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(entityId);

				qPos.add(typeId);

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

	private static final String _FINDER_COLUMN_ENTITYIDANDTYPEID_ENTITYID_2 = "like_.entityId = ? AND ";
	private static final String _FINDER_COLUMN_ENTITYIDANDTYPEID_TYPEID_2 = "like_.typeId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ENTITYIDANDTYPEIDANDISDISLIKE =
		new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, LikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEntityIdAndTypeIdAndIsDislike",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTITYIDANDTYPEIDANDISDISLIKE =
		new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, LikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByEntityIdAndTypeIdAndIsDislike",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			LikeModelImpl.ENTITYID_COLUMN_BITMASK |
			LikeModelImpl.TYPEID_COLUMN_BITMASK |
			LikeModelImpl.ISDISLIKE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ENTITYIDANDTYPEIDANDISDISLIKE =
		new FinderPath(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByEntityIdAndTypeIdAndIsDislike",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the likes where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param isDislike the is dislike
	 * @return the matching likes
	 */
	@Override
	public List<Like> findByEntityIdAndTypeIdAndIsDislike(long entityId,
		long typeId, boolean isDislike) {
		return findByEntityIdAndTypeIdAndIsDislike(entityId, typeId, isDislike,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the likes where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param isDislike the is dislike
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @return the range of matching likes
	 */
	@Override
	public List<Like> findByEntityIdAndTypeIdAndIsDislike(long entityId,
		long typeId, boolean isDislike, int start, int end) {
		return findByEntityIdAndTypeIdAndIsDislike(entityId, typeId, isDislike,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the likes where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param isDislike the is dislike
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching likes
	 */
	@Override
	public List<Like> findByEntityIdAndTypeIdAndIsDislike(long entityId,
		long typeId, boolean isDislike, int start, int end,
		OrderByComparator<Like> orderByComparator) {
		return findByEntityIdAndTypeIdAndIsDislike(entityId, typeId, isDislike,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the likes where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param isDislike the is dislike
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching likes
	 */
	@Override
	public List<Like> findByEntityIdAndTypeIdAndIsDislike(long entityId,
		long typeId, boolean isDislike, int start, int end,
		OrderByComparator<Like> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTITYIDANDTYPEIDANDISDISLIKE;
			finderArgs = new Object[] { entityId, typeId, isDislike };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ENTITYIDANDTYPEIDANDISDISLIKE;
			finderArgs = new Object[] {
					entityId, typeId, isDislike,
					
					start, end, orderByComparator
				};
		}

		List<Like> list = null;

		if (retrieveFromCache) {
			list = (List<Like>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Like like : list) {
					if ((entityId != like.getEntityId()) ||
							(typeId != like.getTypeId()) ||
							(isDislike != like.getIsDislike())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_LIKE__WHERE);

			query.append(_FINDER_COLUMN_ENTITYIDANDTYPEIDANDISDISLIKE_ENTITYID_2);

			query.append(_FINDER_COLUMN_ENTITYIDANDTYPEIDANDISDISLIKE_TYPEID_2);

			query.append(_FINDER_COLUMN_ENTITYIDANDTYPEIDANDISDISLIKE_ISDISLIKE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LikeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(entityId);

				qPos.add(typeId);

				qPos.add(isDislike);

				if (!pagination) {
					list = (List<Like>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Like>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first like in the ordered set where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param isDislike the is dislike
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching like
	 * @throws NoSuchLikeException if a matching like could not be found
	 */
	@Override
	public Like findByEntityIdAndTypeIdAndIsDislike_First(long entityId,
		long typeId, boolean isDislike,
		OrderByComparator<Like> orderByComparator) throws NoSuchLikeException {
		Like like = fetchByEntityIdAndTypeIdAndIsDislike_First(entityId,
				typeId, isDislike, orderByComparator);

		if (like != null) {
			return like;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("entityId=");
		msg.append(entityId);

		msg.append(", typeId=");
		msg.append(typeId);

		msg.append(", isDislike=");
		msg.append(isDislike);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLikeException(msg.toString());
	}

	/**
	 * Returns the first like in the ordered set where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param isDislike the is dislike
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching like, or <code>null</code> if a matching like could not be found
	 */
	@Override
	public Like fetchByEntityIdAndTypeIdAndIsDislike_First(long entityId,
		long typeId, boolean isDislike,
		OrderByComparator<Like> orderByComparator) {
		List<Like> list = findByEntityIdAndTypeIdAndIsDislike(entityId, typeId,
				isDislike, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last like in the ordered set where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param isDislike the is dislike
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching like
	 * @throws NoSuchLikeException if a matching like could not be found
	 */
	@Override
	public Like findByEntityIdAndTypeIdAndIsDislike_Last(long entityId,
		long typeId, boolean isDislike,
		OrderByComparator<Like> orderByComparator) throws NoSuchLikeException {
		Like like = fetchByEntityIdAndTypeIdAndIsDislike_Last(entityId, typeId,
				isDislike, orderByComparator);

		if (like != null) {
			return like;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("entityId=");
		msg.append(entityId);

		msg.append(", typeId=");
		msg.append(typeId);

		msg.append(", isDislike=");
		msg.append(isDislike);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLikeException(msg.toString());
	}

	/**
	 * Returns the last like in the ordered set where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param isDislike the is dislike
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching like, or <code>null</code> if a matching like could not be found
	 */
	@Override
	public Like fetchByEntityIdAndTypeIdAndIsDislike_Last(long entityId,
		long typeId, boolean isDislike,
		OrderByComparator<Like> orderByComparator) {
		int count = countByEntityIdAndTypeIdAndIsDislike(entityId, typeId,
				isDislike);

		if (count == 0) {
			return null;
		}

		List<Like> list = findByEntityIdAndTypeIdAndIsDislike(entityId, typeId,
				isDislike, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the likes before and after the current like in the ordered set where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	 *
	 * @param likeId the primary key of the current like
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param isDislike the is dislike
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next like
	 * @throws NoSuchLikeException if a like with the primary key could not be found
	 */
	@Override
	public Like[] findByEntityIdAndTypeIdAndIsDislike_PrevAndNext(long likeId,
		long entityId, long typeId, boolean isDislike,
		OrderByComparator<Like> orderByComparator) throws NoSuchLikeException {
		Like like = findByPrimaryKey(likeId);

		Session session = null;

		try {
			session = openSession();

			Like[] array = new LikeImpl[3];

			array[0] = getByEntityIdAndTypeIdAndIsDislike_PrevAndNext(session,
					like, entityId, typeId, isDislike, orderByComparator, true);

			array[1] = like;

			array[2] = getByEntityIdAndTypeIdAndIsDislike_PrevAndNext(session,
					like, entityId, typeId, isDislike, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Like getByEntityIdAndTypeIdAndIsDislike_PrevAndNext(
		Session session, Like like, long entityId, long typeId,
		boolean isDislike, OrderByComparator<Like> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_LIKE__WHERE);

		query.append(_FINDER_COLUMN_ENTITYIDANDTYPEIDANDISDISLIKE_ENTITYID_2);

		query.append(_FINDER_COLUMN_ENTITYIDANDTYPEIDANDISDISLIKE_TYPEID_2);

		query.append(_FINDER_COLUMN_ENTITYIDANDTYPEIDANDISDISLIKE_ISDISLIKE_2);

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
			query.append(LikeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(entityId);

		qPos.add(typeId);

		qPos.add(isDislike);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(like);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Like> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the likes where entityId = &#63; and typeId = &#63; and isDislike = &#63; from the database.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param isDislike the is dislike
	 */
	@Override
	public void removeByEntityIdAndTypeIdAndIsDislike(long entityId,
		long typeId, boolean isDislike) {
		for (Like like : findByEntityIdAndTypeIdAndIsDislike(entityId, typeId,
				isDislike, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(like);
		}
	}

	/**
	 * Returns the number of likes where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param isDislike the is dislike
	 * @return the number of matching likes
	 */
	@Override
	public int countByEntityIdAndTypeIdAndIsDislike(long entityId, long typeId,
		boolean isDislike) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ENTITYIDANDTYPEIDANDISDISLIKE;

		Object[] finderArgs = new Object[] { entityId, typeId, isDislike };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LIKE__WHERE);

			query.append(_FINDER_COLUMN_ENTITYIDANDTYPEIDANDISDISLIKE_ENTITYID_2);

			query.append(_FINDER_COLUMN_ENTITYIDANDTYPEIDANDISDISLIKE_TYPEID_2);

			query.append(_FINDER_COLUMN_ENTITYIDANDTYPEIDANDISDISLIKE_ISDISLIKE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(entityId);

				qPos.add(typeId);

				qPos.add(isDislike);

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

	private static final String _FINDER_COLUMN_ENTITYIDANDTYPEIDANDISDISLIKE_ENTITYID_2 =
		"like_.entityId = ? AND ";
	private static final String _FINDER_COLUMN_ENTITYIDANDTYPEIDANDISDISLIKE_TYPEID_2 =
		"like_.typeId = ? AND ";
	private static final String _FINDER_COLUMN_ENTITYIDANDTYPEIDANDISDISLIKE_ISDISLIKE_2 =
		"like_.isDislike = ?";

	public LikePersistenceImpl() {
		setModelClass(Like.class);
	}

	/**
	 * Caches the like in the entity cache if it is enabled.
	 *
	 * @param like the like
	 */
	@Override
	public void cacheResult(Like like) {
		entityCache.putResult(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeImpl.class, like.getPrimaryKey(), like);

		finderCache.putResult(FINDER_PATH_FETCH_BY_ALLATTRIBUTES,
			new Object[] {
				like.getPublikUserId(), like.getTitle(), like.getIsDislike(),
				like.getTypeId(), like.getEntityId()
			}, like);

		like.resetOriginalValues();
	}

	/**
	 * Caches the likes in the entity cache if it is enabled.
	 *
	 * @param likes the likes
	 */
	@Override
	public void cacheResult(List<Like> likes) {
		for (Like like : likes) {
			if (entityCache.getResult(LikeModelImpl.ENTITY_CACHE_ENABLED,
						LikeImpl.class, like.getPrimaryKey()) == null) {
				cacheResult(like);
			}
			else {
				like.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all likes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LikeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the like.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Like like) {
		entityCache.removeResult(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeImpl.class, like.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LikeModelImpl)like, true);
	}

	@Override
	public void clearCache(List<Like> likes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Like like : likes) {
			entityCache.removeResult(LikeModelImpl.ENTITY_CACHE_ENABLED,
				LikeImpl.class, like.getPrimaryKey());

			clearUniqueFindersCache((LikeModelImpl)like, true);
		}
	}

	protected void cacheUniqueFindersCache(LikeModelImpl likeModelImpl) {
		Object[] args = new Object[] {
				likeModelImpl.getPublikUserId(), likeModelImpl.getTitle(),
				likeModelImpl.getIsDislike(), likeModelImpl.getTypeId(),
				likeModelImpl.getEntityId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_ALLATTRIBUTES, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_ALLATTRIBUTES, args,
			likeModelImpl, false);
	}

	protected void clearUniqueFindersCache(LikeModelImpl likeModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					likeModelImpl.getPublikUserId(), likeModelImpl.getTitle(),
					likeModelImpl.getIsDislike(), likeModelImpl.getTypeId(),
					likeModelImpl.getEntityId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ALLATTRIBUTES, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ALLATTRIBUTES, args);
		}

		if ((likeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ALLATTRIBUTES.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					likeModelImpl.getOriginalPublikUserId(),
					likeModelImpl.getOriginalTitle(),
					likeModelImpl.getOriginalIsDislike(),
					likeModelImpl.getOriginalTypeId(),
					likeModelImpl.getOriginalEntityId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ALLATTRIBUTES, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ALLATTRIBUTES, args);
		}
	}

	/**
	 * Creates a new like with the primary key. Does not add the like to the database.
	 *
	 * @param likeId the primary key for the new like
	 * @return the new like
	 */
	@Override
	public Like create(long likeId) {
		Like like = new LikeImpl();

		like.setNew(true);
		like.setPrimaryKey(likeId);

		return like;
	}

	/**
	 * Removes the like with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param likeId the primary key of the like
	 * @return the like that was removed
	 * @throws NoSuchLikeException if a like with the primary key could not be found
	 */
	@Override
	public Like remove(long likeId) throws NoSuchLikeException {
		return remove((Serializable)likeId);
	}

	/**
	 * Removes the like with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the like
	 * @return the like that was removed
	 * @throws NoSuchLikeException if a like with the primary key could not be found
	 */
	@Override
	public Like remove(Serializable primaryKey) throws NoSuchLikeException {
		Session session = null;

		try {
			session = openSession();

			Like like = (Like)session.get(LikeImpl.class, primaryKey);

			if (like == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLikeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(like);
		}
		catch (NoSuchLikeException nsee) {
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
	protected Like removeImpl(Like like) {
		like = toUnwrappedModel(like);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(like)) {
				like = (Like)session.get(LikeImpl.class, like.getPrimaryKeyObj());
			}

			if (like != null) {
				session.delete(like);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (like != null) {
			clearCache(like);
		}

		return like;
	}

	@Override
	public Like updateImpl(Like like) {
		like = toUnwrappedModel(like);

		boolean isNew = like.isNew();

		LikeModelImpl likeModelImpl = (LikeModelImpl)like;

		Session session = null;

		try {
			session = openSession();

			if (like.isNew()) {
				session.save(like);

				like.setNew(false);
			}
			else {
				like = (Like)session.merge(like);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LikeModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { likeModelImpl.getPublikUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
				args);

			args = new Object[] {
					likeModelImpl.getPublikUserId(),
					likeModelImpl.getIsDislike()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDISDISLIKE,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERIDANDISDISLIKE,
				args);

			args = new Object[] {
					likeModelImpl.getEntityId(), likeModelImpl.getTypeId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ENTITYIDANDTYPEID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTITYIDANDTYPEID,
				args);

			args = new Object[] {
					likeModelImpl.getEntityId(), likeModelImpl.getTypeId(),
					likeModelImpl.getIsDislike()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ENTITYIDANDTYPEIDANDISDISLIKE,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTITYIDANDTYPEIDANDISDISLIKE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((likeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						likeModelImpl.getOriginalPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);

				args = new Object[] { likeModelImpl.getPublikUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);
			}

			if ((likeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERIDANDISDISLIKE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						likeModelImpl.getOriginalPublikUserId(),
						likeModelImpl.getOriginalIsDislike()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDISDISLIKE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERIDANDISDISLIKE,
					args);

				args = new Object[] {
						likeModelImpl.getPublikUserId(),
						likeModelImpl.getIsDislike()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDISDISLIKE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERIDANDISDISLIKE,
					args);
			}

			if ((likeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTITYIDANDTYPEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						likeModelImpl.getOriginalEntityId(),
						likeModelImpl.getOriginalTypeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ENTITYIDANDTYPEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTITYIDANDTYPEID,
					args);

				args = new Object[] {
						likeModelImpl.getEntityId(), likeModelImpl.getTypeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ENTITYIDANDTYPEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTITYIDANDTYPEID,
					args);
			}

			if ((likeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTITYIDANDTYPEIDANDISDISLIKE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						likeModelImpl.getOriginalEntityId(),
						likeModelImpl.getOriginalTypeId(),
						likeModelImpl.getOriginalIsDislike()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ENTITYIDANDTYPEIDANDISDISLIKE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTITYIDANDTYPEIDANDISDISLIKE,
					args);

				args = new Object[] {
						likeModelImpl.getEntityId(), likeModelImpl.getTypeId(),
						likeModelImpl.getIsDislike()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ENTITYIDANDTYPEIDANDISDISLIKE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTITYIDANDTYPEIDANDISDISLIKE,
					args);
			}
		}

		entityCache.putResult(LikeModelImpl.ENTITY_CACHE_ENABLED,
			LikeImpl.class, like.getPrimaryKey(), like, false);

		clearUniqueFindersCache(likeModelImpl, false);
		cacheUniqueFindersCache(likeModelImpl);

		like.resetOriginalValues();

		return like;
	}

	protected Like toUnwrappedModel(Like like) {
		if (like instanceof LikeImpl) {
			return like;
		}

		LikeImpl likeImpl = new LikeImpl();

		likeImpl.setNew(like.isNew());
		likeImpl.setPrimaryKey(like.getPrimaryKey());

		likeImpl.setLikeId(like.getLikeId());
		likeImpl.setPublikUserId(like.getPublikUserId());
		likeImpl.setTitle(like.getTitle());
		likeImpl.setIsDislike(like.isIsDislike());
		likeImpl.setTypeId(like.getTypeId());
		likeImpl.setEntityId(like.getEntityId());
		likeImpl.setEntityGroupId(like.getEntityGroupId());

		return likeImpl;
	}

	/**
	 * Returns the like with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the like
	 * @return the like
	 * @throws NoSuchLikeException if a like with the primary key could not be found
	 */
	@Override
	public Like findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLikeException {
		Like like = fetchByPrimaryKey(primaryKey);

		if (like == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLikeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return like;
	}

	/**
	 * Returns the like with the primary key or throws a {@link NoSuchLikeException} if it could not be found.
	 *
	 * @param likeId the primary key of the like
	 * @return the like
	 * @throws NoSuchLikeException if a like with the primary key could not be found
	 */
	@Override
	public Like findByPrimaryKey(long likeId) throws NoSuchLikeException {
		return findByPrimaryKey((Serializable)likeId);
	}

	/**
	 * Returns the like with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the like
	 * @return the like, or <code>null</code> if a like with the primary key could not be found
	 */
	@Override
	public Like fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LikeModelImpl.ENTITY_CACHE_ENABLED,
				LikeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Like like = (Like)serializable;

		if (like == null) {
			Session session = null;

			try {
				session = openSession();

				like = (Like)session.get(LikeImpl.class, primaryKey);

				if (like != null) {
					cacheResult(like);
				}
				else {
					entityCache.putResult(LikeModelImpl.ENTITY_CACHE_ENABLED,
						LikeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LikeModelImpl.ENTITY_CACHE_ENABLED,
					LikeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return like;
	}

	/**
	 * Returns the like with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param likeId the primary key of the like
	 * @return the like, or <code>null</code> if a like with the primary key could not be found
	 */
	@Override
	public Like fetchByPrimaryKey(long likeId) {
		return fetchByPrimaryKey((Serializable)likeId);
	}

	@Override
	public Map<Serializable, Like> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Like> map = new HashMap<Serializable, Like>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Like like = fetchByPrimaryKey(primaryKey);

			if (like != null) {
				map.put(primaryKey, like);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LikeModelImpl.ENTITY_CACHE_ENABLED,
					LikeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Like)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LIKE__WHERE_PKS_IN);

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

			for (Like like : (List<Like>)q.list()) {
				map.put(like.getPrimaryKeyObj(), like);

				cacheResult(like);

				uncachedPrimaryKeys.remove(like.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LikeModelImpl.ENTITY_CACHE_ENABLED,
					LikeImpl.class, primaryKey, nullModel);
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
	 * Returns all the likes.
	 *
	 * @return the likes
	 */
	@Override
	public List<Like> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the likes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @return the range of likes
	 */
	@Override
	public List<Like> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the likes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of likes
	 */
	@Override
	public List<Like> findAll(int start, int end,
		OrderByComparator<Like> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the likes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of likes
	 * @param end the upper bound of the range of likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of likes
	 */
	@Override
	public List<Like> findAll(int start, int end,
		OrderByComparator<Like> orderByComparator, boolean retrieveFromCache) {
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

		List<Like> list = null;

		if (retrieveFromCache) {
			list = (List<Like>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LIKE_);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LIKE_;

				if (pagination) {
					sql = sql.concat(LikeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Like>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Like>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the likes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Like like : findAll()) {
			remove(like);
		}
	}

	/**
	 * Returns the number of likes.
	 *
	 * @return the number of likes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LIKE_);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return LikeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the like persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LikeImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LIKE_ = "SELECT like_ FROM Like like_";
	private static final String _SQL_SELECT_LIKE__WHERE_PKS_IN = "SELECT like_ FROM Like like_ WHERE likeId IN (";
	private static final String _SQL_SELECT_LIKE__WHERE = "SELECT like_ FROM Like like_ WHERE ";
	private static final String _SQL_COUNT_LIKE_ = "SELECT COUNT(like_) FROM Like like_";
	private static final String _SQL_COUNT_LIKE__WHERE = "SELECT COUNT(like_) FROM Like like_ WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "like_.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Like exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Like exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LikePersistenceImpl.class);
}