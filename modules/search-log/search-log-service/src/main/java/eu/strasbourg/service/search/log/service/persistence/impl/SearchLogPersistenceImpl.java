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

package eu.strasbourg.service.search.log.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.search.log.exception.NoSuchSearchLogException;
import eu.strasbourg.service.search.log.model.SearchLog;
import eu.strasbourg.service.search.log.model.impl.SearchLogImpl;
import eu.strasbourg.service.search.log.model.impl.SearchLogModelImpl;
import eu.strasbourg.service.search.log.service.persistence.SearchLogPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the search log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @generated
 */
public class SearchLogPersistenceImpl
	extends BasePersistenceImpl<SearchLog> implements SearchLogPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SearchLogUtil</code> to access the search log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SearchLogImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public SearchLogPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("date", "date_");

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

		setModelClass(SearchLog.class);
	}

	/**
	 * Caches the search log in the entity cache if it is enabled.
	 *
	 * @param searchLog the search log
	 */
	@Override
	public void cacheResult(SearchLog searchLog) {
		entityCache.putResult(
			SearchLogModelImpl.ENTITY_CACHE_ENABLED, SearchLogImpl.class,
			searchLog.getPrimaryKey(), searchLog);

		searchLog.resetOriginalValues();
	}

	/**
	 * Caches the search logs in the entity cache if it is enabled.
	 *
	 * @param searchLogs the search logs
	 */
	@Override
	public void cacheResult(List<SearchLog> searchLogs) {
		for (SearchLog searchLog : searchLogs) {
			if (entityCache.getResult(
					SearchLogModelImpl.ENTITY_CACHE_ENABLED,
					SearchLogImpl.class, searchLog.getPrimaryKey()) == null) {

				cacheResult(searchLog);
			}
			else {
				searchLog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all search logs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SearchLogImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the search log.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SearchLog searchLog) {
		entityCache.removeResult(
			SearchLogModelImpl.ENTITY_CACHE_ENABLED, SearchLogImpl.class,
			searchLog.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SearchLog> searchLogs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SearchLog searchLog : searchLogs) {
			entityCache.removeResult(
				SearchLogModelImpl.ENTITY_CACHE_ENABLED, SearchLogImpl.class,
				searchLog.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				SearchLogModelImpl.ENTITY_CACHE_ENABLED, SearchLogImpl.class,
				primaryKey);
		}
	}

	/**
	 * Creates a new search log with the primary key. Does not add the search log to the database.
	 *
	 * @param searchLogId the primary key for the new search log
	 * @return the new search log
	 */
	@Override
	public SearchLog create(long searchLogId) {
		SearchLog searchLog = new SearchLogImpl();

		searchLog.setNew(true);
		searchLog.setPrimaryKey(searchLogId);

		return searchLog;
	}

	/**
	 * Removes the search log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param searchLogId the primary key of the search log
	 * @return the search log that was removed
	 * @throws NoSuchSearchLogException if a search log with the primary key could not be found
	 */
	@Override
	public SearchLog remove(long searchLogId) throws NoSuchSearchLogException {
		return remove((Serializable)searchLogId);
	}

	/**
	 * Removes the search log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the search log
	 * @return the search log that was removed
	 * @throws NoSuchSearchLogException if a search log with the primary key could not be found
	 */
	@Override
	public SearchLog remove(Serializable primaryKey)
		throws NoSuchSearchLogException {

		Session session = null;

		try {
			session = openSession();

			SearchLog searchLog = (SearchLog)session.get(
				SearchLogImpl.class, primaryKey);

			if (searchLog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSearchLogException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(searchLog);
		}
		catch (NoSuchSearchLogException noSuchEntityException) {
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
	protected SearchLog removeImpl(SearchLog searchLog) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(searchLog)) {
				searchLog = (SearchLog)session.get(
					SearchLogImpl.class, searchLog.getPrimaryKeyObj());
			}

			if (searchLog != null) {
				session.delete(searchLog);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (searchLog != null) {
			clearCache(searchLog);
		}

		return searchLog;
	}

	@Override
	public SearchLog updateImpl(SearchLog searchLog) {
		boolean isNew = searchLog.isNew();

		Session session = null;

		try {
			session = openSession();

			if (searchLog.isNew()) {
				session.save(searchLog);

				searchLog.setNew(false);
			}
			else {
				searchLog = (SearchLog)session.merge(searchLog);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			SearchLogModelImpl.ENTITY_CACHE_ENABLED, SearchLogImpl.class,
			searchLog.getPrimaryKey(), searchLog, false);

		searchLog.resetOriginalValues();

		return searchLog;
	}

	/**
	 * Returns the search log with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the search log
	 * @return the search log
	 * @throws NoSuchSearchLogException if a search log with the primary key could not be found
	 */
	@Override
	public SearchLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSearchLogException {

		SearchLog searchLog = fetchByPrimaryKey(primaryKey);

		if (searchLog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSearchLogException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return searchLog;
	}

	/**
	 * Returns the search log with the primary key or throws a <code>NoSuchSearchLogException</code> if it could not be found.
	 *
	 * @param searchLogId the primary key of the search log
	 * @return the search log
	 * @throws NoSuchSearchLogException if a search log with the primary key could not be found
	 */
	@Override
	public SearchLog findByPrimaryKey(long searchLogId)
		throws NoSuchSearchLogException {

		return findByPrimaryKey((Serializable)searchLogId);
	}

	/**
	 * Returns the search log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the search log
	 * @return the search log, or <code>null</code> if a search log with the primary key could not be found
	 */
	@Override
	public SearchLog fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			SearchLogModelImpl.ENTITY_CACHE_ENABLED, SearchLogImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SearchLog searchLog = (SearchLog)serializable;

		if (searchLog == null) {
			Session session = null;

			try {
				session = openSession();

				searchLog = (SearchLog)session.get(
					SearchLogImpl.class, primaryKey);

				if (searchLog != null) {
					cacheResult(searchLog);
				}
				else {
					entityCache.putResult(
						SearchLogModelImpl.ENTITY_CACHE_ENABLED,
						SearchLogImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					SearchLogModelImpl.ENTITY_CACHE_ENABLED,
					SearchLogImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return searchLog;
	}

	/**
	 * Returns the search log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param searchLogId the primary key of the search log
	 * @return the search log, or <code>null</code> if a search log with the primary key could not be found
	 */
	@Override
	public SearchLog fetchByPrimaryKey(long searchLogId) {
		return fetchByPrimaryKey((Serializable)searchLogId);
	}

	@Override
	public Map<Serializable, SearchLog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SearchLog> map =
			new HashMap<Serializable, SearchLog>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SearchLog searchLog = fetchByPrimaryKey(primaryKey);

			if (searchLog != null) {
				map.put(primaryKey, searchLog);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				SearchLogModelImpl.ENTITY_CACHE_ENABLED, SearchLogImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SearchLog)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_SEARCHLOG_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			sb.append((long)primaryKey);

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			for (SearchLog searchLog : (List<SearchLog>)query.list()) {
				map.put(searchLog.getPrimaryKeyObj(), searchLog);

				cacheResult(searchLog);

				uncachedPrimaryKeys.remove(searchLog.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					SearchLogModelImpl.ENTITY_CACHE_ENABLED,
					SearchLogImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the search logs.
	 *
	 * @return the search logs
	 */
	@Override
	public List<SearchLog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the search logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SearchLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of search logs
	 * @param end the upper bound of the range of search logs (not inclusive)
	 * @return the range of search logs
	 */
	@Override
	public List<SearchLog> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the search logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SearchLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of search logs
	 * @param end the upper bound of the range of search logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of search logs
	 */
	@Override
	public List<SearchLog> findAll(
		int start, int end, OrderByComparator<SearchLog> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the search logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SearchLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of search logs
	 * @param end the upper bound of the range of search logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of search logs
	 */
	@Override
	public List<SearchLog> findAll(
		int start, int end, OrderByComparator<SearchLog> orderByComparator,
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

		List<SearchLog> list = null;

		if (useFinderCache) {
			list = (List<SearchLog>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SEARCHLOG);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SEARCHLOG;

				sql = sql.concat(SearchLogModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SearchLog>)QueryUtil.list(
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
	 * Removes all the search logs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SearchLog searchLog : findAll()) {
			remove(searchLog);
		}
	}

	/**
	 * Returns the number of search logs.
	 *
	 * @return the number of search logs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SEARCHLOG);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return SearchLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the search log persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			SearchLogModelImpl.ENTITY_CACHE_ENABLED,
			SearchLogModelImpl.FINDER_CACHE_ENABLED, SearchLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			SearchLogModelImpl.ENTITY_CACHE_ENABLED,
			SearchLogModelImpl.FINDER_CACHE_ENABLED, SearchLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			SearchLogModelImpl.ENTITY_CACHE_ENABLED,
			SearchLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(SearchLogImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SEARCHLOG =
		"SELECT searchLog FROM SearchLog searchLog";

	private static final String _SQL_SELECT_SEARCHLOG_WHERE_PKS_IN =
		"SELECT searchLog FROM SearchLog searchLog WHERE searchLogId IN (";

	private static final String _SQL_COUNT_SEARCHLOG =
		"SELECT COUNT(searchLog) FROM SearchLog searchLog";

	private static final String _ORDER_BY_ENTITY_ALIAS = "searchLog.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SearchLog exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		SearchLogPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"date"});

}