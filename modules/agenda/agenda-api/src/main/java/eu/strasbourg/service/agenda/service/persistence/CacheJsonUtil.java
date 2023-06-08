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

package eu.strasbourg.service.agenda.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.agenda.model.CacheJson;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the cache json service. This utility wraps <code>eu.strasbourg.service.agenda.service.persistence.impl.CacheJsonPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see CacheJsonPersistence
 * @generated
 */
public class CacheJsonUtil {

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
	public static void clearCache(CacheJson cacheJson) {
		getPersistence().clearCache(cacheJson);
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
	public static Map<Serializable, CacheJson> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CacheJson> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CacheJson> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CacheJson> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CacheJson update(CacheJson cacheJson) {
		return getPersistence().update(cacheJson);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CacheJson update(
		CacheJson cacheJson, ServiceContext serviceContext) {

		return getPersistence().update(cacheJson, serviceContext);
	}

	/**
	 * Returns the cache json where eventId = &#63; or throws a <code>NoSuchCacheJsonException</code> if it could not be found.
	 *
	 * @param eventId the event ID
	 * @return the matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByeventId(long eventId)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByeventId(eventId);
	}

	/**
	 * Returns the cache json where eventId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param eventId the event ID
	 * @return the matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByeventId(long eventId) {
		return getPersistence().fetchByeventId(eventId);
	}

	/**
	 * Returns the cache json where eventId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param eventId the event ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByeventId(
		long eventId, boolean useFinderCache) {

		return getPersistence().fetchByeventId(eventId, useFinderCache);
	}

	/**
	 * Removes the cache json where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 * @return the cache json that was removed
	 */
	public static CacheJson removeByeventId(long eventId)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().removeByeventId(eventId);
	}

	/**
	 * Returns the number of cache jsons where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching cache jsons
	 */
	public static int countByeventId(long eventId) {
		return getPersistence().countByeventId(eventId);
	}

	/**
	 * Returns the cache json where eventId = &#63; and isApproved = &#63; or throws a <code>NoSuchCacheJsonException</code> if it could not be found.
	 *
	 * @param eventId the event ID
	 * @param isApproved the is approved
	 * @return the matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByeventIdAndIsApproved(
			long eventId, boolean isApproved)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByeventIdAndIsApproved(eventId, isApproved);
	}

	/**
	 * Returns the cache json where eventId = &#63; and isApproved = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param eventId the event ID
	 * @param isApproved the is approved
	 * @return the matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByeventIdAndIsApproved(
		long eventId, boolean isApproved) {

		return getPersistence().fetchByeventIdAndIsApproved(
			eventId, isApproved);
	}

	/**
	 * Returns the cache json where eventId = &#63; and isApproved = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param eventId the event ID
	 * @param isApproved the is approved
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByeventIdAndIsApproved(
		long eventId, boolean isApproved, boolean useFinderCache) {

		return getPersistence().fetchByeventIdAndIsApproved(
			eventId, isApproved, useFinderCache);
	}

	/**
	 * Removes the cache json where eventId = &#63; and isApproved = &#63; from the database.
	 *
	 * @param eventId the event ID
	 * @param isApproved the is approved
	 * @return the cache json that was removed
	 */
	public static CacheJson removeByeventIdAndIsApproved(
			long eventId, boolean isApproved)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().removeByeventIdAndIsApproved(
			eventId, isApproved);
	}

	/**
	 * Returns the number of cache jsons where eventId = &#63; and isApproved = &#63;.
	 *
	 * @param eventId the event ID
	 * @param isApproved the is approved
	 * @return the number of matching cache jsons
	 */
	public static int countByeventIdAndIsApproved(
		long eventId, boolean isApproved) {

		return getPersistence().countByeventIdAndIsApproved(
			eventId, isApproved);
	}

	/**
	 * Returns all the cache jsons where isApproved = &#63;.
	 *
	 * @param isApproved the is approved
	 * @return the matching cache jsons
	 */
	public static List<CacheJson> findByisApproved(boolean isApproved) {
		return getPersistence().findByisApproved(isApproved);
	}

	/**
	 * Returns a range of all the cache jsons where isApproved = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param isApproved the is approved
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	public static List<CacheJson> findByisApproved(
		boolean isApproved, int start, int end) {

		return getPersistence().findByisApproved(isApproved, start, end);
	}

	/**
	 * Returns an ordered range of all the cache jsons where isApproved = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param isApproved the is approved
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	public static List<CacheJson> findByisApproved(
		boolean isApproved, int start, int end,
		OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().findByisApproved(
			isApproved, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache jsons where isApproved = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param isApproved the is approved
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	public static List<CacheJson> findByisApproved(
		boolean isApproved, int start, int end,
		OrderByComparator<CacheJson> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByisApproved(
			isApproved, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first cache json in the ordered set where isApproved = &#63;.
	 *
	 * @param isApproved the is approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByisApproved_First(
			boolean isApproved, OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByisApproved_First(
			isApproved, orderByComparator);
	}

	/**
	 * Returns the first cache json in the ordered set where isApproved = &#63;.
	 *
	 * @param isApproved the is approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByisApproved_First(
		boolean isApproved, OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().fetchByisApproved_First(
			isApproved, orderByComparator);
	}

	/**
	 * Returns the last cache json in the ordered set where isApproved = &#63;.
	 *
	 * @param isApproved the is approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByisApproved_Last(
			boolean isApproved, OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByisApproved_Last(
			isApproved, orderByComparator);
	}

	/**
	 * Returns the last cache json in the ordered set where isApproved = &#63;.
	 *
	 * @param isApproved the is approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByisApproved_Last(
		boolean isApproved, OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().fetchByisApproved_Last(
			isApproved, orderByComparator);
	}

	/**
	 * Returns the cache jsons before and after the current cache json in the ordered set where isApproved = &#63;.
	 *
	 * @param eventId the primary key of the current cache json
	 * @param isApproved the is approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	public static CacheJson[] findByisApproved_PrevAndNext(
			long eventId, boolean isApproved,
			OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByisApproved_PrevAndNext(
			eventId, isApproved, orderByComparator);
	}

	/**
	 * Removes all the cache jsons where isApproved = &#63; from the database.
	 *
	 * @param isApproved the is approved
	 */
	public static void removeByisApproved(boolean isApproved) {
		getPersistence().removeByisApproved(isApproved);
	}

	/**
	 * Returns the number of cache jsons where isApproved = &#63;.
	 *
	 * @param isApproved the is approved
	 * @return the number of matching cache jsons
	 */
	public static int countByisApproved(boolean isApproved) {
		return getPersistence().countByisApproved(isApproved);
	}

	/**
	 * Caches the cache json in the entity cache if it is enabled.
	 *
	 * @param cacheJson the cache json
	 */
	public static void cacheResult(CacheJson cacheJson) {
		getPersistence().cacheResult(cacheJson);
	}

	/**
	 * Caches the cache jsons in the entity cache if it is enabled.
	 *
	 * @param cacheJsons the cache jsons
	 */
	public static void cacheResult(List<CacheJson> cacheJsons) {
		getPersistence().cacheResult(cacheJsons);
	}

	/**
	 * Creates a new cache json with the primary key. Does not add the cache json to the database.
	 *
	 * @param eventId the primary key for the new cache json
	 * @return the new cache json
	 */
	public static CacheJson create(long eventId) {
		return getPersistence().create(eventId);
	}

	/**
	 * Removes the cache json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventId the primary key of the cache json
	 * @return the cache json that was removed
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	public static CacheJson remove(long eventId)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().remove(eventId);
	}

	public static CacheJson updateImpl(CacheJson cacheJson) {
		return getPersistence().updateImpl(cacheJson);
	}

	/**
	 * Returns the cache json with the primary key or throws a <code>NoSuchCacheJsonException</code> if it could not be found.
	 *
	 * @param eventId the primary key of the cache json
	 * @return the cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	public static CacheJson findByPrimaryKey(long eventId)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByPrimaryKey(eventId);
	}

	/**
	 * Returns the cache json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventId the primary key of the cache json
	 * @return the cache json, or <code>null</code> if a cache json with the primary key could not be found
	 */
	public static CacheJson fetchByPrimaryKey(long eventId) {
		return getPersistence().fetchByPrimaryKey(eventId);
	}

	/**
	 * Returns all the cache jsons.
	 *
	 * @return the cache jsons
	 */
	public static List<CacheJson> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of cache jsons
	 */
	public static List<CacheJson> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cache jsons
	 */
	public static List<CacheJson> findAll(
		int start, int end, OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cache jsons
	 */
	public static List<CacheJson> findAll(
		int start, int end, OrderByComparator<CacheJson> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the cache jsons from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of cache jsons.
	 *
	 * @return the number of cache jsons
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CacheJsonPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CacheJsonPersistence, CacheJsonPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CacheJsonPersistence.class);

		ServiceTracker<CacheJsonPersistence, CacheJsonPersistence>
			serviceTracker =
				new ServiceTracker<CacheJsonPersistence, CacheJsonPersistence>(
					bundle.getBundleContext(), CacheJsonPersistence.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}