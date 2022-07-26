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

package eu.strasbourg.service.csmap.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import eu.strasbourg.service.csmap.exception.NoSuchCsmapCacheException;
import eu.strasbourg.service.csmap.model.CsmapCache;
import eu.strasbourg.service.csmap.model.impl.CsmapCacheImpl;
import eu.strasbourg.service.csmap.model.impl.CsmapCacheModelImpl;
import eu.strasbourg.service.csmap.service.persistence.CsmapCachePersistence;
import eu.strasbourg.service.csmap.service.persistence.impl.constants.csmapPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the csmap cache service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CsmapCachePersistence.class)
public class CsmapCachePersistenceImpl
	extends BasePersistenceImpl<CsmapCache> implements CsmapCachePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CsmapCacheUtil</code> to access the csmap cache persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CsmapCacheImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByCodeCache;
	private FinderPath _finderPathCountByCodeCache;

	/**
	 * Returns the csmap cache where codeCache = &#63; or throws a <code>NoSuchCsmapCacheException</code> if it could not be found.
	 *
	 * @param codeCache the code cache
	 * @return the matching csmap cache
	 * @throws NoSuchCsmapCacheException if a matching csmap cache could not be found
	 */
	@Override
	public CsmapCache findByCodeCache(long codeCache)
		throws NoSuchCsmapCacheException {

		CsmapCache csmapCache = fetchByCodeCache(codeCache);

		if (csmapCache == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("codeCache=");
			sb.append(codeCache);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCsmapCacheException(sb.toString());
		}

		return csmapCache;
	}

	/**
	 * Returns the csmap cache where codeCache = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param codeCache the code cache
	 * @return the matching csmap cache, or <code>null</code> if a matching csmap cache could not be found
	 */
	@Override
	public CsmapCache fetchByCodeCache(long codeCache) {
		return fetchByCodeCache(codeCache, true);
	}

	/**
	 * Returns the csmap cache where codeCache = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param codeCache the code cache
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching csmap cache, or <code>null</code> if a matching csmap cache could not be found
	 */
	@Override
	public CsmapCache fetchByCodeCache(long codeCache, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {codeCache};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByCodeCache, finderArgs, this);
		}

		if (result instanceof CsmapCache) {
			CsmapCache csmapCache = (CsmapCache)result;

			if (codeCache != csmapCache.getCodeCache()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_CSMAPCACHE_WHERE);

			sb.append(_FINDER_COLUMN_CODECACHE_CODECACHE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(codeCache);

				List<CsmapCache> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByCodeCache, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {codeCache};
							}

							_log.warn(
								"CsmapCachePersistenceImpl.fetchByCodeCache(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CsmapCache csmapCache = list.get(0);

					result = csmapCache;

					cacheResult(csmapCache);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByCodeCache, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CsmapCache)result;
		}
	}

	/**
	 * Removes the csmap cache where codeCache = &#63; from the database.
	 *
	 * @param codeCache the code cache
	 * @return the csmap cache that was removed
	 */
	@Override
	public CsmapCache removeByCodeCache(long codeCache)
		throws NoSuchCsmapCacheException {

		CsmapCache csmapCache = findByCodeCache(codeCache);

		return remove(csmapCache);
	}

	/**
	 * Returns the number of csmap caches where codeCache = &#63;.
	 *
	 * @param codeCache the code cache
	 * @return the number of matching csmap caches
	 */
	@Override
	public int countByCodeCache(long codeCache) {
		FinderPath finderPath = _finderPathCountByCodeCache;

		Object[] finderArgs = new Object[] {codeCache};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CSMAPCACHE_WHERE);

			sb.append(_FINDER_COLUMN_CODECACHE_CODECACHE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(codeCache);

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

	private static final String _FINDER_COLUMN_CODECACHE_CODECACHE_2 =
		"csmapCache.codeCache = ?";

	private FinderPath _finderPathWithPaginationFindByLastProcessNotSuccess;
	private FinderPath _finderPathWithoutPaginationFindByLastProcessNotSuccess;
	private FinderPath _finderPathCountByLastProcessNotSuccess;

	/**
	 * Returns all the csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @return the matching csmap caches
	 */
	@Override
	public List<CsmapCache> findByLastProcessNotSuccess(
		Boolean isLastProcessSuccess) {

		return findByLastProcessNotSuccess(
			isLastProcessSuccess, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>.
	 * </p>
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @return the range of matching csmap caches
	 */
	@Override
	public List<CsmapCache> findByLastProcessNotSuccess(
		Boolean isLastProcessSuccess, int start, int end) {

		return findByLastProcessNotSuccess(
			isLastProcessSuccess, start, end, null);
	}

	/**
	 * Returns an ordered range of all the csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>.
	 * </p>
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap caches
	 */
	@Override
	public List<CsmapCache> findByLastProcessNotSuccess(
		Boolean isLastProcessSuccess, int start, int end,
		OrderByComparator<CsmapCache> orderByComparator) {

		return findByLastProcessNotSuccess(
			isLastProcessSuccess, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>.
	 * </p>
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap caches
	 */
	@Override
	public List<CsmapCache> findByLastProcessNotSuccess(
		Boolean isLastProcessSuccess, int start, int end,
		OrderByComparator<CsmapCache> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByLastProcessNotSuccess;
				finderArgs = new Object[] {isLastProcessSuccess};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByLastProcessNotSuccess;
			finderArgs = new Object[] {
				isLastProcessSuccess, start, end, orderByComparator
			};
		}

		List<CsmapCache> list = null;

		if (useFinderCache) {
			list = (List<CsmapCache>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CsmapCache csmapCache : list) {
					if (!Objects.equals(
							isLastProcessSuccess,
							csmapCache.getIsLastProcessSuccess())) {

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

			sb.append(_SQL_SELECT_CSMAPCACHE_WHERE);

			sb.append(
				_FINDER_COLUMN_LASTPROCESSNOTSUCCESS_ISLASTPROCESSSUCCESS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CsmapCacheModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isLastProcessSuccess.booleanValue());

				list = (List<CsmapCache>)QueryUtil.list(
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
	 * Returns the first csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache
	 * @throws NoSuchCsmapCacheException if a matching csmap cache could not be found
	 */
	@Override
	public CsmapCache findByLastProcessNotSuccess_First(
			Boolean isLastProcessSuccess,
			OrderByComparator<CsmapCache> orderByComparator)
		throws NoSuchCsmapCacheException {

		CsmapCache csmapCache = fetchByLastProcessNotSuccess_First(
			isLastProcessSuccess, orderByComparator);

		if (csmapCache != null) {
			return csmapCache;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isLastProcessSuccess=");
		sb.append(isLastProcessSuccess);

		sb.append("}");

		throw new NoSuchCsmapCacheException(sb.toString());
	}

	/**
	 * Returns the first csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache, or <code>null</code> if a matching csmap cache could not be found
	 */
	@Override
	public CsmapCache fetchByLastProcessNotSuccess_First(
		Boolean isLastProcessSuccess,
		OrderByComparator<CsmapCache> orderByComparator) {

		List<CsmapCache> list = findByLastProcessNotSuccess(
			isLastProcessSuccess, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache
	 * @throws NoSuchCsmapCacheException if a matching csmap cache could not be found
	 */
	@Override
	public CsmapCache findByLastProcessNotSuccess_Last(
			Boolean isLastProcessSuccess,
			OrderByComparator<CsmapCache> orderByComparator)
		throws NoSuchCsmapCacheException {

		CsmapCache csmapCache = fetchByLastProcessNotSuccess_Last(
			isLastProcessSuccess, orderByComparator);

		if (csmapCache != null) {
			return csmapCache;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isLastProcessSuccess=");
		sb.append(isLastProcessSuccess);

		sb.append("}");

		throw new NoSuchCsmapCacheException(sb.toString());
	}

	/**
	 * Returns the last csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache, or <code>null</code> if a matching csmap cache could not be found
	 */
	@Override
	public CsmapCache fetchByLastProcessNotSuccess_Last(
		Boolean isLastProcessSuccess,
		OrderByComparator<CsmapCache> orderByComparator) {

		int count = countByLastProcessNotSuccess(isLastProcessSuccess);

		if (count == 0) {
			return null;
		}

		List<CsmapCache> list = findByLastProcessNotSuccess(
			isLastProcessSuccess, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the csmap caches before and after the current csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param cacheId the primary key of the current csmap cache
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache
	 * @throws NoSuchCsmapCacheException if a csmap cache with the primary key could not be found
	 */
	@Override
	public CsmapCache[] findByLastProcessNotSuccess_PrevAndNext(
			long cacheId, Boolean isLastProcessSuccess,
			OrderByComparator<CsmapCache> orderByComparator)
		throws NoSuchCsmapCacheException {

		CsmapCache csmapCache = findByPrimaryKey(cacheId);

		Session session = null;

		try {
			session = openSession();

			CsmapCache[] array = new CsmapCacheImpl[3];

			array[0] = getByLastProcessNotSuccess_PrevAndNext(
				session, csmapCache, isLastProcessSuccess, orderByComparator,
				true);

			array[1] = csmapCache;

			array[2] = getByLastProcessNotSuccess_PrevAndNext(
				session, csmapCache, isLastProcessSuccess, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CsmapCache getByLastProcessNotSuccess_PrevAndNext(
		Session session, CsmapCache csmapCache, Boolean isLastProcessSuccess,
		OrderByComparator<CsmapCache> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_CSMAPCACHE_WHERE);

		sb.append(_FINDER_COLUMN_LASTPROCESSNOTSUCCESS_ISLASTPROCESSSUCCESS_2);

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
			sb.append(CsmapCacheModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(isLastProcessSuccess.booleanValue());

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(csmapCache)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CsmapCache> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the csmap caches where isLastProcessSuccess = &#63; from the database.
	 *
	 * @param isLastProcessSuccess the is last process success
	 */
	@Override
	public void removeByLastProcessNotSuccess(Boolean isLastProcessSuccess) {
		for (CsmapCache csmapCache :
				findByLastProcessNotSuccess(
					isLastProcessSuccess, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(csmapCache);
		}
	}

	/**
	 * Returns the number of csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @return the number of matching csmap caches
	 */
	@Override
	public int countByLastProcessNotSuccess(Boolean isLastProcessSuccess) {
		FinderPath finderPath = _finderPathCountByLastProcessNotSuccess;

		Object[] finderArgs = new Object[] {isLastProcessSuccess};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CSMAPCACHE_WHERE);

			sb.append(
				_FINDER_COLUMN_LASTPROCESSNOTSUCCESS_ISLASTPROCESSSUCCESS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isLastProcessSuccess.booleanValue());

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

	private static final String
		_FINDER_COLUMN_LASTPROCESSNOTSUCCESS_ISLASTPROCESSSUCCESS_2 =
			"csmapCache.isLastProcessSuccess = ?";

	public CsmapCachePersistenceImpl() {
		setModelClass(CsmapCache.class);

		setModelImplClass(CsmapCacheImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the csmap cache in the entity cache if it is enabled.
	 *
	 * @param csmapCache the csmap cache
	 */
	@Override
	public void cacheResult(CsmapCache csmapCache) {
		entityCache.putResult(
			entityCacheEnabled, CsmapCacheImpl.class,
			csmapCache.getPrimaryKey(), csmapCache);

		finderCache.putResult(
			_finderPathFetchByCodeCache,
			new Object[] {csmapCache.getCodeCache()}, csmapCache);

		csmapCache.resetOriginalValues();
	}

	/**
	 * Caches the csmap caches in the entity cache if it is enabled.
	 *
	 * @param csmapCaches the csmap caches
	 */
	@Override
	public void cacheResult(List<CsmapCache> csmapCaches) {
		for (CsmapCache csmapCache : csmapCaches) {
			if (entityCache.getResult(
					entityCacheEnabled, CsmapCacheImpl.class,
					csmapCache.getPrimaryKey()) == null) {

				cacheResult(csmapCache);
			}
			else {
				csmapCache.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all csmap caches.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CsmapCacheImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the csmap cache.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CsmapCache csmapCache) {
		entityCache.removeResult(
			entityCacheEnabled, CsmapCacheImpl.class,
			csmapCache.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CsmapCacheModelImpl)csmapCache, true);
	}

	@Override
	public void clearCache(List<CsmapCache> csmapCaches) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CsmapCache csmapCache : csmapCaches) {
			entityCache.removeResult(
				entityCacheEnabled, CsmapCacheImpl.class,
				csmapCache.getPrimaryKey());

			clearUniqueFindersCache((CsmapCacheModelImpl)csmapCache, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, CsmapCacheImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CsmapCacheModelImpl csmapCacheModelImpl) {

		Object[] args = new Object[] {csmapCacheModelImpl.getCodeCache()};

		finderCache.putResult(
			_finderPathCountByCodeCache, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByCodeCache, args, csmapCacheModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CsmapCacheModelImpl csmapCacheModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {csmapCacheModelImpl.getCodeCache()};

			finderCache.removeResult(_finderPathCountByCodeCache, args);
			finderCache.removeResult(_finderPathFetchByCodeCache, args);
		}

		if ((csmapCacheModelImpl.getColumnBitmask() &
			 _finderPathFetchByCodeCache.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				csmapCacheModelImpl.getOriginalCodeCache()
			};

			finderCache.removeResult(_finderPathCountByCodeCache, args);
			finderCache.removeResult(_finderPathFetchByCodeCache, args);
		}
	}

	/**
	 * Creates a new csmap cache with the primary key. Does not add the csmap cache to the database.
	 *
	 * @param cacheId the primary key for the new csmap cache
	 * @return the new csmap cache
	 */
	@Override
	public CsmapCache create(long cacheId) {
		CsmapCache csmapCache = new CsmapCacheImpl();

		csmapCache.setNew(true);
		csmapCache.setPrimaryKey(cacheId);

		return csmapCache;
	}

	/**
	 * Removes the csmap cache with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheId the primary key of the csmap cache
	 * @return the csmap cache that was removed
	 * @throws NoSuchCsmapCacheException if a csmap cache with the primary key could not be found
	 */
	@Override
	public CsmapCache remove(long cacheId) throws NoSuchCsmapCacheException {
		return remove((Serializable)cacheId);
	}

	/**
	 * Removes the csmap cache with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the csmap cache
	 * @return the csmap cache that was removed
	 * @throws NoSuchCsmapCacheException if a csmap cache with the primary key could not be found
	 */
	@Override
	public CsmapCache remove(Serializable primaryKey)
		throws NoSuchCsmapCacheException {

		Session session = null;

		try {
			session = openSession();

			CsmapCache csmapCache = (CsmapCache)session.get(
				CsmapCacheImpl.class, primaryKey);

			if (csmapCache == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCsmapCacheException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(csmapCache);
		}
		catch (NoSuchCsmapCacheException noSuchEntityException) {
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
	protected CsmapCache removeImpl(CsmapCache csmapCache) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(csmapCache)) {
				csmapCache = (CsmapCache)session.get(
					CsmapCacheImpl.class, csmapCache.getPrimaryKeyObj());
			}

			if (csmapCache != null) {
				session.delete(csmapCache);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (csmapCache != null) {
			clearCache(csmapCache);
		}

		return csmapCache;
	}

	@Override
	public CsmapCache updateImpl(CsmapCache csmapCache) {
		boolean isNew = csmapCache.isNew();

		if (!(csmapCache instanceof CsmapCacheModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(csmapCache.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(csmapCache);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in csmapCache proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CsmapCache implementation " +
					csmapCache.getClass());
		}

		CsmapCacheModelImpl csmapCacheModelImpl =
			(CsmapCacheModelImpl)csmapCache;

		Session session = null;

		try {
			session = openSession();

			if (csmapCache.isNew()) {
				session.save(csmapCache);

				csmapCache.setNew(false);
			}
			else {
				csmapCache = (CsmapCache)session.merge(csmapCache);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				csmapCacheModelImpl.getIsLastProcessSuccess()
			};

			finderCache.removeResult(
				_finderPathCountByLastProcessNotSuccess, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByLastProcessNotSuccess, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((csmapCacheModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByLastProcessNotSuccess.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					csmapCacheModelImpl.getOriginalIsLastProcessSuccess()
				};

				finderCache.removeResult(
					_finderPathCountByLastProcessNotSuccess, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByLastProcessNotSuccess,
					args);

				args = new Object[] {
					csmapCacheModelImpl.getIsLastProcessSuccess()
				};

				finderCache.removeResult(
					_finderPathCountByLastProcessNotSuccess, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByLastProcessNotSuccess,
					args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, CsmapCacheImpl.class,
			csmapCache.getPrimaryKey(), csmapCache, false);

		clearUniqueFindersCache(csmapCacheModelImpl, false);
		cacheUniqueFindersCache(csmapCacheModelImpl);

		csmapCache.resetOriginalValues();

		return csmapCache;
	}

	/**
	 * Returns the csmap cache with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the csmap cache
	 * @return the csmap cache
	 * @throws NoSuchCsmapCacheException if a csmap cache with the primary key could not be found
	 */
	@Override
	public CsmapCache findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCsmapCacheException {

		CsmapCache csmapCache = fetchByPrimaryKey(primaryKey);

		if (csmapCache == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCsmapCacheException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return csmapCache;
	}

	/**
	 * Returns the csmap cache with the primary key or throws a <code>NoSuchCsmapCacheException</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the csmap cache
	 * @return the csmap cache
	 * @throws NoSuchCsmapCacheException if a csmap cache with the primary key could not be found
	 */
	@Override
	public CsmapCache findByPrimaryKey(long cacheId)
		throws NoSuchCsmapCacheException {

		return findByPrimaryKey((Serializable)cacheId);
	}

	/**
	 * Returns the csmap cache with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the csmap cache
	 * @return the csmap cache, or <code>null</code> if a csmap cache with the primary key could not be found
	 */
	@Override
	public CsmapCache fetchByPrimaryKey(long cacheId) {
		return fetchByPrimaryKey((Serializable)cacheId);
	}

	/**
	 * Returns all the csmap caches.
	 *
	 * @return the csmap caches
	 */
	@Override
	public List<CsmapCache> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the csmap caches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @return the range of csmap caches
	 */
	@Override
	public List<CsmapCache> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the csmap caches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of csmap caches
	 */
	@Override
	public List<CsmapCache> findAll(
		int start, int end, OrderByComparator<CsmapCache> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the csmap caches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of csmap caches
	 */
	@Override
	public List<CsmapCache> findAll(
		int start, int end, OrderByComparator<CsmapCache> orderByComparator,
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

		List<CsmapCache> list = null;

		if (useFinderCache) {
			list = (List<CsmapCache>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CSMAPCACHE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CSMAPCACHE;

				sql = sql.concat(CsmapCacheModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CsmapCache>)QueryUtil.list(
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
	 * Removes all the csmap caches from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CsmapCache csmapCache : findAll()) {
			remove(csmapCache);
		}
	}

	/**
	 * Returns the number of csmap caches.
	 *
	 * @return the number of csmap caches
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_CSMAPCACHE);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "cacheId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CSMAPCACHE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CsmapCacheModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the csmap cache persistence.
	 */
	@Activate
	public void activate() {
		CsmapCacheModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		CsmapCacheModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CsmapCacheImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CsmapCacheImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByCodeCache = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CsmapCacheImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCodeCache",
			new String[] {Long.class.getName()},
			CsmapCacheModelImpl.CODECACHE_COLUMN_BITMASK);

		_finderPathCountByCodeCache = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCodeCache",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByLastProcessNotSuccess = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CsmapCacheImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLastProcessNotSuccess",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByLastProcessNotSuccess =
			new FinderPath(
				entityCacheEnabled, finderCacheEnabled, CsmapCacheImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByLastProcessNotSuccess",
				new String[] {Boolean.class.getName()},
				CsmapCacheModelImpl.ISLASTPROCESSSUCCESS_COLUMN_BITMASK);

		_finderPathCountByLastProcessNotSuccess = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLastProcessNotSuccess",
			new String[] {Boolean.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(CsmapCacheImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = csmapPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.eu.strasbourg.service.csmap.model.CsmapCache"),
			true);
	}

	@Override
	@Reference(
		target = csmapPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = csmapPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CSMAPCACHE =
		"SELECT csmapCache FROM CsmapCache csmapCache";

	private static final String _SQL_SELECT_CSMAPCACHE_WHERE =
		"SELECT csmapCache FROM CsmapCache csmapCache WHERE ";

	private static final String _SQL_COUNT_CSMAPCACHE =
		"SELECT COUNT(csmapCache) FROM CsmapCache csmapCache";

	private static final String _SQL_COUNT_CSMAPCACHE_WHERE =
		"SELECT COUNT(csmapCache) FROM CsmapCache csmapCache WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "csmapCache.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CsmapCache exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CsmapCache exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CsmapCachePersistenceImpl.class);

	static {
		try {
			Class.forName(csmapPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

}