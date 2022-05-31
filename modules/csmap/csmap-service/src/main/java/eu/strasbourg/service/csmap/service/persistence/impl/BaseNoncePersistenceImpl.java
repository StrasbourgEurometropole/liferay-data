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

import eu.strasbourg.service.csmap.exception.NoSuchBaseNonceException;
import eu.strasbourg.service.csmap.model.BaseNonce;
import eu.strasbourg.service.csmap.model.impl.BaseNonceImpl;
import eu.strasbourg.service.csmap.model.impl.BaseNonceModelImpl;
import eu.strasbourg.service.csmap.service.persistence.BaseNoncePersistence;
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
 * The persistence implementation for the base nonce service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = BaseNoncePersistence.class)
public class BaseNoncePersistenceImpl
	extends BasePersistenceImpl<BaseNonce> implements BaseNoncePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>BaseNonceUtil</code> to access the base nonce persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		BaseNonceImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByValue;
	private FinderPath _finderPathCountByValue;

	/**
	 * Returns the base nonce where value = &#63; or throws a <code>NoSuchBaseNonceException</code> if it could not be found.
	 *
	 * @param value the value
	 * @return the matching base nonce
	 * @throws NoSuchBaseNonceException if a matching base nonce could not be found
	 */
	@Override
	public BaseNonce findByValue(String value) throws NoSuchBaseNonceException {
		BaseNonce baseNonce = fetchByValue(value);

		if (baseNonce == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("value=");
			sb.append(value);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchBaseNonceException(sb.toString());
		}

		return baseNonce;
	}

	/**
	 * Returns the base nonce where value = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param value the value
	 * @return the matching base nonce, or <code>null</code> if a matching base nonce could not be found
	 */
	@Override
	public BaseNonce fetchByValue(String value) {
		return fetchByValue(value, true);
	}

	/**
	 * Returns the base nonce where value = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param value the value
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching base nonce, or <code>null</code> if a matching base nonce could not be found
	 */
	@Override
	public BaseNonce fetchByValue(String value, boolean useFinderCache) {
		value = Objects.toString(value, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {value};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByValue, finderArgs, this);
		}

		if (result instanceof BaseNonce) {
			BaseNonce baseNonce = (BaseNonce)result;

			if (!Objects.equals(value, baseNonce.getValue())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_BASENONCE_WHERE);

			boolean bindValue = false;

			if (value.isEmpty()) {
				sb.append(_FINDER_COLUMN_VALUE_VALUE_3);
			}
			else {
				bindValue = true;

				sb.append(_FINDER_COLUMN_VALUE_VALUE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindValue) {
					queryPos.add(value);
				}

				List<BaseNonce> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByValue, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {value};
							}

							_log.warn(
								"BaseNoncePersistenceImpl.fetchByValue(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					BaseNonce baseNonce = list.get(0);

					result = baseNonce;

					cacheResult(baseNonce);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByValue, finderArgs);
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
			return (BaseNonce)result;
		}
	}

	/**
	 * Removes the base nonce where value = &#63; from the database.
	 *
	 * @param value the value
	 * @return the base nonce that was removed
	 */
	@Override
	public BaseNonce removeByValue(String value)
		throws NoSuchBaseNonceException {

		BaseNonce baseNonce = findByValue(value);

		return remove(baseNonce);
	}

	/**
	 * Returns the number of base nonces where value = &#63;.
	 *
	 * @param value the value
	 * @return the number of matching base nonces
	 */
	@Override
	public int countByValue(String value) {
		value = Objects.toString(value, "");

		FinderPath finderPath = _finderPathCountByValue;

		Object[] finderArgs = new Object[] {value};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BASENONCE_WHERE);

			boolean bindValue = false;

			if (value.isEmpty()) {
				sb.append(_FINDER_COLUMN_VALUE_VALUE_3);
			}
			else {
				bindValue = true;

				sb.append(_FINDER_COLUMN_VALUE_VALUE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindValue) {
					queryPos.add(value);
				}

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

	private static final String _FINDER_COLUMN_VALUE_VALUE_2 =
		"baseNonce.value = ?";

	private static final String _FINDER_COLUMN_VALUE_VALUE_3 =
		"(baseNonce.value IS NULL OR baseNonce.value = '')";

	public BaseNoncePersistenceImpl() {
		setModelClass(BaseNonce.class);

		setModelImplClass(BaseNonceImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the base nonce in the entity cache if it is enabled.
	 *
	 * @param baseNonce the base nonce
	 */
	@Override
	public void cacheResult(BaseNonce baseNonce) {
		entityCache.putResult(
			entityCacheEnabled, BaseNonceImpl.class, baseNonce.getPrimaryKey(),
			baseNonce);

		finderCache.putResult(
			_finderPathFetchByValue, new Object[] {baseNonce.getValue()},
			baseNonce);

		baseNonce.resetOriginalValues();
	}

	/**
	 * Caches the base nonces in the entity cache if it is enabled.
	 *
	 * @param baseNonces the base nonces
	 */
	@Override
	public void cacheResult(List<BaseNonce> baseNonces) {
		for (BaseNonce baseNonce : baseNonces) {
			if (entityCache.getResult(
					entityCacheEnabled, BaseNonceImpl.class,
					baseNonce.getPrimaryKey()) == null) {

				cacheResult(baseNonce);
			}
			else {
				baseNonce.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all base nonces.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BaseNonceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the base nonce.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BaseNonce baseNonce) {
		entityCache.removeResult(
			entityCacheEnabled, BaseNonceImpl.class, baseNonce.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((BaseNonceModelImpl)baseNonce, true);
	}

	@Override
	public void clearCache(List<BaseNonce> baseNonces) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BaseNonce baseNonce : baseNonces) {
			entityCache.removeResult(
				entityCacheEnabled, BaseNonceImpl.class,
				baseNonce.getPrimaryKey());

			clearUniqueFindersCache((BaseNonceModelImpl)baseNonce, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, BaseNonceImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		BaseNonceModelImpl baseNonceModelImpl) {

		Object[] args = new Object[] {baseNonceModelImpl.getValue()};

		finderCache.putResult(
			_finderPathCountByValue, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByValue, args, baseNonceModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		BaseNonceModelImpl baseNonceModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {baseNonceModelImpl.getValue()};

			finderCache.removeResult(_finderPathCountByValue, args);
			finderCache.removeResult(_finderPathFetchByValue, args);
		}

		if ((baseNonceModelImpl.getColumnBitmask() &
			 _finderPathFetchByValue.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				baseNonceModelImpl.getOriginalValue()
			};

			finderCache.removeResult(_finderPathCountByValue, args);
			finderCache.removeResult(_finderPathFetchByValue, args);
		}
	}

	/**
	 * Creates a new base nonce with the primary key. Does not add the base nonce to the database.
	 *
	 * @param baseNonceId the primary key for the new base nonce
	 * @return the new base nonce
	 */
	@Override
	public BaseNonce create(long baseNonceId) {
		BaseNonce baseNonce = new BaseNonceImpl();

		baseNonce.setNew(true);
		baseNonce.setPrimaryKey(baseNonceId);

		return baseNonce;
	}

	/**
	 * Removes the base nonce with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param baseNonceId the primary key of the base nonce
	 * @return the base nonce that was removed
	 * @throws NoSuchBaseNonceException if a base nonce with the primary key could not be found
	 */
	@Override
	public BaseNonce remove(long baseNonceId) throws NoSuchBaseNonceException {
		return remove((Serializable)baseNonceId);
	}

	/**
	 * Removes the base nonce with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the base nonce
	 * @return the base nonce that was removed
	 * @throws NoSuchBaseNonceException if a base nonce with the primary key could not be found
	 */
	@Override
	public BaseNonce remove(Serializable primaryKey)
		throws NoSuchBaseNonceException {

		Session session = null;

		try {
			session = openSession();

			BaseNonce baseNonce = (BaseNonce)session.get(
				BaseNonceImpl.class, primaryKey);

			if (baseNonce == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBaseNonceException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(baseNonce);
		}
		catch (NoSuchBaseNonceException noSuchEntityException) {
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
	protected BaseNonce removeImpl(BaseNonce baseNonce) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(baseNonce)) {
				baseNonce = (BaseNonce)session.get(
					BaseNonceImpl.class, baseNonce.getPrimaryKeyObj());
			}

			if (baseNonce != null) {
				session.delete(baseNonce);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (baseNonce != null) {
			clearCache(baseNonce);
		}

		return baseNonce;
	}

	@Override
	public BaseNonce updateImpl(BaseNonce baseNonce) {
		boolean isNew = baseNonce.isNew();

		if (!(baseNonce instanceof BaseNonceModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(baseNonce.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(baseNonce);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in baseNonce proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom BaseNonce implementation " +
					baseNonce.getClass());
		}

		BaseNonceModelImpl baseNonceModelImpl = (BaseNonceModelImpl)baseNonce;

		Session session = null;

		try {
			session = openSession();

			if (baseNonce.isNew()) {
				session.save(baseNonce);

				baseNonce.setNew(false);
			}
			else {
				baseNonce = (BaseNonce)session.merge(baseNonce);
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
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			entityCacheEnabled, BaseNonceImpl.class, baseNonce.getPrimaryKey(),
			baseNonce, false);

		clearUniqueFindersCache(baseNonceModelImpl, false);
		cacheUniqueFindersCache(baseNonceModelImpl);

		baseNonce.resetOriginalValues();

		return baseNonce;
	}

	/**
	 * Returns the base nonce with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the base nonce
	 * @return the base nonce
	 * @throws NoSuchBaseNonceException if a base nonce with the primary key could not be found
	 */
	@Override
	public BaseNonce findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBaseNonceException {

		BaseNonce baseNonce = fetchByPrimaryKey(primaryKey);

		if (baseNonce == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBaseNonceException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return baseNonce;
	}

	/**
	 * Returns the base nonce with the primary key or throws a <code>NoSuchBaseNonceException</code> if it could not be found.
	 *
	 * @param baseNonceId the primary key of the base nonce
	 * @return the base nonce
	 * @throws NoSuchBaseNonceException if a base nonce with the primary key could not be found
	 */
	@Override
	public BaseNonce findByPrimaryKey(long baseNonceId)
		throws NoSuchBaseNonceException {

		return findByPrimaryKey((Serializable)baseNonceId);
	}

	/**
	 * Returns the base nonce with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param baseNonceId the primary key of the base nonce
	 * @return the base nonce, or <code>null</code> if a base nonce with the primary key could not be found
	 */
	@Override
	public BaseNonce fetchByPrimaryKey(long baseNonceId) {
		return fetchByPrimaryKey((Serializable)baseNonceId);
	}

	/**
	 * Returns all the base nonces.
	 *
	 * @return the base nonces
	 */
	@Override
	public List<BaseNonce> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the base nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BaseNonceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of base nonces
	 * @param end the upper bound of the range of base nonces (not inclusive)
	 * @return the range of base nonces
	 */
	@Override
	public List<BaseNonce> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the base nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BaseNonceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of base nonces
	 * @param end the upper bound of the range of base nonces (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of base nonces
	 */
	@Override
	public List<BaseNonce> findAll(
		int start, int end, OrderByComparator<BaseNonce> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the base nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BaseNonceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of base nonces
	 * @param end the upper bound of the range of base nonces (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of base nonces
	 */
	@Override
	public List<BaseNonce> findAll(
		int start, int end, OrderByComparator<BaseNonce> orderByComparator,
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

		List<BaseNonce> list = null;

		if (useFinderCache) {
			list = (List<BaseNonce>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_BASENONCE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_BASENONCE;

				sql = sql.concat(BaseNonceModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<BaseNonce>)QueryUtil.list(
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
	 * Removes all the base nonces from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BaseNonce baseNonce : findAll()) {
			remove(baseNonce);
		}
	}

	/**
	 * Returns the number of base nonces.
	 *
	 * @return the number of base nonces
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_BASENONCE);

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
		return "baseNonceId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_BASENONCE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return BaseNonceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the base nonce persistence.
	 */
	@Activate
	public void activate() {
		BaseNonceModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		BaseNonceModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, BaseNonceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, BaseNonceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByValue = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, BaseNonceImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByValue",
			new String[] {String.class.getName()},
			BaseNonceModelImpl.VALUE_COLUMN_BITMASK);

		_finderPathCountByValue = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByValue",
			new String[] {String.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(BaseNonceImpl.class.getName());
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
				"value.object.column.bitmask.enabled.eu.strasbourg.service.csmap.model.BaseNonce"),
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

	private static final String _SQL_SELECT_BASENONCE =
		"SELECT baseNonce FROM BaseNonce baseNonce";

	private static final String _SQL_SELECT_BASENONCE_WHERE =
		"SELECT baseNonce FROM BaseNonce baseNonce WHERE ";

	private static final String _SQL_COUNT_BASENONCE =
		"SELECT COUNT(baseNonce) FROM BaseNonce baseNonce";

	private static final String _SQL_COUNT_BASENONCE_WHERE =
		"SELECT COUNT(baseNonce) FROM BaseNonce baseNonce WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "baseNonce.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No BaseNonce exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No BaseNonce exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		BaseNoncePersistenceImpl.class);

	static {
		try {
			Class.forName(csmapPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

}