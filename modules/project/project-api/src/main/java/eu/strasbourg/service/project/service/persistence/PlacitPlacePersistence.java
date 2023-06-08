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

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the placit place service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
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
	@Override
	public Map<Serializable, PlacitPlace> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the placit places where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching placit places
	 */
	public java.util.List<PlacitPlace> findByUuid(String uuid);

	/**
	 * Returns a range of all the placit places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the placit places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns an ordered range of all the placit places where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first placit place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the first placit place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the last placit place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the last placit place in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where uuid = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	public PlacitPlace[] findByUuid_PrevAndNext(
			long placitPlaceId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Removes all the placit places where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of placit places where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching placit places
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the placit place where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchPlacitPlaceException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByUUID_G(String uuid, long groupId)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the placit place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the placit place where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the placit place where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the placit place that was removed
	 */
	public PlacitPlace removeByUUID_G(String uuid, long groupId)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the number of placit places where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching placit places
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the placit places where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching placit places
	 */
	public java.util.List<PlacitPlace> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the placit places where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the placit places where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns an ordered range of all the placit places where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the first placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the last placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the last placit place in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

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
	public PlacitPlace[] findByUuid_C_PrevAndNext(
			long placitPlaceId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Removes all the placit places where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of placit places where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching placit places
	 */
	public int countByUuid_C(String uuid, long companyId);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the placit places where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns an ordered range of all the placit places where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first placit place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the first placit place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the last placit place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the last placit place in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where groupId = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	public PlacitPlace[] findByGroupId_PrevAndNext(
			long placitPlaceId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByProject(
		long projectId, int start, int end);

	/**
	 * Returns an ordered range of all the placit places where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByProject(
		long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns an ordered range of all the placit places where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByProject(
		long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first placit place in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByProject_First(
			long projectId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the first placit place in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByProject_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the last placit place in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByProject_Last(
			long projectId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the last placit place in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByProject_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where projectId = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	public PlacitPlace[] findByProject_PrevAndNext(
			long placitPlaceId, long projectId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
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
	public java.util.List<PlacitPlace> findByParticipation(
		long participationId);

	/**
	 * Returns a range of all the placit places where participationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
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
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns an ordered range of all the placit places where participationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param participationId the participation ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByParticipation(
		long participationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first placit place in the ordered set where participationId = &#63;.
	 *
	 * @param participationId the participation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByParticipation_First(
			long participationId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the first placit place in the ordered set where participationId = &#63;.
	 *
	 * @param participationId the participation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByParticipation_First(
		long participationId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the last placit place in the ordered set where participationId = &#63;.
	 *
	 * @param participationId the participation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByParticipation_Last(
			long participationId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the last placit place in the ordered set where participationId = &#63;.
	 *
	 * @param participationId the participation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByParticipation_Last(
		long participationId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where participationId = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param participationId the participation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	public PlacitPlace[] findByParticipation_PrevAndNext(
			long placitPlaceId, long participationId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByPetition(
		long petitionId, int start, int end);

	/**
	 * Returns an ordered range of all the placit places where petitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByPetition(
		long petitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns an ordered range of all the placit places where petitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param petitionId the petition ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByPetition(
		long petitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first placit place in the ordered set where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByPetition_First(
			long petitionId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the first placit place in the ordered set where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByPetition_First(
		long petitionId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the last placit place in the ordered set where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByPetition_Last(
			long petitionId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the last placit place in the ordered set where petitionId = &#63;.
	 *
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByPetition_Last(
		long petitionId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where petitionId = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param petitionId the petition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	public PlacitPlace[] findByPetition_PrevAndNext(
			long placitPlaceId, long petitionId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
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
	 * Returns all the placit places where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @return the matching placit places
	 */
	public java.util.List<PlacitPlace> findByBudgetParticipatif(
		long budgetParticipatifId);

	/**
	 * Returns a range of all the placit places where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByBudgetParticipatif(
		long budgetParticipatifId, int start, int end);

	/**
	 * Returns an ordered range of all the placit places where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByBudgetParticipatif(
		long budgetParticipatifId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns an ordered range of all the placit places where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByBudgetParticipatif(
		long budgetParticipatifId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first placit place in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByBudgetParticipatif_First(
			long budgetParticipatifId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the first placit place in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByBudgetParticipatif_First(
		long budgetParticipatifId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the last placit place in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByBudgetParticipatif_Last(
			long budgetParticipatifId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the last placit place in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByBudgetParticipatif_Last(
		long budgetParticipatifId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	public PlacitPlace[] findByBudgetParticipatif_PrevAndNext(
			long placitPlaceId, long budgetParticipatifId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Removes all the placit places where budgetParticipatifId = &#63; from the database.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 */
	public void removeByBudgetParticipatif(long budgetParticipatifId);

	/**
	 * Returns the number of placit places where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @return the number of matching placit places
	 */
	public int countByBudgetParticipatif(long budgetParticipatifId);

	/**
	 * Returns all the placit places where initiativeId = &#63;.
	 *
	 * @param initiativeId the initiative ID
	 * @return the matching placit places
	 */
	public java.util.List<PlacitPlace> findByInitiative(long initiativeId);

	/**
	 * Returns a range of all the placit places where initiativeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param initiativeId the initiative ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByInitiative(
		long initiativeId, int start, int end);

	/**
	 * Returns an ordered range of all the placit places where initiativeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param initiativeId the initiative ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByInitiative(
		long initiativeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns an ordered range of all the placit places where initiativeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param initiativeId the initiative ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findByInitiative(
		long initiativeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first placit place in the ordered set where initiativeId = &#63;.
	 *
	 * @param initiativeId the initiative ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByInitiative_First(
			long initiativeId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the first placit place in the ordered set where initiativeId = &#63;.
	 *
	 * @param initiativeId the initiative ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByInitiative_First(
		long initiativeId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the last placit place in the ordered set where initiativeId = &#63;.
	 *
	 * @param initiativeId the initiative ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findByInitiative_Last(
			long initiativeId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the last placit place in the ordered set where initiativeId = &#63;.
	 *
	 * @param initiativeId the initiative ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchByInitiative_Last(
		long initiativeId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where initiativeId = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param initiativeId the initiative ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	public PlacitPlace[] findByInitiative_PrevAndNext(
			long placitPlaceId, long initiativeId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Removes all the placit places where initiativeId = &#63; from the database.
	 *
	 * @param initiativeId the initiative ID
	 */
	public void removeByInitiative(long initiativeId);

	/**
	 * Returns the number of placit places where initiativeId = &#63;.
	 *
	 * @param initiativeId the initiative ID
	 * @return the number of matching placit places
	 */
	public int countByInitiative(long initiativeId);

	/**
	 * Returns all the placit places where placeSIGId = &#63;.
	 *
	 * @param placeSIGId the place sig ID
	 * @return the matching placit places
	 */
	public java.util.List<PlacitPlace> findBySigId(String placeSIGId);

	/**
	 * Returns a range of all the placit places where placeSIGId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param placeSIGId the place sig ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @return the range of matching placit places
	 */
	public java.util.List<PlacitPlace> findBySigId(
		String placeSIGId, int start, int end);

	/**
	 * Returns an ordered range of all the placit places where placeSIGId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param placeSIGId the place sig ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findBySigId(
		String placeSIGId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns an ordered range of all the placit places where placeSIGId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param placeSIGId the place sig ID
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching placit places
	 */
	public java.util.List<PlacitPlace> findBySigId(
		String placeSIGId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first placit place in the ordered set where placeSIGId = &#63;.
	 *
	 * @param placeSIGId the place sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findBySigId_First(
			String placeSIGId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the first placit place in the ordered set where placeSIGId = &#63;.
	 *
	 * @param placeSIGId the place sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchBySigId_First(
		String placeSIGId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the last placit place in the ordered set where placeSIGId = &#63;.
	 *
	 * @param placeSIGId the place sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place
	 * @throws NoSuchPlacitPlaceException if a matching placit place could not be found
	 */
	public PlacitPlace findBySigId_Last(
			String placeSIGId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Returns the last placit place in the ordered set where placeSIGId = &#63;.
	 *
	 * @param placeSIGId the place sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching placit place, or <code>null</code> if a matching placit place could not be found
	 */
	public PlacitPlace fetchBySigId_Last(
		String placeSIGId,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns the placit places before and after the current placit place in the ordered set where placeSIGId = &#63;.
	 *
	 * @param placitPlaceId the primary key of the current placit place
	 * @param placeSIGId the place sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next placit place
	 * @throws NoSuchPlacitPlaceException if a placit place with the primary key could not be found
	 */
	public PlacitPlace[] findBySigId_PrevAndNext(
			long placitPlaceId, String placeSIGId,
			com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
				orderByComparator)
		throws NoSuchPlacitPlaceException;

	/**
	 * Removes all the placit places where placeSIGId = &#63; from the database.
	 *
	 * @param placeSIGId the place sig ID
	 */
	public void removeBySigId(String placeSIGId);

	/**
	 * Returns the number of placit places where placeSIGId = &#63;.
	 *
	 * @param placeSIGId the place sig ID
	 * @return the number of matching placit places
	 */
	public int countBySigId(String placeSIGId);

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
	 * Returns the placit place with the primary key or throws a <code>NoSuchPlacitPlaceException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of placit places
	 */
	public java.util.List<PlacitPlace> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator);

	/**
	 * Returns an ordered range of all the placit places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlacitPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of placit places
	 * @param end the upper bound of the range of placit places (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of placit places
	 */
	public java.util.List<PlacitPlace> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlacitPlace>
			orderByComparator,
		boolean useFinderCache);

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
	public Set<String> getBadColumnNames();

}