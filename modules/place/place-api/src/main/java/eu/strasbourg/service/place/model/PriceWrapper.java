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

package eu.strasbourg.service.place.model;

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
 * This class is a wrapper for {@link Price}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see Price
 * @generated
 */
@ProviderType
public class PriceWrapper implements Price, ModelWrapper<Price> {
	public PriceWrapper(Price price) {
		_price = price;
	}

	@Override
	public Class<?> getModelClass() {
		return Price.class;
	}

	@Override
	public String getModelClassName() {
		return Price.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("priceId", getPriceId());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("title", getTitle());
		attributes.put("price", getPrice());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long priceId = (Long)attributes.get("priceId");

		if (priceId != null) {
			setPriceId(priceId);
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

		String price = (String)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}
	}

	/**
	* Returns <code>true</code> if this price is approved.
	*
	* @return <code>true</code> if this price is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _price.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _price.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this price is denied.
	*
	* @return <code>true</code> if this price is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _price.isDenied();
	}

	/**
	* Returns <code>true</code> if this price is a draft.
	*
	* @return <code>true</code> if this price is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _price.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _price.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this price is expired.
	*
	* @return <code>true</code> if this price is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _price.isExpired();
	}

	/**
	* Returns <code>true</code> if this price is inactive.
	*
	* @return <code>true</code> if this price is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _price.isInactive();
	}

	/**
	* Returns <code>true</code> if this price is incomplete.
	*
	* @return <code>true</code> if this price is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _price.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _price.isNew();
	}

	/**
	* Returns <code>true</code> if this price is pending.
	*
	* @return <code>true</code> if this price is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _price.isPending();
	}

	/**
	* Returns <code>true</code> if this price is scheduled.
	*
	* @return <code>true</code> if this price is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _price.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _price.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.place.model.Price> toCacheModel() {
		return _price.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.place.model.Price toEscapedModel() {
		return new PriceWrapper(_price.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.place.model.Price toUnescapedModel() {
		return new PriceWrapper(_price.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.place.model.Price price) {
		return _price.compareTo(price);
	}

	/**
	* Returns the status of this price.
	*
	* @return the status of this price
	*/
	@Override
	public int getStatus() {
		return _price.getStatus();
	}

	@Override
	public int hashCode() {
		return _price.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _price.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PriceWrapper((Price)_price.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _price.getDefaultLanguageId();
	}

	/**
	* Renvoie la liste des IDs des lieux auxquelles ce tarif
	* appartient sous forme de String
	*/
	@Override
	public java.lang.String getPlacesIds() {
		return _price.getPlacesIds();
	}

	/**
	* Returns the price of this price.
	*
	* @return the price of this price
	*/
	@Override
	public java.lang.String getPrice() {
		return _price.getPrice();
	}

	/**
	* Returns the localized price of this price in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized price of this price
	*/
	@Override
	public java.lang.String getPrice(java.lang.String languageId) {
		return _price.getPrice(languageId);
	}

	/**
	* Returns the localized price of this price in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized price of this price
	*/
	@Override
	public java.lang.String getPrice(java.lang.String languageId,
		boolean useDefault) {
		return _price.getPrice(languageId, useDefault);
	}

	/**
	* Returns the localized price of this price in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized price of this price
	*/
	@Override
	public java.lang.String getPrice(java.util.Locale locale) {
		return _price.getPrice(locale);
	}

	/**
	* Returns the localized price of this price in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized price of this price. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPrice(java.util.Locale locale, boolean useDefault) {
		return _price.getPrice(locale, useDefault);
	}

	@Override
	public java.lang.String getPriceCurrentLanguageId() {
		return _price.getPriceCurrentLanguageId();
	}

	@Override
	public java.lang.String getPriceCurrentValue() {
		return _price.getPriceCurrentValue();
	}

	/**
	* Returns the status by user name of this price.
	*
	* @return the status by user name of this price
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _price.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this price.
	*
	* @return the status by user uuid of this price
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _price.getStatusByUserUuid();
	}

	/**
	* Returns the title of this price.
	*
	* @return the title of this price
	*/
	@Override
	public java.lang.String getTitle() {
		return _price.getTitle();
	}

	/**
	* Returns the localized title of this price in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this price
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _price.getTitle(languageId);
	}

	/**
	* Returns the localized title of this price in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this price
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _price.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this price in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this price
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _price.getTitle(locale);
	}

	/**
	* Returns the localized title of this price in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this price. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _price.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _price.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _price.getTitleCurrentValue();
	}

	/**
	* Returns the uuid of this price.
	*
	* @return the uuid of this price
	*/
	@Override
	public java.lang.String getUuid() {
		return _price.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _price.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _price.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _price.getAvailableLanguageIds();
	}

	/**
	* Returns the status date of this price.
	*
	* @return the status date of this price
	*/
	@Override
	public Date getStatusDate() {
		return _price.getStatusDate();
	}

	/**
	* Retourne les lieux du tarif
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Place> getPlaces() {
		return _price.getPlaces();
	}

	/**
	* Returns a map of the locales and localized prices of this price.
	*
	* @return the locales and localized prices of this price
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getPriceMap() {
		return _price.getPriceMap();
	}

	/**
	* Returns a map of the locales and localized titles of this price.
	*
	* @return the locales and localized titles of this price
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _price.getTitleMap();
	}

	/**
	* Returns the price ID of this price.
	*
	* @return the price ID of this price
	*/
	@Override
	public long getPriceId() {
		return _price.getPriceId();
	}

	/**
	* Returns the primary key of this price.
	*
	* @return the primary key of this price
	*/
	@Override
	public long getPrimaryKey() {
		return _price.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this price.
	*
	* @return the status by user ID of this price
	*/
	@Override
	public long getStatusByUserId() {
		return _price.getStatusByUserId();
	}

	@Override
	public void persist() {
		_price.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_price.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_price.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_price.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_price.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_price.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_price.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_price.setNew(n);
	}

	/**
	* Sets the price of this price.
	*
	* @param price the price of this price
	*/
	@Override
	public void setPrice(java.lang.String price) {
		_price.setPrice(price);
	}

	/**
	* Sets the localized price of this price in the language.
	*
	* @param price the localized price of this price
	* @param locale the locale of the language
	*/
	@Override
	public void setPrice(java.lang.String price, java.util.Locale locale) {
		_price.setPrice(price, locale);
	}

	/**
	* Sets the localized price of this price in the language, and sets the default locale.
	*
	* @param price the localized price of this price
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPrice(java.lang.String price, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_price.setPrice(price, locale, defaultLocale);
	}

	@Override
	public void setPriceCurrentLanguageId(java.lang.String languageId) {
		_price.setPriceCurrentLanguageId(languageId);
	}

	/**
	* Sets the price ID of this price.
	*
	* @param priceId the price ID of this price
	*/
	@Override
	public void setPriceId(long priceId) {
		_price.setPriceId(priceId);
	}

	/**
	* Sets the localized prices of this price from the map of locales and localized prices.
	*
	* @param priceMap the locales and localized prices of this price
	*/
	@Override
	public void setPriceMap(Map<java.util.Locale, java.lang.String> priceMap) {
		_price.setPriceMap(priceMap);
	}

	/**
	* Sets the localized prices of this price from the map of locales and localized prices, and sets the default locale.
	*
	* @param priceMap the locales and localized prices of this price
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPriceMap(Map<java.util.Locale, java.lang.String> priceMap,
		java.util.Locale defaultLocale) {
		_price.setPriceMap(priceMap, defaultLocale);
	}

	/**
	* Sets the primary key of this price.
	*
	* @param primaryKey the primary key of this price
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_price.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_price.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this price.
	*
	* @param status the status of this price
	*/
	@Override
	public void setStatus(int status) {
		_price.setStatus(status);
	}

	/**
	* Sets the status by user ID of this price.
	*
	* @param statusByUserId the status by user ID of this price
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_price.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this price.
	*
	* @param statusByUserName the status by user name of this price
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_price.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this price.
	*
	* @param statusByUserUuid the status by user uuid of this price
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_price.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this price.
	*
	* @param statusDate the status date of this price
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_price.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this price.
	*
	* @param title the title of this price
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_price.setTitle(title);
	}

	/**
	* Sets the localized title of this price in the language.
	*
	* @param title the localized title of this price
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_price.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this price in the language, and sets the default locale.
	*
	* @param title the localized title of this price
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_price.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_price.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this price from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this price
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_price.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this price from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this price
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_price.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the uuid of this price.
	*
	* @param uuid the uuid of this price
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_price.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PriceWrapper)) {
			return false;
		}

		PriceWrapper priceWrapper = (PriceWrapper)obj;

		if (Objects.equals(_price, priceWrapper._price)) {
			return true;
		}

		return false;
	}

	@Override
	public Price getWrappedModel() {
		return _price;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _price.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _price.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_price.resetOriginalValues();
	}

	private final Price _price;
}