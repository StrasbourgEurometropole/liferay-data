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
 * This class is a wrapper for {@link Association}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Association
 * @generated
 */
@ProviderType
public class AssociationWrapper implements Association,
	ModelWrapper<Association> {
	public AssociationWrapper(Association association) {
		_association = association;
	}

	@Override
	public Class<?> getModelClass() {
		return Association.class;
	}

	@Override
	public String getModelClassName() {
		return Association.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("associationId", getAssociationId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("presentation", getPresentation());
		attributes.put("phone", getPhone());
		attributes.put("siteURL", getSiteURL());
		attributes.put("mail", getMail());
		attributes.put("facebookURL", getFacebookURL());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long associationId = (Long)attributes.get("associationId");

		if (associationId != null) {
			setAssociationId(associationId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String presentation = (String)attributes.get("presentation");

		if (presentation != null) {
			setPresentation(presentation);
		}

		String phone = (String)attributes.get("phone");

		if (phone != null) {
			setPhone(phone);
		}

		String siteURL = (String)attributes.get("siteURL");

		if (siteURL != null) {
			setSiteURL(siteURL);
		}

		String mail = (String)attributes.get("mail");

		if (mail != null) {
			setMail(mail);
		}

		String facebookURL = (String)attributes.get("facebookURL");

		if (facebookURL != null) {
			setFacebookURL(facebookURL);
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
	}

	/**
	* Returns <code>true</code> if this association is approved.
	*
	* @return <code>true</code> if this association is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _association.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _association.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this association is denied.
	*
	* @return <code>true</code> if this association is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _association.isDenied();
	}

	/**
	* Returns <code>true</code> if this association is a draft.
	*
	* @return <code>true</code> if this association is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _association.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _association.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this association is expired.
	*
	* @return <code>true</code> if this association is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _association.isExpired();
	}

	/**
	* Returns <code>true</code> if this association is inactive.
	*
	* @return <code>true</code> if this association is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _association.isInactive();
	}

	/**
	* Returns <code>true</code> if this association is incomplete.
	*
	* @return <code>true</code> if this association is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _association.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _association.isNew();
	}

	/**
	* Returns <code>true</code> if this association is pending.
	*
	* @return <code>true</code> if this association is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _association.isPending();
	}

	/**
	* Returns <code>true</code> if this association is scheduled.
	*
	* @return <code>true</code> if this association is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _association.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché à cette entité
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _association.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _association.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.activity.model.Association> toCacheModel() {
		return _association.toCacheModel();
	}

	/**
	* Retourne la version live de cette entité
	*/
	@Override
	public eu.strasbourg.service.activity.model.Association getLiveVersion() {
		return _association.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.activity.model.Association toEscapedModel() {
		return new AssociationWrapper(_association.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.activity.model.Association toUnescapedModel() {
		return new AssociationWrapper(_association.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.activity.model.Association association) {
		return _association.compareTo(association);
	}

	/**
	* Returns the status of this association.
	*
	* @return the status of this association
	*/
	@Override
	public int getStatus() {
		return _association.getStatus();
	}

	@Override
	public int hashCode() {
		return _association.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _association.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AssociationWrapper((Association)_association.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _association.getDefaultLanguageId();
	}

	/**
	* Returns the facebook url of this association.
	*
	* @return the facebook url of this association
	*/
	@Override
	public java.lang.String getFacebookURL() {
		return _association.getFacebookURL();
	}

	/**
	* Returns the localized facebook url of this association in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized facebook url of this association
	*/
	@Override
	public java.lang.String getFacebookURL(java.lang.String languageId) {
		return _association.getFacebookURL(languageId);
	}

	/**
	* Returns the localized facebook url of this association in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized facebook url of this association
	*/
	@Override
	public java.lang.String getFacebookURL(java.lang.String languageId,
		boolean useDefault) {
		return _association.getFacebookURL(languageId, useDefault);
	}

	/**
	* Returns the localized facebook url of this association in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized facebook url of this association
	*/
	@Override
	public java.lang.String getFacebookURL(java.util.Locale locale) {
		return _association.getFacebookURL(locale);
	}

	/**
	* Returns the localized facebook url of this association in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized facebook url of this association. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getFacebookURL(java.util.Locale locale,
		boolean useDefault) {
		return _association.getFacebookURL(locale, useDefault);
	}

	@Override
	public java.lang.String getFacebookURLCurrentLanguageId() {
		return _association.getFacebookURLCurrentLanguageId();
	}

	@Override
	public java.lang.String getFacebookURLCurrentValue() {
		return _association.getFacebookURLCurrentValue();
	}

	/**
	* Returns the mail of this association.
	*
	* @return the mail of this association
	*/
	@Override
	public java.lang.String getMail() {
		return _association.getMail();
	}

	/**
	* Returns the name of this association.
	*
	* @return the name of this association
	*/
	@Override
	public java.lang.String getName() {
		return _association.getName();
	}

	/**
	* Returns the localized name of this association in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this association
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _association.getName(languageId);
	}

	/**
	* Returns the localized name of this association in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this association
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _association.getName(languageId, useDefault);
	}

	/**
	* Returns the localized name of this association in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this association
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _association.getName(locale);
	}

	/**
	* Returns the localized name of this association in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this association. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _association.getName(locale, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _association.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _association.getNameCurrentValue();
	}

	/**
	* Returns the phone of this association.
	*
	* @return the phone of this association
	*/
	@Override
	public java.lang.String getPhone() {
		return _association.getPhone();
	}

	/**
	* Returns the presentation of this association.
	*
	* @return the presentation of this association
	*/
	@Override
	public java.lang.String getPresentation() {
		return _association.getPresentation();
	}

	/**
	* Returns the localized presentation of this association in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized presentation of this association
	*/
	@Override
	public java.lang.String getPresentation(java.lang.String languageId) {
		return _association.getPresentation(languageId);
	}

	/**
	* Returns the localized presentation of this association in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized presentation of this association
	*/
	@Override
	public java.lang.String getPresentation(java.lang.String languageId,
		boolean useDefault) {
		return _association.getPresentation(languageId, useDefault);
	}

	/**
	* Returns the localized presentation of this association in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized presentation of this association
	*/
	@Override
	public java.lang.String getPresentation(java.util.Locale locale) {
		return _association.getPresentation(locale);
	}

	/**
	* Returns the localized presentation of this association in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized presentation of this association. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPresentation(java.util.Locale locale,
		boolean useDefault) {
		return _association.getPresentation(locale, useDefault);
	}

	@Override
	public java.lang.String getPresentationCurrentLanguageId() {
		return _association.getPresentationCurrentLanguageId();
	}

	@Override
	public java.lang.String getPresentationCurrentValue() {
		return _association.getPresentationCurrentValue();
	}

	/**
	* Returns the site url of this association.
	*
	* @return the site url of this association
	*/
	@Override
	public java.lang.String getSiteURL() {
		return _association.getSiteURL();
	}

	/**
	* Returns the localized site url of this association in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized site url of this association
	*/
	@Override
	public java.lang.String getSiteURL(java.lang.String languageId) {
		return _association.getSiteURL(languageId);
	}

	/**
	* Returns the localized site url of this association in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized site url of this association
	*/
	@Override
	public java.lang.String getSiteURL(java.lang.String languageId,
		boolean useDefault) {
		return _association.getSiteURL(languageId, useDefault);
	}

	/**
	* Returns the localized site url of this association in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized site url of this association
	*/
	@Override
	public java.lang.String getSiteURL(java.util.Locale locale) {
		return _association.getSiteURL(locale);
	}

	/**
	* Returns the localized site url of this association in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized site url of this association. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getSiteURL(java.util.Locale locale,
		boolean useDefault) {
		return _association.getSiteURL(locale, useDefault);
	}

	@Override
	public java.lang.String getSiteURLCurrentLanguageId() {
		return _association.getSiteURLCurrentLanguageId();
	}

	@Override
	public java.lang.String getSiteURLCurrentValue() {
		return _association.getSiteURLCurrentValue();
	}

	/**
	* Returns the status by user name of this association.
	*
	* @return the status by user name of this association
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _association.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this association.
	*
	* @return the status by user uuid of this association
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _association.getStatusByUserUuid();
	}

	/**
	* Returns the user name of this association.
	*
	* @return the user name of this association
	*/
	@Override
	public java.lang.String getUserName() {
		return _association.getUserName();
	}

	/**
	* Returns the user uuid of this association.
	*
	* @return the user uuid of this association
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _association.getUserUuid();
	}

	/**
	* Returns the uuid of this association.
	*
	* @return the uuid of this association
	*/
	@Override
	public java.lang.String getUuid() {
		return _association.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _association.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _association.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _association.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this association.
	*
	* @return the create date of this association
	*/
	@Override
	public Date getCreateDate() {
		return _association.getCreateDate();
	}

	/**
	* Returns the modified date of this association.
	*
	* @return the modified date of this association
	*/
	@Override
	public Date getModifiedDate() {
		return _association.getModifiedDate();
	}

	/**
	* Returns the status date of this association.
	*
	* @return the status date of this association
	*/
	@Override
	public Date getStatusDate() {
		return _association.getStatusDate();
	}

	/**
	* Retourne les activités de l'association
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.AssociationActivity> getAssociationActivities() {
		return _association.getAssociationActivities();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cette entité (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _association.getCategories();
	}

	/**
	* Returns a map of the locales and localized facebook urls of this association.
	*
	* @return the locales and localized facebook urls of this association
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getFacebookURLMap() {
		return _association.getFacebookURLMap();
	}

	/**
	* Returns a map of the locales and localized names of this association.
	*
	* @return the locales and localized names of this association
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _association.getNameMap();
	}

	/**
	* Returns a map of the locales and localized presentations of this association.
	*
	* @return the locales and localized presentations of this association
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getPresentationMap() {
		return _association.getPresentationMap();
	}

	/**
	* Returns a map of the locales and localized site urls of this association.
	*
	* @return the locales and localized site urls of this association
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getSiteURLMap() {
		return _association.getSiteURLMap();
	}

	/**
	* Returns the association ID of this association.
	*
	* @return the association ID of this association
	*/
	@Override
	public long getAssociationId() {
		return _association.getAssociationId();
	}

	/**
	* Returns the company ID of this association.
	*
	* @return the company ID of this association
	*/
	@Override
	public long getCompanyId() {
		return _association.getCompanyId();
	}

	/**
	* Returns the group ID of this association.
	*
	* @return the group ID of this association
	*/
	@Override
	public long getGroupId() {
		return _association.getGroupId();
	}

	/**
	* Returns the primary key of this association.
	*
	* @return the primary key of this association
	*/
	@Override
	public long getPrimaryKey() {
		return _association.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this association.
	*
	* @return the status by user ID of this association
	*/
	@Override
	public long getStatusByUserId() {
		return _association.getStatusByUserId();
	}

	/**
	* Returns the user ID of this association.
	*
	* @return the user ID of this association
	*/
	@Override
	public long getUserId() {
		return _association.getUserId();
	}

	@Override
	public void persist() {
		_association.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_association.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_association.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the association ID of this association.
	*
	* @param associationId the association ID of this association
	*/
	@Override
	public void setAssociationId(long associationId) {
		_association.setAssociationId(associationId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_association.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this association.
	*
	* @param companyId the company ID of this association
	*/
	@Override
	public void setCompanyId(long companyId) {
		_association.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this association.
	*
	* @param createDate the create date of this association
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_association.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_association.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_association.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_association.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the facebook url of this association.
	*
	* @param facebookURL the facebook url of this association
	*/
	@Override
	public void setFacebookURL(java.lang.String facebookURL) {
		_association.setFacebookURL(facebookURL);
	}

	/**
	* Sets the localized facebook url of this association in the language.
	*
	* @param facebookURL the localized facebook url of this association
	* @param locale the locale of the language
	*/
	@Override
	public void setFacebookURL(java.lang.String facebookURL,
		java.util.Locale locale) {
		_association.setFacebookURL(facebookURL, locale);
	}

	/**
	* Sets the localized facebook url of this association in the language, and sets the default locale.
	*
	* @param facebookURL the localized facebook url of this association
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFacebookURL(java.lang.String facebookURL,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_association.setFacebookURL(facebookURL, locale, defaultLocale);
	}

	@Override
	public void setFacebookURLCurrentLanguageId(java.lang.String languageId) {
		_association.setFacebookURLCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized facebook urls of this association from the map of locales and localized facebook urls.
	*
	* @param facebookURLMap the locales and localized facebook urls of this association
	*/
	@Override
	public void setFacebookURLMap(
		Map<java.util.Locale, java.lang.String> facebookURLMap) {
		_association.setFacebookURLMap(facebookURLMap);
	}

	/**
	* Sets the localized facebook urls of this association from the map of locales and localized facebook urls, and sets the default locale.
	*
	* @param facebookURLMap the locales and localized facebook urls of this association
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFacebookURLMap(
		Map<java.util.Locale, java.lang.String> facebookURLMap,
		java.util.Locale defaultLocale) {
		_association.setFacebookURLMap(facebookURLMap, defaultLocale);
	}

	/**
	* Sets the group ID of this association.
	*
	* @param groupId the group ID of this association
	*/
	@Override
	public void setGroupId(long groupId) {
		_association.setGroupId(groupId);
	}

	/**
	* Sets the mail of this association.
	*
	* @param mail the mail of this association
	*/
	@Override
	public void setMail(java.lang.String mail) {
		_association.setMail(mail);
	}

	/**
	* Sets the modified date of this association.
	*
	* @param modifiedDate the modified date of this association
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_association.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this association.
	*
	* @param name the name of this association
	*/
	@Override
	public void setName(java.lang.String name) {
		_association.setName(name);
	}

	/**
	* Sets the localized name of this association in the language.
	*
	* @param name the localized name of this association
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_association.setName(name, locale);
	}

	/**
	* Sets the localized name of this association in the language, and sets the default locale.
	*
	* @param name the localized name of this association
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_association.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_association.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this association from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this association
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_association.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this association from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this association
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_association.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_association.setNew(n);
	}

	/**
	* Sets the phone of this association.
	*
	* @param phone the phone of this association
	*/
	@Override
	public void setPhone(java.lang.String phone) {
		_association.setPhone(phone);
	}

	/**
	* Sets the presentation of this association.
	*
	* @param presentation the presentation of this association
	*/
	@Override
	public void setPresentation(java.lang.String presentation) {
		_association.setPresentation(presentation);
	}

	/**
	* Sets the localized presentation of this association in the language.
	*
	* @param presentation the localized presentation of this association
	* @param locale the locale of the language
	*/
	@Override
	public void setPresentation(java.lang.String presentation,
		java.util.Locale locale) {
		_association.setPresentation(presentation, locale);
	}

	/**
	* Sets the localized presentation of this association in the language, and sets the default locale.
	*
	* @param presentation the localized presentation of this association
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPresentation(java.lang.String presentation,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_association.setPresentation(presentation, locale, defaultLocale);
	}

	@Override
	public void setPresentationCurrentLanguageId(java.lang.String languageId) {
		_association.setPresentationCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized presentations of this association from the map of locales and localized presentations.
	*
	* @param presentationMap the locales and localized presentations of this association
	*/
	@Override
	public void setPresentationMap(
		Map<java.util.Locale, java.lang.String> presentationMap) {
		_association.setPresentationMap(presentationMap);
	}

	/**
	* Sets the localized presentations of this association from the map of locales and localized presentations, and sets the default locale.
	*
	* @param presentationMap the locales and localized presentations of this association
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPresentationMap(
		Map<java.util.Locale, java.lang.String> presentationMap,
		java.util.Locale defaultLocale) {
		_association.setPresentationMap(presentationMap, defaultLocale);
	}

	/**
	* Sets the primary key of this association.
	*
	* @param primaryKey the primary key of this association
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_association.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_association.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the site url of this association.
	*
	* @param siteURL the site url of this association
	*/
	@Override
	public void setSiteURL(java.lang.String siteURL) {
		_association.setSiteURL(siteURL);
	}

	/**
	* Sets the localized site url of this association in the language.
	*
	* @param siteURL the localized site url of this association
	* @param locale the locale of the language
	*/
	@Override
	public void setSiteURL(java.lang.String siteURL, java.util.Locale locale) {
		_association.setSiteURL(siteURL, locale);
	}

	/**
	* Sets the localized site url of this association in the language, and sets the default locale.
	*
	* @param siteURL the localized site url of this association
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSiteURL(java.lang.String siteURL, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_association.setSiteURL(siteURL, locale, defaultLocale);
	}

	@Override
	public void setSiteURLCurrentLanguageId(java.lang.String languageId) {
		_association.setSiteURLCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized site urls of this association from the map of locales and localized site urls.
	*
	* @param siteURLMap the locales and localized site urls of this association
	*/
	@Override
	public void setSiteURLMap(
		Map<java.util.Locale, java.lang.String> siteURLMap) {
		_association.setSiteURLMap(siteURLMap);
	}

	/**
	* Sets the localized site urls of this association from the map of locales and localized site urls, and sets the default locale.
	*
	* @param siteURLMap the locales and localized site urls of this association
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSiteURLMap(
		Map<java.util.Locale, java.lang.String> siteURLMap,
		java.util.Locale defaultLocale) {
		_association.setSiteURLMap(siteURLMap, defaultLocale);
	}

	/**
	* Sets the status of this association.
	*
	* @param status the status of this association
	*/
	@Override
	public void setStatus(int status) {
		_association.setStatus(status);
	}

	/**
	* Sets the status by user ID of this association.
	*
	* @param statusByUserId the status by user ID of this association
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_association.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this association.
	*
	* @param statusByUserName the status by user name of this association
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_association.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this association.
	*
	* @param statusByUserUuid the status by user uuid of this association
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_association.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this association.
	*
	* @param statusDate the status date of this association
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_association.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this association.
	*
	* @param userId the user ID of this association
	*/
	@Override
	public void setUserId(long userId) {
		_association.setUserId(userId);
	}

	/**
	* Sets the user name of this association.
	*
	* @param userName the user name of this association
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_association.setUserName(userName);
	}

	/**
	* Sets the user uuid of this association.
	*
	* @param userUuid the user uuid of this association
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_association.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this association.
	*
	* @param uuid the uuid of this association
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_association.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssociationWrapper)) {
			return false;
		}

		AssociationWrapper associationWrapper = (AssociationWrapper)obj;

		if (Objects.equals(_association, associationWrapper._association)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _association.getStagedModelType();
	}

	@Override
	public Association getWrappedModel() {
		return _association;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _association.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _association.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_association.resetOriginalValues();
	}

	private final Association _association;
}