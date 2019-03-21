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

package eu.strasbourg.service.place.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.place.model.Place;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Place in entity cache.
 *
 * @author Angelique Zunino Champougny
 * @see Place
 * @generated
 */
@ProviderType
public class PlaceCacheModel implements CacheModel<Place>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PlaceCacheModel)) {
			return false;
		}

		PlaceCacheModel placeCacheModel = (PlaceCacheModel)obj;

		if (placeId == placeCacheModel.placeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, placeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(125);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", placeId=");
		sb.append(placeId);
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
		sb.append(", SIGid=");
		sb.append(SIGid);
		sb.append(", name=");
		sb.append(name);
		sb.append(", addressComplement=");
		sb.append(addressComplement);
		sb.append(", addressStreet=");
		sb.append(addressStreet);
		sb.append(", addressDistribution=");
		sb.append(addressDistribution);
		sb.append(", addressZipCode=");
		sb.append(addressZipCode);
		sb.append(", addressCountry=");
		sb.append(addressCountry);
		sb.append(", mercatorX=");
		sb.append(mercatorX);
		sb.append(", mercatorY=");
		sb.append(mercatorY);
		sb.append(", RGF93X=");
		sb.append(RGF93X);
		sb.append(", RGF93Y=");
		sb.append(RGF93Y);
		sb.append(", alias=");
		sb.append(alias);
		sb.append(", presentation=");
		sb.append(presentation);
		sb.append(", serviceAndActivities=");
		sb.append(serviceAndActivities);
		sb.append(", characteristics=");
		sb.append(characteristics);
		sb.append(", subjectToPublicHoliday=");
		sb.append(subjectToPublicHoliday);
		sb.append(", exceptionalSchedule=");
		sb.append(exceptionalSchedule);
		sb.append(", displayEvents=");
		sb.append(displayEvents);
		sb.append(", additionalInformation=");
		sb.append(additionalInformation);
		sb.append(", contenuTooltipCarto=");
		sb.append(contenuTooltipCarto);
		sb.append(", phone=");
		sb.append(phone);
		sb.append(", mail=");
		sb.append(mail);
		sb.append(", siteURL=");
		sb.append(siteURL);
		sb.append(", siteLabel=");
		sb.append(siteLabel);
		sb.append(", facebookURL=");
		sb.append(facebookURL);
		sb.append(", facebookLabel=");
		sb.append(facebookLabel);
		sb.append(", accesMap=");
		sb.append(accesMap);
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
		sb.append(", RTEnabled=");
		sb.append(RTEnabled);
		sb.append(", RTType=");
		sb.append(RTType);
		sb.append(", RTExternalId=");
		sb.append(RTExternalId);
		sb.append(", RTAvailable=");
		sb.append(RTAvailable);
		sb.append(", RTOccupation=");
		sb.append(RTOccupation);
		sb.append(", RTCapacity=");
		sb.append(RTCapacity);
		sb.append(", RTStatus=");
		sb.append(RTStatus);
		sb.append(", RTLastUpdate=");
		sb.append(RTLastUpdate);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append(", imageWidth=");
		sb.append(imageWidth);
		sb.append(", imageHeight=");
		sb.append(imageHeight);
		sb.append(", imageIds=");
		sb.append(imageIds);
		sb.append(", videosIds=");
		sb.append(videosIds);
		sb.append(", priceId=");
		sb.append(priceId);
		sb.append(", documentsIds=");
		sb.append(documentsIds);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Place toEntityModel() {
		PlaceImpl placeImpl = new PlaceImpl();

		if (uuid == null) {
			placeImpl.setUuid(StringPool.BLANK);
		}
		else {
			placeImpl.setUuid(uuid);
		}

		placeImpl.setPlaceId(placeId);
		placeImpl.setGroupId(groupId);
		placeImpl.setCompanyId(companyId);
		placeImpl.setUserId(userId);

		if (userName == null) {
			placeImpl.setUserName(StringPool.BLANK);
		}
		else {
			placeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			placeImpl.setCreateDate(null);
		}
		else {
			placeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			placeImpl.setModifiedDate(null);
		}
		else {
			placeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			placeImpl.setLastPublishDate(null);
		}
		else {
			placeImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		placeImpl.setStatus(status);
		placeImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			placeImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			placeImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			placeImpl.setStatusDate(null);
		}
		else {
			placeImpl.setStatusDate(new Date(statusDate));
		}

		if (SIGid == null) {
			placeImpl.setSIGid(StringPool.BLANK);
		}
		else {
			placeImpl.setSIGid(SIGid);
		}

		if (name == null) {
			placeImpl.setName(StringPool.BLANK);
		}
		else {
			placeImpl.setName(name);
		}

		if (addressComplement == null) {
			placeImpl.setAddressComplement(StringPool.BLANK);
		}
		else {
			placeImpl.setAddressComplement(addressComplement);
		}

		if (addressStreet == null) {
			placeImpl.setAddressStreet(StringPool.BLANK);
		}
		else {
			placeImpl.setAddressStreet(addressStreet);
		}

		if (addressDistribution == null) {
			placeImpl.setAddressDistribution(StringPool.BLANK);
		}
		else {
			placeImpl.setAddressDistribution(addressDistribution);
		}

		if (addressZipCode == null) {
			placeImpl.setAddressZipCode(StringPool.BLANK);
		}
		else {
			placeImpl.setAddressZipCode(addressZipCode);
		}

		if (addressCountry == null) {
			placeImpl.setAddressCountry(StringPool.BLANK);
		}
		else {
			placeImpl.setAddressCountry(addressCountry);
		}

		if (mercatorX == null) {
			placeImpl.setMercatorX(StringPool.BLANK);
		}
		else {
			placeImpl.setMercatorX(mercatorX);
		}

		if (mercatorY == null) {
			placeImpl.setMercatorY(StringPool.BLANK);
		}
		else {
			placeImpl.setMercatorY(mercatorY);
		}

		if (RGF93X == null) {
			placeImpl.setRGF93X(StringPool.BLANK);
		}
		else {
			placeImpl.setRGF93X(RGF93X);
		}

		if (RGF93Y == null) {
			placeImpl.setRGF93Y(StringPool.BLANK);
		}
		else {
			placeImpl.setRGF93Y(RGF93Y);
		}

		if (alias == null) {
			placeImpl.setAlias(StringPool.BLANK);
		}
		else {
			placeImpl.setAlias(alias);
		}

		if (presentation == null) {
			placeImpl.setPresentation(StringPool.BLANK);
		}
		else {
			placeImpl.setPresentation(presentation);
		}

		if (serviceAndActivities == null) {
			placeImpl.setServiceAndActivities(StringPool.BLANK);
		}
		else {
			placeImpl.setServiceAndActivities(serviceAndActivities);
		}

		if (characteristics == null) {
			placeImpl.setCharacteristics(StringPool.BLANK);
		}
		else {
			placeImpl.setCharacteristics(characteristics);
		}

		placeImpl.setSubjectToPublicHoliday(subjectToPublicHoliday);

		if (exceptionalSchedule == null) {
			placeImpl.setExceptionalSchedule(StringPool.BLANK);
		}
		else {
			placeImpl.setExceptionalSchedule(exceptionalSchedule);
		}

		placeImpl.setDisplayEvents(displayEvents);

		if (additionalInformation == null) {
			placeImpl.setAdditionalInformation(StringPool.BLANK);
		}
		else {
			placeImpl.setAdditionalInformation(additionalInformation);
		}

		if (contenuTooltipCarto == null) {
			placeImpl.setContenuTooltipCarto(StringPool.BLANK);
		}
		else {
			placeImpl.setContenuTooltipCarto(contenuTooltipCarto);
		}

		if (phone == null) {
			placeImpl.setPhone(StringPool.BLANK);
		}
		else {
			placeImpl.setPhone(phone);
		}

		if (mail == null) {
			placeImpl.setMail(StringPool.BLANK);
		}
		else {
			placeImpl.setMail(mail);
		}

		if (siteURL == null) {
			placeImpl.setSiteURL(StringPool.BLANK);
		}
		else {
			placeImpl.setSiteURL(siteURL);
		}

		if (siteLabel == null) {
			placeImpl.setSiteLabel(StringPool.BLANK);
		}
		else {
			placeImpl.setSiteLabel(siteLabel);
		}

		if (facebookURL == null) {
			placeImpl.setFacebookURL(StringPool.BLANK);
		}
		else {
			placeImpl.setFacebookURL(facebookURL);
		}

		if (facebookLabel == null) {
			placeImpl.setFacebookLabel(StringPool.BLANK);
		}
		else {
			placeImpl.setFacebookLabel(facebookLabel);
		}

		if (accesMap == null) {
			placeImpl.setAccesMap(StringPool.BLANK);
		}
		else {
			placeImpl.setAccesMap(accesMap);
		}

		if (access == null) {
			placeImpl.setAccess(StringPool.BLANK);
		}
		else {
			placeImpl.setAccess(access);
		}

		if (accessForDisabled == null) {
			placeImpl.setAccessForDisabled(StringPool.BLANK);
		}
		else {
			placeImpl.setAccessForDisabled(accessForDisabled);
		}

		placeImpl.setAccessForBlind(accessForBlind);
		placeImpl.setAccessForDeaf(accessForDeaf);
		placeImpl.setAccessForWheelchair(accessForWheelchair);
		placeImpl.setAccessForElder(accessForElder);
		placeImpl.setAccessForDeficient(accessForDeficient);
		placeImpl.setRTEnabled(RTEnabled);

		if (RTType == null) {
			placeImpl.setRTType(StringPool.BLANK);
		}
		else {
			placeImpl.setRTType(RTType);
		}

		if (RTExternalId == null) {
			placeImpl.setRTExternalId(StringPool.BLANK);
		}
		else {
			placeImpl.setRTExternalId(RTExternalId);
		}

		placeImpl.setRTAvailable(RTAvailable);
		placeImpl.setRTOccupation(RTOccupation);
		placeImpl.setRTCapacity(RTCapacity);

		if (RTStatus == null) {
			placeImpl.setRTStatus(StringPool.BLANK);
		}
		else {
			placeImpl.setRTStatus(RTStatus);
		}

		if (RTLastUpdate == Long.MIN_VALUE) {
			placeImpl.setRTLastUpdate(null);
		}
		else {
			placeImpl.setRTLastUpdate(new Date(RTLastUpdate));
		}

		placeImpl.setImageId(imageId);
		placeImpl.setImageWidth(imageWidth);
		placeImpl.setImageHeight(imageHeight);

		if (imageIds == null) {
			placeImpl.setImageIds(StringPool.BLANK);
		}
		else {
			placeImpl.setImageIds(imageIds);
		}

		if (videosIds == null) {
			placeImpl.setVideosIds(StringPool.BLANK);
		}
		else {
			placeImpl.setVideosIds(videosIds);
		}

		placeImpl.setPriceId(priceId);

		if (documentsIds == null) {
			placeImpl.setDocumentsIds(StringPool.BLANK);
		}
		else {
			placeImpl.setDocumentsIds(documentsIds);
		}

		placeImpl.resetOriginalValues();

		return placeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		placeId = objectInput.readLong();

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
		SIGid = objectInput.readUTF();
		name = objectInput.readUTF();
		addressComplement = objectInput.readUTF();
		addressStreet = objectInput.readUTF();
		addressDistribution = objectInput.readUTF();
		addressZipCode = objectInput.readUTF();
		addressCountry = objectInput.readUTF();
		mercatorX = objectInput.readUTF();
		mercatorY = objectInput.readUTF();
		RGF93X = objectInput.readUTF();
		RGF93Y = objectInput.readUTF();
		alias = objectInput.readUTF();
		presentation = objectInput.readUTF();
		serviceAndActivities = objectInput.readUTF();
		characteristics = objectInput.readUTF();

		subjectToPublicHoliday = objectInput.readBoolean();
		exceptionalSchedule = objectInput.readUTF();

		displayEvents = objectInput.readBoolean();
		additionalInformation = objectInput.readUTF();
		contenuTooltipCarto = objectInput.readUTF();
		phone = objectInput.readUTF();
		mail = objectInput.readUTF();
		siteURL = objectInput.readUTF();
		siteLabel = objectInput.readUTF();
		facebookURL = objectInput.readUTF();
		facebookLabel = objectInput.readUTF();
		accesMap = objectInput.readUTF();
		access = objectInput.readUTF();
		accessForDisabled = objectInput.readUTF();

		accessForBlind = objectInput.readBoolean();

		accessForDeaf = objectInput.readBoolean();

		accessForWheelchair = objectInput.readBoolean();

		accessForElder = objectInput.readBoolean();

		accessForDeficient = objectInput.readBoolean();

		RTEnabled = objectInput.readBoolean();
		RTType = objectInput.readUTF();
		RTExternalId = objectInput.readUTF();

		RTAvailable = objectInput.readLong();

		RTOccupation = objectInput.readLong();

		RTCapacity = objectInput.readLong();
		RTStatus = objectInput.readUTF();
		RTLastUpdate = objectInput.readLong();

		imageId = objectInput.readLong();

		imageWidth = objectInput.readInt();

		imageHeight = objectInput.readInt();
		imageIds = objectInput.readUTF();
		videosIds = objectInput.readUTF();

		priceId = objectInput.readLong();
		documentsIds = objectInput.readUTF();
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

		objectOutput.writeLong(placeId);

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

		if (SIGid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(SIGid);
		}

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (addressComplement == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addressComplement);
		}

		if (addressStreet == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addressStreet);
		}

		if (addressDistribution == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addressDistribution);
		}

		if (addressZipCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addressZipCode);
		}

		if (addressCountry == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addressCountry);
		}

		if (mercatorX == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(mercatorX);
		}

		if (mercatorY == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(mercatorY);
		}

		if (RGF93X == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(RGF93X);
		}

		if (RGF93Y == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(RGF93Y);
		}

		if (alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(alias);
		}

		if (presentation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(presentation);
		}

		if (serviceAndActivities == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(serviceAndActivities);
		}

		if (characteristics == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(characteristics);
		}

		objectOutput.writeBoolean(subjectToPublicHoliday);

		if (exceptionalSchedule == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(exceptionalSchedule);
		}

		objectOutput.writeBoolean(displayEvents);

		if (additionalInformation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(additionalInformation);
		}

		if (contenuTooltipCarto == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contenuTooltipCarto);
		}

		if (phone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(phone);
		}

		if (mail == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(mail);
		}

		if (siteURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(siteURL);
		}

		if (siteLabel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(siteLabel);
		}

		if (facebookURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(facebookURL);
		}

		if (facebookLabel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(facebookLabel);
		}

		if (accesMap == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accesMap);
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

		objectOutput.writeBoolean(RTEnabled);

		if (RTType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(RTType);
		}

		if (RTExternalId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(RTExternalId);
		}

		objectOutput.writeLong(RTAvailable);

		objectOutput.writeLong(RTOccupation);

		objectOutput.writeLong(RTCapacity);

		if (RTStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(RTStatus);
		}

		objectOutput.writeLong(RTLastUpdate);

		objectOutput.writeLong(imageId);

		objectOutput.writeInt(imageWidth);

		objectOutput.writeInt(imageHeight);

		if (imageIds == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(imageIds);
		}

		if (videosIds == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(videosIds);
		}

		objectOutput.writeLong(priceId);

		if (documentsIds == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(documentsIds);
		}
	}

	public String uuid;
	public long placeId;
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
	public String SIGid;
	public String name;
	public String addressComplement;
	public String addressStreet;
	public String addressDistribution;
	public String addressZipCode;
	public String addressCountry;
	public String mercatorX;
	public String mercatorY;
	public String RGF93X;
	public String RGF93Y;
	public String alias;
	public String presentation;
	public String serviceAndActivities;
	public String characteristics;
	public boolean subjectToPublicHoliday;
	public String exceptionalSchedule;
	public boolean displayEvents;
	public String additionalInformation;
	public String contenuTooltipCarto;
	public String phone;
	public String mail;
	public String siteURL;
	public String siteLabel;
	public String facebookURL;
	public String facebookLabel;
	public String accesMap;
	public String access;
	public String accessForDisabled;
	public boolean accessForBlind;
	public boolean accessForDeaf;
	public boolean accessForWheelchair;
	public boolean accessForElder;
	public boolean accessForDeficient;
	public boolean RTEnabled;
	public String RTType;
	public String RTExternalId;
	public long RTAvailable;
	public long RTOccupation;
	public long RTCapacity;
	public String RTStatus;
	public long RTLastUpdate;
	public long imageId;
	public int imageWidth;
	public int imageHeight;
	public String imageIds;
	public String videosIds;
	public long priceId;
	public String documentsIds;
}