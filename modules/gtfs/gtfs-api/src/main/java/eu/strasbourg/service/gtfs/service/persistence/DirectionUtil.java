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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.gtfs.model.Direction;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the direction service. This utility wraps <code>eu.strasbourg.service.gtfs.service.persistence.impl.DirectionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see DirectionPersistence
 * @generated
 */
public class DirectionUtil {

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
	public static void clearCache(Direction direction) {
		getPersistence().clearCache(direction);
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
	public static Map<Serializable, Direction> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Direction> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Direction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Direction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Direction> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Direction update(Direction direction) {
		return getPersistence().update(direction);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Direction update(
		Direction direction, ServiceContext serviceContext) {

		return getPersistence().update(direction, serviceContext);
	}

	/**
	 * Returns all the directions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching directions
	 */
	public static List<Direction> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the directions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	public static List<Direction> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the directions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	public static List<Direction> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Direction> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the directions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching directions
	 */
	public static List<Direction> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Direction> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first direction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public static Direction findByUuid_First(
			String uuid, OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first direction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByUuid_First(
		String uuid, OrderByComparator<Direction> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last direction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public static Direction findByUuid_Last(
			String uuid, OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last direction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByUuid_Last(
		String uuid, OrderByComparator<Direction> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the directions before and after the current direction in the ordered set where uuid = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	public static Direction[] findByUuid_PrevAndNext(
			long directionId, String uuid,
			OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByUuid_PrevAndNext(
			directionId, uuid, orderByComparator);
	}

	/**
	 * Removes all the directions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of directions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching directions
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the direction where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchDirectionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public static Direction findByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the direction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the direction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the direction where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the direction that was removed
	 */
	public static Direction removeByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of directions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching directions
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the directions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching directions
	 */
	public static List<Direction> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the directions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	public static List<Direction> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the directions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	public static List<Direction> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Direction> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the directions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching directions
	 */
	public static List<Direction> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Direction> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public static Direction findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Direction> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public static Direction findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last direction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Direction> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

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
	public static Direction[] findByUuid_C_PrevAndNext(
			long directionId, String uuid, long companyId,
			OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByUuid_C_PrevAndNext(
			directionId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the directions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of directions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching directions
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the directions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching directions
	 */
	public static List<Direction> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the directions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	public static List<Direction> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the directions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	public static List<Direction> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Direction> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the directions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching directions
	 */
	public static List<Direction> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Direction> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first direction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public static Direction findByGroupId_First(
			long groupId, OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first direction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByGroupId_First(
		long groupId, OrderByComparator<Direction> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last direction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public static Direction findByGroupId_Last(
			long groupId, OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last direction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByGroupId_Last(
		long groupId, OrderByComparator<Direction> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the directions before and after the current direction in the ordered set where groupId = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	public static Direction[] findByGroupId_PrevAndNext(
			long directionId, long groupId,
			OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByGroupId_PrevAndNext(
			directionId, groupId, orderByComparator);
	}

	/**
	 * Removes all the directions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of directions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching directions
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns the direction where tripId = &#63; or throws a <code>NoSuchDirectionException</code> if it could not be found.
	 *
	 * @param tripId the trip ID
	 * @return the matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public static Direction findByTripId(String tripId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByTripId(tripId);
	}

	/**
	 * Returns the direction where tripId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param tripId the trip ID
	 * @return the matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByTripId(String tripId) {
		return getPersistence().fetchByTripId(tripId);
	}

	/**
	 * Returns the direction where tripId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param tripId the trip ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByTripId(
		String tripId, boolean useFinderCache) {

		return getPersistence().fetchByTripId(tripId, useFinderCache);
	}

	/**
	 * Removes the direction where tripId = &#63; from the database.
	 *
	 * @param tripId the trip ID
	 * @return the direction that was removed
	 */
	public static Direction removeByTripId(String tripId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().removeByTripId(tripId);
	}

	/**
	 * Returns the number of directions where tripId = &#63;.
	 *
	 * @param tripId the trip ID
	 * @return the number of matching directions
	 */
	public static int countByTripId(String tripId) {
		return getPersistence().countByTripId(tripId);
	}

	/**
	 * Returns all the directions where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @return the matching directions
	 */
	public static List<Direction> findByStopId(String stopId) {
		return getPersistence().findByStopId(stopId);
	}

	/**
	 * Returns a range of all the directions where stopId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param stopId the stop ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	public static List<Direction> findByStopId(
		String stopId, int start, int end) {

		return getPersistence().findByStopId(stopId, start, end);
	}

	/**
	 * Returns an ordered range of all the directions where stopId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param stopId the stop ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	public static List<Direction> findByStopId(
		String stopId, int start, int end,
		OrderByComparator<Direction> orderByComparator) {

		return getPersistence().findByStopId(
			stopId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the directions where stopId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param stopId the stop ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching directions
	 */
	public static List<Direction> findByStopId(
		String stopId, int start, int end,
		OrderByComparator<Direction> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByStopId(
			stopId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first direction in the ordered set where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public static Direction findByStopId_First(
			String stopId, OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByStopId_First(stopId, orderByComparator);
	}

	/**
	 * Returns the first direction in the ordered set where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByStopId_First(
		String stopId, OrderByComparator<Direction> orderByComparator) {

		return getPersistence().fetchByStopId_First(stopId, orderByComparator);
	}

	/**
	 * Returns the last direction in the ordered set where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public static Direction findByStopId_Last(
			String stopId, OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByStopId_Last(stopId, orderByComparator);
	}

	/**
	 * Returns the last direction in the ordered set where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByStopId_Last(
		String stopId, OrderByComparator<Direction> orderByComparator) {

		return getPersistence().fetchByStopId_Last(stopId, orderByComparator);
	}

	/**
	 * Returns the directions before and after the current direction in the ordered set where stopId = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param stopId the stop ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	public static Direction[] findByStopId_PrevAndNext(
			long directionId, String stopId,
			OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByStopId_PrevAndNext(
			directionId, stopId, orderByComparator);
	}

	/**
	 * Removes all the directions where stopId = &#63; from the database.
	 *
	 * @param stopId the stop ID
	 */
	public static void removeByStopId(String stopId) {
		getPersistence().removeByStopId(stopId);
	}

	/**
	 * Returns the number of directions where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @return the number of matching directions
	 */
	public static int countByStopId(String stopId) {
		return getPersistence().countByStopId(stopId);
	}

	/**
	 * Returns all the directions where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @return the matching directions
	 */
	public static List<Direction> findByRouteId(String routeId) {
		return getPersistence().findByRouteId(routeId);
	}

	/**
	 * Returns a range of all the directions where routeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param routeId the route ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of matching directions
	 */
	public static List<Direction> findByRouteId(
		String routeId, int start, int end) {

		return getPersistence().findByRouteId(routeId, start, end);
	}

	/**
	 * Returns an ordered range of all the directions where routeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param routeId the route ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching directions
	 */
	public static List<Direction> findByRouteId(
		String routeId, int start, int end,
		OrderByComparator<Direction> orderByComparator) {

		return getPersistence().findByRouteId(
			routeId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the directions where routeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param routeId the route ID
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching directions
	 */
	public static List<Direction> findByRouteId(
		String routeId, int start, int end,
		OrderByComparator<Direction> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByRouteId(
			routeId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first direction in the ordered set where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public static Direction findByRouteId_First(
			String routeId, OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByRouteId_First(routeId, orderByComparator);
	}

	/**
	 * Returns the first direction in the ordered set where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByRouteId_First(
		String routeId, OrderByComparator<Direction> orderByComparator) {

		return getPersistence().fetchByRouteId_First(
			routeId, orderByComparator);
	}

	/**
	 * Returns the last direction in the ordered set where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction
	 * @throws NoSuchDirectionException if a matching direction could not be found
	 */
	public static Direction findByRouteId_Last(
			String routeId, OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByRouteId_Last(routeId, orderByComparator);
	}

	/**
	 * Returns the last direction in the ordered set where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching direction, or <code>null</code> if a matching direction could not be found
	 */
	public static Direction fetchByRouteId_Last(
		String routeId, OrderByComparator<Direction> orderByComparator) {

		return getPersistence().fetchByRouteId_Last(routeId, orderByComparator);
	}

	/**
	 * Returns the directions before and after the current direction in the ordered set where routeId = &#63;.
	 *
	 * @param directionId the primary key of the current direction
	 * @param routeId the route ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	public static Direction[] findByRouteId_PrevAndNext(
			long directionId, String routeId,
			OrderByComparator<Direction> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByRouteId_PrevAndNext(
			directionId, routeId, orderByComparator);
	}

	/**
	 * Removes all the directions where routeId = &#63; from the database.
	 *
	 * @param routeId the route ID
	 */
	public static void removeByRouteId(String routeId) {
		getPersistence().removeByRouteId(routeId);
	}

	/**
	 * Returns the number of directions where routeId = &#63;.
	 *
	 * @param routeId the route ID
	 * @return the number of matching directions
	 */
	public static int countByRouteId(String routeId) {
		return getPersistence().countByRouteId(routeId);
	}

	/**
	 * Caches the direction in the entity cache if it is enabled.
	 *
	 * @param direction the direction
	 */
	public static void cacheResult(Direction direction) {
		getPersistence().cacheResult(direction);
	}

	/**
	 * Caches the directions in the entity cache if it is enabled.
	 *
	 * @param directions the directions
	 */
	public static void cacheResult(List<Direction> directions) {
		getPersistence().cacheResult(directions);
	}

	/**
	 * Creates a new direction with the primary key. Does not add the direction to the database.
	 *
	 * @param directionId the primary key for the new direction
	 * @return the new direction
	 */
	public static Direction create(long directionId) {
		return getPersistence().create(directionId);
	}

	/**
	 * Removes the direction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param directionId the primary key of the direction
	 * @return the direction that was removed
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	public static Direction remove(long directionId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().remove(directionId);
	}

	public static Direction updateImpl(Direction direction) {
		return getPersistence().updateImpl(direction);
	}

	/**
	 * Returns the direction with the primary key or throws a <code>NoSuchDirectionException</code> if it could not be found.
	 *
	 * @param directionId the primary key of the direction
	 * @return the direction
	 * @throws NoSuchDirectionException if a direction with the primary key could not be found
	 */
	public static Direction findByPrimaryKey(long directionId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchDirectionException {

		return getPersistence().findByPrimaryKey(directionId);
	}

	/**
	 * Returns the direction with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param directionId the primary key of the direction
	 * @return the direction, or <code>null</code> if a direction with the primary key could not be found
	 */
	public static Direction fetchByPrimaryKey(long directionId) {
		return getPersistence().fetchByPrimaryKey(directionId);
	}

	/**
	 * Returns all the directions.
	 *
	 * @return the directions
	 */
	public static List<Direction> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the directions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of directions
	 */
	public static List<Direction> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the directions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of directions
	 */
	public static List<Direction> findAll(
		int start, int end, OrderByComparator<Direction> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the directions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of directions
	 */
	public static List<Direction> findAll(
		int start, int end, OrderByComparator<Direction> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the directions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of directions.
	 *
	 * @return the number of directions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DirectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DirectionPersistence, DirectionPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DirectionPersistence.class);

		ServiceTracker<DirectionPersistence, DirectionPersistence>
			serviceTracker =
				new ServiceTracker<DirectionPersistence, DirectionPersistence>(
					bundle.getBundleContext(), DirectionPersistence.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}