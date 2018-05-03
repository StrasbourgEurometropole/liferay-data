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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.objtp.service.http.ObjectCategoryServiceSoap}.
 *
 * @author JeremyZwickert
 * @see eu.strasbourg.service.objtp.service.http.ObjectCategoryServiceSoap
 * @generated
 */
@ProviderType
public class ObjectCategorySoap implements Serializable {
	public static ObjectCategorySoap toSoapModel(ObjectCategory model) {
		ObjectCategorySoap soapModel = new ObjectCategorySoap();

		soapModel.setCode(model.getCode());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static ObjectCategorySoap[] toSoapModels(ObjectCategory[] models) {
		ObjectCategorySoap[] soapModels = new ObjectCategorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ObjectCategorySoap[][] toSoapModels(ObjectCategory[][] models) {
		ObjectCategorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ObjectCategorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new ObjectCategorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ObjectCategorySoap[] toSoapModels(List<ObjectCategory> models) {
		List<ObjectCategorySoap> soapModels = new ArrayList<ObjectCategorySoap>(models.size());

		for (ObjectCategory model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ObjectCategorySoap[soapModels.size()]);
	}

	public ObjectCategorySoap() {
	}

	public String getPrimaryKey() {
		return _code;
	}

	public void setPrimaryKey(String pk) {
		setCode(pk);
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private String _code;
	private String _name;
}