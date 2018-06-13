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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.project.model.Project;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Project in entity cache.
 *
 * @author Cedric Henry
 * @see Project
 * @generated
 */
@ProviderType
public class ProjectCacheModel implements CacheModel<Project>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectCacheModel)) {
			return false;
		}

		ProjectCacheModel projectCacheModel = (ProjectCacheModel)obj;

		if (projectId == projectCacheModel.projectId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, projectId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(53);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", title=");
		sb.append(title);
		sb.append(", externalImageURL=");
		sb.append(externalImageURL);
		sb.append(", externalImageCopyright=");
		sb.append(externalImageCopyright);
		sb.append(", description=");
		sb.append(description);
		sb.append(", detailURL=");
		sb.append(detailURL);
		sb.append(", budget=");
		sb.append(budget);
		sb.append(", label=");
		sb.append(label);
		sb.append(", duration=");
		sb.append(duration);
		sb.append(", partners=");
		sb.append(partners);
		sb.append(", contactName=");
		sb.append(contactName);
		sb.append(", contactLine1=");
		sb.append(contactLine1);
		sb.append(", contactLine2=");
		sb.append(contactLine2);
		sb.append(", contactPhoneNumber=");
		sb.append(contactPhoneNumber);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Project toEntityModel() {
		ProjectImpl projectImpl = new ProjectImpl();

		if (uuid == null) {
			projectImpl.setUuid(StringPool.BLANK);
		}
		else {
			projectImpl.setUuid(uuid);
		}

		projectImpl.setProjectId(projectId);
		projectImpl.setGroupId(groupId);
		projectImpl.setCompanyId(companyId);
		projectImpl.setUserId(userId);

		if (userName == null) {
			projectImpl.setUserName(StringPool.BLANK);
		}
		else {
			projectImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			projectImpl.setCreateDate(null);
		}
		else {
			projectImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			projectImpl.setModifiedDate(null);
		}
		else {
			projectImpl.setModifiedDate(new Date(modifiedDate));
		}

		projectImpl.setStatus(status);
		projectImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			projectImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			projectImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			projectImpl.setStatusDate(null);
		}
		else {
			projectImpl.setStatusDate(new Date(statusDate));
		}

		if (title == null) {
			projectImpl.setTitle(StringPool.BLANK);
		}
		else {
			projectImpl.setTitle(title);
		}

		if (externalImageURL == null) {
			projectImpl.setExternalImageURL(StringPool.BLANK);
		}
		else {
			projectImpl.setExternalImageURL(externalImageURL);
		}

		if (externalImageCopyright == null) {
			projectImpl.setExternalImageCopyright(StringPool.BLANK);
		}
		else {
			projectImpl.setExternalImageCopyright(externalImageCopyright);
		}

		if (description == null) {
			projectImpl.setDescription(StringPool.BLANK);
		}
		else {
			projectImpl.setDescription(description);
		}

		if (detailURL == null) {
			projectImpl.setDetailURL(StringPool.BLANK);
		}
		else {
			projectImpl.setDetailURL(detailURL);
		}

		if (budget == null) {
			projectImpl.setBudget(StringPool.BLANK);
		}
		else {
			projectImpl.setBudget(budget);
		}

		if (label == null) {
			projectImpl.setLabel(StringPool.BLANK);
		}
		else {
			projectImpl.setLabel(label);
		}

		if (duration == null) {
			projectImpl.setDuration(StringPool.BLANK);
		}
		else {
			projectImpl.setDuration(duration);
		}

		if (partners == null) {
			projectImpl.setPartners(StringPool.BLANK);
		}
		else {
			projectImpl.setPartners(partners);
		}

		if (contactName == null) {
			projectImpl.setContactName(StringPool.BLANK);
		}
		else {
			projectImpl.setContactName(contactName);
		}

		if (contactLine1 == null) {
			projectImpl.setContactLine1(StringPool.BLANK);
		}
		else {
			projectImpl.setContactLine1(contactLine1);
		}

		if (contactLine2 == null) {
			projectImpl.setContactLine2(StringPool.BLANK);
		}
		else {
			projectImpl.setContactLine2(contactLine2);
		}

		if (contactPhoneNumber == null) {
			projectImpl.setContactPhoneNumber(StringPool.BLANK);
		}
		else {
			projectImpl.setContactPhoneNumber(contactPhoneNumber);
		}

		projectImpl.setImageId(imageId);

		projectImpl.resetOriginalValues();

		return projectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		projectId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
		title = objectInput.readUTF();
		externalImageURL = objectInput.readUTF();
		externalImageCopyright = objectInput.readUTF();
		description = objectInput.readUTF();
		detailURL = objectInput.readUTF();
		budget = objectInput.readUTF();
		label = objectInput.readUTF();
		duration = objectInput.readUTF();
		partners = objectInput.readUTF();
		contactName = objectInput.readUTF();
		contactLine1 = objectInput.readUTF();
		contactLine2 = objectInput.readUTF();
		contactPhoneNumber = objectInput.readUTF();

		imageId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(projectId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (externalImageURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(externalImageURL);
		}

		if (externalImageCopyright == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(externalImageCopyright);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (detailURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(detailURL);
		}

		if (budget == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(budget);
		}

		if (label == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(label);
		}

		if (duration == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(duration);
		}

		if (partners == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(partners);
		}

		if (contactName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contactName);
		}

		if (contactLine1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contactLine1);
		}

		if (contactLine2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contactLine2);
		}

		if (contactPhoneNumber == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contactPhoneNumber);
		}

		objectOutput.writeLong(imageId);
	}

	public String uuid;
	public long projectId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String title;
	public String externalImageURL;
	public String externalImageCopyright;
	public String description;
	public String detailURL;
	public String budget;
	public String label;
	public String duration;
	public String partners;
	public String contactName;
	public String contactLine1;
	public String contactLine2;
	public String contactPhoneNumber;
	public long imageId;
}