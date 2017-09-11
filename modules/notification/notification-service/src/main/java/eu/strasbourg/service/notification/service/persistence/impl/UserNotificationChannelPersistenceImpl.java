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

import eu.strasbourg.service.notification.exception.NoSuchUserNotificationChannelException;
import eu.strasbourg.service.notification.model.UserNotificationChannel;
import eu.strasbourg.service.notification.model.impl.UserNotificationChannelImpl;
import eu.strasbourg.service.notification.model.impl.UserNotificationChannelModelImpl;
import eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK;
import eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the user notification channel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see UserNotificationChannelPersistence
 * @see eu.strasbourg.service.notification.service.persistence.UserNotificationChannelUtil
 * @generated
 */
@ProviderType
public class UserNotificationChannelPersistenceImpl extends BasePersistenceImpl<UserNotificationChannel>
	implements UserNotificationChannelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserNotificationChannelUtil} to access the user notification channel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UserNotificationChannelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationChannelModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationChannelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationChannelModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationChannelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationChannelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationChannelModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationChannelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPublikUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationChannelModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationChannelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikUserId",
			new String[] { Long.class.getName() },
			UserNotificationChannelModelImpl.PUBLIKUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKUSERID = new FinderPath(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationChannelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the user notification channels where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching user notification channels
	 */
	@Override
	public List<UserNotificationChannel> findByPublikUserId(long publikUserId) {
		return findByPublikUserId(publikUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification channels where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user notification channels
	 * @param end the upper bound of the range of user notification channels (not inclusive)
	 * @return the range of matching user notification channels
	 */
	@Override
	public List<UserNotificationChannel> findByPublikUserId(long publikUserId,
		int start, int end) {
		return findByPublikUserId(publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification channels where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user notification channels
	 * @param end the upper bound of the range of user notification channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user notification channels
	 */
	@Override
	public List<UserNotificationChannel> findByPublikUserId(long publikUserId,
		int start, int end,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		return findByPublikUserId(publikUserId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the user notification channels where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of user notification channels
	 * @param end the upper bound of the range of user notification channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching user notification channels
	 */
	@Override
	public List<UserNotificationChannel> findByPublikUserId(long publikUserId,
		int start, int end,
		OrderByComparator<UserNotificationChannel> orderByComparator,
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

		List<UserNotificationChannel> list = null;

		if (retrieveFromCache) {
			list = (List<UserNotificationChannel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserNotificationChannel userNotificationChannel : list) {
					if ((publikUserId != userNotificationChannel.getPublikUserId())) {
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

			query.append(_SQL_SELECT_USERNOTIFICATIONCHANNEL_WHERE);

			query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserNotificationChannelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(publikUserId);

				if (!pagination) {
					list = (List<UserNotificationChannel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserNotificationChannel>)QueryUtil.list(q,
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
	 * Returns the first user notification channel in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification channel
	 * @throws NoSuchUserNotificationChannelException if a matching user notification channel could not be found
	 */
	@Override
	public UserNotificationChannel findByPublikUserId_First(long publikUserId,
		OrderByComparator<UserNotificationChannel> orderByComparator)
		throws NoSuchUserNotificationChannelException {
		UserNotificationChannel userNotificationChannel = fetchByPublikUserId_First(publikUserId,
				orderByComparator);

		if (userNotificationChannel != null) {
			return userNotificationChannel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationChannelException(msg.toString());
	}

	/**
	 * Returns the first user notification channel in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification channel, or <code>null</code> if a matching user notification channel could not be found
	 */
	@Override
	public UserNotificationChannel fetchByPublikUserId_First(
		long publikUserId,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		List<UserNotificationChannel> list = findByPublikUserId(publikUserId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user notification channel in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification channel
	 * @throws NoSuchUserNotificationChannelException if a matching user notification channel could not be found
	 */
	@Override
	public UserNotificationChannel findByPublikUserId_Last(long publikUserId,
		OrderByComparator<UserNotificationChannel> orderByComparator)
		throws NoSuchUserNotificationChannelException {
		UserNotificationChannel userNotificationChannel = fetchByPublikUserId_Last(publikUserId,
				orderByComparator);

		if (userNotificationChannel != null) {
			return userNotificationChannel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationChannelException(msg.toString());
	}

	/**
	 * Returns the last user notification channel in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification channel, or <code>null</code> if a matching user notification channel could not be found
	 */
	@Override
	public UserNotificationChannel fetchByPublikUserId_Last(long publikUserId,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		int count = countByPublikUserId(publikUserId);

		if (count == 0) {
			return null;
		}

		List<UserNotificationChannel> list = findByPublikUserId(publikUserId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user notification channels before and after the current user notification channel in the ordered set where publikUserId = &#63;.
	 *
	 * @param userNotificationChannelPK the primary key of the current user notification channel
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user notification channel
	 * @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	 */
	@Override
	public UserNotificationChannel[] findByPublikUserId_PrevAndNext(
		UserNotificationChannelPK userNotificationChannelPK, long publikUserId,
		OrderByComparator<UserNotificationChannel> orderByComparator)
		throws NoSuchUserNotificationChannelException {
		UserNotificationChannel userNotificationChannel = findByPrimaryKey(userNotificationChannelPK);

		Session session = null;

		try {
			session = openSession();

			UserNotificationChannel[] array = new UserNotificationChannelImpl[3];

			array[0] = getByPublikUserId_PrevAndNext(session,
					userNotificationChannel, publikUserId, orderByComparator,
					true);

			array[1] = userNotificationChannel;

			array[2] = getByPublikUserId_PrevAndNext(session,
					userNotificationChannel, publikUserId, orderByComparator,
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

	protected UserNotificationChannel getByPublikUserId_PrevAndNext(
		Session session, UserNotificationChannel userNotificationChannel,
		long publikUserId,
		OrderByComparator<UserNotificationChannel> orderByComparator,
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

		query.append(_SQL_SELECT_USERNOTIFICATIONCHANNEL_WHERE);

		query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);

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
			query.append(UserNotificationChannelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(publikUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userNotificationChannel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserNotificationChannel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user notification channels where publikUserId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 */
	@Override
	public void removeByPublikUserId(long publikUserId) {
		for (UserNotificationChannel userNotificationChannel : findByPublikUserId(
				publikUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userNotificationChannel);
		}
	}

	/**
	 * Returns the number of user notification channels where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the number of matching user notification channels
	 */
	@Override
	public int countByPublikUserId(long publikUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKUSERID;

		Object[] finderArgs = new Object[] { publikUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERNOTIFICATIONCHANNEL_WHERE);

			query.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(publikUserId);

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

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2 = "userNotificationChannel.id.publikUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CHANNELID =
		new FinderPath(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationChannelModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationChannelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByChannelId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHANNELID =
		new FinderPath(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationChannelModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationChannelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByChannelId",
			new String[] { Long.class.getName() },
			UserNotificationChannelModelImpl.CHANNELID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CHANNELID = new FinderPath(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationChannelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByChannelId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the user notification channels where channelId = &#63;.
	 *
	 * @param channelId the channel ID
	 * @return the matching user notification channels
	 */
	@Override
	public List<UserNotificationChannel> findByChannelId(long channelId) {
		return findByChannelId(channelId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the user notification channels where channelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param channelId the channel ID
	 * @param start the lower bound of the range of user notification channels
	 * @param end the upper bound of the range of user notification channels (not inclusive)
	 * @return the range of matching user notification channels
	 */
	@Override
	public List<UserNotificationChannel> findByChannelId(long channelId,
		int start, int end) {
		return findByChannelId(channelId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification channels where channelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param channelId the channel ID
	 * @param start the lower bound of the range of user notification channels
	 * @param end the upper bound of the range of user notification channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user notification channels
	 */
	@Override
	public List<UserNotificationChannel> findByChannelId(long channelId,
		int start, int end,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		return findByChannelId(channelId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user notification channels where channelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param channelId the channel ID
	 * @param start the lower bound of the range of user notification channels
	 * @param end the upper bound of the range of user notification channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching user notification channels
	 */
	@Override
	public List<UserNotificationChannel> findByChannelId(long channelId,
		int start, int end,
		OrderByComparator<UserNotificationChannel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHANNELID;
			finderArgs = new Object[] { channelId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CHANNELID;
			finderArgs = new Object[] { channelId, start, end, orderByComparator };
		}

		List<UserNotificationChannel> list = null;

		if (retrieveFromCache) {
			list = (List<UserNotificationChannel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserNotificationChannel userNotificationChannel : list) {
					if ((channelId != userNotificationChannel.getChannelId())) {
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

			query.append(_SQL_SELECT_USERNOTIFICATIONCHANNEL_WHERE);

			query.append(_FINDER_COLUMN_CHANNELID_CHANNELID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserNotificationChannelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(channelId);

				if (!pagination) {
					list = (List<UserNotificationChannel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserNotificationChannel>)QueryUtil.list(q,
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
	 * Returns the first user notification channel in the ordered set where channelId = &#63;.
	 *
	 * @param channelId the channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification channel
	 * @throws NoSuchUserNotificationChannelException if a matching user notification channel could not be found
	 */
	@Override
	public UserNotificationChannel findByChannelId_First(long channelId,
		OrderByComparator<UserNotificationChannel> orderByComparator)
		throws NoSuchUserNotificationChannelException {
		UserNotificationChannel userNotificationChannel = fetchByChannelId_First(channelId,
				orderByComparator);

		if (userNotificationChannel != null) {
			return userNotificationChannel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("channelId=");
		msg.append(channelId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationChannelException(msg.toString());
	}

	/**
	 * Returns the first user notification channel in the ordered set where channelId = &#63;.
	 *
	 * @param channelId the channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification channel, or <code>null</code> if a matching user notification channel could not be found
	 */
	@Override
	public UserNotificationChannel fetchByChannelId_First(long channelId,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		List<UserNotificationChannel> list = findByChannelId(channelId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user notification channel in the ordered set where channelId = &#63;.
	 *
	 * @param channelId the channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification channel
	 * @throws NoSuchUserNotificationChannelException if a matching user notification channel could not be found
	 */
	@Override
	public UserNotificationChannel findByChannelId_Last(long channelId,
		OrderByComparator<UserNotificationChannel> orderByComparator)
		throws NoSuchUserNotificationChannelException {
		UserNotificationChannel userNotificationChannel = fetchByChannelId_Last(channelId,
				orderByComparator);

		if (userNotificationChannel != null) {
			return userNotificationChannel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("channelId=");
		msg.append(channelId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationChannelException(msg.toString());
	}

	/**
	 * Returns the last user notification channel in the ordered set where channelId = &#63;.
	 *
	 * @param channelId the channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification channel, or <code>null</code> if a matching user notification channel could not be found
	 */
	@Override
	public UserNotificationChannel fetchByChannelId_Last(long channelId,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		int count = countByChannelId(channelId);

		if (count == 0) {
			return null;
		}

		List<UserNotificationChannel> list = findByChannelId(channelId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user notification channels before and after the current user notification channel in the ordered set where channelId = &#63;.
	 *
	 * @param userNotificationChannelPK the primary key of the current user notification channel
	 * @param channelId the channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user notification channel
	 * @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	 */
	@Override
	public UserNotificationChannel[] findByChannelId_PrevAndNext(
		UserNotificationChannelPK userNotificationChannelPK, long channelId,
		OrderByComparator<UserNotificationChannel> orderByComparator)
		throws NoSuchUserNotificationChannelException {
		UserNotificationChannel userNotificationChannel = findByPrimaryKey(userNotificationChannelPK);

		Session session = null;

		try {
			session = openSession();

			UserNotificationChannel[] array = new UserNotificationChannelImpl[3];

			array[0] = getByChannelId_PrevAndNext(session,
					userNotificationChannel, channelId, orderByComparator, true);

			array[1] = userNotificationChannel;

			array[2] = getByChannelId_PrevAndNext(session,
					userNotificationChannel, channelId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserNotificationChannel getByChannelId_PrevAndNext(
		Session session, UserNotificationChannel userNotificationChannel,
		long channelId,
		OrderByComparator<UserNotificationChannel> orderByComparator,
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

		query.append(_SQL_SELECT_USERNOTIFICATIONCHANNEL_WHERE);

		query.append(_FINDER_COLUMN_CHANNELID_CHANNELID_2);

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
			query.append(UserNotificationChannelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(channelId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userNotificationChannel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserNotificationChannel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user notification channels where channelId = &#63; from the database.
	 *
	 * @param channelId the channel ID
	 */
	@Override
	public void removeByChannelId(long channelId) {
		for (UserNotificationChannel userNotificationChannel : findByChannelId(
				channelId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userNotificationChannel);
		}
	}

	/**
	 * Returns the number of user notification channels where channelId = &#63;.
	 *
	 * @param channelId the channel ID
	 * @return the number of matching user notification channels
	 */
	@Override
	public int countByChannelId(long channelId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CHANNELID;

		Object[] finderArgs = new Object[] { channelId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERNOTIFICATIONCHANNEL_WHERE);

			query.append(_FINDER_COLUMN_CHANNELID_CHANNELID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(channelId);

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

	private static final String _FINDER_COLUMN_CHANNELID_CHANNELID_2 = "userNotificationChannel.id.channelId = ?";

	public UserNotificationChannelPersistenceImpl() {
		setModelClass(UserNotificationChannel.class);
	}

	/**
	 * Caches the user notification channel in the entity cache if it is enabled.
	 *
	 * @param userNotificationChannel the user notification channel
	 */
	@Override
	public void cacheResult(UserNotificationChannel userNotificationChannel) {
		entityCache.putResult(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationChannelImpl.class,
			userNotificationChannel.getPrimaryKey(), userNotificationChannel);

		userNotificationChannel.resetOriginalValues();
	}

	/**
	 * Caches the user notification channels in the entity cache if it is enabled.
	 *
	 * @param userNotificationChannels the user notification channels
	 */
	@Override
	public void cacheResult(
		List<UserNotificationChannel> userNotificationChannels) {
		for (UserNotificationChannel userNotificationChannel : userNotificationChannels) {
			if (entityCache.getResult(
						UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
						UserNotificationChannelImpl.class,
						userNotificationChannel.getPrimaryKey()) == null) {
				cacheResult(userNotificationChannel);
			}
			else {
				userNotificationChannel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all user notification channels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserNotificationChannelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the user notification channel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserNotificationChannel userNotificationChannel) {
		entityCache.removeResult(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationChannelImpl.class,
			userNotificationChannel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<UserNotificationChannel> userNotificationChannels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserNotificationChannel userNotificationChannel : userNotificationChannels) {
			entityCache.removeResult(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
				UserNotificationChannelImpl.class,
				userNotificationChannel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new user notification channel with the primary key. Does not add the user notification channel to the database.
	 *
	 * @param userNotificationChannelPK the primary key for the new user notification channel
	 * @return the new user notification channel
	 */
	@Override
	public UserNotificationChannel create(
		UserNotificationChannelPK userNotificationChannelPK) {
		UserNotificationChannel userNotificationChannel = new UserNotificationChannelImpl();

		userNotificationChannel.setNew(true);
		userNotificationChannel.setPrimaryKey(userNotificationChannelPK);

		return userNotificationChannel;
	}

	/**
	 * Removes the user notification channel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationChannelPK the primary key of the user notification channel
	 * @return the user notification channel that was removed
	 * @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	 */
	@Override
	public UserNotificationChannel remove(
		UserNotificationChannelPK userNotificationChannelPK)
		throws NoSuchUserNotificationChannelException {
		return remove((Serializable)userNotificationChannelPK);
	}

	/**
	 * Removes the user notification channel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user notification channel
	 * @return the user notification channel that was removed
	 * @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	 */
	@Override
	public UserNotificationChannel remove(Serializable primaryKey)
		throws NoSuchUserNotificationChannelException {
		Session session = null;

		try {
			session = openSession();

			UserNotificationChannel userNotificationChannel = (UserNotificationChannel)session.get(UserNotificationChannelImpl.class,
					primaryKey);

			if (userNotificationChannel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserNotificationChannelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(userNotificationChannel);
		}
		catch (NoSuchUserNotificationChannelException nsee) {
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
	protected UserNotificationChannel removeImpl(
		UserNotificationChannel userNotificationChannel) {
		userNotificationChannel = toUnwrappedModel(userNotificationChannel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userNotificationChannel)) {
				userNotificationChannel = (UserNotificationChannel)session.get(UserNotificationChannelImpl.class,
						userNotificationChannel.getPrimaryKeyObj());
			}

			if (userNotificationChannel != null) {
				session.delete(userNotificationChannel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (userNotificationChannel != null) {
			clearCache(userNotificationChannel);
		}

		return userNotificationChannel;
	}

	@Override
	public UserNotificationChannel updateImpl(
		UserNotificationChannel userNotificationChannel) {
		userNotificationChannel = toUnwrappedModel(userNotificationChannel);

		boolean isNew = userNotificationChannel.isNew();

		UserNotificationChannelModelImpl userNotificationChannelModelImpl = (UserNotificationChannelModelImpl)userNotificationChannel;

		Session session = null;

		try {
			session = openSession();

			if (userNotificationChannel.isNew()) {
				session.save(userNotificationChannel);

				userNotificationChannel.setNew(false);
			}
			else {
				userNotificationChannel = (UserNotificationChannel)session.merge(userNotificationChannel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!UserNotificationChannelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					userNotificationChannelModelImpl.getPublikUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
				args);

			args = new Object[] { userNotificationChannelModelImpl.getChannelId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CHANNELID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHANNELID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((userNotificationChannelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userNotificationChannelModelImpl.getOriginalPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);

				args = new Object[] {
						userNotificationChannelModelImpl.getPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);
			}

			if ((userNotificationChannelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHANNELID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userNotificationChannelModelImpl.getOriginalChannelId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CHANNELID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHANNELID,
					args);

				args = new Object[] {
						userNotificationChannelModelImpl.getChannelId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CHANNELID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHANNELID,
					args);
			}
		}

		entityCache.putResult(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationChannelImpl.class,
			userNotificationChannel.getPrimaryKey(), userNotificationChannel,
			false);

		userNotificationChannel.resetOriginalValues();

		return userNotificationChannel;
	}

	protected UserNotificationChannel toUnwrappedModel(
		UserNotificationChannel userNotificationChannel) {
		if (userNotificationChannel instanceof UserNotificationChannelImpl) {
			return userNotificationChannel;
		}

		UserNotificationChannelImpl userNotificationChannelImpl = new UserNotificationChannelImpl();

		userNotificationChannelImpl.setNew(userNotificationChannel.isNew());
		userNotificationChannelImpl.setPrimaryKey(userNotificationChannel.getPrimaryKey());

		userNotificationChannelImpl.setPublikUserId(userNotificationChannel.getPublikUserId());
		userNotificationChannelImpl.setChannelId(userNotificationChannel.getChannelId());

		return userNotificationChannelImpl;
	}

	/**
	 * Returns the user notification channel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user notification channel
	 * @return the user notification channel
	 * @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	 */
	@Override
	public UserNotificationChannel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserNotificationChannelException {
		UserNotificationChannel userNotificationChannel = fetchByPrimaryKey(primaryKey);

		if (userNotificationChannel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserNotificationChannelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return userNotificationChannel;
	}

	/**
	 * Returns the user notification channel with the primary key or throws a {@link NoSuchUserNotificationChannelException} if it could not be found.
	 *
	 * @param userNotificationChannelPK the primary key of the user notification channel
	 * @return the user notification channel
	 * @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	 */
	@Override
	public UserNotificationChannel findByPrimaryKey(
		UserNotificationChannelPK userNotificationChannelPK)
		throws NoSuchUserNotificationChannelException {
		return findByPrimaryKey((Serializable)userNotificationChannelPK);
	}

	/**
	 * Returns the user notification channel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user notification channel
	 * @return the user notification channel, or <code>null</code> if a user notification channel with the primary key could not be found
	 */
	@Override
	public UserNotificationChannel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
				UserNotificationChannelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		UserNotificationChannel userNotificationChannel = (UserNotificationChannel)serializable;

		if (userNotificationChannel == null) {
			Session session = null;

			try {
				session = openSession();

				userNotificationChannel = (UserNotificationChannel)session.get(UserNotificationChannelImpl.class,
						primaryKey);

				if (userNotificationChannel != null) {
					cacheResult(userNotificationChannel);
				}
				else {
					entityCache.putResult(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
						UserNotificationChannelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(UserNotificationChannelModelImpl.ENTITY_CACHE_ENABLED,
					UserNotificationChannelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return userNotificationChannel;
	}

	/**
	 * Returns the user notification channel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userNotificationChannelPK the primary key of the user notification channel
	 * @return the user notification channel, or <code>null</code> if a user notification channel with the primary key could not be found
	 */
	@Override
	public UserNotificationChannel fetchByPrimaryKey(
		UserNotificationChannelPK userNotificationChannelPK) {
		return fetchByPrimaryKey((Serializable)userNotificationChannelPK);
	}

	@Override
	public Map<Serializable, UserNotificationChannel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, UserNotificationChannel> map = new HashMap<Serializable, UserNotificationChannel>();

		for (Serializable primaryKey : primaryKeys) {
			UserNotificationChannel userNotificationChannel = fetchByPrimaryKey(primaryKey);

			if (userNotificationChannel != null) {
				map.put(primaryKey, userNotificationChannel);
			}
		}

		return map;
	}

	/**
	 * Returns all the user notification channels.
	 *
	 * @return the user notification channels
	 */
	@Override
	public List<UserNotificationChannel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification channels
	 * @param end the upper bound of the range of user notification channels (not inclusive)
	 * @return the range of user notification channels
	 */
	@Override
	public List<UserNotificationChannel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification channels
	 * @param end the upper bound of the range of user notification channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user notification channels
	 */
	@Override
	public List<UserNotificationChannel> findAll(int start, int end,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user notification channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification channels
	 * @param end the upper bound of the range of user notification channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of user notification channels
	 */
	@Override
	public List<UserNotificationChannel> findAll(int start, int end,
		OrderByComparator<UserNotificationChannel> orderByComparator,
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

		List<UserNotificationChannel> list = null;

		if (retrieveFromCache) {
			list = (List<UserNotificationChannel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_USERNOTIFICATIONCHANNEL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERNOTIFICATIONCHANNEL;

				if (pagination) {
					sql = sql.concat(UserNotificationChannelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UserNotificationChannel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserNotificationChannel>)QueryUtil.list(q,
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
	 * Removes all the user notification channels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserNotificationChannel userNotificationChannel : findAll()) {
			remove(userNotificationChannel);
		}
	}

	/**
	 * Returns the number of user notification channels.
	 *
	 * @return the number of user notification channels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERNOTIFICATIONCHANNEL);

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
		return UserNotificationChannelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user notification channel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(UserNotificationChannelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_USERNOTIFICATIONCHANNEL = "SELECT userNotificationChannel FROM UserNotificationChannel userNotificationChannel";
	private static final String _SQL_SELECT_USERNOTIFICATIONCHANNEL_WHERE = "SELECT userNotificationChannel FROM UserNotificationChannel userNotificationChannel WHERE ";
	private static final String _SQL_COUNT_USERNOTIFICATIONCHANNEL = "SELECT COUNT(userNotificationChannel) FROM UserNotificationChannel userNotificationChannel";
	private static final String _SQL_COUNT_USERNOTIFICATIONCHANNEL_WHERE = "SELECT COUNT(userNotificationChannel) FROM UserNotificationChannel userNotificationChannel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userNotificationChannel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserNotificationChannel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserNotificationChannel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(UserNotificationChannelPersistenceImpl.class);
}