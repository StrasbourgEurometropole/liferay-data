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

package eu.strasbourg.service.activity.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.activity.model.ActivityCoursePlace;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ActivityCoursePlace in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCoursePlace
 * @generated
 */
@ProviderType
public class ActivityCoursePlaceCacheModel implements CacheModel<ActivityCoursePlace>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ActivityCoursePlaceCacheModel)) {
			return false;
		}

		ActivityCoursePlaceCacheModel activityCoursePlaceCacheModel = (ActivityCoursePlaceCacheModel)obj;

		if (activityCoursePlaceId == activityCoursePlaceCacheModel.activityCoursePlaceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, activityCoursePlaceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", activityCoursePlaceId=");
		sb.append(activityCoursePlaceId);
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
		sb.append(", activityCourseId=");
		sb.append(activityCourseId);
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
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ActivityCoursePlace toEntityModel() {
		ActivityCoursePlaceImpl activityCoursePlaceImpl = new ActivityCoursePlaceImpl();

		if (uuid == null) {
			activityCoursePlaceImpl.setUuid(StringPool.BLANK);
		}
		else {
			activityCoursePlaceImpl.setUuid(uuid);
		}

		activityCoursePlaceImpl.setActivityCoursePlaceId(activityCoursePlaceId);
		activityCoursePlaceImpl.setGroupId(groupId);
		activityCoursePlaceImpl.setCompanyId(companyId);
		activityCoursePlaceImpl.setUserId(userId);

		if (userName == null) {
			activityCoursePlaceImpl.setUserName(StringPool.BLANK);
		}
		else {
			activityCoursePlaceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			activityCoursePlaceImpl.setCreateDate(null);
		}
		else {
			activityCoursePlaceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			activityCoursePlaceImpl.setModifiedDate(null);
		}
		else {
			activityCoursePlaceImpl.setModifiedDate(new Date(modifiedDate));
		}

		activityCoursePlaceImpl.setActivityCourseId(activityCourseId);

		if (placeSIGId == null) {
			activityCoursePlaceImpl.setPlaceSIGId(StringPool.BLANK);
		}
		else {
			activityCoursePlaceImpl.setPlaceSIGId(placeSIGId);
		}

		if (placeName == null) {
			activityCoursePlaceImpl.setPlaceName(StringPool.BLANK);
		}
		else {
			activityCoursePlaceImpl.setPlaceName(placeName);
		}

		if (placeStreetNumber == null) {
			activityCoursePlaceImpl.setPlaceStreetNumber(StringPool.BLANK);
		}
		else {
			activityCoursePlaceImpl.setPlaceStreetNumber(placeStreetNumber);
		}

		if (placeStreetName == null) {
			activityCoursePlaceImpl.setPlaceStreetName(StringPool.BLANK);
		}
		else {
			activityCoursePlaceImpl.setPlaceStreetName(placeStreetName);
		}

		if (placeZipCode == null) {
			activityCoursePlaceImpl.setPlaceZipCode(StringPool.BLANK);
		}
		else {
			activityCoursePlaceImpl.setPlaceZipCode(placeZipCode);
		}

		activityCoursePlaceImpl.setPlaceCityId(placeCityId);

		activityCoursePlaceImpl.resetOriginalValues();

		return activityCoursePlaceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		activityCoursePlaceId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		activityCourseId = objectInput.readLong();
		placeSIGId = objectInput.readUTF();
		placeName = objectInput.readUTF();
		placeStreetNumber = objectInput.readUTF();
		placeStreetName = objectInput.readUTF();
		placeZipCode = objectInput.readUTF();

		placeCityId = objectInput.readLong();
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

		objectOutput.writeLong(activityCoursePlaceId);

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

		objectOutput.writeLong(activityCourseId);

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
	}

	public String uuid;
	public long activityCoursePlaceId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long activityCourseId;
	public String placeSIGId;
	public String placeName;
	public String placeStreetNumber;
	public String placeStreetName;
	public String placeZipCode;
	public long placeCityId;
}