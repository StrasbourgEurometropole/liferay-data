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

package eu.strasbourg.service.formSendRecordField.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FormSendRecordFieldSignalement in entity cache.
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldSignalement
 * @generated
 */
@ProviderType
public class FormSendRecordFieldSignalementCacheModel implements CacheModel<FormSendRecordFieldSignalement>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FormSendRecordFieldSignalementCacheModel)) {
			return false;
		}

		FormSendRecordFieldSignalementCacheModel formSendRecordFieldSignalementCacheModel =
			(FormSendRecordFieldSignalementCacheModel)obj;

		if (signalementId == formSendRecordFieldSignalementCacheModel.signalementId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, signalementId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", signalementId=");
		sb.append(signalementId);
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
		sb.append(", formSendRecordFieldId=");
		sb.append(formSendRecordFieldId);
		sb.append(", publikId=");
		sb.append(publikId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FormSendRecordFieldSignalement toEntityModel() {
		FormSendRecordFieldSignalementImpl formSendRecordFieldSignalementImpl = new FormSendRecordFieldSignalementImpl();

		if (uuid == null) {
			formSendRecordFieldSignalementImpl.setUuid(StringPool.BLANK);
		}
		else {
			formSendRecordFieldSignalementImpl.setUuid(uuid);
		}

		formSendRecordFieldSignalementImpl.setSignalementId(signalementId);
		formSendRecordFieldSignalementImpl.setGroupId(groupId);
		formSendRecordFieldSignalementImpl.setCompanyId(companyId);
		formSendRecordFieldSignalementImpl.setUserId(userId);

		if (userName == null) {
			formSendRecordFieldSignalementImpl.setUserName(StringPool.BLANK);
		}
		else {
			formSendRecordFieldSignalementImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			formSendRecordFieldSignalementImpl.setCreateDate(null);
		}
		else {
			formSendRecordFieldSignalementImpl.setCreateDate(new Date(
					createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			formSendRecordFieldSignalementImpl.setModifiedDate(null);
		}
		else {
			formSendRecordFieldSignalementImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		formSendRecordFieldSignalementImpl.setStatus(status);
		formSendRecordFieldSignalementImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			formSendRecordFieldSignalementImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			formSendRecordFieldSignalementImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			formSendRecordFieldSignalementImpl.setStatusDate(null);
		}
		else {
			formSendRecordFieldSignalementImpl.setStatusDate(new Date(
					statusDate));
		}

		formSendRecordFieldSignalementImpl.setFormSendRecordFieldId(formSendRecordFieldId);

		if (publikId == null) {
			formSendRecordFieldSignalementImpl.setPublikId(StringPool.BLANK);
		}
		else {
			formSendRecordFieldSignalementImpl.setPublikId(publikId);
		}

		formSendRecordFieldSignalementImpl.resetOriginalValues();

		return formSendRecordFieldSignalementImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		signalementId = objectInput.readLong();

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

		formSendRecordFieldId = objectInput.readLong();
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

		objectOutput.writeLong(signalementId);

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

		objectOutput.writeLong(formSendRecordFieldId);

		if (publikId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publikId);
		}
	}

	public String uuid;
	public long signalementId;
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
	public long formSendRecordFieldId;
	public String publikId;
}