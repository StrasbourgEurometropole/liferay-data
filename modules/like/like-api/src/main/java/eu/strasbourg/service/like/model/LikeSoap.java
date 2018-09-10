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

package eu.strasbourg.service.like.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.like.service.http.LikeServiceSoap}.
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.like.service.http.LikeServiceSoap
 * @generated
 */
@ProviderType
public class LikeSoap implements Serializable {
	public static LikeSoap toSoapModel(Like model) {
		LikeSoap soapModel = new LikeSoap();

		soapModel.setLikeId(model.getLikeId());
		soapModel.setPublikUserId(model.getPublikUserId());
		soapModel.setTitle(model.getTitle());
		soapModel.setIsDislike(model.getIsDislike());
		soapModel.setTypeId(model.getTypeId());
		soapModel.setEntityId(model.getEntityId());
		soapModel.setEntityGroupId(model.getEntityGroupId());

		return soapModel;
	}

	public static LikeSoap[] toSoapModels(Like[] models) {
		LikeSoap[] soapModels = new LikeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LikeSoap[][] toSoapModels(Like[][] models) {
		LikeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LikeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LikeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LikeSoap[] toSoapModels(List<Like> models) {
		List<LikeSoap> soapModels = new ArrayList<LikeSoap>(models.size());

		for (Like model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LikeSoap[soapModels.size()]);
	}

	public LikeSoap() {
	}

	public long getPrimaryKey() {
		return _likeId;
	}

	public void setPrimaryKey(long pk) {
		setLikeId(pk);
	}

	public long getLikeId() {
		return _likeId;
	}

	public void setLikeId(long likeId) {
		_likeId = likeId;
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

	public boolean getIsDislike() {
		return _isDislike;
	}

	public boolean isIsDislike() {
		return _isDislike;
	}

	public void setIsDislike(boolean isDislike) {
		_isDislike = isDislike;
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

	private long _likeId;
	private String _publikUserId;
	private String _title;
	private boolean _isDislike;
	private long _typeId;
	private long _entityId;
	private long _entityGroupId;
}