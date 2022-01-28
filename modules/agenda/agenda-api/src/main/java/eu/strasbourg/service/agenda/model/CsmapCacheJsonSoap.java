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
public class CsmapCacheJsonSoap implements Serializable {

	public static CsmapCacheJsonSoap toSoapModel(CsmapCacheJson model) {
		CsmapCacheJsonSoap soapModel = new CsmapCacheJsonSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setEventId(model.getEventId());
		soapModel.setJsonEvent(model.getJsonEvent());
		soapModel.setCreateEvent(model.getCreateEvent());
		soapModel.setModifiedEvent(model.getModifiedEvent());
		soapModel.setIsActive(model.isIsActive());
		soapModel.setRegeneratedDate(model.getRegeneratedDate());
		soapModel.setHasSchedules(model.isHasSchedules());

		return soapModel;
	}

	public static CsmapCacheJsonSoap[] toSoapModels(CsmapCacheJson[] models) {
		CsmapCacheJsonSoap[] soapModels = new CsmapCacheJsonSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CsmapCacheJsonSoap[][] toSoapModels(
		CsmapCacheJson[][] models) {

		CsmapCacheJsonSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CsmapCacheJsonSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CsmapCacheJsonSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CsmapCacheJsonSoap[] toSoapModels(
		List<CsmapCacheJson> models) {

		List<CsmapCacheJsonSoap> soapModels = new ArrayList<CsmapCacheJsonSoap>(
			models.size());

		for (CsmapCacheJson model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CsmapCacheJsonSoap[soapModels.size()]);
	}

	public CsmapCacheJsonSoap() {
	}

	public long getPrimaryKey() {
		return _eventId;
	}

	public void setPrimaryKey(long pk) {
		setEventId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
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

	public boolean getIsActive() {
		return _isActive;
	}

	public boolean isIsActive() {
		return _isActive;
	}

	public void setIsActive(boolean isActive) {
		_isActive = isActive;
	}

	public Date getRegeneratedDate() {
		return _regeneratedDate;
	}

	public void setRegeneratedDate(Date regeneratedDate) {
		_regeneratedDate = regeneratedDate;
	}

	public boolean getHasSchedules() {
		return _hasSchedules;
	}

	public boolean isHasSchedules() {
		return _hasSchedules;
	}

	public void setHasSchedules(boolean hasSchedules) {
		_hasSchedules = hasSchedules;
	}

	private String _uuid;
	private long _eventId;
	private String _jsonEvent;
	private Date _createEvent;
	private Date _modifiedEvent;
	private boolean _isActive;
	private Date _regeneratedDate;
	private boolean _hasSchedules;

}