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
 * @generated
 */
@ProviderType
public class ProcurationCacheModel
	implements CacheModel<Procuration>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcurationCacheModel)) {
			return false;
		}

		ProcurationCacheModel procurationCacheModel =
			(ProcurationCacheModel)obj;

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
		StringBundler sb = new StringBundler(47);

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
		sb.append(", councilSessionId=");
		sb.append(councilSessionId);
		sb.append(", isAbsent=");
		sb.append(isAbsent);
		sb.append(", procurationMode=");
		sb.append(procurationMode);
		sb.append(", isPresential=");
		sb.append(isPresential);
		sb.append(", isAfterVote=");
		sb.append(isAfterVote);
		sb.append(", procurationStartHour=");
		sb.append(procurationStartHour);
		sb.append(", procurationEndHour=");
		sb.append(procurationEndHour);
		sb.append(", procurationStartPoint=");
		sb.append(procurationStartPoint);
		sb.append(", procurationEndPoint=");
		sb.append(procurationEndPoint);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Procuration toEntityModel() {
		ProcurationImpl procurationImpl = new ProcurationImpl();

		if (uuid == null) {
			procurationImpl.setUuid("");
		}
		else {
			procurationImpl.setUuid(uuid);
		}

		procurationImpl.setProcurationId(procurationId);
		procurationImpl.setGroupId(groupId);
		procurationImpl.setCompanyId(companyId);
		procurationImpl.setUserId(userId);

		if (userName == null) {
			procurationImpl.setUserName("");
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
			procurationImpl.setStatusByUserName("");
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
		procurationImpl.setCouncilSessionId(councilSessionId);
		procurationImpl.setIsAbsent(isAbsent);

		if (procurationMode == null) {
			procurationImpl.setProcurationMode("");
		}
		else {
			procurationImpl.setProcurationMode(procurationMode);
		}

		procurationImpl.setIsPresential(isPresential);
		procurationImpl.setIsAfterVote(isAfterVote);

		if (procurationStartHour == Long.MIN_VALUE) {
			procurationImpl.setProcurationStartHour(null);
		}
		else {
			procurationImpl.setProcurationStartHour(
				new Date(procurationStartHour));
		}

		if (procurationEndHour == Long.MIN_VALUE) {
			procurationImpl.setProcurationEndHour(null);
		}
		else {
			procurationImpl.setProcurationEndHour(new Date(procurationEndHour));
		}

		procurationImpl.setProcurationStartPoint(procurationStartPoint);
		procurationImpl.setProcurationEndPoint(procurationEndPoint);

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

		councilSessionId = objectInput.readLong();

		isAbsent = objectInput.readBoolean();
		procurationMode = objectInput.readUTF();

		isPresential = objectInput.readBoolean();

		isAfterVote = objectInput.readBoolean();
		procurationStartHour = objectInput.readLong();
		procurationEndHour = objectInput.readLong();

		procurationStartPoint = objectInput.readInt();

		procurationEndPoint = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(procurationId);

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

		objectOutput.writeLong(officialVotersId);

		objectOutput.writeLong(officialUnavailableId);

		objectOutput.writeLong(councilSessionId);

		objectOutput.writeBoolean(isAbsent);

		if (procurationMode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(procurationMode);
		}

		objectOutput.writeBoolean(isPresential);

		objectOutput.writeBoolean(isAfterVote);
		objectOutput.writeLong(procurationStartHour);
		objectOutput.writeLong(procurationEndHour);

		objectOutput.writeInt(procurationStartPoint);

		objectOutput.writeInt(procurationEndPoint);
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
	public long councilSessionId;
	public boolean isAbsent;
	public String procurationMode;
	public boolean isPresential;
	public boolean isAfterVote;
	public long procurationStartHour;
	public long procurationEndHour;
	public int procurationStartPoint;
	public int procurationEndPoint;

}