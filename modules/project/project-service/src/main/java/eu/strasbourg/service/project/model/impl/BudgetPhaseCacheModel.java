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

import eu.strasbourg.service.project.model.BudgetPhase;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BudgetPhase in entity cache.
 *
 * @author Cedric Henry
 * @see BudgetPhase
 * @generated
 */
@ProviderType
public class BudgetPhaseCacheModel implements CacheModel<BudgetPhase>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BudgetPhaseCacheModel)) {
			return false;
		}

		BudgetPhaseCacheModel budgetPhaseCacheModel = (BudgetPhaseCacheModel)obj;

		if (budgetPhaseId == budgetPhaseCacheModel.budgetPhaseId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, budgetPhaseId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", budgetPhaseId=");
		sb.append(budgetPhaseId);
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
		sb.append(", beginVoteDate=");
		sb.append(beginVoteDate);
		sb.append(", endVoteDate=");
		sb.append(endVoteDate);
		sb.append(", publikId=");
		sb.append(publikId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BudgetPhase toEntityModel() {
		BudgetPhaseImpl budgetPhaseImpl = new BudgetPhaseImpl();

		if (uuid == null) {
			budgetPhaseImpl.setUuid(StringPool.BLANK);
		}
		else {
			budgetPhaseImpl.setUuid(uuid);
		}

		budgetPhaseImpl.setBudgetPhaseId(budgetPhaseId);
		budgetPhaseImpl.setGroupId(groupId);
		budgetPhaseImpl.setCompanyId(companyId);
		budgetPhaseImpl.setUserId(userId);

		if (userName == null) {
			budgetPhaseImpl.setUserName(StringPool.BLANK);
		}
		else {
			budgetPhaseImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			budgetPhaseImpl.setCreateDate(null);
		}
		else {
			budgetPhaseImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			budgetPhaseImpl.setModifiedDate(null);
		}
		else {
			budgetPhaseImpl.setModifiedDate(new Date(modifiedDate));
		}

		budgetPhaseImpl.setStatus(status);
		budgetPhaseImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			budgetPhaseImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			budgetPhaseImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			budgetPhaseImpl.setStatusDate(null);
		}
		else {
			budgetPhaseImpl.setStatusDate(new Date(statusDate));
		}

		if (name == null) {
			budgetPhaseImpl.setName(StringPool.BLANK);
		}
		else {
			budgetPhaseImpl.setName(name);
		}

		if (description == null) {
			budgetPhaseImpl.setDescription(StringPool.BLANK);
		}
		else {
			budgetPhaseImpl.setDescription(description);
		}

		budgetPhaseImpl.setNumberOfVote(numberOfVote);
		budgetPhaseImpl.setIsActive(isActive);

		if (beginDate == Long.MIN_VALUE) {
			budgetPhaseImpl.setBeginDate(null);
		}
		else {
			budgetPhaseImpl.setBeginDate(new Date(beginDate));
		}

		if (endDate == Long.MIN_VALUE) {
			budgetPhaseImpl.setEndDate(null);
		}
		else {
			budgetPhaseImpl.setEndDate(new Date(endDate));
		}

		if (beginVoteDate == Long.MIN_VALUE) {
			budgetPhaseImpl.setBeginVoteDate(null);
		}
		else {
			budgetPhaseImpl.setBeginVoteDate(new Date(beginVoteDate));
		}

		if (endVoteDate == Long.MIN_VALUE) {
			budgetPhaseImpl.setEndVoteDate(null);
		}
		else {
			budgetPhaseImpl.setEndVoteDate(new Date(endVoteDate));
		}

		if (publikId == null) {
			budgetPhaseImpl.setPublikId(StringPool.BLANK);
		}
		else {
			budgetPhaseImpl.setPublikId(publikId);
		}

		budgetPhaseImpl.resetOriginalValues();

		return budgetPhaseImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		budgetPhaseId = objectInput.readLong();

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
		beginVoteDate = objectInput.readLong();
		endVoteDate = objectInput.readLong();
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

		objectOutput.writeLong(budgetPhaseId);

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
		objectOutput.writeLong(beginVoteDate);
		objectOutput.writeLong(endVoteDate);

		if (publikId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publikId);
		}
	}

	public String uuid;
	public long budgetPhaseId;
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
	public long beginVoteDate;
	public long endVoteDate;
	public String publikId;
}