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

import eu.strasbourg.service.place.model.Slot;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the slot service. This utility wraps {@link eu.strasbourg.service.place.service.persistence.impl.SlotPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see SlotPersistence
 * @see eu.strasbourg.service.place.service.persistence.impl.SlotPersistenceImpl
 * @generated
 */
@ProviderType
public class SlotUtil {
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
	public static void clearCache(Slot slot) {
		getPersistence().clearCache(slot);
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
	public static List<Slot> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Slot> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Slot> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Slot> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Slot update(Slot slot) {
		return getPersistence().update(slot);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Slot update(Slot slot, ServiceContext serviceContext) {
		return getPersistence().update(slot, serviceContext);
	}

	/**
	* Returns all the slots where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching slots
	*/
	public static List<Slot> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the slots where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of slots
	* @param end the upper bound of the range of slots (not inclusive)
	* @return the range of matching slots
	*/
	public static List<Slot> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the slots where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of slots
	* @param end the upper bound of the range of slots (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching slots
	*/
	public static List<Slot> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Slot> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the slots where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of slots
	* @param end the upper bound of the range of slots (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching slots
	*/
	public static List<Slot> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Slot> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first slot in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching slot
	* @throws NoSuchSlotException if a matching slot could not be found
	*/
	public static Slot findByUuid_First(java.lang.String uuid,
		OrderByComparator<Slot> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSlotException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first slot in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching slot, or <code>null</code> if a matching slot could not be found
	*/
	public static Slot fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Slot> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last slot in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching slot
	* @throws NoSuchSlotException if a matching slot could not be found
	*/
	public static Slot findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Slot> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSlotException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last slot in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching slot, or <code>null</code> if a matching slot could not be found
	*/
	public static Slot fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Slot> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the slots before and after the current slot in the ordered set where uuid = &#63;.
	*
	* @param slotId the primary key of the current slot
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next slot
	* @throws NoSuchSlotException if a slot with the primary key could not be found
	*/
	public static Slot[] findByUuid_PrevAndNext(long slotId,
		java.lang.String uuid, OrderByComparator<Slot> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSlotException {
		return getPersistence()
				   .findByUuid_PrevAndNext(slotId, uuid, orderByComparator);
	}

	/**
	* Removes all the slots where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of slots where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching slots
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the slots where periodId = &#63;.
	*
	* @param periodId the period ID
	* @return the matching slots
	*/
	public static List<Slot> findByPeriodId(long periodId) {
		return getPersistence().findByPeriodId(periodId);
	}

	/**
	* Returns a range of all the slots where periodId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param periodId the period ID
	* @param start the lower bound of the range of slots
	* @param end the upper bound of the range of slots (not inclusive)
	* @return the range of matching slots
	*/
	public static List<Slot> findByPeriodId(long periodId, int start, int end) {
		return getPersistence().findByPeriodId(periodId, start, end);
	}

	/**
	* Returns an ordered range of all the slots where periodId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param periodId the period ID
	* @param start the lower bound of the range of slots
	* @param end the upper bound of the range of slots (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching slots
	*/
	public static List<Slot> findByPeriodId(long periodId, int start, int end,
		OrderByComparator<Slot> orderByComparator) {
		return getPersistence()
				   .findByPeriodId(periodId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the slots where periodId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param periodId the period ID
	* @param start the lower bound of the range of slots
	* @param end the upper bound of the range of slots (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching slots
	*/
	public static List<Slot> findByPeriodId(long periodId, int start, int end,
		OrderByComparator<Slot> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByPeriodId(periodId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first slot in the ordered set where periodId = &#63;.
	*
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching slot
	* @throws NoSuchSlotException if a matching slot could not be found
	*/
	public static Slot findByPeriodId_First(long periodId,
		OrderByComparator<Slot> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSlotException {
		return getPersistence().findByPeriodId_First(periodId, orderByComparator);
	}

	/**
	* Returns the first slot in the ordered set where periodId = &#63;.
	*
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching slot, or <code>null</code> if a matching slot could not be found
	*/
	public static Slot fetchByPeriodId_First(long periodId,
		OrderByComparator<Slot> orderByComparator) {
		return getPersistence()
				   .fetchByPeriodId_First(periodId, orderByComparator);
	}

	/**
	* Returns the last slot in the ordered set where periodId = &#63;.
	*
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching slot
	* @throws NoSuchSlotException if a matching slot could not be found
	*/
	public static Slot findByPeriodId_Last(long periodId,
		OrderByComparator<Slot> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSlotException {
		return getPersistence().findByPeriodId_Last(periodId, orderByComparator);
	}

	/**
	* Returns the last slot in the ordered set where periodId = &#63;.
	*
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching slot, or <code>null</code> if a matching slot could not be found
	*/
	public static Slot fetchByPeriodId_Last(long periodId,
		OrderByComparator<Slot> orderByComparator) {
		return getPersistence().fetchByPeriodId_Last(periodId, orderByComparator);
	}

	/**
	* Returns the slots before and after the current slot in the ordered set where periodId = &#63;.
	*
	* @param slotId the primary key of the current slot
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next slot
	* @throws NoSuchSlotException if a slot with the primary key could not be found
	*/
	public static Slot[] findByPeriodId_PrevAndNext(long slotId, long periodId,
		OrderByComparator<Slot> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSlotException {
		return getPersistence()
				   .findByPeriodId_PrevAndNext(slotId, periodId,
			orderByComparator);
	}

	/**
	* Removes all the slots where periodId = &#63; from the database.
	*
	* @param periodId the period ID
	*/
	public static void removeByPeriodId(long periodId) {
		getPersistence().removeByPeriodId(periodId);
	}

	/**
	* Returns the number of slots where periodId = &#63;.
	*
	* @param periodId the period ID
	* @return the number of matching slots
	*/
	public static int countByPeriodId(long periodId) {
		return getPersistence().countByPeriodId(periodId);
	}

	/**
	* Returns all the slots where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @return the matching slots
	*/
	public static List<Slot> findBySubPlaceId(long subPlaceId) {
		return getPersistence().findBySubPlaceId(subPlaceId);
	}

	/**
	* Returns a range of all the slots where subPlaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param subPlaceId the sub place ID
	* @param start the lower bound of the range of slots
	* @param end the upper bound of the range of slots (not inclusive)
	* @return the range of matching slots
	*/
	public static List<Slot> findBySubPlaceId(long subPlaceId, int start,
		int end) {
		return getPersistence().findBySubPlaceId(subPlaceId, start, end);
	}

	/**
	* Returns an ordered range of all the slots where subPlaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param subPlaceId the sub place ID
	* @param start the lower bound of the range of slots
	* @param end the upper bound of the range of slots (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching slots
	*/
	public static List<Slot> findBySubPlaceId(long subPlaceId, int start,
		int end, OrderByComparator<Slot> orderByComparator) {
		return getPersistence()
				   .findBySubPlaceId(subPlaceId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the slots where subPlaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param subPlaceId the sub place ID
	* @param start the lower bound of the range of slots
	* @param end the upper bound of the range of slots (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching slots
	*/
	public static List<Slot> findBySubPlaceId(long subPlaceId, int start,
		int end, OrderByComparator<Slot> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySubPlaceId(subPlaceId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first slot in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching slot
	* @throws NoSuchSlotException if a matching slot could not be found
	*/
	public static Slot findBySubPlaceId_First(long subPlaceId,
		OrderByComparator<Slot> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSlotException {
		return getPersistence()
				   .findBySubPlaceId_First(subPlaceId, orderByComparator);
	}

	/**
	* Returns the first slot in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching slot, or <code>null</code> if a matching slot could not be found
	*/
	public static Slot fetchBySubPlaceId_First(long subPlaceId,
		OrderByComparator<Slot> orderByComparator) {
		return getPersistence()
				   .fetchBySubPlaceId_First(subPlaceId, orderByComparator);
	}

	/**
	* Returns the last slot in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching slot
	* @throws NoSuchSlotException if a matching slot could not be found
	*/
	public static Slot findBySubPlaceId_Last(long subPlaceId,
		OrderByComparator<Slot> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSlotException {
		return getPersistence()
				   .findBySubPlaceId_Last(subPlaceId, orderByComparator);
	}

	/**
	* Returns the last slot in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching slot, or <code>null</code> if a matching slot could not be found
	*/
	public static Slot fetchBySubPlaceId_Last(long subPlaceId,
		OrderByComparator<Slot> orderByComparator) {
		return getPersistence()
				   .fetchBySubPlaceId_Last(subPlaceId, orderByComparator);
	}

	/**
	* Returns the slots before and after the current slot in the ordered set where subPlaceId = &#63;.
	*
	* @param slotId the primary key of the current slot
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next slot
	* @throws NoSuchSlotException if a slot with the primary key could not be found
	*/
	public static Slot[] findBySubPlaceId_PrevAndNext(long slotId,
		long subPlaceId, OrderByComparator<Slot> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchSlotException {
		return getPersistence()
				   .findBySubPlaceId_PrevAndNext(slotId, subPlaceId,
			orderByComparator);
	}

	/**
	* Removes all the slots where subPlaceId = &#63; from the database.
	*
	* @param subPlaceId the sub place ID
	*/
	public static void removeBySubPlaceId(long subPlaceId) {
		getPersistence().removeBySubPlaceId(subPlaceId);
	}

	/**
	* Returns the number of slots where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @return the number of matching slots
	*/
	public static int countBySubPlaceId(long subPlaceId) {
		return getPersistence().countBySubPlaceId(subPlaceId);
	}

	/**
	* Caches the slot in the entity cache if it is enabled.
	*
	* @param slot the slot
	*/
	public static void cacheResult(Slot slot) {
		getPersistence().cacheResult(slot);
	}

	/**
	* Caches the slots in the entity cache if it is enabled.
	*
	* @param slots the slots
	*/
	public static void cacheResult(List<Slot> slots) {
		getPersistence().cacheResult(slots);
	}

	/**
	* Creates a new slot with the primary key. Does not add the slot to the database.
	*
	* @param slotId the primary key for the new slot
	* @return the new slot
	*/
	public static Slot create(long slotId) {
		return getPersistence().create(slotId);
	}

	/**
	* Removes the slot with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param slotId the primary key of the slot
	* @return the slot that was removed
	* @throws NoSuchSlotException if a slot with the primary key could not be found
	*/
	public static Slot remove(long slotId)
		throws eu.strasbourg.service.place.exception.NoSuchSlotException {
		return getPersistence().remove(slotId);
	}

	public static Slot updateImpl(Slot slot) {
		return getPersistence().updateImpl(slot);
	}

	/**
	* Returns the slot with the primary key or throws a {@link NoSuchSlotException} if it could not be found.
	*
	* @param slotId the primary key of the slot
	* @return the slot
	* @throws NoSuchSlotException if a slot with the primary key could not be found
	*/
	public static Slot findByPrimaryKey(long slotId)
		throws eu.strasbourg.service.place.exception.NoSuchSlotException {
		return getPersistence().findByPrimaryKey(slotId);
	}

	/**
	* Returns the slot with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param slotId the primary key of the slot
	* @return the slot, or <code>null</code> if a slot with the primary key could not be found
	*/
	public static Slot fetchByPrimaryKey(long slotId) {
		return getPersistence().fetchByPrimaryKey(slotId);
	}

	public static java.util.Map<java.io.Serializable, Slot> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the slots.
	*
	* @return the slots
	*/
	public static List<Slot> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the slots.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of slots
	* @param end the upper bound of the range of slots (not inclusive)
	* @return the range of slots
	*/
	public static List<Slot> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the slots.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of slots
	* @param end the upper bound of the range of slots (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of slots
	*/
	public static List<Slot> findAll(int start, int end,
		OrderByComparator<Slot> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the slots.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of slots
	* @param end the upper bound of the range of slots (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of slots
	*/
	public static List<Slot> findAll(int start, int end,
		OrderByComparator<Slot> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the slots from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of slots.
	*
	* @return the number of slots
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SlotPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SlotPersistence, SlotPersistence> _serviceTracker =
		ServiceTrackerFactory.open(SlotPersistence.class);
}