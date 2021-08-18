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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Angelique Zunino Champougny
 * @generated
 */
@ProviderType
public class CacheJsonSoap implements Serializable {

	public static CacheJsonSoap toSoapModel(CacheJson model) {
		CacheJsonSoap soapModel = new CacheJsonSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSigId(model.getSigId());
		soapModel.setJsonLieu(model.getJsonLieu());
		soapModel.setJsonHoraire(model.getJsonHoraire());
		soapModel.setCreatePlace(model.getCreatePlace());
		soapModel.setModifiedPlace(model.getModifiedPlace());
		soapModel.setIsActive(model.isIsActive());

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

	public String getPrimaryKey() {
		return _sigId;
	}

	public void setPrimaryKey(String pk) {
		setSigId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public String getSigId() {
		return _sigId;
	}

	public void setSigId(String sigId) {
		_sigId = sigId;
	}

	public String getJsonLieu() {
		return _jsonLieu;
	}

	public void setJsonLieu(String jsonLieu) {
		_jsonLieu = jsonLieu;
	}

	public String getJsonHoraire() {
		return _jsonHoraire;
	}

	public void setJsonHoraire(String jsonHoraire) {
		_jsonHoraire = jsonHoraire;
	}

	public Date getCreatePlace() {
		return _createPlace;
	}

	public void setCreatePlace(Date createPlace) {
		_createPlace = createPlace;
	}

	public Date getModifiedPlace() {
		return _modifiedPlace;
	}

	public void setModifiedPlace(Date modifiedPlace) {
		_modifiedPlace = modifiedPlace;
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

	private String _uuid;
	private String _sigId;
	private String _jsonLieu;
	private String _jsonHoraire;
	private Date _createPlace;
	private Date _modifiedPlace;
	private boolean _isActive;

}