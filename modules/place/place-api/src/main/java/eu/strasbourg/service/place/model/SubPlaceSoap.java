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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Angelique Zunino Champougny
 * @generated
 */
@ProviderType
public class SubPlaceSoap implements Serializable {
	public static SubPlaceSoap toSoapModel(SubPlace model) {
		SubPlaceSoap soapModel = new SubPlaceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSubPlaceId(model.getSubPlaceId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setPlaceId(model.getPlaceId());

		return soapModel;
	}

	public static SubPlaceSoap[] toSoapModels(SubPlace[] models) {
		SubPlaceSoap[] soapModels = new SubPlaceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SubPlaceSoap[][] toSoapModels(SubPlace[][] models) {
		SubPlaceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SubPlaceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SubPlaceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SubPlaceSoap[] toSoapModels(List<SubPlace> models) {
		List<SubPlaceSoap> soapModels = new ArrayList<SubPlaceSoap>(models.size());

		for (SubPlace model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SubPlaceSoap[soapModels.size()]);
	}

	public SubPlaceSoap() {
	}

	public long getPrimaryKey() {
		return _subPlaceId;
	}

	public void setPrimaryKey(long pk) {
		setSubPlaceId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSubPlaceId() {
		return _subPlaceId;
	}

	public void setSubPlaceId(long subPlaceId) {
		_subPlaceId = subPlaceId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getPlaceId() {
		return _placeId;
	}

	public void setPlaceId(long placeId) {
		_placeId = placeId;
	}

	private String _uuid;
	private long _subPlaceId;
	private String _name;
	private String _description;
	private long _placeId;
}