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

package eu.strasbourg.service.petition.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.petition.model.Petition;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Petition in entity cache.
 *
 * @author Alexandre QUERE
 * @see Petition
 * @generated
 */
@ProviderType
public class PetitionCacheModel implements CacheModel<Petition>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PetitionCacheModel)) {
			return false;
		}

		PetitionCacheModel petitionCacheModel = (PetitionCacheModel)obj;

		if (petitionId == petitionCacheModel.petitionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, petitionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", petitionId=");
		sb.append(petitionId);
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
		sb.append(", videoURL=");
		sb.append(videoURL);
		sb.append(", pictureURL=");
		sb.append(pictureURL);
		sb.append(", isVideo=");
		sb.append(isVideo);
		sb.append(", description=");
		sb.append(description);
		sb.append(", placeTextArea=");
		sb.append(placeTextArea);
		sb.append(", filesDownload=");
		sb.append(filesDownload);
		sb.append(", publicationDate=");
		sb.append(publicationDate);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Petition toEntityModel() {
		PetitionImpl petitionImpl = new PetitionImpl();

		if (uuid == null) {
			petitionImpl.setUuid(StringPool.BLANK);
		}
		else {
			petitionImpl.setUuid(uuid);
		}

		petitionImpl.setPetitionId(petitionId);
		petitionImpl.setGroupId(groupId);
		petitionImpl.setCompanyId(companyId);
		petitionImpl.setUserId(userId);

		if (userName == null) {
			petitionImpl.setUserName(StringPool.BLANK);
		}
		else {
			petitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			petitionImpl.setCreateDate(null);
		}
		else {
			petitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			petitionImpl.setModifiedDate(null);
		}
		else {
			petitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		petitionImpl.setStatus(status);
		petitionImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			petitionImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			petitionImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			petitionImpl.setStatusDate(null);
		}
		else {
			petitionImpl.setStatusDate(new Date(statusDate));
		}

		if (title == null) {
			petitionImpl.setTitle(StringPool.BLANK);
		}
		else {
			petitionImpl.setTitle(title);
		}

		if (videoURL == null) {
			petitionImpl.setVideoURL(StringPool.BLANK);
		}
		else {
			petitionImpl.setVideoURL(videoURL);
		}

		if (pictureURL == null) {
			petitionImpl.setPictureURL(StringPool.BLANK);
		}
		else {
			petitionImpl.setPictureURL(pictureURL);
		}

		petitionImpl.setIsVideo(isVideo);

		if (description == null) {
			petitionImpl.setDescription(StringPool.BLANK);
		}
		else {
			petitionImpl.setDescription(description);
		}

		if (placeTextArea == null) {
			petitionImpl.setPlaceTextArea(StringPool.BLANK);
		}
		else {
			petitionImpl.setPlaceTextArea(placeTextArea);
		}

		if (filesDownload == null) {
			petitionImpl.setFilesDownload(StringPool.BLANK);
		}
		else {
			petitionImpl.setFilesDownload(filesDownload);
		}

		if (publicationDate == Long.MIN_VALUE) {
			petitionImpl.setPublicationDate(null);
		}
		else {
			petitionImpl.setPublicationDate(new Date(publicationDate));
		}

		if (expirationDate == Long.MIN_VALUE) {
			petitionImpl.setExpirationDate(null);
		}
		else {
			petitionImpl.setExpirationDate(new Date(expirationDate));
		}

		petitionImpl.resetOriginalValues();

		return petitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		petitionId = objectInput.readLong();

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
		videoURL = objectInput.readUTF();
		pictureURL = objectInput.readUTF();

		isVideo = objectInput.readBoolean();
		description = objectInput.readUTF();
		placeTextArea = objectInput.readUTF();
		filesDownload = objectInput.readUTF();
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

		objectOutput.writeLong(petitionId);

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

		if (videoURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(videoURL);
		}

		if (pictureURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(pictureURL);
		}

		objectOutput.writeBoolean(isVideo);

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (placeTextArea == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(placeTextArea);
		}

		if (filesDownload == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(filesDownload);
		}

		objectOutput.writeLong(publicationDate);
		objectOutput.writeLong(expirationDate);
	}

	public String uuid;
	public long petitionId;
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
	public String videoURL;
	public String pictureURL;
	public boolean isVideo;
	public String description;
	public String placeTextArea;
	public String filesDownload;
	public long publicationDate;
	public long expirationDate;
}