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

package eu.strasbourg.service.csmap.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link Agenda}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Agenda
 * @generated
 */
@ProviderType
public class AgendaWrapper
	extends BaseModelWrapper<Agenda> implements Agenda, ModelWrapper<Agenda> {

	public AgendaWrapper(Agenda agenda) {
		super(agenda);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("agendaId", getAgendaId());
		attributes.put("title", getTitle());
		attributes.put("editorialTitle", getEditorialTitle());
		attributes.put("subtitle", getSubtitle());
		attributes.put("imageId", getImageId());
		attributes.put("isPrincipal", getIsPrincipal());
		attributes.put("isActive", getIsActive());
		attributes.put("campaignsIds", getCampaignsIds());
		attributes.put("themesIds", getThemesIds());
		attributes.put("typesIds", getTypesIds());
		attributes.put("territoriesIds", getTerritoriesIds());
		attributes.put("tags", getTags());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long agendaId = (Long)attributes.get("agendaId");

		if (agendaId != null) {
			setAgendaId(agendaId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String editorialTitle = (String)attributes.get("editorialTitle");

		if (editorialTitle != null) {
			setEditorialTitle(editorialTitle);
		}

		String subtitle = (String)attributes.get("subtitle");

		if (subtitle != null) {
			setSubtitle(subtitle);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		Boolean isPrincipal = (Boolean)attributes.get("isPrincipal");

		if (isPrincipal != null) {
			setIsPrincipal(isPrincipal);
		}

		Boolean isActive = (Boolean)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}

		String campaignsIds = (String)attributes.get("campaignsIds");

		if (campaignsIds != null) {
			setCampaignsIds(campaignsIds);
		}

		String themesIds = (String)attributes.get("themesIds");

		if (themesIds != null) {
			setThemesIds(themesIds);
		}

		String typesIds = (String)attributes.get("typesIds");

		if (typesIds != null) {
			setTypesIds(typesIds);
		}

		String territoriesIds = (String)attributes.get("territoriesIds");

		if (territoriesIds != null) {
			setTerritoriesIds(territoriesIds);
		}

		String tags = (String)attributes.get("tags");

		if (tags != null) {
			setTags(tags);
		}
	}

	/**
	 * Returns the agenda ID of this agenda.
	 *
	 * @return the agenda ID of this agenda
	 */
	@Override
	public long getAgendaId() {
		return model.getAgendaId();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return model.getAvailableLanguageIds();
	}

	/**
	 * Returns the campaigns IDs of this agenda.
	 *
	 * @return the campaigns IDs of this agenda
	 */
	@Override
	public String getCampaignsIds() {
		return model.getCampaignsIds();
	}

	@Override
	public String getDefaultLanguageId() {
		return model.getDefaultLanguageId();
	}

	/**
	 * Returns the editorial title of this agenda.
	 *
	 * @return the editorial title of this agenda
	 */
	@Override
	public String getEditorialTitle() {
		return model.getEditorialTitle();
	}

	/**
	 * Returns the localized editorial title of this agenda in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized editorial title of this agenda
	 */
	@Override
	public String getEditorialTitle(java.util.Locale locale) {
		return model.getEditorialTitle(locale);
	}

	/**
	 * Returns the localized editorial title of this agenda in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized editorial title of this agenda. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getEditorialTitle(
		java.util.Locale locale, boolean useDefault) {

		return model.getEditorialTitle(locale, useDefault);
	}

	/**
	 * Returns the localized editorial title of this agenda in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized editorial title of this agenda
	 */
	@Override
	public String getEditorialTitle(String languageId) {
		return model.getEditorialTitle(languageId);
	}

	/**
	 * Returns the localized editorial title of this agenda in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized editorial title of this agenda
	 */
	@Override
	public String getEditorialTitle(String languageId, boolean useDefault) {
		return model.getEditorialTitle(languageId, useDefault);
	}

	@Override
	public String getEditorialTitleCurrentLanguageId() {
		return model.getEditorialTitleCurrentLanguageId();
	}

	@Override
	public String getEditorialTitleCurrentValue() {
		return model.getEditorialTitleCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized editorial titles of this agenda.
	 *
	 * @return the locales and localized editorial titles of this agenda
	 */
	@Override
	public Map<java.util.Locale, String> getEditorialTitleMap() {
		return model.getEditorialTitleMap();
	}

	/**
	 * Returns the image ID of this agenda.
	 *
	 * @return the image ID of this agenda
	 */
	@Override
	public Long getImageId() {
		return model.getImageId();
	}

	/**
	 * Returns the is active of this agenda.
	 *
	 * @return the is active of this agenda
	 */
	@Override
	public Boolean getIsActive() {
		return model.getIsActive();
	}

	/**
	 * Returns the is principal of this agenda.
	 *
	 * @return the is principal of this agenda
	 */
	@Override
	public Boolean getIsPrincipal() {
		return model.getIsPrincipal();
	}

	/**
	 * Returns the primary key of this agenda.
	 *
	 * @return the primary key of this agenda
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the subtitle of this agenda.
	 *
	 * @return the subtitle of this agenda
	 */
	@Override
	public String getSubtitle() {
		return model.getSubtitle();
	}

	/**
	 * Returns the localized subtitle of this agenda in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized subtitle of this agenda
	 */
	@Override
	public String getSubtitle(java.util.Locale locale) {
		return model.getSubtitle(locale);
	}

	/**
	 * Returns the localized subtitle of this agenda in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized subtitle of this agenda. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getSubtitle(java.util.Locale locale, boolean useDefault) {
		return model.getSubtitle(locale, useDefault);
	}

	/**
	 * Returns the localized subtitle of this agenda in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized subtitle of this agenda
	 */
	@Override
	public String getSubtitle(String languageId) {
		return model.getSubtitle(languageId);
	}

	/**
	 * Returns the localized subtitle of this agenda in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized subtitle of this agenda
	 */
	@Override
	public String getSubtitle(String languageId, boolean useDefault) {
		return model.getSubtitle(languageId, useDefault);
	}

	@Override
	public String getSubtitleCurrentLanguageId() {
		return model.getSubtitleCurrentLanguageId();
	}

	@Override
	public String getSubtitleCurrentValue() {
		return model.getSubtitleCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized subtitles of this agenda.
	 *
	 * @return the locales and localized subtitles of this agenda
	 */
	@Override
	public Map<java.util.Locale, String> getSubtitleMap() {
		return model.getSubtitleMap();
	}

	/**
	 * Returns the tags of this agenda.
	 *
	 * @return the tags of this agenda
	 */
	@Override
	public String getTags() {
		return model.getTags();
	}

	/**
	 * Returns the territories IDs of this agenda.
	 *
	 * @return the territories IDs of this agenda
	 */
	@Override
	public String getTerritoriesIds() {
		return model.getTerritoriesIds();
	}

	/**
	 * Returns the themes IDs of this agenda.
	 *
	 * @return the themes IDs of this agenda
	 */
	@Override
	public String getThemesIds() {
		return model.getThemesIds();
	}

	/**
	 * Returns the title of this agenda.
	 *
	 * @return the title of this agenda
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the localized title of this agenda in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this agenda
	 */
	@Override
	public String getTitle(java.util.Locale locale) {
		return model.getTitle(locale);
	}

	/**
	 * Returns the localized title of this agenda in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this agenda. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getTitle(java.util.Locale locale, boolean useDefault) {
		return model.getTitle(locale, useDefault);
	}

	/**
	 * Returns the localized title of this agenda in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this agenda
	 */
	@Override
	public String getTitle(String languageId) {
		return model.getTitle(languageId);
	}

	/**
	 * Returns the localized title of this agenda in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this agenda
	 */
	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return model.getTitle(languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return model.getTitleCurrentLanguageId();
	}

	@Override
	public String getTitleCurrentValue() {
		return model.getTitleCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized titles of this agenda.
	 *
	 * @return the locales and localized titles of this agenda
	 */
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return model.getTitleMap();
	}

	/**
	 * Returns the types IDs of this agenda.
	 *
	 * @return the types IDs of this agenda
	 */
	@Override
	public String getTypesIds() {
		return model.getTypesIds();
	}

	/**
	 * Returns the uuid of this agenda.
	 *
	 * @return the uuid of this agenda
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		model.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		model.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	 * Sets the agenda ID of this agenda.
	 *
	 * @param agendaId the agenda ID of this agenda
	 */
	@Override
	public void setAgendaId(long agendaId) {
		model.setAgendaId(agendaId);
	}

	/**
	 * Sets the campaigns IDs of this agenda.
	 *
	 * @param campaignsIds the campaigns IDs of this agenda
	 */
	@Override
	public void setCampaignsIds(String campaignsIds) {
		model.setCampaignsIds(campaignsIds);
	}

	/**
	 * Sets the editorial title of this agenda.
	 *
	 * @param editorialTitle the editorial title of this agenda
	 */
	@Override
	public void setEditorialTitle(String editorialTitle) {
		model.setEditorialTitle(editorialTitle);
	}

	/**
	 * Sets the localized editorial title of this agenda in the language.
	 *
	 * @param editorialTitle the localized editorial title of this agenda
	 * @param locale the locale of the language
	 */
	@Override
	public void setEditorialTitle(
		String editorialTitle, java.util.Locale locale) {

		model.setEditorialTitle(editorialTitle, locale);
	}

	/**
	 * Sets the localized editorial title of this agenda in the language, and sets the default locale.
	 *
	 * @param editorialTitle the localized editorial title of this agenda
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setEditorialTitle(
		String editorialTitle, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setEditorialTitle(editorialTitle, locale, defaultLocale);
	}

	@Override
	public void setEditorialTitleCurrentLanguageId(String languageId) {
		model.setEditorialTitleCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized editorial titles of this agenda from the map of locales and localized editorial titles.
	 *
	 * @param editorialTitleMap the locales and localized editorial titles of this agenda
	 */
	@Override
	public void setEditorialTitleMap(
		Map<java.util.Locale, String> editorialTitleMap) {

		model.setEditorialTitleMap(editorialTitleMap);
	}

	/**
	 * Sets the localized editorial titles of this agenda from the map of locales and localized editorial titles, and sets the default locale.
	 *
	 * @param editorialTitleMap the locales and localized editorial titles of this agenda
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setEditorialTitleMap(
		Map<java.util.Locale, String> editorialTitleMap,
		java.util.Locale defaultLocale) {

		model.setEditorialTitleMap(editorialTitleMap, defaultLocale);
	}

	/**
	 * Sets the image ID of this agenda.
	 *
	 * @param imageId the image ID of this agenda
	 */
	@Override
	public void setImageId(Long imageId) {
		model.setImageId(imageId);
	}

	/**
	 * Sets the is active of this agenda.
	 *
	 * @param isActive the is active of this agenda
	 */
	@Override
	public void setIsActive(Boolean isActive) {
		model.setIsActive(isActive);
	}

	/**
	 * Sets the is principal of this agenda.
	 *
	 * @param isPrincipal the is principal of this agenda
	 */
	@Override
	public void setIsPrincipal(Boolean isPrincipal) {
		model.setIsPrincipal(isPrincipal);
	}

	/**
	 * Sets the primary key of this agenda.
	 *
	 * @param primaryKey the primary key of this agenda
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the subtitle of this agenda.
	 *
	 * @param subtitle the subtitle of this agenda
	 */
	@Override
	public void setSubtitle(String subtitle) {
		model.setSubtitle(subtitle);
	}

	/**
	 * Sets the localized subtitle of this agenda in the language.
	 *
	 * @param subtitle the localized subtitle of this agenda
	 * @param locale the locale of the language
	 */
	@Override
	public void setSubtitle(String subtitle, java.util.Locale locale) {
		model.setSubtitle(subtitle, locale);
	}

	/**
	 * Sets the localized subtitle of this agenda in the language, and sets the default locale.
	 *
	 * @param subtitle the localized subtitle of this agenda
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setSubtitle(
		String subtitle, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setSubtitle(subtitle, locale, defaultLocale);
	}

	@Override
	public void setSubtitleCurrentLanguageId(String languageId) {
		model.setSubtitleCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized subtitles of this agenda from the map of locales and localized subtitles.
	 *
	 * @param subtitleMap the locales and localized subtitles of this agenda
	 */
	@Override
	public void setSubtitleMap(Map<java.util.Locale, String> subtitleMap) {
		model.setSubtitleMap(subtitleMap);
	}

	/**
	 * Sets the localized subtitles of this agenda from the map of locales and localized subtitles, and sets the default locale.
	 *
	 * @param subtitleMap the locales and localized subtitles of this agenda
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setSubtitleMap(
		Map<java.util.Locale, String> subtitleMap,
		java.util.Locale defaultLocale) {

		model.setSubtitleMap(subtitleMap, defaultLocale);
	}

	/**
	 * Sets the tags of this agenda.
	 *
	 * @param tags the tags of this agenda
	 */
	@Override
	public void setTags(String tags) {
		model.setTags(tags);
	}

	/**
	 * Sets the territories IDs of this agenda.
	 *
	 * @param territoriesIds the territories IDs of this agenda
	 */
	@Override
	public void setTerritoriesIds(String territoriesIds) {
		model.setTerritoriesIds(territoriesIds);
	}

	/**
	 * Sets the themes IDs of this agenda.
	 *
	 * @param themesIds the themes IDs of this agenda
	 */
	@Override
	public void setThemesIds(String themesIds) {
		model.setThemesIds(themesIds);
	}

	/**
	 * Sets the title of this agenda.
	 *
	 * @param title the title of this agenda
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the localized title of this agenda in the language.
	 *
	 * @param title the localized title of this agenda
	 * @param locale the locale of the language
	 */
	@Override
	public void setTitle(String title, java.util.Locale locale) {
		model.setTitle(title, locale);
	}

	/**
	 * Sets the localized title of this agenda in the language, and sets the default locale.
	 *
	 * @param title the localized title of this agenda
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitle(
		String title, java.util.Locale locale, java.util.Locale defaultLocale) {

		model.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		model.setTitleCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized titles of this agenda from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this agenda
	 */
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		model.setTitleMap(titleMap);
	}

	/**
	 * Sets the localized titles of this agenda from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this agenda
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitleMap(
		Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {

		model.setTitleMap(titleMap, defaultLocale);
	}

	/**
	 * Sets the types IDs of this agenda.
	 *
	 * @param typesIds the types IDs of this agenda
	 */
	@Override
	public void setTypesIds(String typesIds) {
		model.setTypesIds(typesIds);
	}

	/**
	 * Sets the uuid of this agenda.
	 *
	 * @param uuid the uuid of this agenda
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected AgendaWrapper wrap(Agenda agenda) {
		return new AgendaWrapper(agenda);
	}

}