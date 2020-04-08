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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the google my business historic service. This utility wraps <code>eu.strasbourg.service.place.service.persistence.impl.GoogleMyBusinessHistoricPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see GoogleMyBusinessHistoricPersistence
 * @generated
 */
@ProviderType
public class GoogleMyBusinessHistoricUtil {

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
	public static void clearCache(
		GoogleMyBusinessHistoric googleMyBusinessHistoric) {

		getPersistence().clearCache(googleMyBusinessHistoric);
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
	public static Map<Serializable, GoogleMyBusinessHistoric>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<GoogleMyBusinessHistoric> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<GoogleMyBusinessHistoric> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<GoogleMyBusinessHistoric> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static GoogleMyBusinessHistoric update(
		GoogleMyBusinessHistoric googleMyBusinessHistoric) {

		return getPersistence().update(googleMyBusinessHistoric);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static GoogleMyBusinessHistoric update(
		GoogleMyBusinessHistoric googleMyBusinessHistoric,
		ServiceContext serviceContext) {

		return getPersistence().update(
			googleMyBusinessHistoric, serviceContext);
	}

	/**
	 * Returns all the google my business historics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the google my business historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @return the range of matching google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the google my business historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the google my business historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first google my business historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric findByUuid_First(
			String uuid,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchGoogleMyBusinessHistoricException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first google my business historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric fetchByUuid_First(
		String uuid,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last google my business historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric findByUuid_Last(
			String uuid,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchGoogleMyBusinessHistoricException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last google my business historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric fetchByUuid_Last(
		String uuid,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the google my business historics before and after the current google my business historic in the ordered set where uuid = &#63;.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the current google my business historic
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a google my business historic with the primary key could not be found
	 */
	public static GoogleMyBusinessHistoric[] findByUuid_PrevAndNext(
			long googleMyBusinessHistoricId, String uuid,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchGoogleMyBusinessHistoricException {

		return getPersistence().findByUuid_PrevAndNext(
			googleMyBusinessHistoricId, uuid, orderByComparator);
	}

	/**
	 * Removes all the google my business historics where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of google my business historics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching google my business historics
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the google my business historic where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchGoogleMyBusinessHistoricException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric findByUUID_G(
			String uuid, long groupId)
		throws eu.strasbourg.service.place.exception.
			NoSuchGoogleMyBusinessHistoricException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the google my business historic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric fetchByUUID_G(
		String uuid, long groupId) {

		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the google my business historic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	 * Removes the google my business historic where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the google my business historic that was removed
	 */
	public static GoogleMyBusinessHistoric removeByUUID_G(
			String uuid, long groupId)
		throws eu.strasbourg.service.place.exception.
			NoSuchGoogleMyBusinessHistoricException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of google my business historics where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching google my business historics
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the google my business historics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the google my business historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @return the range of matching google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the google my business historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the google my business historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first google my business historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchGoogleMyBusinessHistoricException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first google my business historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last google my business historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchGoogleMyBusinessHistoricException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last google my business historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the google my business historics before and after the current google my business historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the current google my business historic
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a google my business historic with the primary key could not be found
	 */
	public static GoogleMyBusinessHistoric[] findByUuid_C_PrevAndNext(
			long googleMyBusinessHistoricId, String uuid, long companyId,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchGoogleMyBusinessHistoricException {

		return getPersistence().findByUuid_C_PrevAndNext(
			googleMyBusinessHistoricId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the google my business historics where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of google my business historics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching google my business historics
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the google my business historics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the google my business historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @return the range of matching google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the google my business historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the google my business historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first google my business historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric findByGroupId_First(
			long groupId,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchGoogleMyBusinessHistoricException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first google my business historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric fetchByGroupId_First(
		long groupId,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last google my business historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric findByGroupId_Last(
			long groupId,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchGoogleMyBusinessHistoricException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last google my business historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	 */
	public static GoogleMyBusinessHistoric fetchByGroupId_Last(
		long groupId,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the google my business historics before and after the current google my business historic in the ordered set where groupId = &#63;.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the current google my business historic
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a google my business historic with the primary key could not be found
	 */
	public static GoogleMyBusinessHistoric[] findByGroupId_PrevAndNext(
			long googleMyBusinessHistoricId, long groupId,
			OrderByComparator<GoogleMyBusinessHistoric> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchGoogleMyBusinessHistoricException {

		return getPersistence().findByGroupId_PrevAndNext(
			googleMyBusinessHistoricId, groupId, orderByComparator);
	}

	/**
	 * Removes all the google my business historics where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of google my business historics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching google my business historics
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Caches the google my business historic in the entity cache if it is enabled.
	 *
	 * @param googleMyBusinessHistoric the google my business historic
	 */
	public static void cacheResult(
		GoogleMyBusinessHistoric googleMyBusinessHistoric) {

		getPersistence().cacheResult(googleMyBusinessHistoric);
	}

	/**
	 * Caches the google my business historics in the entity cache if it is enabled.
	 *
	 * @param googleMyBusinessHistorics the google my business historics
	 */
	public static void cacheResult(
		List<GoogleMyBusinessHistoric> googleMyBusinessHistorics) {

		getPersistence().cacheResult(googleMyBusinessHistorics);
	}

	/**
	 * Creates a new google my business historic with the primary key. Does not add the google my business historic to the database.
	 *
	 * @param googleMyBusinessHistoricId the primary key for the new google my business historic
	 * @return the new google my business historic
	 */
	public static GoogleMyBusinessHistoric create(
		long googleMyBusinessHistoricId) {

		return getPersistence().create(googleMyBusinessHistoricId);
	}

	/**
	 * Removes the google my business historic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the google my business historic
	 * @return the google my business historic that was removed
	 * @throws NoSuchGoogleMyBusinessHistoricException if a google my business historic with the primary key could not be found
	 */
	public static GoogleMyBusinessHistoric remove(
			long googleMyBusinessHistoricId)
		throws eu.strasbourg.service.place.exception.
			NoSuchGoogleMyBusinessHistoricException {

		return getPersistence().remove(googleMyBusinessHistoricId);
	}

	public static GoogleMyBusinessHistoric updateImpl(
		GoogleMyBusinessHistoric googleMyBusinessHistoric) {

		return getPersistence().updateImpl(googleMyBusinessHistoric);
	}

	/**
	 * Returns the google my business historic with the primary key or throws a <code>NoSuchGoogleMyBusinessHistoricException</code> if it could not be found.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the google my business historic
	 * @return the google my business historic
	 * @throws NoSuchGoogleMyBusinessHistoricException if a google my business historic with the primary key could not be found
	 */
	public static GoogleMyBusinessHistoric findByPrimaryKey(
			long googleMyBusinessHistoricId)
		throws eu.strasbourg.service.place.exception.
			NoSuchGoogleMyBusinessHistoricException {

		return getPersistence().findByPrimaryKey(googleMyBusinessHistoricId);
	}

	/**
	 * Returns the google my business historic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param googleMyBusinessHistoricId the primary key of the google my business historic
	 * @return the google my business historic, or <code>null</code> if a google my business historic with the primary key could not be found
	 */
	public static GoogleMyBusinessHistoric fetchByPrimaryKey(
		long googleMyBusinessHistoricId) {

		return getPersistence().fetchByPrimaryKey(googleMyBusinessHistoricId);
	}

	/**
	 * Returns all the google my business historics.
	 *
	 * @return the google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the google my business historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @return the range of google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the google my business historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findAll(
		int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the google my business historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GoogleMyBusinessHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of google my business historics
	 * @param end the upper bound of the range of google my business historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of google my business historics
	 */
	public static List<GoogleMyBusinessHistoric> findAll(
		int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the google my business historics from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of google my business historics.
	 *
	 * @return the number of google my business historics
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static GoogleMyBusinessHistoricPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<GoogleMyBusinessHistoricPersistence,
		 GoogleMyBusinessHistoricPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			GoogleMyBusinessHistoricPersistence.class);

		ServiceTracker
			<GoogleMyBusinessHistoricPersistence,
			 GoogleMyBusinessHistoricPersistence> serviceTracker =
				new ServiceTracker
					<GoogleMyBusinessHistoricPersistence,
					 GoogleMyBusinessHistoricPersistence>(
						 bundle.getBundleContext(),
						 GoogleMyBusinessHistoricPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}