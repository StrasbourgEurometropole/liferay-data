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

package eu.strasbourg.service.oidc.service.persistence.impl;

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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import eu.strasbourg.service.oidc.exception.NoSuchPublikUserException;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.model.impl.PublikUserImpl;
import eu.strasbourg.service.oidc.model.impl.PublikUserModelImpl;
import eu.strasbourg.service.oidc.service.persistence.PublikUserPersistence;

import java.io.Serializable;
import java.lang.reflect.Field;
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
 * The persistence implementation for the publik user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PublikUserPersistence
 * @see eu.strasbourg.service.oidc.service.persistence.PublikUserUtil
 * @generated
 */
@ProviderType
public class PublikUserPersistenceImpl extends BasePersistenceImpl<PublikUser>
	implements PublikUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PublikUserUtil} to access the publik user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PublikUserImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
			PublikUserModelImpl.FINDER_CACHE_ENABLED, PublikUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
			PublikUserModelImpl.FINDER_CACHE_ENABLED, PublikUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
			PublikUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
			PublikUserModelImpl.FINDER_CACHE_ENABLED, PublikUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
			PublikUserModelImpl.FINDER_CACHE_ENABLED, PublikUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PublikUserModelImpl.UUID_COLUMN_BITMASK |
			PublikUserModelImpl.LASTNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
			PublikUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the publik users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching publik users
	 */
	@Override
	public List<PublikUser> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the publik users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of publik users
	 * @param end the upper bound of the range of publik users (not inclusive)
	 * @return the range of matching publik users
	 */
	@Override
	public List<PublikUser> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the publik users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of publik users
	 * @param end the upper bound of the range of publik users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching publik users
	 */
	@Override
	public List<PublikUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<PublikUser> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the publik users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of publik users
	 * @param end the upper bound of the range of publik users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching publik users
	 */
	@Override
	public List<PublikUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<PublikUser> orderByComparator,
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

		List<PublikUser> list = null;

		if (retrieveFromCache) {
			list = (List<PublikUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PublikUser publikUser : list) {
					if (!Objects.equals(uuid, publikUser.getUuid())) {
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

			query.append(_SQL_SELECT_PUBLIKUSER_WHERE);

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
				query.append(PublikUserModelImpl.ORDER_BY_JPQL);
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
					list = (List<PublikUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PublikUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first publik user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching publik user
	 * @throws NoSuchPublikUserException if a matching publik user could not be found
	 */
	@Override
	public PublikUser findByUuid_First(String uuid,
		OrderByComparator<PublikUser> orderByComparator)
		throws NoSuchPublikUserException {
		PublikUser publikUser = fetchByUuid_First(uuid, orderByComparator);

		if (publikUser != null) {
			return publikUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPublikUserException(msg.toString());
	}

	/**
	 * Returns the first publik user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching publik user, or <code>null</code> if a matching publik user could not be found
	 */
	@Override
	public PublikUser fetchByUuid_First(String uuid,
		OrderByComparator<PublikUser> orderByComparator) {
		List<PublikUser> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last publik user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching publik user
	 * @throws NoSuchPublikUserException if a matching publik user could not be found
	 */
	@Override
	public PublikUser findByUuid_Last(String uuid,
		OrderByComparator<PublikUser> orderByComparator)
		throws NoSuchPublikUserException {
		PublikUser publikUser = fetchByUuid_Last(uuid, orderByComparator);

		if (publikUser != null) {
			return publikUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPublikUserException(msg.toString());
	}

	/**
	 * Returns the last publik user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching publik user, or <code>null</code> if a matching publik user could not be found
	 */
	@Override
	public PublikUser fetchByUuid_Last(String uuid,
		OrderByComparator<PublikUser> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PublikUser> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the publik users before and after the current publik user in the ordered set where uuid = &#63;.
	 *
	 * @param publikUserLiferayId the primary key of the current publik user
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next publik user
	 * @throws NoSuchPublikUserException if a publik user with the primary key could not be found
	 */
	@Override
	public PublikUser[] findByUuid_PrevAndNext(long publikUserLiferayId,
		String uuid, OrderByComparator<PublikUser> orderByComparator)
		throws NoSuchPublikUserException {
		PublikUser publikUser = findByPrimaryKey(publikUserLiferayId);

		Session session = null;

		try {
			session = openSession();

			PublikUser[] array = new PublikUserImpl[3];

			array[0] = getByUuid_PrevAndNext(session, publikUser, uuid,
					orderByComparator, true);

			array[1] = publikUser;

			array[2] = getByUuid_PrevAndNext(session, publikUser, uuid,
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

	protected PublikUser getByUuid_PrevAndNext(Session session,
		PublikUser publikUser, String uuid,
		OrderByComparator<PublikUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PUBLIKUSER_WHERE);

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
			query.append(PublikUserModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(publikUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PublikUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the publik users where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PublikUser publikUser : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(publikUser);
		}
	}

	/**
	 * Returns the number of publik users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching publik users
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PUBLIKUSER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "publikUser.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "publikUser.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(publikUser.uuid IS NULL OR publikUser.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_PUBLIKID = new FinderPath(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
			PublikUserModelImpl.FINDER_CACHE_ENABLED, PublikUserImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByPublikId",
			new String[] { String.class.getName() },
			PublikUserModelImpl.PUBLIKID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKID = new FinderPath(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
			PublikUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikId",
			new String[] { String.class.getName() });

	/**
	 * Returns the publik user where publikId = &#63; or throws a {@link NoSuchPublikUserException} if it could not be found.
	 *
	 * @param publikId the publik ID
	 * @return the matching publik user
	 * @throws NoSuchPublikUserException if a matching publik user could not be found
	 */
	@Override
	public PublikUser findByPublikId(String publikId)
		throws NoSuchPublikUserException {
		PublikUser publikUser = fetchByPublikId(publikId);

		if (publikUser == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("publikId=");
			msg.append(publikId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPublikUserException(msg.toString());
		}

		return publikUser;
	}

	/**
	 * Returns the publik user where publikId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param publikId the publik ID
	 * @return the matching publik user, or <code>null</code> if a matching publik user could not be found
	 */
	@Override
	public PublikUser fetchByPublikId(String publikId) {
		return fetchByPublikId(publikId, true);
	}

	/**
	 * Returns the publik user where publikId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param publikId the publik ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching publik user, or <code>null</code> if a matching publik user could not be found
	 */
	@Override
	public PublikUser fetchByPublikId(String publikId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { publikId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_PUBLIKID,
					finderArgs, this);
		}

		if (result instanceof PublikUser) {
			PublikUser publikUser = (PublikUser)result;

			if (!Objects.equals(publikId, publikUser.getPublikId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PUBLIKUSER_WHERE);

			boolean bindPublikId = false;

			if (publikId == null) {
				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_1);
			}
			else if (publikId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_3);
			}
			else {
				bindPublikId = true;

				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikId) {
					qPos.add(publikId);
				}

				List<PublikUser> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_PUBLIKID,
						finderArgs, list);
				}
				else {
					PublikUser publikUser = list.get(0);

					result = publikUser;

					cacheResult(publikUser);

					if ((publikUser.getPublikId() == null) ||
							!publikUser.getPublikId().equals(publikId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_PUBLIKID,
							finderArgs, publikUser);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_PUBLIKID,
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
			return (PublikUser)result;
		}
	}

	/**
	 * Removes the publik user where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 * @return the publik user that was removed
	 */
	@Override
	public PublikUser removeByPublikId(String publikId)
		throws NoSuchPublikUserException {
		PublikUser publikUser = findByPublikId(publikId);

		return remove(publikUser);
	}

	/**
	 * Returns the number of publik users where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching publik users
	 */
	@Override
	public int countByPublikId(String publikId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKID;

		Object[] finderArgs = new Object[] { publikId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PUBLIKUSER_WHERE);

			boolean bindPublikId = false;

			if (publikId == null) {
				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_1);
			}
			else if (publikId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_3);
			}
			else {
				bindPublikId = true;

				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikId) {
					qPos.add(publikId);
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

	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_1 = "publikUser.publikId IS NULL";
	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_2 = "publikUser.publikId = ?";
	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_3 = "(publikUser.publikId IS NULL OR publikUser.publikId = '')";

	public PublikUserPersistenceImpl() {
		setModelClass(PublikUser.class);

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
	 * Caches the publik user in the entity cache if it is enabled.
	 *
	 * @param publikUser the publik user
	 */
	@Override
	public void cacheResult(PublikUser publikUser) {
		entityCache.putResult(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
			PublikUserImpl.class, publikUser.getPrimaryKey(), publikUser);

		finderCache.putResult(FINDER_PATH_FETCH_BY_PUBLIKID,
			new Object[] { publikUser.getPublikId() }, publikUser);

		publikUser.resetOriginalValues();
	}

	/**
	 * Caches the publik users in the entity cache if it is enabled.
	 *
	 * @param publikUsers the publik users
	 */
	@Override
	public void cacheResult(List<PublikUser> publikUsers) {
		for (PublikUser publikUser : publikUsers) {
			if (entityCache.getResult(
						PublikUserModelImpl.ENTITY_CACHE_ENABLED,
						PublikUserImpl.class, publikUser.getPrimaryKey()) == null) {
				cacheResult(publikUser);
			}
			else {
				publikUser.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all publik users.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PublikUserImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the publik user.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PublikUser publikUser) {
		entityCache.removeResult(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
			PublikUserImpl.class, publikUser.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PublikUserModelImpl)publikUser, true);
	}

	@Override
	public void clearCache(List<PublikUser> publikUsers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PublikUser publikUser : publikUsers) {
			entityCache.removeResult(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
				PublikUserImpl.class, publikUser.getPrimaryKey());

			clearUniqueFindersCache((PublikUserModelImpl)publikUser, true);
		}
	}

	protected void cacheUniqueFindersCache(
		PublikUserModelImpl publikUserModelImpl) {
		Object[] args = new Object[] { publikUserModelImpl.getPublikId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_PUBLIKID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_PUBLIKID, args,
			publikUserModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PublikUserModelImpl publikUserModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { publikUserModelImpl.getPublikId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PUBLIKID, args);
		}

		if ((publikUserModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_PUBLIKID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					publikUserModelImpl.getOriginalPublikId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PUBLIKID, args);
		}
	}

	/**
	 * Creates a new publik user with the primary key. Does not add the publik user to the database.
	 *
	 * @param publikUserLiferayId the primary key for the new publik user
	 * @return the new publik user
	 */
	@Override
	public PublikUser create(long publikUserLiferayId) {
		PublikUser publikUser = new PublikUserImpl();

		publikUser.setNew(true);
		publikUser.setPrimaryKey(publikUserLiferayId);

		String uuid = PortalUUIDUtil.generate();

		publikUser.setUuid(uuid);

		return publikUser;
	}

	/**
	 * Removes the publik user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param publikUserLiferayId the primary key of the publik user
	 * @return the publik user that was removed
	 * @throws NoSuchPublikUserException if a publik user with the primary key could not be found
	 */
	@Override
	public PublikUser remove(long publikUserLiferayId)
		throws NoSuchPublikUserException {
		return remove((Serializable)publikUserLiferayId);
	}

	/**
	 * Removes the publik user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the publik user
	 * @return the publik user that was removed
	 * @throws NoSuchPublikUserException if a publik user with the primary key could not be found
	 */
	@Override
	public PublikUser remove(Serializable primaryKey)
		throws NoSuchPublikUserException {
		Session session = null;

		try {
			session = openSession();

			PublikUser publikUser = (PublikUser)session.get(PublikUserImpl.class,
					primaryKey);

			if (publikUser == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPublikUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(publikUser);
		}
		catch (NoSuchPublikUserException nsee) {
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
	protected PublikUser removeImpl(PublikUser publikUser) {
		publikUser = toUnwrappedModel(publikUser);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(publikUser)) {
				publikUser = (PublikUser)session.get(PublikUserImpl.class,
						publikUser.getPrimaryKeyObj());
			}

			if (publikUser != null) {
				session.delete(publikUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (publikUser != null) {
			clearCache(publikUser);
		}

		return publikUser;
	}

	@Override
	public PublikUser updateImpl(PublikUser publikUser) {
		publikUser = toUnwrappedModel(publikUser);

		boolean isNew = publikUser.isNew();

		PublikUserModelImpl publikUserModelImpl = (PublikUserModelImpl)publikUser;

		if (Validator.isNull(publikUser.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			publikUser.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (publikUser.getCreateDate() == null)) {
			if (serviceContext == null) {
				publikUser.setCreateDate(now);
			}
			else {
				publikUser.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!publikUserModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				publikUser.setModifiedDate(now);
			}
			else {
				publikUser.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (publikUser.isNew()) {
				session.save(publikUser);

				publikUser.setNew(false);
			}
			else {
				publikUser = (PublikUser)session.merge(publikUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PublikUserModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { publikUserModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((publikUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						publikUserModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { publikUserModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		entityCache.putResult(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
			PublikUserImpl.class, publikUser.getPrimaryKey(), publikUser, false);

		clearUniqueFindersCache(publikUserModelImpl, false);
		cacheUniqueFindersCache(publikUserModelImpl);

		publikUser.resetOriginalValues();

		return publikUser;
	}

	protected PublikUser toUnwrappedModel(PublikUser publikUser) {
		if (publikUser instanceof PublikUserImpl) {
			return publikUser;
		}

		PublikUserImpl publikUserImpl = new PublikUserImpl();

		publikUserImpl.setNew(publikUser.isNew());
		publikUserImpl.setPrimaryKey(publikUser.getPrimaryKey());

		publikUserImpl.setUuid(publikUser.getUuid());
		publikUserImpl.setPublikUserLiferayId(publikUser.getPublikUserLiferayId());
		publikUserImpl.setCreateDate(publikUser.getCreateDate());
		publikUserImpl.setModifiedDate(publikUser.getModifiedDate());
		publikUserImpl.setUserId(publikUser.getUserId());
		publikUserImpl.setUserName(publikUser.getUserName());
		publikUserImpl.setPublikId(publikUser.getPublikId());
		publikUserImpl.setAccessToken(publikUser.getAccessToken());
		publikUserImpl.setFirstName(publikUser.getFirstName());
		publikUserImpl.setLastName(publikUser.getLastName());
		publikUserImpl.setEmail(publikUser.getEmail());
		publikUserImpl.setMapConfig(publikUser.getMapConfig());
		publikUserImpl.setDisplayConfig(publikUser.getDisplayConfig());
		publikUserImpl.setPactSignature(publikUser.getPactSignature());
		publikUserImpl.setBanishDate(publikUser.getBanishDate());
		publikUserImpl.setBanishDescription(publikUser.getBanishDescription());

		return publikUserImpl;
	}

	/**
	 * Returns the publik user with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the publik user
	 * @return the publik user
	 * @throws NoSuchPublikUserException if a publik user with the primary key could not be found
	 */
	@Override
	public PublikUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPublikUserException {
		PublikUser publikUser = fetchByPrimaryKey(primaryKey);

		if (publikUser == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPublikUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return publikUser;
	}

	/**
	 * Returns the publik user with the primary key or throws a {@link NoSuchPublikUserException} if it could not be found.
	 *
	 * @param publikUserLiferayId the primary key of the publik user
	 * @return the publik user
	 * @throws NoSuchPublikUserException if a publik user with the primary key could not be found
	 */
	@Override
	public PublikUser findByPrimaryKey(long publikUserLiferayId)
		throws NoSuchPublikUserException {
		return findByPrimaryKey((Serializable)publikUserLiferayId);
	}

	/**
	 * Returns the publik user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the publik user
	 * @return the publik user, or <code>null</code> if a publik user with the primary key could not be found
	 */
	@Override
	public PublikUser fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
				PublikUserImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PublikUser publikUser = (PublikUser)serializable;

		if (publikUser == null) {
			Session session = null;

			try {
				session = openSession();

				publikUser = (PublikUser)session.get(PublikUserImpl.class,
						primaryKey);

				if (publikUser != null) {
					cacheResult(publikUser);
				}
				else {
					entityCache.putResult(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
						PublikUserImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
					PublikUserImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return publikUser;
	}

	/**
	 * Returns the publik user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param publikUserLiferayId the primary key of the publik user
	 * @return the publik user, or <code>null</code> if a publik user with the primary key could not be found
	 */
	@Override
	public PublikUser fetchByPrimaryKey(long publikUserLiferayId) {
		return fetchByPrimaryKey((Serializable)publikUserLiferayId);
	}

	@Override
	public Map<Serializable, PublikUser> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PublikUser> map = new HashMap<Serializable, PublikUser>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PublikUser publikUser = fetchByPrimaryKey(primaryKey);

			if (publikUser != null) {
				map.put(primaryKey, publikUser);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
					PublikUserImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PublikUser)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PUBLIKUSER_WHERE_PKS_IN);

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

			for (PublikUser publikUser : (List<PublikUser>)q.list()) {
				map.put(publikUser.getPrimaryKeyObj(), publikUser);

				cacheResult(publikUser);

				uncachedPrimaryKeys.remove(publikUser.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PublikUserModelImpl.ENTITY_CACHE_ENABLED,
					PublikUserImpl.class, primaryKey, nullModel);
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
	 * Returns all the publik users.
	 *
	 * @return the publik users
	 */
	@Override
	public List<PublikUser> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the publik users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of publik users
	 * @param end the upper bound of the range of publik users (not inclusive)
	 * @return the range of publik users
	 */
	@Override
	public List<PublikUser> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the publik users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of publik users
	 * @param end the upper bound of the range of publik users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of publik users
	 */
	@Override
	public List<PublikUser> findAll(int start, int end,
		OrderByComparator<PublikUser> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the publik users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of publik users
	 * @param end the upper bound of the range of publik users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of publik users
	 */
	@Override
	public List<PublikUser> findAll(int start, int end,
		OrderByComparator<PublikUser> orderByComparator,
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

		List<PublikUser> list = null;

		if (retrieveFromCache) {
			list = (List<PublikUser>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PUBLIKUSER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PUBLIKUSER;

				if (pagination) {
					sql = sql.concat(PublikUserModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PublikUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PublikUser>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the publik users from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PublikUser publikUser : findAll()) {
			remove(publikUser);
		}
	}

	/**
	 * Returns the number of publik users.
	 *
	 * @return the number of publik users
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PUBLIKUSER);

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
		return PublikUserModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the publik user persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PublikUserImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PUBLIKUSER = "SELECT publikUser FROM PublikUser publikUser";
	private static final String _SQL_SELECT_PUBLIKUSER_WHERE_PKS_IN = "SELECT publikUser FROM PublikUser publikUser WHERE publikUserLiferayId IN (";
	private static final String _SQL_SELECT_PUBLIKUSER_WHERE = "SELECT publikUser FROM PublikUser publikUser WHERE ";
	private static final String _SQL_COUNT_PUBLIKUSER = "SELECT COUNT(publikUser) FROM PublikUser publikUser";
	private static final String _SQL_COUNT_PUBLIKUSER_WHERE = "SELECT COUNT(publikUser) FROM PublikUser publikUser WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "publikUser.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PublikUser exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PublikUser exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PublikUserPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}