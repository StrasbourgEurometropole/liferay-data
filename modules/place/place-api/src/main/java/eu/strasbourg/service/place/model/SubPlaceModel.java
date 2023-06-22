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
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the SubPlace service. Represents a row in the &quot;place_SubPlace&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>eu.strasbourg.service.place.model.impl.SubPlaceModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>eu.strasbourg.service.place.model.impl.SubPlaceImpl</code>.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see SubPlace
 * @generated
 */
@ProviderType
public interface SubPlaceModel
	extends BaseModel<SubPlace>, LocalizedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a sub place model instance should use the {@link SubPlace} interface instead.
	 */

	/**
	 * Returns the primary key of this sub place.
	 *
	 * @return the primary key of this sub place
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this sub place.
	 *
	 * @param primaryKey the primary key of this sub place
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this sub place.
	 *
	 * @return the uuid of this sub place
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this sub place.
	 *
	 * @param uuid the uuid of this sub place
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the sub place ID of this sub place.
	 *
	 * @return the sub place ID of this sub place
	 */
	public long getSubPlaceId();

	/**
	 * Sets the sub place ID of this sub place.
	 *
	 * @param subPlaceId the sub place ID of this sub place
	 */
	public void setSubPlaceId(long subPlaceId);

	/**
	 * Returns the status of this sub place.
	 *
	 * @return the status of this sub place
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this sub place.
	 *
	 * @param status the status of this sub place
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this sub place.
	 *
	 * @return the status by user ID of this sub place
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this sub place.
	 *
	 * @param statusByUserId the status by user ID of this sub place
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this sub place.
	 *
	 * @return the status by user uuid of this sub place
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this sub place.
	 *
	 * @param statusByUserUuid the status by user uuid of this sub place
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this sub place.
	 *
	 * @return the status by user name of this sub place
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this sub place.
	 *
	 * @param statusByUserName the status by user name of this sub place
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this sub place.
	 *
	 * @return the status date of this sub place
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this sub place.
	 *
	 * @param statusDate the status date of this sub place
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the name of this sub place.
	 *
	 * @return the name of this sub place
	 */
	public String getName();

	/**
	 * Returns the localized name of this sub place in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this sub place
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this sub place in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this sub place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this sub place in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this sub place
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this sub place in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this sub place
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this sub place.
	 *
	 * @return the locales and localized names of this sub place
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this sub place.
	 *
	 * @param name the name of this sub place
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this sub place in the language.
	 *
	 * @param name the localized name of this sub place
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this sub place in the language, and sets the default locale.
	 *
	 * @param name the localized name of this sub place
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this sub place from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this sub place
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this sub place from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this sub place
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this sub place.
	 *
	 * @return the description of this sub place
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this sub place in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this sub place
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this sub place in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this sub place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this sub place in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this sub place
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this sub place in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this sub place
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this sub place.
	 *
	 * @return the locales and localized descriptions of this sub place
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this sub place.
	 *
	 * @param description the description of this sub place
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this sub place in the language.
	 *
	 * @param description the localized description of this sub place
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this sub place in the language, and sets the default locale.
	 *
	 * @param description the localized description of this sub place
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this sub place from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this sub place
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this sub place from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this sub place
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	/**
	 * Returns the place ID of this sub place.
	 *
	 * @return the place ID of this sub place
	 */
	public long getPlaceId();

	/**
	 * Sets the place ID of this sub place.
	 *
	 * @param placeId the place ID of this sub place
	 */
	public void setPlaceId(long placeId);

	/**
	 * Returns <code>true</code> if this sub place is approved.
	 *
	 * @return <code>true</code> if this sub place is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this sub place is denied.
	 *
	 * @return <code>true</code> if this sub place is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this sub place is a draft.
	 *
	 * @return <code>true</code> if this sub place is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this sub place is expired.
	 *
	 * @return <code>true</code> if this sub place is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this sub place is inactive.
	 *
	 * @return <code>true</code> if this sub place is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this sub place is incomplete.
	 *
	 * @return <code>true</code> if this sub place is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this sub place is pending.
	 *
	 * @return <code>true</code> if this sub place is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this sub place is scheduled.
	 *
	 * @return <code>true</code> if this sub place is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public Object clone();

	@Override
	public int compareTo(eu.strasbourg.service.place.model.SubPlace subPlace);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.place.model.SubPlace>
		toCacheModel();

	@Override
	public eu.strasbourg.service.place.model.SubPlace toEscapedModel();

	@Override
	public eu.strasbourg.service.place.model.SubPlace toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}