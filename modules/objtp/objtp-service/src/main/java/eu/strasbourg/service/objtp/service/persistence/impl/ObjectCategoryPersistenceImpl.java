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

package eu.strasbourg.service.objtp.service.persistence.impl;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.objtp.exception.NoSuchObjectCategoryException;
import eu.strasbourg.service.objtp.model.ObjectCategory;
import eu.strasbourg.service.objtp.model.impl.ObjectCategoryImpl;
import eu.strasbourg.service.objtp.model.impl.ObjectCategoryModelImpl;
import eu.strasbourg.service.objtp.service.persistence.ObjectCategoryPersistence;

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
 * The persistence implementation for the object category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author JeremyZwickert
 * @generated
 */
public class ObjectCategoryPersistenceImpl
	extends BasePersistenceImpl<ObjectCategory>
	implements ObjectCategoryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ObjectCategoryUtil</code> to access the object category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ObjectCategoryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ObjectCategoryPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("code", "code_");

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

		setModelClass(ObjectCategory.class);
	}

	/**
	 * Caches the object category in the entity cache if it is enabled.
	 *
	 * @param objectCategory the object category
	 */
	@Override
	public void cacheResult(ObjectCategory objectCategory) {
		entityCache.putResult(
			ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
			ObjectCategoryImpl.class, objectCategory.getPrimaryKey(),
			objectCategory);

		objectCategory.resetOriginalValues();
	}

	/**
	 * Caches the object categories in the entity cache if it is enabled.
	 *
	 * @param objectCategories the object categories
	 */
	@Override
	public void cacheResult(List<ObjectCategory> objectCategories) {
		for (ObjectCategory objectCategory : objectCategories) {
			if (entityCache.getResult(
					ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
					ObjectCategoryImpl.class, objectCategory.getPrimaryKey()) ==
						null) {

				cacheResult(objectCategory);
			}
			else {
				objectCategory.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all object categories.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ObjectCategoryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the object category.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ObjectCategory objectCategory) {
		entityCache.removeResult(
			ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
			ObjectCategoryImpl.class, objectCategory.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ObjectCategory> objectCategories) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ObjectCategory objectCategory : objectCategories) {
			entityCache.removeResult(
				ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
				ObjectCategoryImpl.class, objectCategory.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
				ObjectCategoryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new object category with the primary key. Does not add the object category to the database.
	 *
	 * @param code the primary key for the new object category
	 * @return the new object category
	 */
	@Override
	public ObjectCategory create(String code) {
		ObjectCategory objectCategory = new ObjectCategoryImpl();

		objectCategory.setNew(true);
		objectCategory.setPrimaryKey(code);

		return objectCategory;
	}

	/**
	 * Removes the object category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param code the primary key of the object category
	 * @return the object category that was removed
	 * @throws NoSuchObjectCategoryException if a object category with the primary key could not be found
	 */
	@Override
	public ObjectCategory remove(String code)
		throws NoSuchObjectCategoryException {

		return remove((Serializable)code);
	}

	/**
	 * Removes the object category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the object category
	 * @return the object category that was removed
	 * @throws NoSuchObjectCategoryException if a object category with the primary key could not be found
	 */
	@Override
	public ObjectCategory remove(Serializable primaryKey)
		throws NoSuchObjectCategoryException {

		Session session = null;

		try {
			session = openSession();

			ObjectCategory objectCategory = (ObjectCategory)session.get(
				ObjectCategoryImpl.class, primaryKey);

			if (objectCategory == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchObjectCategoryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(objectCategory);
		}
		catch (NoSuchObjectCategoryException noSuchEntityException) {
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
	protected ObjectCategory removeImpl(ObjectCategory objectCategory) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(objectCategory)) {
				objectCategory = (ObjectCategory)session.get(
					ObjectCategoryImpl.class,
					objectCategory.getPrimaryKeyObj());
			}

			if (objectCategory != null) {
				session.delete(objectCategory);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (objectCategory != null) {
			clearCache(objectCategory);
		}

		return objectCategory;
	}

	@Override
	public ObjectCategory updateImpl(ObjectCategory objectCategory) {
		boolean isNew = objectCategory.isNew();

		Session session = null;

		try {
			session = openSession();

			if (objectCategory.isNew()) {
				session.save(objectCategory);

				objectCategory.setNew(false);
			}
			else {
				objectCategory = (ObjectCategory)session.merge(objectCategory);
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
			ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
			ObjectCategoryImpl.class, objectCategory.getPrimaryKey(),
			objectCategory, false);

		objectCategory.resetOriginalValues();

		return objectCategory;
	}

	/**
	 * Returns the object category with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the object category
	 * @return the object category
	 * @throws NoSuchObjectCategoryException if a object category with the primary key could not be found
	 */
	@Override
	public ObjectCategory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchObjectCategoryException {

		ObjectCategory objectCategory = fetchByPrimaryKey(primaryKey);

		if (objectCategory == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchObjectCategoryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return objectCategory;
	}

	/**
	 * Returns the object category with the primary key or throws a <code>NoSuchObjectCategoryException</code> if it could not be found.
	 *
	 * @param code the primary key of the object category
	 * @return the object category
	 * @throws NoSuchObjectCategoryException if a object category with the primary key could not be found
	 */
	@Override
	public ObjectCategory findByPrimaryKey(String code)
		throws NoSuchObjectCategoryException {

		return findByPrimaryKey((Serializable)code);
	}

	/**
	 * Returns the object category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the object category
	 * @return the object category, or <code>null</code> if a object category with the primary key could not be found
	 */
	@Override
	public ObjectCategory fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
			ObjectCategoryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ObjectCategory objectCategory = (ObjectCategory)serializable;

		if (objectCategory == null) {
			Session session = null;

			try {
				session = openSession();

				objectCategory = (ObjectCategory)session.get(
					ObjectCategoryImpl.class, primaryKey);

				if (objectCategory != null) {
					cacheResult(objectCategory);
				}
				else {
					entityCache.putResult(
						ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
						ObjectCategoryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
					ObjectCategoryImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return objectCategory;
	}

	/**
	 * Returns the object category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param code the primary key of the object category
	 * @return the object category, or <code>null</code> if a object category with the primary key could not be found
	 */
	@Override
	public ObjectCategory fetchByPrimaryKey(String code) {
		return fetchByPrimaryKey((Serializable)code);
	}

	@Override
	public Map<Serializable, ObjectCategory> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ObjectCategory> map =
			new HashMap<Serializable, ObjectCategory>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ObjectCategory objectCategory = fetchByPrimaryKey(primaryKey);

			if (objectCategory != null) {
				map.put(primaryKey, objectCategory);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
				ObjectCategoryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ObjectCategory)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_OBJECTCATEGORY_WHERE_PKS_IN);

		for (int i = 0; i < uncachedPrimaryKeys.size(); i++) {
			sb.append("?");

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			QueryPos queryPos = QueryPos.getInstance(query);

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				queryPos.add((String)primaryKey);
			}

			for (ObjectCategory objectCategory :
					(List<ObjectCategory>)query.list()) {

				map.put(objectCategory.getPrimaryKeyObj(), objectCategory);

				cacheResult(objectCategory);

				uncachedPrimaryKeys.remove(objectCategory.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
					ObjectCategoryImpl.class, primaryKey, nullModel);
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
	 * Returns all the object categories.
	 *
	 * @return the object categories
	 */
	@Override
	public List<ObjectCategory> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the object categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of object categories
	 * @param end the upper bound of the range of object categories (not inclusive)
	 * @return the range of object categories
	 */
	@Override
	public List<ObjectCategory> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the object categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of object categories
	 * @param end the upper bound of the range of object categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of object categories
	 */
	@Override
	public List<ObjectCategory> findAll(
		int start, int end,
		OrderByComparator<ObjectCategory> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the object categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of object categories
	 * @param end the upper bound of the range of object categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of object categories
	 */
	@Override
	public List<ObjectCategory> findAll(
		int start, int end, OrderByComparator<ObjectCategory> orderByComparator,
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

		List<ObjectCategory> list = null;

		if (useFinderCache) {
			list = (List<ObjectCategory>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_OBJECTCATEGORY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_OBJECTCATEGORY;

				sql = sql.concat(ObjectCategoryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ObjectCategory>)QueryUtil.list(
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
	 * Removes all the object categories from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ObjectCategory objectCategory : findAll()) {
			remove(objectCategory);
		}
	}

	/**
	 * Returns the number of object categories.
	 *
	 * @return the number of object categories
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_OBJECTCATEGORY);

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
		return ObjectCategoryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the object category persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
			ObjectCategoryModelImpl.FINDER_CACHE_ENABLED,
			ObjectCategoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
			ObjectCategoryModelImpl.FINDER_CACHE_ENABLED,
			ObjectCategoryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			ObjectCategoryModelImpl.ENTITY_CACHE_ENABLED,
			ObjectCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(ObjectCategoryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_OBJECTCATEGORY =
		"SELECT objectCategory FROM ObjectCategory objectCategory";

	private static final String _SQL_SELECT_OBJECTCATEGORY_WHERE_PKS_IN =
		"SELECT objectCategory FROM ObjectCategory objectCategory WHERE code_ IN (";

	private static final String _SQL_COUNT_OBJECTCATEGORY =
		"SELECT COUNT(objectCategory) FROM ObjectCategory objectCategory";

	private static final String _ORDER_BY_ENTITY_ALIAS = "objectCategory.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ObjectCategory exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectCategoryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"code"});

}