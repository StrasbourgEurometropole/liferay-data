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

package eu.strasbourg.service.edition.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.edition.service.http.EditionGalleryServiceSoap}.
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.edition.service.http.EditionGalleryServiceSoap
 * @generated
 */
@ProviderType
public class EditionGallerySoap implements Serializable {
	public static EditionGallerySoap toSoapModel(EditionGallery model) {
		EditionGallerySoap soapModel = new EditionGallerySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setGalleryId(model.getGalleryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setImage(model.getImage());
		soapModel.setPublicationDate(model.getPublicationDate());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static EditionGallerySoap[] toSoapModels(EditionGallery[] models) {
		EditionGallerySoap[] soapModels = new EditionGallerySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EditionGallerySoap[][] toSoapModels(EditionGallery[][] models) {
		EditionGallerySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EditionGallerySoap[models.length][models[0].length];
		}
		else {
			soapModels = new EditionGallerySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EditionGallerySoap[] toSoapModels(List<EditionGallery> models) {
		List<EditionGallerySoap> soapModels = new ArrayList<EditionGallerySoap>(models.size());

		for (EditionGallery model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EditionGallerySoap[soapModels.size()]);
	}

	public EditionGallerySoap() {
	}

	public long getPrimaryKey() {
		return _galleryId;
	}

	public void setPrimaryKey(long pk) {
		setGalleryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getGalleryId() {
		return _galleryId;
	}

	public void setGalleryId(long galleryId) {
		_galleryId = galleryId;
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

	public String getImage() {
		return _image;
	}

	public void setImage(String image) {
		_image = image;
	}

	public Date getPublicationDate() {
		return _publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		_publicationDate = publicationDate;
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

	private String _uuid;
	private long _galleryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _description;
	private String _image;
	private Date _publicationDate;
	private boolean _status;
}