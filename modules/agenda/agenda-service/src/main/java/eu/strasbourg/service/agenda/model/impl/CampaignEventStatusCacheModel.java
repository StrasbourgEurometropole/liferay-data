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

package eu.strasbourg.service.agenda.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.agenda.model.CampaignEventStatus;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CampaignEventStatus in entity cache.
 *
 * @author BenjaminBini
 * @see CampaignEventStatus
 * @generated
 */
@ProviderType
public class CampaignEventStatusCacheModel implements CacheModel<CampaignEventStatus>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CampaignEventStatusCacheModel)) {
			return false;
		}

		CampaignEventStatusCacheModel campaignEventStatusCacheModel = (CampaignEventStatusCacheModel)obj;

		if (statusId == campaignEventStatusCacheModel.statusId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, statusId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", statusId=");
		sb.append(statusId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", comment=");
		sb.append(comment);
		sb.append(", deletionDenied=");
		sb.append(deletionDenied);
		sb.append(", date=");
		sb.append(date);
		sb.append(", campaignEventId=");
		sb.append(campaignEventId);
		sb.append(", previousStatusId=");
		sb.append(previousStatusId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CampaignEventStatus toEntityModel() {
		CampaignEventStatusImpl campaignEventStatusImpl = new CampaignEventStatusImpl();

		if (uuid == null) {
			campaignEventStatusImpl.setUuid(StringPool.BLANK);
		}
		else {
			campaignEventStatusImpl.setUuid(uuid);
		}

		campaignEventStatusImpl.setStatusId(statusId);
		campaignEventStatusImpl.setStatus(status);

		if (comment == null) {
			campaignEventStatusImpl.setComment(StringPool.BLANK);
		}
		else {
			campaignEventStatusImpl.setComment(comment);
		}

		campaignEventStatusImpl.setDeletionDenied(deletionDenied);

		if (date == Long.MIN_VALUE) {
			campaignEventStatusImpl.setDate(null);
		}
		else {
			campaignEventStatusImpl.setDate(new Date(date));
		}

		campaignEventStatusImpl.setCampaignEventId(campaignEventId);
		campaignEventStatusImpl.setPreviousStatusId(previousStatusId);
		campaignEventStatusImpl.setUserId(userId);

		if (userName == null) {
			campaignEventStatusImpl.setUserName(StringPool.BLANK);
		}
		else {
			campaignEventStatusImpl.setUserName(userName);
		}

		campaignEventStatusImpl.resetOriginalValues();

		return campaignEventStatusImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		statusId = objectInput.readLong();

		status = objectInput.readInt();
		comment = objectInput.readUTF();

		deletionDenied = objectInput.readBoolean();
		date = objectInput.readLong();

		campaignEventId = objectInput.readLong();

		previousStatusId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
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

		objectOutput.writeLong(statusId);

		objectOutput.writeInt(status);

		if (comment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(comment);
		}

		objectOutput.writeBoolean(deletionDenied);
		objectOutput.writeLong(date);

		objectOutput.writeLong(campaignEventId);

		objectOutput.writeLong(previousStatusId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}
	}

	public String uuid;
	public long statusId;
	public int status;
	public String comment;
	public boolean deletionDenied;
	public long date;
	public long campaignEventId;
	public long previousStatusId;
	public long userId;
	public String userName;
}