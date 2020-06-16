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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.council.model.Official;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Official in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Official
 * @generated
 */
@ProviderType
public class OfficialCacheModel implements CacheModel<Official>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfficialCacheModel)) {
			return false;
		}

		OfficialCacheModel officialCacheModel = (OfficialCacheModel)obj;

		if (officialId == officialCacheModel.officialId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, officialId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", officialId=");
		sb.append(officialId);
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
		sb.append(", email=");
		sb.append(email);
		sb.append(", firstname=");
		sb.append(firstname);
		sb.append(", lastname=");
		sb.append(lastname);
		sb.append(", isActive=");
		sb.append(isActive);
		sb.append(", lastActivity=");
		sb.append(lastActivity);
		sb.append(", lastSignInDeviceInfo=");
		sb.append(lastSignInDeviceInfo);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Official toEntityModel() {
		OfficialImpl officialImpl = new OfficialImpl();

		if (uuid == null) {
			officialImpl.setUuid(StringPool.BLANK);
		}
		else {
			officialImpl.setUuid(uuid);
		}

		officialImpl.setOfficialId(officialId);
		officialImpl.setGroupId(groupId);
		officialImpl.setCompanyId(companyId);
		officialImpl.setUserId(userId);

		if (userName == null) {
			officialImpl.setUserName(StringPool.BLANK);
		}
		else {
			officialImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			officialImpl.setCreateDate(null);
		}
		else {
			officialImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			officialImpl.setModifiedDate(null);
		}
		else {
			officialImpl.setModifiedDate(new Date(modifiedDate));
		}

		officialImpl.setStatus(status);
		officialImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			officialImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			officialImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			officialImpl.setStatusDate(null);
		}
		else {
			officialImpl.setStatusDate(new Date(statusDate));
		}

		if (email == null) {
			officialImpl.setEmail(StringPool.BLANK);
		}
		else {
			officialImpl.setEmail(email);
		}

		if (firstname == null) {
			officialImpl.setFirstname(StringPool.BLANK);
		}
		else {
			officialImpl.setFirstname(firstname);
		}

		if (lastname == null) {
			officialImpl.setLastname(StringPool.BLANK);
		}
		else {
			officialImpl.setLastname(lastname);
		}

		officialImpl.setIsActive(isActive);

		if (lastActivity == Long.MIN_VALUE) {
			officialImpl.setLastActivity(null);
		}
		else {
			officialImpl.setLastActivity(new Date(lastActivity));
		}

		if (lastSignInDeviceInfo == null) {
			officialImpl.setLastSignInDeviceInfo(StringPool.BLANK);
		}
		else {
			officialImpl.setLastSignInDeviceInfo(lastSignInDeviceInfo);
		}

		officialImpl.resetOriginalValues();

		return officialImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		officialId = objectInput.readLong();

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
		email = objectInput.readUTF();
		firstname = objectInput.readUTF();
		lastname = objectInput.readUTF();

		isActive = objectInput.readBoolean();
		lastActivity = objectInput.readLong();
		lastSignInDeviceInfo = objectInput.readUTF();
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

		objectOutput.writeLong(officialId);

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

		if (email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (firstname == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(firstname);
		}

		if (lastname == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lastname);
		}

		objectOutput.writeBoolean(isActive);
		objectOutput.writeLong(lastActivity);

		if (lastSignInDeviceInfo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lastSignInDeviceInfo);
		}
	}

	public String uuid;
	public long officialId;
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
	public String email;
	public String firstname;
	public String lastname;
	public boolean isActive;
	public long lastActivity;
	public String lastSignInDeviceInfo;
}