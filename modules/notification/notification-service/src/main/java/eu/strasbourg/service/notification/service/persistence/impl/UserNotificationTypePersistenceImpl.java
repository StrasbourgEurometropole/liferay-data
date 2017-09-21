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

import eu.strasbourg.service.notification.exception.NoSuchUserNotificationTypeException;
import eu.strasbourg.service.notification.model.UserNotificationType;
import eu.strasbourg.service.notification.model.impl.UserNotificationTypeImpl;
import eu.strasbourg.service.notification.model.impl.UserNotificationTypeModelImpl;
import eu.strasbourg.service.notification.service.persistence.UserNotificationTypePK;
import eu.strasbourg.service.notification.service.persistence.UserNotificationTypePersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the user notification type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see UserNotificationTypePersistence
 * @see eu.strasbourg.service.notification.service.persistence.UserNotificationTypeUtil
 * @generated
 */
@ProviderType
public class UserNotificationTypePersistenceImpl extends BasePersistenceImpl<UserNotificationType>
	implements UserNotificationTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserNotificationTypeUtil} to access the user notification type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UserNotificationTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationTypeModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationTypeModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationTypeModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPublikUserId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationTypeModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikUserId",
			new String[] { String.class.getName() },
			UserNotificationTypeModelImpl.PUBLIKUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKUSERID = new FinderPath(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikUserId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the user notification types where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching user notification types
	 */
	@Override
	public List<UserNotificationType> findByPublikUserId(String publikUserId) {
		return findByPublikUserId(publikUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification types where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user notification types
	 * @param end the upper bound of the range of user notification types (not inclusive)
	 * @return the range of matching user notification types
	 */
	@Override
	public List<UserNotificationType> findByPublikUserId(String publikUserId,
		int start, int end) {
		return findByPublikUserId(publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification types where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user notification types
	 * @param end the upper bound of the range of user notification types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user notification types
	 */
	@Override
	public List<UserNotificationType> findByPublikUserId(String publikUserId,
		int start, int end,
		OrderByComparator<UserNotificationType> orderByComparator) {
		return findByPublikUserId(publikUserId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the user notification types where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user notification types
	 * @param end the upper bound of the range of user notification types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching user notification types
	 */
	@Override
	public List<UserNotificationType> findByPublikUserId(String publikUserId,
		int start, int end,
		OrderByComparator<UserNotificationType> orderByComparator,
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

		List<UserNotificationType> list = null;

		if (retrieveFromCache) {
			list = (List<UserNotificationType>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserNotificationType userNotificationType : list) {
					if (!Objects.equals(publikUserId,
								userNotificationType.getPublikUserId())) {
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

			query.append(_SQL_SELECT_USERNOTIFICATIONTYPE_WHERE);

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
				query.append(UserNotificationTypeModelImpl.ORDER_BY_JPQL);
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
					list = (List<UserNotificationType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserNotificationType>)QueryUtil.list(q,
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
	 * Returns the first user notification type in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification type
	 * @throws NoSuchUserNotificationTypeException if a matching user notification type could not be found
	 */
	@Override
	public UserNotificationType findByPublikUserId_First(String publikUserId,
		OrderByComparator<UserNotificationType> orderByComparator)
		throws NoSuchUserNotificationTypeException {
		UserNotificationType userNotificationType = fetchByPublikUserId_First(publikUserId,
				orderByComparator);

		if (userNotificationType != null) {
			return userNotificationType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationTypeException(msg.toString());
	}

	/**
	 * Returns the first user notification type in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification type, or <code>null</code> if a matching user notification type could not be found
	 */
	@Override
	public UserNotificationType fetchByPublikUserId_First(String publikUserId,
		OrderByComparator<UserNotificationType> orderByComparator) {
		List<UserNotificationType> list = findByPublikUserId(publikUserId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user notification type in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification type
	 * @throws NoSuchUserNotificationTypeException if a matching user notification type could not be found
	 */
	@Override
	public UserNotificationType findByPublikUserId_Last(String publikUserId,
		OrderByComparator<UserNotificationType> orderByComparator)
		throws NoSuchUserNotificationTypeException {
		UserNotificationType userNotificationType = fetchByPublikUserId_Last(publikUserId,
				orderByComparator);

		if (userNotificationType != null) {
			return userNotificationType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationTypeException(msg.toString());
	}

	/**
	 * Returns the last user notification type in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification type, or <code>null</code> if a matching user notification type could not be found
	 */
	@Override
	public UserNotificationType fetchByPublikUserId_Last(String publikUserId,
		OrderByComparator<UserNotificationType> orderByComparator) {
		int count = countByPublikUserId(publikUserId);

		if (count == 0) {
			return null;
		}

		List<UserNotificationType> list = findByPublikUserId(publikUserId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user notification types before and after the current user notification type in the ordered set where publikUserId = &#63;.
	 *
	 * @param userNotificationTypePK the primary key of the current user notification type
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user notification type
	 * @throws NoSuchUserNotificationTypeException if a user notification type with the primary key could not be found
	 */
	@Override
	public UserNotificationType[] findByPublikUserId_PrevAndNext(
		UserNotificationTypePK userNotificationTypePK, String publikUserId,
		OrderByComparator<UserNotificationType> orderByComparator)
		throws NoSuchUserNotificationTypeException {
		UserNotificationType userNotificationType = findByPrimaryKey(userNotificationTypePK);

		Session session = null;

		try {
			session = openSession();

			UserNotificationType[] array = new UserNotificationTypeImpl[3];

			array[0] = getByPublikUserId_PrevAndNext(session,
					userNotificationType, publikUserId, orderByComparator, true);

			array[1] = userNotificationType;

			array[2] = getByPublikUserId_PrevAndNext(session,
					userNotificationType, publikUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserNotificationType getByPublikUserId_PrevAndNext(
		Session session, UserNotificationType userNotificationType,
		String publikUserId,
		OrderByComparator<UserNotificationType> orderByComparator,
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

		query.append(_SQL_SELECT_USERNOTIFICATIONTYPE_WHERE);

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
			query.append(UserNotificationTypeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(userNotificationType);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserNotificationType> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user notification types where publikUserId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 */
	@Override
	public void removeByPublikUserId(String publikUserId) {
		for (UserNotificationType userNotificationType : findByPublikUserId(
				publikUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userNotificationType);
		}
	}

	/**
	 * Returns the number of user notification types where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the number of matching user notification types
	 */
	@Override
	public int countByPublikUserId(String publikUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKUSERID;

		Object[] finderArgs = new Object[] { publikUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERNOTIFICATIONTYPE_WHERE);

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

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1 = "userNotificationType.id.publikUserId IS NULL";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2 = "userNotificationType.id.publikUserId = ?";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3 = "(userNotificationType.id.publikUserId IS NULL OR userNotificationType.id.publikUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPEID = new FinderPath(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationTypeModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTypeId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID =
		new FinderPath(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationTypeModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTypeId",
			new String[] { Long.class.getName() },
			UserNotificationTypeModelImpl.TYPEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPEID = new FinderPath(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTypeId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the user notification types where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the matching user notification types
	 */
	@Override
	public List<UserNotificationType> findByTypeId(long typeId) {
		return findByTypeId(typeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification types where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of user notification types
	 * @param end the upper bound of the range of user notification types (not inclusive)
	 * @return the range of matching user notification types
	 */
	@Override
	public List<UserNotificationType> findByTypeId(long typeId, int start,
		int end) {
		return findByTypeId(typeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification types where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of user notification types
	 * @param end the upper bound of the range of user notification types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user notification types
	 */
	@Override
	public List<UserNotificationType> findByTypeId(long typeId, int start,
		int end, OrderByComparator<UserNotificationType> orderByComparator) {
		return findByTypeId(typeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user notification types where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of user notification types
	 * @param end the upper bound of the range of user notification types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching user notification types
	 */
	@Override
	public List<UserNotificationType> findByTypeId(long typeId, int start,
		int end, OrderByComparator<UserNotificationType> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID;
			finderArgs = new Object[] { typeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPEID;
			finderArgs = new Object[] { typeId, start, end, orderByComparator };
		}

		List<UserNotificationType> list = null;

		if (retrieveFromCache) {
			list = (List<UserNotificationType>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserNotificationType userNotificationType : list) {
					if ((typeId != userNotificationType.getTypeId())) {
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

			query.append(_SQL_SELECT_USERNOTIFICATIONTYPE_WHERE);

			query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserNotificationTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(typeId);

				if (!pagination) {
					list = (List<UserNotificationType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserNotificationType>)QueryUtil.list(q,
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
	 * Returns the first user notification type in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification type
	 * @throws NoSuchUserNotificationTypeException if a matching user notification type could not be found
	 */
	@Override
	public UserNotificationType findByTypeId_First(long typeId,
		OrderByComparator<UserNotificationType> orderByComparator)
		throws NoSuchUserNotificationTypeException {
		UserNotificationType userNotificationType = fetchByTypeId_First(typeId,
				orderByComparator);

		if (userNotificationType != null) {
			return userNotificationType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("typeId=");
		msg.append(typeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationTypeException(msg.toString());
	}

	/**
	 * Returns the first user notification type in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification type, or <code>null</code> if a matching user notification type could not be found
	 */
	@Override
	public UserNotificationType fetchByTypeId_First(long typeId,
		OrderByComparator<UserNotificationType> orderByComparator) {
		List<UserNotificationType> list = findByTypeId(typeId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user notification type in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification type
	 * @throws NoSuchUserNotificationTypeException if a matching user notification type could not be found
	 */
	@Override
	public UserNotificationType findByTypeId_Last(long typeId,
		OrderByComparator<UserNotificationType> orderByComparator)
		throws NoSuchUserNotificationTypeException {
		UserNotificationType userNotificationType = fetchByTypeId_Last(typeId,
				orderByComparator);

		if (userNotificationType != null) {
			return userNotificationType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("typeId=");
		msg.append(typeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationTypeException(msg.toString());
	}

	/**
	 * Returns the last user notification type in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification type, or <code>null</code> if a matching user notification type could not be found
	 */
	@Override
	public UserNotificationType fetchByTypeId_Last(long typeId,
		OrderByComparator<UserNotificationType> orderByComparator) {
		int count = countByTypeId(typeId);

		if (count == 0) {
			return null;
		}

		List<UserNotificationType> list = findByTypeId(typeId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user notification types before and after the current user notification type in the ordered set where typeId = &#63;.
	 *
	 * @param userNotificationTypePK the primary key of the current user notification type
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user notification type
	 * @throws NoSuchUserNotificationTypeException if a user notification type with the primary key could not be found
	 */
	@Override
	public UserNotificationType[] findByTypeId_PrevAndNext(
		UserNotificationTypePK userNotificationTypePK, long typeId,
		OrderByComparator<UserNotificationType> orderByComparator)
		throws NoSuchUserNotificationTypeException {
		UserNotificationType userNotificationType = findByPrimaryKey(userNotificationTypePK);

		Session session = null;

		try {
			session = openSession();

			UserNotificationType[] array = new UserNotificationTypeImpl[3];

			array[0] = getByTypeId_PrevAndNext(session, userNotificationType,
					typeId, orderByComparator, true);

			array[1] = userNotificationType;

			array[2] = getByTypeId_PrevAndNext(session, userNotificationType,
					typeId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserNotificationType getByTypeId_PrevAndNext(Session session,
		UserNotificationType userNotificationType, long typeId,
		OrderByComparator<UserNotificationType> orderByComparator,
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

		query.append(_SQL_SELECT_USERNOTIFICATIONTYPE_WHERE);

		query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

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
			query.append(UserNotificationTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(typeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userNotificationType);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserNotificationType> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user notification types where typeId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 */
	@Override
	public void removeByTypeId(long typeId) {
		for (UserNotificationType userNotificationType : findByTypeId(typeId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userNotificationType);
		}
	}

	/**
	 * Returns the number of user notification types where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the number of matching user notification types
	 */
	@Override
	public int countByTypeId(long typeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TYPEID;

		Object[] finderArgs = new Object[] { typeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERNOTIFICATIONTYPE_WHERE);

			query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_TYPEID_TYPEID_2 = "userNotificationType.id.typeId = ?";

	public UserNotificationTypePersistenceImpl() {
		setModelClass(UserNotificationType.class);
	}

	/**
	 * Caches the user notification type in the entity cache if it is enabled.
	 *
	 * @param userNotificationType the user notification type
	 */
	@Override
	public void cacheResult(UserNotificationType userNotificationType) {
		entityCache.putResult(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationTypeImpl.class,
			userNotificationType.getPrimaryKey(), userNotificationType);

		userNotificationType.resetOriginalValues();
	}

	/**
	 * Caches the user notification types in the entity cache if it is enabled.
	 *
	 * @param userNotificationTypes the user notification types
	 */
	@Override
	public void cacheResult(List<UserNotificationType> userNotificationTypes) {
		for (UserNotificationType userNotificationType : userNotificationTypes) {
			if (entityCache.getResult(
						UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
						UserNotificationTypeImpl.class,
						userNotificationType.getPrimaryKey()) == null) {
				cacheResult(userNotificationType);
			}
			else {
				userNotificationType.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all user notification types.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserNotificationTypeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the user notification type.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserNotificationType userNotificationType) {
		entityCache.removeResult(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationTypeImpl.class, userNotificationType.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<UserNotificationType> userNotificationTypes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserNotificationType userNotificationType : userNotificationTypes) {
			entityCache.removeResult(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
				UserNotificationTypeImpl.class,
				userNotificationType.getPrimaryKey());
		}
	}

	/**
	 * Creates a new user notification type with the primary key. Does not add the user notification type to the database.
	 *
	 * @param userNotificationTypePK the primary key for the new user notification type
	 * @return the new user notification type
	 */
	@Override
	public UserNotificationType create(
		UserNotificationTypePK userNotificationTypePK) {
		UserNotificationType userNotificationType = new UserNotificationTypeImpl();

		userNotificationType.setNew(true);
		userNotificationType.setPrimaryKey(userNotificationTypePK);

		return userNotificationType;
	}

	/**
	 * Removes the user notification type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationTypePK the primary key of the user notification type
	 * @return the user notification type that was removed
	 * @throws NoSuchUserNotificationTypeException if a user notification type with the primary key could not be found
	 */
	@Override
	public UserNotificationType remove(
		UserNotificationTypePK userNotificationTypePK)
		throws NoSuchUserNotificationTypeException {
		return remove((Serializable)userNotificationTypePK);
	}

	/**
	 * Removes the user notification type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user notification type
	 * @return the user notification type that was removed
	 * @throws NoSuchUserNotificationTypeException if a user notification type with the primary key could not be found
	 */
	@Override
	public UserNotificationType remove(Serializable primaryKey)
		throws NoSuchUserNotificationTypeException {
		Session session = null;

		try {
			session = openSession();

			UserNotificationType userNotificationType = (UserNotificationType)session.get(UserNotificationTypeImpl.class,
					primaryKey);

			if (userNotificationType == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserNotificationTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(userNotificationType);
		}
		catch (NoSuchUserNotificationTypeException nsee) {
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
	protected UserNotificationType removeImpl(
		UserNotificationType userNotificationType) {
		userNotificationType = toUnwrappedModel(userNotificationType);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userNotificationType)) {
				userNotificationType = (UserNotificationType)session.get(UserNotificationTypeImpl.class,
						userNotificationType.getPrimaryKeyObj());
			}

			if (userNotificationType != null) {
				session.delete(userNotificationType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (userNotificationType != null) {
			clearCache(userNotificationType);
		}

		return userNotificationType;
	}

	@Override
	public UserNotificationType updateImpl(
		UserNotificationType userNotificationType) {
		userNotificationType = toUnwrappedModel(userNotificationType);

		boolean isNew = userNotificationType.isNew();

		UserNotificationTypeModelImpl userNotificationTypeModelImpl = (UserNotificationTypeModelImpl)userNotificationType;

		Session session = null;

		try {
			session = openSession();

			if (userNotificationType.isNew()) {
				session.save(userNotificationType);

				userNotificationType.setNew(false);
			}
			else {
				userNotificationType = (UserNotificationType)session.merge(userNotificationType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!UserNotificationTypeModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					userNotificationTypeModelImpl.getPublikUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
				args);

			args = new Object[] { userNotificationTypeModelImpl.getTypeId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TYPEID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((userNotificationTypeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userNotificationTypeModelImpl.getOriginalPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);

				args = new Object[] {
						userNotificationTypeModelImpl.getPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);
			}

			if ((userNotificationTypeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userNotificationTypeModelImpl.getOriginalTypeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TYPEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID,
					args);

				args = new Object[] { userNotificationTypeModelImpl.getTypeId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TYPEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID,
					args);
			}
		}

		entityCache.putResult(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationTypeImpl.class,
			userNotificationType.getPrimaryKey(), userNotificationType, false);

		userNotificationType.resetOriginalValues();

		return userNotificationType;
	}

	protected UserNotificationType toUnwrappedModel(
		UserNotificationType userNotificationType) {
		if (userNotificationType instanceof UserNotificationTypeImpl) {
			return userNotificationType;
		}

		UserNotificationTypeImpl userNotificationTypeImpl = new UserNotificationTypeImpl();

		userNotificationTypeImpl.setNew(userNotificationType.isNew());
		userNotificationTypeImpl.setPrimaryKey(userNotificationType.getPrimaryKey());

		userNotificationTypeImpl.setPublikUserId(userNotificationType.getPublikUserId());
		userNotificationTypeImpl.setTypeId(userNotificationType.getTypeId());

		return userNotificationTypeImpl;
	}

	/**
	 * Returns the user notification type with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user notification type
	 * @return the user notification type
	 * @throws NoSuchUserNotificationTypeException if a user notification type with the primary key could not be found
	 */
	@Override
	public UserNotificationType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserNotificationTypeException {
		UserNotificationType userNotificationType = fetchByPrimaryKey(primaryKey);

		if (userNotificationType == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserNotificationTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return userNotificationType;
	}

	/**
	 * Returns the user notification type with the primary key or throws a {@link NoSuchUserNotificationTypeException} if it could not be found.
	 *
	 * @param userNotificationTypePK the primary key of the user notification type
	 * @return the user notification type
	 * @throws NoSuchUserNotificationTypeException if a user notification type with the primary key could not be found
	 */
	@Override
	public UserNotificationType findByPrimaryKey(
		UserNotificationTypePK userNotificationTypePK)
		throws NoSuchUserNotificationTypeException {
		return findByPrimaryKey((Serializable)userNotificationTypePK);
	}

	/**
	 * Returns the user notification type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user notification type
	 * @return the user notification type, or <code>null</code> if a user notification type with the primary key could not be found
	 */
	@Override
	public UserNotificationType fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
				UserNotificationTypeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		UserNotificationType userNotificationType = (UserNotificationType)serializable;

		if (userNotificationType == null) {
			Session session = null;

			try {
				session = openSession();

				userNotificationType = (UserNotificationType)session.get(UserNotificationTypeImpl.class,
						primaryKey);

				if (userNotificationType != null) {
					cacheResult(userNotificationType);
				}
				else {
					entityCache.putResult(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
						UserNotificationTypeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(UserNotificationTypeModelImpl.ENTITY_CACHE_ENABLED,
					UserNotificationTypeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return userNotificationType;
	}

	/**
	 * Returns the user notification type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userNotificationTypePK the primary key of the user notification type
	 * @return the user notification type, or <code>null</code> if a user notification type with the primary key could not be found
	 */
	@Override
	public UserNotificationType fetchByPrimaryKey(
		UserNotificationTypePK userNotificationTypePK) {
		return fetchByPrimaryKey((Serializable)userNotificationTypePK);
	}

	@Override
	public Map<Serializable, UserNotificationType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, UserNotificationType> map = new HashMap<Serializable, UserNotificationType>();

		for (Serializable primaryKey : primaryKeys) {
			UserNotificationType userNotificationType = fetchByPrimaryKey(primaryKey);

			if (userNotificationType != null) {
				map.put(primaryKey, userNotificationType);
			}
		}

		return map;
	}

	/**
	 * Returns all the user notification types.
	 *
	 * @return the user notification types
	 */
	@Override
	public List<UserNotificationType> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification types
	 * @param end the upper bound of the range of user notification types (not inclusive)
	 * @return the range of user notification types
	 */
	@Override
	public List<UserNotificationType> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification types
	 * @param end the upper bound of the range of user notification types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user notification types
	 */
	@Override
	public List<UserNotificationType> findAll(int start, int end,
		OrderByComparator<UserNotificationType> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user notification types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification types
	 * @param end the upper bound of the range of user notification types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of user notification types
	 */
	@Override
	public List<UserNotificationType> findAll(int start, int end,
		OrderByComparator<UserNotificationType> orderByComparator,
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

		List<UserNotificationType> list = null;

		if (retrieveFromCache) {
			list = (List<UserNotificationType>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_USERNOTIFICATIONTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERNOTIFICATIONTYPE;

				if (pagination) {
					sql = sql.concat(UserNotificationTypeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UserNotificationType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserNotificationType>)QueryUtil.list(q,
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
	 * Removes all the user notification types from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserNotificationType userNotificationType : findAll()) {
			remove(userNotificationType);
		}
	}

	/**
	 * Returns the number of user notification types.
	 *
	 * @return the number of user notification types
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERNOTIFICATIONTYPE);

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
		return UserNotificationTypeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user notification type persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(UserNotificationTypeImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_USERNOTIFICATIONTYPE = "SELECT userNotificationType FROM UserNotificationType userNotificationType";
	private static final String _SQL_SELECT_USERNOTIFICATIONTYPE_WHERE = "SELECT userNotificationType FROM UserNotificationType userNotificationType WHERE ";
	private static final String _SQL_COUNT_USERNOTIFICATIONTYPE = "SELECT COUNT(userNotificationType) FROM UserNotificationType userNotificationType";
	private static final String _SQL_COUNT_USERNOTIFICATIONTYPE_WHERE = "SELECT COUNT(userNotificationType) FROM UserNotificationType userNotificationType WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userNotificationType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserNotificationType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserNotificationType exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(UserNotificationTypePersistenceImpl.class);
}