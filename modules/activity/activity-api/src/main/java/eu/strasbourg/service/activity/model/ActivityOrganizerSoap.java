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

package eu.strasbourg.service.activity.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.activity.service.http.ActivityOrganizerServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.activity.service.http.ActivityOrganizerServiceSoap
 * @generated
 */
@ProviderType
public class ActivityOrganizerSoap implements Serializable {
	public static ActivityOrganizerSoap toSoapModel(ActivityOrganizer model) {
		ActivityOrganizerSoap soapModel = new ActivityOrganizerSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setActivityOrganizerId(model.getActivityOrganizerId());
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
		soapModel.setName(model.getName());
		soapModel.setPresentation(model.getPresentation());
		soapModel.setAddress(model.getAddress());
		soapModel.setPhone(model.getPhone());
		soapModel.setMail(model.getMail());
		soapModel.setSiteURL(model.getSiteURL());
		soapModel.setImageId(model.getImageId());

		return soapModel;
	}

	public static ActivityOrganizerSoap[] toSoapModels(
		ActivityOrganizer[] models) {
		ActivityOrganizerSoap[] soapModels = new ActivityOrganizerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ActivityOrganizerSoap[][] toSoapModels(
		ActivityOrganizer[][] models) {
		ActivityOrganizerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ActivityOrganizerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ActivityOrganizerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ActivityOrganizerSoap[] toSoapModels(
		List<ActivityOrganizer> models) {
		List<ActivityOrganizerSoap> soapModels = new ArrayList<ActivityOrganizerSoap>(models.size());

		for (ActivityOrganizer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ActivityOrganizerSoap[soapModels.size()]);
	}

	public ActivityOrganizerSoap() {
	}

	public long getPrimaryKey() {
		return _activityOrganizerId;
	}

	public void setPrimaryKey(long pk) {
		setActivityOrganizerId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getActivityOrganizerId() {
		return _activityOrganizerId;
	}

	public void setActivityOrganizerId(long activityOrganizerId) {
		_activityOrganizerId = activityOrganizerId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPresentation() {
		return _presentation;
	}

	public void setPresentation(String presentation) {
		_presentation = presentation;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public String getPhone() {
		return _phone;
	}

	public void setPhone(String phone) {
		_phone = phone;
	}

	public String getMail() {
		return _mail;
	}

	public void setMail(String mail) {
		_mail = mail;
	}

	public String getSiteURL() {
		return _siteURL;
	}

	public void setSiteURL(String siteURL) {
		_siteURL = siteURL;
	}

	public long getImageId() {
		return _imageId;
	}

	public void setImageId(long imageId) {
		_imageId = imageId;
	}

	private String _uuid;
	private long _activityOrganizerId;
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
	private String _name;
	private String _presentation;
	private String _address;
	private String _phone;
	private String _mail;
	private String _siteURL;
	private long _imageId;
}