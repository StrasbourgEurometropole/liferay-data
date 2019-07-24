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

import eu.strasbourg.service.activity.model.AssociationActivity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AssociationActivity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssociationActivity
 * @generated
 */
@ProviderType
public class AssociationActivityCacheModel implements CacheModel<AssociationActivity>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssociationActivityCacheModel)) {
			return false;
		}

		AssociationActivityCacheModel associationActivityCacheModel = (AssociationActivityCacheModel)obj;

		if (associationActivityId == associationActivityCacheModel.associationActivityId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, associationActivityId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", associationActivityId=");
		sb.append(associationActivityId);
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
	public AssociationActivity toEntityModel() {
		AssociationActivityImpl associationActivityImpl = new AssociationActivityImpl();

		if (uuid == null) {
			associationActivityImpl.setUuid(StringPool.BLANK);
		}
		else {
			associationActivityImpl.setUuid(uuid);
		}

		associationActivityImpl.setAssociationActivityId(associationActivityId);
		associationActivityImpl.setGroupId(groupId);
		associationActivityImpl.setCompanyId(companyId);
		associationActivityImpl.setUserId(userId);

		if (userName == null) {
			associationActivityImpl.setUserName(StringPool.BLANK);
		}
		else {
			associationActivityImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			associationActivityImpl.setCreateDate(null);
		}
		else {
			associationActivityImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			associationActivityImpl.setModifiedDate(null);
		}
		else {
			associationActivityImpl.setModifiedDate(new Date(modifiedDate));
		}

		associationActivityImpl.setStatus(status);
		associationActivityImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			associationActivityImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			associationActivityImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			associationActivityImpl.setStatusDate(null);
		}
		else {
			associationActivityImpl.setStatusDate(new Date(statusDate));
		}

		associationActivityImpl.setAssociationId(associationId);

		associationActivityImpl.resetOriginalValues();

		return associationActivityImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		associationActivityId = objectInput.readLong();

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

		objectOutput.writeLong(associationActivityId);

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
	public long associationActivityId;
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