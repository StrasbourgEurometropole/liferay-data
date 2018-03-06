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
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.activity.service.http.ActivityCourseServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.activity.service.http.ActivityCourseServiceSoap
 * @generated
 */
@ProviderType
public class ActivityCourseSoap implements Serializable {
	public static ActivityCourseSoap toSoapModel(ActivityCourse model) {
		ActivityCourseSoap soapModel = new ActivityCourseSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setActivityCourseId(model.getActivityCourseId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setName(model.getName());
		soapModel.setPresentation(model.getPresentation());
		soapModel.setArrangements(model.getArrangements());
		soapModel.setPrice(model.getPrice());
		soapModel.setActivityId(model.getActivityId());
		soapModel.setOrganizerId(model.getOrganizerId());
		soapModel.setImageId(model.getImageId());
		soapModel.setImageIds(model.getImageIds());
		soapModel.setVideosIds(model.getVideosIds());
		soapModel.setDocumentsIds(model.getDocumentsIds());

		return soapModel;
	}

	public static ActivityCourseSoap[] toSoapModels(ActivityCourse[] models) {
		ActivityCourseSoap[] soapModels = new ActivityCourseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ActivityCourseSoap[][] toSoapModels(ActivityCourse[][] models) {
		ActivityCourseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ActivityCourseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ActivityCourseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ActivityCourseSoap[] toSoapModels(List<ActivityCourse> models) {
		List<ActivityCourseSoap> soapModels = new ArrayList<ActivityCourseSoap>(models.size());

		for (ActivityCourse model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ActivityCourseSoap[soapModels.size()]);
	}

	public ActivityCourseSoap() {
	}

	public long getPrimaryKey() {
		return _activityCourseId;
	}

	public void setPrimaryKey(long pk) {
		setActivityCourseId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getActivityCourseId() {
		return _activityCourseId;
	}

	public void setActivityCourseId(long activityCourseId) {
		_activityCourseId = activityCourseId;
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

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPresentation() {
		return _presentation;
	}

	public void setPresentation(String presentation) {
		_presentation = presentation;
	}

	public String getArrangements() {
		return _arrangements;
	}

	public void setArrangements(String arrangements) {
		_arrangements = arrangements;
	}

	public String getPrice() {
		return _price;
	}

	public void setPrice(String price) {
		_price = price;
	}

	public long getActivityId() {
		return _activityId;
	}

	public void setActivityId(long activityId) {
		_activityId = activityId;
	}

	public long getOrganizerId() {
		return _organizerId;
	}

	public void setOrganizerId(long organizerId) {
		_organizerId = organizerId;
	}

	public long getImageId() {
		return _imageId;
	}

	public void setImageId(long imageId) {
		_imageId = imageId;
	}

	public String getImageIds() {
		return _imageIds;
	}

	public void setImageIds(String imageIds) {
		_imageIds = imageIds;
	}

	public String getVideosIds() {
		return _videosIds;
	}

	public void setVideosIds(String videosIds) {
		_videosIds = videosIds;
	}

	public String getDocumentsIds() {
		return _documentsIds;
	}

	public void setDocumentsIds(String documentsIds) {
		_documentsIds = documentsIds;
	}

	private String _uuid;
	private long _activityCourseId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _name;
	private String _presentation;
	private String _arrangements;
	private String _price;
	private long _activityId;
	private long _organizerId;
	private long _imageId;
	private String _imageIds;
	private String _videosIds;
	private String _documentsIds;
}