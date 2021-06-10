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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.agenda.model.CacheJson;

import java.io.Serializable;

import java.util.Date;
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
@ProviderType
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
	 * Returns all the cache jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cache jsons
	 */
	public static List<CacheJson> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	public static List<CacheJson> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	public static List<CacheJson> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	public static List<CacheJson> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByUuid_First(
			String uuid, OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByUuid_First(
		String uuid, OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByUuid_Last(
			String uuid, OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByUuid_Last(
		String uuid, OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the cache jsons before and after the current cache json in the ordered set where uuid = &#63;.
	 *
	 * @param eventId the primary key of the current cache json
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	public static CacheJson[] findByUuid_PrevAndNext(
			long eventId, String uuid,
			OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByUuid_PrevAndNext(
			eventId, uuid, orderByComparator);
	}

	/**
	 * Removes all the cache jsons where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of cache jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cache jsons
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the cache jsons where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching cache jsons
	 */
	public static List<CacheJson> findByeventId(long eventId) {
		return getPersistence().findByeventId(eventId);
	}

	/**
	 * Returns a range of all the cache jsons where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	public static List<CacheJson> findByeventId(
		long eventId, int start, int end) {

		return getPersistence().findByeventId(eventId, start, end);
	}

	/**
	 * Returns an ordered range of all the cache jsons where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	public static List<CacheJson> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().findByeventId(
			eventId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache jsons where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	public static List<CacheJson> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByeventId(
			eventId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByeventId_First(
			long eventId, OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByeventId_First(eventId, orderByComparator);
	}

	/**
	 * Returns the first cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByeventId_First(
		long eventId, OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().fetchByeventId_First(
			eventId, orderByComparator);
	}

	/**
	 * Returns the last cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByeventId_Last(
			long eventId, OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByeventId_Last(eventId, orderByComparator);
	}

	/**
	 * Returns the last cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByeventId_Last(
		long eventId, OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().fetchByeventId_Last(eventId, orderByComparator);
	}

	/**
	 * Removes all the cache jsons where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 */
	public static void removeByeventId(long eventId) {
		getPersistence().removeByeventId(eventId);
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
	 * Returns all the cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @return the matching cache jsons
	 */
	public static List<CacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive) {

		return getPersistence().findByCreatedDateAndIsActive(
			createEvent, isActive);
	}

	/**
	 * Returns a range of all the cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	public static List<CacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive, int start, int end) {

		return getPersistence().findByCreatedDateAndIsActive(
			createEvent, isActive, start, end);
	}

	/**
	 * Returns an ordered range of all the cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	public static List<CacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive, int start, int end,
		OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().findByCreatedDateAndIsActive(
			createEvent, isActive, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	public static List<CacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive, int start, int end,
		OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByCreatedDateAndIsActive(
			createEvent, isActive, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByCreatedDateAndIsActive_First(
			Date createEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByCreatedDateAndIsActive_First(
			createEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the first cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByCreatedDateAndIsActive_First(
		Date createEvent, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().fetchByCreatedDateAndIsActive_First(
			createEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the last cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByCreatedDateAndIsActive_Last(
			Date createEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByCreatedDateAndIsActive_Last(
			createEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the last cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByCreatedDateAndIsActive_Last(
		Date createEvent, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().fetchByCreatedDateAndIsActive_Last(
			createEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the cache jsons before and after the current cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current cache json
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	public static CacheJson[] findByCreatedDateAndIsActive_PrevAndNext(
			long eventId, Date createEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByCreatedDateAndIsActive_PrevAndNext(
			eventId, createEvent, isActive, orderByComparator);
	}

	/**
	 * Removes all the cache jsons where createEvent &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 */
	public static void removeByCreatedDateAndIsActive(
		Date createEvent, boolean isActive) {

		getPersistence().removeByCreatedDateAndIsActive(createEvent, isActive);
	}

	/**
	 * Returns the number of cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @return the number of matching cache jsons
	 */
	public static int countByCreatedDateAndIsActive(
		Date createEvent, boolean isActive) {

		return getPersistence().countByCreatedDateAndIsActive(
			createEvent, isActive);
	}

	/**
	 * Returns all the cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the matching cache jsons
	 */
	public static List<CacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive) {

		return getPersistence().findByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive);
	}

	/**
	 * Returns a range of all the cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	public static List<CacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive, int start,
		int end) {

		return getPersistence().findByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive, start, end);
	}

	/**
	 * Returns an ordered range of all the cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	public static List<CacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive, int start,
		int end, OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().findByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	public static List<CacheJson> findByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive, int start,
		int end, OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByCreatedDateAndModifiedDateAndIsActive_First(
			Date createEvent, Date modifiedEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActive_First(
				createEvent, modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the first cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByCreatedDateAndModifiedDateAndIsActive_First(
		Date createEvent, Date modifiedEvent, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().
			fetchByCreatedDateAndModifiedDateAndIsActive_First(
				createEvent, modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the last cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByCreatedDateAndModifiedDateAndIsActive_Last(
			Date createEvent, Date modifiedEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActive_Last(
				createEvent, modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the last cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByCreatedDateAndModifiedDateAndIsActive_Last(
		Date createEvent, Date modifiedEvent, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().
			fetchByCreatedDateAndModifiedDateAndIsActive_Last(
				createEvent, modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the cache jsons before and after the current cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current cache json
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	public static CacheJson[]
			findByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				long eventId, Date createEvent, Date modifiedEvent,
				boolean isActive,
				OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				eventId, createEvent, modifiedEvent, isActive,
				orderByComparator);
	}

	/**
	 * Removes all the cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 */
	public static void removeByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive) {

		getPersistence().removeByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive);
	}

	/**
	 * Returns the number of cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the number of matching cache jsons
	 */
	public static int countByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive) {

		return getPersistence().countByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive);
	}

	/**
	 * Returns all the cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the matching cache jsons
	 */
	public static List<CacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive) {

		return getPersistence().findByModifiedDateAndIsActive(
			modifiedEvent, isActive);
	}

	/**
	 * Returns a range of all the cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of matching cache jsons
	 */
	public static List<CacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive, int start, int end) {

		return getPersistence().findByModifiedDateAndIsActive(
			modifiedEvent, isActive, start, end);
	}

	/**
	 * Returns an ordered range of all the cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache jsons
	 */
	public static List<CacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive, int start, int end,
		OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().findByModifiedDateAndIsActive(
			modifiedEvent, isActive, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache jsons
	 */
	public static List<CacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive, int start, int end,
		OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByModifiedDateAndIsActive(
			modifiedEvent, isActive, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByModifiedDateAndIsActive_First(
			Date modifiedEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByModifiedDateAndIsActive_First(
			modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the first cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByModifiedDateAndIsActive_First(
		Date modifiedEvent, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().fetchByModifiedDateAndIsActive_First(
			modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the last cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json
	 * @throws NoSuchCacheJsonException if a matching cache json could not be found
	 */
	public static CacheJson findByModifiedDateAndIsActive_Last(
			Date modifiedEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByModifiedDateAndIsActive_Last(
			modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the last cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache json, or <code>null</code> if a matching cache json could not be found
	 */
	public static CacheJson fetchByModifiedDateAndIsActive_Last(
		Date modifiedEvent, boolean isActive,
		OrderByComparator<CacheJson> orderByComparator) {

		return getPersistence().fetchByModifiedDateAndIsActive_Last(
			modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the cache jsons before and after the current cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current cache json
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache json
	 * @throws NoSuchCacheJsonException if a cache json with the primary key could not be found
	 */
	public static CacheJson[] findByModifiedDateAndIsActive_PrevAndNext(
			long eventId, Date modifiedEvent, boolean isActive,
			OrderByComparator<CacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCacheJsonException {

		return getPersistence().findByModifiedDateAndIsActive_PrevAndNext(
			eventId, modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Removes all the cache jsons where modifiedEvent &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 */
	public static void removeByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive) {

		getPersistence().removeByModifiedDateAndIsActive(
			modifiedEvent, isActive);
	}

	/**
	 * Returns the number of cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the number of matching cache jsons
	 */
	public static int countByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive) {

		return getPersistence().countByModifiedDateAndIsActive(
			modifiedEvent, isActive);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cache jsons
	 */
	public static List<CacheJson> findAll(
		int start, int end, OrderByComparator<CacheJson> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
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

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
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