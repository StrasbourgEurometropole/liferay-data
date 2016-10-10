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

package eu.strasbourg.service.artwork.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.artwork.model.ArtworkCollection;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ArtworkCollection in entity cache.
 *
 * @author BenjaminBini
 * @see ArtworkCollection
 * @generated
 */
@ProviderType
public class ArtworkCollectionCacheModel implements CacheModel<ArtworkCollection>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ArtworkCollectionCacheModel)) {
			return false;
		}

		ArtworkCollectionCacheModel artworkCollectionCacheModel = (ArtworkCollectionCacheModel)obj;

		if (collectionId == artworkCollectionCacheModel.collectionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, collectionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", collectionId=");
		sb.append(collectionId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", contributors=");
		sb.append(contributors);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ArtworkCollection toEntityModel() {
		ArtworkCollectionImpl artworkCollectionImpl = new ArtworkCollectionImpl();

		if (uuid == null) {
			artworkCollectionImpl.setUuid(StringPool.BLANK);
		}
		else {
			artworkCollectionImpl.setUuid(uuid);
		}

		artworkCollectionImpl.setCollectionId(collectionId);
		artworkCollectionImpl.setGroupId(groupId);
		artworkCollectionImpl.setCompanyId(companyId);
		artworkCollectionImpl.setUserId(userId);

		if (userName == null) {
			artworkCollectionImpl.setUserName(StringPool.BLANK);
		}
		else {
			artworkCollectionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			artworkCollectionImpl.setCreateDate(null);
		}
		else {
			artworkCollectionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			artworkCollectionImpl.setModifiedDate(null);
		}
		else {
			artworkCollectionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			artworkCollectionImpl.setLastPublishDate(null);
		}
		else {
			artworkCollectionImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		artworkCollectionImpl.setStatus(status);
		artworkCollectionImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			artworkCollectionImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			artworkCollectionImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			artworkCollectionImpl.setStatusDate(null);
		}
		else {
			artworkCollectionImpl.setStatusDate(new Date(statusDate));
		}

		if (title == null) {
			artworkCollectionImpl.setTitle(StringPool.BLANK);
		}
		else {
			artworkCollectionImpl.setTitle(title);
		}

		if (description == null) {
			artworkCollectionImpl.setDescription(StringPool.BLANK);
		}
		else {
			artworkCollectionImpl.setDescription(description);
		}

		if (contributors == null) {
			artworkCollectionImpl.setContributors(StringPool.BLANK);
		}
		else {
			artworkCollectionImpl.setContributors(contributors);
		}

		artworkCollectionImpl.setImageId(imageId);

		artworkCollectionImpl.resetOriginalValues();

		return artworkCollectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		collectionId = objectInput.readLong();

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
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		contributors = objectInput.readUTF();

		imageId = objectInput.readLong();
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

		objectOutput.writeLong(collectionId);

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

		if (contributors == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contributors);
		}

		objectOutput.writeLong(imageId);
	}

	public String uuid;
	public long collectionId;
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
	public String title;
	public String description;
	public String contributors;
	public long imageId;
}