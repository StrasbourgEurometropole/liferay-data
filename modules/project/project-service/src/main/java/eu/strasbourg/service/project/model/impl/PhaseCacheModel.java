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

import eu.strasbourg.service.project.model.Phase;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Phase in entity cache.
 *
 * @author Cedric Henry
 * @see Phase
 * @generated
 */
@ProviderType
public class PhaseCacheModel implements CacheModel<Phase>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PhaseCacheModel)) {
			return false;
		}

		PhaseCacheModel phaseCacheModel = (PhaseCacheModel)obj;

		if (PhaseId == phaseCacheModel.PhaseId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, PhaseId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", PhaseId=");
		sb.append(PhaseId);
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
		sb.append(", description=");
		sb.append(description);
		sb.append(", numberOfVote=");
		sb.append(numberOfVote);
		sb.append(", isActive=");
		sb.append(isActive);
		sb.append(", beginDate=");
		sb.append(beginDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", publikId=");
		sb.append(publikId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Phase toEntityModel() {
		PhaseImpl phaseImpl = new PhaseImpl();

		if (uuid == null) {
			phaseImpl.setUuid(StringPool.BLANK);
		}
		else {
			phaseImpl.setUuid(uuid);
		}

		phaseImpl.setPhaseId(PhaseId);
		phaseImpl.setGroupId(groupId);
		phaseImpl.setCompanyId(companyId);
		phaseImpl.setUserId(userId);

		if (userName == null) {
			phaseImpl.setUserName(StringPool.BLANK);
		}
		else {
			phaseImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			phaseImpl.setCreateDate(null);
		}
		else {
			phaseImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			phaseImpl.setModifiedDate(null);
		}
		else {
			phaseImpl.setModifiedDate(new Date(modifiedDate));
		}

		phaseImpl.setStatus(status);
		phaseImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			phaseImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			phaseImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			phaseImpl.setStatusDate(null);
		}
		else {
			phaseImpl.setStatusDate(new Date(statusDate));
		}

		if (name == null) {
			phaseImpl.setName(StringPool.BLANK);
		}
		else {
			phaseImpl.setName(name);
		}

		if (description == null) {
			phaseImpl.setDescription(StringPool.BLANK);
		}
		else {
			phaseImpl.setDescription(description);
		}

		phaseImpl.setNumberOfVote(numberOfVote);
		phaseImpl.setIsActive(isActive);

		if (beginDate == Long.MIN_VALUE) {
			phaseImpl.setBeginDate(null);
		}
		else {
			phaseImpl.setBeginDate(new Date(beginDate));
		}

		if (endDate == Long.MIN_VALUE) {
			phaseImpl.setEndDate(null);
		}
		else {
			phaseImpl.setEndDate(new Date(endDate));
		}

		if (publikId == null) {
			phaseImpl.setPublikId(StringPool.BLANK);
		}
		else {
			phaseImpl.setPublikId(publikId);
		}

		phaseImpl.resetOriginalValues();

		return phaseImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		PhaseId = objectInput.readLong();

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
		description = objectInput.readUTF();

		numberOfVote = objectInput.readLong();

		isActive = objectInput.readBoolean();
		beginDate = objectInput.readLong();
		endDate = objectInput.readLong();
		publikId = objectInput.readUTF();
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

		objectOutput.writeLong(PhaseId);

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

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(numberOfVote);

		objectOutput.writeBoolean(isActive);
		objectOutput.writeLong(beginDate);
		objectOutput.writeLong(endDate);

		if (publikId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publikId);
		}
	}

	public String uuid;
	public long PhaseId;
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
	public String description;
	public long numberOfVote;
	public boolean isActive;
	public long beginDate;
	public long endDate;
	public String publikId;
}