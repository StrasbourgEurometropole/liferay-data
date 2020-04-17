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

package eu.strasbourg.service.council.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
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

import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.model.ProcurationModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Procuration service. Represents a row in the &quot;council_Procuration&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ProcurationModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProcurationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcurationImpl
 * @see Procuration
 * @see ProcurationModel
 * @generated
 */
@ProviderType
public class ProcurationModelImpl extends BaseModelImpl<Procuration>
	implements ProcurationModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a procuration model instance should use the {@link Procuration} interface instead.
	 */
	public static final String TABLE_NAME = "council_Procuration";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "procurationId", Types.BIGINT },
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
			{ "officialVotersId", Types.BIGINT },
			{ "officialUnavailableId", Types.BIGINT },
			{ "councilSessionId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("procurationId", Types.BIGINT);
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
		TABLE_COLUMNS_MAP.put("officialVotersId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("officialUnavailableId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("councilSessionId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table council_Procuration (uuid_ VARCHAR(75) null,procurationId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,officialVotersId LONG,officialUnavailableId LONG,councilSessionId LONG)";
	public static final String TABLE_SQL_DROP = "drop table council_Procuration";
	public static final String ORDER_BY_JPQL = " ORDER BY procuration.procurationId DESC";
	public static final String ORDER_BY_SQL = " ORDER BY council_Procuration.procurationId DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.council.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.eu.strasbourg.service.council.model.Procuration"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.council.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.eu.strasbourg.service.council.model.Procuration"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.council.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.eu.strasbourg.service.council.model.Procuration"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long COUNCILSESSIONID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long PROCURATIONID_COLUMN_BITMASK = 16L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(eu.strasbourg.service.council.service.util.ServiceProps.get(
				"lock.expiration.time.eu.strasbourg.service.council.model.Procuration"));

	public ProcurationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _procurationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setProcurationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _procurationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Procuration.class;
	}

	@Override
	public String getModelClassName() {
		return Procuration.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("procurationId", getProcurationId());
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
		attributes.put("officialVotersId", getOfficialVotersId());
		attributes.put("officialUnavailableId", getOfficialUnavailableId());
		attributes.put("councilSessionId", getCouncilSessionId());

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

		Long procurationId = (Long)attributes.get("procurationId");

		if (procurationId != null) {
			setProcurationId(procurationId);
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

		Long officialVotersId = (Long)attributes.get("officialVotersId");

		if (officialVotersId != null) {
			setOfficialVotersId(officialVotersId);
		}

		Long officialUnavailableId = (Long)attributes.get(
				"officialUnavailableId");

		if (officialUnavailableId != null) {
			setOfficialUnavailableId(officialUnavailableId);
		}

		Long councilSessionId = (Long)attributes.get("councilSessionId");

		if (councilSessionId != null) {
			setCouncilSessionId(councilSessionId);
		}
	}

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

	@Override
	public long getProcurationId() {
		return _procurationId;
	}

	@Override
	public void setProcurationId(long procurationId) {
		_columnBitmask = -1L;

		_procurationId = procurationId;
	}

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

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

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

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;
	}

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

	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	@Override
	public long getOfficialVotersId() {
		return _officialVotersId;
	}

	@Override
	public void setOfficialVotersId(long officialVotersId) {
		_officialVotersId = officialVotersId;
	}

	@Override
	public long getOfficialUnavailableId() {
		return _officialUnavailableId;
	}

	@Override
	public void setOfficialUnavailableId(long officialUnavailableId) {
		_officialUnavailableId = officialUnavailableId;
	}

	@Override
	public long getCouncilSessionId() {
		return _councilSessionId;
	}

	@Override
	public void setCouncilSessionId(long councilSessionId) {
		_columnBitmask |= COUNCILSESSIONID_COLUMN_BITMASK;

		if (!_setOriginalCouncilSessionId) {
			_setOriginalCouncilSessionId = true;

			_originalCouncilSessionId = _councilSessionId;
		}

		_councilSessionId = councilSessionId;
	}

	public long getOriginalCouncilSessionId() {
		return _originalCouncilSessionId;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Procuration.class.getName()));
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
			Procuration.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Procuration toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Procuration)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ProcurationImpl procurationImpl = new ProcurationImpl();

		procurationImpl.setUuid(getUuid());
		procurationImpl.setProcurationId(getProcurationId());
		procurationImpl.setGroupId(getGroupId());
		procurationImpl.setCompanyId(getCompanyId());
		procurationImpl.setUserId(getUserId());
		procurationImpl.setUserName(getUserName());
		procurationImpl.setCreateDate(getCreateDate());
		procurationImpl.setModifiedDate(getModifiedDate());
		procurationImpl.setStatus(getStatus());
		procurationImpl.setStatusByUserId(getStatusByUserId());
		procurationImpl.setStatusByUserName(getStatusByUserName());
		procurationImpl.setStatusDate(getStatusDate());
		procurationImpl.setOfficialVotersId(getOfficialVotersId());
		procurationImpl.setOfficialUnavailableId(getOfficialUnavailableId());
		procurationImpl.setCouncilSessionId(getCouncilSessionId());

		procurationImpl.resetOriginalValues();

		return procurationImpl;
	}

	@Override
	public int compareTo(Procuration procuration) {
		int value = 0;

		if (getProcurationId() < procuration.getProcurationId()) {
			value = -1;
		}
		else if (getProcurationId() > procuration.getProcurationId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

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

		if (!(obj instanceof Procuration)) {
			return false;
		}

		Procuration procuration = (Procuration)obj;

		long primaryKey = procuration.getPrimaryKey();

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
		ProcurationModelImpl procurationModelImpl = this;

		procurationModelImpl._originalUuid = procurationModelImpl._uuid;

		procurationModelImpl._originalGroupId = procurationModelImpl._groupId;

		procurationModelImpl._setOriginalGroupId = false;

		procurationModelImpl._originalCompanyId = procurationModelImpl._companyId;

		procurationModelImpl._setOriginalCompanyId = false;

		procurationModelImpl._setModifiedDate = false;

		procurationModelImpl._originalCouncilSessionId = procurationModelImpl._councilSessionId;

		procurationModelImpl._setOriginalCouncilSessionId = false;

		procurationModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Procuration> toCacheModel() {
		ProcurationCacheModel procurationCacheModel = new ProcurationCacheModel();

		procurationCacheModel.uuid = getUuid();

		String uuid = procurationCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			procurationCacheModel.uuid = null;
		}

		procurationCacheModel.procurationId = getProcurationId();

		procurationCacheModel.groupId = getGroupId();

		procurationCacheModel.companyId = getCompanyId();

		procurationCacheModel.userId = getUserId();

		procurationCacheModel.userName = getUserName();

		String userName = procurationCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			procurationCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			procurationCacheModel.createDate = createDate.getTime();
		}
		else {
			procurationCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			procurationCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			procurationCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		procurationCacheModel.status = getStatus();

		procurationCacheModel.statusByUserId = getStatusByUserId();

		procurationCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = procurationCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			procurationCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			procurationCacheModel.statusDate = statusDate.getTime();
		}
		else {
			procurationCacheModel.statusDate = Long.MIN_VALUE;
		}

		procurationCacheModel.officialVotersId = getOfficialVotersId();

		procurationCacheModel.officialUnavailableId = getOfficialUnavailableId();

		procurationCacheModel.councilSessionId = getCouncilSessionId();

		return procurationCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", procurationId=");
		sb.append(getProcurationId());
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
		sb.append(", officialVotersId=");
		sb.append(getOfficialVotersId());
		sb.append(", officialUnavailableId=");
		sb.append(getOfficialUnavailableId());
		sb.append(", councilSessionId=");
		sb.append(getCouncilSessionId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(49);

		sb.append("<model><model-name>");
		sb.append("eu.strasbourg.service.council.model.Procuration");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>procurationId</column-name><column-value><![CDATA[");
		sb.append(getProcurationId());
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
			"<column><column-name>officialVotersId</column-name><column-value><![CDATA[");
		sb.append(getOfficialVotersId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>officialUnavailableId</column-name><column-value><![CDATA[");
		sb.append(getOfficialUnavailableId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>councilSessionId</column-name><column-value><![CDATA[");
		sb.append(getCouncilSessionId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Procuration.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Procuration.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _procurationId;
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
	private long _officialVotersId;
	private long _officialUnavailableId;
	private long _councilSessionId;
	private long _originalCouncilSessionId;
	private boolean _setOriginalCouncilSessionId;
	private long _columnBitmask;
	private Procuration _escapedModel;
}