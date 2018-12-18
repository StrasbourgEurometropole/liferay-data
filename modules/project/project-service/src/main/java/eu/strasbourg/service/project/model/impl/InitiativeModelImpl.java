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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.InitiativeModel;
import eu.strasbourg.service.project.model.InitiativeSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Initiative service. Represents a row in the &quot;project_Initiative&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link InitiativeModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link InitiativeImpl}.
 * </p>
 *
 * @author Cedric Henry
 * @see InitiativeImpl
 * @see Initiative
 * @see InitiativeModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class InitiativeModelImpl extends BaseModelImpl<Initiative>
	implements InitiativeModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a initiative model instance should use the {@link Initiative} interface instead.
	 */
	public static final String TABLE_NAME = "project_Initiative";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "initiativeId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "status", Types.INTEGER },
			{ "statusByUserId", Types.BIGINT },
			{ "statusByUserName", Types.VARCHAR },
			{ "statusDate", Types.TIMESTAMP },
			{ "title", Types.VARCHAR },
			{ "description", Types.CLOB },
			{ "placeTextArea", Types.VARCHAR },
			{ "videoUrl", Types.VARCHAR },
			{ "externalImageURL", Types.VARCHAR },
			{ "externalImageCopyright", Types.VARCHAR },
			{ "mediaChoice", Types.BOOLEAN },
			{ "assetEntryId", Types.BIGINT },
			{ "publikId", Types.VARCHAR },
			{ "imageId", Types.BIGINT },
			{ "filesIds", Types.VARCHAR },
			{ "publicationDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("initiativeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.CLOB);
		TABLE_COLUMNS_MAP.put("placeTextArea", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("videoUrl", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalImageURL", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalImageCopyright", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("mediaChoice", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("assetEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("publikId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("imageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("filesIds", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("publicationDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table project_Initiative (uuid_ VARCHAR(75) null,initiativeId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,title VARCHAR(400) null,description TEXT null,placeTextArea VARCHAR(75) null,videoUrl VARCHAR(400) null,externalImageURL VARCHAR(400) null,externalImageCopyright VARCHAR(400) null,mediaChoice BOOLEAN,assetEntryId LONG,publikId VARCHAR(75) null,imageId LONG,filesIds VARCHAR(75) null,publicationDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table project_Initiative";
	public static final String ORDER_BY_JPQL = " ORDER BY initiative.title ASC";
	public static final String ORDER_BY_SQL = " ORDER BY project_Initiative.title ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.project.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.eu.strasbourg.service.project.model.Initiative"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.project.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.eu.strasbourg.service.project.model.Initiative"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.project.service.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.eu.strasbourg.service.project.model.Initiative"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long PUBLIKID_COLUMN_BITMASK = 4L;
	public static final long STATUS_COLUMN_BITMASK = 8L;
	public static final long UUID_COLUMN_BITMASK = 16L;
	public static final long TITLE_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Initiative toModel(InitiativeSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Initiative model = new InitiativeImpl();

		model.setUuid(soapModel.getUuid());
		model.setInitiativeId(soapModel.getInitiativeId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());
		model.setTitle(soapModel.getTitle());
		model.setDescription(soapModel.getDescription());
		model.setPlaceTextArea(soapModel.getPlaceTextArea());
		model.setVideoUrl(soapModel.getVideoUrl());
		model.setExternalImageURL(soapModel.getExternalImageURL());
		model.setExternalImageCopyright(soapModel.getExternalImageCopyright());
		model.setMediaChoice(soapModel.getMediaChoice());
		model.setAssetEntryId(soapModel.getAssetEntryId());
		model.setPublikId(soapModel.getPublikId());
		model.setImageId(soapModel.getImageId());
		model.setFilesIds(soapModel.getFilesIds());
		model.setPublicationDate(soapModel.getPublicationDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Initiative> toModels(InitiativeSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Initiative> models = new ArrayList<Initiative>(soapModels.length);

		for (InitiativeSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(eu.strasbourg.service.project.service.util.PropsUtil.get(
				"lock.expiration.time.eu.strasbourg.service.project.model.Initiative"));

	public InitiativeModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _initiativeId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setInitiativeId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _initiativeId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Initiative.class;
	}

	@Override
	public String getModelClassName() {
		return Initiative.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("initiativeId", getInitiativeId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("placeTextArea", getPlaceTextArea());
		attributes.put("videoUrl", getVideoUrl());
		attributes.put("externalImageURL", getExternalImageURL());
		attributes.put("externalImageCopyright", getExternalImageCopyright());
		attributes.put("mediaChoice", getMediaChoice());
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("publikId", getPublikId());
		attributes.put("imageId", getImageId());
		attributes.put("filesIds", getFilesIds());
		attributes.put("publicationDate", getPublicationDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long initiativeId = (Long)attributes.get("initiativeId");

		if (initiativeId != null) {
			setInitiativeId(initiativeId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String placeTextArea = (String)attributes.get("placeTextArea");

		if (placeTextArea != null) {
			setPlaceTextArea(placeTextArea);
		}

		String videoUrl = (String)attributes.get("videoUrl");

		if (videoUrl != null) {
			setVideoUrl(videoUrl);
		}

		String externalImageURL = (String)attributes.get("externalImageURL");

		if (externalImageURL != null) {
			setExternalImageURL(externalImageURL);
		}

		String externalImageCopyright = (String)attributes.get(
				"externalImageCopyright");

		if (externalImageCopyright != null) {
			setExternalImageCopyright(externalImageCopyright);
		}

		Boolean mediaChoice = (Boolean)attributes.get("mediaChoice");

		if (mediaChoice != null) {
			setMediaChoice(mediaChoice);
		}

		Long assetEntryId = (Long)attributes.get("assetEntryId");

		if (assetEntryId != null) {
			setAssetEntryId(assetEntryId);
		}

		String publikId = (String)attributes.get("publikId");

		if (publikId != null) {
			setPublikId(publikId);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		String filesIds = (String)attributes.get("filesIds");

		if (filesIds != null) {
			setFilesIds(filesIds);
		}

		Date publicationDate = (Date)attributes.get("publicationDate");

		if (publicationDate != null) {
			setPublicationDate(publicationDate);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getInitiativeId() {
		return _initiativeId;
	}

	@Override
	public void setInitiativeId(long initiativeId) {
		_initiativeId = initiativeId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (!_setOriginalStatus) {
			_setOriginalStatus = true;

			_originalStatus = _status;
		}

		_status = status;
	}

	public int getOriginalStatus() {
		return _originalStatus;
	}

	@JSON
	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return StringPool.BLANK;
		}
		else {
			return _statusByUserName;
		}
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	@JSON
	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	@JSON
	@Override
	public String getTitle() {
		if (_title == null) {
			return StringPool.BLANK;
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		_columnBitmask = -1L;

		_title = title;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return StringPool.BLANK;
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public String getPlaceTextArea() {
		if (_placeTextArea == null) {
			return StringPool.BLANK;
		}
		else {
			return _placeTextArea;
		}
	}

	@Override
	public void setPlaceTextArea(String placeTextArea) {
		_placeTextArea = placeTextArea;
	}

	@JSON
	@Override
	public String getVideoUrl() {
		if (_videoUrl == null) {
			return StringPool.BLANK;
		}
		else {
			return _videoUrl;
		}
	}

	@Override
	public void setVideoUrl(String videoUrl) {
		_videoUrl = videoUrl;
	}

	@JSON
	@Override
	public String getExternalImageURL() {
		if (_externalImageURL == null) {
			return StringPool.BLANK;
		}
		else {
			return _externalImageURL;
		}
	}

	@Override
	public void setExternalImageURL(String externalImageURL) {
		_externalImageURL = externalImageURL;
	}

	@JSON
	@Override
	public String getExternalImageCopyright() {
		if (_externalImageCopyright == null) {
			return StringPool.BLANK;
		}
		else {
			return _externalImageCopyright;
		}
	}

	@Override
	public void setExternalImageCopyright(String externalImageCopyright) {
		_externalImageCopyright = externalImageCopyright;
	}

	@JSON
	@Override
	public boolean getMediaChoice() {
		return _mediaChoice;
	}

	@JSON
	@Override
	public boolean isMediaChoice() {
		return _mediaChoice;
	}

	@Override
	public void setMediaChoice(boolean mediaChoice) {
		_mediaChoice = mediaChoice;
	}

	@JSON
	@Override
	public long getAssetEntryId() {
		return _assetEntryId;
	}

	@Override
	public void setAssetEntryId(long assetEntryId) {
		_assetEntryId = assetEntryId;
	}

	@JSON
	@Override
	public String getPublikId() {
		if (_publikId == null) {
			return StringPool.BLANK;
		}
		else {
			return _publikId;
		}
	}

	@Override
	public void setPublikId(String publikId) {
		_columnBitmask |= PUBLIKID_COLUMN_BITMASK;

		if (_originalPublikId == null) {
			_originalPublikId = _publikId;
		}

		_publikId = publikId;
	}

	public String getOriginalPublikId() {
		return GetterUtil.getString(_originalPublikId);
	}

	@JSON
	@Override
	public long getImageId() {
		return _imageId;
	}

	@Override
	public void setImageId(long imageId) {
		_imageId = imageId;
	}

	@JSON
	@Override
	public String getFilesIds() {
		if (_filesIds == null) {
			return StringPool.BLANK;
		}
		else {
			return _filesIds;
		}
	}

	@Override
	public void setFilesIds(String filesIds) {
		_filesIds = filesIds;
	}

	@JSON
	@Override
	public Date getPublicationDate() {
		return _publicationDate;
	}

	@Override
	public void setPublicationDate(Date publicationDate) {
		_publicationDate = publicationDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Initiative.class.getName()));
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Initiative.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Initiative toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Initiative)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		InitiativeImpl initiativeImpl = new InitiativeImpl();

		initiativeImpl.setUuid(getUuid());
		initiativeImpl.setInitiativeId(getInitiativeId());
		initiativeImpl.setGroupId(getGroupId());
		initiativeImpl.setCompanyId(getCompanyId());
		initiativeImpl.setUserId(getUserId());
		initiativeImpl.setUserName(getUserName());
		initiativeImpl.setCreateDate(getCreateDate());
		initiativeImpl.setModifiedDate(getModifiedDate());
		initiativeImpl.setStatus(getStatus());
		initiativeImpl.setStatusByUserId(getStatusByUserId());
		initiativeImpl.setStatusByUserName(getStatusByUserName());
		initiativeImpl.setStatusDate(getStatusDate());
		initiativeImpl.setTitle(getTitle());
		initiativeImpl.setDescription(getDescription());
		initiativeImpl.setPlaceTextArea(getPlaceTextArea());
		initiativeImpl.setVideoUrl(getVideoUrl());
		initiativeImpl.setExternalImageURL(getExternalImageURL());
		initiativeImpl.setExternalImageCopyright(getExternalImageCopyright());
		initiativeImpl.setMediaChoice(getMediaChoice());
		initiativeImpl.setAssetEntryId(getAssetEntryId());
		initiativeImpl.setPublikId(getPublikId());
		initiativeImpl.setImageId(getImageId());
		initiativeImpl.setFilesIds(getFilesIds());
		initiativeImpl.setPublicationDate(getPublicationDate());

		initiativeImpl.resetOriginalValues();

		return initiativeImpl;
	}

	@Override
	public int compareTo(Initiative initiative) {
		int value = 0;

		value = getTitle().compareTo(initiative.getTitle());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Initiative)) {
			return false;
		}

		Initiative initiative = (Initiative)obj;

		long primaryKey = initiative.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		InitiativeModelImpl initiativeModelImpl = this;

		initiativeModelImpl._originalUuid = initiativeModelImpl._uuid;

		initiativeModelImpl._originalGroupId = initiativeModelImpl._groupId;

		initiativeModelImpl._setOriginalGroupId = false;

		initiativeModelImpl._originalCompanyId = initiativeModelImpl._companyId;

		initiativeModelImpl._setOriginalCompanyId = false;

		initiativeModelImpl._setModifiedDate = false;

		initiativeModelImpl._originalStatus = initiativeModelImpl._status;

		initiativeModelImpl._setOriginalStatus = false;

		initiativeModelImpl._originalPublikId = initiativeModelImpl._publikId;

		initiativeModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Initiative> toCacheModel() {
		InitiativeCacheModel initiativeCacheModel = new InitiativeCacheModel();

		initiativeCacheModel.uuid = getUuid();

		String uuid = initiativeCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			initiativeCacheModel.uuid = null;
		}

		initiativeCacheModel.initiativeId = getInitiativeId();

		initiativeCacheModel.groupId = getGroupId();

		initiativeCacheModel.companyId = getCompanyId();

		initiativeCacheModel.userId = getUserId();

		initiativeCacheModel.userName = getUserName();

		String userName = initiativeCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			initiativeCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			initiativeCacheModel.createDate = createDate.getTime();
		}
		else {
			initiativeCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			initiativeCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			initiativeCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		initiativeCacheModel.status = getStatus();

		initiativeCacheModel.statusByUserId = getStatusByUserId();

		initiativeCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = initiativeCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			initiativeCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			initiativeCacheModel.statusDate = statusDate.getTime();
		}
		else {
			initiativeCacheModel.statusDate = Long.MIN_VALUE;
		}

		initiativeCacheModel.title = getTitle();

		String title = initiativeCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			initiativeCacheModel.title = null;
		}

		initiativeCacheModel.description = getDescription();

		String description = initiativeCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			initiativeCacheModel.description = null;
		}

		initiativeCacheModel.placeTextArea = getPlaceTextArea();

		String placeTextArea = initiativeCacheModel.placeTextArea;

		if ((placeTextArea != null) && (placeTextArea.length() == 0)) {
			initiativeCacheModel.placeTextArea = null;
		}

		initiativeCacheModel.videoUrl = getVideoUrl();

		String videoUrl = initiativeCacheModel.videoUrl;

		if ((videoUrl != null) && (videoUrl.length() == 0)) {
			initiativeCacheModel.videoUrl = null;
		}

		initiativeCacheModel.externalImageURL = getExternalImageURL();

		String externalImageURL = initiativeCacheModel.externalImageURL;

		if ((externalImageURL != null) && (externalImageURL.length() == 0)) {
			initiativeCacheModel.externalImageURL = null;
		}

		initiativeCacheModel.externalImageCopyright = getExternalImageCopyright();

		String externalImageCopyright = initiativeCacheModel.externalImageCopyright;

		if ((externalImageCopyright != null) &&
				(externalImageCopyright.length() == 0)) {
			initiativeCacheModel.externalImageCopyright = null;
		}

		initiativeCacheModel.mediaChoice = getMediaChoice();

		initiativeCacheModel.assetEntryId = getAssetEntryId();

		initiativeCacheModel.publikId = getPublikId();

		String publikId = initiativeCacheModel.publikId;

		if ((publikId != null) && (publikId.length() == 0)) {
			initiativeCacheModel.publikId = null;
		}

		initiativeCacheModel.imageId = getImageId();

		initiativeCacheModel.filesIds = getFilesIds();

		String filesIds = initiativeCacheModel.filesIds;

		if ((filesIds != null) && (filesIds.length() == 0)) {
			initiativeCacheModel.filesIds = null;
		}

		Date publicationDate = getPublicationDate();

		if (publicationDate != null) {
			initiativeCacheModel.publicationDate = publicationDate.getTime();
		}
		else {
			initiativeCacheModel.publicationDate = Long.MIN_VALUE;
		}

		return initiativeCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", initiativeId=");
		sb.append(getInitiativeId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", placeTextArea=");
		sb.append(getPlaceTextArea());
		sb.append(", videoUrl=");
		sb.append(getVideoUrl());
		sb.append(", externalImageURL=");
		sb.append(getExternalImageURL());
		sb.append(", externalImageCopyright=");
		sb.append(getExternalImageCopyright());
		sb.append(", mediaChoice=");
		sb.append(getMediaChoice());
		sb.append(", assetEntryId=");
		sb.append(getAssetEntryId());
		sb.append(", publikId=");
		sb.append(getPublikId());
		sb.append(", imageId=");
		sb.append(getImageId());
		sb.append(", filesIds=");
		sb.append(getFilesIds());
		sb.append(", publicationDate=");
		sb.append(getPublicationDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(76);

		sb.append("<model><model-name>");
		sb.append("eu.strasbourg.service.project.model.Initiative");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>initiativeId</column-name><column-value><![CDATA[");
		sb.append(getInitiativeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>placeTextArea</column-name><column-value><![CDATA[");
		sb.append(getPlaceTextArea());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>videoUrl</column-name><column-value><![CDATA[");
		sb.append(getVideoUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>externalImageURL</column-name><column-value><![CDATA[");
		sb.append(getExternalImageURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>externalImageCopyright</column-name><column-value><![CDATA[");
		sb.append(getExternalImageCopyright());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mediaChoice</column-name><column-value><![CDATA[");
		sb.append(getMediaChoice());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetEntryId</column-name><column-value><![CDATA[");
		sb.append(getAssetEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>publikId</column-name><column-value><![CDATA[");
		sb.append(getPublikId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>imageId</column-name><column-value><![CDATA[");
		sb.append(getImageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>filesIds</column-name><column-value><![CDATA[");
		sb.append(getFilesIds());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>publicationDate</column-name><column-value><![CDATA[");
		sb.append(getPublicationDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Initiative.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Initiative.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _initiativeId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _title;
	private String _description;
	private String _placeTextArea;
	private String _videoUrl;
	private String _externalImageURL;
	private String _externalImageCopyright;
	private boolean _mediaChoice;
	private long _assetEntryId;
	private String _publikId;
	private String _originalPublikId;
	private long _imageId;
	private String _filesIds;
	private Date _publicationDate;
	private long _columnBitmask;
	private Initiative _escapedModel;
}