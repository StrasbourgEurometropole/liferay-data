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

package eu.strasbourg.service.gtfs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.gtfs.exception.NoSuchDirectionException;
import eu.strasbourg.service.gtfs.model.Direction;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the direction service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see DirectionUtil
 * @generated
 */
@ProviderType
public interface DirectionPersistence extends BasePersistence<Direction> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DirectionUtil} to access the direction persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, Direction> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the directions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching directions
	 */
	public java.util.List<Direction> findByUuid(String uuid);

	/**
	 * Returns a range of all the directions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	public java.util.List<Direction> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the directions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	public java.util.List<Direction> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns an ordered range of all the directions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching directions
	 */
	public java.util.List<Direction> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first direction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public Direction findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Returns the first direction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns the last direction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public Direction findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Returns the last direction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns the directions before and after the current direction in the ordered set where uuid = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	public Direction[] findByUuid_PrevAndNext(
			long directionId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Removes all the directions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of directions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching directions
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the direction where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchDirectionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public Direction findByUUID_G(String uuid, long groupId)
		throws NoSuchDirectionException;

	/**
	 * Returns the direction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the direction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache);

	/**
	 * Removes the direction where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the direction that was removed
	 */
	public Direction removeByUUID_G(String uuid, long groupId)
		throws NoSuchDirectionException;

	/**
	 * Returns the number of directions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching directions
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the directions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching directions
	 */
	public java.util.List<Direction> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the directions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	public java.util.List<Direction> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the directions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	public java.util.List<Direction> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns an ordered range of all the directions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching directions
	 */
	public java.util.List<Direction> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public Direction findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Returns the first direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns the last direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public Direction findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Returns the last direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns the directions before and after the current direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	public Direction[] findByUuid_C_PrevAndNext(
			long directionId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Removes all the directions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of directions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching directions
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the directions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching directions
	 */
	public java.util.List<Direction> findByGroupId(long groupId);

	/**
	 * Returns a range of all the directions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	public java.util.List<Direction> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the directions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	public java.util.List<Direction> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns an ordered range of all the directions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching directions
	 */
	public java.util.List<Direction> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first direction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public Direction findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Returns the first direction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns the last direction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public Direction findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Returns the last direction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns the directions before and after the current direction in the ordered set where groupId = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	public Direction[] findByGroupId_PrevAndNext(
			long directionId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Removes all the directions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of directions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching directions
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns the direction where tripId = &#63; or throws a <code>NoSuchDirectionException</code> if it could not be found.
	 *
	 * @param tripId the trip ID
	 * @return the matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public Direction findByTripId(String tripId)
		throws NoSuchDirectionException;

	/**
	 * Returns the direction where tripId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param tripId the trip ID
	 * @return the matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByTripId(String tripId);

	/**
	 * Returns the direction where tripId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param tripId the trip ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByTripId(String tripId, boolean retrieveFromCache);

	/**
	 * Removes the direction where tripId = &#63; from the database.
	 *
	 * @param tripId the trip ID
	 * @return the direction that was removed
	 */
	public Direction removeByTripId(String tripId)
		throws NoSuchDirectionException;

	/**
	 * Returns the number of directions where tripId = &#63;.
	 *
	 * @param tripId the trip ID
	 * @return the number of matching directions
	 */
	public int countByTripId(String tripId);

	/**
	 * Returns all the directions where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @return the matching directions
	 */
	public java.util.List<Direction> findByStopId(String stopId);

	/**
	 * Returns a range of all the directions where stopId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stopId the stop ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	public java.util.List<Direction> findByStopId(
		String stopId, int start, int end);

	/**
	 * Returns an ordered range of all the directions where stopId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stopId the stop ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	public java.util.List<Direction> findByStopId(
		String stopId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns an ordered range of all the directions where stopId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stopId the stop ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching directions
	 */
	public java.util.List<Direction> findByStopId(
		String stopId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first direction in the ordered set where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public Direction findByStopId_First(
			String stopId,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Returns the first direction in the ordered set where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByStopId_First(
		String stopId,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns the last direction in the ordered set where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public Direction findByStopId_Last(
			String stopId,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Returns the last direction in the ordered set where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByStopId_Last(
		String stopId,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns the directions before and after the current direction in the ordered set where stopId = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	public Direction[] findByStopId_PrevAndNext(
			long directionId, String stopId,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Removes all the directions where stopId = &#63; from the database.
	 *
	 * @param stopId the stop ID
	 */
	public void removeByStopId(String stopId);

	/**
	 * Returns the number of directions where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @return the number of matching directions
	 */
	public int countByStopId(String stopId);

	/**
	 * Returns all the directions where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @return the matching directions
	 */
	public java.util.List<Direction> findByRouteId(String routeId);

	/**
	 * Returns a range of all the directions where routeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param routeId the route ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	public java.util.List<Direction> findByRouteId(
		String routeId, int start, int end);

	/**
	 * Returns an ordered range of all the directions where routeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param routeId the route ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	public java.util.List<Direction> findByRouteId(
		String routeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns an ordered range of all the directions where routeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param routeId the route ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching directions
	 */
	public java.util.List<Direction> findByRouteId(
		String routeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first direction in the ordered set where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public Direction findByRouteId_First(
			String routeId,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Returns the first direction in the ordered set where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByRouteId_First(
		String routeId,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns the last direction in the ordered set where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public Direction findByRouteId_Last(
			String routeId,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Returns the last direction in the ordered set where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public Direction fetchByRouteId_Last(
		String routeId,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns the directions before and after the current direction in the ordered set where routeId = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	public Direction[] findByRouteId_PrevAndNext(
			long directionId, String routeId,
			com.liferay.portal.kernel.util.OrderByComparator<Direction>
				orderByComparator)
		throws NoSuchDirectionException;

	/**
	 * Removes all the directions where routeId = &#63; from the database.
	 *
	 * @param routeId the route ID
	 */
	public void removeByRouteId(String routeId);

	/**
	 * Returns the number of directions where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @return the number of matching directions
	 */
	public int countByRouteId(String routeId);

	/**
	 * Caches the direction in the entity cache if it is enabled.
	 *
	 * @param direction the direction
	 */
	public void cacheResult(Direction direction);

	/**
	 * Caches the directions in the entity cache if it is enabled.
	 *
	 * @param directions the directions
	 */
	public void cacheResult(java.util.List<Direction> directions);

	/**
	 * Creates a new direction with the primary key. Does not add the direction to the database.
	 *
	 * @param directionId the primary key for the new direction
	 * @return the new direction
	 */
	public Direction create(long directionId);

	/**
	 * Removes the direction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param directionId the primary key of the direction
	 * @return the direction that was removed
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	public Direction remove(long directionId) throws NoSuchDirectionException;

	public Direction updateImpl(Direction direction);

	/**
	 * Returns the direction with the primary key or throws a <code>NoSuchDirectionException</code> if it could not be found.
	 *
	 * @param directionId the primary key of the direction
	 * @return the direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	public Direction findByPrimaryKey(long directionId)
		throws NoSuchDirectionException;

	/**
	 * Returns the direction with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param directionId the primary key of the direction
	 * @return the direction, or <code>null</code> if a direction with the primary key could not be found
	 */
	public Direction fetchByPrimaryKey(long directionId);

	/**
	 * Returns all the directions.
	 *
	 * @return the directions
	 */
	public java.util.List<Direction> findAll();

	/**
	 * Returns a range of all the directions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of directions
	 */
	public java.util.List<Direction> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the directions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of directions
	 */
	public java.util.List<Direction> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator);

	/**
	 * Returns an ordered range of all the directions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of directions
	 */
	public java.util.List<Direction> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Direction>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the directions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of directions.
	 *
	 * @return the number of directions
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}