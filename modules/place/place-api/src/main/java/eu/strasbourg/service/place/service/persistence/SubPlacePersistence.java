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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.place.exception.NoSuchSubPlaceException;
import eu.strasbourg.service.place.model.SubPlace;

/**
 * The persistence interface for the sub place service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see eu.strasbourg.service.place.service.persistence.impl.SubPlacePersistenceImpl
 * @see SubPlaceUtil
 * @generated
 */
@ProviderType
public interface SubPlacePersistence extends BasePersistence<SubPlace> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SubPlaceUtil} to access the sub place persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the sub places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sub places
	*/
	public java.util.List<SubPlace> findByUuid(java.lang.String uuid);

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
	public java.util.List<SubPlace> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<SubPlace> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator);

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
	public java.util.List<SubPlace> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sub place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sub place
	* @throws NoSuchSubPlaceException if a matching sub place could not be found
	*/
	public SubPlace findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException;

	/**
	* Returns the first sub place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sub place, or <code>null</code> if a matching sub place could not be found
	*/
	public SubPlace fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator);

	/**
	* Returns the last sub place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sub place
	* @throws NoSuchSubPlaceException if a matching sub place could not be found
	*/
	public SubPlace findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException;

	/**
	* Returns the last sub place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sub place, or <code>null</code> if a matching sub place could not be found
	*/
	public SubPlace fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator);

	/**
	* Returns the sub places before and after the current sub place in the ordered set where uuid = &#63;.
	*
	* @param subPlaceId the primary key of the current sub place
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sub place
	* @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	*/
	public SubPlace[] findByUuid_PrevAndNext(long subPlaceId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException;

	/**
	* Removes all the sub places where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of sub places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sub places
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the sub places where placeId = &#63;.
	*
	* @param placeId the place ID
	* @return the matching sub places
	*/
	public java.util.List<SubPlace> findByPlaceId(long placeId);

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
	public java.util.List<SubPlace> findByPlaceId(long placeId, int start,
		int end);

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
	public java.util.List<SubPlace> findByPlaceId(long placeId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator);

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
	public java.util.List<SubPlace> findByPlaceId(long placeId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sub place in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sub place
	* @throws NoSuchSubPlaceException if a matching sub place could not be found
	*/
	public SubPlace findByPlaceId_First(long placeId,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException;

	/**
	* Returns the first sub place in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sub place, or <code>null</code> if a matching sub place could not be found
	*/
	public SubPlace fetchByPlaceId_First(long placeId,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator);

	/**
	* Returns the last sub place in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sub place
	* @throws NoSuchSubPlaceException if a matching sub place could not be found
	*/
	public SubPlace findByPlaceId_Last(long placeId,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException;

	/**
	* Returns the last sub place in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sub place, or <code>null</code> if a matching sub place could not be found
	*/
	public SubPlace fetchByPlaceId_Last(long placeId,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator);

	/**
	* Returns the sub places before and after the current sub place in the ordered set where placeId = &#63;.
	*
	* @param subPlaceId the primary key of the current sub place
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sub place
	* @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	*/
	public SubPlace[] findByPlaceId_PrevAndNext(long subPlaceId, long placeId,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator)
		throws NoSuchSubPlaceException;

	/**
	* Removes all the sub places where placeId = &#63; from the database.
	*
	* @param placeId the place ID
	*/
	public void removeByPlaceId(long placeId);

	/**
	* Returns the number of sub places where placeId = &#63;.
	*
	* @param placeId the place ID
	* @return the number of matching sub places
	*/
	public int countByPlaceId(long placeId);

	/**
	* Caches the sub place in the entity cache if it is enabled.
	*
	* @param subPlace the sub place
	*/
	public void cacheResult(SubPlace subPlace);

	/**
	* Caches the sub places in the entity cache if it is enabled.
	*
	* @param subPlaces the sub places
	*/
	public void cacheResult(java.util.List<SubPlace> subPlaces);

	/**
	* Creates a new sub place with the primary key. Does not add the sub place to the database.
	*
	* @param subPlaceId the primary key for the new sub place
	* @return the new sub place
	*/
	public SubPlace create(long subPlaceId);

	/**
	* Removes the sub place with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param subPlaceId the primary key of the sub place
	* @return the sub place that was removed
	* @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	*/
	public SubPlace remove(long subPlaceId) throws NoSuchSubPlaceException;

	public SubPlace updateImpl(SubPlace subPlace);

	/**
	* Returns the sub place with the primary key or throws a {@link NoSuchSubPlaceException} if it could not be found.
	*
	* @param subPlaceId the primary key of the sub place
	* @return the sub place
	* @throws NoSuchSubPlaceException if a sub place with the primary key could not be found
	*/
	public SubPlace findByPrimaryKey(long subPlaceId)
		throws NoSuchSubPlaceException;

	/**
	* Returns the sub place with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param subPlaceId the primary key of the sub place
	* @return the sub place, or <code>null</code> if a sub place with the primary key could not be found
	*/
	public SubPlace fetchByPrimaryKey(long subPlaceId);

	@Override
	public java.util.Map<java.io.Serializable, SubPlace> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the sub places.
	*
	* @return the sub places
	*/
	public java.util.List<SubPlace> findAll();

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
	public java.util.List<SubPlace> findAll(int start, int end);

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
	public java.util.List<SubPlace> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator);

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
	public java.util.List<SubPlace> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubPlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the sub places from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sub places.
	*
	* @return the number of sub places
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}