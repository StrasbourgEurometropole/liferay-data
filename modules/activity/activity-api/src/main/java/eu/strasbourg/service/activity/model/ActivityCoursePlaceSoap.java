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
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.activity.service.http.ActivityCoursePlaceServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.activity.service.http.ActivityCoursePlaceServiceSoap
 * @generated
 */
@ProviderType
public class ActivityCoursePlaceSoap implements Serializable {
	public static ActivityCoursePlaceSoap toSoapModel(ActivityCoursePlace model) {
		ActivityCoursePlaceSoap soapModel = new ActivityCoursePlaceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setActivityCoursePlaceId(model.getActivityCoursePlaceId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setActivityCourseId(model.getActivityCourseId());
		soapModel.setPlaceSIGId(model.getPlaceSIGId());
		soapModel.setPlaceName(model.getPlaceName());
		soapModel.setPlaceStreetNumber(model.getPlaceStreetNumber());
		soapModel.setPlaceStreetName(model.getPlaceStreetName());
		soapModel.setPlaceZipCode(model.getPlaceZipCode());
		soapModel.setPlaceCityId(model.getPlaceCityId());

		return soapModel;
	}

	public static ActivityCoursePlaceSoap[] toSoapModels(
		ActivityCoursePlace[] models) {
		ActivityCoursePlaceSoap[] soapModels = new ActivityCoursePlaceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ActivityCoursePlaceSoap[][] toSoapModels(
		ActivityCoursePlace[][] models) {
		ActivityCoursePlaceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ActivityCoursePlaceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ActivityCoursePlaceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ActivityCoursePlaceSoap[] toSoapModels(
		List<ActivityCoursePlace> models) {
		List<ActivityCoursePlaceSoap> soapModels = new ArrayList<ActivityCoursePlaceSoap>(models.size());

		for (ActivityCoursePlace model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ActivityCoursePlaceSoap[soapModels.size()]);
	}

	public ActivityCoursePlaceSoap() {
	}

	public long getPrimaryKey() {
		return _activityCoursePlaceId;
	}

	public void setPrimaryKey(long pk) {
		setActivityCoursePlaceId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getActivityCoursePlaceId() {
		return _activityCoursePlaceId;
	}

	public void setActivityCoursePlaceId(long activityCoursePlaceId) {
		_activityCoursePlaceId = activityCoursePlaceId;
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

	public long getActivityCourseId() {
		return _activityCourseId;
	}

	public void setActivityCourseId(long activityCourseId) {
		_activityCourseId = activityCourseId;
	}

	public String getPlaceSIGId() {
		return _placeSIGId;
	}

	public void setPlaceSIGId(String placeSIGId) {
		_placeSIGId = placeSIGId;
	}

	public String getPlaceName() {
		return _placeName;
	}

	public void setPlaceName(String placeName) {
		_placeName = placeName;
	}

	public String getPlaceStreetNumber() {
		return _placeStreetNumber;
	}

	public void setPlaceStreetNumber(String placeStreetNumber) {
		_placeStreetNumber = placeStreetNumber;
	}

	public String getPlaceStreetName() {
		return _placeStreetName;
	}

	public void setPlaceStreetName(String placeStreetName) {
		_placeStreetName = placeStreetName;
	}

	public String getPlaceZipCode() {
		return _placeZipCode;
	}

	public void setPlaceZipCode(String placeZipCode) {
		_placeZipCode = placeZipCode;
	}

	public long getPlaceCityId() {
		return _placeCityId;
	}

	public void setPlaceCityId(long placeCityId) {
		_placeCityId = placeCityId;
	}

	private String _uuid;
	private long _activityCoursePlaceId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _activityCourseId;
	private String _placeSIGId;
	private String _placeName;
	private String _placeStreetNumber;
	private String _placeStreetName;
	private String _placeZipCode;
	private long _placeCityId;
}