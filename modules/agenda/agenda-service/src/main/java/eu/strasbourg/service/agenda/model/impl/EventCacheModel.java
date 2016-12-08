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

import eu.strasbourg.service.agenda.model.Event;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Event in entity cache.
 *
 * @author BenjaminBini
 * @see Event
 * @generated
 */
@ProviderType
public class EventCacheModel implements CacheModel<Event>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventCacheModel)) {
			return false;
		}

		EventCacheModel eventCacheModel = (EventCacheModel)obj;

		if (eventId == eventCacheModel.eventId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, eventId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(91);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", eventId=");
		sb.append(eventId);
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
		sb.append(", subtitle=");
		sb.append(subtitle);
		sb.append(", description=");
		sb.append(description);
		sb.append(", externalImageURL=");
		sb.append(externalImageURL);
		sb.append(", externalImageCopyright=");
		sb.append(externalImageCopyright);
		sb.append(", placeSIGId=");
		sb.append(placeSIGId);
		sb.append(", placeName=");
		sb.append(placeName);
		sb.append(", placeStreetNumber=");
		sb.append(placeStreetNumber);
		sb.append(", placeStreetName=");
		sb.append(placeStreetName);
		sb.append(", placeZipCode=");
		sb.append(placeZipCode);
		sb.append(", placeCity=");
		sb.append(placeCity);
		sb.append(", placeCountry=");
		sb.append(placeCountry);
		sb.append(", access=");
		sb.append(access);
		sb.append(", accessForDisabled=");
		sb.append(accessForDisabled);
		sb.append(", accessForBlind=");
		sb.append(accessForBlind);
		sb.append(", accessForDeaf=");
		sb.append(accessForDeaf);
		sb.append(", accessForWheelchair=");
		sb.append(accessForWheelchair);
		sb.append(", accessForElder=");
		sb.append(accessForElder);
		sb.append(", accessForDeficient=");
		sb.append(accessForDeficient);
		sb.append(", promoter=");
		sb.append(promoter);
		sb.append(", phone=");
		sb.append(phone);
		sb.append(", email=");
		sb.append(email);
		sb.append(", websiteURL=");
		sb.append(websiteURL);
		sb.append(", websiteName=");
		sb.append(websiteName);
		sb.append(", free=");
		sb.append(free);
		sb.append(", price=");
		sb.append(price);
		sb.append(", source=");
		sb.append(source);
		sb.append(", displayDate=");
		sb.append(displayDate);
		sb.append(", scheduleComments=");
		sb.append(scheduleComments);
		sb.append(", firstStartDate=");
		sb.append(firstStartDate);
		sb.append(", lastEndDate=");
		sb.append(lastEndDate);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Event toEntityModel() {
		EventImpl eventImpl = new EventImpl();

		if (uuid == null) {
			eventImpl.setUuid(StringPool.BLANK);
		}
		else {
			eventImpl.setUuid(uuid);
		}

		eventImpl.setEventId(eventId);
		eventImpl.setGroupId(groupId);
		eventImpl.setCompanyId(companyId);
		eventImpl.setUserId(userId);

		if (userName == null) {
			eventImpl.setUserName(StringPool.BLANK);
		}
		else {
			eventImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			eventImpl.setCreateDate(null);
		}
		else {
			eventImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			eventImpl.setModifiedDate(null);
		}
		else {
			eventImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			eventImpl.setLastPublishDate(null);
		}
		else {
			eventImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		eventImpl.setStatus(status);
		eventImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			eventImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			eventImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			eventImpl.setStatusDate(null);
		}
		else {
			eventImpl.setStatusDate(new Date(statusDate));
		}

		if (title == null) {
			eventImpl.setTitle(StringPool.BLANK);
		}
		else {
			eventImpl.setTitle(title);
		}

		if (subtitle == null) {
			eventImpl.setSubtitle(StringPool.BLANK);
		}
		else {
			eventImpl.setSubtitle(subtitle);
		}

		if (description == null) {
			eventImpl.setDescription(StringPool.BLANK);
		}
		else {
			eventImpl.setDescription(description);
		}

		if (externalImageURL == null) {
			eventImpl.setExternalImageURL(StringPool.BLANK);
		}
		else {
			eventImpl.setExternalImageURL(externalImageURL);
		}

		if (externalImageCopyright == null) {
			eventImpl.setExternalImageCopyright(StringPool.BLANK);
		}
		else {
			eventImpl.setExternalImageCopyright(externalImageCopyright);
		}

		if (placeSIGId == null) {
			eventImpl.setPlaceSIGId(StringPool.BLANK);
		}
		else {
			eventImpl.setPlaceSIGId(placeSIGId);
		}

		if (placeName == null) {
			eventImpl.setPlaceName(StringPool.BLANK);
		}
		else {
			eventImpl.setPlaceName(placeName);
		}

		if (placeStreetNumber == null) {
			eventImpl.setPlaceStreetNumber(StringPool.BLANK);
		}
		else {
			eventImpl.setPlaceStreetNumber(placeStreetNumber);
		}

		if (placeStreetName == null) {
			eventImpl.setPlaceStreetName(StringPool.BLANK);
		}
		else {
			eventImpl.setPlaceStreetName(placeStreetName);
		}

		if (placeZipCode == null) {
			eventImpl.setPlaceZipCode(StringPool.BLANK);
		}
		else {
			eventImpl.setPlaceZipCode(placeZipCode);
		}

		if (placeCity == null) {
			eventImpl.setPlaceCity(StringPool.BLANK);
		}
		else {
			eventImpl.setPlaceCity(placeCity);
		}

		if (placeCountry == null) {
			eventImpl.setPlaceCountry(StringPool.BLANK);
		}
		else {
			eventImpl.setPlaceCountry(placeCountry);
		}

		if (access == null) {
			eventImpl.setAccess(StringPool.BLANK);
		}
		else {
			eventImpl.setAccess(access);
		}

		if (accessForDisabled == null) {
			eventImpl.setAccessForDisabled(StringPool.BLANK);
		}
		else {
			eventImpl.setAccessForDisabled(accessForDisabled);
		}

		eventImpl.setAccessForBlind(accessForBlind);
		eventImpl.setAccessForDeaf(accessForDeaf);
		eventImpl.setAccessForWheelchair(accessForWheelchair);
		eventImpl.setAccessForElder(accessForElder);
		eventImpl.setAccessForDeficient(accessForDeficient);

		if (promoter == null) {
			eventImpl.setPromoter(StringPool.BLANK);
		}
		else {
			eventImpl.setPromoter(promoter);
		}

		if (phone == null) {
			eventImpl.setPhone(StringPool.BLANK);
		}
		else {
			eventImpl.setPhone(phone);
		}

		if (email == null) {
			eventImpl.setEmail(StringPool.BLANK);
		}
		else {
			eventImpl.setEmail(email);
		}

		if (websiteURL == null) {
			eventImpl.setWebsiteURL(StringPool.BLANK);
		}
		else {
			eventImpl.setWebsiteURL(websiteURL);
		}

		if (websiteName == null) {
			eventImpl.setWebsiteName(StringPool.BLANK);
		}
		else {
			eventImpl.setWebsiteName(websiteName);
		}

		eventImpl.setFree(free);

		if (price == null) {
			eventImpl.setPrice(StringPool.BLANK);
		}
		else {
			eventImpl.setPrice(price);
		}

		if (source == null) {
			eventImpl.setSource(StringPool.BLANK);
		}
		else {
			eventImpl.setSource(source);
		}

		if (displayDate == Long.MIN_VALUE) {
			eventImpl.setDisplayDate(null);
		}
		else {
			eventImpl.setDisplayDate(new Date(displayDate));
		}

		if (scheduleComments == null) {
			eventImpl.setScheduleComments(StringPool.BLANK);
		}
		else {
			eventImpl.setScheduleComments(scheduleComments);
		}

		if (firstStartDate == Long.MIN_VALUE) {
			eventImpl.setFirstStartDate(null);
		}
		else {
			eventImpl.setFirstStartDate(new Date(firstStartDate));
		}

		if (lastEndDate == Long.MIN_VALUE) {
			eventImpl.setLastEndDate(null);
		}
		else {
			eventImpl.setLastEndDate(new Date(lastEndDate));
		}

		eventImpl.setImageId(imageId);

		eventImpl.resetOriginalValues();

		return eventImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		eventId = objectInput.readLong();

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
		subtitle = objectInput.readUTF();
		description = objectInput.readUTF();
		externalImageURL = objectInput.readUTF();
		externalImageCopyright = objectInput.readUTF();
		placeSIGId = objectInput.readUTF();
		placeName = objectInput.readUTF();
		placeStreetNumber = objectInput.readUTF();
		placeStreetName = objectInput.readUTF();
		placeZipCode = objectInput.readUTF();
		placeCity = objectInput.readUTF();
		placeCountry = objectInput.readUTF();
		access = objectInput.readUTF();
		accessForDisabled = objectInput.readUTF();

		accessForBlind = objectInput.readBoolean();

		accessForDeaf = objectInput.readBoolean();

		accessForWheelchair = objectInput.readBoolean();

		accessForElder = objectInput.readBoolean();

		accessForDeficient = objectInput.readBoolean();
		promoter = objectInput.readUTF();
		phone = objectInput.readUTF();
		email = objectInput.readUTF();
		websiteURL = objectInput.readUTF();
		websiteName = objectInput.readUTF();

		free = objectInput.readInt();
		price = objectInput.readUTF();
		source = objectInput.readUTF();
		displayDate = objectInput.readLong();
		scheduleComments = objectInput.readUTF();
		firstStartDate = objectInput.readLong();
		lastEndDate = objectInput.readLong();

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

		objectOutput.writeLong(eventId);

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

		if (subtitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subtitle);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (externalImageURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(externalImageURL);
		}

		if (externalImageCopyright == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(externalImageCopyright);
		}

		if (placeSIGId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(placeSIGId);
		}

		if (placeName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(placeName);
		}

		if (placeStreetNumber == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(placeStreetNumber);
		}

		if (placeStreetName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(placeStreetName);
		}

		if (placeZipCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(placeZipCode);
		}

		if (placeCity == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(placeCity);
		}

		if (placeCountry == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(placeCountry);
		}

		if (access == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(access);
		}

		if (accessForDisabled == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accessForDisabled);
		}

		objectOutput.writeBoolean(accessForBlind);

		objectOutput.writeBoolean(accessForDeaf);

		objectOutput.writeBoolean(accessForWheelchair);

		objectOutput.writeBoolean(accessForElder);

		objectOutput.writeBoolean(accessForDeficient);

		if (promoter == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(promoter);
		}

		if (phone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(phone);
		}

		if (email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (websiteURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(websiteURL);
		}

		if (websiteName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(websiteName);
		}

		objectOutput.writeInt(free);

		if (price == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(price);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeLong(displayDate);

		if (scheduleComments == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(scheduleComments);
		}

		objectOutput.writeLong(firstStartDate);
		objectOutput.writeLong(lastEndDate);

		objectOutput.writeLong(imageId);
	}

	public String uuid;
	public long eventId;
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
	public String subtitle;
	public String description;
	public String externalImageURL;
	public String externalImageCopyright;
	public String placeSIGId;
	public String placeName;
	public String placeStreetNumber;
	public String placeStreetName;
	public String placeZipCode;
	public String placeCity;
	public String placeCountry;
	public String access;
	public String accessForDisabled;
	public boolean accessForBlind;
	public boolean accessForDeaf;
	public boolean accessForWheelchair;
	public boolean accessForElder;
	public boolean accessForDeficient;
	public String promoter;
	public String phone;
	public String email;
	public String websiteURL;
	public String websiteName;
	public int free;
	public String price;
	public String source;
	public long displayDate;
	public String scheduleComments;
	public long firstStartDate;
	public long lastEndDate;
	public long imageId;
}