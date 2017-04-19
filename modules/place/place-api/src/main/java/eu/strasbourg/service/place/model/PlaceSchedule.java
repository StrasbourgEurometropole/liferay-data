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

import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model implementation for the Place service. Represents a row in
 * the &quot;place_Place&quot; database table, with each column mapped to a
 * property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.place.model.Place} interface.
 * </p>
 *
 * @author Angelique Zunino Champougny
 */
@ProviderType
public class PlaceSchedule {

	private long idSchedule;
	private Date startDate;
	private Date endDate;
	private LocalTime startTime = LocalTime.of(0, 0);
	private LocalTime endTime = LocalTime.of(0, 0);
	private Boolean closed = false;
	private Boolean alwaysOpen = false;
	private Boolean publicHoliday = false;
	private Boolean exception = false;
	private String _description;
	private String _descriptionCurrentLanguageId;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a place
	 * model instance should use the {@link
	 * eu.strasbourg.service.place.model.Place} interface instead.
	 */
	public PlaceSchedule() {
	}
	
	public PlaceSchedule(long idSchedule, Date startDate, Date endDate, Map<Locale, String> descriptionMap) {
		this.setIdSchedule(idSchedule);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setDescriptionMap(descriptionMap);
	}

	public long getIdSchedule() {
		return idSchedule;
	}

	public void setIdSchedule(long idSchedule) {
		this.idSchedule = idSchedule;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Boolean isClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public Boolean isAlwaysOpen() {
		return alwaysOpen;
	}

	public void setAlwaysOpen(Boolean alwaysOpen) {
		this.alwaysOpen = alwaysOpen;
	}

	public Boolean isPublicHoliday() {
		return publicHoliday;
	}

	public void setPublicHoliday(Boolean publicHoliday) {
		this.publicHoliday = publicHoliday;
	}

	public Boolean isException() {
		return exception;
	}

	public void setException(Boolean exception) {
		this.exception = exception;
	}

	public String getDescription() {
		if (_description == null) {
			return StringPool.BLANK;
		}
		else {
			return _description;
		}
	}

	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getDescription(), languageId,
			useDefault);
	}

	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	public String getDescriptionCurrentValue() {
		Locale locale = null;

		if (_descriptionCurrentLanguageId != null) {
			locale = LocaleUtil.fromLanguageId(_descriptionCurrentLanguageId);
		}

		if (locale == null) {
			locale = LocaleUtil.getMostRelevantLocale();
		}

		return getDescription(locale);
	}

	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	public void setDescription(String name) {
		_description = name;
	}

	public void setDescription(String name, Locale locale) {
		setDescription(name, locale, LocaleUtil.getDefault());
	}

	public void setDescription(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setDescription(LocalizationUtil.updateLocalization(getDescription(), "Name",
					name, languageId, defaultLanguageId));
		}
		else {
			setDescription(LocalizationUtil.removeLocalization(getDescription(), "Name",
					languageId));
		}
	}

	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	public void setDescriptionMap(Map<Locale, String> nameMap) {
		setDescriptionMap(nameMap, LocaleUtil.getDefault());
	}

	public void setDescriptionMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setDescription(LocalizationUtil.updateLocalization(nameMap, getDescription(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
	}
}