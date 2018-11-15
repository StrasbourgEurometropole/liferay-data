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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.project.exception.NoSuchInitiativeHelpException;
import eu.strasbourg.service.project.model.InitiativeHelp;
import eu.strasbourg.service.project.model.impl.InitiativeHelpImpl;
import eu.strasbourg.service.project.model.impl.InitiativeHelpModelImpl;
import eu.strasbourg.service.project.service.persistence.InitiativeHelpPersistence;

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
 * The persistence implementation for the initiative help service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see InitiativeHelpPersistence
 * @see eu.strasbourg.service.project.service.persistence.InitiativeHelpUtil
 * @generated
 */
@ProviderType
public class InitiativeHelpPersistenceImpl extends BasePersistenceImpl<InitiativeHelp>
	implements InitiativeHelpPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link InitiativeHelpUtil} to access the initiative help persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = InitiativeHelpImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpModelImpl.FINDER_CACHE_ENABLED,
			InitiativeHelpImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpModelImpl.FINDER_CACHE_ENABLED,
			InitiativeHelpImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpModelImpl.FINDER_CACHE_ENABLED,
			InitiativeHelpImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPublikUserId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID =
		new FinderPath(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpModelImpl.FINDER_CACHE_ENABLED,
			InitiativeHelpImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikUserId",
			new String[] { String.class.getName() },
			InitiativeHelpModelImpl.PUBLIKUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKUSERID = new FinderPath(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikUserId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the initiative helps where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching initiative helps
	 */
	@Override
	public List<InitiativeHelp> findByPublikUserId(String publikUserId) {
		return findByPublikUserId(publikUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the initiative helps where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of initiative helps
	 * @param end the upper bound of the range of initiative helps (not inclusive)
	 * @return the range of matching initiative helps
	 */
	@Override
	public List<InitiativeHelp> findByPublikUserId(String publikUserId,
		int start, int end) {
		return findByPublikUserId(publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the initiative helps where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of initiative helps
	 * @param end the upper bound of the range of initiative helps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching initiative helps
	 */
	@Override
	public List<InitiativeHelp> findByPublikUserId(String publikUserId,
		int start, int end, OrderByComparator<InitiativeHelp> orderByComparator) {
		return findByPublikUserId(publikUserId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the initiative helps where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of initiative helps
	 * @param end the upper bound of the range of initiative helps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching initiative helps
	 */
	@Override
	public List<InitiativeHelp> findByPublikUserId(String publikUserId,
		int start, int end,
		OrderByComparator<InitiativeHelp> orderByComparator,
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

		List<InitiativeHelp> list = null;

		if (retrieveFromCache) {
			list = (List<InitiativeHelp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (InitiativeHelp initiativeHelp : list) {
					if (!Objects.equals(publikUserId,
								initiativeHelp.getPublikUserId())) {
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

			query.append(_SQL_SELECT_INITIATIVEHELP_WHERE);

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
				query.append(InitiativeHelpModelImpl.ORDER_BY_JPQL);
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
					list = (List<InitiativeHelp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<InitiativeHelp>)QueryUtil.list(q,
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
	 * Returns the first initiative help in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching initiative help
	 * @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	 */
	@Override
	public InitiativeHelp findByPublikUserId_First(String publikUserId,
		OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException {
		InitiativeHelp initiativeHelp = fetchByPublikUserId_First(publikUserId,
				orderByComparator);

		if (initiativeHelp != null) {
			return initiativeHelp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInitiativeHelpException(msg.toString());
	}

	/**
	 * Returns the first initiative help in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching initiative help, or <code>null</code> if a matching initiative help could not be found
	 */
	@Override
	public InitiativeHelp fetchByPublikUserId_First(String publikUserId,
		OrderByComparator<InitiativeHelp> orderByComparator) {
		List<InitiativeHelp> list = findByPublikUserId(publikUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last initiative help in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching initiative help
	 * @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	 */
	@Override
	public InitiativeHelp findByPublikUserId_Last(String publikUserId,
		OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException {
		InitiativeHelp initiativeHelp = fetchByPublikUserId_Last(publikUserId,
				orderByComparator);

		if (initiativeHelp != null) {
			return initiativeHelp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikUserId=");
		msg.append(publikUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInitiativeHelpException(msg.toString());
	}

	/**
	 * Returns the last initiative help in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching initiative help, or <code>null</code> if a matching initiative help could not be found
	 */
	@Override
	public InitiativeHelp fetchByPublikUserId_Last(String publikUserId,
		OrderByComparator<InitiativeHelp> orderByComparator) {
		int count = countByPublikUserId(publikUserId);

		if (count == 0) {
			return null;
		}

		List<InitiativeHelp> list = findByPublikUserId(publikUserId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the initiative helps before and after the current initiative help in the ordered set where publikUserId = &#63;.
	 *
	 * @param initiativeHelpId the primary key of the current initiative help
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next initiative help
	 * @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	 */
	@Override
	public InitiativeHelp[] findByPublikUserId_PrevAndNext(
		long initiativeHelpId, String publikUserId,
		OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException {
		InitiativeHelp initiativeHelp = findByPrimaryKey(initiativeHelpId);

		Session session = null;

		try {
			session = openSession();

			InitiativeHelp[] array = new InitiativeHelpImpl[3];

			array[0] = getByPublikUserId_PrevAndNext(session, initiativeHelp,
					publikUserId, orderByComparator, true);

			array[1] = initiativeHelp;

			array[2] = getByPublikUserId_PrevAndNext(session, initiativeHelp,
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

	protected InitiativeHelp getByPublikUserId_PrevAndNext(Session session,
		InitiativeHelp initiativeHelp, String publikUserId,
		OrderByComparator<InitiativeHelp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_INITIATIVEHELP_WHERE);

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
			query.append(InitiativeHelpModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(initiativeHelp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<InitiativeHelp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the initiative helps where publikUserId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 */
	@Override
	public void removeByPublikUserId(String publikUserId) {
		for (InitiativeHelp initiativeHelp : findByPublikUserId(publikUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(initiativeHelp);
		}
	}

	/**
	 * Returns the number of initiative helps where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the number of matching initiative helps
	 */
	@Override
	public int countByPublikUserId(String publikUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKUSERID;

		Object[] finderArgs = new Object[] { publikUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_INITIATIVEHELP_WHERE);

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

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_1 = "initiativeHelp.publikUserId IS NULL";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2 = "initiativeHelp.publikUserId = ?";
	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3 = "(initiativeHelp.publikUserId IS NULL OR initiativeHelp.publikUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_INITIATIVEID =
		new FinderPath(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpModelImpl.FINDER_CACHE_ENABLED,
			InitiativeHelpImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByinitiativeId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INITIATIVEID =
		new FinderPath(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpModelImpl.FINDER_CACHE_ENABLED,
			InitiativeHelpImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByinitiativeId",
			new String[] { Long.class.getName() },
			InitiativeHelpModelImpl.INITIATIVEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_INITIATIVEID = new FinderPath(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByinitiativeId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the initiative helps where initiativeId = &#63;.
	 *
	 * @param initiativeId the initiative ID
	 * @return the matching initiative helps
	 */
	@Override
	public List<InitiativeHelp> findByinitiativeId(long initiativeId) {
		return findByinitiativeId(initiativeId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the initiative helps where initiativeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param initiativeId the initiative ID
	 * @param start the lower bound of the range of initiative helps
	 * @param end the upper bound of the range of initiative helps (not inclusive)
	 * @return the range of matching initiative helps
	 */
	@Override
	public List<InitiativeHelp> findByinitiativeId(long initiativeId,
		int start, int end) {
		return findByinitiativeId(initiativeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the initiative helps where initiativeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param initiativeId the initiative ID
	 * @param start the lower bound of the range of initiative helps
	 * @param end the upper bound of the range of initiative helps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching initiative helps
	 */
	@Override
	public List<InitiativeHelp> findByinitiativeId(long initiativeId,
		int start, int end, OrderByComparator<InitiativeHelp> orderByComparator) {
		return findByinitiativeId(initiativeId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the initiative helps where initiativeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param initiativeId the initiative ID
	 * @param start the lower bound of the range of initiative helps
	 * @param end the upper bound of the range of initiative helps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching initiative helps
	 */
	@Override
	public List<InitiativeHelp> findByinitiativeId(long initiativeId,
		int start, int end,
		OrderByComparator<InitiativeHelp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INITIATIVEID;
			finderArgs = new Object[] { initiativeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_INITIATIVEID;
			finderArgs = new Object[] {
					initiativeId,
					
					start, end, orderByComparator
				};
		}

		List<InitiativeHelp> list = null;

		if (retrieveFromCache) {
			list = (List<InitiativeHelp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (InitiativeHelp initiativeHelp : list) {
					if ((initiativeId != initiativeHelp.getInitiativeId())) {
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

			query.append(_SQL_SELECT_INITIATIVEHELP_WHERE);

			query.append(_FINDER_COLUMN_INITIATIVEID_INITIATIVEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(InitiativeHelpModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(initiativeId);

				if (!pagination) {
					list = (List<InitiativeHelp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<InitiativeHelp>)QueryUtil.list(q,
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
	 * Returns the first initiative help in the ordered set where initiativeId = &#63;.
	 *
	 * @param initiativeId the initiative ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching initiative help
	 * @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	 */
	@Override
	public InitiativeHelp findByinitiativeId_First(long initiativeId,
		OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException {
		InitiativeHelp initiativeHelp = fetchByinitiativeId_First(initiativeId,
				orderByComparator);

		if (initiativeHelp != null) {
			return initiativeHelp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("initiativeId=");
		msg.append(initiativeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInitiativeHelpException(msg.toString());
	}

	/**
	 * Returns the first initiative help in the ordered set where initiativeId = &#63;.
	 *
	 * @param initiativeId the initiative ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching initiative help, or <code>null</code> if a matching initiative help could not be found
	 */
	@Override
	public InitiativeHelp fetchByinitiativeId_First(long initiativeId,
		OrderByComparator<InitiativeHelp> orderByComparator) {
		List<InitiativeHelp> list = findByinitiativeId(initiativeId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last initiative help in the ordered set where initiativeId = &#63;.
	 *
	 * @param initiativeId the initiative ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching initiative help
	 * @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	 */
	@Override
	public InitiativeHelp findByinitiativeId_Last(long initiativeId,
		OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException {
		InitiativeHelp initiativeHelp = fetchByinitiativeId_Last(initiativeId,
				orderByComparator);

		if (initiativeHelp != null) {
			return initiativeHelp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("initiativeId=");
		msg.append(initiativeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInitiativeHelpException(msg.toString());
	}

	/**
	 * Returns the last initiative help in the ordered set where initiativeId = &#63;.
	 *
	 * @param initiativeId the initiative ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching initiative help, or <code>null</code> if a matching initiative help could not be found
	 */
	@Override
	public InitiativeHelp fetchByinitiativeId_Last(long initiativeId,
		OrderByComparator<InitiativeHelp> orderByComparator) {
		int count = countByinitiativeId(initiativeId);

		if (count == 0) {
			return null;
		}

		List<InitiativeHelp> list = findByinitiativeId(initiativeId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the initiative helps before and after the current initiative help in the ordered set where initiativeId = &#63;.
	 *
	 * @param initiativeHelpId the primary key of the current initiative help
	 * @param initiativeId the initiative ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next initiative help
	 * @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	 */
	@Override
	public InitiativeHelp[] findByinitiativeId_PrevAndNext(
		long initiativeHelpId, long initiativeId,
		OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException {
		InitiativeHelp initiativeHelp = findByPrimaryKey(initiativeHelpId);

		Session session = null;

		try {
			session = openSession();

			InitiativeHelp[] array = new InitiativeHelpImpl[3];

			array[0] = getByinitiativeId_PrevAndNext(session, initiativeHelp,
					initiativeId, orderByComparator, true);

			array[1] = initiativeHelp;

			array[2] = getByinitiativeId_PrevAndNext(session, initiativeHelp,
					initiativeId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected InitiativeHelp getByinitiativeId_PrevAndNext(Session session,
		InitiativeHelp initiativeHelp, long initiativeId,
		OrderByComparator<InitiativeHelp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_INITIATIVEHELP_WHERE);

		query.append(_FINDER_COLUMN_INITIATIVEID_INITIATIVEID_2);

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
			query.append(InitiativeHelpModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(initiativeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(initiativeHelp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<InitiativeHelp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the initiative helps where initiativeId = &#63; from the database.
	 *
	 * @param initiativeId the initiative ID
	 */
	@Override
	public void removeByinitiativeId(long initiativeId) {
		for (InitiativeHelp initiativeHelp : findByinitiativeId(initiativeId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(initiativeHelp);
		}
	}

	/**
	 * Returns the number of initiative helps where initiativeId = &#63;.
	 *
	 * @param initiativeId the initiative ID
	 * @return the number of matching initiative helps
	 */
	@Override
	public int countByinitiativeId(long initiativeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_INITIATIVEID;

		Object[] finderArgs = new Object[] { initiativeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_INITIATIVEHELP_WHERE);

			query.append(_FINDER_COLUMN_INITIATIVEID_INITIATIVEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(initiativeId);

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

	private static final String _FINDER_COLUMN_INITIATIVEID_INITIATIVEID_2 = "initiativeHelp.initiativeId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDINITIATIVEID =
		new FinderPath(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpModelImpl.FINDER_CACHE_ENABLED,
			InitiativeHelpImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByPublikUserIdAndInitiativeId",
			new String[] { String.class.getName(), Long.class.getName() },
			InitiativeHelpModelImpl.PUBLIKUSERID_COLUMN_BITMASK |
			InitiativeHelpModelImpl.INITIATIVEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDINITIATIVEID =
		new FinderPath(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPublikUserIdAndInitiativeId",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the initiative help where publikUserId = &#63; and initiativeId = &#63; or throws a {@link NoSuchInitiativeHelpException} if it could not be found.
	 *
	 * @param publikUserId the publik user ID
	 * @param initiativeId the initiative ID
	 * @return the matching initiative help
	 * @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	 */
	@Override
	public InitiativeHelp findByPublikUserIdAndInitiativeId(
		String publikUserId, long initiativeId)
		throws NoSuchInitiativeHelpException {
		InitiativeHelp initiativeHelp = fetchByPublikUserIdAndInitiativeId(publikUserId,
				initiativeId);

		if (initiativeHelp == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("publikUserId=");
			msg.append(publikUserId);

			msg.append(", initiativeId=");
			msg.append(initiativeId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchInitiativeHelpException(msg.toString());
		}

		return initiativeHelp;
	}

	/**
	 * Returns the initiative help where publikUserId = &#63; and initiativeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param publikUserId the publik user ID
	 * @param initiativeId the initiative ID
	 * @return the matching initiative help, or <code>null</code> if a matching initiative help could not be found
	 */
	@Override
	public InitiativeHelp fetchByPublikUserIdAndInitiativeId(
		String publikUserId, long initiativeId) {
		return fetchByPublikUserIdAndInitiativeId(publikUserId, initiativeId,
			true);
	}

	/**
	 * Returns the initiative help where publikUserId = &#63; and initiativeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param publikUserId the publik user ID
	 * @param initiativeId the initiative ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching initiative help, or <code>null</code> if a matching initiative help could not be found
	 */
	@Override
	public InitiativeHelp fetchByPublikUserIdAndInitiativeId(
		String publikUserId, long initiativeId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { publikUserId, initiativeId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDINITIATIVEID,
					finderArgs, this);
		}

		if (result instanceof InitiativeHelp) {
			InitiativeHelp initiativeHelp = (InitiativeHelp)result;

			if (!Objects.equals(publikUserId, initiativeHelp.getPublikUserId()) ||
					(initiativeId != initiativeHelp.getInitiativeId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_INITIATIVEHELP_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDINITIATIVEID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDINITIATIVEID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDINITIATIVEID_PUBLIKUSERID_2);
			}

			query.append(_FINDER_COLUMN_PUBLIKUSERIDANDINITIATIVEID_INITIATIVEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
				}

				qPos.add(initiativeId);

				List<InitiativeHelp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDINITIATIVEID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"InitiativeHelpPersistenceImpl.fetchByPublikUserIdAndInitiativeId(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					InitiativeHelp initiativeHelp = list.get(0);

					result = initiativeHelp;

					cacheResult(initiativeHelp);

					if ((initiativeHelp.getPublikUserId() == null) ||
							!initiativeHelp.getPublikUserId()
											   .equals(publikUserId) ||
							(initiativeHelp.getInitiativeId() != initiativeId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDINITIATIVEID,
							finderArgs, initiativeHelp);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDINITIATIVEID,
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
			return (InitiativeHelp)result;
		}
	}

	/**
	 * Removes the initiative help where publikUserId = &#63; and initiativeId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 * @param initiativeId the initiative ID
	 * @return the initiative help that was removed
	 */
	@Override
	public InitiativeHelp removeByPublikUserIdAndInitiativeId(
		String publikUserId, long initiativeId)
		throws NoSuchInitiativeHelpException {
		InitiativeHelp initiativeHelp = findByPublikUserIdAndInitiativeId(publikUserId,
				initiativeId);

		return remove(initiativeHelp);
	}

	/**
	 * Returns the number of initiative helps where publikUserId = &#63; and initiativeId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param initiativeId the initiative ID
	 * @return the number of matching initiative helps
	 */
	@Override
	public int countByPublikUserIdAndInitiativeId(String publikUserId,
		long initiativeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDINITIATIVEID;

		Object[] finderArgs = new Object[] { publikUserId, initiativeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_INITIATIVEHELP_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId == null) {
				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDINITIATIVEID_PUBLIKUSERID_1);
			}
			else if (publikUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDINITIATIVEID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				query.append(_FINDER_COLUMN_PUBLIKUSERIDANDINITIATIVEID_PUBLIKUSERID_2);
			}

			query.append(_FINDER_COLUMN_PUBLIKUSERIDANDINITIATIVEID_INITIATIVEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikUserId) {
					qPos.add(publikUserId);
				}

				qPos.add(initiativeId);

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

	private static final String _FINDER_COLUMN_PUBLIKUSERIDANDINITIATIVEID_PUBLIKUSERID_1 =
		"initiativeHelp.publikUserId IS NULL AND ";
	private static final String _FINDER_COLUMN_PUBLIKUSERIDANDINITIATIVEID_PUBLIKUSERID_2 =
		"initiativeHelp.publikUserId = ? AND ";
	private static final String _FINDER_COLUMN_PUBLIKUSERIDANDINITIATIVEID_PUBLIKUSERID_3 =
		"(initiativeHelp.publikUserId IS NULL OR initiativeHelp.publikUserId = '') AND ";
	private static final String _FINDER_COLUMN_PUBLIKUSERIDANDINITIATIVEID_INITIATIVEID_2 =
		"initiativeHelp.initiativeId = ?";

	public InitiativeHelpPersistenceImpl() {
		setModelClass(InitiativeHelp.class);
	}

	/**
	 * Caches the initiative help in the entity cache if it is enabled.
	 *
	 * @param initiativeHelp the initiative help
	 */
	@Override
	public void cacheResult(InitiativeHelp initiativeHelp) {
		entityCache.putResult(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpImpl.class, initiativeHelp.getPrimaryKey(),
			initiativeHelp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDINITIATIVEID,
			new Object[] {
				initiativeHelp.getPublikUserId(),
				initiativeHelp.getInitiativeId()
			}, initiativeHelp);

		initiativeHelp.resetOriginalValues();
	}

	/**
	 * Caches the initiative helps in the entity cache if it is enabled.
	 *
	 * @param initiativeHelps the initiative helps
	 */
	@Override
	public void cacheResult(List<InitiativeHelp> initiativeHelps) {
		for (InitiativeHelp initiativeHelp : initiativeHelps) {
			if (entityCache.getResult(
						InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
						InitiativeHelpImpl.class, initiativeHelp.getPrimaryKey()) == null) {
				cacheResult(initiativeHelp);
			}
			else {
				initiativeHelp.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all initiative helps.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(InitiativeHelpImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the initiative help.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(InitiativeHelp initiativeHelp) {
		entityCache.removeResult(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpImpl.class, initiativeHelp.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((InitiativeHelpModelImpl)initiativeHelp, true);
	}

	@Override
	public void clearCache(List<InitiativeHelp> initiativeHelps) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (InitiativeHelp initiativeHelp : initiativeHelps) {
			entityCache.removeResult(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
				InitiativeHelpImpl.class, initiativeHelp.getPrimaryKey());

			clearUniqueFindersCache((InitiativeHelpModelImpl)initiativeHelp,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		InitiativeHelpModelImpl initiativeHelpModelImpl) {
		Object[] args = new Object[] {
				initiativeHelpModelImpl.getPublikUserId(),
				initiativeHelpModelImpl.getInitiativeId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDINITIATIVEID,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDINITIATIVEID,
			args, initiativeHelpModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		InitiativeHelpModelImpl initiativeHelpModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					initiativeHelpModelImpl.getPublikUserId(),
					initiativeHelpModelImpl.getInitiativeId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDINITIATIVEID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDINITIATIVEID,
				args);
		}

		if ((initiativeHelpModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDINITIATIVEID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					initiativeHelpModelImpl.getOriginalPublikUserId(),
					initiativeHelpModelImpl.getOriginalInitiativeId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERIDANDINITIATIVEID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PUBLIKUSERIDANDINITIATIVEID,
				args);
		}
	}

	/**
	 * Creates a new initiative help with the primary key. Does not add the initiative help to the database.
	 *
	 * @param initiativeHelpId the primary key for the new initiative help
	 * @return the new initiative help
	 */
	@Override
	public InitiativeHelp create(long initiativeHelpId) {
		InitiativeHelp initiativeHelp = new InitiativeHelpImpl();

		initiativeHelp.setNew(true);
		initiativeHelp.setPrimaryKey(initiativeHelpId);

		return initiativeHelp;
	}

	/**
	 * Removes the initiative help with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param initiativeHelpId the primary key of the initiative help
	 * @return the initiative help that was removed
	 * @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	 */
	@Override
	public InitiativeHelp remove(long initiativeHelpId)
		throws NoSuchInitiativeHelpException {
		return remove((Serializable)initiativeHelpId);
	}

	/**
	 * Removes the initiative help with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the initiative help
	 * @return the initiative help that was removed
	 * @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	 */
	@Override
	public InitiativeHelp remove(Serializable primaryKey)
		throws NoSuchInitiativeHelpException {
		Session session = null;

		try {
			session = openSession();

			InitiativeHelp initiativeHelp = (InitiativeHelp)session.get(InitiativeHelpImpl.class,
					primaryKey);

			if (initiativeHelp == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInitiativeHelpException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(initiativeHelp);
		}
		catch (NoSuchInitiativeHelpException nsee) {
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
	protected InitiativeHelp removeImpl(InitiativeHelp initiativeHelp) {
		initiativeHelp = toUnwrappedModel(initiativeHelp);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(initiativeHelp)) {
				initiativeHelp = (InitiativeHelp)session.get(InitiativeHelpImpl.class,
						initiativeHelp.getPrimaryKeyObj());
			}

			if (initiativeHelp != null) {
				session.delete(initiativeHelp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (initiativeHelp != null) {
			clearCache(initiativeHelp);
		}

		return initiativeHelp;
	}

	@Override
	public InitiativeHelp updateImpl(InitiativeHelp initiativeHelp) {
		initiativeHelp = toUnwrappedModel(initiativeHelp);

		boolean isNew = initiativeHelp.isNew();

		InitiativeHelpModelImpl initiativeHelpModelImpl = (InitiativeHelpModelImpl)initiativeHelp;

		Session session = null;

		try {
			session = openSession();

			if (initiativeHelp.isNew()) {
				session.save(initiativeHelp);

				initiativeHelp.setNew(false);
			}
			else {
				initiativeHelp = (InitiativeHelp)session.merge(initiativeHelp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!InitiativeHelpModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					initiativeHelpModelImpl.getPublikUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
				args);

			args = new Object[] { initiativeHelpModelImpl.getInitiativeId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_INITIATIVEID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INITIATIVEID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((initiativeHelpModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						initiativeHelpModelImpl.getOriginalPublikUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);

				args = new Object[] { initiativeHelpModelImpl.getPublikUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKUSERID,
					args);
			}

			if ((initiativeHelpModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INITIATIVEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						initiativeHelpModelImpl.getOriginalInitiativeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_INITIATIVEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INITIATIVEID,
					args);

				args = new Object[] { initiativeHelpModelImpl.getInitiativeId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_INITIATIVEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INITIATIVEID,
					args);
			}
		}

		entityCache.putResult(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
			InitiativeHelpImpl.class, initiativeHelp.getPrimaryKey(),
			initiativeHelp, false);

		clearUniqueFindersCache(initiativeHelpModelImpl, false);
		cacheUniqueFindersCache(initiativeHelpModelImpl);

		initiativeHelp.resetOriginalValues();

		return initiativeHelp;
	}

	protected InitiativeHelp toUnwrappedModel(InitiativeHelp initiativeHelp) {
		if (initiativeHelp instanceof InitiativeHelpImpl) {
			return initiativeHelp;
		}

		InitiativeHelpImpl initiativeHelpImpl = new InitiativeHelpImpl();

		initiativeHelpImpl.setNew(initiativeHelp.isNew());
		initiativeHelpImpl.setPrimaryKey(initiativeHelp.getPrimaryKey());

		initiativeHelpImpl.setInitiativeHelpId(initiativeHelp.getInitiativeHelpId());
		initiativeHelpImpl.setCreateDate(initiativeHelp.getCreateDate());
		initiativeHelpImpl.setPublikUserId(initiativeHelp.getPublikUserId());
		initiativeHelpImpl.setInitiativeId(initiativeHelp.getInitiativeId());
		initiativeHelpImpl.setGroupId(initiativeHelp.getGroupId());
		initiativeHelpImpl.setMessage(initiativeHelp.getMessage());

		return initiativeHelpImpl;
	}

	/**
	 * Returns the initiative help with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the initiative help
	 * @return the initiative help
	 * @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	 */
	@Override
	public InitiativeHelp findByPrimaryKey(Serializable primaryKey)
		throws NoSuchInitiativeHelpException {
		InitiativeHelp initiativeHelp = fetchByPrimaryKey(primaryKey);

		if (initiativeHelp == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInitiativeHelpException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return initiativeHelp;
	}

	/**
	 * Returns the initiative help with the primary key or throws a {@link NoSuchInitiativeHelpException} if it could not be found.
	 *
	 * @param initiativeHelpId the primary key of the initiative help
	 * @return the initiative help
	 * @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	 */
	@Override
	public InitiativeHelp findByPrimaryKey(long initiativeHelpId)
		throws NoSuchInitiativeHelpException {
		return findByPrimaryKey((Serializable)initiativeHelpId);
	}

	/**
	 * Returns the initiative help with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the initiative help
	 * @return the initiative help, or <code>null</code> if a initiative help with the primary key could not be found
	 */
	@Override
	public InitiativeHelp fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
				InitiativeHelpImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		InitiativeHelp initiativeHelp = (InitiativeHelp)serializable;

		if (initiativeHelp == null) {
			Session session = null;

			try {
				session = openSession();

				initiativeHelp = (InitiativeHelp)session.get(InitiativeHelpImpl.class,
						primaryKey);

				if (initiativeHelp != null) {
					cacheResult(initiativeHelp);
				}
				else {
					entityCache.putResult(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
						InitiativeHelpImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
					InitiativeHelpImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return initiativeHelp;
	}

	/**
	 * Returns the initiative help with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param initiativeHelpId the primary key of the initiative help
	 * @return the initiative help, or <code>null</code> if a initiative help with the primary key could not be found
	 */
	@Override
	public InitiativeHelp fetchByPrimaryKey(long initiativeHelpId) {
		return fetchByPrimaryKey((Serializable)initiativeHelpId);
	}

	@Override
	public Map<Serializable, InitiativeHelp> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, InitiativeHelp> map = new HashMap<Serializable, InitiativeHelp>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			InitiativeHelp initiativeHelp = fetchByPrimaryKey(primaryKey);

			if (initiativeHelp != null) {
				map.put(primaryKey, initiativeHelp);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
					InitiativeHelpImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (InitiativeHelp)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_INITIATIVEHELP_WHERE_PKS_IN);

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

			for (InitiativeHelp initiativeHelp : (List<InitiativeHelp>)q.list()) {
				map.put(initiativeHelp.getPrimaryKeyObj(), initiativeHelp);

				cacheResult(initiativeHelp);

				uncachedPrimaryKeys.remove(initiativeHelp.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(InitiativeHelpModelImpl.ENTITY_CACHE_ENABLED,
					InitiativeHelpImpl.class, primaryKey, nullModel);
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
	 * Returns all the initiative helps.
	 *
	 * @return the initiative helps
	 */
	@Override
	public List<InitiativeHelp> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the initiative helps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of initiative helps
	 * @param end the upper bound of the range of initiative helps (not inclusive)
	 * @return the range of initiative helps
	 */
	@Override
	public List<InitiativeHelp> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the initiative helps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of initiative helps
	 * @param end the upper bound of the range of initiative helps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of initiative helps
	 */
	@Override
	public List<InitiativeHelp> findAll(int start, int end,
		OrderByComparator<InitiativeHelp> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the initiative helps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of initiative helps
	 * @param end the upper bound of the range of initiative helps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of initiative helps
	 */
	@Override
	public List<InitiativeHelp> findAll(int start, int end,
		OrderByComparator<InitiativeHelp> orderByComparator,
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

		List<InitiativeHelp> list = null;

		if (retrieveFromCache) {
			list = (List<InitiativeHelp>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_INITIATIVEHELP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_INITIATIVEHELP;

				if (pagination) {
					sql = sql.concat(InitiativeHelpModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<InitiativeHelp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<InitiativeHelp>)QueryUtil.list(q,
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
	 * Removes all the initiative helps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (InitiativeHelp initiativeHelp : findAll()) {
			remove(initiativeHelp);
		}
	}

	/**
	 * Returns the number of initiative helps.
	 *
	 * @return the number of initiative helps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_INITIATIVEHELP);

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
		return InitiativeHelpModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the initiative help persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(InitiativeHelpImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_INITIATIVEHELP = "SELECT initiativeHelp FROM InitiativeHelp initiativeHelp";
	private static final String _SQL_SELECT_INITIATIVEHELP_WHERE_PKS_IN = "SELECT initiativeHelp FROM InitiativeHelp initiativeHelp WHERE initiativeHelpId IN (";
	private static final String _SQL_SELECT_INITIATIVEHELP_WHERE = "SELECT initiativeHelp FROM InitiativeHelp initiativeHelp WHERE ";
	private static final String _SQL_COUNT_INITIATIVEHELP = "SELECT COUNT(initiativeHelp) FROM InitiativeHelp initiativeHelp";
	private static final String _SQL_COUNT_INITIATIVEHELP_WHERE = "SELECT COUNT(initiativeHelp) FROM InitiativeHelp initiativeHelp WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "initiativeHelp.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No InitiativeHelp exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No InitiativeHelp exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(InitiativeHelpPersistenceImpl.class);
}