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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.models.Pair;

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
	private List<Pair<LocalTime, LocalTime>> openingTimes;
	private Boolean closed = false;
	private Boolean alwaysOpen = false;
	private Boolean publicHoliday = false;
	private Boolean exception = false;
	private String description;
	private String[] comments = new String[0];

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
	
	public static PlaceSchedule fromSlots(List<Slot> slots, boolean alwaysOpen) {
		PlaceSchedule placeSchedule = new PlaceSchedule();
		if (alwaysOpen) {
			placeSchedule.setAlwaysOpen(true);
		} else if (slots.isEmpty()) {
			placeSchedule.setClosed(true);
		} else {
			List<Pair<LocalTime, LocalTime>> openingTimes = new ArrayList<Pair<LocalTime, LocalTime>>();
			for (Slot slot : slots) {
				
				String[] startTimeParts = slot.getStartHour().split(":");
				LocalTime startTime = LocalTime.of(Integer.parseInt(startTimeParts[0]),
						Integer.parseInt(startTimeParts[1]));
				
				String[] endTimeParts= slot.getEndHour().split(":");
				LocalTime endTime = LocalTime.of(Integer.parseInt(endTimeParts[0]),
						Integer.parseInt(endTimeParts[1]));
				
				openingTimes.add(Pair.of(startTime, endTime));	
			}
			placeSchedule.setOpeningTimes(openingTimes);
		}
		String[] comments = new String[slots.size()];
		for (int i = 0; i < slots.size(); i++) {
			String slotComment = slots.get(i).getComment(Locale.FRANCE);
			if (Validator.isNotNull(slotComment)) {
				comments[i] = slotComment;
			}
		}
		placeSchedule.setComments(comments);
		return placeSchedule;
	}
	
	public static PlaceSchedule createClosedSchedule() {
		PlaceSchedule placeSchedule = new PlaceSchedule();
		placeSchedule.setClosed(true);
		return placeSchedule;
	}
	
	public String getPeriodDisplay(Locale locale) {
		return DateHelper.displayPeriod(this.getStartDate(), this.getEndDate(), locale);
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

	public List<Pair<LocalTime, LocalTime>> getOpeningTimes() {
		return openingTimes;
	}

	public void setOpeningTimes(List<Pair<LocalTime, LocalTime>> openingTimes) {
		this.openingTimes = openingTimes;
	}

	public String[] getComments() {
		return comments;
	}

	public void setComments(String[] comments) {
		this.comments = comments;
	}
}