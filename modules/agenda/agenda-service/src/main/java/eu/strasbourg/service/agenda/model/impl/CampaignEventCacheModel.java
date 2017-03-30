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

import eu.strasbourg.service.agenda.model.CampaignEvent;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CampaignEvent in entity cache.
 *
 * @author BenjaminBini
 * @see CampaignEvent
 * @generated
 */
@ProviderType
public class CampaignEventCacheModel implements CacheModel<CampaignEvent>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CampaignEventCacheModel)) {
			return false;
		}

		CampaignEventCacheModel campaignEventCacheModel = (CampaignEventCacheModel)obj;

		if (campaignEventId == campaignEventCacheModel.campaignEventId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, campaignEventId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(89);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", campaignEventId=");
		sb.append(campaignEventId);
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
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", phone=");
		sb.append(phone);
		sb.append(", email=");
		sb.append(email);
		sb.append(", serviceId=");
		sb.append(serviceId);
		sb.append(", service=");
		sb.append(service);
		sb.append(", onSiteFirstName=");
		sb.append(onSiteFirstName);
		sb.append(", onSiteLastName=");
		sb.append(onSiteLastName);
		sb.append(", onSitePhone=");
		sb.append(onSitePhone);
		sb.append(", title=");
		sb.append(title);
		sb.append(", subtitle=");
		sb.append(subtitle);
		sb.append(", description=");
		sb.append(description);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append(", webImageId=");
		sb.append(webImageId);
		sb.append(", imageOwner=");
		sb.append(imageOwner);
		sb.append(", manifestationsIds=");
		sb.append(manifestationsIds);
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
		sb.append(", placeCityId=");
		sb.append(placeCityId);
		sb.append(", placeCountry=");
		sb.append(placeCountry);
		sb.append(", promoter=");
		sb.append(promoter);
		sb.append(", publicPhone=");
		sb.append(publicPhone);
		sb.append(", publicEmail=");
		sb.append(publicEmail);
		sb.append(", websiteURL=");
		sb.append(websiteURL);
		sb.append(", websiteName=");
		sb.append(websiteName);
		sb.append(", free=");
		sb.append(free);
		sb.append(", price=");
		sb.append(price);
		sb.append(", campaignId=");
		sb.append(campaignId);
		sb.append(", themeId=");
		sb.append(themeId);
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append(", publicsIds=");
		sb.append(publicsIds);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CampaignEvent toEntityModel() {
		CampaignEventImpl campaignEventImpl = new CampaignEventImpl();

		if (uuid == null) {
			campaignEventImpl.setUuid(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setUuid(uuid);
		}

		campaignEventImpl.setCampaignEventId(campaignEventId);
		campaignEventImpl.setGroupId(groupId);
		campaignEventImpl.setCompanyId(companyId);
		campaignEventImpl.setUserId(userId);

		if (userName == null) {
			campaignEventImpl.setUserName(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			campaignEventImpl.setCreateDate(null);
		}
		else {
			campaignEventImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			campaignEventImpl.setModifiedDate(null);
		}
		else {
			campaignEventImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			campaignEventImpl.setLastPublishDate(null);
		}
		else {
			campaignEventImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		campaignEventImpl.setStatus(status);

		if (firstName == null) {
			campaignEventImpl.setFirstName(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setFirstName(firstName);
		}

		if (lastName == null) {
			campaignEventImpl.setLastName(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setLastName(lastName);
		}

		if (phone == null) {
			campaignEventImpl.setPhone(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setPhone(phone);
		}

		if (email == null) {
			campaignEventImpl.setEmail(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setEmail(email);
		}

		campaignEventImpl.setServiceId(serviceId);

		if (service == null) {
			campaignEventImpl.setService(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setService(service);
		}

		if (onSiteFirstName == null) {
			campaignEventImpl.setOnSiteFirstName(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setOnSiteFirstName(onSiteFirstName);
		}

		if (onSiteLastName == null) {
			campaignEventImpl.setOnSiteLastName(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setOnSiteLastName(onSiteLastName);
		}

		if (onSitePhone == null) {
			campaignEventImpl.setOnSitePhone(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setOnSitePhone(onSitePhone);
		}

		if (title == null) {
			campaignEventImpl.setTitle(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setTitle(title);
		}

		if (subtitle == null) {
			campaignEventImpl.setSubtitle(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setSubtitle(subtitle);
		}

		if (description == null) {
			campaignEventImpl.setDescription(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setDescription(description);
		}

		campaignEventImpl.setImageId(imageId);
		campaignEventImpl.setWebImageId(webImageId);

		if (imageOwner == null) {
			campaignEventImpl.setImageOwner(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setImageOwner(imageOwner);
		}

		if (manifestationsIds == null) {
			campaignEventImpl.setManifestationsIds(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setManifestationsIds(manifestationsIds);
		}

		if (placeSIGId == null) {
			campaignEventImpl.setPlaceSIGId(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setPlaceSIGId(placeSIGId);
		}

		if (placeName == null) {
			campaignEventImpl.setPlaceName(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setPlaceName(placeName);
		}

		if (placeStreetNumber == null) {
			campaignEventImpl.setPlaceStreetNumber(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setPlaceStreetNumber(placeStreetNumber);
		}

		if (placeStreetName == null) {
			campaignEventImpl.setPlaceStreetName(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setPlaceStreetName(placeStreetName);
		}

		if (placeZipCode == null) {
			campaignEventImpl.setPlaceZipCode(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setPlaceZipCode(placeZipCode);
		}

		campaignEventImpl.setPlaceCityId(placeCityId);

		if (placeCountry == null) {
			campaignEventImpl.setPlaceCountry(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setPlaceCountry(placeCountry);
		}

		if (promoter == null) {
			campaignEventImpl.setPromoter(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setPromoter(promoter);
		}

		if (publicPhone == null) {
			campaignEventImpl.setPublicPhone(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setPublicPhone(publicPhone);
		}

		if (publicEmail == null) {
			campaignEventImpl.setPublicEmail(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setPublicEmail(publicEmail);
		}

		if (websiteURL == null) {
			campaignEventImpl.setWebsiteURL(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setWebsiteURL(websiteURL);
		}

		if (websiteName == null) {
			campaignEventImpl.setWebsiteName(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setWebsiteName(websiteName);
		}

		campaignEventImpl.setFree(free);

		if (price == null) {
			campaignEventImpl.setPrice(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setPrice(price);
		}

		campaignEventImpl.setCampaignId(campaignId);
		campaignEventImpl.setThemeId(themeId);
		campaignEventImpl.setTypeId(typeId);

		if (publicsIds == null) {
			campaignEventImpl.setPublicsIds(StringPool.BLANK);
		}
		else {
			campaignEventImpl.setPublicsIds(publicsIds);
		}

		campaignEventImpl.resetOriginalValues();

		return campaignEventImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		campaignEventId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		lastPublishDate = objectInput.readLong();

		status = objectInput.readInt();
		firstName = objectInput.readUTF();
		lastName = objectInput.readUTF();
		phone = objectInput.readUTF();
		email = objectInput.readUTF();

		serviceId = objectInput.readLong();
		service = objectInput.readUTF();
		onSiteFirstName = objectInput.readUTF();
		onSiteLastName = objectInput.readUTF();
		onSitePhone = objectInput.readUTF();
		title = objectInput.readUTF();
		subtitle = objectInput.readUTF();
		description = objectInput.readUTF();

		imageId = objectInput.readLong();

		webImageId = objectInput.readLong();
		imageOwner = objectInput.readUTF();
		manifestationsIds = objectInput.readUTF();
		placeSIGId = objectInput.readUTF();
		placeName = objectInput.readUTF();
		placeStreetNumber = objectInput.readUTF();
		placeStreetName = objectInput.readUTF();
		placeZipCode = objectInput.readUTF();

		placeCityId = objectInput.readLong();
		placeCountry = objectInput.readUTF();
		promoter = objectInput.readUTF();
		publicPhone = objectInput.readUTF();
		publicEmail = objectInput.readUTF();
		websiteURL = objectInput.readUTF();
		websiteName = objectInput.readUTF();

		free = objectInput.readInt();
		price = objectInput.readUTF();

		campaignId = objectInput.readLong();

		themeId = objectInput.readLong();

		typeId = objectInput.readLong();
		publicsIds = objectInput.readUTF();
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

		objectOutput.writeLong(campaignEventId);

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

		if (firstName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(firstName);
		}

		if (lastName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lastName);
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

		objectOutput.writeLong(serviceId);

		if (service == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(service);
		}

		if (onSiteFirstName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(onSiteFirstName);
		}

		if (onSiteLastName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(onSiteLastName);
		}

		if (onSitePhone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(onSitePhone);
		}

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

		objectOutput.writeLong(imageId);

		objectOutput.writeLong(webImageId);

		if (imageOwner == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(imageOwner);
		}

		if (manifestationsIds == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(manifestationsIds);
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

		objectOutput.writeLong(placeCityId);

		if (placeCountry == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(placeCountry);
		}

		if (promoter == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(promoter);
		}

		if (publicPhone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publicPhone);
		}

		if (publicEmail == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publicEmail);
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

		objectOutput.writeLong(campaignId);

		objectOutput.writeLong(themeId);

		objectOutput.writeLong(typeId);

		if (publicsIds == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publicsIds);
		}
	}

	public String uuid;
	public long campaignEventId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long lastPublishDate;
	public int status;
	public String firstName;
	public String lastName;
	public String phone;
	public String email;
	public long serviceId;
	public String service;
	public String onSiteFirstName;
	public String onSiteLastName;
	public String onSitePhone;
	public String title;
	public String subtitle;
	public String description;
	public long imageId;
	public long webImageId;
	public String imageOwner;
	public String manifestationsIds;
	public String placeSIGId;
	public String placeName;
	public String placeStreetNumber;
	public String placeStreetName;
	public String placeZipCode;
	public long placeCityId;
	public String placeCountry;
	public String promoter;
	public String publicPhone;
	public String publicEmail;
	public String websiteURL;
	public String websiteName;
	public int free;
	public String price;
	public long campaignId;
	public long themeId;
	public long typeId;
	public String publicsIds;
}