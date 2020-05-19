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

package eu.strasbourg.service.council.service.persistence.impl;

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
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.council.exception.NoSuchVoteException;
import eu.strasbourg.service.council.model.Vote;
import eu.strasbourg.service.council.model.impl.VoteImpl;
import eu.strasbourg.service.council.model.impl.VoteModelImpl;
import eu.strasbourg.service.council.service.persistence.VotePK;
import eu.strasbourg.service.council.service.persistence.VotePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the vote service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VotePersistence
 * @see eu.strasbourg.service.council.service.persistence.VoteUtil
 * @generated
 */
@ProviderType
public class VotePersistenceImpl extends BasePersistenceImpl<Vote>
	implements VotePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VoteUtil} to access the vote persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VoteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, VoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, VoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, VoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, VoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			VoteModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the votes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching votes
	 */
	@Override
	public List<Vote> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the votes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @return the range of matching votes
	 */
	@Override
	public List<Vote> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the votes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching votes
	 */
	@Override
	public List<Vote> findByUuid(String uuid, int start, int end,
		OrderByComparator<Vote> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the votes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching votes
	 */
	@Override
	public List<Vote> findByUuid(String uuid, int start, int end,
		OrderByComparator<Vote> orderByComparator, boolean retrieveFromCache) {
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

		List<Vote> list = null;

		if (retrieveFromCache) {
			list = (List<Vote>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Vote vote : list) {
					if (!Objects.equals(uuid, vote.getUuid())) {
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

			query.append(_SQL_SELECT_VOTE_WHERE);

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
				query.append(VoteModelImpl.ORDER_BY_JPQL);
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
					list = (List<Vote>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Vote>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first vote in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	@Override
	public Vote findByUuid_First(String uuid,
		OrderByComparator<Vote> orderByComparator) throws NoSuchVoteException {
		Vote vote = fetchByUuid_First(uuid, orderByComparator);

		if (vote != null) {
			return vote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the first vote in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vote, or <code>null</code> if a matching vote could not be found
	 */
	@Override
	public Vote fetchByUuid_First(String uuid,
		OrderByComparator<Vote> orderByComparator) {
		List<Vote> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vote in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	@Override
	public Vote findByUuid_Last(String uuid,
		OrderByComparator<Vote> orderByComparator) throws NoSuchVoteException {
		Vote vote = fetchByUuid_Last(uuid, orderByComparator);

		if (vote != null) {
			return vote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the last vote in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vote, or <code>null</code> if a matching vote could not be found
	 */
	@Override
	public Vote fetchByUuid_Last(String uuid,
		OrderByComparator<Vote> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Vote> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the votes before and after the current vote in the ordered set where uuid = &#63;.
	 *
	 * @param votePK the primary key of the current vote
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vote
	 * @throws NoSuchVoteException if a vote with the primary key could not be found
	 */
	@Override
	public Vote[] findByUuid_PrevAndNext(VotePK votePK, String uuid,
		OrderByComparator<Vote> orderByComparator) throws NoSuchVoteException {
		Vote vote = findByPrimaryKey(votePK);

		Session session = null;

		try {
			session = openSession();

			Vote[] array = new VoteImpl[3];

			array[0] = getByUuid_PrevAndNext(session, vote, uuid,
					orderByComparator, true);

			array[1] = vote;

			array[2] = getByUuid_PrevAndNext(session, vote, uuid,
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

	protected Vote getByUuid_PrevAndNext(Session session, Vote vote,
		String uuid, OrderByComparator<Vote> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VOTE_WHERE);

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
			query.append(VoteModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(vote);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Vote> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the votes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Vote vote : findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(vote);
		}
	}

	/**
	 * Returns the number of votes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching votes
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VOTE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "vote.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "vote.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(vote.uuid IS NULL OR vote.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, VoteImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			VoteModelImpl.UUID_COLUMN_BITMASK |
			VoteModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the vote where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVoteException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	@Override
	public Vote findByUUID_G(String uuid, long groupId)
		throws NoSuchVoteException {
		Vote vote = fetchByUUID_G(uuid, groupId);

		if (vote == null) {
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

			throw new NoSuchVoteException(msg.toString());
		}

		return vote;
	}

	/**
	 * Returns the vote where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching vote, or <code>null</code> if a matching vote could not be found
	 */
	@Override
	public Vote fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the vote where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching vote, or <code>null</code> if a matching vote could not be found
	 */
	@Override
	public Vote fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Vote) {
			Vote vote = (Vote)result;

			if (!Objects.equals(uuid, vote.getUuid()) ||
					(groupId != vote.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VOTE_WHERE);

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

				List<Vote> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Vote vote = list.get(0);

					result = vote;

					cacheResult(vote);

					if ((vote.getUuid() == null) ||
							!vote.getUuid().equals(uuid) ||
							(vote.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, vote);
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
			return (Vote)result;
		}
	}

	/**
	 * Removes the vote where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the vote that was removed
	 */
	@Override
	public Vote removeByUUID_G(String uuid, long groupId)
		throws NoSuchVoteException {
		Vote vote = findByUUID_G(uuid, groupId);

		return remove(vote);
	}

	/**
	 * Returns the number of votes where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching votes
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VOTE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "vote.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "vote.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(vote.uuid IS NULL OR vote.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "vote.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, VoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, VoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			VoteModelImpl.UUID_COLUMN_BITMASK |
			VoteModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the votes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching votes
	 */
	@Override
	public List<Vote> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the votes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @return the range of matching votes
	 */
	@Override
	public List<Vote> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the votes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching votes
	 */
	@Override
	public List<Vote> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Vote> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the votes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching votes
	 */
	@Override
	public List<Vote> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Vote> orderByComparator,
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

		List<Vote> list = null;

		if (retrieveFromCache) {
			list = (List<Vote>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Vote vote : list) {
					if (!Objects.equals(uuid, vote.getUuid()) ||
							(companyId != vote.getCompanyId())) {
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

			query.append(_SQL_SELECT_VOTE_WHERE);

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
				query.append(VoteModelImpl.ORDER_BY_JPQL);
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
					list = (List<Vote>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Vote>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	@Override
	public Vote findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Vote> orderByComparator) throws NoSuchVoteException {
		Vote vote = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (vote != null) {
			return vote;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the first vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vote, or <code>null</code> if a matching vote could not be found
	 */
	@Override
	public Vote fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Vote> orderByComparator) {
		List<Vote> list = findByUuid_C(uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	@Override
	public Vote findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Vote> orderByComparator) throws NoSuchVoteException {
		Vote vote = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (vote != null) {
			return vote;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the last vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vote, or <code>null</code> if a matching vote could not be found
	 */
	@Override
	public Vote fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Vote> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Vote> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the votes before and after the current vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param votePK the primary key of the current vote
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vote
	 * @throws NoSuchVoteException if a vote with the primary key could not be found
	 */
	@Override
	public Vote[] findByUuid_C_PrevAndNext(VotePK votePK, String uuid,
		long companyId, OrderByComparator<Vote> orderByComparator)
		throws NoSuchVoteException {
		Vote vote = findByPrimaryKey(votePK);

		Session session = null;

		try {
			session = openSession();

			Vote[] array = new VoteImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, vote, uuid, companyId,
					orderByComparator, true);

			array[1] = vote;

			array[2] = getByUuid_C_PrevAndNext(session, vote, uuid, companyId,
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

	protected Vote getByUuid_C_PrevAndNext(Session session, Vote vote,
		String uuid, long companyId, OrderByComparator<Vote> orderByComparator,
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

		query.append(_SQL_SELECT_VOTE_WHERE);

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
			query.append(VoteModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(vote);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Vote> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the votes where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Vote vote : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(vote);
		}
	}

	/**
	 * Returns the number of votes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching votes
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VOTE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "vote.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "vote.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(vote.uuid IS NULL OR vote.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "vote.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DELIBERATIONID =
		new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, VoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDeliberationId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DELIBERATIONID =
		new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, VoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDeliberationId",
			new String[] { Long.class.getName() },
			VoteModelImpl.DELIBERATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DELIBERATIONID = new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDeliberationId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the votes where deliberationId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @return the matching votes
	 */
	@Override
	public List<Vote> findByDeliberationId(long deliberationId) {
		return findByDeliberationId(deliberationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the votes where deliberationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliberationId the deliberation ID
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @return the range of matching votes
	 */
	@Override
	public List<Vote> findByDeliberationId(long deliberationId, int start,
		int end) {
		return findByDeliberationId(deliberationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the votes where deliberationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliberationId the deliberation ID
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching votes
	 */
	@Override
	public List<Vote> findByDeliberationId(long deliberationId, int start,
		int end, OrderByComparator<Vote> orderByComparator) {
		return findByDeliberationId(deliberationId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the votes where deliberationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliberationId the deliberation ID
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching votes
	 */
	@Override
	public List<Vote> findByDeliberationId(long deliberationId, int start,
		int end, OrderByComparator<Vote> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DELIBERATIONID;
			finderArgs = new Object[] { deliberationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DELIBERATIONID;
			finderArgs = new Object[] {
					deliberationId,
					
					start, end, orderByComparator
				};
		}

		List<Vote> list = null;

		if (retrieveFromCache) {
			list = (List<Vote>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Vote vote : list) {
					if ((deliberationId != vote.getDeliberationId())) {
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

			query.append(_SQL_SELECT_VOTE_WHERE);

			query.append(_FINDER_COLUMN_DELIBERATIONID_DELIBERATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(VoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(deliberationId);

				if (!pagination) {
					list = (List<Vote>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Vote>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first vote in the ordered set where deliberationId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	@Override
	public Vote findByDeliberationId_First(long deliberationId,
		OrderByComparator<Vote> orderByComparator) throws NoSuchVoteException {
		Vote vote = fetchByDeliberationId_First(deliberationId,
				orderByComparator);

		if (vote != null) {
			return vote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("deliberationId=");
		msg.append(deliberationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the first vote in the ordered set where deliberationId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vote, or <code>null</code> if a matching vote could not be found
	 */
	@Override
	public Vote fetchByDeliberationId_First(long deliberationId,
		OrderByComparator<Vote> orderByComparator) {
		List<Vote> list = findByDeliberationId(deliberationId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vote in the ordered set where deliberationId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	@Override
	public Vote findByDeliberationId_Last(long deliberationId,
		OrderByComparator<Vote> orderByComparator) throws NoSuchVoteException {
		Vote vote = fetchByDeliberationId_Last(deliberationId, orderByComparator);

		if (vote != null) {
			return vote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("deliberationId=");
		msg.append(deliberationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the last vote in the ordered set where deliberationId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vote, or <code>null</code> if a matching vote could not be found
	 */
	@Override
	public Vote fetchByDeliberationId_Last(long deliberationId,
		OrderByComparator<Vote> orderByComparator) {
		int count = countByDeliberationId(deliberationId);

		if (count == 0) {
			return null;
		}

		List<Vote> list = findByDeliberationId(deliberationId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the votes before and after the current vote in the ordered set where deliberationId = &#63;.
	 *
	 * @param votePK the primary key of the current vote
	 * @param deliberationId the deliberation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vote
	 * @throws NoSuchVoteException if a vote with the primary key could not be found
	 */
	@Override
	public Vote[] findByDeliberationId_PrevAndNext(VotePK votePK,
		long deliberationId, OrderByComparator<Vote> orderByComparator)
		throws NoSuchVoteException {
		Vote vote = findByPrimaryKey(votePK);

		Session session = null;

		try {
			session = openSession();

			Vote[] array = new VoteImpl[3];

			array[0] = getByDeliberationId_PrevAndNext(session, vote,
					deliberationId, orderByComparator, true);

			array[1] = vote;

			array[2] = getByDeliberationId_PrevAndNext(session, vote,
					deliberationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Vote getByDeliberationId_PrevAndNext(Session session, Vote vote,
		long deliberationId, OrderByComparator<Vote> orderByComparator,
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

		query.append(_SQL_SELECT_VOTE_WHERE);

		query.append(_FINDER_COLUMN_DELIBERATIONID_DELIBERATIONID_2);

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
			query.append(VoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(deliberationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vote);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Vote> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the votes where deliberationId = &#63; from the database.
	 *
	 * @param deliberationId the deliberation ID
	 */
	@Override
	public void removeByDeliberationId(long deliberationId) {
		for (Vote vote : findByDeliberationId(deliberationId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(vote);
		}
	}

	/**
	 * Returns the number of votes where deliberationId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @return the number of matching votes
	 */
	@Override
	public int countByDeliberationId(long deliberationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DELIBERATIONID;

		Object[] finderArgs = new Object[] { deliberationId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VOTE_WHERE);

			query.append(_FINDER_COLUMN_DELIBERATIONID_DELIBERATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(deliberationId);

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

	private static final String _FINDER_COLUMN_DELIBERATIONID_DELIBERATIONID_2 = "vote.id.deliberationId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DELIBERATIONIDANDOFFICIALID =
		new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, VoteImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDeliberationIdAndOfficialId",
			new String[] { Long.class.getName(), Long.class.getName() },
			VoteModelImpl.DELIBERATIONID_COLUMN_BITMASK |
			VoteModelImpl.OFFICIALID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DELIBERATIONIDANDOFFICIALID =
		new FinderPath(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByDeliberationIdAndOfficialId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the vote where deliberationId = &#63; and officialId = &#63; or throws a {@link NoSuchVoteException} if it could not be found.
	 *
	 * @param deliberationId the deliberation ID
	 * @param officialId the official ID
	 * @return the matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	@Override
	public Vote findByDeliberationIdAndOfficialId(long deliberationId,
		long officialId) throws NoSuchVoteException {
		Vote vote = fetchByDeliberationIdAndOfficialId(deliberationId,
				officialId);

		if (vote == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("deliberationId=");
			msg.append(deliberationId);

			msg.append(", officialId=");
			msg.append(officialId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchVoteException(msg.toString());
		}

		return vote;
	}

	/**
	 * Returns the vote where deliberationId = &#63; and officialId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param deliberationId the deliberation ID
	 * @param officialId the official ID
	 * @return the matching vote, or <code>null</code> if a matching vote could not be found
	 */
	@Override
	public Vote fetchByDeliberationIdAndOfficialId(long deliberationId,
		long officialId) {
		return fetchByDeliberationIdAndOfficialId(deliberationId, officialId,
			true);
	}

	/**
	 * Returns the vote where deliberationId = &#63; and officialId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param deliberationId the deliberation ID
	 * @param officialId the official ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching vote, or <code>null</code> if a matching vote could not be found
	 */
	@Override
	public Vote fetchByDeliberationIdAndOfficialId(long deliberationId,
		long officialId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { deliberationId, officialId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DELIBERATIONIDANDOFFICIALID,
					finderArgs, this);
		}

		if (result instanceof Vote) {
			Vote vote = (Vote)result;

			if ((deliberationId != vote.getDeliberationId()) ||
					(officialId != vote.getOfficialId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VOTE_WHERE);

			query.append(_FINDER_COLUMN_DELIBERATIONIDANDOFFICIALID_DELIBERATIONID_2);

			query.append(_FINDER_COLUMN_DELIBERATIONIDANDOFFICIALID_OFFICIALID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(deliberationId);

				qPos.add(officialId);

				List<Vote> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DELIBERATIONIDANDOFFICIALID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"VotePersistenceImpl.fetchByDeliberationIdAndOfficialId(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Vote vote = list.get(0);

					result = vote;

					cacheResult(vote);

					if ((vote.getDeliberationId() != deliberationId) ||
							(vote.getOfficialId() != officialId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_DELIBERATIONIDANDOFFICIALID,
							finderArgs, vote);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DELIBERATIONIDANDOFFICIALID,
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
			return (Vote)result;
		}
	}

	/**
	 * Removes the vote where deliberationId = &#63; and officialId = &#63; from the database.
	 *
	 * @param deliberationId the deliberation ID
	 * @param officialId the official ID
	 * @return the vote that was removed
	 */
	@Override
	public Vote removeByDeliberationIdAndOfficialId(long deliberationId,
		long officialId) throws NoSuchVoteException {
		Vote vote = findByDeliberationIdAndOfficialId(deliberationId, officialId);

		return remove(vote);
	}

	/**
	 * Returns the number of votes where deliberationId = &#63; and officialId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @param officialId the official ID
	 * @return the number of matching votes
	 */
	@Override
	public int countByDeliberationIdAndOfficialId(long deliberationId,
		long officialId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DELIBERATIONIDANDOFFICIALID;

		Object[] finderArgs = new Object[] { deliberationId, officialId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VOTE_WHERE);

			query.append(_FINDER_COLUMN_DELIBERATIONIDANDOFFICIALID_DELIBERATIONID_2);

			query.append(_FINDER_COLUMN_DELIBERATIONIDANDOFFICIALID_OFFICIALID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(deliberationId);

				qPos.add(officialId);

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

	private static final String _FINDER_COLUMN_DELIBERATIONIDANDOFFICIALID_DELIBERATIONID_2 =
		"vote.id.deliberationId = ? AND ";
	private static final String _FINDER_COLUMN_DELIBERATIONIDANDOFFICIALID_OFFICIALID_2 =
		"vote.id.officialId = ?";

	public VotePersistenceImpl() {
		setModelClass(Vote.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vote in the entity cache if it is enabled.
	 *
	 * @param vote the vote
	 */
	@Override
	public void cacheResult(Vote vote) {
		entityCache.putResult(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteImpl.class, vote.getPrimaryKey(), vote);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { vote.getUuid(), vote.getGroupId() }, vote);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DELIBERATIONIDANDOFFICIALID,
			new Object[] { vote.getDeliberationId(), vote.getOfficialId() },
			vote);

		vote.resetOriginalValues();
	}

	/**
	 * Caches the votes in the entity cache if it is enabled.
	 *
	 * @param votes the votes
	 */
	@Override
	public void cacheResult(List<Vote> votes) {
		for (Vote vote : votes) {
			if (entityCache.getResult(VoteModelImpl.ENTITY_CACHE_ENABLED,
						VoteImpl.class, vote.getPrimaryKey()) == null) {
				cacheResult(vote);
			}
			else {
				vote.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all votes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VoteImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vote.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Vote vote) {
		entityCache.removeResult(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteImpl.class, vote.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((VoteModelImpl)vote, true);
	}

	@Override
	public void clearCache(List<Vote> votes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Vote vote : votes) {
			entityCache.removeResult(VoteModelImpl.ENTITY_CACHE_ENABLED,
				VoteImpl.class, vote.getPrimaryKey());

			clearUniqueFindersCache((VoteModelImpl)vote, true);
		}
	}

	protected void cacheUniqueFindersCache(VoteModelImpl voteModelImpl) {
		Object[] args = new Object[] {
				voteModelImpl.getUuid(), voteModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args, voteModelImpl,
			false);

		args = new Object[] {
				voteModelImpl.getDeliberationId(), voteModelImpl.getOfficialId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DELIBERATIONIDANDOFFICIALID,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DELIBERATIONIDANDOFFICIALID,
			args, voteModelImpl, false);
	}

	protected void clearUniqueFindersCache(VoteModelImpl voteModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					voteModelImpl.getUuid(), voteModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((voteModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					voteModelImpl.getOriginalUuid(),
					voteModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					voteModelImpl.getDeliberationId(),
					voteModelImpl.getOfficialId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DELIBERATIONIDANDOFFICIALID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DELIBERATIONIDANDOFFICIALID,
				args);
		}

		if ((voteModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DELIBERATIONIDANDOFFICIALID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					voteModelImpl.getOriginalDeliberationId(),
					voteModelImpl.getOriginalOfficialId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DELIBERATIONIDANDOFFICIALID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DELIBERATIONIDANDOFFICIALID,
				args);
		}
	}

	/**
	 * Creates a new vote with the primary key. Does not add the vote to the database.
	 *
	 * @param votePK the primary key for the new vote
	 * @return the new vote
	 */
	@Override
	public Vote create(VotePK votePK) {
		Vote vote = new VoteImpl();

		vote.setNew(true);
		vote.setPrimaryKey(votePK);

		String uuid = PortalUUIDUtil.generate();

		vote.setUuid(uuid);

		vote.setCompanyId(companyProvider.getCompanyId());

		return vote;
	}

	/**
	 * Removes the vote with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param votePK the primary key of the vote
	 * @return the vote that was removed
	 * @throws NoSuchVoteException if a vote with the primary key could not be found
	 */
	@Override
	public Vote remove(VotePK votePK) throws NoSuchVoteException {
		return remove((Serializable)votePK);
	}

	/**
	 * Removes the vote with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vote
	 * @return the vote that was removed
	 * @throws NoSuchVoteException if a vote with the primary key could not be found
	 */
	@Override
	public Vote remove(Serializable primaryKey) throws NoSuchVoteException {
		Session session = null;

		try {
			session = openSession();

			Vote vote = (Vote)session.get(VoteImpl.class, primaryKey);

			if (vote == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vote);
		}
		catch (NoSuchVoteException nsee) {
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
	protected Vote removeImpl(Vote vote) {
		vote = toUnwrappedModel(vote);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vote)) {
				vote = (Vote)session.get(VoteImpl.class, vote.getPrimaryKeyObj());
			}

			if (vote != null) {
				session.delete(vote);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vote != null) {
			clearCache(vote);
		}

		return vote;
	}

	@Override
	public Vote updateImpl(Vote vote) {
		vote = toUnwrappedModel(vote);

		boolean isNew = vote.isNew();

		VoteModelImpl voteModelImpl = (VoteModelImpl)vote;

		if (Validator.isNull(vote.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			vote.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (vote.isNew()) {
				session.save(vote);

				vote.setNew(false);
			}
			else {
				vote = (Vote)session.merge(vote);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!VoteModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { voteModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					voteModelImpl.getUuid(), voteModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { voteModelImpl.getDeliberationId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DELIBERATIONID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DELIBERATIONID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((voteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { voteModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { voteModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((voteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						voteModelImpl.getOriginalUuid(),
						voteModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						voteModelImpl.getUuid(), voteModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((voteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DELIBERATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						voteModelImpl.getOriginalDeliberationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DELIBERATIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DELIBERATIONID,
					args);

				args = new Object[] { voteModelImpl.getDeliberationId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DELIBERATIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DELIBERATIONID,
					args);
			}
		}

		entityCache.putResult(VoteModelImpl.ENTITY_CACHE_ENABLED,
			VoteImpl.class, vote.getPrimaryKey(), vote, false);

		clearUniqueFindersCache(voteModelImpl, false);
		cacheUniqueFindersCache(voteModelImpl);

		vote.resetOriginalValues();

		return vote;
	}

	protected Vote toUnwrappedModel(Vote vote) {
		if (vote instanceof VoteImpl) {
			return vote;
		}

		VoteImpl voteImpl = new VoteImpl();

		voteImpl.setNew(vote.isNew());
		voteImpl.setPrimaryKey(vote.getPrimaryKey());

		voteImpl.setUuid(vote.getUuid());
		voteImpl.setOfficialId(vote.getOfficialId());
		voteImpl.setDeliberationId(vote.getDeliberationId());
		voteImpl.setGroupId(vote.getGroupId());
		voteImpl.setCompanyId(vote.getCompanyId());
		voteImpl.setCreateDate(vote.getCreateDate());
		voteImpl.setResult(vote.getResult());
		voteImpl.setOfficialProcurationId(vote.getOfficialProcurationId());

		return voteImpl;
	}

	/**
	 * Returns the vote with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vote
	 * @return the vote
	 * @throws NoSuchVoteException if a vote with the primary key could not be found
	 */
	@Override
	public Vote findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVoteException {
		Vote vote = fetchByPrimaryKey(primaryKey);

		if (vote == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vote;
	}

	/**
	 * Returns the vote with the primary key or throws a {@link NoSuchVoteException} if it could not be found.
	 *
	 * @param votePK the primary key of the vote
	 * @return the vote
	 * @throws NoSuchVoteException if a vote with the primary key could not be found
	 */
	@Override
	public Vote findByPrimaryKey(VotePK votePK) throws NoSuchVoteException {
		return findByPrimaryKey((Serializable)votePK);
	}

	/**
	 * Returns the vote with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vote
	 * @return the vote, or <code>null</code> if a vote with the primary key could not be found
	 */
	@Override
	public Vote fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VoteModelImpl.ENTITY_CACHE_ENABLED,
				VoteImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Vote vote = (Vote)serializable;

		if (vote == null) {
			Session session = null;

			try {
				session = openSession();

				vote = (Vote)session.get(VoteImpl.class, primaryKey);

				if (vote != null) {
					cacheResult(vote);
				}
				else {
					entityCache.putResult(VoteModelImpl.ENTITY_CACHE_ENABLED,
						VoteImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VoteModelImpl.ENTITY_CACHE_ENABLED,
					VoteImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vote;
	}

	/**
	 * Returns the vote with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param votePK the primary key of the vote
	 * @return the vote, or <code>null</code> if a vote with the primary key could not be found
	 */
	@Override
	public Vote fetchByPrimaryKey(VotePK votePK) {
		return fetchByPrimaryKey((Serializable)votePK);
	}

	@Override
	public Map<Serializable, Vote> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Vote> map = new HashMap<Serializable, Vote>();

		for (Serializable primaryKey : primaryKeys) {
			Vote vote = fetchByPrimaryKey(primaryKey);

			if (vote != null) {
				map.put(primaryKey, vote);
			}
		}

		return map;
	}

	/**
	 * Returns all the votes.
	 *
	 * @return the votes
	 */
	@Override
	public List<Vote> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the votes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @return the range of votes
	 */
	@Override
	public List<Vote> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the votes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of votes
	 */
	@Override
	public List<Vote> findAll(int start, int end,
		OrderByComparator<Vote> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the votes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of votes
	 */
	@Override
	public List<Vote> findAll(int start, int end,
		OrderByComparator<Vote> orderByComparator, boolean retrieveFromCache) {
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

		List<Vote> list = null;

		if (retrieveFromCache) {
			list = (List<Vote>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VOTE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VOTE;

				if (pagination) {
					sql = sql.concat(VoteModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Vote>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Vote>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the votes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Vote vote : findAll()) {
			remove(vote);
		}
	}

	/**
	 * Returns the number of votes.
	 *
	 * @return the number of votes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VOTE);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return VoteModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vote persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VoteImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VOTE = "SELECT vote FROM Vote vote";
	private static final String _SQL_SELECT_VOTE_WHERE = "SELECT vote FROM Vote vote WHERE ";
	private static final String _SQL_COUNT_VOTE = "SELECT COUNT(vote) FROM Vote vote";
	private static final String _SQL_COUNT_VOTE_WHERE = "SELECT COUNT(vote) FROM Vote vote WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vote.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Vote exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Vote exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(VotePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}