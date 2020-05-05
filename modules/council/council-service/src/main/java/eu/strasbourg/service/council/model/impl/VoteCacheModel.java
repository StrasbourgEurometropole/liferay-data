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

import eu.strasbourg.service.council.model.Vote;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Vote in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Vote
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

		if (voteId == voteCacheModel.voteId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, voteId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", voteId=");
		sb.append(voteId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", result=");
		sb.append(result);
		sb.append(", officialId=");
		sb.append(officialId);
		sb.append(", deliberationId=");
		sb.append(deliberationId);
		sb.append(", officialProcurationId=");
		sb.append(officialProcurationId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Vote toEntityModel() {
		VoteImpl voteImpl = new VoteImpl();

		if (uuid == null) {
			voteImpl.setUuid(StringPool.BLANK);
		}
		else {
			voteImpl.setUuid(uuid);
		}

		voteImpl.setVoteId(voteId);
		voteImpl.setGroupId(groupId);
		voteImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			voteImpl.setCreateDate(null);
		}
		else {
			voteImpl.setCreateDate(new Date(createDate));
		}

		if (result == null) {
			voteImpl.setResult(StringPool.BLANK);
		}
		else {
			voteImpl.setResult(result);
		}

		voteImpl.setOfficialId(officialId);
		voteImpl.setDeliberationId(deliberationId);
		voteImpl.setOfficialProcurationId(officialProcurationId);

		voteImpl.resetOriginalValues();

		return voteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		voteId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		result = objectInput.readUTF();

		officialId = objectInput.readLong();

		deliberationId = objectInput.readLong();

		officialProcurationId = objectInput.readLong();
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

		objectOutput.writeLong(voteId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);

		if (result == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(result);
		}

		objectOutput.writeLong(officialId);

		objectOutput.writeLong(deliberationId);

		objectOutput.writeLong(officialProcurationId);
	}

	public String uuid;
	public long voteId;
	public long groupId;
	public long companyId;
	public long createDate;
	public String result;
	public long officialId;
	public long deliberationId;
	public long officialProcurationId;
}