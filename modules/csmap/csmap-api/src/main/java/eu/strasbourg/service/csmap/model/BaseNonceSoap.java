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
public class BaseNonceSoap implements Serializable {

	public static BaseNonceSoap toSoapModel(BaseNonce model) {
		BaseNonceSoap soapModel = new BaseNonceSoap();

		soapModel.setBaseNonceId(model.getBaseNonceId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setValue(model.getValue());

		return soapModel;
	}

	public static BaseNonceSoap[] toSoapModels(BaseNonce[] models) {
		BaseNonceSoap[] soapModels = new BaseNonceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BaseNonceSoap[][] toSoapModels(BaseNonce[][] models) {
		BaseNonceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BaseNonceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BaseNonceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BaseNonceSoap[] toSoapModels(List<BaseNonce> models) {
		List<BaseNonceSoap> soapModels = new ArrayList<BaseNonceSoap>(
			models.size());

		for (BaseNonce model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BaseNonceSoap[soapModels.size()]);
	}

	public BaseNonceSoap() {
	}

	public long getPrimaryKey() {
		return _baseNonceId;
	}

	public void setPrimaryKey(long pk) {
		setBaseNonceId(pk);
	}

	public long getBaseNonceId() {
		return _baseNonceId;
	}

	public void setBaseNonceId(long baseNonceId) {
		_baseNonceId = baseNonceId;
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

	private long _baseNonceId;
	private Date _createDate;
	private String _value;

}