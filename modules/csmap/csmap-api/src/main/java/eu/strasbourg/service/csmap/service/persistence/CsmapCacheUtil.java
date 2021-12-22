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

package eu.strasbourg.service.csmap.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.csmap.model.CsmapCache;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the csmap cache service. This utility wraps <code>eu.strasbourg.service.csmap.service.persistence.impl.CsmapCachePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CsmapCachePersistence
 * @generated
 */
@ProviderType
public class CsmapCacheUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(CsmapCache csmapCache) {
		getPersistence().clearCache(csmapCache);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CsmapCache> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CsmapCache> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CsmapCache> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CsmapCache> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CsmapCache> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CsmapCache update(CsmapCache csmapCache) {
		return getPersistence().update(csmapCache);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CsmapCache update(
		CsmapCache csmapCache, ServiceContext serviceContext) {

		return getPersistence().update(csmapCache, serviceContext);
	}

	/**
	 * Returns the csmap cache where codeCache = &#63; or throws a <code>NoSuchCsmapCacheException</code> if it could not be found.
	 *
	 * @param codeCache the code cache
	 * @return the matching csmap cache
	 * @throws NoSuchCsmapCacheException if a matching csmap cache could not be found
	 */
	public static CsmapCache findByCodeCache(long codeCache)
		throws eu.strasbourg.service.csmap.exception.NoSuchCsmapCacheException {

		return getPersistence().findByCodeCache(codeCache);
	}

	/**
	 * Returns the csmap cache where codeCache = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param codeCache the code cache
	 * @return the matching csmap cache, or <code>null</code> if a matching csmap cache could not be found
	 */
	public static CsmapCache fetchByCodeCache(long codeCache) {
		return getPersistence().fetchByCodeCache(codeCache);
	}

	/**
	 * Returns the csmap cache where codeCache = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param codeCache the code cache
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching csmap cache, or <code>null</code> if a matching csmap cache could not be found
	 */
	public static CsmapCache fetchByCodeCache(
		long codeCache, boolean retrieveFromCache) {

		return getPersistence().fetchByCodeCache(codeCache, retrieveFromCache);
	}

	/**
	 * Removes the csmap cache where codeCache = &#63; from the database.
	 *
	 * @param codeCache the code cache
	 * @return the csmap cache that was removed
	 */
	public static CsmapCache removeByCodeCache(long codeCache)
		throws eu.strasbourg.service.csmap.exception.NoSuchCsmapCacheException {

		return getPersistence().removeByCodeCache(codeCache);
	}

	/**
	 * Returns the number of csmap caches where codeCache = &#63;.
	 *
	 * @param codeCache the code cache
	 * @return the number of matching csmap caches
	 */
	public static int countByCodeCache(long codeCache) {
		return getPersistence().countByCodeCache(codeCache);
	}

	/**
	 * Returns all the csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @return the matching csmap caches
	 */
	public static List<CsmapCache> findByLastProcessNotSuccess(
		Boolean isLastProcessSuccess) {

		return getPersistence().findByLastProcessNotSuccess(
			isLastProcessSuccess);
	}

	/**
	 * Returns a range of all the csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @return the range of matching csmap caches
	 */
	public static List<CsmapCache> findByLastProcessNotSuccess(
		Boolean isLastProcessSuccess, int start, int end) {

		return getPersistence().findByLastProcessNotSuccess(
			isLastProcessSuccess, start, end);
	}

	/**
	 * Returns an ordered range of all the csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap caches
	 */
	public static List<CsmapCache> findByLastProcessNotSuccess(
		Boolean isLastProcessSuccess, int start, int end,
		OrderByComparator<CsmapCache> orderByComparator) {

		return getPersistence().findByLastProcessNotSuccess(
			isLastProcessSuccess, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap caches
	 */
	public static List<CsmapCache> findByLastProcessNotSuccess(
		Boolean isLastProcessSuccess, int start, int end,
		OrderByComparator<CsmapCache> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByLastProcessNotSuccess(
			isLastProcessSuccess, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache
	 * @throws NoSuchCsmapCacheException if a matching csmap cache could not be found
	 */
	public static CsmapCache findByLastProcessNotSuccess_First(
			Boolean isLastProcessSuccess,
			OrderByComparator<CsmapCache> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchCsmapCacheException {

		return getPersistence().findByLastProcessNotSuccess_First(
			isLastProcessSuccess, orderByComparator);
	}

	/**
	 * Returns the first csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache, or <code>null</code> if a matching csmap cache could not be found
	 */
	public static CsmapCache fetchByLastProcessNotSuccess_First(
		Boolean isLastProcessSuccess,
		OrderByComparator<CsmapCache> orderByComparator) {

		return getPersistence().fetchByLastProcessNotSuccess_First(
			isLastProcessSuccess, orderByComparator);
	}

	/**
	 * Returns the last csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache
	 * @throws NoSuchCsmapCacheException if a matching csmap cache could not be found
	 */
	public static CsmapCache findByLastProcessNotSuccess_Last(
			Boolean isLastProcessSuccess,
			OrderByComparator<CsmapCache> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchCsmapCacheException {

		return getPersistence().findByLastProcessNotSuccess_Last(
			isLastProcessSuccess, orderByComparator);
	}

	/**
	 * Returns the last csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache, or <code>null</code> if a matching csmap cache could not be found
	 */
	public static CsmapCache fetchByLastProcessNotSuccess_Last(
		Boolean isLastProcessSuccess,
		OrderByComparator<CsmapCache> orderByComparator) {

		return getPersistence().fetchByLastProcessNotSuccess_Last(
			isLastProcessSuccess, orderByComparator);
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
	public static CsmapCache[] findByLastProcessNotSuccess_PrevAndNext(
			long cacheId, Boolean isLastProcessSuccess,
			OrderByComparator<CsmapCache> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchCsmapCacheException {

		return getPersistence().findByLastProcessNotSuccess_PrevAndNext(
			cacheId, isLastProcessSuccess, orderByComparator);
	}

	/**
	 * Removes all the csmap caches where isLastProcessSuccess = &#63; from the database.
	 *
	 * @param isLastProcessSuccess the is last process success
	 */
	public static void removeByLastProcessNotSuccess(
		Boolean isLastProcessSuccess) {

		getPersistence().removeByLastProcessNotSuccess(isLastProcessSuccess);
	}

	/**
	 * Returns the number of csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @return the number of matching csmap caches
	 */
	public static int countByLastProcessNotSuccess(
		Boolean isLastProcessSuccess) {

		return getPersistence().countByLastProcessNotSuccess(
			isLastProcessSuccess);
	}

	/**
	 * Caches the csmap cache in the entity cache if it is enabled.
	 *
	 * @param csmapCache the csmap cache
	 */
	public static void cacheResult(CsmapCache csmapCache) {
		getPersistence().cacheResult(csmapCache);
	}

	/**
	 * Caches the csmap caches in the entity cache if it is enabled.
	 *
	 * @param csmapCaches the csmap caches
	 */
	public static void cacheResult(List<CsmapCache> csmapCaches) {
		getPersistence().cacheResult(csmapCaches);
	}

	/**
	 * Creates a new csmap cache with the primary key. Does not add the csmap cache to the database.
	 *
	 * @param cacheId the primary key for the new csmap cache
	 * @return the new csmap cache
	 */
	public static CsmapCache create(long cacheId) {
		return getPersistence().create(cacheId);
	}

	/**
	 * Removes the csmap cache with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheId the primary key of the csmap cache
	 * @return the csmap cache that was removed
	 * @throws NoSuchCsmapCacheException if a csmap cache with the primary key could not be found
	 */
	public static CsmapCache remove(long cacheId)
		throws eu.strasbourg.service.csmap.exception.NoSuchCsmapCacheException {

		return getPersistence().remove(cacheId);
	}

	public static CsmapCache updateImpl(CsmapCache csmapCache) {
		return getPersistence().updateImpl(csmapCache);
	}

	/**
	 * Returns the csmap cache with the primary key or throws a <code>NoSuchCsmapCacheException</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the csmap cache
	 * @return the csmap cache
	 * @throws NoSuchCsmapCacheException if a csmap cache with the primary key could not be found
	 */
	public static CsmapCache findByPrimaryKey(long cacheId)
		throws eu.strasbourg.service.csmap.exception.NoSuchCsmapCacheException {

		return getPersistence().findByPrimaryKey(cacheId);
	}

	/**
	 * Returns the csmap cache with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the csmap cache
	 * @return the csmap cache, or <code>null</code> if a csmap cache with the primary key could not be found
	 */
	public static CsmapCache fetchByPrimaryKey(long cacheId) {
		return getPersistence().fetchByPrimaryKey(cacheId);
	}

	/**
	 * Returns all the csmap caches.
	 *
	 * @return the csmap caches
	 */
	public static List<CsmapCache> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the csmap caches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @return the range of csmap caches
	 */
	public static List<CsmapCache> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the csmap caches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of csmap caches
	 */
	public static List<CsmapCache> findAll(
		int start, int end, OrderByComparator<CsmapCache> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap caches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of csmap caches
	 */
	public static List<CsmapCache> findAll(
		int start, int end, OrderByComparator<CsmapCache> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the csmap caches from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of csmap caches.
	 *
	 * @return the number of csmap caches
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CsmapCachePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CsmapCachePersistence, CsmapCachePersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CsmapCachePersistence.class);

		ServiceTracker<CsmapCachePersistence, CsmapCachePersistence>
			serviceTracker =
				new ServiceTracker
					<CsmapCachePersistence, CsmapCachePersistence>(
						bundle.getBundleContext(), CsmapCachePersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}