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
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.project.service.http.ProjectFollowedServiceSoap}.
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.http.ProjectFollowedServiceSoap
 * @generated
 */
@ProviderType
public class ProjectFollowedSoap implements Serializable {
	public static ProjectFollowedSoap toSoapModel(ProjectFollowed model) {
		ProjectFollowedSoap soapModel = new ProjectFollowedSoap();

		soapModel.setProjectFollowedId(model.getProjectFollowedId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setPublikUserId(model.getPublikUserId());
		soapModel.setProjectId(model.getProjectId());
		soapModel.setGroupId(model.getGroupId());

		return soapModel;
	}

	public static ProjectFollowedSoap[] toSoapModels(ProjectFollowed[] models) {
		ProjectFollowedSoap[] soapModels = new ProjectFollowedSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProjectFollowedSoap[][] toSoapModels(
		ProjectFollowed[][] models) {
		ProjectFollowedSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProjectFollowedSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProjectFollowedSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProjectFollowedSoap[] toSoapModels(
		List<ProjectFollowed> models) {
		List<ProjectFollowedSoap> soapModels = new ArrayList<ProjectFollowedSoap>(models.size());

		for (ProjectFollowed model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProjectFollowedSoap[soapModels.size()]);
	}

	public ProjectFollowedSoap() {
	}

	public long getPrimaryKey() {
		return _projectFollowedId;
	}

	public void setPrimaryKey(long pk) {
		setProjectFollowedId(pk);
	}

	public long getProjectFollowedId() {
		return _projectFollowedId;
	}

	public void setProjectFollowedId(long projectFollowedId) {
		_projectFollowedId = projectFollowedId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getPublikUserId() {
		return _publikUserId;
	}

	public void setPublikUserId(String publikUserId) {
		_publikUserId = publikUserId;
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

	private long _projectFollowedId;
	private Date _createDate;
	private String _publikUserId;
	private long _projectId;
	private long _groupId;
}