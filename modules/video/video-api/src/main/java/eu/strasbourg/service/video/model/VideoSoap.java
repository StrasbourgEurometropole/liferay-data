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

package eu.strasbourg.service.video.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.video.service.http.VideoServiceSoap}.
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.video.service.http.VideoServiceSoap
 * @generated
 */
@ProviderType
public class VideoSoap implements Serializable {
	public static VideoSoap toSoapModel(Video model) {
		VideoSoap soapModel = new VideoSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setVideoId(model.getVideoId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLastPublishDate(model.getLastPublishDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setCopyright(model.getCopyright());
		soapModel.setOrigin(model.getOrigin());
		soapModel.setSource(model.getSource());
		soapModel.setImageId(model.getImageId());
		soapModel.setTranscriptionFileId(model.getTranscriptionFileId());

		return soapModel;
	}

	public static VideoSoap[] toSoapModels(Video[] models) {
		VideoSoap[] soapModels = new VideoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VideoSoap[][] toSoapModels(Video[][] models) {
		VideoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VideoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VideoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VideoSoap[] toSoapModels(List<Video> models) {
		List<VideoSoap> soapModels = new ArrayList<VideoSoap>(models.size());

		for (Video model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VideoSoap[soapModels.size()]);
	}

	public VideoSoap() {
	}

	public long getPrimaryKey() {
		return _videoId;
	}

	public void setPrimaryKey(long pk) {
		setVideoId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getVideoId() {
		return _videoId;
	}

	public void setVideoId(long videoId) {
		_videoId = videoId;
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

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
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

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getCopyright() {
		return _copyright;
	}

	public void setCopyright(String copyright) {
		_copyright = copyright;
	}

	public String getOrigin() {
		return _origin;
	}

	public void setOrigin(String origin) {
		_origin = origin;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public Long getImageId() {
		return _imageId;
	}

	public void setImageId(Long imageId) {
		_imageId = imageId;
	}

	public Long getTranscriptionFileId() {
		return _transcriptionFileId;
	}

	public void setTranscriptionFileId(Long transcriptionFileId) {
		_transcriptionFileId = transcriptionFileId;
	}

	private String _uuid;
	private long _videoId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private Date _lastPublishDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _title;
	private String _description;
	private String _copyright;
	private String _origin;
	private String _source;
	private Long _imageId;
	private Long _transcriptionFileId;
}