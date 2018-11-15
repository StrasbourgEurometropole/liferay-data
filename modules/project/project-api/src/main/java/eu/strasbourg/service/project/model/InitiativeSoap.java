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

package eu.strasbourg.service.project.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.project.service.http.InitiativeServiceSoap}.
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.http.InitiativeServiceSoap
 * @generated
 */
@ProviderType
public class InitiativeSoap implements Serializable {
	public static InitiativeSoap toSoapModel(Initiative model) {
		InitiativeSoap soapModel = new InitiativeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setInitiativeId(model.getInitiativeId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setAuthor(model.getAuthor());
		soapModel.setDescription(model.getDescription());
		soapModel.setVideoUrl(model.getVideoUrl());
		soapModel.setExternalImageURL(model.getExternalImageURL());
		soapModel.setExternalImageCopyright(model.getExternalImageCopyright());
		soapModel.setMediaChoice(model.getMediaChoice());
		soapModel.setAssetEntryId(model.getAssetEntryId());
		soapModel.setPublikId(model.getPublikId());
		soapModel.setImageId(model.getImageId());
		soapModel.setFilesIds(model.getFilesIds());
		soapModel.setConsultationPlacesText(model.getConsultationPlacesText());
		soapModel.setConsultationPlacesBody(model.getConsultationPlacesBody());
		soapModel.setPublicationDate(model.getPublicationDate());

		return soapModel;
	}

	public static InitiativeSoap[] toSoapModels(Initiative[] models) {
		InitiativeSoap[] soapModels = new InitiativeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static InitiativeSoap[][] toSoapModels(Initiative[][] models) {
		InitiativeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new InitiativeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new InitiativeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static InitiativeSoap[] toSoapModels(List<Initiative> models) {
		List<InitiativeSoap> soapModels = new ArrayList<InitiativeSoap>(models.size());

		for (Initiative model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new InitiativeSoap[soapModels.size()]);
	}

	public InitiativeSoap() {
	}

	public long getPrimaryKey() {
		return _initiativeId;
	}

	public void setPrimaryKey(long pk) {
		setInitiativeId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getInitiativeId() {
		return _initiativeId;
	}

	public void setInitiativeId(long initiativeId) {
		_initiativeId = initiativeId;
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

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getAuthor() {
		return _author;
	}

	public void setAuthor(String author) {
		_author = author;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getVideoUrl() {
		return _videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		_videoUrl = videoUrl;
	}

	public String getExternalImageURL() {
		return _externalImageURL;
	}

	public void setExternalImageURL(String externalImageURL) {
		_externalImageURL = externalImageURL;
	}

	public String getExternalImageCopyright() {
		return _externalImageCopyright;
	}

	public void setExternalImageCopyright(String externalImageCopyright) {
		_externalImageCopyright = externalImageCopyright;
	}

	public boolean getMediaChoice() {
		return _mediaChoice;
	}

	public boolean isMediaChoice() {
		return _mediaChoice;
	}

	public void setMediaChoice(boolean mediaChoice) {
		_mediaChoice = mediaChoice;
	}

	public long getAssetEntryId() {
		return _assetEntryId;
	}

	public void setAssetEntryId(long assetEntryId) {
		_assetEntryId = assetEntryId;
	}

	public String getPublikId() {
		return _publikId;
	}

	public void setPublikId(String publikId) {
		_publikId = publikId;
	}

	public long getImageId() {
		return _imageId;
	}

	public void setImageId(long imageId) {
		_imageId = imageId;
	}

	public String getFilesIds() {
		return _filesIds;
	}

	public void setFilesIds(String filesIds) {
		_filesIds = filesIds;
	}

	public String getConsultationPlacesText() {
		return _consultationPlacesText;
	}

	public void setConsultationPlacesText(String consultationPlacesText) {
		_consultationPlacesText = consultationPlacesText;
	}

	public String getConsultationPlacesBody() {
		return _consultationPlacesBody;
	}

	public void setConsultationPlacesBody(String consultationPlacesBody) {
		_consultationPlacesBody = consultationPlacesBody;
	}

	public Date getPublicationDate() {
		return _publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		_publicationDate = publicationDate;
	}

	private String _uuid;
	private long _initiativeId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _title;
	private String _author;
	private String _description;
	private String _videoUrl;
	private String _externalImageURL;
	private String _externalImageCopyright;
	private boolean _mediaChoice;
	private long _assetEntryId;
	private String _publikId;
	private long _imageId;
	private String _filesIds;
	private String _consultationPlacesText;
	private String _consultationPlacesBody;
	private Date _publicationDate;
}