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
public class CsmapCacheJsonSoap implements Serializable {

	public static CsmapCacheJsonSoap toSoapModel(CsmapCacheJson model) {
		CsmapCacheJsonSoap soapModel = new CsmapCacheJsonSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSigId(model.getSigId());
		soapModel.setJsonLieu(model.getJsonLieu());
		soapModel.setJsonHoraire(model.getJsonHoraire());
		soapModel.setCreatePlace(model.getCreatePlace());
		soapModel.setModifiedPlace(model.getModifiedPlace());
		soapModel.setIsActive(model.isIsActive());

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