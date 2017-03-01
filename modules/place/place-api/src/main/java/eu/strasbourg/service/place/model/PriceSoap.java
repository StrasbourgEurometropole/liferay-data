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
public class PriceSoap implements Serializable {
	public static PriceSoap toSoapModel(Price model) {
		PriceSoap soapModel = new PriceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPriceId(model.getPriceId());
		soapModel.setTitle(model.getTitle());
		soapModel.setPrice(model.getPrice());

		return soapModel;
	}

	public static PriceSoap[] toSoapModels(Price[] models) {
		PriceSoap[] soapModels = new PriceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PriceSoap[][] toSoapModels(Price[][] models) {
		PriceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PriceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PriceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PriceSoap[] toSoapModels(List<Price> models) {
		List<PriceSoap> soapModels = new ArrayList<PriceSoap>(models.size());

		for (Price model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PriceSoap[soapModels.size()]);
	}

	public PriceSoap() {
	}

	public long getPrimaryKey() {
		return _priceId;
	}

	public void setPrimaryKey(long pk) {
		setPriceId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPriceId() {
		return _priceId;
	}

	public void setPriceId(long priceId) {
		_priceId = priceId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getPrice() {
		return _price;
	}

	public void setPrice(String price) {
		_price = price;
	}

	private String _uuid;
	private long _priceId;
	private String _title;
	private String _price;
}