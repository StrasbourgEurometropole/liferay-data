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

package eu.strasbourg.service.edition.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.edition.model.EditionGallery;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing EditionGallery in entity cache.
 *
 * @author BenjaminBini
 * @see EditionGallery
 * @generated
 */
@ProviderType
public class EditionGalleryCacheModel implements CacheModel<EditionGallery>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EditionGalleryCacheModel)) {
			return false;
		}

		EditionGalleryCacheModel editionGalleryCacheModel = (EditionGalleryCacheModel)obj;

		if (galleryId == editionGalleryCacheModel.galleryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, galleryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", galleryId=");
		sb.append(galleryId);
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
		sb.append(", publicationDate=");
		sb.append(publicationDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EditionGallery toEntityModel() {
		EditionGalleryImpl editionGalleryImpl = new EditionGalleryImpl();

		if (uuid == null) {
			editionGalleryImpl.setUuid(StringPool.BLANK);
		}
		else {
			editionGalleryImpl.setUuid(uuid);
		}

		editionGalleryImpl.setGalleryId(galleryId);
		editionGalleryImpl.setGroupId(groupId);
		editionGalleryImpl.setCompanyId(companyId);
		editionGalleryImpl.setUserId(userId);

		if (userName == null) {
			editionGalleryImpl.setUserName(StringPool.BLANK);
		}
		else {
			editionGalleryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			editionGalleryImpl.setCreateDate(null);
		}
		else {
			editionGalleryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			editionGalleryImpl.setModifiedDate(null);
		}
		else {
			editionGalleryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			editionGalleryImpl.setLastPublishDate(null);
		}
		else {
			editionGalleryImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		editionGalleryImpl.setStatus(status);
		editionGalleryImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			editionGalleryImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			editionGalleryImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			editionGalleryImpl.setStatusDate(null);
		}
		else {
			editionGalleryImpl.setStatusDate(new Date(statusDate));
		}

		editionGalleryImpl.setImageId(imageId);

		if (title == null) {
			editionGalleryImpl.setTitle(StringPool.BLANK);
		}
		else {
			editionGalleryImpl.setTitle(title);
		}

		if (description == null) {
			editionGalleryImpl.setDescription(StringPool.BLANK);
		}
		else {
			editionGalleryImpl.setDescription(description);
		}

		if (publicationDate == Long.MIN_VALUE) {
			editionGalleryImpl.setPublicationDate(null);
		}
		else {
			editionGalleryImpl.setPublicationDate(new Date(publicationDate));
		}

		editionGalleryImpl.resetOriginalValues();

		return editionGalleryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		galleryId = objectInput.readLong();

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
		publicationDate = objectInput.readLong();
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

		objectOutput.writeLong(galleryId);

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

		objectOutput.writeLong(publicationDate);
	}

	public String uuid;
	public long galleryId;
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
	public long publicationDate;
}