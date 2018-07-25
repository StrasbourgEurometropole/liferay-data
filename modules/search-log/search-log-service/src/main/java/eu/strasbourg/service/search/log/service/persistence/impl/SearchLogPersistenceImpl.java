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

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
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
 * @see SearchLogPersistence
 * @see eu.strasbourg.service.search.log.service.persistence.SearchLogUtil
 * @generated
 */
@ProviderType
public class SearchLogPersistenceImpl extends BasePersistenceImpl<SearchLog>
	implements SearchLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SearchLogUtil} to access the search log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SearchLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SearchLogModelImpl.ENTITY_CACHE_ENABLED,
			SearchLogModelImpl.FINDER_CACHE_ENABLED, SearchLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SearchLogModelImpl.ENTITY_CACHE_ENABLED,
			SearchLogModelImpl.FINDER_CACHE_ENABLED, SearchLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SearchLogModelImpl.ENTITY_CACHE_ENABLED,
			SearchLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public SearchLogPersistenceImpl() {
		setModelClass(SearchLog.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("date", "date_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the search log in the entity cache if it is enabled.
	 *
	 * @param searchLog the search log
	 */
	@Override
	public void cacheResult(SearchLog searchLog) {
		entityCache.putResult(SearchLogModelImpl.ENTITY_CACHE_ENABLED,
			SearchLogImpl.class, searchLog.getPrimaryKey(), searchLog);

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
			if (entityCache.getResult(SearchLogModelImpl.ENTITY_CACHE_ENABLED,
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SearchLog searchLog) {
		entityCache.removeResult(SearchLogModelImpl.ENTITY_CACHE_ENABLED,
			SearchLogImpl.class, searchLog.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SearchLog> searchLogs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SearchLog searchLog : searchLogs) {
			entityCache.removeResult(SearchLogModelImpl.ENTITY_CACHE_ENABLED,
				SearchLogImpl.class, searchLog.getPrimaryKey());
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

			SearchLog searchLog = (SearchLog)session.get(SearchLogImpl.class,
					primaryKey);

			if (searchLog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSearchLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(searchLog);
		}
		catch (NoSuchSearchLogException nsee) {
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
	protected SearchLog removeImpl(SearchLog searchLog) {
		searchLog = toUnwrappedModel(searchLog);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(searchLog)) {
				searchLog = (SearchLog)session.get(SearchLogImpl.class,
						searchLog.getPrimaryKeyObj());
			}

			if (searchLog != null) {
				session.delete(searchLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
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
		searchLog = toUnwrappedModel(searchLog);

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
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(SearchLogModelImpl.ENTITY_CACHE_ENABLED,
			SearchLogImpl.class, searchLog.getPrimaryKey(), searchLog, false);

		searchLog.resetOriginalValues();

		return searchLog;
	}

	protected SearchLog toUnwrappedModel(SearchLog searchLog) {
		if (searchLog instanceof SearchLogImpl) {
			return searchLog;
		}

		SearchLogImpl searchLogImpl = new SearchLogImpl();

		searchLogImpl.setNew(searchLog.isNew());
		searchLogImpl.setPrimaryKey(searchLog.getPrimaryKey());

		searchLogImpl.setSearchLogId(searchLog.getSearchLogId());
		searchLogImpl.setKeywords(searchLog.getKeywords());
		searchLogImpl.setSearchTime(searchLog.getSearchTime());
		searchLogImpl.setResultCount(searchLog.getResultCount());
		searchLogImpl.setResult1ClassId(searchLog.getResult1ClassId());
		searchLogImpl.setResult1ClassPK(searchLog.getResult1ClassPK());
		searchLogImpl.setResult1Title(searchLog.getResult1Title());
		searchLogImpl.setResult2ClassId(searchLog.getResult2ClassId());
		searchLogImpl.setResult2ClassPK(searchLog.getResult2ClassPK());
		searchLogImpl.setResult2Title(searchLog.getResult2Title());
		searchLogImpl.setResult3ClassId(searchLog.getResult3ClassId());
		searchLogImpl.setResult3ClassPK(searchLog.getResult3ClassPK());
		searchLogImpl.setResult3Title(searchLog.getResult3Title());
		searchLogImpl.setUserTargetClassId(searchLog.getUserTargetClassId());
		searchLogImpl.setUserTargetClassPK(searchLog.getUserTargetClassPK());
		searchLogImpl.setUserTargetTitle(searchLog.getUserTargetTitle());
		searchLogImpl.setGroupId(searchLog.getGroupId());
		searchLogImpl.setLayoutId(searchLog.getLayoutId());
		searchLogImpl.setLayoutFriendlyURL(searchLog.getLayoutFriendlyURL());
		searchLogImpl.setLanguage(searchLog.getLanguage());
		searchLogImpl.setDate(searchLog.getDate());

		return searchLogImpl;
	}

	/**
	 * Returns the search log with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
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

			throw new NoSuchSearchLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return searchLog;
	}

	/**
	 * Returns the search log with the primary key or throws a {@link NoSuchSearchLogException} if it could not be found.
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
		Serializable serializable = entityCache.getResult(SearchLogModelImpl.ENTITY_CACHE_ENABLED,
				SearchLogImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SearchLog searchLog = (SearchLog)serializable;

		if (searchLog == null) {
			Session session = null;

			try {
				session = openSession();

				searchLog = (SearchLog)session.get(SearchLogImpl.class,
						primaryKey);

				if (searchLog != null) {
					cacheResult(searchLog);
				}
				else {
					entityCache.putResult(SearchLogModelImpl.ENTITY_CACHE_ENABLED,
						SearchLogImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SearchLogModelImpl.ENTITY_CACHE_ENABLED,
					SearchLogImpl.class, primaryKey);

				throw processException(e);
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

		Map<Serializable, SearchLog> map = new HashMap<Serializable, SearchLog>();

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
			Serializable serializable = entityCache.getResult(SearchLogModelImpl.ENTITY_CACHE_ENABLED,
					SearchLogImpl.class, primaryKey);

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

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SEARCHLOG_WHERE_PKS_IN);

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

			for (SearchLog searchLog : (List<SearchLog>)q.list()) {
				map.put(searchLog.getPrimaryKeyObj(), searchLog);

				cacheResult(searchLog);

				uncachedPrimaryKeys.remove(searchLog.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SearchLogModelImpl.ENTITY_CACHE_ENABLED,
					SearchLogImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of search logs
	 * @param end the upper bound of the range of search logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of search logs
	 */
	@Override
	public List<SearchLog> findAll(int start, int end,
		OrderByComparator<SearchLog> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the search logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of search logs
	 * @param end the upper bound of the range of search logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of search logs
	 */
	@Override
	public List<SearchLog> findAll(int start, int end,
		OrderByComparator<SearchLog> orderByComparator,
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

		List<SearchLog> list = null;

		if (retrieveFromCache) {
			list = (List<SearchLog>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SEARCHLOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SEARCHLOG;

				if (pagination) {
					sql = sql.concat(SearchLogModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SearchLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SearchLog>)QueryUtil.list(q, getDialect(),
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
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SEARCHLOG);

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
		return SearchLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the search log persistence.
	 */
	public void afterPropertiesSet() {
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
	private static final String _SQL_SELECT_SEARCHLOG = "SELECT searchLog FROM SearchLog searchLog";
	private static final String _SQL_SELECT_SEARCHLOG_WHERE_PKS_IN = "SELECT searchLog FROM SearchLog searchLog WHERE searchLogId IN (";
	private static final String _SQL_COUNT_SEARCHLOG = "SELECT COUNT(searchLog) FROM SearchLog searchLog";
	private static final String _ORDER_BY_ENTITY_ALIAS = "searchLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SearchLog exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(SearchLogPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"date"
			});
}