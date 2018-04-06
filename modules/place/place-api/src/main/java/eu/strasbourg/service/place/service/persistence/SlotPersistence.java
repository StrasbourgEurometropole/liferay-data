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

import eu.strasbourg.service.place.exception.NoSuchSlotException;
import eu.strasbourg.service.place.model.Slot;

/**
 * The persistence interface for the slot service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see eu.strasbourg.service.place.service.persistence.impl.SlotPersistenceImpl
 * @see SlotUtil
 * @generated
 */
@ProviderType
public interface SlotPersistence extends BasePersistence<Slot> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SlotUtil} to access the slot persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the slots where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching slots
	*/
	public java.util.List<Slot> findByUuid(java.lang.String uuid);

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
	public java.util.List<Slot> findByUuid(java.lang.String uuid, int start,
		int end);

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
	public java.util.List<Slot> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator);

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
	public java.util.List<Slot> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first slot in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching slot
	* @throws NoSuchSlotException if a matching slot could not be found
	*/
	public Slot findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator)
		throws NoSuchSlotException;

	/**
	* Returns the first slot in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching slot, or <code>null</code> if a matching slot could not be found
	*/
	public Slot fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator);

	/**
	* Returns the last slot in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching slot
	* @throws NoSuchSlotException if a matching slot could not be found
	*/
	public Slot findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator)
		throws NoSuchSlotException;

	/**
	* Returns the last slot in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching slot, or <code>null</code> if a matching slot could not be found
	*/
	public Slot fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator);

	/**
	* Returns the slots before and after the current slot in the ordered set where uuid = &#63;.
	*
	* @param slotId the primary key of the current slot
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next slot
	* @throws NoSuchSlotException if a slot with the primary key could not be found
	*/
	public Slot[] findByUuid_PrevAndNext(long slotId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator)
		throws NoSuchSlotException;

	/**
	* Removes all the slots where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of slots where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching slots
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the slots where periodId = &#63;.
	*
	* @param periodId the period ID
	* @return the matching slots
	*/
	public java.util.List<Slot> findByPeriodId(long periodId);

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
	public java.util.List<Slot> findByPeriodId(long periodId, int start, int end);

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
	public java.util.List<Slot> findByPeriodId(long periodId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator);

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
	public java.util.List<Slot> findByPeriodId(long periodId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first slot in the ordered set where periodId = &#63;.
	*
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching slot
	* @throws NoSuchSlotException if a matching slot could not be found
	*/
	public Slot findByPeriodId_First(long periodId,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator)
		throws NoSuchSlotException;

	/**
	* Returns the first slot in the ordered set where periodId = &#63;.
	*
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching slot, or <code>null</code> if a matching slot could not be found
	*/
	public Slot fetchByPeriodId_First(long periodId,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator);

	/**
	* Returns the last slot in the ordered set where periodId = &#63;.
	*
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching slot
	* @throws NoSuchSlotException if a matching slot could not be found
	*/
	public Slot findByPeriodId_Last(long periodId,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator)
		throws NoSuchSlotException;

	/**
	* Returns the last slot in the ordered set where periodId = &#63;.
	*
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching slot, or <code>null</code> if a matching slot could not be found
	*/
	public Slot fetchByPeriodId_Last(long periodId,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator);

	/**
	* Returns the slots before and after the current slot in the ordered set where periodId = &#63;.
	*
	* @param slotId the primary key of the current slot
	* @param periodId the period ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next slot
	* @throws NoSuchSlotException if a slot with the primary key could not be found
	*/
	public Slot[] findByPeriodId_PrevAndNext(long slotId, long periodId,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator)
		throws NoSuchSlotException;

	/**
	* Removes all the slots where periodId = &#63; from the database.
	*
	* @param periodId the period ID
	*/
	public void removeByPeriodId(long periodId);

	/**
	* Returns the number of slots where periodId = &#63;.
	*
	* @param periodId the period ID
	* @return the number of matching slots
	*/
	public int countByPeriodId(long periodId);

	/**
	* Returns all the slots where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @return the matching slots
	*/
	public java.util.List<Slot> findBySubPlaceId(long subPlaceId);

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
	public java.util.List<Slot> findBySubPlaceId(long subPlaceId, int start,
		int end);

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
	public java.util.List<Slot> findBySubPlaceId(long subPlaceId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator);

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
	public java.util.List<Slot> findBySubPlaceId(long subPlaceId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first slot in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching slot
	* @throws NoSuchSlotException if a matching slot could not be found
	*/
	public Slot findBySubPlaceId_First(long subPlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator)
		throws NoSuchSlotException;

	/**
	* Returns the first slot in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching slot, or <code>null</code> if a matching slot could not be found
	*/
	public Slot fetchBySubPlaceId_First(long subPlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator);

	/**
	* Returns the last slot in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching slot
	* @throws NoSuchSlotException if a matching slot could not be found
	*/
	public Slot findBySubPlaceId_Last(long subPlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator)
		throws NoSuchSlotException;

	/**
	* Returns the last slot in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching slot, or <code>null</code> if a matching slot could not be found
	*/
	public Slot fetchBySubPlaceId_Last(long subPlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator);

	/**
	* Returns the slots before and after the current slot in the ordered set where subPlaceId = &#63;.
	*
	* @param slotId the primary key of the current slot
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next slot
	* @throws NoSuchSlotException if a slot with the primary key could not be found
	*/
	public Slot[] findBySubPlaceId_PrevAndNext(long slotId, long subPlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator)
		throws NoSuchSlotException;

	/**
	* Removes all the slots where subPlaceId = &#63; from the database.
	*
	* @param subPlaceId the sub place ID
	*/
	public void removeBySubPlaceId(long subPlaceId);

	/**
	* Returns the number of slots where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @return the number of matching slots
	*/
	public int countBySubPlaceId(long subPlaceId);

	/**
	* Caches the slot in the entity cache if it is enabled.
	*
	* @param slot the slot
	*/
	public void cacheResult(Slot slot);

	/**
	* Caches the slots in the entity cache if it is enabled.
	*
	* @param slots the slots
	*/
	public void cacheResult(java.util.List<Slot> slots);

	/**
	* Creates a new slot with the primary key. Does not add the slot to the database.
	*
	* @param slotId the primary key for the new slot
	* @return the new slot
	*/
	public Slot create(long slotId);

	/**
	* Removes the slot with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param slotId the primary key of the slot
	* @return the slot that was removed
	* @throws NoSuchSlotException if a slot with the primary key could not be found
	*/
	public Slot remove(long slotId) throws NoSuchSlotException;

	public Slot updateImpl(Slot slot);

	/**
	* Returns the slot with the primary key or throws a {@link NoSuchSlotException} if it could not be found.
	*
	* @param slotId the primary key of the slot
	* @return the slot
	* @throws NoSuchSlotException if a slot with the primary key could not be found
	*/
	public Slot findByPrimaryKey(long slotId) throws NoSuchSlotException;

	/**
	* Returns the slot with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param slotId the primary key of the slot
	* @return the slot, or <code>null</code> if a slot with the primary key could not be found
	*/
	public Slot fetchByPrimaryKey(long slotId);

	@Override
	public java.util.Map<java.io.Serializable, Slot> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the slots.
	*
	* @return the slots
	*/
	public java.util.List<Slot> findAll();

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
	public java.util.List<Slot> findAll(int start, int end);

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
	public java.util.List<Slot> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator);

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
	public java.util.List<Slot> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Slot> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the slots from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of slots.
	*
	* @return the number of slots
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}