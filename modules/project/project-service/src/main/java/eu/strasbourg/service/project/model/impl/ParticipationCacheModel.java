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

import eu.strasbourg.service.project.model.Participation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Participation in entity cache.
 *
 * @author Cedric Henry
 * @see Participation
 * @generated
 */
@ProviderType
public class ParticipationCacheModel implements CacheModel<Participation>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ParticipationCacheModel)) {
			return false;
		}

		ParticipationCacheModel participationCacheModel = (ParticipationCacheModel)obj;

		if (participationId == participationCacheModel.participationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, participationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", participationId=");
		sb.append(participationId);
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
		sb.append(", author=");
		sb.append(author);
		sb.append(", contactName=");
		sb.append(contactName);
		sb.append(", contactLine1=");
		sb.append(contactLine1);
		sb.append(", contactLine2=");
		sb.append(contactLine2);
		sb.append(", contactPhoneNumber=");
		sb.append(contactPhoneNumber);
		sb.append(", publicationDate=");
		sb.append(publicationDate);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Participation toEntityModel() {
		ParticipationImpl participationImpl = new ParticipationImpl();

		if (uuid == null) {
			participationImpl.setUuid(StringPool.BLANK);
		}
		else {
			participationImpl.setUuid(uuid);
		}

		participationImpl.setParticipationId(participationId);
		participationImpl.setGroupId(groupId);
		participationImpl.setCompanyId(companyId);
		participationImpl.setUserId(userId);

		if (userName == null) {
			participationImpl.setUserName(StringPool.BLANK);
		}
		else {
			participationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			participationImpl.setCreateDate(null);
		}
		else {
			participationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			participationImpl.setModifiedDate(null);
		}
		else {
			participationImpl.setModifiedDate(new Date(modifiedDate));
		}

		participationImpl.setStatus(status);
		participationImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			participationImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			participationImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			participationImpl.setStatusDate(null);
		}
		else {
			participationImpl.setStatusDate(new Date(statusDate));
		}

		if (title == null) {
			participationImpl.setTitle(StringPool.BLANK);
		}
		else {
			participationImpl.setTitle(title);
		}

		if (author == null) {
			participationImpl.setAuthor(StringPool.BLANK);
		}
		else {
			participationImpl.setAuthor(author);
		}

		if (contactName == null) {
			participationImpl.setContactName(StringPool.BLANK);
		}
		else {
			participationImpl.setContactName(contactName);
		}

		if (contactLine1 == null) {
			participationImpl.setContactLine1(StringPool.BLANK);
		}
		else {
			participationImpl.setContactLine1(contactLine1);
		}

		if (contactLine2 == null) {
			participationImpl.setContactLine2(StringPool.BLANK);
		}
		else {
			participationImpl.setContactLine2(contactLine2);
		}

		if (contactPhoneNumber == null) {
			participationImpl.setContactPhoneNumber(StringPool.BLANK);
		}
		else {
			participationImpl.setContactPhoneNumber(contactPhoneNumber);
		}

		if (publicationDate == Long.MIN_VALUE) {
			participationImpl.setPublicationDate(null);
		}
		else {
			participationImpl.setPublicationDate(new Date(publicationDate));
		}

		if (expirationDate == Long.MIN_VALUE) {
			participationImpl.setExpirationDate(null);
		}
		else {
			participationImpl.setExpirationDate(new Date(expirationDate));
		}

		participationImpl.resetOriginalValues();

		return participationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		participationId = objectInput.readLong();

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
		author = objectInput.readUTF();
		contactName = objectInput.readUTF();
		contactLine1 = objectInput.readUTF();
		contactLine2 = objectInput.readUTF();
		contactPhoneNumber = objectInput.readUTF();
		publicationDate = objectInput.readLong();
		expirationDate = objectInput.readLong();
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

		objectOutput.writeLong(participationId);

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

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (author == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(author);
		}

		if (contactName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contactName);
		}

		if (contactLine1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contactLine1);
		}

		if (contactLine2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contactLine2);
		}

		if (contactPhoneNumber == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contactPhoneNumber);
		}

		objectOutput.writeLong(publicationDate);
		objectOutput.writeLong(expirationDate);
	}

	public String uuid;
	public long participationId;
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
	public String author;
	public String contactName;
	public String contactLine1;
	public String contactLine2;
	public String contactPhoneNumber;
	public long publicationDate;
	public long expirationDate;
}