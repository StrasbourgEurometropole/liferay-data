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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.agenda.service.http.EventParticipationServiceSoap}.
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.http.EventParticipationServiceSoap
 * @generated
 */
@ProviderType
public class EventParticipationSoap implements Serializable {
	public static EventParticipationSoap toSoapModel(EventParticipation model) {
		EventParticipationSoap soapModel = new EventParticipationSoap();

		soapModel.setEventParticipationId(model.getEventParticipationId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setPublikUserId(model.getPublikUserId());
		soapModel.setEventId(model.getEventId());
		soapModel.setGroupId(model.getGroupId());

		return soapModel;
	}

	public static EventParticipationSoap[] toSoapModels(
		EventParticipation[] models) {
		EventParticipationSoap[] soapModels = new EventParticipationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EventParticipationSoap[][] toSoapModels(
		EventParticipation[][] models) {
		EventParticipationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EventParticipationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EventParticipationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EventParticipationSoap[] toSoapModels(
		List<EventParticipation> models) {
		List<EventParticipationSoap> soapModels = new ArrayList<EventParticipationSoap>(models.size());

		for (EventParticipation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EventParticipationSoap[soapModels.size()]);
	}

	public EventParticipationSoap() {
	}

	public long getPrimaryKey() {
		return _eventParticipationId;
	}

	public void setPrimaryKey(long pk) {
		setEventParticipationId(pk);
	}

	public long getEventParticipationId() {
		return _eventParticipationId;
	}

	public void setEventParticipationId(long eventParticipationId) {
		_eventParticipationId = eventParticipationId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getPublikUserId() {
		return _publikUserId;
	}

	public void setPublikUserId(String publikUserId) {
		_publikUserId = publikUserId;
	}

	public long getEventId() {
		return _eventId;
	}

	public void setEventId(long eventId) {
		_eventId = eventId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	private long _eventParticipationId;
	private Date _createDate;
	private String _publikUserId;
	private long _eventId;
	private long _groupId;
}