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

package eu.strasbourg.service.ejob.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.ejob.model.Offer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Offer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class OfferCacheModel implements CacheModel<Offer>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfferCacheModel)) {
			return false;
		}

		OfferCacheModel offerCacheModel = (OfferCacheModel)obj;

		if (offerId == offerCacheModel.offerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, offerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(67);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", offerId=");
		sb.append(offerId);
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
		sb.append(", publicationId=");
		sb.append(publicationId);
		sb.append(", postNumber=");
		sb.append(postNumber);
		sb.append(", jobCreationDescription=");
		sb.append(jobCreationDescription);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", motif=");
		sb.append(motif);
		sb.append(", permanentDescription=");
		sb.append(permanentDescription);
		sb.append(", duration=");
		sb.append(duration);
		sb.append(", post=");
		sb.append(post);
		sb.append(", isFullTime=");
		sb.append(isFullTime);
		sb.append(", fullTimeDescription=");
		sb.append(fullTimeDescription);
		sb.append(", introduction=");
		sb.append(introduction);
		sb.append(", activities=");
		sb.append(activities);
		sb.append(", profil=");
		sb.append(profil);
		sb.append(", conditions=");
		sb.append(conditions);
		sb.append(", avantages=");
		sb.append(avantages);
		sb.append(", limitDate=");
		sb.append(limitDate);
		sb.append(", contact=");
		sb.append(contact);
		sb.append(", emails=");
		sb.append(emails);
		sb.append(", shareLinkedin=");
		sb.append(shareLinkedin);
		sb.append(", exportTotem=");
		sb.append(exportTotem);
		sb.append(", publicationDate=");
		sb.append(publicationDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Offer toEntityModel() {
		OfferImpl offerImpl = new OfferImpl();

		if (uuid == null) {
			offerImpl.setUuid("");
		}
		else {
			offerImpl.setUuid(uuid);
		}

		offerImpl.setOfferId(offerId);
		offerImpl.setGroupId(groupId);
		offerImpl.setCompanyId(companyId);
		offerImpl.setUserId(userId);

		if (userName == null) {
			offerImpl.setUserName("");
		}
		else {
			offerImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			offerImpl.setCreateDate(null);
		}
		else {
			offerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			offerImpl.setModifiedDate(null);
		}
		else {
			offerImpl.setModifiedDate(new Date(modifiedDate));
		}

		offerImpl.setStatus(status);
		offerImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			offerImpl.setStatusByUserName("");
		}
		else {
			offerImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			offerImpl.setStatusDate(null);
		}
		else {
			offerImpl.setStatusDate(new Date(statusDate));
		}

		if (publicationId == null) {
			offerImpl.setPublicationId("");
		}
		else {
			offerImpl.setPublicationId(publicationId);
		}

		if (postNumber == null) {
			offerImpl.setPostNumber("");
		}
		else {
			offerImpl.setPostNumber(postNumber);
		}

		if (jobCreationDescription == null) {
			offerImpl.setJobCreationDescription("");
		}
		else {
			offerImpl.setJobCreationDescription(jobCreationDescription);
		}

		if (startDate == Long.MIN_VALUE) {
			offerImpl.setStartDate(null);
		}
		else {
			offerImpl.setStartDate(new Date(startDate));
		}

		if (motif == null) {
			offerImpl.setMotif("");
		}
		else {
			offerImpl.setMotif(motif);
		}

		if (permanentDescription == null) {
			offerImpl.setPermanentDescription("");
		}
		else {
			offerImpl.setPermanentDescription(permanentDescription);
		}

		if (duration == null) {
			offerImpl.setDuration("");
		}
		else {
			offerImpl.setDuration(duration);
		}

		if (post == null) {
			offerImpl.setPost("");
		}
		else {
			offerImpl.setPost(post);
		}

		offerImpl.setIsFullTime(isFullTime);

		if (fullTimeDescription == null) {
			offerImpl.setFullTimeDescription("");
		}
		else {
			offerImpl.setFullTimeDescription(fullTimeDescription);
		}

		if (introduction == null) {
			offerImpl.setIntroduction("");
		}
		else {
			offerImpl.setIntroduction(introduction);
		}

		if (activities == null) {
			offerImpl.setActivities("");
		}
		else {
			offerImpl.setActivities(activities);
		}

		if (profil == null) {
			offerImpl.setProfil("");
		}
		else {
			offerImpl.setProfil(profil);
		}

		if (conditions == null) {
			offerImpl.setConditions("");
		}
		else {
			offerImpl.setConditions(conditions);
		}

		if (avantages == null) {
			offerImpl.setAvantages("");
		}
		else {
			offerImpl.setAvantages(avantages);
		}

		if (limitDate == Long.MIN_VALUE) {
			offerImpl.setLimitDate(null);
		}
		else {
			offerImpl.setLimitDate(new Date(limitDate));
		}

		if (contact == null) {
			offerImpl.setContact("");
		}
		else {
			offerImpl.setContact(contact);
		}

		if (emails == null) {
			offerImpl.setEmails("");
		}
		else {
			offerImpl.setEmails(emails);
		}

		offerImpl.setShareLinkedin(shareLinkedin);

		if (exportTotem == null) {
			offerImpl.setExportTotem("");
		}
		else {
			offerImpl.setExportTotem(exportTotem);
		}

		if (publicationDate == Long.MIN_VALUE) {
			offerImpl.setPublicationDate(null);
		}
		else {
			offerImpl.setPublicationDate(new Date(publicationDate));
		}

		offerImpl.resetOriginalValues();

		return offerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		offerId = objectInput.readLong();

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
		publicationId = objectInput.readUTF();
		postNumber = objectInput.readUTF();
		jobCreationDescription = objectInput.readUTF();
		startDate = objectInput.readLong();
		motif = objectInput.readUTF();
		permanentDescription = objectInput.readUTF();
		duration = objectInput.readUTF();
		post = objectInput.readUTF();

		isFullTime = objectInput.readBoolean();
		fullTimeDescription = objectInput.readUTF();
		introduction = objectInput.readUTF();
		activities = objectInput.readUTF();
		profil = objectInput.readUTF();
		conditions = objectInput.readUTF();
		avantages = objectInput.readUTF();
		limitDate = objectInput.readLong();
		contact = objectInput.readUTF();
		emails = objectInput.readUTF();

		shareLinkedin = objectInput.readBoolean();
		exportTotem = objectInput.readUTF();
		publicationDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(offerId);

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

		if (publicationId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(publicationId);
		}

		if (postNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postNumber);
		}

		if (jobCreationDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(jobCreationDescription);
		}

		objectOutput.writeLong(startDate);

		if (motif == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(motif);
		}

		if (permanentDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(permanentDescription);
		}

		if (duration == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(duration);
		}

		if (post == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(post);
		}

		objectOutput.writeBoolean(isFullTime);

		if (fullTimeDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fullTimeDescription);
		}

		if (introduction == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(introduction);
		}

		if (activities == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(activities);
		}

		if (profil == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(profil);
		}

		if (conditions == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(conditions);
		}

		if (avantages == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(avantages);
		}

		objectOutput.writeLong(limitDate);

		if (contact == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contact);
		}

		if (emails == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(emails);
		}

		objectOutput.writeBoolean(shareLinkedin);

		if (exportTotem == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(exportTotem);
		}

		objectOutput.writeLong(publicationDate);
	}

	public String uuid;
	public long offerId;
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
	public String publicationId;
	public String postNumber;
	public String jobCreationDescription;
	public long startDate;
	public String motif;
	public String permanentDescription;
	public String duration;
	public String post;
	public boolean isFullTime;
	public String fullTimeDescription;
	public String introduction;
	public String activities;
	public String profil;
	public String conditions;
	public String avantages;
	public long limitDate;
	public String contact;
	public String emails;
	public boolean shareLinkedin;
	public String exportTotem;
	public long publicationDate;

}