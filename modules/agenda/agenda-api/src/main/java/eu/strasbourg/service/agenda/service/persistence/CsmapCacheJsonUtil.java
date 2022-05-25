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

import eu.strasbourg.service.agenda.model.CsmapCacheJson;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the csmap cache json service. This utility wraps <code>eu.strasbourg.service.agenda.service.persistence.impl.CsmapCacheJsonPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see CsmapCacheJsonPersistence
 * @generated
 */
public class CsmapCacheJsonUtil {

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
	public static void clearCache(CsmapCacheJson csmapCacheJson) {
		getPersistence().clearCache(csmapCacheJson);
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
	public static Map<Serializable, CsmapCacheJson> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CsmapCacheJson> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CsmapCacheJson> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CsmapCacheJson> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CsmapCacheJson update(CsmapCacheJson csmapCacheJson) {
		return getPersistence().update(csmapCacheJson);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CsmapCacheJson update(
		CsmapCacheJson csmapCacheJson, ServiceContext serviceContext) {

		return getPersistence().update(csmapCacheJson, serviceContext);
	}

	/**
	 * Returns all the csmap cache jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the csmap cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByUuid_First(
			String uuid, OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByUuid_First(
		String uuid, OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByUuid_Last(
			String uuid, OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByUuid_Last(
		String uuid, OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson[] findByUuid_PrevAndNext(
			long eventId, String uuid,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByUuid_PrevAndNext(
			eventId, uuid, orderByComparator);
	}

	/**
	 * Removes all the csmap cache jsons where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of csmap cache jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching csmap cache jsons
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the csmap cache jsons where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByeventId(long eventId) {
		return getPersistence().findByeventId(eventId);
	}

	/**
	 * Returns a range of all the csmap cache jsons where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByeventId(
		long eventId, int start, int end) {

		return getPersistence().findByeventId(eventId, start, end);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().findByeventId(
			eventId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByeventId(
			eventId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByeventId_First(
			long eventId, OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByeventId_First(eventId, orderByComparator);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByeventId_First(
		long eventId, OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByeventId_First(
			eventId, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByeventId_Last(
			long eventId, OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByeventId_Last(eventId, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByeventId_Last(
		long eventId, OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByeventId_Last(eventId, orderByComparator);
	}

	/**
	 * Removes all the csmap cache jsons where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 */
	public static void removeByeventId(long eventId) {
		getPersistence().removeByeventId(eventId);
	}

	/**
	 * Returns the number of csmap cache jsons where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching csmap cache jsons
	 */
	public static int countByeventId(long eventId) {
		return getPersistence().countByeventId(eventId);
	}

	/**
	 * Returns all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive) {

		return getPersistence().findByCreatedDateAndIsActive(
			createEvent, isActive);
	}

	/**
	 * Returns a range of all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive, int start, int end) {

		return getPersistence().findByCreatedDateAndIsActive(
			createEvent, isActive, start, end);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().findByCreatedDateAndIsActive(
			createEvent, isActive, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCreatedDateAndIsActive(
			createEvent, isActive, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByCreatedDateAndIsActive_First(
			Date createEvent, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByCreatedDateAndIsActive_First(
			createEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByCreatedDateAndIsActive_First(
		Date createEvent, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByCreatedDateAndIsActive_First(
			createEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByCreatedDateAndIsActive_Last(
			Date createEvent, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByCreatedDateAndIsActive_Last(
			createEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByCreatedDateAndIsActive_Last(
		Date createEvent, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByCreatedDateAndIsActive_Last(
			createEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson[] findByCreatedDateAndIsActive_PrevAndNext(
			long eventId, Date createEvent, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByCreatedDateAndIsActive_PrevAndNext(
			eventId, createEvent, isActive, orderByComparator);
	}

	/**
	 * Removes all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 */
	public static void removeByCreatedDateAndIsActive(
		Date createEvent, boolean isActive) {

		getPersistence().removeByCreatedDateAndIsActive(createEvent, isActive);
	}

	/**
	 * Returns the number of csmap cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public static int countByCreatedDateAndIsActive(
		Date createEvent, boolean isActive) {

		return getPersistence().countByCreatedDateAndIsActive(
			createEvent, isActive);
	}

	/**
	 * Returns all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createEvent, Date modifiedEvent, boolean isActive) {

		return getPersistence().findByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive);
	}

	/**
	 * Returns a range of all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createEvent, Date modifiedEvent, boolean isActive, int start,
			int end) {

		return getPersistence().findByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive, start, end);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createEvent, Date modifiedEvent, boolean isActive, int start,
			int end, OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().findByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createEvent, Date modifiedEvent, boolean isActive, int start,
			int end, OrderByComparator<CsmapCacheJson> orderByComparator,
			boolean useFinderCache) {

		return getPersistence().findByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
			findByCreatedDateAndModifiedDateAndIsActive_First(
				Date createEvent, Date modifiedEvent, boolean isActive,
				OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActive_First(
				createEvent, modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
		fetchByCreatedDateAndModifiedDateAndIsActive_First(
			Date createEvent, Date modifiedEvent, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().
			fetchByCreatedDateAndModifiedDateAndIsActive_First(
				createEvent, modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
			findByCreatedDateAndModifiedDateAndIsActive_Last(
				Date createEvent, Date modifiedEvent, boolean isActive,
				OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActive_Last(
				createEvent, modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
		fetchByCreatedDateAndModifiedDateAndIsActive_Last(
			Date createEvent, Date modifiedEvent, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().
			fetchByCreatedDateAndModifiedDateAndIsActive_Last(
				createEvent, modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson[]
			findByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				long eventId, Date createEvent, Date modifiedEvent,
				boolean isActive,
				OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				eventId, createEvent, modifiedEvent, isActive,
				orderByComparator);
	}

	/**
	 * Removes all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; from the database.
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
	 * Returns the number of csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public static int countByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive) {

		return getPersistence().countByCreatedDateAndModifiedDateAndIsActive(
			createEvent, modifiedEvent, isActive);
	}

	/**
	 * Returns all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @return the matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndIsActiveAndWithSchedules(
			Date createEvent, boolean isActive, boolean hasSchedules) {

		return getPersistence().findByCreatedDateAndIsActiveAndWithSchedules(
			createEvent, isActive, hasSchedules);
	}

	/**
	 * Returns a range of all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndIsActiveAndWithSchedules(
			Date createEvent, boolean isActive, boolean hasSchedules, int start,
			int end) {

		return getPersistence().findByCreatedDateAndIsActiveAndWithSchedules(
			createEvent, isActive, hasSchedules, start, end);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndIsActiveAndWithSchedules(
			Date createEvent, boolean isActive, boolean hasSchedules, int start,
			int end, OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().findByCreatedDateAndIsActiveAndWithSchedules(
			createEvent, isActive, hasSchedules, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndIsActiveAndWithSchedules(
			Date createEvent, boolean isActive, boolean hasSchedules, int start,
			int end, OrderByComparator<CsmapCacheJson> orderByComparator,
			boolean useFinderCache) {

		return getPersistence().findByCreatedDateAndIsActiveAndWithSchedules(
			createEvent, isActive, hasSchedules, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
			findByCreatedDateAndIsActiveAndWithSchedules_First(
				Date createEvent, boolean isActive, boolean hasSchedules,
				OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().
			findByCreatedDateAndIsActiveAndWithSchedules_First(
				createEvent, isActive, hasSchedules, orderByComparator);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
		fetchByCreatedDateAndIsActiveAndWithSchedules_First(
			Date createEvent, boolean isActive, boolean hasSchedules,
			OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().
			fetchByCreatedDateAndIsActiveAndWithSchedules_First(
				createEvent, isActive, hasSchedules, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
			findByCreatedDateAndIsActiveAndWithSchedules_Last(
				Date createEvent, boolean isActive, boolean hasSchedules,
				OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().
			findByCreatedDateAndIsActiveAndWithSchedules_Last(
				createEvent, isActive, hasSchedules, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
		fetchByCreatedDateAndIsActiveAndWithSchedules_Last(
			Date createEvent, boolean isActive, boolean hasSchedules,
			OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().
			fetchByCreatedDateAndIsActiveAndWithSchedules_Last(
				createEvent, isActive, hasSchedules, orderByComparator);
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson[]
			findByCreatedDateAndIsActiveAndWithSchedules_PrevAndNext(
				long eventId, Date createEvent, boolean isActive,
				boolean hasSchedules,
				OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().
			findByCreatedDateAndIsActiveAndWithSchedules_PrevAndNext(
				eventId, createEvent, isActive, hasSchedules,
				orderByComparator);
	}

	/**
	 * Removes all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63; from the database.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 */
	public static void removeByCreatedDateAndIsActiveAndWithSchedules(
		Date createEvent, boolean isActive, boolean hasSchedules) {

		getPersistence().removeByCreatedDateAndIsActiveAndWithSchedules(
			createEvent, isActive, hasSchedules);
	}

	/**
	 * Returns the number of csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @return the number of matching csmap cache jsons
	 */
	public static int countByCreatedDateAndIsActiveAndWithSchedules(
		Date createEvent, boolean isActive, boolean hasSchedules) {

		return getPersistence().countByCreatedDateAndIsActiveAndWithSchedules(
			createEvent, isActive, hasSchedules);
	}

	/**
	 * Returns all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @return the matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules) {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
				createEvent, modifiedEvent, isActive, hasSchedules);
	}

	/**
	 * Returns a range of all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules, int start, int end) {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
				createEvent, modifiedEvent, isActive, hasSchedules, start, end);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules, int start, int end,
			OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
				createEvent, modifiedEvent, isActive, hasSchedules, start, end,
				orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules, int start, int end,
			OrderByComparator<CsmapCacheJson> orderByComparator,
			boolean useFinderCache) {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
				createEvent, modifiedEvent, isActive, hasSchedules, start, end,
				orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
			findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_First(
				Date createEvent, Date modifiedEvent, boolean isActive,
				boolean hasSchedules,
				OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_First(
				createEvent, modifiedEvent, isActive, hasSchedules,
				orderByComparator);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
		fetchByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_First(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules,
			OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().
			fetchByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_First(
				createEvent, modifiedEvent, isActive, hasSchedules,
				orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
			findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_Last(
				Date createEvent, Date modifiedEvent, boolean isActive,
				boolean hasSchedules,
				OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_Last(
				createEvent, modifiedEvent, isActive, hasSchedules,
				orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
		fetchByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_Last(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules,
			OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().
			fetchByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_Last(
				createEvent, modifiedEvent, isActive, hasSchedules,
				orderByComparator);
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson[]
			findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_PrevAndNext(
				long eventId, Date createEvent, Date modifiedEvent,
				boolean isActive, boolean hasSchedules,
				OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_PrevAndNext(
				eventId, createEvent, modifiedEvent, isActive, hasSchedules,
				orderByComparator);
	}

	/**
	 * Removes all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63; from the database.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 */
	public static void
		removeByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules) {

		getPersistence().
			removeByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
				createEvent, modifiedEvent, isActive, hasSchedules);
	}

	/**
	 * Returns the number of csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @return the number of matching csmap cache jsons
	 */
	public static int
		countByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules) {

		return getPersistence().
			countByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
				createEvent, modifiedEvent, isActive, hasSchedules);
	}

	/**
	 * Returns all the csmap cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive) {

		return getPersistence().findByModifiedDateAndIsActive(
			modifiedEvent, isActive);
	}

	/**
	 * Returns a range of all the csmap cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive, int start, int end) {

		return getPersistence().findByModifiedDateAndIsActive(
			modifiedEvent, isActive, start, end);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().findByModifiedDateAndIsActive(
			modifiedEvent, isActive, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByModifiedDateAndIsActive(
			modifiedEvent, isActive, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByModifiedDateAndIsActive_First(
			Date modifiedEvent, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByModifiedDateAndIsActive_First(
			modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByModifiedDateAndIsActive_First(
		Date modifiedEvent, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByModifiedDateAndIsActive_First(
			modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByModifiedDateAndIsActive_Last(
			Date modifiedEvent, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByModifiedDateAndIsActive_Last(
			modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByModifiedDateAndIsActive_Last(
		Date modifiedEvent, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByModifiedDateAndIsActive_Last(
			modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson[] findByModifiedDateAndIsActive_PrevAndNext(
			long eventId, Date modifiedEvent, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByModifiedDateAndIsActive_PrevAndNext(
			eventId, modifiedEvent, isActive, orderByComparator);
	}

	/**
	 * Removes all the csmap cache jsons where modifiedEvent &ge; &#63; and isActive = &#63; from the database.
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
	 * Returns the number of csmap cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public static int countByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive) {

		return getPersistence().countByModifiedDateAndIsActive(
			modifiedEvent, isActive);
	}

	/**
	 * Returns all the csmap cache jsons where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByRegeneratedDateAndIsActive(
		Date regeneratedDate, boolean isActive) {

		return getPersistence().findByRegeneratedDateAndIsActive(
			regeneratedDate, isActive);
	}

	/**
	 * Returns a range of all the csmap cache jsons where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByRegeneratedDateAndIsActive(
		Date regeneratedDate, boolean isActive, int start, int end) {

		return getPersistence().findByRegeneratedDateAndIsActive(
			regeneratedDate, isActive, start, end);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByRegeneratedDateAndIsActive(
		Date regeneratedDate, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().findByRegeneratedDateAndIsActive(
			regeneratedDate, isActive, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByRegeneratedDateAndIsActive(
		Date regeneratedDate, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByRegeneratedDateAndIsActive(
			regeneratedDate, isActive, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByRegeneratedDateAndIsActive_First(
			Date regeneratedDate, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByRegeneratedDateAndIsActive_First(
			regeneratedDate, isActive, orderByComparator);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByRegeneratedDateAndIsActive_First(
		Date regeneratedDate, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByRegeneratedDateAndIsActive_First(
			regeneratedDate, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByRegeneratedDateAndIsActive_Last(
			Date regeneratedDate, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByRegeneratedDateAndIsActive_Last(
			regeneratedDate, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByRegeneratedDateAndIsActive_Last(
		Date regeneratedDate, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByRegeneratedDateAndIsActive_Last(
			regeneratedDate, isActive, orderByComparator);
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson[] findByRegeneratedDateAndIsActive_PrevAndNext(
			long eventId, Date regeneratedDate, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByRegeneratedDateAndIsActive_PrevAndNext(
			eventId, regeneratedDate, isActive, orderByComparator);
	}

	/**
	 * Removes all the csmap cache jsons where regeneratedDate &le; &#63; and isActive = &#63; from the database.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 */
	public static void removeByRegeneratedDateAndIsActive(
		Date regeneratedDate, boolean isActive) {

		getPersistence().removeByRegeneratedDateAndIsActive(
			regeneratedDate, isActive);
	}

	/**
	 * Returns the number of csmap cache jsons where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public static int countByRegeneratedDateAndIsActive(
		Date regeneratedDate, boolean isActive) {

		return getPersistence().countByRegeneratedDateAndIsActive(
			regeneratedDate, isActive);
	}

	/**
	 * Caches the csmap cache json in the entity cache if it is enabled.
	 *
	 * @param csmapCacheJson the csmap cache json
	 */
	public static void cacheResult(CsmapCacheJson csmapCacheJson) {
		getPersistence().cacheResult(csmapCacheJson);
	}

	/**
	 * Caches the csmap cache jsons in the entity cache if it is enabled.
	 *
	 * @param csmapCacheJsons the csmap cache jsons
	 */
	public static void cacheResult(List<CsmapCacheJson> csmapCacheJsons) {
		getPersistence().cacheResult(csmapCacheJsons);
	}

	/**
	 * Creates a new csmap cache json with the primary key. Does not add the csmap cache json to the database.
	 *
	 * @param eventId the primary key for the new csmap cache json
	 * @return the new csmap cache json
	 */
	public static CsmapCacheJson create(long eventId) {
		return getPersistence().create(eventId);
	}

	/**
	 * Removes the csmap cache json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventId the primary key of the csmap cache json
	 * @return the csmap cache json that was removed
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson remove(long eventId)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().remove(eventId);
	}

	public static CsmapCacheJson updateImpl(CsmapCacheJson csmapCacheJson) {
		return getPersistence().updateImpl(csmapCacheJson);
	}

	/**
	 * Returns the csmap cache json with the primary key or throws a <code>NoSuchCsmapCacheJsonException</code> if it could not be found.
	 *
	 * @param eventId the primary key of the csmap cache json
	 * @return the csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson findByPrimaryKey(long eventId)
		throws eu.strasbourg.service.agenda.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByPrimaryKey(eventId);
	}

	/**
	 * Returns the csmap cache json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventId the primary key of the csmap cache json
	 * @return the csmap cache json, or <code>null</code> if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson fetchByPrimaryKey(long eventId) {
		return getPersistence().fetchByPrimaryKey(eventId);
	}

	/**
	 * Returns all the csmap cache jsons.
	 *
	 * @return the csmap cache jsons
	 */
	public static List<CsmapCacheJson> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of csmap cache jsons
	 */
	public static List<CsmapCacheJson> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of csmap cache jsons
	 */
	public static List<CsmapCacheJson> findAll(
		int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of csmap cache jsons
	 */
	public static List<CsmapCacheJson> findAll(
		int start, int end, OrderByComparator<CsmapCacheJson> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the csmap cache jsons from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of csmap cache jsons.
	 *
	 * @return the number of csmap cache jsons
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CsmapCacheJsonPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CsmapCacheJsonPersistence, CsmapCacheJsonPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CsmapCacheJsonPersistence.class);

		ServiceTracker<CsmapCacheJsonPersistence, CsmapCacheJsonPersistence>
			serviceTracker =
				new ServiceTracker
					<CsmapCacheJsonPersistence, CsmapCacheJsonPersistence>(
						bundle.getBundleContext(),
						CsmapCacheJsonPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}