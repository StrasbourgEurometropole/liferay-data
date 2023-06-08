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

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.formSendRecordField.model.FormSendRecordField;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FormSendRecordField in entity cache.
 *
 * @author Angélique Zunino
 * @generated
 */
public class FormSendRecordFieldCacheModel
	implements CacheModel<FormSendRecordField>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FormSendRecordFieldCacheModel)) {
			return false;
		}

		FormSendRecordFieldCacheModel formSendRecordFieldCacheModel =
			(FormSendRecordFieldCacheModel)object;

		if (formSendRecordFieldId ==
				formSendRecordFieldCacheModel.formSendRecordFieldId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, formSendRecordFieldId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", formSendRecordFieldId=");
		sb.append(formSendRecordFieldId);
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
		sb.append(", response=");
		sb.append(response);
		sb.append(", assetEntryId=");
		sb.append(assetEntryId);
		sb.append(", contentId=");
		sb.append(contentId);
		sb.append(", instanceId=");
		sb.append(instanceId);
		sb.append(", responseUserId=");
		sb.append(responseUserId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FormSendRecordField toEntityModel() {
		FormSendRecordFieldImpl formSendRecordFieldImpl =
			new FormSendRecordFieldImpl();

		if (uuid == null) {
			formSendRecordFieldImpl.setUuid("");
		}
		else {
			formSendRecordFieldImpl.setUuid(uuid);
		}

		formSendRecordFieldImpl.setFormSendRecordFieldId(formSendRecordFieldId);
		formSendRecordFieldImpl.setGroupId(groupId);
		formSendRecordFieldImpl.setCompanyId(companyId);
		formSendRecordFieldImpl.setUserId(userId);

		if (userName == null) {
			formSendRecordFieldImpl.setUserName("");
		}
		else {
			formSendRecordFieldImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			formSendRecordFieldImpl.setCreateDate(null);
		}
		else {
			formSendRecordFieldImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			formSendRecordFieldImpl.setModifiedDate(null);
		}
		else {
			formSendRecordFieldImpl.setModifiedDate(new Date(modifiedDate));
		}

		formSendRecordFieldImpl.setStatus(status);
		formSendRecordFieldImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			formSendRecordFieldImpl.setStatusByUserName("");
		}
		else {
			formSendRecordFieldImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			formSendRecordFieldImpl.setStatusDate(null);
		}
		else {
			formSendRecordFieldImpl.setStatusDate(new Date(statusDate));
		}

		if (response == null) {
			formSendRecordFieldImpl.setResponse("");
		}
		else {
			formSendRecordFieldImpl.setResponse(response);
		}

		formSendRecordFieldImpl.setAssetEntryId(assetEntryId);
		formSendRecordFieldImpl.setContentId(contentId);

		if (instanceId == null) {
			formSendRecordFieldImpl.setInstanceId("");
		}
		else {
			formSendRecordFieldImpl.setInstanceId(instanceId);
		}

		formSendRecordFieldImpl.setResponseUserId(responseUserId);

		formSendRecordFieldImpl.resetOriginalValues();

		return formSendRecordFieldImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		uuid = objectInput.readUTF();

		formSendRecordFieldId = objectInput.readLong();

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
		response = (String)objectInput.readObject();

		assetEntryId = objectInput.readLong();

		contentId = objectInput.readLong();
		instanceId = objectInput.readUTF();

		responseUserId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(formSendRecordFieldId);

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

		if (response == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(response);
		}

		objectOutput.writeLong(assetEntryId);

		objectOutput.writeLong(contentId);

		if (instanceId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(instanceId);
		}

		objectOutput.writeLong(responseUserId);
	}

	public String uuid;
	public long formSendRecordFieldId;
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
	public String response;
	public long assetEntryId;
	public long contentId;
	public String instanceId;
	public long responseUserId;

}