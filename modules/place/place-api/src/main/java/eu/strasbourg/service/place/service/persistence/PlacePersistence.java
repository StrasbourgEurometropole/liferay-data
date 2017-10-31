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

import eu.strasbourg.service.place.exception.NoSuchPlaceException;
import eu.strasbourg.service.place.model.Place;

/**
 * The persistence interface for the place service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see eu.strasbourg.service.place.service.persistence.impl.PlacePersistenceImpl
 * @see PlaceUtil
 * @generated
 */
@ProviderType
public interface PlacePersistence extends BasePersistence<Place> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PlaceUtil} to access the place persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching places
	*/
	public java.util.List<Place> findByUuid(java.lang.String uuid);

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
	public java.util.List<Place> findByUuid(java.lang.String uuid, int start,
		int end);

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
	public java.util.List<Place> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator);

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
	public java.util.List<Place> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public Place findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator)
		throws NoSuchPlaceException;

	/**
	* Returns the first place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place, or <code>null</code> if a matching place could not be found
	*/
	public Place fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator);

	/**
	* Returns the last place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public Place findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator)
		throws NoSuchPlaceException;

	/**
	* Returns the last place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place, or <code>null</code> if a matching place could not be found
	*/
	public Place fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator);

	/**
	* Returns the places before and after the current place in the ordered set where uuid = &#63;.
	*
	* @param placeId the primary key of the current place
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next place
	* @throws NoSuchPlaceException if a place with the primary key could not be found
	*/
	public Place[] findByUuid_PrevAndNext(long placeId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator)
		throws NoSuchPlaceException;

	/**
	* Removes all the places where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching places
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the place where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPlaceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public Place findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPlaceException;

	/**
	* Returns the place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching place, or <code>null</code> if a matching place could not be found
	*/
	public Place fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching place, or <code>null</code> if a matching place could not be found
	*/
	public Place fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the place where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the place that was removed
	*/
	public Place removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPlaceException;

	/**
	* Returns the number of places where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching places
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the places where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching places
	*/
	public java.util.List<Place> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<Place> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<Place> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator);

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
	public java.util.List<Place> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public Place findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator)
		throws NoSuchPlaceException;

	/**
	* Returns the first place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place, or <code>null</code> if a matching place could not be found
	*/
	public Place fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator);

	/**
	* Returns the last place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public Place findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator)
		throws NoSuchPlaceException;

	/**
	* Returns the last place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place, or <code>null</code> if a matching place could not be found
	*/
	public Place fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator);

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
	public Place[] findByUuid_C_PrevAndNext(long placeId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator)
		throws NoSuchPlaceException;

	/**
	* Removes all the places where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of places where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching places
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the places where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching places
	*/
	public java.util.List<Place> findByGroupId(long groupId);

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
	public java.util.List<Place> findByGroupId(long groupId, int start, int end);

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
	public java.util.List<Place> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator);

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
	public java.util.List<Place> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public Place findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator)
		throws NoSuchPlaceException;

	/**
	* Returns the first place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place, or <code>null</code> if a matching place could not be found
	*/
	public Place fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator);

	/**
	* Returns the last place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public Place findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator)
		throws NoSuchPlaceException;

	/**
	* Returns the last place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place, or <code>null</code> if a matching place could not be found
	*/
	public Place fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator);

	/**
	* Returns the places before and after the current place in the ordered set where groupId = &#63;.
	*
	* @param placeId the primary key of the current place
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next place
	* @throws NoSuchPlaceException if a place with the primary key could not be found
	*/
	public Place[] findByGroupId_PrevAndNext(long placeId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator)
		throws NoSuchPlaceException;

	/**
	* Removes all the places where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of places where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching places
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the places where priceId = &#63;.
	*
	* @param priceId the price ID
	* @return the matching places
	*/
	public java.util.List<Place> findByPriceId(long priceId);

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
	public java.util.List<Place> findByPriceId(long priceId, int start, int end);

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
	public java.util.List<Place> findByPriceId(long priceId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator);

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
	public java.util.List<Place> findByPriceId(long priceId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first place in the ordered set where priceId = &#63;.
	*
	* @param priceId the price ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public Place findByPriceId_First(long priceId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator)
		throws NoSuchPlaceException;

	/**
	* Returns the first place in the ordered set where priceId = &#63;.
	*
	* @param priceId the price ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching place, or <code>null</code> if a matching place could not be found
	*/
	public Place fetchByPriceId_First(long priceId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator);

	/**
	* Returns the last place in the ordered set where priceId = &#63;.
	*
	* @param priceId the price ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public Place findByPriceId_Last(long priceId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator)
		throws NoSuchPlaceException;

	/**
	* Returns the last place in the ordered set where priceId = &#63;.
	*
	* @param priceId the price ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching place, or <code>null</code> if a matching place could not be found
	*/
	public Place fetchByPriceId_Last(long priceId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator);

	/**
	* Returns the places before and after the current place in the ordered set where priceId = &#63;.
	*
	* @param placeId the primary key of the current place
	* @param priceId the price ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next place
	* @throws NoSuchPlaceException if a place with the primary key could not be found
	*/
	public Place[] findByPriceId_PrevAndNext(long placeId, long priceId,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator)
		throws NoSuchPlaceException;

	/**
	* Removes all the places where priceId = &#63; from the database.
	*
	* @param priceId the price ID
	*/
	public void removeByPriceId(long priceId);

	/**
	* Returns the number of places where priceId = &#63;.
	*
	* @param priceId the price ID
	* @return the number of matching places
	*/
	public int countByPriceId(long priceId);

	/**
	* Returns the place where SIGid = &#63; or throws a {@link NoSuchPlaceException} if it could not be found.
	*
	* @param SIGid the si gid
	* @return the matching place
	* @throws NoSuchPlaceException if a matching place could not be found
	*/
	public Place findBySIGId(java.lang.String SIGid)
		throws NoSuchPlaceException;

	/**
	* Returns the place where SIGid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param SIGid the si gid
	* @return the matching place, or <code>null</code> if a matching place could not be found
	*/
	public Place fetchBySIGId(java.lang.String SIGid);

	/**
	* Returns the place where SIGid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param SIGid the si gid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching place, or <code>null</code> if a matching place could not be found
	*/
	public Place fetchBySIGId(java.lang.String SIGid, boolean retrieveFromCache);

	/**
	* Removes the place where SIGid = &#63; from the database.
	*
	* @param SIGid the si gid
	* @return the place that was removed
	*/
	public Place removeBySIGId(java.lang.String SIGid)
		throws NoSuchPlaceException;

	/**
	* Returns the number of places where SIGid = &#63;.
	*
	* @param SIGid the si gid
	* @return the number of matching places
	*/
	public int countBySIGId(java.lang.String SIGid);

	/**
	* Caches the place in the entity cache if it is enabled.
	*
	* @param place the place
	*/
	public void cacheResult(Place place);

	/**
	* Caches the places in the entity cache if it is enabled.
	*
	* @param places the places
	*/
	public void cacheResult(java.util.List<Place> places);

	/**
	* Creates a new place with the primary key. Does not add the place to the database.
	*
	* @param placeId the primary key for the new place
	* @return the new place
	*/
	public Place create(long placeId);

	/**
	* Removes the place with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param placeId the primary key of the place
	* @return the place that was removed
	* @throws NoSuchPlaceException if a place with the primary key could not be found
	*/
	public Place remove(long placeId) throws NoSuchPlaceException;

	public Place updateImpl(Place place);

	/**
	* Returns the place with the primary key or throws a {@link NoSuchPlaceException} if it could not be found.
	*
	* @param placeId the primary key of the place
	* @return the place
	* @throws NoSuchPlaceException if a place with the primary key could not be found
	*/
	public Place findByPrimaryKey(long placeId) throws NoSuchPlaceException;

	/**
	* Returns the place with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param placeId the primary key of the place
	* @return the place, or <code>null</code> if a place with the primary key could not be found
	*/
	public Place fetchByPrimaryKey(long placeId);

	@Override
	public java.util.Map<java.io.Serializable, Place> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the places.
	*
	* @return the places
	*/
	public java.util.List<Place> findAll();

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
	public java.util.List<Place> findAll(int start, int end);

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
	public java.util.List<Place> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator);

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
	public java.util.List<Place> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Place> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the places from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of places.
	*
	* @return the number of places
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}