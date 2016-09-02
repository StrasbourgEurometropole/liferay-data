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

package eu.strasbourg.service.artwork.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.artwork.service.http.ArtworkCollectionServiceSoap}.
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.artwork.service.http.ArtworkCollectionServiceSoap
 * @generated
 */
@ProviderType
public class ArtworkCollectionSoap implements Serializable {
	public static ArtworkCollectionSoap toSoapModel(ArtworkCollection model) {
		ArtworkCollectionSoap soapModel = new ArtworkCollectionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCollectionId(model.getCollectionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setContributors(model.getContributors());
		soapModel.setStatus(model.getStatus());
		soapModel.setImageId(model.getImageId());

		return soapModel;
	}

	public static ArtworkCollectionSoap[] toSoapModels(
		ArtworkCollection[] models) {
		ArtworkCollectionSoap[] soapModels = new ArtworkCollectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ArtworkCollectionSoap[][] toSoapModels(
		ArtworkCollection[][] models) {
		ArtworkCollectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ArtworkCollectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ArtworkCollectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ArtworkCollectionSoap[] toSoapModels(
		List<ArtworkCollection> models) {
		List<ArtworkCollectionSoap> soapModels = new ArrayList<ArtworkCollectionSoap>(models.size());

		for (ArtworkCollection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ArtworkCollectionSoap[soapModels.size()]);
	}

	public ArtworkCollectionSoap() {
	}

	public long getPrimaryKey() {
		return _collectionId;
	}

	public void setPrimaryKey(long pk) {
		setCollectionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCollectionId() {
		return _collectionId;
	}

	public void setCollectionId(long collectionId) {
		_collectionId = collectionId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getContributors() {
		return _contributors;
	}

	public void setContributors(String contributors) {
		_contributors = contributors;
	}

	public boolean getStatus() {
		return _status;
	}

	public boolean isStatus() {
		return _status;
	}

	public void setStatus(boolean status) {
		_status = status;
	}

	public Long getImageId() {
		return _imageId;
	}

	public void setImageId(Long imageId) {
		_imageId = imageId;
	}

	private String _uuid;
	private long _collectionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _description;
	private String _contributors;
	private boolean _status;
	private Long _imageId;
}