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

package eu.strasbourg.service.gtfs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.gtfs.model.CacheAlertJSON;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the cache alert json service. This utility wraps <code>eu.strasbourg.service.gtfs.service.persistence.impl.CacheAlertJSONPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see CacheAlertJSONPersistence
 * @generated
 */
@ProviderType
public class CacheAlertJSONUtil {

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
	public static void clearCache(CacheAlertJSON cacheAlertJSON) {
		getPersistence().clearCache(cacheAlertJSON);
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
	public static Map<Serializable, CacheAlertJSON> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CacheAlertJSON> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CacheAlertJSON> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CacheAlertJSON> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CacheAlertJSON> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CacheAlertJSON update(CacheAlertJSON cacheAlertJSON) {
		return getPersistence().update(cacheAlertJSON);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CacheAlertJSON update(
		CacheAlertJSON cacheAlertJSON, ServiceContext serviceContext) {

		return getPersistence().update(cacheAlertJSON, serviceContext);
	}

	/**
	 * Returns all the cache alert jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cache alert jsons
	 */
	public static List<CacheAlertJSON> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the cache alert jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @return the range of matching cache alert jsons
	 */
	public static List<CacheAlertJSON> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the cache alert jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache alert jsons
	 */
	public static List<CacheAlertJSON> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CacheAlertJSON> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache alert jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache alert jsons
	 */
	public static List<CacheAlertJSON> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CacheAlertJSON> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache alert json
	 * @throws NoSuchCacheAlertJSONException if a matching cache alert json could not be found
	 */
	public static CacheAlertJSON findByUuid_First(
			String uuid, OrderByComparator<CacheAlertJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheAlertJSONException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache alert json, or <code>null</code> if a matching cache alert json could not be found
	 */
	public static CacheAlertJSON fetchByUuid_First(
		String uuid, OrderByComparator<CacheAlertJSON> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache alert json
	 * @throws NoSuchCacheAlertJSONException if a matching cache alert json could not be found
	 */
	public static CacheAlertJSON findByUuid_Last(
			String uuid, OrderByComparator<CacheAlertJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheAlertJSONException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache alert json, or <code>null</code> if a matching cache alert json could not be found
	 */
	public static CacheAlertJSON fetchByUuid_Last(
		String uuid, OrderByComparator<CacheAlertJSON> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the cache alert jsons before and after the current cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param cacheId the primary key of the current cache alert json
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache alert json
	 * @throws NoSuchCacheAlertJSONException if a cache alert json with the primary key could not be found
	 */
	public static CacheAlertJSON[] findByUuid_PrevAndNext(
			long cacheId, String uuid,
			OrderByComparator<CacheAlertJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheAlertJSONException {

		return getPersistence().findByUuid_PrevAndNext(
			cacheId, uuid, orderByComparator);
	}

	/**
	 * Removes all the cache alert jsons where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of cache alert jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cache alert jsons
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the cache alert jsons where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @return the matching cache alert jsons
	 */
	public static List<CacheAlertJSON> findBycacheId(long cacheId) {
		return getPersistence().findBycacheId(cacheId);
	}

	/**
	 * Returns a range of all the cache alert jsons where cacheId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cacheId the cache ID
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @return the range of matching cache alert jsons
	 */
	public static List<CacheAlertJSON> findBycacheId(
		long cacheId, int start, int end) {

		return getPersistence().findBycacheId(cacheId, start, end);
	}

	/**
	 * Returns an ordered range of all the cache alert jsons where cacheId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cacheId the cache ID
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache alert jsons
	 */
	public static List<CacheAlertJSON> findBycacheId(
		long cacheId, int start, int end,
		OrderByComparator<CacheAlertJSON> orderByComparator) {

		return getPersistence().findBycacheId(
			cacheId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache alert jsons where cacheId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cacheId the cache ID
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache alert jsons
	 */
	public static List<CacheAlertJSON> findBycacheId(
		long cacheId, int start, int end,
		OrderByComparator<CacheAlertJSON> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findBycacheId(
			cacheId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first cache alert json in the ordered set where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache alert json
	 * @throws NoSuchCacheAlertJSONException if a matching cache alert json could not be found
	 */
	public static CacheAlertJSON findBycacheId_First(
			long cacheId, OrderByComparator<CacheAlertJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheAlertJSONException {

		return getPersistence().findBycacheId_First(cacheId, orderByComparator);
	}

	/**
	 * Returns the first cache alert json in the ordered set where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache alert json, or <code>null</code> if a matching cache alert json could not be found
	 */
	public static CacheAlertJSON fetchBycacheId_First(
		long cacheId, OrderByComparator<CacheAlertJSON> orderByComparator) {

		return getPersistence().fetchBycacheId_First(
			cacheId, orderByComparator);
	}

	/**
	 * Returns the last cache alert json in the ordered set where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache alert json
	 * @throws NoSuchCacheAlertJSONException if a matching cache alert json could not be found
	 */
	public static CacheAlertJSON findBycacheId_Last(
			long cacheId, OrderByComparator<CacheAlertJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheAlertJSONException {

		return getPersistence().findBycacheId_Last(cacheId, orderByComparator);
	}

	/**
	 * Returns the last cache alert json in the ordered set where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache alert json, or <code>null</code> if a matching cache alert json could not be found
	 */
	public static CacheAlertJSON fetchBycacheId_Last(
		long cacheId, OrderByComparator<CacheAlertJSON> orderByComparator) {

		return getPersistence().fetchBycacheId_Last(cacheId, orderByComparator);
	}

	/**
	 * Removes all the cache alert jsons where cacheId = &#63; from the database.
	 *
	 * @param cacheId the cache ID
	 */
	public static void removeBycacheId(long cacheId) {
		getPersistence().removeBycacheId(cacheId);
	}

	/**
	 * Returns the number of cache alert jsons where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @return the number of matching cache alert jsons
	 */
	public static int countBycacheId(long cacheId) {
		return getPersistence().countBycacheId(cacheId);
	}

	/**
	 * Caches the cache alert json in the entity cache if it is enabled.
	 *
	 * @param cacheAlertJSON the cache alert json
	 */
	public static void cacheResult(CacheAlertJSON cacheAlertJSON) {
		getPersistence().cacheResult(cacheAlertJSON);
	}

	/**
	 * Caches the cache alert jsons in the entity cache if it is enabled.
	 *
	 * @param cacheAlertJSONs the cache alert jsons
	 */
	public static void cacheResult(List<CacheAlertJSON> cacheAlertJSONs) {
		getPersistence().cacheResult(cacheAlertJSONs);
	}

	/**
	 * Creates a new cache alert json with the primary key. Does not add the cache alert json to the database.
	 *
	 * @param cacheId the primary key for the new cache alert json
	 * @return the new cache alert json
	 */
	public static CacheAlertJSON create(long cacheId) {
		return getPersistence().create(cacheId);
	}

	/**
	 * Removes the cache alert json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheId the primary key of the cache alert json
	 * @return the cache alert json that was removed
	 * @throws NoSuchCacheAlertJSONException if a cache alert json with the primary key could not be found
	 */
	public static CacheAlertJSON remove(long cacheId)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheAlertJSONException {

		return getPersistence().remove(cacheId);
	}

	public static CacheAlertJSON updateImpl(CacheAlertJSON cacheAlertJSON) {
		return getPersistence().updateImpl(cacheAlertJSON);
	}

	/**
	 * Returns the cache alert json with the primary key or throws a <code>NoSuchCacheAlertJSONException</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the cache alert json
	 * @return the cache alert json
	 * @throws NoSuchCacheAlertJSONException if a cache alert json with the primary key could not be found
	 */
	public static CacheAlertJSON findByPrimaryKey(long cacheId)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheAlertJSONException {

		return getPersistence().findByPrimaryKey(cacheId);
	}

	/**
	 * Returns the cache alert json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the cache alert json
	 * @return the cache alert json, or <code>null</code> if a cache alert json with the primary key could not be found
	 */
	public static CacheAlertJSON fetchByPrimaryKey(long cacheId) {
		return getPersistence().fetchByPrimaryKey(cacheId);
	}

	/**
	 * Returns all the cache alert jsons.
	 *
	 * @return the cache alert jsons
	 */
	public static List<CacheAlertJSON> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the cache alert jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @return the range of cache alert jsons
	 */
	public static List<CacheAlertJSON> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the cache alert jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cache alert jsons
	 */
	public static List<CacheAlertJSON> findAll(
		int start, int end,
		OrderByComparator<CacheAlertJSON> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache alert jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cache alert jsons
	 */
	public static List<CacheAlertJSON> findAll(
		int start, int end, OrderByComparator<CacheAlertJSON> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the cache alert jsons from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of cache alert jsons.
	 *
	 * @return the number of cache alert jsons
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CacheAlertJSONPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CacheAlertJSONPersistence, CacheAlertJSONPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CacheAlertJSONPersistence.class);

		ServiceTracker<CacheAlertJSONPersistence, CacheAlertJSONPersistence>
			serviceTracker =
				new ServiceTracker
					<CacheAlertJSONPersistence, CacheAlertJSONPersistence>(
						bundle.getBundleContext(),
						CacheAlertJSONPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}