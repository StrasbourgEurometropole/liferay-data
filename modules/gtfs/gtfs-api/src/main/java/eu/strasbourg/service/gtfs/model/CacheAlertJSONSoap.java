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

import aQute.bnd.annotation.ProviderType;

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
@ProviderType
public class CacheAlertJSONSoap implements Serializable {

	public static CacheAlertJSONSoap toSoapModel(CacheAlertJSON model) {
		CacheAlertJSONSoap soapModel = new CacheAlertJSONSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCacheId(model.getCacheId());
		soapModel.setJsonAlert(model.getJsonAlert());
		soapModel.setCreationDate(model.getCreationDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static CacheAlertJSONSoap[] toSoapModels(CacheAlertJSON[] models) {
		CacheAlertJSONSoap[] soapModels = new CacheAlertJSONSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CacheAlertJSONSoap[][] toSoapModels(
		CacheAlertJSON[][] models) {

		CacheAlertJSONSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CacheAlertJSONSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CacheAlertJSONSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CacheAlertJSONSoap[] toSoapModels(
		List<CacheAlertJSON> models) {

		List<CacheAlertJSONSoap> soapModels = new ArrayList<CacheAlertJSONSoap>(
			models.size());

		for (CacheAlertJSON model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CacheAlertJSONSoap[soapModels.size()]);
	}

	public CacheAlertJSONSoap() {
	}

	public long getPrimaryKey() {
		return _cacheId;
	}

	public void setPrimaryKey(long pk) {
		setCacheId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCacheId() {
		return _cacheId;
	}

	public void setCacheId(long cacheId) {
		_cacheId = cacheId;
	}

	public String getJsonAlert() {
		return _jsonAlert;
	}

	public void setJsonAlert(String jsonAlert) {
		_jsonAlert = jsonAlert;
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
	private long _cacheId;
	private String _jsonAlert;
	private Date _creationDate;
	private Date _modifiedDate;

}