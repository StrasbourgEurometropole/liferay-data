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

import eu.strasbourg.service.project.model.BudgetParticipatif;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BudgetParticipatif in entity cache.
 *
 * @author Cedric Henry
 * @see BudgetParticipatif
 * @generated
 */
@ProviderType
public class BudgetParticipatifCacheModel implements CacheModel<BudgetParticipatif>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BudgetParticipatifCacheModel)) {
			return false;
		}

		BudgetParticipatifCacheModel budgetParticipatifCacheModel = (BudgetParticipatifCacheModel)obj;

		if (budgetParticipatifId == budgetParticipatifCacheModel.budgetParticipatifId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, budgetParticipatifId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(73);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", budgetParticipatifId=");
		sb.append(budgetParticipatifId);
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
		sb.append(", summary=");
		sb.append(summary);
		sb.append(", budget=");
		sb.append(budget);
		sb.append(", motif=");
		sb.append(motif);
		sb.append(", placeTextArea=");
		sb.append(placeTextArea);
		sb.append(", inTheNameOf=");
		sb.append(inTheNameOf);
		sb.append(", citoyenLastname=");
		sb.append(citoyenLastname);
		sb.append(", citoyenFirstname=");
		sb.append(citoyenFirstname);
		sb.append(", citoyenAdresse=");
		sb.append(citoyenAdresse);
		sb.append(", citoyenPostalCode=");
		sb.append(citoyenPostalCode);
		sb.append(", citoyenCity=");
		sb.append(citoyenCity);
		sb.append(", citoyenPhone=");
		sb.append(citoyenPhone);
		sb.append(", citoyenMobile=");
		sb.append(citoyenMobile);
		sb.append(", citoyenEmail=");
		sb.append(citoyenEmail);
		sb.append(", citoyenBirthday=");
		sb.append(citoyenBirthday);
		sb.append(", hasCopyright=");
		sb.append(hasCopyright);
		sb.append(", videoUrl=");
		sb.append(videoUrl);
		sb.append(", isCrush=");
		sb.append(isCrush);
		sb.append(", crushComment=");
		sb.append(crushComment);
		sb.append(", publikId=");
		sb.append(publikId);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append(", filesIds=");
		sb.append(filesIds);
		sb.append(", budgetPhaseId=");
		sb.append(budgetPhaseId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BudgetParticipatif toEntityModel() {
		BudgetParticipatifImpl budgetParticipatifImpl = new BudgetParticipatifImpl();

		if (uuid == null) {
			budgetParticipatifImpl.setUuid(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setUuid(uuid);
		}

		budgetParticipatifImpl.setBudgetParticipatifId(budgetParticipatifId);
		budgetParticipatifImpl.setGroupId(groupId);
		budgetParticipatifImpl.setCompanyId(companyId);
		budgetParticipatifImpl.setUserId(userId);

		if (userName == null) {
			budgetParticipatifImpl.setUserName(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			budgetParticipatifImpl.setCreateDate(null);
		}
		else {
			budgetParticipatifImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			budgetParticipatifImpl.setModifiedDate(null);
		}
		else {
			budgetParticipatifImpl.setModifiedDate(new Date(modifiedDate));
		}

		budgetParticipatifImpl.setStatus(status);
		budgetParticipatifImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			budgetParticipatifImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			budgetParticipatifImpl.setStatusDate(null);
		}
		else {
			budgetParticipatifImpl.setStatusDate(new Date(statusDate));
		}

		if (title == null) {
			budgetParticipatifImpl.setTitle(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setTitle(title);
		}

		if (description == null) {
			budgetParticipatifImpl.setDescription(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setDescription(description);
		}

		if (summary == null) {
			budgetParticipatifImpl.setSummary(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setSummary(summary);
		}

		if (budget == null) {
			budgetParticipatifImpl.setBudget(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setBudget(budget);
		}

		if (motif == null) {
			budgetParticipatifImpl.setMotif(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setMotif(motif);
		}

		if (placeTextArea == null) {
			budgetParticipatifImpl.setPlaceTextArea(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setPlaceTextArea(placeTextArea);
		}

		if (inTheNameOf == null) {
			budgetParticipatifImpl.setInTheNameOf(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setInTheNameOf(inTheNameOf);
		}

		if (citoyenLastname == null) {
			budgetParticipatifImpl.setCitoyenLastname(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setCitoyenLastname(citoyenLastname);
		}

		if (citoyenFirstname == null) {
			budgetParticipatifImpl.setCitoyenFirstname(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setCitoyenFirstname(citoyenFirstname);
		}

		if (citoyenAdresse == null) {
			budgetParticipatifImpl.setCitoyenAdresse(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setCitoyenAdresse(citoyenAdresse);
		}

		budgetParticipatifImpl.setCitoyenPostalCode(citoyenPostalCode);

		if (citoyenCity == null) {
			budgetParticipatifImpl.setCitoyenCity(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setCitoyenCity(citoyenCity);
		}

		if (citoyenPhone == null) {
			budgetParticipatifImpl.setCitoyenPhone(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setCitoyenPhone(citoyenPhone);
		}

		if (citoyenMobile == null) {
			budgetParticipatifImpl.setCitoyenMobile(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setCitoyenMobile(citoyenMobile);
		}

		if (citoyenEmail == null) {
			budgetParticipatifImpl.setCitoyenEmail(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setCitoyenEmail(citoyenEmail);
		}

		if (citoyenBirthday == Long.MIN_VALUE) {
			budgetParticipatifImpl.setCitoyenBirthday(null);
		}
		else {
			budgetParticipatifImpl.setCitoyenBirthday(new Date(citoyenBirthday));
		}

		budgetParticipatifImpl.setHasCopyright(hasCopyright);

		if (videoUrl == null) {
			budgetParticipatifImpl.setVideoUrl(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setVideoUrl(videoUrl);
		}

		budgetParticipatifImpl.setIsCrush(isCrush);

		if (crushComment == null) {
			budgetParticipatifImpl.setCrushComment(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setCrushComment(crushComment);
		}

		if (publikId == null) {
			budgetParticipatifImpl.setPublikId(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setPublikId(publikId);
		}

		budgetParticipatifImpl.setImageId(imageId);

		if (filesIds == null) {
			budgetParticipatifImpl.setFilesIds(StringPool.BLANK);
		}
		else {
			budgetParticipatifImpl.setFilesIds(filesIds);
		}

		budgetParticipatifImpl.setBudgetPhaseId(budgetPhaseId);

		budgetParticipatifImpl.resetOriginalValues();

		return budgetParticipatifImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		budgetParticipatifId = objectInput.readLong();

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
		description = objectInput.readUTF();
		summary = objectInput.readUTF();
		budget = objectInput.readUTF();
		motif = objectInput.readUTF();
		placeTextArea = objectInput.readUTF();
		inTheNameOf = objectInput.readUTF();
		citoyenLastname = objectInput.readUTF();
		citoyenFirstname = objectInput.readUTF();
		citoyenAdresse = objectInput.readUTF();

		citoyenPostalCode = objectInput.readLong();
		citoyenCity = objectInput.readUTF();
		citoyenPhone = objectInput.readUTF();
		citoyenMobile = objectInput.readUTF();
		citoyenEmail = objectInput.readUTF();
		citoyenBirthday = objectInput.readLong();

		hasCopyright = objectInput.readBoolean();
		videoUrl = objectInput.readUTF();

		isCrush = objectInput.readBoolean();
		crushComment = objectInput.readUTF();
		publikId = objectInput.readUTF();

		imageId = objectInput.readLong();
		filesIds = objectInput.readUTF();

		budgetPhaseId = objectInput.readLong();
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

		objectOutput.writeLong(budgetParticipatifId);

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

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (summary == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(summary);
		}

		if (budget == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(budget);
		}

		if (motif == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(motif);
		}

		if (placeTextArea == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(placeTextArea);
		}

		if (inTheNameOf == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inTheNameOf);
		}

		if (citoyenLastname == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenLastname);
		}

		if (citoyenFirstname == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenFirstname);
		}

		if (citoyenAdresse == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenAdresse);
		}

		objectOutput.writeLong(citoyenPostalCode);

		if (citoyenCity == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenCity);
		}

		if (citoyenPhone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenPhone);
		}

		if (citoyenMobile == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenMobile);
		}

		if (citoyenEmail == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(citoyenEmail);
		}

		objectOutput.writeLong(citoyenBirthday);

		objectOutput.writeBoolean(hasCopyright);

		if (videoUrl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(videoUrl);
		}

		objectOutput.writeBoolean(isCrush);

		if (crushComment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(crushComment);
		}

		if (publikId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publikId);
		}

		objectOutput.writeLong(imageId);

		if (filesIds == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(filesIds);
		}

		objectOutput.writeLong(budgetPhaseId);
	}

	public String uuid;
	public long budgetParticipatifId;
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
	public String summary;
	public String budget;
	public String motif;
	public String placeTextArea;
	public String inTheNameOf;
	public String citoyenLastname;
	public String citoyenFirstname;
	public String citoyenAdresse;
	public long citoyenPostalCode;
	public String citoyenCity;
	public String citoyenPhone;
	public String citoyenMobile;
	public String citoyenEmail;
	public long citoyenBirthday;
	public boolean hasCopyright;
	public String videoUrl;
	public boolean isCrush;
	public String crushComment;
	public String publikId;
	public long imageId;
	public String filesIds;
	public long budgetPhaseId;
}