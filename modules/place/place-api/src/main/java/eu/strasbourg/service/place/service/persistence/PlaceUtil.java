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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.place.model.Place;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the place service. This utility wraps {@link eu.strasbourg.service.place.service.persistence.impl.PlacePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see PlacePersistence
 * @see eu.strasbourg.service.place.service.persistence.impl.PlacePersistenceImpl
 * @generated
 */
@ProviderType
public class PlaceUtil {
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
	public static void clearCache(Place place) {
		getPersistence().clearCache(place);
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
	public static List<Place> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Place> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Place> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Place> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Place update(Place place) {
		return getPersistence().update(place);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Place update(Place place, ServiceContext serviceContext) {
		return getPersistence().update(place, serviceContext);
	}

	/**
	* Returns all the places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching places
	*/
	public static List<Place> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the places where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @return the range of matching places
	*/
	public static List<Place> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the places where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching places
	*/
	public static List<Place> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Place> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the places where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching places
	*/
	public static List<Place> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Place> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public static Place findByUuid_First(java.lang.String uuid,
		OrderByComparator<Place> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place, or <code>null</code> if a matching place could not be found
	*/
	public static Place fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Place> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public static Place findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Place> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place, or <code>null</code> if a matching place could not be found
	*/
	public static Place fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Place> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the places before and after the current place in the ordered set where uuid = &#63;.
	*
	* @param placeId the primary key of the current place
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next place
	* @throws NoSuchPlaceException if a place with the primary key could not be found
	*/
	public static Place[] findByUuid_PrevAndNext(long placeId,
		java.lang.String uuid, OrderByComparator<Place> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence()
				   .findByUuid_PrevAndNext(placeId, uuid, orderByComparator);
	}

	/**
	* Removes all the places where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching places
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the place where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPlaceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public static Place findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching place, or <code>null</code> if a matching place could not be found
	*/
	public static Place fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching place, or <code>null</code> if a matching place could not be found
	*/
	public static Place fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the place where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the place that was removed
	*/
	public static Place removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of places where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching places
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the places where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching places
	*/
	public static List<Place> findByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the places where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @return the range of matching places
	*/
	public static List<Place> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the places where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching places
	*/
	public static List<Place> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Place> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the places where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching places
	*/
	public static List<Place> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Place> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public static Place findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Place> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place, or <code>null</code> if a matching place could not be found
	*/
	public static Place fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Place> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public static Place findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Place> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place, or <code>null</code> if a matching place could not be found
	*/
	public static Place fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Place> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the places before and after the current place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param placeId the primary key of the current place
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next place
	* @throws NoSuchPlaceException if a place with the primary key could not be found
	*/
	public static Place[] findByUuid_C_PrevAndNext(long placeId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Place> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(placeId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the places where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of places where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching places
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the places where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching places
	*/
	public static List<Place> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the places where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @return the range of matching places
	*/
	public static List<Place> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the places where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching places
	*/
	public static List<Place> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Place> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the places where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching places
	*/
	public static List<Place> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Place> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public static Place findByGroupId_First(long groupId,
		OrderByComparator<Place> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place, or <code>null</code> if a matching place could not be found
	*/
	public static Place fetchByGroupId_First(long groupId,
		OrderByComparator<Place> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public static Place findByGroupId_Last(long groupId,
		OrderByComparator<Place> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place, or <code>null</code> if a matching place could not be found
	*/
	public static Place fetchByGroupId_Last(long groupId,
		OrderByComparator<Place> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the places before and after the current place in the ordered set where groupId = &#63;.
	*
	* @param placeId the primary key of the current place
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next place
	* @throws NoSuchPlaceException if a place with the primary key could not be found
	*/
	public static Place[] findByGroupId_PrevAndNext(long placeId, long groupId,
		OrderByComparator<Place> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(placeId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the places where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of places where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching places
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the places where priceId = &#63;.
	*
	* @param priceId the price ID
	* @return the matching places
	*/
	public static List<Place> findByPriceId(long priceId) {
		return getPersistence().findByPriceId(priceId);
	}

	/**
	* Returns a range of all the places where priceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceId the price ID
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @return the range of matching places
	*/
	public static List<Place> findByPriceId(long priceId, int start, int end) {
		return getPersistence().findByPriceId(priceId, start, end);
	}

	/**
	* Returns an ordered range of all the places where priceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceId the price ID
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching places
	*/
	public static List<Place> findByPriceId(long priceId, int start, int end,
		OrderByComparator<Place> orderByComparator) {
		return getPersistence()
				   .findByPriceId(priceId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the places where priceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceId the price ID
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching places
	*/
	public static List<Place> findByPriceId(long priceId, int start, int end,
		OrderByComparator<Place> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByPriceId(priceId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first place in the ordered set where priceId = &#63;.
	*
	* @param priceId the price ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public static Place findByPriceId_First(long priceId,
		OrderByComparator<Place> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence().findByPriceId_First(priceId, orderByComparator);
	}

	/**
	* Returns the first place in the ordered set where priceId = &#63;.
	*
	* @param priceId the price ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place, or <code>null</code> if a matching place could not be found
	*/
	public static Place fetchByPriceId_First(long priceId,
		OrderByComparator<Place> orderByComparator) {
		return getPersistence().fetchByPriceId_First(priceId, orderByComparator);
	}

	/**
	* Returns the last place in the ordered set where priceId = &#63;.
	*
	* @param priceId the price ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public static Place findByPriceId_Last(long priceId,
		OrderByComparator<Place> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence().findByPriceId_Last(priceId, orderByComparator);
	}

	/**
	* Returns the last place in the ordered set where priceId = &#63;.
	*
	* @param priceId the price ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place, or <code>null</code> if a matching place could not be found
	*/
	public static Place fetchByPriceId_Last(long priceId,
		OrderByComparator<Place> orderByComparator) {
		return getPersistence().fetchByPriceId_Last(priceId, orderByComparator);
	}

	/**
	* Returns the places before and after the current place in the ordered set where priceId = &#63;.
	*
	* @param placeId the primary key of the current place
	* @param priceId the price ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next place
	* @throws NoSuchPlaceException if a place with the primary key could not be found
	*/
	public static Place[] findByPriceId_PrevAndNext(long placeId, long priceId,
		OrderByComparator<Place> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence()
				   .findByPriceId_PrevAndNext(placeId, priceId,
			orderByComparator);
	}

	/**
	* Removes all the places where priceId = &#63; from the database.
	*
	* @param priceId the price ID
	*/
	public static void removeByPriceId(long priceId) {
		getPersistence().removeByPriceId(priceId);
	}

	/**
	* Returns the number of places where priceId = &#63;.
	*
	* @param priceId the price ID
	* @return the number of matching places
	*/
	public static int countByPriceId(long priceId) {
		return getPersistence().countByPriceId(priceId);
	}

	/**
	* Returns the place where SIGid = &#63; or throws a {@link NoSuchPlaceException} if it could not be found.
	*
	* @param SIGid the s i gid
	* @return the matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public static Place findBySIGId(java.lang.String SIGid)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence().findBySIGId(SIGid);
	}

	/**
	* Returns the place where SIGid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param SIGid the s i gid
	* @return the matching place, or <code>null</code> if a matching place could not be found
	*/
	public static Place fetchBySIGId(java.lang.String SIGid) {
		return getPersistence().fetchBySIGId(SIGid);
	}

	/**
	* Returns the place where SIGid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param SIGid the s i gid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching place, or <code>null</code> if a matching place could not be found
	*/
	public static Place fetchBySIGId(java.lang.String SIGid,
		boolean retrieveFromCache) {
		return getPersistence().fetchBySIGId(SIGid, retrieveFromCache);
	}

	/**
	* Removes the place where SIGid = &#63; from the database.
	*
	* @param SIGid the s i gid
	* @return the place that was removed
	*/
	public static Place removeBySIGId(java.lang.String SIGid)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence().removeBySIGId(SIGid);
	}

	/**
	* Returns the number of places where SIGid = &#63;.
	*
	* @param SIGid the s i gid
	* @return the number of matching places
	*/
	public static int countBySIGId(java.lang.String SIGid) {
		return getPersistence().countBySIGId(SIGid);
	}

	/**
	* Caches the place in the entity cache if it is enabled.
	*
	* @param place the place
	*/
	public static void cacheResult(Place place) {
		getPersistence().cacheResult(place);
	}

	/**
	* Caches the places in the entity cache if it is enabled.
	*
	* @param places the places
	*/
	public static void cacheResult(List<Place> places) {
		getPersistence().cacheResult(places);
	}

	/**
	* Creates a new place with the primary key. Does not add the place to the database.
	*
	* @param placeId the primary key for the new place
	* @return the new place
	*/
	public static Place create(long placeId) {
		return getPersistence().create(placeId);
	}

	/**
	* Removes the place with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param placeId the primary key of the place
	* @return the place that was removed
	* @throws NoSuchPlaceException if a place with the primary key could not be found
	*/
	public static Place remove(long placeId)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence().remove(placeId);
	}

	public static Place updateImpl(Place place) {
		return getPersistence().updateImpl(place);
	}

	/**
	* Returns the place with the primary key or throws a {@link NoSuchPlaceException} if it could not be found.
	*
	* @param placeId the primary key of the place
	* @return the place
	* @throws NoSuchPlaceException if a place with the primary key could not be found
	*/
	public static Place findByPrimaryKey(long placeId)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getPersistence().findByPrimaryKey(placeId);
	}

	/**
	* Returns the place with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param placeId the primary key of the place
	* @return the place, or <code>null</code> if a place with the primary key could not be found
	*/
	public static Place fetchByPrimaryKey(long placeId) {
		return getPersistence().fetchByPrimaryKey(placeId);
	}

	public static java.util.Map<java.io.Serializable, Place> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the places.
	*
	* @return the places
	*/
	public static List<Place> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @return the range of places
	*/
	public static List<Place> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of places
	*/
	public static List<Place> findAll(int start, int end,
		OrderByComparator<Place> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of places
	*/
	public static List<Place> findAll(int start, int end,
		OrderByComparator<Place> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the places from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of places.
	*
	* @return the number of places
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PlacePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PlacePersistence, PlacePersistence> _serviceTracker =
		ServiceTrackerFactory.open(PlacePersistence.class);
}