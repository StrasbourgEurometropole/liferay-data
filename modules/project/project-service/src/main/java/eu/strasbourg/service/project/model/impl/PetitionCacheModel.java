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

import eu.strasbourg.service.project.model.Petition;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Petition in entity cache.
 *
 * @author Cedric Henry
 * @generated
 */
@ProviderType
public class PetitionCacheModel
	implements CacheModel<Petition>, Externalizable {

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
		StringBundler sb = new StringBundler(79);

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
		sb.append(", summary=");
		sb.append(summary);
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
		sb.append(", extensionDate=");
		sb.append(extensionDate);
		sb.append(", quotaSignature=");
		sb.append(quotaSignature);
		sb.append(", inTheNameOf=");
		sb.append(inTheNameOf);
		sb.append(", petitionnaireLastname=");
		sb.append(petitionnaireLastname);
		sb.append(", petitionnaireFirstname=");
		sb.append(petitionnaireFirstname);
		sb.append(", petitionnaireBirthday=");
		sb.append(petitionnaireBirthday);
		sb.append(", petitionnaireAdresse=");
		sb.append(petitionnaireAdresse);
		sb.append(", petitionnairePostalCode=");
		sb.append(petitionnairePostalCode);
		sb.append(", petitionnaireCity=");
		sb.append(petitionnaireCity);
		sb.append(", petitionnairePhone=");
		sb.append(petitionnairePhone);
		sb.append(", petitionnaireEmail=");
		sb.append(petitionnaireEmail);
		sb.append(", isSupported=");
		sb.append(isSupported);
		sb.append(", supportedBy=");
		sb.append(supportedBy);
		sb.append(", videoUrl=");
		sb.append(videoUrl);
		sb.append(", externalImageURL=");
		sb.append(externalImageURL);
		sb.append(", externalImageCopyright=");
		sb.append(externalImageCopyright);
		sb.append(", mediaChoice=");
		sb.append(mediaChoice);
		sb.append(", publikId=");
		sb.append(publikId);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append(", filesIds=");
		sb.append(filesIds);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Petition toEntityModel() {
		PetitionImpl petitionImpl = new PetitionImpl();

		if (uuid == null) {
			petitionImpl.setUuid("");
		}
		else {
			petitionImpl.setUuid(uuid);
		}

		petitionImpl.setPetitionId(petitionId);
		petitionImpl.setGroupId(groupId);
		petitionImpl.setCompanyId(companyId);
		petitionImpl.setUserId(userId);

		if (userName == null) {
			petitionImpl.setUserName("");
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
			petitionImpl.setStatusByUserName("");
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
			petitionImpl.setTitle("");
		}
		else {
			petitionImpl.setTitle(title);
		}

		if (summary == null) {
			petitionImpl.setSummary("");
		}
		else {
			petitionImpl.setSummary(summary);
		}

		if (description == null) {
			petitionImpl.setDescription("");
		}
		else {
			petitionImpl.setDescription(description);
		}

		if (placeTextArea == null) {
			petitionImpl.setPlaceTextArea("");
		}
		else {
			petitionImpl.setPlaceTextArea(placeTextArea);
		}

		if (filesDownload == null) {
			petitionImpl.setFilesDownload("");
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

		if (extensionDate == Long.MIN_VALUE) {
			petitionImpl.setExtensionDate(null);
		}
		else {
			petitionImpl.setExtensionDate(new Date(extensionDate));
		}

		petitionImpl.setQuotaSignature(quotaSignature);

		if (inTheNameOf == null) {
			petitionImpl.setInTheNameOf("");
		}
		else {
			petitionImpl.setInTheNameOf(inTheNameOf);
		}

		if (petitionnaireLastname == null) {
			petitionImpl.setPetitionnaireLastname("");
		}
		else {
			petitionImpl.setPetitionnaireLastname(petitionnaireLastname);
		}

		if (petitionnaireFirstname == null) {
			petitionImpl.setPetitionnaireFirstname("");
		}
		else {
			petitionImpl.setPetitionnaireFirstname(petitionnaireFirstname);
		}

		if (petitionnaireBirthday == Long.MIN_VALUE) {
			petitionImpl.setPetitionnaireBirthday(null);
		}
		else {
			petitionImpl.setPetitionnaireBirthday(
				new Date(petitionnaireBirthday));
		}

		if (petitionnaireAdresse == null) {
			petitionImpl.setPetitionnaireAdresse("");
		}
		else {
			petitionImpl.setPetitionnaireAdresse(petitionnaireAdresse);
		}

		petitionImpl.setPetitionnairePostalCode(petitionnairePostalCode);

		if (petitionnaireCity == null) {
			petitionImpl.setPetitionnaireCity("");
		}
		else {
			petitionImpl.setPetitionnaireCity(petitionnaireCity);
		}

		if (petitionnairePhone == null) {
			petitionImpl.setPetitionnairePhone("");
		}
		else {
			petitionImpl.setPetitionnairePhone(petitionnairePhone);
		}

		if (petitionnaireEmail == null) {
			petitionImpl.setPetitionnaireEmail("");
		}
		else {
			petitionImpl.setPetitionnaireEmail(petitionnaireEmail);
		}

		petitionImpl.setIsSupported(isSupported);

		if (supportedBy == null) {
			petitionImpl.setSupportedBy("");
		}
		else {
			petitionImpl.setSupportedBy(supportedBy);
		}

		if (videoUrl == null) {
			petitionImpl.setVideoUrl("");
		}
		else {
			petitionImpl.setVideoUrl(videoUrl);
		}

		if (externalImageURL == null) {
			petitionImpl.setExternalImageURL("");
		}
		else {
			petitionImpl.setExternalImageURL(externalImageURL);
		}

		if (externalImageCopyright == null) {
			petitionImpl.setExternalImageCopyright("");
		}
		else {
			petitionImpl.setExternalImageCopyright(externalImageCopyright);
		}

		petitionImpl.setMediaChoice(mediaChoice);

		if (publikId == null) {
			petitionImpl.setPublikId("");
		}
		else {
			petitionImpl.setPublikId(publikId);
		}

		petitionImpl.setImageId(imageId);

		if (filesIds == null) {
			petitionImpl.setFilesIds("");
		}
		else {
			petitionImpl.setFilesIds(filesIds);
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
		summary = objectInput.readUTF();
		description = objectInput.readUTF();
		placeTextArea = objectInput.readUTF();
		filesDownload = objectInput.readUTF();
		publicationDate = objectInput.readLong();
		expirationDate = objectInput.readLong();
		extensionDate = objectInput.readLong();

		quotaSignature = objectInput.readLong();
		inTheNameOf = objectInput.readUTF();
		petitionnaireLastname = objectInput.readUTF();
		petitionnaireFirstname = objectInput.readUTF();
		petitionnaireBirthday = objectInput.readLong();
		petitionnaireAdresse = objectInput.readUTF();

		petitionnairePostalCode = objectInput.readLong();
		petitionnaireCity = objectInput.readUTF();
		petitionnairePhone = objectInput.readUTF();
		petitionnaireEmail = objectInput.readUTF();

		isSupported = objectInput.readBoolean();
		supportedBy = objectInput.readUTF();
		videoUrl = objectInput.readUTF();
		externalImageURL = objectInput.readUTF();
		externalImageCopyright = objectInput.readUTF();

		mediaChoice = objectInput.readBoolean();
		publikId = objectInput.readUTF();

		imageId = objectInput.readLong();
		filesIds = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(petitionId);

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

		if (summary == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(summary);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (placeTextArea == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(placeTextArea);
		}

		if (filesDownload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(filesDownload);
		}

		objectOutput.writeLong(publicationDate);
		objectOutput.writeLong(expirationDate);
		objectOutput.writeLong(extensionDate);

		objectOutput.writeLong(quotaSignature);

		if (inTheNameOf == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(inTheNameOf);
		}

		if (petitionnaireLastname == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(petitionnaireLastname);
		}

		if (petitionnaireFirstname == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(petitionnaireFirstname);
		}

		objectOutput.writeLong(petitionnaireBirthday);

		if (petitionnaireAdresse == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(petitionnaireAdresse);
		}

		objectOutput.writeLong(petitionnairePostalCode);

		if (petitionnaireCity == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(petitionnaireCity);
		}

		if (petitionnairePhone == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(petitionnairePhone);
		}

		if (petitionnaireEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(petitionnaireEmail);
		}

		objectOutput.writeBoolean(isSupported);

		if (supportedBy == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(supportedBy);
		}

		if (videoUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(videoUrl);
		}

		if (externalImageURL == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalImageURL);
		}

		if (externalImageCopyright == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalImageCopyright);
		}

		objectOutput.writeBoolean(mediaChoice);

		if (publikId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(publikId);
		}

		objectOutput.writeLong(imageId);

		if (filesIds == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(filesIds);
		}
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
	public String summary;
	public String description;
	public String placeTextArea;
	public String filesDownload;
	public long publicationDate;
	public long expirationDate;
	public long extensionDate;
	public long quotaSignature;
	public String inTheNameOf;
	public String petitionnaireLastname;
	public String petitionnaireFirstname;
	public long petitionnaireBirthday;
	public String petitionnaireAdresse;
	public long petitionnairePostalCode;
	public String petitionnaireCity;
	public String petitionnairePhone;
	public String petitionnaireEmail;
	public boolean isSupported;
	public String supportedBy;
	public String videoUrl;
	public String externalImageURL;
	public String externalImageCopyright;
	public boolean mediaChoice;
	public String publikId;
	public long imageId;
	public String filesIds;

}