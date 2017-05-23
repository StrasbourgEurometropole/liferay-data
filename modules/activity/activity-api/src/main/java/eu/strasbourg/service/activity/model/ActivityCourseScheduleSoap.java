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

package eu.strasbourg.service.activity.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.activity.service.http.ActivityCourseScheduleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.activity.service.http.ActivityCourseScheduleServiceSoap
 * @generated
 */
@ProviderType
public class ActivityCourseScheduleSoap implements Serializable {
	public static ActivityCourseScheduleSoap toSoapModel(
		ActivityCourseSchedule model) {
		ActivityCourseScheduleSoap soapModel = new ActivityCourseScheduleSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setActivityCourseScheduleId(model.getActivityCourseScheduleId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setActivityCoursePlaceId(model.getActivityCoursePlaceId());
		soapModel.setStartTime(model.getStartTime());
		soapModel.setEndTime(model.getEndTime());
		soapModel.setDays(model.getDays());
		soapModel.setComments(model.getComments());
		soapModel.setPeriodsIds(model.getPeriodsIds());

		return soapModel;
	}

	public static ActivityCourseScheduleSoap[] toSoapModels(
		ActivityCourseSchedule[] models) {
		ActivityCourseScheduleSoap[] soapModels = new ActivityCourseScheduleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ActivityCourseScheduleSoap[][] toSoapModels(
		ActivityCourseSchedule[][] models) {
		ActivityCourseScheduleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ActivityCourseScheduleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ActivityCourseScheduleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ActivityCourseScheduleSoap[] toSoapModels(
		List<ActivityCourseSchedule> models) {
		List<ActivityCourseScheduleSoap> soapModels = new ArrayList<ActivityCourseScheduleSoap>(models.size());

		for (ActivityCourseSchedule model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ActivityCourseScheduleSoap[soapModels.size()]);
	}

	public ActivityCourseScheduleSoap() {
	}

	public long getPrimaryKey() {
		return _activityCourseScheduleId;
	}

	public void setPrimaryKey(long pk) {
		setActivityCourseScheduleId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getActivityCourseScheduleId() {
		return _activityCourseScheduleId;
	}

	public void setActivityCourseScheduleId(long activityCourseScheduleId) {
		_activityCourseScheduleId = activityCourseScheduleId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getActivityCoursePlaceId() {
		return _activityCoursePlaceId;
	}

	public void setActivityCoursePlaceId(long activityCoursePlaceId) {
		_activityCoursePlaceId = activityCoursePlaceId;
	}

	public String getStartTime() {
		return _startTime;
	}

	public void setStartTime(String startTime) {
		_startTime = startTime;
	}

	public String getEndTime() {
		return _endTime;
	}

	public void setEndTime(String endTime) {
		_endTime = endTime;
	}

	public String getDays() {
		return _days;
	}

	public void setDays(String days) {
		_days = days;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public String getPeriodsIds() {
		return _periodsIds;
	}

	public void setPeriodsIds(String periodsIds) {
		_periodsIds = periodsIds;
	}

	private String _uuid;
	private long _activityCourseScheduleId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _activityCoursePlaceId;
	private String _startTime;
	private String _endTime;
	private String _days;
	private String _comments;
	private String _periodsIds;
}