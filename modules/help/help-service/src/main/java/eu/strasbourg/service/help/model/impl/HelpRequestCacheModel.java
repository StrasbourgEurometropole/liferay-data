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

package eu.strasbourg.service.help.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import eu.strasbourg.service.help.model.HelpRequest;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HelpRequest in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HelpRequestCacheModel
	implements CacheModel<HelpRequest>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HelpRequestCacheModel)) {
			return false;
		}

		HelpRequestCacheModel helpRequestCacheModel =
			(HelpRequestCacheModel)object;

		if (helpRequestId == helpRequestCacheModel.helpRequestId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, helpRequestId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", helpRequestId=");
		sb.append(helpRequestId);
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
		sb.append(", publikId=");
		sb.append(publikId);
		sb.append(", helpProposalId=");
		sb.append(helpProposalId);
		sb.append(", phoneNumber=");
		sb.append(phoneNumber);
		sb.append(", message=");
		sb.append(message);
		sb.append(", studentCardImageId=");
		sb.append(studentCardImageId);
		sb.append(", agreementSigned1=");
		sb.append(agreementSigned1);
		sb.append(", agreementSigned2=");
		sb.append(agreementSigned2);
		sb.append(", agreementSigned3=");
		sb.append(agreementSigned3);
		sb.append(", comment=");
		sb.append(comment);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HelpRequest toEntityModel() {
		HelpRequestImpl helpRequestImpl = new HelpRequestImpl();

		if (uuid == null) {
			helpRequestImpl.setUuid("");
		}
		else {
			helpRequestImpl.setUuid(uuid);
		}

		helpRequestImpl.setHelpRequestId(helpRequestId);
		helpRequestImpl.setGroupId(groupId);
		helpRequestImpl.setCompanyId(companyId);
		helpRequestImpl.setUserId(userId);

		if (userName == null) {
			helpRequestImpl.setUserName("");
		}
		else {
			helpRequestImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			helpRequestImpl.setCreateDate(null);
		}
		else {
			helpRequestImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			helpRequestImpl.setModifiedDate(null);
		}
		else {
			helpRequestImpl.setModifiedDate(new Date(modifiedDate));
		}

		helpRequestImpl.setStatus(status);
		helpRequestImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			helpRequestImpl.setStatusByUserName("");
		}
		else {
			helpRequestImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			helpRequestImpl.setStatusDate(null);
		}
		else {
			helpRequestImpl.setStatusDate(new Date(statusDate));
		}

		if (publikId == null) {
			helpRequestImpl.setPublikId("");
		}
		else {
			helpRequestImpl.setPublikId(publikId);
		}

		helpRequestImpl.setHelpProposalId(helpProposalId);

		if (phoneNumber == null) {
			helpRequestImpl.setPhoneNumber("");
		}
		else {
			helpRequestImpl.setPhoneNumber(phoneNumber);
		}

		if (message == null) {
			helpRequestImpl.setMessage("");
		}
		else {
			helpRequestImpl.setMessage(message);
		}

		helpRequestImpl.setStudentCardImageId(studentCardImageId);
		helpRequestImpl.setAgreementSigned1(agreementSigned1);
		helpRequestImpl.setAgreementSigned2(agreementSigned2);
		helpRequestImpl.setAgreementSigned3(agreementSigned3);

		if (comment == null) {
			helpRequestImpl.setComment("");
		}
		else {
			helpRequestImpl.setComment(comment);
		}

		helpRequestImpl.resetOriginalValues();

		return helpRequestImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		uuid = objectInput.readUTF();

		helpRequestId = objectInput.readLong();

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
		publikId = objectInput.readUTF();

		helpProposalId = objectInput.readLong();
		phoneNumber = objectInput.readUTF();
		message = (String)objectInput.readObject();

		studentCardImageId = objectInput.readLong();

		agreementSigned1 = objectInput.readBoolean();

		agreementSigned2 = objectInput.readBoolean();

		agreementSigned3 = objectInput.readBoolean();
		comment = (String)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(helpRequestId);

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

		if (publikId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(publikId);
		}

		objectOutput.writeLong(helpProposalId);

		if (phoneNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(phoneNumber);
		}

		if (message == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(message);
		}

		objectOutput.writeLong(studentCardImageId);

		objectOutput.writeBoolean(agreementSigned1);

		objectOutput.writeBoolean(agreementSigned2);

		objectOutput.writeBoolean(agreementSigned3);

		if (comment == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(comment);
		}
	}

	public String uuid;
	public long helpRequestId;
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
	public String publikId;
	public long helpProposalId;
	public String phoneNumber;
	public String message;
	public long studentCardImageId;
	public boolean agreementSigned1;
	public boolean agreementSigned2;
	public boolean agreementSigned3;
	public String comment;

}