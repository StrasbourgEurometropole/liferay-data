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

import eu.strasbourg.service.csmap.model.CacheAgendaJson;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the cache agenda json service. This utility wraps <code>eu.strasbourg.service.csmap.service.persistence.impl.CacheAgendaJsonPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CacheAgendaJsonPersistence
 * @generated
 */
@ProviderType
public class CacheAgendaJsonUtil {

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
	public static void clearCache(CacheAgendaJson cacheAgendaJson) {
		getPersistence().clearCache(cacheAgendaJson);
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
	public static Map<Serializable, CacheAgendaJson> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CacheAgendaJson> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CacheAgendaJson> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CacheAgendaJson> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CacheAgendaJson> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CacheAgendaJson update(CacheAgendaJson cacheAgendaJson) {
		return getPersistence().update(cacheAgendaJson);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CacheAgendaJson update(
		CacheAgendaJson cacheAgendaJson, ServiceContext serviceContext) {

		return getPersistence().update(cacheAgendaJson, serviceContext);
	}

	/**
	 * Caches the cache agenda json in the entity cache if it is enabled.
	 *
	 * @param cacheAgendaJson the cache agenda json
	 */
	public static void cacheResult(CacheAgendaJson cacheAgendaJson) {
		getPersistence().cacheResult(cacheAgendaJson);
	}

	/**
	 * Caches the cache agenda jsons in the entity cache if it is enabled.
	 *
	 * @param cacheAgendaJsons the cache agenda jsons
	 */
	public static void cacheResult(List<CacheAgendaJson> cacheAgendaJsons) {
		getPersistence().cacheResult(cacheAgendaJsons);
	}

	/**
	 * Creates a new cache agenda json with the primary key. Does not add the cache agenda json to the database.
	 *
	 * @param cacheId the primary key for the new cache agenda json
	 * @return the new cache agenda json
	 */
	public static CacheAgendaJson create(long cacheId) {
		return getPersistence().create(cacheId);
	}

	/**
	 * Removes the cache agenda json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheId the primary key of the cache agenda json
	 * @return the cache agenda json that was removed
	 * @throws NoSuchCacheAgendaJsonException if a cache agenda json with the primary key could not be found
	 */
	public static CacheAgendaJson remove(long cacheId)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchCacheAgendaJsonException {

		return getPersistence().remove(cacheId);
	}

	public static CacheAgendaJson updateImpl(CacheAgendaJson cacheAgendaJson) {
		return getPersistence().updateImpl(cacheAgendaJson);
	}

	/**
	 * Returns the cache agenda json with the primary key or throws a <code>NoSuchCacheAgendaJsonException</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the cache agenda json
	 * @return the cache agenda json
	 * @throws NoSuchCacheAgendaJsonException if a cache agenda json with the primary key could not be found
	 */
	public static CacheAgendaJson findByPrimaryKey(long cacheId)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchCacheAgendaJsonException {

		return getPersistence().findByPrimaryKey(cacheId);
	}

	/**
	 * Returns the cache agenda json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the cache agenda json
	 * @return the cache agenda json, or <code>null</code> if a cache agenda json with the primary key could not be found
	 */
	public static CacheAgendaJson fetchByPrimaryKey(long cacheId) {
		return getPersistence().fetchByPrimaryKey(cacheId);
	}

	/**
	 * Returns all the cache agenda jsons.
	 *
	 * @return the cache agenda jsons
	 */
	public static List<CacheAgendaJson> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the cache agenda jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAgendaJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache agenda jsons
	 * @param end the upper bound of the range of cache agenda jsons (not inclusive)
	 * @return the range of cache agenda jsons
	 */
	public static List<CacheAgendaJson> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the cache agenda jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAgendaJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache agenda jsons
	 * @param end the upper bound of the range of cache agenda jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cache agenda jsons
	 */
	public static List<CacheAgendaJson> findAll(
		int start, int end,
		OrderByComparator<CacheAgendaJson> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache agenda jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAgendaJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache agenda jsons
	 * @param end the upper bound of the range of cache agenda jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cache agenda jsons
	 */
	public static List<CacheAgendaJson> findAll(
		int start, int end,
		OrderByComparator<CacheAgendaJson> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the cache agenda jsons from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of cache agenda jsons.
	 *
	 * @return the number of cache agenda jsons
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CacheAgendaJsonPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CacheAgendaJsonPersistence, CacheAgendaJsonPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CacheAgendaJsonPersistence.class);

		ServiceTracker<CacheAgendaJsonPersistence, CacheAgendaJsonPersistence>
			serviceTracker =
				new ServiceTracker
					<CacheAgendaJsonPersistence, CacheAgendaJsonPersistence>(
						bundle.getBundleContext(),
						CacheAgendaJsonPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}