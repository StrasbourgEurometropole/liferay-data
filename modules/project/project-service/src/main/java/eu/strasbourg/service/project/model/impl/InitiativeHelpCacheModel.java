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

import eu.strasbourg.service.project.model.InitiativeHelp;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing InitiativeHelp in entity cache.
 *
 * @author Cedric Henry
 * @see InitiativeHelp
 * @generated
 */
@ProviderType
public class InitiativeHelpCacheModel implements CacheModel<InitiativeHelp>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InitiativeHelpCacheModel)) {
			return false;
		}

		InitiativeHelpCacheModel initiativeHelpCacheModel = (InitiativeHelpCacheModel)obj;

		if (initiativeHelpId == initiativeHelpCacheModel.initiativeHelpId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, initiativeHelpId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", initiativeHelpId=");
		sb.append(initiativeHelpId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", publikUserId=");
		sb.append(publikUserId);
		sb.append(", initiativeId=");
		sb.append(initiativeId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", message=");
		sb.append(message);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public InitiativeHelp toEntityModel() {
		InitiativeHelpImpl initiativeHelpImpl = new InitiativeHelpImpl();

		if (uuid == null) {
			initiativeHelpImpl.setUuid(StringPool.BLANK);
		}
		else {
			initiativeHelpImpl.setUuid(uuid);
		}

		initiativeHelpImpl.setInitiativeHelpId(initiativeHelpId);

		if (createDate == Long.MIN_VALUE) {
			initiativeHelpImpl.setCreateDate(null);
		}
		else {
			initiativeHelpImpl.setCreateDate(new Date(createDate));
		}

		if (publikUserId == null) {
			initiativeHelpImpl.setPublikUserId(StringPool.BLANK);
		}
		else {
			initiativeHelpImpl.setPublikUserId(publikUserId);
		}

		initiativeHelpImpl.setInitiativeId(initiativeId);
		initiativeHelpImpl.setGroupId(groupId);

		if (message == null) {
			initiativeHelpImpl.setMessage(StringPool.BLANK);
		}
		else {
			initiativeHelpImpl.setMessage(message);
		}

		initiativeHelpImpl.resetOriginalValues();

		return initiativeHelpImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		initiativeHelpId = objectInput.readLong();
		createDate = objectInput.readLong();
		publikUserId = objectInput.readUTF();

		initiativeId = objectInput.readLong();

		groupId = objectInput.readLong();
		message = objectInput.readUTF();
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

		objectOutput.writeLong(initiativeHelpId);
		objectOutput.writeLong(createDate);

		if (publikUserId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publikUserId);
		}

		objectOutput.writeLong(initiativeId);

		objectOutput.writeLong(groupId);

		if (message == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(message);
		}
	}

	public String uuid;
	public long initiativeHelpId;
	public long createDate;
	public String publikUserId;
	public long initiativeId;
	public long groupId;
	public String message;
}