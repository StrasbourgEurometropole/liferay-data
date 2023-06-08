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

package eu.strasbourg.service.notification.service.persistence.impl;

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

import eu.strasbourg.service.notification.exception.NoSuchUserNotificationStatusException;
import eu.strasbourg.service.notification.model.UserNotificationStatus;
import eu.strasbourg.service.notification.model.impl.UserNotificationStatusImpl;
import eu.strasbourg.service.notification.model.impl.UserNotificationStatusModelImpl;
import eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK;
import eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the user notification status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @generated
 */
public class UserNotificationStatusPersistenceImpl
	extends BasePersistenceImpl<UserNotificationStatus>
	implements UserNotificationStatusPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UserNotificationStatusUtil</code> to access the user notification status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UserNotificationStatusImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByNotificationId;
	private FinderPath _finderPathWithoutPaginationFindByNotificationId;
	private FinderPath _finderPathCountByNotificationId;

	/**
	 * Returns all the user notification statuses where notificationId = &#63;.
	 *
	 * @param notificationId the notification ID
	 * @return the matching user notification statuses
	 */
	@Override
	public List<UserNotificationStatus> findByNotificationId(
		long notificationId) {

		return findByNotificationId(
			notificationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification statuses where notificationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserNotificationStatusModelImpl</code>.
	 * </p>
	 *
	 * @param notificationId the notification ID
	 * @param start the lower bound of the range of user notification statuses
	 * @param end the upper bound of the range of user notification statuses (not inclusive)
	 * @return the range of matching user notification statuses
	 */
	@Override
	public List<UserNotificationStatus> findByNotificationId(
		long notificationId, int start, int end) {

		return findByNotificationId(notificationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification statuses where notificationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserNotificationStatusModelImpl</code>.
	 * </p>
	 *
	 * @param notificationId the notification ID
	 * @param start the lower bound of the range of user notification statuses
	 * @param end the upper bound of the range of user notification statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user notification statuses
	 */
	@Override
	public List<UserNotificationStatus> findByNotificationId(
		long notificationId, int start, int end,
		OrderByComparator<UserNotificationStatus> orderByComparator) {

		return findByNotificationId(
			notificationId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user notification statuses where notificationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserNotificationStatusModelImpl</code>.
	 * </p>
	 *
	 * @param notificationId the notification ID
	 * @param start the lower bound of the range of user notification statuses
	 * @param end the upper bound of the range of user notification statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user notification statuses
	 */
	@Override
	public List<UserNotificationStatus> findByNotificationId(
		long notificationId, int start, int end,
		OrderByComparator<UserNotificationStatus> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByNotificationId;
				finderArgs = new Object[] {notificationId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByNotificationId;
			finderArgs = new Object[] {
				notificationId, start, end, orderByComparator
			};
		}

		List<UserNotificationStatus> list = null;

		if (useFinderCache) {
			list = (List<UserNotificationStatus>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserNotificationStatus userNotificationStatus : list) {
					if (notificationId !=
							userNotificationStatus.getNotificationId()) {

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

			sb.append(_SQL_SELECT_USERNOTIFICATIONSTATUS_WHERE);

			sb.append(_FINDER_COLUMN_NOTIFICATIONID_NOTIFICATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(UserNotificationStatusModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(notificationId);

				list = (List<UserNotificationStatus>)QueryUtil.list(
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
	 * Returns the first user notification status in the ordered set where notificationId = &#63;.
	 *
	 * @param notificationId the notification ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification status
	 * @throws NoSuchUserNotificationStatusException if a matching user notification status could not be found
	 */
	@Override
	public UserNotificationStatus findByNotificationId_First(
			long notificationId,
			OrderByComparator<UserNotificationStatus> orderByComparator)
		throws NoSuchUserNotificationStatusException {

		UserNotificationStatus userNotificationStatus =
			fetchByNotificationId_First(notificationId, orderByComparator);

		if (userNotificationStatus != null) {
			return userNotificationStatus;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("notificationId=");
		sb.append(notificationId);

		sb.append("}");

		throw new NoSuchUserNotificationStatusException(sb.toString());
	}

	/**
	 * Returns the first user notification status in the ordered set where notificationId = &#63;.
	 *
	 * @param notificationId the notification ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification status, or <code>null</code> if a matching user notification status could not be found
	 */
	@Override
	public UserNotificationStatus fetchByNotificationId_First(
		long notificationId,
		OrderByComparator<UserNotificationStatus> orderByComparator) {

		List<UserNotificationStatus> list = findByNotificationId(
			notificationId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user notification status in the ordered set where notificationId = &#63;.
	 *
	 * @param notificationId the notification ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification status
	 * @throws NoSuchUserNotificationStatusException if a matching user notification status could not be found
	 */
	@Override
	public UserNotificationStatus findByNotificationId_Last(
			long notificationId,
			OrderByComparator<UserNotificationStatus> orderByComparator)
		throws NoSuchUserNotificationStatusException {

		UserNotificationStatus userNotificationStatus =
			fetchByNotificationId_Last(notificationId, orderByComparator);

		if (userNotificationStatus != null) {
			return userNotificationStatus;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("notificationId=");
		sb.append(notificationId);

		sb.append("}");

		throw new NoSuchUserNotificationStatusException(sb.toString());
	}

	/**
	 * Returns the last user notification status in the ordered set where notificationId = &#63;.
	 *
	 * @param notificationId the notification ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification status, or <code>null</code> if a matching user notification status could not be found
	 */
	@Override
	public UserNotificationStatus fetchByNotificationId_Last(
		long notificationId,
		OrderByComparator<UserNotificationStatus> orderByComparator) {

		int count = countByNotificationId(notificationId);

		if (count == 0) {
			return null;
		}

		List<UserNotificationStatus> list = findByNotificationId(
			notificationId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user notification statuses before and after the current user notification status in the ordered set where notificationId = &#63;.
	 *
	 * @param userNotificationStatusPK the primary key of the current user notification status
	 * @param notificationId the notification ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user notification status
	 * @throws NoSuchUserNotificationStatusException if a user notification status with the primary key could not be found
	 */
	@Override
	public UserNotificationStatus[] findByNotificationId_PrevAndNext(
			UserNotificationStatusPK userNotificationStatusPK,
			long notificationId,
			OrderByComparator<UserNotificationStatus> orderByComparator)
		throws NoSuchUserNotificationStatusException {

		UserNotificationStatus userNotificationStatus = findByPrimaryKey(
			userNotificationStatusPK);

		Session session = null;

		try {
			session = openSession();

			UserNotificationStatus[] array = new UserNotificationStatusImpl[3];

			array[0] = getByNotificationId_PrevAndNext(
				session, userNotificationStatus, notificationId,
				orderByComparator, true);

			array[1] = userNotificationStatus;

			array[2] = getByNotificationId_PrevAndNext(
				session, userNotificationStatus, notificationId,
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

	protected UserNotificationStatus getByNotificationId_PrevAndNext(
		Session session, UserNotificationStatus userNotificationStatus,
		long notificationId,
		OrderByComparator<UserNotificationStatus> orderByComparator,
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

		sb.append(_SQL_SELECT_USERNOTIFICATIONSTATUS_WHERE);

		sb.append(_FINDER_COLUMN_NOTIFICATIONID_NOTIFICATIONID_2);

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
			sb.append(UserNotificationStatusModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(notificationId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						userNotificationStatus)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserNotificationStatus> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user notification statuses where notificationId = &#63; from the database.
	 *
	 * @param notificationId the notification ID
	 */
	@Override
	public void removeByNotificationId(long notificationId) {
		for (UserNotificationStatus userNotificationStatus :
				findByNotificationId(
					notificationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(userNotificationStatus);
		}
	}

	/**
	 * Returns the number of user notification statuses where notificationId = &#63;.
	 *
	 * @param notificationId the notification ID
	 * @return the number of matching user notification statuses
	 */
	@Override
	public int countByNotificationId(long notificationId) {
		FinderPath finderPath = _finderPathCountByNotificationId;

		Object[] finderArgs = new Object[] {notificationId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USERNOTIFICATIONSTATUS_WHERE);

			sb.append(_FINDER_COLUMN_NOTIFICATIONID_NOTIFICATIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(notificationId);

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

	private static final String _FINDER_COLUMN_NOTIFICATIONID_NOTIFICATIONID_2 =
		"userNotificationStatus.id.notificationId = ?";

	private FinderPath _finderPathWithPaginationFindByPublikUserId;
	private FinderPath _finderPathWithoutPaginationFindByPublikUserId;
	private FinderPath _finderPathCountByPublikUserId;

	/**
	 * Returns all the user notification statuses where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching user notification statuses
	 */
	@Override
	public List<UserNotificationStatus> findByPublikUserId(
		String publikUserId) {

		return findByPublikUserId(
			publikUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification statuses where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserNotificationStatusModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user notification statuses
	 * @param end the upper bound of the range of user notification statuses (not inclusive)
	 * @return the range of matching user notification statuses
	 */
	@Override
	public List<UserNotificationStatus> findByPublikUserId(
		String publikUserId, int start, int end) {

		return findByPublikUserId(publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification statuses where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserNotificationStatusModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user notification statuses
	 * @param end the upper bound of the range of user notification statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user notification statuses
	 */
	@Override
	public List<UserNotificationStatus> findByPublikUserId(
		String publikUserId, int start, int end,
		OrderByComparator<UserNotificationStatus> orderByComparator) {

		return findByPublikUserId(
			publikUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user notification statuses where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserNotificationStatusModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user notification statuses
	 * @param end the upper bound of the range of user notification statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user notification statuses
	 */
	@Override
	public List<UserNotificationStatus> findByPublikUserId(
		String publikUserId, int start, int end,
		OrderByComparator<UserNotificationStatus> orderByComparator,
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

		List<UserNotificationStatus> list = null;

		if (useFinderCache) {
			list = (List<UserNotificationStatus>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserNotificationStatus userNotificationStatus : list) {
					if (!publikUserId.equals(
							userNotificationStatus.getPublikUserId())) {

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

			sb.append(_SQL_SELECT_USERNOTIFICATIONSTATUS_WHERE);

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
				sb.append(UserNotificationStatusModelImpl.ORDER_BY_JPQL);
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

				list = (List<UserNotificationStatus>)QueryUtil.list(
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
	 * Returns the first user notification status in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification status
	 * @throws NoSuchUserNotificationStatusException if a matching user notification status could not be found
	 */
	@Override
	public UserNotificationStatus findByPublikUserId_First(
			String publikUserId,
			OrderByComparator<UserNotificationStatus> orderByComparator)
		throws NoSuchUserNotificationStatusException {

		UserNotificationStatus userNotificationStatus =
			fetchByPublikUserId_First(publikUserId, orderByComparator);

		if (userNotificationStatus != null) {
			return userNotificationStatus;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publikUserId=");
		sb.append(publikUserId);

		sb.append("}");

		throw new NoSuchUserNotificationStatusException(sb.toString());
	}

	/**
	 * Returns the first user notification status in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification status, or <code>null</code> if a matching user notification status could not be found
	 */
	@Override
	public UserNotificationStatus fetchByPublikUserId_First(
		String publikUserId,
		OrderByComparator<UserNotificationStatus> orderByComparator) {

		List<UserNotificationStatus> list = findByPublikUserId(
			publikUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user notification status in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification status
	 * @throws NoSuchUserNotificationStatusException if a matching user notification status could not be found
	 */
	@Override
	public UserNotificationStatus findByPublikUserId_Last(
			String publikUserId,
			OrderByComparator<UserNotificationStatus> orderByComparator)
		throws NoSuchUserNotificationStatusException {

		UserNotificationStatus userNotificationStatus =
			fetchByPublikUserId_Last(publikUserId, orderByComparator);

		if (userNotificationStatus != null) {
			return userNotificationStatus;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publikUserId=");
		sb.append(publikUserId);

		sb.append("}");

		throw new NoSuchUserNotificationStatusException(sb.toString());
	}

	/**
	 * Returns the last user notification status in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification status, or <code>null</code> if a matching user notification status could not be found
	 */
	@Override
	public UserNotificationStatus fetchByPublikUserId_Last(
		String publikUserId,
		OrderByComparator<UserNotificationStatus> orderByComparator) {

		int count = countByPublikUserId(publikUserId);

		if (count == 0) {
			return null;
		}

		List<UserNotificationStatus> list = findByPublikUserId(
			publikUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user notification statuses before and after the current user notification status in the ordered set where publikUserId = &#63;.
	 *
	 * @param userNotificationStatusPK the primary key of the current user notification status
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user notification status
	 * @throws NoSuchUserNotificationStatusException if a user notification status with the primary key could not be found
	 */
	@Override
	public UserNotificationStatus[] findByPublikUserId_PrevAndNext(
			UserNotificationStatusPK userNotificationStatusPK,
			String publikUserId,
			OrderByComparator<UserNotificationStatus> orderByComparator)
		throws NoSuchUserNotificationStatusException {

		publikUserId = Objects.toString(publikUserId, "");

		UserNotificationStatus userNotificationStatus = findByPrimaryKey(
			userNotificationStatusPK);

		Session session = null;

		try {
			session = openSession();

			UserNotificationStatus[] array = new UserNotificationStatusImpl[3];

			array[0] = getByPublikUserId_PrevAndNext(
				session, userNotificationStatus, publikUserId,
				orderByComparator, true);

			array[1] = userNotificationStatus;

			array[2] = getByPublikUserId_PrevAndNext(
				session, userNotificationStatus, publikUserId,
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

	protected UserNotificationStatus getByPublikUserId_PrevAndNext(
		Session session, UserNotificationStatus userNotificationStatus,
		String publikUserId,
		OrderByComparator<UserNotificationStatus> orderByComparator,
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

		sb.append(_SQL_SELECT_USERNOTIFICATIONSTATUS_WHERE);

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
			sb.append(UserNotificationStatusModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(
						userNotificationStatus)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserNotificationStatus> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user notification statuses where publikUserId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 */
	@Override
	public void removeByPublikUserId(String publikUserId) {
		for (UserNotificationStatus userNotificationStatus :
				findByPublikUserId(
					publikUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(userNotificationStatus);
		}
	}

	/**
	 * Returns the number of user notification statuses where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the number of matching user notification statuses
	 */
	@Override
	public int countByPublikUserId(String publikUserId) {
		publikUserId = Objects.toString(publikUserId, "");

		FinderPath finderPath = _finderPathCountByPublikUserId;

		Object[] finderArgs = new Object[] {publikUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USERNOTIFICATIONSTATUS_WHERE);

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
		"userNotificationStatus.id.publikUserId = ?";

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3 =
		"(userNotificationStatus.id.publikUserId IS NULL OR userNotificationStatus.id.publikUserId = '')";

	public UserNotificationStatusPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("read", "read_");

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

		setModelClass(UserNotificationStatus.class);
	}

	/**
	 * Caches the user notification status in the entity cache if it is enabled.
	 *
	 * @param userNotificationStatus the user notification status
	 */
	@Override
	public void cacheResult(UserNotificationStatus userNotificationStatus) {
		entityCache.putResult(
			UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationStatusImpl.class,
			userNotificationStatus.getPrimaryKey(), userNotificationStatus);

		userNotificationStatus.resetOriginalValues();
	}

	/**
	 * Caches the user notification statuses in the entity cache if it is enabled.
	 *
	 * @param userNotificationStatuses the user notification statuses
	 */
	@Override
	public void cacheResult(
		List<UserNotificationStatus> userNotificationStatuses) {

		for (UserNotificationStatus userNotificationStatus :
				userNotificationStatuses) {

			if (entityCache.getResult(
					UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
					UserNotificationStatusImpl.class,
					userNotificationStatus.getPrimaryKey()) == null) {

				cacheResult(userNotificationStatus);
			}
			else {
				userNotificationStatus.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all user notification statuses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserNotificationStatusImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the user notification status.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserNotificationStatus userNotificationStatus) {
		entityCache.removeResult(
			UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationStatusImpl.class,
			userNotificationStatus.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<UserNotificationStatus> userNotificationStatuses) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserNotificationStatus userNotificationStatus :
				userNotificationStatuses) {

			entityCache.removeResult(
				UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
				UserNotificationStatusImpl.class,
				userNotificationStatus.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
				UserNotificationStatusImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new user notification status with the primary key. Does not add the user notification status to the database.
	 *
	 * @param userNotificationStatusPK the primary key for the new user notification status
	 * @return the new user notification status
	 */
	@Override
	public UserNotificationStatus create(
		UserNotificationStatusPK userNotificationStatusPK) {

		UserNotificationStatus userNotificationStatus =
			new UserNotificationStatusImpl();

		userNotificationStatus.setNew(true);
		userNotificationStatus.setPrimaryKey(userNotificationStatusPK);

		return userNotificationStatus;
	}

	/**
	 * Removes the user notification status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationStatusPK the primary key of the user notification status
	 * @return the user notification status that was removed
	 * @throws NoSuchUserNotificationStatusException if a user notification status with the primary key could not be found
	 */
	@Override
	public UserNotificationStatus remove(
			UserNotificationStatusPK userNotificationStatusPK)
		throws NoSuchUserNotificationStatusException {

		return remove((Serializable)userNotificationStatusPK);
	}

	/**
	 * Removes the user notification status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user notification status
	 * @return the user notification status that was removed
	 * @throws NoSuchUserNotificationStatusException if a user notification status with the primary key could not be found
	 */
	@Override
	public UserNotificationStatus remove(Serializable primaryKey)
		throws NoSuchUserNotificationStatusException {

		Session session = null;

		try {
			session = openSession();

			UserNotificationStatus userNotificationStatus =
				(UserNotificationStatus)session.get(
					UserNotificationStatusImpl.class, primaryKey);

			if (userNotificationStatus == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserNotificationStatusException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(userNotificationStatus);
		}
		catch (NoSuchUserNotificationStatusException noSuchEntityException) {
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
	protected UserNotificationStatus removeImpl(
		UserNotificationStatus userNotificationStatus) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userNotificationStatus)) {
				userNotificationStatus = (UserNotificationStatus)session.get(
					UserNotificationStatusImpl.class,
					userNotificationStatus.getPrimaryKeyObj());
			}

			if (userNotificationStatus != null) {
				session.delete(userNotificationStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (userNotificationStatus != null) {
			clearCache(userNotificationStatus);
		}

		return userNotificationStatus;
	}

	@Override
	public UserNotificationStatus updateImpl(
		UserNotificationStatus userNotificationStatus) {

		boolean isNew = userNotificationStatus.isNew();

		if (!(userNotificationStatus instanceof
				UserNotificationStatusModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(userNotificationStatus.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					userNotificationStatus);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in userNotificationStatus proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom UserNotificationStatus implementation " +
					userNotificationStatus.getClass());
		}

		UserNotificationStatusModelImpl userNotificationStatusModelImpl =
			(UserNotificationStatusModelImpl)userNotificationStatus;

		Session session = null;

		try {
			session = openSession();

			if (userNotificationStatus.isNew()) {
				session.save(userNotificationStatus);

				userNotificationStatus.setNew(false);
			}
			else {
				userNotificationStatus = (UserNotificationStatus)session.merge(
					userNotificationStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!UserNotificationStatusModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				userNotificationStatusModelImpl.getNotificationId()
			};

			finderCache.removeResult(_finderPathCountByNotificationId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByNotificationId, args);

			args = new Object[] {
				userNotificationStatusModelImpl.getPublikUserId()
			};

			finderCache.removeResult(_finderPathCountByPublikUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPublikUserId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((userNotificationStatusModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByNotificationId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					userNotificationStatusModelImpl.getOriginalNotificationId()
				};

				finderCache.removeResult(
					_finderPathCountByNotificationId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByNotificationId, args);

				args = new Object[] {
					userNotificationStatusModelImpl.getNotificationId()
				};

				finderCache.removeResult(
					_finderPathCountByNotificationId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByNotificationId, args);
			}

			if ((userNotificationStatusModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPublikUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					userNotificationStatusModelImpl.getOriginalPublikUserId()
				};

				finderCache.removeResult(_finderPathCountByPublikUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPublikUserId, args);

				args = new Object[] {
					userNotificationStatusModelImpl.getPublikUserId()
				};

				finderCache.removeResult(_finderPathCountByPublikUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPublikUserId, args);
			}
		}

		entityCache.putResult(
			UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationStatusImpl.class,
			userNotificationStatus.getPrimaryKey(), userNotificationStatus,
			false);

		userNotificationStatus.resetOriginalValues();

		return userNotificationStatus;
	}

	/**
	 * Returns the user notification status with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user notification status
	 * @return the user notification status
	 * @throws NoSuchUserNotificationStatusException if a user notification status with the primary key could not be found
	 */
	@Override
	public UserNotificationStatus findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserNotificationStatusException {

		UserNotificationStatus userNotificationStatus = fetchByPrimaryKey(
			primaryKey);

		if (userNotificationStatus == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserNotificationStatusException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return userNotificationStatus;
	}

	/**
	 * Returns the user notification status with the primary key or throws a <code>NoSuchUserNotificationStatusException</code> if it could not be found.
	 *
	 * @param userNotificationStatusPK the primary key of the user notification status
	 * @return the user notification status
	 * @throws NoSuchUserNotificationStatusException if a user notification status with the primary key could not be found
	 */
	@Override
	public UserNotificationStatus findByPrimaryKey(
			UserNotificationStatusPK userNotificationStatusPK)
		throws NoSuchUserNotificationStatusException {

		return findByPrimaryKey((Serializable)userNotificationStatusPK);
	}

	/**
	 * Returns the user notification status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user notification status
	 * @return the user notification status, or <code>null</code> if a user notification status with the primary key could not be found
	 */
	@Override
	public UserNotificationStatus fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationStatusImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		UserNotificationStatus userNotificationStatus =
			(UserNotificationStatus)serializable;

		if (userNotificationStatus == null) {
			Session session = null;

			try {
				session = openSession();

				userNotificationStatus = (UserNotificationStatus)session.get(
					UserNotificationStatusImpl.class, primaryKey);

				if (userNotificationStatus != null) {
					cacheResult(userNotificationStatus);
				}
				else {
					entityCache.putResult(
						UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
						UserNotificationStatusImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
					UserNotificationStatusImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return userNotificationStatus;
	}

	/**
	 * Returns the user notification status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userNotificationStatusPK the primary key of the user notification status
	 * @return the user notification status, or <code>null</code> if a user notification status with the primary key could not be found
	 */
	@Override
	public UserNotificationStatus fetchByPrimaryKey(
		UserNotificationStatusPK userNotificationStatusPK) {

		return fetchByPrimaryKey((Serializable)userNotificationStatusPK);
	}

	@Override
	public Map<Serializable, UserNotificationStatus> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, UserNotificationStatus> map =
			new HashMap<Serializable, UserNotificationStatus>();

		for (Serializable primaryKey : primaryKeys) {
			UserNotificationStatus userNotificationStatus = fetchByPrimaryKey(
				primaryKey);

			if (userNotificationStatus != null) {
				map.put(primaryKey, userNotificationStatus);
			}
		}

		return map;
	}

	/**
	 * Returns all the user notification statuses.
	 *
	 * @return the user notification statuses
	 */
	@Override
	public List<UserNotificationStatus> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserNotificationStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification statuses
	 * @param end the upper bound of the range of user notification statuses (not inclusive)
	 * @return the range of user notification statuses
	 */
	@Override
	public List<UserNotificationStatus> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserNotificationStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification statuses
	 * @param end the upper bound of the range of user notification statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user notification statuses
	 */
	@Override
	public List<UserNotificationStatus> findAll(
		int start, int end,
		OrderByComparator<UserNotificationStatus> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user notification statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserNotificationStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification statuses
	 * @param end the upper bound of the range of user notification statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user notification statuses
	 */
	@Override
	public List<UserNotificationStatus> findAll(
		int start, int end,
		OrderByComparator<UserNotificationStatus> orderByComparator,
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

		List<UserNotificationStatus> list = null;

		if (useFinderCache) {
			list = (List<UserNotificationStatus>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_USERNOTIFICATIONSTATUS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_USERNOTIFICATIONSTATUS;

				sql = sql.concat(UserNotificationStatusModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UserNotificationStatus>)QueryUtil.list(
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
	 * Removes all the user notification statuses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserNotificationStatus userNotificationStatus : findAll()) {
			remove(userNotificationStatus);
		}
	}

	/**
	 * Returns the number of user notification statuses.
	 *
	 * @return the number of user notification statuses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_USERNOTIFICATIONSTATUS);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UserNotificationStatusModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user notification status persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationStatusModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationStatusModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByNotificationId = new FinderPath(
			UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationStatusModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByNotificationId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByNotificationId = new FinderPath(
			UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationStatusModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByNotificationId",
			new String[] {Long.class.getName()},
			UserNotificationStatusModelImpl.NOTIFICATIONID_COLUMN_BITMASK);

		_finderPathCountByNotificationId = new FinderPath(
			UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByNotificationId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByPublikUserId = new FinderPath(
			UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationStatusModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPublikUserId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPublikUserId = new FinderPath(
			UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationStatusModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikUserId",
			new String[] {String.class.getName()},
			UserNotificationStatusModelImpl.PUBLIKUSERID_COLUMN_BITMASK);

		_finderPathCountByPublikUserId = new FinderPath(
			UserNotificationStatusModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikUserId",
			new String[] {String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(UserNotificationStatusImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_USERNOTIFICATIONSTATUS =
		"SELECT userNotificationStatus FROM UserNotificationStatus userNotificationStatus";

	private static final String _SQL_SELECT_USERNOTIFICATIONSTATUS_WHERE =
		"SELECT userNotificationStatus FROM UserNotificationStatus userNotificationStatus WHERE ";

	private static final String _SQL_COUNT_USERNOTIFICATIONSTATUS =
		"SELECT COUNT(userNotificationStatus) FROM UserNotificationStatus userNotificationStatus";

	private static final String _SQL_COUNT_USERNOTIFICATIONSTATUS_WHERE =
		"SELECT COUNT(userNotificationStatus) FROM UserNotificationStatus userNotificationStatus WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"userNotificationStatus.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UserNotificationStatus exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No UserNotificationStatus exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		UserNotificationStatusPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"read"});
	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"notificationId", "publikUserId"});

}