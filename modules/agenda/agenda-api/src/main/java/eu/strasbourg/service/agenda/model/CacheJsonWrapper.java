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
 * This class is a wrapper for {@link CacheJson}.
 * </p>
 *
 * @author BenjaminBini
 * @see CacheJson
 * @generated
 */
@ProviderType
public class CacheJsonWrapper implements CacheJson, ModelWrapper<CacheJson> {

	public CacheJsonWrapper(CacheJson cacheJson) {
		_cacheJson = cacheJson;
	}

	@Override
	public Class<?> getModelClass() {
		return CacheJson.class;
	}

	@Override
	public String getModelClassName() {
		return CacheJson.class.getName();
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
	}

	@Override
	public Object clone() {
		return new CacheJsonWrapper((CacheJson)_cacheJson.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.agenda.model.CacheJson cacheJson) {

		return _cacheJson.compareTo(cacheJson);
	}

	/**
	 * Returns the create event of this cache json.
	 *
	 * @return the create event of this cache json
	 */
	@Override
	public Date getCreateEvent() {
		return _cacheJson.getCreateEvent();
	}

	/**
	 * Returns the event ID of this cache json.
	 *
	 * @return the event ID of this cache json
	 */
	@Override
	public long getEventId() {
		return _cacheJson.getEventId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cacheJson.getExpandoBridge();
	}

	/**
	 * Returns the is active of this cache json.
	 *
	 * @return the is active of this cache json
	 */
	@Override
	public boolean getIsActive() {
		return _cacheJson.getIsActive();
	}

	/**
	 * Returns the json event of this cache json.
	 *
	 * @return the json event of this cache json
	 */
	@Override
	public String getJsonEvent() {
		return _cacheJson.getJsonEvent();
	}

	/**
	 * Returns the modified event of this cache json.
	 *
	 * @return the modified event of this cache json
	 */
	@Override
	public Date getModifiedEvent() {
		return _cacheJson.getModifiedEvent();
	}

	/**
	 * Returns the primary key of this cache json.
	 *
	 * @return the primary key of this cache json
	 */
	@Override
	public long getPrimaryKey() {
		return _cacheJson.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cacheJson.getPrimaryKeyObj();
	}

	/**
	 * Returns the uuid of this cache json.
	 *
	 * @return the uuid of this cache json
	 */
	@Override
	public String getUuid() {
		return _cacheJson.getUuid();
	}

	@Override
	public int hashCode() {
		return _cacheJson.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cacheJson.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cacheJson.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this cache json is is active.
	 *
	 * @return <code>true</code> if this cache json is is active; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsActive() {
		return _cacheJson.isIsActive();
	}

	@Override
	public boolean isNew() {
		return _cacheJson.isNew();
	}

	@Override
	public void persist() {
		_cacheJson.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cacheJson.setCachedModel(cachedModel);
	}

	/**
	 * Sets the create event of this cache json.
	 *
	 * @param createEvent the create event of this cache json
	 */
	@Override
	public void setCreateEvent(Date createEvent) {
		_cacheJson.setCreateEvent(createEvent);
	}

	/**
	 * Sets the event ID of this cache json.
	 *
	 * @param eventId the event ID of this cache json
	 */
	@Override
	public void setEventId(long eventId) {
		_cacheJson.setEventId(eventId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_cacheJson.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cacheJson.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cacheJson.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets whether this cache json is is active.
	 *
	 * @param isActive the is active of this cache json
	 */
	@Override
	public void setIsActive(boolean isActive) {
		_cacheJson.setIsActive(isActive);
	}

	/**
	 * Sets the json event of this cache json.
	 *
	 * @param jsonEvent the json event of this cache json
	 */
	@Override
	public void setJsonEvent(String jsonEvent) {
		_cacheJson.setJsonEvent(jsonEvent);
	}

	/**
	 * Sets the modified event of this cache json.
	 *
	 * @param modifiedEvent the modified event of this cache json
	 */
	@Override
	public void setModifiedEvent(Date modifiedEvent) {
		_cacheJson.setModifiedEvent(modifiedEvent);
	}

	@Override
	public void setNew(boolean n) {
		_cacheJson.setNew(n);
	}

	/**
	 * Sets the primary key of this cache json.
	 *
	 * @param primaryKey the primary key of this cache json
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_cacheJson.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cacheJson.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the uuid of this cache json.
	 *
	 * @param uuid the uuid of this cache json
	 */
	@Override
	public void setUuid(String uuid) {
		_cacheJson.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.agenda.model.CacheJson> toCacheModel() {

		return _cacheJson.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.agenda.model.CacheJson toEscapedModel() {
		return new CacheJsonWrapper(_cacheJson.toEscapedModel());
	}

	@Override
	public String toString() {
		return _cacheJson.toString();
	}

	@Override
	public eu.strasbourg.service.agenda.model.CacheJson toUnescapedModel() {
		return new CacheJsonWrapper(_cacheJson.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _cacheJson.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CacheJsonWrapper)) {
			return false;
		}

		CacheJsonWrapper cacheJsonWrapper = (CacheJsonWrapper)obj;

		if (Objects.equals(_cacheJson, cacheJsonWrapper._cacheJson)) {
			return true;
		}

		return false;
	}

	@Override
	public CacheJson getWrappedModel() {
		return _cacheJson;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cacheJson.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cacheJson.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cacheJson.resetOriginalValues();
	}

	private final CacheJson _cacheJson;

}