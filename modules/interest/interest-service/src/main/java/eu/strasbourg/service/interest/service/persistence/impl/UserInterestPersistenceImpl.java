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

package eu.strasbourg.service.interest.service.persistence.impl;

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
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.interest.exception.NoSuchUserInterestException;
import eu.strasbourg.service.interest.model.UserInterest;
import eu.strasbourg.service.interest.model.impl.UserInterestImpl;
import eu.strasbourg.service.interest.model.impl.UserInterestModelImpl;
import eu.strasbourg.service.interest.service.persistence.UserInterestPK;
import eu.strasbourg.service.interest.service.persistence.UserInterestPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the user interest service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see UserInterestPersistence
 * @see eu.strasbourg.service.interest.service.persistence.UserInterestUtil
 * @generated
 */
@ProviderType
public class UserInterestPersistenceImpl extends BasePersistenceImpl<UserInterest>
	implements UserInterestPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserInterestUtil} to access the user interest persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UserInterestImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, UserInterestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, UserInterestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_INTERESTID =
		new FinderPath(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, UserInterestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByInterestId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INTERESTID =
		new FinderPath(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, UserInterestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByInterestId",
			new String[] { Long.class.getName() },
			UserInterestModelImpl.INTERESTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_INTERESTID = new FinderPath(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByInterestId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the user interests where interestId = &#63;.
	 *
	 * @param interestId the interest ID
	 * @return the matching user interests
	 */
	@Override
	public List<UserInterest> findByInterestId(long interestId) {
		return findByInterestId(interestId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user interests where interestId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param interestId the interest ID
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @return the range of matching user interests
	 */
	@Override
	public List<UserInterest> findByInterestId(long interestId, int start,
		int end) {
		return findByInterestId(interestId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user interests where interestId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param interestId the interest ID
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user interests
	 */
	@Override
	public List<UserInterest> findByInterestId(long interestId, int start,
		int end, OrderByComparator<UserInterest> orderByComparator) {
		return findByInterestId(interestId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user interests where interestId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param interestId the interest ID
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching user interests
	 */
	@Override
	public List<UserInterest> findByInterestId(long interestId, int start,
		int end, OrderByComparator<UserInterest> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INTERESTID;
			finderArgs = new Object[] { interestId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_INTERESTID;
			finderArgs = new Object[] { interestId, start, end, orderByComparator };
		}

		List<UserInterest> list = null;

		if (retrieveFromCache) {
			list = (List<UserInterest>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserInterest userInterest : list) {
					if ((interestId != userInterest.getInterestId())) {
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

			query.append(_SQL_SELECT_USERINTEREST_WHERE);

			query.append(_FINDER_COLUMN_INTERESTID_INTERESTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserInterestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(interestId);

				if (!pagination) {
					list = (List<UserInterest>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserInterest>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first user interest in the ordered set where interestId = &#63;.
	 *
	 * @param interestId the interest ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user interest
	 * @throws NoSuchUserInterestException if a matching user interest could not be found
	 */
	@Override
	public UserInterest findByInterestId_First(long interestId,
		OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException {
		UserInterest userInterest = fetchByInterestId_First(interestId,
				orderByComparator);

		if (userInterest != null) {
			return userInterest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("interestId=");
		msg.append(interestId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserInterestException(msg.toString());
	}

	/**
	 * Returns the first user interest in the ordered set where interestId = &#63;.
	 *
	 * @param interestId the interest ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user interest, or <code>null</code> if a matching user interest could not be found
	 */
	@Override
	public UserInterest fetchByInterestId_First(long interestId,
		OrderByComparator<UserInterest> orderByComparator) {
		List<UserInterest> list = findByInterestId(interestId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user interest in the ordered set where interestId = &#63;.
	 *
	 * @param interestId the interest ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user interest
	 * @throws NoSuchUserInterestException if a matching user interest could not be found
	 */
	@Override
	public UserInterest findByInterestId_Last(long interestId,
		OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException {
		UserInterest userInterest = fetchByInterestId_Last(interestId,
				orderByComparator);

		if (userInterest != null) {
			return userInterest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("interestId=");
		msg.append(interestId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserInterestException(msg.toString());
	}

	/**
	 * Returns the last user interest in the ordered set where interestId = &#63;.
	 *
	 * @param interestId the interest ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user interest, or <code>null</code> if a matching user interest could not be found
	 */
	@Override
	public UserInterest fetchByInterestId_Last(long interestId,
		OrderByComparator<UserInterest> orderByComparator) {
		int count = countByInterestId(interestId);

		if (count == 0) {
			return null;
		}

		List<UserInterest> list = findByInterestId(interestId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user interests before and after the current user interest in the ordered set where interestId = &#63;.
	 *
	 * @param userInterestPK the primary key of the current user interest
	 * @param interestId the interest ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user interest
	 * @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	 */
	@Override
	public UserInterest[] findByInterestId_PrevAndNext(
		UserInterestPK userInterestPK, long interestId,
		OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException {
		UserInterest userInterest = findByPrimaryKey(userInterestPK);

		Session session = null;

		try {
			session = openSession();

			UserInterest[] array = new UserInterestImpl[3];

			array[0] = getByInterestId_PrevAndNext(session, userInterest,
					interestId, orderByComparator, true);

			array[1] = userInterest;

			array[2] = getByInterestId_PrevAndNext(session, userInterest,
					interestId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserInterest getByInterestId_PrevAndNext(Session session,
		UserInterest userInterest, long interestId,
		OrderByComparator<UserInterest> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERINTEREST_WHERE);

		query.append(_FINDER_COLUMN_INTERESTID_INTERESTID_2);

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
			query.append(UserInterestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(interestId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userInterest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserInterest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user interests where interestId = &#63; from the database.
	 *
	 * @param interestId the interest ID
	 */
	@Override
	public void removeByInterestId(long interestId) {
		for (UserInterest userInterest : findByInterestId(interestId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userInterest);
		}
	}

	/**
	 * Returns the number of user interests where interestId = &#63;.
	 *
	 * @param interestId the interest ID
	 * @return the number of matching user interests
	 */
	@Override
	public int countByInterestId(long interestId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_INTERESTID;

		Object[] finderArgs = new Object[] { interestId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERINTEREST_WHERE);

			query.append(_FINDER_COLUMN_INTERESTID_INTERESTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(interestId);

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

	private static final String _FINDER_COLUMN_INTERESTID_INTERESTID_2 = "userInterest.id.interestId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, UserInterestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPublikUserId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, UserInterestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikUserId",
			new String[] { String.class.getName() },
			UserInterestModelImpl.PUBLIKUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKUSERID = new FinderPath(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikUserId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the user interests where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching user interests
	 */
	@Override
	public List<UserInterest> findByPublikUserId(String publikUserId) {
		return findByPublikUserId(publikUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user interests where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @return the range of matching user interests
	 */
	@Override
	public List<UserInterest> findByPublikUserId(String publikUserId,
		int start, int end) {
		return findByPublikUserId(publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user interests where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user interests
	 */
	@Override
	public List<UserInterest> findByPublikUserId(String publikUserId,
		int start, int end, OrderByComparator<UserInterest> orderByComparator) {
		return findByPublikUserId(publikUserId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the user interests where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching user interests
	 */
	@Override
	public List<UserInterest> findByPublikUserId(String publikUserId,
		int start, int end, OrderByComparator<UserInterest> orderByComparator,
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

		List<UserInterest> list = null;

		if (retrieveFromCache) {
			list = (List<UserInterest>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserInterest userInterest : list) {
					if (!Objects.equals(publikUserId,
								userInterest.getPublikUserId())) {
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

			query.append(_SQL_SELECT_USERINTEREST_WHERE);

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
				query.append(UserInterestModelImpl.ORDER_BY_JPQL);
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
					list = (List<UserInterest>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserInterest>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first user interest in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user interest
	 * @throws NoSuchUserInterestException if a matching user interest could not be found
	 */
	@Override
	public UserInterest findByPublikUserId_First(String publikUserId,
		OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException {
		UserInterest userInterest = fetchByPublikUserId_First(publikUserId,
				orderByComparator);

		if (userInterest != null) {
			return userInterest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserInterestException(msg.toString());
	}

	/**
	 * Returns the first user interest in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user interest, or <code>null</code> if a matching user interest could not be found
	 */
	@Override
	public UserInterest fetchByPublikUserId_First(String publikUserId,
		OrderByComparator<UserInterest> orderByComparator) {
		List<UserInterest> list = findByPublikUserId(publikUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user interest in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user interest
	 * @throws NoSuchUserInterestException if a matching user interest could not be found
	 */
	@Override
	public UserInterest findByPublikUserId_Last(String publikUserId,
		OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException {
		UserInterest userInterest = fetchByPublikUserId_Last(publikUserId,
				orderByComparator);

		if (userInterest != null) {
			return userInterest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserInterestException(msg.toString());
	}

	/**
	 * Returns the last user interest in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user interest, or <code>null</code> if a matching user interest could not be found
	 */
	@Override
	public UserInterest fetchByPublikUserId_Last(String publikUserId,
		OrderByComparator<UserInterest> orderByComparator) {
		int count = countByPublikUserId(publikUserId);

		if (count == 0) {
			return null;
		}

		List<UserInterest> list = findByPublikUserId(publikUserId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user interests before and after the current user interest in the ordered set where publikUserId = &#63;.
	 *
	 * @param userInterestPK the primary key of the current user interest
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user interest
	 * @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	 */
	@Override
	public UserInterest[] findByPublikUserId_PrevAndNext(
		UserInterestPK userInterestPK, String publikUserId,
		OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException {
		UserInterest userInterest = findByPrimaryKey(userInterestPK);

		Session session = null;

		try {
			session = openSession();

			UserInterest[] array = new UserInterestImpl[3];

			array[0] = getByPublikUserId_PrevAndNext(session, userInterest,
					publikUserId, orderByComparator, true);

			array[1] = userInterest;

			array[2] = getByPublikUserId_PrevAndNext(session, userInterest,
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

	protected UserInterest getByPublikUserId_PrevAndNext(Session session,
		UserInterest userInterest, String publikUserId,
		OrderByComparator<UserInterest> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERINTEREST_WHERE);

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
			query.append(UserInterestModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(userInterest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserInterest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user interests where publikUserId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 */
	@Override
	public void removeByPublikUserId(String publikUserId) {
		for (UserInterest userInterest : findByPublikUserId(publikUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userInterest);
		}
	}

	/**
	 * Returns the number of user interests where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the number of matching user interests
	 */
	@Override
	public int countByPublikUserId(String publikUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKUSERID;

		Object[] finderArgs = new Object[] { publikUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERINTEREST_WHERE);

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

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1 = "userInterest.id.publikUserId IS NULL";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2 = "userInterest.id.publikUserId = ?";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3 = "(userInterest.id.publikUserId IS NULL OR userInterest.id.publikUserId = '')";

	public UserInterestPersistenceImpl() {
		setModelClass(UserInterest.class);
	}

	/**
	 * Caches the user interest in the entity cache if it is enabled.
	 *
	 * @param userInterest the user interest
	 */
	@Override
	public void cacheResult(UserInterest userInterest) {
		entityCache.putResult(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestImpl.class, userInterest.getPrimaryKey(), userInterest);

		userInterest.resetOriginalValues();
	}

	/**
	 * Caches the user interests in the entity cache if it is enabled.
	 *
	 * @param userInterests the user interests
	 */
	@Override
	public void cacheResult(List<UserInterest> userInterests) {
		for (UserInterest userInterest : userInterests) {
			if (entityCache.getResult(
						UserInterestModelImpl.ENTITY_CACHE_ENABLED,
						UserInterestImpl.class, userInterest.getPrimaryKey()) == null) {
				cacheResult(userInterest);
			}
			else {
				userInterest.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all user interests.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserInterestImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the user interest.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserInterest userInterest) {
		entityCache.removeResult(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestImpl.class, userInterest.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<UserInterest> userInterests) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserInterest userInterest : userInterests) {
			entityCache.removeResult(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
				UserInterestImpl.class, userInterest.getPrimaryKey());
		}
	}

	/**
	 * Creates a new user interest with the primary key. Does not add the user interest to the database.
	 *
	 * @param userInterestPK the primary key for the new user interest
	 * @return the new user interest
	 */
	@Override
	public UserInterest create(UserInterestPK userInterestPK) {
		UserInterest userInterest = new UserInterestImpl();

		userInterest.setNew(true);
		userInterest.setPrimaryKey(userInterestPK);

		return userInterest;
	}

	/**
	 * Removes the user interest with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userInterestPK the primary key of the user interest
	 * @return the user interest that was removed
	 * @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	 */
	@Override
	public UserInterest remove(UserInterestPK userInterestPK)
		throws NoSuchUserInterestException {
		return remove((Serializable)userInterestPK);
	}

	/**
	 * Removes the user interest with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user interest
	 * @return the user interest that was removed
	 * @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	 */
	@Override
	public UserInterest remove(Serializable primaryKey)
		throws NoSuchUserInterestException {
		Session session = null;

		try {
			session = openSession();

			UserInterest userInterest = (UserInterest)session.get(UserInterestImpl.class,
					primaryKey);

			if (userInterest == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserInterestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(userInterest);
		}
		catch (NoSuchUserInterestException nsee) {
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
	protected UserInterest removeImpl(UserInterest userInterest) {
		userInterest = toUnwrappedModel(userInterest);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userInterest)) {
				userInterest = (UserInterest)session.get(UserInterestImpl.class,
						userInterest.getPrimaryKeyObj());
			}

			if (userInterest != null) {
				session.delete(userInterest);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (userInterest != null) {
			clearCache(userInterest);
		}

		return userInterest;
	}

	@Override
	public UserInterest updateImpl(UserInterest userInterest) {
		userInterest = toUnwrappedModel(userInterest);

		boolean isNew = userInterest.isNew();

		UserInterestModelImpl userInterestModelImpl = (UserInterestModelImpl)userInterest;

		Session session = null;

		try {
			session = openSession();

			if (userInterest.isNew()) {
				session.save(userInterest);

				userInterest.setNew(false);
			}
			else {
				userInterest = (UserInterest)session.merge(userInterest);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!UserInterestModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { userInterestModelImpl.getInterestId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_INTERESTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INTERESTID,
				args);

			args = new Object[] { userInterestModelImpl.getPublikUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((userInterestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INTERESTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userInterestModelImpl.getOriginalInterestId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_INTERESTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INTERESTID,
					args);

				args = new Object[] { userInterestModelImpl.getInterestId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_INTERESTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INTERESTID,
					args);
			}

			if ((userInterestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userInterestModelImpl.getOriginalPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);

				args = new Object[] { userInterestModelImpl.getPublikUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);
			}
		}

		entityCache.putResult(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestImpl.class, userInterest.getPrimaryKey(), userInterest,
			false);

		userInterest.resetOriginalValues();

		return userInterest;
	}

	protected UserInterest toUnwrappedModel(UserInterest userInterest) {
		if (userInterest instanceof UserInterestImpl) {
			return userInterest;
		}

		UserInterestImpl userInterestImpl = new UserInterestImpl();

		userInterestImpl.setNew(userInterest.isNew());
		userInterestImpl.setPrimaryKey(userInterest.getPrimaryKey());

		userInterestImpl.setInterestId(userInterest.getInterestId());
		userInterestImpl.setPublikUserId(userInterest.getPublikUserId());

		return userInterestImpl;
	}

	/**
	 * Returns the user interest with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user interest
	 * @return the user interest
	 * @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	 */
	@Override
	public UserInterest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserInterestException {
		UserInterest userInterest = fetchByPrimaryKey(primaryKey);

		if (userInterest == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserInterestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return userInterest;
	}

	/**
	 * Returns the user interest with the primary key or throws a {@link NoSuchUserInterestException} if it could not be found.
	 *
	 * @param userInterestPK the primary key of the user interest
	 * @return the user interest
	 * @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	 */
	@Override
	public UserInterest findByPrimaryKey(UserInterestPK userInterestPK)
		throws NoSuchUserInterestException {
		return findByPrimaryKey((Serializable)userInterestPK);
	}

	/**
	 * Returns the user interest with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user interest
	 * @return the user interest, or <code>null</code> if a user interest with the primary key could not be found
	 */
	@Override
	public UserInterest fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
				UserInterestImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		UserInterest userInterest = (UserInterest)serializable;

		if (userInterest == null) {
			Session session = null;

			try {
				session = openSession();

				userInterest = (UserInterest)session.get(UserInterestImpl.class,
						primaryKey);

				if (userInterest != null) {
					cacheResult(userInterest);
				}
				else {
					entityCache.putResult(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
						UserInterestImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(UserInterestModelImpl.ENTITY_CACHE_ENABLED,
					UserInterestImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return userInterest;
	}

	/**
	 * Returns the user interest with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userInterestPK the primary key of the user interest
	 * @return the user interest, or <code>null</code> if a user interest with the primary key could not be found
	 */
	@Override
	public UserInterest fetchByPrimaryKey(UserInterestPK userInterestPK) {
		return fetchByPrimaryKey((Serializable)userInterestPK);
	}

	@Override
	public Map<Serializable, UserInterest> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, UserInterest> map = new HashMap<Serializable, UserInterest>();

		for (Serializable primaryKey : primaryKeys) {
			UserInterest userInterest = fetchByPrimaryKey(primaryKey);

			if (userInterest != null) {
				map.put(primaryKey, userInterest);
			}
		}

		return map;
	}

	/**
	 * Returns all the user interests.
	 *
	 * @return the user interests
	 */
	@Override
	public List<UserInterest> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user interests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @return the range of user interests
	 */
	@Override
	public List<UserInterest> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user interests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user interests
	 */
	@Override
	public List<UserInterest> findAll(int start, int end,
		OrderByComparator<UserInterest> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user interests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of user interests
	 */
	@Override
	public List<UserInterest> findAll(int start, int end,
		OrderByComparator<UserInterest> orderByComparator,
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

		List<UserInterest> list = null;

		if (retrieveFromCache) {
			list = (List<UserInterest>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_USERINTEREST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERINTEREST;

				if (pagination) {
					sql = sql.concat(UserInterestModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UserInterest>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserInterest>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the user interests from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserInterest userInterest : findAll()) {
			remove(userInterest);
		}
	}

	/**
	 * Returns the number of user interests.
	 *
	 * @return the number of user interests
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERINTEREST);

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
		return UserInterestModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user interest persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(UserInterestImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_USERINTEREST = "SELECT userInterest FROM UserInterest userInterest";
	private static final String _SQL_SELECT_USERINTEREST_WHERE = "SELECT userInterest FROM UserInterest userInterest WHERE ";
	private static final String _SQL_COUNT_USERINTEREST = "SELECT COUNT(userInterest) FROM UserInterest userInterest";
	private static final String _SQL_COUNT_USERINTEREST_WHERE = "SELECT COUNT(userInterest) FROM UserInterest userInterest WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userInterest.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserInterest exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserInterest exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(UserInterestPersistenceImpl.class);
}