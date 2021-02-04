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

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class NonceSoap implements Serializable {

	public static NonceSoap toSoapModel(Nonce model) {
		NonceSoap soapModel = new NonceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setNonceId(model.getNonceId());
		soapModel.setValue(model.getValue());
		soapModel.setExpirationDate(model.getExpirationDate());

		return soapModel;
	}

	public static NonceSoap[] toSoapModels(Nonce[] models) {
		NonceSoap[] soapModels = new NonceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NonceSoap[][] toSoapModels(Nonce[][] models) {
		NonceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NonceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NonceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NonceSoap[] toSoapModels(List<Nonce> models) {
		List<NonceSoap> soapModels = new ArrayList<NonceSoap>(models.size());

		for (Nonce model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NonceSoap[soapModels.size()]);
	}

	public NonceSoap() {
	}

	public long getPrimaryKey() {
		return _nonceId;
	}

	public void setPrimaryKey(long pk) {
		setNonceId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getNonceId() {
		return _nonceId;
	}

	public void setNonceId(long nonceId) {
		_nonceId = nonceId;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	private String _uuid;
	private long _nonceId;
	private String _value;
	private Date _expirationDate;

}