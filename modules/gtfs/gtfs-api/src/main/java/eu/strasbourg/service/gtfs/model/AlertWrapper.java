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

package eu.strasbourg.service.gtfs.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Alert}.
 * </p>
 *
 * @author Cedric Henry
 * @see Alert
 * @generated
 */
@ProviderType
public class AlertWrapper implements Alert, ModelWrapper<Alert> {
	public AlertWrapper(Alert alert) {
		_alert = alert;
	}

	@Override
	public Class<?> getModelClass() {
		return Alert.class;
	}

	@Override
	public String getModelClassName() {
		return Alert.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("alertId", getAlertId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("arretId", getArretId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("ligneAndDirection", getLigneAndDirection());
		attributes.put("perturbation", getPerturbation());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long alertId = (Long)attributes.get("alertId");

		if (alertId != null) {
			setAlertId(alertId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long arretId = (Long)attributes.get("arretId");

		if (arretId != null) {
			setArretId(arretId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String ligneAndDirection = (String)attributes.get("ligneAndDirection");

		if (ligneAndDirection != null) {
			setLigneAndDirection(ligneAndDirection);
		}

		String perturbation = (String)attributes.get("perturbation");

		if (perturbation != null) {
			setPerturbation(perturbation);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _alert.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _alert.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _alert.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _alert.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.gtfs.model.Alert> toCacheModel() {
		return _alert.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Alert toEscapedModel() {
		return new AlertWrapper(_alert.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Alert toUnescapedModel() {
		return new AlertWrapper(_alert.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.gtfs.model.Alert alert) {
		return _alert.compareTo(alert);
	}

	@Override
	public int hashCode() {
		return _alert.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _alert.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AlertWrapper((Alert)_alert.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _alert.getDefaultLanguageId();
	}

	/**
	* Returns the ligne and direction of this alert.
	*
	* @return the ligne and direction of this alert
	*/
	@Override
	public java.lang.String getLigneAndDirection() {
		return _alert.getLigneAndDirection();
	}

	/**
	* Returns the localized ligne and direction of this alert in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized ligne and direction of this alert
	*/
	@Override
	public java.lang.String getLigneAndDirection(java.lang.String languageId) {
		return _alert.getLigneAndDirection(languageId);
	}

	/**
	* Returns the localized ligne and direction of this alert in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized ligne and direction of this alert
	*/
	@Override
	public java.lang.String getLigneAndDirection(java.lang.String languageId,
		boolean useDefault) {
		return _alert.getLigneAndDirection(languageId, useDefault);
	}

	/**
	* Returns the localized ligne and direction of this alert in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized ligne and direction of this alert
	*/
	@Override
	public java.lang.String getLigneAndDirection(java.util.Locale locale) {
		return _alert.getLigneAndDirection(locale);
	}

	/**
	* Returns the localized ligne and direction of this alert in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized ligne and direction of this alert. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getLigneAndDirection(java.util.Locale locale,
		boolean useDefault) {
		return _alert.getLigneAndDirection(locale, useDefault);
	}

	@Override
	public java.lang.String getLigneAndDirectionCurrentLanguageId() {
		return _alert.getLigneAndDirectionCurrentLanguageId();
	}

	@Override
	public java.lang.String getLigneAndDirectionCurrentValue() {
		return _alert.getLigneAndDirectionCurrentValue();
	}

	/**
	* Returns the perturbation of this alert.
	*
	* @return the perturbation of this alert
	*/
	@Override
	public java.lang.String getPerturbation() {
		return _alert.getPerturbation();
	}

	/**
	* Returns the localized perturbation of this alert in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized perturbation of this alert
	*/
	@Override
	public java.lang.String getPerturbation(java.lang.String languageId) {
		return _alert.getPerturbation(languageId);
	}

	/**
	* Returns the localized perturbation of this alert in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized perturbation of this alert
	*/
	@Override
	public java.lang.String getPerturbation(java.lang.String languageId,
		boolean useDefault) {
		return _alert.getPerturbation(languageId, useDefault);
	}

	/**
	* Returns the localized perturbation of this alert in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized perturbation of this alert
	*/
	@Override
	public java.lang.String getPerturbation(java.util.Locale locale) {
		return _alert.getPerturbation(locale);
	}

	/**
	* Returns the localized perturbation of this alert in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized perturbation of this alert. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPerturbation(java.util.Locale locale,
		boolean useDefault) {
		return _alert.getPerturbation(locale, useDefault);
	}

	@Override
	public java.lang.String getPerturbationCurrentLanguageId() {
		return _alert.getPerturbationCurrentLanguageId();
	}

	@Override
	public java.lang.String getPerturbationCurrentValue() {
		return _alert.getPerturbationCurrentValue();
	}

	/**
	* Returns the uuid of this alert.
	*
	* @return the uuid of this alert
	*/
	@Override
	public java.lang.String getUuid() {
		return _alert.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _alert.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _alert.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _alert.getAvailableLanguageIds();
	}

	/**
	* Returns the end date of this alert.
	*
	* @return the end date of this alert
	*/
	@Override
	public Date getEndDate() {
		return _alert.getEndDate();
	}

	/**
	* Returns the start date of this alert.
	*
	* @return the start date of this alert
	*/
	@Override
	public Date getStartDate() {
		return _alert.getStartDate();
	}

	/**
	* Returns a map of the locales and localized ligne and directions of this alert.
	*
	* @return the locales and localized ligne and directions of this alert
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getLigneAndDirectionMap() {
		return _alert.getLigneAndDirectionMap();
	}

	/**
	* Returns a map of the locales and localized perturbations of this alert.
	*
	* @return the locales and localized perturbations of this alert
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getPerturbationMap() {
		return _alert.getPerturbationMap();
	}

	/**
	* Returns the alert ID of this alert.
	*
	* @return the alert ID of this alert
	*/
	@Override
	public long getAlertId() {
		return _alert.getAlertId();
	}

	/**
	* Returns the arret ID of this alert.
	*
	* @return the arret ID of this alert
	*/
	@Override
	public long getArretId() {
		return _alert.getArretId();
	}

	/**
	* Returns the company ID of this alert.
	*
	* @return the company ID of this alert
	*/
	@Override
	public long getCompanyId() {
		return _alert.getCompanyId();
	}

	/**
	* Returns the group ID of this alert.
	*
	* @return the group ID of this alert
	*/
	@Override
	public long getGroupId() {
		return _alert.getGroupId();
	}

	/**
	* Returns the primary key of this alert.
	*
	* @return the primary key of this alert
	*/
	@Override
	public long getPrimaryKey() {
		return _alert.getPrimaryKey();
	}

	@Override
	public void persist() {
		_alert.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_alert.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_alert.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the alert ID of this alert.
	*
	* @param alertId the alert ID of this alert
	*/
	@Override
	public void setAlertId(long alertId) {
		_alert.setAlertId(alertId);
	}

	/**
	* Sets the arret ID of this alert.
	*
	* @param arretId the arret ID of this alert
	*/
	@Override
	public void setArretId(long arretId) {
		_alert.setArretId(arretId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_alert.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this alert.
	*
	* @param companyId the company ID of this alert
	*/
	@Override
	public void setCompanyId(long companyId) {
		_alert.setCompanyId(companyId);
	}

	/**
	* Sets the end date of this alert.
	*
	* @param endDate the end date of this alert
	*/
	@Override
	public void setEndDate(Date endDate) {
		_alert.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_alert.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_alert.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_alert.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this alert.
	*
	* @param groupId the group ID of this alert
	*/
	@Override
	public void setGroupId(long groupId) {
		_alert.setGroupId(groupId);
	}

	/**
	* Sets the ligne and direction of this alert.
	*
	* @param ligneAndDirection the ligne and direction of this alert
	*/
	@Override
	public void setLigneAndDirection(java.lang.String ligneAndDirection) {
		_alert.setLigneAndDirection(ligneAndDirection);
	}

	/**
	* Sets the localized ligne and direction of this alert in the language.
	*
	* @param ligneAndDirection the localized ligne and direction of this alert
	* @param locale the locale of the language
	*/
	@Override
	public void setLigneAndDirection(java.lang.String ligneAndDirection,
		java.util.Locale locale) {
		_alert.setLigneAndDirection(ligneAndDirection, locale);
	}

	/**
	* Sets the localized ligne and direction of this alert in the language, and sets the default locale.
	*
	* @param ligneAndDirection the localized ligne and direction of this alert
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setLigneAndDirection(java.lang.String ligneAndDirection,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_alert.setLigneAndDirection(ligneAndDirection, locale, defaultLocale);
	}

	@Override
	public void setLigneAndDirectionCurrentLanguageId(
		java.lang.String languageId) {
		_alert.setLigneAndDirectionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized ligne and directions of this alert from the map of locales and localized ligne and directions.
	*
	* @param ligneAndDirectionMap the locales and localized ligne and directions of this alert
	*/
	@Override
	public void setLigneAndDirectionMap(
		Map<java.util.Locale, java.lang.String> ligneAndDirectionMap) {
		_alert.setLigneAndDirectionMap(ligneAndDirectionMap);
	}

	/**
	* Sets the localized ligne and directions of this alert from the map of locales and localized ligne and directions, and sets the default locale.
	*
	* @param ligneAndDirectionMap the locales and localized ligne and directions of this alert
	* @param defaultLocale the default locale
	*/
	@Override
	public void setLigneAndDirectionMap(
		Map<java.util.Locale, java.lang.String> ligneAndDirectionMap,
		java.util.Locale defaultLocale) {
		_alert.setLigneAndDirectionMap(ligneAndDirectionMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_alert.setNew(n);
	}

	/**
	* Sets the perturbation of this alert.
	*
	* @param perturbation the perturbation of this alert
	*/
	@Override
	public void setPerturbation(java.lang.String perturbation) {
		_alert.setPerturbation(perturbation);
	}

	/**
	* Sets the localized perturbation of this alert in the language.
	*
	* @param perturbation the localized perturbation of this alert
	* @param locale the locale of the language
	*/
	@Override
	public void setPerturbation(java.lang.String perturbation,
		java.util.Locale locale) {
		_alert.setPerturbation(perturbation, locale);
	}

	/**
	* Sets the localized perturbation of this alert in the language, and sets the default locale.
	*
	* @param perturbation the localized perturbation of this alert
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPerturbation(java.lang.String perturbation,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_alert.setPerturbation(perturbation, locale, defaultLocale);
	}

	@Override
	public void setPerturbationCurrentLanguageId(java.lang.String languageId) {
		_alert.setPerturbationCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized perturbations of this alert from the map of locales and localized perturbations.
	*
	* @param perturbationMap the locales and localized perturbations of this alert
	*/
	@Override
	public void setPerturbationMap(
		Map<java.util.Locale, java.lang.String> perturbationMap) {
		_alert.setPerturbationMap(perturbationMap);
	}

	/**
	* Sets the localized perturbations of this alert from the map of locales and localized perturbations, and sets the default locale.
	*
	* @param perturbationMap the locales and localized perturbations of this alert
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPerturbationMap(
		Map<java.util.Locale, java.lang.String> perturbationMap,
		java.util.Locale defaultLocale) {
		_alert.setPerturbationMap(perturbationMap, defaultLocale);
	}

	/**
	* Sets the primary key of this alert.
	*
	* @param primaryKey the primary key of this alert
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_alert.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_alert.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start date of this alert.
	*
	* @param startDate the start date of this alert
	*/
	@Override
	public void setStartDate(Date startDate) {
		_alert.setStartDate(startDate);
	}

	/**
	* Sets the uuid of this alert.
	*
	* @param uuid the uuid of this alert
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_alert.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AlertWrapper)) {
			return false;
		}

		AlertWrapper alertWrapper = (AlertWrapper)obj;

		if (Objects.equals(_alert, alertWrapper._alert)) {
			return true;
		}

		return false;
	}

	@Override
	public Alert getWrappedModel() {
		return _alert;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _alert.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _alert.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_alert.resetOriginalValues();
	}

	private final Alert _alert;
}