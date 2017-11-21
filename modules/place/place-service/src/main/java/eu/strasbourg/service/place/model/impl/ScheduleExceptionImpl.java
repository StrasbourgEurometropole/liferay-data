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

package eu.strasbourg.service.place.model.impl;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.models.Pair;

/**
 * The extended model implementation for the ScheduleException service.
 * Represents a row in the &quot;place_ScheduleException&quot; database table,
 * with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.place.model.ScheduleException}
 * interface.
 * </p>
 *
 * @author Angelique Zunino Champougny
 */
@ProviderType
public class ScheduleExceptionImpl extends ScheduleExceptionBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a schedule
	 * exception model instance should use the {@link
	 * eu.strasbourg.service.place.model.ScheduleException} interface instead.
	 */
	public ScheduleExceptionImpl() {
	}

	/**
	 * Retourne la version JSON des exceptions
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject scheduleExceptionJSON = JSONFactoryUtil.createJSONObject();

		scheduleExceptionJSON.put("description",
				JSONHelper.getJSONFromI18nMap(this.getCommentMap()));
		DateFormat dateFormat = DateFormatFactoryUtil
				.getSimpleDateFormat("yyyy-MM-dd");
		scheduleExceptionJSON.put("startDate",
				dateFormat.format(this.getStartDate()));
		scheduleExceptionJSON.put("endDate",
				dateFormat.format(this.getEndDate()));
		scheduleExceptionJSON.put("closed", this.getClosed());
		if (!this.getClosed()) {
			JSONArray exceptionOpenings = JSONFactoryUtil.createJSONArray();
			for (Pair<LocalTime, LocalTime> openingTime : this.getOpeningLocalTimes()) {
				JSONObject exceptionOpening = JSONFactoryUtil.createJSONObject();
				String startTimeString = openingTime.getFirst().toString();
				String endTimeString = openingTime.getSecond().toString();
				exceptionOpening.put("startHour", startTimeString);
				exceptionOpening.put("endHour", endTimeString);
				exceptionOpenings.put(exceptionOpening);
			}
			scheduleExceptionJSON.put("schedule", exceptionOpenings);
		}
		

		return scheduleExceptionJSON;
	}
	
	/**
	 * Retourne la liste des horaires d'ouvertures d'une exception
	 */
	@Override
	public List<Pair<LocalTime, LocalTime>> getOpeningLocalTimes() {
		List<Pair<LocalTime, LocalTime>> openingLocalTimes = new ArrayList<Pair<LocalTime, LocalTime>>();
		for (String openingTimeString : this.getOpeningTimes().split(";")) {
			if (Validator.isNull(openingTimeString) || !openingTimeString.contains("-")) {
				continue;
			}
			String startTimeString = openingTimeString.split("-")[0];
			String endTimeString = openingTimeString.split("-")[1];
			if (Validator.isNull(startTimeString) || !startTimeString.contains(":")) {
				continue;
			}
			if (Validator.isNull(endTimeString) || !endTimeString.contains(":")) {
				continue;
			}
			try {
				int startTimeHour = Integer.parseInt(startTimeString.split(":")[0]);
				int startTimeMinute = Integer.parseInt(startTimeString.split(":")[1]);
				int endTimeHour = Integer.parseInt(endTimeString.split(":")[0]);
				int endTimeMinute = Integer.parseInt(endTimeString.split(":")[1]);
				LocalTime startTime = LocalTime.of(startTimeHour, startTimeMinute);
				LocalTime endTime = LocalTime.of(endTimeHour, endTimeMinute);
				Pair<LocalTime, LocalTime> openingTime = Pair.of(startTime, endTime);
				openingLocalTimes.add(openingTime);
			} catch (Exception e) {
				continue;
			}
		}
		return openingLocalTimes;
	}
	
	/**
	 * Retourne la Nème heure de début
	 */
	@Override
	public LocalTime getStartHour(int index) {
		List<Pair<LocalTime, LocalTime>> openingLocalTimes = getOpeningLocalTimes();
		if (openingLocalTimes.size() <= index) {
			return null;
		}
		return openingLocalTimes.get(index).getFirst();
	}

	/**
	 * Retourne la Nème heure de début
	 */
	@Override
	public LocalTime getEndHour(int index) {
		List<Pair<LocalTime, LocalTime>> openingLocalTimes = getOpeningLocalTimes();
		if (openingLocalTimes.size() <= index) {
			return null;
		}
		return openingLocalTimes.get(index).getSecond();
	}
}