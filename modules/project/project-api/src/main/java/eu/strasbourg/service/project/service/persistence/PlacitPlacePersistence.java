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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.project.exception.NoSuchPlacitPlaceException;
import eu.strasbourg.service.project.model.PlacitPlace;

/**
 * The persistence interface for the placit place service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.persistence.impl.PlacitPlacePersistenceImpl
 * @see PlacitPlaceUtil
 * @generated
 */
@ProviderType
public interface PlacitPlacePersistence extends BasePersistence<PlacitPlace> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PlacitPlaceUtil} to access the placit place persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the placit places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching placit places
	*/
	public java.util.List<PlacitPlace> findByUuid(java.lang.String uuid);

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
	public java.util.List<PlacitPlace> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<PlacitPlace> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

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
	public java.util.List<PlacitPlace> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first placit place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the first placit place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

	/**
	* Returns the last placit place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the last placit place in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

	/**
	* Returns the placit places before and after the current placit place in the ordered set where uuid = &#63;.
	*
	* @param placitPlaceId the primary key of the current placit place
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public PlacitPlace[] findByUuid_PrevAndNext(long placitPlaceId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Removes all the placit places where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of placit places where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching placit places
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the placit place where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPlacitPlaceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the placit place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the placit place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the placit place where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the placit place that was removed
	*/
	public PlacitPlace removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the number of placit places where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching placit places
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the placit places where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching placit places
	*/
	public java.util.List<PlacitPlace> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<PlacitPlace> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<PlacitPlace> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

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
	public java.util.List<PlacitPlace> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the first placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

	/**
	* Returns the last placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the last placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

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
	public PlacitPlace[] findByUuid_C_PrevAndNext(long placitPlaceId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Removes all the placit places where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of placit places where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching placit places
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the placit places where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching placit places
	*/
	public java.util.List<PlacitPlace> findByGroupId(long groupId);

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
	public java.util.List<PlacitPlace> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<PlacitPlace> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

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
	public java.util.List<PlacitPlace> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first placit place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the first placit place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

	/**
	* Returns the last placit place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the last placit place in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

	/**
	* Returns the placit places before and after the current placit place in the ordered set where groupId = &#63;.
	*
	* @param placitPlaceId the primary key of the current placit place
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public PlacitPlace[] findByGroupId_PrevAndNext(long placitPlaceId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Removes all the placit places where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of placit places where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching placit places
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the placit places where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching placit places
	*/
	public java.util.List<PlacitPlace> findByProject(long projectId);

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
	public java.util.List<PlacitPlace> findByProject(long projectId, int start,
		int end);

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
	public java.util.List<PlacitPlace> findByProject(long projectId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

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
	public java.util.List<PlacitPlace> findByProject(long projectId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first placit place in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findByProject_First(long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the first placit place in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByProject_First(long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

	/**
	* Returns the last placit place in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findByProject_Last(long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the last placit place in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByProject_Last(long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

	/**
	* Returns the placit places before and after the current placit place in the ordered set where projectId = &#63;.
	*
	* @param placitPlaceId the primary key of the current placit place
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public PlacitPlace[] findByProject_PrevAndNext(long placitPlaceId,
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Removes all the placit places where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	*/
	public void removeByProject(long projectId);

	/**
	* Returns the number of placit places where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching placit places
	*/
	public int countByProject(long projectId);

	/**
	* Returns all the placit places where participationId = &#63;.
	*
	* @param participationId the participation ID
	* @return the matching placit places
	*/
	public java.util.List<PlacitPlace> findByParticipation(long participationId);

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
	public java.util.List<PlacitPlace> findByParticipation(
		long participationId, int start, int end);

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
	public java.util.List<PlacitPlace> findByParticipation(
		long participationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

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
	public java.util.List<PlacitPlace> findByParticipation(
		long participationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first placit place in the ordered set where participationId = &#63;.
	*
	* @param participationId the participation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findByParticipation_First(long participationId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the first placit place in the ordered set where participationId = &#63;.
	*
	* @param participationId the participation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByParticipation_First(long participationId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

	/**
	* Returns the last placit place in the ordered set where participationId = &#63;.
	*
	* @param participationId the participation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findByParticipation_Last(long participationId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the last placit place in the ordered set where participationId = &#63;.
	*
	* @param participationId the participation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByParticipation_Last(long participationId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

	/**
	* Returns the placit places before and after the current placit place in the ordered set where participationId = &#63;.
	*
	* @param placitPlaceId the primary key of the current placit place
	* @param participationId the participation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public PlacitPlace[] findByParticipation_PrevAndNext(long placitPlaceId,
		long participationId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Removes all the placit places where participationId = &#63; from the database.
	*
	* @param participationId the participation ID
	*/
	public void removeByParticipation(long participationId);

	/**
	* Returns the number of placit places where participationId = &#63;.
	*
	* @param participationId the participation ID
	* @return the number of matching placit places
	*/
	public int countByParticipation(long participationId);

	/**
	* Returns all the placit places where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @return the matching placit places
	*/
	public java.util.List<PlacitPlace> findByPetition(long petitionId);

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
	public java.util.List<PlacitPlace> findByPetition(long petitionId,
		int start, int end);

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
	public java.util.List<PlacitPlace> findByPetition(long petitionId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

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
	public java.util.List<PlacitPlace> findByPetition(long petitionId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first placit place in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findByPetition_First(long petitionId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the first placit place in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByPetition_First(long petitionId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

	/**
	* Returns the last placit place in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findByPetition_Last(long petitionId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the last placit place in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchByPetition_Last(long petitionId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

	/**
	* Returns the placit places before and after the current placit place in the ordered set where petitionId = &#63;.
	*
	* @param placitPlaceId the primary key of the current placit place
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public PlacitPlace[] findByPetition_PrevAndNext(long placitPlaceId,
		long petitionId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Removes all the placit places where petitionId = &#63; from the database.
	*
	* @param petitionId the petition ID
	*/
	public void removeByPetition(long petitionId);

	/**
	* Returns the number of placit places where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @return the number of matching placit places
	*/
	public int countByPetition(long petitionId);

	/**
	* Returns all the placit places where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @return the matching placit places
	*/
	public java.util.List<PlacitPlace> findBySigId(java.lang.String placeSIGId);

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
	public java.util.List<PlacitPlace> findBySigId(
		java.lang.String placeSIGId, int start, int end);

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
	public java.util.List<PlacitPlace> findBySigId(
		java.lang.String placeSIGId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

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
	public java.util.List<PlacitPlace> findBySigId(
		java.lang.String placeSIGId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first placit place in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findBySigId_First(java.lang.String placeSIGId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the first placit place in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchBySigId_First(java.lang.String placeSIGId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

	/**
	* Returns the last placit place in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place
	* @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	*/
	public PlacitPlace findBySigId_Last(java.lang.String placeSIGId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the last placit place in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	public PlacitPlace fetchBySigId_Last(java.lang.String placeSIGId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

	/**
	* Returns the placit places before and after the current placit place in the ordered set where placeSIGId = &#63;.
	*
	* @param placitPlaceId the primary key of the current placit place
	* @param placeSIGId the place sig ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public PlacitPlace[] findBySigId_PrevAndNext(long placitPlaceId,
		java.lang.String placeSIGId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	* Removes all the placit places where placeSIGId = &#63; from the database.
	*
	* @param placeSIGId the place sig ID
	*/
	public void removeBySigId(java.lang.String placeSIGId);

	/**
	* Returns the number of placit places where placeSIGId = &#63;.
	*
	* @param placeSIGId the place sig ID
	* @return the number of matching placit places
	*/
	public int countBySigId(java.lang.String placeSIGId);

	/**
	* Caches the placit place in the entity cache if it is enabled.
	*
	* @param placitPlace the placit place
	*/
	public void cacheResult(PlacitPlace placitPlace);

	/**
	* Caches the placit places in the entity cache if it is enabled.
	*
	* @param placitPlaces the placit places
	*/
	public void cacheResult(java.util.List<PlacitPlace> placitPlaces);

	/**
	* Creates a new placit place with the primary key. Does not add the placit place to the database.
	*
	* @param placitPlaceId the primary key for the new placit place
	* @return the new placit place
	*/
	public PlacitPlace create(long placitPlaceId);

	/**
	* Removes the placit place with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param placitPlaceId the primary key of the placit place
	* @return the placit place that was removed
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public PlacitPlace remove(long placitPlaceId)
		throws NoSuchPlacitPlaceException;

	public PlacitPlace updateImpl(PlacitPlace placitPlace);

	/**
	* Returns the placit place with the primary key or throws a {@link NoSuchPlacitPlaceException} if it could not be found.
	*
	* @param placitPlaceId the primary key of the placit place
	* @return the placit place
	* @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	*/
	public PlacitPlace findByPrimaryKey(long placitPlaceId)
		throws NoSuchPlacitPlaceException;

	/**
	* Returns the placit place with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param placitPlaceId the primary key of the placit place
	* @return the placit place, or <code>null</code> if a placit place with the primary key could not be found
	*/
	public PlacitPlace fetchByPrimaryKey(long placitPlaceId);

	@Override
	public java.util.Map<java.io.Serializable, PlacitPlace> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the placit places.
	*
	* @return the placit places
	*/
	public java.util.List<PlacitPlace> findAll();

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
	public java.util.List<PlacitPlace> findAll(int start, int end);

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
	public java.util.List<PlacitPlace> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator);

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
	public java.util.List<PlacitPlace> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the placit places from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of placit places.
	*
	* @return the number of placit places
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}