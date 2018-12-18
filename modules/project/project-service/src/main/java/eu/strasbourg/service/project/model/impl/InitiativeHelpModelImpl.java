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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.project.model.InitiativeHelp;
import eu.strasbourg.service.project.model.InitiativeHelpModel;
import eu.strasbourg.service.project.model.InitiativeHelpSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the InitiativeHelp service. Represents a row in the &quot;project_InitiativeHelp&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link InitiativeHelpModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link InitiativeHelpImpl}.
 * </p>
 *
 * @author Cedric Henry
 * @see InitiativeHelpImpl
 * @see InitiativeHelp
 * @see InitiativeHelpModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class InitiativeHelpModelImpl extends BaseModelImpl<InitiativeHelp>
	implements InitiativeHelpModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a initiative help model instance should use the {@link InitiativeHelp} interface instead.
	 */
	public static final String TABLE_NAME = "project_InitiativeHelp";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "initiativeHelpId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "publikUserId", Types.VARCHAR },
			{ "initiativeId", Types.BIGINT },
			{ "helpTypes", Types.VARCHAR },
			{ "groupId", Types.BIGINT },
			{ "message", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("initiativeHelpId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("publikUserId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("initiativeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("helpTypes", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("message", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table project_InitiativeHelp (uuid_ VARCHAR(75) null,initiativeHelpId LONG not null primary key,createDate DATE null,publikUserId VARCHAR(75) null,initiativeId LONG,helpTypes VARCHAR(75) null,groupId LONG,message VARCHAR(400) null)";
	public static final String TABLE_SQL_DROP = "drop table project_InitiativeHelp";
	public static final String ORDER_BY_JPQL = " ORDER BY initiativeHelp.initiativeHelpId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY project_InitiativeHelp.initiativeHelpId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.project.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.eu.strasbourg.service.project.model.InitiativeHelp"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.project.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.eu.strasbourg.service.project.model.InitiativeHelp"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.project.service.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.eu.strasbourg.service.project.model.InitiativeHelp"),
			true);
	public static final long GROUPID_COLUMN_BITMASK = 1L;
	public static final long INITIATIVEID_COLUMN_BITMASK = 2L;
	public static final long PUBLIKUSERID_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long INITIATIVEHELPID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static InitiativeHelp toModel(InitiativeHelpSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		InitiativeHelp model = new InitiativeHelpImpl();

		model.setUuid(soapModel.getUuid());
		model.setInitiativeHelpId(soapModel.getInitiativeHelpId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setPublikUserId(soapModel.getPublikUserId());
		model.setInitiativeId(soapModel.getInitiativeId());
		model.setHelpTypes(soapModel.getHelpTypes());
		model.setGroupId(soapModel.getGroupId());
		model.setMessage(soapModel.getMessage());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<InitiativeHelp> toModels(InitiativeHelpSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<InitiativeHelp> models = new ArrayList<InitiativeHelp>(soapModels.length);

		for (InitiativeHelpSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(eu.strasbourg.service.project.service.util.PropsUtil.get(
				"lock.expiration.time.eu.strasbourg.service.project.model.InitiativeHelp"));

	public InitiativeHelpModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _initiativeHelpId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setInitiativeHelpId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _initiativeHelpId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return InitiativeHelp.class;
	}

	@Override
	public String getModelClassName() {
		return InitiativeHelp.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("initiativeHelpId", getInitiativeHelpId());
		attributes.put("createDate", getCreateDate());
		attributes.put("publikUserId", getPublikUserId());
		attributes.put("initiativeId", getInitiativeId());
		attributes.put("helpTypes", getHelpTypes());
		attributes.put("groupId", getGroupId());
		attributes.put("message", getMessage());

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

		Long initiativeHelpId = (Long)attributes.get("initiativeHelpId");

		if (initiativeHelpId != null) {
			setInitiativeHelpId(initiativeHelpId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String publikUserId = (String)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}

		Long initiativeId = (Long)attributes.get("initiativeId");

		if (initiativeId != null) {
			setInitiativeId(initiativeId);
		}

		String helpTypes = (String)attributes.get("helpTypes");

		if (helpTypes != null) {
			setHelpTypes(helpTypes);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String message = (String)attributes.get("message");

		if (message != null) {
			setMessage(message);
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
	public long getInitiativeHelpId() {
		return _initiativeHelpId;
	}

	@Override
	public void setInitiativeHelpId(long initiativeHelpId) {
		_initiativeHelpId = initiativeHelpId;
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
	public String getPublikUserId() {
		if (_publikUserId == null) {
			return StringPool.BLANK;
		}
		else {
			return _publikUserId;
		}
	}

	@Override
	public void setPublikUserId(String publikUserId) {
		_columnBitmask |= PUBLIKUSERID_COLUMN_BITMASK;

		if (_originalPublikUserId == null) {
			_originalPublikUserId = _publikUserId;
		}

		_publikUserId = publikUserId;
	}

	public String getOriginalPublikUserId() {
		return GetterUtil.getString(_originalPublikUserId);
	}

	@JSON
	@Override
	public long getInitiativeId() {
		return _initiativeId;
	}

	@Override
	public void setInitiativeId(long initiativeId) {
		_columnBitmask |= INITIATIVEID_COLUMN_BITMASK;

		if (!_setOriginalInitiativeId) {
			_setOriginalInitiativeId = true;

			_originalInitiativeId = _initiativeId;
		}

		_initiativeId = initiativeId;
	}

	public long getOriginalInitiativeId() {
		return _originalInitiativeId;
	}

	@JSON
	@Override
	public String getHelpTypes() {
		if (_helpTypes == null) {
			return StringPool.BLANK;
		}
		else {
			return _helpTypes;
		}
	}

	@Override
	public void setHelpTypes(String helpTypes) {
		_helpTypes = helpTypes;
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
	public String getMessage() {
		if (_message == null) {
			return StringPool.BLANK;
		}
		else {
			return _message;
		}
	}

	@Override
	public void setMessage(String message) {
		_message = message;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			InitiativeHelp.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public InitiativeHelp toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (InitiativeHelp)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		InitiativeHelpImpl initiativeHelpImpl = new InitiativeHelpImpl();

		initiativeHelpImpl.setUuid(getUuid());
		initiativeHelpImpl.setInitiativeHelpId(getInitiativeHelpId());
		initiativeHelpImpl.setCreateDate(getCreateDate());
		initiativeHelpImpl.setPublikUserId(getPublikUserId());
		initiativeHelpImpl.setInitiativeId(getInitiativeId());
		initiativeHelpImpl.setHelpTypes(getHelpTypes());
		initiativeHelpImpl.setGroupId(getGroupId());
		initiativeHelpImpl.setMessage(getMessage());

		initiativeHelpImpl.resetOriginalValues();

		return initiativeHelpImpl;
	}

	@Override
	public int compareTo(InitiativeHelp initiativeHelp) {
		long primaryKey = initiativeHelp.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InitiativeHelp)) {
			return false;
		}

		InitiativeHelp initiativeHelp = (InitiativeHelp)obj;

		long primaryKey = initiativeHelp.getPrimaryKey();

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
		InitiativeHelpModelImpl initiativeHelpModelImpl = this;

		initiativeHelpModelImpl._originalUuid = initiativeHelpModelImpl._uuid;

		initiativeHelpModelImpl._originalPublikUserId = initiativeHelpModelImpl._publikUserId;

		initiativeHelpModelImpl._originalInitiativeId = initiativeHelpModelImpl._initiativeId;

		initiativeHelpModelImpl._setOriginalInitiativeId = false;

		initiativeHelpModelImpl._originalGroupId = initiativeHelpModelImpl._groupId;

		initiativeHelpModelImpl._setOriginalGroupId = false;

		initiativeHelpModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<InitiativeHelp> toCacheModel() {
		InitiativeHelpCacheModel initiativeHelpCacheModel = new InitiativeHelpCacheModel();

		initiativeHelpCacheModel.uuid = getUuid();

		String uuid = initiativeHelpCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			initiativeHelpCacheModel.uuid = null;
		}

		initiativeHelpCacheModel.initiativeHelpId = getInitiativeHelpId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			initiativeHelpCacheModel.createDate = createDate.getTime();
		}
		else {
			initiativeHelpCacheModel.createDate = Long.MIN_VALUE;
		}

		initiativeHelpCacheModel.publikUserId = getPublikUserId();

		String publikUserId = initiativeHelpCacheModel.publikUserId;

		if ((publikUserId != null) && (publikUserId.length() == 0)) {
			initiativeHelpCacheModel.publikUserId = null;
		}

		initiativeHelpCacheModel.initiativeId = getInitiativeId();

		initiativeHelpCacheModel.helpTypes = getHelpTypes();

		String helpTypes = initiativeHelpCacheModel.helpTypes;

		if ((helpTypes != null) && (helpTypes.length() == 0)) {
			initiativeHelpCacheModel.helpTypes = null;
		}

		initiativeHelpCacheModel.groupId = getGroupId();

		initiativeHelpCacheModel.message = getMessage();

		String message = initiativeHelpCacheModel.message;

		if ((message != null) && (message.length() == 0)) {
			initiativeHelpCacheModel.message = null;
		}

		return initiativeHelpCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", initiativeHelpId=");
		sb.append(getInitiativeHelpId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", publikUserId=");
		sb.append(getPublikUserId());
		sb.append(", initiativeId=");
		sb.append(getInitiativeId());
		sb.append(", helpTypes=");
		sb.append(getHelpTypes());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", message=");
		sb.append(getMessage());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("eu.strasbourg.service.project.model.InitiativeHelp");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>initiativeHelpId</column-name><column-value><![CDATA[");
		sb.append(getInitiativeHelpId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>publikUserId</column-name><column-value><![CDATA[");
		sb.append(getPublikUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>initiativeId</column-name><column-value><![CDATA[");
		sb.append(getInitiativeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>helpTypes</column-name><column-value><![CDATA[");
		sb.append(getHelpTypes());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>message</column-name><column-value><![CDATA[");
		sb.append(getMessage());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = InitiativeHelp.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			InitiativeHelp.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _initiativeHelpId;
	private Date _createDate;
	private String _publikUserId;
	private String _originalPublikUserId;
	private long _initiativeId;
	private long _originalInitiativeId;
	private boolean _setOriginalInitiativeId;
	private String _helpTypes;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private String _message;
	private long _columnBitmask;
	private InitiativeHelp _escapedModel;
}