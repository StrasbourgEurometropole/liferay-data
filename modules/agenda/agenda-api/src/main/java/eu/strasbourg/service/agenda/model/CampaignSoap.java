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

package eu.strasbourg.service.agenda.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author BenjaminBini
 * @generated
 */
@ProviderType
public class CampaignSoap implements Serializable {
	public static CampaignSoap toSoapModel(Campaign model) {
		CampaignSoap soapModel = new CampaignSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCampaignId(model.getCampaignId());
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
		soapModel.setDefaultImageId(model.getDefaultImageId());
		soapModel.setDefaultImageCopyright(model.getDefaultImageCopyright());
		soapModel.setManagersIds(model.getManagersIds());
		soapModel.setExportEnabled(model.getExportEnabled());

		return soapModel;
	}

	public static CampaignSoap[] toSoapModels(Campaign[] models) {
		CampaignSoap[] soapModels = new CampaignSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CampaignSoap[][] toSoapModels(Campaign[][] models) {
		CampaignSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CampaignSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CampaignSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CampaignSoap[] toSoapModels(List<Campaign> models) {
		List<CampaignSoap> soapModels = new ArrayList<CampaignSoap>(models.size());

		for (Campaign model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CampaignSoap[soapModels.size()]);
	}

	public CampaignSoap() {
	}

	public long getPrimaryKey() {
		return _campaignId;
	}

	public void setPrimaryKey(long pk) {
		setCampaignId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCampaignId() {
		return _campaignId;
	}

	public void setCampaignId(long campaignId) {
		_campaignId = campaignId;
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

	public long getDefaultImageId() {
		return _defaultImageId;
	}

	public void setDefaultImageId(long defaultImageId) {
		_defaultImageId = defaultImageId;
	}

	public String getDefaultImageCopyright() {
		return _defaultImageCopyright;
	}

	public void setDefaultImageCopyright(String defaultImageCopyright) {
		_defaultImageCopyright = defaultImageCopyright;
	}

	public String getManagersIds() {
		return _managersIds;
	}

	public void setManagersIds(String managersIds) {
		_managersIds = managersIds;
	}

	public Boolean getExportEnabled() {
		return _exportEnabled;
	}

	public void setExportEnabled(Boolean exportEnabled) {
		_exportEnabled = exportEnabled;
	}

	private String _uuid;
	private long _campaignId;
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
	private long _defaultImageId;
	private String _defaultImageCopyright;
	private String _managersIds;
	private Boolean _exportEnabled;
}