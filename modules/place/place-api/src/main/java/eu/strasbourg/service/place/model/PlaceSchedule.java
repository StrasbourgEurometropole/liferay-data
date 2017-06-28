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

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.utils.DateHelper;

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
	private String period;
	private Date startDate;
	private Date endDate;
	private LocalTime startTime = LocalTime.of(0, 0);
	private LocalTime endTime = LocalTime.of(0, 0);
	private Boolean closed = false;
	private Boolean alwaysOpen = false;
	private Boolean publicHoliday = false;
	private Boolean exception = false;
	private String description;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a place
	 * model instance should use the {@link
	 * eu.strasbourg.service.place.model.Place} interface instead.
	 */
	public PlaceSchedule() {
	}
	
	public PlaceSchedule(long idSchedule, Date startDate, Date endDate, String description, Locale locale) {
		this.setIdSchedule(idSchedule);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setPeriod(DateHelper.displayPeriod(startDate, endDate, locale));
		this.setDescription(description);
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

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
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
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}