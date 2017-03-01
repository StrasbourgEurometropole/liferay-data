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

package eu.strasbourg.service.place.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Slot}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see Slot
 * @generated
 */
@ProviderType
public class SlotWrapper implements Slot, ModelWrapper<Slot> {
	public SlotWrapper(Slot slot) {
		_slot = slot;
	}

	@Override
	public Class<?> getModelClass() {
		return Slot.class;
	}

	@Override
	public String getModelClassName() {
		return Slot.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("slotId", getSlotId());
		attributes.put("dayOfWeek", getDayOfWeek());
		attributes.put("startHout", getStartHout());
		attributes.put("endHour", getEndHour());
		attributes.put("periodId", getPeriodId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long slotId = (Long)attributes.get("slotId");

		if (slotId != null) {
			setSlotId(slotId);
		}

		Long dayOfWeek = (Long)attributes.get("dayOfWeek");

		if (dayOfWeek != null) {
			setDayOfWeek(dayOfWeek);
		}

		String startHout = (String)attributes.get("startHout");

		if (startHout != null) {
			setStartHout(startHout);
		}

		String endHour = (String)attributes.get("endHour");

		if (endHour != null) {
			setEndHour(endHour);
		}

		Long periodId = (Long)attributes.get("periodId");

		if (periodId != null) {
			setPeriodId(periodId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _slot.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _slot.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _slot.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _slot.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.place.model.Slot> toCacheModel() {
		return _slot.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.place.model.Slot toEscapedModel() {
		return new SlotWrapper(_slot.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.place.model.Slot toUnescapedModel() {
		return new SlotWrapper(_slot.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.place.model.Slot slot) {
		return _slot.compareTo(slot);
	}

	@Override
	public int hashCode() {
		return _slot.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _slot.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SlotWrapper((Slot)_slot.clone());
	}

	/**
	* Returns the end hour of this slot.
	*
	* @return the end hour of this slot
	*/
	@Override
	public java.lang.String getEndHour() {
		return _slot.getEndHour();
	}

	/**
	* Returns the start hout of this slot.
	*
	* @return the start hout of this slot
	*/
	@Override
	public java.lang.String getStartHout() {
		return _slot.getStartHout();
	}

	/**
	* Returns the uuid of this slot.
	*
	* @return the uuid of this slot
	*/
	@Override
	public java.lang.String getUuid() {
		return _slot.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _slot.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _slot.toXmlString();
	}

	/**
	* Returns the day of week of this slot.
	*
	* @return the day of week of this slot
	*/
	@Override
	public long getDayOfWeek() {
		return _slot.getDayOfWeek();
	}

	/**
	* Returns the period ID of this slot.
	*
	* @return the period ID of this slot
	*/
	@Override
	public long getPeriodId() {
		return _slot.getPeriodId();
	}

	/**
	* Returns the primary key of this slot.
	*
	* @return the primary key of this slot
	*/
	@Override
	public long getPrimaryKey() {
		return _slot.getPrimaryKey();
	}

	/**
	* Returns the slot ID of this slot.
	*
	* @return the slot ID of this slot
	*/
	@Override
	public long getSlotId() {
		return _slot.getSlotId();
	}

	@Override
	public void persist() {
		_slot.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_slot.setCachedModel(cachedModel);
	}

	/**
	* Sets the day of week of this slot.
	*
	* @param dayOfWeek the day of week of this slot
	*/
	@Override
	public void setDayOfWeek(long dayOfWeek) {
		_slot.setDayOfWeek(dayOfWeek);
	}

	/**
	* Sets the end hour of this slot.
	*
	* @param endHour the end hour of this slot
	*/
	@Override
	public void setEndHour(java.lang.String endHour) {
		_slot.setEndHour(endHour);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_slot.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_slot.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_slot.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_slot.setNew(n);
	}

	/**
	* Sets the period ID of this slot.
	*
	* @param periodId the period ID of this slot
	*/
	@Override
	public void setPeriodId(long periodId) {
		_slot.setPeriodId(periodId);
	}

	/**
	* Sets the primary key of this slot.
	*
	* @param primaryKey the primary key of this slot
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_slot.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_slot.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the slot ID of this slot.
	*
	* @param slotId the slot ID of this slot
	*/
	@Override
	public void setSlotId(long slotId) {
		_slot.setSlotId(slotId);
	}

	/**
	* Sets the start hout of this slot.
	*
	* @param startHout the start hout of this slot
	*/
	@Override
	public void setStartHout(java.lang.String startHout) {
		_slot.setStartHout(startHout);
	}

	/**
	* Sets the uuid of this slot.
	*
	* @param uuid the uuid of this slot
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_slot.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SlotWrapper)) {
			return false;
		}

		SlotWrapper slotWrapper = (SlotWrapper)obj;

		if (Objects.equals(_slot, slotWrapper._slot)) {
			return true;
		}

		return false;
	}

	@Override
	public Slot getWrappedModel() {
		return _slot;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _slot.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _slot.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_slot.resetOriginalValues();
	}

	private final Slot _slot;
}