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

package eu.strasbourg.service.agenda.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link AgendaExport}.
 * </p>
 *
 * @author BenjaminBini
 * @see AgendaExport
 * @generated
 */
@ProviderType
public class AgendaExportWrapper implements AgendaExport,
	ModelWrapper<AgendaExport> {
	public AgendaExportWrapper(AgendaExport agendaExport) {
		_agendaExport = agendaExport;
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
	}

	/**
	* Returns <code>true</code> if this agenda export is approved.
	*
	* @return <code>true</code> if this agenda export is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _agendaExport.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _agendaExport.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this agenda export is denied.
	*
	* @return <code>true</code> if this agenda export is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _agendaExport.isDenied();
	}

	/**
	* Returns <code>true</code> if this agenda export is a draft.
	*
	* @return <code>true</code> if this agenda export is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _agendaExport.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _agendaExport.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this agenda export is expired.
	*
	* @return <code>true</code> if this agenda export is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _agendaExport.isExpired();
	}

	/**
	* Returns <code>true</code> if this agenda export is inactive.
	*
	* @return <code>true</code> if this agenda export is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _agendaExport.isInactive();
	}

	/**
	* Returns <code>true</code> if this agenda export is incomplete.
	*
	* @return <code>true</code> if this agenda export is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _agendaExport.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _agendaExport.isNew();
	}

	/**
	* Returns <code>true</code> if this agenda export is pending.
	*
	* @return <code>true</code> if this agenda export is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _agendaExport.isPending();
	}

	/**
	* Returns <code>true</code> if this agenda export is scheduled.
	*
	* @return <code>true</code> if this agenda export is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _agendaExport.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _agendaExport.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _agendaExport.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.agenda.model.AgendaExport> toCacheModel() {
		return _agendaExport.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport toEscapedModel() {
		return new AgendaExportWrapper(_agendaExport.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport toUnescapedModel() {
		return new AgendaExportWrapper(_agendaExport.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.agenda.model.AgendaExport agendaExport) {
		return _agendaExport.compareTo(agendaExport);
	}

	/**
	* Returns the status of this agenda export.
	*
	* @return the status of this agenda export
	*/
	@Override
	public int getStatus() {
		return _agendaExport.getStatus();
	}

	@Override
	public int hashCode() {
		return _agendaExport.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _agendaExport.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AgendaExportWrapper((AgendaExport)_agendaExport.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _agendaExport.getDefaultLanguageId();
	}

	/**
	* Returns the status by user name of this agenda export.
	*
	* @return the status by user name of this agenda export
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _agendaExport.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this agenda export.
	*
	* @return the status by user uuid of this agenda export
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _agendaExport.getStatusByUserUuid();
	}

	/**
	* Returns the title of this agenda export.
	*
	* @return the title of this agenda export
	*/
	@Override
	public java.lang.String getTitle() {
		return _agendaExport.getTitle();
	}

	/**
	* Returns the localized title of this agenda export in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this agenda export
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _agendaExport.getTitle(languageId);
	}

	/**
	* Returns the localized title of this agenda export in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this agenda export
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _agendaExport.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this agenda export in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this agenda export
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _agendaExport.getTitle(locale);
	}

	/**
	* Returns the localized title of this agenda export in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this agenda export. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _agendaExport.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _agendaExport.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _agendaExport.getTitleCurrentValue();
	}

	/**
	* Returns the user name of this agenda export.
	*
	* @return the user name of this agenda export
	*/
	@Override
	public java.lang.String getUserName() {
		return _agendaExport.getUserName();
	}

	/**
	* Returns the user uuid of this agenda export.
	*
	* @return the user uuid of this agenda export
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _agendaExport.getUserUuid();
	}

	/**
	* Returns the uuid of this agenda export.
	*
	* @return the uuid of this agenda export
	*/
	@Override
	public java.lang.String getUuid() {
		return _agendaExport.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _agendaExport.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _agendaExport.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _agendaExport.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this agenda export.
	*
	* @return the create date of this agenda export
	*/
	@Override
	public Date getCreateDate() {
		return _agendaExport.getCreateDate();
	}

	/**
	* Returns the last publish date of this agenda export.
	*
	* @return the last publish date of this agenda export
	*/
	@Override
	public Date getLastPublishDate() {
		return _agendaExport.getLastPublishDate();
	}

	/**
	* Returns the modified date of this agenda export.
	*
	* @return the modified date of this agenda export
	*/
	@Override
	public Date getModifiedDate() {
		return _agendaExport.getModifiedDate();
	}

	/**
	* Returns the status date of this agenda export.
	*
	* @return the status date of this agenda export
	*/
	@Override
	public Date getStatusDate() {
		return _agendaExport.getStatusDate();
	}

	/**
	* Retourne la liste des périodes auxquelles l'événement à lieu (classées par
	* date de début croissante)
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.AgendaExportPeriod> getAgendaExportPeriods() {
		return _agendaExport.getAgendaExportPeriods();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _agendaExport.getCategories();
	}

	/**
	* Returns a map of the locales and localized titles of this agenda export.
	*
	* @return the locales and localized titles of this agenda export
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _agendaExport.getTitleMap();
	}

	/**
	* Returns the agenda export ID of this agenda export.
	*
	* @return the agenda export ID of this agenda export
	*/
	@Override
	public long getAgendaExportId() {
		return _agendaExport.getAgendaExportId();
	}

	/**
	* Returns the company ID of this agenda export.
	*
	* @return the company ID of this agenda export
	*/
	@Override
	public long getCompanyId() {
		return _agendaExport.getCompanyId();
	}

	/**
	* Returns the group ID of this agenda export.
	*
	* @return the group ID of this agenda export
	*/
	@Override
	public long getGroupId() {
		return _agendaExport.getGroupId();
	}

	/**
	* Returns the primary key of this agenda export.
	*
	* @return the primary key of this agenda export
	*/
	@Override
	public long getPrimaryKey() {
		return _agendaExport.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this agenda export.
	*
	* @return the status by user ID of this agenda export
	*/
	@Override
	public long getStatusByUserId() {
		return _agendaExport.getStatusByUserId();
	}

	/**
	* Returns the user ID of this agenda export.
	*
	* @return the user ID of this agenda export
	*/
	@Override
	public long getUserId() {
		return _agendaExport.getUserId();
	}

	@Override
	public void persist() {
		_agendaExport.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_agendaExport.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_agendaExport.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the agenda export ID of this agenda export.
	*
	* @param agendaExportId the agenda export ID of this agenda export
	*/
	@Override
	public void setAgendaExportId(long agendaExportId) {
		_agendaExport.setAgendaExportId(agendaExportId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_agendaExport.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this agenda export.
	*
	* @param companyId the company ID of this agenda export
	*/
	@Override
	public void setCompanyId(long companyId) {
		_agendaExport.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this agenda export.
	*
	* @param createDate the create date of this agenda export
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_agendaExport.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_agendaExport.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_agendaExport.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_agendaExport.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this agenda export.
	*
	* @param groupId the group ID of this agenda export
	*/
	@Override
	public void setGroupId(long groupId) {
		_agendaExport.setGroupId(groupId);
	}

	/**
	* Sets the last publish date of this agenda export.
	*
	* @param lastPublishDate the last publish date of this agenda export
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_agendaExport.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this agenda export.
	*
	* @param modifiedDate the modified date of this agenda export
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_agendaExport.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_agendaExport.setNew(n);
	}

	/**
	* Sets the primary key of this agenda export.
	*
	* @param primaryKey the primary key of this agenda export
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_agendaExport.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_agendaExport.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this agenda export.
	*
	* @param status the status of this agenda export
	*/
	@Override
	public void setStatus(int status) {
		_agendaExport.setStatus(status);
	}

	/**
	* Sets the status by user ID of this agenda export.
	*
	* @param statusByUserId the status by user ID of this agenda export
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_agendaExport.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this agenda export.
	*
	* @param statusByUserName the status by user name of this agenda export
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_agendaExport.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this agenda export.
	*
	* @param statusByUserUuid the status by user uuid of this agenda export
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_agendaExport.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this agenda export.
	*
	* @param statusDate the status date of this agenda export
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_agendaExport.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this agenda export.
	*
	* @param title the title of this agenda export
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_agendaExport.setTitle(title);
	}

	/**
	* Sets the localized title of this agenda export in the language.
	*
	* @param title the localized title of this agenda export
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_agendaExport.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this agenda export in the language, and sets the default locale.
	*
	* @param title the localized title of this agenda export
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_agendaExport.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_agendaExport.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this agenda export from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this agenda export
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_agendaExport.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this agenda export from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this agenda export
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_agendaExport.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the user ID of this agenda export.
	*
	* @param userId the user ID of this agenda export
	*/
	@Override
	public void setUserId(long userId) {
		_agendaExport.setUserId(userId);
	}

	/**
	* Sets the user name of this agenda export.
	*
	* @param userName the user name of this agenda export
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_agendaExport.setUserName(userName);
	}

	/**
	* Sets the user uuid of this agenda export.
	*
	* @param userUuid the user uuid of this agenda export
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_agendaExport.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this agenda export.
	*
	* @param uuid the uuid of this agenda export
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_agendaExport.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AgendaExportWrapper)) {
			return false;
		}

		AgendaExportWrapper agendaExportWrapper = (AgendaExportWrapper)obj;

		if (Objects.equals(_agendaExport, agendaExportWrapper._agendaExport)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _agendaExport.getStagedModelType();
	}

	@Override
	public AgendaExport getWrappedModel() {
		return _agendaExport;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _agendaExport.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _agendaExport.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_agendaExport.resetOriginalValues();
	}

	private final AgendaExport _agendaExport;
}