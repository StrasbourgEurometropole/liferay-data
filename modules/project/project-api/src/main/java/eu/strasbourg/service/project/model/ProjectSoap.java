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
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.project.service.http.ProjectServiceSoap}.
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.http.ProjectServiceSoap
 * @generated
 */
@ProviderType
public class ProjectSoap implements Serializable {
	public static ProjectSoap toSoapModel(Project model) {
		ProjectSoap soapModel = new ProjectSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setProjectId(model.getProjectId());
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
		soapModel.setExternalImageURL(model.getExternalImageURL());
		soapModel.setExternalImageCopyright(model.getExternalImageCopyright());
		soapModel.setDescription(model.getDescription());
		soapModel.setDetailURL(model.getDetailURL());
		soapModel.setBudget(model.getBudget());
		soapModel.setLabel(model.getLabel());
		soapModel.setDuration(model.getDuration());
		soapModel.setPartners(model.getPartners());
		soapModel.setContactName(model.getContactName());
		soapModel.setContactLine1(model.getContactLine1());
		soapModel.setContactLine2(model.getContactLine2());
		soapModel.setContactPhoneNumber(model.getContactPhoneNumber());
		soapModel.setImageId(model.getImageId());

		return soapModel;
	}

	public static ProjectSoap[] toSoapModels(Project[] models) {
		ProjectSoap[] soapModels = new ProjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProjectSoap[][] toSoapModels(Project[][] models) {
		ProjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProjectSoap[] toSoapModels(List<Project> models) {
		List<ProjectSoap> soapModels = new ArrayList<ProjectSoap>(models.size());

		for (Project model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProjectSoap[soapModels.size()]);
	}

	public ProjectSoap() {
	}

	public long getPrimaryKey() {
		return _projectId;
	}

	public void setPrimaryKey(long pk) {
		setProjectId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getProjectId() {
		return _projectId;
	}

	public void setProjectId(long projectId) {
		_projectId = projectId;
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

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getDetailURL() {
		return _detailURL;
	}

	public void setDetailURL(String detailURL) {
		_detailURL = detailURL;
	}

	public String getBudget() {
		return _budget;
	}

	public void setBudget(String budget) {
		_budget = budget;
	}

	public String getLabel() {
		return _label;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public int getDuration() {
		return _duration;
	}

	public void setDuration(int duration) {
		_duration = duration;
	}

	public String getPartners() {
		return _partners;
	}

	public void setPartners(String partners) {
		_partners = partners;
	}

	public String getContactName() {
		return _contactName;
	}

	public void setContactName(String contactName) {
		_contactName = contactName;
	}

	public String getContactLine1() {
		return _contactLine1;
	}

	public void setContactLine1(String contactLine1) {
		_contactLine1 = contactLine1;
	}

	public String getContactLine2() {
		return _contactLine2;
	}

	public void setContactLine2(String contactLine2) {
		_contactLine2 = contactLine2;
	}

	public String getContactPhoneNumber() {
		return _contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		_contactPhoneNumber = contactPhoneNumber;
	}

	public long getImageId() {
		return _imageId;
	}

	public void setImageId(long imageId) {
		_imageId = imageId;
	}

	private String _uuid;
	private long _projectId;
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
	private String _externalImageURL;
	private String _externalImageCopyright;
	private String _description;
	private String _detailURL;
	private String _budget;
	private String _label;
	private int _duration;
	private String _partners;
	private String _contactName;
	private String _contactLine1;
	private String _contactLine2;
	private String _contactPhoneNumber;
	private long _imageId;
}