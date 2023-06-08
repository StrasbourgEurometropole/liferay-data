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

import eu.strasbourg.service.help.model.HelpProposal;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HelpProposal in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HelpProposalCacheModel
	implements CacheModel<HelpProposal>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HelpProposalCacheModel)) {
			return false;
		}

		HelpProposalCacheModel helpProposalCacheModel =
			(HelpProposalCacheModel)object;

		if (helpProposalId == helpProposalCacheModel.helpProposalId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, helpProposalId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(55);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", helpProposalId=");
		sb.append(helpProposalId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", inTheNameOf=");
		sb.append(inTheNameOf);
		sb.append(", address=");
		sb.append(address);
		sb.append(", city=");
		sb.append(city);
		sb.append(", postalCode=");
		sb.append(postalCode);
		sb.append(", phoneNumber=");
		sb.append(phoneNumber);
		sb.append(", modifiedByUserDate=");
		sb.append(modifiedByUserDate);
		sb.append(", spokenLanguages=");
		sb.append(spokenLanguages);
		sb.append(", agreementSigned1=");
		sb.append(agreementSigned1);
		sb.append(", agreementSigned2=");
		sb.append(agreementSigned2);
		sb.append(", agreementSigned3=");
		sb.append(agreementSigned3);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append(", publikId=");
		sb.append(publikId);
		sb.append(", comment=");
		sb.append(comment);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HelpProposal toEntityModel() {
		HelpProposalImpl helpProposalImpl = new HelpProposalImpl();

		if (uuid == null) {
			helpProposalImpl.setUuid("");
		}
		else {
			helpProposalImpl.setUuid(uuid);
		}

		helpProposalImpl.setHelpProposalId(helpProposalId);
		helpProposalImpl.setGroupId(groupId);
		helpProposalImpl.setCompanyId(companyId);
		helpProposalImpl.setUserId(userId);

		if (userName == null) {
			helpProposalImpl.setUserName("");
		}
		else {
			helpProposalImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			helpProposalImpl.setCreateDate(null);
		}
		else {
			helpProposalImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			helpProposalImpl.setModifiedDate(null);
		}
		else {
			helpProposalImpl.setModifiedDate(new Date(modifiedDate));
		}

		helpProposalImpl.setStatus(status);
		helpProposalImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			helpProposalImpl.setStatusByUserName("");
		}
		else {
			helpProposalImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			helpProposalImpl.setStatusDate(null);
		}
		else {
			helpProposalImpl.setStatusDate(new Date(statusDate));
		}

		if (title == null) {
			helpProposalImpl.setTitle("");
		}
		else {
			helpProposalImpl.setTitle(title);
		}

		if (description == null) {
			helpProposalImpl.setDescription("");
		}
		else {
			helpProposalImpl.setDescription(description);
		}

		if (inTheNameOf == null) {
			helpProposalImpl.setInTheNameOf("");
		}
		else {
			helpProposalImpl.setInTheNameOf(inTheNameOf);
		}

		if (address == null) {
			helpProposalImpl.setAddress("");
		}
		else {
			helpProposalImpl.setAddress(address);
		}

		if (city == null) {
			helpProposalImpl.setCity("");
		}
		else {
			helpProposalImpl.setCity(city);
		}

		helpProposalImpl.setPostalCode(postalCode);

		if (phoneNumber == null) {
			helpProposalImpl.setPhoneNumber("");
		}
		else {
			helpProposalImpl.setPhoneNumber(phoneNumber);
		}

		if (modifiedByUserDate == Long.MIN_VALUE) {
			helpProposalImpl.setModifiedByUserDate(null);
		}
		else {
			helpProposalImpl.setModifiedByUserDate(
				new Date(modifiedByUserDate));
		}

		if (spokenLanguages == null) {
			helpProposalImpl.setSpokenLanguages("");
		}
		else {
			helpProposalImpl.setSpokenLanguages(spokenLanguages);
		}

		helpProposalImpl.setAgreementSigned1(agreementSigned1);
		helpProposalImpl.setAgreementSigned2(agreementSigned2);
		helpProposalImpl.setAgreementSigned3(agreementSigned3);
		helpProposalImpl.setImageId(imageId);

		if (publikId == null) {
			helpProposalImpl.setPublikId("");
		}
		else {
			helpProposalImpl.setPublikId(publikId);
		}

		if (comment == null) {
			helpProposalImpl.setComment("");
		}
		else {
			helpProposalImpl.setComment(comment);
		}

		helpProposalImpl.resetOriginalValues();

		return helpProposalImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		uuid = objectInput.readUTF();

		helpProposalId = objectInput.readLong();

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
		title = objectInput.readUTF();
		description = (String)objectInput.readObject();
		inTheNameOf = objectInput.readUTF();
		address = objectInput.readUTF();
		city = objectInput.readUTF();

		postalCode = objectInput.readLong();
		phoneNumber = objectInput.readUTF();
		modifiedByUserDate = objectInput.readLong();
		spokenLanguages = objectInput.readUTF();

		agreementSigned1 = objectInput.readBoolean();

		agreementSigned2 = objectInput.readBoolean();

		agreementSigned3 = objectInput.readBoolean();

		imageId = objectInput.readLong();
		publikId = objectInput.readUTF();
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

		objectOutput.writeLong(helpProposalId);

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

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(description);
		}

		if (inTheNameOf == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(inTheNameOf);
		}

		if (address == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(address);
		}

		if (city == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(city);
		}

		objectOutput.writeLong(postalCode);

		if (phoneNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(phoneNumber);
		}

		objectOutput.writeLong(modifiedByUserDate);

		if (spokenLanguages == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(spokenLanguages);
		}

		objectOutput.writeBoolean(agreementSigned1);

		objectOutput.writeBoolean(agreementSigned2);

		objectOutput.writeBoolean(agreementSigned3);

		objectOutput.writeLong(imageId);

		if (publikId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(publikId);
		}

		if (comment == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(comment);
		}
	}

	public String uuid;
	public long helpProposalId;
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
	public String title;
	public String description;
	public String inTheNameOf;
	public String address;
	public String city;
	public long postalCode;
	public String phoneNumber;
	public long modifiedByUserDate;
	public String spokenLanguages;
	public boolean agreementSigned1;
	public boolean agreementSigned2;
	public boolean agreementSigned3;
	public long imageId;
	public String publikId;
	public String comment;

}