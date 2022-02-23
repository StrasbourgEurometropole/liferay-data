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
 * This class is used by SOAP remote services.
 *
 * @author BenjaminBini
 * @generated
 */
@ProviderType
public class CacheJsonSoap implements Serializable {

	public static CacheJsonSoap toSoapModel(CacheJson model) {
		CacheJsonSoap soapModel = new CacheJsonSoap();

		soapModel.setEventId(model.getEventId());
		soapModel.setJsonEvent(model.getJsonEvent());
		soapModel.setCreateEvent(model.getCreateEvent());
		soapModel.setModifiedEvent(model.getModifiedEvent());
		soapModel.setIsApproved(model.isIsApproved());

		return soapModel;
	}

	public static CacheJsonSoap[] toSoapModels(CacheJson[] models) {
		CacheJsonSoap[] soapModels = new CacheJsonSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CacheJsonSoap[][] toSoapModels(CacheJson[][] models) {
		CacheJsonSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CacheJsonSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CacheJsonSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CacheJsonSoap[] toSoapModels(List<CacheJson> models) {
		List<CacheJsonSoap> soapModels = new ArrayList<CacheJsonSoap>(
			models.size());

		for (CacheJson model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CacheJsonSoap[soapModels.size()]);
	}

	public CacheJsonSoap() {
	}

	public long getPrimaryKey() {
		return _eventId;
	}

	public void setPrimaryKey(long pk) {
		setEventId(pk);
	}

	public long getEventId() {
		return _eventId;
	}

	public void setEventId(long eventId) {
		_eventId = eventId;
	}

	public String getJsonEvent() {
		return _jsonEvent;
	}

	public void setJsonEvent(String jsonEvent) {
		_jsonEvent = jsonEvent;
	}

	public Date getCreateEvent() {
		return _createEvent;
	}

	public void setCreateEvent(Date createEvent) {
		_createEvent = createEvent;
	}

	public Date getModifiedEvent() {
		return _modifiedEvent;
	}

	public void setModifiedEvent(Date modifiedEvent) {
		_modifiedEvent = modifiedEvent;
	}

	public boolean getIsApproved() {
		return _isApproved;
	}

	public boolean isIsApproved() {
		return _isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		_isApproved = isApproved;
	}

	private long _eventId;
	private String _jsonEvent;
	private Date _createEvent;
	private Date _modifiedEvent;
	private boolean _isApproved;

}