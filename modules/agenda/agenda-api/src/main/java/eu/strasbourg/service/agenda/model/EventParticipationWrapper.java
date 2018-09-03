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
 * This class is a wrapper for {@link EventParticipation}.
 * </p>
 *
 * @author BenjaminBini
 * @see EventParticipation
 * @generated
 */
@ProviderType
public class EventParticipationWrapper implements EventParticipation,
	ModelWrapper<EventParticipation> {
	public EventParticipationWrapper(EventParticipation eventParticipation) {
		_eventParticipation = eventParticipation;
	}

	@Override
	public Class<?> getModelClass() {
		return EventParticipation.class;
	}

	@Override
	public String getModelClassName() {
		return EventParticipation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("eventParticipationId", getEventParticipationId());
		attributes.put("createDate", getCreateDate());
		attributes.put("publikUserId", getPublikUserId());
		attributes.put("eventId", getEventId());
		attributes.put("groupId", getGroupId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long eventParticipationId = (Long)attributes.get("eventParticipationId");

		if (eventParticipationId != null) {
			setEventParticipationId(eventParticipationId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String publikUserId = (String)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}

		Long eventId = (Long)attributes.get("eventId");

		if (eventId != null) {
			setEventId(eventId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _eventParticipation.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _eventParticipation.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _eventParticipation.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _eventParticipation.getExpandoBridge();
	}

	/**
	* Retourne la version JSON d'une participation d'evenement
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _eventParticipation.toJSON();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.agenda.model.EventParticipation> toCacheModel() {
		return _eventParticipation.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.agenda.model.EventParticipation toEscapedModel() {
		return new EventParticipationWrapper(_eventParticipation.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.agenda.model.EventParticipation toUnescapedModel() {
		return new EventParticipationWrapper(_eventParticipation.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.agenda.model.EventParticipation eventParticipation) {
		return _eventParticipation.compareTo(eventParticipation);
	}

	@Override
	public int hashCode() {
		return _eventParticipation.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _eventParticipation.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new EventParticipationWrapper((EventParticipation)_eventParticipation.clone());
	}

	/**
	* Returns the publik user ID of this event participation.
	*
	* @return the publik user ID of this event participation
	*/
	@Override
	public java.lang.String getPublikUserId() {
		return _eventParticipation.getPublikUserId();
	}

	@Override
	public java.lang.String toString() {
		return _eventParticipation.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _eventParticipation.toXmlString();
	}

	/**
	* Returns the create date of this event participation.
	*
	* @return the create date of this event participation
	*/
	@Override
	public Date getCreateDate() {
		return _eventParticipation.getCreateDate();
	}

	/**
	* Returns the event ID of this event participation.
	*
	* @return the event ID of this event participation
	*/
	@Override
	public long getEventId() {
		return _eventParticipation.getEventId();
	}

	/**
	* Returns the event participation ID of this event participation.
	*
	* @return the event participation ID of this event participation
	*/
	@Override
	public long getEventParticipationId() {
		return _eventParticipation.getEventParticipationId();
	}

	/**
	* Returns the group ID of this event participation.
	*
	* @return the group ID of this event participation
	*/
	@Override
	public long getGroupId() {
		return _eventParticipation.getGroupId();
	}

	/**
	* Returns the primary key of this event participation.
	*
	* @return the primary key of this event participation
	*/
	@Override
	public long getPrimaryKey() {
		return _eventParticipation.getPrimaryKey();
	}

	@Override
	public void persist() {
		_eventParticipation.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_eventParticipation.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this event participation.
	*
	* @param createDate the create date of this event participation
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_eventParticipation.setCreateDate(createDate);
	}

	/**
	* Sets the event ID of this event participation.
	*
	* @param eventId the event ID of this event participation
	*/
	@Override
	public void setEventId(long eventId) {
		_eventParticipation.setEventId(eventId);
	}

	/**
	* Sets the event participation ID of this event participation.
	*
	* @param eventParticipationId the event participation ID of this event participation
	*/
	@Override
	public void setEventParticipationId(long eventParticipationId) {
		_eventParticipation.setEventParticipationId(eventParticipationId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_eventParticipation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_eventParticipation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_eventParticipation.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this event participation.
	*
	* @param groupId the group ID of this event participation
	*/
	@Override
	public void setGroupId(long groupId) {
		_eventParticipation.setGroupId(groupId);
	}

	@Override
	public void setNew(boolean n) {
		_eventParticipation.setNew(n);
	}

	/**
	* Sets the primary key of this event participation.
	*
	* @param primaryKey the primary key of this event participation
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_eventParticipation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_eventParticipation.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik user ID of this event participation.
	*
	* @param publikUserId the publik user ID of this event participation
	*/
	@Override
	public void setPublikUserId(java.lang.String publikUserId) {
		_eventParticipation.setPublikUserId(publikUserId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventParticipationWrapper)) {
			return false;
		}

		EventParticipationWrapper eventParticipationWrapper = (EventParticipationWrapper)obj;

		if (Objects.equals(_eventParticipation,
					eventParticipationWrapper._eventParticipation)) {
			return true;
		}

		return false;
	}

	@Override
	public EventParticipation getWrappedModel() {
		return _eventParticipation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _eventParticipation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _eventParticipation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_eventParticipation.resetOriginalValues();
	}

	private final EventParticipation _eventParticipation;
}