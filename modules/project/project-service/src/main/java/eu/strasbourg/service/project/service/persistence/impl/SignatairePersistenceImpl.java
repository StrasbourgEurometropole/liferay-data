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

package eu.strasbourg.service.project.service.persistence.impl;

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
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import eu.strasbourg.service.project.exception.NoSuchSignataireException;
import eu.strasbourg.service.project.model.Signataire;
import eu.strasbourg.service.project.model.impl.SignataireImpl;
import eu.strasbourg.service.project.model.impl.SignataireModelImpl;
import eu.strasbourg.service.project.service.persistence.SignatairePersistence;

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
 * The persistence implementation for the signataire service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see SignatairePersistence
 * @see eu.strasbourg.service.project.service.persistence.SignataireUtil
 * @generated
 */
@ProviderType
public class SignatairePersistenceImpl extends BasePersistenceImpl<Signataire>
	implements SignatairePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SignataireUtil} to access the signataire persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SignataireImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SignataireModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the signataires where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching signataires
	 */
	@Override
	public List<Signataire> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the signataires where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @return the range of matching signataires
	 */
	@Override
	public List<Signataire> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the signataires where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByUuid(String uuid, int start, int end,
		OrderByComparator<Signataire> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the signataires where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByUuid(String uuid, int start, int end,
		OrderByComparator<Signataire> orderByComparator,
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

		List<Signataire> list = null;

		if (retrieveFromCache) {
			list = (List<Signataire>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Signataire signataire : list) {
					if (!Objects.equals(uuid, signataire.getUuid())) {
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

			query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

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
				query.append(SignataireModelImpl.ORDER_BY_JPQL);
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
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first signataire in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByUuid_First(String uuid,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByUuid_First(uuid, orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the first signataire in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByUuid_First(String uuid,
		OrderByComparator<Signataire> orderByComparator) {
		List<Signataire> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last signataire in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByUuid_Last(String uuid,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByUuid_Last(uuid, orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the last signataire in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByUuid_Last(String uuid,
		OrderByComparator<Signataire> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Signataire> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the signataires before and after the current signataire in the ordered set where uuid = &#63;.
	 *
	 * @param signataireId the primary key of the current signataire
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next signataire
	 * @throws NoSuchSignataireException if a signataire with the primary key could not be found
	 */
	@Override
	public Signataire[] findByUuid_PrevAndNext(long signataireId, String uuid,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = findByPrimaryKey(signataireId);

		Session session = null;

		try {
			session = openSession();

			Signataire[] array = new SignataireImpl[3];

			array[0] = getByUuid_PrevAndNext(session, signataire, uuid,
					orderByComparator, true);

			array[1] = signataire;

			array[2] = getByUuid_PrevAndNext(session, signataire, uuid,
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

	protected Signataire getByUuid_PrevAndNext(Session session,
		Signataire signataire, String uuid,
		OrderByComparator<Signataire> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

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
			query.append(SignataireModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(signataire);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Signataire> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the signataires where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Signataire signataire : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(signataire);
		}
	}

	/**
	 * Returns the number of signataires where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching signataires
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIGNATAIRE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "signataire.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "signataire.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(signataire.uuid IS NULL OR signataire.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			SignataireModelImpl.UUID_COLUMN_BITMASK |
			SignataireModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the signataire where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSignataireException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByUUID_G(String uuid, long groupId)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByUUID_G(uuid, groupId);

		if (signataire == null) {
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

			throw new NoSuchSignataireException(msg.toString());
		}

		return signataire;
	}

	/**
	 * Returns the signataire where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the signataire where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Signataire) {
			Signataire signataire = (Signataire)result;

			if (!Objects.equals(uuid, signataire.getUuid()) ||
					(groupId != signataire.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

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

				List<Signataire> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Signataire signataire = list.get(0);

					result = signataire;

					cacheResult(signataire);

					if ((signataire.getUuid() == null) ||
							!signataire.getUuid().equals(uuid) ||
							(signataire.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, signataire);
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
			return (Signataire)result;
		}
	}

	/**
	 * Removes the signataire where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the signataire that was removed
	 */
	@Override
	public Signataire removeByUUID_G(String uuid, long groupId)
		throws NoSuchSignataireException {
		Signataire signataire = findByUUID_G(uuid, groupId);

		return remove(signataire);
	}

	/**
	 * Returns the number of signataires where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching signataires
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SIGNATAIRE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "signataire.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "signataire.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(signataire.uuid IS NULL OR signataire.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "signataire.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			SignataireModelImpl.UUID_COLUMN_BITMASK |
			SignataireModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the signataires where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching signataires
	 */
	@Override
	public List<Signataire> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the signataires where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @return the range of matching signataires
	 */
	@Override
	public List<Signataire> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the signataires where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Signataire> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the signataires where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Signataire> orderByComparator,
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

		List<Signataire> list = null;

		if (retrieveFromCache) {
			list = (List<Signataire>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Signataire signataire : list) {
					if (!Objects.equals(uuid, signataire.getUuid()) ||
							(companyId != signataire.getCompanyId())) {
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

			query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

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
				query.append(SignataireModelImpl.ORDER_BY_JPQL);
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
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the first signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Signataire> orderByComparator) {
		List<Signataire> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the last signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Signataire> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Signataire> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the signataires before and after the current signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param signataireId the primary key of the current signataire
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next signataire
	 * @throws NoSuchSignataireException if a signataire with the primary key could not be found
	 */
	@Override
	public Signataire[] findByUuid_C_PrevAndNext(long signataireId,
		String uuid, long companyId,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = findByPrimaryKey(signataireId);

		Session session = null;

		try {
			session = openSession();

			Signataire[] array = new SignataireImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, signataire, uuid,
					companyId, orderByComparator, true);

			array[1] = signataire;

			array[2] = getByUuid_C_PrevAndNext(session, signataire, uuid,
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

	protected Signataire getByUuid_C_PrevAndNext(Session session,
		Signataire signataire, String uuid, long companyId,
		OrderByComparator<Signataire> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

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
			query.append(SignataireModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(signataire);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Signataire> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the signataires where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Signataire signataire : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(signataire);
		}
	}

	/**
	 * Returns the number of signataires where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching signataires
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SIGNATAIRE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "signataire.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "signataire.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(signataire.uuid IS NULL OR signataire.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "signataire.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			SignataireModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the signataires where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching signataires
	 */
	@Override
	public List<Signataire> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the signataires where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @return the range of matching signataires
	 */
	@Override
	public List<Signataire> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the signataires where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Signataire> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the signataires where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Signataire> orderByComparator,
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

		List<Signataire> list = null;

		if (retrieveFromCache) {
			list = (List<Signataire>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Signataire signataire : list) {
					if ((groupId != signataire.getGroupId())) {
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

			query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SignataireModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first signataire in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByGroupId_First(long groupId,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByGroupId_First(groupId, orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the first signataire in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByGroupId_First(long groupId,
		OrderByComparator<Signataire> orderByComparator) {
		List<Signataire> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last signataire in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByGroupId_Last(long groupId,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByGroupId_Last(groupId, orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the last signataire in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByGroupId_Last(long groupId,
		OrderByComparator<Signataire> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Signataire> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the signataires before and after the current signataire in the ordered set where groupId = &#63;.
	 *
	 * @param signataireId the primary key of the current signataire
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next signataire
	 * @throws NoSuchSignataireException if a signataire with the primary key could not be found
	 */
	@Override
	public Signataire[] findByGroupId_PrevAndNext(long signataireId,
		long groupId, OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = findByPrimaryKey(signataireId);

		Session session = null;

		try {
			session = openSession();

			Signataire[] array = new SignataireImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, signataire, groupId,
					orderByComparator, true);

			array[1] = signataire;

			array[2] = getByGroupId_PrevAndNext(session, signataire, groupId,
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

	protected Signataire getByGroupId_PrevAndNext(Session session,
		Signataire signataire, long groupId,
		OrderByComparator<Signataire> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

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
			query.append(SignataireModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(signataire);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Signataire> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the signataires where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (Signataire signataire : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(signataire);
		}
	}

	/**
	 * Returns the number of signataires where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching signataires
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIGNATAIRE_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "signataire.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PETITION = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPetition",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITION =
		new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPetition",
			new String[] { Long.class.getName() },
			SignataireModelImpl.PETITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PETITION = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPetition",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the signataires where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @return the matching signataires
	 */
	@Override
	public List<Signataire> findByPetition(long petitionId) {
		return findByPetition(petitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the signataires where petitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @return the range of matching signataires
	 */
	@Override
	public List<Signataire> findByPetition(long petitionId, int start, int end) {
		return findByPetition(petitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the signataires where petitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByPetition(long petitionId, int start, int end,
		OrderByComparator<Signataire> orderByComparator) {
		return findByPetition(petitionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the signataires where petitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByPetition(long petitionId, int start, int end,
		OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITION;
			finderArgs = new Object[] { petitionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PETITION;
			finderArgs = new Object[] { petitionId, start, end, orderByComparator };
		}

		List<Signataire> list = null;

		if (retrieveFromCache) {
			list = (List<Signataire>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Signataire signataire : list) {
					if ((petitionId != signataire.getPetitionId())) {
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

			query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

			query.append(_FINDER_COLUMN_PETITION_PETITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SignataireModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(petitionId);

				if (!pagination) {
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first signataire in the ordered set where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByPetition_First(long petitionId,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByPetition_First(petitionId,
				orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("petitionId=");
		msg.append(petitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the first signataire in the ordered set where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByPetition_First(long petitionId,
		OrderByComparator<Signataire> orderByComparator) {
		List<Signataire> list = findByPetition(petitionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last signataire in the ordered set where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByPetition_Last(long petitionId,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByPetition_Last(petitionId,
				orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("petitionId=");
		msg.append(petitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the last signataire in the ordered set where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByPetition_Last(long petitionId,
		OrderByComparator<Signataire> orderByComparator) {
		int count = countByPetition(petitionId);

		if (count == 0) {
			return null;
		}

		List<Signataire> list = findByPetition(petitionId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the signataires before and after the current signataire in the ordered set where petitionId = &#63;.
	 *
	 * @param signataireId the primary key of the current signataire
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next signataire
	 * @throws NoSuchSignataireException if a signataire with the primary key could not be found
	 */
	@Override
	public Signataire[] findByPetition_PrevAndNext(long signataireId,
		long petitionId, OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = findByPrimaryKey(signataireId);

		Session session = null;

		try {
			session = openSession();

			Signataire[] array = new SignataireImpl[3];

			array[0] = getByPetition_PrevAndNext(session, signataire,
					petitionId, orderByComparator, true);

			array[1] = signataire;

			array[2] = getByPetition_PrevAndNext(session, signataire,
					petitionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Signataire getByPetition_PrevAndNext(Session session,
		Signataire signataire, long petitionId,
		OrderByComparator<Signataire> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

		query.append(_FINDER_COLUMN_PETITION_PETITIONID_2);

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
			query.append(SignataireModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(petitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(signataire);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Signataire> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the signataires where petitionId = &#63; from the database.
	 *
	 * @param petitionId the petition ID
	 */
	@Override
	public void removeByPetition(long petitionId) {
		for (Signataire signataire : findByPetition(petitionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(signataire);
		}
	}

	/**
	 * Returns the number of signataires where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @return the number of matching signataires
	 */
	@Override
	public int countByPetition(long petitionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PETITION;

		Object[] finderArgs = new Object[] { petitionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIGNATAIRE_WHERE);

			query.append(_FINDER_COLUMN_PETITION_PETITIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(petitionId);

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

	private static final String _FINDER_COLUMN_PETITION_PETITIONID_2 = "signataire.petitionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPublikUserId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikUserId",
			new String[] { String.class.getName() },
			SignataireModelImpl.PUBLIKUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKUSERID = new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikUserId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the signataires where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching signataires
	 */
	@Override
	public List<Signataire> findByPublikUserId(String publikUserId) {
		return findByPublikUserId(publikUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the signataires where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @return the range of matching signataires
	 */
	@Override
	public List<Signataire> findByPublikUserId(String publikUserId, int start,
		int end) {
		return findByPublikUserId(publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the signataires where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByPublikUserId(String publikUserId, int start,
		int end, OrderByComparator<Signataire> orderByComparator) {
		return findByPublikUserId(publikUserId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the signataires where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByPublikUserId(String publikUserId, int start,
		int end, OrderByComparator<Signataire> orderByComparator,
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

		List<Signataire> list = null;

		if (retrieveFromCache) {
			list = (List<Signataire>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Signataire signataire : list) {
					if (!Objects.equals(publikUserId,
								signataire.getPublikUserId())) {
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

			query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

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
				query.append(SignataireModelImpl.ORDER_BY_JPQL);
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
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first signataire in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByPublikUserId_First(String publikUserId,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByPublikUserId_First(publikUserId,
				orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the first signataire in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByPublikUserId_First(String publikUserId,
		OrderByComparator<Signataire> orderByComparator) {
		List<Signataire> list = findByPublikUserId(publikUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last signataire in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByPublikUserId_Last(String publikUserId,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByPublikUserId_Last(publikUserId,
				orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the last signataire in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByPublikUserId_Last(String publikUserId,
		OrderByComparator<Signataire> orderByComparator) {
		int count = countByPublikUserId(publikUserId);

		if (count == 0) {
			return null;
		}

		List<Signataire> list = findByPublikUserId(publikUserId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the signataires before and after the current signataire in the ordered set where publikUserId = &#63;.
	 *
	 * @param signataireId the primary key of the current signataire
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next signataire
	 * @throws NoSuchSignataireException if a signataire with the primary key could not be found
	 */
	@Override
	public Signataire[] findByPublikUserId_PrevAndNext(long signataireId,
		String publikUserId, OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = findByPrimaryKey(signataireId);

		Session session = null;

		try {
			session = openSession();

			Signataire[] array = new SignataireImpl[3];

			array[0] = getByPublikUserId_PrevAndNext(session, signataire,
					publikUserId, orderByComparator, true);

			array[1] = signataire;

			array[2] = getByPublikUserId_PrevAndNext(session, signataire,
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

	protected Signataire getByPublikUserId_PrevAndNext(Session session,
		Signataire signataire, String publikUserId,
		OrderByComparator<Signataire> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

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
			query.append(SignataireModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(signataire);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Signataire> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the signataires where publikUserId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 */
	@Override
	public void removeByPublikUserId(String publikUserId) {
		for (Signataire signataire : findByPublikUserId(publikUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(signataire);
		}
	}

	/**
	 * Returns the number of signataires where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the number of matching signataires
	 */
	@Override
	public int countByPublikUserId(String publikUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKUSERID;

		Object[] finderArgs = new Object[] { publikUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIGNATAIRE_WHERE);

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

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1 = "signataire.publikUserId IS NULL";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2 = "signataire.publikUserId = ?";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3 = "(signataire.publikUserId IS NULL OR signataire.publikUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PETITIONIDANDSIGNATAIRENAME =
		new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPetitionIdAndSignataireName",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITIONIDANDSIGNATAIRENAME =
		new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPetitionIdAndSignataireName",
			new String[] { Long.class.getName(), String.class.getName() },
			SignataireModelImpl.PETITIONID_COLUMN_BITMASK |
			SignataireModelImpl.SIGNATAIRENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PETITIONIDANDSIGNATAIRENAME =
		new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPetitionIdAndSignataireName",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the signataires where petitionId = &#63; and signataireName = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param signataireName the signataire name
	 * @return the matching signataires
	 */
	@Override
	public List<Signataire> findByPetitionIdAndSignataireName(long petitionId,
		String signataireName) {
		return findByPetitionIdAndSignataireName(petitionId, signataireName,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the signataires where petitionId = &#63; and signataireName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param signataireName the signataire name
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @return the range of matching signataires
	 */
	@Override
	public List<Signataire> findByPetitionIdAndSignataireName(long petitionId,
		String signataireName, int start, int end) {
		return findByPetitionIdAndSignataireName(petitionId, signataireName,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the signataires where petitionId = &#63; and signataireName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param signataireName the signataire name
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByPetitionIdAndSignataireName(long petitionId,
		String signataireName, int start, int end,
		OrderByComparator<Signataire> orderByComparator) {
		return findByPetitionIdAndSignataireName(petitionId, signataireName,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the signataires where petitionId = &#63; and signataireName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param signataireName the signataire name
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByPetitionIdAndSignataireName(long petitionId,
		String signataireName, int start, int end,
		OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITIONIDANDSIGNATAIRENAME;
			finderArgs = new Object[] { petitionId, signataireName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PETITIONIDANDSIGNATAIRENAME;
			finderArgs = new Object[] {
					petitionId, signataireName,
					
					start, end, orderByComparator
				};
		}

		List<Signataire> list = null;

		if (retrieveFromCache) {
			list = (List<Signataire>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Signataire signataire : list) {
					if ((petitionId != signataire.getPetitionId()) ||
							!Objects.equals(signataireName,
								signataire.getSignataireName())) {
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

			query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

			query.append(_FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_PETITIONID_2);

			boolean bindSignataireName = false;

			if (signataireName == null) {
				query.append(_FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_SIGNATAIRENAME_1);
			}
			else if (signataireName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_SIGNATAIRENAME_3);
			}
			else {
				bindSignataireName = true;

				query.append(_FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_SIGNATAIRENAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SignataireModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(petitionId);

				if (bindSignataireName) {
					qPos.add(signataireName);
				}

				if (!pagination) {
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param signataireName the signataire name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByPetitionIdAndSignataireName_First(long petitionId,
		String signataireName, OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByPetitionIdAndSignataireName_First(petitionId,
				signataireName, orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("petitionId=");
		msg.append(petitionId);

		msg.append(", signataireName=");
		msg.append(signataireName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the first signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param signataireName the signataire name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByPetitionIdAndSignataireName_First(
		long petitionId, String signataireName,
		OrderByComparator<Signataire> orderByComparator) {
		List<Signataire> list = findByPetitionIdAndSignataireName(petitionId,
				signataireName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param signataireName the signataire name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByPetitionIdAndSignataireName_Last(long petitionId,
		String signataireName, OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByPetitionIdAndSignataireName_Last(petitionId,
				signataireName, orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("petitionId=");
		msg.append(petitionId);

		msg.append(", signataireName=");
		msg.append(signataireName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the last signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param signataireName the signataire name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByPetitionIdAndSignataireName_Last(long petitionId,
		String signataireName, OrderByComparator<Signataire> orderByComparator) {
		int count = countByPetitionIdAndSignataireName(petitionId,
				signataireName);

		if (count == 0) {
			return null;
		}

		List<Signataire> list = findByPetitionIdAndSignataireName(petitionId,
				signataireName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the signataires before and after the current signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	 *
	 * @param signataireId the primary key of the current signataire
	 * @param petitionId the petition ID
	 * @param signataireName the signataire name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next signataire
	 * @throws NoSuchSignataireException if a signataire with the primary key could not be found
	 */
	@Override
	public Signataire[] findByPetitionIdAndSignataireName_PrevAndNext(
		long signataireId, long petitionId, String signataireName,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = findByPrimaryKey(signataireId);

		Session session = null;

		try {
			session = openSession();

			Signataire[] array = new SignataireImpl[3];

			array[0] = getByPetitionIdAndSignataireName_PrevAndNext(session,
					signataire, petitionId, signataireName, orderByComparator,
					true);

			array[1] = signataire;

			array[2] = getByPetitionIdAndSignataireName_PrevAndNext(session,
					signataire, petitionId, signataireName, orderByComparator,
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

	protected Signataire getByPetitionIdAndSignataireName_PrevAndNext(
		Session session, Signataire signataire, long petitionId,
		String signataireName, OrderByComparator<Signataire> orderByComparator,
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

		query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

		query.append(_FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_PETITIONID_2);

		boolean bindSignataireName = false;

		if (signataireName == null) {
			query.append(_FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_SIGNATAIRENAME_1);
		}
		else if (signataireName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_SIGNATAIRENAME_3);
		}
		else {
			bindSignataireName = true;

			query.append(_FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_SIGNATAIRENAME_2);
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
			query.append(SignataireModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(petitionId);

		if (bindSignataireName) {
			qPos.add(signataireName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(signataire);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Signataire> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the signataires where petitionId = &#63; and signataireName = &#63; from the database.
	 *
	 * @param petitionId the petition ID
	 * @param signataireName the signataire name
	 */
	@Override
	public void removeByPetitionIdAndSignataireName(long petitionId,
		String signataireName) {
		for (Signataire signataire : findByPetitionIdAndSignataireName(
				petitionId, signataireName, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(signataire);
		}
	}

	/**
	 * Returns the number of signataires where petitionId = &#63; and signataireName = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param signataireName the signataire name
	 * @return the number of matching signataires
	 */
	@Override
	public int countByPetitionIdAndSignataireName(long petitionId,
		String signataireName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PETITIONIDANDSIGNATAIRENAME;

		Object[] finderArgs = new Object[] { petitionId, signataireName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SIGNATAIRE_WHERE);

			query.append(_FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_PETITIONID_2);

			boolean bindSignataireName = false;

			if (signataireName == null) {
				query.append(_FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_SIGNATAIRENAME_1);
			}
			else if (signataireName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_SIGNATAIRENAME_3);
			}
			else {
				bindSignataireName = true;

				query.append(_FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_SIGNATAIRENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(petitionId);

				if (bindSignataireName) {
					qPos.add(signataireName);
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

	private static final String _FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_PETITIONID_2 =
		"signataire.petitionId = ? AND ";
	private static final String _FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_SIGNATAIRENAME_1 =
		"signataire.signataireName IS NULL";
	private static final String _FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_SIGNATAIRENAME_2 =
		"signataire.signataireName = ?";
	private static final String _FINDER_COLUMN_PETITIONIDANDSIGNATAIRENAME_SIGNATAIRENAME_3 =
		"(signataire.signataireName IS NULL OR signataire.signataireName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PETITIONIDANDPUBLIKUSERID =
		new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPetitionIdAndPublikUserId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITIONIDANDPUBLIKUSERID =
		new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, SignataireImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPetitionIdAndPublikUserId",
			new String[] { Long.class.getName(), String.class.getName() },
			SignataireModelImpl.PETITIONID_COLUMN_BITMASK |
			SignataireModelImpl.PUBLIKUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PETITIONIDANDPUBLIKUSERID =
		new FinderPath(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPetitionIdAndPublikUserId",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the signataires where petitionId = &#63; and publikUserId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param publikUserId the publik user ID
	 * @return the matching signataires
	 */
	@Override
	public List<Signataire> findByPetitionIdAndPublikUserId(long petitionId,
		String publikUserId) {
		return findByPetitionIdAndPublikUserId(petitionId, publikUserId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the signataires where petitionId = &#63; and publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @return the range of matching signataires
	 */
	@Override
	public List<Signataire> findByPetitionIdAndPublikUserId(long petitionId,
		String publikUserId, int start, int end) {
		return findByPetitionIdAndPublikUserId(petitionId, publikUserId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the signataires where petitionId = &#63; and publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByPetitionIdAndPublikUserId(long petitionId,
		String publikUserId, int start, int end,
		OrderByComparator<Signataire> orderByComparator) {
		return findByPetitionIdAndPublikUserId(petitionId, publikUserId, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the signataires where petitionId = &#63; and publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching signataires
	 */
	@Override
	public List<Signataire> findByPetitionIdAndPublikUserId(long petitionId,
		String publikUserId, int start, int end,
		OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITIONIDANDPUBLIKUSERID;
			finderArgs = new Object[] { petitionId, publikUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PETITIONIDANDPUBLIKUSERID;
			finderArgs = new Object[] {
					petitionId, publikUserId,
					
					start, end, orderByComparator
				};
		}

		List<Signataire> list = null;

		if (retrieveFromCache) {
			list = (List<Signataire>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Signataire signataire : list) {
					if ((petitionId != signataire.getPetitionId()) ||
							!Objects.equals(publikUserId,
								signataire.getPublikUserId())) {
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

			query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

			query.append(_FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PETITIONID_2);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PUBLIKUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SignataireModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(petitionId);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
				}

				if (!pagination) {
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first signataire in the ordered set where petitionId = &#63; and publikUserId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByPetitionIdAndPublikUserId_First(long petitionId,
		String publikUserId, OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByPetitionIdAndPublikUserId_First(petitionId,
				publikUserId, orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("petitionId=");
		msg.append(petitionId);

		msg.append(", publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the first signataire in the ordered set where petitionId = &#63; and publikUserId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByPetitionIdAndPublikUserId_First(long petitionId,
		String publikUserId, OrderByComparator<Signataire> orderByComparator) {
		List<Signataire> list = findByPetitionIdAndPublikUserId(petitionId,
				publikUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last signataire in the ordered set where petitionId = &#63; and publikUserId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire
	 * @throws NoSuchSignataireException if a matching signataire could not be found
	 */
	@Override
	public Signataire findByPetitionIdAndPublikUserId_Last(long petitionId,
		String publikUserId, OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByPetitionIdAndPublikUserId_Last(petitionId,
				publikUserId, orderByComparator);

		if (signataire != null) {
			return signataire;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("petitionId=");
		msg.append(petitionId);

		msg.append(", publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSignataireException(msg.toString());
	}

	/**
	 * Returns the last signataire in the ordered set where petitionId = &#63; and publikUserId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	 */
	@Override
	public Signataire fetchByPetitionIdAndPublikUserId_Last(long petitionId,
		String publikUserId, OrderByComparator<Signataire> orderByComparator) {
		int count = countByPetitionIdAndPublikUserId(petitionId, publikUserId);

		if (count == 0) {
			return null;
		}

		List<Signataire> list = findByPetitionIdAndPublikUserId(petitionId,
				publikUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the signataires before and after the current signataire in the ordered set where petitionId = &#63; and publikUserId = &#63;.
	 *
	 * @param signataireId the primary key of the current signataire
	 * @param petitionId the petition ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next signataire
	 * @throws NoSuchSignataireException if a signataire with the primary key could not be found
	 */
	@Override
	public Signataire[] findByPetitionIdAndPublikUserId_PrevAndNext(
		long signataireId, long petitionId, String publikUserId,
		OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException {
		Signataire signataire = findByPrimaryKey(signataireId);

		Session session = null;

		try {
			session = openSession();

			Signataire[] array = new SignataireImpl[3];

			array[0] = getByPetitionIdAndPublikUserId_PrevAndNext(session,
					signataire, petitionId, publikUserId, orderByComparator,
					true);

			array[1] = signataire;

			array[2] = getByPetitionIdAndPublikUserId_PrevAndNext(session,
					signataire, petitionId, publikUserId, orderByComparator,
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

	protected Signataire getByPetitionIdAndPublikUserId_PrevAndNext(
		Session session, Signataire signataire, long petitionId,
		String publikUserId, OrderByComparator<Signataire> orderByComparator,
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

		query.append(_SQL_SELECT_SIGNATAIRE_WHERE);

		query.append(_FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PETITIONID_2);

		boolean bindPublikUserId = false;

		if (publikUserId == null) {
			query.append(_FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PUBLIKUSERID_1);
		}
		else if (publikUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PUBLIKUSERID_3);
		}
		else {
			bindPublikUserId = true;

			query.append(_FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PUBLIKUSERID_2);
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
			query.append(SignataireModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(petitionId);

		if (bindPublikUserId) {
			qPos.add(publikUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(signataire);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Signataire> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the signataires where petitionId = &#63; and publikUserId = &#63; from the database.
	 *
	 * @param petitionId the petition ID
	 * @param publikUserId the publik user ID
	 */
	@Override
	public void removeByPetitionIdAndPublikUserId(long petitionId,
		String publikUserId) {
		for (Signataire signataire : findByPetitionIdAndPublikUserId(
				petitionId, publikUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(signataire);
		}
	}

	/**
	 * Returns the number of signataires where petitionId = &#63; and publikUserId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param publikUserId the publik user ID
	 * @return the number of matching signataires
	 */
	@Override
	public int countByPetitionIdAndPublikUserId(long petitionId,
		String publikUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PETITIONIDANDPUBLIKUSERID;

		Object[] finderArgs = new Object[] { petitionId, publikUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SIGNATAIRE_WHERE);

			query.append(_FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PETITIONID_2);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PUBLIKUSERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(petitionId);

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

	private static final String _FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PETITIONID_2 =
		"signataire.petitionId = ? AND ";
	private static final String _FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PUBLIKUSERID_1 =
		"signataire.publikUserId IS NULL";
	private static final String _FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PUBLIKUSERID_2 =
		"signataire.publikUserId = ?";
	private static final String _FINDER_COLUMN_PETITIONIDANDPUBLIKUSERID_PUBLIKUSERID_3 =
		"(signataire.publikUserId IS NULL OR signataire.publikUserId = '')";

	public SignatairePersistenceImpl() {
		setModelClass(Signataire.class);

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
	 * Caches the signataire in the entity cache if it is enabled.
	 *
	 * @param signataire the signataire
	 */
	@Override
	public void cacheResult(Signataire signataire) {
		entityCache.putResult(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireImpl.class, signataire.getPrimaryKey(), signataire);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { signataire.getUuid(), signataire.getGroupId() },
			signataire);

		signataire.resetOriginalValues();
	}

	/**
	 * Caches the signataires in the entity cache if it is enabled.
	 *
	 * @param signataires the signataires
	 */
	@Override
	public void cacheResult(List<Signataire> signataires) {
		for (Signataire signataire : signataires) {
			if (entityCache.getResult(
						SignataireModelImpl.ENTITY_CACHE_ENABLED,
						SignataireImpl.class, signataire.getPrimaryKey()) == null) {
				cacheResult(signataire);
			}
			else {
				signataire.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all signataires.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SignataireImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the signataire.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Signataire signataire) {
		entityCache.removeResult(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireImpl.class, signataire.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SignataireModelImpl)signataire, true);
	}

	@Override
	public void clearCache(List<Signataire> signataires) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Signataire signataire : signataires) {
			entityCache.removeResult(SignataireModelImpl.ENTITY_CACHE_ENABLED,
				SignataireImpl.class, signataire.getPrimaryKey());

			clearUniqueFindersCache((SignataireModelImpl)signataire, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SignataireModelImpl signataireModelImpl) {
		Object[] args = new Object[] {
				signataireModelImpl.getUuid(), signataireModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			signataireModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SignataireModelImpl signataireModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					signataireModelImpl.getUuid(),
					signataireModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((signataireModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					signataireModelImpl.getOriginalUuid(),
					signataireModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new signataire with the primary key. Does not add the signataire to the database.
	 *
	 * @param signataireId the primary key for the new signataire
	 * @return the new signataire
	 */
	@Override
	public Signataire create(long signataireId) {
		Signataire signataire = new SignataireImpl();

		signataire.setNew(true);
		signataire.setPrimaryKey(signataireId);

		String uuid = PortalUUIDUtil.generate();

		signataire.setUuid(uuid);

		signataire.setCompanyId(companyProvider.getCompanyId());

		return signataire;
	}

	/**
	 * Removes the signataire with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param signataireId the primary key of the signataire
	 * @return the signataire that was removed
	 * @throws NoSuchSignataireException if a signataire with the primary key could not be found
	 */
	@Override
	public Signataire remove(long signataireId)
		throws NoSuchSignataireException {
		return remove((Serializable)signataireId);
	}

	/**
	 * Removes the signataire with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the signataire
	 * @return the signataire that was removed
	 * @throws NoSuchSignataireException if a signataire with the primary key could not be found
	 */
	@Override
	public Signataire remove(Serializable primaryKey)
		throws NoSuchSignataireException {
		Session session = null;

		try {
			session = openSession();

			Signataire signataire = (Signataire)session.get(SignataireImpl.class,
					primaryKey);

			if (signataire == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSignataireException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(signataire);
		}
		catch (NoSuchSignataireException nsee) {
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
	protected Signataire removeImpl(Signataire signataire) {
		signataire = toUnwrappedModel(signataire);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(signataire)) {
				signataire = (Signataire)session.get(SignataireImpl.class,
						signataire.getPrimaryKeyObj());
			}

			if (signataire != null) {
				session.delete(signataire);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (signataire != null) {
			clearCache(signataire);
		}

		return signataire;
	}

	@Override
	public Signataire updateImpl(Signataire signataire) {
		signataire = toUnwrappedModel(signataire);

		boolean isNew = signataire.isNew();

		SignataireModelImpl signataireModelImpl = (SignataireModelImpl)signataire;

		if (Validator.isNull(signataire.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			signataire.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (signataire.getCreateDate() == null)) {
			if (serviceContext == null) {
				signataire.setCreateDate(now);
			}
			else {
				signataire.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!signataireModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				signataire.setModifiedDate(now);
			}
			else {
				signataire.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (signataire.isNew()) {
				session.save(signataire);

				signataire.setNew(false);
			}
			else {
				signataire = (Signataire)session.merge(signataire);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SignataireModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { signataireModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					signataireModelImpl.getUuid(),
					signataireModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { signataireModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] { signataireModelImpl.getPetitionId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PETITION, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITION,
				args);

			args = new Object[] { signataireModelImpl.getPublikUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
				args);

			args = new Object[] {
					signataireModelImpl.getPetitionId(),
					signataireModelImpl.getSignataireName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PETITIONIDANDSIGNATAIRENAME,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITIONIDANDSIGNATAIRENAME,
				args);

			args = new Object[] {
					signataireModelImpl.getPetitionId(),
					signataireModelImpl.getPublikUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PETITIONIDANDPUBLIKUSERID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITIONIDANDPUBLIKUSERID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((signataireModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						signataireModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { signataireModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((signataireModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						signataireModelImpl.getOriginalUuid(),
						signataireModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						signataireModelImpl.getUuid(),
						signataireModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((signataireModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						signataireModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { signataireModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((signataireModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						signataireModelImpl.getOriginalPetitionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PETITION, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITION,
					args);

				args = new Object[] { signataireModelImpl.getPetitionId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PETITION, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITION,
					args);
			}

			if ((signataireModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						signataireModelImpl.getOriginalPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);

				args = new Object[] { signataireModelImpl.getPublikUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);
			}

			if ((signataireModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITIONIDANDSIGNATAIRENAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						signataireModelImpl.getOriginalPetitionId(),
						signataireModelImpl.getOriginalSignataireName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PETITIONIDANDSIGNATAIRENAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITIONIDANDSIGNATAIRENAME,
					args);

				args = new Object[] {
						signataireModelImpl.getPetitionId(),
						signataireModelImpl.getSignataireName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PETITIONIDANDSIGNATAIRENAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITIONIDANDSIGNATAIRENAME,
					args);
			}

			if ((signataireModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITIONIDANDPUBLIKUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						signataireModelImpl.getOriginalPetitionId(),
						signataireModelImpl.getOriginalPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PETITIONIDANDPUBLIKUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITIONIDANDPUBLIKUSERID,
					args);

				args = new Object[] {
						signataireModelImpl.getPetitionId(),
						signataireModelImpl.getPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PETITIONIDANDPUBLIKUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PETITIONIDANDPUBLIKUSERID,
					args);
			}
		}

		entityCache.putResult(SignataireModelImpl.ENTITY_CACHE_ENABLED,
			SignataireImpl.class, signataire.getPrimaryKey(), signataire, false);

		clearUniqueFindersCache(signataireModelImpl, false);
		cacheUniqueFindersCache(signataireModelImpl);

		signataire.resetOriginalValues();

		return signataire;
	}

	protected Signataire toUnwrappedModel(Signataire signataire) {
		if (signataire instanceof SignataireImpl) {
			return signataire;
		}

		SignataireImpl signataireImpl = new SignataireImpl();

		signataireImpl.setNew(signataire.isNew());
		signataireImpl.setPrimaryKey(signataire.getPrimaryKey());

		signataireImpl.setUuid(signataire.getUuid());
		signataireImpl.setSignataireId(signataire.getSignataireId());
		signataireImpl.setGroupId(signataire.getGroupId());
		signataireImpl.setCompanyId(signataire.getCompanyId());
		signataireImpl.setUserId(signataire.getUserId());
		signataireImpl.setUserName(signataire.getUserName());
		signataireImpl.setCreateDate(signataire.getCreateDate());
		signataireImpl.setModifiedDate(signataire.getModifiedDate());
		signataireImpl.setStatus(signataire.getStatus());
		signataireImpl.setStatusByUserId(signataire.getStatusByUserId());
		signataireImpl.setStatusByUserName(signataire.getStatusByUserName());
		signataireImpl.setStatusDate(signataire.getStatusDate());
		signataireImpl.setSignataireName(signataire.getSignataireName());
		signataireImpl.setSignataireFirstname(signataire.getSignataireFirstname());
		signataireImpl.setBirthday(signataire.getBirthday());
		signataireImpl.setAddress(signataire.getAddress());
		signataireImpl.setMail(signataire.getMail());
		signataireImpl.setPostalCode(signataire.getPostalCode());
		signataireImpl.setMobilePhone(signataire.getMobilePhone());
		signataireImpl.setPhone(signataire.getPhone());
		signataireImpl.setCity(signataire.getCity());
		signataireImpl.setSignatureDate(signataire.getSignatureDate());
		signataireImpl.setPublikUserId(signataire.getPublikUserId());
		signataireImpl.setPetitionId(signataire.getPetitionId());

		return signataireImpl;
	}

	/**
	 * Returns the signataire with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the signataire
	 * @return the signataire
	 * @throws NoSuchSignataireException if a signataire with the primary key could not be found
	 */
	@Override
	public Signataire findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSignataireException {
		Signataire signataire = fetchByPrimaryKey(primaryKey);

		if (signataire == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSignataireException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return signataire;
	}

	/**
	 * Returns the signataire with the primary key or throws a {@link NoSuchSignataireException} if it could not be found.
	 *
	 * @param signataireId the primary key of the signataire
	 * @return the signataire
	 * @throws NoSuchSignataireException if a signataire with the primary key could not be found
	 */
	@Override
	public Signataire findByPrimaryKey(long signataireId)
		throws NoSuchSignataireException {
		return findByPrimaryKey((Serializable)signataireId);
	}

	/**
	 * Returns the signataire with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the signataire
	 * @return the signataire, or <code>null</code> if a signataire with the primary key could not be found
	 */
	@Override
	public Signataire fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SignataireModelImpl.ENTITY_CACHE_ENABLED,
				SignataireImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Signataire signataire = (Signataire)serializable;

		if (signataire == null) {
			Session session = null;

			try {
				session = openSession();

				signataire = (Signataire)session.get(SignataireImpl.class,
						primaryKey);

				if (signataire != null) {
					cacheResult(signataire);
				}
				else {
					entityCache.putResult(SignataireModelImpl.ENTITY_CACHE_ENABLED,
						SignataireImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SignataireModelImpl.ENTITY_CACHE_ENABLED,
					SignataireImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return signataire;
	}

	/**
	 * Returns the signataire with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param signataireId the primary key of the signataire
	 * @return the signataire, or <code>null</code> if a signataire with the primary key could not be found
	 */
	@Override
	public Signataire fetchByPrimaryKey(long signataireId) {
		return fetchByPrimaryKey((Serializable)signataireId);
	}

	@Override
	public Map<Serializable, Signataire> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Signataire> map = new HashMap<Serializable, Signataire>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Signataire signataire = fetchByPrimaryKey(primaryKey);

			if (signataire != null) {
				map.put(primaryKey, signataire);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SignataireModelImpl.ENTITY_CACHE_ENABLED,
					SignataireImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Signataire)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SIGNATAIRE_WHERE_PKS_IN);

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

			for (Signataire signataire : (List<Signataire>)q.list()) {
				map.put(signataire.getPrimaryKeyObj(), signataire);

				cacheResult(signataire);

				uncachedPrimaryKeys.remove(signataire.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SignataireModelImpl.ENTITY_CACHE_ENABLED,
					SignataireImpl.class, primaryKey, nullModel);
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
	 * Returns all the signataires.
	 *
	 * @return the signataires
	 */
	@Override
	public List<Signataire> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the signataires.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @return the range of signataires
	 */
	@Override
	public List<Signataire> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the signataires.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of signataires
	 */
	@Override
	public List<Signataire> findAll(int start, int end,
		OrderByComparator<Signataire> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the signataires.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of signataires
	 * @param end the upper bound of the range of signataires (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of signataires
	 */
	@Override
	public List<Signataire> findAll(int start, int end,
		OrderByComparator<Signataire> orderByComparator,
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

		List<Signataire> list = null;

		if (retrieveFromCache) {
			list = (List<Signataire>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SIGNATAIRE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SIGNATAIRE;

				if (pagination) {
					sql = sql.concat(SignataireModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Signataire>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the signataires from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Signataire signataire : findAll()) {
			remove(signataire);
		}
	}

	/**
	 * Returns the number of signataires.
	 *
	 * @return the number of signataires
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SIGNATAIRE);

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
		return SignataireModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the signataire persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SignataireImpl.class.getName());
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
	private static final String _SQL_SELECT_SIGNATAIRE = "SELECT signataire FROM Signataire signataire";
	private static final String _SQL_SELECT_SIGNATAIRE_WHERE_PKS_IN = "SELECT signataire FROM Signataire signataire WHERE signataireId IN (";
	private static final String _SQL_SELECT_SIGNATAIRE_WHERE = "SELECT signataire FROM Signataire signataire WHERE ";
	private static final String _SQL_COUNT_SIGNATAIRE = "SELECT COUNT(signataire) FROM Signataire signataire";
	private static final String _SQL_COUNT_SIGNATAIRE_WHERE = "SELECT COUNT(signataire) FROM Signataire signataire WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "signataire.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Signataire exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Signataire exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SignatairePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}