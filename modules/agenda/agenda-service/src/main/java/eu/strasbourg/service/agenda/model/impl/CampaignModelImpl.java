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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.CampaignModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The base model implementation for the Campaign service. Represents a row in the &quot;agenda_Campaign&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CampaignModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CampaignImpl}.
 * </p>
 *
 * @author BenjaminBini
 * @see CampaignImpl
 * @see Campaign
 * @see CampaignModel
 * @generated
 */
@ProviderType
public class CampaignModelImpl extends BaseModelImpl<Campaign>
	implements CampaignModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a campaign model instance should use the {@link Campaign} interface instead.
	 */
	public static final String TABLE_NAME = "agenda_Campaign";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "campaignId", Types.BIGINT },
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
			{ "defaultImageId", Types.BIGINT },
			{ "defaultImageCopyright", Types.VARCHAR },
			{ "managersIds", Types.VARCHAR },
			{ "exportEnabled", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("campaignId", Types.BIGINT);
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
		TABLE_COLUMNS_MAP.put("defaultImageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("defaultImageCopyright", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("managersIds", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("exportEnabled", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table agenda_Campaign (uuid_ VARCHAR(75) null,campaignId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,lastPublishDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,title STRING null,defaultImageId LONG,defaultImageCopyright STRING null,managersIds VARCHAR(75) null,exportEnabled BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table agenda_Campaign";
	public static final String ORDER_BY_JPQL = " ORDER BY campaign.modifiedDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY agenda_Campaign.modifiedDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.agenda.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.eu.strasbourg.service.agenda.model.Campaign"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.agenda.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.eu.strasbourg.service.agenda.model.Campaign"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.agenda.service.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.eu.strasbourg.service.agenda.model.Campaign"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long TITLE_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 16L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(eu.strasbourg.service.agenda.service.util.PropsUtil.get(
				"lock.expiration.time.eu.strasbourg.service.agenda.model.Campaign"));

	public CampaignModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _campaignId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCampaignId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _campaignId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Campaign.class;
	}

	@Override
	public String getModelClassName() {
		return Campaign.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("campaignId", getCampaignId());
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
		attributes.put("defaultImageId", getDefaultImageId());
		attributes.put("defaultImageCopyright", getDefaultImageCopyright());
		attributes.put("managersIds", getManagersIds());
		attributes.put("exportEnabled", getExportEnabled());

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

		Long campaignId = (Long)attributes.get("campaignId");

		if (campaignId != null) {
			setCampaignId(campaignId);
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

		Long defaultImageId = (Long)attributes.get("defaultImageId");

		if (defaultImageId != null) {
			setDefaultImageId(defaultImageId);
		}

		String defaultImageCopyright = (String)attributes.get(
				"defaultImageCopyright");

		if (defaultImageCopyright != null) {
			setDefaultImageCopyright(defaultImageCopyright);
		}

		String managersIds = (String)attributes.get("managersIds");

		if (managersIds != null) {
			setManagersIds(managersIds);
		}

		Boolean exportEnabled = (Boolean)attributes.get("exportEnabled");

		if (exportEnabled != null) {
			setExportEnabled(exportEnabled);
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
	public long getCampaignId() {
		return _campaignId;
	}

	@Override
	public void setCampaignId(long campaignId) {
		_campaignId = campaignId;
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

		_columnBitmask = -1L;

		_modifiedDate = modifiedDate;
	}

	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
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
		_columnBitmask |= TITLE_COLUMN_BITMASK;

		if (_originalTitle == null) {
			_originalTitle = _title;
		}

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

	public String getOriginalTitle() {
		return GetterUtil.getString(_originalTitle);
	}

	@Override
	public long getDefaultImageId() {
		return _defaultImageId;
	}

	@Override
	public void setDefaultImageId(long defaultImageId) {
		_defaultImageId = defaultImageId;
	}

	@Override
	public String getDefaultImageCopyright() {
		if (_defaultImageCopyright == null) {
			return StringPool.BLANK;
		}
		else {
			return _defaultImageCopyright;
		}
	}

	@Override
	public String getDefaultImageCopyright(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDefaultImageCopyright(languageId);
	}

	@Override
	public String getDefaultImageCopyright(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDefaultImageCopyright(languageId, useDefault);
	}

	@Override
	public String getDefaultImageCopyright(String languageId) {
		return LocalizationUtil.getLocalization(getDefaultImageCopyright(),
			languageId);
	}

	@Override
	public String getDefaultImageCopyright(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getDefaultImageCopyright(),
			languageId, useDefault);
	}

	@Override
	public String getDefaultImageCopyrightCurrentLanguageId() {
		return _defaultImageCopyrightCurrentLanguageId;
	}

	@JSON
	@Override
	public String getDefaultImageCopyrightCurrentValue() {
		Locale locale = getLocale(_defaultImageCopyrightCurrentLanguageId);

		return getDefaultImageCopyright(locale);
	}

	@Override
	public Map<Locale, String> getDefaultImageCopyrightMap() {
		return LocalizationUtil.getLocalizationMap(getDefaultImageCopyright());
	}

	@Override
	public void setDefaultImageCopyright(String defaultImageCopyright) {
		_defaultImageCopyright = defaultImageCopyright;
	}

	@Override
	public void setDefaultImageCopyright(String defaultImageCopyright,
		Locale locale) {
		setDefaultImageCopyright(defaultImageCopyright, locale,
			LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDefaultImageCopyright(String defaultImageCopyright,
		Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(defaultImageCopyright)) {
			setDefaultImageCopyright(LocalizationUtil.updateLocalization(
					getDefaultImageCopyright(), "DefaultImageCopyright",
					defaultImageCopyright, languageId, defaultLanguageId));
		}
		else {
			setDefaultImageCopyright(LocalizationUtil.removeLocalization(
					getDefaultImageCopyright(), "DefaultImageCopyright",
					languageId));
		}
	}

	@Override
	public void setDefaultImageCopyrightCurrentLanguageId(String languageId) {
		_defaultImageCopyrightCurrentLanguageId = languageId;
	}

	@Override
	public void setDefaultImageCopyrightMap(
		Map<Locale, String> defaultImageCopyrightMap) {
		setDefaultImageCopyrightMap(defaultImageCopyrightMap,
			LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDefaultImageCopyrightMap(
		Map<Locale, String> defaultImageCopyrightMap, Locale defaultLocale) {
		if (defaultImageCopyrightMap == null) {
			return;
		}

		setDefaultImageCopyright(LocalizationUtil.updateLocalization(
				defaultImageCopyrightMap, getDefaultImageCopyright(),
				"DefaultImageCopyright", LocaleUtil.toLanguageId(defaultLocale)));
	}

	@Override
	public String getManagersIds() {
		if (_managersIds == null) {
			return StringPool.BLANK;
		}
		else {
			return _managersIds;
		}
	}

	@Override
	public void setManagersIds(String managersIds) {
		_managersIds = managersIds;
	}

	@Override
	public Boolean getExportEnabled() {
		return _exportEnabled;
	}

	@Override
	public void setExportEnabled(Boolean exportEnabled) {
		_exportEnabled = exportEnabled;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Campaign.class.getName()));
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
			Campaign.class.getName(), getPrimaryKey());
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

		Map<Locale, String> defaultImageCopyrightMap = getDefaultImageCopyrightMap();

		for (Map.Entry<Locale, String> entry : defaultImageCopyrightMap.entrySet()) {
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

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(Campaign.class.getName(),
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

		String defaultImageCopyright = getDefaultImageCopyright(defaultLocale);

		if (Validator.isNull(defaultImageCopyright)) {
			setDefaultImageCopyright(getDefaultImageCopyright(
					modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDefaultImageCopyright(getDefaultImageCopyright(defaultLocale),
				defaultLocale, defaultLocale);
		}
	}

	@Override
	public Campaign toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Campaign)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CampaignImpl campaignImpl = new CampaignImpl();

		campaignImpl.setUuid(getUuid());
		campaignImpl.setCampaignId(getCampaignId());
		campaignImpl.setGroupId(getGroupId());
		campaignImpl.setCompanyId(getCompanyId());
		campaignImpl.setUserId(getUserId());
		campaignImpl.setUserName(getUserName());
		campaignImpl.setCreateDate(getCreateDate());
		campaignImpl.setModifiedDate(getModifiedDate());
		campaignImpl.setLastPublishDate(getLastPublishDate());
		campaignImpl.setStatus(getStatus());
		campaignImpl.setStatusByUserId(getStatusByUserId());
		campaignImpl.setStatusByUserName(getStatusByUserName());
		campaignImpl.setStatusDate(getStatusDate());
		campaignImpl.setTitle(getTitle());
		campaignImpl.setDefaultImageId(getDefaultImageId());
		campaignImpl.setDefaultImageCopyright(getDefaultImageCopyright());
		campaignImpl.setManagersIds(getManagersIds());
		campaignImpl.setExportEnabled(getExportEnabled());

		campaignImpl.resetOriginalValues();

		return campaignImpl;
	}

	@Override
	public int compareTo(Campaign campaign) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(), campaign.getModifiedDate());

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

		if (!(obj instanceof Campaign)) {
			return false;
		}

		Campaign campaign = (Campaign)obj;

		long primaryKey = campaign.getPrimaryKey();

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
		CampaignModelImpl campaignModelImpl = this;

		campaignModelImpl._originalUuid = campaignModelImpl._uuid;

		campaignModelImpl._originalGroupId = campaignModelImpl._groupId;

		campaignModelImpl._setOriginalGroupId = false;

		campaignModelImpl._originalCompanyId = campaignModelImpl._companyId;

		campaignModelImpl._setOriginalCompanyId = false;

		campaignModelImpl._setModifiedDate = false;

		campaignModelImpl._originalTitle = campaignModelImpl._title;

		campaignModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Campaign> toCacheModel() {
		CampaignCacheModel campaignCacheModel = new CampaignCacheModel();

		campaignCacheModel.uuid = getUuid();

		String uuid = campaignCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			campaignCacheModel.uuid = null;
		}

		campaignCacheModel.campaignId = getCampaignId();

		campaignCacheModel.groupId = getGroupId();

		campaignCacheModel.companyId = getCompanyId();

		campaignCacheModel.userId = getUserId();

		campaignCacheModel.userName = getUserName();

		String userName = campaignCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			campaignCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			campaignCacheModel.createDate = createDate.getTime();
		}
		else {
			campaignCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			campaignCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			campaignCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			campaignCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			campaignCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		campaignCacheModel.status = getStatus();

		campaignCacheModel.statusByUserId = getStatusByUserId();

		campaignCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = campaignCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			campaignCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			campaignCacheModel.statusDate = statusDate.getTime();
		}
		else {
			campaignCacheModel.statusDate = Long.MIN_VALUE;
		}

		campaignCacheModel.title = getTitle();

		String title = campaignCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			campaignCacheModel.title = null;
		}

		campaignCacheModel.defaultImageId = getDefaultImageId();

		campaignCacheModel.defaultImageCopyright = getDefaultImageCopyright();

		String defaultImageCopyright = campaignCacheModel.defaultImageCopyright;

		if ((defaultImageCopyright != null) &&
				(defaultImageCopyright.length() == 0)) {
			campaignCacheModel.defaultImageCopyright = null;
		}

		campaignCacheModel.managersIds = getManagersIds();

		String managersIds = campaignCacheModel.managersIds;

		if ((managersIds != null) && (managersIds.length() == 0)) {
			campaignCacheModel.managersIds = null;
		}

		campaignCacheModel.exportEnabled = getExportEnabled();

		return campaignCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", campaignId=");
		sb.append(getCampaignId());
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
		sb.append(", defaultImageId=");
		sb.append(getDefaultImageId());
		sb.append(", defaultImageCopyright=");
		sb.append(getDefaultImageCopyright());
		sb.append(", managersIds=");
		sb.append(getManagersIds());
		sb.append(", exportEnabled=");
		sb.append(getExportEnabled());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("eu.strasbourg.service.agenda.model.Campaign");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>campaignId</column-name><column-value><![CDATA[");
		sb.append(getCampaignId());
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
			"<column><column-name>defaultImageId</column-name><column-value><![CDATA[");
		sb.append(getDefaultImageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>defaultImageCopyright</column-name><column-value><![CDATA[");
		sb.append(getDefaultImageCopyright());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>managersIds</column-name><column-value><![CDATA[");
		sb.append(getManagersIds());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>exportEnabled</column-name><column-value><![CDATA[");
		sb.append(getExportEnabled());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Campaign.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Campaign.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _campaignId;
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
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _title;
	private String _titleCurrentLanguageId;
	private String _originalTitle;
	private long _defaultImageId;
	private String _defaultImageCopyright;
	private String _defaultImageCopyrightCurrentLanguageId;
	private String _managersIds;
	private Boolean _exportEnabled;
	private long _columnBitmask;
	private Campaign _escapedModel;
}