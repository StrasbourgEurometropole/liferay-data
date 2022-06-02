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

package eu.strasbourg.service.council.model.impl;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.council.model.CouncilSession;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CouncilSession in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CouncilSessionCacheModel
	implements CacheModel<CouncilSession>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CouncilSessionCacheModel)) {
			return false;
		}

		CouncilSessionCacheModel councilSessionCacheModel =
			(CouncilSessionCacheModel)object;

		if (councilSessionId == councilSessionCacheModel.councilSessionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, councilSessionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", councilSessionId=");
		sb.append(councilSessionId);
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
		sb.append(", date=");
		sb.append(date);
		sb.append(", lastDelibProcessed=");
		sb.append(lastDelibProcessed);
		sb.append(", officialLeaderId=");
		sb.append(officialLeaderId);
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CouncilSession toEntityModel() {
		CouncilSessionImpl councilSessionImpl = new CouncilSessionImpl();

		if (uuid == null) {
			councilSessionImpl.setUuid("");
		}
		else {
			councilSessionImpl.setUuid(uuid);
		}

		councilSessionImpl.setCouncilSessionId(councilSessionId);
		councilSessionImpl.setGroupId(groupId);
		councilSessionImpl.setCompanyId(companyId);
		councilSessionImpl.setUserId(userId);

		if (userName == null) {
			councilSessionImpl.setUserName("");
		}
		else {
			councilSessionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			councilSessionImpl.setCreateDate(null);
		}
		else {
			councilSessionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			councilSessionImpl.setModifiedDate(null);
		}
		else {
			councilSessionImpl.setModifiedDate(new Date(modifiedDate));
		}

		councilSessionImpl.setStatus(status);
		councilSessionImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			councilSessionImpl.setStatusByUserName("");
		}
		else {
			councilSessionImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			councilSessionImpl.setStatusDate(null);
		}
		else {
			councilSessionImpl.setStatusDate(new Date(statusDate));
		}

		if (title == null) {
			councilSessionImpl.setTitle("");
		}
		else {
			councilSessionImpl.setTitle(title);
		}

		if (date == Long.MIN_VALUE) {
			councilSessionImpl.setDate(null);
		}
		else {
			councilSessionImpl.setDate(new Date(date));
		}

		councilSessionImpl.setLastDelibProcessed(lastDelibProcessed);
		councilSessionImpl.setOfficialLeaderId(officialLeaderId);
		councilSessionImpl.setTypeId(typeId);

		councilSessionImpl.resetOriginalValues();

		return councilSessionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		councilSessionId = objectInput.readLong();

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
		date = objectInput.readLong();

		lastDelibProcessed = objectInput.readLong();

		officialLeaderId = objectInput.readLong();

		typeId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(councilSessionId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeLong(date);

		objectOutput.writeLong(lastDelibProcessed);

		objectOutput.writeLong(officialLeaderId);

		objectOutput.writeLong(typeId);
	}

	public String uuid;
	public long councilSessionId;
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
	public long date;
	public long lastDelibProcessed;
	public long officialLeaderId;
	public long typeId;

}