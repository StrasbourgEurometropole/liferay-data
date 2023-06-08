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

package eu.strasbourg.service.place.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.place.model.CsmapCacheJson;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the csmap cache json service. This utility wraps <code>eu.strasbourg.service.place.service.persistence.impl.CsmapCacheJsonPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
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
		throws eu.strasbourg.service.place.exception.
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
		throws eu.strasbourg.service.place.exception.
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
	 * @param sigId the primary key of the current csmap cache json
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson[] findByUuid_PrevAndNext(
			String sigId, String uuid,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByUuid_PrevAndNext(
			sigId, uuid, orderByComparator);
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
	 * Returns all the csmap cache jsons where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @return the matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findBysigId(String sigId) {
		return getPersistence().findBysigId(sigId);
	}

	/**
	 * Returns a range of all the csmap cache jsons where sigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param sigId the sig ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findBysigId(
		String sigId, int start, int end) {

		return getPersistence().findBysigId(sigId, start, end);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where sigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param sigId the sig ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findBysigId(
		String sigId, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().findBysigId(
			sigId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where sigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param sigId the sig ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findBysigId(
		String sigId, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBysigId(
			sigId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findBysigId_First(
			String sigId, OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findBysigId_First(sigId, orderByComparator);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchBysigId_First(
		String sigId, OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchBysigId_First(sigId, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findBysigId_Last(
			String sigId, OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findBysigId_Last(sigId, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchBysigId_Last(
		String sigId, OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchBysigId_Last(sigId, orderByComparator);
	}

	/**
	 * Removes all the csmap cache jsons where sigId = &#63; from the database.
	 *
	 * @param sigId the sig ID
	 */
	public static void removeBysigId(String sigId) {
		getPersistence().removeBysigId(sigId);
	}

	/**
	 * Returns the number of csmap cache jsons where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @return the number of matching csmap cache jsons
	 */
	public static int countBysigId(String sigId) {
		return getPersistence().countBysigId(sigId);
	}

	/**
	 * Returns all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive) {

		return getPersistence().findByCreatedDateAndIsActive(
			createPlace, isActive);
	}

	/**
	 * Returns a range of all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive, int start, int end) {

		return getPersistence().findByCreatedDateAndIsActive(
			createPlace, isActive, start, end);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().findByCreatedDateAndIsActive(
			createPlace, isActive, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCreatedDateAndIsActive(
			createPlace, isActive, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByCreatedDateAndIsActive_First(
			Date createPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByCreatedDateAndIsActive_First(
			createPlace, isActive, orderByComparator);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByCreatedDateAndIsActive_First(
		Date createPlace, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByCreatedDateAndIsActive_First(
			createPlace, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByCreatedDateAndIsActive_Last(
			Date createPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByCreatedDateAndIsActive_Last(
			createPlace, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByCreatedDateAndIsActive_Last(
		Date createPlace, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByCreatedDateAndIsActive_Last(
			createPlace, isActive, orderByComparator);
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param sigId the primary key of the current csmap cache json
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson[] findByCreatedDateAndIsActive_PrevAndNext(
			String sigId, Date createPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByCreatedDateAndIsActive_PrevAndNext(
			sigId, createPlace, isActive, orderByComparator);
	}

	/**
	 * Removes all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 */
	public static void removeByCreatedDateAndIsActive(
		Date createPlace, boolean isActive) {

		getPersistence().removeByCreatedDateAndIsActive(createPlace, isActive);
	}

	/**
	 * Returns the number of csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public static int countByCreatedDateAndIsActive(
		Date createPlace, boolean isActive) {

		return getPersistence().countByCreatedDateAndIsActive(
			createPlace, isActive);
	}

	/**
	 * Returns all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createPlace, Date modifiedPlace, boolean isActive) {

		return getPersistence().findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive);
	}

	/**
	 * Returns a range of all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createPlace, Date modifiedPlace, boolean isActive, int start,
			int end) {

		return getPersistence().findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive, start, end);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createPlace, Date modifiedPlace, boolean isActive, int start,
			int end, OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createPlace, Date modifiedPlace, boolean isActive, int start,
			int end, OrderByComparator<CsmapCacheJson> orderByComparator,
			boolean useFinderCache) {

		return getPersistence().findByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
			findByCreatedDateAndModifiedDateAndIsActive_First(
				Date createPlace, Date modifiedPlace, boolean isActive,
				OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActive_First(
				createPlace, modifiedPlace, isActive, orderByComparator);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
		fetchByCreatedDateAndModifiedDateAndIsActive_First(
			Date createPlace, Date modifiedPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().
			fetchByCreatedDateAndModifiedDateAndIsActive_First(
				createPlace, modifiedPlace, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
			findByCreatedDateAndModifiedDateAndIsActive_Last(
				Date createPlace, Date modifiedPlace, boolean isActive,
				OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActive_Last(
				createPlace, modifiedPlace, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson
		fetchByCreatedDateAndModifiedDateAndIsActive_Last(
			Date createPlace, Date modifiedPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().
			fetchByCreatedDateAndModifiedDateAndIsActive_Last(
				createPlace, modifiedPlace, isActive, orderByComparator);
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param sigId the primary key of the current csmap cache json
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson[]
			findByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				String sigId, Date createPlace, Date modifiedPlace,
				boolean isActive,
				OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().
			findByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				sigId, createPlace, modifiedPlace, isActive, orderByComparator);
	}

	/**
	 * Removes all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 */
	public static void removeByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive) {

		getPersistence().removeByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive);
	}

	/**
	 * Returns the number of csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public static int countByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive) {

		return getPersistence().countByCreatedDateAndModifiedDateAndIsActive(
			createPlace, modifiedPlace, isActive);
	}

	/**
	 * Returns all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive) {

		return getPersistence().findByModifiedDateAndIsActive(
			modifiedPlace, isActive);
	}

	/**
	 * Returns a range of all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive, int start, int end) {

		return getPersistence().findByModifiedDateAndIsActive(
			modifiedPlace, isActive, start, end);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().findByModifiedDateAndIsActive(
			modifiedPlace, isActive, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public static List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive, int start, int end,
		OrderByComparator<CsmapCacheJson> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByModifiedDateAndIsActive(
			modifiedPlace, isActive, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByModifiedDateAndIsActive_First(
			Date modifiedPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByModifiedDateAndIsActive_First(
			modifiedPlace, isActive, orderByComparator);
	}

	/**
	 * Returns the first csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByModifiedDateAndIsActive_First(
		Date modifiedPlace, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByModifiedDateAndIsActive_First(
			modifiedPlace, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson findByModifiedDateAndIsActive_Last(
			Date modifiedPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByModifiedDateAndIsActive_Last(
			modifiedPlace, isActive, orderByComparator);
	}

	/**
	 * Returns the last csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public static CsmapCacheJson fetchByModifiedDateAndIsActive_Last(
		Date modifiedPlace, boolean isActive,
		OrderByComparator<CsmapCacheJson> orderByComparator) {

		return getPersistence().fetchByModifiedDateAndIsActive_Last(
			modifiedPlace, isActive, orderByComparator);
	}

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param sigId the primary key of the current csmap cache json
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson[] findByModifiedDateAndIsActive_PrevAndNext(
			String sigId, Date modifiedPlace, boolean isActive,
			OrderByComparator<CsmapCacheJson> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByModifiedDateAndIsActive_PrevAndNext(
			sigId, modifiedPlace, isActive, orderByComparator);
	}

	/**
	 * Removes all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 */
	public static void removeByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive) {

		getPersistence().removeByModifiedDateAndIsActive(
			modifiedPlace, isActive);
	}

	/**
	 * Returns the number of csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public static int countByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive) {

		return getPersistence().countByModifiedDateAndIsActive(
			modifiedPlace, isActive);
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
	 * @param sigId the primary key for the new csmap cache json
	 * @return the new csmap cache json
	 */
	public static CsmapCacheJson create(String sigId) {
		return getPersistence().create(sigId);
	}

	/**
	 * Removes the csmap cache json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sigId the primary key of the csmap cache json
	 * @return the csmap cache json that was removed
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson remove(String sigId)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().remove(sigId);
	}

	public static CsmapCacheJson updateImpl(CsmapCacheJson csmapCacheJson) {
		return getPersistence().updateImpl(csmapCacheJson);
	}

	/**
	 * Returns the csmap cache json with the primary key or throws a <code>NoSuchCsmapCacheJsonException</code> if it could not be found.
	 *
	 * @param sigId the primary key of the csmap cache json
	 * @return the csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson findByPrimaryKey(String sigId)
		throws eu.strasbourg.service.place.exception.
			NoSuchCsmapCacheJsonException {

		return getPersistence().findByPrimaryKey(sigId);
	}

	/**
	 * Returns the csmap cache json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sigId the primary key of the csmap cache json
	 * @return the csmap cache json, or <code>null</code> if a csmap cache json with the primary key could not be found
	 */
	public static CsmapCacheJson fetchByPrimaryKey(String sigId) {
		return getPersistence().fetchByPrimaryKey(sigId);
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