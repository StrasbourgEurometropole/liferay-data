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
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CacheAgendaJsonSoap implements Serializable {

	public static CacheAgendaJsonSoap toSoapModel(CacheAgendaJson model) {
		CacheAgendaJsonSoap soapModel = new CacheAgendaJsonSoap();

		soapModel.setCacheId(model.getCacheId());
		soapModel.setJson(model.getJson());

		return soapModel;
	}

	public static CacheAgendaJsonSoap[] toSoapModels(CacheAgendaJson[] models) {
		CacheAgendaJsonSoap[] soapModels =
			new CacheAgendaJsonSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CacheAgendaJsonSoap[][] toSoapModels(
		CacheAgendaJson[][] models) {

		CacheAgendaJsonSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CacheAgendaJsonSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CacheAgendaJsonSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CacheAgendaJsonSoap[] toSoapModels(
		List<CacheAgendaJson> models) {

		List<CacheAgendaJsonSoap> soapModels =
			new ArrayList<CacheAgendaJsonSoap>(models.size());

		for (CacheAgendaJson model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CacheAgendaJsonSoap[soapModels.size()]);
	}

	public CacheAgendaJsonSoap() {
	}

	public long getPrimaryKey() {
		return _cacheId;
	}

	public void setPrimaryKey(long pk) {
		setCacheId(pk);
	}

	public long getCacheId() {
		return _cacheId;
	}

	public void setCacheId(long cacheId) {
		_cacheId = cacheId;
	}

	public String getJson() {
		return _json;
	}

	public void setJson(String json) {
		_json = json;
	}

	private long _cacheId;
	private String _json;

}