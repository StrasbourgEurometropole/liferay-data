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

package eu.strasbourg.service.activity.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.activity.model.ActivityOrganizer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ActivityOrganizer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityOrganizer
 * @generated
 */
@ProviderType
public class ActivityOrganizerCacheModel implements CacheModel<ActivityOrganizer>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ActivityOrganizerCacheModel)) {
			return false;
		}

		ActivityOrganizerCacheModel activityOrganizerCacheModel = (ActivityOrganizerCacheModel)obj;

		if (activityOrganizerId == activityOrganizerCacheModel.activityOrganizerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, activityOrganizerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", activityOrganizerId=");
		sb.append(activityOrganizerId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", contactInformation=");
		sb.append(contactInformation);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ActivityOrganizer toEntityModel() {
		ActivityOrganizerImpl activityOrganizerImpl = new ActivityOrganizerImpl();

		if (uuid == null) {
			activityOrganizerImpl.setUuid(StringPool.BLANK);
		}
		else {
			activityOrganizerImpl.setUuid(uuid);
		}

		activityOrganizerImpl.setActivityOrganizerId(activityOrganizerId);
		activityOrganizerImpl.setGroupId(groupId);
		activityOrganizerImpl.setCompanyId(companyId);
		activityOrganizerImpl.setUserId(userId);

		if (userName == null) {
			activityOrganizerImpl.setUserName(StringPool.BLANK);
		}
		else {
			activityOrganizerImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			activityOrganizerImpl.setCreateDate(null);
		}
		else {
			activityOrganizerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			activityOrganizerImpl.setModifiedDate(null);
		}
		else {
			activityOrganizerImpl.setModifiedDate(new Date(modifiedDate));
		}

		activityOrganizerImpl.setStatus(status);
		activityOrganizerImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			activityOrganizerImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			activityOrganizerImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			activityOrganizerImpl.setStatusDate(null);
		}
		else {
			activityOrganizerImpl.setStatusDate(new Date(statusDate));
		}

		if (name == null) {
			activityOrganizerImpl.setName(StringPool.BLANK);
		}
		else {
			activityOrganizerImpl.setName(name);
		}

		if (contactInformation == null) {
			activityOrganizerImpl.setContactInformation(StringPool.BLANK);
		}
		else {
			activityOrganizerImpl.setContactInformation(contactInformation);
		}

		activityOrganizerImpl.setImageId(imageId);

		activityOrganizerImpl.resetOriginalValues();

		return activityOrganizerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		activityOrganizerId = objectInput.readLong();

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
		name = objectInput.readUTF();
		contactInformation = objectInput.readUTF();

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

		objectOutput.writeLong(activityOrganizerId);

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

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (contactInformation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contactInformation);
		}

		objectOutput.writeLong(imageId);
	}

	public String uuid;
	public long activityOrganizerId;
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
	public String name;
	public String contactInformation;
	public long imageId;
}