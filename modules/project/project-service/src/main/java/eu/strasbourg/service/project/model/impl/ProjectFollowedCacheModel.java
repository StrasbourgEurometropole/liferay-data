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

package eu.strasbourg.service.project.model.impl;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.project.model.ProjectFollowed;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProjectFollowed in entity cache.
 *
 * @author Cedric Henry
 * @generated
 */
public class ProjectFollowedCacheModel
	implements CacheModel<ProjectFollowed>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProjectFollowedCacheModel)) {
			return false;
		}

		ProjectFollowedCacheModel projectFollowedCacheModel =
			(ProjectFollowedCacheModel)object;

		if (projectFollowedId == projectFollowedCacheModel.projectFollowedId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, projectFollowedId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{projectFollowedId=");
		sb.append(projectFollowedId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", publikUserId=");
		sb.append(publikUserId);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProjectFollowed toEntityModel() {
		ProjectFollowedImpl projectFollowedImpl = new ProjectFollowedImpl();

		projectFollowedImpl.setProjectFollowedId(projectFollowedId);

		if (createDate == Long.MIN_VALUE) {
			projectFollowedImpl.setCreateDate(null);
		}
		else {
			projectFollowedImpl.setCreateDate(new Date(createDate));
		}

		if (publikUserId == null) {
			projectFollowedImpl.setPublikUserId("");
		}
		else {
			projectFollowedImpl.setPublikUserId(publikUserId);
		}

		projectFollowedImpl.setProjectId(projectId);
		projectFollowedImpl.setGroupId(groupId);

		projectFollowedImpl.resetOriginalValues();

		return projectFollowedImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		projectFollowedId = objectInput.readLong();
		createDate = objectInput.readLong();
		publikUserId = objectInput.readUTF();

		projectId = objectInput.readLong();

		groupId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(projectFollowedId);
		objectOutput.writeLong(createDate);

		if (publikUserId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(publikUserId);
		}

		objectOutput.writeLong(projectId);

		objectOutput.writeLong(groupId);
	}

	public long projectFollowedId;
	public long createDate;
	public String publikUserId;
	public long projectId;
	public long groupId;

}