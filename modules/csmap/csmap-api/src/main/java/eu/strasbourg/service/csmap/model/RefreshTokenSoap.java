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

package eu.strasbourg.service.csmap.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RefreshTokenSoap implements Serializable {

	public static RefreshTokenSoap toSoapModel(RefreshToken model) {
		RefreshTokenSoap soapModel = new RefreshTokenSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setRefreshTokenId(model.getRefreshTokenId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setValue(model.getValue());
		soapModel.setPublikId(model.getPublikId());

		return soapModel;
	}

	public static RefreshTokenSoap[] toSoapModels(RefreshToken[] models) {
		RefreshTokenSoap[] soapModels = new RefreshTokenSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RefreshTokenSoap[][] toSoapModels(RefreshToken[][] models) {
		RefreshTokenSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RefreshTokenSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RefreshTokenSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RefreshTokenSoap[] toSoapModels(List<RefreshToken> models) {
		List<RefreshTokenSoap> soapModels = new ArrayList<RefreshTokenSoap>(
			models.size());

		for (RefreshToken model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RefreshTokenSoap[soapModels.size()]);
	}

	public RefreshTokenSoap() {
	}

	public long getPrimaryKey() {
		return _refreshTokenId;
	}

	public void setPrimaryKey(long pk) {
		setRefreshTokenId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getRefreshTokenId() {
		return _refreshTokenId;
	}

	public void setRefreshTokenId(long refreshTokenId) {
		_refreshTokenId = refreshTokenId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
	}

	public String getPublikId() {
		return _publikId;
	}

	public void setPublikId(String publikId) {
		_publikId = publikId;
	}

	private String _uuid;
	private long _refreshTokenId;
	private Date _createDate;
	private String _value;
	private String _publikId;

}