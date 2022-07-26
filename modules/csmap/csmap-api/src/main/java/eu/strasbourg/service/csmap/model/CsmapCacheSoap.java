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
public class CsmapCacheSoap implements Serializable {

	public static CsmapCacheSoap toSoapModel(CsmapCache model) {
		CsmapCacheSoap soapModel = new CsmapCacheSoap();

		soapModel.setCacheId(model.getCacheId());
		soapModel.setCodeCache(model.getCodeCache());
		soapModel.setDescription(model.getDescription());
		soapModel.setCacheJson(model.getCacheJson());
		soapModel.setIsLastProcessSuccess(model.getIsLastProcessSuccess());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setProcessedDate(model.getProcessedDate());

		return soapModel;
	}

	public static CsmapCacheSoap[] toSoapModels(CsmapCache[] models) {
		CsmapCacheSoap[] soapModels = new CsmapCacheSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CsmapCacheSoap[][] toSoapModels(CsmapCache[][] models) {
		CsmapCacheSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CsmapCacheSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CsmapCacheSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CsmapCacheSoap[] toSoapModels(List<CsmapCache> models) {
		List<CsmapCacheSoap> soapModels = new ArrayList<CsmapCacheSoap>(
			models.size());

		for (CsmapCache model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CsmapCacheSoap[soapModels.size()]);
	}

	public CsmapCacheSoap() {
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

	public long getCodeCache() {
		return _codeCache;
	}

	public void setCodeCache(long codeCache) {
		_codeCache = codeCache;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getCacheJson() {
		return _cacheJson;
	}

	public void setCacheJson(String cacheJson) {
		_cacheJson = cacheJson;
	}

	public Boolean getIsLastProcessSuccess() {
		return _isLastProcessSuccess;
	}

	public void setIsLastProcessSuccess(Boolean isLastProcessSuccess) {
		_isLastProcessSuccess = isLastProcessSuccess;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public Date getProcessedDate() {
		return _processedDate;
	}

	public void setProcessedDate(Date processedDate) {
		_processedDate = processedDate;
	}

	private long _cacheId;
	private long _codeCache;
	private String _description;
	private String _cacheJson;
	private Boolean _isLastProcessSuccess;
	private Date _modifiedDate;
	private Date _processedDate;

}