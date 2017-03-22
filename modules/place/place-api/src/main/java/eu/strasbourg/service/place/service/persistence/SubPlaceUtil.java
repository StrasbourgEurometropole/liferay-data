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

import eu.strasbourg.service.place.model.SubPlace;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the sub place service. This utility wraps {@link eu.strasbourg.service.place.service.persistence.impl.SubPlacePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see SubPlacePersistence
 * @see eu.strasbourg.service.place.service.persistence.impl.SubPlacePersistenceImpl
 * @generated
 */
@ProviderType
public class SubPlaceUtil {
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
	public static void clearCache(SubPlace subPlace) {
		getPersistence().clearCache(subPlace);
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
	public static List<SubPlace> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SubPlace> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SubPlace> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SubPlace> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SubPlace update(SubPlace subPlace) {
		return getPersistence().update(subPlace);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SubPlace update(SubPlace subPlace,
		ServiceContext serviceContext) {
		return getPersistence().update(subPlace, serviceContext);
	}

	/**
	* Returns all the sub places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sub places
	*/
	public static List<SubPlace> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the sub places where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sub places
	* @param end the upper bound of the range of sub places (not inclusive)
	* @return the range of matching sub places
	*/
	public static List<SubPlace> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the sub places where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sub places
	* @param end the upper bound of the range of sub places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sub places
	*/
	public static List<SubPlace> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<SubPlace> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sub places where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sub places
	* @param end the upper bound of the range of sub places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sub places
	*/
	public static List<SubPlace> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<SubPlace> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sub place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sub place
	* @throws NoSuchSubPlaceException if a matching sub place could not be found
	*/
	public static SubPlace findByUuid_First(java.lang.String uuid,
		OrderByComparator<SubPlace> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSubPlaceException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first sub place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sub place, or <code>null</code> if a matching sub place could not be found
	*/
	public static SubPlace fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<SubPlace> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last sub place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sub place
	* @throws NoSuchSubPlaceException if a matching sub place could not be found
	*/
	public static SubPlace findByUuid_Last(java.lang.String uuid,
		OrderByComparator<SubPlace> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSubPlaceException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last sub place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sub place, or <code>null</code> if a matching sub place could not be found
	*/
	public static SubPlace fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<SubPlace> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the sub places before and after the current sub place in the ordered set where uuid = &#63;.
	*
	* @param subPlaceId the primary key of the current sub place
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sub place
	* @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	*/
	public static SubPlace[] findByUuid_PrevAndNext(long subPlaceId,
		java.lang.String uuid, OrderByComparator<SubPlace> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSubPlaceException {
		return getPersistence()
				   .findByUuid_PrevAndNext(subPlaceId, uuid, orderByComparator);
	}

	/**
	* Removes all the sub places where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of sub places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sub places
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the sub places where placeId = &#63;.
	*
	* @param placeId the place ID
	* @return the matching sub places
	*/
	public static List<SubPlace> findByPlaceId(long placeId) {
		return getPersistence().findByPlaceId(placeId);
	}

	/**
	* Returns a range of all the sub places where placeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeId the place ID
	* @param start the lower bound of the range of sub places
	* @param end the upper bound of the range of sub places (not inclusive)
	* @return the range of matching sub places
	*/
	public static List<SubPlace> findByPlaceId(long placeId, int start, int end) {
		return getPersistence().findByPlaceId(placeId, start, end);
	}

	/**
	* Returns an ordered range of all the sub places where placeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeId the place ID
	* @param start the lower bound of the range of sub places
	* @param end the upper bound of the range of sub places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sub places
	*/
	public static List<SubPlace> findByPlaceId(long placeId, int start,
		int end, OrderByComparator<SubPlace> orderByComparator) {
		return getPersistence()
				   .findByPlaceId(placeId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sub places where placeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeId the place ID
	* @param start the lower bound of the range of sub places
	* @param end the upper bound of the range of sub places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sub places
	*/
	public static List<SubPlace> findByPlaceId(long placeId, int start,
		int end, OrderByComparator<SubPlace> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPlaceId(placeId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sub place in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sub place
	* @throws NoSuchSubPlaceException if a matching sub place could not be found
	*/
	public static SubPlace findByPlaceId_First(long placeId,
		OrderByComparator<SubPlace> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSubPlaceException {
		return getPersistence().findByPlaceId_First(placeId, orderByComparator);
	}

	/**
	* Returns the first sub place in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sub place, or <code>null</code> if a matching sub place could not be found
	*/
	public static SubPlace fetchByPlaceId_First(long placeId,
		OrderByComparator<SubPlace> orderByComparator) {
		return getPersistence().fetchByPlaceId_First(placeId, orderByComparator);
	}

	/**
	* Returns the last sub place in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sub place
	* @throws NoSuchSubPlaceException if a matching sub place could not be found
	*/
	public static SubPlace findByPlaceId_Last(long placeId,
		OrderByComparator<SubPlace> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSubPlaceException {
		return getPersistence().findByPlaceId_Last(placeId, orderByComparator);
	}

	/**
	* Returns the last sub place in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sub place, or <code>null</code> if a matching sub place could not be found
	*/
	public static SubPlace fetchByPlaceId_Last(long placeId,
		OrderByComparator<SubPlace> orderByComparator) {
		return getPersistence().fetchByPlaceId_Last(placeId, orderByComparator);
	}

	/**
	* Returns the sub places before and after the current sub place in the ordered set where placeId = &#63;.
	*
	* @param subPlaceId the primary key of the current sub place
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sub place
	* @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	*/
	public static SubPlace[] findByPlaceId_PrevAndNext(long subPlaceId,
		long placeId, OrderByComparator<SubPlace> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSubPlaceException {
		return getPersistence()
				   .findByPlaceId_PrevAndNext(subPlaceId, placeId,
			orderByComparator);
	}

	/**
	* Removes all the sub places where placeId = &#63; from the database.
	*
	* @param placeId the place ID
	*/
	public static void removeByPlaceId(long placeId) {
		getPersistence().removeByPlaceId(placeId);
	}

	/**
	* Returns the number of sub places where placeId = &#63;.
	*
	* @param placeId the place ID
	* @return the number of matching sub places
	*/
	public static int countByPlaceId(long placeId) {
		return getPersistence().countByPlaceId(placeId);
	}

	/**
	* Caches the sub place in the entity cache if it is enabled.
	*
	* @param subPlace the sub place
	*/
	public static void cacheResult(SubPlace subPlace) {
		getPersistence().cacheResult(subPlace);
	}

	/**
	* Caches the sub places in the entity cache if it is enabled.
	*
	* @param subPlaces the sub places
	*/
	public static void cacheResult(List<SubPlace> subPlaces) {
		getPersistence().cacheResult(subPlaces);
	}

	/**
	* Creates a new sub place with the primary key. Does not add the sub place to the database.
	*
	* @param subPlaceId the primary key for the new sub place
	* @return the new sub place
	*/
	public static SubPlace create(long subPlaceId) {
		return getPersistence().create(subPlaceId);
	}

	/**
	* Removes the sub place with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param subPlaceId the primary key of the sub place
	* @return the sub place that was removed
	* @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	*/
	public static SubPlace remove(long subPlaceId)
		throws eu.strasbourg.service.place.exception.NoSuchSubPlaceException {
		return getPersistence().remove(subPlaceId);
	}

	public static SubPlace updateImpl(SubPlace subPlace) {
		return getPersistence().updateImpl(subPlace);
	}

	/**
	* Returns the sub place with the primary key or throws a {@link NoSuchSubPlaceException} if it could not be found.
	*
	* @param subPlaceId the primary key of the sub place
	* @return the sub place
	* @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	*/
	public static SubPlace findByPrimaryKey(long subPlaceId)
		throws eu.strasbourg.service.place.exception.NoSuchSubPlaceException {
		return getPersistence().findByPrimaryKey(subPlaceId);
	}

	/**
	* Returns the sub place with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param subPlaceId the primary key of the sub place
	* @return the sub place, or <code>null</code> if a sub place with the primary key could not be found
	*/
	public static SubPlace fetchByPrimaryKey(long subPlaceId) {
		return getPersistence().fetchByPrimaryKey(subPlaceId);
	}

	public static java.util.Map<java.io.Serializable, SubPlace> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sub places.
	*
	* @return the sub places
	*/
	public static List<SubPlace> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the sub places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sub places
	* @param end the upper bound of the range of sub places (not inclusive)
	* @return the range of sub places
	*/
	public static List<SubPlace> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the sub places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sub places
	* @param end the upper bound of the range of sub places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sub places
	*/
	public static List<SubPlace> findAll(int start, int end,
		OrderByComparator<SubPlace> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sub places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sub places
	* @param end the upper bound of the range of sub places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sub places
	*/
	public static List<SubPlace> findAll(int start, int end,
		OrderByComparator<SubPlace> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the sub places from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sub places.
	*
	* @return the number of sub places
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SubPlacePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SubPlacePersistence, SubPlacePersistence> _serviceTracker =
		ServiceTrackerFactory.open(SubPlacePersistence.class);
}