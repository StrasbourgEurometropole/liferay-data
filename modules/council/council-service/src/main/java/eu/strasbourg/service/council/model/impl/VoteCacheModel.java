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

import eu.strasbourg.service.council.model.Vote;
import eu.strasbourg.service.council.service.persistence.VotePK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Vote in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class VoteCacheModel implements CacheModel<Vote>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VoteCacheModel)) {
			return false;
		}

		VoteCacheModel voteCacheModel = (VoteCacheModel)obj;

		if (votePK.equals(voteCacheModel.votePK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, votePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", officialId=");
		sb.append(officialId);
		sb.append(", deliberationId=");
		sb.append(deliberationId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", result=");
		sb.append(result);
		sb.append(", officialProcurationId=");
		sb.append(officialProcurationId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Vote toEntityModel() {
		VoteImpl voteImpl = new VoteImpl();

		if (uuid == null) {
			voteImpl.setUuid("");
		}
		else {
			voteImpl.setUuid(uuid);
		}

		voteImpl.setOfficialId(officialId);
		voteImpl.setDeliberationId(deliberationId);
		voteImpl.setGroupId(groupId);
		voteImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			voteImpl.setCreateDate(null);
		}
		else {
			voteImpl.setCreateDate(new Date(createDate));
		}

		if (result == null) {
			voteImpl.setResult("");
		}
		else {
			voteImpl.setResult(result);
		}

		voteImpl.setOfficialProcurationId(officialProcurationId);

		voteImpl.resetOriginalValues();

		return voteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		officialId = objectInput.readLong();

		deliberationId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		result = objectInput.readUTF();

		officialProcurationId = objectInput.readLong();

		votePK = new VotePK(officialId, deliberationId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(officialId);

		objectOutput.writeLong(deliberationId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);

		if (result == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(result);
		}

		objectOutput.writeLong(officialProcurationId);
	}

	public String uuid;
	public long officialId;
	public long deliberationId;
	public long groupId;
	public long companyId;
	public long createDate;
	public String result;
	public long officialProcurationId;
	public transient VotePK votePK;

}