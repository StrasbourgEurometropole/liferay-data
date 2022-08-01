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

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.artwork.model.Artwork;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Artwork in entity cache.
 *
 * @author BenjaminBini
 * @generated
 */
public class ArtworkCacheModel implements CacheModel<Artwork>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ArtworkCacheModel)) {
			return false;
		}

		ArtworkCacheModel artworkCacheModel = (ArtworkCacheModel)object;

		if (artworkId == artworkCacheModel.artworkId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, artworkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(55);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", artworkId=");
		sb.append(artworkId);
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
		sb.append(", technicalInformation=");
		sb.append(technicalInformation);
		sb.append(", noticeLink=");
		sb.append(noticeLink);
		sb.append(", artistName=");
		sb.append(artistName);
		sb.append(", creationYear=");
		sb.append(creationYear);
		sb.append(", origin=");
		sb.append(origin);
		sb.append(", exhibitionName=");
		sb.append(exhibitionName);
		sb.append(", exhibitionPlace=");
		sb.append(exhibitionPlace);
		sb.append(", loanPeriod=");
		sb.append(loanPeriod);
		sb.append(", linkName=");
		sb.append(linkName);
		sb.append(", link=");
		sb.append(link);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append(", imagesIds=");
		sb.append(imagesIds);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Artwork toEntityModel() {
		ArtworkImpl artworkImpl = new ArtworkImpl();

		if (uuid == null) {
			artworkImpl.setUuid("");
		}
		else {
			artworkImpl.setUuid(uuid);
		}

		artworkImpl.setArtworkId(artworkId);
		artworkImpl.setGroupId(groupId);
		artworkImpl.setCompanyId(companyId);
		artworkImpl.setUserId(userId);

		if (userName == null) {
			artworkImpl.setUserName("");
		}
		else {
			artworkImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			artworkImpl.setCreateDate(null);
		}
		else {
			artworkImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			artworkImpl.setModifiedDate(null);
		}
		else {
			artworkImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			artworkImpl.setLastPublishDate(null);
		}
		else {
			artworkImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		artworkImpl.setStatus(status);
		artworkImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			artworkImpl.setStatusByUserName("");
		}
		else {
			artworkImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			artworkImpl.setStatusDate(null);
		}
		else {
			artworkImpl.setStatusDate(new Date(statusDate));
		}

		if (title == null) {
			artworkImpl.setTitle("");
		}
		else {
			artworkImpl.setTitle(title);
		}

		if (description == null) {
			artworkImpl.setDescription("");
		}
		else {
			artworkImpl.setDescription(description);
		}

		if (technicalInformation == null) {
			artworkImpl.setTechnicalInformation("");
		}
		else {
			artworkImpl.setTechnicalInformation(technicalInformation);
		}

		if (noticeLink == null) {
			artworkImpl.setNoticeLink("");
		}
		else {
			artworkImpl.setNoticeLink(noticeLink);
		}

		if (artistName == null) {
			artworkImpl.setArtistName("");
		}
		else {
			artworkImpl.setArtistName(artistName);
		}

		if (creationYear == null) {
			artworkImpl.setCreationYear("");
		}
		else {
			artworkImpl.setCreationYear(creationYear);
		}

		if (origin == null) {
			artworkImpl.setOrigin("");
		}
		else {
			artworkImpl.setOrigin(origin);
		}

		if (exhibitionName == null) {
			artworkImpl.setExhibitionName("");
		}
		else {
			artworkImpl.setExhibitionName(exhibitionName);
		}

		if (exhibitionPlace == null) {
			artworkImpl.setExhibitionPlace("");
		}
		else {
			artworkImpl.setExhibitionPlace(exhibitionPlace);
		}

		if (loanPeriod == null) {
			artworkImpl.setLoanPeriod("");
		}
		else {
			artworkImpl.setLoanPeriod(loanPeriod);
		}

		if (linkName == null) {
			artworkImpl.setLinkName("");
		}
		else {
			artworkImpl.setLinkName(linkName);
		}

		if (link == null) {
			artworkImpl.setLink("");
		}
		else {
			artworkImpl.setLink(link);
		}

		artworkImpl.setImageId(imageId);

		if (imagesIds == null) {
			artworkImpl.setImagesIds("");
		}
		else {
			artworkImpl.setImagesIds(imagesIds);
		}

		artworkImpl.resetOriginalValues();

		return artworkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		uuid = objectInput.readUTF();

		artworkId = objectInput.readLong();

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
		description = (String)objectInput.readObject();
		technicalInformation = (String)objectInput.readObject();
		noticeLink = objectInput.readUTF();
		artistName = objectInput.readUTF();
		creationYear = objectInput.readUTF();
		origin = objectInput.readUTF();
		exhibitionName = objectInput.readUTF();
		exhibitionPlace = objectInput.readUTF();
		loanPeriod = objectInput.readUTF();
		linkName = objectInput.readUTF();
		link = objectInput.readUTF();

		imageId = objectInput.readLong();
		imagesIds = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(artworkId);

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
		objectOutput.writeLong(lastPublishDate);

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

		if (technicalInformation == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(technicalInformation);
		}

		if (noticeLink == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(noticeLink);
		}

		if (artistName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(artistName);
		}

		if (creationYear == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(creationYear);
		}

		if (origin == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(origin);
		}

		if (exhibitionName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(exhibitionName);
		}

		if (exhibitionPlace == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(exhibitionPlace);
		}

		if (loanPeriod == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(loanPeriod);
		}

		if (linkName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(linkName);
		}

		if (link == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(link);
		}

		objectOutput.writeLong(imageId);

		if (imagesIds == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imagesIds);
		}
	}

	public String uuid;
	public long artworkId;
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
	public String technicalInformation;
	public String noticeLink;
	public String artistName;
	public String creationYear;
	public String origin;
	public String exhibitionName;
	public String exhibitionPlace;
	public String loanPeriod;
	public String linkName;
	public String link;
	public long imageId;
	public String imagesIds;

}