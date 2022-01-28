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

import eu.strasbourg.service.gtfs.model.CacheHoursJSON;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the cache hours json service. This utility wraps <code>eu.strasbourg.service.gtfs.service.persistence.impl.CacheHoursJSONPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see CacheHoursJSONPersistence
 * @generated
 */
@ProviderType
public class CacheHoursJSONUtil {

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
	public static void clearCache(CacheHoursJSON cacheHoursJSON) {
		getPersistence().clearCache(cacheHoursJSON);
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
	public static Map<Serializable, CacheHoursJSON> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CacheHoursJSON> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CacheHoursJSON> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CacheHoursJSON> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CacheHoursJSON> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CacheHoursJSON update(CacheHoursJSON cacheHoursJSON) {
		return getPersistence().update(cacheHoursJSON);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CacheHoursJSON update(
		CacheHoursJSON cacheHoursJSON, ServiceContext serviceContext) {

		return getPersistence().update(cacheHoursJSON, serviceContext);
	}

	/**
	 * Returns all the cache hours jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cache hours jsons
	 */
	public static List<CacheHoursJSON> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the cache hours jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @return the range of matching cache hours jsons
	 */
	public static List<CacheHoursJSON> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the cache hours jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache hours jsons
	 */
	public static List<CacheHoursJSON> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CacheHoursJSON> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache hours jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache hours jsons
	 */
	public static List<CacheHoursJSON> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CacheHoursJSON> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first cache hours json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON findByUuid_First(
			String uuid, OrderByComparator<CacheHoursJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheHoursJSONException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first cache hours json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON fetchByUuid_First(
		String uuid, OrderByComparator<CacheHoursJSON> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last cache hours json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON findByUuid_Last(
			String uuid, OrderByComparator<CacheHoursJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheHoursJSONException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last cache hours json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON fetchByUuid_Last(
		String uuid, OrderByComparator<CacheHoursJSON> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the cache hours jsons before and after the current cache hours json in the ordered set where uuid = &#63;.
	 *
	 * @param cacheHoursJSONPK the primary key of the current cache hours json
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache hours json
	 * @throws NoSuchCacheHoursJSONException if a cache hours json with the primary key could not be found
	 */
	public static CacheHoursJSON[] findByUuid_PrevAndNext(
			eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
				cacheHoursJSONPK,
			String uuid, OrderByComparator<CacheHoursJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheHoursJSONException {

		return getPersistence().findByUuid_PrevAndNext(
			cacheHoursJSONPK, uuid, orderByComparator);
	}

	/**
	 * Removes all the cache hours jsons where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of cache hours jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cache hours jsons
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the cache hours jsons where stopCode = &#63;.
	 *
	 * @param stopCode the stop code
	 * @return the matching cache hours jsons
	 */
	public static List<CacheHoursJSON> findByStopCode(String stopCode) {
		return getPersistence().findByStopCode(stopCode);
	}

	/**
	 * Returns a range of all the cache hours jsons where stopCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stopCode the stop code
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @return the range of matching cache hours jsons
	 */
	public static List<CacheHoursJSON> findByStopCode(
		String stopCode, int start, int end) {

		return getPersistence().findByStopCode(stopCode, start, end);
	}

	/**
	 * Returns an ordered range of all the cache hours jsons where stopCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stopCode the stop code
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache hours jsons
	 */
	public static List<CacheHoursJSON> findByStopCode(
		String stopCode, int start, int end,
		OrderByComparator<CacheHoursJSON> orderByComparator) {

		return getPersistence().findByStopCode(
			stopCode, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache hours jsons where stopCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stopCode the stop code
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache hours jsons
	 */
	public static List<CacheHoursJSON> findByStopCode(
		String stopCode, int start, int end,
		OrderByComparator<CacheHoursJSON> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByStopCode(
			stopCode, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first cache hours json in the ordered set where stopCode = &#63;.
	 *
	 * @param stopCode the stop code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON findByStopCode_First(
			String stopCode,
			OrderByComparator<CacheHoursJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheHoursJSONException {

		return getPersistence().findByStopCode_First(
			stopCode, orderByComparator);
	}

	/**
	 * Returns the first cache hours json in the ordered set where stopCode = &#63;.
	 *
	 * @param stopCode the stop code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON fetchByStopCode_First(
		String stopCode, OrderByComparator<CacheHoursJSON> orderByComparator) {

		return getPersistence().fetchByStopCode_First(
			stopCode, orderByComparator);
	}

	/**
	 * Returns the last cache hours json in the ordered set where stopCode = &#63;.
	 *
	 * @param stopCode the stop code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON findByStopCode_Last(
			String stopCode,
			OrderByComparator<CacheHoursJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheHoursJSONException {

		return getPersistence().findByStopCode_Last(
			stopCode, orderByComparator);
	}

	/**
	 * Returns the last cache hours json in the ordered set where stopCode = &#63;.
	 *
	 * @param stopCode the stop code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON fetchByStopCode_Last(
		String stopCode, OrderByComparator<CacheHoursJSON> orderByComparator) {

		return getPersistence().fetchByStopCode_Last(
			stopCode, orderByComparator);
	}

	/**
	 * Returns the cache hours jsons before and after the current cache hours json in the ordered set where stopCode = &#63;.
	 *
	 * @param cacheHoursJSONPK the primary key of the current cache hours json
	 * @param stopCode the stop code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache hours json
	 * @throws NoSuchCacheHoursJSONException if a cache hours json with the primary key could not be found
	 */
	public static CacheHoursJSON[] findByStopCode_PrevAndNext(
			eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
				cacheHoursJSONPK,
			String stopCode,
			OrderByComparator<CacheHoursJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheHoursJSONException {

		return getPersistence().findByStopCode_PrevAndNext(
			cacheHoursJSONPK, stopCode, orderByComparator);
	}

	/**
	 * Removes all the cache hours jsons where stopCode = &#63; from the database.
	 *
	 * @param stopCode the stop code
	 */
	public static void removeByStopCode(String stopCode) {
		getPersistence().removeByStopCode(stopCode);
	}

	/**
	 * Returns the number of cache hours jsons where stopCode = &#63;.
	 *
	 * @param stopCode the stop code
	 * @return the number of matching cache hours jsons
	 */
	public static int countByStopCode(String stopCode) {
		return getPersistence().countByStopCode(stopCode);
	}

	/**
	 * Returns all the cache hours jsons where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching cache hours jsons
	 */
	public static List<CacheHoursJSON> findByType(int type) {
		return getPersistence().findByType(type);
	}

	/**
	 * Returns a range of all the cache hours jsons where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @return the range of matching cache hours jsons
	 */
	public static List<CacheHoursJSON> findByType(
		int type, int start, int end) {

		return getPersistence().findByType(type, start, end);
	}

	/**
	 * Returns an ordered range of all the cache hours jsons where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache hours jsons
	 */
	public static List<CacheHoursJSON> findByType(
		int type, int start, int end,
		OrderByComparator<CacheHoursJSON> orderByComparator) {

		return getPersistence().findByType(type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache hours jsons where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache hours jsons
	 */
	public static List<CacheHoursJSON> findByType(
		int type, int start, int end,
		OrderByComparator<CacheHoursJSON> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByType(
			type, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first cache hours json in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON findByType_First(
			int type, OrderByComparator<CacheHoursJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheHoursJSONException {

		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	 * Returns the first cache hours json in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON fetchByType_First(
		int type, OrderByComparator<CacheHoursJSON> orderByComparator) {

		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	 * Returns the last cache hours json in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON findByType_Last(
			int type, OrderByComparator<CacheHoursJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheHoursJSONException {

		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	 * Returns the last cache hours json in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON fetchByType_Last(
		int type, OrderByComparator<CacheHoursJSON> orderByComparator) {

		return getPersistence().fetchByType_Last(type, orderByComparator);
	}

	/**
	 * Returns the cache hours jsons before and after the current cache hours json in the ordered set where type = &#63;.
	 *
	 * @param cacheHoursJSONPK the primary key of the current cache hours json
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache hours json
	 * @throws NoSuchCacheHoursJSONException if a cache hours json with the primary key could not be found
	 */
	public static CacheHoursJSON[] findByType_PrevAndNext(
			eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
				cacheHoursJSONPK,
			int type, OrderByComparator<CacheHoursJSON> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheHoursJSONException {

		return getPersistence().findByType_PrevAndNext(
			cacheHoursJSONPK, type, orderByComparator);
	}

	/**
	 * Removes all the cache hours jsons where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	public static void removeByType(int type) {
		getPersistence().removeByType(type);
	}

	/**
	 * Returns the number of cache hours jsons where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching cache hours jsons
	 */
	public static int countByType(int type) {
		return getPersistence().countByType(type);
	}

	/**
	 * Returns the cache hours json where stopCode = &#63; and type = &#63; or throws a <code>NoSuchCacheHoursJSONException</code> if it could not be found.
	 *
	 * @param stopCode the stop code
	 * @param type the type
	 * @return the matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON findByStopCodeAndType(
			String stopCode, int type)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheHoursJSONException {

		return getPersistence().findByStopCodeAndType(stopCode, type);
	}

	/**
	 * Returns the cache hours json where stopCode = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param stopCode the stop code
	 * @param type the type
	 * @return the matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON fetchByStopCodeAndType(
		String stopCode, int type) {

		return getPersistence().fetchByStopCodeAndType(stopCode, type);
	}

	/**
	 * Returns the cache hours json where stopCode = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param stopCode the stop code
	 * @param type the type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public static CacheHoursJSON fetchByStopCodeAndType(
		String stopCode, int type, boolean retrieveFromCache) {

		return getPersistence().fetchByStopCodeAndType(
			stopCode, type, retrieveFromCache);
	}

	/**
	 * Removes the cache hours json where stopCode = &#63; and type = &#63; from the database.
	 *
	 * @param stopCode the stop code
	 * @param type the type
	 * @return the cache hours json that was removed
	 */
	public static CacheHoursJSON removeByStopCodeAndType(
			String stopCode, int type)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheHoursJSONException {

		return getPersistence().removeByStopCodeAndType(stopCode, type);
	}

	/**
	 * Returns the number of cache hours jsons where stopCode = &#63; and type = &#63;.
	 *
	 * @param stopCode the stop code
	 * @param type the type
	 * @return the number of matching cache hours jsons
	 */
	public static int countByStopCodeAndType(String stopCode, int type) {
		return getPersistence().countByStopCodeAndType(stopCode, type);
	}

	/**
	 * Caches the cache hours json in the entity cache if it is enabled.
	 *
	 * @param cacheHoursJSON the cache hours json
	 */
	public static void cacheResult(CacheHoursJSON cacheHoursJSON) {
		getPersistence().cacheResult(cacheHoursJSON);
	}

	/**
	 * Caches the cache hours jsons in the entity cache if it is enabled.
	 *
	 * @param cacheHoursJSONs the cache hours jsons
	 */
	public static void cacheResult(List<CacheHoursJSON> cacheHoursJSONs) {
		getPersistence().cacheResult(cacheHoursJSONs);
	}

	/**
	 * Creates a new cache hours json with the primary key. Does not add the cache hours json to the database.
	 *
	 * @param cacheHoursJSONPK the primary key for the new cache hours json
	 * @return the new cache hours json
	 */
	public static CacheHoursJSON create(
		eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
			cacheHoursJSONPK) {

		return getPersistence().create(cacheHoursJSONPK);
	}

	/**
	 * Removes the cache hours json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheHoursJSONPK the primary key of the cache hours json
	 * @return the cache hours json that was removed
	 * @throws NoSuchCacheHoursJSONException if a cache hours json with the primary key could not be found
	 */
	public static CacheHoursJSON remove(
			eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
				cacheHoursJSONPK)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheHoursJSONException {

		return getPersistence().remove(cacheHoursJSONPK);
	}

	public static CacheHoursJSON updateImpl(CacheHoursJSON cacheHoursJSON) {
		return getPersistence().updateImpl(cacheHoursJSON);
	}

	/**
	 * Returns the cache hours json with the primary key or throws a <code>NoSuchCacheHoursJSONException</code> if it could not be found.
	 *
	 * @param cacheHoursJSONPK the primary key of the cache hours json
	 * @return the cache hours json
	 * @throws NoSuchCacheHoursJSONException if a cache hours json with the primary key could not be found
	 */
	public static CacheHoursJSON findByPrimaryKey(
			eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
				cacheHoursJSONPK)
		throws eu.strasbourg.service.gtfs.exception.
			NoSuchCacheHoursJSONException {

		return getPersistence().findByPrimaryKey(cacheHoursJSONPK);
	}

	/**
	 * Returns the cache hours json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cacheHoursJSONPK the primary key of the cache hours json
	 * @return the cache hours json, or <code>null</code> if a cache hours json with the primary key could not be found
	 */
	public static CacheHoursJSON fetchByPrimaryKey(
		eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
			cacheHoursJSONPK) {

		return getPersistence().fetchByPrimaryKey(cacheHoursJSONPK);
	}

	/**
	 * Returns all the cache hours jsons.
	 *
	 * @return the cache hours jsons
	 */
	public static List<CacheHoursJSON> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the cache hours jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @return the range of cache hours jsons
	 */
	public static List<CacheHoursJSON> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the cache hours jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cache hours jsons
	 */
	public static List<CacheHoursJSON> findAll(
		int start, int end,
		OrderByComparator<CacheHoursJSON> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache hours jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cache hours jsons
	 */
	public static List<CacheHoursJSON> findAll(
		int start, int end, OrderByComparator<CacheHoursJSON> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the cache hours jsons from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of cache hours jsons.
	 *
	 * @return the number of cache hours jsons
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static CacheHoursJSONPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CacheHoursJSONPersistence, CacheHoursJSONPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CacheHoursJSONPersistence.class);

		ServiceTracker<CacheHoursJSONPersistence, CacheHoursJSONPersistence>
			serviceTracker =
				new ServiceTracker
					<CacheHoursJSONPersistence, CacheHoursJSONPersistence>(
						bundle.getBundleContext(),
						CacheHoursJSONPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}