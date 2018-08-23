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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ProjectFollowed}.
 * </p>
 *
 * @author Cedric Henry
 * @see ProjectFollowed
 * @generated
 */
@ProviderType
public class ProjectFollowedWrapper implements ProjectFollowed,
	ModelWrapper<ProjectFollowed> {
	public ProjectFollowedWrapper(ProjectFollowed projectFollowed) {
		_projectFollowed = projectFollowed;
	}

	@Override
	public Class<?> getModelClass() {
		return ProjectFollowed.class;
	}

	@Override
	public String getModelClassName() {
		return ProjectFollowed.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("projectFollowedId", getProjectFollowedId());
		attributes.put("createDate", getCreateDate());
		attributes.put("publikUserId", getPublikUserId());
		attributes.put("projectId", getProjectId());
		attributes.put("groupId", getGroupId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long projectFollowedId = (Long)attributes.get("projectFollowedId");

		if (projectFollowedId != null) {
			setProjectFollowedId(projectFollowedId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String publikUserId = (String)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _projectFollowed.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _projectFollowed.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _projectFollowed.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _projectFollowed.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.project.model.ProjectFollowed> toCacheModel() {
		return _projectFollowed.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.project.model.ProjectFollowed toEscapedModel() {
		return new ProjectFollowedWrapper(_projectFollowed.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.project.model.ProjectFollowed toUnescapedModel() {
		return new ProjectFollowedWrapper(_projectFollowed.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.project.model.ProjectFollowed projectFollowed) {
		return _projectFollowed.compareTo(projectFollowed);
	}

	@Override
	public int hashCode() {
		return _projectFollowed.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _projectFollowed.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectFollowedWrapper((ProjectFollowed)_projectFollowed.clone());
	}

	/**
	* Returns the publik user ID of this project followed.
	*
	* @return the publik user ID of this project followed
	*/
	@Override
	public java.lang.String getPublikUserId() {
		return _projectFollowed.getPublikUserId();
	}

	@Override
	public java.lang.String toString() {
		return _projectFollowed.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _projectFollowed.toXmlString();
	}

	/**
	* Returns the create date of this project followed.
	*
	* @return the create date of this project followed
	*/
	@Override
	public Date getCreateDate() {
		return _projectFollowed.getCreateDate();
	}

	/**
	* Returns the group ID of this project followed.
	*
	* @return the group ID of this project followed
	*/
	@Override
	public long getGroupId() {
		return _projectFollowed.getGroupId();
	}

	/**
	* Returns the primary key of this project followed.
	*
	* @return the primary key of this project followed
	*/
	@Override
	public long getPrimaryKey() {
		return _projectFollowed.getPrimaryKey();
	}

	/**
	* Returns the project followed ID of this project followed.
	*
	* @return the project followed ID of this project followed
	*/
	@Override
	public long getProjectFollowedId() {
		return _projectFollowed.getProjectFollowedId();
	}

	/**
	* Returns the project ID of this project followed.
	*
	* @return the project ID of this project followed
	*/
	@Override
	public long getProjectId() {
		return _projectFollowed.getProjectId();
	}

	@Override
	public void persist() {
		_projectFollowed.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_projectFollowed.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this project followed.
	*
	* @param createDate the create date of this project followed
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_projectFollowed.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_projectFollowed.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_projectFollowed.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_projectFollowed.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this project followed.
	*
	* @param groupId the group ID of this project followed
	*/
	@Override
	public void setGroupId(long groupId) {
		_projectFollowed.setGroupId(groupId);
	}

	@Override
	public void setNew(boolean n) {
		_projectFollowed.setNew(n);
	}

	/**
	* Sets the primary key of this project followed.
	*
	* @param primaryKey the primary key of this project followed
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_projectFollowed.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_projectFollowed.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the project followed ID of this project followed.
	*
	* @param projectFollowedId the project followed ID of this project followed
	*/
	@Override
	public void setProjectFollowedId(long projectFollowedId) {
		_projectFollowed.setProjectFollowedId(projectFollowedId);
	}

	/**
	* Sets the project ID of this project followed.
	*
	* @param projectId the project ID of this project followed
	*/
	@Override
	public void setProjectId(long projectId) {
		_projectFollowed.setProjectId(projectId);
	}

	/**
	* Sets the publik user ID of this project followed.
	*
	* @param publikUserId the publik user ID of this project followed
	*/
	@Override
	public void setPublikUserId(java.lang.String publikUserId) {
		_projectFollowed.setPublikUserId(publikUserId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectFollowedWrapper)) {
			return false;
		}

		ProjectFollowedWrapper projectFollowedWrapper = (ProjectFollowedWrapper)obj;

		if (Objects.equals(_projectFollowed,
					projectFollowedWrapper._projectFollowed)) {
			return true;
		}

		return false;
	}

	@Override
	public ProjectFollowed getWrappedModel() {
		return _projectFollowed;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _projectFollowed.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _projectFollowed.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_projectFollowed.resetOriginalValues();
	}

	private final ProjectFollowed _projectFollowed;
}