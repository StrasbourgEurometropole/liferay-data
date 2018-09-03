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

package eu.strasbourg.service.project.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.project.model.PlacitPlace;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the placit place service. This utility wraps {@link eu.strasbourg.service.project.service.persistence.impl.PlacitPlacePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see PlacitPlacePersistence
 * @see eu.strasbourg.service.project.service.persistence.impl.PlacitPlacePersistenceImpl
 * @generated
 */
@ProviderType
public class PlacitPlaceUtil {
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
	public static void clearCache(PlacitPlace placitPlace) {
		getPersistence().clearCache(placitPlace);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<PlacitPlace> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PlacitPlace> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PlacitPlace> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PlacitPlace update(PlacitPlace placitPlace) {
		return getPersistence().update(placitPlace);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PlacitPlace update(PlacitPlace placitPlace,
		ServiceContext serviceContext) {
		return getPersistence().update(placitPlace, serviceContext);
	}

	/**
	* Returns all the placit places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching placit places
	*/
	public static List<PlacitPlace> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the placit places where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @return the range of matching placit places
	*/
	public static List<PlacitPlace> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the placit places where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the placit places where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first placit place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findByUuid_First(java.lang.String uuid,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first placit place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findByUuid_Last(java.lang.String uuid,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the placit places before and after the current placit place in the ordered set where uuid = &#63;.
	*
	* @param placitPlaceId the primary key of the current placit place
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public static PlacitPlace[] findByUuid_PrevAndNext(long placitPlaceId,
		java.lang.String uuid, OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence()
				   .findByUuid_PrevAndNext(placitPlaceId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the placit places where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of placit places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching placit places
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the placit place where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPlacitPlaceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the placit place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the placit place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the placit place where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the placit place that was removed
	*/
	public static PlacitPlace removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of placit places where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching placit places
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the placit places where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching placit places
	*/
	public static List<PlacitPlace> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the placit places where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @return the range of matching placit places
	*/
	public static List<PlacitPlace> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the placit places where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the placit places where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the placit places before and after the current placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param placitPlaceId the primary key of the current placit place
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public static PlacitPlace[] findByUuid_C_PrevAndNext(long placitPlaceId,
		java.lang.String uuid, long companyId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(placitPlaceId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the placit places where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of placit places where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching placit places
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the placit places where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching placit places
	*/
	public static List<PlacitPlace> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the placit places where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @return the range of matching placit places
	*/
	public static List<PlacitPlace> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the placit places where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findByGroupId(long groupId, int start,
		int end, OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the placit places where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findByGroupId(long groupId, int start,
		int end, OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first placit place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findByGroupId_First(long groupId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first placit place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByGroupId_First(long groupId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findByGroupId_Last(long groupId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByGroupId_Last(long groupId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the placit places before and after the current placit place in the ordered set where groupId = &#63;.
	*
	* @param placitPlaceId the primary key of the current placit place
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public static PlacitPlace[] findByGroupId_PrevAndNext(long placitPlaceId,
		long groupId, OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(placitPlaceId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the placit places where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of placit places where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching placit places
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the placit places where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching placit places
	*/
	public static List<PlacitPlace> findByProject(long projectId) {
		return getPersistence().findByProject(projectId);
	}

	/**
	* Returns a range of all the placit places where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @return the range of matching placit places
	*/
	public static List<PlacitPlace> findByProject(long projectId, int start,
		int end) {
		return getPersistence().findByProject(projectId, start, end);
	}

	/**
	* Returns an ordered range of all the placit places where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findByProject(long projectId, int start,
		int end, OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .findByProject(projectId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the placit places where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findByProject(long projectId, int start,
		int end, OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByProject(projectId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first placit place in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findByProject_First(long projectId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence().findByProject_First(projectId, orderByComparator);
	}

	/**
	* Returns the first placit place in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByProject_First(long projectId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .fetchByProject_First(projectId, orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findByProject_Last(long projectId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence().findByProject_Last(projectId, orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByProject_Last(long projectId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence().fetchByProject_Last(projectId, orderByComparator);
	}

	/**
	* Returns the placit places before and after the current placit place in the ordered set where projectId = &#63;.
	*
	* @param placitPlaceId the primary key of the current placit place
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public static PlacitPlace[] findByProject_PrevAndNext(long placitPlaceId,
		long projectId, OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence()
				   .findByProject_PrevAndNext(placitPlaceId, projectId,
			orderByComparator);
	}

	/**
	* Removes all the placit places where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	*/
	public static void removeByProject(long projectId) {
		getPersistence().removeByProject(projectId);
	}

	/**
	* Returns the number of placit places where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching placit places
	*/
	public static int countByProject(long projectId) {
		return getPersistence().countByProject(projectId);
	}

	/**
	* Returns all the placit places where participationId = &#63;.
	*
	* @param participationId the participation ID
	* @return the matching placit places
	*/
	public static List<PlacitPlace> findByParticipation(long participationId) {
		return getPersistence().findByParticipation(participationId);
	}

	/**
	* Returns a range of all the placit places where participationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param participationId the participation ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @return the range of matching placit places
	*/
	public static List<PlacitPlace> findByParticipation(long participationId,
		int start, int end) {
		return getPersistence().findByParticipation(participationId, start, end);
	}

	/**
	* Returns an ordered range of all the placit places where participationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param participationId the participation ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findByParticipation(long participationId,
		int start, int end, OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .findByParticipation(participationId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the placit places where participationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param participationId the participation ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findByParticipation(long participationId,
		int start, int end, OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByParticipation(participationId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first placit place in the ordered set where participationId = &#63;.
	*
	* @param participationId the participation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findByParticipation_First(long participationId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence()
				   .findByParticipation_First(participationId, orderByComparator);
	}

	/**
	* Returns the first placit place in the ordered set where participationId = &#63;.
	*
	* @param participationId the participation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByParticipation_First(long participationId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .fetchByParticipation_First(participationId,
			orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where participationId = &#63;.
	*
	* @param participationId the participation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findByParticipation_Last(long participationId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence()
				   .findByParticipation_Last(participationId, orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where participationId = &#63;.
	*
	* @param participationId the participation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByParticipation_Last(long participationId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .fetchByParticipation_Last(participationId, orderByComparator);
	}

	/**
	* Returns the placit places before and after the current placit place in the ordered set where participationId = &#63;.
	*
	* @param placitPlaceId the primary key of the current placit place
	* @param participationId the participation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public static PlacitPlace[] findByParticipation_PrevAndNext(
		long placitPlaceId, long participationId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence()
				   .findByParticipation_PrevAndNext(placitPlaceId,
			participationId, orderByComparator);
	}

	/**
	* Removes all the placit places where participationId = &#63; from the database.
	*
	* @param participationId the participation ID
	*/
	public static void removeByParticipation(long participationId) {
		getPersistence().removeByParticipation(participationId);
	}

	/**
	* Returns the number of placit places where participationId = &#63;.
	*
	* @param participationId the participation ID
	* @return the number of matching placit places
	*/
	public static int countByParticipation(long participationId) {
		return getPersistence().countByParticipation(participationId);
	}

	/**
	* Returns all the placit places where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @return the matching placit places
	*/
	public static List<PlacitPlace> findByPetition(long petitionId) {
		return getPersistence().findByPetition(petitionId);
	}

	/**
	* Returns a range of all the placit places where petitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @return the range of matching placit places
	*/
	public static List<PlacitPlace> findByPetition(long petitionId, int start,
		int end) {
		return getPersistence().findByPetition(petitionId, start, end);
	}

	/**
	* Returns an ordered range of all the placit places where petitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findByPetition(long petitionId, int start,
		int end, OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .findByPetition(petitionId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the placit places where petitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findByPetition(long petitionId, int start,
		int end, OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPetition(petitionId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first placit place in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findByPetition_First(long petitionId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence()
				   .findByPetition_First(petitionId, orderByComparator);
	}

	/**
	* Returns the first placit place in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByPetition_First(long petitionId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .fetchByPetition_First(petitionId, orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findByPetition_Last(long petitionId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence()
				   .findByPetition_Last(petitionId, orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchByPetition_Last(long petitionId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .fetchByPetition_Last(petitionId, orderByComparator);
	}

	/**
	* Returns the placit places before and after the current placit place in the ordered set where petitionId = &#63;.
	*
	* @param placitPlaceId the primary key of the current placit place
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public static PlacitPlace[] findByPetition_PrevAndNext(long placitPlaceId,
		long petitionId, OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence()
				   .findByPetition_PrevAndNext(placitPlaceId, petitionId,
			orderByComparator);
	}

	/**
	* Removes all the placit places where petitionId = &#63; from the database.
	*
	* @param petitionId the petition ID
	*/
	public static void removeByPetition(long petitionId) {
		getPersistence().removeByPetition(petitionId);
	}

	/**
	* Returns the number of placit places where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @return the number of matching placit places
	*/
	public static int countByPetition(long petitionId) {
		return getPersistence().countByPetition(petitionId);
	}

	/**
	* Returns all the placit places where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @return the matching placit places
	*/
	public static List<PlacitPlace> findBySigId(java.lang.String placeSIGId) {
		return getPersistence().findBySigId(placeSIGId);
	}

	/**
	* Returns a range of all the placit places where placeSIGId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeSIGId the place sig ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @return the range of matching placit places
	*/
	public static List<PlacitPlace> findBySigId(java.lang.String placeSIGId,
		int start, int end) {
		return getPersistence().findBySigId(placeSIGId, start, end);
	}

	/**
	* Returns an ordered range of all the placit places where placeSIGId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeSIGId the place sig ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findBySigId(java.lang.String placeSIGId,
		int start, int end, OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence()
				   .findBySigId(placeSIGId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the placit places where placeSIGId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeSIGId the place sig ID
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching placit places
	*/
	public static List<PlacitPlace> findBySigId(java.lang.String placeSIGId,
		int start, int end, OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySigId(placeSIGId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first placit place in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findBySigId_First(java.lang.String placeSIGId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence().findBySigId_First(placeSIGId, orderByComparator);
	}

	/**
	* Returns the first placit place in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchBySigId_First(java.lang.String placeSIGId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence().fetchBySigId_First(placeSIGId, orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public static PlacitPlace findBySigId_Last(java.lang.String placeSIGId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence().findBySigId_Last(placeSIGId, orderByComparator);
	}

	/**
	* Returns the last placit place in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public static PlacitPlace fetchBySigId_Last(java.lang.String placeSIGId,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence().fetchBySigId_Last(placeSIGId, orderByComparator);
	}

	/**
	* Returns the placit places before and after the current placit place in the ordered set where placeSIGId = &#63;.
	*
	* @param placitPlaceId the primary key of the current placit place
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public static PlacitPlace[] findBySigId_PrevAndNext(long placitPlaceId,
		java.lang.String placeSIGId,
		OrderByComparator<PlacitPlace> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence()
				   .findBySigId_PrevAndNext(placitPlaceId, placeSIGId,
			orderByComparator);
	}

	/**
	* Removes all the placit places where placeSIGId = &#63; from the database.
	*
	* @param placeSIGId the place sig ID
	*/
	public static void removeBySigId(java.lang.String placeSIGId) {
		getPersistence().removeBySigId(placeSIGId);
	}

	/**
	* Returns the number of placit places where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @return the number of matching placit places
	*/
	public static int countBySigId(java.lang.String placeSIGId) {
		return getPersistence().countBySigId(placeSIGId);
	}

	/**
	* Caches the placit place in the entity cache if it is enabled.
	*
	* @param placitPlace the placit place
	*/
	public static void cacheResult(PlacitPlace placitPlace) {
		getPersistence().cacheResult(placitPlace);
	}

	/**
	* Caches the placit places in the entity cache if it is enabled.
	*
	* @param placitPlaces the placit places
	*/
	public static void cacheResult(List<PlacitPlace> placitPlaces) {
		getPersistence().cacheResult(placitPlaces);
	}

	/**
	* Creates a new placit place with the primary key. Does not add the placit place to the database.
	*
	* @param placitPlaceId the primary key for the new placit place
	* @return the new placit place
	*/
	public static PlacitPlace create(long placitPlaceId) {
		return getPersistence().create(placitPlaceId);
	}

	/**
	* Removes the placit place with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param placitPlaceId the primary key of the placit place
	* @return the placit place that was removed
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public static PlacitPlace remove(long placitPlaceId)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence().remove(placitPlaceId);
	}

	public static PlacitPlace updateImpl(PlacitPlace placitPlace) {
		return getPersistence().updateImpl(placitPlace);
	}

	/**
	* Returns the placit place with the primary key or throws a {@link NoSuchPlacitPlaceException} if it could not be found.
	*
	* @param placitPlaceId the primary key of the placit place
	* @return the placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public static PlacitPlace findByPrimaryKey(long placitPlaceId)
		throws eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException {
		return getPersistence().findByPrimaryKey(placitPlaceId);
	}

	/**
	* Returns the placit place with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param placitPlaceId the primary key of the placit place
	* @return the placit place, or <code>null</code> if a placit place with the primary key could not be found
	*/
	public static PlacitPlace fetchByPrimaryKey(long placitPlaceId) {
		return getPersistence().fetchByPrimaryKey(placitPlaceId);
	}

	public static java.util.Map<java.io.Serializable, PlacitPlace> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the placit places.
	*
	* @return the placit places
	*/
	public static List<PlacitPlace> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the placit places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @return the range of placit places
	*/
	public static List<PlacitPlace> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the placit places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of placit places
	*/
	public static List<PlacitPlace> findAll(int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the placit places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of placit places
	*/
	public static List<PlacitPlace> findAll(int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the placit places from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of placit places.
	*
	* @return the number of placit places
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PlacitPlacePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PlacitPlacePersistence, PlacitPlacePersistence> _serviceTracker =
		ServiceTrackerFactory.open(PlacitPlacePersistence.class);
}