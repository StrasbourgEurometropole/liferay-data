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

package eu.strasbourg.service.agenda.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.agenda.model.AgendaExport;
import eu.strasbourg.service.agenda.model.AgendaExportModel;
import eu.strasbourg.service.agenda.model.AgendaExportSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The base model implementation for the AgendaExport service. Represents a row in the &quot;agenda_AgendaExport&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link AgendaExportModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AgendaExportImpl}.
 * </p>
 *
 * @author BenjaminBini
 * @see AgendaExportImpl
 * @see AgendaExport
 * @see AgendaExportModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class AgendaExportModelImpl extends BaseModelImpl<AgendaExport>
	implements AgendaExportModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a agenda export model instance should use the {@link AgendaExport} interface instead.
	 */
	public static final String TABLE_NAME = "agenda_AgendaExport";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "agendaExportId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "lastPublishDate", Types.TIMESTAMP },
			{ "status", Types.INTEGER },
			{ "statusByUserId", Types.BIGINT },
			{ "statusByUserName", Types.VARCHAR },
			{ "statusDate", Types.TIMESTAMP },
			{ "title", Types.VARCHAR },
			{ "language", Types.VARCHAR },
			{ "exportFormat", Types.VARCHAR },
			{ "eventCategories", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("agendaExportId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("language", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("exportFormat", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("eventCategories", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table agenda_AgendaExport (uuid_ VARCHAR(75) null,agendaExportId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,lastPublishDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,title STRING null,language VARCHAR(75) null,exportFormat VARCHAR(75) null,eventCategories VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table agenda_AgendaExport";
	public static final String ORDER_BY_JPQL = " ORDER BY agendaExport.agendaExportId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY agenda_AgendaExport.agendaExportId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.agenda.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.eu.strasbourg.service.agenda.model.AgendaExport"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.agenda.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.eu.strasbourg.service.agenda.model.AgendaExport"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.agenda.service.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.eu.strasbourg.service.agenda.model.AgendaExport"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long STATUS_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long AGENDAEXPORTID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static AgendaExport toModel(AgendaExportSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		AgendaExport model = new AgendaExportImpl();

		model.setUuid(soapModel.getUuid());
		model.setAgendaExportId(soapModel.getAgendaExportId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setLastPublishDate(soapModel.getLastPublishDate());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());
		model.setTitle(soapModel.getTitle());
		model.setLanguage(soapModel.getLanguage());
		model.setExportFormat(soapModel.getExportFormat());
		model.setEventCategories(soapModel.getEventCategories());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<AgendaExport> toModels(AgendaExportSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<AgendaExport> models = new ArrayList<AgendaExport>(soapModels.length);

		for (AgendaExportSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(eu.strasbourg.service.agenda.service.util.PropsUtil.get(
				"lock.expiration.time.eu.strasbourg.service.agenda.model.AgendaExport"));

	public AgendaExportModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _agendaExportId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAgendaExportId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _agendaExportId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AgendaExport.class;
	}

	@Override
	public String getModelClassName() {
		return AgendaExport.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("agendaExportId", getAgendaExportId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("title", getTitle());
		attributes.put("language", getLanguage());
		attributes.put("exportFormat", getExportFormat());
		attributes.put("eventCategories", getEventCategories());

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

		Long agendaExportId = (Long)attributes.get("agendaExportId");

		if (agendaExportId != null) {
			setAgendaExportId(agendaExportId);
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

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
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

		String language = (String)attributes.get("language");

		if (language != null) {
			setLanguage(language);
		}

		String exportFormat = (String)attributes.get("exportFormat");

		if (exportFormat != null) {
			setExportFormat(exportFormat);
		}

		String eventCategories = (String)attributes.get("eventCategories");

		if (eventCategories != null) {
			setEventCategories(eventCategories);
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
	public long getAgendaExportId() {
		return _agendaExportId;
	}

	@Override
	public void setAgendaExportId(long agendaExportId) {
		_agendaExportId = agendaExportId;
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
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
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
	public String getTitle(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId);
	}

	@Override
	public String getTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId, useDefault);
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getTitle(), languageId,
			useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _titleCurrentLanguageId;
	}

	@JSON
	@Override
	public String getTitleCurrentValue() {
		Locale locale = getLocale(_titleCurrentLanguageId);

		return getTitle(locale);
	}

	@Override
	public Map<Locale, String> getTitleMap() {
		return LocalizationUtil.getLocalizationMap(getTitle());
	}

	@Override
	public void setTitle(String title) {
		_title = title;
	}

	@Override
	public void setTitle(String title, Locale locale) {
		setTitle(title, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setTitle(String title, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(title)) {
			setTitle(LocalizationUtil.updateLocalization(getTitle(), "Title",
					title, languageId, defaultLanguageId));
		}
		else {
			setTitle(LocalizationUtil.removeLocalization(getTitle(), "Title",
					languageId));
		}
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_titleCurrentLanguageId = languageId;
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap) {
		setTitleMap(titleMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale) {
		if (titleMap == null) {
			return;
		}

		setTitle(LocalizationUtil.updateLocalization(titleMap, getTitle(),
				"Title", LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getLanguage() {
		if (_language == null) {
			return StringPool.BLANK;
		}
		else {
			return _language;
		}
	}

	@Override
	public void setLanguage(String language) {
		_language = language;
	}

	@JSON
	@Override
	public String getExportFormat() {
		if (_exportFormat == null) {
			return StringPool.BLANK;
		}
		else {
			return _exportFormat;
		}
	}

	@Override
	public void setExportFormat(String exportFormat) {
		_exportFormat = exportFormat;
	}

	@JSON
	@Override
	public String getEventCategories() {
		if (_eventCategories == null) {
			return StringPool.BLANK;
		}
		else {
			return _eventCategories;
		}
	}

	@Override
	public void setEventCategories(String eventCategories) {
		_eventCategories = eventCategories;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				AgendaExport.class.getName()));
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
			AgendaExport.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> titleMap = getTitleMap();

		for (Map.Entry<Locale, String> entry : titleMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getTitle();

		if (xml == null) {
			return StringPool.BLANK;
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(AgendaExport.class.getName(),
				getPrimaryKey(), defaultLocale, availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String title = getTitle(defaultLocale);

		if (Validator.isNull(title)) {
			setTitle(getTitle(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setTitle(getTitle(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public AgendaExport toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (AgendaExport)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AgendaExportImpl agendaExportImpl = new AgendaExportImpl();

		agendaExportImpl.setUuid(getUuid());
		agendaExportImpl.setAgendaExportId(getAgendaExportId());
		agendaExportImpl.setGroupId(getGroupId());
		agendaExportImpl.setCompanyId(getCompanyId());
		agendaExportImpl.setUserId(getUserId());
		agendaExportImpl.setUserName(getUserName());
		agendaExportImpl.setCreateDate(getCreateDate());
		agendaExportImpl.setModifiedDate(getModifiedDate());
		agendaExportImpl.setLastPublishDate(getLastPublishDate());
		agendaExportImpl.setStatus(getStatus());
		agendaExportImpl.setStatusByUserId(getStatusByUserId());
		agendaExportImpl.setStatusByUserName(getStatusByUserName());
		agendaExportImpl.setStatusDate(getStatusDate());
		agendaExportImpl.setTitle(getTitle());
		agendaExportImpl.setLanguage(getLanguage());
		agendaExportImpl.setExportFormat(getExportFormat());
		agendaExportImpl.setEventCategories(getEventCategories());

		agendaExportImpl.resetOriginalValues();

		return agendaExportImpl;
	}

	@Override
	public int compareTo(AgendaExport agendaExport) {
		long primaryKey = agendaExport.getPrimaryKey();

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

		if (!(obj instanceof AgendaExport)) {
			return false;
		}

		AgendaExport agendaExport = (AgendaExport)obj;

		long primaryKey = agendaExport.getPrimaryKey();

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
		AgendaExportModelImpl agendaExportModelImpl = this;

		agendaExportModelImpl._originalUuid = agendaExportModelImpl._uuid;

		agendaExportModelImpl._originalGroupId = agendaExportModelImpl._groupId;

		agendaExportModelImpl._setOriginalGroupId = false;

		agendaExportModelImpl._originalCompanyId = agendaExportModelImpl._companyId;

		agendaExportModelImpl._setOriginalCompanyId = false;

		agendaExportModelImpl._setModifiedDate = false;

		agendaExportModelImpl._originalStatus = agendaExportModelImpl._status;

		agendaExportModelImpl._setOriginalStatus = false;

		agendaExportModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<AgendaExport> toCacheModel() {
		AgendaExportCacheModel agendaExportCacheModel = new AgendaExportCacheModel();

		agendaExportCacheModel.uuid = getUuid();

		String uuid = agendaExportCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			agendaExportCacheModel.uuid = null;
		}

		agendaExportCacheModel.agendaExportId = getAgendaExportId();

		agendaExportCacheModel.groupId = getGroupId();

		agendaExportCacheModel.companyId = getCompanyId();

		agendaExportCacheModel.userId = getUserId();

		agendaExportCacheModel.userName = getUserName();

		String userName = agendaExportCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			agendaExportCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			agendaExportCacheModel.createDate = createDate.getTime();
		}
		else {
			agendaExportCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			agendaExportCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			agendaExportCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			agendaExportCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			agendaExportCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		agendaExportCacheModel.status = getStatus();

		agendaExportCacheModel.statusByUserId = getStatusByUserId();

		agendaExportCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = agendaExportCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			agendaExportCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			agendaExportCacheModel.statusDate = statusDate.getTime();
		}
		else {
			agendaExportCacheModel.statusDate = Long.MIN_VALUE;
		}

		agendaExportCacheModel.title = getTitle();

		String title = agendaExportCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			agendaExportCacheModel.title = null;
		}

		agendaExportCacheModel.language = getLanguage();

		String language = agendaExportCacheModel.language;

		if ((language != null) && (language.length() == 0)) {
			agendaExportCacheModel.language = null;
		}

		agendaExportCacheModel.exportFormat = getExportFormat();

		String exportFormat = agendaExportCacheModel.exportFormat;

		if ((exportFormat != null) && (exportFormat.length() == 0)) {
			agendaExportCacheModel.exportFormat = null;
		}

		agendaExportCacheModel.eventCategories = getEventCategories();

		String eventCategories = agendaExportCacheModel.eventCategories;

		if ((eventCategories != null) && (eventCategories.length() == 0)) {
			agendaExportCacheModel.eventCategories = null;
		}

		return agendaExportCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", agendaExportId=");
		sb.append(getAgendaExportId());
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
		sb.append(", lastPublishDate=");
		sb.append(getLastPublishDate());
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
		sb.append(", language=");
		sb.append(getLanguage());
		sb.append(", exportFormat=");
		sb.append(getExportFormat());
		sb.append(", eventCategories=");
		sb.append(getEventCategories());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("eu.strasbourg.service.agenda.model.AgendaExport");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>agendaExportId</column-name><column-value><![CDATA[");
		sb.append(getAgendaExportId());
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
			"<column><column-name>lastPublishDate</column-name><column-value><![CDATA[");
		sb.append(getLastPublishDate());
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
			"<column><column-name>language</column-name><column-value><![CDATA[");
		sb.append(getLanguage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>exportFormat</column-name><column-value><![CDATA[");
		sb.append(getExportFormat());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>eventCategories</column-name><column-value><![CDATA[");
		sb.append(getEventCategories());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = AgendaExport.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			AgendaExport.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _agendaExportId;
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
	private Date _lastPublishDate;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _title;
	private String _titleCurrentLanguageId;
	private String _language;
	private String _exportFormat;
	private String _eventCategories;
	private long _columnBitmask;
	private AgendaExport _escapedModel;
}