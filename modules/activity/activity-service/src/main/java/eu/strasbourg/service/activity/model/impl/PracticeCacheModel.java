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

import eu.strasbourg.service.activity.model.Practice;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Practice in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Practice
 * @generated
 */
@ProviderType
public class PracticeCacheModel implements CacheModel<Practice>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PracticeCacheModel)) {
			return false;
		}

		PracticeCacheModel practiceCacheModel = (PracticeCacheModel)obj;

		if (practiceId == practiceCacheModel.practiceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, practiceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", practiceId=");
		sb.append(practiceId);
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
		sb.append(", associationId=");
		sb.append(associationId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Practice toEntityModel() {
		PracticeImpl practiceImpl = new PracticeImpl();

		if (uuid == null) {
			practiceImpl.setUuid(StringPool.BLANK);
		}
		else {
			practiceImpl.setUuid(uuid);
		}

		practiceImpl.setPracticeId(practiceId);
		practiceImpl.setGroupId(groupId);
		practiceImpl.setCompanyId(companyId);
		practiceImpl.setUserId(userId);

		if (userName == null) {
			practiceImpl.setUserName(StringPool.BLANK);
		}
		else {
			practiceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			practiceImpl.setCreateDate(null);
		}
		else {
			practiceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			practiceImpl.setModifiedDate(null);
		}
		else {
			practiceImpl.setModifiedDate(new Date(modifiedDate));
		}

		practiceImpl.setStatus(status);
		practiceImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			practiceImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			practiceImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			practiceImpl.setStatusDate(null);
		}
		else {
			practiceImpl.setStatusDate(new Date(statusDate));
		}

		practiceImpl.setAssociationId(associationId);

		practiceImpl.resetOriginalValues();

		return practiceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		practiceId = objectInput.readLong();

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

		associationId = objectInput.readLong();
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

		objectOutput.writeLong(practiceId);

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

		objectOutput.writeLong(associationId);
	}

	public String uuid;
	public long practiceId;
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
	public long associationId;
}