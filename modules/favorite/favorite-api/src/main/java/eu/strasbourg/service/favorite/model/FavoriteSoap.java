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

package eu.strasbourg.service.favorite.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.favorite.service.http.FavoriteServiceSoap}.
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.favorite.service.http.FavoriteServiceSoap
 * @generated
 */
@ProviderType
public class FavoriteSoap implements Serializable {
	public static FavoriteSoap toSoapModel(Favorite model) {
		FavoriteSoap soapModel = new FavoriteSoap();

		soapModel.setFavoriteId(model.getFavoriteId());
		soapModel.setPublikUserId(model.getPublikUserId());
		soapModel.setTitle(model.getTitle());
		soapModel.setUrl(model.getUrl());
		soapModel.setTypeId(model.getTypeId());
		soapModel.setEntityId(model.getEntityId());
		soapModel.setEntityGroupId(model.getEntityGroupId());
		soapModel.setOnDashboardDate(model.getOnDashboardDate());

		return soapModel;
	}

	public static FavoriteSoap[] toSoapModels(Favorite[] models) {
		FavoriteSoap[] soapModels = new FavoriteSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FavoriteSoap[][] toSoapModels(Favorite[][] models) {
		FavoriteSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FavoriteSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FavoriteSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FavoriteSoap[] toSoapModels(List<Favorite> models) {
		List<FavoriteSoap> soapModels = new ArrayList<FavoriteSoap>(models.size());

		for (Favorite model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FavoriteSoap[soapModels.size()]);
	}

	public FavoriteSoap() {
	}

	public long getPrimaryKey() {
		return _favoriteId;
	}

	public void setPrimaryKey(long pk) {
		setFavoriteId(pk);
	}

	public long getFavoriteId() {
		return _favoriteId;
	}

	public void setFavoriteId(long favoriteId) {
		_favoriteId = favoriteId;
	}

	public String getPublikUserId() {
		return _publikUserId;
	}

	public void setPublikUserId(String publikUserId) {
		_publikUserId = publikUserId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public long getTypeId() {
		return _typeId;
	}

	public void setTypeId(long typeId) {
		_typeId = typeId;
	}

	public long getEntityId() {
		return _entityId;
	}

	public void setEntityId(long entityId) {
		_entityId = entityId;
	}

	public long getEntityGroupId() {
		return _entityGroupId;
	}

	public void setEntityGroupId(long entityGroupId) {
		_entityGroupId = entityGroupId;
	}

	public Date getOnDashboardDate() {
		return _onDashboardDate;
	}

	public void setOnDashboardDate(Date onDashboardDate) {
		_onDashboardDate = onDashboardDate;
	}

	private long _favoriteId;
	private String _publikUserId;
	private String _title;
	private String _url;
	private long _typeId;
	private long _entityId;
	private long _entityGroupId;
	private Date _onDashboardDate;
}