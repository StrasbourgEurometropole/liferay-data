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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.interest.exception.NoSuchUserInterestException;
import eu.strasbourg.service.interest.model.UserInterest;
import eu.strasbourg.service.interest.model.impl.UserInterestImpl;
import eu.strasbourg.service.interest.model.impl.UserInterestModelImpl;
import eu.strasbourg.service.interest.service.persistence.UserInterestPK;
import eu.strasbourg.service.interest.service.persistence.UserInterestPersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * @generated
 */
public class UserInterestPersistenceImpl
	extends BasePersistenceImpl<UserInterest>
	implements UserInterestPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UserInterestUtil</code> to access the user interest persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UserInterestImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByInterestId;
	private FinderPath _finderPathWithoutPaginationFindByInterestId;
	private FinderPath _finderPathCountByInterestId;

	/**
	 * Returns all the user interests where interestId = &#63;.
	 *
	 * @param interestId the interest ID
	 * @return the matching user interests
	 */
	@Override
	public List<UserInterest> findByInterestId(long interestId) {
		return findByInterestId(
			interestId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user interests where interestId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserInterestModelImpl</code>.
	 * </p>
	 *
	 * @param interestId the interest ID
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @return the range of matching user interests
	 */
	@Override
	public List<UserInterest> findByInterestId(
		long interestId, int start, int end) {

		return findByInterestId(interestId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user interests where interestId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserInterestModelImpl</code>.
	 * </p>
	 *
	 * @param interestId the interest ID
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user interests
	 */
	@Override
	public List<UserInterest> findByInterestId(
		long interestId, int start, int end,
		OrderByComparator<UserInterest> orderByComparator) {

		return findByInterestId(
			interestId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user interests where interestId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserInterestModelImpl</code>.
	 * </p>
	 *
	 * @param interestId the interest ID
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user interests
	 */
	@Override
	public List<UserInterest> findByInterestId(
		long interestId, int start, int end,
		OrderByComparator<UserInterest> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByInterestId;
				finderArgs = new Object[] {interestId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByInterestId;
			finderArgs = new Object[] {
				interestId, start, end, orderByComparator
			};
		}

		List<UserInterest> list = null;

		if (useFinderCache) {
			list = (List<UserInterest>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserInterest userInterest : list) {
					if (interestId != userInterest.getInterestId()) {
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

			sb.append(_SQL_SELECT_USERINTEREST_WHERE);

			sb.append(_FINDER_COLUMN_INTERESTID_INTERESTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(UserInterestModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(interestId);

				list = (List<UserInterest>)QueryUtil.list(
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
	 * Returns the first user interest in the ordered set where interestId = &#63;.
	 *
	 * @param interestId the interest ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user interest
	 * @throws NoSuchUserInterestException if a matching user interest could not be found
	 */
	@Override
	public UserInterest findByInterestId_First(
			long interestId, OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException {

		UserInterest userInterest = fetchByInterestId_First(
			interestId, orderByComparator);

		if (userInterest != null) {
			return userInterest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("interestId=");
		sb.append(interestId);

		sb.append("}");

		throw new NoSuchUserInterestException(sb.toString());
	}

	/**
	 * Returns the first user interest in the ordered set where interestId = &#63;.
	 *
	 * @param interestId the interest ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user interest, or <code>null</code> if a matching user interest could not be found
	 */
	@Override
	public UserInterest fetchByInterestId_First(
		long interestId, OrderByComparator<UserInterest> orderByComparator) {

		List<UserInterest> list = findByInterestId(
			interestId, 0, 1, orderByComparator);

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
	public UserInterest findByInterestId_Last(
			long interestId, OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException {

		UserInterest userInterest = fetchByInterestId_Last(
			interestId, orderByComparator);

		if (userInterest != null) {
			return userInterest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("interestId=");
		sb.append(interestId);

		sb.append("}");

		throw new NoSuchUserInterestException(sb.toString());
	}

	/**
	 * Returns the last user interest in the ordered set where interestId = &#63;.
	 *
	 * @param interestId the interest ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user interest, or <code>null</code> if a matching user interest could not be found
	 */
	@Override
	public UserInterest fetchByInterestId_Last(
		long interestId, OrderByComparator<UserInterest> orderByComparator) {

		int count = countByInterestId(interestId);

		if (count == 0) {
			return null;
		}

		List<UserInterest> list = findByInterestId(
			interestId, count - 1, count, orderByComparator);

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

			array[0] = getByInterestId_PrevAndNext(
				session, userInterest, interestId, orderByComparator, true);

			array[1] = userInterest;

			array[2] = getByInterestId_PrevAndNext(
				session, userInterest, interestId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserInterest getByInterestId_PrevAndNext(
		Session session, UserInterest userInterest, long interestId,
		OrderByComparator<UserInterest> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_USERINTEREST_WHERE);

		sb.append(_FINDER_COLUMN_INTERESTID_INTERESTID_2);

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
			sb.append(UserInterestModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(interestId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(userInterest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserInterest> list = query.list();

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
		for (UserInterest userInterest :
				findByInterestId(
					interestId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

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
		FinderPath finderPath = _finderPathCountByInterestId;

		Object[] finderArgs = new Object[] {interestId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USERINTEREST_WHERE);

			sb.append(_FINDER_COLUMN_INTERESTID_INTERESTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(interestId);

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

	private static final String _FINDER_COLUMN_INTERESTID_INTERESTID_2 =
		"userInterest.id.interestId = ?";

	private FinderPath _finderPathWithPaginationFindByPublikUserId;
	private FinderPath _finderPathWithoutPaginationFindByPublikUserId;
	private FinderPath _finderPathCountByPublikUserId;

	/**
	 * Returns all the user interests where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching user interests
	 */
	@Override
	public List<UserInterest> findByPublikUserId(String publikUserId) {
		return findByPublikUserId(
			publikUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user interests where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserInterestModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @return the range of matching user interests
	 */
	@Override
	public List<UserInterest> findByPublikUserId(
		String publikUserId, int start, int end) {

		return findByPublikUserId(publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user interests where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserInterestModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user interests
	 */
	@Override
	public List<UserInterest> findByPublikUserId(
		String publikUserId, int start, int end,
		OrderByComparator<UserInterest> orderByComparator) {

		return findByPublikUserId(
			publikUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user interests where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserInterestModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user interests
	 */
	@Override
	public List<UserInterest> findByPublikUserId(
		String publikUserId, int start, int end,
		OrderByComparator<UserInterest> orderByComparator,
		boolean useFinderCache) {

		publikUserId = Objects.toString(publikUserId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPublikUserId;
				finderArgs = new Object[] {publikUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPublikUserId;
			finderArgs = new Object[] {
				publikUserId, start, end, orderByComparator
			};
		}

		List<UserInterest> list = null;

		if (useFinderCache) {
			list = (List<UserInterest>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserInterest userInterest : list) {
					if (!publikUserId.equals(userInterest.getPublikUserId())) {
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

			sb.append(_SQL_SELECT_USERINTEREST_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(UserInterestModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublikUserId) {
					queryPos.add(publikUserId);
				}

				list = (List<UserInterest>)QueryUtil.list(
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
	 * Returns the first user interest in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user interest
	 * @throws NoSuchUserInterestException if a matching user interest could not be found
	 */
	@Override
	public UserInterest findByPublikUserId_First(
			String publikUserId,
			OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException {

		UserInterest userInterest = fetchByPublikUserId_First(
			publikUserId, orderByComparator);

		if (userInterest != null) {
			return userInterest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publikUserId=");
		sb.append(publikUserId);

		sb.append("}");

		throw new NoSuchUserInterestException(sb.toString());
	}

	/**
	 * Returns the first user interest in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user interest, or <code>null</code> if a matching user interest could not be found
	 */
	@Override
	public UserInterest fetchByPublikUserId_First(
		String publikUserId,
		OrderByComparator<UserInterest> orderByComparator) {

		List<UserInterest> list = findByPublikUserId(
			publikUserId, 0, 1, orderByComparator);

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
	public UserInterest findByPublikUserId_Last(
			String publikUserId,
			OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException {

		UserInterest userInterest = fetchByPublikUserId_Last(
			publikUserId, orderByComparator);

		if (userInterest != null) {
			return userInterest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publikUserId=");
		sb.append(publikUserId);

		sb.append("}");

		throw new NoSuchUserInterestException(sb.toString());
	}

	/**
	 * Returns the last user interest in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user interest, or <code>null</code> if a matching user interest could not be found
	 */
	@Override
	public UserInterest fetchByPublikUserId_Last(
		String publikUserId,
		OrderByComparator<UserInterest> orderByComparator) {

		int count = countByPublikUserId(publikUserId);

		if (count == 0) {
			return null;
		}

		List<UserInterest> list = findByPublikUserId(
			publikUserId, count - 1, count, orderByComparator);

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

		publikUserId = Objects.toString(publikUserId, "");

		UserInterest userInterest = findByPrimaryKey(userInterestPK);

		Session session = null;

		try {
			session = openSession();

			UserInterest[] array = new UserInterestImpl[3];

			array[0] = getByPublikUserId_PrevAndNext(
				session, userInterest, publikUserId, orderByComparator, true);

			array[1] = userInterest;

			array[2] = getByPublikUserId_PrevAndNext(
				session, userInterest, publikUserId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserInterest getByPublikUserId_PrevAndNext(
		Session session, UserInterest userInterest, String publikUserId,
		OrderByComparator<UserInterest> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_USERINTEREST_WHERE);

		boolean bindPublikUserId = false;

		if (publikUserId.isEmpty()) {
			sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
		}
		else {
			bindPublikUserId = true;

			sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
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
			sb.append(UserInterestModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindPublikUserId) {
			queryPos.add(publikUserId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(userInterest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserInterest> list = query.list();

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
		for (UserInterest userInterest :
				findByPublikUserId(
					publikUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

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
		publikUserId = Objects.toString(publikUserId, "");

		FinderPath finderPath = _finderPathCountByPublikUserId;

		Object[] finderArgs = new Object[] {publikUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USERINTEREST_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublikUserId) {
					queryPos.add(publikUserId);
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

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2 =
		"userInterest.id.publikUserId = ?";

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3 =
		"(userInterest.id.publikUserId IS NULL OR userInterest.id.publikUserId = '')";

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
		entityCache.putResult(
			UserInterestModelImpl.ENTITY_CACHE_ENABLED, UserInterestImpl.class,
			userInterest.getPrimaryKey(), userInterest);

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
					UserInterestImpl.class, userInterest.getPrimaryKey()) ==
						null) {

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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserInterest userInterest) {
		entityCache.removeResult(
			UserInterestModelImpl.ENTITY_CACHE_ENABLED, UserInterestImpl.class,
			userInterest.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<UserInterest> userInterests) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserInterest userInterest : userInterests) {
			entityCache.removeResult(
				UserInterestModelImpl.ENTITY_CACHE_ENABLED,
				UserInterestImpl.class, userInterest.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				UserInterestModelImpl.ENTITY_CACHE_ENABLED,
				UserInterestImpl.class, primaryKey);
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

			UserInterest userInterest = (UserInterest)session.get(
				UserInterestImpl.class, primaryKey);

			if (userInterest == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserInterestException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(userInterest);
		}
		catch (NoSuchUserInterestException noSuchEntityException) {
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
	protected UserInterest removeImpl(UserInterest userInterest) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userInterest)) {
				userInterest = (UserInterest)session.get(
					UserInterestImpl.class, userInterest.getPrimaryKeyObj());
			}

			if (userInterest != null) {
				session.delete(userInterest);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
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
		boolean isNew = userInterest.isNew();

		if (!(userInterest instanceof UserInterestModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(userInterest.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					userInterest);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in userInterest proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom UserInterest implementation " +
					userInterest.getClass());
		}

		UserInterestModelImpl userInterestModelImpl =
			(UserInterestModelImpl)userInterest;

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
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!UserInterestModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				userInterestModelImpl.getInterestId()
			};

			finderCache.removeResult(_finderPathCountByInterestId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByInterestId, args);

			args = new Object[] {userInterestModelImpl.getPublikUserId()};

			finderCache.removeResult(_finderPathCountByPublikUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPublikUserId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((userInterestModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByInterestId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					userInterestModelImpl.getOriginalInterestId()
				};

				finderCache.removeResult(_finderPathCountByInterestId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByInterestId, args);

				args = new Object[] {userInterestModelImpl.getInterestId()};

				finderCache.removeResult(_finderPathCountByInterestId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByInterestId, args);
			}

			if ((userInterestModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPublikUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					userInterestModelImpl.getOriginalPublikUserId()
				};

				finderCache.removeResult(_finderPathCountByPublikUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPublikUserId, args);

				args = new Object[] {userInterestModelImpl.getPublikUserId()};

				finderCache.removeResult(_finderPathCountByPublikUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPublikUserId, args);
			}
		}

		entityCache.putResult(
			UserInterestModelImpl.ENTITY_CACHE_ENABLED, UserInterestImpl.class,
			userInterest.getPrimaryKey(), userInterest, false);

		userInterest.resetOriginalValues();

		return userInterest;
	}

	/**
	 * Returns the user interest with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
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

			throw new NoSuchUserInterestException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return userInterest;
	}

	/**
	 * Returns the user interest with the primary key or throws a <code>NoSuchUserInterestException</code> if it could not be found.
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
		Serializable serializable = entityCache.getResult(
			UserInterestModelImpl.ENTITY_CACHE_ENABLED, UserInterestImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		UserInterest userInterest = (UserInterest)serializable;

		if (userInterest == null) {
			Session session = null;

			try {
				session = openSession();

				userInterest = (UserInterest)session.get(
					UserInterestImpl.class, primaryKey);

				if (userInterest != null) {
					cacheResult(userInterest);
				}
				else {
					entityCache.putResult(
						UserInterestModelImpl.ENTITY_CACHE_ENABLED,
						UserInterestImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					UserInterestModelImpl.ENTITY_CACHE_ENABLED,
					UserInterestImpl.class, primaryKey);

				throw processException(exception);
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

		Map<Serializable, UserInterest> map =
			new HashMap<Serializable, UserInterest>();

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserInterestModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserInterestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user interests
	 */
	@Override
	public List<UserInterest> findAll(
		int start, int end, OrderByComparator<UserInterest> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user interests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserInterestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user interests
	 * @param end the upper bound of the range of user interests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user interests
	 */
	@Override
	public List<UserInterest> findAll(
		int start, int end, OrderByComparator<UserInterest> orderByComparator,
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

		List<UserInterest> list = null;

		if (useFinderCache) {
			list = (List<UserInterest>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_USERINTEREST);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_USERINTEREST;

				sql = sql.concat(UserInterestModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UserInterest>)QueryUtil.list(
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
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_USERINTEREST);

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

	@Override
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UserInterestModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user interest persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, UserInterestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, UserInterestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByInterestId = new FinderPath(
			UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, UserInterestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByInterestId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByInterestId = new FinderPath(
			UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, UserInterestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByInterestId",
			new String[] {Long.class.getName()},
			UserInterestModelImpl.INTERESTID_COLUMN_BITMASK);

		_finderPathCountByInterestId = new FinderPath(
			UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByInterestId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByPublikUserId = new FinderPath(
			UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, UserInterestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPublikUserId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPublikUserId = new FinderPath(
			UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, UserInterestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikUserId",
			new String[] {String.class.getName()},
			UserInterestModelImpl.PUBLIKUSERID_COLUMN_BITMASK);

		_finderPathCountByPublikUserId = new FinderPath(
			UserInterestModelImpl.ENTITY_CACHE_ENABLED,
			UserInterestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikUserId",
			new String[] {String.class.getName()});
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

	private static final String _SQL_SELECT_USERINTEREST =
		"SELECT userInterest FROM UserInterest userInterest";

	private static final String _SQL_SELECT_USERINTEREST_WHERE =
		"SELECT userInterest FROM UserInterest userInterest WHERE ";

	private static final String _SQL_COUNT_USERINTEREST =
		"SELECT COUNT(userInterest) FROM UserInterest userInterest";

	private static final String _SQL_COUNT_USERINTEREST_WHERE =
		"SELECT COUNT(userInterest) FROM UserInterest userInterest WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "userInterest.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UserInterest exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No UserInterest exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		UserInterestPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"interestId", "publikUserId"});

}