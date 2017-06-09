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

import eu.strasbourg.service.activity.model.ActivityCourseSchedule;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ActivityCourseSchedule in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCourseSchedule
 * @generated
 */
@ProviderType
public class ActivityCourseScheduleCacheModel implements CacheModel<ActivityCourseSchedule>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ActivityCourseScheduleCacheModel)) {
			return false;
		}

		ActivityCourseScheduleCacheModel activityCourseScheduleCacheModel = (ActivityCourseScheduleCacheModel)obj;

		if (activityCourseScheduleId == activityCourseScheduleCacheModel.activityCourseScheduleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, activityCourseScheduleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", activityCourseScheduleId=");
		sb.append(activityCourseScheduleId);
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
		sb.append(", activityCoursePlaceId=");
		sb.append(activityCoursePlaceId);
		sb.append(", startTime=");
		sb.append(startTime);
		sb.append(", endTime=");
		sb.append(endTime);
		sb.append(", monday=");
		sb.append(monday);
		sb.append(", tuesday=");
		sb.append(tuesday);
		sb.append(", wednesday=");
		sb.append(wednesday);
		sb.append(", thursday=");
		sb.append(thursday);
		sb.append(", friday=");
		sb.append(friday);
		sb.append(", saturday=");
		sb.append(saturday);
		sb.append(", sunday=");
		sb.append(sunday);
		sb.append(", comments=");
		sb.append(comments);
		sb.append(", periodsIds=");
		sb.append(periodsIds);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ActivityCourseSchedule toEntityModel() {
		ActivityCourseScheduleImpl activityCourseScheduleImpl = new ActivityCourseScheduleImpl();

		if (uuid == null) {
			activityCourseScheduleImpl.setUuid(StringPool.BLANK);
		}
		else {
			activityCourseScheduleImpl.setUuid(uuid);
		}

		activityCourseScheduleImpl.setActivityCourseScheduleId(activityCourseScheduleId);
		activityCourseScheduleImpl.setGroupId(groupId);
		activityCourseScheduleImpl.setCompanyId(companyId);
		activityCourseScheduleImpl.setUserId(userId);

		if (userName == null) {
			activityCourseScheduleImpl.setUserName(StringPool.BLANK);
		}
		else {
			activityCourseScheduleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			activityCourseScheduleImpl.setCreateDate(null);
		}
		else {
			activityCourseScheduleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			activityCourseScheduleImpl.setModifiedDate(null);
		}
		else {
			activityCourseScheduleImpl.setModifiedDate(new Date(modifiedDate));
		}

		activityCourseScheduleImpl.setActivityCoursePlaceId(activityCoursePlaceId);

		if (startTime == null) {
			activityCourseScheduleImpl.setStartTime(StringPool.BLANK);
		}
		else {
			activityCourseScheduleImpl.setStartTime(startTime);
		}

		if (endTime == null) {
			activityCourseScheduleImpl.setEndTime(StringPool.BLANK);
		}
		else {
			activityCourseScheduleImpl.setEndTime(endTime);
		}

		activityCourseScheduleImpl.setMonday(monday);
		activityCourseScheduleImpl.setTuesday(tuesday);
		activityCourseScheduleImpl.setWednesday(wednesday);
		activityCourseScheduleImpl.setThursday(thursday);
		activityCourseScheduleImpl.setFriday(friday);
		activityCourseScheduleImpl.setSaturday(saturday);
		activityCourseScheduleImpl.setSunday(sunday);

		if (comments == null) {
			activityCourseScheduleImpl.setComments(StringPool.BLANK);
		}
		else {
			activityCourseScheduleImpl.setComments(comments);
		}

		if (periodsIds == null) {
			activityCourseScheduleImpl.setPeriodsIds(StringPool.BLANK);
		}
		else {
			activityCourseScheduleImpl.setPeriodsIds(periodsIds);
		}

		activityCourseScheduleImpl.resetOriginalValues();

		return activityCourseScheduleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		activityCourseScheduleId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		activityCoursePlaceId = objectInput.readLong();
		startTime = objectInput.readUTF();
		endTime = objectInput.readUTF();

		monday = objectInput.readBoolean();

		tuesday = objectInput.readBoolean();

		wednesday = objectInput.readBoolean();

		thursday = objectInput.readBoolean();

		friday = objectInput.readBoolean();

		saturday = objectInput.readBoolean();

		sunday = objectInput.readBoolean();
		comments = objectInput.readUTF();
		periodsIds = objectInput.readUTF();
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

		objectOutput.writeLong(activityCourseScheduleId);

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

		objectOutput.writeLong(activityCoursePlaceId);

		if (startTime == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(startTime);
		}

		if (endTime == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(endTime);
		}

		objectOutput.writeBoolean(monday);

		objectOutput.writeBoolean(tuesday);

		objectOutput.writeBoolean(wednesday);

		objectOutput.writeBoolean(thursday);

		objectOutput.writeBoolean(friday);

		objectOutput.writeBoolean(saturday);

		objectOutput.writeBoolean(sunday);

		if (comments == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(comments);
		}

		if (periodsIds == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(periodsIds);
		}
	}

	public String uuid;
	public long activityCourseScheduleId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long activityCoursePlaceId;
	public String startTime;
	public String endTime;
	public boolean monday;
	public boolean tuesday;
	public boolean wednesday;
	public boolean thursday;
	public boolean friday;
	public boolean saturday;
	public boolean sunday;
	public String comments;
	public String periodsIds;
}