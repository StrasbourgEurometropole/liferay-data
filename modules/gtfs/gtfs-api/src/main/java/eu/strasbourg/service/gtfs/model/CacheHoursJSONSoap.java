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

package eu.strasbourg.service.gtfs.model;

import eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Cedric Henry
 * @generated
 */
public class CacheHoursJSONSoap implements Serializable {

	public static CacheHoursJSONSoap toSoapModel(CacheHoursJSON model) {
		CacheHoursJSONSoap soapModel = new CacheHoursJSONSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setStopCode(model.getStopCode());
		soapModel.setType(model.getType());
		soapModel.setJsonHour(model.getJsonHour());
		soapModel.setCreationDate(model.getCreationDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static CacheHoursJSONSoap[] toSoapModels(CacheHoursJSON[] models) {
		CacheHoursJSONSoap[] soapModels = new CacheHoursJSONSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CacheHoursJSONSoap[][] toSoapModels(
		CacheHoursJSON[][] models) {

		CacheHoursJSONSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CacheHoursJSONSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CacheHoursJSONSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CacheHoursJSONSoap[] toSoapModels(
		List<CacheHoursJSON> models) {

		List<CacheHoursJSONSoap> soapModels = new ArrayList<CacheHoursJSONSoap>(
			models.size());

		for (CacheHoursJSON model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CacheHoursJSONSoap[soapModels.size()]);
	}

	public CacheHoursJSONSoap() {
	}

	public CacheHoursJSONPK getPrimaryKey() {
		return new CacheHoursJSONPK(_stopCode, _type);
	}

	public void setPrimaryKey(CacheHoursJSONPK pk) {
		setStopCode(pk.stopCode);
		setType(pk.type);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public String getStopCode() {
		return _stopCode;
	}

	public void setStopCode(String stopCode) {
		_stopCode = stopCode;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public String getJsonHour() {
		return _jsonHour;
	}

	public void setJsonHour(String jsonHour) {
		_jsonHour = jsonHour;
	}

	public Date getCreationDate() {
		return _creationDate;
	}

	public void setCreationDate(Date creationDate) {
		_creationDate = creationDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	private String _uuid;
	private String _stopCode;
	private int _type;
	private String _jsonHour;
	private Date _creationDate;
	private Date _modifiedDate;

}