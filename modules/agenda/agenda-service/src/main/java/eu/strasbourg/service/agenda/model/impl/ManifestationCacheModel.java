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

import eu.strasbourg.service.agenda.model.Manifestation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Manifestation in entity cache.
 *
 * @author BenjaminBini
 * @see Manifestation
 * @generated
 */
@ProviderType
public class ManifestationCacheModel implements CacheModel<Manifestation>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ManifestationCacheModel)) {
			return false;
		}

		ManifestationCacheModel manifestationCacheModel = (ManifestationCacheModel)obj;

		if (manifestationId == manifestationCacheModel.manifestationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, manifestationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", manifestationId=");
		sb.append(manifestationId);
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
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", displayDate=");
		sb.append(displayDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Manifestation toEntityModel() {
		ManifestationImpl manifestationImpl = new ManifestationImpl();

		if (uuid == null) {
			manifestationImpl.setUuid(StringPool.BLANK);
		}
		else {
			manifestationImpl.setUuid(uuid);
		}

		manifestationImpl.setManifestationId(manifestationId);
		manifestationImpl.setGroupId(groupId);
		manifestationImpl.setCompanyId(companyId);
		manifestationImpl.setUserId(userId);

		if (userName == null) {
			manifestationImpl.setUserName(StringPool.BLANK);
		}
		else {
			manifestationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			manifestationImpl.setCreateDate(null);
		}
		else {
			manifestationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			manifestationImpl.setModifiedDate(null);
		}
		else {
			manifestationImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			manifestationImpl.setLastPublishDate(null);
		}
		else {
			manifestationImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		manifestationImpl.setStatus(status);
		manifestationImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			manifestationImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			manifestationImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			manifestationImpl.setStatusDate(null);
		}
		else {
			manifestationImpl.setStatusDate(new Date(statusDate));
		}

		manifestationImpl.setImageId(imageId);

		if (title == null) {
			manifestationImpl.setTitle(StringPool.BLANK);
		}
		else {
			manifestationImpl.setTitle(title);
		}

		if (description == null) {
			manifestationImpl.setDescription(StringPool.BLANK);
		}
		else {
			manifestationImpl.setDescription(description);
		}

		if (displayDate == Long.MIN_VALUE) {
			manifestationImpl.setDisplayDate(null);
		}
		else {
			manifestationImpl.setDisplayDate(new Date(displayDate));
		}

		manifestationImpl.resetOriginalValues();

		return manifestationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		manifestationId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		lastPublishDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();

		imageId = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		displayDate = objectInput.readLong();
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

		objectOutput.writeLong(manifestationId);

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
		objectOutput.writeLong(lastPublishDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		objectOutput.writeLong(imageId);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(displayDate);
	}

	public String uuid;
	public long manifestationId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long lastPublishDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public long imageId;
	public String title;
	public String description;
	public long displayDate;
}