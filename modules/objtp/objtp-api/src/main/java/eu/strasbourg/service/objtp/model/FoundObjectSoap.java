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

package eu.strasbourg.service.objtp.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.objtp.service.http.FoundObjectServiceSoap}.
 *
 * @author JeremyZwickert
 * @see eu.strasbourg.service.objtp.service.http.FoundObjectServiceSoap
 * @generated
 */
@ProviderType
public class FoundObjectSoap implements Serializable {
	public static FoundObjectSoap toSoapModel(FoundObject model) {
		FoundObjectSoap soapModel = new FoundObjectSoap();

		soapModel.setNumber(model.getNumber());
		soapModel.setDate(model.getDate());
		soapModel.setImageUrl(model.getImageUrl());
		soapModel.setCategoryCode(model.getCategoryCode());

		return soapModel;
	}

	public static FoundObjectSoap[] toSoapModels(FoundObject[] models) {
		FoundObjectSoap[] soapModels = new FoundObjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FoundObjectSoap[][] toSoapModels(FoundObject[][] models) {
		FoundObjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FoundObjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FoundObjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FoundObjectSoap[] toSoapModels(List<FoundObject> models) {
		List<FoundObjectSoap> soapModels = new ArrayList<FoundObjectSoap>(models.size());

		for (FoundObject model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FoundObjectSoap[soapModels.size()]);
	}

	public FoundObjectSoap() {
	}

	public String getPrimaryKey() {
		return _number;
	}

	public void setPrimaryKey(String pk) {
		setNumber(pk);
	}

	public String getNumber() {
		return _number;
	}

	public void setNumber(String number) {
		_number = number;
	}

	public Date getDate() {
		return _date;
	}

	public void setDate(Date date) {
		_date = date;
	}

	public String getImageUrl() {
		return _imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		_imageUrl = imageUrl;
	}

	public String getCategoryCode() {
		return _categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		_categoryCode = categoryCode;
	}

	private String _number;
	private Date _date;
	private String _imageUrl;
	private String _categoryCode;
}