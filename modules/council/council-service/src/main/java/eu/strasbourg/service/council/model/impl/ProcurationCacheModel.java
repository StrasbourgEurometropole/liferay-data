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

import eu.strasbourg.service.council.model.Procuration;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Procuration in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Procuration
 * @generated
 */
@ProviderType
public class ProcurationCacheModel implements CacheModel<Procuration>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcurationCacheModel)) {
			return false;
		}

		ProcurationCacheModel procurationCacheModel = (ProcurationCacheModel)obj;

		if (procurationId == procurationCacheModel.procurationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, procurationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", procurationId=");
		sb.append(procurationId);
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
		sb.append(", officialVotersId=");
		sb.append(officialVotersId);
		sb.append(", officialUnavailableId=");
		sb.append(officialUnavailableId);
		sb.append(", officialProcurationId=");
		sb.append(officialProcurationId);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Procuration toEntityModel() {
		ProcurationImpl procurationImpl = new ProcurationImpl();

		if (uuid == null) {
			procurationImpl.setUuid(StringPool.BLANK);
		}
		else {
			procurationImpl.setUuid(uuid);
		}

		procurationImpl.setProcurationId(procurationId);
		procurationImpl.setGroupId(groupId);
		procurationImpl.setCompanyId(companyId);
		procurationImpl.setUserId(userId);

		if (userName == null) {
			procurationImpl.setUserName(StringPool.BLANK);
		}
		else {
			procurationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			procurationImpl.setCreateDate(null);
		}
		else {
			procurationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			procurationImpl.setModifiedDate(null);
		}
		else {
			procurationImpl.setModifiedDate(new Date(modifiedDate));
		}

		procurationImpl.setStatus(status);
		procurationImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			procurationImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			procurationImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			procurationImpl.setStatusDate(null);
		}
		else {
			procurationImpl.setStatusDate(new Date(statusDate));
		}

		procurationImpl.setOfficialVotersId(officialVotersId);
		procurationImpl.setOfficialUnavailableId(officialUnavailableId);
		procurationImpl.setOfficialProcurationId(officialProcurationId);
		procurationImpl.setSessionId(sessionId);

		procurationImpl.resetOriginalValues();

		return procurationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		procurationId = objectInput.readLong();

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

		officialVotersId = objectInput.readLong();

		officialUnavailableId = objectInput.readLong();

		officialProcurationId = objectInput.readLong();

		sessionId = objectInput.readLong();
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

		objectOutput.writeLong(procurationId);

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

		objectOutput.writeLong(officialVotersId);

		objectOutput.writeLong(officialUnavailableId);

		objectOutput.writeLong(officialProcurationId);

		objectOutput.writeLong(sessionId);
	}

	public String uuid;
	public long procurationId;
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
	public long officialVotersId;
	public long officialUnavailableId;
	public long officialProcurationId;
	public long sessionId;
}