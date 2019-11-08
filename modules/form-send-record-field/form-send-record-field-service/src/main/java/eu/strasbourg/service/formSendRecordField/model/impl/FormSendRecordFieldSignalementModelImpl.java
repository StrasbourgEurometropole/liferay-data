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

package eu.strasbourg.service.formSendRecordField.model.impl;

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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalementModel;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalementSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the FormSendRecordFieldSignalement service. Represents a row in the &quot;FormSendRecordField_FormSendRecordFieldSignalement&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link FormSendRecordFieldSignalementModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FormSendRecordFieldSignalementImpl}.
 * </p>
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldSignalementImpl
 * @see FormSendRecordFieldSignalement
 * @see FormSendRecordFieldSignalementModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class FormSendRecordFieldSignalementModelImpl extends BaseModelImpl<FormSendRecordFieldSignalement>
	implements FormSendRecordFieldSignalementModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a form send record field signalement model instance should use the {@link FormSendRecordFieldSignalement} interface instead.
	 */
	public static final String TABLE_NAME = "FormSendRecordField_FormSendRecordFieldSignalement";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "signalementId", Types.BIGINT },
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
			{ "formSendRecordFieldId", Types.BIGINT },
			{ "publikId", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("signalementId", Types.BIGINT);
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
		TABLE_COLUMNS_MAP.put("formSendRecordFieldId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("publikId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table FormSendRecordField_FormSendRecordFieldSignalement (uuid_ VARCHAR(75) null,signalementId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,formSendRecordFieldId LONG,publikId VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table FormSendRecordField_FormSendRecordFieldSignalement";
	public static final String ORDER_BY_JPQL = " ORDER BY formSendRecordFieldSignalement.createDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY FormSendRecordField_FormSendRecordFieldSignalement.createDate ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.formSendRecordField.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.formSendRecordField.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.formSendRecordField.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long FORMSENDRECORDFIELDID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long CREATEDATE_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static FormSendRecordFieldSignalement toModel(
		FormSendRecordFieldSignalementSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		FormSendRecordFieldSignalement model = new FormSendRecordFieldSignalementImpl();

		model.setUuid(soapModel.getUuid());
		model.setSignalementId(soapModel.getSignalementId());
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
		model.setFormSendRecordFieldId(soapModel.getFormSendRecordFieldId());
		model.setPublikId(soapModel.getPublikId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<FormSendRecordFieldSignalement> toModels(
		FormSendRecordFieldSignalementSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<FormSendRecordFieldSignalement> models = new ArrayList<FormSendRecordFieldSignalement>(soapModels.length);

		for (FormSendRecordFieldSignalementSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(eu.strasbourg.service.formSendRecordField.service.util.ServiceProps.get(
				"lock.expiration.time.eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement"));

	public FormSendRecordFieldSignalementModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _signalementId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSignalementId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _signalementId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return FormSendRecordFieldSignalement.class;
	}

	@Override
	public String getModelClassName() {
		return FormSendRecordFieldSignalement.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("signalementId", getSignalementId());
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
		attributes.put("formSendRecordFieldId", getFormSendRecordFieldId());
		attributes.put("publikId", getPublikId());

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

		Long signalementId = (Long)attributes.get("signalementId");

		if (signalementId != null) {
			setSignalementId(signalementId);
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

		Long formSendRecordFieldId = (Long)attributes.get(
				"formSendRecordFieldId");

		if (formSendRecordFieldId != null) {
			setFormSendRecordFieldId(formSendRecordFieldId);
		}

		String publikId = (String)attributes.get("publikId");

		if (publikId != null) {
			setPublikId(publikId);
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
	public long getSignalementId() {
		return _signalementId;
	}

	@Override
	public void setSignalementId(long signalementId) {
		_signalementId = signalementId;
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
		_columnBitmask = -1L;

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
		_status = status;
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
	public long getFormSendRecordFieldId() {
		return _formSendRecordFieldId;
	}

	@Override
	public void setFormSendRecordFieldId(long formSendRecordFieldId) {
		_columnBitmask |= FORMSENDRECORDFIELDID_COLUMN_BITMASK;

		if (!_setOriginalFormSendRecordFieldId) {
			_setOriginalFormSendRecordFieldId = true;

			_originalFormSendRecordFieldId = _formSendRecordFieldId;
		}

		_formSendRecordFieldId = formSendRecordFieldId;
	}

	public long getOriginalFormSendRecordFieldId() {
		return _originalFormSendRecordFieldId;
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
		_publikId = publikId;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				FormSendRecordFieldSignalement.class.getName()));
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
			FormSendRecordFieldSignalement.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public FormSendRecordFieldSignalement toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (FormSendRecordFieldSignalement)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		FormSendRecordFieldSignalementImpl formSendRecordFieldSignalementImpl = new FormSendRecordFieldSignalementImpl();

		formSendRecordFieldSignalementImpl.setUuid(getUuid());
		formSendRecordFieldSignalementImpl.setSignalementId(getSignalementId());
		formSendRecordFieldSignalementImpl.setGroupId(getGroupId());
		formSendRecordFieldSignalementImpl.setCompanyId(getCompanyId());
		formSendRecordFieldSignalementImpl.setUserId(getUserId());
		formSendRecordFieldSignalementImpl.setUserName(getUserName());
		formSendRecordFieldSignalementImpl.setCreateDate(getCreateDate());
		formSendRecordFieldSignalementImpl.setModifiedDate(getModifiedDate());
		formSendRecordFieldSignalementImpl.setStatus(getStatus());
		formSendRecordFieldSignalementImpl.setStatusByUserId(getStatusByUserId());
		formSendRecordFieldSignalementImpl.setStatusByUserName(getStatusByUserName());
		formSendRecordFieldSignalementImpl.setStatusDate(getStatusDate());
		formSendRecordFieldSignalementImpl.setFormSendRecordFieldId(getFormSendRecordFieldId());
		formSendRecordFieldSignalementImpl.setPublikId(getPublikId());

		formSendRecordFieldSignalementImpl.resetOriginalValues();

		return formSendRecordFieldSignalementImpl;
	}

	@Override
	public int compareTo(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				formSendRecordFieldSignalement.getCreateDate());

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

		if (!(obj instanceof FormSendRecordFieldSignalement)) {
			return false;
		}

		FormSendRecordFieldSignalement formSendRecordFieldSignalement = (FormSendRecordFieldSignalement)obj;

		long primaryKey = formSendRecordFieldSignalement.getPrimaryKey();

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
		FormSendRecordFieldSignalementModelImpl formSendRecordFieldSignalementModelImpl =
			this;

		formSendRecordFieldSignalementModelImpl._originalUuid = formSendRecordFieldSignalementModelImpl._uuid;

		formSendRecordFieldSignalementModelImpl._originalGroupId = formSendRecordFieldSignalementModelImpl._groupId;

		formSendRecordFieldSignalementModelImpl._setOriginalGroupId = false;

		formSendRecordFieldSignalementModelImpl._originalCompanyId = formSendRecordFieldSignalementModelImpl._companyId;

		formSendRecordFieldSignalementModelImpl._setOriginalCompanyId = false;

		formSendRecordFieldSignalementModelImpl._setModifiedDate = false;

		formSendRecordFieldSignalementModelImpl._originalFormSendRecordFieldId = formSendRecordFieldSignalementModelImpl._formSendRecordFieldId;

		formSendRecordFieldSignalementModelImpl._setOriginalFormSendRecordFieldId = false;

		formSendRecordFieldSignalementModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<FormSendRecordFieldSignalement> toCacheModel() {
		FormSendRecordFieldSignalementCacheModel formSendRecordFieldSignalementCacheModel =
			new FormSendRecordFieldSignalementCacheModel();

		formSendRecordFieldSignalementCacheModel.uuid = getUuid();

		String uuid = formSendRecordFieldSignalementCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			formSendRecordFieldSignalementCacheModel.uuid = null;
		}

		formSendRecordFieldSignalementCacheModel.signalementId = getSignalementId();

		formSendRecordFieldSignalementCacheModel.groupId = getGroupId();

		formSendRecordFieldSignalementCacheModel.companyId = getCompanyId();

		formSendRecordFieldSignalementCacheModel.userId = getUserId();

		formSendRecordFieldSignalementCacheModel.userName = getUserName();

		String userName = formSendRecordFieldSignalementCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			formSendRecordFieldSignalementCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			formSendRecordFieldSignalementCacheModel.createDate = createDate.getTime();
		}
		else {
			formSendRecordFieldSignalementCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			formSendRecordFieldSignalementCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			formSendRecordFieldSignalementCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		formSendRecordFieldSignalementCacheModel.status = getStatus();

		formSendRecordFieldSignalementCacheModel.statusByUserId = getStatusByUserId();

		formSendRecordFieldSignalementCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = formSendRecordFieldSignalementCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			formSendRecordFieldSignalementCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			formSendRecordFieldSignalementCacheModel.statusDate = statusDate.getTime();
		}
		else {
			formSendRecordFieldSignalementCacheModel.statusDate = Long.MIN_VALUE;
		}

		formSendRecordFieldSignalementCacheModel.formSendRecordFieldId = getFormSendRecordFieldId();

		formSendRecordFieldSignalementCacheModel.publikId = getPublikId();

		String publikId = formSendRecordFieldSignalementCacheModel.publikId;

		if ((publikId != null) && (publikId.length() == 0)) {
			formSendRecordFieldSignalementCacheModel.publikId = null;
		}

		return formSendRecordFieldSignalementCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", signalementId=");
		sb.append(getSignalementId());
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
		sb.append(", formSendRecordFieldId=");
		sb.append(getFormSendRecordFieldId());
		sb.append(", publikId=");
		sb.append(getPublikId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append(
			"eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>signalementId</column-name><column-value><![CDATA[");
		sb.append(getSignalementId());
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
			"<column><column-name>formSendRecordFieldId</column-name><column-value><![CDATA[");
		sb.append(getFormSendRecordFieldId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>publikId</column-name><column-value><![CDATA[");
		sb.append(getPublikId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = FormSendRecordFieldSignalement.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			FormSendRecordFieldSignalement.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _signalementId;
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
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private long _formSendRecordFieldId;
	private long _originalFormSendRecordFieldId;
	private boolean _setOriginalFormSendRecordFieldId;
	private String _publikId;
	private long _columnBitmask;
	private FormSendRecordFieldSignalement _escapedModel;
}