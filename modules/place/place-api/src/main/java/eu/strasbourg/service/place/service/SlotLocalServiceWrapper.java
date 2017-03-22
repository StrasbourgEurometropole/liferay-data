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

package eu.strasbourg.service.place.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SlotLocalService}.
 *
 * @author Angelique Zunino Champougny
 * @see SlotLocalService
 * @generated
 */
@ProviderType
public class SlotLocalServiceWrapper implements SlotLocalService,
	ServiceWrapper<SlotLocalService> {
	public SlotLocalServiceWrapper(SlotLocalService slotLocalService) {
		_slotLocalService = slotLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _slotLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _slotLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _slotLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _slotLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _slotLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the slot to the database. Also notifies the appropriate model listeners.
	*
	* @param slot the slot
	* @return the slot that was added
	*/
	@Override
	public eu.strasbourg.service.place.model.Slot addSlot(
		eu.strasbourg.service.place.model.Slot slot) {
		return _slotLocalService.addSlot(slot);
	}

	/**
	* Crée un slot vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.place.model.Slot createSlot(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _slotLocalService.createSlot(sc);
	}

	/**
	* Creates a new slot with the primary key. Does not add the slot to the database.
	*
	* @param slotId the primary key for the new slot
	* @return the new slot
	*/
	@Override
	public eu.strasbourg.service.place.model.Slot createSlot(long slotId) {
		return _slotLocalService.createSlot(slotId);
	}

	/**
	* Deletes the slot from the database. Also notifies the appropriate model listeners.
	*
	* @param slot the slot
	* @return the slot that was removed
	*/
	@Override
	public eu.strasbourg.service.place.model.Slot deleteSlot(
		eu.strasbourg.service.place.model.Slot slot) {
		return _slotLocalService.deleteSlot(slot);
	}

	/**
	* Deletes the slot with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param slotId the primary key of the slot
	* @return the slot that was removed
	* @throws PortalException if a slot with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.place.model.Slot deleteSlot(long slotId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _slotLocalService.deleteSlot(slotId);
	}

	@Override
	public eu.strasbourg.service.place.model.Slot fetchSlot(long slotId) {
		return _slotLocalService.fetchSlot(slotId);
	}

	/**
	* Returns the slot with the primary key.
	*
	* @param slotId the primary key of the slot
	* @return the slot
	* @throws PortalException if a slot with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.place.model.Slot getSlot(long slotId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _slotLocalService.getSlot(slotId);
	}

	/**
	* Updates the slot in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param slot the slot
	* @return the slot that was updated
	*/
	@Override
	public eu.strasbourg.service.place.model.Slot updateSlot(
		eu.strasbourg.service.place.model.Slot slot) {
		return _slotLocalService.updateSlot(slot);
	}

	/**
	* Returns the number of slots.
	*
	* @return the number of slots
	*/
	@Override
	public int getSlotsCount() {
		return _slotLocalService.getSlotsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _slotLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _slotLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _slotLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _slotLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Retourne les slots rattachées à une période
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Slot> getByPeriodId(
		long slotId) {
		return _slotLocalService.getByPeriodId(slotId);
	}

	/**
	* Returns a range of all the slots.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.SlotModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of slots
	* @param end the upper bound of the range of slots (not inclusive)
	* @return the range of slots
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Slot> getSlots(
		int start, int end) {
		return _slotLocalService.getSlots(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _slotLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _slotLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public SlotLocalService getWrappedService() {
		return _slotLocalService;
	}

	@Override
	public void setWrappedService(SlotLocalService slotLocalService) {
		_slotLocalService = slotLocalService;
	}

	private SlotLocalService _slotLocalService;
}