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
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.agenda.service.http.EventPeriodServiceSoap}.
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.http.EventPeriodServiceSoap
 * @generated
 */
@ProviderType
public class EventPeriodSoap implements Serializable {
	public static EventPeriodSoap toSoapModel(EventPeriod model) {
		EventPeriodSoap soapModel = new EventPeriodSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setEventPeriodId(model.getEventPeriodId());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setTimeDetail(model.getTimeDetail());
		soapModel.setEventId(model.getEventId());

		return soapModel;
	}

	public static EventPeriodSoap[] toSoapModels(EventPeriod[] models) {
		EventPeriodSoap[] soapModels = new EventPeriodSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EventPeriodSoap[][] toSoapModels(EventPeriod[][] models) {
		EventPeriodSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EventPeriodSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EventPeriodSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EventPeriodSoap[] toSoapModels(List<EventPeriod> models) {
		List<EventPeriodSoap> soapModels = new ArrayList<EventPeriodSoap>(models.size());

		for (EventPeriod model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EventPeriodSoap[soapModels.size()]);
	}

	public EventPeriodSoap() {
	}

	public long getPrimaryKey() {
		return _eventPeriodId;
	}

	public void setPrimaryKey(long pk) {
		setEventPeriodId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getEventPeriodId() {
		return _eventPeriodId;
	}

	public void setEventPeriodId(long eventPeriodId) {
		_eventPeriodId = eventPeriodId;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public String getTimeDetail() {
		return _timeDetail;
	}

	public void setTimeDetail(String timeDetail) {
		_timeDetail = timeDetail;
	}

	public long getEventId() {
		return _eventId;
	}

	public void setEventId(long eventId) {
		_eventId = eventId;
	}

	private String _uuid;
	private long _eventPeriodId;
	private Date _startDate;
	private Date _endDate;
	private String _timeDetail;
	private long _eventId;
}