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

package eu.strasbourg.service.agenda.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CsmapCacheJson}.
 * </p>
 *
 * @author BenjaminBini
 * @see CsmapCacheJson
 * @generated
 */
@ProviderType
public class CsmapCacheJsonWrapper
	implements CsmapCacheJson, ModelWrapper<CsmapCacheJson> {

	public CsmapCacheJsonWrapper(CsmapCacheJson csmapCacheJson) {
		_csmapCacheJson = csmapCacheJson;
	}

	@Override
	public Class<?> getModelClass() {
		return CsmapCacheJson.class;
	}

	@Override
	public String getModelClassName() {
		return CsmapCacheJson.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("eventId", getEventId());
		attributes.put("jsonEvent", getJsonEvent());
		attributes.put("createEvent", getCreateEvent());
		attributes.put("modifiedEvent", getModifiedEvent());
		attributes.put("isActive", isIsActive());
		attributes.put("regeneratedDate", getRegeneratedDate());
		attributes.put("hasSchedules", isHasSchedules());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long eventId = (Long)attributes.get("eventId");

		if (eventId != null) {
			setEventId(eventId);
		}

		String jsonEvent = (String)attributes.get("jsonEvent");

		if (jsonEvent != null) {
			setJsonEvent(jsonEvent);
		}

		Date createEvent = (Date)attributes.get("createEvent");

		if (createEvent != null) {
			setCreateEvent(createEvent);
		}

		Date modifiedEvent = (Date)attributes.get("modifiedEvent");

		if (modifiedEvent != null) {
			setModifiedEvent(modifiedEvent);
		}

		Boolean isActive = (Boolean)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}

		Date regeneratedDate = (Date)attributes.get("regeneratedDate");

		if (regeneratedDate != null) {
			setRegeneratedDate(regeneratedDate);
		}

		Boolean hasSchedules = (Boolean)attributes.get("hasSchedules");

		if (hasSchedules != null) {
			setHasSchedules(hasSchedules);
		}
	}

	@Override
	public Object clone() {
		return new CsmapCacheJsonWrapper(
			(CsmapCacheJson)_csmapCacheJson.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.agenda.model.CsmapCacheJson csmapCacheJson) {

		return _csmapCacheJson.compareTo(csmapCacheJson);
	}

	/**
	 * Returns the create event of this csmap cache json.
	 *
	 * @return the create event of this csmap cache json
	 */
	@Override
	public Date getCreateEvent() {
		return _csmapCacheJson.getCreateEvent();
	}

	/**
	 * Returns the event ID of this csmap cache json.
	 *
	 * @return the event ID of this csmap cache json
	 */
	@Override
	public long getEventId() {
		return _csmapCacheJson.getEventId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _csmapCacheJson.getExpandoBridge();
	}

	/**
	 * Returns the has schedules of this csmap cache json.
	 *
	 * @return the has schedules of this csmap cache json
	 */
	@Override
	public boolean getHasSchedules() {
		return _csmapCacheJson.getHasSchedules();
	}

	/**
	 * Returns the is active of this csmap cache json.
	 *
	 * @return the is active of this csmap cache json
	 */
	@Override
	public boolean getIsActive() {
		return _csmapCacheJson.getIsActive();
	}

	/**
	 * Returns the json event of this csmap cache json.
	 *
	 * @return the json event of this csmap cache json
	 */
	@Override
	public String getJsonEvent() {
		return _csmapCacheJson.getJsonEvent();
	}

	/**
	 * Returns the modified event of this csmap cache json.
	 *
	 * @return the modified event of this csmap cache json
	 */
	@Override
	public Date getModifiedEvent() {
		return _csmapCacheJson.getModifiedEvent();
	}

	/**
	 * Returns the primary key of this csmap cache json.
	 *
	 * @return the primary key of this csmap cache json
	 */
	@Override
	public long getPrimaryKey() {
		return _csmapCacheJson.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _csmapCacheJson.getPrimaryKeyObj();
	}

	/**
	 * Returns the regenerated date of this csmap cache json.
	 *
	 * @return the regenerated date of this csmap cache json
	 */
	@Override
	public Date getRegeneratedDate() {
		return _csmapCacheJson.getRegeneratedDate();
	}

	/**
	 * Returns the uuid of this csmap cache json.
	 *
	 * @return the uuid of this csmap cache json
	 */
	@Override
	public String getUuid() {
		return _csmapCacheJson.getUuid();
	}

	@Override
	public int hashCode() {
		return _csmapCacheJson.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _csmapCacheJson.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _csmapCacheJson.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this csmap cache json is has schedules.
	 *
	 * @return <code>true</code> if this csmap cache json is has schedules; <code>false</code> otherwise
	 */
	@Override
	public boolean isHasSchedules() {
		return _csmapCacheJson.isHasSchedules();
	}

	/**
	 * Returns <code>true</code> if this csmap cache json is is active.
	 *
	 * @return <code>true</code> if this csmap cache json is is active; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsActive() {
		return _csmapCacheJson.isIsActive();
	}

	@Override
	public boolean isNew() {
		return _csmapCacheJson.isNew();
	}

	@Override
	public void persist() {
		_csmapCacheJson.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_csmapCacheJson.setCachedModel(cachedModel);
	}

	/**
	 * Sets the create event of this csmap cache json.
	 *
	 * @param createEvent the create event of this csmap cache json
	 */
	@Override
	public void setCreateEvent(Date createEvent) {
		_csmapCacheJson.setCreateEvent(createEvent);
	}

	/**
	 * Sets the event ID of this csmap cache json.
	 *
	 * @param eventId the event ID of this csmap cache json
	 */
	@Override
	public void setEventId(long eventId) {
		_csmapCacheJson.setEventId(eventId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_csmapCacheJson.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_csmapCacheJson.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_csmapCacheJson.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets whether this csmap cache json is has schedules.
	 *
	 * @param hasSchedules the has schedules of this csmap cache json
	 */
	@Override
	public void setHasSchedules(boolean hasSchedules) {
		_csmapCacheJson.setHasSchedules(hasSchedules);
	}

	/**
	 * Sets whether this csmap cache json is is active.
	 *
	 * @param isActive the is active of this csmap cache json
	 */
	@Override
	public void setIsActive(boolean isActive) {
		_csmapCacheJson.setIsActive(isActive);
	}

	/**
	 * Sets the json event of this csmap cache json.
	 *
	 * @param jsonEvent the json event of this csmap cache json
	 */
	@Override
	public void setJsonEvent(String jsonEvent) {
		_csmapCacheJson.setJsonEvent(jsonEvent);
	}

	/**
	 * Sets the modified event of this csmap cache json.
	 *
	 * @param modifiedEvent the modified event of this csmap cache json
	 */
	@Override
	public void setModifiedEvent(Date modifiedEvent) {
		_csmapCacheJson.setModifiedEvent(modifiedEvent);
	}

	@Override
	public void setNew(boolean n) {
		_csmapCacheJson.setNew(n);
	}

	/**
	 * Sets the primary key of this csmap cache json.
	 *
	 * @param primaryKey the primary key of this csmap cache json
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_csmapCacheJson.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_csmapCacheJson.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the regenerated date of this csmap cache json.
	 *
	 * @param regeneratedDate the regenerated date of this csmap cache json
	 */
	@Override
	public void setRegeneratedDate(Date regeneratedDate) {
		_csmapCacheJson.setRegeneratedDate(regeneratedDate);
	}

	/**
	 * Sets the uuid of this csmap cache json.
	 *
	 * @param uuid the uuid of this csmap cache json
	 */
	@Override
	public void setUuid(String uuid) {
		_csmapCacheJson.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.agenda.model.CsmapCacheJson> toCacheModel() {

		return _csmapCacheJson.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.agenda.model.CsmapCacheJson toEscapedModel() {
		return new CsmapCacheJsonWrapper(_csmapCacheJson.toEscapedModel());
	}

	@Override
	public String toString() {
		return _csmapCacheJson.toString();
	}

	@Override
	public eu.strasbourg.service.agenda.model.CsmapCacheJson
		toUnescapedModel() {

		return new CsmapCacheJsonWrapper(_csmapCacheJson.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _csmapCacheJson.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CsmapCacheJsonWrapper)) {
			return false;
		}

		CsmapCacheJsonWrapper csmapCacheJsonWrapper =
			(CsmapCacheJsonWrapper)obj;

		if (Objects.equals(
				_csmapCacheJson, csmapCacheJsonWrapper._csmapCacheJson)) {

			return true;
		}

		return false;
	}

	@Override
	public CsmapCacheJson getWrappedModel() {
		return _csmapCacheJson;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _csmapCacheJson.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _csmapCacheJson.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_csmapCacheJson.resetOriginalValues();
	}

	private final CsmapCacheJson _csmapCacheJson;

}